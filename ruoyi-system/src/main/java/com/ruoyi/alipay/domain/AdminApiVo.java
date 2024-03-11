package com.ruoyi.alipay.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Locale;

public interface AdminApiVo {
    @Data
    @Slf4j
    class UserInfoRequestVo {
        private String name;
        private String agent;
        private Integer userStatus;
        private String currency;
    }

    @Data
    @Slf4j
    class UserInfoResponseVo {
        private String userId;
        private String balance;
        private String agent;
        private String currency;
        private Integer userStatus;
    }


    @Data
    @Slf4j
    class UserRateVo {
        private Integer id;
        private String name;
        private BigDecimal fee;
        private BigDecimal percent;
        private String currency;
        private String channelId;
        private String payType;
        private BigDecimal minAmount;
        private BigDecimal maxAmount;
        private Date createTime;
        private Integer priority;
        private Integer feeStatus;
    }

    @Data
    @Slf4j
    class UserOrderVo {
        private Integer id;
        private String orderId;
        private String orderAppId;
        private String name;
        private BigDecimal amount;
        private BigDecimal fee;
        private BigDecimal cost;
        private String info;
        private Integer orderStatus;
        private Integer channelStatus;
        private String channelId;
        private String currency;
        private String witType;
        private Integer payStatus;
        private Integer pushOrder;
        private Integer witLock;
        private Integer watingTime;
        private String requestApp;
        private String responseApp;
        private String requestChannel;
        private String responseChannel;
        private String apply;
        private Date createTime;
        private Date submitTime;
    }

    @Data
    @Slf4j
    class ChannelInfoVo {
        private Integer id;
        private String channelId;
        private String appId;
        private String md5Key;
        private String privateKey;
        private String url;
        private String decKey;
        private String deposit;
        private String wit;
        private String notifyIp;
        private Date createTime;
    }

    @Data
    @Slf4j
    class RouteVo {
        private Integer id;
        private String channelId;
        private String productId;
        private String systemType;
        private String channelType;
        private Integer routeStatus;
        private BigDecimal fee;
        private BigDecimal percent;
        private Date createTime;
    }

    @Data
    @Slf4j
    class ProductVo {
        private Integer id;
        private String productId;
        private String productCode;
        private String productName;
        private Date createTime;
    }
    @Data
    @Slf4j
    class RunOrderVo {
        private Integer id;
        private String orderId;
        private String account;
        private BigDecimal amount;
        private Integer orderType;
        private String describe;
        private Integer amountType;
        private BigDecimal balance;
        private Date createTime;
    }



    @Data
    @Slf4j
    class UserInfoAmount   {
        private String name;
        private BigDecimal amount;
        private Integer type;
        private String apply;
        private String common;
    }

}
