package com.honest.sdms.transaction.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.transaction.entity.PoLine;

public interface PoLineMapper extends IBaseMapper<PoLine, Long>{
	
	List<PoLine> findPoLinesByPoHeaderId(@Param("poHeaderId") Long poHeaderId);
}