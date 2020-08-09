package com.ruoyi.alipay.domain.echarts;

import java.io.Serializable;
import java.util.List;

/**
 * 堆叠区域图 实体类
 */
public class StackedAreaChart implements Serializable {
    private static final long serialVersionUID = 1L;
    /*name: '联盟广告',
    type: 'line',
    stack: '总量',
    areaStyle: {},
    data: [220, 182, 191, 234, 290, 330, 310]*/
    private String name;
    private String type;
/*    private String stack;
    private Object areaStyle;*/
    private List data;

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    /*public Object getAreaStyle() {
        return areaStyle;
    }

    public void setAreaStyle(Object areaStyle) {
        this.areaStyle = areaStyle;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }
*/
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
