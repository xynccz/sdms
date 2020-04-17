package com.honest.sdms.system.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.Constants;
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

	/**
	 * 修改或保存产品规格信息
	 * 注意事项：产品的等级和规格信息这2个值不允许更新，只能新增，因为更新了会影响之前生成的订单信息，
	 * 系统其它地方已保存了id，如果更新的话信息就不准确了。
	 */
	@Override
	public void saveOrUpdateItemSpecificConfigs(List<ItemSpecific> list)throws HSException{
		if(list != null && list.size() > 0){
			for(ItemSpecific obj : list) {
				Long id = obj.getId();
				if(id != null){
					//1、做此步骤的作用：预防前台做更新，就算前台做了更新，后台还是会把数据还原
					ItemSpecific itemSpecific = selectByPrimaryKey(id);
					obj.setGradeId(itemSpecific.getGradeId());
					obj.setStandardId(itemSpecific.getStandardId());
					//2、再对数据做更新
					updateByPrimaryKey(obj);
				}else{
					insert(obj);
				}
			}
		}
	}

	@Override
	public List<ItemSpecific> getItemSpecificListByItemId(Long itemId) {
		return itemSpecificMapper.getItemSpecificListByItemId(itemId,Constants.getCurrentOrganizationId());
	}

}
