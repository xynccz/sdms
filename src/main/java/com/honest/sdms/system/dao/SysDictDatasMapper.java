package com.honest.sdms.system.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.honest.sdms.system.entity.SysDictDatas;

public interface SysDictDatasMapper extends IBaseMapper<SysDictDatas, Long>{
    
	abstract List<SysDictDatas> getDictDatasByDictId(@Param("dictId") Long dictId,@Param("organizationId") Long organizationId);
	
	abstract List<SysDictDatas> getDictDatasByDictCode(@Param("dictCode") String dictCode,@Param("organizationId") Long organizationId);
	
	
}