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
import com.ruoyi.alipay.domain.AlipayDealOrderApp;
import com.ruoyi.alipay.service.IAlipayDealOrderAppService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商户订单登记Controller
 * 
 * @author kiwi
 * @date 2020-03-17
 */
@Controller
@RequestMapping("/alipay/orderApp")
public class AlipayDealOrderAppController extends BaseController {
	private String prefix = "alipay/orderApp";
	@Autowired
	private IAlipayDealOrderAppService alipayDealOrderAppService;
	@RequiresPermissions("alipay:orderApp:view")
	@GetMapping()
	public String orderApp() {
		return prefix + "/orderApp";
	}

	/**
	 * 查询商户订单登记列表
	 */
	@RequiresPermissions("alipay:orderApp:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AlipayDealOrderApp alipayDealOrderApp) {
		startPage();
		List<AlipayDealOrderApp> list = alipayDealOrderAppService.selectAlipayDealOrderAppList(alipayDealOrderApp);
		return getDataTable(list);
	}

	/**
	 * 导出商户订单登记列表
	 */
	@RequiresPermissions("alipay:orderApp:export")
	@Log(title = "商户订单登记", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(AlipayDealOrderApp alipayDealOrderApp) {
		List<AlipayDealOrderApp> list = alipayDealOrderAppService.selectAlipayDealOrderAppList(alipayDealOrderApp);
		ExcelUtil<AlipayDealOrderApp> util = new ExcelUtil<AlipayDealOrderApp>(AlipayDealOrderApp.class);
		return util.exportExcel(list, "orderApp");
	}

	/**
	 * 新增商户订单登记
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存商户订单登记
	 */
	@RequiresPermissions("alipay:orderApp:add")
	@Log(title = "商户订单登记", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AlipayDealOrderApp alipayDealOrderApp) {
		return toAjax(alipayDealOrderAppService.insertAlipayDealOrderApp(alipayDealOrderApp));
	}

	/**
	 * 修改商户订单登记
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		AlipayDealOrderApp alipayDealOrderApp = alipayDealOrderAppService.selectAlipayDealOrderAppById(id);
		mmap.put("alipayDealOrderApp", alipayDealOrderApp);
		return prefix + "/edit";
	}

	/**
	 * 修改保存商户订单登记
	 */
	@RequiresPermissions("alipay:orderApp:edit")
	@Log(title = "商户订单登记", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AlipayDealOrderApp alipayDealOrderApp) {
		return toAjax(alipayDealOrderAppService.updateAlipayDealOrderApp(alipayDealOrderApp));
	}

	/**
	 * 删除商户订单登记
	 */
	@RequiresPermissions("alipay:orderApp:remove")
	@Log(title = "商户订单登记", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(alipayDealOrderAppService.deleteAlipayDealOrderAppByIds(ids));
	}
}
