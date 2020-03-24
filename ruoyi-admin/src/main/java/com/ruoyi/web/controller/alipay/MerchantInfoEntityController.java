package com.ruoyi.web.controller.alipay;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.MapDataUtil;
import com.ruoyi.common.utils.NoUtils;
import com.ruoyi.common.utils.RSAUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.util.DictionaryUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.alipay.domain.MerchantInfoEntity;
import com.ruoyi.alipay.service.IMerchantInfoEntityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

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

    @RequiresPermissions("alipay:merchant:view")
    @GetMapping()
    public String merchant() {
        return prefix + "/merchant";
    }

    /**
     * 查询商户信息列表
     */
    @RequiresPermissions("alipay:merchant:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayUserInfo merchantInfoEntity) {
        startPage();
        List<AlipayUserInfo> list = merchantInfoEntityService.selectMerchantInfoEntityList(merchantInfoEntity);
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
    public String open(@PathVariable("userId") String userId,ModelMap mmap) {
        AlipayUserInfo userInfo = new AlipayUserInfo();
        userInfo.setAgent(userId);
        mmap.put("userInfo", userInfo);
        return prefix + "/children";
    }

    /**
     * 新增保存商户信息
     */
    @RequiresPermissions("alipay:merchant:add")
    @Log(title = "商户信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayUserInfo merchantInfoEntity) {
        return toAjax(merchantInfoEntityService.insertMerchantInfoEntity(merchantInfoEntity));
    }

    /**
     * 修改商户信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayUserInfo userInfo = merchantInfoEntityService.selectMerchantInfoEntityById(id);
        mmap.put("alipayUserInfo", userInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存商户信息
     */
    @RequiresPermissions("alipay:merchant:edit")
    @Log(title = "商户信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayUserInfo merchantInfoEntity) {
        //获取alipay处理接口URL
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_ERVICE_API_KEY, StaticConstants.ALIPAY_ERVICE_API_VALUE);
        //获取数据库内请求路径
        Map<String, Object> mapParam = Collections.synchronizedMap(Maps.newHashMap());
        mapParam.put("id", merchantInfoEntity.getId());
        mapParam.put("userId", merchantInfoEntity.getUserId());
        mapParam.put("userName", merchantInfoEntity.getUserName());
        mapParam.put("email", merchantInfoEntity.getEmail());
        mapParam.put("QQ", merchantInfoEntity.getQQ());
        mapParam.put("telegram", merchantInfoEntity.getTelegram());
        mapParam.put("userNode", merchantInfoEntity.getUserNode());
        AlipayUserInfo alipayUserInfo = merchantInfoEntityService.selectMerchantInfoEntityById(merchantInfoEntity.getId());
        //对参数进行RSA加密处理
        String cipherText = RSAUtils.getEncryptPublicKey(mapParam, alipayUserInfo.getPublicKey());
        String pathParam = "userId=" + merchantInfoEntity.getUserId() + "&cipherText=" + cipherText;
        //post调用alipay方法
        String flag = HttpUtils.sendPost(ipPort + urlPath, pathParam);
        if ("ConnectException".equals(flag)) {
            throw new BusinessException("操作失败，请求alipay接口地址超时,URL=" + ipPort + urlPath);
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
     * 码商状态修改（调用api）
     */
    @Log(title = "码商查询", businessType = BusinessType.UPDATE)
    @RequiresPermissions("alipay:merchant:switch")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(AlipayUserInfo user) {
        //获取alipay处理接口URL
        /*String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_ERVICE_API_KEY, StaticConstants.ALIPAY_ERVICE_API_VALUE);
        Map<String, Object> mapParam = Maps.newHashMap();
        mapParam.put(user.getParams().get("paramKey").toString(),user.getParams().get("paramValue"));
        mapParam.put("userId", user.getUserId());
        String flag = HttpUtils.sendPost(ipPort + urlPath, MapDataUtil.createParam(mapParam));
        if ("ConnectException".equals(flag)) {
            throw new BusinessException("操作失败，请求alipay接口地址超时,URL=" + ipPort + urlPath);
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
        return null;*/


        return null;
    }

    /**
     * 删除商户信息
     */
    @RequiresPermissions("alipay:merchant:remove")
    @Log(title = "商户信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(merchantInfoEntityService.deleteMerchantInfoEntityByIds(ids));
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
    @RequiresPermissions("alipay:merchant:reset:payPassword")
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
        if(currentUser.getPassword().equals(verify)){
            return AjaxResult.success("验证通过");
        }else{
            return AjaxResult.error("密码验证失败");
        }

    }

    /**
     * 新增下级开户保存信息
     */
        @RequiresPermissions("alipay:merchant:save:children")
    @Log(title = "商户信息", businessType = BusinessType.INSERT)
    @PostMapping("/save/children")
    @ResponseBody
    public AjaxResult saveChildrenAccount(AlipayUserInfo merchantInfoEntity) {
        return toAjax(merchantInfoEntityService.insertMerchantInfoEntity(merchantInfoEntity));
    }

}
