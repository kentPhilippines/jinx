-- cp_admin.gen_table definition

CREATE TABLE `gen_table`
(
    `table_id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `table_name`      varchar(200)  DEFAULT '' COMMENT '表名称',
    `table_comment`   varchar(500)  DEFAULT '' COMMENT '表描述',
    `class_name`      varchar(100)  DEFAULT '' COMMENT '实体类名称',
    `tpl_category`    varchar(200)  DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
    `package_name`    varchar(100)  DEFAULT NULL COMMENT '生成包路径',
    `module_name`     varchar(30)   DEFAULT NULL COMMENT '生成模块名',
    `business_name`   varchar(30)   DEFAULT NULL COMMENT '生成业务名',
    `function_name`   varchar(50)   DEFAULT NULL COMMENT '生成功能名',
    `function_author` varchar(50)   DEFAULT NULL COMMENT '生成功能作者',
    `options`         varchar(1000) DEFAULT NULL COMMENT '其它生成选项',
    `create_by`       varchar(64)   DEFAULT '' COMMENT '创建者',
    `create_time`     datetime      DEFAULT NULL COMMENT '创建时间',
    `update_by`       varchar(64)   DEFAULT '' COMMENT '更新者',
    `update_time`     datetime      DEFAULT NULL COMMENT '更新时间',
    `remark`          varchar(500)  DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`table_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='代码生成业务表';


-- cp_admin.gen_table_column definition

CREATE TABLE `gen_table_column`
(
    `column_id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
    `table_id`       varchar(64)  DEFAULT NULL COMMENT '归属表编号',
    `column_name`    varchar(200) DEFAULT NULL COMMENT '列名称',
    `column_comment` varchar(500) DEFAULT NULL COMMENT '列描述',
    `column_type`    varchar(100) DEFAULT NULL COMMENT '列类型',
    `java_type`      varchar(500) DEFAULT NULL COMMENT 'JAVA类型',
    `java_field`     varchar(200) DEFAULT NULL COMMENT 'JAVA字段名',
    `is_pk`          char(1)      DEFAULT NULL COMMENT '是否主键（1是）',
    `is_increment`   char(1)      DEFAULT NULL COMMENT '是否自增（1是）',
    `is_required`    char(1)      DEFAULT NULL COMMENT '是否必填（1是）',
    `is_insert`      char(1)      DEFAULT NULL COMMENT '是否为插入字段（1是）',
    `is_edit`        char(1)      DEFAULT NULL COMMENT '是否编辑字段（1是）',
    `is_list`        char(1)      DEFAULT NULL COMMENT '是否列表字段（1是）',
    `is_query`       char(1)      DEFAULT NULL COMMENT '是否查询字段（1是）',
    `query_type`     varchar(200) DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
    `html_type`      varchar(200) DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
    `dict_type`      varchar(200) DEFAULT '' COMMENT '字典类型',
    `sort`           int(11) DEFAULT NULL COMMENT '排序',
    `create_by`      varchar(64)  DEFAULT '' COMMENT '创建者',
    `create_time`    datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`      varchar(64)  DEFAULT '' COMMENT '更新者',
    `update_time`    datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`column_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='代码生成业务表字段';


-- auto-generated definition
create table sys_config
(
    config_id    int(5) auto_increment comment '参数主键'
        primary key,
    config_name  varchar(100) default '' null comment '参数名称',
    config_key   varchar(100) default '' null comment '参数键名',
    config_value varchar(500) default '' null comment '参数键值',
    config_type  char         default 'N' null comment '系统内置（Y是 N否）',
    create_by    varchar(64)  default '' null comment '创建者',
    create_time  datetime null comment '创建时间',
    update_by    varchar(64)  default '' null comment '更新者',
    update_time  datetime null comment '更新时间',
    remark       varchar(500) null comment '备注'
) comment '参数配置表' charset = utf8mb4;

-- auto-generated definition
create table sys_dept
(
    dept_id      bigint auto_increment comment '部门id'
        primary key,
    parent_id    bigint      default 0 null comment '父部门id',
    ancestors    varchar(50) default '' null comment '祖级列表',
    dept_name    varchar(30) default '' null comment '部门名称',
    order_num    int(4) default 0 null comment '显示顺序',
    leader       varchar(20) null comment '负责人',
    phone        varchar(11) null comment '联系电话',
    email        varchar(50) null comment '邮箱',
    status       char        default '0' null comment '部门状态（0正常 1停用）',
    del_flag     char        default '0' null comment '删除标志（0代表存在 2代表删除）',
    create_by    varchar(64) default '' null comment '创建者',
    create_time  datetime null comment '创建时间',
    update_by    varchar(64) default '' null comment '更新者',
    update_time  datetime null comment '更新时间',
    merchant_id  varchar(50) null comment '商户id',
    account_type int(2) default 0 null comment '0:平台，1:商户子账户'
) comment '部门表' charset = utf8mb4;

-- auto-generated definition
create table sys_dict_data
(
    dict_code   bigint auto_increment comment '字典编码'
        primary key,
    dict_sort   int(4) default 0 null comment '字典排序',
    dict_label  varchar(100) default '' null comment '字典标签',
    dict_value  varchar(300) default '' null comment '字典键值',
    dict_type   varchar(100) default '' null comment '字典类型',
    css_class   varchar(100) null comment '样式属性（其他样式扩展）',
    list_class  varchar(100) null comment '表格回显样式',
    is_default  char         default 'N' null comment '是否默认（Y是 N否）',
    status      char         default '0' null comment '状态（0正常 1停用）',
    create_by   varchar(64)  default '' null comment '创建者',
    create_time datetime null comment '创建时间',
    update_by   varchar(64)  default '' null comment '更新者',
    update_time datetime null comment '更新时间',
    remark      varchar(500) null comment '备注'
) comment '字典数据表' charset = utf8mb4;

-- auto-generated definition
create table sys_dict_type
(
    dict_id     bigint auto_increment comment '字典主键'
        primary key,
    dict_name   varchar(100) default '' null comment '字典名称',
    dict_type   varchar(100) default '' null comment '字典类型',
    status      char         default '0' null comment '状态（0正常 1停用）',
    create_by   varchar(64)  default '' null comment '创建者',
    create_time datetime null comment '创建时间',
    update_by   varchar(64)  default '' null comment '更新者',
    update_time datetime null comment '更新时间',
    remark      varchar(500) null comment '备注',
    constraint dict_type
        unique (dict_type)
) comment '字典类型表' charset = utf8mb4;

-- auto-generated definition
create table sys_ip_white
(
    id          int(32) auto_increment comment '数据id'
        primary key,
    ip_type     varchar(12) null comment 'ip内别',
    ip_addree   varchar(128) null comment 'ip地址',
    create_by   varchar(128) null comment '创建人',
    update_by   varchar(64) null comment '修改人',
    remark      text null comment '备注',
    create_time datetime default CURRENT_TIMESTAMP not null comment '数据生成时间',
    update_time datetime default CURRENT_TIMESTAMP not null comment '数据修改时间'
) comment 'ip列表';

-- auto-generated definition
create table sys_job
(
    job_id          bigint auto_increment comment '任务ID',
    job_name        varchar(64)  default ''        not null comment '任务名称',
    job_group       varchar(64)  default 'DEFAULT' not null comment '任务组名',
    invoke_target   varchar(500)                   not null comment '调用目标字符串',
    cron_expression varchar(255) default '' null comment 'cron执行表达式',
    misfire_policy  varchar(20)  default '3' null comment '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
    concurrent      char         default '1' null comment '是否并发执行（0允许 1禁止）',
    status          char         default '0' null comment '状态（0正常 1暂停）',
    create_by       varchar(64)  default '' null comment '创建者',
    create_time     datetime null comment '创建时间',
    update_by       varchar(64)  default '' null comment '更新者',
    update_time     datetime null comment '更新时间',
    remark          varchar(500) default '' null comment '备注信息',
    primary key (job_id, job_name, job_group)
) comment '定时任务调度表' charset = utf8mb4;

-- auto-generated definition
create table sys_job_log
(
    job_log_id     bigint auto_increment comment '任务日志ID'
        primary key,
    job_name       varchar(64)  not null comment '任务名称',
    job_group      varchar(64)  not null comment '任务组名',
    invoke_target  varchar(500) not null comment '调用目标字符串',
    job_message    varchar(500) null comment '日志信息',
    status         char          default '0' null comment '执行状态（0正常 1失败）',
    exception_info varchar(2000) default '' null comment '异常信息',
    create_time    datetime null comment '创建时间'
) comment '定时任务调度日志表' charset = utf8mb4;

-- auto-generated definition
create table sys_logininfor
(
    info_id        bigint auto_increment comment '访问ID'
        primary key,
    login_name     varchar(50)  default '' null comment '登录账号',
    ipaddr         varchar(50)  default '' null comment '登录IP地址',
    login_location varchar(255) default '' null comment '登录地点',
    browser        varchar(50)  default '' null comment '浏览器类型',
    os             varchar(50)  default '' null comment '操作系统',
    status         char         default '0' null comment '登录状态（0成功 1失败）',
    msg            varchar(255) default '' null comment '提示消息',
    login_time     datetime null comment '访问时间'
) comment '系统访问记录' charset = utf8mb4;

-- auto-generated definition
create table sys_menu
(
    menu_id     bigint auto_increment comment '菜单ID'
        primary key,
    menu_name   varchar(50) not null comment '菜单名称',
    parent_id   bigint       default 0 null comment '父菜单ID',
    order_num   int(4) default 0 null comment '显示顺序',
    url         varchar(200) default '#' null comment '请求地址',
    target      varchar(20)  default '' null comment '打开方式（menuItem页签 menuBlank新窗口）',
    menu_type   char         default '' null comment '菜单类型（M目录 C菜单 F按钮）',
    visible     char         default '0' null comment '菜单状态（0显示 1隐藏）',
    perms       varchar(100) null comment '权限标识',
    icon        varchar(100) default '#' null comment '菜单图标',
    create_by   varchar(64)  default '' null comment '创建者',
    create_time datetime null comment '创建时间',
    update_by   varchar(64)  default '' null comment '更新者',
    update_time datetime null comment '更新时间',
    remark      varchar(500) default '' null comment '备注'
) comment '菜单权限表' charset = utf8mb4;

-- auto-generated definition
create table sys_notice
(
    notice_id      int(4) auto_increment comment '公告ID'
        primary key,
    notice_title   varchar(50)              not null comment '公告标题',
    notice_type    char                     not null comment '公告类型（1通知 2公告）',
    notice_content text null comment '公告内容',
    status         char         default '0' null comment '公告状态（0正常 1关闭）',
    create_by      varchar(64)  default '' null comment '创建者',
    create_time    datetime null comment '创建时间',
    update_by      varchar(64)  default '' null comment '更新者',
    update_time    datetime null comment '更新时间',
    remark         varchar(255) default '1' not null comment '角色id,可查看此公告的角色ID'
) comment '通知公告表' charset = utf8mb4;

-- auto-generated definition
create table sys_oper_log
(
    oper_id        bigint auto_increment comment '日志主键'
        primary key,
    title          varchar(50)   default '' null comment '模块标题',
    business_type  int(2) default 0 null comment '业务类型（0其它 1新增 2修改 3删除）',
    method         varchar(100)  default '' null comment '方法名称',
    request_method varchar(10)   default '' null comment '请求方式',
    operator_type  int(1) default 0 null comment '操作类别（0其它 1后台用户 2手机端用户）',
    oper_name      varchar(50)   default '' null comment '操作人员',
    dept_name      varchar(50)   default '' null comment '部门名称',
    oper_url       varchar(255)  default '' null comment '请求URL',
    oper_ip        varchar(50)   default '' null comment '主机地址',
    oper_location  varchar(255)  default '' null comment '操作地点',
    oper_param     varchar(2000) default '' null comment '请求参数',
    json_result    varchar(2000) default '' null comment '返回参数',
    status         int(1) default 0 null comment '操作状态（0正常 1异常）',
    error_msg      varchar(2000) default '' null comment '错误消息',
    oper_time      datetime null comment '操作时间'
) comment '操作日志记录';

-- auto-generated definition
create table sys_post
(
    post_id      bigint auto_increment comment '岗位ID'
        primary key,
    post_code    varchar(64) not null comment '岗位编码',
    post_name    varchar(50) not null comment '岗位名称',
    post_sort    int(4) not null comment '显示顺序',
    status       char        not null comment '状态（0正常 1停用）',
    create_by    varchar(64) default '' null comment '创建者',
    create_time  datetime null comment '创建时间',
    update_by    varchar(64) default '' null comment '更新者',
    update_time  datetime null comment '更新时间',
    remark       varchar(500) null comment '备注',
    merchant_id  varchar(50) null comment '商户账号',
    account_type int(2) default 0 null comment '0:平台类型1:商户子账号类型'
) comment '岗位信息表' charset = utf8mb4;

-- auto-generated definition
create table sys_role
(
    role_id      bigint auto_increment comment '角色ID'
        primary key,
    role_name    varchar(30)  not null comment '角色名称',
    role_key     varchar(100) not null comment '角色权限字符串',
    role_sort    int(4) not null comment '显示顺序',
    data_scope   char        default '1' null comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    status       char         not null comment '角色状态（0正常 1停用）',
    del_flag     char        default '0' null comment '删除标志（0代表存在 2代表删除）',
    create_by    varchar(64) default '' null comment '创建者',
    create_time  datetime null comment '创建时间',
    update_by    varchar(64) default '' null comment '更新者',
    update_time  datetime null comment '更新时间',
    remark       varchar(500) null comment '备注',
    merchant_id  varchar(50) null comment '商户账户',
    account_type int(2) default 0 null comment '0:平台用户，1:商户用户'
) comment '角色信息表' charset = utf8mb4;

-- auto-generated definition
create table sys_role_dept
(
    role_id bigint not null comment '角色ID',
    dept_id bigint not null comment '部门ID',
    primary key (role_id, dept_id)
) comment '角色和部门关联表' charset = utf8mb4;

-- auto-generated definition
create table sys_role_menu
(
    role_id bigint not null comment '角色ID',
    menu_id bigint not null comment '菜单ID',
    primary key (role_id, menu_id)
) comment '角色和菜单关联表' charset = utf8mb4;

-- auto-generated definition
create table sys_user
(
    user_id       bigint auto_increment comment '用户ID'
        primary key,
    dept_id       bigint null comment '部门ID',
    login_name    varchar(50)              not null comment '登录账号',
    user_name     varchar(50)              not null comment '用户昵称',
    user_type     varchar(2)   default '00' null comment '用户类型（00系统用户）',
    email         varchar(50)  default '' null comment '用户邮箱',
    phonenumber   varchar(11)  default '' null comment '手机号码',
    sex           char         default '0' null comment '用户性别（0男 1女 2未知）',
    avatar        varchar(100) default '' null comment '头像路径',
    password      varchar(50)  default '' null comment '登陆密码',
    fund_password varchar(50) null comment '资金密码（商户用于提现密码）',
    salt          varchar(20)  default '' null comment '盐加密',
    status        char         default '0' not null comment '帐号状态（0正常 1停用）',
    del_flag      char         default '0' null comment '删除标志（0代表存在 2代表删除）',
    login_ip      varchar(50)  default '' null comment '最后登陆IP',
    login_date    datetime null comment '最后登陆时间',
    create_by     varchar(64)  default '' null comment '创建者',
    create_time   datetime null comment '创建时间',
    update_by     varchar(64)  default '' null comment '更新者',
    update_time   datetime null comment '更新时间',
    remark        varchar(500) null comment '备注',
    merchant_id   varchar(100) collate utf8_bin null comment '商户ID（后台管理员标识）',
    is_bind       char charset utf8 default '0' null comment '是否绑定Google验证码',
    account_type  int(2) default 0 null comment '0:平台用户，1:商户用户',
    constraint idex_login_name
        unique (login_name)
) comment '用户信息表' charset = utf8mb4;

-- auto-generated definition
create table sys_user_google
(
    id          int auto_increment comment '数据ID'
        primary key,
    login_name  varchar(50) charset utf8 not null comment '后台登陆账号（唯一）',
    google_url  varchar(255) charset utf8 null comment '二维码地址',
    secret_key  varchar(32) charset utf8 null comment '密钥',
    host        varchar(400) charset utf8 null comment '服务器地址',
    expire_time int null comment '绑定过期时间（分）',
    create_by   varchar(50) charset utf8 null comment '创建人',
    create_time timestamp default CURRENT_TIMESTAMP null comment '绑定时间',
    remark      varchar(100) charset utf8 null comment '备注',
    constraint indx_login
        unique (login_name)
) charset = utf8mb4;

-- auto-generated definition
create table sys_user_online
(
    sessionId        varchar(50)  default '' not null comment '用户会话id'
        primary key,
    login_name       varchar(50)  default '' null comment '登录账号',
    dept_name        varchar(50)  default '' null comment '部门名称',
    ipaddr           varchar(50)  default '' null comment '登录IP地址',
    login_location   varchar(255) default '' null comment '登录地点',
    browser          varchar(50)  default '' null comment '浏览器类型',
    os               varchar(50)  default '' null comment '操作系统',
    status           varchar(10)  default '' null comment '在线状态on_line在线off_line离线',
    start_timestamp  datetime null comment 'session创建时间',
    last_access_time datetime null comment 'session最后访问时间',
    expire_time      int(5) default 0 null comment '超时时间，单位为分钟'
) comment '在线用户记录' charset = utf8mb4;

-- auto-generated definition
create table sys_user_post
(
    user_id bigint not null comment '用户ID',
    post_id bigint not null comment '岗位ID',
    primary key (user_id, post_id)
) comment '用户与岗位关联表' charset = utf8mb4;
-- auto-generated definition
create table sys_user_role
(
    user_id bigint not null comment '用户ID',
    role_id bigint not null comment '角色ID',
    primary key (user_id, role_id)
) comment '用户和角色关联表' charset = utf8mb4;


