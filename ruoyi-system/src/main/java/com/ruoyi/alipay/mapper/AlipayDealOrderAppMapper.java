package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayDealOrderApp;
import java.util.List;

/**
 * 商户订单登记Mapper接口
 * 
 * @author kiwi
 * @date 2020-03-17
 */
public interface AlipayDealOrderAppMapper 
{
    /**
     * 查询商户订单登记
     * 
     * @param id 商户订单登记ID
     * @return 商户订单登记
     */
    public AlipayDealOrderApp selectAlipayDealOrderAppById(Long id);

    /**
     * 查询商户订单登记列表
     * 
     * @param alipayDealOrderApp 商户订单登记
     * @return 商户订单登记集合
     */
    public List<AlipayDealOrderApp> selectAlipayDealOrderAppList(AlipayDealOrderApp alipayDealOrderApp);

    /**
     * 新增商户订单登记
     * 
     * @param alipayDealOrderApp 商户订单登记
     * @return 结果
     */
    public int insertAlipayDealOrderApp(AlipayDealOrderApp alipayDealOrderApp);

    /**
     * 修改商户订单登记
     * 
     * @param alipayDealOrderApp 商户订单登记
     * @return 结果
     */
    public int updateAlipayDealOrderApp(AlipayDealOrderApp alipayDealOrderApp);

    /**
     * 删除商户订单登记
     * 
     * @param id 商户订单登记ID
     * @return 结果
     */
    public int deleteAlipayDealOrderAppById(Long id);

    /**
     * 批量删除商户订单登记
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayDealOrderAppByIds(String[] ids);
}
