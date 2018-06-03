package com.vix.common.org.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.meta.dao.IBaseMetaDataDao;
import com.vix.common.org.dao.IBaseOrganizationDao;
import com.vix.common.org.hql.BaseOrganizationHqlProvider;
import com.vix.common.org.service.IBaseOrganizationService;
import com.vix.common.security.dao.IRoleDao;
import com.vix.common.security.dao.IUserAccountDao;
import com.vix.common.security.entity.ConfigUrlAdd;
import com.vix.common.share.entity.BaseOrganization;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.web.Pager;

@Service("baseOrganizationService")
@Transactional
public class BaseOrganizationServiceImpl extends BaseHibernateServiceImpl implements IBaseOrganizationService {

	private static final long serialVersionUID = 1L;

	@Resource(name="baseOrganizationDao")
    private IBaseOrganizationDao organizationDao;
	
	@Resource(name="baseOrganizationHqlProvider")
    private BaseOrganizationHqlProvider organizationHqlProvider;
	
	@Resource(name="userAccountDao")
    private IUserAccountDao userAccountDao;
	
	@Resource(name="roleDao")
	private IRoleDao roleDao;
	
	@Resource(name="baseMetaDataDao")
	private IBaseMetaDataDao baseMetaDataDao;
	
	
	@Override
	public Pager findOrganizationPager4Comp(Pager pager,Map<String,Object> reqParams) throws Exception{
		StringBuilder hql = organizationHqlProvider.findOrgPager(reqParams,pager);
		
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(reqParams);//不处理CompanyInnerCode;
        pager = organizationDao.findPager2ByHql(pager,organizationHqlProvider.entityAsName(),hql.toString(), reqParams);
        return pager;
	}
	
	@Override
	public Pager findOrganizationPager4NoComp(Pager pager,Map<String,Object> reqParams) throws Exception{
		StringBuilder hql = organizationHqlProvider.findOrgPager(reqParams,pager);
		
		//HqlTenantIdUtil.handleParamMap4CompanyInnerCode(reqParams);//不处理CompanyInnerCode;
        pager = organizationDao.findPager2ByHql(pager,organizationHqlProvider.entityAsName(),hql.toString(), reqParams);
        return pager;
	}
	
    @Override
    public List<BaseOrganization> findSubBaseOrganizationList(String id) throws Exception {
       /* Map<String,Object> params = new HashMap<String,Object>();
        if(null == id || id == 0){
            params.put("parentOrganization.id"+","+SearchCondition.IS, "NULL");
        }else{
            params.put("parentOrganization.id"+","+SearchCondition.EQUAL, id);
        }
        StringBuilder hql = organizationHqlProvider.findOrgList(params, null, null, null);
        
        HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
        
        List<Organization> orgList = organizationDao.findAllByHql(hql.toString(), params);
        return orgList;*/
    	Map<String,Object> params = new HashMap<String,Object>();
    	if(StringUtils.isEmpty(id) || id.equals("0")){//null == id || id == 0
    		params.put("orgId", null);
    	}else{
    		params.put("orgId", id);
    	}
    	StringBuilder hql = organizationHqlProvider.findOrgList(params);
 
    	HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
 
    	List<BaseOrganization> orgList = organizationDao.findAllByHql2(hql.toString(), params);
    	return orgList;
    }

    @Override
    public List<BaseOrganization> findSubBaseOrganizationListNoTenantId(String id) throws Exception{
    	 Map<String,Object> params = new HashMap<String,Object>();
    	 if(StringUtils.isEmpty(id) || id.equals("0")){//if(null == id || id == 0){
             params.put("orgId", null);
         }else{
             params.put("orgId", id);
         }
         StringBuilder hql = organizationHqlProvider.findOrgList(params);
         List<BaseOrganization> orgList = organizationDao.findAllByHql2NoTenantId(hql.toString(), params);
         return orgList;
    }

	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IOrganizationService#findCompByEmpId(java.lang.String)
	 */
	@Override
	public BaseOrganization findCompByEmpId(String empId)throws Exception{
		return organizationDao.findCompByEmpId(empId);
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IOrganizationService#findCompTopByCompInnerCode(java.lang.String)
	 */
	@Override
	public BaseOrganization findCompTopByCompInnerCode(String innerCode)throws Exception{
		BaseOrganization org = organizationDao.findCompByInnerCode(innerCode);
		return org;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IOrganizationService#findCompByCompSuperAdminId(java.lang.String)
	 */
	@Override
	public BaseOrganization findCompByCompSuperAdminId(String compAdminAccountId)throws Exception{
		String ename = "comp";
		StringBuilder hql = new StringBuilder();
		Map<String,Object> param = new HashMap<String,Object>();
		
		hql.append("select ").append(ename).append(" from ").append(BaseOrganization.class.getName());
		hql.append(" ").append(ename).append(" where ");
		hql.append(ename).append(".compSuperAdmin.id = :userAccountId ");
		param.put("userAccountId",compAdminAccountId);

		List<BaseOrganization> orgList = organizationDao.findAllByHql2NoTenantId(hql.toString(), param);
		if(orgList!=null && orgList.size()>0){
			return orgList.get(0);
		}
		return null;
	}
	
	/* 
	 * @see com.vix.common.org.service.IOrganizationService#findAllConfigUrlList()
	 */ 
	@Override
	public List<ConfigUrlAdd> findAllConfigUrlList()throws Exception{
		List<ConfigUrlAdd> urlList = organizationDao.findAllByHqlOrginial("select cu from ConfigUrlAdd cu ", new HashMap<String, Object>());
		if(urlList == null){
			urlList = new ArrayList<ConfigUrlAdd>();
		}
		return urlList;
	}
	
	/* 
	 * @see com.vix.common.org.service.IOrganizationService#findTargetUrlOfTenantByTenantId(java.lang.String)
	 */ 
	@Override
	public ConfigUrlAdd findTargetUrlOfTenantByTenantId(String tenantId)throws Exception{
		if(StringUtils.isEmpty(tenantId)){
			return null;
		}
		StringBuilder hql = new StringBuilder();
		Map<String,Object> param = new HashMap<String,Object>();
		
		hql.append("select comp from ").append(BaseOrganization.class.getName());
		hql.append(" comp where ");
		hql.append("comp.tenantId = :tenantId and comp.parentOrganization is null ");
		param.put("tenantId",tenantId);

		BaseOrganization comp = null;
		List<BaseOrganization> orgList = organizationDao.findAllByHql2NoTenantId(hql.toString(), param);
		if(orgList!=null && orgList.size()>0){
			comp = orgList.get(0);
		}
		if(comp==null || StringUtils.isEmpty(comp.getConfigUrlCode())){
			return null;
		}
		
		hql.setLength(0);
		param.clear();
		
		hql.append(" select cu from ConfigUrlAdd cu where  cu.code = :code ");
		param.put("code", comp.getConfigUrlCode());
		List<ConfigUrlAdd> urlList = organizationDao.findAllByHqlOrginial(hql.toString(),param);
		if(urlList!=null && !urlList.isEmpty()){
			return urlList.get(0);
		}
		return null;
	}

}
