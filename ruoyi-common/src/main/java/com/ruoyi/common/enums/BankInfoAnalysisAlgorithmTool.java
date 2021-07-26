package com.ruoyi.common.enums;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.constant.TypeConstants;
import com.ruoyi.common.entity.BankInfoSplitResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 银行信息解析算法类
 *
 * @author water
 */

public enum BankInfoAnalysisAlgorithmTool {
    Instance;

    private static final Logger log = LoggerFactory.getLogger(BankInfoAnalysisAlgorithmTool.class);

    /**
     * 执行解析银行短信信息算法
     *
     * @param text
     * @return
     */
    public static BankInfoSplitResult runAlgorithm(String text) {
//        String decodeContent = Base64.decodeStr(content);
//        Map<String, String> contentMap = getContent(decodeContent);
//        String phoneNumber = contentMap.get("phoneNumber");
//        String text = contentMap.get("text");
        BankInfoSplitResult bankInfoSplitEntity = getRegexContent(text);
        BankNameEnum bankNameEnum = BankNameEnum.getEnum(bankInfoSplitEntity.getBankName());
        BankBusinessTypeEnum bankBusinessTypeEnum = BankBusinessTypeEnum.getEnum(bankInfoSplitEntity.getTypeDetail());
        CounterpartyAccountNameAlgorithmTool counterpartyAccountNameAlgorithmTool = CounterpartyAccountNameAlgorithmTool
                .getEnum(bankNameEnum.name(), bankBusinessTypeEnum.name());
        String name = counterpartyAccountNameAlgorithmTool.getName(text);
        bankInfoSplitEntity.setCounterpartyAccountName(name);
//        bankInfoSplitResult.setPhoneNumber(phoneNumber);
        bankInfoSplitEntity.setTransactionType(TypeConstants.income.contains(bankBusinessTypeEnum.name()) ?
                TransactionTypeEnum.income.name() : TransactionTypeEnum.expenditure.name());
        String resultText = JSON.toJSONString(bankInfoSplitEntity);
        bankInfoSplitEntity.setResultText(resultText);
        bankInfoSplitEntity.setOriginText(text);
        System.out.println(resultText);
        return bankInfoSplitEntity;
    }


    /**
     * 将解密后的字符串，转换为map
     *
     * @param content
     * @return
     */
    public static Map<String, String> getContent(String content) {
        HashMap<String, String> contentMap = new HashMap<>();
        String[] contents = content.split("&");
        for (String s : contents) {
            String[] split = s.split("=");
            contentMap.put(split[0], split[1]);
        }
        return contentMap;
    }


    /**
     * 将短信根据不通字段的匹配正则
     *
     * @param text
     * @return
     */
    public static BankInfoSplitResult getRegexContent(String text) {
        int i = 0;
        BankInfoSplitResult bankInfoSplitEntity = new BankInfoSplitResult();
        SplitRegex[] values = SplitRegex.values();
        for (SplitRegex value : values) {
            if (value != SplitRegex.counterpartyAccountNameRegex) {
                if (value == SplitRegex.myselfTailNumberRegex) {
                    Matcher matcher = Pattern.compile(value.getFirstRegex()).matcher(text);
                    while (matcher.find()) {
                        String group = matcher.group();
                        Matcher matcher1 = Pattern.compile(value.getSecondRegex()).matcher(group);
                        while (matcher1.find()) {
                            bankInfoSplitEntity.setMyselfTailNumber(matcher1.group().replace("*", "").trim());
                        }
                    }
                }
                if (value == SplitRegex.counterpartyTailNumberRegex) {
                    Matcher matcher = Pattern.compile(value.getFirstRegex()).matcher(text);
                    while (matcher.find()) {
                        String group = matcher.group();
                        Matcher matcher1 = Pattern.compile(value.getSecondRegex()).matcher(group);
                        while (matcher1.find()) {
                            bankInfoSplitEntity.setCounterpartyTailNumber(matcher1.group().replace("*", "").trim());
                        }
                    }
                }
                if (value == SplitRegex.money) {
                    Matcher matcher = Pattern.compile(value.getFirstRegex()).matcher(text);
                    while (matcher.find()) {
                        i++;
                        String group = matcher.group();
                        String replace = group.replace(",", "");
                        Matcher matcher1 = Pattern.compile(value.getSecondRegex()).matcher(replace);
                        if (i == 1) {
                            while (matcher1.find()) {
                                BigDecimal bigDecimal = BigDecimal.valueOf(Double.valueOf(matcher1.group()));
                                bigDecimal.setScale(4);
                                bankInfoSplitEntity.setTransactionAmount(matcher1.group().trim());
                            }
                        }
                        if (i == 2) {
                            while (matcher1.find()) {
                                bankInfoSplitEntity.setBalance(matcher1.group().trim());
                            }
                        }
                    }
                }
                if (value == SplitRegex.BankNameRegex || value == SplitRegex.TypeRegex || value == SplitRegex.TransactionDateRegex) {
                    Pattern compile = Pattern.compile(value.getFirstRegex());
                    Matcher matcher = compile.matcher(text);
                    if (value == SplitRegex.BankNameRegex) {
                        while (matcher.find()) {
                            String group = matcher.group();
                            bankInfoSplitEntity.setBankName(group.trim());
                        }
                    }
                    if (value == SplitRegex.TypeRegex) {
                        while (matcher.find()) {
                            String group = matcher.group();
                            bankInfoSplitEntity.setTypeDetail(group.trim());
                        }
                    }
                    if (value == SplitRegex.TransactionDateRegex) {
                        while (matcher.find()) {
                            String group = matcher.group();
                            bankInfoSplitEntity.setTransactionDate(group.trim());
                        }
                    }
                }
            }
        }
        return bankInfoSplitEntity;
    }


