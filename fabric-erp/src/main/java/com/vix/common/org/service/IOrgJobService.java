package com.vix.common.org.service;

import java.util.List;

import com.vix.common.org.entity.OrgJob;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

public interface IOrgJobService extends IBaseHibernateService{
	
	
	
	/**
	 * 根据公司id 得到所有的职位id
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public List<OrgJob> findOrgJobByOrgId(String orgId) throws Exception;
	
	/**
	 * 根据部门id 得到所有的职位id
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public List<OrgJob> findOrgJobByOrgUnitId(String orgUnitId) throws Exception;
	
	/**
	 * 查询职员列表
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 
	public Pager findEmpAccountPager(Pager pager,String empId) throws Exception;
	*/
	/**
	 * 保存职员基本信息
	 * @param emp
	 * @return
	 * @throws Exception
	 
	Employee saveOrUpdateEmp(Employee emp) throws Exception;
	*/
}
