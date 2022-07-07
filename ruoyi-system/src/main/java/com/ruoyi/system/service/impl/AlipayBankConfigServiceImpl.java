package com.ruoyi.system.service.impl;

import com.ruoyi.alipay.domain.AlipayBankConfig;
import com.ruoyi.alipay.mapper.AlipayBankConfigMapper;
import com.ruoyi.alipay.service.IAlipayBankConfigService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * bankconfigService业务层处理
 * 
 * @author ruoyi
 * @date 2022-07-07
 */
@Service
public class AlipayBankConfigServiceImpl implements IAlipayBankConfigService
{
    @Autowired
    private AlipayBankConfigMapper alipayBankConfigMapper;

    /**
     * 查询bankconfig
     * 
     * @param id bankconfigID
     * @return bankconfig
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public AlipayBankConfig selectAlipayBankConfigById(Integer id)
    {
        return alipayBankConfigMapper.selectAlipayBankConfigById(id);
    }

    /**
     * 查询bankconfig列表
     * 
     * @param alipayBankConfig bankconfig
     * @return bankconfig
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayBankConfig> selectAlipayBankConfigList(AlipayBankConfig alipayBankConfig)
    {
        return alipayBankConfigMapper.selectAlipayBankConfigList(alipayBankConfig);
    }

    /**
     * 新增bankconfig
     * 
     * @param alipayBankConfig bankconfig
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int insertAlipayBankConfig(AlipayBankConfig alipayBankConfig)
    {
        alipayBankConfig.setCreateTime(DateUtils.getNowDate());
        return alipayBankConfigMapper.insertAlipayBankConfig(alipayBankConfig);
    }

    /**
     * 修改bankconfig
     * 
     * @param alipayBankConfig bankconfig
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int updateAlipayBankConfig(AlipayBankConfig alipayBankConfig)
    {
        return alipayBankConfigMapper.updateAlipayBankConfig(alipayBankConfig);
    }

    /**
     * 删除bankconfig对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int deleteAlipayBankConfigByIds(String ids)
    {
        return alipayBankConfigMapper.deleteAlipayBankConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除bankconfig信息
     * 
     * @param id bankconfigID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int deleteAlipayBankConfigById(Integer id)
    {
        return alipayBankConfigMapper.deleteAlipayBankConfigById(id);
    }
}
