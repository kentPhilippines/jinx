package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayProduct;
import java.util.List;

/**
 * 用户产品Service接口
 * 
 * @author ruoyi
 * @date 2020-02-27
 */
public interface IAlipayProductService 
{
    /**
     * 查询用户产品
     * 
     * @param id 用户产品ID
     * @return 用户产品
     */
    public AlipayProduct selectAlipayProductById(Long id);

    /**
     * 查询用户产品列表
     * 
     * @param alipayProduct 用户产品
     * @return 用户产品集合
     */
    public List<AlipayProduct> selectAlipayProductList(AlipayProduct alipayProduct);

    /**
     * 新增用户产品
     * 
     * @param alipayProduct 用户产品
     * @return 结果
     */
    public int insertAlipayProduct(AlipayProduct alipayProduct);

    /**
     * 修改用户产品
     * 
     * @param alipayProduct 用户产品
     * @return 结果
     */
    public int updateAlipayProduct(AlipayProduct alipayProduct);

    /**
     * 批量删除用户产品
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayProductByIds(String ids);

    /**
     * 删除用户产品信息
     * 
     * @param id 用户产品ID
     * @return 结果
     */
    public int deleteAlipayProductById(Long id);
}
