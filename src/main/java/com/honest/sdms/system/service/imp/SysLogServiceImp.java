package com.honest.sdms.system.service.imp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.honest.sdms.Constants;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.dao.SysLogMapper;
import com.honest.sdms.system.entity.SysLog;
import com.honest.sdms.system.service.ISysLogService;
import com.honest.sdms.tools.StringUtil;

@Service
public class SysLogServiceImp extends BaseServiceImp<SysLog, Long> implements ISysLogService{
	
	private SysLogMapper sysLogMapper;

	@Qualifier("sysLogMapper")
	@Autowired
	@Override
	public void setBaseDao(IBaseMapper<SysLog, Long> baseMapper) {
		this.baseMapper = baseMapper;
		sysLogMapper = (SysLogMapper)baseMapper;
	}

	@Override
	public void saveSysLog(SysLog log) {
		log.setOrganizationId(Constants.getCurrentOrganizationId());
		sysLogMapper.insert(log);
	}

	@Override
	public PageInfo<SysLog> findByCondWithPage(SysLog cond, String sortName, String sortOrder, int pageNum, int pageSize) {
		String selectData = cond.getSelectDatas();
		if(!StringUtil.isNullOrEmpty(selectData)){
			selectData = StringUtil.replace(selectData, new String[] {"[","]"}, new String[] {"",""});
			String[] rangeDatas = Constants.SPLIT.split(selectData);
			cond.setCreatedDateStart(new Date(Long.parseLong(rangeDatas[0])));
			cond.setCreatedDateEnd(new Date(Long.parseLong(rangeDatas[1])));
		}
		return super.findByCondWithPage(cond, sortName, sortOrder, pageNum, pageSize);
	}
	
}
