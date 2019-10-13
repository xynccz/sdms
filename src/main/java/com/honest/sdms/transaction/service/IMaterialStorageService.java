package com.honest.sdms.transaction.service;

import java.util.List;

import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.service.IBaseService;
import com.honest.sdms.transaction.entity.MaterialStoreage;

public interface IMaterialStorageService extends IBaseService<MaterialStoreage, Long>{

	abstract void saveList(List<MaterialStoreage> list)throws HSException;
	
	abstract void outStocks(String storeIds)throws HSException;
}
