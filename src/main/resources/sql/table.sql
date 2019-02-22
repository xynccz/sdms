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
	role_code varchar(100) COMMENT '角色编码',
	description varchar(200) COMMENT '角色描述',
	organization_id bigint not null COMMENT '组织号',
	created_by varchar(50),
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
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
	title varchar(100) not null COMMENT '资源名称',
	url varchar(100) not null COMMENT '资源链接',
	parent_id bigint COMMENT '父项id',
	code varchar(100) COMMENT '权限标识',
	type int COMMENT '菜单级别:1模块；2菜单；3按钮',
	organization_id bigint not null COMMENT '组织号'
)DEFAULT CHARSET=utf8 COMMENT='权限表';

--角色与权限关系表
create table if not exists role_resource (
   id bigint primary key AUTO_INCREMENT,
   role_id bigint DEFAULT NULL COMMENT '角色id',
   resource_id bigint DEFAULT NULL COMMENT '资源id',
   organization_id bigint not null COMMENT '组织号'
 ) DEFAULT CHARSET=utf8 COMMENT '角色权限关系表';

-- 字典类型表
CREATE TABLE sys_dict_type(
	dict_id bigint primary key AUTO_INCREMENT,
	dict_name varchar(100) NOT NULL COMMENT '字典名称',
	dict_type varchar(100) NOT NULL COMMENT '字典类型',
	status char(1) DEFAULT '0' NOT NULL COMMENT '状态（0正常 1停用）',
	created_by varchar(64) NOT NULL COMMENT '创建者',
	created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	updated_by varchar(64) NOT NULL COMMENT '更新者',
	updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	remarks varchar(500) COMMENT '备注信息',
	organization_id bigint not null COMMENT '组织号',
	UNIQUE (dict_type,organization_id)
) COMMENT = '字典类型表';

 -- 字典参数配置表
CREATE TABLE sys_dict_datas(
	id bigint primary key AUTO_INCREMENT,
	dict_data_code varchar(50) NOT NULL ,
	dict_data_name varchar(100) NOT NULL COMMENT '名称',
	status char(1) DEFAULT '0' NOT NULL COMMENT '状态（0正常 1停用）',
	dict_id bigint NOT NULL COMMENT '字典类型',
	created_by varchar(64) NOT NULL COMMENT '创建者',
	created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	updated_by varchar(64) NOT NULL COMMENT '更新者',
	updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	remarks varchar(500) COMMENT '备注信息',
	organization_id bigint not null COMMENT '组织号',
	foreign key (dict_id) references sys_dict_type(dict_id) on delete cascade on update cascade
) COMMENT = '字典参数配置表';
