package com.vix.common.org.hql;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.vix.common.org.entity.OrgView;
import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.web.Pager;
import com.vix.hr.position.entity.OrgPositionView;

@Component
// @Scope("prototype")
public class BusinessOrgHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "businessOrg";
	}

	public StringBuilder findBusinessOrgList4Page(Map<String, Object> params, Pager pager) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from BusinessOrg ").append(ename);
		hql.append(" left join fetch ").append(ename).append(".subBusinessOrgs ");
		// where emps.id=:empId
		hql.append(" where 1=1 ");

		if (params != null) {
			if (params.containsKey("bizOrgViewId") || params.containsKey("bizOrgId")) {
				// Object orgId = params.get("orgId");
				// Object orgUnitId = params.get("orgUnitId");

				if (params.containsKey("bizOrgViewId")) {
					hql.append(" and ").append(ename).append(".businessView.id = :bizOrgViewId and ").append(ename).append(".parentBusinessOrg is null ");
				}
				if (params.containsKey("bizOrgId")) {
					hql.append(" and ").append(ename).append(".parentBusinessOrg.id = :bizOrgId ");
				}
			} else if (params.containsKey("businessName")) {
				hql.append(" and ").append(ename).append(".businessOrgName like :businessName ");
			}
		}

		buildOrderQl(ename, hql, pager);
		return hql;
	}

	public StringBuilder findBusinessOrgList(Map<String, Object> params, Pager pager) {
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from BusinessOrg ").append(ename);
		hql.append(" left join fetch ").append(ename).append(".subBusinessOrgs ");
		// where emps.id=:empId
		hql.append(" where 1=1 ");

		if (params != null) {
			if (params.containsKey("businessId")) {
				Object orgId = params.get("businessId");
				if (orgId == null) {
					hql.append(" and ").append(ename).append(".parentBusinessOrg.id is null");
					params.remove("businessId");
				} else {
					hql.append(" and ").append(ename).append(".parentBusinessOrg.id = :businessId ");
				}

			} else if (params.containsKey("businessOrgName")) {
				hql.append(" and ").append(ename).append(".businessOrgName like :businessOrgName ");
			}
		}

		buildOrderQl(ename, hql, pager);
		return hql;
	}

	public StringBuilder findBusinessOrgViewList(Map<String, Object> params) {
		String entityAsName = entityAsName();
		// StringBuilder hqlBuilder =new StringBuilder();
		StringBuilder hqlBuilder = new StringBuilder();
		hqlBuilder.append("select ").append(entityAsName);
		hqlBuilder.append(" from ");

		hqlBuilder.append(OrgView.class.getName()).append(" ").append(entityAsName);
		hqlBuilder.append(" where ");
		String parentId = (String) params.get("parentId");
		if (StringUtils.isNotEmpty(parentId)) {
			hqlBuilder.append(entityAsName).append(".parentId = :parentId");
		} else {
			hqlBuilder.append(entityAsName).append(".parentId is null ");
			params.remove("parentId");
		}

		if (params.containsKey("unitType")) {
			hqlBuilder.append(" and ( ");

			hqlBuilder.append(entityAsName).append(".orgType = 'C' ");
			hqlBuilder.append(" or (");
			hqlBuilder.append(entityAsName).append(".orgType = 'O' ");
			// hqlBuilder.append(" and ").append(entityAsName).append(".orgUnitType in (
			// :unitType ) ");
			hqlBuilder.append(" and ").append(entityAsName).append(".orgUnitType = :unitType  ");

			hqlBuilder.append(")");

			hqlBuilder.append(" ) ");

		}
		return hqlBuilder;
	}

	// 获取部门岗位视图
	public StringBuilder findOrgPositionViewList(Map<String, Object> params) {
		String entityAsName = "orgPositionView";
		// StringBuilder hqlBuilder =new StringBuilder();
		StringBuilder hqlBuilder = new StringBuilder();
		hqlBuilder.append("select ").append(entityAsName);
		hqlBuilder.append(" from ");

		hqlBuilder.append(OrgPositionView.class.getName()).append(" ").append(entityAsName);
		hqlBuilder.append(" where ");
		String parentId = (String) params.get("parentId");
		if (StringUtils.isNotEmpty(parentId)) {
			hqlBuilder.append(entityAsName).append(".parentId = :parentId");
		} else {
			hqlBuilder.append(entityAsName).append(".parentId is null ");
			params.remove("parentId");
		}

		if (params.containsKey("unitType")) {
			hqlBuilder.append(" and ( ");

			hqlBuilder.append(entityAsName).append(".orgType = 'C' ");
			hqlBuilder.append(" or (");
			hqlBuilder.append(entityAsName).append(".orgType = 'O' ");
			hqlBuilder.append(" or (");
			hqlBuilder.append(entityAsName).append(".orgType = 'P' ");
			// hqlBuilder.append(" and ").append(entityAsName).append(".orgUnitType in (
			// :unitType ) ");
			hqlBuilder.append(" and ").append(entityAsName).append(".orgUnitType = :unitType  ");

			hqlBuilder.append(")");
			hqlBuilder.append(")");

			hqlBuilder.append(" ) ");

		}
		return hqlBuilder;
	}

	public StringBuilder findBusinessOrgViewList4CmnSelect(Map<String, Object> params) {
		String entityAsName = entityAsName();
		// StringBuilder hqlBuilder =new StringBuilder();
		StringBuilder hqlBuilder = new StringBuilder();
		hqlBuilder.append("select ").append(entityAsName);
		hqlBuilder.append(" from ");

		hqlBuilder.append(OrgView.class.getName()).append(" ").append(entityAsName);
		hqlBuilder.append(" where ");
		String parentId = (String) params.get("parentId");
		if (StringUtils.isNotEmpty(parentId)) {
			hqlBuilder.append(entityAsName).append(".parentId = :parentId");
		} else {
			hqlBuilder.append(entityAsName).append(".parentId is null ");
			params.remove("parentId");
		}

		if (params.containsKey("unitType")) {
			hqlBuilder.append(" and ( ");

			hqlBuilder.append(entityAsName).append(".orgType = 'C' ");
			hqlBuilder.append(" or (");
			hqlBuilder.append(entityAsName).append(".orgType = 'O' ");
			hqlBuilder.append(" or ").append(entityAsName).append(".orgUnitType in ( :unitType ) ");
			// hqlBuilder.append(" and ").append(entityAsName).append(".orgUnitType =
			// :unitType ");

			hqlBuilder.append(")");

			hqlBuilder.append(" ) ");

		}
		return hqlBuilder;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
