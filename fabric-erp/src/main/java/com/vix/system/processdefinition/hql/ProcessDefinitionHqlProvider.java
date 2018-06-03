package com.vix.system.processdefinition.hql;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.web.Pager;
import com.vix.system.billTypeManagement.entity.BillsCategory;

@Component
@Scope("prototype")
public class ProcessDefinitionHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "billsCategory";
	}

	public StringBuilder findBillsCategoryListByParams(Map<String, Object> params, String orderField, String orderBy, String extHql) {
		String entityAsName = entityAsName();
		StringBuilder joinHql = new StringBuilder();
		joinHql.append(" left join fetch ").append(entityAsName).append(".subBillsCategorys ");
		StringBuilder hql = generatorHql(BillsCategory.class, params, orderField, orderBy, extHql, joinHql.toString());
		return hql;
	}

	/**
	 * 
	 * @param params
	 * @param orderField
	 * @param orderBy
	 * @param extHql
	 * @return
	 */
	public StringBuilder findBillsCategoryList(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from BillsCategory ").append(ename);
		if (params != null) {
			if (params.containsKey("orgId")) {
				Object orgId = params.get("orgId");
				if (orgId == null) {
				} else {
					hql.append(",Organization organization where ").append(ename).append(".companyCode = organization.companyCode").append(" and organization.id = :orgId ");
				}
			}
		} else {

		}

		return hql;
	}

	public StringBuilder findBillsTypeList(Map<String, Object> params) {
		String ename = "billsType";
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from Procedure ").append(ename);
		/*
		 * hql.append(" left join fetch ").append(ename).
		 * append(".subBillsTypes " );
		 */
		hql.append(" where 1=1 ");

		if (params != null) {
			if (params.containsKey("billsCategoryId")) {
				Object billsCategoryId = params.get("billsCategoryId");
				if (billsCategoryId == null) {
					hql.append(" and ").append(ename).append(".billsCategory.id is null ");
					params.remove("billsCategoryId");
				} else {
					hql.append(" and ").append(ename).append(".billsCategory.id = :billsCategoryId ");
				}
			}
		} else {

		}

		return hql;
	}

	public StringBuilder findProcedureHql(Map<String, Object> params, Pager pager) {
		StringBuilder hql = new StringBuilder();
		String ename = "procedure";
		hql.append("select ").append(ename);
		hql.append(" from Procedure ").append(ename);
		hql.append(" left join fetch ").append(ename).append(".subBillsTypes ");
		hql.append(" where 1=1 ");

		if (params != null) {
			if (params.containsKey("billsCategoryId")) {
				Object orgId = params.get("billsCategoryId");
				if (orgId == null) {
					hql.append(" and ").append(ename).append(".billsCategory.id is null");
					params.remove("billsCategoryId");
				} else {
					hql.append(" and ").append(ename).append(".billsCategory.id = :billsCategoryId ");
				}

			} else if (params.containsKey("orgName")) {
				hql.append(" and ").append(ename).append(".orgName like :orgName ");
			}
		}

		buildOrderQl(ename, hql, pager);
		return hql;
	}
}
