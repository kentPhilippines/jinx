package com.ruoyi.alipay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayUserRateMapper;
import com.ruoyi.alipay.domain.AlipayUserRate;
import com.ruoyi.alipay.service.IAlipayUserRateService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户产品费率Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-02-27
 */
@Service
public class AlipayUserRateServiceImpl implements IAlipayUserRateService 
{
    @Autowired
    private AlipayUserRateMapper alipayUserRateMapper;

    /**
     * 查询用户产品费率
     * 
     * @param id 用户产品费率ID
     * @return 用户产品费率
     */
    @Override
    public AlipayUserRate selectAlipayUserRateById(Long id)
    {
        return alipayUserRateMapper.selectAlipayUserRateById(id);
    }

    /**
     * 查询用户产品费率列表
     * 
     * @param alipayUserRate 用户产品费率
     * @return 用户产品费率
     */
    @Override
    public List<AlipayUserRate> selectAlipayUserRateList(AlipayUserRate alipayUserRate)
    {
        return alipayUserRateMapper.selectAlipayUserRateList(alipayUserRate);
    }

    /**
     * 新增用户产品费率
     * 
     * @param alipayUserRate 用户产品费率
     * @return 结果
     */
    @Override
    public int insertAlipayUserRate(AlipayUserRate alipayUserRate)
    {
        return alipayUserRateMapper.insertAlipayUserRate(alipayUserRate);
    }

    /**
     * 修改用户产品费率
     * 
     * @param alipayUserRate 用户产品费率
     * @return 结果
     */
    @Override
    public int updateAlipayUserRate(AlipayUserRate alipayUserRate)
    {
        return alipayUserRateMapper.updateAlipayUserRate(alipayUserRate);
    }

    /**
     * 删除用户产品费率对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAlipayUserRateByIds(String ids)
    {
        return alipayUserRateMapper.deleteAlipayUserRateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户产品费率信息
     * 
     * @param id 用户产品费率ID
     * @return 结果
     */
    @Override
    public int deleteAlipayUserRateById(Long id)
    {
        return alipayUserRateMapper.deleteAlipayUserRateById(id);
    }
}
