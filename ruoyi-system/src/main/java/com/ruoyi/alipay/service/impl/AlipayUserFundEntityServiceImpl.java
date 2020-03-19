package com.ruoyi.alipay.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.alipay.mapper.AlipayUserFundEntityMapper;
import com.ruoyi.alipay.domain.AlipayUserFundEntity;
import com.ruoyi.alipay.service.IAlipayUserFundEntityService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;

/**
 * 用户资金账户Service业务层处理
 * 
 * @author kiwi
 * @date 2020-03-17
 */
@Service
public class AlipayUserFundEntityServiceImpl implements IAlipayUserFundEntityService {
	@Autowired
	private AlipayUserFundEntityMapper alipayUserFundEntityMapper;

	/**
	 * 查询用户资金账户
	 * @param id 用户资金账户ID
	 * @return 用户资金账户
	 */
	@Override
	public AlipayUserFundEntity selectAlipayUserFundEntityById(Long id) {
		return alipayUserFundEntityMapper.selectAlipayUserFundEntityById(id);
	}

	/**
	 * 查询用户资金账户列表
	 * 
	 * @param alipayUserFundEntity 用户资金账户
	 * @return 用户资金账户
	 */
	@Override
	@DataSource(value = DataSourceType.ALIPAY_SLAVE)
	public List<AlipayUserFundEntity> selectAlipayUserFundEntityList(AlipayUserFundEntity alipayUserFundEntity) {
		return alipayUserFundEntityMapper.selectAlipayUserFundEntityList(alipayUserFundEntity);
	}

	/**
	 * 新增用户资金账户
	 * @param alipayUserFundEntity 用户资金账户
	 * @return 结果
	 */
	@Override
	public int insertAlipayUserFundEntity(AlipayUserFundEntity alipayUserFundEntity) {
		alipayUserFundEntity.setCreateTime(DateUtils.getNowDate());
		return alipayUserFundEntityMapper.insertAlipayUserFundEntity(alipayUserFundEntity);
	}

	/**
	 * 修改用户资金账户
	 * @param alipayUserFundEntity 用户资金账户
	 * @return 结果
	 */
	@Override
	public int updateAlipayUserFundEntity(AlipayUserFundEntity alipayUserFundEntity) {
		return alipayUserFundEntityMapper.updateAlipayUserFundEntity(alipayUserFundEntity);
	}

	/**
	 * 删除用户资金账户对象
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@Override
	public int deleteAlipayUserFundEntityByIds(String ids) {
		return alipayUserFundEntityMapper.deleteAlipayUserFundEntityByIds(Convert.toStrArray(ids));
	}

	/**
	 * 删除用户资金账户信息
	 * @param id 用户资金账户ID
	 * @return 结果
	 */
	@Override
	public int deleteAlipayUserFundEntityById(Long id) {
		return alipayUserFundEntityMapper.deleteAlipayUserFundEntityById(id);
	}
}
