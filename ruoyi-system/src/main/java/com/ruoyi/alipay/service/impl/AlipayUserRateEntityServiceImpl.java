package com.ruoyi.alipay.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayUserRateEntityMapper;
import com.ruoyi.alipay.domain.AlipayUserRateEntity;
import com.ruoyi.alipay.service.IAlipayUserRateEntityService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;

/**
 * 用户产品费率Service业务层处理
 * 
 * @author kiwi
 * @date 2020-03-18
 */
@Service
public class AlipayUserRateEntityServiceImpl implements IAlipayUserRateEntityService {
	@Autowired
	private AlipayUserRateEntityMapper alipayUserRateEntityMapper;

	/**
	 * 查询用户产品费率
	 * 
	 * @param id 用户产品费率ID
	 * @return 用户产品费率
	 */
	@Override
	public AlipayUserRateEntity selectAlipayUserRateEntityById(Long id) {
		return alipayUserRateEntityMapper.selectAlipayUserRateEntityById(id);
	}

	/**
	 * 查询用户产品费率列表
	 * 
	 * @param alipayUserRateEntity 用户产品费率
	 * @return 用户产品费率
	 */
	@Override
	@DataSource(value = DataSourceType.ALIPAY_SLAVE)
	public List<AlipayUserRateEntity> selectAlipayUserRateEntityList(AlipayUserRateEntity alipayUserRateEntity) {
		return alipayUserRateEntityMapper.selectAlipayUserRateEntityList(alipayUserRateEntity);
	}

	/**
	 * 新增用户产品费率
	 * 
	 * @param alipayUserRateEntity 用户产品费率
	 * @return 结果
	 */
	@Override
	@DataSource(value = DataSourceType.ALIPAY_SLAVE)
	public int insertAlipayUserRateEntity(AlipayUserRateEntity alipayUserRateEntity) {
		alipayUserRateEntity.setCreateTime(DateUtils.getNowDate());
		return alipayUserRateEntityMapper.insertAlipayUserRateEntity(alipayUserRateEntity);
	}

	/**
	 * 修改用户产品费率
	 * 
	 * @param alipayUserRateEntity 用户产品费率
	 * @return 结果
	 */
	@Override
	public int updateAlipayUserRateEntity(AlipayUserRateEntity alipayUserRateEntity) {
		return alipayUserRateEntityMapper.updateAlipayUserRateEntity(alipayUserRateEntity);
	}

	/**
	 * 删除用户产品费率对象
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteAlipayUserRateEntityByIds(String ids) {
		return alipayUserRateEntityMapper.deleteAlipayUserRateEntityByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除用户产品费率信息
	 * 
	 * @param id 用户产品费率ID
	 * @return 结果
	 */
	@Override
	public int deleteAlipayUserRateEntityById(Long id) {
		return alipayUserRateEntityMapper.deleteAlipayUserRateEntityById(id);
	}
}
