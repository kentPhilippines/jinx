package com.ruoyi.dealpay.service.impl;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.dealpay.domain.DealpayRunOrderEntity;
import com.ruoyi.dealpay.mapper.DealpayRunOrderMapper;
import com.ruoyi.dealpay.service.IDealpayRunOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流水订单记录Service业务层处理
 *
 * @author kiwi
 * @date 2020-04-03
 */
@Service
public class DealpayRunOrderServiceImpl implements IDealpayRunOrderService {
    @Autowired
    private DealpayRunOrderMapper dealpayRunOrderMapper;

    /**
     * 查询流水订单记录
     *
     * @param id 流水订单记录ID
     * @return 流水订单记录
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public DealpayRunOrderEntity selectDealpayRunOrderById(Long id) {
        return dealpayRunOrderMapper.selectDealpayRunOrderById(id);
    }

    /**
     * 查询流水订单记录列表
     *
     * @param dealpayRunOrder 流水订单记录
     * @return 流水订单记录
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public List<DealpayRunOrderEntity> selectDealpayRunOrderList(DealpayRunOrderEntity dealpayRunOrder) {
        return dealpayRunOrderMapper.selectDealpayRunOrderList(dealpayRunOrder);
    }

    /**
     * 新增流水订单记录
     *
     * @param dealpayRunOrder 流水订单记录
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int insertDealpayRunOrder(DealpayRunOrderEntity dealpayRunOrder) {
        dealpayRunOrder.setCreateTime(DateUtils.getNowDate());
        return dealpayRunOrderMapper.insertDealpayRunOrder(dealpayRunOrder);
    }

    /**
     * 修改流水订单记录
     *
     * @param dealpayRunOrder 流水订单记录
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int updateDealpayRunOrder(DealpayRunOrderEntity dealpayRunOrder) {
        return dealpayRunOrderMapper.updateDealpayRunOrder(dealpayRunOrder);
    }

    /**
     * 删除流水订单记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int deleteDealpayRunOrderByIds(String ids) {
        return dealpayRunOrderMapper.deleteDealpayRunOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除流水订单记录信息
     *
     * @param id 流水订单记录ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int deleteDealpayRunOrderById(Long id) {
        return dealpayRunOrderMapper.deleteDealpayRunOrderById(id);
    }
}
