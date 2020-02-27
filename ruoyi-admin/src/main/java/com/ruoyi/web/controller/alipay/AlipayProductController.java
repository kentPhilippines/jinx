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
import com.ruoyi.alipay.domain.AlipayProduct;
import com.ruoyi.alipay.service.IAlipayProductService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户产品Controller
 *
 * @author ruoyi
 * @date 2020-02-27
 */
@Controller
@RequestMapping("/alipay/product")
public class AlipayProductController extends BaseController {
    private String prefix = "alipay/product";

    @Autowired
    private IAlipayProductService alipayProductService;

    @RequiresPermissions("alipay:product:view")
    @GetMapping()
    public String product() {
        return prefix + "/product";
    }

    /**
     * 查询用户产品列表
     */
    @RequiresPermissions("alipay:product:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayProduct alipayProduct) {
        startPage();
        List<AlipayProduct> list = alipayProductService.selectAlipayProductList(alipayProduct);
        return getDataTable(list);
    }

    /**
     * 导出用户产品列表
     */
    @RequiresPermissions("alipay:product:export")
    @Log(title = "用户产品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayProduct alipayProduct) {
        List<AlipayProduct> list = alipayProductService.selectAlipayProductList(alipayProduct);
        ExcelUtil<AlipayProduct> util = new ExcelUtil<AlipayProduct>(AlipayProduct.class);
        return util.exportExcel(list, "product");
    }

    /**
     * 新增用户产品
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存用户产品
     */
    @RequiresPermissions("alipay:product:add")
    @Log(title = "用户产品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayProduct alipayProduct) {
        return toAjax(alipayProductService.insertAlipayProduct(alipayProduct));
    }

    /**
     * 修改用户产品
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayProduct alipayProduct = alipayProductService.selectAlipayProductById(id);
        mmap.put("alipayProduct", alipayProduct);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户产品
     */
    @RequiresPermissions("alipay:product:edit")
    @Log(title = "用户产品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayProduct alipayProduct) {
        return toAjax(alipayProductService.updateAlipayProduct(alipayProduct));
    }

    /**
     * 删除用户产品
     */
    @RequiresPermissions("alipay:product:remove")
    @Log(title = "用户产品", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(alipayProductService.deleteAlipayProductByIds(ids));
    }
}
