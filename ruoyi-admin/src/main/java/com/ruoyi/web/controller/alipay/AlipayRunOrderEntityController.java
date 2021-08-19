package com.ruoyi.web.controller.alipay;

import com.ruoyi.alipay.domain.AlipayRunOrderEntity;
import com.ruoyi.alipay.service.IAlipayRunOrderEntityService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 流水订单记录Controller
 *
 * @author kiwi
 * @date 2020-03-18
 */
@Controller
@RequestMapping("/alipay/running")
public class AlipayRunOrderEntityController extends BaseController {
	private String prefix = "alipay/running";
	@Autowired
	private IAlipayRunOrderEntityService alipayRunOrderEntityService;

	@GetMapping
	@RequiresPermissions("alipay:running:view")
	public String running() {
		return prefix + "/running";
	}

	/**
	 * 查询流水订单记录列表
	 */
	@PostMapping("/list")
	@RequiresPermissions("running:alipay:list")
	@ResponseBody
	public TableDataInfo list(AlipayRunOrderEntity alipayRunOrderEntity) {
		startPage();
		List<AlipayRunOrderEntity> list = alipayRunOrderEntityService.selectAlipayRunOrderEntityList(alipayRunOrderEntity);
		return getDataTable(list);
	}

	/**
	 * 导出流水订单记录列表
	 */
	@Log(title = "资金流水", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@RequiresPermissions("alipay:running:export")
	@ResponseBody
	public AjaxResult export(AlipayRunOrderEntity alipayRunOrderEntity) {
		List<AlipayRunOrderEntity> list = alipayRunOrderEntityService
				.selectAlipayRunOrderEntityList(alipayRunOrderEntity);
		ExcelUtil<AlipayRunOrderEntity> util = new ExcelUtil<AlipayRunOrderEntity>(AlipayRunOrderEntity.class);
		return util.exportExcel(list, "runOrder");
	}
}
