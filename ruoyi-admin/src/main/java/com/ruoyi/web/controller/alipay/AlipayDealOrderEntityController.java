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
import com.ruoyi.alipay.domain.AlipayDealOrderEntity;
import com.ruoyi.alipay.service.IAlipayDealOrderEntityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 交易订单Controller
 * 
 * @author kiwi
 * @date 2020-03-17
 */
@Controller
@RequestMapping("/alipay/orderDeal")
public class AlipayDealOrderEntityController extends BaseController {
	private String prefix = "alipay/orderDeal";

	@Autowired
	private IAlipayDealOrderEntityService alipayDealOrderEntityService;

	@RequiresPermissions("alipay:orderDeal:view")
	@GetMapping()
	public String orderDeal() {
		return prefix + "/orderDeal";
	}

	/**
	 * 查询交易订单列表
	 */
	@RequiresPermissions("alipay:orderDeal:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AlipayDealOrderEntity alipayDealOrderEntity) {
		startPage();
		List<AlipayDealOrderEntity> list = alipayDealOrderEntityService
				.selectAlipayDealOrderEntityList(alipayDealOrderEntity);
		return getDataTable(list);
	}

	/**
	 * 导出交易订单列表
	 */
	@RequiresPermissions("alipay:orderDeal:export")
	@Log(title = "交易订单", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(AlipayDealOrderEntity alipayDealOrderEntity) {
		List<AlipayDealOrderEntity> list = alipayDealOrderEntityService
				.selectAlipayDealOrderEntityList(alipayDealOrderEntity);
		ExcelUtil<AlipayDealOrderEntity> util = new ExcelUtil<AlipayDealOrderEntity>(AlipayDealOrderEntity.class);
		return util.exportExcel(list, "orderDeal");
	}

	/**
	 * 新增交易订单
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存交易订单
	 */
	@RequiresPermissions("alipay:orderDeal:add")
	@Log(title = "交易订单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AlipayDealOrderEntity alipayDealOrderEntity) {
		return toAjax(alipayDealOrderEntityService.insertAlipayDealOrderEntity(alipayDealOrderEntity));
	}

	/**
	 * 修改交易订单
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		AlipayDealOrderEntity alipayDealOrderEntity = alipayDealOrderEntityService.selectAlipayDealOrderEntityById(id);
		mmap.put("alipayDealOrderEntity", alipayDealOrderEntity);
		return prefix + "/edit";
	}

	/**
	 * 修改保存交易订单
	 */
	@RequiresPermissions("alipay:orderDeal:edit")
	@Log(title = "交易订单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AlipayDealOrderEntity alipayDealOrderEntity) {
		return toAjax(alipayDealOrderEntityService.updateAlipayDealOrderEntity(alipayDealOrderEntity));
	}

}
