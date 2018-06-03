package com.vix.common.security.dao;

import java.util.Date;

import com.vix.common.security.entity.UserAccount;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

public interface IUserAccountDao extends IBaseHibernateDao {

	public UserAccount findUserByAccount(String userName) throws Exception;
	/**
	 * 增加账号
	 * @param addRoleIds 增加的角色
	 * @param delRoleIds 删除的角色
	 * @param accountId 账号id  新建账号传null
	 * @param accountBizType 账号的业务分类 
	 * 								分销传 fx
	 * @param account 账号 
	 * @param password 密码 
	 * @param employeeId 职员id
	 * @param enable  0:禁用  1：激活 
	 * @return
	 * @throws Exception
	 */
	UserAccount saveOrUpdate(String addRoleIds,String delRoleIds,String accountId,String accountBizType,String account,String password,String employeeId,String enable,String companyCode)throws Exception;
	
	/**
	 * 增加tenantId的账号增加方法
	 * @param addRoleIds
	 * @param delRoleIds
	 * @param accountId
	 * @param accountBizType
	 * @param account
	 * @param password
	 * @param employeeId
	 * @param enable
	 * @return
	 * @throws Exception
	 */
	UserAccount saveOrUpdate(String tenantId,String companyCode,String companyInnerCode, String addRoleIds,String delRoleIds,String accountId,String accountBizType,String account,String password,String employeeId,String enable)throws Exception;

	/**
	 * 
	 * @param account
	 *            账号
	 * @param password
	 * @param employeeId
	 * @param enable
	 * @return
	 * @throws Exception
	 */
	UserAccount saveOrUpdateUserAccount(String account, String password, String employeeId, String enable) throws Exception;

	UserAccount updateUserAccount(String account, String password) throws Exception;

	UserAccount queryUserAccount(String account, String password) throws Exception;

	/**
	 * @Title: saveOrUpdateBaseinfoSyn
	 * @Description: 用于 数据中心 响应 vix应用服务器的 账号同步
	 * @param @param isSynMaster 	true  vix服务器 -------->  运营中心服务器
	 * 								false 运营中心服务器 ------>  vix服务器
	 * 
	 * @param @param userAccount
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return UserAccount    返回类型
	 * @throws
	 
	public UserAccount saveOrUpdateBaseinfoSyn(Boolean isSynMaster,UserAccount userAccount)throws Exception;
	*/
	
	/**
	 * @Title: updateUserAccountDateByTenantId
	 * @Description: 设定承租户下的账号  时间段
	 * @param @param tenantId
	 * @param @param startDate
	 * @param @param endDate
	 * @param @throws Exception    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void updateUserAccountDateByTenantId(String tenantId,Date startDate,Date endDate)throws Exception;
	
	/**
	 * @Title: synUserAccountErp2Oc
	 * @Description: 同步账号数据到OC   没有事物  只进行http调用
	 */
	public Boolean synUserAccountErp2Oc(Boolean isAdd ,UserAccount userAccount)throws Exception;
	
	/**
	 * @Title: synCheckUserAccountIsRepeatErp2Oc
	 * @Description: 教研账号是否在oc存在
	 */
	public Boolean synCheckUserAccountIsRepeatErp2Oc(UserAccount userAccount)throws Exception;
	
}
