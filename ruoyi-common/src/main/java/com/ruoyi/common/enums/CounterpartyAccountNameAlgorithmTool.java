package com.ruoyi.common.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author water
 */

//ICBC("工商银行"),
//        CCB("建设银行"),
//        PSBC("邮储银行"),
//        CMB("招商银行"),
//        GRC("广东农信"),
//        CIB("兴业银行"),
//        BC("中国银行"),
//        SPDB("浦发银行"),
//        CRBC("珠海华润银行"),
//        CITIC("中信银行"),
//        PAB("平安银行"),
//        CMSB("民生银行"),
//        GUILINBANK("桂林银行"),
//        ABC("中国农业银行");

//    type_1("电子汇入存入"),
//            type_2("他行来账"),
//            type_3("他行汇入"),
//            type_4("转存交易"),
//            type_5("工时交易"),
//            type_6("跨行转出支出"),
//            type_7("转至他行"),
//            type_8("汇款"),
//            type_9("汇款汇入"),
//            type_10("财付通支付支出"),
//            type_11("手机银行支取(跨行支付)"),
//            type_12("互联汇入"),
//            type_13("跨行转入"),
//            type_14("跨行转出"),
//            type_15("跨行转入存入"),
//            type_16("跨行转账转入"),
//            type_17("存款"),
//            type_18("收入（代付）"),
//            type_19("收入（转账）");
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
//张三李四账户
//对方户名张三李四
//张三李四于
//向蓝彬完成
//向李龙军跨行
//收款人蔡晨
//向宋闶尾号
//对方户名麦卓华
//互联汇入汪鹏
//对方户名黄莉
//对方户名财付通支付
//对方户名赵忠河

public enum CounterpartyAccountNameAlgorithmTool {
    CCB_Type_1 {
        @Override
        public String getName(String text) {
            String content = getContent(text);
            return content.trim();
        }
    },
    PSBC_Type_2 {
        @Override
        public String getName(String text) {
            String content = getContent(text);
            String name = content.replace(ReplaceRegex.word_1.getWord(), "");
            return name.trim();
        }
    },
    ICBC_Type_3 {
        @Override
        public String getName(String text) {
            String content = getContent(text);
            String name = content.replace(ReplaceRegex.word_2.getWord(), "");
            return name.trim();
        }
    },
    ABC_Type_4 {
        @Override
        public String getName(String text) {
            String content = getContent(text);
            String name = content.replace(ReplaceRegex.word_3.getWord(), "");
            return name.trim();
        }
    },
    ABC_Type_5 {
        @Override
        public String getName(String text) {
            String content = getContent(text);
            String name = content.replace(ReplaceRegex.word_4.getWord(), "").replace(ReplaceRegex.word_5.getWord(), "");
            return name.trim();
        }
    },

    CCB_Type_6 {
        @Override
        public String getName(String text) {
            String content = getContent(text);
            String name = content.replace(ReplaceRegex.word_4.getWord(), "").replace(ReplaceRegex.word_6.getWord(), "");
            return name.trim();
        }
    },
    CMB_Type_7 {
        @Override
        public String getName(String text) {
            return "";
        }
    },
    PSBC_Type_8 {
        @Override
        public String getName(String text) {
            String content = getContent(text);
            String name = content.replace(ReplaceRegex.word_4.getWord(), "").replace(ReplaceRegex.word_8.getWord(),"");
            return name.trim();
        }
    },
    CIB_Type_9 {
        @Override
        public String getName(String text) {
            String content = getContent(text);
            String name = content.replace(ReplaceRegex.word_2.getWord(), "");
            return name.trim();
        }
    },
    GRC_Type_10 {
        @Override
        public String getName(String text) {
            String content = getContent(text);
            String name = content.replace(ReplaceRegex.word_2.getWord(), "");
            return name.trim();
        }
    },
    BC_Type_11 {
        @Override
        public String getName(String text) {
            return "";
        }
    },
    SPDB_Type_12 {
        @Override
        public String getName(String text) {
            String content = getContent(text);
            String name = content.replace(ReplaceRegex.word_9.getWord(), "");
            return name.trim();
        }
    },
    CRBC_Type_13 {
        @Override
        public String getName(String text) {
            return "";
        }
    },
    ICBC_Type_14 {
        @Override
        public String getName(String text) {
            String content = getContent(text);
            String name = content.replace(ReplaceRegex.word_2.getWord(), "");
            return name.trim();
        }
    },
    CITIC_Type_15 {
        @Override
        public String getName(String text) {
            return "";
        }
    },
    PAB_Type_16 {
        @Override
        public String getName(String text) {
            return "";
        }
    },
    CMSB_Type_17 {
        @Override
        public String getName(String text) {
            return "";
        }
    },
    GUILINBANK_Type_18 {
        @Override
        public String getName(String text) {
            String content = getContent(text);
            String name = content.replace(ReplaceRegex.word_2.getWord(), "");
            return name.trim();
        }
    },
    GUILINBANK_Type_19 {
        @Override
        public String getName(String text) {
            String content = getContent(text);
            String name = content.replace(ReplaceRegex.word_2.getWord(), "");
            return name.trim();
        }
    };


    /**
     * 银行名称
     */
    public String counterpartyAccountName;

    CounterpartyAccountNameAlgorithmTool() {

    }

    public abstract String getName(String text);


    CounterpartyAccountNameAlgorithmTool(String counterpartyAccountName) {
        this.counterpartyAccountName = counterpartyAccountName;

    }

    @Override
    public String toString() {
        return this.name();
    }

    /**
     * 根据模式类型查询枚举名称对象
     *
     * @param bankName
     * @param type
     * @return
     */
    public static CounterpartyAccountNameAlgorithmTool getEnum(String bankName, String type) {
        String format = String.format("%s_%s", bankName, type);
        for (CounterpartyAccountNameAlgorithmTool counterpartyAccountNameAlgorithmTool : CounterpartyAccountNameAlgorithmTool.values()) {
            if (counterpartyAccountNameAlgorithmTool.name().equalsIgnoreCase(format)) {
                return counterpartyAccountNameAlgorithmTool;
            }
        }
        return null;
    }

    /**
     * @return
     */
    public String getContent(String text) {
        Matcher matcher = Pattern.compile(SplitRegex.counterpartyAccountNameRegex.getFirstRegex()).matcher(text);
        while (matcher.find()) {
            String replace = "";
            String group = matcher.group();
            Matcher matcher1 = Pattern.compile(SplitRegex.counterpartyAccountNameRegex.getSecondRegex()).matcher(group);
            while (matcher1.find()) {
                replace = replace + matcher1.group();
            }
            return replace;
        }
        return "";
    }
}
