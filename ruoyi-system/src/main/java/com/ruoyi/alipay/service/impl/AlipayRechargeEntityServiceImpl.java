package com.ruoyi.alipay.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayRechargeEntityMapper;
import com.ruoyi.alipay.domain.AlipayRechargeEntity;
import com.ruoyi.alipay.service.IAlipayRechargeEntityService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;

/**
 * 充值记录Service业务层处理
 * 
 * @author kiwi
 * @date 2020-03-17
 */
@Service
public class AlipayRechargeEntityServiceImpl implements IAlipayRechargeEntityService {
	@Autowired
	private AlipayRechargeEntityMapper alipayRechargeEntityMapper;

	/**
	 * 查询充值记录
	 * 
	 * @param id 充值记录ID
	 * @return 充值记录
	 */
	@Override
	public AlipayRechargeEntity selectAlipayRechargeEntityById(Long id) {
		return alipayRechargeEntityMapper.selectAlipayRechargeEntityById(id);
	}

	/**
	 * 查询充值记录列表
	 * 
	 * @param alipayRechargeEntity 充值记录
	 * @return 充值记录
	 */
	@Override
	@DataSource(value = DataSourceType.ALIPAY_SLAVE)
	public List<AlipayRechargeEntity> selectAlipayRechargeEntityList(AlipayRechargeEntity alipayRechargeEntity) {
		return alipayRechargeEntityMapper.selectAlipayRechargeEntityList(alipayRechargeEntity);
	}

	/**
	 * 新增充值记录
	 * @param alipayRechargeEntity 充值记录
	 * @return 结果
	 */
	@Override
	public int insertAlipayRechargeEntity(AlipayRechargeEntity alipayRechargeEntity) {
		alipayRechargeEntity.setCreateTime(DateUtils.getNowDate());
		return alipayRechargeEntityMapper.insertAlipayRechargeEntity(alipayRechargeEntity);
	}

	/**
	 * 修改充值记录
	 * 
	 * @param alipayRechargeEntity 充值记录
	 * @return 结果
	 */
	@Override
	public int updateAlipayRechargeEntity(AlipayRechargeEntity alipayRechargeEntity) {
		return alipayRechargeEntityMapper.updateAlipayRechargeEntity(alipayRechargeEntity);
	}

	/**
	 * 删除充值记录对象
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteAlipayRechargeEntityByIds(String ids) {
		return alipayRechargeEntityMapper.deleteAlipayRechargeEntityByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除充值记录信息
	 * 
	 * @param id 充值记录ID
	 * @return 结果
	 */
	@Override
	public int deleteAlipayRechargeEntityById(Long id) {
		return alipayRechargeEntityMapper.deleteAlipayRechargeEntityById(id);
	}
}
