package com.honest.sdms.system.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.honest.sdms.Constants;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.dao.SysDictDatasMapper;
import com.honest.sdms.system.entity.SysDictDatas;
import com.honest.sdms.system.service.ISysDictDatasService;

@Service
public class SysDictDatasServiceImp extends BaseServiceImp<SysDictDatas, Long> implements ISysDictDatasService{

	private SysDictDatasMapper sysDictDatasMapper;
	
	@Qualifier("sysDictDatasMapper")
	@Autowired
	@Override
	public void setBaseDao(IBaseMapper<SysDictDatas, Long> baseMapper) {
		this.baseMapper = baseMapper;
		sysDictDatasMapper = (SysDictDatasMapper)baseMapper;
	}
	
	/**
	 * 获取指定字典对应的键值对
	 */
	@Override
	public List<SysDictDatas> getDictDatasByDictId(Long dictId) {
		return sysDictDatasMapper.getDictDatasByDictId(dictId,Constants.getCurrentOrganizationId());
	}

	@Override
	public void saveOrUpdateDictDatas(List<SysDictDatas> list) {
		if(list != null && list.size() > 0){
			for(SysDictDatas dictData : list) {
				Long id = dictData.getId();
				if(id != null){
					this.updateByPrimaryKey(dictData);
				}else{
					this.insert(dictData);
				}
			}
		}
	}

	@Override
	public List<SysDictDatas> getDictDatasByDictCode(String dictCode) {
		return sysDictDatasMapper.getDictDatasByDictCode(dictCode,Constants.getCurrentOrganizationId());
	}

}
