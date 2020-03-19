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
import com.ruoyi.alipay.domain.AlipayLogEntity;
import com.ruoyi.alipay.service.IAlipayLogEntityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 日志表Controller
 * 
 * @author kiwi
 * @date 2020-03-17
 */
@Controller
@RequestMapping("/alipay/loginLog")
public class AlipayLogEntityController extends BaseController {
	private String prefix = "alipay/loginLog";

	@Autowired
	private IAlipayLogEntityService alipayLogEntityService;

	@RequiresPermissions("alipay:loginLog:view")
	@GetMapping()
	public String loginLog() {
		return prefix + "/loginLog";
	}
	/**
	 * 查询日志表列表
	 */
	@RequiresPermissions("alipay:loginLog:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AlipayLogEntity alipayLogEntity) {
		startPage();
		List<AlipayLogEntity> list = alipayLogEntityService.selectAlipayLogEntityList(alipayLogEntity);
		return getDataTable(list);
	}

	/**
	 * 导出日志表列表
	 */
	@RequiresPermissions("alipay:loginLog:export")
	@Log(title = "日志表", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(AlipayLogEntity alipayLogEntity) {
		List<AlipayLogEntity> list = alipayLogEntityService.selectAlipayLogEntityList(alipayLogEntity);
		ExcelUtil<AlipayLogEntity> util = new ExcelUtil<AlipayLogEntity>(AlipayLogEntity.class);
		return util.exportExcel(list, "loginLog");
	}

	/**
	 * 新增日志表
	 */
	@GetMapping("/add")
	public String add() {
		return prefix + "/add";
	}

	/**
	 * 新增保存日志表
	 */
	@RequiresPermissions("alipay:loginLog:add")
	@Log(title = "日志表", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AlipayLogEntity alipayLogEntity) {
		return toAjax(alipayLogEntityService.insertAlipayLogEntity(alipayLogEntity));
	}

	/**
	 * 修改日志表
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap) {
		AlipayLogEntity alipayLogEntity = alipayLogEntityService.selectAlipayLogEntityById(id);
		mmap.put("alipayLogEntity", alipayLogEntity);
		return prefix + "/edit";
	}

	/**
	 * 修改保存日志表
	 */
	@RequiresPermissions("alipay:loginLog:edit")
	@Log(title = "日志表", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AlipayLogEntity alipayLogEntity) {
		return toAjax(alipayLogEntityService.updateAlipayLogEntity(alipayLogEntity));
	}

	/**
	 * 删除日志表
	 */
	@RequiresPermissions("alipay:loginLog:remove")
	@Log(title = "日志表", businessType = BusinessType.DELETE)
	@PostMapping("/remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		return toAjax(alipayLogEntityService.deleteAlipayLogEntityByIds(ids));
	}
}
