package com.honest.sdms.system.dao;

import java.util.List;

import com.honest.sdms.system.entity.CustomerOrderFieldConfig;

public interface CustomerOrderFieldConfigMapper extends IBaseMapper<CustomerOrderFieldConfig, Integer>{

	abstract List<CustomerOrderFieldConfig> getCustomerOrderFields();
}