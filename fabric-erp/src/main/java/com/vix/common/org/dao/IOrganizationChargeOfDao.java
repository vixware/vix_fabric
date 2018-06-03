package com.vix.common.org.dao;

import java.util.Set;

import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationChargeOf;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

/**
 * 分管 公司或者部门
 * 
 * @author Administrator
 *
 */
public interface IOrganizationChargeOfDao extends IBaseHibernateDao {

	/**
	 * 根据职员id 查询 分管信息
	 * 
	 * @param empolyeeId
	 * @return
	 * @throws Exception
	 */
	OrganizationChargeOf findChargeOfByEmpId(String empolyeeId) throws Exception;

	/**
	 * 根据职员id 查询所有该职员分管的公司列表
	 * 
	 * @param empolyeeId
	 * @return
	 * @throws Exception
	 */
	Set<Organization> findChargeOfOrgByEmpId(String empolyeeId) throws Exception;

	/**
	 * 根绝职员id 查询所有该职员分管的部门列表
	 * 
	 * @param empolyeeId
	 * @return
	 * @throws Exception
	 */
	Set<OrganizationUnit> findChargeOfOrgUnitByEmpId(String empolyeeId) throws Exception;

}
