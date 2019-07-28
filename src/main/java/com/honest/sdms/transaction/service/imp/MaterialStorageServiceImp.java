package com.honest.sdms.transaction.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.service.imp.BaseServiceImp;
import com.honest.sdms.transaction.dao.MaterialStoreageMapper;
import com.honest.sdms.transaction.entity.MaterialStoreage;
import com.honest.sdms.transaction.service.IMaterialStorageService;

/**
 * 库存操作服务类
 * @author beisi
 *
 */
@Service
public class MaterialStorageServiceImp extends BaseServiceImp<MaterialStoreage, Long> implements IMaterialStorageService{

	private MaterialStoreageMapper materialStoreageMapper;

	@Autowired
	@Qualifier("materialStoreageMapper")
	@Override
	public void setBaseDao(IBaseMapper<MaterialStoreage, Long> baseMapper) {
		this.baseMapper = baseMapper;
		materialStoreageMapper = (MaterialStoreageMapper)baseMapper;
	}
	
}
