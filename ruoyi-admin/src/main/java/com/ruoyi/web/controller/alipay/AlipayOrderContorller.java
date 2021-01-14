package com.ruoyi.web.controller.alipay;


import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.ruoyi.alipay.domain.AlipayDealOrderEntity;
import com.ruoyi.alipay.service.IAlipayDealOrderEntityService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.DictionaryUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>财务方面对订单的操作</p>
 */
@Controller
@RequestMapping("/alipay/order")
public class AlipayOrderContorller extends BaseController {
    private String prefix = "alipay/order";
    @Autowired
    private DictionaryUtils dictionaryUtils;
    @Autowired
    private IAlipayDealOrderEntityService alipayDealOrderEntityService;

    @GetMapping()
    public String orderDeal() {
        return prefix + "/order";
    }

    /**
     * 查询交易订单列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AlipayDealOrderEntity alipayDealOrderEntity) {
        startPage();
        List<AlipayDealOrderEntity> list = alipayDealOrderEntityService
                .selectAlipayOrderList(alipayDealOrderEntity);
        return getDataTable(list);
    }

    /**
     * 导出交易订单列表
     */
    @Log(title = "交易订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AlipayDealOrderEntity alipayDealOrderEntity) {
        List<AlipayDealOrderEntity> list = alipayDealOrderEntityService
                .selectAlipayOrderList(alipayDealOrderEntity);
        ExcelUtil<AlipayDealOrderEntity> util = new ExcelUtil<AlipayDealOrderEntity>(AlipayDealOrderEntity.class);
        return util.exportExcel(list, "order");
    }

    /**
     * <p>码商交易订单状态确认</p>
     */
    @PostMapping("/updataOrder")
    @ResponseBody
    @Log(title = "交易订单", businessType = BusinessType.UPDATE)
    public AjaxResult enterOrder(Long id, String orderStatus) {
        AlipayDealOrderEntity order = alipayDealOrderEntityService.selectAlipayDealOrderEntityById(id);
        /**
         * <p>当前订单为成功或者失败的时候禁止修改状态</p>
         */
        if (StrUtil.isBlank(orderStatus)) {
            return AjaxResult.error("订单状态出错");
        }
        String status = order.getOrderStatus();
        SysUser currentUser = ShiroUtils.getSysUser();
        Long userId = currentUser.getUserId();
        @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符") String userName = currentUser.getUserName();
        Map<String, Object> mapParam = Collections.synchronizedMap(Maps.newHashMap());
        if ("2".equals(status) || "4".equals(status)) {
            return AjaxResult.error("当前订单状态不允许修改");
        }
        mapParam.put("orderId", order.getOrderId());
        mapParam.put("userName", userName);
        if ("SU".equals(orderStatus)) {
            mapParam.put("orderStatus", "2");
        } else if ("ER".equals(orderStatus)) {
            mapParam.put("orderStatus", "4");
        }
        String ipPort = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_IP_URL_KEY, StaticConstants.ALIPAY_IP_URL_VALUE);
        String urlPath = dictionaryUtils.getApiUrlPath(StaticConstants.ALIPAY_SERVICE_API_KEY, StaticConstants.ALIPAY_SERVICE_API_VALUE_4);
        AjaxResult post = post(ipPort + urlPath, mapParam);
        return post;
    }
}
