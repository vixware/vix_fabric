package com.vix.common.org.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.vix.common.org.dao.IBaseOrganizationDao;
import com.vix.common.org.entity.OrgView;
import com.vix.common.security.entity.Role;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.share.entity.BaseEmployee;
import com.vix.common.share.entity.BaseOrganization;
import com.vix.common.share.entity.BaseOrganizationUnit;
import com.vix.core.constant.BizConstant;
import com.vix.core.exception.BizException;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.core.utils.StrUtils;

import jodd.util.RandomStringUtil;

@Repository("baseOrganizationDao")
public class BaseOrganizationDaoImpl extends BaseHibernateDaoImpl implements IBaseOrganizationDao {


	@Override
	public BaseOrganization findTenantByTenantId(String tenantId)throws Exception{
		StringBuilder hql = new StringBuilder();
		Map<String,Object> param = new HashMap<String,Object>();
		
		hql.append("select org from ").append(BaseOrganization.class.getName());
		hql.append(" org where ");
		hql.append("org.tenantId = :tenantId ");
		param.put("tenantId", tenantId);
		hql.append(" and org.parentOrganization is null ");
		
		BaseOrganization org = findFirstByHqlOrginial(hql.toString(), param);//findObjectByHqlNoTenantId(hql.toString(), param);
		return org;
	}
	
