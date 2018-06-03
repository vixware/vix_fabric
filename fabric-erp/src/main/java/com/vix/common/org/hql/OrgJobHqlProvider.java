package com.vix.common.org.hql;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;

@Component
//@Scope("prototype")
public class OrgJobHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "orgJob";
	}
	
	public StringBuilder findOrgJobByOrgId(Map<String,Object> params){
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from OrgJob ").append(ename);
		hql.append(" inner join ").append(ename).append(".organization org ");
		//where emps.id=:empId
		hql.append(" where org.id=:orgId ");
		
		return hql;
	}
	
	/*
	public StringBuilder findEmpListByBusinessOrgId(Map<String,Object> params){
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from Employee ").append(ename);
		hql.append(" inner join ").append(ename).append(".businessOrgs bos ");
		//where emps.id=:empId
		hql.append(" where bos.id=:bosId ");
		
		return hql;
	}*/
}
