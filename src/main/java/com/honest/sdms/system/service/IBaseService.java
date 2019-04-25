package com.honest.sdms.system.service;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.honest.sdms.basedata.entity.BaseVO;

public interface IBaseService<T extends BaseVO, PK extends Serializable> {
	
	int insert(T model);

    int insertSelective(T model);

    int updateByPrimaryKeySelective(T model);

    int updateByPrimaryKey(T model);
    
    T selectByPrimaryKey(PK id);
    
    int deleteByPrimaryKey(PK id);
    
    int count(T cond);
    
    List<T> findByCond(T cond);
    
    /**
     * 分页查询
     * @param cond 查询对象
     * @param sortName 排序字段
     * @param sortOrder 排序顺序
     * @param pageNum 页数
     * @param pageSize 每页大小
     * @return 分页对象
     */
    PageInfo<T> findByCondWithPage(T cond,String sortName, String sortOrder, int pageNum, int pageSize);

}
