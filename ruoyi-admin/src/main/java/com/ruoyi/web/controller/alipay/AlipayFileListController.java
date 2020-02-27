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
import com.ruoyi.alipay.domain.AlipayFileList;
import com.ruoyi.alipay.service.IAlipayFileListService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文件信息表Controller
 *
 * @author otc
 * @date 2020-02-27
 */
@Controller
@RequestMapping("/alipay/fileList")
public class AlipayFileListController extends BaseController {
    private String prefix = "alipay/fileList";

    @Autowired
    private IAlipayFileListService alipayFileListService;

    @RequiresPermissions("alipay:fileList:view")
    @GetMapping()
    public String fileList() {
        return prefix + "/fileList";
    }

    /**
     * 查询文件信息表列表
     */
    @RequiresPermissions("alipay:fileList:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayFileList alipayFileList) {
        startPage();
        List<AlipayFileList> list = alipayFileListService.selectAlipayFileListList(alipayFileList);
        return getDataTable(list);
    }

    /**
     * 导出文件信息表列表
     */
    @RequiresPermissions("alipay:fileList:export")
    @Log(title = "文件信息表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayFileList alipayFileList) {
        List<AlipayFileList> list = alipayFileListService.selectAlipayFileListList(alipayFileList);
        ExcelUtil<AlipayFileList> util = new ExcelUtil<AlipayFileList>(AlipayFileList.class);
        return util.exportExcel(list, "fileList");
    }

    /**
     * 新增文件信息表
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存文件信息表
     */
    @RequiresPermissions("alipay:fileList:add")
    @Log(title = "文件信息表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AlipayFileList alipayFileList) {
        return toAjax(alipayFileListService.insertAlipayFileList(alipayFileList));
    }

    /**
     * 修改文件信息表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayFileList alipayFileList = alipayFileListService.selectAlipayFileListById(id);
        mmap.put("alipayFileList", alipayFileList);
        return prefix + "/edit";
    }

    /**
     * 修改保存文件信息表
     */
    @RequiresPermissions("alipay:fileList:edit")
    @Log(title = "文件信息表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AlipayFileList alipayFileList) {
        return toAjax(alipayFileListService.updateAlipayFileList(alipayFileList));
    }

    /**
     * 删除文件信息表
     */
    @RequiresPermissions("alipay:fileList:remove")
    @Log(title = "文件信息表", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(alipayFileListService.deleteAlipayFileListByIds(ids));
    }
}
