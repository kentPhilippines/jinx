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
import com.ruoyi.alipay.domain.AlipayRunOrderEntity;
import com.ruoyi.alipay.service.IAlipayRunOrderEntityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 流水订单记录Controller
 * 
 * @author kiwi
 * @date 2020-03-17
 */
@Controller
@RequestMapping("/alipay/running")
public class AlipayRunOrderEntityController extends BaseController
{
    private String prefix = "alipay/running";

    @Autowired
    private IAlipayRunOrderEntityService alipayRunOrderEntityService;

    @RequiresPermissions("alipay:running:view")
    @GetMapping()
    public String running()
    {
        return prefix + "/running";
    }

    /**
     * 查询流水订单记录列表
     */
    @RequiresPermissions("alipay:running:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayRunOrderEntity alipayRunOrderEntity)
    {
        startPage();
        List<AlipayRunOrderEntity> list = alipayRunOrderEntityService.selectAlipayRunOrderEntityList(alipayRunOrderEntity);
        return getDataTable(list);
    }

    /**
     * 导出流水订单记录列表
     */
    @RequiresPermissions("alipay:running:export")
    @Log(title = "流水订单记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayRunOrderEntity alipayRunOrderEntity)
    {
        List<AlipayRunOrderEntity> list = alipayRunOrderEntityService.selectAlipayRunOrderEntityList(alipayRunOrderEntity);
        ExcelUtil<AlipayRunOrderEntity> util = new ExcelUtil<AlipayRunOrderEntity>(AlipayRunOrderEntity.class);
        return util.exportExcel(list, "running");
    }

    /**
     * 新增流水订单记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存流水订单记录
     */
    @RequiresPermissions("alipay:running:add")
    @Log(title = "流水订单记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayRunOrderEntity alipayRunOrderEntity)
    {
        return toAjax(alipayRunOrderEntityService.insertAlipayRunOrderEntity(alipayRunOrderEntity));
    }

    /**
     * 修改流水订单记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        AlipayRunOrderEntity alipayRunOrderEntity = alipayRunOrderEntityService.selectAlipayRunOrderEntityById(id);
        mmap.put("alipayRunOrderEntity", alipayRunOrderEntity);
        return prefix + "/edit";
    }

    /**
     * 修改保存流水订单记录
     */
    @RequiresPermissions("alipay:running:edit")
    @Log(title = "流水订单记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayRunOrderEntity alipayRunOrderEntity)
    {
        return toAjax(alipayRunOrderEntityService.updateAlipayRunOrderEntity(alipayRunOrderEntity));
    }

    /**
     * 删除流水订单记录
     */
    @RequiresPermissions("alipay:running:remove")
    @Log(title = "流水订单记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(alipayRunOrderEntityService.deleteAlipayRunOrderEntityByIds(ids));
    }
}
