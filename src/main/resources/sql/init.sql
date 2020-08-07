create table sixlab.post_attr
(
    id int auto_increment
        primary key,
    attr_code varchar(20) null,
    attr_type varchar(20) null comment '类型:category-分类，tag-标签',
    attr_name varchar(20) null,
    attr_summary varchar(1000) null,
    create_time datetime null
)
    comment '文章属性表';

create index post_attr_attr_code_index
    on sixlab.post_attr (attr_code);

create index post_attr_attr_type_index
    on sixlab.post_attr (attr_type);

create table sixlab.post_attr_relate
(
    id int auto_increment
        primary key,
    post_id varchar(20) null,
    attr_id varchar(20) null,
    create_time datetime null
)
    comment 'post 和 属性的关联关系';

create index post_attr_relate_attr_id_index
    on sixlab.post_attr_relate (attr_id);

create index post_attr_relate_post_id_attr_id_index
    on sixlab.post_attr_relate (post_id, attr_id);

create index post_attr_relate_post_id_index
    on sixlab.post_attr_relate (post_id);

create table sixlab.post_info
(
    id int auto_increment
        primary key,
    alias_name varchar(20) null,
    author_id int null,
    author_name varchar(50) null,
    post_type varchar(20) null,
    post_title varchar(200) null,
    post_summary varchar(1000) null,
    post_content longtext null,
    post_status varchar(10) null comment '状态: wait-等待，open-公开',
    comment_status varchar(10) null comment '评论状态：open-开放，close-关闭',
    comment_count int null,
    post_date datetime null,
    post_modified datetime null,
    create_time datetime null
)
    comment '文章信息表';

create index post_info_author_id_index
    on sixlab.post_info (author_id);

create index post_info_post_status_index
    on sixlab.post_info (post_status);

create index post_info_post_type_index
    on sixlab.post_info (post_type);

create index post_info_post_type_post_status_index
    on sixlab.post_info (post_type, post_status);

create table sixlab.site_menu
(
    id int auto_increment
        primary key,
    menu_position varchar(20) null,
    menu_level int null,
    menu_name varchar(20) null,
    menu_path varchar(200) null,
    menu_summary varchar(1000) null,
    menu_order int null,
    create_time datetime null
)
    comment '站点菜单';

create index site_menu_menu_position_index
    on sixlab.site_menu (menu_position);

create table sixlab.six_data
(
    id int auto_increment
        primary key,
    code varchar(255) null,
    data decimal(18,4) null,
    text varchar(255) null,
    status int null,
    create_time datetime(6) null
)
    comment '数据';

create index six_data_code_index
    on sixlab.six_data (code);

create table sixlab.six_notify_config
(
    id int auto_increment
        primary key,
    code varchar(255) null,
    type int null,
    rise decimal(18,4) null,
    status int null,
    create_time datetime(6) null
)
    comment 'notify';

create index six_notify_config_code_index
    on sixlab.six_notify_config (code);

create index six_notify_config_type_index
    on sixlab.six_notify_config (type);

