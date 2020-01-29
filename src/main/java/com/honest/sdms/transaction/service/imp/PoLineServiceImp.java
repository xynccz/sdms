package com.honest.sdms.transaction.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.Constants;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.service.imp.BaseServiceImp;
import com.honest.sdms.transaction.dao.PoLineMapper;
import com.honest.sdms.transaction.entity.PoLine;
import com.honest.sdms.transaction.service.IPoLineService;

@Service
public class PoLineServiceImp extends BaseServiceImp<PoLine, Long> implements IPoLineService{

	private PoLineMapper poLineMapper;
	
	@Qualifier("poLineMapper")
	@Autowired
	@Override
	public void setBaseDao(IBaseMapper<PoLine, Long> baseMapper) {
		this.baseMapper = baseMapper;
		poLineMapper = (PoLineMapper)baseMapper;
	}
	
	@Override
	public void savePoLine(PoLine poLine) {
		insert(poLine);
	}

	@Override
	public List<PoLine> findPoLinesByPoHeaderId(Long poHeaderId) {
		return poLineMapper.findPoLinesByPoHeaderId(poHeaderId,Constants.getCurrentSysUser().getOrganizationId());
	}

	@Override
	public List<PoLine> findPoLinesByPoHeaderIds(Long[] poHeaderIds){
		return poLineMapper.findPoLinesByPoHeaderIds(poHeaderIds, Constants.getCurrentSysUser().getOrganizationId());
	}

}
