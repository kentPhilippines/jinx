package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayUserRateEntity;

import java.util.List;

/**
 * 用户产品费率Service接口
 *
 * @author kiwi
 * @date 2020-03-18
 */
public interface IAlipayUserRateEntityService {
    /**
     * 查询用户产品费率
     *
     * @param id 用户产品费率ID
     * @return 用户产品费率
     */
    AlipayUserRateEntity selectAlipayUserRateEntityById(Long id);

    /**
     * 查询用户产品费率列表
     *
     * @param alipayUserRateEntity 用户产品费率
     * @return 用户产品费率集合
     */
    List<AlipayUserRateEntity> selectAlipayUserRateEntityList(AlipayUserRateEntity alipayUserRateEntity);

    /**
     * 新增用户产品费率
     *
     * @param alipayUserRateEntity 用户产品费率
     * @return 结果
     */
    int insertAlipayUserRateEntity(AlipayUserRateEntity alipayUserRateEntity);

    /**
     * 修改用户产品费率
     *
     * @param alipayUserRateEntity 用户产品费率
     * @return 结果
     */
    int updateAlipayUserRateEntity(AlipayUserRateEntity alipayUserRateEntity);

    /**
     * 批量删除用户产品费率
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAlipayUserRateEntityByIds(String ids);

    /**
     * 查询码商的费率
     *
     * @param alipayUserRateEntity
     * @return
     */
    List<AlipayUserRateEntity> selectUserRateEntityList_qr(AlipayUserRateEntity alipayUserRateEntity);

    /**
     * @param alipayUserRateEntity
     * @return
     */
    int insertAlipayUserRateEntity_qr(AlipayUserRateEntity alipayUserRateEntity);

    /**
     * 费率开关
     *
     * @param id
     * @param userId
     * @param feeType
     * @param switchs
     * @return
     */
    int changeStatus(String id, String userId, String feeType, String switchs);

    AlipayUserRateEntity checkUniqueRate(AlipayUserRateEntity alipayUserRateEntity);

    /**
     * <p>查询自己代理用的的费率情况</p>
     *
     * @param merchantId
     * @param rate
     * @return
     */
    List<AlipayUserRateEntity> findAgentRateLiat(String merchantId, AlipayUserRateEntity rate);

    /**
     * 检查当前费率是否有重复配置，和  当前费率是否有配置渠道费率
     *
     * @param alipayUserRateEntity
     * @return
     */
    Boolean clickFee(AlipayUserRateEntity alipayUserRateEntity);

    Boolean isAgentFee(AlipayUserRateEntity alipayUserRateEntity);
}
