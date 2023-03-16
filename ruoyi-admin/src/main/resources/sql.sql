-- auto-generated definition
create table alipay_amount
(
    id             int(32) auto_increment comment '数据id(主键索引)'
        primary key,
    orderId        char(48)                           not null comment '手动加扣款订单号',
    userId         varchar(32)                        not null comment '会员id(唯一识别号)(index索引)',
    amountType     char(2)                            not null comment '1 加款类型  2 扣款类型',
    accname        varchar(32)                        null comment '申请人姓名【后台管理人员】',
    orderStatus    char(3)                            not null comment '1申请 2审批中  3成功  4失败 5挂起',
    amount         decimal(19, 2)                     not null comment '金额',
    fee            decimal(19, 2)                     null comment '手续费',
    actualAmount   decimal(19, 2)                     not null comment '真实到账金额',
    dealDescribe   text                               not null comment '加扣款描述',
    createTime     datetime default CURRENT_TIMESTAMP not null comment '数据创建时间',
    submitTime     datetime default CURRENT_TIMESTAMP null comment '数据修改时间',
    status         int(2)   default 1                 not null comment '1数据可用2数据无用',
    approval       varchar(50)                        null comment '审核人',
    comment        varchar(300)                       null comment '审核意见',
    transferUserId varchar(32)                        null comment '转账订单入款用户',
    constraint orderId
        unique (orderId)
)
    comment '手动加扣款记录表';





-- auto-generated definition
create table alipay_bank_config
(
    id          int unsigned auto_increment
        primary key,
    code_value  varchar(100)                       not null,
    bank_name   varchar(100)                       not null,
    alias1      varchar(100)                       null,
    alias2      varchar(100)                       null,
    alias3      varchar(100)                       null,
    alias4      varchar(100)                       null,
    create_time datetime default CURRENT_TIMESTAMP not null
);






-- auto-generated definition
create table alipay_bank_list
(
    id              int(32) auto_increment comment '数据id',
    bankcardId      varchar(48)                              not null comment '银行卡(本地编号)',
    bankcardAccount varchar(64)                              null comment '银行卡号',
    accountHolder   varchar(32)                              not null comment '银行卡开户人',
    openAccountBank varchar(32)                              not null comment '银行卡开户行',
    bankType        char(8)                                  null comment '银行英文简写',
    bankcode        char(3)                                  null comment '银行卡类型:R收款W出款',
    sysTYpe         int(2)         default 2                 null comment '系统状态 1黑卡  2 可正常使用 ',
    account         varchar(32)                              not null comment '关联账户',
    phone           char(16)                                 null comment '银行卡手机号',
    limitAmount     decimal(19, 4) default 200000.0000       not null comment '当日交易限制金额',
    bankAmount      decimal(19, 4) default 0.0000            not null comment '银行卡余额',
    cardType        int(2)                                   null comment '系统的卡1，码商的卡2，商户的卡3',
    qrcodeNote      text                                     null comment '备注',
    status          int(2)         default 1                 not null comment '状态:1可使用；0不可使用',
    isDeal          int(2)         default 2                 null comment '是否逻辑删除：1删除2可用',
    createTime      datetime       default CURRENT_TIMESTAMP not null comment '数据生成时间',
    submitTime      datetime       default CURRENT_TIMESTAMP not null comment '数据修改时间',
    primary key (id, bankcardId),
    constraint query
        unique (bankcardAccount, bankType, bankcode, cardType)
)
    comment '银行卡列表';

-- auto-generated definition
create table alipay_chanel_fee
(
    id          int(32) auto_increment comment '数据id'
        primary key,
    channelId   varchar(24)                        not null comment '渠道ID',
    productId   varchar(24)                        not null comment '产品id',
    impl        varchar(32)                        not null comment '实体类对应关系',
    channelRFee decimal(19, 6)                     null comment '渠道充值 费率',
    channelDFee decimal(19, 6)                     null comment '渠道代付费率',
    channelNo   varchar(24)                        null comment '上游渠道通道编号',
    createTime  datetime default CURRENT_TIMESTAMP not null,
    submitTime  datetime default CURRENT_TIMESTAMP not null,
    status      int(3)   default 1                 not null comment '1为可用  2为不可用',
    constraint impl
        unique (channelId, productId)
);









-- auto-generated definition
create table alipay_correlation
(
    id           int(32) auto_increment comment '数据id'
        primary key,
    parentId     int(32)                            null comment '父节点id',
    parentName   char(16)                           not null comment '父节点名字',
    childrenId   int(32)                            null comment '孩子节点的id',
    childrenName char(16)                           not null comment '孩子节点的名字',
    distance     int(3)                             not null comment '节点距离',
    medium       text                               null comment '支付宝内容',
    status       int(2)   default 1                 null comment '状态:1可使用；0不可使用',
    createTime   datetime default CURRENT_TIMESTAMP not null comment '数据创建时间',
    submitTime   datetime default CURRENT_TIMESTAMP not null comment '数据最后一次修改时间',
    parentType   int(3)                             null comment '1代理商 2会员',
    childrenType int(3)                             null comment '1代理商 2会员'
);










