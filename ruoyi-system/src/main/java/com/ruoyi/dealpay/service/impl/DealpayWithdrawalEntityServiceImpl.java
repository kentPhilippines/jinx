package com.ruoyi.dealpay.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dealpay.mapper.DealpayWithdrawalEntityMapper;
import com.ruoyi.dealpay.domain.DealpayWithdrawalEntity;
import com.ruoyi.dealpay.service.IDealpayWithdrawalEntityService;
import com.ruoyi.common.core.text.Convert;

/**
 * 卡商出款记录表Service业务层处理
 *
 * @author kiwi
 * @date 2020-04-03
 */
@Service
public class DealpayWithdrawalEntityServiceImpl implements IDealpayWithdrawalEntityService {
    @Autowired
    private DealpayWithdrawalEntityMapper dealpayWithdrawalEntityMapper;

    /**
     * 查询卡商出款记录表
     *
     * @param id 卡商出款记录表ID
     * @return 卡商出款记录表
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public DealpayWithdrawalEntity selectDealpayWithdrawalEntityById(Long id) {
        return dealpayWithdrawalEntityMapper.selectDealpayWithdrawalEntityById(id);
    }

    /**
     * 查询卡商出款记录表列表
     *
     * @param dealpayWithdrawalEntity 卡商出款记录表
     * @return 卡商出款记录表
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public List<DealpayWithdrawalEntity> selectDealpayWithdrawalEntityList(DealpayWithdrawalEntity dealpayWithdrawalEntity) {
        return dealpayWithdrawalEntityMapper.selectDealpayWithdrawalEntityList(dealpayWithdrawalEntity);
    }

    /**
     * 新增卡商出款记录表
     *
     * @param dealpayWithdrawalEntity 卡商出款记录表
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int insertDealpayWithdrawalEntity(DealpayWithdrawalEntity dealpayWithdrawalEntity) {
        dealpayWithdrawalEntity.setCreateTime(DateUtils.getNowDate());
        return dealpayWithdrawalEntityMapper.insertDealpayWithdrawalEntity(dealpayWithdrawalEntity);
    }

    /**
     * 修改卡商出款记录表
     *
     * @param dealpayWithdrawalEntity 卡商出款记录表
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int updateDealpayWithdrawalEntity(DealpayWithdrawalEntity dealpayWithdrawalEntity) {
        return dealpayWithdrawalEntityMapper.updateDealpayWithdrawalEntity(dealpayWithdrawalEntity);
    }

    /**
     * 删除卡商出款记录表对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int deleteDealpayWithdrawalEntityByIds(String ids) {
        return dealpayWithdrawalEntityMapper.deleteDealpayWithdrawalEntityByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除卡商出款记录表信息
     *
     * @param id 卡商出款记录表ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int deleteDealpayWithdrawalEntityById(Long id) {
        return dealpayWithdrawalEntityMapper.deleteDealpayWithdrawalEntityById(id);
    }
}
