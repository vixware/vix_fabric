package com.vix.common.org.service;

import java.util.List;
import java.util.Set;

import com.vix.common.share.entity.BaseOrganization;
import com.vix.common.share.entity.BaseOrganizationChargeOf;
import com.vix.common.share.entity.BaseOrganizationUnit;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

/**
 * 分管公司部门的业务层
 * @author Administrator
 *
 */
public interface IBaseOrganizationChargeOfService extends IBaseHibernateService{
    
	/**
	 * 根据职员id 查询 分管信息
	 * @param empolyeeId
	 * @return
	 * @throws Exception
	 */
	BaseOrganizationChargeOf findChargeOfByEmpId(String empolyeeId) throws Exception;
	
	/**
	 * 根据职员id 查询所有该职员分管的公司列表
	 * @param empolyeeId
	 * @return
	 * @throws Exception
	 */
	Set<BaseOrganization> findChargeOfOrgByEmpId(String empolyeeId) throws Exception;
	List<BaseOrganization> findChargeOfOrgListByEmpId(String empolyeeId) throws Exception;
	
	/**
	 * 根绝职员id 查询所有该职员分管的部门列表
	 * @param empolyeeId
	 * @return
	 * @throws Exception
	 */
	Set<BaseOrganizationUnit> findChargeOfOrgUnitByEmpId(String empolyeeId) throws Exception;
	List<BaseOrganizationUnit> findChargeOfOrgUnitListByEmpId(String empolyeeId) throws Exception;
	
}
