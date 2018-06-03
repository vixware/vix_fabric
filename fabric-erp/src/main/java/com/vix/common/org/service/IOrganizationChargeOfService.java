package com.vix.common.org.service;

import java.util.List;
import java.util.Set;

import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationChargeOf;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

/**
 * 分管公司部门的业务层
 * 
 * @author Administrator
 *
 */
public interface IOrganizationChargeOfService extends IBaseHibernateService {

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
	List<Organization> findChargeOfOrgListByEmpId(String empolyeeId) throws Exception;

	/**
	 * 根绝职员id 查询所有该职员分管的部门列表
	 * 
	 * @param empolyeeId
	 * @return
	 * @throws Exception
	 */
	Set<OrganizationUnit> findChargeOfOrgUnitByEmpId(String empolyeeId) throws Exception;
	List<OrganizationUnit> findChargeOfOrgUnitListByEmpId(String empolyeeId) throws Exception;

	/**
	 * 保存分管公司和部门
	 * 
	 * @param orgType
	 *            组织类型 C 公司 O 部门
	 * @param empId
	 *            职员id
	 * @param orgId
	 *            公司id
	 * @param orgUnitIds
	 *            部门id，逗号分割
	 * @throws Exception
	 */
	void saveChargeOf(String orgType, String empId, String orgId, String orgUnitIds) throws Exception;

	/**
	 * 删除分管公司或者部门
	 * 
	 * @param orgType
	 * @param empId
	 * @param orgOrUnitId
	 * @throws Exception
	 */
	void deleteChargeOf(String orgType, String empId, String orgOrUnitId) throws Exception;
}
