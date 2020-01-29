package com.honest.sdms.system.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.system.dao.DownloadRecordsMapper;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.entity.DownloadRecords;
import com.honest.sdms.system.service.IDownloadRecordsService;

@Service
public class DownloadRecordsServiceImp extends BaseServiceImp<DownloadRecords, Long> implements IDownloadRecordsService{

	private DownloadRecordsMapper downloadRecordsMapper;
	
	@Autowired
	@Qualifier("downloadRecordsMapper")
	@Override
	public void setBaseDao(IBaseMapper<DownloadRecords, Long> baseMapper) {
		this.baseMapper = baseMapper;
		downloadRecordsMapper = (DownloadRecordsMapper)baseMapper;
	}

	@Override
	public void saveDownloadRecords(List<DownloadRecords> records) {
		if(records != null && records.size() > 0){
			for(DownloadRecords record : records) {
				downloadRecordsMapper.insert(record);
			}
		}
		
	}

	
}
