package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * ip白名单对象 sys_ip_white
 *
 * @author otc
 * @date 2020-05-13
 */
public class SysIpWhite extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * null
     */
    private Long id;

    /**
     * 0：白名单;1：黑名单
     */
    @Excel(name = "0：白名单;1：黑名单")
    private Integer ipType;

    /**
     * ip地址，ipv6长度是128位
     */
    @Excel(name = "ip地址，ipv6长度是128位")
    private String ipAddree;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIpType() {
        return ipType;
    }

    public void setIpType(Integer ipType) {
        this.ipType = ipType;
    }

    public String getIpAddree() {
        return ipAddree;
    }

    public void setIpAddree(String ipAddree) {
        this.ipAddree = ipAddree;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("ipType", getIpType())
                .append("ipAddree", getIpAddree())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .toString();
    }
}
