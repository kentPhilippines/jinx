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

    @RequiresPermissions("qr:withdrawal:view")
    @GetMapping("/qr")
    public String qr_withdrawal() {
        return prefix + "/qr_withdrawal";
    }

    /**
     * 查询码商提现记录列表
     */
    @RequiresPermissions("qr:withdrawal:list")
    @PostMapping("/qr/list")
    @ResponseBody
    public TableDataInfo qr_list(AlipayWithdrawEntity alipayWithdrawEntity) {
        startPage();
        alipayWithdrawEntity.setWithdrawType("2");
        List<AlipayWithdrawEntity> list = alipayWithdrawEntityService
                .selectAlipayWithdrawEntityList(alipayWithdrawEntity);
        return getDataTable(list);
    }

    @RequiresPermissions("merchant:withdrawal:view")
    @GetMapping("/merchant")
    public String merchant_withdrawal() {
        return prefix + "/merchant_withdrawal";
    }

    /**
     * 查询商户提现记录列表
     */
    @RequiresPermissions("merchant:withdrawal:list")
    @PostMapping("/merchant/list")
    @ResponseBody
    public TableDataInfo merchant_list(AlipayWithdrawEntity alipayWithdrawEntity) {
        startPage();
        alipayWithdrawEntity.setWithdrawType("1");
        List<AlipayWithdrawEntity> list = alipayWithdrawEntityService
                .selectAlipayWithdrawEntityList(alipayWithdrawEntity);
        return getDataTable(list);
    }


    /**
     * 导出码商提现记录列表
     */
    @RequiresPermissions("qr:withdrawal:export")
    @Log(title = "码商代付订单", businessType = BusinessType.EXPORT)
    @PostMapping("/qr/export")
    @ResponseBody
    public AjaxResult qr_export(AlipayWithdrawEntity alipayWithdrawEntity) {
    	alipayWithdrawEntity.setWithdrawType("2");
        List<AlipayWithdrawEntity> list = alipayWithdrawEntityService.selectAlipayWithdrawEntityList(alipayWithdrawEntity);
        ExcelUtil<AlipayWithdrawEntity> util = new ExcelUtil<AlipayWithdrawEntity>(AlipayWithdrawEntity.class);
        return util.exportExcel(list, "withdrawal");
    }

    /**
     * 导出商户提现记录列表
     */
    @RequiresPermissions("merchant:withdrawal:export")
    @Log(title = "商户代付订单", businessType = BusinessType.EXPORT)
    @PostMapping("/merchant/export")
    @ResponseBody
    public AjaxResult export(AlipayWithdrawEntity alipayWithdrawEntity) {
    	alipayWithdrawEntity.setWithdrawType("1");
        List<AlipayWithdrawEntity> list = alipayWithdrawEntityService.selectAlipayWithdrawEntityList(alipayWithdrawEntity);
        ExcelUtil<AlipayWithdrawEntity> util = new ExcelUtil<AlipayWithdrawEntity>(AlipayWithdrawEntity.class);
        return util.exportExcel(list, "withdrawal");
    }
}
