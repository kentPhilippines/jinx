package com.ruoyi.framework.util;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysDictDataService;
import com.ruoyi.system.service.impl.SysDictDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 获取数据字典值
 *
 * @author otc
 */
@Component
public class DictionaryUtils {

    @Autowired
    private SysDictDataServiceImpl sysDictDataServiceImpl;

    public String getApiUrlPath(String dictType, String dictLabel) {
        if (StringUtils.isEmpty(dictType)) {
            throw new BusinessException("参数不能为空");
        }
        if (StringUtils.isEmpty(dictLabel)) {
            throw new BusinessException("参数不能为空");
        }
        List<String> dictVlaue = sysDictDataServiceImpl.selectDictValueByKye(dictType, dictLabel);
        if (dictVlaue.get(0) == null) {
            throw new BusinessException("未配置数据库字典API请求地址");
        }
        if (dictVlaue.size() > 1) {
            throw new BusinessException("重复配置数据库字典Api请求地址");
        }
        return dictVlaue.get(0);
    }

}
