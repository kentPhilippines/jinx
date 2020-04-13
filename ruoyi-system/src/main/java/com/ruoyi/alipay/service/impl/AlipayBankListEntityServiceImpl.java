package com.ruoyi.alipay.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.constant.StaticConstants;
import com.ruoyi.common.constant.SystemConstants;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.GenerateOrderNo;
import org.omg.CORBA.DATA_CONVERSION;
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
public class AlipayBankListEntityServiceImpl implements IAlipayBankListEntityService {
    @Autowired
    private AlipayBankListEntityMapper alipayBankListEntityMapper;

    /**
     * 查询银行卡列表
     *
     * @param id 银行卡列表ID
     * @return 银行卡列表
     */
    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public AlipayBankListEntity selectAlipayBankListEntityById(Long id) {
        return alipayBankListEntityMapper.selectAlipayBankListEntityById(id);
    }

    /**
     * 商户提现申请，有效银行卡
     *
     * @param alipayBankListEntity 银行卡列表
     * @return 银行卡列表
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayBankListEntity> selectAlipayBankListEntityList(AlipayBankListEntity alipayBankListEntity) {
        alipayBankListEntity.setIsDeal(2);
        return alipayBankListEntityMapper.selectAlipayBankListEntityList(alipayBankListEntity);
    }

    /**
     * 新增银行卡列表
     *
     * @param alipayBankListEntity 银行卡列表
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int insertAlipayBankListEntity(AlipayBankListEntity alipayBankListEntity) {
        alipayBankListEntity.setBankcardId(GenerateOrderNo.getInstance().Generate(StaticConstants.BANK_CARD_ID));
        alipayBankListEntity.setBankcode("R");
        alipayBankListEntity.setCardType(2);
        alipayBankListEntity.setSysTYpe(2);
        return alipayBankListEntityMapper.insertAlipayBankListEntity(alipayBankListEntity);
    }

    /**
     * 修改银行卡列表
     *
     * @param alipayBankListEntity 银行卡列表
     * @return 结果
     */
    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public int updateAlipayBankListEntity(AlipayBankListEntity alipayBankListEntity) {
        alipayBankListEntity.setSubmitTime(new Date());
        return alipayBankListEntityMapper.updateAlipayBankListEntity(alipayBankListEntity);
    }

    /**
     * 删除银行卡列表对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int deleteAlipayBankListEntityByIds(String ids) {
        return alipayBankListEntityMapper.deleteAlipayBankListEntityByIds(Convert.toStrArray(ids));
    }

    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public int updateBankCardStatusById(AlipayBankListEntity alipayBankListEntity) {
        return alipayBankListEntityMapper.updateBankCardStatusById(alipayBankListEntity);
    }

    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public int updateAlipayBankCardBlackList(AlipayBankListEntity alipayBankListEntity) {
        AlipayBankListEntity check = alipayBankListEntityMapper.selectBankCardByAccount(alipayBankListEntity);
        if (check == null) {
            throw new BusinessException("银行卡信息不正确或已被删除");
        }
        return alipayBankListEntityMapper.updateBankCardSysTypeByAccount(check);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int deleteAlipayBankBlackListById(String ids) {
        return alipayBankListEntityMapper.deleteAlipayBankListSysTypeById(ids);
    }

}
