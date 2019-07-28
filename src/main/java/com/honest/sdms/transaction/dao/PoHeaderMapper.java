package com.honest.sdms.transaction.dao;

import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.transaction.entity.PoHeader;

public interface PoHeaderMapper extends IBaseMapper<PoHeader, Long>{
	
	abstract Long nextSeqNumber();
	
}