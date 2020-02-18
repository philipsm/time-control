
--创建计划执行表
create table tc_time_plan_execute (
    execute_id int unsigned NOT NULL auto_increment  COMMENT '主键'  PRIMARY KEY,
    time_plan_id int COMMENT '计划ID' ,
		excute_date varchar(40) default '' COMMENT '执行日期' ,
		excute_result tinyint(4) default 0 COMMENT '执行结果:0已完成1未完成2基本完成' ,
		title varchar(200)  default '' COMMENT '标题' ,
		remarks varchar(200) default '' COMMENT '备注' ,
		add_user varchar(40) default '' COMMENT '数据添加人' ,
		modify_user varchar(40) default '' COMMENT '数据修改人' ,
		add_time varchar(40) default '' COMMENT '数据添加时间' ,
		modify_time varchar(40) default '' COMMENT '数据修改时间' ,
		is_delete tinyint(1) DEFAULT 0 COMMENT '标记字段：0未删除1已删除'
   )engine=innodb DEFAULT CHARSET=utf8 COMMENT '计划执行表';


