package com.honest.sdms.system.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.dao.SysDictTypeMapper;
import com.honest.sdms.system.entity.SysDictType;
import com.honest.sdms.system.service.ISysDictTypeService;

/**
 * 数据字典服务类
 * @author beisi
 */
@Service
public class SysDictTypeServiceImp extends BaseServiceImp<SysDictType, Long> implements ISysDictTypeService{

	private SysDictTypeMapper sysDictTypeMapper;
	
	@Qualifier("sysDictTypeMapper")
	@Autowired
	@Override
	public void setBaseDao(IBaseMapper<SysDictType, Long> baseMapper) {
		this.baseMapper = baseMapper;
		sysDictTypeMapper = (SysDictTypeMapper)baseMapper;
	}

	/**
	 * 新增或更新数据字典
	 * @throws HSException 
	 */
	@Override
	public void saveOrUpdateDictType(SysDictType dictType) throws HSException {
		if(dictType.getDictId() == null){
			sysDictTypeMapper.insert(dictType);
		}else{ 
			sysDictTypeMapper.updateByPrimaryKey(dictType);
		}
	}
}
