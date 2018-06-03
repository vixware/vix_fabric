package com.vix.system.orginialBillTypeManagement.hql;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.web.Pager;

@Component
@Scope("prototype")
public class OrginialBillTypeHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "billsCategory";
	}

	public StringBuilder findBillsPropertyList(Map<String, Object> params) {
		String ename = "billsProperty";
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from OrginialBillsProperty ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("billsCategoryId")) {
				Object billsCategoryId = params.get("billsCategoryId");
				if (billsCategoryId == null) {
					hql.append(" and ").append(ename).append(".orginialBillsCategory.id is null ");
					params.remove("billsCategoryId");
				} else {
					hql.append(" and ").append(ename).append(".orginialBillsCategory.id = :billsCategoryId ");
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
		hql.append(" from OrginialBillsType ").append(ename);
		hql.append(" where 1=1 ");

		if (params != null) {
			if (params.containsKey("billsPropertyId")) {
				Object billsCategoryId = params.get("billsPropertyId");
				if (billsCategoryId == null) {
					hql.append(" and ").append(ename).append(".orginialBillsProperty.id is null ");
					params.remove("billsPropertyId");
				} else {
					hql.append(" and ").append(ename).append(".orginialBillsProperty.id = :billsPropertyId ");
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
		hql.append(" from OrginialBillsType ").append(ename);
		hql.append(" where 1=1 ");

		if (params != null) {
			if (params.containsKey("billsPropertyId")) {
				Object orgId = params.get("billsPropertyId");
				if (orgId == null) {
					hql.append(" and ").append(ename).append(".orginialBillsProperty.id is null");
					params.remove("billsPropertyId");
				} else {
					hql.append(" and ").append(ename).append(".orginialBillsProperty.id = :billsPropertyId ");
				}
			}
		} else {
			
		}

		buildOrderQl(ename, hql, pager);
		return hql;
	}

}
