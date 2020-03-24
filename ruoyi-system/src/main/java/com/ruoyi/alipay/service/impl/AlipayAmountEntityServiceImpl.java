package com.ruoyi.alipay.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayAmountEntityMapper;
import com.ruoyi.alipay.domain.AlipayAmountEntity;
import com.ruoyi.alipay.service.IAlipayAmountEntityService;
import com.ruoyi.common.core.text.Convert;

/**
 * 手动加扣款记录Service业务层处理
 *
 * @author kiwi
 * @date 2020-03-24
 */
@Service
public class AlipayAmountEntityServiceImpl implements IAlipayAmountEntityService {
    @Autowired
    private AlipayAmountEntityMapper alipayAmountEntityMapper;

    /**
     * 查询手动加扣款记录
     *
     * @param id 手动加扣款记录ID
     * @return 手动加扣款记录
     */
    @Override
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

    /**
     * 新增手动加扣款记录
     *
     * @param alipayAmountEntity 手动加扣款记录
     * @return 结果
     */
    @Override
    public int insertAlipayAmountEntity(AlipayAmountEntity alipayAmountEntity) {
        alipayAmountEntity.setCreateTime(DateUtils.getNowDate());
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
     * 删除手动加扣款记录信息
     *
     * @param id 手动加扣款记录ID
     * @return 结果
     */
    @Override
    public int deleteAlipayAmountEntityById(Long id) {
        return alipayAmountEntityMapper.deleteAlipayAmountEntityById(id);
    }
}
