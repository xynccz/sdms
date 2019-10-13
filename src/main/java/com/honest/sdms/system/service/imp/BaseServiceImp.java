package com.honest.sdms.system.service.imp;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.honest.sdms.Constants;
import com.honest.sdms.system.dao.IBaseMapper;
import com.honest.sdms.system.entity.BaseVO;
import com.honest.sdms.system.service.IBaseService;
import com.honest.sdms.tools.DateTimeUtil;

public abstract class BaseServiceImp<T extends BaseVO, PK extends Serializable> implements IBaseService<T, PK> {

	protected IBaseMapper<T, PK> baseMapper;
    
    public abstract void setBaseDao(IBaseMapper<T, PK> baseMapper);
    
	@Override
	public int insert(T model) {
		if(model.getOrganizationId() == null)
			model.setOrganizationId(Constants.getCurrentOrganizationId());
		model.setCreatedBy(Constants.getCurrentSysUser().getLoginName());
		model.setLastUpdatedBy(model.getCreatedBy());
		model.setCreatedDate(new DateTimeUtil().toTimestamp());
		model.setLastUpdatedDate(new DateTimeUtil().toTimestamp());
		return baseMapper.insert(model);
	}

	@Override
	public int insertSelective(T model) {
		if(model.getOrganizationId() == null)
			model.setOrganizationId(Constants.getCurrentOrganizationId());
		model.setCreatedBy(Constants.getCurrentSysUser().getLoginName());
		model.setLastUpdatedBy(model.getCreatedBy());
		model.setCreatedDate(new DateTimeUtil().toTimestamp());
		model.setLastUpdatedDate(new DateTimeUtil().toTimestamp());
		return baseMapper.insertSelective(model);
	}

	@Override
	public int updateByPrimaryKeySelective(T model) {
		model.setLastUpdatedBy(Constants.getCurrentSysUser().getLoginName());
		model.setLastUpdatedDate(new DateTimeUtil().toTimestamp());
		return baseMapper.updateByPrimaryKeySelective(model);
	}

	@Override
	public int updateByPrimaryKey(T model) {
		model.setLastUpdatedBy(Constants.getCurrentSysUser().getLoginName());
		model.setLastUpdatedDate(new DateTimeUtil().toTimestamp());
		return baseMapper.updateByPrimaryKey(model);
	}

	@Override
	public T selectByPrimaryKey(PK id) {
		return baseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKey(PK id) {
		return baseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<T> findByCond(T cond) {
		if(cond.getOrganizationId() == null)
			cond.setOrganizationId(Constants.getCurrentOrganizationId());
		
		return baseMapper.findByCond(cond);
	}

	@Override
	public int count(T cond) {
		if(cond.getOrganizationId() == null)
			cond.setOrganizationId(Constants.getCurrentOrganizationId());
		
		return baseMapper.count(cond);
	}

	/**
     * 利用PageHelper分页工具实现分页查询功能
     * @param cond 查询对象
     * @param sortName 排序字段
     * @param sortOrder 排序顺序
     * @param pageNum 页数
     * @param pageSize 每页大小
     * @return 分页对象
     */
	@Override
	public PageInfo<T> findByCondWithPage(T cond, String sortName, String sortOrder, int pageNum, int pageSize) {
		if(cond.getOrganizationId() == null)
			cond.setOrganizationId(Constants.getCurrentOrganizationId());
		
		PageHelper.startPage(pageNum, pageSize);
		if(sortName != null) {
			PageHelper.orderBy(sortName+" "+(sortOrder == null?"asc":sortOrder));
		}
		
        List<T> list = baseMapper.findByCond(cond);
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        return pageInfo;
	}

}
