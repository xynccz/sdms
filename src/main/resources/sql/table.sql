
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
	UNIQUE (dict_id,dict_data_code,organization_id)
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


--===============================业务相关表====================================--
create table mtl_system_item(
	item_id bigint primary key AUTO_INCREMENT,
	item varchar(200) not null,
	remarks varchar(500) COMMENT '备注信息',
	created_by varchar(64) NOT NULL COMMENT '创建者',
	created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	last_updated_by varchar(64) NOT NULL COMMENT '更新者',
	last_updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	organization_id bigint not null COMMENT '组织号'
)COMMENT = '产品资料表';

create table po_header(
	header_id bigint primary key AUTO_INCREMENT,
	po_number varchar(500) not null COMMENT 'po号',
	po_type varchar(50) COMMENT 'PO类型,产地采购或同行采购',
	vendor varchar(200) COMMENT '供应商,比如果农名字',
	total_amount double not null COMMENT '采购总金额',
	buyer varchar(200) COMMENT '采购员',
	agent varchar(200) COMMENT '代办人',
	agent_pay double COMMENT '代办支付费用',
	item_id bigint not null,
	item varchar(200) not null COMMENT '原料信息，比如苹果等',
	origin_place varchar(200) COMMENT '产地名称',
	net_weight double COMMENT '净重，结算重量+扣秤重量',
	gross_weight double COMMENT '毛重',
	settlement_weight double COMMENT '结算数量,实际产生采购费用的重量',
	loss_weight double COMMENT '扣秤重量,这部分重量不产生采购费用',
	unit varchar(10) COMMENT '重量单位,g或kg',
	totle_pieces bigint COMMENT '总装货件数',
	package_type VARCHAR(30) COMMENT '打包分类，比如纸箱或筐',
	date_of_purchase TIMESTAMP not null COMMENT '采购日期',
	is_closed char DEFAULT 'N' COMMENT '订单是否关闭',
	remarks varchar(500) COMMENT '备注信息',
	created_by varchar(64) NOT NULL COMMENT '创建者',
	created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	last_updated_by varchar(64) NOT NULL COMMENT '更新者',
	last_updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	organization_id bigint not null COMMENT '组织号'
)COMMENT = 'PO头表';

create table po_line(
	line_id bigint primary key AUTO_INCREMENT,
	header_id bigint not null,
	is_ship char(1) DEFAULT 'N' COMMENT '是否已生成发货信息',
	weight double,
	specific_id bigint COMMENT '规格，用数据字典配置，使用sys_dict_datas表的主键id标识',
	piece_num bigint COMMENT '行件数',
	Unit_Price double COMMENT '采购单价',
	remarks varchar(500) COMMENT '备注信息',
	created_by varchar(64) NOT NULL COMMENT '创建者',
	created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	last_updated_by varchar(64) NOT NULL COMMENT '更新者',
	last_updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	organization_id bigint not null COMMENT '组织号',
	foreign key (header_id) references po_header(header_id) on delete cascade on update cascade
)COMMENT = 'PO行表';

create table material_transaction(
	transaction_id bigint primary key AUTO_INCREMENT,
	transaction_summary_id bigint COMMENT '为每车货分配唯一标识,每车货可能对于多行交易记录，这多行交易记录需指向同一个transaction_summary_id',
	item_id bigint not null,
	item varchar(200) not null COMMENT '产品名称',
	po_header_id  bigint,
	po_number varchar(500) COMMENT 'PO信息',
	po_line bigint COMMENT 'PO行',
	io_type varchar(100) DEFAULT NULL COMMENT '交易类型,来源数据字典',
	io_status char DEFAULT 'N' COMMENT '是否入库，Y入库',
	car_number varchar(100) COMMENT '车牌号',
	driver_name varchar(100) COMMENT '司机姓名',
	driver_phone varchar(100) COMMENT '司机电话',
	ship_date DATETIME COMMENT '发货日期',
	scheduled_arrival_date DATETIME COMMENT '预计到货日期',
	actual_arrival_date DATETIME COMMENT '实际到货日期',
	transaction_date DATETIME COMMENT '交易日期',
	transaction_piece bigint COMMENT '交易件数,通常情况整车货用件数表示',
	transaction_weight double COMMENT '交易数量,散装一般整车用斤表示',
	warehouse varchar(200) COMMENT '到货仓库',
	remarks varchar(500) COMMENT '备注信息',
	created_by varchar(64) NOT NULL COMMENT '创建者',
	created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	last_updated_by varchar(64) NOT NULL COMMENT '更新者',
	last_updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	organization_id bigint not null COMMENT '组织号'
)COMMENT = '交易信息表';


