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
import com.ruoyi.alipay.domain.AlipayWithdrawEntity;
import com.ruoyi.alipay.service.IAlipayWithdrawEntityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 会员提现记录Controller
 * 
 * @author kiwi
 * @date 2020-03-17
 */
@Controller
@RequestMapping("/alipay/withdrawal")
public class AlipayWithdrawEntityController extends BaseController {
	private String prefix = "alipay/withdrawal";

	@Autowired
	private IAlipayWithdrawEntityService alipayWithdrawEntityService;

	@RequiresPermissions("alipay:withdrawal:view")
	@GetMapping()
	public String withdrawal() {
		return prefix + "/withdrawal";
	}

	/**
	 * 查询会员提现记录列表
	 */
	@RequiresPermissions("alipay:withdrawal:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AlipayWithdrawEntity alipayWithdrawEntity) {
		startPage();
		List<AlipayWithdrawEntity> list = alipayWithdrawEntityService
				.selectAlipayWithdrawEntityList(alipayWithdrawEntity);
		return getDataTable(list);
	}

	/**
	 * 导出会员提现记录列表
	 */
	@RequiresPermissions("alipay:withdrawal:export")
	@Log(title = "会员提现记录", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(AlipayWithdrawEntity alipayWithdrawEntity) {
		List<AlipayWithdrawEntity> list = alipayWithdrawEntityService
				.selectAlipayWithdrawEntityList(alipayWithdrawEntity);
		ExcelUtil<AlipayWithdrawEntity> util = new ExcelUtil<AlipayWithdrawEntity>(AlipayWithdrawEntity.class);
		return util.exportExcel(list, "withdrawal");
	}

	/**
	 * 新增会员提现记录
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存会员提现记录
	 */
	@RequiresPermissions("alipay:withdrawal:add")
	@Log(title = "会员提现记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AlipayWithdrawEntity alipayWithdrawEntity) {
		return toAjax(alipayWithdrawEntityService.insertAlipayWithdrawEntity(alipayWithdrawEntity));
	}

	/**
	 * 修改会员提现记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		AlipayWithdrawEntity alipayWithdrawEntity = alipayWithdrawEntityService.selectAlipayWithdrawEntityById(id);
		mmap.put("alipayWithdrawEntity", alipayWithdrawEntity);
		return prefix + "/edit";
	}

	/**
	 * 修改保存会员提现记录
	 */
	@RequiresPermissions("alipay:withdrawal:edit")
	@Log(title = "会员提现记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AlipayWithdrawEntity alipayWithdrawEntity) {
		return toAjax(alipayWithdrawEntityService.updateAlipayWithdrawEntity(alipayWithdrawEntity));
	}

	/**
	 * 删除会员提现记录
	 */
	@RequiresPermissions("alipay:withdrawal:remove")
	@Log(title = "会员提现记录", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(alipayWithdrawEntityService.deleteAlipayWithdrawEntityByIds(ids));
	}
}
