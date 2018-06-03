package com.vix.system.billTypeManagement.hql;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.web.Pager;
import com.vix.system.billTypeManagement.entity.BillsCategory;

@Component
@Scope("prototype")
public class BillTypeManagementHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "billsCategory";
	}

	public StringBuilder findBillsCategoryListByParams(Map<String, Object> params, String orderField, String orderBy, String extHql) {
		StringBuilder joinHql = new StringBuilder();
		StringBuilder hql = generatorHql(BillsCategory.class, params, orderField, orderBy, extHql, joinHql.toString());
		return hql;
	}

	public StringBuilder findBillsCategoryByCompanyCodeAndCategoryCode(String companyInnerCode, String categoryCode) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from BillsCategory ").append(ename).append(" where ").append(ename).append(".companyInnerCode='").append(companyInnerCode).append("' and ").append(ename).append(".categoryCode='").append(categoryCode).append("'");
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
					hql.append(",Organization organization where ").append(ename).append(".companyInnerCode = organization.companyInnerCode").append(" and organization.id = :orgId ");
				}
			}
		} else {
			
		}

		return hql;
	}

	public StringBuilder findBillsPropertyList(Map<String, Object> params) {
		String ename = "billsProperty";
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from BillsProperty ").append(ename);
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

	public StringBuilder findBillsTypeList(Map<String, Object> params) {
		String ename = "billsType";
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from BillsType ").append(ename);
		hql.append(" where 1=1 ");

		if (params != null) {
			if (params.containsKey("billsPropertyId")) {
				Object billsCategoryId = params.get("billsPropertyId");
				if (billsCategoryId == null) {
					hql.append(" and ").append(ename).append(".billsProperty.id is null ");
					params.remove("billsPropertyId");
				} else {
					hql.append(" and ").append(ename).append(".billsProperty.id = :billsPropertyId ");
				}
			}
		} else {
			
		}

		return hql;
	}

	public StringBuilder findOrgPager(Map<String, Object> params, Pager pager) {
		StringBuilder hql = new StringBuilder();
		String ename = "billsType";
		hql.append("select ").append(ename);
		hql.append(" from BillsType ").append(ename);
		hql.append(" where 1=1 ");

		if (params != null) {
			if (params.containsKey("billsPropertyId")) {
				Object orgId = params.get("billsPropertyId");
				if (orgId == null) {
					hql.append(" and ").append(ename).append(".billsProperty.id is null");
					params.remove("billsPropertyId");
				} else {
					hql.append(" and ").append(ename).append(".billsProperty.id = :billsPropertyId ");
				}

			} else if (params.containsKey("orgName")) {
				hql.append(" and ").append(ename).append(".orgName like :orgName ");
			}
		} else {
			
		}

		buildOrderQl(ename, hql, pager);
		return hql;
	}

}
