package com.ruoyi.web.controller.dealpay;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.MapDataUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.framework.util.DictionaryUtils;
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
import com.ruoyi.dealpay.domain.DealpayUserInfoEntity;
import com.ruoyi.dealpay.service.IDealpayUserInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户详情Controller
 *
 * @author kiwi
 * @date 2020-04-03
 */
@Controller
@RequestMapping("/dealpay/cardInfo")
public class DealpayUserInfoController extends BaseController {
    @Autowired
    private DictionaryUtils dictionaryUtils;
    private String prefix = "dealpay/cardInfo";

    @Autowired
    private IDealpayUserInfoService dealpayUserInfoService;

    @RequiresPermissions("dealpay:cardInfo:view")
    @GetMapping()
    public String cardInfo() {
        return prefix + "/cardInfo";
    }

    /**
     * 查询卡商详情列表
     */
    @RequiresPermissions("dealpay:cardInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DealpayUserInfoEntity dealpayUserInfoEntity) {
        startPage();
        //通过数据库源获取数据列表
        List<DealpayUserInfoEntity> list = dealpayUserInfoService.selectDealpayUserInfoList(dealpayUserInfoEntity);
        return getDataTable(list);
    }

    /**
     * 新增卡商详情
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存卡商详情
     */
    @RequiresPermissions("dealpay:cardInfo:add")
    @Log(title = "用户详情", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DealpayUserInfoEntity dealpayUserInfoEntity) {
        //获取alipay处理接口URL
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.DealPAY_IP_URL_KEY, StaticConstants.DealPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.DealPAY_SERVICE_API_KEY, StaticConstants.DealPAY_SERVICE_API_VALUE_1);
        //获取数据库内请求路径
//        String url1 = "http://10.14.180.64:5055/api-alipay/account-api/add-account";
        Map<String, Object> mapParam = Maps.newHashMap();
        mapParam.put("userId", dealpayUserInfoEntity.getUserId());
        mapParam.put("userName", dealpayUserInfoEntity.getUserName());
        mapParam.put("password", dealpayUserInfoEntity.getPassword());
        mapParam.put("payPasword", dealpayUserInfoEntity.getPayPasword());
        mapParam.put("userType", 2);//卡商代码
        mapParam.put("isAgent", 1);//是否为代理
        mapParam.put("email", dealpayUserInfoEntity.getEmail());
        mapParam.put("QQ", dealpayUserInfoEntity.getQQ());
        mapParam.put("telegram", dealpayUserInfoEntity.getTelegram());
        mapParam.put("skype", dealpayUserInfoEntity.getSkype());
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
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DealpayUserInfoEntity dealpayUserInfo = dealpayUserInfoService.selectDealpayUserInfoById(id);
        mmap.put("dealpayUserInfo", dealpayUserInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存卡商详情
     */
    @RequiresPermissions("dealpay:cardInfo:edit")
    @Log(title = "用户详情", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DealpayUserInfoEntity dealpayUserInfoEntity) {
        //获取alipay处理接口URL
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.DealPAY_IP_URL_KEY, StaticConstants.DealPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.DealPAY_SERVICE_API_KEY, StaticConstants.DealPAY_SERVICE_API_VALUE_1);
        //获取数据库内请求路径
        Map<String, Object> mapParam = Collections.synchronizedMap(Maps.newHashMap());
        mapParam.put("userName", dealpayUserInfoEntity.getUserName());
        mapParam.put("email", dealpayUserInfoEntity.getEmail());
        mapParam.put("QQ", dealpayUserInfoEntity.getQQ());
        mapParam.put("telegram", dealpayUserInfoEntity.getTelegram());
        mapParam.put("skype", dealpayUserInfoEntity.getSkype());
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
     * 删除卡商详情(调用api)
     */
    @RequiresPermissions("dealpay:cardInfo:remove")
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
     * 卡商状态修改（调用api）
     */
    @Log(title = "码商查询", businessType = BusinessType.UPDATE)
    @RequiresPermissions("dealpay:cardInfo:switch")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(DealpayUserInfoEntity user) {
        //获取alipay处理接口URL
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_SERVICE_API_KEY, StaticConstants.ALIPAY_SERVICE_API_VALUE_1);
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
    public String checkAlipayUserIdUnique(DealpayUserInfoEntity dealpayUserInfoEntity) {
        logger.info("进入验证用户名是否唯一");
        return dealpayUserInfoService.checkDealpayUserIdUnique(dealpayUserInfoEntity);
    }

    /**
     * 重置用戶的登陸密碼
     */
    @Log(title = "码商查询", businessType = BusinessType.RESET)
    @RequiresPermissions("dealpay:cardInfo:reset:login")
    @PostMapping("resetLoginPwd")
    @ResponseBody
    public AjaxResult resetLoginPwd(Long id) {
        String resetPwd = dealpayUserInfoService.resetLoginPwd(id);
        if ("false".equals(resetPwd)) {
            return error("操作失败");
        }
        return success("重置成功，新的登陆密码：" + resetPwd);

    }
    /**
     * 重置用戶的提現密碼
     */
    @Log(title = "码商查询", businessType = BusinessType.RESET)
    @RequiresPermissions("dealpay:cardInfo:reset:withdrawal")
    @PostMapping("resetWithdrawalPwd")
    @ResponseBody
    public AjaxResult resetWithdrawalPwd(Long id) {
        String resetPwd = dealpayUserInfoService.resetWithdrawalPwd(id);
        if ("false".equals(resetPwd)) {
            return error("操作失败");
        }
        return success("重置成功，新的提现密码：" + resetPwd);
    }
}
