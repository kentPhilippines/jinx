package com.ruoyi.web.controller.alipay;

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
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.framework.util.DictionaryUtils;
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

    @RequiresPermissions("alipay:userInfo:view")
    @GetMapping()
    public String userInfo() {
        return prefix + "/userInfo";
    }
    /**
     * 查询用户详情列表
     */
    @RequiresPermissions("alipay:userInfo:list")
    @PostMapping("/list")
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
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存用户详情
     */
    @RequiresPermissions("alipay:userInfo:add")
    @Log(title = "用户详情", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayUserInfo alipayUserInfo) {
        //获取alipay处理接口URL
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_ERVICE_API_KEY, StaticConstants.ALIPAY_ERVICE_API_VALUE);
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
     * 修改用户详情
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayUserInfo alipayUserInfo = alipayUserInfoService.selectAlipayUserInfoById(id);
        mmap.put("info", alipayUserInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户详情
     */
    @RequiresPermissions("alipay:userInfo:edit")
    @Log(title = "用户详情", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayUserInfo alipayUserInfo) {
        //获取alipay处理接口URL
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_ERVICE_API_KEY, StaticConstants.ALIPAY_ERVICE_API_VALUE);
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
    @RequiresPermissions("alipay:userInfo:remove")
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
    @Log(title = "码商查询", businessType = BusinessType.UPDATE)
    @RequiresPermissions("alipay:userInfo:switch")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(AlipayUserInfo user) {
        //获取alipay处理接口URL
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_ERVICE_API_KEY, StaticConstants.ALIPAY_ERVICE_API_VALUE);
        Map<String, Object> mapParam = Maps.newHashMap();
        mapParam.put("switchs", user.getSwitchs());
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
    @PostMapping("checkAlipayUserIdUnique")
    @ResponseBody
    public String checkAlipayUserIdUnique(AlipayUserInfo alipayUserInfo) {
        logger.info("进入验证用户名是否唯一");
        return alipayUserInfoService.checkAlipayUserIdUnique(alipayUserInfo);
    }

    /**
     * 重置用戶的登陸密碼
     */
    @Log(title = "码商查询", businessType = BusinessType.RESET)
    @RequiresPermissions("alipay:userInfo:reset:login")
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
    @Log(title = "码商查询", businessType = BusinessType.RESET)
    @RequiresPermissions("alipay:userInfo:reset:withdrawal")
    @PostMapping("resetWithdrawalPwd")
    @ResponseBody
    public AjaxResult resetWithdrawalPwd(Long id) {
        String resetPwd = alipayUserInfoService.resetWithdrawalPwd(id);
        if ("false".equals(resetPwd)) {
            return error("操作失败");
        }
        return success("重置成功，新的提现密码：" + resetPwd);
    }





}
