package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayAmountEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 手动加扣款记录Mapper接口
 *
 * @author kiwi
 * @date 2020-03-24
 */
public interface AlipayAmountEntityMapper {
    /**
     * 查询手动加扣款记录
     *
     * @param id 手动加扣款记录ID
     * @return 手动加扣款记录
     */
    public AlipayAmountEntity selectAlipayAmountEntityById(Long id);

    /**
     * 查询手动加扣款记录列表
     *
     * @param alipayAmountEntity 手动加扣款记录
     * @return 手动加扣款记录集合
     */

    List<AlipayAmountEntity> selectAlipayAmountEntityList(AlipayAmountEntity alipayAmountEntity);

    List<AlipayAmountEntity> selectTransferList(AlipayAmountEntity alipayAmountEntity);

    /**
     * 新增手动加扣款记录
     *
     * @param alipayAmountEntity 手动加扣款记录
     * @return 结果
     */
    int insertAlipayAmountEntity(AlipayAmountEntity alipayAmountEntity);

    /**
     * 修改手动加扣款记录
     *
     * @param alipayAmountEntity 手动加扣款记录
     * @return 结果
     */
    public int updateAlipayAmountEntity(AlipayAmountEntity alipayAmountEntity);

    /**
     * 删除手动加扣款记录
     *
     * @param id 手动加扣款记录ID
     * @return 结果
     */
    public int deleteAlipayAmountEntityById(Long id);

    /**
     * 批量删除手动加扣款记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayAmountEntityByIds(String[] ids);
    @Delete("     delete from alipay_amount  where userId = #{userId} ")
    void deleteUserId(@Param("userId") String userId);


}
