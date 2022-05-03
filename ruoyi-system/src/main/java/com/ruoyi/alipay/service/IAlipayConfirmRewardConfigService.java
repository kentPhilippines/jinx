package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayConfirmRewardConfig;
import java.util.List;

/**
 * 代付确认奖励配置Service接口
 * 
 * @author ruoyi
 * @date 2022-05-03
 */
public interface IAlipayConfirmRewardConfigService 
{
    /**
     * 查询代付确认奖励配置
     * 
     * @param id 代付确认奖励配置ID
     * @return 代付确认奖励配置
     */
    public AlipayConfirmRewardConfig selectAlipayConfirmRewardConfigById(Long id);

    /**
     * 查询代付确认奖励配置列表
     * 
     * @param alipayConfirmRewardConfig 代付确认奖励配置
     * @return 代付确认奖励配置集合
     */
    public List<AlipayConfirmRewardConfig> selectAlipayConfirmRewardConfigList(AlipayConfirmRewardConfig alipayConfirmRewardConfig);

    /**
     * 新增代付确认奖励配置
     * 
     * @param alipayConfirmRewardConfig 代付确认奖励配置
     * @return 结果
     */
    public int insertAlipayConfirmRewardConfig(AlipayConfirmRewardConfig alipayConfirmRewardConfig);

    /**
     * 修改代付确认奖励配置
     * 
     * @param alipayConfirmRewardConfig 代付确认奖励配置
     * @return 结果
     */
    public int updateAlipayConfirmRewardConfig(AlipayConfirmRewardConfig alipayConfirmRewardConfig);

    /**
     * 批量删除代付确认奖励配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayConfirmRewardConfigByIds(String ids);

    /**
     * 删除代付确认奖励配置信息
     * 
     * @param id 代付确认奖励配置ID
     * @return 结果
     */
    public int deleteAlipayConfirmRewardConfigById(Long id);
}
