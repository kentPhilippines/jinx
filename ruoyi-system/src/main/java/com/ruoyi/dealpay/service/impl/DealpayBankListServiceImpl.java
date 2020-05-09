package com.ruoyi.dealpay.service.impl;

import java.util.List;

import cn.hutool.core.util.IdUtil;
import com.ruoyi.alipay.domain.AlipayBankListEntity;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dealpay.mapper.DealpayBankListMapper;
import com.ruoyi.dealpay.domain.DealpayBankListEntity;
import com.ruoyi.dealpay.service.IDealpayBankListService;
import com.ruoyi.common.core.text.Convert;

/**
 * 银行卡列Service业务层处理
 *
 * @author k
 * @date 2020-04-03
 */
@Service
public class DealpayBankListServiceImpl implements IDealpayBankListService {
    @Autowired
    protected DealpayBankListMapper dealpayBankListMapper;

    /**
     * 查询银行卡列
     *
     * @param id 银行卡列ID
     * @return 银行卡列
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public DealpayBankListEntity selectDealpayBankListById(Long id) {
        return dealpayBankListMapper.selectDealpayBankListById(id);
    }

    /**
     * 查询银行卡列列表
     *
     * @param dealpayBankList 银行卡列
     * @return 银行卡列
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public List<DealpayBankListEntity> selectDealpayBankListList(DealpayBankListEntity dealpayBankList) {
        dealpayBankList.setIsDeal(2);
        return dealpayBankListMapper.selectDealpayBankListList(dealpayBankList);
    }

    /**
     * 新增银行卡列
     *
     * @param dealpayBankList 银行卡列
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int insertDealpayBankList(DealpayBankListEntity dealpayBankList) {
        dealpayBankList.setCreateTime(DateUtils.getNowDate());
        String objectId = IdUtil.objectId().toUpperCase();
        dealpayBankList.setBankcardId("SYS-C"+objectId);
        return dealpayBankListMapper.insertDealpayBankList(dealpayBankList);
    }

    /**
     * 修改银行卡列
     *
     * @param dealpayBankList 银行卡列
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int updateDealpayBankList(DealpayBankListEntity dealpayBankList) {
        return dealpayBankListMapper.updateDealpayBankList(dealpayBankList);
    }

    /**
     * 删除银行卡列对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int deleteDealpayBankListByIds(String ids) {
        return dealpayBankListMapper.deleteDealpayBankListByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除银行卡列信息
     *
     * @param id 银行卡列ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int deleteDealpayBankListById(Long id) {
        return dealpayBankListMapper.deleteDealpayBankListById(id);
    }

    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int updateDealpayBankCardStatusById(DealpayBankListEntity dealpayBankListEntity) {
        return dealpayBankListMapper.updateDealpayBankCardStatusById(dealpayBankListEntity);

    }

    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int updateDealpayBankCardBlackList(DealpayBankListEntity dealpayBankListEntity) {
        DealpayBankListEntity check = dealpayBankListMapper.selectPayforCardInfoByAccount(dealpayBankListEntity);
        if (check == null) {
            throw new BusinessException("银行卡信息不正确或已被删除");
        }
        return dealpayBankListMapper.updatePayforSysTypeByAccount(check);
    }

    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int deleteDealpayBankBlackListById(String ids) {
        return dealpayBankListMapper.updateDealpayBankCardBlackListById(ids);
    }
}
