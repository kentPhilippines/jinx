package com.ruoyi.web.controller.alipay;

import java.util.List;

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
import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.service.IAlipayUserInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户详情Controller
 *
 * @author ruoyi
 * @date 2020-02-27
 */
@Controller
@RequestMapping("/alipay/userInfo")
public class AlipayUserInfoController extends BaseController {
    private String prefix = "alipay/userInfo";

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
        return toAjax(alipayUserInfoService.insertAlipayUserInfo(alipayUserInfo));
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
     * 删除用户详情
     */
    @RequiresPermissions("alipay:userInfo:remove")
    @Log(title = "用户详情", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(alipayUserInfoService.deleteAlipayUserInfoByIds(ids));
    }
}
