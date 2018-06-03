package com.vix.crm.business.hql;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;

@Component
@Scope("prototype")
public class OrderManagementHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "hentity";
	}

	public StringBuilder findCustomerHql(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select * ");
		hql.append(" from E_CUSTOMER ").append(ename);
		hql.append(" where 1=1 group by ").append(ename).append(".customerName ");
		if (params != null) {
			if (params.containsKey("mobile")) {
				String mobile = (String) params.get("mobile");
				if (mobile == null) {
					params.remove("mobile");
				} else {
					hql.append(",").append(ename).append(".").append(mobile);
				}
			}
			if (params.containsKey("tel")) {
				String tel = (String) params.get("tel");
				if (tel == null) {
					params.remove("tel");
				} else {
					hql.append(",").append(ename).append(".").append(tel);
				}
			}
		}
		hql.append(" having count(*) >= 2 ");
		return hql;
	}

	public StringBuilder findCustomerAccount(Map<String, Object> params) {

		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from CustomerAccount ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("name")) {
				Object name = params.get("name");
				if (name == null) {
					hql.append(" and ").append(ename).append(".name is null ");
					params.remove("name");
				} else {
					hql.append(" and ").append(ename).append(".name = :name ");
				}
			}
			if (params.containsKey("mobilePhone")) {
				Object mobilePhone = params.get("mobilePhone");
				if (mobilePhone == null) {
					hql.append(" and ").append(ename).append(".mobilePhone is null ");
					params.remove("mobilePhone");
				} else {
					hql.append(" and ").append(ename).append(".mobilePhone = :mobilePhone ");
				}
			}

			if (params.containsKey("addr")) {
				Object addr = params.get("addr");
				if (addr == null) {
					hql.append(" and ").append(ename).append(".address is null ");
					params.remove("addr");
				} else {
					hql.append(" and ").append(ename).append(".address = :addr ");
				}
			}
			if (params.containsKey("tenantId")) {
				Object tenantId = params.get("tenantId");
				if (tenantId == null) {
					hql.append(" and ").append(ename).append(".tenantId is null ");
					params.remove("tenantId");
				} else {
					hql.append(" and ").append(ename).append(".tenantId = :tenantId ");
				}
			}
			if (params.containsKey("companyInnerCode")) {
				Object companyInnerCode = params.get("companyInnerCode");
				if (companyInnerCode == null) {
					hql.append(" and ").append(ename).append(".companyInnerCode is null ");
					params.remove("companyInnerCode");
				} else {
					hql.append(" and ").append(ename).append(".companyInnerCode = :companyInnerCode ");
				}
			}
		}
		return hql;
	}
}
