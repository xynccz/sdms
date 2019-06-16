
--Mysql数据库显示时间与应用程序获取到的不一致的问题
show VARIABLES like '%time_zone%';
set global time_zone = '+08:00';
set time_zone = '+08:00';
flush privileges;

--创建用户表
create table if not exists sys_users(
	user_id bigint primary key AUTO_INCREMENT,
	login_name varchar(100) not null,
	login_password varchar(100) not null,
	user_name varchar(100) not null,
	sex char(1),
	phone_number varchar(20),
	Telephone varchar(20),
	email varchar(100),
	is_valid char(1),
	created_by varchar(50),
	created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	last_updated_by varchar(50),
	last_updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	organization_id bigint not null COMMENT '组织号'
)DEFAULT CHARSET=utf8 COMMENT='用户表';
--关联系统字典参数配置表中的id
--角色表
create table if not exists roles(
	role_id bigint primary key AUTO_INCREMENT,
	role_name varchar(100) not null COMMENT '角色名称',
	role_code varchar(100) not null COMMENT '角色编码',
	description varchar(200) COMMENT '角色描述',
	organization_id bigint not null COMMENT '组织号',
	created_by varchar(50),
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    UNIQUE (role_name,role_code,organization_id)
)DEFAULT CHARSET=utf8 COMMENT='角色表';

--用户与角色关系表
create table if not exists user_role(
   id bigint primary key AUTO_INCREMENT,
   user_id bigint NOT NULL COMMENT '用户id',
   role_id bigint NOT NULL COMMENT '角色id',
   organization_id bigint not null COMMENT '组织号',
   created_by varchar(50),
   created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
 )DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

--权限表
create table if not exists resources(
	resource_id bigint primary key AUTO_INCREMENT,
	parent_id bigint COMMENT '父项id',
	title varchar(100) not null COMMENT '资源名称',
	path varchar(100) COMMENT '地址索引',
	icon varchar(100) COMMENT '图标',
	url varchar(100) not null COMMENT '资源链接',
	code varchar(100) COMMENT '权限标识,比如a:b:c',
	type int COMMENT '菜单级别:1模块；2菜单；3按钮',
	sort_order int not null unique COMMENT '菜单显示顺序',
	organization_id bigint not null COMMENT '组织号',
	created_by varchar(50),
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    last_updated_by varchar(50),
	last_updated_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'
)DEFAULT CHARSET=utf8 COMMENT='权限表';

--角色与权限关系表
create table if not exists role_resource (
   id bigint primary key AUTO_INCREMENT,
   role_id bigint DEFAULT NULL COMMENT '角色id',
   resource_id bigint DEFAULT NULL COMMENT '资源id',
   organization_id bigint not null COMMENT '组织号',
   created_by varchar(50),
   created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
 ) DEFAULT CHARSET=utf8 COMMENT '角色权限关系表';

-- 字典类型表
CREATE TABLE sys_dict_type(
	dict_id bigint primary key AUTO_INCREMENT,
	dict_name varchar(100) NOT NULL COMMENT '字典名称',
	dict_code varchar(100) NOT NULL COMMENT '字典类型',
	is_valid char(1) DEFAULT 'Y' NOT NULL COMMENT '状态是否有效',
	created_by varchar(64) NOT NULL COMMENT '创建者',
	created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	last_updated_by varchar(64) NOT NULL COMMENT '更新者',
	last_updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	remarks varchar(500) COMMENT '备注信息',
	organization_id bigint not null COMMENT '组织号',
	UNIQUE (dict_code,organization_id)
) COMMENT = '字典类型表';

 -- 字典参数配置表
CREATE TABLE sys_dict_datas(
	id bigint primary key AUTO_INCREMENT,
	dict_data_code varchar(50) NOT NULL ,
	dict_data_name varchar(100) NOT NULL COMMENT '名称',
	is_valid char(1) DEFAULT 'Y' NOT NULL COMMENT '状态是否有效',
	dict_id bigint NOT NULL COMMENT '字典类型',
	created_by varchar(64) NOT NULL COMMENT '创建者',
	created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	last_updated_by varchar(64) NOT NULL COMMENT '更新者',
	last_updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	remarks varchar(500) COMMENT '备注信息',
	organization_id bigint not null COMMENT '组织号',
	UNIQUE (dict_data_code,organization_id)
	foreign key (dict_id) references sys_dict_type(dict_id) on delete cascade on update cascade
) COMMENT = '字典参数配置表';

-- 系统操作日志表
CREATE TABLE sys_log (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  login_name varchar(100) NOT NULL DEFAULT '' COMMENT '登录人',
  ip varchar(100) DEFAULT NULL COMMENT '登录IP',
  operate_type varchar(100) DEFAULT NULL COMMENT '操作类型',
  operate_url varchar(500) DEFAULT NULL COMMENT '操作模块',
  content text COMMENT '操作明细内容',
  operate_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  remarks varchar(800) DEFAULT NULL COMMENT '备注',
  execute_time bigint COMMENT '请求花费时间',
  organization_id bigint(20) NOT NULL COMMENT '组织号',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
