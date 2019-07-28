package com.honest.sdms.transaction.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.service.imp.BaseServiceImp;
import com.honest.sdms.transaction.dao.IoTypeMapper;
import com.honest.sdms.transaction.entity.IoType;
import com.honest.sdms.transaction.service.IIoTypeService;

@Service
public class IoTypeServiceImp extends BaseServiceImp<IoType, Long> implements IIoTypeService{

	private IoTypeMapper ioTypeMapper;

	@Autowired
	@Qualifier("ioTypeMapper")
	@Override
	public void setBaseDao(IBaseMapper<IoType, Long> baseMapper) {
		this.baseMapper = baseMapper;
		ioTypeMapper = (IoTypeMapper)baseMapper;
	}
	
	
	
}
