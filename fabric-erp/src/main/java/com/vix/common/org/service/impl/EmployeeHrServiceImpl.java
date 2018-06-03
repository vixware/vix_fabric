package com.vix.common.org.service.impl;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.org.dao.IEmployeeHrDao;
import com.vix.common.org.entity.BusinessOrg;
import com.vix.common.org.entity.OrgPosition;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.hql.EmployeeHrHqlProvider;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.common.security.entity.UserAccount;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;

@Service("employeeHrService")
@Transactional
public class EmployeeHrServiceImpl extends BaseHibernateServiceImpl implements IEmployeeHrService {

	private static final long serialVersionUID = 1L;

	@Resource(name="employeeHrDao")
	private IEmployeeHrDao employeeHrDao;
	
	@Resource(name="employeeHrHqlProvider")
	private EmployeeHrHqlProvider employeeHrHqlProvider;
	
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IEmployeeHrService#findEmpAccountPager(com.vix.core.web.Pager, java.lang.String, java.util.Map)
	 */
	@Override
	public Pager findEmpAccountPager(Pager pager,String orgUnitId,Map<String,Object> reqParams) throws Exception{
		Map<String,Object> params = new HashMap<String, Object>();
		if(orgUnitId!=null){
			params.put("orgUnitId", orgUnitId);
		}
		if(reqParams!=null){
			params.putAll(reqParams);
		}
		
		StringBuilder hql = employeeHrHqlProvider.findEmpListByBusinessOrgId(params,pager);
        pager = employeeHrDao.findPager2ByHql(pager,employeeHrHqlProvider.entityAsName(),hql.toString(), params);
        return pager;
	}
	
