package com.vix.common.org.hql;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.vix.common.org.entity.OrganizationUnit;
import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.web.Pager;

@Component
//@Scope("prototype")
public class OrganizationUnitHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "organizationUnit";
	}

	public StringBuilder findOrgUnitList(Map<String, Object> params, Pager pager) {
		/*
		 * String entityAsName = entityAsName(); //StringBuilder hqlBuilder =new
		 * StringBuilder(); StringBuilder joinHql =new StringBuilder();
		 * joinHql.append(" left join fetch ").append(entityAsName).append(
		 * ".subOrganizationUnits ");
		 * 
		 * StringBuilder hql = generatorHql(OrganizationUnit.class, params,
		 * orderField, orderBy,extHql,joinHql.toString());
		 */
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from OrganizationUnit ").append(ename);
		hql.append(" left join fetch ").append(ename).append(".subOrganizationUnits ");
		// where emps.id=:empId
		hql.append(" where 1=1 ");

		if (params != null) {
			if (params.containsKey("orgId") || params.containsKey("orgUnitId")) {
				//Object orgId = params.get("orgId");
				//Object orgUnitId = params.get("orgUnitId");

				if (params.containsKey("orgId")) {
					hql.append(" and ").append(ename).append(".organization.id = :orgId ");
				}
				if (params.containsKey("orgUnitId")) {
					hql.append(" and ").append(ename).append(".parentOrganizationUnit.id = :orgUnitId ");
				}
			} else if (params.containsKey("fullName")) {
				hql.append(" and ").append(ename).append(".fullName like :fullName ");
			}
		}

		buildOrderQl(ename, hql, pager);
		return hql;
	}

	public StringBuilder findOrgUnitList(Map<String, Object> params) {
		/*
		 * String entityAsName = entityAsName(); //StringBuilder hqlBuilder =new
		 * StringBuilder(); StringBuilder joinHql =new StringBuilder();
		 * joinHql.append(" left join fetch ").append(entityAsName).append(
		 * ".subOrganizationUnits ");
		 * 
		 * StringBuilder hql = generatorHql(OrganizationUnit.class, params,
		 * orderField, orderBy,extHql,joinHql.toString());
		 */
		String ename = entityAsName();
		StringBuilder hql = new StringBuilder();
		hql.append("select ").append(ename);
		hql.append(" from OrganizationUnit ").append(ename);
		hql.append(" left join fetch ").append(ename).append(".subOrganizationUnits ");
		// where emps.id=:empId
		hql.append(" where 1=1 ");

		if (params != null) {
			if (params.containsKey("orgId") || params.containsKey("orgUnitId")) {
				if (params.containsKey("orgId")) {
					Object orgId = params.get("orgId");
					if(orgId==null){
						hql.append(" and ").append(ename).append(".organization.id is null ");
						params.remove("orgId");
					}else{
						hql.append(" and ").append(ename).append(".organization.id = :orgId ");
					}
					
				}
				if (params.containsKey("orgUnitId")) {
					Object orgUnitId = params.get("orgUnitId");
					if(orgUnitId==null){
						hql.append(" and ").append(ename).append(".parentOrganizationUnit.id is null ");
						params.remove("orgUnitId");
					}else{
						hql.append(" and ").append(ename).append(".parentOrganizationUnit.id = :orgUnitId ");
					}
				}
			} else if (params.containsKey("fullName")) {
				hql.append(" and ").append(ename).append(".fullName like :fullName ");
			}
		}

		return hql;
	}

	
	public StringBuilder findOrganizationUnitAll(Map<String, Object> params,
			String orderField, String orderBy, String extHql) {

		String entityAsName = entityAsName();
		// StringBuilder hqlBuilder =new StringBuilder();
		StringBuilder joinHql = new StringBuilder();
		joinHql.append(" left join fetch ").append(entityAsName)
				.append(".parentOrganizationUnit ");
		joinHql.append(" left join fetch ").append(entityAsName)
				.append(".subOrganizationUnits ");
		joinHql.append(" left join fetch ").append(entityAsName)
				.append(".organization ");
		joinHql.append(" left join fetch ").append(entityAsName)
				.append(".orgPositions ");

		StringBuilder hql = generatorHql(OrganizationUnit.class, params,
				orderField, orderBy, extHql, joinHql.toString());
		return hql;
	}

	/**
	 * 
	 * @Title: findOrgUnitListByOrgId
	 * @Description: 根据公司id得到部门信息
	 * @param @param orgId
	 * @param @param params
	 * @param @param orderField
	 * @param @param orderBy
	 * @param @param extHql
	 * @param @return 设定文件
	 * @return StringBuilder 返回类型
	 * @throws public
	 *             StringBuilder findOrgUnitListByOrgId(Long
	 *             orgId,Map<String,Object> params,String orderField,String
	 *             orderBy,String extHql){ String entityAsName = entityAsName();
	 *             //StringBuilder hqlBuilder =new StringBuilder();
	 * 
	 *             params.put("organization.id,"+SearchCondition.NOEQUAL,
	 *             orgId); StringBuilder queryHql =
	 *             generatorHql(OrganizationUnit.class, params, orderField,
	 *             orderBy, extHql.toString(), null );
	 * 
	 *             //如果需要自定义参数 必须generatorHql 后面添加 return queryHql; }
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
