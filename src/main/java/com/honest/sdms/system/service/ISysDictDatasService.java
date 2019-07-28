package com.honest.sdms.system.service;

import java.util.List;

import com.honest.sdms.system.entity.SysDictDatas;

public interface ISysDictDatasService extends IBaseService<SysDictDatas, Long>{
	
	abstract List<SysDictDatas> getDictDatasByDictId(Long dictId);
	
	abstract void saveOrUpdateDictDatas(List<SysDictDatas> list);
	
	abstract List<SysDictDatas> getDictDatasByDictCode(String dictCode);

}
