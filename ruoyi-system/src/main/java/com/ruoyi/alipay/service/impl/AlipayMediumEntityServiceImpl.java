package com.ruoyi.alipay.service.impl;

import com.ruoyi.alipay.domain.AlipayMediumEntity;
import com.ruoyi.alipay.mapper.AlipayMediumEntityMapper;
import com.ruoyi.alipay.service.IAlipayMediumEntityService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收款媒介列Service业务层处理
 *
 * @author kiwi
 * @date 2020-03-17
 */
@Service
public class AlipayMediumEntityServiceImpl implements IAlipayMediumEntityService {
    @Autowired
    private AlipayMediumEntityMapper alipayMediumEntityMapper;

    /**
     * 查询收款媒介列
     *
     * @param id 收款媒介列ID
     * @return 收款媒介列
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public AlipayMediumEntity selectAlipayMediumEntityById(Long id) {
        return alipayMediumEntityMapper.selectAlipayMediumEntityById(id);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<String> selectCodeByAlipayMediumEntity() {
        return alipayMediumEntityMapper.selectCodeByAlipayMediumEntity();
    }

    /**
     * 查询收款媒介列列表
     *
     * @param alipayMediumEntity 收款媒介列
     * @return 收款媒介列
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayMediumEntity> selectAlipayMediumEntityList(AlipayMediumEntity alipayMediumEntity) {
        return alipayMediumEntityMapper.selectAlipayMediumEntityList(alipayMediumEntity);
    }

    /**
     * 新增收款媒介列
     *
     * @param alipayMediumEntity 收款媒介列
     * @return 结果
     */
    @Override
    public int insertAlipayMediumEntity(AlipayMediumEntity alipayMediumEntity) {
        alipayMediumEntity.setCreateTime(DateUtils.getNowDate());
        return alipayMediumEntityMapper.insertAlipayMediumEntity(alipayMediumEntity);
    }

    /**
     * 修改收款媒介列
     *
     * @param alipayMediumEntity 收款媒介列
     * @return 结果
     */
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    @Override
    public int updateAlipayMediumEntity(AlipayMediumEntity alipayMediumEntity) {
        return alipayMediumEntityMapper.updateAlipayMediumEntity(alipayMediumEntity);
    }

    /**
     * 根据code修改上限金额
     *
     * @param alipayMediumEntity 收款媒介列
     * @return 结果
     */
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    @Override
    public int updateAlipayMediumEntityByCode(AlipayMediumEntity alipayMediumEntity) {
        return alipayMediumEntityMapper.updateAlipayMediumEntityByCode(alipayMediumEntity);
    }

    /**
     * 删除收款媒介列对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAlipayMediumEntityByIds(String ids) {
        return alipayMediumEntityMapper.deleteAlipayMediumEntityByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除收款媒介列信息
     *
     * @param id 收款媒介列ID
     * @return 结果
     */
    @Override
    public int deleteAlipayMediumEntityById(Long id) {
        return alipayMediumEntityMapper.deleteAlipayMediumEntityById(id);
    }
}
