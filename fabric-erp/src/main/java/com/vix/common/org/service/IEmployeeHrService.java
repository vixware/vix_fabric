package com.vix.common.org.service;

import java.util.List;
import java.util.Map;

import com.vix.common.org.entity.BusinessOrg;
import com.vix.common.security.entity.UserAccount;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;

public interface IEmployeeHrService extends IBaseHibernateService {

	/**
	 * 查询职员列表
	 * 
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findEmpAccountPager(Pager pager, String orgUnitId, Map<String, Object> params) throws Exception;

	/**
	 * @Title: findEmpAccountPager @Description: 查询 部门 和 drp部门的 职员 @param @param
	 * pager @param @param orgType O 部门 D drp组织 OD 部门和分销组织所有职员 @param @param
	 * orgId 部门id或者Drp组织id @param @param params @param @return @param @throws
	 * Exception 设定文件 @return Pager 返回类型 @throws
	 */
	public Pager findEmp4OrgDrpPager(Pager pager, String orgType, String orgId, String empName) throws Exception;

	/**
	 * 根据部门id 得到职员信息
	 * 
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findEmpByOrgUnitIdPager(Pager pager, Map<String, Object> params) throws Exception;

	/**
	 * 根据组织机构id获得所有职员
	 * 
	 * @param pager
	 * @param params
	 *            参数 orgUnitId 组织机构id key epmName 职员姓名
	 * @return
	 * @throws Exception
	 */
	public List<Employee> findAllEmpByOrgUnitId(Map<String, Object> params) throws Exception;

	/**
	 * 查询职员列表 用于hr中薪酬的职员工资类别关系设定的人员列表查询
	 * 
	 * @param pager
	 * @param orgUnitId
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findEmpAccountSalaryProjectPager(Pager pager, String orgUnitId, Map<String, Object> params) throws Exception;

	/**
	 * 查询职员列表 非组织机构树的方式
	 * 
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findEmpAccountPager(Pager pager, Map<String, Object> params) throws Exception;

	/**
	 * 保存职员基本信息
	 * 
	 * @param emp
	 * @return
	 * @throws Exception
	 */
	Employee saveOrUpdateEmp(Employee emp) throws Exception;

	/**
	 * 根据职员id得到业务组织列表
	 * 
	 * @param employeeId
	 * @return
	 * @throws Exception
	 */
	List<BusinessOrg> findEssOfBusinessOrgList(String employeeId) throws Exception;

	/**
	 * 根据职员id得到帐号的列表
	 * 
	 * @param employeeId
	 * @return
	 * @throws Exception
	 */
	List<UserAccount> findEssOfUserAccountList(String employeeId) throws Exception;

	/**
	 * 保存职员和岗位关系
	 * 
	 * @param empId
	 * @param businessOrgId
	 * @throws Exception
	 */
	void saveOrUpdateEmpOrgPosition(String empId, String posId) throws Exception;

	/**
	 * 删除岗位和职员的关系
	 * 
	 * @param empId
	 * @param posId
	 * @throws Exception
	 */
	void deleteForEmpOrgPosition(String empId, String posId) throws Exception;
}
