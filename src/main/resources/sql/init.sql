# create table sixlab
# create table if not exists

create table if not exists post_attr
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
    on post_attr (attr_code);

create index post_attr_attr_type_index
    on post_attr (attr_type);

create table if not exists post_attr_relate
(
    id int auto_increment
        primary key,
    post_id varchar(20) null,
    attr_id varchar(20) null,
    create_time datetime null
)
    comment 'post 和 属性的关联关系';

create index post_attr_relate_attr_id_index
    on post_attr_relate (attr_id);

create index post_attr_relate_post_id_attr_id_index
    on post_attr_relate (post_id, attr_id);

create index post_attr_relate_post_id_index
    on post_attr_relate (post_id);

create table if not exists post_info
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
    on post_info (author_id);

create index post_info_post_status_index
    on post_info (post_status);

create index post_info_post_type_index
    on post_info (post_type);

create index post_info_post_type_post_status_index
    on post_info (post_type, post_status);

create table if not exists rank_group
(
    id int auto_increment
        primary key,
    parent_id int null,
    group_name varchar(20) null,
    group_level int null,
    group_order int null,
    create_time datetime null
)
    comment '榜单组';

create index rank_group_group_level_index
    on rank_group (group_level);

create index rank_group_group_order_index
    on rank_group (group_order);

create index rank_group_parent_id_index
    on rank_group (parent_id);

create table if not exists rank_item
(
    id int auto_increment
        primary key,
    group_id int null,
    item_rank int null,
    item_name varchar(50) null,
    item_hit int null,
    item_change int null comment '变化，1:升，-1:降，0:不变',
    item_intro varchar(1000) null,
    baike_id varchar(20) null,
    douban_id varchar(20) null,
    create_time datetime null
)
    comment '榜单项';

create index rank_item_group_id_index
    on rank_item (group_id);

create index rank_item_group_id_item_rank_index
    on rank_item (group_id, item_rank);

create table if not exists site_menu
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
    on site_menu (menu_position);

create table if not exists six_data
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
    on six_data (code);

create table if not exists six_notify_config
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
    on six_notify_config (code);

create index six_notify_config_type_index
    on six_notify_config (type);

