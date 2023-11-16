package com.ruoyi.alipay.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.ruoyi.alipay.domain.AlipayChanelFee;
import com.ruoyi.alipay.domain.AlipayWithdrawEntity;
import com.ruoyi.alipay.mapper.AlipayChanelFeeMapper;
import com.ruoyi.alipay.mapper.AlipayWithdrawEntityMapper;
import com.ruoyi.alipay.service.IAlipayWithdrawEntityService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.domain.StatisticsEntity;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.exception.BusinessException;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 会员提现记录Service业务层处理
 *
 * @author kiwi
 * @date 2020-03-17
 */
@Service
public class AlipayWithdrawEntityServiceImpl implements IAlipayWithdrawEntityService {
    @Resource
    private AlipayWithdrawEntityMapper alipayWithdrawEntityMapper;

    @Autowired
    private AlipayChanelFeeMapper alipayChanelFeeMapper;

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public void batchUpdateChannel(List<AlipayWithdrawEntity> list)
    {
        list.stream().forEach(e->{
            this.updateWithdrawEntityById(e);
        });

    }
    /**
     * 查询会员提现记录
     *
     * @param id 会员提现记录ID
     * @return 会员提现记录
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public AlipayWithdrawEntity selectAlipayWithdrawEntityById(Long id) {
        return alipayWithdrawEntityMapper.selectAlipayWithdrawEntityById(id);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public void updateWithdrawEntityById(AlipayWithdrawEntity alipayWithdrawEntity) {
        //todo 判断订单超时时间

        AlipayWithdrawEntity data  = alipayWithdrawEntityMapper.selectAlipayWithdrawEntityById(alipayWithdrawEntity.getId());
        DateTime now = DateTime.of(new Date());
        DateTime expireDate = DateUtil.offset(data.getCreateTime(), DateField.SECOND, data.getWatingTime());
        if(now.isAfter(expireDate))
        {
            throw new BusinessException(alipayWithdrawEntity.getOrderId()+"已经超时，无法调整渠道");
        }


        //校验费率是否存在
        AlipayChanelFee chanelFee = alipayChanelFeeMapper.findChannelBy(data.getWitChannel(),data.getWitType());
        if(chanelFee==null)
        {
            throw new BusinessException(data.getWitChannel()+"--"+data.getWitType()+"渠道费率未配置");
        }


         alipayWithdrawEntityMapper.updateByPrimaryKeySelective(alipayWithdrawEntity);
    }
    /**
     * 查询会员提现记录
     *
     * @param ids 会员提现记录ID
     * @return 会员提现记录
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayWithdrawEntity> selectAlipayWithdrawEntityByIds(String ids) {
        String[] split = ids.split(",");
        return alipayWithdrawEntityMapper.selectAlipayWithdrawEntityByIds(split);
    }

    /**
     * 查询会员提现记录列表
     *
     * @param alipayWithdrawEntity 会员提现记录
     * @return 会员提现记录
     */
    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayWithdrawEntity> selectAlipayWithdrawEntityList(AlipayWithdrawEntity alipayWithdrawEntity) {
        return alipayWithdrawEntityMapper.selectAlipayWithdrawEntityList(alipayWithdrawEntity);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public StatisticsEntity selectPayforStatisticsDataByDay(String dayStart, String dayEnd) {
        return alipayWithdrawEntityMapper.selectPayforStatDataByDay(dayStart, dayEnd);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<StatisticsEntity> statisticsWit(StatisticsEntity statisticsEntity) {
        /**
         * #########  查询类型包括
         * 1,账户类型
         * 2,代付金额累计
         * 3,代付手续费累计
         * 4,api 和后台代付划分
         * 5,不同渠道代付划分
         * ############以上是商·户维度数据
         */


        /**
         * ##############
         * 渠道维度数据
         * 1,账户类型
         * 2,代付金额累计
         * 3,代付手续费累计
         * 4,api 和后台代付划分
         */


        List<StatisticsEntity> staList = alipayWithdrawEntityMapper.statisticsWit(statisticsEntity);


        return staList;
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public void updateWitStatus(Long id) {
        alipayWithdrawEntityMapper.updateWitStatus(id);
    }


    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public List<AlipayWithdrawEntity> findWitLimit(String starTime, String endTime, Integer page, Integer size) {
        return alipayWithdrawEntityMapper.findWitLimit(starTime, endTime, page, size);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public AlipayWithdrawEntity selectAlipayWithdrawEntityListSum(AlipayWithdrawEntity alipayWithdrawEntity) {
        return alipayWithdrawEntityMapper.selectAlipayWithdrawEntityListSum(alipayWithdrawEntity);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int updateMacthMore(String orderId, Integer moreMacth) {
        return alipayWithdrawEntityMapper.updateMacthMore(orderId,moreMacth);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public void batchUpdateMacthMore(String ids, Integer moreMacth, String watingTime) {
        Arrays.stream(ids.split(",")).forEach(id->{
            alipayWithdrawEntityMapper.updateMacthMoreById(id,moreMacth);
        });

    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public void batchUpdateMacthMoreWatingTime(String ids, String watingTime) {
        Integer wati = Integer.valueOf(watingTime);
        Arrays.stream(ids.split(",")).forEach(id->{
            alipayWithdrawEntityMapper.batchUpdateMacthMoreWatingTime(id,wati);
        });
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public void deleteUserId(String userId) {
        alipayWithdrawEntityMapper.deleteUserId(userId);
    }
}
