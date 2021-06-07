package com.ruoyi.system.domain;

public class HUOBI {

    private String id;
    private String rateType;
    private String price;
    private String caeateTime;

    public String getCaeateTime() {
        return caeateTime;
    }

    public void setCaeateTime(String caeateTime) {
        this.caeateTime = caeateTime;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
