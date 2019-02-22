package com.honest.sdms.basedata.dao;

import java.io.Serializable;

import com.honest.sdms.basedata.entity.BaseVO;

/**
 * DAO基础类
 * @author beisi
 *
 * @param <T>
 * @param <PK>
 */
public interface IBaseDao<T extends BaseVO, PK extends Serializable>{

    int insert(T model);

    int insertSelective(T model);

    int updateByPrimaryKeySelective(T model);

    int updateByPrimaryKey(T model);
    
    T selectByPrimaryKey(PK id);
    
    int deleteByPrimaryKey(PK id);

}
