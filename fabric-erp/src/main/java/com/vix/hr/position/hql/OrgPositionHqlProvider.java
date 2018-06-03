package com.vix.hr.position.hql;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vix.common.org.entity.OrgPosition;
import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.web.Pager;

@Component
@Scope("prototype")
public class OrgPositionHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "orgPosition";
	}

	public StringBuilder findOrgPosition(Map<String,Object> params,Pager pager){
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from ").append(OrgPosition.class.getName()).append(" ").append(ename);
		hql.append(" left join fetch ").append(ename).append(".orgJob orgJob ");
		hql.append(" inner join ").append(ename).append(".organizationUnit orgUnit ");
		//where emps.id=:empId
		hql.append(" where 1=1 ");
		if(params.containsKey("posName")){
			//hql.append(" and ").append(ename).append(".posName LIKE :posName ");
			hql.append(" and orgPosition.posName like :posName ");
			//params.remove("posName");
		}
		if(params.containsKey("orgUnitId")){
			hql.append(" and orgUnit.id=:orgUnitId ");
		}
		buildOrderQl(ename, hql, pager);
		return hql;
	}
	
	public StringBuilder findOrgPositionByOrgId(Map<String,Object> params){
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from ").append(OrgPosition.class.getName()).append(" ").append(ename);
		hql.append(" inner join ").append(ename).append(".organization org ");
		//where emps.id=:empId
		hql.append(" where org.id= :orgId ");
		
		Object parentId = params.get("parentId");
		if(parentId==null){
			hql.append(" and " + ename + ".parentOrgPosition is null ");
			params.remove("parentId");
		}else{
			hql.append(" and " + ename + ".parentOrgPosition.id = :parentId  ");
		}
		
		return hql;
	}
	
	public StringBuilder findOrgPositionByEmpId(Map<String,Object> params){
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from ").append(OrgPosition.class.getName()).append(" ").append(ename);
		hql.append(" inner join ").append(ename).append(".employees emp ");
		hql.append(" left join fetch ").append(ename).append(".orgJob orgJob ");
		hql.append(" inner join ").append(ename).append(".organizationUnit orgUnit ");
		//where emps.id=:empId
		hql.append(" where emp.id = :empId ");//.append(ename).append(".");
		
		return hql;
	}
}
