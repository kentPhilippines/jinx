package com.ruoyi.web.controller.alipay;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.StatisticsEntity;
import com.ruoyi.common.utils.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.weaver.loadtime.Aj;
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
import com.ruoyi.alipay.domain.AlipayRechargeEntity;
import com.ruoyi.alipay.service.IAlipayRechargeEntityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 充值记录Controller
 * 
 * @author kiwi
 * @date 2020-03-17
 */
@Controller
@RequestMapping("/alipay/deposit")
public class AlipayRechargeEntityController extends BaseController {
	private String prefix = "alipay/deposit";

	@Autowired
	private IAlipayRechargeEntityService alipayRechargeEntityService;

	@RequiresPermissions("alipay:deposit:view")
	@GetMapping()
	public String deposit() {
		return prefix + "/deposit";
	}

	/**
	 * 查询充值记录列表
	 */
	@RequiresPermissions("alipay:deposit:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AlipayRechargeEntity alipayRechargeEntity) {
		startPage();
		List<AlipayRechargeEntity> list = alipayRechargeEntityService
				.selectAlipayRechargeEntityList(alipayRechargeEntity);
		return getDataTable(list);
	}

	/**
	 * 导出充值记录列表
	 */
	@RequiresPermissions("alipay:deposit:export")
	@Log(title = "充值记录", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(AlipayRechargeEntity alipayRechargeEntity) {
		List<AlipayRechargeEntity> list = alipayRechargeEntityService
				.selectAlipayRechargeEntityList(alipayRechargeEntity);
		ExcelUtil<AlipayRechargeEntity> util = new ExcelUtil<AlipayRechargeEntity>(AlipayRechargeEntity.class);
		return util.exportExcel(list, "deposit");
	}


	/**
	 * 修改充值记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		AlipayRechargeEntity alipayRechargeEntity = alipayRechargeEntityService.selectAlipayRechargeEntityById(id);
		mmap.put("alipayRechargeEntity", alipayRechargeEntity);
		return prefix + "/edit";
	}

	/**
	 * 转发财务
	 */
	@RequiresPermissions("alipay:deposit:toFinance")
	@Log(title = "充值记录", businessType = BusinessType.DELETE)
	@PostMapping("/updateOrder")
	@ResponseBody
	public AjaxResult transfer(AlipayRechargeEntity alipayRechargeEntity){
		alipayRechargeEntity.setOrderStatus("4");
		alipayRechargeEntity.setSubmitTime(new Date());
		return toAjax(alipayRechargeEntityService.updateAlipayRechargeEntity(alipayRechargeEntity));
	}

	/**
	 * 商户提现统计当天数据
	 * @param mmap
	 * @return
	 */
	@RequiresPermissions("qr:deposit:statistics")
	@GetMapping("/statistics/qr/deposit")
	public String dayStat(ModelMap mmap) {
		StatisticsEntity statisticsEntity = alipayRechargeEntityService.selectQrDpositStatisticsDataByDay(DateUtils.dayStart(), DateUtils.dayEnd());
		if (statisticsEntity.getTotalCount() == 0) {
			statisticsEntity.setSuccessPercent(0.00);
		} else {
			BigDecimal percent = BigDecimal.valueOf((float) statisticsEntity.getSuccessCount() / statisticsEntity.getTotalCount());
			Double successPercent = percent.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			statisticsEntity.setSuccessPercent(successPercent);
		}
		mmap.put("statisticsEntity", statisticsEntity);
		return prefix + "/currentData";
	}

}
