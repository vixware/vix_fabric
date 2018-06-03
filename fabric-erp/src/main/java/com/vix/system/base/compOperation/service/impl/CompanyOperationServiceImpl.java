package com.vix.system.base.compOperation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.dao.IOrganizationDao;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.hql.OrganizationHqlProvider;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.web.Pager;
import com.vix.system.base.compOperation.service.ICompanyOperationService;

@Service("companyOperationService")
@Transactional
public class CompanyOperationServiceImpl extends BaseHibernateServiceImpl implements ICompanyOperationService {

	private static final long serialVersionUID = 1L;

	@Resource(name="organizationDao")
    private IOrganizationDao organizationDao;
	
	@Resource(name="organizationHqlProvider")
    private OrganizationHqlProvider organizationHqlProvider;
	
	
	@Override
	public Pager findOrganizationPager(Pager pager,Map<String,Object> reqParams) throws Exception{
		StringBuilder hql = organizationHqlProvider.findOrgPager(reqParams,pager);
        pager = organizationDao.findPager2ByHql(pager,organizationHqlProvider.entityAsName(),hql.toString(), reqParams);
        return pager;
	}
	
	@Override
	public Organization findOrganizationByCompCode(String compcode)	throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
        params.put("companyCode", compcode);
        StringBuilder hql = organizationHqlProvider.findOrgList(params);
        List<Organization> orgList = organizationDao.findAllByHql2(hql.toString(), params);
        if(orgList!=null && !orgList.isEmpty()){
        	return orgList.get(0);
        }
		return null;
	}

}
