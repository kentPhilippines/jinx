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
import com.ruoyi.alipay.domain.AlipayCorrelationData;
import com.ruoyi.alipay.service.IAlipayCorrelationDataService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单记录表Controller
 * 
 * @author kiwi
 * @date 2020-03-17
 */
@Controller
@RequestMapping("/alipay/record")
public class AlipayCorrelationDataController extends BaseController {
	private String prefix = "alipay/record";
	@Autowired
	private IAlipayCorrelationDataService alipayCorrelationDataService;

	@GetMapping()
	public String record() {
		return prefix + "/record";
	}

	/**
	 * 查询订单记录表列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AlipayCorrelationData alipayCorrelationData) {
		startPage();
		List<AlipayCorrelationData> list = alipayCorrelationDataService
				.selectAlipayCorrelationDataList(alipayCorrelationData);
		return getDataTable(list);
	}

	/**
	 * 导出订单记录表列表
	 */
	@Log(title = "订单记录表", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(AlipayCorrelationData alipayCorrelationData) {
		List<AlipayCorrelationData> list = alipayCorrelationDataService
				.selectAlipayCorrelationDataList(alipayCorrelationData);
		ExcelUtil<AlipayCorrelationData> util = new ExcelUtil<AlipayCorrelationData>(AlipayCorrelationData.class);
		return util.exportExcel(list, "record");
	}

	/**
	 * 新增订单记录表
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存订单记录表
	 */
	@Log(title = "订单记录表", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AlipayCorrelationData alipayCorrelationData) {
		return toAjax(alipayCorrelationDataService.insertAlipayCorrelationData(alipayCorrelationData));
	}

	/**
	 * 修改订单记录表
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		AlipayCorrelationData alipayCorrelationData = alipayCorrelationDataService.selectAlipayCorrelationDataById(id);
		mmap.put("alipayCorrelationData", alipayCorrelationData);
		return prefix + "/edit";
	}

	/**
	 * 修改保存订单记录表
	 */
	@Log(title = "订单记录表", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AlipayCorrelationData alipayCorrelationData) {
		return toAjax(alipayCorrelationDataService.updateAlipayCorrelationData(alipayCorrelationData));
	}

	/**
	 * 删除订单记录表
	 */
	@Log(title = "订单记录表", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(alipayCorrelationDataService.deleteAlipayCorrelationDataByIds(ids));
	}
}
