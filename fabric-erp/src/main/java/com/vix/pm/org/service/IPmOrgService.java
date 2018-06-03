package com.vix.pm.org.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;
import com.vix.pm.org.entity.PmOrg;

public interface IPmOrgService extends IBaseHibernateService{
    
    /**
     * 得到业务部门分页信息
     * @param pager
     * @param params
     * @return
     * @throws Exception
     */
	Pager findBusinessOrgPager(Pager pager,Map<String, Object> params) throws Exception;
    
    //public PmOrg findBusinessOrgAll(String id) throws Exception;
	/**
	 * 查询整个业务组织列表findAllBusinessOrg
	 */
	List<PmOrg> findAllBusinessOrg() throws Exception;
	
	/**
	 * 查询业务组织树形结构
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Set<PmOrg> findBusinessOrgAll() throws Exception;
	
	
	/**
	 * 保存业务组织  并且返回该对象（父对象）
	 * @return
	 * @throws Exception
	 */
	PmOrg saveUpdateBusinessOrg(String bizOrgIds,String bizOrgType,PmOrg boForm, char parentType,String parentId) throws Exception;
	
	/**
	 * 查询所有的业务组织，并且如果orgUnitId 部门 包含了该业务组织则选中
	 * 0 业务组指树的json串 1 部门的业务组织id  2 部门的业务组织名称
	 * @param orgUnitId
	 * @return
	 * @throws Exception
	 */
	String[] findBusinessOrgAllForOrgUnit(String orgUnitId) throws Exception;
}