-- auto-generated definition
create table alipay_correlation_data
(
    id          int(32) auto_increment comment '数据id'
        primary key,
    orderId     char(54)                           not null comment '订单号',
    userId      char(24)                           not null comment '用户id',
    appId       varchar(24)                        null comment '下游商户id',
    mediumId    int(32)                            null comment '媒介数据id',
    qrId        char(16)                           null comment '二维码数据id',
    amount      decimal(19, 4)                     not null comment '流动金额',
    fee         decimal(19, 4)                     null comment '商户结算费率',
    channelFee  decimal(19, 4)                     null comment '渠道结算费率',
    profit      decimal(19, 4)                     null comment '当前系统自己利润',
    orderStatus int(32)                            not null comment '订单状态  与交易订单 一样',
    status      int(2)   default 1                 null comment '状态:1可使用；0不可使用',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '数据创建时间',
    submitTime  datetime default CURRENT_TIMESTAMP not null comment '数据最后一次修改时间',
    constraint orderId
        unique (orderId)
);










-- auto-generated definition
create table alipay_deal_order
(
    id              bigint unsigned auto_increment comment '主键'
        primary key,
    orderId         char(48)                                 not null comment '订单号',
    associatedId    char(48)                                 not null comment '关联订单号',
    orderStatus     char(2)                                  not null comment '订单状态:0预下单1处理中2成功3未收到回调4失败5超时6订单申述7人工处理',
    dealAmount      decimal(19, 6)                           not null comment '订单交易金额',
    dealFee         decimal(19, 6)                           null comment '订单交易手续费',
    actualAmount    decimal(17, 6)                           not null comment '实际到账金额',
    orderType       char(2)                                  null comment '订单类型:1交易,2系统加款',
    orderAccount    char(24)                                 not null comment '订单关联商户账号',
    orderQrUser     char(32)                                 not null comment '关联码商账户',
    orderQr         varchar(128)                             null,
    externalOrderId varchar(48)                              null comment '外部订单号(下游商户请求参数,用户数据回调)',
    generationIp    varchar(14)                              null comment '订单生成IP(客户端ip或者是下游商户id)',
    dealDescribe    text                                     null comment '交易备注',
    notify          varchar(128)                             null comment '订单异步回调地址',
    back            varchar(128)                             null comment '订单同步回调地址',
    isNotify        varchar(3)     default 'NO'              not null comment '是否發送通知 //  YES 已發送    NO 未發送',
    createTime      datetime       default CURRENT_TIMESTAMP not null comment '数据生成时间（该时间等同于交易时间）',
    submitTime      datetime       default CURRENT_TIMESTAMP not null comment '数据修改时间',
    feeId           int(3)                                   null comment '使用费率id',
    status          int(3)         default 1                 not null comment '状态:1可使用；0不可使用',
    retain1         char(28) collate utf8_bin                null comment '订单交易产品类型',
    retain2         char(32)                                 null comment '备用',
    retain3         decimal(19, 6)                           null comment '当前订单系统盈利',
    retain4         int(2)         default 1                 not null comment '1为订单成功未结算，2为订单成功已结算',
    currency        varchar(5)     default 'CNY'             not null comment '货币类型',
    txhash          varchar(256)                             null comment 'usdt交易hash唯一值',
    macthMsg        varchar(255)   default ''                null comment '撮合解释',
    macthStatus     int(3)                                   null comment '撮合订单 状态   1已撮合 未支付     2 已撮合 已支付',
    witOrderId      varchar(48)                              null comment '撮合的代付订单',
    bankAmount      decimal(19, 4) default 0.0000            not null comment '实际支付金额',
    constraint orderId
        unique (orderId)
)
    comment '交易订单表';

create index createTime
    on alipay_deal_order (createTime);

create index idx_associatedId
    on alipay_deal_order (associatedId);

create index idx_orderStatus_retain4
    on alipay_deal_order (orderStatus, retain4);

create index idx_orderStatus_retain4_orderType_submitTime
    on alipay_deal_order (orderStatus, retain4, orderType, submitTime);

create index index_name
    on alipay_deal_order (externalOrderId);

create index st
    on alipay_deal_order (orderId, orderStatus, retain4);

create index userId
    on alipay_deal_order (submitTime);














