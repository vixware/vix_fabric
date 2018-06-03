package com.vix.common.activity.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.dao.IEmployeeHrDao;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.hql.EmployeeHrHqlProvider;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.BaseEmployee;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.hr.organization.entity.Employee;

@Service("activitiService")
@Transactional(readOnly=true)
public class ActivitiServiceImpl extends BaseHibernateServiceImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name="employeeHrDao")
	private IEmployeeHrDao employeeHrDao;
	
	@Resource(name="employeeHrHqlProvider")
	private EmployeeHrHqlProvider employeeHrHqlProvider;
	
	/**
	 * 根据分部Id获取分部下所有有效员工
	 * @param subId
	 * @return
	 */
	public List<String> getUserIdListBySubId(String subId){
		List<String> list = new LinkedList<String>();
		if(StringUtils.isEmpty(subId)){
			return list;
		}
		
		try {
			Organization org = employeeHrDao.findEntityById(Organization.class, subId);
			String companyInnerCode = org.getCompanyInnerCode();
			
			Map<String,Object> params =new HashMap<String, Object>();
	    	StringBuilder hql = new StringBuilder();
			String ename = "emp";
			hql.append("select ").append(ename);
			hql.append(" from Employee ").append(ename);
			hql.append(" inner join ").append(ename).append(".organizationUnit orgUnit ");
			hql.append(" where 1=1 ");
		
			hql.append(" and orgUnit.companyInnerCode like :companyInnerCode ");
			params.put("companyInnerCode",companyInnerCode+"%");
			
			List<Employee> empList =  employeeHrDao.findAllByHql2(hql.toString(), params);
			if(empList!=null && empList.size()>0){
				for(Employee emp:empList){
					list.add(String.valueOf(emp.getId()));
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * 根据部门Id获取部门下所有有效员工
	 * @param depId
	 * @return
	 */
	public List<String> getUserIdListByDepId(String depId){
		if(StringUtils.isEmpty(depId)){
			return null;
		}
		
		Map<String,Object> params =new HashMap<String, Object>();
    	StringBuilder hql = new StringBuilder();
		String ename = "emp";
		hql.append("select ").append(ename);
		hql.append(" from Employee ").append(ename);
		hql.append(" inner join ").append(ename).append(".organizationUnit orgUnit ");
		hql.append(" where 1=1 ");
	
		hql.append(" and orgUnit.id=:orgUnitId ");
		params.put("orgUnitId", depId);
		
		List<Employee> empList = null;
		try {
			empList = employeeHrDao.findAllByHql2(hql.toString(), params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<String> list = new LinkedList<String>();
		if(empList!=null && empList.size()>0){
			for(Employee emp:empList){
				list.add(String.valueOf(emp.getId()));
			}
		}
        return list;
	}
	
	
	
	/**
	 * 根据角色Id获取角色下所有有效员工
	 * @param roleId
	 * @return
	 */
	public List<String> getUserIdListByRoleId(String roleId){
		if(StringUtils.isEmpty(roleId)){
			return null;
		}
		List<String> list = new LinkedList<String>();
		Map<String,Object> params = new HashMap<String,Object>();
		StringBuilder sb = new StringBuilder();
		
		sb.append("select userAccount from ").append(UserAccount.class.getName()).append(" userAccount");
		sb.append(" left join userAccount.roles role");
		sb.append(" where role.id=:roleId ");
		
		params.put("roleId", String.valueOf(roleId));
		
		try {
			List<UserAccount> accountList = employeeHrDao.findAllByHql2(sb.toString(), params);
			
			if(accountList!=null && accountList.size()>0){
				for(UserAccount account:accountList){
					BaseEmployee emp = account.getEmployee();
					if(emp!=null){
						list.add(String.valueOf(emp.getId()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public String getUserNameById(String userId){
		if(StringUtils.isEmpty(userId)){
			return null;
		}
		String userName = null;
		
		try {
			Employee emp = employeeHrDao.findEntityById(Employee.class, userId);
			if(emp!=null){
				userName = emp.getName();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userName;
	}
	
	
	public String getLoginId(){
		String empId =  SecurityUtil.getCurrentEmpId();
		if(empId!=null){
			return String.valueOf(empId);
		}
		return null;
	}
}
