package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayBankListEntity;
import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.domain.MerchantInfoEntity;
import java.util.List;

/**
 * 商户信息Service接口
 * 
 * @author ruoyi
 * @date 2020-03-18
 */
public interface IMerchantInfoEntityService 
{
    /**
     * 查询商户信息
     * 
     * @param id 商户信息ID
     * @return 商户信息
     */
    AlipayUserInfo selectMerchantInfoEntityById(Long id);

    /**
     * 查询商户信息列表
     * 
     * @param merchantInfoEntity 商户信息
     * @return 商户信息集合
     */
     List<AlipayUserInfo> selectMerchantInfoEntityList(AlipayUserInfo merchantInfoEntity);

    /**
     * 新增商户信息
     * 
     * @param merchantInfoEntity 商户信息
     * @return 结果
     */
     int insertMerchantInfoEntity(AlipayUserInfo merchantInfoEntity);

    /**
     * 修改商户信息
     * 
     * @param merchantInfoEntity 商户信息
     * @return 结果
     */
     int updateMerchantInfoEntity(AlipayUserInfo merchantInfoEntity);

    /**
     * 批量删除商户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
     int deleteMerchantInfoEntityByIds(String ids);

    /**
     * 删除商户信息信息
     * 
     * @param id 商户信息ID
     * @return 结果
     */
     int deleteMerchantInfoEntityById(Long id);

    /**
     * 重置商户的提现密码
     * @param id
     * @return
     */
    String resetPayPassword(Long id);



    /*下面处理风控模块的业务逻辑*/

    /**
     *  风控信息列表
     * @param merchantInfoEntity
     * @return
     */
    List<AlipayUserInfo> selectMerchantControlList(AlipayUserInfo merchantInfoEntity);

    /**
     * 根据userId查询商户详情
     * @param userId
     * @return
     */
    AlipayUserInfo selectBackUserByUserId(String userId);

    /**
     * 商户自行修改保存信息
     * @param alipayUserInfo
     * @return
     */
    int updateMerchantByBackAdmin(AlipayUserInfo alipayUserInfo);

    /**
     * 更新码商的交易地址
     * @param alipayUserInfo
     * @return
     */
    int updateAlipayUserInfoDealUrlByObj(AlipayUserInfo alipayUserInfo);

    /**
     * 根据UserId查询下线代理
     * @param alipayUserInfo
     * @return
     */
    List<AlipayUserInfo> selectAgentByMerchantId(AlipayUserInfo alipayUserInfo);
}
