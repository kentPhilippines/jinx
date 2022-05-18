package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayRunOrderEntity;
import com.ruoyi.alipay.domain.AlipayWithdrawEntity;
import com.ruoyi.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlipayWithdrawAuditRuleService {

    @Autowired
    private IAlipayRunOrderEntityService alipayRunOrderEntityService;

    /**
     * 新增出款规则审批
     */
    public void checkRuleBeforeAudit(AlipayWithdrawEntity alipayWithdrawEntity) {
        switch (alipayWithdrawEntity.getOrderStatus()) {
            case "3"://取消按钮
                //新增逻辑 判断金流里是否有这笔失败的 如果有就返回
                AlipayRunOrderEntity alipayRunOrderEntity = new AlipayRunOrderEntity();
                alipayRunOrderEntity.setAssociatedId(alipayWithdrawEntity.getOrderId());
                List<AlipayRunOrderEntity> list = alipayRunOrderEntityService.selectAlipayRunOrderEntityList(alipayRunOrderEntity);
                list.stream().filter(entity -> entity.getRunOrderType() == 8 || entity.getRunOrderType() == 22).findAny().ifPresent(runOrderEntity -> {
                    throw new BusinessException("此订单金流里已有失败数据");
                });
                break;
            case "2"://
                // code block
                checkRunOrderIsReduce(alipayWithdrawEntity);
                break;
            case "100"://
                checkRunOrderIsReduce(alipayWithdrawEntity);
                break;
            default:
                // code block
        }


    }

    private void checkRunOrderIsReduce(AlipayWithdrawEntity alipayWithdrawEntity) {
        AlipayRunOrderEntity alipayRunOrderEntity = new AlipayRunOrderEntity();
        alipayRunOrderEntity.setAssociatedId(alipayWithdrawEntity.getOrderId());
        List<AlipayRunOrderEntity> list = alipayRunOrderEntityService.selectAlipayRunOrderEntityList(alipayRunOrderEntity);
        list.stream().filter(entity -> entity.getRunOrderType() == 10).findAny().orElseThrow(() -> new BusinessException("金流里没有代付扣款数据，请核实。"));
    }
}
