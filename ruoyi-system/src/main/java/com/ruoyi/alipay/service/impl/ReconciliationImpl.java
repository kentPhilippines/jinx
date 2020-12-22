package com.ruoyi.alipay.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.alipay.domain.AlipayDealOrderEntity;
import com.ruoyi.alipay.domain.AlipayRunOrderEntity;
import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.domain.AlipayWithdrawEntity;
import com.ruoyi.alipay.service.IAlipayDealOrderAppService;
import com.ruoyi.alipay.service.IAlipayRunOrderEntityService;
import com.ruoyi.alipay.service.IAlipayUserInfoService;
import com.ruoyi.alipay.service.Reconciliation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ReconciliationImpl implements Reconciliation {

    @Autowired
    private IAlipayDealOrderAppService alipayDealOrderAppService;
    @Autowired
    private IAlipayRunOrderEntityService alipayRunOrderEntityService;
    @Autowired
    private IAlipayUserInfoService alipayUserInfoService;

    @Override
    public Map<String, String> orderReconciliation(String orderId, AlipayDealOrderEntity orderEntity) {
        Map<String, String> map = new HashMap<>();
        StringBuffer str = new StringBuffer();
        /**
         * 交易订单核对
         * 交易订单成功   1，商户交易加款 20  2，商户交易手续费扣款 21 3，商户交易代理商分润 13
         */
        Integer addOrderAmount = 1; //商户交易加款
        Integer addOrderFeeAmount = 21; //商户交易手续费扣款
        Integer addOrderFeeAgentAmount = 13; //商户交易代理商分润
        List<AlipayRunOrderEntity> runList = alipayRunOrderEntityService.findAssocidOrder(orderId);
        ConcurrentHashMap<Integer, AlipayRunOrderEntity> userCollect = runList.stream().collect(Collectors.toConcurrentMap(AlipayRunOrderEntity::getRunOrderType, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        AlipayRunOrderEntity runOrderEntity20 = userCollect.get(addOrderAmount);
        AlipayRunOrderEntity runOrderEntity21 = userCollect.get(addOrderFeeAmount);
        AlipayRunOrderEntity runOrderEntity13 = userCollect.get(addOrderFeeAgentAmount);
        if (ObjectUtil.isNotNull(runOrderEntity13)) {
            AlipayUserInfo merchantInfoByUserId = alipayUserInfoService.findMerchantInfoByUserId(orderEntity.getOrderAccount());
            if (StrUtil.isNotEmpty(merchantInfoByUserId.getAgent())) {
                str.append("商户交易加款流水不存在");
            }
        } else if (ObjectUtil.isNotNull(runOrderEntity21)) {
            str.append("商户交易手续费扣款流水不存在");
        } else if (ObjectUtil.isNotNull(runOrderEntity20)) {
            str.append("商户交易手续费扣款流水不存在");
        }
        if (StrUtil.isNotEmpty(str.toString())) {
            map.put(ERROR_RECONCILIATION, "【当前订单：" + orderEntity.getOrderId() + " 异常：" + str.toString() + "】");
            return map;
        } else {
            map.put(SUCCESS_RECONCILIATION, "【当前订单：" + orderEntity.getOrderId() + ",流水正常】");
            return map;
        }
    }

    /**
     * 代付订单核对
     * 1，当前订单为成功 ------>  1，代付扣款 10     2，代付手续费 9   3，代付代理分润 26
     * 2，当前订单为失败 ------>  1，代付扣款 10     2，代付手续费 9   3，代付代理分润 26 4，商户代付失败解冻 8 5，丧户代付失败手续费解冻 22
     */
    @Override
    public Map<String, String> witReconciliation(String orderId, AlipayWithdrawEntity wit) {
        Map<String, String> map = new HashMap<>();
        StringBuffer str = new StringBuffer();
        Integer witSu = 10;
        Integer witSuFee = 9;
        Integer witSuPro = 26;
        Integer witEr = 8;
        Integer witErFee = 22;
        List<AlipayRunOrderEntity> runList = alipayRunOrderEntityService.findAssocidOrder(orderId);
        ConcurrentHashMap<Integer, AlipayRunOrderEntity> runCollect = runList.stream().collect(Collectors.toConcurrentMap(AlipayRunOrderEntity::getRunOrderType, Function.identity(), (o1, o2) -> o1, ConcurrentHashMap::new));
        if (wit.getOrderStatus().toString().equals("2")) {
            //1，当前订单为成功 ------>  1，代付扣款 10     2，代付手续费 9   3，代付代理分润 26
            if (runList.size() > 3) {
                for (AlipayRunOrderEntity run : runList) {
                    str.append("流水类型：" + run + "，流水备注：" + run.getDealDescribe());
                }
            }
            AlipayRunOrderEntity runOrderEntity10 = runCollect.get(witSu);
            AlipayRunOrderEntity runOrderEntity9 = runCollect.get(witSuFee);
            AlipayRunOrderEntity runOrderEntity26 = runCollect.get(witSuPro);
            if (ObjectUtil.isNotNull(runOrderEntity9)) {
                str.append("商户代付手续费不存在");
            } else if (ObjectUtil.isNotNull(runOrderEntity10)) {
                str.append("商户代付扣款不存在");
            } else if (ObjectUtil.isNotNull(runOrderEntity26)) {
                AlipayUserInfo merchantInfoByUserId = alipayUserInfoService.findMerchantInfoByUserId(wit.getUserId());
                if (StrUtil.isNotEmpty(merchantInfoByUserId.getAgent())) {
                    str.append("商户代付代理分润不存在");
                }
            }
            if (StrUtil.isNotEmpty(str.toString())) {
                map.put(ERROR_RECONCILIATION, "【当前订单：" + wit.getOrderId() + " 异常：" + str.toString() + "】");
                return map;
            } else {
                map.put(SUCCESS_RECONCILIATION, "【当前订单：" + wit.getOrderId() + ",流水正常】");
                return map;
            }
        } else if (wit.getOrderStatus().toString().equals("3")) {
            //2，当前订单为失败 ------> 1，代付扣款 10     2，代付手续费 9   3，代付代理分润 26
            // 4，商户代付失败解冻 8 5，丧户代付失败手续费解冻 22
            AlipayRunOrderEntity runOrderEntity10 = runCollect.get(witSu);
            AlipayRunOrderEntity runOrderEntity9 = runCollect.get(witSuFee);
            AlipayRunOrderEntity runOrderEntity26 = runCollect.get(witSuPro);
            AlipayRunOrderEntity runOrderEntity8 = runCollect.get(witEr);
            AlipayRunOrderEntity runOrderEntity22 = runCollect.get(witErFee);
            if (ObjectUtil.isNotNull(runOrderEntity9)) {
                str.append("商户代付手续费不存在");
            } else if (ObjectUtil.isNotNull(runOrderEntity10)) {
                str.append("商户代付扣款不存在");
            } else if (ObjectUtil.isNotNull(runOrderEntity26)) {
                AlipayUserInfo merchantInfoByUserId = alipayUserInfoService.findMerchantInfoByUserId(wit.getUserId());
                if (StrUtil.isNotEmpty(merchantInfoByUserId.getAgent())) {
                    str.append("商户代付代理分润不存在");
                }
            } else if (ObjectUtil.isNotNull(runOrderEntity8)) {
                str.append("商户代付失败解冻流水不存在");
            } else if (ObjectUtil.isNotNull(runOrderEntity22)) {
                str.append("商户代付失败手续费解冻流水不存在");
            }
            if (StrUtil.isNotEmpty(str.toString())) {
                map.put(ERROR_RECONCILIATION, "【当前订单：" + wit.getOrderId() + " 异常：" + str.toString() + "】");
                return map;
            } else {
                map.put(SUCCESS_RECONCILIATION, "【当前订单：" + wit.getOrderId() + ",流水正常】");
                return map;
            }
        }
        return map;
    }
}
