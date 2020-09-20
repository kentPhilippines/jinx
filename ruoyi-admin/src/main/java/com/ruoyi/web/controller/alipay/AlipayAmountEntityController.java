package com.ruoyi.web.controller.alipay;

import com.google.common.collect.Maps;
import com.ruoyi.alipay.domain.AlipayAmountEntity;
import com.ruoyi.alipay.domain.AlipayProductEntity;
import com.ruoyi.alipay.domain.AlipayUserFundEntity;
import com.ruoyi.alipay.service.IAlipayAmountEntityService;
import com.ruoyi.alipay.service.IAlipayProductService;
import com.ruoyi.alipay.service.IAlipayUserFundEntityService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.DictionaryUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 加减款记录表Controller
 *
 * @author kiwi
 * @date 2020-03-24
 */
@Controller
@RequestMapping("/alipay/deduct")
public class AlipayAmountEntityController extends BaseController {
    private String prefix = "alipay/deduct";
    private static final String AMOUNT_TYPE_APP = "3";
    private String prefixAddition = "alipay/amount";
    @Autowired
    private IAlipayAmountEntityService alipayAmountEntityService;
    @Autowired
    private IAlipayProductService alipayProductService;
    @Autowired
    private IAlipayUserFundEntityService alipayUserFundEntityService;

    @Autowired
    private DictionaryUtils dictionaryUtils;

    @GetMapping()
    public String deduct() {
        return prefix + "/deduct";
    }

    @GetMapping("/additional")
    public String additional() {
        return prefixAddition + "/amount";
    }

    @PostMapping("/additional")
    @ResponseBody
    public TableDataInfo additionaList(AlipayAmountEntity alipayAmountEntity) {
        alipayAmountEntity.setAmountType(AMOUNT_TYPE_APP);
        startPage();
        List<AlipayAmountEntity> list = alipayAmountEntityService.selectAlipayAmountEntityList(alipayAmountEntity);
        return getDataTable(list);
    }

    @GetMapping("/additionaEdit/{id}")
    public String additionaEdit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayAmountEntity alipayAmountEntity = alipayAmountEntityService.selectAlipayAmountEntityById(id);
        mmap.put("alipayAmountEntity", alipayAmountEntity);
        AlipayProductEntity alipayProductEntity = new AlipayProductEntity();
        alipayProductEntity.setStatus(1);
        List<AlipayUserFundEntity> channelList = alipayUserFundEntityService.findUserFundRate();
        //查询产品类型下拉菜单
        List<AlipayProductEntity> list = alipayProductService.selectAlipayProductList(alipayProductEntity);
        mmap.put("productList", list);
        mmap.put("channelList", channelList);
        return prefix + "/edit";
    }

    @Log(title = "运营人员审核商户补单", businessType = BusinessType.EXPORT)
    @PostMapping("/additionaEdit")
    @ResponseBody
    public AjaxResult additionaEditEnter(AlipayAmountEntity amountEntity) {
        SysUser currentUser = ShiroUtils.getSysUser();
        amountEntity.setApproval(currentUser.getLoginName());
        return toAjax(alipayAmountEntityService.additionaEditEnter(amountEntity));

    }

    /**
     * 查询手动加扣款记录列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayAmountEntity alipayAmountEntity) {
        startPage();
        List<AlipayAmountEntity> list = alipayAmountEntityService.selectAlipayAmountEntityList(alipayAmountEntity);
        return getDataTable(list);
    }

    /**
     * 导出加减款记录列表
     */
    @Log(title = "加减款记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayAmountEntity alipayAmountEntity) {
        List<AlipayAmountEntity> list = alipayAmountEntityService.selectAlipayAmountEntityList(alipayAmountEntity);
        ExcelUtil<AlipayAmountEntity> util = new ExcelUtil<AlipayAmountEntity>(AlipayAmountEntity.class);
        return util.exportExcel(list, "deduct");
    }

    /**
     * 显示加减款详情
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AlipayAmountEntity alipayAmountEntity = alipayAmountEntityService.selectAlipayAmountEntityById(id);
        mmap.put("alipayAmountEntity", alipayAmountEntity);
        return prefix + "/edit";
    }

    /*处理加减款的controller逻辑处理*/

    /**
     * 财务审核加减款记录
     */
    @Log(title = "加减款记录", businessType = BusinessType.UPDATE)
    @PostMapping("/alipay/approval")
    @ResponseBody
    public AjaxResult apporval(AlipayAmountEntity alipayAmountEntity) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        //获取alipay处理接口URL
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_SERVICE_API_KEY, StaticConstants.ALIPAY_SERVICE_API_VALUE_3);
        Map<String, Object> mapParam = Collections.synchronizedMap(Maps.newHashMap());
        mapParam.put("id", alipayAmountEntity.getId());
        mapParam.put("userId", alipayAmountEntity.getUserId());
        mapParam.put("amount", alipayAmountEntity.getAmount());
        mapParam.put("orderStatus", alipayAmountEntity.getOrderStatus());//通过
        mapParam.put("orderId", alipayAmountEntity.getOrderId());//订单号
        mapParam.put("approval", currentUser.getLoginName());//审核人
        mapParam.put("comment", alipayAmountEntity.getComment());//审核人
        return HttpUtils.adminRequest2Gateway(mapParam, ipPort + urlPath);
    }

    /**
     * 财务审核加减款记录
     */
    @Log(title = "加减款记录", businessType = BusinessType.UPDATE)
    @PostMapping("/alipay/reject")
    @ResponseBody
    public AjaxResult reject(AlipayAmountEntity alipayAmountEntity) {
        // 获取当前的用户
        SysUser currentUser = ShiroUtils.getSysUser();
        //获取alipay处理接口URL
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_SERVICE_API_KEY, StaticConstants.ALIPAY_SERVICE_API_VALUE_3);
        Map<String, Object> mapParam = Collections.synchronizedMap(Maps.newHashMap());
        mapParam.put("id", alipayAmountEntity.getId());
        mapParam.put("userId", alipayAmountEntity.getUserId());
        mapParam.put("amount", alipayAmountEntity.getAmount());
        mapParam.put("orderStatus", alipayAmountEntity.getOrderStatus());//拒绝
        mapParam.put("orderId", alipayAmountEntity.getOrderId());//订单号
        mapParam.put("approval", currentUser.getLoginName());//审核人
        mapParam.put("comment", alipayAmountEntity.getComment());
        return HttpUtils.adminRequest2Gateway(mapParam, ipPort + urlPath);
    }

}
