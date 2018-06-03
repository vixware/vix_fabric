package com.vix.common.org.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.vix.common.org.dao.IBusinessOrgDao;
import com.vix.common.org.dao.IBusinessViewDao;
import com.vix.common.org.entity.BusinessOrg;
import com.vix.common.org.entity.BusinessOrgDetail;
import com.vix.common.org.entity.BusinessView;
import com.vix.core.constant.BizConstant;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.core.utils.StrUtils;

@Repository("businessOrgDao")
public class BusinessOrgDaoImpl extends BaseHibernateDaoImpl implements IBusinessOrgDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "businessViewDao")
	private IBusinessViewDao businessViewDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vix.common.org.dao.IBusinessOrgDao#findBusinessOrgByOrgUnitId(java.
	 * lang.String)
	 * 
	 * public Set<BusinessOrg> findBusinessOrgByOrgUnitId(String
	 * orgUnitId)throws Exception{ StringBuilder hql = new StringBuilder();
	 * Map<String,Object> params = new HashMap<String, Object>();
	 * 
	 * hql.append(
	 * "select bo from BusinessOrg bo inner join bo.organizationUnit orgUnit where orgUnit.id = :orgUnitId"
	 * ); params.put("orgUnitId", orgUnitId);
	 * 
	 * List<BusinessOrg> resList = findAllByHql2(hql.toString(), params);
	 * Set<BusinessOrg> resSet = new HashSet<BusinessOrg>(resList); return
	 * resSet; }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vix.common.org.dao.IBusinessOrgDao#findBusinessOrgTreeList(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public List<BusinessOrg> findBusinessOrgTreeList(String pmCode, String nodeId) throws Exception {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();

		String ename = "businessOrg";
		hql.append("select ").append(ename);
		hql.append(" from ").append(BusinessOrg.class.getName()).append(" ").append(ename);
		hql.append(" left join fetch ").append(ename).append(".subBusinessOrgs ");
		// where emps.id=:empId
		hql.append(" where 1=1 ");

		// 查询根节点
		hql.append(" and ").append(ename).append(".businessView.pmCode = :pmCode ");
		params.put("pmCode", pmCode);

		if (nodeId == null) {
			hql.append(" and ").append(ename).append(".parentBusinessOrg is null ");
			;
		} else if (!nodeId.equals("-1")) {// }else if(nodeId!=-1L){
			hql.append(" and ").append(ename).append(".parentBusinessOrg.id = :nodeId ");
			params.put("nodeId", nodeId);
		}

		hql.append(" order by businessOrg.id ");

		List<BusinessOrg> boList = findAllByHql2(hql.toString(), params);
		return boList;
	}

	// 未进行验证
	/*
	 * public List<BusinessOrgDetail> findBusinessOrgDetailByIdType(String
	 * businessOrgDetailBoId,String businessOrgDetailBoType, String
	 * businessViewId) throws Exception{ StringBuilder hql = new
	 * StringBuilder(); Map<String,Object> params = new HashMap<String,
	 * Object>();
	 * 
	 * String ename = "businessOrgDetail"; hql.append("select ").append(ename);
	 * hql.append(" from ").append(BusinessOrgDetail.class.getName()).append(" "
	 * ).append(ename); hql.append(" where 1=1 ");
	 * 
	 * hql.append(" and ").append(ename).append(
	 * ".businessView.id = :businessViewId "); params.put("businessViewId",
	 * businessViewId);
	 * 
	 * switch (businessOrgDetailBoType) { case BizConstant.COMMON_ORG_C:
	 * //hql.append(" and ").append(ename).append(
	 * ".businessView.pmCode = :pmCode ");  break; case
	 * BizConstant.COMMON_ORG_O: hql.append(" and ").append(ename).append(
	 * ".organizationUnit.id = :businessOrgDetailBoId ");
	 * params.put("businessOrgDetailBoId", businessOrgDetailBoId); break; case
	 * BizConstant.COMMON_ORG_E: hql.append(" and ").append(ename).append(
	 * ".employee.id = :businessOrgDetailBoId ");
	 * params.put("businessOrgDetailBoId", businessOrgDetailBoId); break; case
	 * BizConstant.COMMON_ORG_R: hql.append(" and ").append(ename).append(
	 * ".role.id = :businessOrgDetailBoId ");
	 * params.put("businessOrgDetailBoId", businessOrgDetailBoId); break;
	 * default:  break; }
	 * 
	 * List<BusinessOrgDetail> boList = findAllByHql2(hql.toString(), params);
	 * return boList; }
	 */

	@Override
	public List<BusinessOrg> findBusinessOrgByDetailBoIdType(String businessOrgDetailBoId, String businessOrgDetailBoType, String businessViewId) throws Exception {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();

		String ename = "businessOrg";
		hql.append("select ").append(ename);
		hql.append(" from ").append(BusinessOrg.class.getName()).append(" ").append(ename);
		hql.append(" join ").append(ename).append(".businessOrgDetails boDetail ");

		hql.append(" where 1=1 ");

		hql.append(" and ").append(ename).append(".businessView.id = :businessViewId ");
		params.put("businessViewId", businessViewId);

		switch (businessOrgDetailBoType) {
		case BizConstant.COMMON_ORG_C:
			// hql.append(" and ").append(ename).append(".businessView.pmCode =
			// :pmCode ");
			break;
		case BizConstant.COMMON_ORG_O:
			hql.append(" and boDetail.organizationUnit.id = :businessOrgDetailBoId ");
			params.put("businessOrgDetailBoId", businessOrgDetailBoId);
			break;
		case BizConstant.COMMON_ORG_E:
			hql.append(" and boDetail.employee.id = :businessOrgDetailBoId ");
			params.put("businessOrgDetailBoId", businessOrgDetailBoId);
			break;
		case BizConstant.COMMON_ORG_R:
			hql.append(" and boDetail.role.id = :businessOrgDetailBoId ");
			params.put("businessOrgDetailBoId", businessOrgDetailBoId);
			break;
		default:
			break;
		}

		List<BusinessOrg> boList = findAllByHql2(hql.toString(), params);
		return boList;
	}

	@Override
	public List<BusinessOrg> findSubBusinessOrgByCode(String businessOrgCode, String businessViewId, Boolean isQueryAllSub) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append(" SELECT mb.*  from MDM_ORG_BUSINESSORG mb ");
		sql.append(" WHERE mb.BusinessView_ID = ? ");
		params.add(businessViewId);

		sql.append(" and mb.BusinessOrgCode like ? ");
		params.add(businessOrgCode + "_%");
		if (!isQueryAllSub) {
			int count = StringUtils.countMatches(businessOrgCode, "_") + 2;
			sql.append(" and func_strLength(mb.BusinessOrgCode,'_') = ? ");
			params.add(count);
		}
		List<BusinessOrg> boList = queryObjectListBySql(sql.toString(), BusinessOrg.class, params.toArray());
		return boList;
	}

	@Override
	public List<BusinessOrg> findBusinessOrg4ReportLine(String businessViewId, String tenantId, Boolean isAboveLevel, Boolean isQueryAllLevel, String queryBusinessOrgDetailBoId, String queryBusinessOrgDetailBoIdType
	// String resultBusinessOrgDetailType
	) throws Exception {
		Assert.isTrue(StrUtils.isNotEmpty(tenantId), "承租户标识不能为空!");
		Assert.isTrue(StrUtils.isNotEmpty(queryBusinessOrgDetailBoId), "查询业务对象参数标识不能为空!");
		Assert.isTrue(StrUtils.isNotEmpty(queryBusinessOrgDetailBoIdType), "查询业务对象类型不能为空!");
		// Assert.isTrue(StrUtils.isNotEmpty(resultBusinessOrgDetailType),
		// "返回业务对象类型不能为空!");
		Assert.isTrue(StrUtils.isNotEmpty(businessViewId), "业务视图标识不能为空!");

		// Assert.isTrue(StrUtils.isNotEmpty(resultBusinessOrgDetailType),
		// "返回结果业务对象类型不能为空!");

		// "O\":\"部门\",\"R\":\"角色\",\"E\":\"人员
		// List<Employee> empList = new LinkedList<Employee>();
		List<BusinessOrg> resBoList = new LinkedList<BusinessOrg>();

		/*
		 * BusinessView bv = null; if(StrUtils.isNotEmpty(businessViewCode)){ bv
		 * = businessViewDao.findBizOrgViewByCode(businessViewCode); }else{ bv =
		 * businessViewDao.findBizOrgViewByCode(BizConstant.COMMON_DEFAULT_FLAG+
		 * tenantId); } Assert.notNull(bv, "业务视图不存在！");
		 */
		// 1 查询当前明细所在的业务组织数据
		List<BusinessOrg> boList = findBusinessOrgByDetailBoIdType(queryBusinessOrgDetailBoId, queryBusinessOrgDetailBoIdType, businessViewId);

		if (boList != null && !boList.isEmpty()) {
			Set<String> boCodeSet = new HashSet<String>();
			boList.stream().parallel().forEach((BusinessOrg bo) -> {
				boCodeSet.add(bo.getBusinessOrgCode());
			});
			// boCode current businessOrgCode
			List<BusinessOrg> curBoList = new ArrayList<BusinessOrg>();

			// iterator every businessorg code
			for (String boCode : boCodeSet) {

				if (isAboveLevel) {
					// businessOrgCode List
					List<String> paramStrList = new ArrayList<String>();

					// query above level
					String[] boCodeArray = StringUtils.split(boCode, "\\_");
					if (isQueryAllLevel) {
						// query All level data?
						// 1.1
						// one businessorg above level
						if (boCodeArray.length > 1) {
							String codeTmp = boCodeArray[0];
							for (int i = 0; i < boCodeArray.length - 1; i++) {
								if (i > 0) {
									codeTmp += "_" + boCodeArray[i];
								}
								paramStrList.add(codeTmp);
							}
						}

					} else {
						// 1.2 direct above level
						if (boCodeArray.length > 1) {
							int lastSepPos = StringUtils.lastIndexOf(boCode, "_");
							paramStrList.add(StringUtils.left(boCode, lastSepPos));
						}

					}

					if (!paramStrList.isEmpty()) {
						curBoList.addAll(findBusinessOrgByCodeList(businessViewId, paramStrList));
					}

				} else {
					// query below level
					curBoList.addAll(findSubBusinessOrgByCode(boCode, businessViewId, isQueryAllLevel));
				}

			}
			/*
			 * for(BusinessOrg bo : curBoList){
			 * System.out.println(bo.getBusinessOrgName()); }
			 */
			resBoList.addAll(curBoList);
		}
		return resBoList;
	}

	private List<BusinessOrg> findBusinessOrgByCodeList(String businessViewId, List<String> businessOrgCodeList) throws Exception {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();

		String ename = "businessOrg";
		hql.append("select ").append(ename);
		hql.append(" from ").append(BusinessOrg.class.getName()).append(" ").append(ename);
		hql.append(" where 1=1 ");
		hql.append(" and ").append(ename).append(".businessView.id = :businessViewId ");
		params.put("businessViewId", businessViewId);

		hql.append(" and ").append(ename).append(".businessOrgCode in (:businessOrgCodeList) ");
		params.put("businessOrgCodeList", businessOrgCodeList);

		// HqlTenantIdUtil.handleParamMap4CompanyInnerCode(paramsTmp);//不处理CompanyInnerCode;
		List<BusinessOrg> boList = findAllByHql2(hql.toString(), params);
		return boList;
	}

	private List<BusinessOrgDetail> findBusinessOrgDetailByBoIdList(String businessViewId, List<String> businessOrgIdList, String resultBusinessOrgDetailType) throws Exception {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();

		String ename = "businessOrgDetail";
		hql.append("select ").append(ename);
		hql.append(" from ").append(BusinessOrgDetail.class.getName()).append(" ").append(ename);
		hql.append(" where 1=1 ");
		hql.append(" and ").append(ename).append(".businessView.id = :businessViewId ");
		params.put("businessViewId", businessViewId);

		hql.append(" and ").append(ename).append(".bizOrgType = :bizOrgType ");
		params.put("bizOrgType", resultBusinessOrgDetailType);

		hql.append(" and ").append(ename).append(".businessOrg.id in (:businessOrgIdList) ");
		params.put("businessOrgIdList", businessOrgIdList);

		List<BusinessOrgDetail> boDetailList = findAllByHql2(hql.toString(), params);
		return boDetailList;
	}

	@Override
	public List<BusinessOrgDetail> findBusinessOrgDetail4ReportLine(String businessViewId, String tenantId, Boolean isAboveLevel, Boolean isQueryAllLevel, String businessOrgDetailBoId, String resultBusinessOrgDetailType) throws Exception {
		List<BusinessOrg> boList = findBusinessOrg4ReportLine(businessViewId, tenantId, isAboveLevel, isQueryAllLevel, businessOrgDetailBoId, resultBusinessOrgDetailType);

		List<BusinessOrgDetail> detailList = null;
		if (boList != null && !boList.isEmpty()) {
			final List<String> boIdList = new LinkedList<String>();
			boList.stream().forEach((BusinessOrg bo) -> {
				boIdList.add(bo.getId());
			});

			if (!boIdList.isEmpty()) {
				detailList = findBusinessOrgDetailByBoIdList(businessViewId, boIdList, resultBusinessOrgDetailType);
			}
		}
		return detailList;
	}

	@Override
	public <T> List<T> findBusinessOrgDetailBo4ReportLine(String businessViewCode, String tenantId, Boolean isAboveLevel, Boolean isQueryAllLevel, String queryBusinessOrgDetailBoId, String queryBusinessOrgDetailBoIdType, String resultBusinessOrgDetailType) throws Exception {

		BusinessView bv = null;
		if (StrUtils.isNotEmpty(businessViewCode)) {
			bv = businessViewDao.findBizOrgViewByCode(businessViewCode);
		} else {
			bv = businessViewDao.findBizOrgViewByCode(BizConstant.COMMON_DEFAULT_FLAG + tenantId);
		}
		Assert.notNull(bv, "业务视图不存在！");

		String businessViewId = bv.getId();
		List<BusinessOrg> boList = findBusinessOrg4ReportLine(businessViewId, tenantId, isAboveLevel, isQueryAllLevel, queryBusinessOrgDetailBoId, queryBusinessOrgDetailBoIdType);

		List<T> detailBoList = null;
		if (boList != null && !boList.isEmpty()) {
			final List<String> boIdList = new LinkedList<String>();
			boList.stream().forEach((BusinessOrg bo) -> {
				boIdList.add(bo.getId());
			});

			if (!boIdList.isEmpty()) {
				StringBuilder hql = new StringBuilder();
				Map<String, Object> params = new HashMap<String, Object>();

				switch (resultBusinessOrgDetailType) {
				case BizConstant.COMMON_ORG_C:
					// TODO
					break;
				case BizConstant.COMMON_ORG_O:
					hql.append("select distinct boUnit from ").append(BusinessOrgDetail.class.getName()).append(" bo ").append(" inner join bo.organizationUnit boUnit ");
					hql.append(" where  bo.businessView.id = :businessViewId and bo.bizOrgType = :resultBusinessOrgDetailType ");
					params.put("businessViewId", businessViewId);
					params.put("resultBusinessOrgDetailType", resultBusinessOrgDetailType);

					hql.append(" and bo.businessOrg.id in ( :boIdList ) ");
					params.put("boIdList", boIdList);

					detailBoList = findAllByHqlOrginial(hql.toString(), params);
					break;
				case BizConstant.COMMON_ORG_E:
					hql.append("select distinct emp from ").append(BusinessOrgDetail.class.getName()).append(" bo ").append(" inner join bo.employee emp ");
					hql.append(" where  bo.businessView.id = :businessViewId and bo.bizOrgType = :resultBusinessOrgDetailType ");
					params.put("businessViewId", businessViewId);
					params.put("resultBusinessOrgDetailType", resultBusinessOrgDetailType);

					hql.append(" and bo.businessOrg.id in ( :boIdList ) ");
					params.put("boIdList", boIdList);

					detailBoList = findAllByHqlOrginial(hql.toString(), params);
					break;
				case BizConstant.COMMON_ORG_R:
					hql.append("select distinct role from ").append(BusinessOrgDetail.class.getName()).append(" bo ").append(" inner join bo.role role ");
					hql.append(" where bo.businessView.id = :businessViewId and bo.bizOrgType = :resultBusinessOrgDetailType ");
					params.put("businessViewId", businessViewId);
					params.put("resultBusinessOrgDetailType", resultBusinessOrgDetailType);

					hql.append(" and bo.businessOrg.id = ( :boIdList ) ");
					params.put("boIdList", boIdList);

					detailBoList = findAllByHqlOrginial(hql.toString(), params);
					break;
				}

			}
		}
		return detailBoList;
	}
}
