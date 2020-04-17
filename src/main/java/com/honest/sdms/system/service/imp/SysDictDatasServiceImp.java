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

	/**
	 * 修改或保存数据字典明细信息
	 * 注意事项：字典键值和标签不允许更新，只能新增
	 * 系统其它地方已保存了id，如果更新的话信息就不准确了。
	 */
	@Override
	public void saveOrUpdateDictDatas(List<SysDictDatas> list) {
		if(list != null && list.size() > 0){
			for(SysDictDatas dictData : list) {
				Long id = dictData.getId();
				if(id != null){
					//1、做此步骤的作用：预防前台做更新，就算前台做了更新，后台还是会把数据还原
					SysDictDatas obj = this.selectByPrimaryKey(id);
					dictData.setDictDataCode(obj.getDictDataCode());
					dictData.setDictDataName(obj.getDictDataName());
					//2、再对数据做更新
					updateByPrimaryKey(dictData);
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
