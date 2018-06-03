package com.vix.common.security.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vix.common.security.entity.Authority;
import com.vix.common.security.entity.IndexPageDataConfig;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IRoleService extends IBaseHibernateService {

	/**
	 * 查询角色的分页信息
	 * 
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findRolePager(Pager pager, Map<String, Object> params) throws Exception;

	/**
	 * 查询角色的分页信息
	 * 
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findCommonSelectRolePager(Pager pager, Map<String, Object> params) throws Exception;

	// 角色授权相关 开始

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

	public void saveForAuthorityByAuthIdOnly(String roleId, String bizType, String authId, String tenantId) throws Exception;

	/**
	 * 角色授权树的 单节点取消 @Title: deleteForAuthorityByAuthId @Description:
	 * TODO @param @param roleId @param @param bizType @param @param
	 * authId @param @throws Exception 设定文件 @return void 返回类型 @throws
	 */
	public void deleteForAuthorityByAuthId(String roleId, String bizType, String authId, String tenantId) throws Exception;

	// 角色授权相关 结束

	/**
	 * @Title: saveForColConfig @Description: 添加列配置项 @param @param
	 *         roleId @param @param colConfigIds 设定文件 @return void 返回类型 @throws
	 */
	public void saveForColConfig(String roleId, String colConfigIds) throws Exception;

	/**
	 * @Title: deleteForColConfig @Description: 删除配置项 @param @param
	 *         roleId @param @param colConfigId 设定文件 @return void 返回类型 @throws
	 */
	public void deleteForColConfig(String roleId, String colConfigId) throws Exception;

	/**
	 * @Title: findPdcByUserAccount @Description:
	 *         根据账号id查询该帐号下的所有授权的首页面div配置项 @param @param
	 *         userAccountId @param @return @param @throws Exception
	 *         设定文件 @return List<IndexPageDataConfig> 返回类型 @throws
	 */
	public List<IndexPageDataConfig> findPdcByUserAccount(String userAccountId) throws Exception;
}
