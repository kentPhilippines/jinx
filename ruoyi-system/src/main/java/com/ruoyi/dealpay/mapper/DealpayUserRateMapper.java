package com.ruoyi.dealpay.mapper;

import com.ruoyi.dealpay.domain.DealpayUserRateEntity;

import java.util.List;

/**
 * 费率Mapper接口
 *
 * @author kiwi
 * @date 2020-04-03
 */
public interface DealpayUserRateMapper {
    /**
     * 查询费率
     *
     * @param id 费率ID
     * @return 费率
     */
    public DealpayUserRateEntity selectDealpayUserRateEntityById(Long id);

    /**
     * 查询费率列表
     *
     * @param dealpayUserRateEntity 费率
     * @return 费率集合
     */
    public List<DealpayUserRateEntity> selectDealpayUserRateEntityList(DealpayUserRateEntity dealpayUserRateEntity);

    /**
     * 新增费率
     *
     * @param dealpayUserRateEntity 费率
     * @return 结果
     */
    public int insertDealpayUserRateEntity(DealpayUserRateEntity dealpayUserRateEntity);

    /**
     * 修改费率
     *
     * @param dealpayUserRateEntity 费率
     * @return 结果
     */
    public int updateDealpayUserRateEntity(DealpayUserRateEntity dealpayUserRateEntity);

    /**
     * 删除费率
     *
     * @param id 费率ID
     * @return 结果
     */
    public int deleteDealpayUserRateEntityById(Long id);

    /**
     * 批量删除费率
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDealpayUserRateEntityByIds(String[] ids);
}
