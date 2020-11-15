package com.ruoyi.dealpay.service.impl;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.dealpay.domain.DealpayDealOrderEntity;
import com.ruoyi.dealpay.mapper.DealpayDealOrderMapper;
import com.ruoyi.dealpay.service.IDealpayDealOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 交易订单Service业务层处理
 *
 * @author k
 * @date 2020-04-03
 */
@Service
public class DealpayDealOrderServiceImpl implements IDealpayDealOrderService {
    @Resource
    private DealpayDealOrderMapper dealpayDealOrderMapper;

    /**
     * 查询交易订单
     *
     * @param id 交易订单ID
     * @return 交易订单
     */
    @Override
    @DataSource(DataSourceType.DEALPAY_SLAVE)
    public DealpayDealOrderEntity selectDealpayDealOrderById(Long id) {
        return dealpayDealOrderMapper.selectDealpayDealOrderById(id);
    }

    /**
     * 查询交易订单列表
     *
     * @param dealpayDealOrder 交易订单
     * @return 交易订单
     */
    @Override
    @DataSource(DataSourceType.DEALPAY_SLAVE)
    public List<DealpayDealOrderEntity> selectDealpayDealOrderList(DealpayDealOrderEntity dealpayDealOrder) {
        return dealpayDealOrderMapper.selectDealpayDealOrderList(dealpayDealOrder);
    }

    /**
     * 新增交易订单
     *
     * @param dealpayDealOrder 交易订单
     * @return 结果
     */
    @Override
    @DataSource(DataSourceType.DEALPAY_SLAVE)
    public int insertDealpayDealOrder(DealpayDealOrderEntity dealpayDealOrder) {
        dealpayDealOrder.setCreateTime(DateUtils.getNowDate());
        return dealpayDealOrderMapper.insertDealpayDealOrder(dealpayDealOrder);
    }

    /**
     * 修改交易订单
     *
     * @param dealpayDealOrder 交易订单
     * @return 结果
     */
    @Override
    @DataSource(DataSourceType.DEALPAY_SLAVE)
    public int updateDealpayDealOrder(DealpayDealOrderEntity dealpayDealOrder) {
        return dealpayDealOrderMapper.updateDealpayDealOrder(dealpayDealOrder);
    }

    /**
     * 删除交易订单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @DataSource(DataSourceType.DEALPAY_SLAVE)
    public int deleteDealpayDealOrderByIds(String ids) {
        return dealpayDealOrderMapper.deleteDealpayDealOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除交易订单信息
     *
     * @param id 交易订单ID
     * @return 结果
     */
    @Override
    @DataSource(DataSourceType.DEALPAY_SLAVE)
    public int deleteDealpayDealOrderById(Long id) {
        return dealpayDealOrderMapper.deleteDealpayDealOrderById(id);
    }


    /**
     * 订单核对方法
     */
    public void checkOrder() {


    }
}
