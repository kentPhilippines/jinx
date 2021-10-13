package com.ruoyi.web.controller.alipay;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.service.IMerchantInfoEntityService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.MapDataUtil;
import com.ruoyi.common.utils.NoUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.DictionaryUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.web.controller.tool.RandomValue;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 商户信息Controller
 *
 * @author ruoyi
 * @date 2020-03-18
 */
@Controller
@RequestMapping("/alipay/merchant")
public class MerchantInfoEntityController extends BaseController {
    private String prefix = "alipay/merchant/info";
    @Autowired
    private IMerchantInfoEntityService merchantInfoEntityService;
    @Autowired
    private DictionaryUtils dictionaryUtils;
    @Autowired
    private SysPasswordService passwordService;
    @Autowired
    private ISysUserService userService;



    @GetMapping()
    public String merchant() {
        return prefix + "/merchant";
    }


    /**
     * 查询商户信息列表
     */
    @PostMapping("/list")
    @RequiresPermissions("alipay:merchant:list")
    @ResponseBody
    public TableDataInfo list(AlipayUserInfo merchantInfoEntity) {
        List<AlipayUserInfo> userInfolist = new ArrayList<>();
        SysUser userf = new SysUser();
        startPage();
        userInfolist = merchantInfoEntityService.selectMerchantInfoEntityList(merchantInfoEntity);

        if (CollectionUtils.isNotEmpty(userInfolist)) {
            List<String> loginNames = userInfolist.stream().map(AlipayUserInfo::getUserId).collect(Collectors.toList());
            List<SysUser> sysUsers = userService.selectUserByLoginNames(loginNames);
            ConcurrentHashMap<String, SysUser> value = sysUsers.stream().collect(Collectors.toConcurrentMap(SysUser::getMerchantId, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
            userInfolist.stream().forEach(tmp -> {
                SysUser user = value.get(tmp.getUserId());
                if (null != user) {
                    tmp.setRemark(user.getRemark());
                    tmp.setIsBind(user.getIsBind());
                    tmp.setSysUserId(user.getUserId());
                    tmp.setLoginName(user.getLoginName());
                }
            });
        }
        return getDataTable(userInfolist);
    }

    /**
     * 查询商户子集信息列表
     */
    @PostMapping("/childrenList")
//    @RequiresPermissions("alipay:merchant:childrenList")
    @ResponseBody
    public TableDataInfo getChildren(AlipayUserInfo merchantInfoEntity) {
        startPage();
        List<AlipayUserInfo> list = new ArrayList<>();
        list = merchantInfoEntityService.selectChildrenByUserId(merchantInfoEntity.getAgent());
        if (CollectionUtils.isNotEmpty(list)) {
            List<String> loginNames = list.stream().map(AlipayUserInfo::getUserId).collect(Collectors.toList());
            List<SysUser> sysUsers = userService.selectUserByLoginNames(loginNames);
            Map<String, SysUser> value = sysUsers.stream().collect(Collectors.toMap(SysUser::getMerchantId, tmp -> tmp));
            list.stream().forEach(tmp -> {
                SysUser user = value.get(tmp.getUserId());
                if (null != user) {
                    tmp.setRemark(user.getRemark());
                    tmp.setIsBind(user.getIsBind());
                    tmp.setSysUserId(user.getUserId());
                    tmp.setLoginName(user.getLoginName());
                }
            });
        }
        return getDataTable(list);
    }

    /**
     * 新增商户信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增下级开户页面显示
     */
    @GetMapping("/open/account/{userId}")
    public String open(@PathVariable("userId") String userId, ModelMap mmap) {
        AlipayUserInfo userInfo = new AlipayUserInfo();
        userInfo.setAgent(userId);
        mmap.put("userInfo", userInfo);
        return prefix + "/children";
    }

    /**
     * 新增保存商户信息
     */
    @Log(title = "商户信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayUserInfo merchantInfoEntity) {
        int i = merchantInfoEntityService.insertMerchantInfoEntity(merchantInfoEntity);
        int n = saveSysUser(merchantInfoEntity);
        if (i > 0 && n > 0) {
            return toAjax(1);
        }
        return error("新增失败");
    }

    /**
     * 增加后台用户
     *
     * @param merchantInfoEntity
     * @return
     */
    private int saveSysUser(AlipayUserInfo merchantInfoEntity) {
        SysUser user = new SysUser();
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(merchantInfoEntity.getUserId(), merchantInfoEntity.getBackPassword(), user.getSalt()));
        user.setFundPassword(passwordService.encryptPassword(merchantInfoEntity.getUserId(), merchantInfoEntity.getFundPassword(), user.getSalt()));
        user.setCreateBy(ShiroUtils.getLoginName());
        user.setMerchantId(merchantInfoEntity.getUserId());
        user.setRemark(merchantInfoEntity.getIpStr() + "&0");
        List<SysRole> roles = new ArrayList<>();
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("BackUserType", 1);
        SysRole sysRole = new SysRole();
        sysRole.setRoleId(113L);
        roles.add(sysRole);
        user.setRoles(roles);
        user.setParams(dataMap);
        user.setSex("0");
        user.setLoginName(merchantInfoEntity.getUserId());
        user.setUserName(merchantInfoEntity.getUserName());
        user.setPhonenumber(RandomValue.getTel());
        user.setEmail(RandomValue.getEmail(6, 20));
        int i = userService.insertUser(user);
        return i;
    }


    /**
     * 修改商户信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayUserInfo userInfo = merchantInfoEntityService.selectMerchantInfoEntityById(id);
        List<String> str = new ArrayList();
        str.add(userInfo.getUserId());
        List<SysUser> sysUsers = userService.selectUserByLoginNames(str);
        for (SysUser sys : sysUsers) {
            userInfo.setRemark(sys.getRemark());
        }
        mmap.put("alipayUserInfo", userInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存商户信息
     */
    @Log(title = "商户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @RequiresPermissions("alipay:merchant:edit")
    @ResponseBody
    public AjaxResult editSave(AlipayUserInfo alipayUserInfo) {
        return toAjax(userService.updateUserByLoginName(alipayUserInfo));
//        //获取alipay处理接口URL
//        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
//        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_SERVICE_API_KEY, StaticConstants.ALIPAY_SERVICE_API_VALUE_1);
//        //获取数据库内请求路径
//        Map<String, Object> mapParam = Collections.synchronizedMap(Maps.newHashMap());
//        mapParam.put("id", merchantInfoEntity.getId());
//        mapParam.put("userId", merchantInfoEntity.getUserId());
//        mapParam.put("userName", merchantInfoEntity.getUserName());
//        mapParam.put("email", merchantInfoEntity.getEmail());
//        mapParam.put("QQ", merchantInfoEntity.getQQ());
//        mapParam.put("telegram", merchantInfoEntity.getTelegram());
//        mapParam.put("userNode", merchantInfoEntity.getUserNode());
//        AlipayUserInfo alipayUserInfo = merchantInfoEntityService.selectMerchantInfoEntityById(merchantInfoEntity.getId());
//        //对参数进行RSA加密处理
//        String cipherText = RSAUtils.getEncryptPublicKey(mapParam, alipayUserInfo.getPublicKey());
//        String pathParam = "userId=" + merchantInfoEntity.getUserId() + "&cipherText=" + cipherText;
//        //post调用alipay方法
//        String flag = HttpUtils.sendPost(ipPort + urlPath, pathParam);
//        if ("ConnectException".equals(flag)) {
//            throw new BusinessException("操作失败，请求alipay接口地址超时,URL=" + ipPort + urlPath);
//        }
//        JSONObject json = JSONObject.parseObject(flag);
//        String result = json.getString("success");
//        switch (result) {
//            case "true":
//                return toAjax(1);
//            case "false":
//                String message = json.getString("message");
//                return error(message);
//        }
//        return null;
    }

    /**
     * 商户修改状态（调用api）
     */
    @Log(title = "商户状态开启", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(AlipayUserInfo user) {
        //获取alipay处理接口URL
        logger.info("[当前处理商户关闭或者开启的管理员账号为：" + ShiroUtils.getSysUser().getLoginName() + "]");
        logger.info("[当前处理商户状态的参数为：" + user.getParams().toString() + "]");
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_SERVICE_API_KEY, StaticConstants.ALIPAY_SERVICE_API_VALUE_2);
        Map<String, Object> mapParam = Maps.newHashMap();
        mapParam.put("paramKey", user.getParams().get("paramKey").toString());
        mapParam.put("paramValue", user.getParams().get("paramValue").toString());
        mapParam.put("userId", user.getUserId());
        String flag = HttpUtils.sendPost(ipPort + urlPath, MapDataUtil.createParam(mapParam));
        if ("ConnectException".equals(flag)) {
            throw new BusinessException("操作失败，请求alipay接口地址超时,URL=" + ipPort + urlPath);
        }
        if (StringUtils.isEmpty(flag)) {
            throw new BusinessException("操作失败，请刷新重试");
        }
        JSONObject json = JSONObject.parseObject(flag);
        String result = json.getString("success");
        switch (result) {
            case "true":
                return toAjax(1);
            case "false":
                String message = json.getString("message");
                return error(message);
        }
        return null;
    }

    /**
     * 自动生成商户编码
     *
     * @return
     */
    @PostMapping("/getMerchantNo")
    @ResponseBody
    public AjaxResult getMerchantNo() {
        return AjaxResult.success(NoUtils.shortUUID());
    }

    /**
     * 重置用戶的提現密碼
     */
    @Log(title = "码商查询", businessType = BusinessType.RESET)
    @PostMapping("resetPayPassword")
    @ResponseBody
    public AjaxResult resetWithdrawalPwd(Long id) {
        String resetPwd = merchantInfoEntityService.resetPayPassword(id);
        if ("false".equals(resetPwd)) {
            return error("操作失败");
        }
        return success("重置成功，新的提现密码：" + resetPwd);
    }

    /**
     * 验证用户的登陆密码
     *
     * @return
     */
    @PostMapping("/verify/password")
    @ResponseBody
    public AjaxResult verifyPassword(String password) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        String verify = passwordService.encryptPassword(currentUser.getLoginName(), password, currentUser.getSalt());
        if (currentUser.getPassword().equals(verify)) {
            return AjaxResult.success("验证通过");
        } else {
            return AjaxResult.error("密码验证失败");
        }

    }

    /**
     * 新增下级开户保存信息
     */
    @Log(title = "商户信息", businessType = BusinessType.INSERT)
    @PostMapping("/save/children")
    @ResponseBody
    public AjaxResult saveChildrenAccount(AlipayUserInfo merchantInfoEntity) {
        int n = merchantInfoEntityService.insertMerchantInfoEntity(merchantInfoEntity);
        int i = saveSysUser(merchantInfoEntity);
        if (i > 0 && n > 0) {
            return toAjax(1);
        }
        return error();
    }

}
