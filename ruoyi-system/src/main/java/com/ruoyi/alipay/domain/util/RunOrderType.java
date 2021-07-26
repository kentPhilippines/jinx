package com.ruoyi.alipay.domain.util;

public class RunOrderType {
    public static String getType(Integer runType) {
/**
 *         人工加款	17	runOrderType
 *         人工扣款	18	runOrderType
 *         交易点数扣除	12	runOrderType
 *         代理商分润	13	runOrderType
 *         交易分润	14	runOrderType
 *         账户充值	11	runOrderType
 *         代付	10	runOrderType
 *         代付手续费	9	runOrderType
 *         商户交易加款	20	runOrderType
 *         商户交易手续费扣款	21	runOrderType
 *         渠道代付手续费	25	runOrderType
 *         渠道代付加款	23	runOrderType
 *         代付代理商分润	26	runOrderType
 *         代付失败手续费解冻	22	runOrderType
 *         代付失败解冻	8	runOrderType
 *         账户冻结	27	runOrderType
 *         账户解冻	28	runOrderType
 *         扣除授信	29	runOrderType
 *         增加授信	30	runOrderType
 *         转账扣款	32	runOrderType
 *         转账加款	33	runOrderType
 *         代付加款	34	runOrderType
 *         代付加款分润	35	runOrderType
 *         卡商入款代理分润	36	runOrderType
 *         卡商出款代理分润	37	runOrderType
 */
        switch (runType) {
            case 17:
                return "人工加款";
            case 18:
                return "人工扣款";
            case 12:
                return "交易点数扣除";
            case 13:
                return "代理商分润";
            case 14:
                return "交易分润";
            case 11:
                return "账户充值";
            case 10:
                return "代付";
            case 9:
                return "代付手续费";
            case 20:
                return "商户交易加款";
            case 21:
                return "商户交易手续费扣款";
            case 25:
                return "渠道代付手续费";
            case 23:
                return "渠道代付加款";
            case 26:
                return "代付代理商分润";
            case 22:
                return "代付失败手续费解冻";
            case 8:
                return "代付失败解冻";
            case 27:
                return "账户冻结";
            case 28:
                return "账户解冻";
            case 30:
                return "增加授信";
            case 32:
                return "扣除授信";
            case 33:
                return "转账扣款";
            case 34:
                return "代付加款";
            case 35:
                return "代付加款分润";
            case 36:
                return "卡商入款代理分润";
            case 37:
                return "卡商出款代理分润";
            default:
                return "未知";
        }


    }

}
