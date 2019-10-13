package com.honest.sdms.transaction.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.transaction.entity.MaterialTransaction;

public interface MaterialTransactionMapper extends IBaseMapper<MaterialTransaction, Long>{

	abstract List<MaterialTransaction> findMaterialTransactionsByIds(@Param("transactionIds") Long[] transactionIds, @Param("organizationId") Long organizationId);
}