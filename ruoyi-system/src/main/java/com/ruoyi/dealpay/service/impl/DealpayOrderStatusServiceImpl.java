package com.ruoyi.dealpay.service.impl;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.dealpay.domain.DealpayOrderStatusEntity;
import com.ruoyi.dealpay.mapper.DealpayOrderStatusMapper;
import com.ruoyi.dealpay.service.IDealpayOrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 双方确认订单状态Service业务层处理
 *
 * @author kiwi
 * @date 2020-04-03
 */
@Service
public class DealpayOrderStatusServiceImpl implements IDealpayOrderStatusService {
    @Autowired
    private DealpayOrderStatusMapper dealpayOrderStatusMapper;

    /**
     * 查询双方确认订单状态
     * 
     * @param id 双方确认订单状态ID
     * @return 双方确认订单状态
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public DealpayOrderStatusEntity selectDealpayOrderStatusById(Long id)
    {
        return dealpayOrderStatusMapper.selectDealpayOrderStatusById(id);
    }

    /**
     * 查询双方确认订单状态列表
     * 
     * @param dealpayOrderStatus 双方确认订单状态
     * @return 双方确认订单状态
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public List<DealpayOrderStatusEntity> selectDealpayOrderStatusList(DealpayOrderStatusEntity dealpayOrderStatus)
    {
        return dealpayOrderStatusMapper.selectDealpayOrderStatusList(dealpayOrderStatus);
    }

    /**
     * 新增双方确认订单状态
     * 
     * @param dealpayOrderStatus 双方确认订单状态
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int insertDealpayOrderStatus(DealpayOrderStatusEntity dealpayOrderStatus)
    {
        dealpayOrderStatus.setCreateTime(DateUtils.getNowDate());
        return dealpayOrderStatusMapper.insertDealpayOrderStatus(dealpayOrderStatus);
    }

    /**
     * 修改双方确认订单状态
     * 
     * @param dealpayOrderStatus 双方确认订单状态
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int updateDealpayOrderStatus(DealpayOrderStatusEntity dealpayOrderStatus)
    {
        return dealpayOrderStatusMapper.updateDealpayOrderStatus(dealpayOrderStatus);
    }

    /**
     * 删除双方确认订单状态对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int deleteDealpayOrderStatusByIds(String ids)
    {
        return dealpayOrderStatusMapper.deleteDealpayOrderStatusByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除双方确认订单状态信息
     * 
     * @param id 双方确认订单状态ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int deleteDealpayOrderStatusById(Long id)
    {
        return dealpayOrderStatusMapper.deleteDealpayOrderStatusById(id);
    }
}
