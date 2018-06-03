package com.vix.core.persistence.hibernate.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Filter;
import org.hibernate.JDBCException;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.hibernate.transform.Transformers;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.vix.common.security.entity.DataResRowPolicyObj;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.securityDra.util.HandleVixSecurityContext;
import com.vix.common.securityDra.vo.row.DataResRowBizProperty;
import com.vix.core.constant.BizConstant;
import com.vix.core.constant.DataRowConstant;
import com.vix.core.constant.SearchCondition;
import com.vix.core.constant.SecurityScope;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.persistence.hibernate.util.HqlUtil;
import com.vix.core.utils.ContextUtil;
import com.vix.core.utils.JSonUtils;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

import jodd.bean.BeanUtil;

@SuppressWarnings({"unchecked", "deprecation"})
@Repository("baseHibernateDao")
public class BaseHibernateDaoImpl implements IBaseHibernateDao, Serializable {

	private static final long serialVersionUID = 1L;

	protected static Logger logger = LoggerFactory.getLogger(BaseHibernateDaoImpl.class);

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	protected JdbcTemplate jdbcTemplate;

	@Resource
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// 新增，用sql查询数据，可翻页，返回List<Map>
	@Override
	public Pager findPagerBySqlFull(Pager pager, String fullSql, Map<String, Object> params) throws Exception {
		fullSql = fullSql.toLowerCase();
		int idx = fullSql.indexOf(" from ");

		if (idx != -1) {
			String countSql = "select count(1) from (select 1" + fullSql.substring(idx) + ") temp";
			Query countQuery = getSession().createSQLQuery(countSql);

			if (null != params && params.keySet().size() > 0) {
				countQuery.setProperties(params);
			}

			int totalCount = 0;
			Object uniqueR = countQuery.uniqueResult();
			if (null != uniqueR && !"".equals(uniqueR)) {
				totalCount = Integer.parseInt(uniqueR.toString());
			}
			pager.setTotalCount(totalCount);

			Query resultQuery = getSession().createSQLQuery(fullSql);
			if (null != params && params.keySet().size() > 0) {
				resultQuery.setProperties(params);
			}
			resultQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			resultQuery.setFirstResult((pager.getPageNo() - 1) * pager.getPageSize());
			resultQuery.setMaxResults(pager.getPageSize());
			pager.setResultList(resultQuery.list());
		} else {
			Query resultQuery = getSession().createSQLQuery(fullSql);
			if (null != params && params.keySet().size() > 0) {
				resultQuery.setProperties(params);
			}
			resultQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			pager.setResultList(resultQuery.list());
			if (pager.getResultList() != null)
				pager.setTotalCount(pager.getResultList().size());
		}

		return pager;
	}

	/**
	 * 判断参数中是否有 tenantId
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("unused")
	private Boolean checkIsAddTenantId(Map<String, Object> paramMap) {
		if (SecurityUtil.getCurrentUserName().equals(BizConstant.USERACCOUNT_SUPERADMIN)) {
			if (paramMap != null)
				paramMap.remove(BizConstant.COMMON_GLOBAL_FLAG_TENANTID_KEY);
			return false;
		}

		if (paramMap != null && paramMap.containsKey(BizConstant.COMMON_GLOBAL_FLAG_TENANTID_KEY)) {
			Object tenantIdVal = paramMap.get(BizConstant.COMMON_GLOBAL_FLAG_TENANTID_KEY);
			if (tenantIdVal.toString().equals(BizConstant.COMMON_GLOBAL_FLAG_TENANTID_KEY_NO)) {
				paramMap.remove(BizConstant.COMMON_GLOBAL_FLAG_TENANTID_KEY);
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取tenantId
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getTenantId(Map<String, Object> paramMap) {
		Boolean isAddTenantId = checkIsAddTenantId(paramMap);
		if (isAddTenantId) {
			return getTenantId();
		}
		return null;
	}

	/**
	 * 获取tenantId
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getTenantId() {
		return SecurityUtil.getCurrentUserTenantId();
	}

	/**
	 * 判断(paramMap是否包含tenantId 且为-1时 不添加tenantId )是否需要添加 tenantId,得到 .tenantId =
	 * :tenantId
	 * 
	 * @param paramMap
	 * @return
	 */
	@SuppressWarnings("unused")
	private String getTenantIdCondition4ParamHql(String entityAlilasName, Map<String, Object> paramMap) {

		// 如果paramMap为null，下面的赋值无法传递会父级方法（仍然为null）
		Assert.isTrue(paramMap != null, "getTenantIdCondition4Hql传入参数paramMap不能为null！");

		StringBuilder sbTmp = new StringBuilder();

		Boolean isAddStr = false;// 标识是否进入 添加tenantId

		String tenantId = getTenantId(paramMap);
		if (StringUtils.isNotEmpty(tenantId)) {
			isAddStr = true;
			paramMap.put(BizConstant.COMMON_GLOBAL_FLAG_TENANTID_KEY, tenantId);

			// .tenantId = :tenantId_global
			if (StringUtils.isNotEmpty(entityAlilasName)) {
				sbTmp.append(" ").append(entityAlilasName).append(".").append(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID);
			} else {
				sbTmp.append(" ").append(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID);
			}
			// sbTmp.append("
			// ").append(entityAlilasName).append(".").append(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID);
			sbTmp.append(" = :").append(BizConstant.COMMON_GLOBAL_FLAG_TENANTID_KEY).append(" ");
			// return "
			// "+entityAlilasName+"."+BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID
			// +
			// " = :" +
			// BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID_BINDPARAM +
			// "
			// ";

		}

		String companyInnerCode = getCompanyInnerCode(paramMap);
		if (StringUtils.isNotEmpty(companyInnerCode)) {

			Boolean isRootEmp = SecurityUtil.getCurrentEmpIsRoot();// 是否是集团公司人员
			String orgDataFilterType = SecurityUtil.getCurrentEmpOrgDataFilterType();// 数据加载策略

			if (StrUtils.isNotEmpty(orgDataFilterType) && !orgDataFilterType.equals(SecurityScope.USER_ORG_DATAFILTERTYPE_N)) {
				if (isAddStr) {
					sbTmp.append(" and ");
				}

				if (StringUtils.isNotEmpty(entityAlilasName)) {
					sbTmp.append(" ").append(entityAlilasName).append(".").append(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_COMPANYINNERCODE);
				} else {
					sbTmp.append(" ").append(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_COMPANYINNERCODE);
				}

				if (isRootEmp) {
					if (orgDataFilterType.equals(SecurityScope.USER_ORG_DATAFILTERTYPE_P)) {// 只能查看自己
						sbTmp.append(" = :").append(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY).append(" ");
						paramMap.put(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY, companyInnerCode);
					} else if (orgDataFilterType.equals(SecurityScope.USER_ORG_DATAFILTERTYPE_A)) {// 总公司人员查看自己和所有子公司数据
						sbTmp.append(" like :").append(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY).append(" ");
						paramMap.put(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY, companyInnerCode + "%");
					}
				} else {
					// 子公司的只能查看自己的
					sbTmp.append(" = :").append(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY).append(" ");
					paramMap.put(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY, companyInnerCode);
				}
			}

		}
		// 不使用分管
		// handlerForAddProxyHql(sbTmp, entityAlilasName, paramMap);
		if (sbTmp.length() == 0) {
			return " 1=1 ";
		}
		return sbTmp.toString();

	}

	// 分管 hql中增加代理的条件
	private void handlerForAddProxyHql(StringBuilder sbTmp, String entityAlilasName, Map<String, Object> paramMap) {
		Boolean isAddProxyData = checkIsAddProxyCode(paramMap);
		Boolean hasProxyData = SecurityUtil.hasProxyData();
		/**/
		if (isAddProxyData && hasProxyData) {
			sbTmp.append(" and ( 1=1 ");

			String proxyOrgIds = SecurityUtil.getProxyAllOrgIds();// 代理公司
			if (StringUtils.isNotEmpty(proxyOrgIds)) {
				sbTmp.append(" or ");
				if (StringUtils.isNotEmpty(entityAlilasName)) {
					sbTmp.append(" ").append(entityAlilasName).append(".").append(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_COMPANYINNERCODE);
				} else {
					sbTmp.append(" ").append(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_COMPANYINNERCODE);
				}
				sbTmp.append(" in (").append(proxyOrgIds).append(") ");
			}

			String proxyOrgUnitIds = SecurityUtil.getProxyAllOrgUnitIds();// 代理部门
			if (StringUtils.isNotEmpty(proxyOrgUnitIds)) {
				if (StringUtils.isNotEmpty(proxyOrgIds)) {// 如果存在代理公司 则添加or
					sbTmp.append(" or ");
				}

				if (StringUtils.isNotEmpty(entityAlilasName)) {
					sbTmp.append(" ").append(entityAlilasName).append(".").append(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_DEPARTMENTCODE);
				} else {
					sbTmp.append(" ").append(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_DEPARTMENTCODE);
				}
				sbTmp.append(" in (").append(proxyOrgUnitIds).append(") ");
			}

			sbTmp.append(") ");
		}

	}

