package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.AdminAplipayDealOrder;
import java.util.List;

/**
 * 交易订单Mapper接口
 * 
 * @author kiwi
 * @date 2020-04-15
 */
public interface AdminAplipayDealOrderMapper 
{
    /**
     * 查询交易订单
     * 
     * @param id 交易订单ID
     * @return 交易订单
     */
    public AdminAplipayDealOrder selectAdminAplipayDealOrderById(Long id);

    /**
     * 查询交易订单列表
     * 
     * @param adminAplipayDealOrder 交易订单
     * @return 交易订单集合
     */
    public List<AdminAplipayDealOrder> selectAdminAplipayDealOrderList(AdminAplipayDealOrder adminAplipayDealOrder);

    /**
     * 新增交易订单
     * 
     * @param adminAplipayDealOrder 交易订单
     * @return 结果
     */
    public int insertAdminAplipayDealOrder(AdminAplipayDealOrder adminAplipayDealOrder);

    /**
     * 修改交易订单
     * 
     * @param adminAplipayDealOrder 交易订单
     * @return 结果
     */
    public int updateAdminAplipayDealOrder(AdminAplipayDealOrder adminAplipayDealOrder);

    /**
     * 删除交易订单
     * 
     * @param id 交易订单ID
     * @return 结果
     */
    public int deleteAdminAplipayDealOrderById(Long id);

    /**
     * 批量删除交易订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAdminAplipayDealOrderByIds(String[] ids);
}
