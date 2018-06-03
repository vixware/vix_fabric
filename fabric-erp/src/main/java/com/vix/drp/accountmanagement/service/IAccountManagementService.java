package com.vix.drp.accountmanagement.service;

import java.util.List;
import java.util.Map;

import com.vix.common.org.vo.OrgUnit;
import com.vix.common.security.entity.UserAccount;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IAccountManagementService extends IBaseHibernateService {

	// 根据条件查询列表数据

	/**
	 * @Title: findOrgAndUnitTreeList
	 * @Description: 加载公司和组织机构数据
	 * @param @param treeType 公司 C 部门 O
	 * @param @param id 公司或者部门id
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<OrgUnit> 返回类型
	 * @throws
	 */
	List<OrgUnit> findOrgAndUnitTreeList(String treeType, String id) throws Exception;

	List<OrgUnit> findEmpTreeList(String treeType, String id) throws Exception;

	/**
	 * 公司的分页信息
	 * 
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findOrganizationPager(Pager pager, Map<String, Object> params) throws Exception;

	/**
	 * 增加账号
	 * 
	 * @param addRoleIds
	 *            增加的角色
	 * @param delRoleIds
	 *            删除的角色
	 * @param accountId
	 *            账号id 新建账号传null
	 * @param accountBizType
	 *            账号的业务分类 分销传 fx
	 * @param account
	 *            账号
	 * @param password
	 *            密码
	 * @param employeeId
	 *            职员id
	 * @param enable
	 *            0:禁用 1：激活
	 * @return
	 * @throws Exception
	 */
	public UserAccount saveOrUpdate(String addRoleIds, String delRoleIds, String accountId, String accountBizType, String account, String password, String employeeId, String enable, String companyCode) throws Exception;
	
	public UserAccount saveOrUpdateUserAccount(String account, String password, String employeeId, String enable) throws Exception;

	public UserAccount updateUserAccount(String account, String password) throws Exception;

	public UserAccount queryUserAccount(String account, String password) throws Exception;

}
