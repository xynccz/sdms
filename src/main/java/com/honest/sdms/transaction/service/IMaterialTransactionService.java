package com.honest.sdms.transaction.service;

import java.util.List;

import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.service.IBaseService;
import com.honest.sdms.transaction.entity.MaterialTransaction;

public interface IMaterialTransactionService extends IBaseService<MaterialTransaction, Long>{

	abstract void createMaterialTransactions(MaterialTransaction cond)throws HSException;
	
	abstract List<MaterialTransaction> findMaterialTransactionsByIds(Long[] transactionIds);
	
	abstract void createStocks(String transactionIds)throws HSException;
	
	abstract void saveList(List<MaterialTransaction> list)throws HSException;
	
	abstract void updateList(List<MaterialTransaction> list)throws HSException;
}
