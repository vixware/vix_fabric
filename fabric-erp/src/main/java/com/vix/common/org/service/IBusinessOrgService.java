package com.vix.common.org.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vix.common.org.entity.BusinessOrg;
import com.vix.common.org.entity.BusinessOrgDetail;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.entity.Role;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;

public interface IBusinessOrgService extends IBaseHibernateService{
    
    /**
     * 得到业务部门分页信息
     * @param pager
     * @param params
     * @return
     * @throws Exception
     */
	Pager findBusinessOrgPager(Pager pager,Map<String, Object> params) throws Exception;
    
    //public BusinessOrg findBusinessOrgAll(Long id) throws Exception;
	/**
	 * 查询整个业务组织列表findAllBusinessOrg
	 */
	List<BusinessOrg> findAllBusinessOrg() throws Exception;
	
	/**
	 * 查询业务组织树形结构
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Set<BusinessOrg> findBusinessOrgAll() throws Exception;
	
	
	/**
	 * 保存业务组织  并且返回该对象（父对象）
	 * @return
	 * @throws Exception
	 */
	BusinessOrg saveUpdateBusinessOrg(BusinessOrg boForm, char parentType,String parentId) throws Exception;
	
	/**
	 * 不可用 
	 * 查询所有的业务组织，并且如果orgUnitId 部门 包含了该业务组织则选中
	 * 0 业务组指树的json串 1 部门的业务组织id  2 部门的业务组织名称
	 * @param orgUnitId
	 * @return
	 * @throws Exception
	 */
	//String[] findBusinessOrgAllForOrgUnit(Long orgUnitId) throws Exception;
	
	//以下为业务组织明细的相关方法
	
	/**
	 * 根据业务组织id查询业务组织明细列表
	 * @param boId
	 * @param bizOrgType
	 * @return
	 * @throws Exception
	 */
	List<BusinessOrgDetail> findBusinessOrgDetailByBoId(String boId,String bizOrgType)throws Exception;
	
	/**
	 * 查询部门列表
	 * @param boId
	 * @return
	 * @throws Exception
	 */
	List<OrganizationUnit> findBoOrgUnitDetailByBoId(String boId)throws Exception;
	/**
	 * 查询职员列表
	 * @param boId
	 * @return
	 * @throws Exception
	 */
	List<Employee> findBoEmpDetailByBoId(String boId)throws Exception;
	/**
	 * 查询角色列表
	 * @param boId
	 * @return
	 * @throws Exception
	 */
	List<Role> findBoRoleDetailByBoId(String boId)throws Exception;
	
	/**
	 * 保存业务组织明细     部门 职员 角色
	 * @param boId 业务组织id
	 * @param bizOrgId 部门 或者 职员  或者角色id
	 * @param bizOrgType 类型  O  E  R
	 * @throws Exception
	 */
	void saveBoDetail(String boId,String[] bizOrgId,String bizOrgType)throws Exception;
	
	/**
	 *  删除明细业务组织
	 * @param id 业务组织id
	 * @param detailBizOrgId  具体业务组织的id 如部门  员工
	 * @param bizOrgType 类型
	 * @throws Exception
	 */
	void deleteDetail(String id,String detailBizOrgId,String bizOrgType)throws Exception;
}
