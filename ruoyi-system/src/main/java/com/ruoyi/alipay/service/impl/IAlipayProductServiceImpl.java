package com.ruoyi.alipay.service.impl;

import com.ruoyi.alipay.domain.AlipayProductEntity;
import com.ruoyi.alipay.mapper.AlipayProductMapper;
import com.ruoyi.alipay.service.IAlipayProductService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 产品管理Service业务层处理
 */
@Service
public class IAlipayProductServiceImpl implements IAlipayProductService {
    @Resource
    AlipayProductMapper alipayProductMapper;

    /**
     *  产品查询有效数据
     * @param alipayProductEntity
     * @return
     */
    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public List<AlipayProductEntity> selectAlipayProductList(AlipayProductEntity alipayProductEntity) {
        return alipayProductMapper.selectAlipayProductListEntityList(alipayProductEntity);
    }

    @Override
    @DataSource(value = DataSourceType.ALIPAY_SLAVE)
    public int insertAlipayProductListEntity(AlipayProductEntity alipayProductEntity) {
        alipayProductEntity.setStatus(1);
        return alipayProductMapper.insertAlipayProductListEntity(alipayProductEntity);
    }

    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public AlipayProductEntity selectAlipayProductListEntityById(Long id) {
        return alipayProductMapper.selectAlipayProductListEntityById(id);
    }

    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public int updateAlipayProductListEntity(AlipayProductEntity alipayProductEntity) {
        alipayProductEntity.setSubmitTime(new Date());
        return alipayProductMapper.updateAlipayProductListEntity(alipayProductEntity);
    }

    /**
     * 、<p>删除产品列表</p>
     * @param ids
     * @return
     */
    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public int deleteAlipayProductListEntityByIds(String ids) {
        return alipayProductMapper.deleteAlipayProductListEntityByIds(Convert.toStrArray(ids));
    }

    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public int updateProductStatusById(AlipayProductEntity alipayProductEntity) {
        return alipayProductMapper.updateAlipayProductById(alipayProductEntity);
    }

    /**
     *
     * <p>查询添加产品是否存在</p>
     * @param productId
     * @return
     */
    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public AlipayProductEntity checkAlipayProductIdUnique(String productId) {
        return alipayProductMapper.checkAlipayProductIdUnique(productId);
    }

    @Override
    @DataSource(DataSourceType.ALIPAY_SLAVE)
    public List<AlipayProductEntity> selectProductTypeListToWeb() {
       return alipayProductMapper.selectAlipayProductTypeListToWeb();
    }

}
