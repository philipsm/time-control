--创建时间管理计划分组表
create table tc_time_plan_group (
    group_id int unsigned NOT NULL auto_increment  COMMENT '主键'  PRIMARY KEY,
    group_name varchar(40)  default '' COMMENT '分组名称' ,
		add_user varchar(40) default '' COMMENT '数据添加人' ,
		modify_user varchar(40) default '' COMMENT '数据修改人' ,
		add_time varchar(40) default '' COMMENT '数据添加时间' ,
		modify_time varchar(40) default '' COMMENT '数据修改时间' ,
		is_delete tinyint(1) DEFAULT 0 COMMENT '标记字段：0未删除1已删除',
   index index_add_user (add_user)
   )engine=innodb DEFAULT CHARSET=utf8 COMMENT '时间管理分组表';


