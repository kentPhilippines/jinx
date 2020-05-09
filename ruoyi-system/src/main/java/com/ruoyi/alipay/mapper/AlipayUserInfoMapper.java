package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayUserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 用户详情Mapper接口
 *
 * @author ruoyi
 * @date 2020-02-27
 */
public interface AlipayUserInfoMapper {

    /**
     * 查询用户详情列表
     *
     * @param alipayUserInfo 用户详情
     * @return 用户详情集合
     */
    @Select("<script>" +
            "SELECT " +
            " id,userId, userName, `password`, payPasword, salt, userType, switchs," +
            " userNode, email, agent, isAgent, credit, receiveOrderState, remitOrderState," +
            " QQ, telegram, skype, createTime, submitTime, `status`, privateKey, publicKey   " +
            " FROM " +
            " alipay_user_info" +
            " where userType = 2 " +
            "<if test=\"userId != null and userId != ''\">" +
            " and userId = #{userId}" +
            "</if>" +
            "<if test=\"agent != null and agent != ''\">" +
            " and agent = #{agent}" +
            "</if>" +
            "<if test=\"switchs == 0 and switchs ==1\">" +
            " and switchs = #{switchs}" +
            "</if>" +
            "<if test=\"params.beginTime != null and params.beginTime != ''\">" +
            " and date_format(u.create_time,'%y%m%d') &gt;= date_format(#{params.beginTime},'%y%m%d')" +
            "</if>" +
            "<if test=\"params.endTime != null and params.endTime != ''\">" +
            " and date_format(u.create_time,'%y%m%d') &lt;= date_format(#{params.endTime},'%y%m%d')" +
            "</if>" +
            " order by switchs desc, createTime desc " +
            "</script>")
    List<AlipayUserInfo> selectAliaUserInfoList(AlipayUserInfo alipayUserInfo);

    /**
     * 新增用户详情
     *
     * @param alipayUserInfo 用户详情
     * @return 结果
     */
    public int insertAlipayUserInfo(AlipayUserInfo alipayUserInfo);

    /**
     * 修改用户详情
     *
     * @param alipayUserInfo 用户详情
     * @return 结果
     */
    int updateAlipayUserInfo(AlipayUserInfo alipayUserInfo);

    /**
     * 删除用户详情
     *
     * @param id 用户详情ID
     * @return 结果
     */
    public int deleteAlipayUserInfoById(Long id);

    /**
     * 批量删除用户详情
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayUserInfoByIds(String[] ids);

    /**
     * 根据userId查询用户
     *
     * @param userId
     * @return 结果
     */
    @Select("select id, userId, userName, password, userType, switchs, userNode,agent, isAgent, credit, remitOrderState from alipay_user_info where userId = #{userId}")
    AlipayUserInfo checkAlipayUserIdUnique(@Param("userId") String userId);

    /**
     * 重置登陆密码
     *
     * @param alipayUserInfo
     * @return
     */
    @Update("update alipay_user_info set password = #{password} where id = #{id}")
    int updateUserLoginPwd(AlipayUserInfo alipayUserInfo);

    /**
     * 重置提现密码
     *
     * @param alipayUserInfo
     * @return
     */
    @Update("update alipay_user_info set payPasword = #{payPasword} where id = #{id}")
    int updateWithdrawalPwd(AlipayUserInfo alipayUserInfo);

    @Select("select id, userId, userName, password, userType, switchs, userNode,isAgent, credit, remitOrderState, privateKey, publicKey from alipay_user_info where userId = #{userId} ")
    AlipayUserInfo selectMerhantInfoByUserId(@Param("userId") String userId);


    @Select("<script>" +
            "select id, userId, userName, switchs, qrRechargeList,queueList,submitTime, dealUrl from alipay_user_info where agent is null and status = 1 " +
            "<if test=\"userType == 1 or userType == 2\">" +
            " and userType = #{userType}" +
            "</if>" +
            "<if test=\"userId != null and userId != ''\">" +
            " and userId = #{userId}" +
            "</if>" +
            "<if test=\"agent != null and agent != ''\">" +
            " and agent = #{agent}" +
            "</if>" +
            "<if test=\"switchs == 0 and switchs ==1\">" +
            " and switchs = #{switchs}" +
            "</if>" +
            "<if test=\"params.beginTime != null and params.beginTime != ''\">" +
            " and date_format(u.create_time,'%y%m%d') &gt;= date_format(#{params.beginTime},'%y%m%d')" +
            "</if>" +
            "<if test=\"params.endTime != null and params.endTime != ''\">" +
            " and date_format(u.create_time,'%y%m%d') &lt;= date_format(#{params.endTime},'%y%m%d')" +
            "</if>" +
            " order by switchs desc, createTime desc " +
            "</script>")
    List<AlipayUserInfo> selectAlipayUserInfoListByControl(AlipayUserInfo alipayUserInfo);

    AlipayUserInfo selectAlipayUserInfoById(Long id);

    @Select("select * from alipay_user_info  where agent is null")
    List<AlipayUserInfo> selectdealpayUserInfoByAgent();

    @Update("update alipay_user_info set qrRechargeList = #{qrRechargeList}, queueList = #{queueList}, submitTime = sysdate() where id = #{id}")
    int updateAlipayUserInfo2Control(AlipayUserInfo alipayUserInfo);
}
