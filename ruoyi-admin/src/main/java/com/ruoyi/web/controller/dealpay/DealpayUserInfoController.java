package com.ruoyi.web.controller.dealpay;

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
    private String prefix = "dealpay/cardInfo";

    @Autowired
    private IDealpayUserInfoService dealpayUserInfoService;

    @RequiresPermissions("dealpay:cardInfo:view")
    @GetMapping()
    public String cardInfo() {
        return prefix + "/cardInfo";
    }

    /**
     * 查询用户详情列表
     */
    @RequiresPermissions("dealpay:cardInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DealpayUserInfoEntity dealpayUserInfo) {
        startPage();
        List<DealpayUserInfoEntity> list = dealpayUserInfoService.selectDealpayUserInfoList(dealpayUserInfo);
        return getDataTable(list);
    }

    /**
     * 导出用户详情列表
     */
    @RequiresPermissions("dealpay:cardInfo:export")
    @Log(title = "用户详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DealpayUserInfoEntity dealpayUserInfo) {
        List<DealpayUserInfoEntity> list = dealpayUserInfoService.selectDealpayUserInfoList(dealpayUserInfo);
        ExcelUtil<DealpayUserInfoEntity> util = new ExcelUtil<DealpayUserInfoEntity>(DealpayUserInfoEntity.class);
        return util.exportExcel(list, "cardInfo");
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
    @RequiresPermissions("dealpay:cardInfo:add")
    @Log(title = "用户详情", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DealpayUserInfoEntity dealpayUserInfo) {
        return toAjax(dealpayUserInfoService.insertDealpayUserInfo(dealpayUserInfo));
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
     * 修改保存用户详情
     */
    @RequiresPermissions("dealpay:cardInfo:edit")
    @Log(title = "用户详情", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DealpayUserInfoEntity dealpayUserInfo) {
        return toAjax(dealpayUserInfoService.updateDealpayUserInfo(dealpayUserInfo));
    }

    /**
     * 删除用户详情
     */
    @RequiresPermissions("dealpay:cardInfo:remove")
    @Log(title = "用户详情", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dealpayUserInfoService.deleteDealpayUserInfoByIds(ids));
    }
}
