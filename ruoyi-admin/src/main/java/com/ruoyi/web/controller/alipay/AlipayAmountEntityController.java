package com.ruoyi.web.controller.alipay;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.GenerateOrderNo;
import com.ruoyi.common.utils.MapDataUtil;
import com.ruoyi.common.utils.RSAUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.framework.util.DictionaryUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
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
import com.ruoyi.alipay.domain.AlipayAmountEntity;
import com.ruoyi.alipay.service.IAlipayAmountEntityService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

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

    @Autowired
    private IAlipayAmountEntityService alipayAmountEntityService;
    @Autowired
    private DictionaryUtils dictionaryUtils;

    @RequiresPermissions("alipay:deduct:view")
    @GetMapping()
    public String deduct() {
        return prefix + "/deduct";
    }

    /**
     * 查询手动加扣款记录列表
     */
    @RequiresPermissions("alipay:deduct:list")
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
    @RequiresPermissions("alipay:deduct:export")
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
    @RequiresPermissions("alipay:finance:approval")
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
    @RequiresPermissions("alipay:finance:reject")
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
