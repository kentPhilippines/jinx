package com.ruoyi.alipay.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayConfirmRewardConfigMapper;
import com.ruoyi.alipay.domain.AlipayConfirmRewardConfig;
import com.ruoyi.alipay.service.IAlipayConfirmRewardConfigService;
import com.ruoyi.common.core.text.Convert;

/**
 * 代付确认奖励配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-05-03
 */
@Service
public class AlipayConfirmRewardConfigServiceImpl implements IAlipayConfirmRewardConfigService 
{
    @Autowired
    private AlipayConfirmRewardConfigMapper alipayConfirmRewardConfigMapper;

    /**
     * 查询代付确认奖励配置
     * 
     * @param id 代付确认奖励配置ID
     * @return 代付确认奖励配置
     */
    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public AlipayConfirmRewardConfig selectAlipayConfirmRewardConfigById(Long id)
    {
        return alipayConfirmRewardConfigMapper.selectAlipayConfirmRewardConfigById(id);
    }

    /**
     * 查询代付确认奖励配置列表
     * 
     * @param alipayConfirmRewardConfig 代付确认奖励配置
     * @return 代付确认奖励配置
     */
    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public List<AlipayConfirmRewardConfig> selectAlipayConfirmRewardConfigList(AlipayConfirmRewardConfig alipayConfirmRewardConfig)
    {
        return alipayConfirmRewardConfigMapper.selectAlipayConfirmRewardConfigList(alipayConfirmRewardConfig);
    }

    /**
     * 新增代付确认奖励配置
     * 
     * @param alipayConfirmRewardConfig 代付确认奖励配置
     * @return 结果
     */
    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public int insertAlipayConfirmRewardConfig(AlipayConfirmRewardConfig alipayConfirmRewardConfig)
    {
        return alipayConfirmRewardConfigMapper.insertAlipayConfirmRewardConfig(alipayConfirmRewardConfig);
    }

    /**
     * 修改代付确认奖励配置
     * 
     * @param alipayConfirmRewardConfig 代付确认奖励配置
     * @return 结果
     */
    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public int updateAlipayConfirmRewardConfig(AlipayConfirmRewardConfig alipayConfirmRewardConfig)
    {
        return alipayConfirmRewardConfigMapper.updateAlipayConfirmRewardConfig(alipayConfirmRewardConfig);
    }

    /**
     * 删除代付确认奖励配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public int deleteAlipayConfirmRewardConfigByIds(String ids)
    {
        return alipayConfirmRewardConfigMapper.deleteAlipayConfirmRewardConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除代付确认奖励配置信息
     * 
     * @param id 代付确认奖励配置ID
     * @return 结果
     */
    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public int deleteAlipayConfirmRewardConfigById(Long id)
    {
        return alipayConfirmRewardConfigMapper.deleteAlipayConfirmRewardConfigById(id);
    }
}
