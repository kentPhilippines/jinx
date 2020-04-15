package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AdminAplipayDealOrderMapper;
import com.ruoyi.system.domain.AdminAplipayDealOrder;
import com.ruoyi.system.service.IAdminAplipayDealOrderService;
import com.ruoyi.common.core.text.Convert;

/**
 * 交易订单Service业务层处理
 * 
 * @author kiwi
 * @date 2020-04-15
 */
@Service
public class AdminAplipayDealOrderServiceImpl implements IAdminAplipayDealOrderService 
{
    @Autowired
    private AdminAplipayDealOrderMapper adminAplipayDealOrderMapper;

    /**
     * 查询交易订单
     * 
     * @param id 交易订单ID
     * @return 交易订单
     */
    @Override
    public AdminAplipayDealOrder selectAdminAplipayDealOrderById(Long id)
    {
        return adminAplipayDealOrderMapper.selectAdminAplipayDealOrderById(id);
    }

    /**
     * 查询交易订单列表
     * 
     * @param adminAplipayDealOrder 交易订单
     * @return 交易订单
     */
    @Override
    public List<AdminAplipayDealOrder> selectAdminAplipayDealOrderList(AdminAplipayDealOrder adminAplipayDealOrder)
    {
        return adminAplipayDealOrderMapper.selectAdminAplipayDealOrderList(adminAplipayDealOrder);
    }

    /**
     * 新增交易订单
     * 
     * @param adminAplipayDealOrder 交易订单
     * @return 结果
     */
    @Override
    public int insertAdminAplipayDealOrder(AdminAplipayDealOrder adminAplipayDealOrder)
    {
        adminAplipayDealOrder.setCreateTime(DateUtils.getNowDate());
        return adminAplipayDealOrderMapper.insertAdminAplipayDealOrder(adminAplipayDealOrder);
    }

    /**
     * 修改交易订单
     * 
     * @param adminAplipayDealOrder 交易订单
     * @return 结果
     */
    @Override
    public int updateAdminAplipayDealOrder(AdminAplipayDealOrder adminAplipayDealOrder)
    {
        return adminAplipayDealOrderMapper.updateAdminAplipayDealOrder(adminAplipayDealOrder);
    }

    /**
     * 删除交易订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAdminAplipayDealOrderByIds(String ids)
    {
        return adminAplipayDealOrderMapper.deleteAdminAplipayDealOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除交易订单信息
     * 
     * @param id 交易订单ID
     * @return 结果
     */
    @Override
    public int deleteAdminAplipayDealOrderById(Long id)
    {
        return adminAplipayDealOrderMapper.deleteAdminAplipayDealOrderById(id);
    }
}
