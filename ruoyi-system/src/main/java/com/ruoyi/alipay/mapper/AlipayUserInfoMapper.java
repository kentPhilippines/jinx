package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayUserInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户详情Mapper接口
 *
 * @author ruoyi
 * @date 2020-02-27
 */
public interface AlipayUserInfoMapper {
    /**
     * 查询用户详情
     *
     * @param id 用户详情ID
     * @return 用户详情
     */
    public AlipayUserInfo selectAlipayUserInfoById(Long id);

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
            " QQ, telegram, skype, createTime, submitTime, `status`, retain1, retain2, retain3, retain4 " +
            " FROM " +
            " alipay_user_info" +
            " where userType = 2 and createTime between #{params.beginCreatetime} and #{params.endCreatetime}" +
            "<if test=\"userId != null and userId != ''\">" +
            " and userId = #{userId}" +
            "</if>" +
            "<if test=\"agent != null and agent != ''\">" +
            " and agent = #{agent}" +
            "</if>" +
            "<if test=\"switchs != null and switchs != ''\">" +
            " and switchs = #{switchs}" +
            "</if>" +
            " order by switchs desc, createTime desc " +
            "</script>")
    List<AlipayUserInfo> selectAlipayUserInfoList(AlipayUserInfo alipayUserInfo);

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
    public int updateAlipayUserInfo(AlipayUserInfo alipayUserInfo);

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
}
