package com.ruoyi.web.controller.alipay;

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
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.DictionaryUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

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
     * 导出用户详情列表
     */
    @RequiresPermissions("alipay:userInfo:export")
    @Log(title = "用户详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayUserInfo alipayUserInfo) {
        List<AlipayUserInfo> list = alipayUserInfoService.selectAlipayUserInfoList(alipayUserInfo);
        ExcelUtil<AlipayUserInfo> util = new ExcelUtil<AlipayUserInfo>(AlipayUserInfo.class);
        return util.exportExcel(list, "userInfo");
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
        StringBuffer url = new StringBuffer();
        String ip = StaticConstants.ALIPAY_IP_PORT;
        //获取数据库内请求路径
        String path = dictionaryUtils.getApiUrlPath("alipay_api_address", "add_user_entity");
        Map<String, Object> mapParam = Maps.newHashMap();
        mapParam.put("userId", alipayUserInfo.getUserId());
        mapParam.put("userName", alipayUserInfo.getUserName());
        mapParam.put("password", alipayUserInfo.getPayPasword());
        mapParam.put("email", alipayUserInfo.getEmail());
        mapParam.put("QQ", alipayUserInfo.getQQ());
        mapParam.put("telegram", alipayUserInfo.getTelegram());
        mapParam.put("skype", alipayUserInfo.getSkype());
        String flag = HttpUtils.sendPost(url.append(ip).append(path).toString(), MapDataUtil.createParam(mapParam));
        switch (flag) {
            case "ConnectException":
                throw new BusinessException("操作失败，请求alipay接口地址超时,URL=" + url);
        }
        int i = flag == "true" ? 0 : 1;
        return toAjax(0);
    }

    /**
     * 修改用户详情
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayUserInfo alipayUserInfo = alipayUserInfoService.selectAlipayUserInfoById(id);
        mmap.put("alipayUserInfo", alipayUserInfo);
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

        return toAjax(alipayUserInfoService.updateAlipayUserInfo(alipayUserInfo));
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
        String ip = StaticConstants.ALIPAY_IP_PORT;
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
        return  toAjax(i);




    }


    /**
     * 新增方法
     */

    /**
     * 码商状态修改（调用api）
     */
    @Log(title = "码商查询", businessType = BusinessType.UPDATE)
    @RequiresPermissions("alipay:userInfo:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(AlipayUserInfo user) {
        //获取alipay处理接口URL
        StringBuffer url = new StringBuffer();
        String ip = StaticConstants.ALIPAY_IP_PORT;
        //获取数据库内请求路径
        String path = dictionaryUtils.getApiUrlPath("alipay_api_address", "update_user_status");
        Map<String, Object> mapParam = Maps.newHashMap();
        mapParam.put("switchs", user.getSwitchs());
        mapParam.put("userId", user.getUserId());
        String flag = HttpUtils.sendPost(url.append(ip).append(path).toString(), MapDataUtil.createParam(mapParam));
        switch (flag) {
            case "ConnectException":
                throw new BusinessException("操作失败，请求alipay接口地址超时,URL=" + url);
        }
        int i = flag == "true" ? 0 : 1;
        return toAjax(0);
    }

    /**
     * 检验登陆用户ID是否唯一
     */
    @ResponseBody
    public TableDataInfo check(AlipayUserInfo alipayUserInfo) {
        startPage();
        //通过数据库源获取数据列表
        List<AlipayUserInfo> list = alipayUserInfoService.selectAlipayUserInfoList(alipayUserInfo);
        return getDataTable(list);
    }

}
