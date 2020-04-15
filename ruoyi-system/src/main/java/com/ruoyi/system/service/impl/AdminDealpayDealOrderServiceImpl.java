package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AdminDealpayDealOrderMapper;
import com.ruoyi.system.domain.AdminDealpayDealOrder;
import com.ruoyi.system.service.IAdminDealpayDealOrderService;
import com.ruoyi.common.core.text.Convert;

/**
 * 后台交易订单Service业务层处理
 * 
 * @author kiwi
 * @date 2020-04-15
 */
@Service
public class AdminDealpayDealOrderServiceImpl implements IAdminDealpayDealOrderService 
{
    @Autowired
    private AdminDealpayDealOrderMapper adminDealpayDealOrderMapper;

    /**
     * 查询后台交易订单
     * 
     * @param id 后台交易订单ID
     * @return 后台交易订单
     */
    @Override
    public AdminDealpayDealOrder selectAdminDealpayDealOrderById(Long id)
    {
        return adminDealpayDealOrderMapper.selectAdminDealpayDealOrderById(id);
    }

    /**
     * 查询后台交易订单列表
     * 
     * @param adminDealpayDealOrder 后台交易订单
     * @return 后台交易订单
     */
    @Override
    public List<AdminDealpayDealOrder> selectAdminDealpayDealOrderList(AdminDealpayDealOrder adminDealpayDealOrder)
    {
        return adminDealpayDealOrderMapper.selectAdminDealpayDealOrderList(adminDealpayDealOrder);
    }

    /**
     * 新增后台交易订单
     * 
     * @param adminDealpayDealOrder 后台交易订单
     * @return 结果
     */
    @Override
    public int insertAdminDealpayDealOrder(AdminDealpayDealOrder adminDealpayDealOrder)
    {
        adminDealpayDealOrder.setCreateTime(DateUtils.getNowDate());
        return adminDealpayDealOrderMapper.insertAdminDealpayDealOrder(adminDealpayDealOrder);
    }

    /**
     * 修改后台交易订单
     * 
     * @param adminDealpayDealOrder 后台交易订单
     * @return 结果
     */
    @Override
    public int updateAdminDealpayDealOrder(AdminDealpayDealOrder adminDealpayDealOrder)
    {
        return adminDealpayDealOrderMapper.updateAdminDealpayDealOrder(adminDealpayDealOrder);
    }

    /**
     * 删除后台交易订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAdminDealpayDealOrderByIds(String ids)
    {
        return adminDealpayDealOrderMapper.deleteAdminDealpayDealOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除后台交易订单信息
     * 
     * @param id 后台交易订单ID
     * @return 结果
     */
    @Override
    public int deleteAdminDealpayDealOrderById(Long id)
    {
        return adminDealpayDealOrderMapper.deleteAdminDealpayDealOrderById(id);
    }
}
