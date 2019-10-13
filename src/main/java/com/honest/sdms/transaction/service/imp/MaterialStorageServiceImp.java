package com.honest.sdms.transaction.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.basedata.exceptions.HSException;
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

	@Override
	public void saveList(List<MaterialStoreage> list)throws HSException {
		if(list != null && list.size() > 0) {
			for(MaterialStoreage ms : list) {
				this.insert(ms);
			}
		}
	}

	/**
	 * 出库
	 */
	@Override
	public void outStocks(String storeIds) throws HSException {
		// TODO Auto-generated method stub
		
	}
	
}
