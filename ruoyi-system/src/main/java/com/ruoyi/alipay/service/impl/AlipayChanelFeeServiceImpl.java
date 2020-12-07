package com.ruoyi.alipay.service.impl;

import com.ruoyi.alipay.domain.AlipayChanelFee;
import com.ruoyi.alipay.domain.AlipayProductEntity;
import com.ruoyi.alipay.mapper.AlipayChanelFeeMapper;
import com.ruoyi.alipay.service.IAlipayChanelFeeService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 渠道费率Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-15
 */
@Service
public class AlipayChanelFeeServiceImpl implements IAlipayChanelFeeService {
	@Autowired
	private AlipayChanelFeeMapper alipayChanelFeeMapper;

	/**
	 * 查询渠道费率
	 * 
	 * @param id 渠道费率ID
	 * @return 渠道费率
	 */
	@Override
	@DataSource(DataSourceType.ALIPAY_SLAVE)
	public AlipayChanelFee selectAlipayChanelFeeById(Long id) {
		return alipayChanelFeeMapper.selectAlipayChanelFeeById(id);
	}

	/**
	 * 查询渠道费率列表
	 * 
	 * @param alipayChanelFee 渠道费率
	 * @return 渠道费率
	 */
	@Override
	@DataSource(DataSourceType.ALIPAY_SLAVE)
	public List<AlipayChanelFee> selectAlipayChanelFeeList(AlipayChanelFee alipayChanelFee) {
		return alipayChanelFeeMapper.selectAlipayChanelFeeList(alipayChanelFee);
	}

	/**
	 * 新增渠道费率
	 * 
	 * @param alipayChanelFee 渠道费率
	 * @return 结果
	 */
	@Override
	@DataSource(DataSourceType.ALIPAY_SLAVE)
	public int insertAlipayChanelFee(AlipayChanelFee alipayChanelFee) {
		alipayChanelFee.setCreateTime(DateUtils.getNowDate());
		return alipayChanelFeeMapper.insertAlipayChanelFee(alipayChanelFee);
	}

	/**
	 * 修改渠道费率
	 * 
	 * @param alipayChanelFee 渠道费率
	 * @return 结果
	 */
	@Override
	@DataSource(DataSourceType.ALIPAY_SLAVE)
	public int updateAlipayChanelFee(AlipayChanelFee alipayChanelFee) {
		return alipayChanelFeeMapper.updateAlipayChanelFee(alipayChanelFee);
	}

	/**
	 * 删除渠道费率对象
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	@DataSource(DataSourceType.ALIPAY_SLAVE)
	public int deleteAlipayChanelFeeByIds(String ids) {
		return alipayChanelFeeMapper.deleteAlipayChanelFeeByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除渠道费率信息
	 * 
	 * @param id 渠道费率ID
	 * @return 结果
	 */
	@Override
	@DataSource(DataSourceType.ALIPAY_SLAVE)
	public int deleteAlipayChanelFeeById(Long id) {
		return alipayChanelFeeMapper.deleteAlipayChanelFeeById(id);
	}

	@Override
	@DataSource(DataSourceType.ALIPAY_SLAVE)
	public AlipayChanelFee findChannelBy(String channelId, String payTypr) {
		return alipayChanelFeeMapper.findChannelBy(channelId, payTypr);
	}

	@Override
	@DataSource(DataSourceType.ALIPAY_SLAVE)
	public List<AlipayProductEntity> findProductByName(String merchantId) {
		return alipayChanelFeeMapper.findProductByName(merchantId);
	}
}
