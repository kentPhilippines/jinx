package com.ruoyi.common.core.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.utils.DateUtils;

/**
 * Entity基类
 *
 * @author ruoyi
 */
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 搜索值
     */
    private String searchValue;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 请求参数
     */
    private Map<String, Object> params;

    /**
     * checkbox 是否被选中
     */
    private boolean checkFlag = false;

    private String[] paramStr;

    private String[] paramStr2;


    public String[] getParamStr2() {
        return paramStr2;
    }

    public void setParamStr2(String[] paramStr2) {
        this.paramStr2 = paramStr2;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }else{
            if(params.containsKey("dayStart")){
                params.put("dayStart", Optional.ofNullable(params.get("dayStart") == "" ? null : params.get("dayStart")).orElse(DateUtils.dayStart()));
            }
            if(params.containsKey("dayEnd")){
                params.put("dayEnd", Optional.ofNullable(params.get("dayEnd") == "" ? null : params.get("dayEnd")).orElse(DateUtils.dayEnd()));
            }
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public String[] getParamStr() {
        return paramStr;
    }

    public void setParamStr(String[] paramStr) {
        this.paramStr = paramStr;
    }

    public boolean isCheckFlag() {
        return checkFlag;
    }

    public void setCheckFlag(boolean checkFlag) {
        this.checkFlag = checkFlag;
    }
}
