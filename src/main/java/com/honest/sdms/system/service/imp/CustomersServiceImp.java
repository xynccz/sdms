package com.honest.sdms.system.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.Constants;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.dao.CustomerArchivesMapper;
import com.honest.sdms.system.dao.CustomerOrderFieldConfigMapper;
import com.honest.sdms.system.dao.CustomersMapper;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.entity.CustomerArchives;
import com.honest.sdms.system.entity.CustomerOrderFieldConfig;
import com.honest.sdms.system.entity.Customers;
import com.honest.sdms.system.service.ICustomersService;
import com.honest.sdms.tools.DateTimeUtil;

import net.sf.json.JSONObject;

/**
 * 客户服务类
 * @author beisi
 */
@Service
public class CustomersServiceImp extends BaseServiceImp<Customers, Long> implements ICustomersService{

	private CustomersMapper customersMapper;
	@Autowired
	private CustomerArchivesMapper customerArchivesMapper;
	@Autowired
	private CustomerOrderFieldConfigMapper customerOrderFieldConfigMapper;
	
	@Autowired
	@Qualifier("customersMapper")
	@Override
	public void setBaseDao(IBaseMapper<Customers, Long> baseMapper) {
		this.baseMapper = baseMapper;
		customersMapper = (CustomersMapper)baseMapper;
	}

	@Override
	public List<Customers> getCustomerList(String customerName) {
		return customersMapper.getCustomerList(customerName, Constants.getCurrentSysUser().getOrganizationId());
	}

	@Override
	public List<CustomerArchives> getCustomerArchivesByCustomerId(Long customerId) {
		return customerArchivesMapper.getCustomerArchivesByCustomerId(customerId,Constants.getCurrentOrganizationId());
	}

	/**
	 * 保存或更新客户信息
	 */
	@Override
	public void saveOrUpdateCustomer(Customers customer) throws HSException {
		//说明是新客户
		if(customer.getId() == null){
			insert(customer);
		}else{ 
			updateByPrimaryKey(customer);
		}		
	}

	@Override
	public void saveCustomerArchives(List<CustomerArchives> customerArchives) throws HSException {
		if(customerArchives != null && customerArchives.size() > 0){
			for(CustomerArchives ca : customerArchives) {
				Long id = ca.getId();
				if(id != null){
					ca.setLastUpdatedBy(Constants.getCurrentSysUser().getLoginName());
					ca.setLastUpdatedDate(new DateTimeUtil().toTimestamp());
					customerArchivesMapper.updateByPrimaryKey(ca);
				}else{
					ca.setOrganizationId(Constants.getCurrentOrganizationId());
					ca.setCreatedBy(Constants.getCurrentSysUser().getLoginName());
					ca.setLastUpdatedBy(ca.getCreatedBy());
					ca.setCreatedDate(new DateTimeUtil().toTimestamp());
					ca.setLastUpdatedDate(new DateTimeUtil().toTimestamp());
					customerArchivesMapper.insert(ca);
				}
			}
		}
	}

	/**
	 * 获取客户订单字段合集
	 */
	@Override
	public List<JSONObject> getCustomerOrderFieldList() {
		List<JSONObject> array = new ArrayList<JSONObject>();
		List<CustomerOrderFieldConfig> list = customerOrderFieldConfigMapper.getCustomerOrderFields();
		list.forEach(item->{
			JSONObject json = new JSONObject();
			json.put("key", item.getCodeField());
			json.put("value", item.getCodeDesc());
			array.add(json);
		});
		return array;
	}
	
}
