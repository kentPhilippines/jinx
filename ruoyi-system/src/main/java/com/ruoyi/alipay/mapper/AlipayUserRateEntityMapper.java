package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayUserRateEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 用户产品费率Mapper接口
 *
 * @author kiwi
 * @date 2020-03-18
 */
public interface AlipayUserRateEntityMapper {
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
    int insertAlipayUserRateEntity(  AlipayUserRateEntity alipayUserRateEntity);

    /**
     * 修改用户产品费率
     *
     * @param alipayUserRateEntity 用户产品费率
     * @return 结果
     */
    int updateAlipayUserRateEntity(AlipayUserRateEntity alipayUserRateEntity);

    /**
     * 删除用户产品费率
     *
     * @param id 用户产品费率ID
     * @return 结果
     */
    int deleteAlipayUserRateEntityById(Long id);

    /**
     * 批量删除用户产品费率
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAlipayUserRateEntityByIds(String[] ids);


    //下面都是码商费率的逻辑处理

    /**
     * 码商的费率
     *
     * @param alipayUserRateEntity
     * @return
     */
    List<AlipayUserRateEntity> selectUserRateEntityList_qr(AlipayUserRateEntity alipayUserRateEntity);

    /**
     * @param alipayUserRateEntity
     * @return
     */
    int insertAlipayUserRateEntity_qr( AlipayUserRateEntity alipayUserRateEntity);

    /**
     * 码商的逻辑
     *
     * @param id
     * @return
     */
    AlipayUserRateEntity selectAlipayUserRateEntityById_qr(Long id);

    /**
     * 根据userId查询实体对象
     *
     * @param userId
     * @return
     */
    @Select("select id, userId, userType, switchs, payTypr, " +
            "fee, feeType, createTime, submitTime, status " +
            "from alipay_user_rate where userId = #{userId} ")
    AlipayUserRateEntity findObjectEntityByUserId(@Param("userId") String userId);

    /**
     * @param id
     * @param switchs
     * @return
     */
    @Update("update alipay_user_rate set switchs = #{switchs} where id = #{id} ")
    int updateStatus(@Param("id") String id, @Param("switchs") String switchs);

    /**
     *
     * @param userId
     * @param feeType
     * @return
     */
    @Select("select * from alipay_user_rate where userId = #{userId}  and feeType = #{feeType} and switchs = 1")
	List<AlipayUserRateEntity> selectListObjectEntityByUserId(@Param("userId") String userId,@Param("feeType") String feeType);

    @Select("<script>" +
            "select id, userId, fee, feeType, switchs, payTypr from alipay_user_rate where userId = #{agent} and feeType = #{feeType} and channelId =#{channel}" +
            "<if test=\"payTypr != null and payTypr != ''\">" +
            " and payTypr = #{payTypr}" +
            "</if>" +
            " and status = 1" +
            " and switchs = 1 " +
            "</script> ")
    AlipayUserRateEntity findRateByUserIdAndType(@Param("agent") String agent, @Param("feeType") Integer feeType, @Param("channel") String channel, @Param("payTypr") String payTypr);

    @Select("select id, userId, fee, feeType, switchs, payTypr from alipay_user_rate where " +
            "userId = #{alipayUserRateEntity.userId}   and payTypr = #{alipayUserRateEntity.payTypr} and status = 1 and channelId = #{alipayUserRateEntity.channelId}")
    AlipayUserRateEntity checkUniqueRate(@Param("alipayUserRateEntity") AlipayUserRateEntity alipayUserRateEntity);

    @Update("update alipay_user_rate set switchs = 0   where " +
            " userId = #{userId} and  id in ( select a.id from  (  " +
            "    select id from alipay_user_rate where userId =  #{userId} and payTypr in " +
            " (    select payTypr from alipay_user_rate where id = #{id}    ) )  a )")
    int updateProduct(@Param("id") String id, @Param("userId") String userId);


    @Select("<script>" +
            "select * from alipay_user_rate where userId IN (select userId from alipay_user_info where " +
            "agent = #{merchantId}" +
            "<if test=\"rate.userId != null and rate.userId != ''\">" +
            " and userId = #{rate.userId} " +
            "</if>" +
            ")   and  switchs = 1 " +
            "<if test=\"rate.payTypr != null and rate.payTypr != ''\">" +
            " and payTypr = #{rate.payTypr} " +
            "</if>" +
            "</script> "
    )
    List<AlipayUserRateEntity> findAgentRateLiat(@Param("merchantId") String merchantId, @Param("rate") AlipayUserRateEntity rate);


    /**
     * 根据账号渠道ID 产品ID 查询费率情况
     *
     * @param userId
     * @param channelId
     * @param payTypr
     * @return
     */
    @Select("select * from alipay_user_rate where userId = #{userId}  and channelId = #{channelId} and payTypr = #{payTypr}")
    AlipayUserRateEntity findFee(@Param("userId") String userId, @Param("channelId") String channelId, @Param("payTypr") String payTypr);

    List<AlipayUserRateEntity> findRates(String[] ids);

    @Select("select * from alipay_user_rate b , " +
            "(select payTypr,retain1,channelId , userId from alipay_user_rate where id = #{id}) a  " +
            "where  b.payTypr = a.payTypr and a.retain1 = b.retain1 and a.userId = b.userId" +
            " and    switchs = 1")
    List<AlipayUserRateEntity> clickPriorityOpen(@Param("id") String id);
}
