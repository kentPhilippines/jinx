package com.ruoyi.alipay.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayProductMapper;
import com.ruoyi.alipay.domain.AlipayProduct;
import com.ruoyi.alipay.service.IAlipayProductService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户产品Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-02-27
 */
@Service
public class AlipayProductServiceImpl implements IAlipayProductService 
{
    @Autowired
    private AlipayProductMapper alipayProductMapper;

    /**
     * 查询用户产品
     * 
     * @param id 用户产品ID
     * @return 用户产品
     */
    @Override
    public AlipayProduct selectAlipayProductById(Long id)
    {
        return alipayProductMapper.selectAlipayProductById(id);
    }

    /**
     * 查询用户产品列表
     * 
     * @param alipayProduct 用户产品
     * @return 用户产品
     */
    @Override
    public List<AlipayProduct> selectAlipayProductList(AlipayProduct alipayProduct)
    {
        return alipayProductMapper.selectAlipayProductList(alipayProduct);
    }

    /**
     * 新增用户产品
     * 
     * @param alipayProduct 用户产品
     * @return 结果
     */
    @Override
    public int insertAlipayProduct(AlipayProduct alipayProduct)
    {
        return alipayProductMapper.insertAlipayProduct(alipayProduct);
    }

    /**
     * 修改用户产品
     * 
     * @param alipayProduct 用户产品
     * @return 结果
     */
    @Override
    public int updateAlipayProduct(AlipayProduct alipayProduct)
    {
        return alipayProductMapper.updateAlipayProduct(alipayProduct);
    }

    /**
     * 删除用户产品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAlipayProductByIds(String ids)
    {
        return alipayProductMapper.deleteAlipayProductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户产品信息
     * 
     * @param id 用户产品ID
     * @return 结果
     */
    @Override
    public int deleteAlipayProductById(Long id)
    {
        return alipayProductMapper.deleteAlipayProductById(id);
    }
}