-- auto-generated definition
create table alipay_deal_order_app
(
    id           int(32) auto_increment comment '数据id'
        primary key,
    orderId      varchar(64)                              not null comment '订单号',
    orderType    int(3)                                   not null comment '订单类型:1交易,5代付',
    orderAccount varchar(32)                              null comment '订单关联商户账号',
    orderStatus  char(2)                                  not null comment '订单状态:1处理中2成功3未收到回调4失败5超时6订单申述7人工处理',
    dealDescribe text                                     null comment '交易备注',
    orderAmount  decimal(19, 2)                           not null comment '订单金额(原始金额)',
    orderIp      varchar(24)                              not null comment '订单生成IP(源头ip)',
    appOrderId   varchar(128)                             null comment '交易外部订单号',
    feeId        int(3)                                   not null comment '使用费率id',
    notify       varchar(128)                             null comment '订单异步回调地址',
    back         varchar(128)                             null comment '订单同步回调地址',
    submitTime   datetime       default CURRENT_TIMESTAMP not null comment '数据修改时间',
    createTime   datetime       default CURRENT_TIMESTAMP not null comment '数据生成时间（该时间等同于交易时间）',
    status       int(3)         default 1                 not null comment '状态:1可使用；0不可使用',
    retain1      char(24)                                 null comment '支付产品类型',
    retain2      char(6)                                  null comment '代理商是否已结算 YES 为已结算，其他为未结算',
    retain3      decimal(19, 4)                           null comment '当前订单结算费率手续费',
    currency     varchar(5)     default 'CNY'             not null comment '货币类型',
    txhash       varchar(256)                             null comment 'usdt交易hash唯一值',
    actualAmount decimal(19, 4) default 0.0000            not null comment '实际支付金额',
    constraint orderId
        unique (orderId)
)
    comment '商户订单登记表';

create index createTime
    on alipay_deal_order_app (createTime);

create index submitTime
    on alipay_deal_order_app (submitTime);

create index userId
    on alipay_deal_order_app (orderAccount);





-- auto-generated definition
create table alipay_exception_order
(
    id                int(32) auto_increment comment '数据id'
        primary key,
    orderExceptId     varchar(64)                        not null comment '流水单号',
    orderId           varchar(64)                        null comment '关联订单单号',
    exceptStatus      int(3)                             null comment '异常订单状态:1程序异常2人工异常',
    exceptType        int(3)                             not null comment '异常类型:1交易,2人工加款,4人工扣款,5代付',
    orderAccount      varchar(32)                        not null comment '订单关联商户账号',
    exceptOrderAmount varchar(64)                        null comment '异常订单金额',
    orderGenerationIp varchar(24)                        null comment '异常订单生成IP(源头ip)',
    explains          text                               not null comment '异常说明',
    operation         varchar(32)                        null comment '操作人',
    createTime        datetime default CURRENT_TIMESTAMP not null comment '数据生成时间（该时间等同于交易时间）',
    submitTime        datetime default CURRENT_TIMESTAMP not null comment '数据修改时间',
    submitSystem      varchar(12)                        null comment '数据提交系统',
    status            int(3)   default 1                 not null comment '状态:1可使用；0不可使用',
    retain1           varchar(32)                        null comment '保留字段(添加业务使用)',
    retain2           varchar(32)                        null comment '保留字段(添加业务使用)',
    retain3           varchar(32)                        null comment '保留字段(添加业务使用)'
)
    comment '拦截订单表';






-- auto-generated definition
create table alipay_file_list
(
    id             int(32) auto_increment comment '数据id'
        primary key,
    fileId         varchar(48)                        not null comment '二维码编号(本地编号)',
    fileholder     varchar(32)                        null comment '二维码持有人',
    fileNote       text                               null comment '备注',
    isFixation     char(2)                            null comment '是否为固码  1为固定码  2不为固定码',
    fixationAmount decimal(19, 2)                     null comment '若为固码,固码金额',
    code           char(10)                           not null comment '收款媒介标识:alipay(支付宝),weichar(微信),card(银行卡),other(暂未开放)',
    concealId      varchar(48)                        null comment '关联收款媒介ID',
    createTime     datetime default CURRENT_TIMESTAMP not null comment '数据生成时间',
    submitTime     datetime default CURRENT_TIMESTAMP not null comment '数据修改时间',
    status         int(3)                             not null comment '状态:1可使用；0不可使用',
    isDeal         varchar(32)                        null comment '是否逻辑删除：1删除2可用',
    isCut          char(5)                            null comment '图片是否以剪裁 Y 剪裁过   字段未空则未剪裁',
    retain1        char(2)                            null comment '预留，添加业务使用',
    retain2        char(2)                            null comment '预留，添加业务使用',
    constraint index_amount
        unique (isFixation, fixationAmount, code, concealId),
    constraint indx_qrcodeId
        unique (fileId)
)
    comment '文件列表';









-- auto-generated definition
create table alipay_invitecode
(
    id         int(32) auto_increment comment '数据id'
        primary key,
    inviteCode varchar(32)             not null comment '邀请码',
    belongUser varchar(32)             not null comment '属于账号',
    count      int(2)                  null comment '使用次数',
    createTime datetime                not null comment '数据生成时间',
    submitTime datetime                not null comment '数据修改时间',
    status     int(3)      default 1   not null comment '状态:1可使用；0不可使用',
    isDeal     varchar(32)             null comment '是否逻辑删除：1删除2可用',
    userType   varchar(12)             null comment '记录类型',
    `use`      varchar(32)             null comment '邀请码使用人',
    rebate     varchar(24) default '1' not null comment '开户返点'
)
    comment '邀请码记录表';













