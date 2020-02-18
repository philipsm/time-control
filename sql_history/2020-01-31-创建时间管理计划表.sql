--创建时间管理计划表
create table tc_time_plan (
    time_plan_id int unsigned NOT NULL auto_increment  COMMENT '主键'  PRIMARY KEY,
    title varchar(200)  default '' COMMENT '标题' ,
		plan_describe varchar(200) default '' COMMENT '计划描述' ,
		date varchar(40) default '' COMMENT '日期' ,
		plan_type tinyint(4) DEFAULT 0 COMMENT '类别：1固定日期2每日循环3工作日循环' ,
		plan_group int DEFAULT 0 COMMENT '分组' ,
		add_user int default 0 COMMENT '数据添加人' ,
		modify_user int default 0 COMMENT '数据修改人' ,
		add_time varchar(40) default '' COMMENT '数据添加时间' ,
		modify_time varchar(40) default '' COMMENT '数据修改时间' ,
		is_delete tinyint(1) DEFAULT 0 COMMENT '标记字段：0未删除1已删除',
   index index_date (date)
   )engine=innodb DEFAULT CHARSET=utf8 COMMENT '时间管理计划表';


