package com.ruoyi.dealpay.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dealpay.mapper.DealpayAmountMapper;
import com.ruoyi.dealpay.domain.DealpayAmountEntity;
import com.ruoyi.dealpay.service.IDealpayAmountService;
import com.ruoyi.common.core.text.Convert;

/**
 * 手动加扣款记录Service业务层处理
 *
 * @author ruoyi
 * @date 2020-04-03
 */
@Service
public class DealpayAmountServiceImpl implements IDealpayAmountService {
    @Autowired
    private DealpayAmountMapper dealpayAmountMapper;

    /**
     * 查询手动加扣款记录
     *
     * @param id 手动加扣款记录ID
     * @return 手动加扣款记录
     */
    @Override
    @DataSource(DataSourceType.DEALPAY_SLAVE)
    public DealpayAmountEntity selectDealpayAmountById(Long id) {
        return dealpayAmountMapper.selectDealpayAmountById(id);
    }

    /**
     * 查询手动加扣款记录列表
     *
     * @param dealpayAmount 手动加扣款记录
     * @return 手动加扣款记录
     */
    @Override
    @DataSource(DataSourceType.DEALPAY_SLAVE)
    public List<DealpayAmountEntity> selectDealpayAmountList(DealpayAmountEntity dealpayAmount) {
        return dealpayAmountMapper.selectDealpayAmountList(dealpayAmount);
    }

    /**
     * 新增手动加扣款记录
     *
     * @param dealpayAmount 手动加扣款记录
     * @return 结果
     */
    @Override
    @DataSource(DataSourceType.DEALPAY_SLAVE)
    public int insertDealpayAmount(DealpayAmountEntity dealpayAmount) {
        dealpayAmount.setCreateTime(DateUtils.getNowDate());
        return dealpayAmountMapper.insertDealpayAmount(dealpayAmount);
    }

    /**
     * 修改手动加扣款记录
     *
     * @param dealpayAmount 手动加扣款记录
     * @return 结果
     */
    @Override
    @DataSource(DataSourceType.DEALPAY_SLAVE)
    public int updateDealpayAmount(DealpayAmountEntity dealpayAmount) {
        return dealpayAmountMapper.updateDealpayAmount(dealpayAmount);
    }

    /**
     * 删除手动加扣款记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @DataSource(DataSourceType.DEALPAY_SLAVE)
    public int deleteDealpayAmountByIds(String ids) {
        return dealpayAmountMapper.deleteDealpayAmountByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除手动加扣款记录信息
     *
     * @param id 手动加扣款记录ID
     * @return 结果
     */
    @Override
    @DataSource(DataSourceType.DEALPAY_SLAVE)
    public int deleteDealpayAmountById(Long id) {
        return dealpayAmountMapper.deleteDealpayAmountById(id);
    }
}
