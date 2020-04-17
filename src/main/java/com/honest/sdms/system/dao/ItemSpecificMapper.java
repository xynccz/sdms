package com.honest.sdms.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honest.sdms.system.entity.ItemSpecific;

public interface ItemSpecificMapper extends IBaseMapper<ItemSpecific, Long>{

	abstract List<ItemSpecific> getItemSpecificListByItemId(@Param("itemId") Long itemId,@Param("organizationId") Long organizationId);
}