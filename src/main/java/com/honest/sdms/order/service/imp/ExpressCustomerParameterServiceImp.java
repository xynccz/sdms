package com.honest.sdms.order.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.Constants;
import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.order.dao.ExpressCustomerParameterMapper;
import com.honest.sdms.order.entity.ExpressCustomerParameter;
import com.honest.sdms.order.service.IExpressCustomerParameterService;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.dao.VendorWarehouseMapper;
import com.honest.sdms.system.entity.VendorWarehouse;
import com.honest.sdms.system.service.imp.BaseServiceImp;

@Service
public class ExpressCustomerParameterServiceImp extends BaseServiceImp<ExpressCustomerParameter, Long> implements IExpressCustomerParameterService{

	@SuppressWarnings("unused")
	private ExpressCustomerParameterMapper expressCustomerParameterMapper;
	@Autowired
	private VendorWarehouseMapper vendorWarehouseMapper;
	
	@Autowired
	@Qualifier("expressCustomerParameterMapper")
	@Override
	public void setBaseDao(IBaseMapper<ExpressCustomerParameter, Long> baseMapper) {
		this.baseMapper = baseMapper;
		expressCustomerParameterMapper = (ExpressCustomerParameterMapper)baseMapper;
	}

	@Override
	public List<VendorWarehouse> getWareHouseByExpressId(Long expressId) {
		return vendorWarehouseMapper.getWareHouseByExpressId(expressId, Constants.getCurrentOrganizationId());
	}

	@Override
	public void save(ExpressCustomerParameter obj) {
		insert(obj);
	}

	@Override
	public void saveOrUpdate(ExpressCustomerParameter obj) throws HSException {
		if(obj.getId() == null){
			insert(obj);
		}else{ 
			updateByPrimaryKey(obj);
		}
	}
	
}