-- auto-generated definition
create table alipay_log
(
    id            int(32) auto_increment comment '数据id'
        primary key,
    ipAddr        varchar(24) charset utf8mb4        null comment 'IP地址',
    loginLocation varchar(32) charset utf8mb4        null comment '定位',
    loginTime     datetime                           null comment '登录时间',
    msg           text                               null comment '消息',
    useName       varchar(32) charset utf8mb4        null comment '用户名',
    createTime    datetime default CURRENT_TIMESTAMP null comment '数据创建时间',
    submitTime    datetime default CURRENT_TIMESTAMP null comment '数据最近一次修改时间',
    status        int(2)   default 1                 null comment '1数据可用2数据无用'
);











-- auto-generated definition
create table alipay_medium
(
    id           int(32) auto_increment comment '数据id',
    mediumId     varchar(48)                              not null comment '收款媒介id(本地编号)',
    mediumNumber varchar(64)                              not null comment '收款媒介登录账号',
    mediumHolder varchar(32)                              not null comment '收款媒介持有人',
    mediumNote   text                                     null comment '备注',
    mediumPhone  varchar(32)                              not null comment '收款媒介绑定手机号',
    qrcodeId     varchar(32)                              not null comment '收款媒介所属商户',
    code         char(10)                                 not null comment '收款媒介标识:alipay(支付宝),weichar(微信),card(银行卡),other(暂未开放)',
    attr         varchar(24)                              null comment '收款媒介供应标识【顶代账号】',
    createTime   datetime       default CURRENT_TIMESTAMP not null comment '数据生成时间',
    submitTime   datetime       default CURRENT_TIMESTAMP not null comment '数据修改时间',
    status       int(3)                                   not null comment '状态:1可使用；0不可使用',
    isDeal       char(3)        default '2'               null comment '是否逻辑删除：1删除2可用',
    bankcode     char(2)                                  null comment '媒介类型 R 入款 W 出款',
    mountLimit   decimal(19, 4) default 10000.0000        not null comment '当前媒介限制金额',
    account      varchar(24)                              null comment '当前媒介所属如：工商银行',
    notfiyMask   varchar(24)                              null comment '回调标识',
    primary key (id, mediumNumber, mediumId)
)
    comment '收款媒介列表';












-- auto-generated definition
create table alipay_product
(
    id          int(32) auto_increment comment '数据id'
        primary key,
    productId   varchar(32)                        not null comment '系统id【产品系统id】',
    productCode varchar(12)                        not null comment '产品code',
    productName varchar(24)                        not null comment '产品名',
    `describe`  varchar(36)                        null comment '产品备注',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '数据创建时间',
    submitTime  datetime default CURRENT_TIMESTAMP not null comment '最后一次数据修改时间',
    status      int(2)   default 1                 not null comment '数据是否可用:1数据可用2数据无用',
    retain1     char(2)                            null comment '预留，添加业务使用',
    retain2     char(2)                            null comment '预留，添加业务使用',
    retain3     char(2)                            null comment '预留，添加业务使用',
    retain4     char(2)                            null comment '预留，添加业务使用'
)
    comment '用户产品表';

create index indx_productId
    on alipay_product (productId);














-- auto-generated definition
create table alipay_qrcode_class_info
(
    id          int auto_increment
        primary key,
    min_amount  decimal(18, 4) default 0.0000            null comment '最小金额',
    max_amount  decimal(18, 4) default 0.0000            null comment '最大金额',
    amount      decimal(18, 4) default 0.0000            not null comment '金额',
    create_by   varchar(30)                              null comment '创建人',
    create_time timestamp      default CURRENT_TIMESTAMP not null comment '创建时间',
    update_by   varchar(30)                              null comment '更新者',
    update_time timestamp      default CURRENT_TIMESTAMP null comment '更新时间',
    pay_type    int(1)         default 0                 not null comment '二维码类型（0：支付宝，1：微信，2：云闪付，3：聚合码）',
    flag        tinyint(1)     default 0                 not null comment '删除标记',
    enable      tinyint(1)     default 0                 not null comment '是否启用',
    remark      varchar(100)                             null comment '备注'
)
    comment '金额等级 ';
















-- auto-generated definition
create table alipay_recharge
(
    id             int(32) auto_increment comment '数据id(主键索引)'
        primary key,
    orderId        char(48)                           not null comment '码商充值订单id订单号长度最多为32位(全文唯一索引)',
    userId         varchar(32)                        not null comment '充值码商Id(码商唯一识别号)(index索引)',
    rechargeType   char(10)                           null comment '充值交易种类:宝转宝ailipay_qr,宝转卡alipay_card,微信二维码wechar_code,..........(index索引)',
    chargeReason   text                               null comment '充值理由',
    isTripartite   char(10)                           null comment '1存在三方收款渠道；2不存在三方收款渠道(index索引)',
    orderStatus    char(10)                           not null comment '0预下单1处理中2成功3失败4财务处理',
    depositor      varchar(32)                        not null comment '充值人姓名',
    amount         decimal(19, 2)                     not null comment '充值金额',
    orderType      char(2)                            null comment '充值类型:1码商充值',
    fee            decimal(19, 2)                     null comment '手续费',
    actualAmount   decimal(19, 2)                     not null comment '真实到账金额',
    chargeBankcard char(20)                           null comment '充值银行卡',
    phone          char(13)                           null comment '充值手机号',
    notfiy         char(255)                          null comment '充值回调地址',
    chargeCard     char(32)                           null comment '银行卡号',
    chargePerson   char(32)                           null comment '充值银行卡对应的账户人',
    weight         text                               null comment '充值用户权重',
    createTime     datetime default CURRENT_TIMESTAMP not null comment '数据创建时间',
    submitTime     datetime default CURRENT_TIMESTAMP null comment '数据修改时间',
    status         int(2)   default 1                 not null comment '1数据可用2数据无用',
    retain1        char(24)                           null comment '充值发起人ip',
    backUrl        varchar(128)                       null comment '充值人同步跳转地址',
    constraint orderId_2
        unique (orderId)
)
    comment '充值记录表';

