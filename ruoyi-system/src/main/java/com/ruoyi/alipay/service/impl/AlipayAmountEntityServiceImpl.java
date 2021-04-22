package com.ruoyi.alipay.service.impl;

import com.ruoyi.alipay.domain.AlipayAmountEntity;
import com.ruoyi.alipay.mapper.AlipayAmountEntityMapper;
import com.ruoyi.alipay.service.IAlipayAmountEntityService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.GenerateOrderNo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 手动加扣款记录Service业务层处理
 *
 * @author kent
 * @date 2020-03-24
 */
@Service
public class AlipayAmountEntityServiceImpl implements IAlipayAmountEntityService {
    @Resource
    private AlipayAmountEntityMapper alipayAmountEntityMapper;

    /**
     * 查询手动加扣款记录
     *
     * @param id 手动加扣款记录ID
     * @return 手动加扣款记录
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public AlipayAmountEntity selectAlipayAmountEntityById(Long id) {
        return alipayAmountEntityMapper.selectAlipayAmountEntityById(id);
    }

    /**
     * 查询手动加扣款记录列表
     *
     * @param alipayAmountEntity 手动加扣款记录
     * @return 手动加扣款记录
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayAmountEntity> selectAlipayAmountEntityList(AlipayAmountEntity alipayAmountEntity) {
        return alipayAmountEntityMapper.selectAlipayAmountEntityList(alipayAmountEntity);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayAmountEntity> selectTransferList(AlipayAmountEntity alipayAmountEntity) {
        return alipayAmountEntityMapper.selectTransferList(alipayAmountEntity);
    }

    /**
     * 新增手动加扣款记录
     *
     * @param alipayAmountEntity 手动加扣款记录
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int insertAlipayAmountEntity(AlipayAmountEntity alipayAmountEntity) {
        alipayAmountEntity.setCreateTime(DateUtils.getNowDate());
        alipayAmountEntity.setOrderId(GenerateOrderNo.getInstance().Generate(StaticConstants.PERFIX_REFUND));
        alipayAmountEntity.setAmountType("1");
        alipayAmountEntity.setOrderStatus("2");
        alipayAmountEntity.setActualAmount(alipayAmountEntity.getAmount());
        return alipayAmountEntityMapper.insertAlipayAmountEntity(alipayAmountEntity);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int insertAlipayAmountFreeze(AlipayAmountEntity alipayAmountEntity) {
        alipayAmountEntity.setCreateTime(DateUtils.getNowDate());
        alipayAmountEntity.setOrderId(GenerateOrderNo.getInstance().Generate(StaticConstants.PERFIX_REFUND));
        alipayAmountEntity.setAmountType("4");
        alipayAmountEntity.setOrderStatus("2");
        alipayAmountEntity.setActualAmount(alipayAmountEntity.getAmount());
        return alipayAmountEntityMapper.insertAlipayAmountEntity(alipayAmountEntity);
    }

    /**
     * 修改手动加扣款记录
     *
     * @param alipayAmountEntity 手动加扣款记录
     * @return 结果
     */
    @Override
    public int updateAlipayAmountEntity(AlipayAmountEntity alipayAmountEntity) {
        return alipayAmountEntityMapper.updateAlipayAmountEntity(alipayAmountEntity);
    }

    /**
     * 删除手动加扣款记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAlipayAmountEntityByIds(String ids) {
        return alipayAmountEntityMapper.deleteAlipayAmountEntityByIds(Convert.toStrArray(ids));
    }

    /**
     * 审核加减款申请
     *
     * @param id 加减款记录ID
     * @return 结果
     */
    @Override
    public int deleteAlipayAmountEntityById(Long id) {
        return alipayAmountEntityMapper.deleteAlipayAmountEntityById(id);
    }

    @Override
    public int addAppOrder(AlipayAmountEntity alipayAmountEntity) {
        alipayAmountEntity.setCreateTime(DateUtils.getNowDate());
        alipayAmountEntity.setOrderId(GenerateOrderNo.getInstance().Generate(StaticConstants.PERFIX_REFUND_APP));
        alipayAmountEntity.setOrderStatus("2");
        return alipayAmountEntityMapper.insertAlipayAmountEntity(alipayAmountEntity);
    }

    /**
     * 审核商户补单申请，
     *
     * @param amountEntity
     * @return
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int additionaEditEnter(AlipayAmountEntity amountEntity) {
        /**
         * ###############
         * 补单申请确认逻辑
         * 1，修改amount 补单记录表的数据 和状态
         * 2，生成商户交易订单和主交易订单以供结算
         */
        // 1 修改当前补单申请的申请订单
        int i = alipayAmountEntityMapper.updateAlipayAmountEntity(amountEntity);
        //2，生成商户交易订单


        return 0;
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int insertAlipayAmountQuota(AlipayAmountEntity alipayAmountEntity) {
        alipayAmountEntity.setCreateTime(DateUtils.getNowDate());
        alipayAmountEntity.setOrderId(GenerateOrderNo.getInstance().Generate(StaticConstants.PERFIX_REFUND));
        alipayAmountEntity.setAmountType("5");
        alipayAmountEntity.setOrderStatus("2");
        alipayAmountEntity.setActualAmount(alipayAmountEntity.getAmount());
        return alipayAmountEntityMapper.insertAlipayAmountEntity(alipayAmountEntity);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int addTransfer(AlipayAmountEntity amount) {
        return alipayAmountEntityMapper.insertAlipayAmountEntity(amount);
    }
}
