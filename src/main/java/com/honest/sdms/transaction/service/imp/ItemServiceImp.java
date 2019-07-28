package com.honest.sdms.transaction.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.service.imp.BaseServiceImp;
import com.honest.sdms.transaction.dao.ItemMapper;
import com.honest.sdms.transaction.entity.Item;
import com.honest.sdms.transaction.service.IItemService;

/**
 * 产品资料服务操作类
 * @author beisi
 *
 */
@Service
public class ItemServiceImp extends BaseServiceImp<Item, Long> implements IItemService{
	
	private ItemMapper itemMapper;

	@Autowired
	@Qualifier("itemMapper")
	@Override
	public void setBaseDao(IBaseMapper<Item, Long> baseMapper) {
		this.baseMapper = baseMapper;
		itemMapper = (ItemMapper)baseMapper;
	}

	@Override
	public Item getItemByItemId(Long itemId) {
		return itemMapper.selectByPrimaryKey(itemId);
	}
	
}