create index rechargeType
    on alipay_recharge (rechargeType);

create index userId
    on alipay_recharge (userId);













-- auto-generated definition
create table alipay_run_order
(
    id           int(32) auto_increment comment '数据id'
        primary key,
    orderId      char(48)                           not null comment '流水订单id(全局唯一索引)',
    associatedId char(48)                           not null comment '关联订单号(普通索引)',
    orderAccount char(32)                           not null comment '关联账户',
    runOrderType int(2)                             null comment '流水类型(1充值交易,2系统加款,3交易手续费,4系统扣款,5代付,6代付手续费,7冻结,8解冻,9代付手手续费冻结,10代付冻结,11增加交易点数,12点数扣除,13代理商分润，14码商分润，17人工加点数，18人工减点数，19 卡商交易加钱，20下游商户交易加款，21下游商户交易手续费扣款)',
    amount       decimal(19, 2)                     null comment '流水金额',
    generationIp char(72)                           null comment '流水关联ip',
    dealDescribe text                               not null comment '流水描述',
    acountR      varchar(32)                        null comment '入款记录账户',
    accountW     varchar(32)                        null comment '出款记录账户',
    createTime   datetime default CURRENT_TIMESTAMP not null comment '数据创建时间',
    submitTime   datetime default CURRENT_TIMESTAMP not null comment '数据最近一次修改时间',
    status       int(3)   default 1                 not null comment '状态:1可使用；0不可使用',
    runType      char(2)                            null comment '流水状态  1.自然状态 2.人工操作',
    amountType   char(2)                            null comment '1支出0收入',
    amountNow    decimal(19, 2)                     null comment '当前账户余额',
    retain4      char(20)                           null comment '记录当前代理商的资金来源账户',
    retain5      char                               null comment '备用字段添加业务使用',
    constraint orderId
        unique (orderId)
)
    comment '流水订单记录表';

create index accpunt_runOrderType
    on alipay_run_order (orderAccount, runOrderType);

create index associatedId
    on alipay_run_order (associatedId);

create index idx_runOrderType_createTime_amount_orderAccount
    on alipay_run_order (runOrderType, createTime, orderAccount);












-- auto-generated definition
create table alipay_usdt_order
(
    id              int(32) auto_increment comment '数据id'
        primary key,
    blockNumber     varchar(128)                       not null comment '区块号',
    timeStamp       datetime                           not null comment '支付时间',
    hash            varchar(128)                       not null comment '支付hash码',
    blockHash       varchar(128)                       not null comment '区块hash码',
    fromAccount     varchar(128)                       not null comment '支付账户',
    contractAddress varchar(128)                       not null comment '支付合同号',
    toAccount       varchar(128)                       not null comment '收款钱包账户',
    value           varchar(64)                        not null comment '交易金额',
    tokenName       varchar(64)                        not null comment '交易令牌名称',
    tokenSymbol     varchar(64)                        not null comment '交易令牌符号',
    fromNow         varchar(64)                        null comment '支付账户当前余额',
    toNow           varchar(64)                        null comment '收款账户当前余额',
    qrcodeNote      text                               null comment '备注',
    status          int(2)   default 1                 not null comment '状态:1可使用；0不可使用',
    isDeal          int(2)   default 2                 null comment '是否逻辑删除：1删除2可用',
    createTime      datetime default CURRENT_TIMESTAMP not null comment '数据生成时间',
    submitTime      datetime default CURRENT_TIMESTAMP not null comment '数据修改时间',
    constraint query
        unique (hash)
)
    comment 'USDT交易详细';














