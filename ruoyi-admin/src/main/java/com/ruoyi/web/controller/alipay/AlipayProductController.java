package com.ruoyi.web.controller.alipay;

import com.ruoyi.alipay.domain.AlipayProductEntity;
import com.ruoyi.alipay.service.IAlipayProductService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>产品管理</p>
 */
@Controller
@RequestMapping("alipay/product")
public class AlipayProductController extends BaseController {
    private String prefix = "alipay/product";
    @Autowired
    IAlipayProductService iAlipayProductService;


    @RequiresPermissions("alipay:product:view")
    @GetMapping()
    public String Product() {
        return prefix + "/product";
    }

    /**
     * 查询产品列表列表
     */
    @RequiresPermissions("alipay:product:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayProductEntity alipayProductEntity) {
        startPage();
        List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        return getDataTable(list);
    }

    /**
     * 导出产品列表列表
     */
    @RequiresPermissions("alipay:product:export")
    @Log(title = "产品列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayProductEntity alipayProductEntity) {
        List<AlipayProductEntity> list = iAlipayProductService.selectAlipayProductList(alipayProductEntity);
        ExcelUtil<AlipayProductEntity> entityExcelUtil = new ExcelUtil<>(AlipayProductEntity.class);
        return entityExcelUtil.exportExcel(list,"product");
    }
    /**
     * 新增产品列表
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存产品列表
     */
    @RequiresPermissions("alipay:product:add")
    @Log(title = "产品列表", businessType = BusinessType.INSERT)
    @PostMapping("/submitAdd")
    @ResponseBody
    public AjaxResult addSave(AlipayProductEntity alipayProductEntity) {
        AlipayProductEntity result=iAlipayProductService.checkAlipayProductIdUnique(alipayProductEntity.getProductId());
        if (result!=null){
            return error("此产品已经存在！！");
        }
        return toAjax(iAlipayProductService.insertAlipayProductListEntity(alipayProductEntity));
    }

    /**
     * 修改产品列表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayProductEntity alipayProductListEntity = iAlipayProductService.selectAlipayProductListEntityById(id);
        mmap.put("alipayProductListEntity", alipayProductListEntity);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品列表
     */
    @RequiresPermissions("alipay:product:edit")
    @Log(title = "产品列表", businessType = BusinessType.UPDATE)
    @PostMapping("/submitEdit")
    @ResponseBody
    public AjaxResult editSave(AlipayProductEntity alipayProductEntity) {
        return toAjax(iAlipayProductService.updateAlipayProductListEntity(alipayProductEntity));
    }

    /**
     * 批量删除产品列表
     */
    @RequiresPermissions("alipay:product:remove")
    @Log(title = "产品列表", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(iAlipayProductService.deleteAlipayProductListEntityByIds(ids));
    }



    /**
     * 产品状态修改（调用api）
     */
    @Log(title = "产品查询", businessType = BusinessType.UPDATE)
    @RequiresPermissions("alipay:product:status")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(AlipayProductEntity alipayProductEntity) {
        return toAjax(iAlipayProductService.updateProductStatusById(alipayProductEntity));
    }
}
