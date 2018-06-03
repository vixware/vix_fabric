package com.vix.common.org.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.vix.common.org.dao.IEmployeeHrDao;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.hr.organization.entity.Employee;

@Repository("employeeHrDao")
public class EmployeeHrDaoImpl extends BaseHibernateDaoImpl implements IEmployeeHrDao {

	private static final long serialVersionUID = -7524355023279202816L;

	
	/* (non-Javadoc)
	 * @see com.vix.common.org.dao.IEmployeeHrDao#findStaffEmployee(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Employee> findStaffEmployee(String empId,String companyCode)throws Exception{
		StringBuilder hql = new StringBuilder();
		Map<String,Object> params = new HashMap<String, Object>();
		
		hql.append("select emp from ").append(Employee.class.getName()).append(" emp inner join emp.organizationUnit org ");//.append(ename);
		hql.append(" where org.manager.id = :magId ");
		params.put("magId", empId);
		
		if(StringUtils.isNotEmpty(companyCode)){
			//hql.append(" and org.manager.id = :magId ");
			hql.append(" and org.organization.companyCode = :companyCode ");
			params.put("companyCode", companyCode);
		}
		List<Employee> empList = findAllByHql2(hql.toString(), params);
		return empList;
	}
}
