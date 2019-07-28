package com.honest.sdms.transaction.service;

import java.util.List;

import com.honest.sdms.basedata.exceptions.HSException;
import com.honest.sdms.system.service.IBaseService;
import com.honest.sdms.transaction.entity.PoHeader;
import com.honest.sdms.transaction.entity.PoLine;

public interface IPoHeaderService extends IBaseService<PoHeader, Long>{

	/**
	 * 保存PO信息
	 * @param poHeader
	 * @param poLines
	 */
	abstract void savePo(PoHeader poHeader, List<PoLine> poLines)throws HSException;
	
	abstract String createPoNumber(String item);
}