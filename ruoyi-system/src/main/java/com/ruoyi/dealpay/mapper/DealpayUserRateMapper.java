package com.ruoyi.dealpay.mapper;

import com.ruoyi.alipay.domain.AlipayUserInfo;
import com.ruoyi.alipay.domain.AlipayUserRateEntity;
import com.ruoyi.dealpay.domain.DealpayUserRateEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 费率Mapper接口
 *
 * @author kiwi
 * @date 2020-04-03
 */
public interface DealpayUserRateMapper {
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
    List<DealpayUserRateEntity> selectDealpayUserRateEntityList(DealpayUserRateEntity dealpayUserRateEntity);

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
     * 删除费率
     *
     * @param id 费率ID
     * @return 结果
     */
    public int deleteDealpayUserRateEntityById(Long id);

    /**
     * 批量删除费率
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDealpayUserRateEntityByIds(String[] ids);

    @Select("select * from dealpay_user_rate where userId = #{userId}  and feeType = #{feeType} and switchs = 1")
    List<DealpayUserRateEntity> selectRateEntityByUserId(@Param("userId") String userId, @Param("feeType") String feeType);

    @Select("select * from dealpay_user_info where userId = #{userId}")
    DealpayUserRateEntity checkDealpayUserIdUnique(@Param("userId") String userId);

    @Update("update dealpay_user_rate set switchs = #{switchs} where id = #{id}" )
    int updateStatus(@Param("id") String id,@Param("switchs") String switchs);
}