	@Override
	public Pager findEmp4OrgDrpPager(Pager pager,String orgType,String orgId,String empName) throws Exception{
		StringBuilder hql = new StringBuilder();
		Map<String,Object> params = new HashMap<String, Object>();
		/*
		if(StringUtils.isNotEmpty(orgType)){
			if(orgType.equals("O")){
				params.put("orgUnitId", orgId);
			}else if(orgType.equals("D")){
				params.put("drpUnitId", orgId);
			}
		}else if(StringUtils.isNotEmpty(empName)){
			params.put("empName", empName);
		}
		StringBuilder hql = employeeHrHqlProvider.findEmp4OrgDrpPager(params,pager);*/
		
		hql.append("select emp from ").append(Employee.class.getName()).append(" emp where 1=1 ");
		
		if(StringUtils.isNotEmpty(orgType) || StringUtils.isNotEmpty(empName)){
			if(StringUtils.isNotEmpty(orgType)){
				if(orgType.equals("O")){
					hql.append(" and emp.organizationUnit.id = :orgUnitId ");
					params.put("orgUnitId", orgId);
				}else if(orgType.equals("D")){
					hql.append(" and emp.channelDistributor.id = :drpUnitId ");
					params.put("drpUnitId", orgId);
				}
			}
			if(StringUtils.isNotEmpty(empName)){
				hql.append(" and emp.name like :empName ");
			}
		}
        pager = employeeHrDao.findPager2ByHql(pager,"emp",hql.toString(), params);
        return pager;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IEmployeeHrService#findEmpByOrgUnitIdPager(com.vix.core.web.Pager, java.lang.String, java.util.Map)
	 */
	@Override
	public Pager findEmpByOrgUnitIdPager(Pager pager,Map<String,Object> params) throws Exception{
		StringBuilder hql = employeeHrHqlProvider.findEmpListByOrgUnitId(params,pager);
        pager = employeeHrDao.findPager2ByHql(pager,employeeHrHqlProvider.entityAsName(),hql.toString(), params);
        return pager;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IEmployeeHrService#findAllEmpByOrgUnitId(java.util.Map)
	 */
	@Override
	public List<Employee> findAllEmpByOrgUnitId(Map<String,Object> params) throws Exception{
		StringBuilder hql = employeeHrHqlProvider.findEmpListByOrgUnitId(params);
		List<Employee> resList = employeeHrDao.findAllByHql2(hql.toString(), params);
		return resList;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.org.service.IEmployeeHrService#findEmpAccountSalaryProjectPager(com.vix.core.web.Pager, java.lang.String, java.util.Map)
	 */
	@Override
	public Pager findEmpAccountSalaryProjectPager(Pager pager,String orgUnitId,Map<String,Object> reqParams) throws Exception{
		Map<String,Object> params = new HashMap<String, Object>();
		if(orgUnitId!=null){
			params.put("orgUnitId", orgUnitId);
		}
		if(reqParams!=null){
			params.putAll(reqParams);
		}
		
		StringBuilder hql = employeeHrHqlProvider.findEmpSalaryProjectListByOrgId(params,pager);
        pager = employeeHrDao.findPager2ByHql(pager,employeeHrHqlProvider.entityAsName(),hql.toString(), params);
        return pager;
	}
	
	@Override
	public Pager findEmpAccountPager(Pager pager,Map<String,Object> reqParams) throws Exception{
		Map<String,Object> params = new HashMap<String, Object>();
		if(reqParams!=null){
			params.putAll(reqParams);
		}
		
		StringBuilder hql = employeeHrHqlProvider.findEmpList(params,pager);
        pager = employeeHrDao.findPager2ByHql(pager,employeeHrHqlProvider.entityAsName(),hql.toString(), params);
        return pager;
	}
	
	@Override
	public Employee saveOrUpdateEmp(Employee emp) throws Exception{
		Employee e1 = null;
		if(emp!=null && StrUtils.isNotEmpty(emp.getId())){
			e1 = employeeHrDao.findEntityById(Employee.class, emp.getId());
		}
		if(e1==null){
			e1 = new Employee();
		}
		e1.setCode(emp.getCode());
		e1.setName(emp.getName());
		e1.setOldName(emp.getOldName());
		e1.setFsName(emp.getFsName());
		e1.setIdNumber(emp.getIdNumber());
		e1.setBirthday(emp.getBirthday());
		e1.setBloodType(emp.getBloodType());
		e1.setGender(emp.getGender());
		e1.setDepartmentCode(emp.getDepartmentCode());
		e1.setQualificationsCode(emp.getQualificationsCode());
		e1.setStaffJobNumber(emp.getStaffJobNumber());
		e1.setResidenceAddress(emp.getResidenceAddress());
		e1.setBirthplace(emp.getBirthplace());
		e1.setIsDemission(emp.getIsDemission());
		e1.setEntityTime(emp.getEntityTime());
		e1.setTelephone(emp.getTelephone());
		e1.setEmployeeType(emp.getEmployeeType());
		e1.setSubordinatePosition(emp.getSubordinatePosition());
		e1.setSubordinateTitle(emp.getSubordinateTitle());
		
		
		OrganizationUnit ou = employeeHrDao.findEntityById(OrganizationUnit.class, emp.getOrganizationUnit().getId());
		e1.setOrganizationUnit(ou);
		e1.setCompanyInnerCode(ou.getCompanyInnerCode());
		e1.setTenantId(ou.getTenantId());
		
		//employeeHrDao.saveOrUpdate(e1);
		employeeHrDao.saveOrUpdateOriginal(e1);
		
		return e1;	
	}
	
	
	/**
	 * 根据职员id得到业务组织列表
	 */
	@Override
	public List<BusinessOrg> findEssOfBusinessOrgList(String employeeId) throws Exception{
		StringBuilder hql = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		hql.append("select bo from BusinessOrg bo inner join bo.employees emps where emps.id=:empId");
		params.put("empId", employeeId);
		
		List<BusinessOrg> boList = employeeHrDao.findAllByHql(hql.toString(), params);
		return boList;
	}
	/**
	 * 根据职员id得到帐号的列表
	 */
	@Override
	public List<UserAccount> findEssOfUserAccountList(String employeeId) throws Exception{
		StringBuilder hql = new StringBuilder();
		Map<String,Object> params = new HashMap<String,Object>();
		hql.append("select ua from UserAccount ua where ua.employee.id=:empId");
		params.put("empId", employeeId);
		
		List<UserAccount> uaList = employeeHrDao.findAllByHql(hql.toString(), params);
		return uaList;
	}
	
	@Override
	public void saveOrUpdateEmpOrgPosition(String empId,String posId)throws Exception{
		Employee emp = employeeHrDao.findEntityById(Employee.class, empId);
		
		Set<OrgPosition> empOrgPosSet = emp.getOrgPositions();
		if(empOrgPosSet==null){
			empOrgPosSet = new HashSet<OrgPosition>();
		}
		OrgPosition op = employeeHrDao.findEntityById(OrgPosition.class, posId);
		empOrgPosSet.add(op);
		emp.setOrgPositions(empOrgPosSet);
		employeeHrDao.update(emp);
	}
	
	@Override
	public void deleteForEmpOrgPosition(String empId,String posId)throws Exception{
		Employee emp = employeeHrDao.findEntityById(Employee.class, empId);
		
		Set<OrgPosition> empOrgPosSet = emp.getOrgPositions();
		OrgPosition op = employeeHrDao.findEntityById(OrgPosition.class, posId);
		empOrgPosSet.remove(op);
		employeeHrDao.update(emp);
	}
}
