package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 谷歌验证器对象 sys_user_google
 * 
 * @author kiwi
 * @date 2020-04-01
 */
public class SysUserGoogle extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 数据ID */
    private Long id;

    /** 登陆账号 */
    @Excel(name = "登陆账号")
    private String loginName;

    /** 二维码地址 */
    @Excel(name = "二维码地址")
    private String googleUrl;

    /** 密钥 */
    @Excel(name = "密钥")
    private String secretKey;

    /** 服务器地址 */
    @Excel(name = "服务器地址")
    private String host;

    /** 绑定过期时间（分） */
    @Excel(name = "绑定过期时间", readConverterExp = "分=")
    private Long expireTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setLoginName(String loginName) 
    {
        this.loginName = loginName;
    }

    public String getLoginName() 
    {
        return loginName;
    }
    public void setGoogleUrl(String googleUrl) 
    {
        this.googleUrl = googleUrl;
    }

    public String getGoogleUrl() 
    {
        return googleUrl;
    }
    public void setSecretKey(String secretKey) 
    {
        this.secretKey = secretKey;
    }

    public String getSecretKey() 
    {
        return secretKey;
    }
    public void setHost(String host) 
    {
        this.host = host;
    }

    public String getHost() 
    {
        return host;
    }
    public void setExpireTime(Long expireTime) 
    {
        this.expireTime = expireTime;
    }

    public Long getExpireTime() 
    {
        return expireTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("loginName", getLoginName())
            .append("googleUrl", getGoogleUrl())
            .append("secretKey", getSecretKey())
            .append("host", getHost())
            .append("expireTime", getExpireTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("remark", getRemark())
            .toString();
    }
}
