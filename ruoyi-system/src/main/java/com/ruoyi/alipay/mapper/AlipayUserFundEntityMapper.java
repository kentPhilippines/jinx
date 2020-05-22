package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayUserFundEntity;
import com.ruoyi.alipay.domain.AlipayUserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.PostMapping;

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

    @Select("select cashBalance, accountBalance, rechargeNumber  from alipay_user_fund where userId = #{merchantId}")
    AlipayUserFundEntity selectAlipayUserFundByUserId(@Param("merchantId") String merchantId);

    List<AlipayUserFundEntity> findChannelAccount(AlipayUserFundEntity alipayUserFundEntity);

    @Select("select userId  , accountBalance, rechargeNumber  from alipay_user_fund  ")
    List<AlipayUserFundEntity> findUserFundAll();

    @Select("select * from alipay_user_fund where userType = 3")
    List<AlipayUserFundEntity> findUserFundRate();
}