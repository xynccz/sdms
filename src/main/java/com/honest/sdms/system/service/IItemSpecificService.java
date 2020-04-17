package com.honest.sdms.system.service;

import java.util.List;

import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.entity.ItemSpecific;

public interface IItemSpecificService extends IBaseService<ItemSpecific, Long>{

	abstract void saveOrUpdateItemSpecificConfigs(List<ItemSpecific> list)throws HSException;
	
	abstract List<ItemSpecific> getItemSpecificListByItemId(Long itemId);
}
