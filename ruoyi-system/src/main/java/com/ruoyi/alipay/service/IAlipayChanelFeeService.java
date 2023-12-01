package com.ruoyi.alipay.service;

import com.ruoyi.alipay.domain.AlipayChanelFee;
import com.ruoyi.alipay.domain.AlipayProductEntity;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;

import java.util.List;

/**
 * 渠道费率Service接口
 * 
 * @author ruoyi
 * @date 2020-05-15
 */
public interface IAlipayChanelFeeService 
{
    /**
     * 查询渠道费率
     * 
     * @param id 渠道费率ID
     * @return 渠道费率
     */
    public AlipayChanelFee selectAlipayChanelFeeById(Long id);

    /**
     * 查询渠道费率列表
     * 
     * @param alipayChanelFee 渠道费率
     * @return 渠道费率集合
     */
    public List<AlipayChanelFee> selectAlipayChanelFeeList(AlipayChanelFee alipayChanelFee);

    /**
     * 新增渠道费率
     * 
     * @param alipayChanelFee 渠道费率
     * @return 结果
     */
    public int insertAlipayChanelFee(AlipayChanelFee alipayChanelFee);

    /**
     * 修改渠道费率
     * 
     * @param alipayChanelFee 渠道费率
     * @return 结果
     */
    public int updateAlipayChanelFee(AlipayChanelFee alipayChanelFee);

    /**
     * 批量删除渠道费率
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAlipayChanelFeeByIds(String ids);

    /**
     * 删除渠道费率信息
     *
     * @param id 渠道费率ID
     * @return 结果
     */
    public int deleteAlipayChanelFeeById(Long id);


    public AlipayChanelFee findChannelBy(String channelId, String payTypr);

    List<AlipayProductEntity> findProductByName(String merchantId);
    void deleteChannelByChannel(String userId);

}
