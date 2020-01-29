package com.honest.sdms.system.service;

import java.util.List;

import com.honest.sdms.system.entity.DownloadRecords;

public interface IDownloadRecordsService extends IBaseService<DownloadRecords, Long>{

	abstract void saveDownloadRecords(List<DownloadRecords> records);
}
