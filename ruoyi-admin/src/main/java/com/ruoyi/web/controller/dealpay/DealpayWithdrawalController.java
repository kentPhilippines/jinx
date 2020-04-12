package com.ruoyi.web.controller.dealpay;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.dealpay.domain.DealpayDealOrderEntity;
import com.ruoyi.dealpay.domain.DealpayRechargeEntity;
import com.ruoyi.framework.util.DictionaryUtils;
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
import com.ruoyi.dealpay.domain.DealpayWithdrawalEntity;
import com.ruoyi.dealpay.service.IDealpayWithdrawalEntityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 卡商出款记录表Controller
 *
 * @author kiwi
 * @date 2020-04-03
 */
@Controller
@RequestMapping("/dealpay/cardWithdrawal")
public class DealpayWithdrawalController extends BaseController {
    private String prefix = "dealpay/cardWithdrawal";
    private String finance_prefix = "dealpay/finance";

    @Autowired
    private IDealpayWithdrawalEntityService dealpayWithdrawalEntityService;

    @Autowired
    private DictionaryUtils dictionaryUtils;

    @RequiresPermissions("dealpay:cardWithdrawal:view")
    @GetMapping()
    public String cardWithdrawal() {
        return prefix + "/cardWithdrawal";
    }

    /**
     * 查询卡商出款记录表列表
     */
    @RequiresPermissions("dealpay:cardWithdrawal:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DealpayWithdrawalEntity dealpayWithdrawalEntity) {
        startPage();
        List<DealpayWithdrawalEntity> list = dealpayWithdrawalEntityService.selectDealpayWithdrawalEntityList(dealpayWithdrawalEntity);
        return getDataTable(list);
    }

    /**
     * 导出卡商出款记录表列表
     */
    @RequiresPermissions("dealpay:cardWithdrawal:export")
    @Log(title = "卡商出款记录表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DealpayWithdrawalEntity dealpayWithdrawalEntity) {
        List<DealpayWithdrawalEntity> list = dealpayWithdrawalEntityService.selectDealpayWithdrawalEntityList(dealpayWithdrawalEntity);
        ExcelUtil<DealpayWithdrawalEntity> util = new ExcelUtil<DealpayWithdrawalEntity>(DealpayWithdrawalEntity.class);
        return util.exportExcel(list, "cardWithdrawal");
    }

    /**
     * 新增卡商出款记录表
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存卡商出款记录表
     */
    @RequiresPermissions("dealpay:cardWithdrawal:add")
    @Log(title = "卡商出款记录表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DealpayWithdrawalEntity dealpayWithdrawalEntity) {
        return toAjax(dealpayWithdrawalEntityService.insertDealpayWithdrawalEntity(dealpayWithdrawalEntity));
    }

    /**
     * 修改卡商出款记录表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        DealpayWithdrawalEntity dealpayWithdrawalEntity = dealpayWithdrawalEntityService.selectDealpayWithdrawalEntityById(id);
        mmap.put("dealpayWithdrawalEntity", dealpayWithdrawalEntity);
        return prefix + "/edit";
    }

    /**
     * 修改保存卡商出款记录表
     */
    @RequiresPermissions("dealpay:cardWithdrawal:edit")
    @Log(title = "卡商出款记录表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DealpayWithdrawalEntity dealpayWithdrawalEntity) {
        return toAjax(dealpayWithdrawalEntityService.updateDealpayWithdrawalEntity(dealpayWithdrawalEntity));
    }

    /**
     * 删除卡商出款记录表
     */
    @RequiresPermissions("dealpay:cardWithdrawal:remove")
    @Log(title = "卡商出款记录表", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(dealpayWithdrawalEntityService.deleteDealpayWithdrawalEntityByIds(ids));
    }


    /*财务处理逻辑*/

    @RequiresPermissions("finance:withdrawal:view")
    @GetMapping("/payfor/view")
    public String payforView() {
        return finance_prefix + "/payfor";
    }

    /**
     * <p>代付管理</p>
     */
    @RequiresPermissions("finance:payfor:manage")
    @PostMapping("/finance/manage/payfor")
    @Log(title = "代付管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    public TableDataInfo payfor(DealpayWithdrawalEntity dealpayWithdrawalEntity) {
        startPage();
        dealpayWithdrawalEntity.setWithdrawType(2);
        List<DealpayWithdrawalEntity> list = dealpayWithdrawalEntityService.selectDealpayWithdrawalEntityList(dealpayWithdrawalEntity);
        return getDataTable(list);
    }

    /**
     * 查看详情
     */
    @GetMapping("/finance/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap) {
        DealpayWithdrawalEntity dealpayWithdrawalEntity = dealpayWithdrawalEntityService.selectDealpayWithdrawalEntityById(id);
        mmap.put("dealpayWithdrawalEntity", dealpayWithdrawalEntity);
        return finance_prefix + "/wDetail";
    }

    /**
     * <p>代付管理</p>
     */
    @RequiresPermissions("finance:withdrawal:updateStatus")
    @PostMapping("/finance/confirmStatus")
    @Log(title = "代付管理", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult confirmDeposit(DealpayWithdrawalEntity dealpayWithdrawalEntity) {
        //获取dealpay处理接口URL
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.DealPAY_IP_URL_KEY, StaticConstants.DealPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.DealPAY_SERVICE_API_KEY, StaticConstants.DealPAY_SERVICE_API_VALUE_3);
        Map<String, Object> mapParam = Collections.synchronizedMap(Maps.newHashMap());
        String orderStatus = dealpayWithdrawalEntity.getOrderStatus();
        Long id = dealpayWithdrawalEntity.getId();
        mapParam.put("id", id);
        mapParam.put("orderStatus", orderStatus);
        return HttpUtils.adminRequest2Gateway(mapParam, ipPort + urlPath);
    }


}
