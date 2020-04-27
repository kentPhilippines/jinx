package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayRechargeEntity;
import com.ruoyi.common.core.domain.StatisticsEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 充值记录Mapper接口
 * 
 * @author kiwi
 * @date 2020-03-17
 */
public interface AlipayRechargeEntityMapper 
{
    /**
     * 查询充值记录
     * 
     * @param id 充值记录ID
     * @return 充值记录
     */
    AlipayRechargeEntity selectAlipayRechargeEntityById(Long id);

    /**
     * 查询充值记录列表
     * 
     * @param alipayRechargeEntity 充值记录
     * @return 充值记录集合
     */
    List<AlipayRechargeEntity> selectAlipayRechargeEntityList(AlipayRechargeEntity alipayRechargeEntity);

     /**
     * 修改状态
     * 
     * @param alipayRechargeEntity 充值记录
     * @return 结果
     */
    int updateAlipayRechargeEntity(AlipayRechargeEntity alipayRechargeEntity);

}
