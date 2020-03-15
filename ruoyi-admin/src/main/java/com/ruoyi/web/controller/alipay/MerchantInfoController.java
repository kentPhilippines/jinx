package com.ruoyi.web.controller.alipay;

import com.ruoyi.common.utils.poi.ExcelUtil;
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
import com.ruoyi.alipay.service.IMerchantInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;

import java.util.List;

/**
 * 商户详情Controller
 *
 * @author kiwi
 * @date 2020-03-14
 */
@Controller
@RequestMapping("/merchant/info")
public class MerchantInfoController extends BaseController {
    private String prefix = "/alipay/merchant/info";

    @Autowired
    private IMerchantInfoService merchantInfoService;

    @RequiresPermissions("merchant:info:view")
    @GetMapping()
    public String merchant() {
        return prefix + "/merchant";
    }

    /**
     * 查询商户详情列表
     */
    @RequiresPermissions("merchant:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MerchantInfoEntity merchantInfoEntity) {
        startPage();
        List<MerchantInfoEntity> list = merchantInfoService.selectMerchantInfoEntityList(merchantInfoEntity);
        return getDataTable(list);
    }

    /**
     * 导出商户详情列表
     */
    @RequiresPermissions("merchant:info:export")
    @Log(title = "商户详情", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MerchantInfoEntity merchantInfoEntity) {
        List<MerchantInfoEntity> list = merchantInfoService.selectMerchantInfoEntityList(merchantInfoEntity);
        ExcelUtil<MerchantInfoEntity> util = new ExcelUtil<MerchantInfoEntity>(MerchantInfoEntity.class);
        return util.exportExcel(list, "merchant");
    }

    /**
     * 新增商户详情
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存商户详情
     */
    @RequiresPermissions("merchant:info:add")
    @Log(title = "商户详情", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MerchantInfoEntity merchantInfoEntity) {
        return toAjax(merchantInfoService.insertMerchantInfoEntity(merchantInfoEntity));
    }

    /**
     * 修改商户详情
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        MerchantInfoEntity merchantInfoEntity = merchantInfoService.selectMerchantInfoEntityById(id);
        mmap.put("merchantInfoEntity", merchantInfoEntity);
        return prefix + "/edit";
    }

    /**
     * 修改保存商户详情
     */
    @RequiresPermissions("merchant:info:edit")
    @Log(title = "商户详情", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MerchantInfoEntity merchantInfoEntity) {
        return toAjax(merchantInfoService.updateMerchantInfoEntity(merchantInfoEntity));
    }

    /**
     * 删除商户详情
     */
    @RequiresPermissions("merchant:info:remove")
    @Log(title = "商户详情", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(merchantInfoService.deleteMerchantInfoEntityByIds(ids));
    }
}