    public static void main(String[] args) {
        //    【建设银行收到转账汇款手机短信】刘德华4月18日15时15分向您尾号2428的储蓄卡电子汇入存入人民币10.00元,活期余额283.85元。[建设银行]
//            【邮储银行】21年04月18日15:27张三李四账户2428向您尾号855账户他行来账金额5.00元，余额25.30元。
//            【工商银行收到转账汇款手机短信】您尾号6090卡4月18日15:38工商银行收入(他行汇入)4,000元，余额79,051.70元，对方户名：张三李四，对方账户尾号：6679。【工商银行】
//            【中国农业银行】张三李四于04月18日15:34向您尾号6679账户完成转存交易人民币5000.00，余额55073.97。
//            【中国农业银行】您尾号5477账户06月24日19:32向蓝彬完成工时交易人民币-3753.00，余额35102.82。
//
//            您尾号2131的储蓄卡6月24日19时33分向李龙军跨行转出支出人民币1000.00元,活期余额40175.49元。[建设银行]
//            您的借记卡账户长城电子借记卡，于06月24日手机银行支取(跨行支付)人民币2000.00元,交易后余额26717.28【中国银行】
//
//            【招商银行】您账户0708于06月24日20:11实时转至他行人民币1000.00，余额36641.58，收款人蔡晨
//            【邮储银行】21年06月24日21:12您尾号397账户向宋闶尾号1963账户汇款金额2000.00元，余额38438.90元。
//            24日17:59账户*6751*汇款汇入收入1000.00元，余额20442.23元。对方户名:麦卓华[兴业银行]
//            【广东农信】您尾数8354的卡号06月24日18:00财付通支付支出人民币14.00元,余额1007.85元。【鹤山农商银行】
//            您尾号7130卡人民币活期13:47存入1,005.00元[互联汇入]汪鹏9107，可用余额6075.00元。查余额明细：http://t.spdb.com.cn/QTgHp【浦发银行】
//            您尾号为0685的账户 05月01日13:40入账人民币2500.00元，余额为2962.39元。摘要：跨行转入。登录手机银行，1折买精选基金。-【珠海华润银行】
//            您尾号0619卡5月1日13:32工商银行收入(跨行转出)888元，余额15,875.99元，对方户名：黄莉，对方账户尾号：8485。【工商银行】
//            【中信银行】您尾号3352的中信卡于04月16日19:53，跨行转入存入人民币5200.00元，当前余额为人民币29405.28元。
//            您存款账户5514于4月16日21:14跨行转账转入人民币220.00元，活期余额人民币4026.42元。【平安银行】
//            账户*2523于03月31日16:02存入￥200.00元，可用余额223.31元。存款。【民生银行】
//            【桂林银行】您尾号1565账户于06月25日16:32收入（代付）10元，现余额10.15元。对方户名财付通支付科技有限公司
//            【桂林银行】您尾号1565账户于06月25日17:58收入（转账）10元，现余额30.15元。对方户名赵忠河刘德华
        String a = "【建设银行收到转账汇款手机短信】刘德华4月18日15时15分向您尾号2428的储蓄卡电子汇入存入人民币10.00元,活期余额283.85元。[建设银行]";
        String b = "【邮储银行】21年04月18日15:27张三李四账户2428向您尾号855账户他行来账金额5.00元，余额25.30元。";
        String c = "【工商银行收到转账汇款手机短信】您尾号6090卡4月18日15:38工商银行收入(他行汇入)4,000元，余额79,051.70元，对方户名：张三李四，对方账户尾号：6679。【工商银行】";
        String d = "【中国农业银行】张三李四于04月18日15:34向您尾号6679账户完成转存交易人民币5000.00，余额55073.97。";
        String e = "【中国农业银行】您尾号5477账户06月24日19:32向蓝彬完成工时交易人民币-3753.00，余额35102.82。";
        String f = "您尾号2131的储蓄卡6月24日19时33分向李龙军跨行转出支出人民币1000.00元,活期余额40175.49元。[建设银行]";
        String g = "您的借记卡账户长城电子借记卡，于06月24日手机银行支取(跨行支付)人民币2000.00元,交易后余额26717.28【中国银行】";
        String h = "【招商银行】您账户0708于06月24日20:11实时转至他行人民币1000.00，余额36641.58，收款人蔡晨";
        String i = "【邮储银行】21年06月24日21:12您尾号397账户向宋闶尾号1963账户汇款金额2000.00元，余额38438.90元。";
        String j = "24日17:59账户*6751*汇款汇入收入1000.00元，余额20442.23元。对方户名:麦卓华[兴业银行]";
        String k = "【广东农信】您尾数8354的卡号06月24日18:00财付通支付支出人民币14.00元,余额1007.85元。【鹤山农商银行】";
        String l = "您尾号7130卡人民币活期13:47存入1,005.00元[互联汇入]汪鹏9107，可用余额6075.00元。查余额明细：http://t.spdb.com.cn/QTgHp【浦发银行】";
        String m = "您尾号为0685的账户 05月01日13:40入账人民币2500.00元，余额为2962.39元。摘要：跨行转入。登录手机银行，1折买精选基金。-【珠海华润银行】";
        String n = "您尾号0619卡5月1日13:32工商银行收入(跨行转出)888元，余额15,875.99元，对方户名：黄莉，对方账户尾号：8485。【工商银行】";
        String o = "【中信银行】您尾号3352的中信卡于04月16日19:53，跨行转入存入人民币5200.00元，当前余额为人民币29405.28元。";
        String p = "您存款账户5514于4月16日21:14跨行转账转入人民币220.00元，活期余额人民币4026.42元。【平安银行】";
        String q = "账户*2523于03月31日16:02存入￥200.00元，可用余额223.31元。存款。【民生银行】";
        String r = "【桂林银行】您尾号1565账户于06月25日16:32收入（代付）10元，现余额10.15元。对方户名财付通支付科技有限公司";
        String s = "【桂林银行】您尾号1565账户于06月25日17:58收入（转账）10元，现余额30.15元。对方户名赵忠河刘德华";
        List<String> objects = new ArrayList<>();
        objects.add(a);
        objects.add(b);
        objects.add(c);
        objects.add(d);
        objects.add(e);
        objects.add(f);
        objects.add(g);
        objects.add(h);
        objects.add(i);
        objects.add(j);
        objects.add(k);
        objects.add(l);
        objects.add(m);
        objects.add(n);
        objects.add(o);
        objects.add(p);
        objects.add(q);
        objects.add(r);
        objects.add(s);
        for (String object : objects) {
            BankInfoSplitResult bankInfoSplitEntity = BankInfoAnalysisAlgorithmTool.runAlgorithm(object);
            System.out.println(bankInfoSplitEntity.toString());

        }
    }
}
