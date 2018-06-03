package com.vix.common.org.dao;

import java.util.List;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.hr.organization.entity.Employee;

public interface IEmployeeHrDao extends IBaseHibernateDao {

	/**
	 * 日报接口,获取下级人员
	 * @param empId
	 * @param companyCode
	 * @return
	 * @throws Exception
	 */
	List<Employee> findStaffEmployee(String empId,String companyCode)throws Exception;
}
