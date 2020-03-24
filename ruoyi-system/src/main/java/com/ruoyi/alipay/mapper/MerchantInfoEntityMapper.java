package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.domain.MerchantInfoEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 商户信息Mapper接口
 * 
 * @author ruoyi
 * @date 2020-03-18
 */
public interface MerchantInfoEntityMapper  {
    /**
     * 查询商户信息
     * 
     * @param id 商户信息ID
     * @return 商户信息
     */
    @Select("select * from alipay_user_info where id = #{id}")
    public AlipayUserInfo selectMerchantInfoEntityById( @Param("id") Long id);

    /**
     * 查询商户信息列表
     * 
     * @param merchantInfoEntity 商户信息
     * @return 商户信息集合
     */
    @Select("<script>" +
            "SELECT " +
            " id,userId, userName, `password`, payPasword, salt, userType, switchs," +
            " userNode, email, agent, isAgent, credit, receiveOrderState, remitOrderState," +
            " QQ, telegram, skype, createTime, submitTime, `status`, privateKey, publicKey " +
            " FROM " +
            " alipay_user_info" +
            " where userType = 1 " +
            "<if test=\"userId != null and userId != ''\">" +
            " and userId = #{userId}" +
            "</if>" +
            "<if test=\"agent != null and agent != ''\">" +
            " and agent = #{agent}" +
            "</if>" +
            "<if test=\"switchs != null and switchs != ''\">" +
            " and switchs = #{switchs}" +
            "</if>" +
            "<if test=\"params.beginTime != null and params.beginTime != ''\">" +
            " and date_format(u.createTime,'%y%m%d') &gt;= date_format(#{params.beginTime},'%y%m%d')" +
            "</if>" +
            "<if test=\"params.endTime != null and params.endTime != ''\">" +
            " and date_format(u.createTime,'%y%m%d') &lt;= date_format(#{params.endTime},'%y%m%d')" +
            "</if>" +
            " order by switchs desc, createTime desc " +
            "</script>")
    public List<AlipayUserInfo> selectMerchantInfoEntityList(AlipayUserInfo merchantInfoEntity);

    /**
     * 新增商户信息
     * 
     * @param merchantInfoEntity 商户信息
     * @return 结果
     */

    @Insert("insert into alipay_user_info (userId,userName,password,salt,payPasword,userType,email, " +
            "QQ,telegram,skype,privateKey,publicKey,agent,isAgent,userNode) values " +
            "(#{userId},#{userName},#{password},#{salt},#{payPasword},#{userType},#{email}," +
            "#{QQ},#{telegram},#{skype},#{privateKey},#{publicKey},#{agent},#{isAgent},#{userNode})")
    public int insertMerchantInfoEntity(AlipayUserInfo merchantInfoEntity);

    /**
     * 修改商户信息
     * 
     * @param merchantInfoEntity 商户信息
     * @return 结果
     */
    @Update("update alipay_user_info set " +
            "minAmount = #{minAmount}," +
            "maxAmount = #{maxAmount}," +
            "totalAmount = #{totalAmount}," +
            "startTime = #{startTime}," +
            "endTime = #{endTime}," +
            "witip = #{witip}," +
            "timesTotal = #{timesTotal}," +
            "submitTime = #{submitTime} " +
            "where id = #{id} ")
    int updateMerchantInfoEntity(AlipayUserInfo merchantInfoEntity);

    /**
     * 删除商户信息
     * 
     * @param id 商户信息ID
     * @return 结果
     */
    public int deleteMerchantInfoEntityById(Long id);

    /**
     * 批量删除商户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMerchantInfoEntityByIds(String[] ids);



    /*下面是处理风控模块的逻辑*/

    /**
     *  查询商户风控列表信息
     * @param merchantInfoEntity
     * @return
     */
    @Select("<script>" +
            "select " +
            " id,userId, userName, userType, switchs, agent, minAmount, maxAmount,totalAmount, timesTotal, startTime, endTime, witip, submitTime " +
            " from " +
            " alipay_user_info" +
            " where userType = 1 " +
            "<if test=\"userId != null and userId != ''\">" +
            " and userId = #{userId}" +
            "</if>" +
            "<if test=\"userName != null and userName != ''\">" +
            " and userName = #{userName}" +
            "</if>" +
            "<if test=\"switchs != null and switchs != ''\">" +
            " and switchs = #{switchs}" +
            "</if>" +
            "<if test=\"params.beginTime != null and params.beginTime != ''\">" +
            " and date_format(u.submitTime,'%y%m%d') &gt;= date_format(#{params.beginTime},'%y%dm%d')" +
            "</if>" +
            "<if test=\"params.endTime != null and params.endTime != ''\">" +
            " and date_format(u.submitTime,'%y%m%d') &lt;= date_format(#{params.endTime},'%y%m%d')" +
            "</if>" +
            " order by switchs desc, submitTime desc " +
            "</script>")
    List<AlipayUserInfo> selectMerchantControlList(AlipayUserInfo merchantInfoEntity);
}
