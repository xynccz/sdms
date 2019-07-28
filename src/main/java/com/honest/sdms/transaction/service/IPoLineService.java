package com.honest.sdms.transaction.service;

import java.util.List;

import com.honest.sdms.system.service.IBaseService;
import com.honest.sdms.transaction.entity.PoLine;

public interface IPoLineService extends IBaseService<PoLine, Long>{
	
	abstract void savePoLine(PoLine poLine);
	
	abstract List<PoLine> findPoLinesByPoHeaderId(Long poHeaderId);

}
