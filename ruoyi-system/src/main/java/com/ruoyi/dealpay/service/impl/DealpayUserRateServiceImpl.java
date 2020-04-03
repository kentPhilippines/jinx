package com.ruoyi.dealpay.service.impl;

import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dealpay.mapper.DealpayUserRateMapper;
import com.ruoyi.dealpay.domain.DealpayUserRateEntity;
import com.ruoyi.dealpay.service.IDealpayUserRateService;
import com.ruoyi.common.core.text.Convert;

/**
 * 费率Service业务层处理
 *
 * @author kiwi
 * @date 2020-04-03
 */
@Service
public class DealpayUserRateServiceImpl implements IDealpayUserRateService {
    @Autowired
    private DealpayUserRateMapper dealpayUserRateMapper;

    /**
     * 查询费率
     *
     * @param id 费率ID
     * @return 费率
     */
    @Override
    public DealpayUserRateEntity selectDealpayUserRateEntityById(Long id) {
        return dealpayUserRateMapper.selectDealpayUserRateEntityById(id);
    }

    /**
     * 查询费率列表
     *
     * @param dealpayUserRateEntity 费率
     * @return 费率
     */
    @Override
    public List<DealpayUserRateEntity> selectDealpayUserRateEntityList(DealpayUserRateEntity dealpayUserRateEntity) {
        return dealpayUserRateMapper.selectDealpayUserRateEntityList(dealpayUserRateEntity);
    }

    /**
     * 新增费率
     *
     * @param dealpayUserRateEntity 费率
     * @return 结果
     */
    @Override
    public int insertDealpayUserRateEntity(DealpayUserRateEntity dealpayUserRateEntity) {
        dealpayUserRateEntity.setCreateTime(DateUtils.getNowDate());
        return dealpayUserRateMapper.insertDealpayUserRateEntity(dealpayUserRateEntity);
    }

    /**
     * 修改费率
     *
     * @param dealpayUserRateEntity 费率
     * @return 结果
     */
    @Override
    public int updateDealpayUserRateEntity(DealpayUserRateEntity dealpayUserRateEntity) {
        return dealpayUserRateMapper.updateDealpayUserRateEntity(dealpayUserRateEntity);
    }

    /**
     * 删除费率对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDealpayUserRateEntityByIds(String ids) {
        return dealpayUserRateMapper.deleteDealpayUserRateEntityByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除费率信息
     *
     * @param id 费率ID
     * @return 结果
     */
    @Override
    public int deleteDealpayUserRateEntityById(Long id) {
        return dealpayUserRateMapper.deleteDealpayUserRateEntityById(id);
    }
}
