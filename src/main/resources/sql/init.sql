create table six_data
(
    id int auto_increment
        primary key,
    code varchar(255) null,
    data decimal(18,4) null,
    text varchar(255) null,
    status int null,
    create_time datetime(6) null
);

create index six_data_code_index
    on six_data (code);

create table six_notify_config
(
    id int auto_increment
        primary key,
    code varchar(255) null,
    type int null,
    rise decimal(18,4) null,
    status int null,
    create_time datetime(6) null
);

create index six_notify_config_code_index
    on six_notify_config (code);

create index six_notify_config_type_index
    on six_notify_config (type);

