package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayProductEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 产品列表Mapper接口
 */
@Mapper
public interface AlipayProductMapper {
    /**
     * <p>查询产品列表</p>
     * @param alipayProductEntity
     * @return
     */
    List<AlipayProductEntity> selectAlipayProductListEntityList(AlipayProductEntity alipayProductEntity);

    /**
     * <p>新增产品列表</p>
     * @param alipayProductEntity
     * @return
     */
    int insertAlipayProductListEntity(AlipayProductEntity alipayProductEntity);

    /**
     * <p>通过ID查询产品列表</p>
     * @param id
     * @return
     */
    AlipayProductEntity selectAlipayProductListEntityById(Long id);

    /**
     * <p>修改产品列表</p>
     * @param alipayProductEntity
     * @return
     */
    int updateAlipayProductListEntity(AlipayProductEntity alipayProductEntity);

    /**
     * <p> 批量删除产品列表</p>
     * @param toStrArray
     * @return
     */
    int deleteAlipayProductListEntityByIds(String[] toStrArray);

    /**
     *  前台回显
     * @return
     */
    @Select("select id,productId,productCode,productName,status from alipay_product where status = 1")
    List<AlipayProductEntity> selectAlipayProductTypeListToWeb();
    /**
     * <p>根据Id更新产品状态</p>
     * @param alipayProductEntity
     * @return
     */
    @Update("update alipay_product set status = #{status} where id = #{id} ")
    int updateAlipayProductById(AlipayProductEntity alipayProductEntity);
}
