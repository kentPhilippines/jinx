package com.ruoyi.web.controller.alipay;

import com.ruoyi.alipay.domain.AlipayBankListEntity;
import com.ruoyi.alipay.service.IAlipayBankListEntityService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 银行卡列表Controller
 *
 * @author kiwi
 * @date 2020-03-17
 */
@Controller
@RequestMapping("/alipay/bankCard")
public class AlipayBankListEntityController extends BaseController {
    private String prefix = "alipay/bankCard";
    @Autowired
    private IAlipayBankListEntityService alipayBankListEntityService;

    @GetMapping()
    public String bankCard() {
        return prefix + "/bankCard";
    }

    /**
     * 查询银行卡列表列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayBankListEntity alipayBankListEntity) {
        startPage();
        List<AlipayBankListEntity> list = alipayBankListEntityService.selectAlipayBankListEntityList(alipayBankListEntity);
        return getDataTable(list);
    }

    /**
     * 导出银行卡列表列表
     */
    @Log(title = "银行卡列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayBankListEntity alipayBankListEntity) {
        List<AlipayBankListEntity> list = alipayBankListEntityService
                .selectAlipayBankListEntityList(alipayBankListEntity);
        ExcelUtil<AlipayBankListEntity> util = new ExcelUtil<AlipayBankListEntity>(AlipayBankListEntity.class);
        return util.exportExcel(list, "bankCard");
    }

    @Autowired
    private ISysDictDataService dictDataService;

    /**
     * 新增银行卡列表
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        SysDictData dictData = new SysDictData();
        dictData.setDictType("system_bankcode");
        List<SysDictData> bankcode = dictDataService.selectDictDataList(dictData);
        mmap.put("bankcode", bankcode);
        return prefix + "/add";
    }

    /**
     * 新增保存银行卡列表
     */
    @Log(title = "银行卡列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayBankListEntity alipayBankListEntity) {
        return toAjax(alipayBankListEntityService.insertAlipayBankListEntity(alipayBankListEntity));
    }

    /**
     * 修改银行卡列表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayBankListEntity alipayBankListEntity = alipayBankListEntityService.selectAlipayBankListEntityById(id);
        mmap.put("alipayBankListEntity", alipayBankListEntity);
        SysDictData dictData = new SysDictData();
        dictData.setDictType("system_bankcode");
        List<SysDictData> bankcode = dictDataService.selectDictDataList(dictData);
        mmap.put("bankcode", bankcode);
        return prefix + "/edit";
    }

    /**
     * 修改保存银行卡列表
     */
    @Log(title = "银行卡列表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayBankListEntity alipayBankListEntity) {
        return toAjax(alipayBankListEntityService.updateAlipayBankListEntity(alipayBankListEntity));
    }

    /**
     * 删除银行卡列表
     */
    @Log(title = "银行卡列表", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(alipayBankListEntityService.deleteAlipayBankListEntityByIds(ids));
    }

    /**
     * 码商状态修改（调用api）
     */
    @Log(title = "码商查询", businessType = BusinessType.UPDATE)
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(AlipayBankListEntity alipayBankListEntity) {
        return toAjax(alipayBankListEntityService.updateBankCardStatusById(alipayBankListEntity));
    }
}
