create table ac_user
(
    id varchar(20) not null primary key,
    username varchar(32) not null comment '账号名',
    password varchar(255) not null comment 'hash存储',
    sex tinyint(4) not null default 0 comment '性别[0-不详 1-男  2-女]',
    birthday date comment '出生日期',
    nick_name varchar(128) comment '昵称',
    head_portrait text comment '头像',
    auth_information_id varchar(20) comment '认证信息[对应认证信息表Id,扩展使用]',
    detail_information_id varchar(20) comment '详细信息【扩展使用】',
    last_login_time datetime comment '最后登录时间',
    enabled      tinyint(1) not null default 1 comment '是否禁用 1-启用 0-禁用',
    modify_time  datetime    not null default current_timestamp() comment '更新时间',
    created_time datetime    not null default current_timestamp() comment '插入时间',
    creator      varchar(20) not null comment '创建人',
    modifier     varchar(20) comment '更新人',
    delete_mark  tinyint(1) not null default 0 comment '删除标识 1-已删除 | 2-正常'

) comment '用户表';

create table ac_group_role
(
    id           varchar(20) not null primary key,
    parent_id    varchar(20) comment '上级Id',
    name         varchar(50) not null comment '角色名称',
    code         varchar(32) not null comment '权限编码',
    type         tinyint(4) not null comment '角色类型 1-角色组|0-角色',
    enabled      tinyint(1) not null default 1 comment '是否禁用 1-启用 0-禁用',
    modify_time  datetime    not null default current_timestamp() comment '更新时间',
    created_time datetime    not null default current_timestamp() comment '插入时间',
    creator      varchar(20) not null comment '创建人',
    modifier     varchar(20) comment '更新人',
    delete_mark  tinyint(1) not null default 0 comment '删除标识 1-已删除 | 2-正常'
) comment = '角色/角色组表';



create table ac_user_role
(
    id           varchar(20) not null primary key,
    user_id      varchar(20) not null comment '用户id',
    role_id      varchar(20) not null comment '角色id',
    modify_time  datetime    not null default current_timestamp() comment '更新时间',
    created_time datetime    not null default current_timestamp() comment '插入时间',
    creator      varchar(20) not null comment '创建人',
    modifier     varchar(20) comment '更新人',
    delete_mark  tinyint(1) not null default 0 comment '删除标识 1-已删除 | 2-正常'
) comment = '用户角色表';


create table ac_resource
(
    id           varchar(20) not null primary key,
    parent_id    varchar(20) comment '父级Id',
    name         varchar(64) not null comment '菜单/功能名称',
    code         varchar(32) not null comment '菜单/功能编码',
    type         tinyint(4) not null comment '资源类型 1-菜单；0-功能',
    enabled      tinyint(1) not null default 1 comment '是否禁用 1-启用 0-禁用',
    sort         tinyint(4) not null comment '排序',
    icon         varchar(255) comment '图标',
    modify_time  datetime    not null default current_timestamp() comment '更新时间',
    created_time datetime    not null default current_timestamp() comment '插入时间',
    creator      varchar(20) not null comment '创建人',
    modifier     varchar(20) comment '更新人',
    delete_mark  tinyint(1) not null default 0 comment '删除标识 1-已删除 | 2-正常'
) comment = '权限详情表';



create table ac_app_access_log
(
    id           varchar(20)   not null primary key,
    target_url   varchar(255)  not null comment '访问的url',
    query_params longtext      not null comment 'get和post参数',
    ua           varchar(255)  not null comment '访问ua',
    ip           varchar(32)   not null comment '访问ip',
    note         varchar(1000) not null comment 'json格式备注字段',
    created_time timestamp     not null default current_timestamp
) comment = '用户操作记录表';