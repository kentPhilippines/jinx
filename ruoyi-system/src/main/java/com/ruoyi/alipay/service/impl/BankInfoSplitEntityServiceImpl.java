package com.ruoyi.alipay.service.impl;

import com.ruoyi.alipay.domain.BankInfoSplitEntity;
import com.ruoyi.alipay.mapper.BankInfoSplitEntityMapper;
import com.ruoyi.alipay.service.IBankInfoSplitResultService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 收款媒介列Service业务层处理
 *
 * @author kiwi
 * @date 2020-03-17
 */
@Service
public class BankInfoSplitEntityServiceImpl implements IBankInfoSplitResultService {
    @Autowired
    private BankInfoSplitEntityMapper bankInfoSplitEntityMapper;

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int insertBankInfoSplitResult(BankInfoSplitEntity bankInfoSplitEntity) {
        return bankInfoSplitEntityMapper.insertBankInfoSplitEntity(bankInfoSplitEntity);
    }

}
