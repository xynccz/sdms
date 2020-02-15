package com.honest.sdms.system.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.system.dao.ErrorDataLogMapper;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.entity.ErrorDataLog;
import com.honest.sdms.system.service.IErrorDataLogService;

@Service
public class ErrorDataLogServiceImp extends BaseServiceImp<ErrorDataLog, Long> implements IErrorDataLogService{

	private ErrorDataLogMapper errorDataLogMapper;
	
	@Autowired
	@Qualifier("errorDataLogMapper")
	@Override
	public void setBaseDao(IBaseMapper<ErrorDataLog, Long> baseMapper) {
		this.baseMapper = baseMapper;
		errorDataLogMapper = (ErrorDataLogMapper)baseMapper;
		
	}

}
