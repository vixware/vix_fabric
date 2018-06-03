package com.vix.common.org.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.vix.common.org.dao.IOrganizationDao;
import com.vix.common.org.entity.OrgView;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.orginialMeta.entity.OrginialBillsCategory;
import com.vix.common.orginialMeta.entity.OrginialBillsProperty;
import com.vix.common.orginialMeta.entity.OrginialBillsType;
import com.vix.common.orginialMeta.entity.OrginialCurrencyType;
import com.vix.common.orginialMeta.entity.OrginialMeasureUnit;
import com.vix.common.orginialMeta.entity.OrginialMeasureUnitGroup;
import com.vix.common.security.dao.IUserAccountDao;
import com.vix.common.security.entity.Role;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.common.share.entity.MeasureUnitGroup;
import com.vix.core.constant.BizConstant;
import com.vix.core.exception.BizException;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.core.utils.DateUtil;
import com.vix.core.utils.SpringUtil;
import com.vix.core.utils.StrUtils;
import com.vix.hr.organization.entity.Employee;
import com.vix.system.base.compOperation.vo.CompanyOperationVO;
import com.vix.system.billTypeManagement.entity.BillsCategory;
import com.vix.system.billTypeManagement.entity.BillsProperty;
import com.vix.system.billTypeManagement.entity.BillsType;

import jodd.util.RandomStringUtil;