-- auto-generated definition
create table alipay_user_fund
(
    id                  int(32) auto_increment comment '数据id'
        primary key,
    userId              varchar(24)                              not null comment '账户id【登录账户】',
    userName            varchar(32)                              not null comment '账户昵称【登录账户名】',
    cashBalance         decimal(19, 4) default 0.0000            not null comment '现金账户【当前可取现】',
    rechargeNumber      decimal(19, 4) default 0.0000            not null comment '充值点数',
    freezeBalance       decimal(19, 4) default 0.0000            not null comment '冻结账户',
    accountBalance      decimal(19, 4) default 0.0000            not null comment '可取现账户金额【码商账户余额=现金账户+充值点数-冻结金额】',
    sumDealAmount       decimal(19, 4) default 0.0000            not null comment '累计交易额',
    sumRechargeAmount   decimal(19, 4) default 0.0000            not null comment '累计充值金额【充值成功时统计记录】',
    sumProfit           decimal(19, 4) default 0.0000            not null comment '累计利润金额',
    sumWitAmount        decimal(19, 4) default 0.0000            not null comment '累计代付金额',
    sumAgentProfit      decimal(19, 4) default 0.0000            not null comment '累计代理商利润【如果当前账户为商户则该数据为0】',
    sumOrderCount       int(32)        default 0                 not null comment '累计接单笔数',
    todayWitAmount      decimal(19)    default 0                 not null comment '当日代付金额',
    todayDealAmount     decimal(19, 4) default 0.0000            not null comment '当日接单金额',
    todayProfit         decimal(19, 4) default 0.0000            not null comment '当日接单利润【代理利润+接单利润=当日利润】',
    todayOrderCount     int(32)        default 0                 not null comment '当日接单笔数',
    todayAgentProfit    decimal(19, 4) default 0.0000            not null comment '当日代理商利润【如果当前账户为商户则该数据为0】',
    userType            char(3)                                  not null comment '用户类型,商户1 码商2渠道商户3',
    agent               varchar(24)                              null comment '代理商id【如果存在代理商则存在数据,如果不存在代理商则为null】',
    isAgent             char(3)                                  not null comment '是否为代理商:1代理商2普通码商3渠道商【分润结算类型看用户类型userType】',
    createTime          datetime       default CURRENT_TIMESTAMP not null comment '数据创建时间',
    submitTime          datetime       default CURRENT_TIMESTAMP not null comment '最后一次数据修改时间',
    status              int(2)         default 1                 not null comment '数据是否可用:1数据可用2数据无用',
    version             int(18)        default 0                 null comment '数据版本号【数据修改锁】',
    currency            varchar(12)    default 'CNY'             not null comment '货币类型',
    quota               decimal(19, 4) default 0.0000            not null comment '授权额度',
    sumOtherWitAmount   decimal(19, 4) default 0.0000            null comment '卡商接单历史',
    todayOtherWitAmount decimal(19, 4) default 0.0000            null comment '卡商当日接单历史',
    constraint user
        unique (userId)
)
    comment '用户资金账户表';
















-- auto-generated definition
create table alipay_user_fund_bak
(
    id                  int(32) auto_increment comment '数据id'
        primary key,
    userId              varchar(24)                              not null comment '账户id【登录账户】',
    userName            varchar(32)                              not null comment '账户昵称【登录账户名】',
    cashBalance         decimal(19, 4) default 0.0000            not null comment '现金账户【当前可取现】',
    rechargeNumber      decimal(19, 4) default 0.0000            not null comment '充值点数',
    freezeBalance       decimal(19, 4) default 0.0000            not null comment '冻结账户',
    accountBalance      decimal(19, 4) default 0.0000            not null comment '可取现账户金额【码商账户余额=现金账户+充值点数-冻结金额】',
    sumDealAmount       decimal(19, 4) default 0.0000            not null comment '累计交易额',
    sumRechargeAmount   decimal(19, 4) default 0.0000            not null comment '累计充值金额【充值成功时统计记录】',
    sumProfit           decimal(19, 4) default 0.0000            not null comment '累计利润金额',
    sumAgentProfit      decimal(19, 4) default 0.0000            not null comment '累计代理商利润【如果当前账户为商户则该数据为0】',
    sumOrderCount       int(32)        default 0                 not null comment '累计接单笔数',
    todayDealAmount     decimal(19, 4) default 0.0000            not null comment '当日接单金额',
    todayProfit         decimal(19, 4) default 0.0000            not null comment '当日接单利润【代理利润+接单利润=当日利润】',
    todayOrderCount     int(32)        default 0                 not null comment '当日接单笔数',
    todayAgentProfit    decimal(19, 4) default 0.0000            not null comment '当日代理商利润【如果当前账户为商户则该数据为0】',
    userType            char(3)                                  not null comment '用户类型,商户1 码商2渠道商户3',
    agent               varchar(24)                              null comment '代理商id【如果存在代理商则存在数据,如果不存在代理商则为null】',
    isAgent             char(3)                                  not null comment '是否为代理商:1代理商2普通码商3渠道商【分润结算类型看用户类型userType】',
    createTime          datetime       default CURRENT_TIMESTAMP not null comment '数据创建时间',
    submitTime          datetime       default CURRENT_TIMESTAMP not null comment '最后一次数据修改时间',
    status              int(2)         default 1                 not null comment '数据是否可用:1数据可用2数据无用',
    version             int(18)        default 0                 null comment '数据版本号【数据修改锁】',
    currency            varchar(12)    default 'CNY'             not null comment '货币类型',
    sumWitAmount        decimal(19, 4) default 0.0000            null comment '商户历史代付',
    todayWitAmount      decimal(19, 4) default 0.0000            null comment '商户当日代付',
    sumOtherWitAmount   decimal(19, 4) default 0.0000            null comment '卡商历史代付接单',
    todayOtherWitAmount decimal(19, 4) default 0.0000            null comment '卡商当日代付接单'
)
    comment '用户资金账户表备份表';

