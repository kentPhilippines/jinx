package com.ruoyi.web.controller.alipay;

import java.util.List;

import com.ruoyi.alipay.domain.AlipayFileListEntity;
import com.ruoyi.alipay.service.IAlipayFileListEntityService;
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

	@RequiresPermissions("alipay:medium:view")
	@GetMapping()
	public String medium() {
		return prefix + "/medium";
	}
	/**
	 * 查询收款媒介列列表
	 */
	@RequiresPermissions("alipay:medium:list")
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
	@RequiresPermissions("alipay:medium:export")
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
	@RequiresPermissions("alipay:medium:add")
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
	 * 修改保存收款媒介列
	 */
	@RequiresPermissions("alipay:medium:edit")
	@Log(title = "收款媒介列", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AlipayMediumEntity alipayMediumEntity) {
		return toAjax(alipayMediumEntityService.updateAlipayMediumEntity(alipayMediumEntity));
	}

	/**
	 * 删除收款媒介列
	 */
	@RequiresPermissions("alipay:medium:remove")
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
