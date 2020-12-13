package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayUserFundEntity;
import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 用户资金账户Mapper接口
 *
 * @author kiwi
 * @date 2020-03-17
 */
public interface AlipayUserFundEntityMapper {
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

    @Insert("insert into alipay_user_fund (userId,userName,userType,isAgent) values(#{userId},#{userName},#{userType},#{isAgent})")
    int insertAlipayUserFundInfo(AlipayUserInfo merchantInfoEntity);

    @Select("select cashBalance, accountBalance, rechargeNumber  ,freezeBalance from alipay_user_fund where userId = #{merchantId}")
    AlipayUserFundEntity selectAlipayUserFundByUserId(@Param("merchantId") String merchantId);

    List<AlipayUserFundEntity> findChannelAccount(AlipayUserFundEntity alipayUserFundEntity);

    @Select("select userId  ,userName, accountBalance, rechargeNumber , freezeBalance ,userType from alipay_user_fund  ")
    List<AlipayUserFundEntity> findUserFundAll();

    @Select("select * from alipay_user_fund where userType = 3 and status = 1 ")
    List<AlipayUserFundEntity> findUserFundRate();


    @Select("SELECT * FROM `alipay_user_fund_bak` WHERE userId   IN (SELECT userId FROM alipay_user_info WHERE agent =  #{merchantId})  " +
            "AND createTime > #{baseEntity.params.dayStart}" +
            " AND createTime < #{baseEntity.params.dayEnd}  order by createTime ")
    List<AlipayUserFundEntity> findUserBakBy(@Param("merchantId") String merchantId, @Param("baseEntity") BaseEntity baseEntity);


    @Select("SELECT * FROM `alipay_user_fund_bak` where userId = #{merchantId}" +
            "AND createTime > #{baseEntity.params.dayStart}" +
            " AND createTime < #{baseEntity.params.dayEnd}  order by createTime ")
    List<AlipayUserFundEntity> findMyUserBak(@Param("merchantId") String merchantId, @Param("baseEntity") BaseEntity baseEntity);


    @Select("select * from alipay_user_fund_bak where userType = 1 " +
            "AND createTime > #{baseEntity.params.dayStart}" +
            " AND createTime < #{baseEntity.params.dayEnd}  order by createTime ")
    List<AlipayUserFundEntity> findUserAppAll(@Param("baseEntity") BaseEntity baseEntity);


    @Select("select '所有' as userId , '下游商户总余额' as userName ," +
            " sum(freezeBalance) as freezeBalance , " +
            "sum(accountBalance) as accountBalance ," +
            "sum(quota) as quota , " +
            "sum(todayDealAmount) , " +
            "sum(todayAgentProfit) " +
            " from " +
            "alipay_user_fund where userType = 1 ")
    AlipayUserFundEntity findSumFundM();


    @Select("select * from alipay_user_info where agent = #{agentUserId}")
    List<AlipayUserInfo> findUserByAgent(@Param("agentUserId") String agentUserId);

    @Update("update alipay_user_fund set status = #{status} where userId = #{userId} ")
    int updateStatus(@Param("userId") String userId, @Param("status") Integer status);
}
