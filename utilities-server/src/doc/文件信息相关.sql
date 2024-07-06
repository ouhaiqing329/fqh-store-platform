create table file_info
(
    id          varchar(32)                        not null
        primary key comment '主键',
    file_path   varchar(255)                       not null comment '文件路径',
    file_name   varchar(128)                       not null comment '文件名称',
    file_type   varchar(16)                        not null comment '文件类型',
    content     longtext                           null comment '文件内容',
    creator     varchar(16)                        null comment '创建人',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    modifier    varchar(16)                        null comment '更新人',
    delete_mark int      default 0                 not null
);

-- 文件夹管理
create table folder_management
(
    id          varchar(32)                        not null
        primary key comment '主键',
    parent_folder_id   varchar(32)                 not null comment '上级文件夹Id',
    folder_name   varchar(128)                     not null comment '文件夹名称',
    ranking     int                                not null comment '排序',
    can_read    varchar(4)                          null comment '是否可读',
    creator     varchar(16)                        null comment '创建人',
    create_time datetime default CURRENT_TIMESTAMP null comment '创建时间',
    modifier    varchar(16)                        null comment '更新人',
    delete_mark int      default 0                 not null
);

-- 文件夹--文件关联表
create table re_folder_file
(
    id          varchar(32)                        not null
        primary key comment '主键',
    file_id   varchar(32)                          not null comment '文件Id',
    folder_id   varchar(32)                        not null comment '文件夹Id',
    ranking     int                                not null comment '排序'
);
