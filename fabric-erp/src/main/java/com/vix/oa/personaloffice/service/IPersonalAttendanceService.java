
package com.vix.oa.personaloffice.service;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.nvix.oa.attendance.entity.PunchCardRecord;

public interface IPersonalAttendanceService extends IBaseHibernateService {

	
	/** 查询头几条数据根据id排序 */
	public <T> List<T> findAllTopEntityByCount(Class<T> entityClass,String orderField,String orderBy,int count) throws Exception;
	/** 根据承租户编码查询头几条数据根据id排序 */
	public <T> List<T> findAllTopEntityByCount(Class<T> entityClass,String orderField,String orderBy,int count,String companyCode) throws Exception;
	/** 查询头几条数据根据id排序 */
	public List<PunchCardRecord> findAllTopEntityByCountAndConditions(Class<PunchCardRecord> entityClass,String orderField,String orderBy,int count,Map<String,Object> params) throws Exception;
	/** 查询头几条数据 */
	public <T> List<T> findAllTopEntityByHql(String hql,int count) throws Exception;
}
