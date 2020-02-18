--创建时间管理计划详情表
create table tc_time_plan_detail (
    time_plan_detail_id int unsigned NOT NULL auto_increment  COMMENT '主键'  PRIMARY KEY,
    time_plan_id int COMMENT '计划ID' ,
		start_time varchar(40) default '' COMMENT '开始时间' ,
		end_time varchar(40) default '' COMMENT '结束时间' ,
		plan_content varchar(400) DEFAULT '' COMMENT '计划内容' ,
		detail_remarks varchar DEFAULT '' COMMENT '备注' ,
		add_user int default 0 COMMENT '数据添加人' ,
		modify_user int default 0 COMMENT '数据修改人' ,
		add_time varchar(40) default '' COMMENT '数据添加时间' ,
		modify_time varchar(40) default '' COMMENT '数据修改时间' ,
		is_delete tinyint(1) DEFAULT 0 COMMENT '标记字段：0未删除1已删除'
   )engine=innodb DEFAULT CHARSET=utf8 COMMENT '时间管理计划详情表';