@Repository("organizationDao")
public class OrganizationDaoImpl extends BaseHibernateDaoImpl implements IOrganizationDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 生成公司编码
	 * 
	 * @param parentCode
	 * @return
	 */
	private String makeCompanyCode(String parentCode) {
		String compCode = RandomStringUtil.randomNumeric(2);
		if (StrUtils.isNotEmpty(parentCode)) {
			compCode = parentCode.trim() + compCode;
		}
		return compCode;
	}

	@Override
	public String find4CompCode(String parentCode) throws Exception {
		String compCode = makeCompanyCode(parentCode);

		String ename = "comp";
		StringBuilder hql = new StringBuilder();
		Map<String, Object> param = new HashMap<String, Object>();

		hql.append("select count(").append(ename).append(".id) from ").append(Organization.class.getName());
		hql.append(" ").append(ename).append(" where ");
		hql.append(ename).append(".companyInnerCode = :companyInnerCode ");
		param.put("companyInnerCode", compCode);

		Long l = findObjectByHqlNoTenantId(hql.toString(), param);

		if (l > 0l) {
			return find4CompCode(parentCode);
		} else {
			return compCode;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vix.common.org.dao.IOrganizationDao#findCompByEmpId(java.lang.String)
	 */
	@Override
	public Organization findCompByEmpId(String empId) throws Exception {
		Employee emp = findEntityById(Employee.class, empId);
		if (emp != null) {
			OrganizationUnit orgUnit = emp.getOrganizationUnit();
			if (orgUnit != null) {
				Organization org = orgUnit.getOrganization();
				return org;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vix.common.org.dao.IOrganizationDao#findCompByInnerCode(java.lang.
	 * String)
	 */
	@Override
	public Organization findCompByInnerCode(String innerCode) throws Exception {
		String ename = "comp";
		StringBuilder hql = new StringBuilder();
		Map<String, Object> param = new HashMap<String, Object>();

		hql.append("select ").append(ename).append(" from ").append(Organization.class.getName());
		hql.append(" ").append(ename).append(" where ");
		hql.append(ename).append(".companyInnerCode = :companyInnerCode ");
		param.put("companyInnerCode", innerCode.substring(0, 2));

		// HqlTenantIdUtil.handleParamMap4CompanyInnerCode(param);//不处理CompanyInnerCode;

		List<Organization> orgList = findAllByHql2(hql.toString(), param);
		if (orgList != null && orgList.size() > 0) {
			return orgList.get(0);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vix.common.org.dao.IOrganizationDao#findOrgByInnerCode(java.lang.
	 * String)
	 */
	@Override
	public OrgView findOrgByInnerCode(String innerCode) throws Exception {
		String ename = "comp";
		StringBuilder hql = new StringBuilder();
		Map<String, Object> param = new HashMap<String, Object>();

		hql.append("select ").append(ename).append(" from ").append(OrgView.class.getName());
		hql.append(" ").append(ename).append(" where comp.orgType= :compType and ");
		param.put("compType", "C");
		hql.append(ename).append(".companyInnerCode = :companyInnerCode ");
		param.put("companyInnerCode", innerCode);

		// HqlTenantIdUtil.handleParamMap4CompanyInnerCode(param);//不处理CompanyInnerCode;
		List<OrgView> orgList = findAllByHql2NoTenantId(hql.toString(), param);
		if (orgList != null && orgList.size() > 0) {
			return orgList.get(0);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vix.common.org.dao.IOrganizationDao#saveForRoleAuthorityByImIds(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public void saveForRoleAuthorityByImIds(String roleId, String tenantId, String industryModuleIds) throws Exception {
		if (roleId == null || !StringUtils.hasText(tenantId) || StrUtils.isEmpty(industryModuleIds)) {
			return;
		}
		String industryModuleIdsParam = "'" + industryModuleIds.replaceAll(",", "','") + "'";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleId", roleId);
		params.put("tenantId", tenantId);
		params.put("industryModuleIds", industryModuleIdsParam);

		// executeSql("call pro_sys_company_im_authority(:roleId, :tenantId
		// :industryModuleIds);", params);
		executeSql("call vix_pro_sys_comp_im_auth(:roleId, :tenantId, :industryModuleIds);", params);
	}

	@Override
	public String findOrgByUserAccountId(String empId, String userAccountId) throws Exception {
		String orgId = null;

		Employee emp = null;
		if (org.apache.commons.lang3.StringUtils.isNotEmpty(empId) && !empId.equals("0")) {// if(empId!=null
																							// &&
																							// empId>0L){
			emp = findEntityById(Employee.class, empId);
			OrganizationUnit empOrgUnit = emp.getOrganizationUnit();
			if (empOrgUnit != null) {
				OrgView empOrgView = findOrgByInnerCode(empOrgUnit.getCompanyInnerCode());
				orgId = empOrgView.getRealId();
				// orgId = empOrgUnit.getTe
			}
		} else {
			UserAccount ua = findEntityById(UserAccount.class, userAccountId);
			String uaTenantId = ua.getTenantId();

			StringBuilder hql = new StringBuilder();
			Map<String, Object> param = new HashMap<String, Object>();

			hql.append("select org from ").append(Organization.class.getName());
			hql.append(" org where ");
			hql.append("org.tenantId = :tenantId ");
			param.put("tenantId", uaTenantId);

			hql.append(" and org.parentOrganization is null ");

			List<Organization> orgList = findAllByHql2NoTenantId(hql.toString(), param);
			if (orgList != null && orgList.size() == 1) {
				orgId = orgList.get(0).getId();
			} else {
				throw new BizException("账户存在多集团公司信息！");
			}
		}

		Organization org = findEntityById(Organization.class, orgId);// 可能是所在公司
																		// 或者顶级公司
		UserAccount compSuperAdmin = org.getCompSuperAdmin();// 找到公司管理员账号，再找到其角色
		Set<Role> userAccountRoles = compSuperAdmin.getRoles();
		// 找出超级管理员
		String roleId = null;
		for (Role role : userAccountRoles) {
			if (role.getRoleCode().contains(BizConstant.ROLE_COMP_PRE_SUPERADMIN)) {
				roleId = role.getId();
				break;
			}
		}

		return roleId;
	}

	@Override
	public void saveForAddHbAndDw(String compid) throws Exception {
		Organization org = findEntityById(Organization.class, compid);
		String tenantId = org.getTenantId();
		String companyInnerCode = org.getCompanyInnerCode();

		StringBuilder sql = new StringBuilder();
		Map<String, Object> sqlParams = new HashMap<String, Object>();

		StringBuilder hql = new StringBuilder();
		Map<String, Object> param = new HashMap<String, Object>();

		Date now = new Date();
		Date endDate = DateUtil.getEndOfWorld();

		// 1 货币
		hql.append("select count(currencyType.id) from ").append(CurrencyType.class.getName());
		hql.append(" currencyType where currencyType.tenantId = :tenantId ");
		param.put("tenantId", tenantId);
		hql.append(" and currencyType.companyInnerCode = :companyInnerCode ");
		param.put("companyInnerCode", companyInnerCode);

		Long l = findDataCountByHqlOrginial(hql, "currencyType", param);
		if (l == 0) {
			// 需要跑初始化
			List<OrginialCurrencyType> vixCurList = findAllByHqlOrginial("select oc from OrginialCurrencyType oc ", new HashMap<String, Object>());

			List<CurrencyType> tenantOcList = new ArrayList<CurrencyType>();
			for (OrginialCurrencyType oc : vixCurList) {
				CurrencyType tenantOc = new CurrencyType();
				tenantOc.setCode(oc.getCode());
				tenantOc.setName(oc.getName());
				tenantOc.setLanguage(oc.getLanguage());
				tenantOc.setCompanyCode(org.getCompanyCode());
				tenantOc.setTenantId(tenantId);
				tenantOc.setCompanyInnerCode(companyInnerCode);

				tenantOc.setStartTime(now);
				tenantOc.setEndTime(endDate);

				tenantOcList.add(tenantOc);
			}
			saveOrUpdateOriginalBatch(tenantOcList);

			flush();
		}
		hql.setLength(0);
		param.clear();

		// 2 计量单位
		hql.append("select count(mg.id) from ").append(MeasureUnitGroup.class.getName());
		hql.append(" mg where mg.tenantId = :tenantId ");
		param.put("tenantId", tenantId);
		hql.append(" and mg.companyInnerCode = :companyInnerCode ");
		param.put("companyInnerCode", companyInnerCode);

		l = findDataCountByHqlOrginial(hql, "mg", param);
		if (l == 0) {
			List<OrginialMeasureUnitGroup> mugList = findAllByHqlOrginial("select om from OrginialMeasureUnitGroup om ", new HashMap<String, Object>());

			// List<MeasureUnitGroup> tenantMgList = new
			// ArrayList<MeasureUnitGroup>();
			for (OrginialMeasureUnitGroup mug : mugList) {
				MeasureUnitGroup copyMug = new MeasureUnitGroup();
				copyMug.setCode(mug.getCode());
				copyMug.setName(mug.getName());
				copyMug.setTenantId(tenantId);
				copyMug.setCompanyInnerCode(companyInnerCode);
				copyMug.setStartTime(now);
				// 新加
				copyMug.setType(mug.getType());
				copyMug.setIsTemp("");
				copyMug.setEndTime(endDate);
				copyMug.setOrganization(org);
				// 新加
				saveOrUpdateOriginal(copyMug);

				Set<OrginialMeasureUnit> orginialMeasureUnits = mug.getOrginialMeasureUnits();
				if (orginialMeasureUnits != null && !orginialMeasureUnits.isEmpty()) {
					for (OrginialMeasureUnit mu : orginialMeasureUnits) {
						MeasureUnit subMu = new MeasureUnit();
						subMu.setIsMeasurementUnit(mu.getIsMeasurementUnit());
						subMu.setEnglishCode(mu.getEnglishCode());
						subMu.setRate(mu.getRate());
						subMu.setCode(mu.getCode());
						subMu.setName(mu.getName());
						subMu.setMemo(mu.getMemo());

						subMu.setTenantId(tenantId);
						subMu.setCompanyInnerCode(companyInnerCode);
						subMu.setStartTime(now);
						subMu.setEndTime(endDate);
						subMu.setMeasureUnitGroup(copyMug);

						saveOrUpdateOriginal(subMu);
					}
				}
			}

			flush();
		}
		hql.setLength(0);
		param.clear();

		// 3 com.vix.common.orginialMeta.entity.OrginialBillsCategory
		hql.append("select count(obc.id) from ").append(BillsCategory.class.getName());
		hql.append(" obc where obc.tenantId = :tenantId ");
		param.put("tenantId", tenantId);
		hql.append(" and obc.companyInnerCode = :companyInnerCode ");
		param.put("companyInnerCode", companyInnerCode);

		l = findDataCountByHqlOrginial(hql, "obc", param);
		if (l == 0) {
			List<OrginialBillsCategory> vixOmuList = findAllByHqlOrginial("select om from OrginialBillsCategory om ", new HashMap<String, Object>());

			for (OrginialBillsCategory om : vixOmuList) {
				BillsCategory tenantMu = new BillsCategory();
				tenantMu.setCategoryCode(om.getCategoryCode());
				tenantMu.setCategoryName(om.getCategoryName());
				tenantMu.setTenantId(tenantId);
				tenantMu.setCompanyInnerCode(companyInnerCode);
				tenantMu.setStartTime(now);
				tenantMu.setEndTime(endDate);
				tenantMu.setCompanyCode(org.getCompanyCode());
				saveOrUpdateOriginal(tenantMu);

				Set<OrginialBillsProperty> subPropertyList = om.getOrginialBillsPropertys();
				if (subPropertyList != null && !subPropertyList.isEmpty()) {
					for (OrginialBillsProperty sp : subPropertyList) {
						BillsProperty bp = new BillsProperty();
						bp.setPropertyCode(sp.getPropertyCode());
						bp.setPropertyName(sp.getPropertyName());
						bp.setTenantId(tenantId);
						bp.setCompanyInnerCode(companyInnerCode);
						bp.setStartTime(now);
						bp.setEndTime(endDate);
						bp.setCompanyCode(org.getCompanyCode());
						bp.setBillsCategory(tenantMu);
						saveOrUpdateOriginal(bp);

						Set<OrginialBillsType> orginialBillsTypeList = sp.getOrginialBillsType();
						if (orginialBillsTypeList != null && !orginialBillsTypeList.isEmpty()) {
							for (OrginialBillsType obt : orginialBillsTypeList) {
								BillsType bt = new BillsType();
								bt.setTypeCode(obt.getTypeCode());
								bt.setTypeName(obt.getTypeName());
								bt.setTenantId(tenantId);
								bt.setCompanyInnerCode(companyInnerCode);
								bt.setStartTime(now);
								bt.setEndTime(endDate);
								bt.setCompanyCode(org.getCompanyCode());
								bt.setBillsProperty(bp);

								saveOrUpdateOriginal(bt);
							}
						}
					}
				}
			}
			flush();
		}
		hql.setLength(0);
		param.clear();
		clear();

		// 4 系统变量
		sql.append("insert into SYS_VAR(ID,VARCODE,DEFAULTVALUE,DESCRIPTION,TENANTID,CreateTime)  ");
		sql.append(" select UUID(), ov.VARCODE, ov.DEFAULTVALUE, ov.DESCRIPTION, :tenantId, now() from CMN_MET_ORGINIAL_VAR ov ");
		sqlParams.put("tenantId", tenantId);
		sql.append(" LEFT JOIN SYS_VAR sv ON sv.VARCODE = ov.VARCODE and sv.tenantId = :tenantId ");
		sqlParams.put("tenantId", tenantId);
		sql.append("   WHERE sv.VARCODE IS NULL ");
		batchUpdateBySql(sql.toString(), sqlParams);
		sql.setLength(0);
		sqlParams.clear();

	}

	@Override
	public Organization saveCompanyOperation(CompanyOperationVO vo, Boolean isFromOc) throws Exception {
		Date now = new Date();
		String compName = vo.getOrgName();// 公司名称
		String compCode = vo.getCompanyCode();// 公司编码

		// 设置公司编码
		String parentIdTmp = vo.getParentId();
		String parentInnerCode = null;
		Organization parentOrganization = null;
		if (StrUtils.isNotEmpty(parentIdTmp)) {
			parentOrganization = findEntityById(Organization.class, parentIdTmp);

			parentInnerCode = parentOrganization.getCompanyInnerCode();
			if (StrUtils.isEmpty(parentInnerCode)) {
				throw new BizException("上级公司内部编码为空，请联系系统维护人员！");
			}
		}
		String compInnerCode = find4CompCode(parentInnerCode);
		if (StrUtils.isNotEmpty(vo.getCompanyInnerCode())) {
			compInnerCode = vo.getCompanyInnerCode();
		}

		Organization comp = null;
		if (vo != null && StrUtils.isNotEmpty(vo.getId())) {
			comp = findEntityById(Organization.class, vo.getId());
			compInnerCode = comp.getCompanyInnerCode();
		}

		// 设置tenantId
		String tenantId = vo.getTenantId();
		if (StrUtils.isEmpty(tenantId)) {
			if (parentOrganization != null) {
				tenantId = parentOrganization.getTenantId();
			} else {
				Assert.notNull(tenantId, "承租户标识不能为空！");
			}
		}

		// 1、先提取账号信息 和角色信息
		String userAccountId = null;

		String roleId = null;
		if (StrUtils.isNotEmpty(vo.getUserAccountId())) {
			userAccountId = vo.getUserAccountId();
			// 查询帐号的角色
			UserAccount userAccountTmp = findEntityById(UserAccount.class, userAccountId);
			Set<Role> userAccountRoles = userAccountTmp.getRoles();
			// roleId = StringUtils.join(userAccountRoles, ",");
			// 找出超级管理员
			for (Role role : userAccountRoles) {
				if (role.getRoleCode().contains(BizConstant.ROLE_COMP_PRE_SUPERADMIN)) {
					roleId = String.valueOf(role.getId());
					break;
				}
			}

		} else {
			// 生成公司超级管理员角色
			Role superCompRole = new Role();
			superCompRole.setName(compName + "_超级管理员");
			superCompRole.setRoleCode(BizConstant.ROLE_COMP_PRE_SUPERADMIN + compCode + "_" + RandomStringUtil.randomAlpha(12));
			superCompRole.setMemo(compName + "_超级管理员");
			superCompRole.setCompanyInnerCode(compInnerCode);
			// userAccountDao.save(superCompRole);

			superCompRole.setTenantId(tenantId);

			saveOrUpdateOriginal(superCompRole);

			roleId = String.valueOf(superCompRole.getId());
		}

		// 2、角色授权

		String industryManagementModuleIds = vo.getIndustryManagementModuleIds();// 行业模块id
		String industryManagementModuleNames = vo.getIndustryManagementModuleNames();// 行业模块名称
		IUserAccountDao userAccountDao = SpringUtil.getBean("userAccountDao");

		UserAccount userAccount = userAccountDao.saveOrUpdate(tenantId, compCode, compInnerCode, roleId, null, userAccountId, BizConstant.COMMON_SECURITY_ACCOUNT_BizType_Sys, vo.getAccount(), vo.getPassword(), null, "1");

		// 初始化权限
		if (org.apache.commons.lang3.StringUtils.isNotEmpty(vo.getIsResetAuth()) && vo.getIsResetAuth().equals("Y")) {
			logger.debug("初始化权限! tenantId:{},roleId:{}, industryManagementModuleIds:{}.", new Object[] { tenantId, roleId, industryManagementModuleIds });
			userAccountDao.flush();
			saveForRoleAuthorityByImIds(roleId, tenantId, industryManagementModuleIds);
		}
		// 刷新权限信息
		// ApplicationSecurityCode.refreshObject();
		userAccountDao.flush();
		userAccountDao.clear();

		// 3、保存公司信息
		// COMMON_SECURITY_ACCOUNT_BizType_Sys

		if (comp == null) {
			comp = new Organization();

			comp.setCompanyInnerCode(compInnerCode);

		}

		comp.setUuid(vo.getUuid());// 更新时无效，仅用于被 业务中心服务器调用使用
		comp.setCompanyCreateDate(vo.getCompanyCreateDate());
		comp.setOrgName(vo.getOrgName());
		comp.setOrgType(vo.getOrgType());

		String parentId = vo.getParentId();
		Boolean isTop = false;

		if (org.apache.commons.lang3.StringUtils.isNotEmpty(parentId)) {
			// if(parentId!=null){
			comp.setParentOrganization(parentOrganization);
		} else {
			isTop = true;

			comp.setParentOrganization(null);

			if (org.apache.commons.lang3.StringUtils.isNotEmpty(vo.getConfigUrlCode())) {
				comp.setConfigUrlCode(vo.getConfigUrlCode());
			} else {
				comp.setConfigUrlCode(null);
			}

		}
		comp.setBriefName(vo.getBriefName());
		comp.setShortName(vo.getShortName());
		comp.setEnName(vo.getEnName());
		comp.setAddress(vo.getAddress());
		comp.setCountry(vo.getCountry());
		comp.setArea(vo.getArea());
		comp.setAppId(vo.getAppId());
		comp.setCountryLanguage(vo.getCountryLanguage());
		comp.setCompanyCode(compCode);
		comp.setOrgDataFilterType(vo.getOrgDataFilterType());
		Date startDate = vo.getStartTime();
		Date endDate = vo.getEndTime();
		if (startDate == null) {
			startDate = now;
		}
		comp.setStartTime(startDate);
		comp.setEndTime(endDate);

		comp.setIndustryManagementModuleIds(industryManagementModuleIds);
		comp.setIndustryManagementModuleNames(industryManagementModuleNames);

		if (isEntityExist(Organization.class, comp.getId(), "companyCode", comp.getCompanyCode())) {
			throw new BizException("公司编码已存在！");
		}

		comp.setTenantId(tenantId);
		comp.setCompSuperAdmin(userAccountDao.findEntityById(UserAccount.class, userAccount.getId()));

		comp.setMatProp(vo.getMatProp());

		Integer bizDataAccountAmount = vo.getBizDataAccountAmount();
		Integer bizDataOrderAmount = vo.getBizDataOrderAmount();
		String companyOrgRegCode = vo.getCompanyOrgRegCode();
		String companyOrgContact = vo.getCompanyOrgContact();
		String email = vo.getEmail();
		String mobile = vo.getMobile();

		if (isFromOc) {
			// OC接口调用服务
			comp.setBizDataAccountAmount(bizDataAccountAmount);
			comp.setBizDataOrderAmount(bizDataOrderAmount);
			comp.setCompanyOrgRegCode(companyOrgRegCode);
			comp.setCompanyOrgContact(companyOrgContact);
			comp.setEmail(email);
			comp.setMobile(mobile);
		}
		if (StrUtils.isNotEmpty(vo.getIsOpenWxQy()) && vo.getIsOpenWxQy().equals(BizConstant.COMMON_BOOLEAN_YES)) {
			vo.setIsOpenWxQy(BizConstant.COMMON_BOOLEAN_YES);
		} else {
			vo.setIsOpenWxQy(BizConstant.COMMON_BOOLEAN_NO);
		}

		saveOrUpdateOriginal(comp);
		flush();
		// 初始化货币 单位
		if (isTop) {
			saveForAddHbAndDw(comp.getId());
		}
		flush();
		clear();
		return comp;
	}

	@Override
	public List<Organization> findAllOrg() throws Exception {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();

		sql.append("select distinct mo.* from MDM_ORG_ORGANIZATION mo where mo.TENANTID is not null ");

		List<Organization> allOrg = queryObjectListBySql(sql.toString(), Organization.class, params.toArray());
		return allOrg;
	}

	@Override
	public Organization findOrganizationByTenantId(String tenantId) throws Exception {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> param = new HashMap<String, Object>();

		hql.append("select comp from ").append(Organization.class.getName());
		hql.append(" comp where ");
		hql.append("comp.tenantId = :tenantId and comp.parentOrganization is null ");
		param.put("tenantId", tenantId);

		// HqlTenantIdUtil.handleParamMap4CompanyInnerCode(param);//不处理CompanyInnerCode;
		List<Organization> orgList = findAllByHql2NoTenantId(hql.toString(), "comp", param);
		if (orgList != null && orgList.size() > 0) {
			return orgList.get(0);
		}

		return null;
	}
}
