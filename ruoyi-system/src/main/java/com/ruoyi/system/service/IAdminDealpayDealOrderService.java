package com.ruoyi.system.service;

import com.ruoyi.system.domain.AdminDealpayDealOrder;
import java.util.List;

/**
 * 后台交易订单Service接口
 * 
 * @author kiwi
 * @date 2020-04-15
 */
public interface IAdminDealpayDealOrderService 
{
    /**
     * 查询后台交易订单
     * 
     * @param id 后台交易订单ID
     * @return 后台交易订单
     */
    public AdminDealpayDealOrder selectAdminDealpayDealOrderById(Long id);

    /**
     * 查询后台交易订单列表
     * 
     * @param adminDealpayDealOrder 后台交易订单
     * @return 后台交易订单集合
     */
    public List<AdminDealpayDealOrder> selectAdminDealpayDealOrderList(AdminDealpayDealOrder adminDealpayDealOrder);

    /**
     * 新增后台交易订单
     * 
     * @param adminDealpayDealOrder 后台交易订单
     * @return 结果
     */
    public int insertAdminDealpayDealOrder(AdminDealpayDealOrder adminDealpayDealOrder);

    /**
     * 修改后台交易订单
     * 
     * @param adminDealpayDealOrder 后台交易订单
     * @return 结果
     */
    public int updateAdminDealpayDealOrder(AdminDealpayDealOrder adminDealpayDealOrder);

    /**
     * 批量删除后台交易订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAdminDealpayDealOrderByIds(String ids);

    /**
     * 删除后台交易订单信息
     * 
     * @param id 后台交易订单ID
     * @return 结果
     */
    public int deleteAdminDealpayDealOrderById(Long id);
}
