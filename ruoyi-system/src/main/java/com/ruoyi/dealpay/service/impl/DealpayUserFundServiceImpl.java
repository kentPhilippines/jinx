package com.ruoyi.dealpay.service.impl;

import java.util.List;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dealpay.mapper.DealpayUserFundMapper;
import com.ruoyi.dealpay.domain.DealpayUserFundEntity;
import com.ruoyi.dealpay.service.IDealpayUserFundService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户资金账户Service业务层处理
 *
 * @author kiwi
 * @date 2020-04-03
 */
@Service
public class DealpayUserFundServiceImpl implements IDealpayUserFundService {
    @Autowired
    private DealpayUserFundMapper dealpayUserFundMapper;

    /**
     * 查询用户资金账户
     *
     * @param id 用户资金账户ID
     * @return 用户资金账户
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public DealpayUserFundEntity selectDealpayUserFundById(Long id) {
        return dealpayUserFundMapper.selectDealpayUserFundById(id);
    }

    /**
     * 查询用户资金账户列表
     *
     * @param dealpayUserFund 用户资金账户
     * @return 用户资金账户
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public List<DealpayUserFundEntity> selectDealpayUserFundList(DealpayUserFundEntity dealpayUserFund) {
        return dealpayUserFundMapper.selectDealpayUserFundList(dealpayUserFund);
    }

    /**
     * 新增用户资金账户
     *
     * @param dealpayUserFund 用户资金账户
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int insertDealpayUserFund(DealpayUserFundEntity dealpayUserFund) {
        dealpayUserFund.setCreateTime(DateUtils.getNowDate());
        return dealpayUserFundMapper.insertDealpayUserFund(dealpayUserFund);
    }

    /**
     * 修改用户资金账户
     *
     * @param dealpayUserFund 用户资金账户
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int updateDealpayUserFund(DealpayUserFundEntity dealpayUserFund) {
        return dealpayUserFundMapper.updateDealpayUserFund(dealpayUserFund);
    }

    /**
     * 删除用户资金账户对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int deleteDealpayUserFundByIds(String ids) {
        return dealpayUserFundMapper.deleteDealpayUserFundByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户资金账户信息
     *
     * @param id 用户资金账户ID
     * @return 结果
     */
    @Override
    @DataSource(value = DataSourceType.DEALPAY_SLAVE)
    public int deleteDealpayUserFundById(Long id) {
        return dealpayUserFundMapper.deleteDealpayUserFundById(id);
    }
}
