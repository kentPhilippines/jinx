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
import com.ruoyi.alipay.domain.AlipayUserFundEntity;
import com.ruoyi.alipay.service.IAlipayUserFundEntityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户资金账户Controller
 * 
 * @author kiwi
 * @date 2020-03-17
 */
@Controller
@RequestMapping("/alipay/fund")
public class AlipayUserFundEntityController extends BaseController
{
    private String prefix = "alipay/fund";

    @Autowired
    private IAlipayUserFundEntityService alipayUserFundEntityService;

    @RequiresPermissions("alipay:fund:view")
    @GetMapping()
    public String fund()
    {
        return prefix + "/fund";
    }

    /**
     * 查询用户资金账户列表
     */
    @RequiresPermissions("alipay:fund:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayUserFundEntity alipayUserFundEntity)
    {
        startPage();
        List<AlipayUserFundEntity> list = alipayUserFundEntityService.selectAlipayUserFundEntityList(alipayUserFundEntity);
        return getDataTable(list);
    }

    /**
     * 导出用户资金账户列表
     */
    @RequiresPermissions("alipay:fund:export")
    @Log(title = "用户资金账户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayUserFundEntity alipayUserFundEntity)
    {
        List<AlipayUserFundEntity> list = alipayUserFundEntityService.selectAlipayUserFundEntityList(alipayUserFundEntity);
        ExcelUtil<AlipayUserFundEntity> util = new ExcelUtil<AlipayUserFundEntity>(AlipayUserFundEntity.class);
        return util.exportExcel(list, "fund");
    }

    /**
     * 新增用户资金账户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户资金账户
     */
    @RequiresPermissions("alipay:fund:add")
    @Log(title = "用户资金账户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayUserFundEntity alipayUserFundEntity)
    {
        return toAjax(alipayUserFundEntityService.insertAlipayUserFundEntity(alipayUserFundEntity));
    }

    /**
     * 修改用户资金账户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        AlipayUserFundEntity alipayUserFundEntity = alipayUserFundEntityService.selectAlipayUserFundEntityById(id);
        mmap.put("alipayUserFundEntity", alipayUserFundEntity);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户资金账户
     */
    @RequiresPermissions("alipay:fund:edit")
    @Log(title = "用户资金账户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayUserFundEntity alipayUserFundEntity)
    {
        return toAjax(alipayUserFundEntityService.updateAlipayUserFundEntity(alipayUserFundEntity));
    }

    /**
     * 删除用户资金账户
     */
    @RequiresPermissions("alipay:fund:remove")
    @Log(title = "用户资金账户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(alipayUserFundEntityService.deleteAlipayUserFundEntityByIds(ids));
    }
}
