package com.vix.common.org.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.meta.dao.IBaseMetaDataDao;
import com.vix.common.org.dao.IOrganizationDao;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.hql.OrganizationHqlProvider;
import com.vix.common.org.service.IOrganizationService;
import com.vix.common.security.dao.IRoleDao;
import com.vix.common.security.dao.IUserAccountDao;
import com.vix.common.security.entity.ConfigUrlAdd;
import com.vix.common.security.entity.Role;
import com.vix.common.security.entity.UserAccount;
import com.vix.core.constant.BizConstant;
import com.vix.core.encode.Md5Encoder;
import com.vix.core.encode.Md5EncoderImpl;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.system.base.compOperation.vo.CompanyOperationVO;

@Service("organizationService")
@Transactional
public class OrganizationServiceImpl extends BaseHibernateServiceImpl implements IOrganizationService {

	private static final long serialVersionUID = 1L;

	@Resource(name="organizationDao")
    private IOrganizationDao organizationDao;
	
	@Resource(name="organizationHqlProvider")
    private OrganizationHqlProvider organizationHqlProvider;
	
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
    public List<Organization> findSubOrganizationList(String id) throws Exception {
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
    	if(StringUtils.isEmpty(id) || id.equals("0")){//if(null == id || id == 0){
    		params.put("orgId", null);
    	}else{
    		params.put("orgId", id);
    	}
    	StringBuilder hql = organizationHqlProvider.findOrgList(params);
 
    	HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
 
    	List<Organization> orgList = organizationDao.findAllByHql2(hql.toString(), params);
    	return orgList;
    }

    @Override
    public List<Organization> findSubOrganizationListNoTenantId(String id) throws Exception{
    	 Map<String,Object> params = new HashMap<String,Object>();
    	 if(StringUtils.isEmpty(id) || id.equals("0")){// if(null == id || id == 0){
             params.put("orgId", null);
         }else{
             params.put("orgId", id);
         }
         StringBuilder hql = organizationHqlProvider.findOrgList(params);
         List<Organization> orgList = organizationDao.findAllByHql2NoTenantId(hql.toString(), params);
         return orgList;
    }

    /*
	@Override
	public Organization findOrganizationByCompCode(String compcode)	throws Exception {
		Map<String,Object> params = new HashMap<String,Object>();
        params.put("companyCode"+","+SearchCondition.EQUAL, compcode);
        StringBuilder hql = organizationHqlProvider.findOrgList(params, null, null, null);
        params.put("companyCode", compcode);
        List<Organization> orgList = organizationDao.findAllByHql(hql.toString(), params);
        if(orgList!=null && !orgList.isEmpty()){
        	return orgList.get(0);
        }
		return null;
	}
*/
    
    
	@Override
	public Organization saveCompanyOperation(CompanyOperationVO vo)throws Exception{
		Md5Encoder md5 = new Md5EncoderImpl();
		if(StrUtils.isNotEmpty(vo.getUserAccountId())){
			
			String  userAccountId = vo.getUserAccountId();
			//查询帐号的角色
			UserAccount userAccountTmp = findEntityById(UserAccount.class, userAccountId);
			if(StrUtils.isNotEmpty(vo.getId()) && StrUtils.isNotEmpty(vo.getOldPassword())){
				if(md5.encodeString(vo.getOldPassword()).equals(userAccountTmp.getPassword())){
					vo.setPassword(md5.encodeString(vo.getPassword()));
				}
			}else{
				vo.setPassword(userAccountTmp.getPassword());//默认密码
			}
		}else{
			vo.setPassword(md5.encodeString(vo.getPassword()));//默认密码
		}
		return organizationDao.saveCompanyOperation(vo,false);
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IOrganizationService#deleteCompanyOperation(java.lang.String)
	 */
	@Override
	public void deleteCompanyOperation(String id)throws Exception{
		Organization comp = organizationDao.findEntityById(Organization.class, id);
		
		
		UserAccount ua = comp.getCompSuperAdmin();
		Set<Role> uaRoles = ua.getRoles();
		
		String roleId = null;
		for(Role role:uaRoles){
			if(role.getRoleCode().contains(BizConstant.ROLE_COMP_PRE_SUPERADMIN)){
				roleId=role.getId();
				break;
			}
		}
		
		if(StringUtils.isNotEmpty(roleId)){
			organizationDao.deleteById(Role.class, roleId);
		}
		
		organizationDao.deleteByEntity(comp);
		organizationDao.deleteById(UserAccount.class, ua.getId());
		
		////ApplicationSecurityCode.refreshObject();
	}
	
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IOrganizationService#findCompByEmpId(java.lang.String)
	 */
	@Override
	public Organization findCompByEmpId(String empId)throws Exception{
		return organizationDao.findCompByEmpId(empId);
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IOrganizationService#findCompTopByCompInnerCode(java.lang.String)
	 */
	@Override
	public Organization findCompTopByCompInnerCode(String innerCode)throws Exception{
		Organization org = organizationDao.findCompByInnerCode(innerCode);
		return org;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IOrganizationService#findCompByCompSuperAdminId(java.lang.String)
	 */
	@Override
	public Organization findCompByCompSuperAdminId(String compAdminAccountId)throws Exception{
		String ename = "comp";
		StringBuilder hql = new StringBuilder();
		Map<String,Object> param = new HashMap<String,Object>();
		
		hql.append("select ").append(ename).append(" from ").append(Organization.class.getName());
		hql.append(" ").append(ename).append(" where ");
		hql.append(ename).append(".compSuperAdmin.id = :userAccountId ");
		param.put("userAccountId",compAdminAccountId);

		List<Organization> orgList = organizationDao.findAllByHql2NoTenantId(hql.toString(), param);
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
		
		hql.append("select comp from ").append(Organization.class.getName());
		hql.append(" comp where ");
		hql.append("comp.tenantId = :tenantId and comp.parentOrganization is null ");
		param.put("tenantId",tenantId);

		Organization comp = null;
		List<Organization> orgList = organizationDao.findAllByHql2NoTenantId(hql.toString(), param);
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
	
	
	/**
	 * @Title: findOrganizationByTenantId
	 * @Description: 根据承租户标识查询承租户
	 * @param @param tenantId
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return Organization    返回类型
	 * @throws
	 */
	@Override
	public Organization findOrganizationByTenantId(String tenantId)throws Exception{
		return organizationDao.findOrganizationByTenantId(tenantId);
	}
}
