package com.honest.sdms.system.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.dao.CustomerOrderExcelConfigMapper;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.entity.CustomerOrderExcelConfig;
import com.honest.sdms.system.service.ICustomerOrderExcelConfigService;

@Service
public class CustomerOrderExcelConfigServiceImp extends BaseServiceImp<CustomerOrderExcelConfig, Long> implements ICustomerOrderExcelConfigService{

	private CustomerOrderExcelConfigMapper customerOrderExcelConfigMapper;
	
	@Autowired
	@Qualifier("customerOrderExcelConfigMapper")
	@Override
	public void setBaseDao(IBaseMapper<CustomerOrderExcelConfig, Long> baseMapper) {
		this.baseMapper = baseMapper;
		customerOrderExcelConfigMapper = (CustomerOrderExcelConfigMapper)baseMapper;
	}

	@Override
	public List<CustomerOrderExcelConfig> findCustomerOrderConfigByCustomerId(Long customerId, String operateType) {
		return customerOrderExcelConfigMapper.findCustomerOrderConfigByCustomerId(customerId, operateType);
	}

	@Override
	public void saveOrUpdateCustomerOrderExcelConfigs(List<CustomerOrderExcelConfig> list) throws HSException {
		if(list != null && list.size() > 0){
			for(CustomerOrderExcelConfig obj : list) {
				Long id = obj.getId();
				if(id != null){
					updateByPrimaryKey(obj);
				}else{
					insert(obj);
				}
			}
		}
	}
	
}
