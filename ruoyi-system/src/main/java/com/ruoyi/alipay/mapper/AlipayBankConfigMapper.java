package com.ruoyi.alipay.mapper;


import com.ruoyi.alipay.domain.AlipayBankConfig;

import java.util.List;

/**
 * bankconfigMapper接口
 * 
 * @author ruoyi
 * @date 2022-07-07
 */
public interface AlipayBankConfigMapper 
{
    /**
     * 查询bankconfig
     * 
     * @param id bankconfigID
     * @return bankconfig
     */
    public AlipayBankConfig selectAlipayBankConfigById(Integer id);

    /**
     * 查询bankconfig列表
     * 
     * @param alipayBankConfig bankconfig
     * @return bankconfig集合
     */
    public List<AlipayBankConfig> selectAlipayBankConfigList(AlipayBankConfig alipayBankConfig);

    /**
     * 新增bankconfig
     * 
     * @param alipayBankConfig bankconfig
     * @return 结果
     */
    public int insertAlipayBankConfig(AlipayBankConfig alipayBankConfig);

    /**
     * 修改bankconfig
     * 
     * @param alipayBankConfig bankconfig
     * @return 结果
     */
    public int updateAlipayBankConfig(AlipayBankConfig alipayBankConfig);

    /**
     * 删除bankconfig
     * 
     * @param id bankconfigID
     * @return 结果
     */
    public int deleteAlipayBankConfigById(Integer id);

    /**
     * 批量删除bankconfig
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayBankConfigByIds(String[] ids);
}