create table qc_check(
	id bigint primary key AUTO_INCREMENT,
	transaction_summary_id bigint not null,
	qc_weight double,
	qc_piece bigint,
	qc_checker varchar(200),
	remarks varchar(500) COMMENT '备注信息',
	created_by varchar(64) NOT NULL COMMENT '创建者',
	created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	last_updated_by varchar(64) NOT NULL COMMENT '更新者',
	last_updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	organization_id bigint not null COMMENT '组织号',
	foreign key (transaction_summary_id) references material_transaction(transaction_summary_id) on delete cascade on update cascade
)COMMENT = 'QC记录表';

create table material_storeage(
	store_id bigint primary key AUTO_INCREMENT,
	Inventory_type_id bigint(20) DEFAULT NULL COMMENT '库存类型，关联库存类型数据字典',
	item_id bigint not null,
	item varchar(200) not null,
	transaction_summary_id bigint,
	po_header_id bigint COMMENT 'PO信息',
	specific_id bigint DEFAULT NULL COMMENT '产品规格',
	warehouse varchar(200) COMMENT '到货仓库',
	net_weight_per_unit double DEFAULT NULL COMMENT '单件净重，成品装的净重,只在成品库存中出现的概念',
	net_weight double,
	gross_weight double,
	piece_num bigint,
	create_transaction_id bigint COMMENT '对应交易表主键ID',
	update_transaction_id bigint,
	remarks varchar(500) COMMENT '备注信息',
	created_by varchar(64) NOT NULL COMMENT '创建者',
	created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	last_updated_by varchar(64) NOT NULL COMMENT '更新者',
	last_updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	organization_id bigint not null COMMENT '组织号'
)COMMENT = '库存表';

create table io_type(
	id bigint primary key AUTO_INCREMENT,
	type_code varchar(100) not null,
	type_name varchar(300) not null,
	is_valid char DEFAULT 'Y',
	remarks varchar(500) COMMENT '备注信息',
	created_by varchar(64) NOT NULL COMMENT '创建者',
	created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	last_updated_by varchar(64) NOT NULL COMMENT '更新者',
	last_updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	organization_id bigint not null COMMENT '组织号'
)

create table order_header(
	header_id bigint primary key,
	order_no varchar(100) comment '订单号，唯一',
	order_type_id bigint comment '订单类型', 
	order_status varchar(100) comment '订单状态,未付款,已付款,已发货,已签收,退货申请,退货中,已退货,取消交易',
	buyer_notes varchar(500) COMMENT '买家留言',
	shop_id varchar(100) COMMENT '商品编号',
	shop_order_no varchar(300) COMMENT '商家订单号，从店铺发过来的',
	shop_name varchar(100) comment '店铺名称',
    customer_service_notes varchar(500) COMMENT '客服备注信息',
    order_count bigint COMMENT '订单数量,客户订单显示2，那么就要发2件货'
    order_amount double COMMENT '订单金额',
	created_by varchar(64) NOT NULL COMMENT '创建者',
	created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	last_updated_by varchar(64) NOT NULL COMMENT '更新者',
	last_updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	organization_id bigint not null COMMENT '组织号'
)COMMENT = '订单头表';

create table order_detail(
	detail_id bigint primary key,
	header_id bigint not null,
	product_name varchar(500) COMMENT '商品名称',
	item_id bigint not null,
	item varchar(200) not null,
	specific_id bigint(20) DEFAULT NULL COMMENT '产品规格',
	warehouse varchar(200) COMMENT '到货仓库',
	weight double COMMENT '产品重量',
	piece_num bigint COMMENT '件数',
	remarks varchar(500),
	created_by varchar(64) NOT NULL COMMENT '创建者',
	created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	last_updated_by varchar(64) NOT NULL COMMENT '更新者',
	last_updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
	organization_id bigint not null,
  	foreign key (header_id) references order_header(header_id) on delete cascade on update cascade
)COMMENT = '订单明细表';

--订单物流表
create table order_express(
	id bigint primary key,
	header_id bigint not null,
	express_company varchar(500) comment '物流公司',
	express_no varchar(200) comment '物流单号',
	express_status varchar(200) comment '物流状态',
	net_name varchar(300) comment '网名',
	consignee_realname varchar(300) comment '收件人',
	consignee_telphone varchar(50) comment '联系电话',
	consignee_province varchar(50) comment '省',
	consignee_city varchar(50) comment '市',
	consignee_county varchar(50) comment '区',
	consignee_address varchar(2000) comment '收件详细地址',
	consignee_zip varchar(50) comment '邮编',
	delivery_conditions varchar(100) COMMENT '发货条件，比如款到发货',
	delivery_amount double comment '实际支付给物流公司的金额',
	express_result_last varchar(500) comment '物流最新状态描述',
	express_result varchar(2000) comment '物流描述',
	express_create_time DATETIME comment '发货时间',
	express_update_time DATETIME comment '物流更新时间',
	created_by varchar(64) NOT NULL COMMENT '创建者',
	created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	last_updated_by varchar(64) NOT NULL COMMENT '更新者',
	last_updated_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  	organization_id bigint not null,
  	foreign key (header_id) references order_header(header_id) on delete cascade on update cascade
)COMMENT = '订单物流信息表';