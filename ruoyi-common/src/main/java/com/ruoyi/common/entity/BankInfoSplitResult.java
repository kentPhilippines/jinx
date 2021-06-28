package com.ruoyi.common.entity;

/**
 * @author water
 */
public class BankInfoSplitResult {
    /**
     * 数据id
     */
    private Long id;

    /**
     * 原始短信内容
     */
    private String originText;
    /**
     * 银行或支付渠道名称
     * 建设银行，工商银行等
     */
    private String bankName;
    /**
     * 业务类型
     * 存入，转账，转存等
     */
    private String typeDetail;
    /**
     * 余额
     */
    private String balance;
    /**
     * 交易时间
     */
    private String transactionDate;
    /**
     * 交易金额
     */
    private String transactionAmount;
    /**
     * 自己账户尾号
     */
    private String myselfTailNumber;
    /**
     * 对方账户尾号
     */
    private String counterpartyTailNumber;
    /**
     * 对方户名
     */
    private String counterpartyAccountName;
    /**
     * 交易类型，收入，支出
     */
    private String transactionType;
    /**
     * 电话号码
     */
    private String phoneNumber;
    /**
     * 电话号码+自己尾号的组装
     */
    private String checkKey;

    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 最终短信内容
     */
    private String resultText;

    public String getOriginText() {
        return originText;
    }

    public void setOriginText(String originText) {
        this.originText = originText;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getTypeDetail() {
        return typeDetail;
    }

    public void setTypeDetail(String typeDetail) {
        this.typeDetail = typeDetail;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getMyselfTailNumber() {
        return myselfTailNumber;
    }

    public void setMyselfTailNumber(String myselfTailNumber) {
        this.myselfTailNumber = myselfTailNumber;
    }

    public String getCounterpartyTailNumber() {
        return counterpartyTailNumber;
    }

    public void setCounterpartyTailNumber(String counterpartyTailNumber) {
        this.counterpartyTailNumber = counterpartyTailNumber;
    }

    public String getCounterpartyAccountName() {
        return counterpartyAccountName;
    }

    public void setCounterpartyAccountName(String counterpartyAccountName) {
        this.counterpartyAccountName = counterpartyAccountName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCheckKey() {
        return checkKey;
    }

    public void setCheckKey(String checkKey) {
        this.checkKey = checkKey;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getResultText() {
        return resultText;
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }
}
