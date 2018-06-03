package com.vix.oa.personaloffice.dao;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.nvix.oa.attendance.entity.PunchCardRecord;

public interface IPersonalAttendanceDao extends IBaseHibernateDao {
	
	public <T> List<T> findAllTopEntityByCount(Class<T> entityClass, String orderField, String orderBy, int count) throws Exception;
	
	public <T> List<T> findAllTopEntityByCount(Class<T> entityClass, String orderField, String orderBy, int count,String companyCode) throws Exception;
	public <T> List<T> findAllTopEntityByCountAndConditions(Class<T> entityClass, String orderField, String orderBy, int count,Map<String, Object> params) throws Exception ;
	
	public <T> List<T> findAllTopEntityByHql(String hql, int count) throws Exception;
	/** 查询当日第一条数据*/
	public PunchCardRecord findFirstByConditions(Class<PunchCardRecord> entityClass,String orderField,String orderBy,Map<String,Object> params) throws Exception;
}
