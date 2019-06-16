package com.honest.sdms.system.service;

import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.entity.SysDictType;

public interface ISysDictTypeService extends IBaseService<SysDictType, Long>{
	
	abstract void saveOrUpdateDictType(SysDictType dictType)throws HSException;

}
