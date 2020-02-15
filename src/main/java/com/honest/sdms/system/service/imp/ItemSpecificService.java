package com.honest.sdms.system.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.dao.ItemSpecificMapper;
import com.honest.sdms.system.entity.ItemSpecific;
import com.honest.sdms.system.service.IItemSpecificService;

@Service
public class ItemSpecificService extends BaseServiceImp<ItemSpecific, Long> implements IItemSpecificService{

	private ItemSpecificMapper itemSpecificMapper;
	
	@Autowired
	@Qualifier("itemSpecificMapper")
	@Override
	public void setBaseDao(IBaseMapper<ItemSpecific, Long> baseMapper) {
		this.baseMapper = baseMapper;
		itemSpecificMapper = (ItemSpecificMapper)baseMapper;
		
	}

	@Override
	public void saveOrUpdateItemSpecificConfigs(List<ItemSpecific> list)throws HSException{
		if(list != null && list.size() > 0){
			for(ItemSpecific obj : list) {
				Long id = obj.getId();
				if(id != null){
					updateByPrimaryKey(obj);
				}else{
					insert(obj);
				}
			}
		}
	}
	
	

}
