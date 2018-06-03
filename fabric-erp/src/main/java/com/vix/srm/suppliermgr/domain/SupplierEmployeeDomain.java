/**   
* @Title: ContractDomain.java 
* @Package com.vix.contract.domain 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenzhengwen
* @author w_a_533@163.com   
* @date 2013-6-24 下午4:18:31  
*/
package com.vix.srm.suppliermgr.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;

/**
 * @Description: 供应商员工
 * @author ivan
 * @date 2013-12-27
 */
@Component("supplierEmployeeDomain")
@Transactional
public class SupplierEmployeeDomain extends BaseDomain {

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Employee.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, Employee.class, params);
		return p;
	}

	public Employee findEmployeeById(String id) throws Exception {
		return baseHibernateService.findEntityById(Employee.class, id);
	}

	public Employee merge(Employee employee) throws Exception {
		return baseHibernateService.merge(employee);
	}

	public void deleteByEmployee(Employee employee) throws Exception {
		baseHibernateService.deleteByEntity(employee);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(Employee.class, ids);
	}

	/** 索引对象列表 */
	public List<Employee> findEmployeeIndex() throws Exception {
		return baseHibernateService.findAllByConditions(Employee.class, null);
	}
}
