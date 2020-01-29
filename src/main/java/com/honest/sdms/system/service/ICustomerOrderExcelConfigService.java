package com.honest.sdms.system.service;

import java.util.List;

import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.entity.CustomerOrderExcelConfig;

public interface ICustomerOrderExcelConfigService extends IBaseService<CustomerOrderExcelConfig, Long>{

	abstract void saveOrUpdateCustomerOrderExcelConfigs(List<CustomerOrderExcelConfig> list)throws HSException;
}
