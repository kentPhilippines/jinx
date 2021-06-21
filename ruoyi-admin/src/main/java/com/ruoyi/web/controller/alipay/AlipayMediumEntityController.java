package com.ruoyi.web.controller.alipay;

import java.util.List;

import com.ruoyi.alipay.domain.AlipayFileListEntity;
import com.ruoyi.alipay.service.IAlipayFileListEntityService;
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
import com.ruoyi.alipay.domain.AlipayMediumEntity;
import com.ruoyi.alipay.service.IAlipayMediumEntityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 收款媒介列Controller
 *
 * @author kiwi
 * @date 2020-03-17
 */
@Controller
@RequestMapping("/alipay/medium")
public class AlipayMediumEntityController extends BaseController {
    private String prefix = "alipay/medium";
    private String code_prefix = "alipay/file";

    @Autowired
    private IAlipayMediumEntityService alipayMediumEntityService;

    @Autowired
    private IAlipayFileListEntityService alipayFileListEntityService;

    @GetMapping()
    public String medium() {

        return prefix + "/medium";
    }

    /**
     * 查询收款媒介列列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayMediumEntity alipayMediumEntity) {
        startPage();
        List<AlipayMediumEntity> list = alipayMediumEntityService.selectAlipayMediumEntityList(alipayMediumEntity);
        return getDataTable(list);
    }

    /**
     * 导出收款媒介列列表
     */
    @Log(title = "收款媒介列", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayMediumEntity alipayMediumEntity) {
        List<AlipayMediumEntity> list = alipayMediumEntityService.selectAlipayMediumEntityList(alipayMediumEntity);
        ExcelUtil<AlipayMediumEntity> util = new ExcelUtil<AlipayMediumEntity>(AlipayMediumEntity.class);
        return util.exportExcel(list, "medium");
    }

    /**
     * 新增收款媒介列
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存收款媒介列
     */
    @Log(title = "收款媒介列", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayMediumEntity alipayMediumEntity) {
        return toAjax(alipayMediumEntityService.insertAlipayMediumEntity(alipayMediumEntity));
    }

    /**
     * 修改收款媒介列
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayMediumEntity alipayMediumEntity = alipayMediumEntityService.selectAlipayMediumEntityById(id);
        mmap.put("alipayMediumEntity", alipayMediumEntity);
        return prefix + "/edit";
    }

    /**
     * 修改单个媒介金额
     */
    @GetMapping("/editAmount/id/{id}")
    public String editAmountById(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayMediumEntity alipayMediumEntity = alipayMediumEntityService.selectAlipayMediumEntityById(id);
        mmap.put("alipayMediumEntity", alipayMediumEntity);
        return prefix + "/editAmount";
    }

    /**
     * 同种媒介修改金额
     */
    @GetMapping("/editAmount/code")
    public String editAmountByCode(ModelMap mmap) {
        List<String> codeList = alipayMediumEntityService.selectCodeByAlipayMediumEntity();
        mmap.put("alipayMediumEntity", new AlipayMediumEntity());
        mmap.put("codeList", codeList);
        return prefix + "/editAmountByCode";
    }


    /**
     * 修改保存收款媒介列
     */
    @Log(title = "收款媒介列", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayMediumEntity alipayMediumEntity) {
        return toAjax(alipayMediumEntityService.updateAlipayMediumEntity(alipayMediumEntity));
    }


    /**
     * 编辑保存单个媒介上限金额
     */
    @Log(title = "收款媒介列", businessType = BusinessType.UPDATE)
    @PostMapping("/editAmount")
    @ResponseBody
    public AjaxResult editSaveById(AlipayMediumEntity alipayMediumEntity) {
        return toAjax(alipayMediumEntityService.updateAlipayMediumEntity(alipayMediumEntity));
    }

    /**
     * 编辑保存同种媒介上限金额
     */
    @Log(title = "收款媒介列", businessType = BusinessType.UPDATE)
    @PostMapping("/editAmount/code")
    @ResponseBody
    public AjaxResult editSaveByCode(AlipayMediumEntity alipayMediumEntity) {
        return toAjax(alipayMediumEntityService.updateAlipayMediumEntityByCode(alipayMediumEntity));
    }


    /**
     * 删除收款媒介列
     */
    @Log(title = "收款媒介列", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(alipayMediumEntityService.deleteAlipayMediumEntityByIds(ids));
    }


    /**
     * 查看所属二维码列表
     */
    @GetMapping("/show/{userId}")
    public String showCodeList(@PathVariable("userId") String mediumId, ModelMap mmap) {
        AlipayFileListEntity alipayFileListEntity = new AlipayFileListEntity();
        alipayFileListEntity.setConcealId(mediumId);
        alipayFileListEntity.setIsDeal("2");
        List<AlipayFileListEntity> list = alipayFileListEntityService.selectAlipayFileListEntityList(alipayFileListEntity);
        mmap.put("codeList", list);
        return code_prefix + "/group_code_list";
    }


}
