package com.vix.common.security.dao;

import java.util.List;
import java.util.Set;

import com.vix.common.security.entity.Authority;
import com.vix.common.security.entity.Role;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

public interface IRoleDao extends IBaseHibernateDao {

	public Set<Authority> findAllAuthorityMWithRole(String roleId, String bizType, String tenantId) throws Exception;

	public List<Authority> findSubAuthorityFByRole(String roleId, String authId, String bizType, String tenantId) throws Exception;

	/**
	 * 保存整个权限树 @Title: saveForAuthority @Description: TODO @param @param
	 * roleId @param @param bizType @param @param menuIds @param @param
	 * funIds @param @param checkedMenuId @param @param
	 * isChangCheckMenu @param @param isChangeCheckFun @param @throws Exception
	 * 设定文件 @return void 返回类型 @throws
	 */
	public void saveForAuthority(String roleId, String bizType, String menuIds, String funIds, String checkedMenuId, Boolean isChangCheckMenu, Boolean isChangeCheckFun, String tenantId) throws Exception;

	/**
	 * 角色授权树的 单节点授权 @Title: saveForAuthorityByAuthId @Description:
	 * TODO @param @param roleId @param @param bizType @param @param
	 * authId @param @throws Exception 设定文件 @return void 返回类型 @throws
	 */
	public void saveForAuthorityByAuthId(String roleId, String bizType, String authId, String tenantId) throws Exception;

	/**
	 * 只授权单节点,不授权子节点
	 * 
	 * @param roleId
	 * @param bizType
	 * @param authId
	 * @param tenantId
	 * @throws Exception
	 */
	public void saveForAuthorityByAuthIdOnly(String roleId, String bizType, String authId, String tenantId) throws Exception;

	/**
	 * 角色授权树的 单节点取消 @Title: deleteForAuthorityByAuthId @Description:
	 * TODO @param @param roleId @param @param bizType @param @param
	 * authId @param @throws Exception 设定文件 @return void 返回类型 @throws
	 */
	public void deleteForAuthorityByAuthId(String roleId, String bizType, String authId, String tenantId) throws Exception;

	/**
	 * @Title: findLastRoleByAccount
	 * @Description: 最新的角色信息
	 */
	public Role findLastRoleByAccount(String accountId) throws Exception;

}
