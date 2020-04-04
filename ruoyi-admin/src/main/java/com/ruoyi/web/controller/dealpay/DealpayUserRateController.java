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
import com.ruoyi.dealpay.domain.DealpayUserRateEntity;
import com.ruoyi.dealpay.service.IDealpayUserRateService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 费率Controller
 *
 * @author kiwi
 * @date 2020-04-03
 */
@Controller
@RequestMapping("/dealpay/dealRate")
public class DealpayUserRateController extends BaseController {
    private String prefix = "dealpay/dealRate";

    @Autowired
    private IDealpayUserRateService dealpayUserRateEntityService;

    @RequiresPermissions("dealpay:dealRate:view")
    @GetMapping()
    public String dealRate() {
        return prefix + "/dealRate";
    }

    /**
     * 查询费率列表
     */
    @RequiresPermissions("dealpay:dealRate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DealpayUserRateEntity dealpayUserRateEntity) {
        startPage();
        List<DealpayUserRateEntity> list = dealpayUserRateEntityService.selectDealpayUserRateEntityList(dealpayUserRateEntity);
        return getDataTable(list);
    }

    /**
     * 导出费率列表
     */
    @RequiresPermissions("dealpay:dealRate:export")
    @Log(title = "费率", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DealpayUserRateEntity dealpayUserRateEntity) {
        List<DealpayUserRateEntity> list = dealpayUserRateEntityService.selectDealpayUserRateEntityList(dealpayUserRateEntity);
        ExcelUtil<DealpayUserRateEntity> util = new ExcelUtil<DealpayUserRateEntity>(DealpayUserRateEntity.class);
        return util.exportExcel(list, "dealRate");
    }

    /**
     * 新增费率
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存费率
     */
    @RequiresPermissions("dealpay:dealRate:add")
    @Log(title = "费率", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DealpayUserRateEntity dealpayUserRateEntity) {
        return toAjax(dealpayUserRateEntityService.insertDealpayUserRateEntity(dealpayUserRateEntity));
    }

    /**
     * 修改费率
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DealpayUserRateEntity dealpayUserRateEntity = dealpayUserRateEntityService.selectDealpayUserRateEntityById(id);
        mmap.put("dealpayUserRateEntity", dealpayUserRateEntity);
        return prefix + "/edit";
    }

    /**
     * 修改保存费率
     */
    @RequiresPermissions("dealpay:dealRate:edit")
    @Log(title = "费率", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DealpayUserRateEntity dealpayUserRateEntity) {
        return toAjax(dealpayUserRateEntityService.updateDealpayUserRateEntity(dealpayUserRateEntity));
    }

    /**
     * 商户费率状态更新
     */
    @RequiresPermissions("dealpay:dealRate:status")
    @Log(title = "用户产品费率", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult updateStatus(String id, String userId, String feeType, String switchs) {
        return toAjax(dealpayUserRateEntityService.changeStatus(id, userId, feeType, switchs));
    }

}
