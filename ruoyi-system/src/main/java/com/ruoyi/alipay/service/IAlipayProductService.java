package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayProductEntity;

import java.util.List;

public interface IAlipayProductService {
    /**
     * <p>查询产品列表</p>
     * @param alipayProductEntity
     * @return
     */
    List<AlipayProductEntity> selectAlipayProductList(AlipayProductEntity alipayProductEntity);

    /**
     * <p>新增产品列表</p>
     * @param alipayProductEntity
     * @return
     */
    int insertAlipayProductListEntity(AlipayProductEntity alipayProductEntity);

    /**
     *  <p>通过ID查询产品列表</p>
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
     * <p>批量删除产品列表</p>
     * @param ids
     * @return
     */
    int deleteAlipayProductListEntityByIds(String ids);

}
