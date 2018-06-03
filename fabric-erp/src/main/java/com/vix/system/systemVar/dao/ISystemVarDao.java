package com.vix.system.systemVar.dao;

import java.util.Map;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.web.Pager;

public interface ISystemVarDao extends IBaseHibernateDao {

	/**
	 * @Title: findSystemVarPager
	 * @Description: 系统变量的分页查询
	 * @param @param pager
	 * @param @param params
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return Pager    返回类型
	 * @throws
	 */
	Pager findSystemVarPager(Pager pager, Map<String, Object> params)throws Exception;
	
	/**
	 * @Title: findSystemVarDisplayViewByTenant
	 * @Description: 查询某承租户  或者超级管理员的  系统变量值（如果tenantId 为null 则查询的是超级管理员的数据）
	 * @param @param tenantId
	 * @param @return    设定文件
	 * @return Map<String,String>    返回类型
	 * @throws
	 */
	Map<String,String> findSystemVarDisplayViewByTenant(String tenantId);
}
