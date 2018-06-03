package com.vix.system.base.compOperation.service;

import java.util.Map;

import com.vix.common.org.entity.Organization;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface ICompanyOperationService extends IBaseHibernateService {
    
	/**
	 * 公司的分页信息
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	Pager findOrganizationPager(Pager pager,Map<String,Object> params) throws Exception;
	
	
    /**
     * 根据公司编码查询公司
     * @param compcode
     * @return
     * @throws Exception
     */
    Organization findOrganizationByCompCode(String compcode)throws Exception;
    
}
