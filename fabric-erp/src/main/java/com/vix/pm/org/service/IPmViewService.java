package com.vix.pm.org.service;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;
import com.vix.pm.org.entity.PmOrgView;
import com.vix.pm.org.entity.PmView;

public interface IPmViewService extends IBaseHibernateService{

	/**
	 * 
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	Pager findBusinessViewPager(Pager pager, Map<String, Object> params) throws Exception;
	
	/**
	 * 
	 * @param entityForm
	 * @return
	 * @throws Exception
	 */
	PmView saveOrUpdateBusinessView(PmView entityForm)throws Exception;
	
	/**
	 * 业务组织视图和业务组织的树
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<PmOrgView> findOrgViewList(String id) throws Exception;
}
