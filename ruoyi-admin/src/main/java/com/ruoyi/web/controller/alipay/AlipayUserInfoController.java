package com.ruoyi.web.controller.alipay;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.service.IAlipayUserInfoService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.MapDataUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.framework.util.DictionaryUtils;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 用户详情Controller
 *
 * @author otc
 * @date 2020-02-27
 */
@Controller
@RequestMapping("/alipay/userInfo")
public class AlipayUserInfoController extends BaseController {
    @Autowired
    private DictionaryUtils dictionaryUtils;
    private String prefix = "alipay/qrOwner/info";

    @Autowired
    private IAlipayUserInfoService alipayUserInfoService;

    @GetMapping()
    @RequiresPermissions("alipay:merchant:view")
    public String userInfo() {
        return prefix + "/userInfo";
    }
    /**
     * 查询用户详情列表
     */
    @PostMapping("/list")
    @RequiresPermissions("alipay:merchant:list")
    @ResponseBody
    public TableDataInfo list(AlipayUserInfo alipayUserInfo) {
        startPage();
        //通过数据库源获取数据列表
        List<AlipayUserInfo> list = alipayUserInfoService.selectAlipayUserInfoList(alipayUserInfo);
        return getDataTable(list);
    }

    /**
     * 新增用户详情
     */
    @GetMapping("/add")
    @RequiresPermissions("alipay:merchant:add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存用户详情
     */
    @Log(title = "用户详情", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayUserInfo alipayUserInfo) {
        //获取alipay处理接口URL
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_SERVICE_API_KEY, StaticConstants.ALIPAY_SERVICE_API_VALUE_1);
        //获取数据库内请求路径
//        String url1 = "http://10.14.180.64:5055/api-alipay/account-api/add-account";
        Map<String, Object> mapParam = Maps.newHashMap();
        mapParam.put("userId", alipayUserInfo.getUserId());
        mapParam.put("userName", alipayUserInfo.getUserName());
        mapParam.put("password", alipayUserInfo.getPassword());
        mapParam.put("payPasword", alipayUserInfo.getPayPasword());
        mapParam.put("userType", 2);//码商代码
        mapParam.put("isAgent", 1);//是否为代理
        mapParam.put("email", alipayUserInfo.getEmail());
        mapParam.put("QQ", alipayUserInfo.getQQ());
        mapParam.put("telegram", alipayUserInfo.getTelegram());
        mapParam.put("skype", alipayUserInfo.getSkype());
        String flag = HttpUtil.post(ipPort + urlPath,mapParam);
        if ("ConnectException".equals(flag)) {
            throw new BusinessException("操作失败，请求alipay接口地址超时,URL=" + ipPort + urlPath);
        }
        if(StringUtils.isEmpty(flag)){
            throw new BusinessException("新增失败，接口返回参数为空");
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
     * 修改用户详情
     */
    @GetMapping("/edit/{id}")
    @RequiresPermissions("alipay:merchant:edit:view")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayUserInfo alipayUserInfo = alipayUserInfoService.selectAlipayUserInfoById(id);
        mmap.put("info", alipayUserInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户详情
     */
    @Log(title = "用户详情", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @RequiresPermissions("alipay:merchant:edit")
    @ResponseBody
    public AjaxResult editSave(AlipayUserInfo alipayUserInfo) {
        //获取alipay处理接口URL
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_SERVICE_API_KEY, StaticConstants.ALIPAY_SERVICE_API_VALUE_1);
        //获取数据库内请求路径
        Map<String, Object> mapParam = Collections.synchronizedMap(Maps.newHashMap());
        mapParam.put("userName", alipayUserInfo.getUserName());
        mapParam.put("email", alipayUserInfo.getEmail());
        mapParam.put("QQ", alipayUserInfo.getQQ());
        mapParam.put("telegram", alipayUserInfo.getTelegram());
        mapParam.put("skype", alipayUserInfo.getSkype());
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
        return null;
    }

    /**
     * 删除用户详情(调用api)
     */
    @Log(title = "用户详情", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        //获取alipay处理接口URL
        StringBuffer url = new StringBuffer();
        String ip = null;
//        StaticConstants.ALIPAY_IP_PORT;
        //获取数据库内请求路径
        String path = dictionaryUtils.getApiUrlPath("alipay_api_address", "update_user_status");
        Map<String, Object> mapParam = Maps.newHashMap();
        mapParam.put("ids", ids);
        String flag = HttpUtils.sendPost(url.append(ip).append(path).toString(), MapDataUtil.createParam(mapParam));
        switch (flag) {
            case "ConnectException":
                throw new BusinessException("操作失败，请求alipay接口地址超时,URL=" + url);
        }
        int i = flag == "true" ? 0 : 1;
        return toAjax(i);

    }

    /**
     * 码商状态修改（调用api）
     */
    @Log(title = "状态调整", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(AlipayUserInfo user) {
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
     * 检验登陆用户ID是否唯一
     */
    @ResponseBody
    @PostMapping("/checkAlipayUserIdUnique")
    public String checkAlipayUserIdUnique(AlipayUserInfo alipayUserInfo) {
        logger.info("进入验证用户名是否唯一");
        return alipayUserInfoService.checkAlipayUserIdUnique(alipayUserInfo);
    }

    /**
     * 重置用戶的登陸密碼
     */
    @Log(title = "卡商重置登录密码", businessType = BusinessType.RESET)
    @PostMapping("resetLoginPwd")
    @ResponseBody
    public AjaxResult resetLoginPwd(Long id) {
        String resetPwd = alipayUserInfoService.resetLoginPwd(id);
        if ("false".equals(resetPwd)) {
            return error("操作失败");
        }
        return success("重置成功，新的登陆密码：" + resetPwd);

    }

    /**
     * 重置用戶的提現密碼
     */
    @Log(title = "卡商重置资金密码", businessType = BusinessType.RESET)
    @PostMapping("resetWithdrawalPwd")
    @RequiresPermissions("alipay:merchant:edit:resetWithdrawalPwd")
    @ResponseBody
    public AjaxResult resetWithdrawalPwd(Long id) {
        String resetPwd = alipayUserInfoService.resetWithdrawalPwd(id);
        if ("false".equals(resetPwd)) {
            return error("操作失败");
        }
        return success("重置成功，新的提现密码：" + resetPwd);
    }


    @Log(title = "卡商升级代理商", businessType = BusinessType.RESET)
    @PostMapping("upUserAgents")
    @RequiresPermissions("alipay:merchant:edit:upUserAgents")
    @ResponseBody
    public AjaxResult upUserAgents(Long id) {
        return toAjax(alipayUserInfoService.upUserAgents(id));
    }


}
