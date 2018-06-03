package com.vix.common.org.hql;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.vix.core.persistence.hibernate.hql.HqlProvider;
import com.vix.core.web.Pager;

@Component
//@Scope("prototype")
public class EmployeeHrHqlProvider extends HqlProvider {

	@Override
	public String entityAsName() {
		return "employee";
	}

	public StringBuilder findEmpListByBusinessOrgId(Map<String,Object> params,Pager pager){
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from Employee ").append(ename);
		hql.append(" inner join ").append(ename).append(".organizationUnit orgUnit ");
		hql.append(" where 1=1 ");
	
		if(params!=null){
			if(params.containsKey("orgUnitId")){
				hql.append(" and orgUnit.id=:orgUnitId ");
			}else if(params.containsKey("epmName")){
				hql.append(" and ").append(ename).append(".name like :epmName ");
			}
		}
		
		buildOrderQl(ename, hql, pager);
		return hql;
	}
	
	/**
	 * 查询  部门 和 drp部门的 职员
	 * @Title: findEmp4OrgDrpPager
	 * @Description: TODO
	 * @param @param params
	 * @param @param pager
	 * @param @return    设定文件
	 * @return StringBuilder    返回类型
	 * @throws
	 */
	public StringBuilder findEmp4OrgDrpPager(Map<String,Object> params,Pager pager){
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from Employee ").append(ename);
		//hql.append(" inner join ").append(ename).append(".organizationUnit orgUnit ");
		hql.append(" where 1=1 ");
	
		if(params!=null){
			if(params.containsKey("orgUnitId")){
				hql.append(" and ").append(ename).append(".organizationUnit.id = :orgUnitId ");
			}else if(params.containsKey("drpUnitId")){
				hql.append(" and ").append(ename).append(".channelDistributor.id = :drpUnitId ");
			}else if(params.containsKey("empName")){
				hql.append(" and ").append(ename).append(".name like :empName ");
			}
		}
		
		buildOrderQl(ename, hql, pager);
		return hql;
	}
	
	
	/**
	 * 根据部门id得到职员
	 * @param params
	 * @param pager
	 * @return
	 */
	public StringBuilder findEmpListByOrgUnitId(Map<String,Object> params,Pager pager){
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from Employee ").append(ename);
		hql.append(" inner join ").append(ename).append(".organizationUnit orgUnit ");
		hql.append(" where 1=1 ");
	
		if(params!=null){
			if(params.containsKey("orgUnitId")){
				hql.append(" and orgUnit.id=:orgUnitId ");
			}
			if(params.containsKey("epmName")){
				hql.append(" and ").append(ename).append(".name like :epmName ");
			}
		}
		
		buildOrderQl(ename, hql, pager);
		return hql;
	}
	
	public StringBuilder findEmpListByOrgUnitId(Map<String,Object> params){
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from Employee ").append(ename);
		hql.append(" inner join ").append(ename).append(".organizationUnit orgUnit ");
		hql.append(" where 1=1 ");
	
		if(params!=null){
			if(params.containsKey("orgUnitId")){
				hql.append(" and orgUnit.id=:orgUnitId ");
			}
			if(params.containsKey("epmName")){
				hql.append(" and ").append(ename).append(".name like :epmName ");
			}
		}
		
		//buildOrderQl(ename, hql, pager);
		buildOrderQl(ename, hql, "id", "asc");
		return hql;
	}
	
	/**
	 * 查询工资类别的 职员关系的职员列表
	 * @param params
	 * @param pager
	 * @return
	 */
	public StringBuilder findEmpSalaryProjectListByOrgId(Map<String,Object> params,Pager pager){
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from Employee ").append(ename);
		hql.append(" inner join ").append(ename).append(".organizationUnit orgUnit ");
		hql.append(" left join fetch ").append(ename).append(".salaryProjectGrantSet salaryProjectGrantSet");
		hql.append(" left join fetch salaryProjectGrantSet.salaryProject sp ");
		hql.append(" where 1=1 ");
	
		if(params!=null){
			if(params.containsKey("orgUnitId")){
				hql.append(" and orgUnit.id=:orgUnitId ");
			}else if(params.containsKey("epmName")){
				hql.append(" and ").append(ename).append(".name like :epmName ");
			}
		}
		
		buildOrderQl(ename, hql, pager);
		return hql;
	}
	
	public StringBuilder findEmpList(Map<String,Object> params,Pager pager){
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from Employee ").append(ename);
		hql.append(" inner join ").append(ename).append(".organizationUnit orgUnit ");
		//where emps.id=:empId
		hql.append(" where 1=1 ");
	
		buildOrderQl(ename, hql, pager);
		return hql;
	}
	
	public StringBuilder testEmp(Map<String,Object> params){
		StringBuilder hql = new StringBuilder();
		String ename = entityAsName();
		hql.append("select ").append(ename);
		hql.append(" from Employee ").append(ename);
		hql.append(" inner join ").append(ename).append(".organizationUnit orgUnit ");
		hql.append(" where orgUnit.id=:orgUnitId ");
		
		return hql;
	}
}
