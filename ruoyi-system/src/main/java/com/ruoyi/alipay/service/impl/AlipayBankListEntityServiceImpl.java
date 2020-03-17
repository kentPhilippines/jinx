package com.ruoyi.alipay.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayBankListEntityMapper;
import com.ruoyi.alipay.domain.AlipayBankListEntity;
import com.ruoyi.alipay.service.IAlipayBankListEntityService;
import com.ruoyi.common.core.text.Convert;

/**
 * 银行卡列表Service业务层处理
 * 
 * @author kiwi
 * @date 2020-03-17
 */
@Service
public class AlipayBankListEntityServiceImpl implements IAlipayBankListEntityService 
{
    @Autowired
    private AlipayBankListEntityMapper alipayBankListEntityMapper;

    /**
     * 查询银行卡列表
     * 
     * @param id 银行卡列表ID
     * @return 银行卡列表
     */
    @Override
    public AlipayBankListEntity selectAlipayBankListEntityById(Long id)
    {
        return alipayBankListEntityMapper.selectAlipayBankListEntityById(id);
    }

    /**
     * 查询银行卡列表列表
     * 
     * @param alipayBankListEntity 银行卡列表
     * @return 银行卡列表
     */
    @Override
    public List<AlipayBankListEntity> selectAlipayBankListEntityList(AlipayBankListEntity alipayBankListEntity)
    {
        return alipayBankListEntityMapper.selectAlipayBankListEntityList(alipayBankListEntity);
    }

    /**
     * 新增银行卡列表
     * 
     * @param alipayBankListEntity 银行卡列表
     * @return 结果
     */
    @Override
    public int insertAlipayBankListEntity(AlipayBankListEntity alipayBankListEntity)
    {
        alipayBankListEntity.setCreateTime(DateUtils.getNowDate());
        return alipayBankListEntityMapper.insertAlipayBankListEntity(alipayBankListEntity);
    }

    /**
     * 修改银行卡列表
     * 
     * @param alipayBankListEntity 银行卡列表
     * @return 结果
     */
    @Override
    public int updateAlipayBankListEntity(AlipayBankListEntity alipayBankListEntity)
    {
        return alipayBankListEntityMapper.updateAlipayBankListEntity(alipayBankListEntity);
    }

    /**
     * 删除银行卡列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAlipayBankListEntityByIds(String ids)
    {
        return alipayBankListEntityMapper.deleteAlipayBankListEntityByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除银行卡列表信息
     * 
     * @param id 银行卡列表ID
     * @return 结果
     */
    @Override
    public int deleteAlipayBankListEntityById(Long id)
    {
        return alipayBankListEntityMapper.deleteAlipayBankListEntityById(id);
    }
}
