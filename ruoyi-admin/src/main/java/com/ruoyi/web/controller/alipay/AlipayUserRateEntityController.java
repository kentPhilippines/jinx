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
import com.ruoyi.system.domain.AlipayUserRateEntity;
import com.ruoyi.system.service.IAlipayUserRateEntityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户产品费率Controller
 * 
 * @author kiwi
 * @date 2020-03-18
 */
@Controller
@RequestMapping("/alipay/rate")
public class AlipayUserRateEntityController extends BaseController
{
    private String prefix = "alipay/rate";

    @Autowired
    private IAlipayUserRateEntityService alipayUserRateEntityService;

    @RequiresPermissions("alipay:rate:view")
    @GetMapping()
    public String rate()
    {
        return prefix + "/rate";
    }

    /**
     * 查询用户产品费率列表
     */
    @RequiresPermissions("alipay:rate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayUserRateEntity alipayUserRateEntity)
    {
        startPage();
        List<AlipayUserRateEntity> list = alipayUserRateEntityService.selectAlipayUserRateEntityList(alipayUserRateEntity);
        return getDataTable(list);
    }

    /**
     * 导出用户产品费率列表
     */
    @RequiresPermissions("alipay:rate:export")
    @Log(title = "用户产品费率", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayUserRateEntity alipayUserRateEntity)
    {
        List<AlipayUserRateEntity> list = alipayUserRateEntityService.selectAlipayUserRateEntityList(alipayUserRateEntity);
        ExcelUtil<AlipayUserRateEntity> util = new ExcelUtil<AlipayUserRateEntity>(AlipayUserRateEntity.class);
        return util.exportExcel(list, "rate");
    }

    /**
     * 新增用户产品费率
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户产品费率
     */
    @RequiresPermissions("alipay:rate:add")
    @Log(title = "用户产品费率", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayUserRateEntity alipayUserRateEntity)
    {
        return toAjax(alipayUserRateEntityService.insertAlipayUserRateEntity(alipayUserRateEntity));
    }

    /**
     * 修改用户产品费率
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        AlipayUserRateEntity alipayUserRateEntity = alipayUserRateEntityService.selectAlipayUserRateEntityById(id);
        mmap.put("alipayUserRateEntity", alipayUserRateEntity);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户产品费率
     */
    @RequiresPermissions("alipay:rate:edit")
    @Log(title = "用户产品费率", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayUserRateEntity alipayUserRateEntity)
    {
        return toAjax(alipayUserRateEntityService.updateAlipayUserRateEntity(alipayUserRateEntity));
    }

    /**
     * 删除用户产品费率
     */
    @RequiresPermissions("alipay:rate:remove")
    @Log(title = "用户产品费率", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(alipayUserRateEntityService.deleteAlipayUserRateEntityByIds(ids));
    }
}