create index idx_userId_time
    on alipay_user_fund_bak (userId, createTime);

create fulltext index userId
    on alipay_user_fund_bak (userId);


















-- auto-generated definition
create table alipay_user_info
(
    id                int(32) auto_increment comment '数据id'
        primary key,
    userId            varchar(32)                              not null comment '用户id【登录账号】',
    userName          varchar(32)                              not null comment '用户昵称',
    password          varchar(128)                             not null comment 'shiro加密秘钥【登录】',
    payPasword        varchar(256)                             not null comment 'shiro加密秘钥【资金】【商户则为交易秘钥】',
    salt              varchar(32)                              null comment '秘钥加密盐值【加密算法】',
    userType          int(2)                                   null comment '用户类型,商户1 码商2',
    switchs           int(2)         default 1                 not null comment '当前用户总开关 1开启0关闭【码商商户后台控制,该值只能在后台显示】',
    userNode          varchar(128)   default '未添加'             null comment '组群备注,如果为渠道账户则为渠道商户号',
    email             varchar(48)                              null comment '邮箱【修改账号秘钥邮件发送地址】',
    agent             varchar(24)                              null comment '代理商id【如果存在代理商则存在数据,如果不存在代理商则为null】',
    isAgent           char(3)                                  null comment '是否为代理商:1代理商2普通码商【分润结算类型看用户类型userType】',
    credit            decimal(10, 4) default 100.0000          not null comment '信用等级为默认为100,掉单一次,或者出现故障一次减分0.001分,可人为加分',
    receiveOrderState int(2)         default 2                 not null comment '是否处于入款接单状态 1 接单 2 暂停接单【下游商户则为是否可以交易】',
    remitOrderState   int(2)         default 2                 not null comment '是否处于入款接单状态 1 接单 2 暂停接单【下游商户则为是否可以代付】',
    QQ                varchar(24)    default '0000'            null comment 'QQ联系方式',
    telegram          varchar(24)    default '0000'            null comment '小飞机',
    skype             varchar(24)    default '0000'            null comment 'skype',
    createTime        datetime       default CURRENT_TIMESTAMP not null comment '数据创建时间',
    submitTime        datetime                                 null comment '最后一次数据修改时间',
    status            int(2)         default 1                 not null comment '数据是否可用:1数据可用2数据无用',
    privateKey        text                                     null comment '商户私钥',
    publicKey         text                                     null comment '商户公钥',
    minAmount         decimal(18, 2) default 0.00              null comment '最小金额',
    maxAmount         decimal(18, 2) default 0.00              null comment '最大金额',
    totalAmount       decimal(18, 2) default 0.00              null comment '总金额限制',
    timesTotal        int(8)         default 0                 null comment '下单次数',
    startTime         varchar(10)                              null comment '限制时间-开始时间',
    endTime           varchar(10)                              null comment '限制时间-结束时间',
    witip             text                                     null comment '代付ip',
    queueList         text                                     null comment '供应队列code',
    dealUrl           text                                     null comment '交易IP设置【码商不填次地址无法交易】',
    qrRechargeList    text                                     null comment '码商充值,商户充值，卡商供应标识',
    autoWit           int(2)         default 0                 null comment '0:手动代付;1:自动代付',
    enterWitOpen      int(2)         default 1                 not null comment '是否开通反查接口 1  开通  0 不开通',
    interFace         varchar(256)                             null comment '商户回调接口',
    balanceUrl        varchar(24)                              null,
    constraint user
        unique (userId)
)
    comment '用户详情表';

















-- auto-generated definition
create table alipay_user_rate
(
    id         int(32) auto_increment comment '数据id'
        primary key,
    userId     varchar(32)                              not null comment '用户id【登录账号】',
    userType   int(2)                                   not null comment '用户类型,商户1 码商2',
    switchs    int(2)         default 1                 not null comment '当前用户总开关 1开启0关闭【码商商户后台控制,该数据只能在后台显示】',
    payTypr    char(32)                                 null comment '产品类型',
    fee        decimal(19, 4) default 0.0000            not null comment '费率数值',
    feeType    int(2)                                   not null comment '费率类型:1交易费率，2代付费率',
    channelId  varchar(32)                              null comment '渠道Id',
    createTime datetime       default CURRENT_TIMESTAMP not null comment '数据创建时间',
    submitTime datetime       default CURRENT_TIMESTAMP not null comment '最后一次数据修改时间',
    status     int(2)         default 1                 not null comment '数据是否可用:1数据可用2数据无用',
    retain1    char(5)        default '1'               null comment '优先级',
    retain2    char(12)       default '500'             null comment '限制金额',
    retain3    varchar(24)    default '0'               null comment '预留，添加业务使用',
    retain4    char(2)                                  null comment '预留，添加业务使用',
    deci       int unsigned   default 0                 not null comment '0是默认整数金额，1是小数金额，小数金额提高匹配的并发',
    constraint `select`
        unique (payTypr, status, feeType, userId, channelId)
)
    comment '用户产品费率表';
