	/**
	 * 生成公司编码
	 * @param parentCode
	 * @return
	 */
	private String makeCompanyCode(String parentCode){
		String compCode = RandomStringUtil.randomNumeric(2);
		if(StrUtils.isNotEmpty(parentCode)){
			//compCode = parentCode.trim() + "#" + compCode;
			compCode = parentCode.trim() + compCode;
		}
		return compCode;
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.dao.IOrganizationDao#find4CompCode(java.lang.String)
	 */
	@Override
	public String find4CompCode(String parentCode)throws Exception{
		String compCode = makeCompanyCode(parentCode);
		
		String ename = "comp";
		StringBuilder hql = new StringBuilder();
		Map<String,Object> param = new HashMap<String,Object>();
		
		hql.append("select count(").append(ename).append(".id) from ").append(BaseOrganization.class.getName());
		hql.append(" ").append(ename).append(" where ");
		hql.append(ename).append(".companyInnerCode = :companyInnerCode ");
		param.put("companyInnerCode", compCode);

		Long l = findObjectByHqlNoTenantId(hql.toString(), param);
		
		if(l>0l){
			return find4CompCode(parentCode);
		}else{
			return compCode;
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.dao.IOrganizationDao#findCompByEmpId(java.lang.Long)
	 */
	@Override
	public BaseOrganization findCompByEmpId(String empId)throws Exception{
		BaseEmployee emp = findEntityById(BaseEmployee.class, empId);
		if(emp!=null){
			BaseOrganizationUnit orgUnit = emp.getOrganizationUnit();
			if(orgUnit!=null){
				BaseOrganization org = orgUnit.getOrganization();
				return org;
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.dao.IOrganizationDao#findCompByInnerCode(java.lang.String)
	 */
	@Override
	public BaseOrganization findCompByInnerCode(String innerCode)throws Exception{
		String ename = "comp";
		StringBuilder hql = new StringBuilder();
		Map<String,Object> param = new HashMap<String,Object>();
		
		hql.append("select ").append(ename).append(" from ").append(BaseOrganization.class.getName());
		hql.append(" ").append(ename).append(" where ");
		hql.append(ename).append(".companyInnerCode = :companyInnerCode ");
		param.put("companyInnerCode", innerCode.substring(0, 2));

		//HqlTenantIdUtil.handleParamMap4CompanyInnerCode(param);//不处理CompanyInnerCode;
		
		List<BaseOrganization> orgList = findAllByHql2(hql.toString(), param);
		if(orgList!=null && orgList.size()>0){
			return orgList.get(0);
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.dao.IOrganizationDao#findOrgByInnerCode(java.lang.String)
	 */
	@Override
	public OrgView findOrgByInnerCode(String innerCode)throws Exception{
		String ename = "comp";
		StringBuilder hql = new StringBuilder();
		Map<String,Object> param = new HashMap<String,Object>();
		
		hql.append("select ").append(ename).append(" from ").append(OrgView.class.getName());
		hql.append(" ").append(ename).append(" where comp.orgType= :compType and ");
		param.put("compType", "C");
		hql.append(ename).append(".companyInnerCode = :companyInnerCode ");
		param.put("companyInnerCode", innerCode);

		//HqlTenantIdUtil.handleParamMap4CompanyInnerCode(param);//不处理CompanyInnerCode;
		List<OrgView> orgList = findAllByHql2NoTenantId(hql.toString(), param);
		if(orgList!=null && orgList.size()>0){
			return orgList.get(0);
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.dao.IOrganizationDao#saveForRoleAuthorityByIndustry(java.lang.Long, java.lang.Long)
	
	public void saveForRoleAuthorityByIndustry(Long roleId,Long industryId)throws Exception{
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		params.put("industryId", industryId);
		
		executeSql("call pro_sys_authority_industrymgt(:roleId, :industryId);", params);
	}
	 */
	/* (non-Javadoc)
	 * @see com.vix.common.org.dao.IOrganizationDao#saveForRoleAuthorityByModuleCode(java.lang.Long, java.lang.String[])
	
	public void saveForRoleAuthorityByModuleCode(Long roleId,String[] moduleCodeArray)throws Exception{
		String moduleCheckCodesPc = moduleCodeArray[0];
		String moduleCheckCodesPad = moduleCodeArray[1];
		String moduleCheckCodesMobile = moduleCodeArray[2]; 
		
		// PC类型
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		params.put("moduleCheckCodes", moduleCheckCodesPc);
		params.put("delimit", "#");
		params.put("moduleType", BizConstant.COMMON_SECURITY_RESTYPE_P);
		
		executeSql("call pro_sys_company_authority(:roleId, :moduleCheckCodes,:delimit,:moduleType);", params);
		
		// MOBILE类型
		params.put("moduleCheckCodes", moduleCheckCodesMobile);
		params.put("moduleType", BizConstant.COMMON_SECURITY_RESTYPE_M);
		
		executeSql("call pro_sys_company_authority(:roleId, :moduleCheckCodes,:delimit,:moduleType);", params);
		
		
		// PAD类型
		params.put("moduleCheckCodes", moduleCheckCodesPad);
		params.put("moduleType", BizConstant.COMMON_SECURITY_RESTYPE_D);
		
		executeSql("call pro_sys_company_authority(:roleId, :moduleCheckCodes,:delimit,:moduleType);", params);
		
	}
	 */
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.dao.IOrganizationDao#saveForRoleAuthorityByImIds(java.lang.Long, java.lang.String)
	 */
	@Override
	public void saveForRoleAuthorityByImIds(String roleId,String tenantId,String industryModuleIds)throws Exception{
		if(roleId==null || !StringUtils.hasText(tenantId)){
			return;
		}
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		params.put("tenantId", tenantId);
		params.put("industryModuleIds", industryModuleIds);
		
		//executeSql("call pro_sys_company_im_authority(:roleId, :tenantId :industryModuleIds);", params);
		executeSql("call vix_pro_sys_comp_im_auth(:roleId, :tenantId, :industryModuleIds);", params);
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.dao.IOrganizationDao#findOrgByUserAccountId(java.lang.Long, java.lang.Long)
	 */
	@Override
	public String findOrgByUserAccountId(String empId,String userAccountId)throws Exception{
		String orgId = null;
		
		BaseEmployee emp =null;
		if(StrUtils.isNotEmpty(empId) && !empId.equals("0")){//if(empId!=null && empId>0L){
			emp = findEntityById(BaseEmployee.class, empId);
			BaseOrganizationUnit empOrgUnit = emp.getOrganizationUnit();
			if(empOrgUnit!=null){
				OrgView empOrgView = findOrgByInnerCode(empOrgUnit.getCompanyInnerCode());
				orgId = empOrgView.getRealId();
				//orgId = empOrgUnit.getTe
			}
		}else{
			UserAccount ua = findEntityById(UserAccount.class, userAccountId);
			String uaTenantId = ua.getTenantId();
			
			StringBuilder hql = new StringBuilder();
			Map<String,Object> param = new HashMap<String,Object>();
			
			hql.append("select org from ").append(BaseOrganization.class.getName());
			hql.append(" org where ");
			hql.append("org.tenantId = :tenantId ");
			param.put("tenantId", uaTenantId);

			hql.append(" and org.parentOrganization is null ");

			List<BaseOrganization> orgList = findAllByHql2NoTenantId(hql.toString(), param);
			if(orgList!=null && orgList.size()==1){
				orgId = orgList.get(0).getId();
			}else{
				throw new BizException("账户存在多集团公司信息！");
			}
		}
		String roleId = null;
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(orgId)){
			BaseOrganization org = findEntityById(BaseOrganization.class, orgId);//可能是所在公司    或者顶级公司
			UserAccount compSuperAdmin = org.getCompSuperAdmin();//找到公司管理员账号，再找到其角色
			Set<Role> userAccountRoles = compSuperAdmin.getRoles();
			//找出超级管理员
			for(Role role:userAccountRoles){
				if(role.getRoleCode().contains(BizConstant.ROLE_COMP_PRE_SUPERADMIN)){
					roleId=role.getId();
					break;
				}
			}
		}

		return roleId;
	}
	
}