	/**
	 * 不再判断条件，直接得到 .tenantId = :tenantId
	 * 
	 * @param entityAlilasName
	 * @param paramMap
	 *            参数map
	 * @return
	 */
	private String getTenantIdCondition4ParamHql2(String entityAlilasName, Map<String, Object> paramMap) {

		// 如果paramMap为null，下面的赋值无法传递会父级方法（仍然为null）
		Assert.isTrue(paramMap != null, "getTenantIdCondition4Hql传入参数paramMap不能为null！");

		Boolean isAddStr = false;// 标识是否进入 添加tenantId

		StringBuilder sbTmp = new StringBuilder();
		String tenantId = getTenantId();
		if (StringUtils.isNotEmpty(tenantId)) {
			isAddStr = true;
			// .tenantId = :tenantId_global
			paramMap.put(BizConstant.COMMON_GLOBAL_FLAG_TENANTID_KEY, tenantId);

			if (StringUtils.isNotEmpty(entityAlilasName)) {
				sbTmp.append(" ").append(entityAlilasName).append(".").append(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID);
			} else {
				sbTmp.append(" ").append(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID);
			}

			sbTmp.append(" = :").append(BizConstant.COMMON_GLOBAL_FLAG_TENANTID_KEY).append(" ");
			// return "
			// "+entityAlilasName+"."+BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID
			// +
			// " = :" +
			// BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID_BINDPARAM +
			// "
			// ";
			// return sbTmp.toString();
		}

		String companyInnerCode = getCompanyInnerCode(paramMap);
		if (StringUtils.isNotEmpty(companyInnerCode)) {

			Boolean isRootEmp = SecurityUtil.getCurrentEmpIsRoot();// 是否是集团公司人员
			String orgDataFilterType = SecurityUtil.getCurrentEmpOrgDataFilterType();// 数据加载策略

			if (StrUtils.isNotEmpty(orgDataFilterType) && !orgDataFilterType.equals(SecurityScope.USER_ORG_DATAFILTERTYPE_N)) {
				if (isAddStr) {
					sbTmp.append(" and ");
				}

				if (StringUtils.isNotEmpty(entityAlilasName)) {
					sbTmp.append(" ").append(entityAlilasName).append(".").append(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_COMPANYINNERCODE);
				} else {
					sbTmp.append(" ").append(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_COMPANYINNERCODE);
				}

				if (isRootEmp) {
					if (orgDataFilterType.equals(SecurityScope.USER_ORG_DATAFILTERTYPE_P)) {// 只能查看自己
						sbTmp.append(" = :").append(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY).append(" ");
						paramMap.put(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY, companyInnerCode);
					} else if (orgDataFilterType.equals(SecurityScope.USER_ORG_DATAFILTERTYPE_A)) {// 总公司人员查看自己和所有子公司数据
						sbTmp.append(" like :").append(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY).append(" ");
						paramMap.put(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY, companyInnerCode + "%");
					}
				} else {
					// 子公司的只能查看自己的
					sbTmp.append(" = :").append(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY).append(" ");
					paramMap.put(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY, companyInnerCode);
				}
			}
		}

		// 不使用分管
		// handlerForAddProxyHql(sbTmp, entityAlilasName, paramMap);

		if (sbTmp.length() == 0) {
			return " 1=1 ";
		}
		return sbTmp.toString();
	}

	/**
	 * 设定参数map 中的 tenantId
	 * 
	 * @param entityAlilasName
	 * @param paramMap
	 *            private void getTenantIdCondition4Map(Map<String,Object>
	 *            paramMap){
	 * 
	 *            //如果paramMap为null，下面的赋值无法传递会父级方法（仍然为null）
	 *            Assert.isTrue(paramMap!=null,"getTenantIdCondition4Hql传入参数paramMap不能为null！");
	 * 
	 *            String tenantId = getTenantId(paramMap);
	 *            if(StringUtils.isNotEmpty(tenantId)){
	 * 
	 *            paramMap.put(BizConstant.COMMON_GLOBAL_FLAG_TENANTID_KEY+","+SearchCondition.EQUAL,
	 *            tenantId); } }
	 */

	/**
	 * hql 的tenantId的拼装
	 * 
	 * @param entityAlilasName
	 * @param paramMap
	 * @return
	 */
	private String getTenantIdCondition4Hql(String hql, String entityAlilasName, Map<String, Object> paramMap) {

		// 如果paramMap为null，下面的赋值无法传递会父级方法（仍然为null）
		Assert.isTrue(paramMap != null, "getTenantIdCondition4Hql传入参数paramMap不能为null！");

		String hqlTrim = hql.trim();

		// 20140411自动增加select尽量少报错,只处理针对声明为hentity的
		if (!hqlTrim.startsWith("select")) {
			if (hqlTrim.startsWith("from")) {
				if (hqlTrim.indexOf(" entity ") != -1) {
					hqlTrim = "select entity " + hqlTrim;
				} else if (hqlTrim.indexOf(" hentity ") != -1) {
					hqlTrim = "select hentity " + hqlTrim;
				} else {
					String[] parts = hqlTrim.split("\\s");
					if (parts.length >= 2 && !"where".equals(parts[2].trim().toLowerCase())) {
						// hql的格式为 from XXX xxx .... 可以取到别名
						hqlTrim = "select " + parts[2] + " " + hqlTrim;
					} else {
						// hql的格式中没有别名 from XXX,但至少有2部分，如果再少则报错
						hqlTrim = "select " + parts[1] + " " + hqlTrim;
					}
				}
			}
		}

		Assert.isTrue(hqlTrim.startsWith("select"), "HQL语句必须以select开头！");

		/*
		 * if(StringUtils.isEmpty(entityAlilasName)){ //如果没有传过来别名，则查找是否有别名
		 * entityAlilasName = getHqlAlilasName(hqlTrim); }
		 */

		StringBuilder sbTmp = new StringBuilder(hqlTrim);

		Boolean isAddStr = false;// 标识是否进入 添加tenantId
		Boolean isAddStrCompCode = false;// 标识是否进入 添加companInnerCode
		Boolean isAddProxyOrg = false;// 是否添加分管

		String tenantId = getTenantId(paramMap);
		if (StringUtils.isNotEmpty(tenantId)) {
			isAddStr = true;

			StringBuilder sbTenantTmp = new StringBuilder();

			paramMap.put(BizConstant.COMMON_GLOBAL_FLAG_TENANTID_KEY, tenantId);

			int wherePos = sbTmp.indexOf("where") + 5;
			Assert.isTrue(wherePos != 4, "HQL语句没有where条件! ");

			if (StringUtils.isNotEmpty(entityAlilasName)) {
				sbTenantTmp.append(" ").append(entityAlilasName).append(".").append(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID);
			} else {
				// 如果没有传过来别名，则查找是否有别名
				entityAlilasName = getHqlAlilasName(hqlTrim);
				Assert.hasLength(entityAlilasName, "HQL语句没有取别名！");

				if (StringUtils.isNotEmpty(entityAlilasName)) {
					sbTenantTmp.append(" ").append(entityAlilasName).append(".").append(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID);
				} else {
					sbTenantTmp.append(" ").append(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID);
				}
			}

			// sbTmp.append("
			// ").append(entityAlilasName).append(".").append(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID);
			sbTenantTmp.append(" = :").append(BizConstant.COMMON_GLOBAL_FLAG_TENANTID_KEY).append(" ");

			// return "
			// "+entityAlilasName+"."+BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID
			// +
			// " = :" +
			// BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID_BINDPARAM +
			// "
			// ";
			sbTenantTmp.append(" and ");

			sbTmp.insert(wherePos, sbTenantTmp.toString());

			// int entityAlilasNamePos = sbTmp.indexOf(entityAlilasName);
			// sbTmp.insert(entityAlilasNamePos, "distinct ");

			// return sbTmp.toString();
		}

		String companyInnerCode = getCompanyInnerCode(paramMap);
		if (StringUtils.isNotEmpty(companyInnerCode)) {

			Boolean isRootEmp = SecurityUtil.getCurrentEmpIsRoot();// 是否是集团公司人员
			String orgDataFilterType = SecurityUtil.getCurrentEmpOrgDataFilterType();// 数据加载策略

			if (StrUtils.isNotEmpty(orgDataFilterType) && !orgDataFilterType.equals(SecurityScope.USER_ORG_DATAFILTERTYPE_N)) {
				isAddStrCompCode = true;

				StringBuilder sbCompInnerCodeTmp = new StringBuilder();
				int wherePos = sbTmp.indexOf("where") + 5;
				Assert.isTrue(wherePos != 4, "HQL语句没有where条件! ");

				if (StringUtils.isNotEmpty(entityAlilasName)) {
					sbCompInnerCodeTmp.append(" ").append(entityAlilasName).append(".").append(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_COMPANYINNERCODE);
				} else {
					sbCompInnerCodeTmp.append(" ").append(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_COMPANYINNERCODE);
				}

				if (isRootEmp) {
					if (orgDataFilterType.equals(SecurityScope.USER_ORG_DATAFILTERTYPE_P)) {// 只能查看自己
						sbCompInnerCodeTmp.append(" = :").append(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY).append(" ");
						paramMap.put(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY, companyInnerCode);
					} else if (orgDataFilterType.equals(SecurityScope.USER_ORG_DATAFILTERTYPE_A)) {// 总公司人员查看自己和所有子公司数据
						sbCompInnerCodeTmp.append(" like :").append(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY).append(" ");
						paramMap.put(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY, companyInnerCode + "%");
					}
				} else {
					// 子公司的只能查看自己的
					sbCompInnerCodeTmp.append(" = :").append(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY).append(" ");
					paramMap.put(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY, companyInnerCode);
				}

				sbCompInnerCodeTmp.append(" and ");

				sbTmp.insert(wherePos, sbCompInnerCodeTmp.toString());
			}
		}

		// 分管
		/*
		 * Boolean isAddProxyData = checkIsAddProxyCode(paramMap); Boolean
		 * hasProxyData = SecurityUtil.hasProxyData();
		 * 
		 * if(isAddProxyData && hasProxyData){ isAddProxyOrg = true;
		 * StringBuilder sbCompInnerCodeTmp = new StringBuilder(); int wherePos
		 * = sbTmp.indexOf("where")+5; Assert.isTrue(wherePos!=4,
		 * "HQL语句没有where条件! ");
		 * 
		 * 
		 * sbTmp.append(" and ( 1=1 ");// and ( 1=1
		 * 
		 * String proxyOrgIds = SecurityUtil.getProxyOrgIds();//代理公司
		 * if(StringUtils.isNotEmpty(proxyOrgIds)){ sbTmp.append(" or ");
		 * if(StringUtils.isNotEmpty(entityAlilasName)){
		 * sbTmp.append(" ").append(entityAlilasName).append(".").append(
		 * BizConstant. COMMON_GLOBAL_FLAG_HQL_PROPERTY_COMPANYINNERCODE);
		 * }else{ sbTmp.append(" ").append(BizConstant.
		 * COMMON_GLOBAL_FLAG_HQL_PROPERTY_COMPANYINNERCODE); }
		 * sbTmp.append(" in (").append(proxyOrgIds).append(") "); }
		 * 
		 * 
		 * String proxyOrgUnitIds = SecurityUtil.getProxyOrgUnitIds();//代理部门
		 * if(StringUtils.isNotEmpty(proxyOrgUnitIds)){
		 * if(StringUtils.isNotEmpty(proxyOrgIds)){//如果存在代理公司 则添加or
		 * sbTmp.append(" or "); }
		 * 
		 * if(StringUtils.isNotEmpty(entityAlilasName)){
		 * sbTmp.append(" ").append(entityAlilasName).append(".").append(
		 * BizConstant. COMMON_GLOBAL_FLAG_HQL_PROPERTY_DEPARTMENTCODE); }else{
		 * sbTmp.append(" ").append(BizConstant.
		 * COMMON_GLOBAL_FLAG_HQL_PROPERTY_DEPARTMENTCODE); }
		 * sbTmp.append(" in (").append(proxyOrgUnitIds).append(") "); }
		 * 
		 * sbTmp.append(") ");
		 * 
		 * //sbCompInnerCodeTmp.append(" and ");
		 * 
		 * sbTmp.insert(wherePos, sbCompInnerCodeTmp.toString()); }
		 */

		if (isAddStr || isAddStrCompCode) {// ||isAddProxyOrg
			int entityAlilasNamePos = sbTmp.indexOf(entityAlilasName);
			sbTmp.insert(entityAlilasNamePos, "distinct ");

			return sbTmp.toString();
		}

		return hqlTrim.toString();
	}

