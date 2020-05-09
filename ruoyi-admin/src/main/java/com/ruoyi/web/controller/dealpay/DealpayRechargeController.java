package com.ruoyi.web.controller.dealpay;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.dealpay.domain.DealpayDealOrderEntity;
import com.ruoyi.framework.util.DictionaryUtils;
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
import com.ruoyi.dealpay.domain.DealpayRechargeEntity;
import com.ruoyi.dealpay.service.IDealpayRechargeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 充值记录Controller
 *
 * @author k
 * @date 2020-04-03
 */
@Controller
@RequestMapping("/dealpay/recharge")
public class DealpayRechargeController extends BaseController {
    private String prefix = "dealpay/recharge";
    private String finance_prefix = "dealpay/finance";

    @Autowired
    private IDealpayRechargeService dealpayRechargeService;
    @Autowired
    private DictionaryUtils dictionaryUtils;

    @GetMapping()
    public String recharge() {
        return prefix + "/recharge";
    }

    /**
     * 查询充值记录列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DealpayRechargeEntity dealpayRecharge) {
        startPage();
        List<DealpayRechargeEntity> list = dealpayRechargeService.selectDealpayRechargeList(dealpayRecharge);
        return getDataTable(list);
    }

    /**
     * 导出充值记录列表
     */
    @Log(title = "充值记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DealpayRechargeEntity dealpayRecharge) {
        List<DealpayRechargeEntity> list = dealpayRechargeService.selectDealpayRechargeList(dealpayRecharge);
        ExcelUtil<DealpayRechargeEntity> util = new ExcelUtil<DealpayRechargeEntity>(DealpayRechargeEntity.class);
        return util.exportExcel(list, "recharge");
    }

    /**
     * 新增充值记录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存充值记录
     */
    @Log(title = "充值记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DealpayRechargeEntity dealpayRecharge) {
        return toAjax(dealpayRechargeService.insertDealpayRecharge(dealpayRecharge));
    }

    /**
     * 修改充值记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DealpayRechargeEntity dealpayRecharge = dealpayRechargeService.selectDealpayRechargeById(id);
        mmap.put("dealpayRecharge", dealpayRecharge);
        return prefix + "/edit";
    }

    /**
     * 修改保存充值记录
     */
    @Log(title = "充值记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DealpayRechargeEntity dealpayRecharge) {
        return toAjax(dealpayRechargeService.updateDealpayRecharge(dealpayRecharge));
    }

    /**
     * 删除充值记录
     */
    @Log(title = "充值记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dealpayRechargeService.deleteDealpayRechargeByIds(ids));
    }


    /*财务管理-充值管理逻辑处理*/

    @GetMapping("/deposit/view")
    public String depositView() {
        return finance_prefix + "/deposit";
    }

    /**
     * <p>充值管理 查询 </p>
     */
    @PostMapping("/finance/manage/deposit")
    @Log(title = "代付管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    public TableDataInfo deposit(DealpayRechargeEntity dealpayRechargeEntity) {
        startPage();
        dealpayRechargeEntity.setRechargeType(2);
        List<DealpayRechargeEntity> list = dealpayRechargeService.selectDealpayRechargeList(dealpayRechargeEntity);
        return getDataTable(list);
    }

    /**
     * 修改充值记录
     */
    @GetMapping("/finance/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap) {
        DealpayRechargeEntity dealpayRecharge = dealpayRechargeService.selectDealpayRechargeById(id);
        mmap.put("dealpayRecharge", dealpayRecharge);
        return finance_prefix + "/rDetail";
    }

    /**
     * <p>充值管理 修改订单状态</p>
     */
    @PostMapping("/finance/confirmDeposit")
    @Log(title = "代付管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult confirmDeposit(DealpayRechargeEntity dealpayRechargeEntity) {
        //获取dealpay处理接口URL
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.DealPAY_IP_URL_KEY, StaticConstants.DealPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.DealPAY_SERVICE_API_KEY, StaticConstants.DealPAY_SERVICE_API_VALUE_4);
        Map<String, Object> mapParam = Collections.synchronizedMap(Maps.newHashMap());
        String orderStatus = dealpayRechargeEntity.getOrderStatus();
        Long id = dealpayRechargeEntity.getId();
        mapParam.put("id", id);
        mapParam.put("orderStatus", orderStatus);
        return HttpUtils.adminRequest2Gateway(mapParam, ipPort + urlPath);
    }
}
