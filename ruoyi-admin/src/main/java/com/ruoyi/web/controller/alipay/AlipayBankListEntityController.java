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
import com.ruoyi.alipay.domain.AlipayBankListEntity;
import com.ruoyi.alipay.service.IAlipayBankListEntityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

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
	@RequiresPermissions("alipay:bankCard:view")
	@GetMapping()
	public String bankCard() {
		return prefix + "/bankCard";
	}
	/**
	 * 查询银行卡列表列表
	 */
	@RequiresPermissions("alipay:bankCard:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AlipayBankListEntity alipayBankListEntity) {
		startPage();
		List<AlipayBankListEntity> list = alipayBankListEntityService
				.selectAlipayBankListEntityList(alipayBankListEntity);
		return getDataTable(list);
	}

	/**
	 * 导出银行卡列表列表
	 */
	@RequiresPermissions("alipay:bankCard:export")
	@Log(title = "银行卡列表", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(AlipayBankListEntity alipayBankListEntity) {
		List<AlipayBankListEntity> list = alipayBankListEntityService
				.selectAlipayBankListEntityList(alipayBankListEntity);
		ExcelUtil<AlipayBankListEntity> util = new ExcelUtil<AlipayBankListEntity>(AlipayBankListEntity.class);
		return util.exportExcel(list, "bankCard");
	}

	/**
	 * 新增银行卡列表
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存银行卡列表
	 */
	@RequiresPermissions("alipay:bankCard:add")
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
		return prefix + "/edit";
	}

	/**
	 * 修改保存银行卡列表
	 */
	@RequiresPermissions("alipay:bankCard:edit")
	@Log(title = "银行卡列表", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AlipayBankListEntity alipayBankListEntity) {
		return toAjax(alipayBankListEntityService.updateAlipayBankListEntity(alipayBankListEntity));
	}

	/**
	 * 删除银行卡列表
	 */
	@RequiresPermissions("alipay:bankCard:remove")
	@Log(title = "银行卡列表", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(alipayBankListEntityService.deleteAlipayBankListEntityByIds(ids));
	}
}
