package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.BankInfoSplitEntity;

/**
 * 手动加扣款记录Mapper接口
 *
 * @author kiwi
 * @date 2020-03-24
 */
public interface BankInfoSplitEntityMapper {


    /**
     * 新增截取银行流水信息数据
     *
     * @param bankInfoSplitEntity
     * @return 结果
     */
    int insertBankInfoSplitEntity(BankInfoSplitEntity bankInfoSplitEntity);
}
