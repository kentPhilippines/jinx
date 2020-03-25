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
import com.ruoyi.alipay.domain.AlipayAmountEntity;
import com.ruoyi.alipay.service.IAlipayAmountEntityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 手动加扣款记录Controller
 *
 * @author kiwi
 * @date 2020-03-24
 */
@Controller
@RequestMapping("/alipay/deduct")
public class AlipayAmountEntityController extends BaseController {
    private String prefix = "alipay/deduct";

    @Autowired
    private IAlipayAmountEntityService alipayAmountEntityService;

    @RequiresPermissions("alipay:deduct:view")
    @GetMapping()
    public String deduct() {
        return prefix + "/deduct";
    }

    /**
     * 查询手动加扣款记录列表
     */
    @RequiresPermissions("alipay:deduct:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayAmountEntity alipayAmountEntity) {
        startPage();
        List<AlipayAmountEntity> list = alipayAmountEntityService.selectAlipayAmountEntityList(alipayAmountEntity);
        return getDataTable(list);
    }

    /**
     * 导出手动加扣款记录列表
     */
    @RequiresPermissions("alipay:deduct:export")
    @Log(title = "手动加扣款记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayAmountEntity alipayAmountEntity) {
        List<AlipayAmountEntity> list = alipayAmountEntityService.selectAlipayAmountEntityList(alipayAmountEntity);
        ExcelUtil<AlipayAmountEntity> util = new ExcelUtil<AlipayAmountEntity>(AlipayAmountEntity.class);
        return util.exportExcel(list, "deduct");
    }

    /**
     * 新增手动加扣款记录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存手动加扣款记录
     */
    @RequiresPermissions("alipay:deduct:add")
    @Log(title = "手动加扣款记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayAmountEntity alipayAmountEntity) {
        return toAjax(alipayAmountEntityService.insertAlipayAmountEntity(alipayAmountEntity));
    }

    /**
     * 修改手动加扣款记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayAmountEntity alipayAmountEntity = alipayAmountEntityService.selectAlipayAmountEntityById(id);
        mmap.put("alipayAmountEntity", alipayAmountEntity);
        return prefix + "/edit";
    }

    /**
     * 修改保存手动加扣款记录
     */
    @RequiresPermissions("alipay:deduct:edit")
    @Log(title = "手动加扣款记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayAmountEntity alipayAmountEntity) {
        return toAjax(alipayAmountEntityService.updateAlipayAmountEntity(alipayAmountEntity));
    }

    /**
     * 删除手动加扣款记录
     */
    @RequiresPermissions("alipay:deduct:remove")
    @Log(title = "手动加扣款记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(alipayAmountEntityService.deleteAlipayAmountEntityByIds(ids));
    }

    /*处理加减款的controller逻辑处理*/

    /**
     * 财务审核加减款记录
     */
    @RequiresPermissions("alipay:deduct:edit:approval")
    @Log(title = "加减款记录", businessType = BusinessType.UPDATE)
    @PostMapping("/approval")
    @ResponseBody
    public AjaxResult apporval(){

        return toAjax(1);
    }

}
