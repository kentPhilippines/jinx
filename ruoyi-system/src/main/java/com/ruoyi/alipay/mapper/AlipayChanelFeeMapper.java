package com.ruoyi.alipay.mapper;

import com.ruoyi.alipay.domain.AlipayChanelFee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 渠道费率Mapper接口
 *
 * @author ruoyi
 * @date 2020-05-15
 */
@Mapper
public interface AlipayChanelFeeMapper {
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
     * 删除渠道费率
     *
     * @param id 渠道费率ID
     * @return 结果
	 */
	public int deleteAlipayChanelFeeById(Long id);

    /**
     * 批量删除渠道费率
     *
     * @param ids 需要删除的数据ID
     * @return 结果
	 */
	public int deleteAlipayChanelFeeByIds(String[] ids);
	public AlipayChanelFee findChannelBy(@Param("channelId") String channelId, @Param("payTypr") String payTypr);
}