	// ************** 开始 如下代码为 添加companyInnerCode的代码

	/** 检查是否存在companyInnerCode */
	private Boolean findHasCompanyInnerCodeHbm(ClassMetadata meta, Object obj) {
		// ClassMetadata meta =
		// getSession().getSessionFactory().getClassMetadata(obj.getClass());
		Type type = meta.getPropertyType(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_COMPANYINNERCODE);
		if (type != null) {
			// meta.setPropertyValue(obj,
			// BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID,
			// SecurityUtil.getCurrentUserAccount().getTenantId());
			return true;
		}
		return false;
	}

	/** 检查参数中是否存在companyInnerCode */
	private Boolean checkIsAddCompanyInnerCode(Map<String, Object> paramMap) {
		if (paramMap != null && paramMap.containsKey(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY)) {
			Object tenantIdVal = paramMap.get(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY);
			if (tenantIdVal.toString().equals(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY_NO)) {
				paramMap.remove(BizConstant.COMMON_GLOBAL_FLAG_COMPANYINNERCODE_KEY);
				return false;
			}
		}

		String orgDataFilterType = SecurityUtil.getCurrentEmpOrgDataFilterType();
		if (StrUtils.isNotEmpty(orgDataFilterType) && orgDataFilterType.equals(SecurityScope.USER_ORG_DATAFILTERTYPE_N)) {
			return false;
		}

		return true;
	}

	/** 获取 CompanyInnerCode */
	private String getCompanyInnerCode(Map<String, Object> paramMap) {
		Boolean isAddCompanyInnerCode = checkIsAddCompanyInnerCode(paramMap);
		if (isAddCompanyInnerCode) {
			return getCompanyInnerCode();
		}
		return null;
	}

	/** 获取 CompanyInnerCode */
	@SuppressWarnings("unused")
	private String getCompanyInnerCode() {
		return SecurityUtil.getCurrentEmpOrgInnerCode();
	}

	// ****************结束 添加companyInnerCode的代码

	// ************** 开始 如下代码为 添加departmentCode的代码

	/** 检查是否存在departmentCode */
	private Boolean findHasDepartmentCodeHbm(ClassMetadata meta, Object obj) {
		// ClassMetadata meta =
		// getSession().getSessionFactory().getClassMetadata(obj.getClass());
		Type type = meta.getPropertyType(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_DEPARTMENTCODE);
		if (type != null) {
			// meta.setPropertyValue(obj,
			// BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID,
			// SecurityUtil.getCurrentUserAccount().getTenantId());
			return true;
		}
		return false;
	}

	/** 检查参数中是否存在代理公司或者部门 编码 */
	private Boolean checkIsAddProxyCode(Map<String, Object> paramMap) {
		if (paramMap != null && paramMap.containsKey(BizConstant.COMMON_GLOBAL_FLAG_PROXY_KEY)) {
			Object tenantIdVal = paramMap.get(BizConstant.COMMON_GLOBAL_FLAG_PROXY_KEY);
			if (tenantIdVal.toString().equals(BizConstant.COMMON_GLOBAL_FLAG_PROXY_KEY_NO)) {
				paramMap.remove(BizConstant.COMMON_GLOBAL_FLAG_PROXY_KEY);
				return false;
			}
		}
		return true;
	}

	/**
	 * 获取 departmentCode private String getDepartmentCode(Map<String,Object>
	 * paramMap){ Boolean isAddDepartmentCode =
	 * checkIsAddDepartmentCode(paramMap); if(isAddDepartmentCode){ return
	 * getDepartmentCode(); } return null; }
	 */

	/** 获取 departmentCode */
	@SuppressWarnings("unused")
	private String getDepartmentCode() {
		return SecurityUtil.getCurrentEmpOrgUnitCode();
	}

	// ****************结束 添加departmentCode的代码

	@Override
	public <T> T findEntityById(Class<T> entityClass, String id) throws Exception {
		Object obj = getSession().get(entityClass, id);
		if (null != obj) {
			return (T) obj;
		}
		return null;
	}

	@Override
	public <T> T queryEntityByJdbc(String tableName, Class<T> T, String id) throws Exception {
		String sql = "select t.* from " + tableName + " t where t.ID = ? ";
		try {
			T obj = this.jdbcTemplate.queryForObject(sql, T, new Object[]{id});
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public <T> String getTableNameByClass(Class<T> entityClass) throws Exception {
		AbstractEntityPersister classMetadata = (AbstractEntityPersister) getSession().getSessionFactory().getClassMetadata(entityClass);
		return classMetadata.getTableName();
	}

	@Override
	public <T> T findEntityByAttribute(Class<T> entityClass, String attribute, Object value) throws Exception {
		StringBuilder hql = new StringBuilder(" from ");
		hql.append(entityClass.getName());
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != attribute && null != value) {
			hql.append(" entity where ");

			// ********添加tenanID条件************************************
			// ########方法体中创建的Map 没办法添加 tenantId ########### 暂时直接追加
			// hql.append(getTenantIdCondition4ParamHql2("entity", params));

			hql.append(" entity.");
			hql.append(attribute);
			hql.append(" = :");
			if (attribute.contains(".")) {
				hql.append(attribute.split("\\.")[0]);
				params.put(attribute.split("\\.")[0], value);
			} else {
				hql.append(attribute);
				params.put(attribute, value);
			}
		}
		return (T) findObjectByHql(hql.toString(), params);
	}

	@Override
	public <T> T findEntityByAttributeNoTenantId(Class<T> entityClass, String attribute, Object value) throws Exception {
		StringBuilder hql = new StringBuilder(" from ");
		hql.append(entityClass.getName());
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != attribute && null != value) {
			hql.append(" entity where entity.");

			// ********添加tenanID条件************************************
			// ########方法体中创建的Map 没办法添加 tenantId ########### 暂时直接追加
			// hql.append(getTenantIdCondition4ParamHql2("entity", params));
			hql.append(attribute);
			hql.append(" = :");
			if (attribute.contains(".")) {
				hql.append(attribute.split("\\.")[0]);
				params.put(attribute.split("\\.")[0], value);
			} else {
				hql.append(attribute);
				params.put(attribute, value);
			}
		}
		return (T) findObjectByHqlNoTenantId(hql.toString(), params);
	}

	@Override
	public <T> List<T> findAllByHql(String hql, Map<String, Object> params) throws Exception {

		hql = getTenantIdCondition4Hql(hql, null, params);

		Query query = getSession().createQuery(hql);
		// getTenantIdCondition4Map(params);
		if (null != params) {
			configQuery(query, params);
		}
		return query.list();
	}

	/**
	 * ####sql查询
	 */
	@Override
	public <T> List<T> findAllBySql(String sql, Map<String, Object> params) throws Exception {
		Query query = getSession().createSQLQuery(sql);
		// ########没有处理
		query.setProperties(params);
		return query.list();
	}

	@Override
	public <T> List<T> findAllByEntityClass(Class<T> entityClass) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from ");
		hql.append(entityClass.getName());

