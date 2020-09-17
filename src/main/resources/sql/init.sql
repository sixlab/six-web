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
	post_id int null,
	attr_id int null,
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
    alias_name varchar(50) null,
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
    group_type varchar(20) null comment '类型，group/item',
    group_code varchar(100) null,
    group_name varchar(20) null,
    group_level int null,
    group_order int null,
    group_link varchar(1000) null,
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

create index six_data_code_create_time_data_index
    on six_data (code, create_time, data);

create index six_data_code_create_time_index
    on six_data (code, create_time);

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

INSERT INTO rank_group
    (id, parent_id, group_type, group_code, group_name, group_level, group_order, group_link)
VALUES
(1, null, 'group', '/category?c=11', '生活', 1, 100, null),
(2, null, 'group', '/category?c=1', '娱乐', 1, 111, null),
(3, null, 'group', '/category?c=10', '小说', 1, 149, null),
(4, null, 'group', '/category?c=16', '游戏', 1, 161, null),
(5, null, 'group', '/category?c=9', '人物', 1, 166, null),
(6, null, 'group', '/category?c=18', '汽车', 1, 178, null),
(7, null, 'group', '/category?c=513', '热点', 1, 190, null),
(8, null, 'group', '/category?c=15', '科技', 1, 194, null),
(9, null, 'group', '/category?c=518', '旅游', 1, 197, null),
(10, 3, 'item', '/buzz?b=7', '全部小说', 2, 150, 'http://top.baidu.com/buzz?b=7'),
(11, 3, 'item', '/buzz?b=353', '玄幻奇幻', 2, 151, 'http://top.baidu.com/buzz?b=353'),
(12, 3, 'item', '/buzz?b=355', '都市言情', 2, 152, 'http://top.baidu.com/buzz?b=355'),
(13, 3, 'item', '/buzz?b=354', '武侠仙侠', 2, 153, 'http://top.baidu.com/buzz?b=354'),
(14, 3, 'item', '/buzz?b=1508', '青春校园', 2, 154, 'http://top.baidu.com/buzz?b=1508'),
(15, 3, 'item', '/buzz?b=1509', '穿越架空', 2, 155, 'http://top.baidu.com/buzz?b=1509'),
(16, 3, 'item', '/buzz?b=356', '惊悚悬疑', 2, 156, 'http://top.baidu.com/buzz?b=356'),
(17, 3, 'item', '/buzz?b=459', '历史军事', 2, 157, 'http://top.baidu.com/buzz?b=459'),
(18, 3, 'item', '/buzz?b=1512', '游戏竞技', 2, 158, 'http://top.baidu.com/buzz?b=1512'),
(19, 3, 'item', '/buzz?b=1510', '耽美同人', 2, 159, 'http://top.baidu.com/buzz?b=1510'),
(20, 3, 'item', '/buzz?b=1513', '文学经典', 2, 160, 'http://top.baidu.com/buzz?b=1513'),
(21, 4, 'item', '/buzz?b=173', '网页游戏', 2, 162, 'http://top.baidu.com/buzz?b=173'),
(22, 4, 'item', '/buzz?b=62', '网络游戏', 2, 163, 'http://top.baidu.com/buzz?b=62'),
(23, 4, 'item', '/buzz?b=524', '手机游戏', 2, 164, 'http://top.baidu.com/buzz?b=524'),
(24, 4, 'item', '/buzz?b=451', '单机游戏', 2, 165, 'http://top.baidu.com/buzz?b=451'),
(25, 5, 'item', '/buzz?b=258', '热点人物', 2, 167, 'http://top.baidu.com/buzz?b=258'),
(26, 5, 'item', '/buzz?b=260', '名家人物', 2, 168, 'http://top.baidu.com/buzz?b=260'),
(27, 5, 'item', '/buzz?b=612', '公益人物', 2, 169, 'http://top.baidu.com/buzz?b=612'),
(28, 5, 'item', '/buzz?b=261', '财经人物', 2, 170, 'http://top.baidu.com/buzz?b=261'),
(29, 5, 'item', '/buzz?b=255', '体坛人物', 2, 171, 'http://top.baidu.com/buzz?b=255'),
(30, 5, 'item', '/buzz?b=454', '主持人', 2, 172, 'http://top.baidu.com/buzz?b=454'),
(31, 5, 'item', '/buzz?b=259', '历史人物', 2, 173, 'http://top.baidu.com/buzz?b=259'),
(32, 5, 'item', '/buzz?b=257', '互联网人', 2, 174, 'http://top.baidu.com/buzz?b=257'),
(33, 5, 'item', '/buzz?b=1570', '女明星', 2, 175, 'http://top.baidu.com/buzz?b=1570'),
(34, 5, 'item', '/buzz?b=1569', '男明星', 2, 176, 'http://top.baidu.com/buzz?b=1569'),
(35, 5, 'item', '/buzz?b=491', '欧美明星', 2, 177, 'http://top.baidu.com/buzz?b=491'),
(36, 6, 'item', '/buzz?b=1540', '热搜汽车', 2, 179, 'http://top.baidu.com/buzz?b=1540'),
(37, 6, 'item', '/buzz?b=1676', '电动汽车', 2, 180, 'http://top.baidu.com/buzz?b=1676'),
(38, 6, 'item', '/buzz?b=1543', '微型车', 2, 181, 'http://top.baidu.com/buzz?b=1543'),
(39, 6, 'item', '/buzz?b=1544', '小型车', 2, 182, 'http://top.baidu.com/buzz?b=1544'),
(40, 6, 'item', '/buzz?b=1541', '紧凑车型', 2, 183, 'http://top.baidu.com/buzz?b=1541'),
(41, 6, 'item', '/buzz?b=1545', '中级车', 2, 184, 'http://top.baidu.com/buzz?b=1545'),
(42, 6, 'item', '/buzz?b=1546', '中高级车', 2, 185, 'http://top.baidu.com/buzz?b=1546'),
(43, 6, 'item', '/buzz?b=1548', '豪华车', 2, 186, 'http://top.baidu.com/buzz?b=1548'),
(44, 6, 'item', '/buzz?b=1542', 'SUV', 2, 187, 'http://top.baidu.com/buzz?b=1542'),
(45, 6, 'item', '/buzz?b=1549', 'MPV', 2, 188, 'http://top.baidu.com/buzz?b=1549'),
(46, 6, 'item', '/buzz?b=1564', '汽车月度', 2, 189, 'http://top.baidu.com/buzz?b=1564'),
(47, 7, 'item', '/buzz?b=1', '实时热点', 2, 191, 'http://top.baidu.com/buzz?b=1'),
(48, 7, 'item', '/buzz?b=341', '今日热点', 2, 192, 'http://top.baidu.com/buzz?b=341'),
(49, 7, 'item', '/buzz?b=42', '七日热点', 2, 193, 'http://top.baidu.com/buzz?b=42'),
(50, null, 'group', '/category?c=1', '电影', 1, 114, null),
(51, null, 'group', '/category?c=2', '电视剧', 1, 121, null),
(52, null, 'group', '/category?c=3', '综艺', 1, 133, null),
(53, null, 'group', '/category?c=5', '动漫', 1, 140, null),
(54, 2, 'item', '/buzz?b=1677', '少儿影视', 2, 112, 'http://top.baidu.com/buzz?b=1677'),
(55, 2, 'item', '/buzz?b=1678', '纪录片', 2, 113, 'http://top.baidu.com/buzz?b=1678'),
(59, 1, 'item', null, '旅游城市', 2, 101, 'http://top.baidu.com/buzz?b=302'),
(60, 1, 'item', null, '风景名胜', 2, 102, 'http://top.baidu.com/buzz?b=14'),
(61, 1, 'item', null, '博物馆', 2, 103, 'http://top.baidu.com/buzz?b=1579'),
(62, 1, 'item', null, '宠物', 2, 104, 'http://top.baidu.com/buzz?b=24'),
(63, 1, 'item', null, '小吃', 2, 105, 'http://top.baidu.com/buzz?b=1434'),
(64, 1, 'item', null, '畅销书', 2, 106, 'http://top.baidu.com/buzz?b=450'),
(65, 1, 'item', null, '高校', 2, 107, 'http://top.baidu.com/buzz?b=12'),
(66, 1, 'item', null, '化妆品', 2, 108, 'http://top.baidu.com/buzz?b=1565'),
(67, 1, 'item', null, '奢侈品', 2, 109, 'http://top.baidu.com/buzz?b=270'),
(68, 1, 'item', null, '慈善公益组织', 2, 110, 'http://top.baidu.com/buzz?b=367'),
(72, 8, 'item', null, '手机', 2, 195, 'http://top.baidu.com/buzz?b=1566'),
(73, 8, 'item', null, '软件', 2, 196, 'http://top.baidu.com/buzz?b=1627'),
(74, 9, 'item', null, '岛屿', 2, 198, 'http://top.baidu.com/buzz?b=1590'),
(75, 9, 'item', null, '国内景点', 2, 199, 'http://top.baidu.com/buzz?b=1581'),
(76, 9, 'item', null, '国外景点', 2, 200, 'http://top.baidu.com/buzz?b=1582'),
(77, 9, 'item', null, '中国名镇', 2, 201, 'http://top.baidu.com/buzz?b=1591'),
(78, 50, 'item', null, '爱情', 2, 120, 'http://top.baidu.com/buzz?b=338'),
(79, 50, 'item', null, '喜剧', 2, 119, 'http://top.baidu.com/buzz?b=340'),
(80, 50, 'item', null, '惊悚', 2, 118, 'http://top.baidu.com/buzz?b=339'),
(81, 50, 'item', null, '科幻', 2, 117, 'http://top.baidu.com/buzz?b=437'),
(82, 50, 'item', null, '剧情', 2, 116, 'http://top.baidu.com/buzz?b=337'),
(83, 50, 'item', null, '全部电影', 2, 115, 'http://top.baidu.com/buzz?b=26'),
(84, 51, 'item', null, '偶像', 2, 123, 'http://top.baidu.com/buzz?b=349'),
(85, 51, 'item', null, '言情', 2, 124, 'http://top.baidu.com/buzz?b=350'),
(86, 51, 'item', null, '古装', 2, 125, 'http://top.baidu.com/buzz?b=351'),
(87, 51, 'item', null, '家庭伦理', 2, 126, 'http://top.baidu.com/buzz?b=448'),
(88, 51, 'item', null, '美剧', 2, 127, 'http://top.baidu.com/buzz?b=452'),
(89, 51, 'item', null, '韩剧', 2, 128, 'http://top.baidu.com/buzz?b=453'),
(90, 51, 'item', null, '日剧', 2, 129, 'http://top.baidu.com/buzz?b=466'),
(91, 51, 'item', null, '港剧', 2, 130, 'http://top.baidu.com/buzz?b=464'),
(92, 51, 'item', null, '台剧', 2, 131, 'http://top.baidu.com/buzz?b=465'),
(93, 51, 'item', null, '泰剧', 2, 132, 'http://top.baidu.com/buzz?b=467'),
(94, 51, 'item', null, '全部电视剧', 2, 122, 'http://top.baidu.com/buzz?b=4'),
(95, 52, 'item', null, '访谈综艺', 2, 135, 'http://top.baidu.com/buzz?b=439'),
(96, 52, 'item', null, '情感综艺', 2, 136, 'http://top.baidu.com/buzz?b=440'),
(97, 52, 'item', null, '选秀综艺', 2, 137, 'http://top.baidu.com/buzz?b=441'),
(98, 52, 'item', null, '内地综艺', 2, 138, 'http://top.baidu.com/buzz?b=368'),
(99, 52, 'item', null, '港台综艺', 2, 139, 'http://top.baidu.com/buzz?b=369'),
(100, 52, 'item', null, '全部综艺', 2, 134, 'http://top.baidu.com/buzz?b=19'),
(101, 53, 'item', null, '搞笑', 2, 148, 'http://top.baidu.com/buzz?b=442'),
(102, 53, 'item', null, '益智', 2, 147, 'http://top.baidu.com/buzz?b=443'),
(103, 53, 'item', null, '冒险', 2, 146, 'http://top.baidu.com/buzz?b=444'),
(104, 53, 'item', null, '情感', 2, 145, 'http://top.baidu.com/buzz?b=623'),
(105, 53, 'item', null, '国产', 2, 144, 'http://top.baidu.com/buzz?b=445'),
(106, 53, 'item', null, '日本', 2, 143, 'http://top.baidu.com/buzz?b=446'),
(107, 53, 'item', null, '欧美', 2, 142, 'http://top.baidu.com/buzz?b=447'),
(108, 53, 'item', null, '全部动漫', 2, 141, 'http://top.baidu.com/buzz?b=23');

update rank_group set create_time = now() where create_time is null;

INSERT INTO ms_menu
(menu_position, menu_level, menu_name, menu_path, menu_summary, menu_order)
VALUES
('admin', 1, '应用', '/', '应用', 6000),
('admin', 2, '文章', '/six/post/list', '文章', 6010),
('admin', 2, '提醒项', '/notify/list', '提醒项', 6100),
('admin', 2, '测试', '/test/list', '测试', 6990);