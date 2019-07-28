package com.honest.sdms.transaction.service;

import com.honest.sdms.system.service.IBaseService;
import com.honest.sdms.transaction.entity.Item;

public interface IItemService extends IBaseService<Item, Long>{

	abstract Item getItemByItemId(Long itemId);
}