-- auto-generated definition
create table alipay_withdraw
(
    id            int(32) auto_increment comment '商户后台管理员'
        primary key,
    orderId       char(48)                                 not null comment '会员提现单号',
    userId        varchar(32)                              not null comment '会员id(唯一识别号)(index索引)',
    withdrawType  char(2)                                  not null comment '商户提现1，码商提现2',
    bankNo        varchar(99)                              null,
    accname       varchar(99)                              null,
    orderStatus   char(3)                                  not null comment ' 1处理中2成功3失败4已推送处理',
    bankName      varchar(99)                              null,
    amount        decimal(19, 2) default 0.00              null comment '提现金额',
    fee           decimal(19, 2) default 0.00              null comment '手续费',
    actualAmount  decimal(19, 2) default 0.00              null comment '真实到账金额',
    mobile        char(20)                                 null comment '手机号',
    notify        varchar(200)                             null comment '提现成功回调地址',
    createTime    datetime       default CURRENT_TIMESTAMP not null comment '数据创建时间',
    submitTime    datetime       default CURRENT_TIMESTAMP null comment '数据修改时间',
    status        int(2)         default 1                 not null comment '1数据可用2数据无用',
    witType       varchar(24)                              null comment '代付产品类型',
    appOrderId    varchar(42)                              null comment '代付下游商户订单号',
    weight        text                                     null comment '用户代付权重',
    retain1       char(5)                                  null comment '代付来源  1API   2后台   3 码商客户端',
    retain2       char(24)                                 null comment '代付发起ip地址',
    apply         varchar(50)                              null comment '商户后台管理员（申请人）',
    approval      varchar(50)                              null comment '审核人',
    comment       varchar(200)                             null comment '审核意见',
    bankcode      varchar(24)                              null comment '如果是银行代付 银行标识号',
    witChannel    varchar(32)                              null comment '代付渠道',
    channelId     varchar(32)                              null comment '当匹配的出款渠道为自营渠道时候实际手动出款渠道',
    currency      varchar(5)     default 'CNY'             not null comment '货币类型',
    ethFee        int(2)         default 0                 not null comment 'eth手续费是否结算，1已结算2未结算',
    txhash        varchar(256)                             null comment 'usdt交易hash唯一值',
    pushOrder     int(2)         default 1                 not null comment '是否推送代付订单， 1 已推送  0 未推送，默认为已推送',
    enterStatus   int            default 0                 null comment ' 代付出款确认状态  0 为未确认  1 为已确认 ',
    macthMsg      varchar(255)   default ''                null comment '撮合解释',
    macthStatus   int(3)                                   null comment '撮合订单 状态   1已撮合 未支付     2 已撮合 已支付',
    payStatus     int(3)         default 2                 null comment '结算状态  1 已扣款结算   2 未扣款结算',
    macthLock     int(3)         default 0                 null comment '撮合锁定当前不可以进行任何操作，  默认不锁定 0    1 锁定',
    moreMacth     int(3)         default 0                 null comment '是否可以多次撮合[是否挂起]， 0 不可以  1 可以      可以就是挂起',
    macthCount    int(5)         default 0                 null comment '撮合次数',
    macthTime     datetime                                 null comment '数据创建时间',
    watingTime    int(24)        default 600               null comment '推送等待时间',
    sgin          varchar(199)                             null,
    amount1       varchar(199)                             null,
    actualAmount1 varchar(199)                             null,
    fee1          varchar(199)                             null,
    constraint `order`
        unique (orderId),
    constraint orderId_2
        unique (orderId, orderStatus),
    constraint orderapp
        unique (appOrderId, userId)
)
    comment '会员提现记录表';

create index createTime
    on alipay_withdraw (createTime);

create index idx_ethFee_currency_orderStatus_createTime_orderId
    on alipay_withdraw (ethFee, currency, orderStatus, createTime, orderId);
























create
  function queryParentAgents(child varchar(32)) returns varchar(4000) security invoker
BEGIN
	DECLARE parents VARCHAR(4000);
	DECLARE tmpParent VARCHAR(32);
	DECLARE existed TINYINT(1);
	SET existed=0;
	SET parents='$';
	SET tmpParent= child;
	SET parents =CONCAT(parents,',',tmpParent);
SELECT COUNT(1) INTO existed FROM product_qrcode_user WHERE accountId = tmpParent;

IF (existed>0)
	THEN
SELECT agent INTO tmpParent FROM product_qrcode_user WHERE accountId = tmpParent;
WHILE(existed>0 AND tmpParent IS NOT NULL ) DO
				SET parents =CONCAT(parents,',',tmpParent);
SELECT COUNT(1) INTO existed FROM product_qrcode_user WHERE accountId = tmpParent;
SELECT agent INTO tmpParent FROM product_qrcode_user WHERE accountId = tmpParent;
END WHILE;
RETURN parents;
ELSE
		RETURN NULL;
END IF;
END;








