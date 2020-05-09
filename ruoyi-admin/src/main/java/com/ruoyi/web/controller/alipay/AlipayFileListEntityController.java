package com.ruoyi.web.controller.alipay;

import java.util.List;
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
import com.ruoyi.alipay.domain.AlipayFileListEntity;
import com.ruoyi.alipay.service.IAlipayFileListEntityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 文件列Controller
 * 
 * @author kiwi
 * @date 2020-03-17
 */
@Controller
@RequestMapping("/alipay/file")
public class AlipayFileListEntityController extends BaseController {
	private String prefix = "alipay/file";

	@Autowired
	private IAlipayFileListEntityService alipayFileListEntityService;

	@GetMapping()
	public String file() {
		return prefix + "/file";
	}

	/**
	 * 查询文件列列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AlipayFileListEntity alipayFileListEntity) {
		startPage();
		List<AlipayFileListEntity> list = alipayFileListEntityService
				.selectAlipayFileListEntityList(alipayFileListEntity);
		return getDataTable(list);
	}

	/**
	 * 导出文件列列表
	 */
	@Log(title = "文件列", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(AlipayFileListEntity alipayFileListEntity) {
		List<AlipayFileListEntity> list = alipayFileListEntityService
				.selectAlipayFileListEntityList(alipayFileListEntity);
		ExcelUtil<AlipayFileListEntity> util = new ExcelUtil<AlipayFileListEntity>(AlipayFileListEntity.class);
		return util.exportExcel(list, "file");
	}

	/**
	 * 新增文件列
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存文件列
	 */
	@Log(title = "文件列", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AlipayFileListEntity alipayFileListEntity) {
		return toAjax(alipayFileListEntityService.insertAlipayFileListEntity(alipayFileListEntity));
	}

	/**
	 * 修改文件列
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		AlipayFileListEntity alipayFileListEntity = alipayFileListEntityService.selectAlipayFileListEntityById(id);
		mmap.put("alipayFileListEntity", alipayFileListEntity);
		return prefix + "/edit";
	}

	/**
	 * 修改保存文件列
	 */
	@Log(title = "文件列", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AlipayFileListEntity alipayFileListEntity) {
		return toAjax(alipayFileListEntityService.updateAlipayFileListEntity(alipayFileListEntity));
	}

	/**
	 * 删除文件列
	 */
	@Log(title = "文件列", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(alipayFileListEntityService.deleteAlipayFileListEntityByIds(ids));
	}
}
