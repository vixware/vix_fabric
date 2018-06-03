package com.vix.oa.administrativeOfficeCenter.hql;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;

@Component
@Scope("prototype")
public class AdministrativeHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "hentity";
	}

	public StringBuilder findTemplateAndOrganizationUnit(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from TemplateAndOrganizationUnit ").append(ename);
		hql.append(" where 1=1  and type ='1' ");
		if (params != null) {
			if (params.containsKey("roleId")) {
				String roleId = (String) params.get("roleId");
				if (roleId == null) {
					hql.append(" and ").append(ename).append(".roleId is null ");
					params.remove("roleId");
				} else {
					hql.append(" and ").append(ename).append(".roleId ='" + roleId + "' ");
				}
			}
			if (params.containsKey("businessFormId")) {
				String businessFormId = (String) params.get("businessFormId");
				if (businessFormId == null) {
					hql.append(" and ").append(ename).append(".businessFormId is null ");
					params.remove("businessFormId");
				} else {
					hql.append(" and ").append(ename).append(".businessFormId ='" + businessFormId + "' ");
				}
			}
		} else {
			
		}
		return hql;
	}

	public StringBuilder findEmpTemplateAndOrganizationUnit(Map<String, Object> params) {
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from TemplateAndOrganizationUnit ").append(ename);
		hql.append(" where 1=1   and type ='2' ");
		if (params != null) {
			if (params.containsKey("empId")) {
				String empId = (String) params.get("empId");
				if (empId == null) {
					hql.append(" and ").append(ename).append(".empId is null ");
					params.remove("empId");
				} else {
					hql.append(" and ").append(ename).append(".empId ='" + empId + "' ");
				}
			}
			if (params.containsKey("businessFormId")) {
				String businessFormId = (String) params.get("businessFormId");
				if (businessFormId == null) {
					hql.append(" and ").append(ename).append(".businessFormId is null ");
					params.remove("businessFormId");
				} else {
					hql.append(" and ").append(ename).append(".businessFormId ='" + businessFormId + "' ");
				}
			}
		} else {
			
		}
		return hql;
	}

}
