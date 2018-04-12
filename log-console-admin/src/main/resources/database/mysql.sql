drop table if exists log_record_info;

/*==============================================================*/
/* Table: log_record_info                                       */
/*==============================================================*/
create table log_record_info
(
   log_id               int(25) auto_increment primary key comment '日志唯一标识 主键id',
   system_id            varchar(50) comment '源系统唯一标识id',
   log_time             timestamp comment '日志打印时间',
   log_level            char(5) comment '日志级别',
   clazz                varchar(100) comment '日志输出类',
   log_content          varchar(2000) comment '日志内容',
   create_date          varchar(20) comment '创建时间'
);

alter table log_record_info comment '日志信息';