		// ×××××××××××××××
		hql.append(" where 1=1 ");
		String tenantHql = getTenantIdCondition4ParamHql2(null, params);
		if (StringUtils.isNotEmpty(tenantHql)) {
			hql.append(" and ").append(tenantHql);
		}
		Query query = getSession().createQuery(hql.toString());
		query.setProperties(params);
		return query.list();
	}

	@Override
	public <T> List<T> findAllByEntityClassNoTenantId(Class<T> entityClass) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from ");
		hql.append(entityClass.getName());

		Query query = getSession().createQuery(hql.toString());
		query.setProperties(params);
		return query.list();
	}

	@Override
	public <T> List<T> findAllByEntityClassAndAttribute(Class<T> entityClass, String attribute, Object value) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from ");
		hql.append(entityClass.getName());

		if (null != attribute && null != value) {
			hql.append(" entity where ");
			// *********
			hql.append(getTenantIdCondition4ParamHql2("entity", params));

			hql.append(" and entity.");
			hql.append(attribute);
			hql.append(" = :");
			hql.append("attr");
			params.put("attr", value);
		}
		Query query = getSession().createQuery(hql.toString());
		query.setProperties(params);
		return query.list();
	}

	@Override
	public <T> List<T> findAllByEntityClassAndAttributeNoTenantId(Class<T> entityClass, String attribute, Object value) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("from ");
		hql.append(entityClass.getName());

		if (null != attribute && null != value) {
			hql.append(" entity where ");

			hql.append(" entity.");
			hql.append(attribute);
			hql.append(" = :");
			hql.append("attr");
			params.put("attr", value);
		} else {
			hql.append(" entity where ");

			hql.append(" entity.");
			hql.append(attribute);
			hql.append(" is null ");
		}
		hql.append(" order by ").append("entity.createTime asc ");
		Query query = getSession().createQuery(hql.toString());
		query.setProperties(params);
		return query.list();
	}

	@Override
	public <T> T findObjectByUUID(Class<T> entityClass, String uuid) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select entity from ");
		hql.append(entityClass.getName());
		hql.append(" entity where entity.uuid  = :attr ");
		params.put("attr", uuid);
		Query query = getSession().createQuery(hql.toString());
		query.setProperties(params);

		Object obj = query.uniqueResult();
		if (null != obj) {
			return (T) obj;
		}
		return null;
	}

	/**
	 * 使用传统的参数设定
	 */
	@Override
	public <T> List<T> findAllByHql2(String hql, Map<String, Object> params) throws Exception {
		// *********************
		// 解析hql
		hql = getTenantIdCondition4Hql(hql, null, params);

		String aliasName = getHqlAlilasName(hql);
		aliasName = StringUtils.replace(aliasName, "distinct", "");
		hql = getFindHqlDis(hql, aliasName);

		Query query = getSession().createQuery(hql);

		if (null != params) {
			// query.setProperties(params);
			addArgs(params, query);
		}
		return query.list();
	}

	/**
	 * 原生HQL查询
	 * 
	 * @param <T>
	 * @param hql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> List<T> findAllByHqlOrginial(String hql, Map<String, Object> params) throws Exception {
		Query query = getSession().createQuery(hql);
		if (null != params) {
			addArgs(params, query);
		}
		return query.list();
	}

	@Override
	public <T> T findFirstByHqlOrginial(String hql, Map<String, Object> params) throws Exception {
		Query query = getSession().createQuery(hql);
		query.setProperties(params);
		query.setMaxResults(1);

		Object obj = query.uniqueResult();
		if (null != obj) {
			return (T) obj;
		}
		return null;
	}

	@Override
	public <T> List<T> findFirstListByHqlOrginial(String hql, Map<String, Object> params, int size) throws Exception {
		Query query = getSession().createQuery(hql);
		if (null != params) {
			query.setProperties(params);
		}
		query.setMaxResults(size);
		return query.list();
	}

	@Override
	public <T> List<T> findAllByHql2NoTenantId(String hql, Map<String, Object> params) throws Exception {
		// *********************
		// 解析hql
		// hql = getTenantIdCondition4Hql(hql, null, params);

		String aliasName = getHqlAlilasName(hql);
		aliasName = StringUtils.replace(aliasName, "distinct", "");
		hql = getFindHqlDis(hql, aliasName);

		Query query = getSession().createQuery(hql);

		if (null != params) {
			// query.setProperties(params);
			addArgs(params, query);
		}
		return query.list();
	}

	@Override
	public <T> List<T> findAllByHql2NoTenantId(String hql, String aliasName, Map<String, Object> params) throws Exception {
		// *********************
		// 解析hql
		// hql = getTenantIdCondition4Hql(hql, null, params);

		// aliasName = StringUtils.replace(aliasName, "distinct", "");
		hql = getFindHqlDis(hql, aliasName);

		Query query = getSession().createQuery(hql);

		if (null != params) {
			// query.setProperties(params);
			addArgs(params, query);
		}
		return query.list();
	}

	@Override
	public <T> List<T> findAllByHql(String filterName, Map<String, Object> filterParams, String hql, Map<String, Object> params) throws Exception {
		Session session = getSession();
		Filter filter = session.enableFilter(filterName);
		for (Map.Entry<String, Object> entry : filterParams.entrySet()) {
			filter.setParameter(entry.getKey(), entry.getValue());
		}

		// ******************解析hql
		hql = getTenantIdCondition4Hql(hql, null, params);

		Query query = session.createQuery(hql);
		if (null != params) {
			query.setProperties(params);
		}
		return query.list();
	}

	@Override
	public <T> T save(Object entity) throws Exception {
		saveTenantIdHbm(entity);
		saveCompanyInnerCodeHbm(entity);
		saveDepartmentCodeHbm(entity);
		// save4UUID(entity);

		T t = (T) getSession().save(entity);
		getSession().flush();
		return t;
	}

	@Override
	public void update(Object entity) throws Exception {
		saveTenantIdHbm(entity);
		saveCompanyInnerCodeHbm(entity);
		saveDepartmentCodeHbm(entity);
		// save4UUID(entity);

		getSession().update(entity);
	}

	@Override
	public void executeSql(String sql, Map<String, Object> params) throws Exception {
		// ##############没有处理
		Query query = getSession().createSQLQuery(sql);
		if (null != params && params.keySet().size() > 0) {
			query.setProperties(params);
		}
		query.executeUpdate();
	}

	@Override
	public void saveOrUpdate(Object entity) throws Exception {
		saveTenantIdHbm(entity);
		saveCompanyInnerCodeHbm(entity);
		saveDepartmentCodeHbm(entity);
		// save4UUID(entity);

		getSession().saveOrUpdate(entity);
	}

	@Override
	public void saveOrUpdateOriginal(Object entity) throws Exception {
		// save4UUID(entity);
		getSession().saveOrUpdate(entity);
	}

	@Override
	public <T> void saveOrUpdateOriginalBatch(List<T> entityList) throws Exception {
		if (entityList != null && !entityList.isEmpty()) {
			for (int i = 0; i < entityList.size(); i++) {
				Object obj = entityList.get(i);
				// save4UUID(obj);
				getSession().saveOrUpdate(obj);
				if (i % 20 == 0) {
					getSession().flush();
					getSession().clear();
				}
			}
		}
	}

	@Override
	public <T> T merge(Object entity) throws Exception {
		saveTenantIdHbm(entity);
		saveCompanyInnerCodeHbm(entity);
		saveDepartmentCodeHbm(entity);
		// save4UUID(entity);

		T t = (T) getSession().merge(entity);
		return t;
	}

	@Override
	public <T> T mergeOriginal(Object entity) throws Exception {
		T t = (T) getSession().merge(entity);
		return t;
	}

	@Override
	public <T> void deleteById(Class<T> entityClass, String id) throws Exception {
		T t = findEntityById(entityClass, id);
		if (null != t) {
			getSession().delete(t);
			logger.info("删除对象{0},对象id为：{1}", entityClass.getName(), id);
		} else {
			logger.info("删除对象{0}失败，对象id为：{1},对象已经在删除操作前被其他用户删除", entityClass.getName(), id);
		}
	}

	@Override
	public <T> void deleteByAttribute(Class<T> entityClass, String attribute, Object value) throws Exception {
		// ××××××××××××××××××××××××××××××
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("delete from ");
		hql.append(entityClass.getName());
		hql.append(" entity where ");

		hql.append(getTenantIdCondition4ParamHql2("entity", params));

		hql.append(" and entity.");
		hql.append(attribute);
		if (null == value) {
			hql.append(" is null");
		} else {
			hql.append(" = :");
			if (attribute.contains(".")) {
				hql.append(attribute.split("\\.")[0]);
				params.put(attribute.split("\\.")[0], value);
			} else {
				hql.append(attribute);
				params.put(attribute, value);
			}
		}
		Query query = getSession().createQuery(hql.toString());
		query.setProperties(params);
		query.executeUpdate();
	}

	@Override
	public <T> void deleteByEntity(Object entity) throws Exception {
		getSession().delete(entity);
	}

	@Override
	public void refresh(Object entity) throws Exception {
		getSession().refresh(entity);
	}

	@Override
	public <T> T findObjectByHql(String hql, Map<String, Object> params) throws Exception {
		// ********************************
		// 解析hql
		try {
			hql = getTenantIdCondition4Hql(hql, null, params);

			Query query = getSession().createQuery(hql);
			query.setProperties(params);
			Object obj = query.uniqueResult();
			if (null != obj) {
				return (T) obj;
			}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}

	@Override
	public <T> T findObjectByHqlNoTenantId(String hql, Map<String, Object> params) throws Exception {
		// ********************************
		// 解析hql
		try {
			Query query = getSession().createQuery(hql);
			query.setProperties(params);
			Object obj = query.uniqueResult();
			if (null != obj) {
				return (T) obj;
			}
		} catch (Exception e) {
			throw e;
		}
		return null;
	}

	@Override
	public <T> T findObjectByHql(String hql, Boolean isAddTenantId, Map<String, Object> params) throws Exception {
		// ********************************
		// 解析hql
		if (isAddTenantId) {
			hql = getTenantIdCondition4Hql(hql, null, params);
		}

		Query query = getSession().createQuery(hql);
		query.setProperties(params);
		Object obj = query.uniqueResult();
		if (null != obj) {
			return (T) obj;
		}
		return null;
	}

	/** 根据条件查询数据条数 */
	@Override
	public <T> long findDataCountByConditions(Class<T> entityClass, Map<String, Object> params) throws Exception {
		StringBuilder hql = new StringBuilder("select count(id) from ");
		hql.append(entityClass.getName());
		// ××××××××××××××××××××××
		hql.append(" where ");
		hql.append(getTenantIdCondition4ParamHql(null, params));

		Query query = getSession().createQuery(hql.toString());
		query.setProperties(params);
		return (Long) query.uniqueResult();
	}

	@Override
	public long findDataCountByHql(StringBuilder hql, String alilasName, Map<String, Object> params) throws Exception {
		hql.append(getTenantIdCondition4ParamHql(alilasName, params));
		Query query = getSession().createQuery(hql.toString());
		query.setProperties(params);
		return (Long) query.uniqueResult();
	}

	@Override
	public long findDataCountByHqlOrginial(StringBuilder hql, String alilasName, Map<String, Object> params) throws Exception {
		Query query = getSession().createQuery(hql.toString());
		query.setProperties(params);
		return (Long) query.uniqueResult();
	}

	@Override
	public <T> T findObjectBySql(String sql, Map<String, Object> params) throws Exception {
		// ############没有处理
		Query query = getSession().createSQLQuery(sql);
		query.setProperties(params);
		Object obj = query.uniqueResult();
		if (null != obj) {
			return (T) obj;
		}
		return null;
	}

	@Override
	public <T> void batchSave(List<T> entityList) throws Exception {

		if (entityList != null && !entityList.isEmpty()) {
			Object objTmp = entityList.get(0);

			String className = objTmp.getClass().getName();
			if (StringUtils.contains(className, "_")) {
				className = StringUtils.substringBefore(className, "_");
			}

			ClassMetadata meta = getSession().getSessionFactory().getClassMetadata(className);

			// saveTenantIdHbm(entity);
			Boolean hasTenantId = findHasTenantIdHbm(meta, objTmp);
			Boolean hasCompanyInnerCode = findHasCompanyInnerCodeHbm(meta, objTmp);

			for (int i = 0; i < entityList.size(); i++) {
				if (hasTenantId) {
					saveTenantIdHbm(entityList.get(i));
				}
				if (hasCompanyInnerCode) {
					saveCompanyInnerCodeHbm(entityList.get(i));
				}
				saveDepartmentCodeHbm(entityList.get(i));
				// save4UUID(entityList.get(i));

				getSession().save(entityList.get(i));
				if (i % 20 == 0) {
					getSession().flush();
					getSession().clear();
				}
			}
		}

	}

	@Override
	public <T> void batchUpdateByList(List<T> entityList) throws Exception {
		if (entityList != null && !entityList.isEmpty()) {
			Object objTmp = entityList.get(0);

			String className = objTmp.getClass().getName();
			if (StringUtils.contains(className, "_")) {
				className = StringUtils.substringBefore(className, "_");
			}

			ClassMetadata meta = getSession().getSessionFactory().getClassMetadata(className);

			// saveTenantIdHbm(entity);
			Boolean hasTenantId = findHasTenantIdHbm(meta, objTmp);

			// Boolean hasCompanyInnerCode = findHasCompanyInnerCodeHbm(meta,
			// objTmp);
			for (int i = 0; i < entityList.size(); i++) {

				if (hasTenantId) {
					saveTenantIdHbm(entityList.get(i));
				}

				/*
				 * if(hasCompanyInnerCode){
				 * saveCompanyInnerCodeHbm(entityList.get(i)); }
				 */

				getSession().update(entityList.get(i));
				// save4UUID(entityList.get(i));
				if (i % 20 == 0) {
					getSession().flush();
					getSession().clear();
				}
			}
		}

	}

	@Override
	public void batchUpdateByHql(String hql, Map<String, Object> params) throws Exception {
		// ###############没有处理
		// ********************************
		// 解析hql
		// 20140411
		// hql = getTenantIdCondition4Hql(hql, null, params);

		Query query = getSession().createQuery(hql);
		query.setProperties(params);
		query.executeUpdate();
	}

	/*
	 * public void batchUpdateByHql2(String hql,Map<String,List<Object>> params)
	 * throws Exception{ Query query = getSession().createQuery(hql);
	 * for(Map.Entry<String, List<Object>> entry: params.entrySet()){
	 * query.setParameterList(entry.getKey(),entry.getValue()); }
	 * query.executeUpdate(); }
	 */

	@Override
	public void batchUpdateBySql(String sql, Map<String, Object> params) throws Exception {
		// ###############没有处理
		Query query = getSession().createSQLQuery(sql);
		query.setProperties(params);
		query.executeUpdate();
	}

	@Override
	public void batchUpdateBySql(String sql, List<Object[]> params) throws Exception {
		// ###############没有处理
		this.jdbcTemplate.batchUpdate(sql, params);
	}

	/*
	 * public void batchUpdateBySql2(String sql,Map<String,List<Object>> params)
	 * throws Exception{ Query query = getSession().createQuery(sql);
	 * for(Map.Entry<String, List<Object>> entry: params.entrySet()){
	 * query.setParameterList(entry.getKey(),entry.getValue()); }
	 * query.executeUpdate(); }
	 */

	@Override
	public <T> void batchDelete(Class<T> entityClass, List<String> ids) throws Exception {
		for (String id : ids) {
			deleteById(entityClass, id);
		}
	}

	@Override
	public void batchDeleteByHql(String hql, Map<String, Object> params) throws Exception {
		// ********************************
		// 解析hql
		hql = getTenantIdCondition4Hql(hql, null, params);
		Query query = getSession().createQuery(hql);
		query.setProperties(params);
		query.executeUpdate();
	}

	@Override
	public void batchDeleteBySql(String sql, Map<String, Object> params) throws Exception {
		// ###############没有处理
		Query query = getSession().createSQLQuery(sql);
		query.setProperties(params);
		query.executeUpdate();
	}

	@Override
	public Pager findTreePagerByHql(String pageHql, String countHql, Pager pager, Map<String, Object> params) throws Exception {
		// ###############没有处理
		Query query = getSession().createQuery(countHql);
		if (null != params && params.keySet().size() > 0) {
			configQuery(query, params);
		}
		int totalCount = 0;
		Object uniqueR = query.uniqueResult();
		if (null != uniqueR && !"".equals(uniqueR)) {
			totalCount = Integer.parseInt(uniqueR.toString());
		}
		pager.setTotalCount(totalCount);

		// *************************************************************
		// 解析hql
		pageHql = getTenantIdCondition4Hql(pageHql, null, params);
		Query resultQuery = getSession().createQuery(pageHql);
		if (null != params && params.keySet().size() > 0) {
			configQuery(resultQuery, params);
		}
		resultQuery.setFirstResult((pager.getPageNo() - 1) * pager.getPageSize());
		resultQuery.setMaxResults(pager.getPageSize());
		pager.setResultList(resultQuery.list());
		return pager;
	}

	@Override
	public Pager findPagerByHql(Pager pager, String hql, Map<String, Object> params) throws Exception {
		return this.findPagerByHql(pager, hql, params, false);
	}

	@Override
	public Pager findPagerByHql(Pager pager, String hql, Map<String, Object> params, boolean isFindAll) throws Exception {

		String classAlilasName = getHqlAlilasName(hql.trim());

		return this.findPagerByHql(pager, classAlilasName, hql, params, isFindAll);
	}

	@Override
	public Pager findPagerByHql(Pager pager, String classAlilasName, String hql, Map<String, Object> params) throws Exception {
		return this.findPagerByHql(pager, hql, params, false);
	}

	public Pager findPagerByHql(Pager pager, String classAlilasName, String hql, Map<String, Object> params, boolean isFindAll) throws Exception {
		// ***********************
		hql = getTenantIdCondition4Hql(hql, classAlilasName, params);

		if (!isFindAll) {
			String countHqlHead = handleCountHql(classAlilasName);
			// String countHql = countHqlHead + removeOrderBy(removeFetch(hql));
			String countHql = handleWithHead(countHqlHead, classAlilasName, removeOrderBy(removeFetch(hql)));
			Query query = getSession().createQuery(countHql);
			if (null != params && params.keySet().size() > 0) {
				configQuery(query, params);
			}
			int totalCount = 0;
			Object uniqueR = query.uniqueResult();
			if (null != uniqueR && !"".equals(uniqueR)) {
				totalCount = Integer.parseInt(uniqueR.toString());
			}
			pager.setTotalCount(totalCount);
		}

		// removeOrderBy
		Query resultQuery = getSession().createQuery(hql);
		if (null != params && params.keySet().size() > 0) {
			configQuery(resultQuery, params);
		}
		if (!isFindAll) {
			resultQuery.setFirstResult((pager.getPageNo() - 1) * pager.getPageSize());
			resultQuery.setMaxResults(pager.getPageSize());
		}
		pager.setResultList(resultQuery.list());
		return pager;
	}

	@Override
	public Pager findPager2ByHql(Pager pager, String classAlilasName, String hql, Map<String, Object> params) throws Exception {

		int fromPos = StringUtils.indexOfIgnoreCase(hql, "from ");
		fromPos = fromPos + 5;
		int endPos = StringUtils.indexOfIgnoreCase(hql, " ", fromPos);
		String entityClass = StringUtils.substring(hql, fromPos, endPos);

		hql = beforeHqlExecute(hql, entityClass, params);

		// ###############没有处理
		hql = getTenantIdCondition4Hql(hql, classAlilasName, params);

		String countHqlHead = handleCountHql(classAlilasName);
		// String countHql = countHqlHead+removeOrderBy(removeFetch(hql));
		String countHql = handleWithHead(countHqlHead, classAlilasName, removeOrderBy(removeFetch(hql)));
		Query query = getSession().createQuery(countHql);
		if (null != params && params.keySet().size() > 0) {
			// configQuery(query,params);
			// query.setProperties(params);
			addArgs(params, query);
		}
		int totalCount = 0;
		Object uniqueR = query.uniqueResult();
		if (null != uniqueR && !"".equals(uniqueR)) {
			totalCount = Integer.parseInt(uniqueR.toString());
		}
		pager.setTotalCount(totalCount);

		// removeOrderBy
		Query resultQuery = getSession().createQuery(hql);
		if (null != params && params.keySet().size() > 0) {
			// configQuery(resultQuery,params);
			resultQuery.setProperties(params);
		}
		resultQuery.setFirstResult((pager.getPageNo() - 1) * pager.getPageSize());
		resultQuery.setMaxResults(pager.getPageSize());
		pager.setResultList(resultQuery.list());
		return pager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.core.persistence.hibernate.dao.IBaseHibernateDao#
	 * findPagerOrginialByHql(com.vix.core.web.Pager, java.lang.String,
	 * java.lang.String, java.util.Map)
	 */
	@Override
	public Pager findPagerOrginialByHql(Pager pager, String classAlilasName, String hql, Map<String, Object> params) throws Exception {
		String countHqlHead = handleCountHql(classAlilasName);
		// String countHql = countHqlHead+removeOrderBy(removeFetch(hql));
		String countHql = handleWithHead(countHqlHead, classAlilasName, removeOrderBy(removeFetch(hql)));
		Query query = getSession().createQuery(countHql);
		if (null != params && params.keySet().size() > 0) {
			// configQuery(query,params);
			// query.setProperties(params);
			addArgs(params, query);
		}
		int totalCount = 0;
		Object uniqueR = query.uniqueResult();
		if (null != uniqueR && !"".equals(uniqueR)) {
			totalCount = Integer.parseInt(uniqueR.toString());
		}
		pager.setTotalCount(totalCount);

		// removeOrderBy
		Query resultQuery = getSession().createQuery(hql);
		if (null != params && params.keySet().size() > 0) {
			// configQuery(resultQuery,params);
			resultQuery.setProperties(params);
		}
		resultQuery.setFirstResult((pager.getPageNo() - 1) * pager.getPageSize());
		resultQuery.setMaxResults(pager.getPageSize());
		pager.setResultList(resultQuery.list());
		return pager;
	}

	@Override
	@Deprecated
	public Pager findPagerJustByHql(Pager pager, String hql) throws Exception {
		// ***********************
		hql = getTenantIdCondition4Hql(hql, null, new HashMap<String, Object>());

		Query query = getSession().createQuery("select count(*) " + hql);
		int totalCount = 0;
		Object uniqueR = query.uniqueResult();
		if (null != uniqueR && !"".equals(uniqueR)) {
			totalCount = Integer.parseInt(uniqueR.toString());
		}
		pager.setTotalCount(totalCount);
		Query resultQuery = getSession().createQuery(hql);
		resultQuery.setFirstResult((pager.getPageNo() - 1) * pager.getPageSize());
		resultQuery.setMaxResults(pager.getPageSize());
		pager.setResultList(resultQuery.list());
		return pager;
	}

	private String handleWithHead(String countHqlHead, String classAlilasName, String withOutHeaderHql) {
		if (withOutHeaderHql.trim().indexOf("select") == 0) {
			String toRep = null;
			String repStr = null;
			if (withOutHeaderHql.contains("distinct")) {
				toRep = "distinct " + classAlilasName;
				repStr = "count(distinct " + classAlilasName + ".id)";
			} else {
				toRep = classAlilasName;
				repStr = "count( distinct " + classAlilasName + ".id)";
			}

			return withOutHeaderHql.replaceFirst(toRep, repStr);
			// return countHqlHead+" from ("+withOutHeaderHql+")";
		} else {
			return countHqlHead + withOutHeaderHql;
		}

	}

	@Override
	public Pager findPagerBySql(Pager pager, String sql, Map<String, Object> params) throws Exception {
		// ###############没有处理
		Query query = getSession().createSQLQuery(sql);
		if (null != params && params.keySet().size() > 0) {
			configQuery(query, params);
		}
		int totalCount = query.list().size();
		pager.setTotalCount(totalCount);
		query.setMaxResults(pager.getPageSize());
		query.setFirstResult((pager.getPageNo() - 1) * pager.getPageSize());
		pager.setResultList(query.list());
		return pager;
	}

	@Override
	public <T> boolean isEntityExist(Class<T> entityClass, String id, String attribute, String value) throws Exception {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		if (null == value || "".equals(value)) {
			return false;
		}
		StringBuilder hql = new StringBuilder("from ");
		hql.append(entityClass.getName());
		hql.append(" entity where ");

		hql.append(getTenantIdCondition4ParamHql2("entity", paramMap));

		hql.append(" and entity.");
		hql.append(attribute);
		hql.append(" = '");
		hql.append(value);
		hql.append("'");
		if (StrUtils.objectIsNotNull(id)) {
			hql.append(" and entity.id != ");
			hql.append("'" + id + "'");
		}
		List<T> list = this.findAllByHql(hql.toString(), paramMap);
		if (list.size() > 0) {
			return true;
		}
		return false;
	}

	private void configQuery(Query query, Map<String, Object> params) {
		for (String key : params.keySet()) {
			if (null != key && !"".equals(key)) {
				Object value = params.get(key);
				String[] k = key.split(",");
				if (k.length == 2) {
					if (k[0].contains(".")) {
						if (k[1].equals(SearchCondition.EQUAL) || k[1].equals(SearchCondition.MORETHAN) || k[1].equals(SearchCondition.MORETHANANDEQUAL) || k[1].equals(SearchCondition.LESSTHAN) || k[1].equals(SearchCondition.LESSTHANANDEQUAL)) {
							query.setParameter(StrUtils.fixParamForHql(k[0]), value);
						} else if (k[1].equals(SearchCondition.ANYLIKE)) {
							query.setParameter(StrUtils.fixParamForHql(k[0]), "%" + value + "%");
						} else if (k[1].equals(SearchCondition.STARTLIKE)) {
							query.setParameter(StrUtils.fixParamForHql(k[0]), "%" + value);
						} else if (k[1].equals(SearchCondition.ENDLIKE)) {
							query.setParameter(StrUtils.fixParamForHql(k[0]), value + "%");
						} else if (k[1].equals(SearchCondition.NOEQUAL)) {
							query.setParameter(StrUtils.fixParamForHql(k[0]), value);
						}
					} else {
						if (k[1].equals(SearchCondition.EQUAL) || k[1].equals(SearchCondition.MORETHAN) || k[1].equals(SearchCondition.MORETHANANDEQUAL) || k[1].equals(SearchCondition.LESSTHAN) || k[1].equals(SearchCondition.LESSTHANANDEQUAL)) {
							query.setParameter(k[0], value);
						} else if (k[1].equals(SearchCondition.ANYLIKE)) {
							System.out.println(k[0] + "--" + value);
							query.setParameter(k[0], "%" + value + "%");
						} else if (k[1].equals(SearchCondition.STARTLIKE)) {
							query.setParameter(k[0], "%" + value);
						} else if (k[1].equals(SearchCondition.ENDLIKE)) {
							query.setParameter(k[0], value + "%");
						} else if (k[1].equals(SearchCondition.NOEQUAL)) {
							query.setParameter(StrUtils.fixParamForHql(k[0]), value);
						}
					}
				} else {
					if (key.contains(".")) {
						query.setParameter(StrUtils.fixParamForHql(k[0]), "%" + value + "%");
					} else {
						// 还需要细化
						query.setParameter(key, value);
					}
				}
			}
		}

	}

	/** 处理hql Count */
	private String handleCountHql(String classAlilasName) {
		StringBuilder hqlCount = new StringBuilder();// "select
														// count("+classAlilasName
														// +".id) "
		hqlCount.append("select count(").append(classAlilasName).append(".id)  ");
		// return hql.toString();
		return hqlCount.toString();
	}

	// 替换join fetch 为join 加快数量查询的速度
	private String removeFetch(String hql) {
		return hql.replace("join fetch", "join");
	}

	// 去除order by
	private String removeOrderBy(String orgSql) {
		String sqlTemp = orgSql;
		if (orgSql.contains("order by")) {
			sqlTemp = StringUtils.substring(orgSql, 0, orgSql.lastIndexOf("order by"));
		}

		return sqlTemp;
	}

	@Override
	public void jdbcBatchUpdate(String sql, Object... params) throws Exception {
		this.jdbcTemplate.update(sql, params);
	}

	@Override
	public List<Object> findListBySql(String sql, Map<String, Object> params) throws Exception {
		Query query = getSession().createSQLQuery(sql);
		if (null != params && params.keySet().size() > 0) {
			query.setProperties(params);
		}
		return query.list();
	}

	/**
	 * 使用jdbctemplate 进行批量更新
	 */
	@Override
	public void jdbcBatchUpdate(String sql, List<Object[]> params) throws Exception {
		// ###############没有处理
		this.jdbcTemplate.batchUpdate(sql, params);
	}

	@Override
	public void evict(Object obj) {
		getSession().evict(obj);
	}

	@Override
	public void clear() {
		getSession().clear();
	}

	@Override
	public void flush() {
		getSession().flush();
	}

	private void addArgs(Map argMap, Query query) {
		if (argMap != null && !argMap.isEmpty()) {
			Iterator itKey = argMap.keySet().iterator();
			while (itKey.hasNext()) {
				String key = (String) itKey.next();
				Object arg = argMap.get(key);
				if (argMap.get(key) instanceof List) {
					query.setParameterList(key, (List) argMap.get(key));
				} else {
					query.setParameter(key, argMap.get(key));
				}
			}
		}

	}

	/**
	 * 设定对象的tenantId
	 * 
	 * @param obj
	 */
	private void saveTenantIdHbm(Object obj) {
		String className = obj.getClass().getName();
		if (StringUtils.contains(className, "_")) {
			className = StringUtils.substringBefore(className, "_");
		}
		ClassMetadata meta = getSession().getSessionFactory().getClassMetadata(className);
		Type type = meta.getPropertyType(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID);
		if (type != null) {
			String tenantId = getTenantId();

			// if(StringUtils.isEmpty(tenantId))
			// tenantId= "1";

			if (StringUtils.isEmpty(tenantId)) {
				Assert.notNull(tenantId, "当前用户没有承租户ID！");
			}

			meta.setPropertyValue(obj, BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID, tenantId);
		}
	}

	/**
	 * 保存CompanyInnerCode
	 * 
	 * @param obj
	 */
	private void saveCompanyInnerCodeHbm(Object obj) {
		String className = obj.getClass().getName();
		if (StringUtils.contains(className, "_")) {
			className = StringUtils.substringBefore(className, "_");
		}

		/**/
		ClassMetadata meta = getSession().getSessionFactory().getClassMetadata(className);
		Type type = meta.getPropertyType(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_COMPANYINNERCODE);
		if (type != null) {
			// 查看对象中是否存在 部门编码的参数
			// Object cpInnerCodeParam =
			// meta.getPropertyValue(obj,"proxyCompanyInnerCode");
			Object cpInnerCodeParam = BeanUtil.getPropertySilently(obj, "proxyCompanyInnerCode");

			String cpInnerCodeStr = null;
			if (cpInnerCodeParam != null && StrUtils.isNotEmpty(cpInnerCodeParam.toString())) {
				cpInnerCodeStr = cpInnerCodeParam.toString();
			} else {
				// 如果不存在代理信息 则 直接读取自己的部门信息的公司信息
				cpInnerCodeStr = getCompanyInnerCode();
			}

			if (StringUtils.isEmpty(cpInnerCodeStr)) {
				Assert.notNull(cpInnerCodeStr, "当前用户没有companyInnerCode！");
			}
			meta.setPropertyValue(obj, BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_COMPANYINNERCODE, cpInnerCodeStr);
		}
		/*
		 * ClassMetadata meta =
		 * getSession().getSessionFactory().getClassMetadata(className); Type
		 * type = meta.getPropertyType(BizConstant.
		 * COMMON_GLOBAL_FLAG_HQL_PROPERTY_COMPANYINNERCODE); if(type!=null){
		 * String cpInnerCodeStr = getCompanyInnerCode();
		 * if(StringUtils.isEmpty(cpInnerCodeStr)){
		 * Assert.notNull(cpInnerCodeStr, "当前用户没有companyInnerCode！"); }
		 * meta.setPropertyValue(obj,
		 * BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_COMPANYINNERCODE,
		 * cpInnerCodeStr); }
		 */
	}

	/**
	 * 保存对象的 部门编码
	 */
	private void saveDepartmentCodeHbm(Object obj) {
		String className = obj.getClass().getName();
		if (StringUtils.contains(className, "_")) {
			className = StringUtils.substringBefore(className, "_");
		}
		ClassMetadata meta = getSession().getSessionFactory().getClassMetadata(className);
		// Type type =
		// meta.getPropertyType(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_DEPARTMENTCODE);
		// if(type!=null){
		Boolean hasPropertyTypeConfig = HqlUtil.hasPropertyInHbmXml(meta, BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_DEPARTMENTCODE);
		if (hasPropertyTypeConfig) {
			// 存在部门编码属性
			// 查看对象中是否存在 部门编码的参数
			// Object dpCodeParam =
			// meta.getPropertyValue(obj,"proxyDepartmentCode");
			Object dpCodeParam = BeanUtil.getPropertySilently(obj, "proxyDepartmentCode");
			String dpCodeStr = null;
			if (dpCodeParam != null && StrUtils.isNotEmpty(dpCodeParam.toString())) {
				dpCodeStr = dpCodeParam.toString();
			} else {
				// 如果不存在代理信息 则 直接读取自己的部门信息
				dpCodeStr = getDepartmentCode();
			}

			if (StrUtils.isNotEmpty(dpCodeStr)) {
				meta.setPropertyValue(obj, BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_DEPARTMENTCODE, dpCodeStr);
			}
		}
	}

	/**
	 * @Title: save4UUID @Description: 设置UUID @param @param obj 设定文件 @return
	 *         void 返回类型 @throws
	 */
	/*
	 * private void save4UUID(Object obj) {
	 * 
	 * String className = obj.getClass().getName();
	 * if(StringUtils.contains(className, "_")){ className =
	 * StringUtils.substringBefore(className,"_"); } ClassMetadata meta =
	 * getSession().getSessionFactory().getClassMetadata(className); Boolean
	 * hasUUID = HqlUtil.hasPropertyInHbmXml(meta, "uuid"); if(hasUUID){ Object
	 * uuidValue = BeanUtil.getPropertySilently(obj, "uuid"); if(uuidValue
	 * !=null && StringUtils.isNotEmpty(uuidValue.toString())){ //String uuidStr
	 * = UuidCoreUtils.compressedUuid(); meta.setPropertyValue(obj, "uuid",
	 * UuidCoreUtils.compressedUuid()); }
	 * 
	 * }
	 * 
	 * Object uuidValue = BeanUtil.getPropertySilently(obj, "uuid"); //
	 * System.out.println(uuidValue.toString()); if (uuidValue == null ||
	 * StringUtils.isEmpty(uuidValue.toString())) { // String uuidStr =
	 * UuidCoreUtils.compressedUuid(); // meta.setPropertyValue(obj, "uuid",
	 * UuidCoreUtils.compressedUuid()); BeanUtil.setPropertySilent(obj, "uuid",
	 * UuidCoreUtils.compressedUuid()); } //
	 * System.out.println(BeanUtil.getPropertySilently(obj, "uuid")); }
	 */

	/**
	 * 判断是否有tenantId属性
	 * 
	 * @param meta
	 * @param obj
	 * @return
	 */
	private Boolean findHasTenantIdHbm(ClassMetadata meta, Object obj) {
		// ClassMetadata meta =
		// getSession().getSessionFactory().getClassMetadata(obj.getClass());
		Type type = meta.getPropertyType(BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID);
		if (type != null) {
			// meta.setPropertyValue(obj,
			// BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID,
			// SecurityUtil.getCurrentUserAccount().getTenantId());
			return true;
		}
		return false;
	}

	/**
	 * 设置tenantId值
	 * 
	 * @param obj
	 * @param tenantId
	 * 
	 *            private void saveTenantIdHbm(ClassMetadata meta,Object
	 *            obj,String tenantId){ //if(findHasTenantIdHbm(meta, obj)){
	 *            meta.setPropertyValue(obj,
	 *            BizConstant.COMMON_GLOBAL_FLAG_HQL_PROPERTY_TENANTID,
	 *            SecurityUtil.getCurrentUserAccount().getTenantId()); //} }
	 */

	/**
	 * 查找hql别名
	 * 
	 * @param hql
	 * @return
	 */
	private String getHqlAlilasName(String hqlTrimLower) {
		// String hqlTrimLower = hql.trim().toLowerCase();
		if (hqlTrimLower.startsWith("select")) {
			Pattern p = Pattern.compile("select.+?from+", Pattern.CASE_INSENSITIVE);// ,Pattern.CASE_INSENSITIVE

			Matcher m = p.matcher(hqlTrimLower);
			String alilasName = null;
			while (m.find()) {
				alilasName = m.group();
				break;
			}
			// System.out.println(alilasName.toLowerCase().replaceFirst("select",
			// "").replaceFirst("from", "").trim());
			return alilasName.replaceFirst("select", "").replaceFirst("from", "").trim();
		}
		return null;
	}

	/**
	 * 查询hql 的添加去重
	 * 
	 * @param hql
	 * @param alilasName
	 * @return
	 */
	private String getFindHqlDis(String hql, String alilasName) {
		int fromPos = StringUtils.indexOfIgnoreCase(hql, "from");
		// System.out.println(fromPos);
		String subHql = StringUtils.substring(hql, fromPos - 1, hql.length());
		// System.out.println(subHql);
		subHql = StringUtils.replace(subHql, "distinct", "");
		hql = "select distinct " + alilasName + subHql;
		return hql;
	}

	/**
	 * 手动处理hql拦截
	 * 
	 * @param hql
	 * @param entityClass
	 * @param params
	 * @return
	 */
	@Override
	public String beforeHqlExecute(String hql, String entityClassName, Map<String, Object> params) {

		// 如果params为null，下面的赋值无法传递会父级方法（仍然为null）
		Assert.isTrue(params != null, "getTenantIdCondition4Hql传入参数paramMap不能为null！");

		// 拦截处理
		Object userMetadataObj = ContextUtil.getSessionAttr(SecurityScope.METADATA_USER_OBJ);
		if (userMetadataObj != null) {
			StringBuilder oldSb = new StringBuilder(hql);

			// 找到别名
			String replaceAlilasName = getHqlAlilasName(hql);

			Map<String, DataResRowPolicyObj> userHqlObjMap = (Map<String, DataResRowPolicyObj>) userMetadataObj;
			// String tokenKey = entityClass.getName();
			String tokenKey = entityClassName;
			if (userHqlObjMap.containsKey(tokenKey)) {
				DataResRowPolicyObj plyObj = userHqlObjMap.get(tokenKey);

				// hql
				if (StrUtils.isNotEmpty(plyObj.getWheres())) {
					oldSb.append(plyObj.getWheres());
				}

				// 参数 方法的参数
				// Object[] oldParam2 = methodInvocation.getArguments();
				/*
				 * Map<String,Object> paramObjMap=null;; if(paramObjMap==null){
				 * paramObjMap = new HashMap<String, Object>(); } params
				 */

				String paramMapStr = plyObj.getParamsMap();
				if (StringUtils.isNotEmpty(paramMapStr)) {
					ConcurrentMap<String, Object> paramMap = JSonUtils.readValue(paramMapStr, ConcurrentHashMap.class);

					for (ConcurrentMap.Entry<String, Object> entry : paramMap.entrySet()) {
						String key = entry.getKey();
						String keyVal = ":" + key;
						if (oldSb.indexOf(keyVal) == -1) {
							paramMap.remove(key);
						}
					}
					// 得到约束的map

					// 2015-05-19 ！！！！！ 这里需要判断是否为空吗？&& !paramMap.isEmpty()
					if (paramMap != null) {
						/*
						 * Map<String,Object> paramObjMap =
						 * (Map<String,Object>)paramObj;
						 * paramObjMap.putAll(paramMap);
						 */
						// paramObjMap.putAll(paramMap);
						params.putAll(paramMap);
					}

				}

				DataRowConstant.handleValueType(params);

				// 2 处理业务对象属性（集合）类型的约束
				String bizJsonStr = plyObj.getBizAndSetParamsJson();
				if (StringUtils.isNotEmpty(bizJsonStr)) {
					DataResRowBizProperty dataResRowBizProperty = JSonUtils.readValue(bizJsonStr, DataResRowBizProperty.class);

					String masterAlilasName = dataResRowBizProperty.getMasterAlilasName();
					String bizAlilasName = dataResRowBizProperty.getBizAlilasName();
					String bizProperty = dataResRowBizProperty.getBizProperty();

					boolean isBizType = dataResRowBizProperty.isBizType();
					String innerJoinBizCondition = dataResRowBizProperty.getInnerJoinBizCondition();
					// String op = dataResRowBizProperty.getOp();
					String ruleOp = dataResRowBizProperty.getOp();

					String hqlBizCondition = dataResRowBizProperty.getHqlBizCondition();
					// String hqlBizSetCondition =
					// dataResRowBizProperty.getHqlBizSetCondition();
					String bizHqlMapStr = plyObj.getBizHqlMap();

					oldSb.append(" ").append(ruleOp);// op

					// 处理嵌入hql问题
					hqlBizCondition = handlerBizHqlCondition(hqlBizCondition, bizHqlMapStr, params);

					// {"bizAlilasName":"subOrgPositions","bizProperty":"subOrgPositions",
					// "bizType":false,"hqlBizCondition":" in ( 7,8 )
					// ","hqlBizSetCondition":" in (
					// 7,8 ) ",
					// "innerJoinBizCondition":" orgPosition.subOrgPositions ",
					// "masterAlilasName":"orgPosition","op":"and"}

					int length = oldSb.length();
					int joinIndex = oldSb.indexOf(innerJoinBizCondition);
					if (joinIndex != -1) {
						// 集合和业务对象类型 因为原sql中已经包含此inner join 都需要需要截取其别名
						// 并在where后添加条件
						// System.out.println(sb.substring(joinIndex, length));
						// System.out.println(sb.substring(joinIndex+innerTest.length(),
						// length));
						String objAlilasNameStr = oldSb.substring(joinIndex + innerJoinBizCondition.length(), length);

						String objAlilasNameStrTrim = objAlilasNameStr.trim();
						// System.out.println(objAlilasNameStrTrim);

						int firstBlanIndex = objAlilasNameStrTrim.indexOf(" ");
						// System.out.println(firstBlanIndex);
						// System.out.println("alilasName:"+StringUtils.substring(objAlilasNameStrTrim,
						// 0, firstBlanIndex));
						String oldAlilasName = StringUtils.substring(objAlilasNameStrTrim, 0, firstBlanIndex);

						oldSb.append(" ").append(oldAlilasName).append(".id ").append(hqlBizCondition);
					} else {
						if (!isBizType) {
							// 集合类型 因为原sql中不含此inner join 需要where前 插入inner join
							// 并在where后添加条件

							String innerJoinHql = " inner join " + masterAlilasName + "." + bizProperty + " " + bizAlilasName;

							int whereIndex = oldSb.indexOf(" where ");
							oldSb.insert(whereIndex, innerJoinHql);

							oldSb.append(" ").append(bizAlilasName).append(".id ").append(hqlBizCondition);// hqlBizSetCondition
						} else {
							// 业务对象类型 因为原sql中不含此inner join 所以可以直接在 where后添加条件
							oldSb.append(" ").append(masterAlilasName).append(".").append(bizAlilasName).append(".id ").append(hqlBizCondition);// hqlBizSetCondition

						}
					}
					// System.out.println(oldSb);
				}
			}

			// 替换
			String resStr = StringUtils.replace(oldSb.toString(), "entityAsName", replaceAlilasName);

			return resStr;
		} else {
			return hql;
		}
	}

	private String handlerBizHqlCondition(String bizCondition, String bizHqlMapStr, Map<String, Object> paramMap) {

		String[] bizHqlMapStrArray = bizHqlMapStr.split("\\,");
		for (String oneParam : bizHqlMapStrArray) {

			// String sysValKey = oneParam;
			String replaceKeyVal = DataRowConstant.sysparamValueMap.get(oneParam);
			bizCondition = StringUtils.replaceOnce(bizCondition, oneParam, replaceKeyVal);
			paramMap.put(replaceKeyVal.substring(1), HandleVixSecurityContext.getContextValue(oneParam));
		}
		return bizCondition;
	}

	// jdbc api
	@Override
	public <T extends Object> List<T> queryObjectListBySql(String sql, Class T, Object[] values) throws JDBCException {
		return this.jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(T), values);
	}

	@Override
	public <T extends Object> T queryObjectUniqueBySql(String sql, Class T, Object[] values) throws JDBCException {
		List<T> objList = queryObjectListBySql(sql, T, values);
		if (objList != null) {
			if (objList.size() == 1) {
				return objList.get(0);
			} else if (objList.size() > 1) {
				throw new NonUniqueObjectException(T, "数据不唯一！");
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	public <T extends Object> List<T> queryObjectList(String sql, Class T, int pageNo, int pageSize, Object[] values) throws JDBCException {
		StringBuilder sb = new StringBuilder();
		/*
		 * oracle sb.append("select * FROM (");
		 * sb.append("select temp.*,ROWNUM num FROM ( "); sb.append(sql); int
		 * frist = ((pageNo - 1) * pageSize) + 1;
		 * sb.append(") temp where ROWNUM<").append(frist+pageSize);
		 * sb.append(" ) where num > ").append(frist==1?0:frist-1);
		 */
		sb.append(sql);
		int frist = ((pageNo - 1) * pageSize) + 1;
		sb.append(" limit ").append(frist == 1 ? 0 : frist - 1);
		sb.append(",").append(pageSize);
		List<T> list = this.jdbcTemplate.query(sb.toString(), ParameterizedBeanPropertyRowMapper.newInstance(T), values);
		return list;
	}

	@Override
	public <T extends Object> Pager queryPagerListBySql(Pager pager, Class T, String sql, String countSql, Object[] values) {

		int totalCount = 0;
		if (StringUtils.isEmpty(countSql)) {
			totalCount = this.countSqlResult(sql, values);
		} else {
			totalCount = this.countSqlResult(countSql, values);
		}

		pager.setTotalCount(totalCount);

		List<T> list = queryObjectList(sql, T, pager.getPageNo(), pager.getPageSize(), values);
		// resultQuery.setFirstResult((pager.getPageNo() - 1) *
		// pager.getPageSize());
		pager.setResultList(list);
		return pager;
	}

	/** 查询数量 去除order by 后的子句 */
	private int countSqlResult(String sql, Object[] values) throws JDBCException {
		String countSql = sql;

		if (!StringUtils.startsWithIgnoreCase(sql, "select count")) {
			countSql = prepareCountSql(sql);
		}
		try {
			// int count = this.jdbcTemplate.queryForInt(countSql, values);
			int count = this.jdbcTemplate.queryForObject(countSql, Integer.class, values);
			return count;
		} catch (Exception e) {
			throw new RuntimeException("sql can't be auto count,sql is:" + countSql, e);
		}
	}

	/** 去除order by */
	private String prepareCountSql(String originalSql) {
		String sqlTemp = originalSql;
		if (StringUtils.containsIgnoreCase(originalSql, "order by")) {
			sqlTemp = StringUtils.substring(originalSql, 0, originalSql.lastIndexOf("order by"));
		}
		String countHql = "select count(1) from ( " + sqlTemp + " )  AS CT ";
		return countHql;
	}

	@Override
	public <T> T queryForObject(String sql, Class<T> T, Object[] values) throws JDBCException {
		try {
			T obj = this.jdbcTemplate.queryForObject(sql, T, values);
			return obj;
		} catch (Exception e) {
			throw new RuntimeException("SQL QUERY ERROR:" + sql, e);
		}
	}

	/** 根据条件查询数据条数 */
	@Override
	public <T> long findDataCountByHqlConditions(String hql, Map<String, Object> params) throws Exception {
		Query query = getSession().createQuery("select count(id) " + hql);
		if (null != params && params.keySet().size() > 0) {
			configQuery(query, params);
		}

		Object uniqueR = query.uniqueResult();
		if (null != uniqueR && !"".equals(uniqueR)) {
			return (Long) uniqueR;
		} else {
			return 0L;
		}
	}
	@Override
	public <T> T findEntityByAttribute(Class<T> entityClass, String attribute, Object value, String appId) throws Exception {
		StringBuilder hql = new StringBuilder(" from ");
		hql.append(entityClass.getName());
		Map<String, Object> params = new HashMap<String, Object>();
		if (null != attribute && null != value) {
			params.put("appId", appId);
			hql.append(" entity where entity.appId = :appId and entity.");
			hql.append(attribute);
			hql.append(" = :");
			if (attribute.contains(".")) {
				hql.append(attribute.split("\\.")[0]);
				params.put(attribute.split("\\.")[0], value);
			} else {
				hql.append(attribute);
				params.put(attribute, value);
			}
		}
		return (T) findObjectByHql(hql.toString(), params);
	}
}