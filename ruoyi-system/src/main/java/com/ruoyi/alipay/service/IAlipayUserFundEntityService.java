package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayUserFundEntity;
import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

/**
 * 用户资金账户Service接口
 *
 * @author kiwi
 * @date 2020-03-17
 */
public interface IAlipayUserFundEntityService {
    /**
     * 查询用户资金账户
     *
     * @param id 用户资金账户ID
     * @return 用户资金账户
     */
    AlipayUserFundEntity selectAlipayUserFundEntityById(Long id);

    /**
     * 查询用户资金账户列表
     *
     * @param alipayUserFundEntity 用户资金账户
     * @return 用户资金账户集合
     */
    List<AlipayUserFundEntity> selectAlipayUserFundEntityList(AlipayUserFundEntity alipayUserFundEntity);

    /**
     * 新增用户资金账户
     *
     * @param alipayUserFundEntity 用户资金账户
     * @return 结果
     */
    int insertAlipayUserFundEntity(AlipayUserFundEntity alipayUserFundEntity);

    /**
     * 修改用户资金账户
     *
     * @param alipayUserFundEntity 用户资金账户
     * @return 结果
     */
    int updateAlipayUserFundEntity(AlipayUserFundEntity alipayUserFundEntity);

    /**
     * 根据userId查询用户的资金账户对象
     *
     * @param merchantId
     * @return
     */
    AlipayUserFundEntity findAlipayUserFundByUserId(String merchantId);

    /**
     * <p>渠道账户查询</p>
     *
     * @param alipayUserFundEntity
     * @return
     */
    List<AlipayUserFundEntity> findChannelAccount(AlipayUserFundEntity alipayUserFundEntity);

    /**
     * <p>获取所有的账户资金</p>
     *
     * @return
     */
    List<AlipayUserFundEntity> findUserFundAll();

    /**
     * <p>查询所有的渠道账户</p>
     *
     * @return
     */
    List<AlipayUserFundEntity> findUserFundRate();

    List<AlipayUserFundEntity> findUserBakBy(String merchantId, BaseEntity baseEntity);

    List<AlipayUserFundEntity> findMyUserBak(String merchantId, BaseEntity baseEntity);

    List<AlipayUserFundEntity> findUserAppAll(BaseEntity baseEntity);


    /**
     * 查看商户每日对账数据
     *
     * @param alipayUserFundEntity
     * @return
     */
    List<AlipayUserFundEntity> findaAcountList(AlipayUserFundEntity alipayUserFundEntity);


    /**
     * 统计当前商户冻结账户和余额账户
     *
     * @return
     */
    AlipayUserFundEntity findSumFundM();

    List<AlipayUserInfo> findUserByAgent(String agentUserId);

    int updateStatus(String userId, Integer status);

    List<AlipayUserFundEntity> findUserList(List<String> asList, BaseEntity baseEntity);


    /**
     * 查询交易量前15的用户月交易量
     *
     * @return
     */
    List<String> findDealAfter15();
}
