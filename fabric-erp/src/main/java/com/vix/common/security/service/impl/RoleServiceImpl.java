package com.vix.common.security.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.security.dao.IRoleDao;
import com.vix.common.security.entity.Authority;
import com.vix.common.security.entity.IndexPageDataConfig;
import com.vix.common.security.hql.RoleHqlProvider;
import com.vix.common.security.service.IRoleService;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.web.Pager;

@Service("roleService")
@Transactional
public class RoleServiceImpl extends BaseHibernateServiceImpl implements IRoleService {

	private static final long serialVersionUID = 1L;

	@Resource(name = "roleDao")
	private IRoleDao roleDao;

	@Resource(name = "roleHqlProvider")
	private RoleHqlProvider roleHqlProvider;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vix.common.security.service.IRoleService#findRolePager(com.vix.core.
	 * web.Pager, java.util.Map)
	 */
	@Override
	public Pager findRolePager(Pager pager, Map<String, Object> params) throws Exception {
		StringBuilder hql = roleHqlProvider.findRoleList(params, pager);
		pager = roleDao.findPager2ByHql(pager, roleHqlProvider.entityAsName(), hql.toString(), params);
		return pager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vix.common.security.service.IRoleService#findCommonSelectRolePager(
	 * com.vix.core.web.Pager, java.util.Map)
	 */
	@Override
	public Pager findCommonSelectRolePager(Pager pager, Map<String, Object> params) throws Exception {
		StringBuilder hql = roleHqlProvider.findRoleList(params, pager);
		pager = roleDao.findPager2ByHql(pager, roleHqlProvider.entityAsName(), hql.toString(), params);
		return pager;
	}

	// 角色授权相关 开始

	@Override
	public Set<Authority> findAllAuthorityMWithRole(String roleId, String bizType, String tenantId) throws Exception {
		return roleDao.findAllAuthorityMWithRole(roleId, bizType, tenantId);
	}

	@Override
	public List<Authority> findSubAuthorityFByRole(String roleId, String authId, String bizType, String tenantId) throws Exception {
		return roleDao.findSubAuthorityFByRole(roleId, authId, bizType, tenantId);
	}

	@Override
	public void saveForAuthority(String roleId, String bizType, String menuIds, String funIds, String checkedMenuId, Boolean isChangCheckMenu, Boolean isChangeCheckFun, String tenantId) throws Exception {
		roleDao.saveForAuthority(roleId, bizType, menuIds, funIds, checkedMenuId, isChangCheckMenu, isChangeCheckFun, tenantId);
	}

	@Override
	public void saveForAuthorityByAuthId(String roleId, String bizType, String authId, String tenantId) throws Exception {
		roleDao.saveForAuthorityByAuthId(roleId, bizType, authId, tenantId);
	}

	@Override
	public void deleteForAuthorityByAuthId(String roleId, String bizType, String authId, String tenantId) throws Exception {
		roleDao.deleteForAuthorityByAuthId(roleId, bizType, authId, tenantId);
	}

	// 角色授权相关 结束

	@Override
	public void saveForColConfig(String roleId, String colConfigIds) throws Exception {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		sql.append("delete dr from CMN_SEC_DATARESCOLCONFIG_ROLE dr ");
		sql.append("where dr.Role_ID = :roleId  ");
		sql.append(" and dr.DATARESCOLCONFIG_ID in( ").append(colConfigIds).append(") ");
		params.put("roleId", roleId);
		batchUpdateBySql(sql.toString(), params);
		sql.setLength(0);
		params.clear();

		List<Object[]> saveArrayParam = new LinkedList<Object[]>();

		sql.append("insert into CMN_SEC_DATARESCOLCONFIG_ROLE(Role_ID,DATARESCOLCONFIG_ID) values (?,?)");
		String[] colConfigIdsArray = colConfigIds.split("\\,");
		for (String cid : colConfigIdsArray) {
			saveArrayParam.add(new Object[] { roleId, cid });
		}
		roleDao.batchUpdateBySql(sql.toString(), saveArrayParam);
	}

	/**
	 * @Title: deleteForColConfig @Description: 删除配置项 @param @param
	 *         roleId @param @param colConfigId 设定文件 @return void 返回类型 @throws
	 */
	@Override
	public void deleteForColConfig(String roleId, String colConfigId) throws Exception {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		sql.append("delete dr from CMN_SEC_DATARESCOLCONFIG_ROLE dr ");
		sql.append("where dr.Role_ID = :roleId  ");
		sql.append(" and dr.DATARESCOLCONFIG_ID = :colConfigId ");// and
																	// a.authType='M'
		params.put("roleId", roleId);
		params.put("colConfigId", colConfigId);
		batchUpdateBySql(sql.toString(), params);
		sql.setLength(0);
		params.clear();
	}

	/*
	 * @see
	 * com.vix.common.security.service.IRoleService#findPdcByUserAccount(java.
	 * lang.String)
	 */
	@Override
	public List<IndexPageDataConfig> findPdcByUserAccount(String userAccountId) throws Exception {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("SELECT  distinct pdc.* FROM CMN_SEC_IPD_CONFIG pdc ");// .append(ename);
		sql.append(" inner join CMN_SEC_ROLE_IPD_CONFIG rpdc on pdc.id = rpdc.IPD_CONFIG_ID ");
		sql.append(" inner join CMN_SEC_USERACCOUNT_ROLE ur on ur.Role_ID = rpdc.Role_ID ");
		sql.append(" where ur.UserAccount_ID = ? ");
		params.add(userAccountId);

		List<IndexPageDataConfig> uaPdcList = roleDao.queryObjectListBySql(sql.toString(), IndexPageDataConfig.class, params.toArray());

		return uaPdcList;
	}

	@Override
	public void saveForAuthorityByAuthIdOnly(String roleId, String bizType, String authId, String tenantId) throws Exception {
		roleDao.saveForAuthorityByAuthIdOnly(roleId, bizType, authId, tenantId);
	}

}
