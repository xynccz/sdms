package com.honest.sdms.transaction.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.service.imp.BaseServiceImp;
import com.honest.sdms.transaction.dao.MaterialTransactionMapper;
import com.honest.sdms.transaction.entity.MaterialTransaction;
import com.honest.sdms.transaction.service.IMaterialTransactionService;

/**
 * 交易信息服务操作类
 * @author beisi
 *
 */
@Service
public class MaterialTransactionServiceImp extends BaseServiceImp<MaterialTransaction, Long> implements IMaterialTransactionService{

	private MaterialTransactionMapper materialTransactionMapper;

	@Autowired
	@Qualifier("materialTransactionMapper")
	@Override
	public void setBaseDao(IBaseMapper<MaterialTransaction, Long> baseMapper) {
		this.baseMapper = baseMapper;
		materialTransactionMapper = (MaterialTransactionMapper)baseMapper;
	}
	
}
