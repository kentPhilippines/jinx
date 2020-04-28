package com.ruoyi.dealpay.service;

import com.ruoyi.dealpay.domain.DealpayUserRateEntity;

import java.util.List;

/**
 * 费率Service接口
 *
 * @author kiwi
 * @date 2020-04-03
 */
public interface IDealpayUserRateService {
    /**
     * 查询费率
     *
     * @param id 费率ID
     * @return 费率
     */
    public DealpayUserRateEntity selectDealpayUserRateEntityById(Long id);

    /**
     * 查询费率列表
     *
     * @param dealpayUserRateEntity 费率
     * @return 费率集合
     */
    public List<DealpayUserRateEntity> selectDealpayUserRateEntityList(DealpayUserRateEntity dealpayUserRateEntity);

    /**
     * 新增费率
     *
     * @param dealpayUserRateEntity 费率
     * @return 结果
     */
    public int insertDealpayUserRateEntity(DealpayUserRateEntity dealpayUserRateEntity);

    /**
     * 修改费率
     *
     * @param dealpayUserRateEntity 费率
     * @return 结果
     */
    public int updateDealpayUserRateEntity(DealpayUserRateEntity dealpayUserRateEntity);

    /**
     * 修改费率通道的状态
     * @param id
     * @param userId
     * @param feeType
     * @param switchs
     * @return
     */
    int changeStatus(String id, String userId, String feeType, String switchs);

    /**
     * <p>确认卡商账户唯一</p>
     * @param userId
     * @return
     */
    DealpayUserRateEntity checkDealpayUserIdUnique(String userId);
}
