package com.ruoyi.dealpay.mapper;

import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.dealpay.domain.DealpayUserInfoEntity;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 用户详情Mapper接口
 *
 * @author kiwi
 * @date 2020-04-03
 */
public interface DealpayUserInfoMapper {
    /**
     * 查询用户详情
     *
     * @param id 用户详情ID
     * @return 用户详情
     */
    DealpayUserInfoEntity selectDealpayUserInfoById(Long id);

    /**
     * 查询用户详情列表
     *
     * @param dealpayUserInfo 用户详情
     * @return 用户详情集合
     */
    List<DealpayUserInfoEntity> selectDealpayUserInfoList(DealpayUserInfoEntity dealpayUserInfo);

    /**
     * 新增用户详情
     *
     * @param dealpayUserInfo 用户详情
     * @return 结果
     */
  //  int insertDealpayUserInfo(DealpayUserInfoEntity dealpayUserInfo);

    /**
     * 修改用户详情
     *
     * @param dealpayUserInfo 用户详情
     * @return 结果
     */
 //   int updateDealpayUserInfo(DealpayUserInfoEntity dealpayUserInfo);

    /**
     * 删除用户详情
     *
     * @param id 用户详情ID
     * @return 结果
     */
  //  int deleteDealpayUserInfoById(Long id);

    /**
     * 批量删除用户详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
  //  int deleteDealpayUserInfoByIds(String[] ids);
    /**
     * 根据userId查询用户
     *
     * @param userId
     * @return 结果
     */
    @Select("select id, userId, userName, password, userType, switchs, userNode,isAgent, credit, remitOrderState from dealpay_user_info where userId = #{userId}")
    DealpayUserInfoEntity checkDealpayUserIdUnique(String userId);
    /**
     * 查询用户详情
     *
     * @param id 用户详情ID
     * @return 用户详情
     */
    @Select("select * from alipay_user_info where id = #{id}")
    DealpayUserInfoEntity selectAliasUserInfoById(Long id);
    /**
     * 重置登陆密码
     * @param dealpayUserInfoEntity
     * @return
     */
    @Update("update alipay_user_info set password = #{password} where id = #{id}")
    int updateUserLoginPwd(DealpayUserInfoEntity dealpayUserInfoEntity);
    /**
     * 重置提现密码
     * @param dealpayUserInfoEntity
     * @return
     */
    @Update("update alipay_user_info set payPasword = #{payPasword} where id = #{id}")
    int updateWithdrawalPwd(DealpayUserInfoEntity dealpayUserInfoEntity);

    /**
     *
     * @param dealpayUserInfoEntity
     * @return
     */
    @Select("select id, userId, userName,  userType, switchs, userNode, submitTime from dealpay_user_info " +
            "where agent is null and isAgent = 1 and status = 1 ")
    List<DealpayUserInfoEntity> selectdealpayUserInfoByAgent(DealpayUserInfoEntity dealpayUserInfoEntity);
}
