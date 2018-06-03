package com.vix.common.org.service;

import java.util.List;

import com.vix.common.org.entity.OrgDrpView;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

public interface IOrgDrpViewService extends IBaseHibernateService{
	
	/**
	 * 返回组织结构树（分销）   根节点为所在公司
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<OrgDrpView> findOrgDrpViewList(String id) throws Exception;
	
	/**
	 * 根据id 查询子节点  包括分销
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<OrgDrpView> findOrgDrpViewListByParentId(String id)throws Exception;
}
