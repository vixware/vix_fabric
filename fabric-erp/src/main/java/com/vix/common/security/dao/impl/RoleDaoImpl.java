package com.vix.common.security.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.vix.common.org.dao.IBaseOrganizationDao;
import com.vix.common.security.dao.IRoleDao;
import com.vix.common.security.entity.Authority;
import com.vix.common.security.entity.Role;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.BizConstant;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

@Repository("roleDao")
public class RoleDaoImpl extends BaseHibernateDaoImpl implements IRoleDao {

	private static final long serialVersionUID = 1L;

	@Resource(name = "baseOrganizationDao")
	private IBaseOrganizationDao organizationDao;

	// 待验证处理 子权限数量
	@Override
	public Set<Authority> findAllAuthorityMWithRole(String roleId, String bizType, String tenantId) throws Exception {
		String compAdminRoleId = organizationDao.findOrgByUserAccountId(SecurityUtil.getCurrentEmpId(), SecurityUtil.getCurrentUserId());

		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("SELECT auth.ID,auth.Name,auth.authorityCode, auth.parentAuthorityCode,auth.SortOrder  ");// .append(ename);
		sql.append(",ra.Role_ID rId, CASE WHEN ra.Role_ID IS NULL THEN 'N' ELSE 'Y' END isCheck ");

		sql.append(",(SELECT COUNT(sub.id) FROM CMN_SEC_AUTHORITY sub ");
		sql.append(" INNER JOIN CMN_SEC_ROLE_AUTHORITY cra2 ON cra2.Role_ID =? and cra2.AUTHORITY_ID = sub.id ");
		params.add(compAdminRoleId);
		sql.append(" WHERE sub.parentAuthorityCode = auth.authorityCode and sub.TENANTID =auth.TENANTID and sub.AuthType = ?  and sub.BizType= ? ) subCount ");
		params.add(BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_M);
		params.add(BizConstant.COMMON_SECURITY_RESTYPE_P);

		sql.append("FROM CMN_SEC_AUTHORITY auth ");
		/*
		 * sql.append(
		 * "INNER JOIN CMN_SEC_ROLE_AUTHORITY cra ON cra.Role_ID =?  and cra.AUTHORITY_ID = auth.id "
		 * ); params.add(compAdminRoleId);
		 */
		sql.append("LEFT JOIN CMN_SEC_ROLE_AUTHORITY ra ON ra.Role_ID =?  and ra.AUTHORITY_ID = auth.id ");
		params.add(roleId);
		sql.append("WHERE  auth.TENANTID = ? and auth.AuthType = ?  and auth.BizType= ? ");
		// sql.append(" and auth.id <= 13628 ");
		params.add(tenantId);
		params.add(BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_M);
		params.add(bizType); // params.add(BizConstant.COMMON_SECURITY_RESTYPE_P);

		List<Authority> allMenuList = queryObjectListBySql(sql.toString(), Authority.class, params.toArray());

		Map<String, Authority> treeMap = new ConcurrentHashMap<String, Authority>();
		Set<Authority> resSet = new TreeSet<Authority>();

		for (Authority au : allMenuList) {
			au.setCheckId(au.getId());
			// isCheck
			// parentId
			// isParent Long subCount = au.getSubCount();
			// System.out.println(au.getParentId());
			treeMap.put(au.getAuthorityCode(), au);
		}

		for (Map.Entry<String, Authority> entry : treeMap.entrySet()) {
			// Long key = entry.getKey();
			Authority node = entry.getValue();

			if (StringUtils.isEmpty(node.getParentAuthorityCode())) {
				resSet.add(node);
			} else {
				String parentIdCode = node.getParentAuthorityCode();
				if (treeMap.containsKey(parentIdCode)) {
					treeMap.get(parentIdCode).addChildren(node);
				}
			}
		}

		return resSet;
	}

	@Override
	public List<Authority> findSubAuthorityFByRole(String roleId, String authId, String bizType, String tenantId) throws Exception {
		String compAdminRoleId = organizationDao.findOrgByUserAccountId(SecurityUtil.getCurrentEmpId(), SecurityUtil.getCurrentUserId());
		/*
		 * StringBuilder sql = new StringBuilder(); List<Object> params = new
		 * LinkedList<Object>(); sql.append(
		 * "SELECT auth.ID,auth.Name,auth.Parent_id,  auth.memo, auth.SortOrder  "
		 * );//.append(ename); sql.append(
		 * ",ra.Role_ID rId, CASE WHEN ra.Role_ID IS NULL THEN 'N' ELSE 'Y' END isCheck "
		 * );
		 * 
		 * sql.append("FROM CMN_SEC_AUTHORITY auth "); sql.append(
		 * "INNER JOIN CMN_SEC_ROLE_AUTHORITY cra ON cra.AUTHORITY_ID = auth.id and cra.Role_ID =? "
		 * ); params.add(compAdminRoleId);
		 * 
		 * sql.append(
		 * "LEFT JOIN CMN_SEC_ROLE_AUTHORITY ra ON ra.AUTHORITY_ID = auth.id and ra.Role_ID =? "
		 * ); sql.append(
		 * "WHERE auth.Parent_id=? and auth.BizType= ? AND auth.AuthType = ? ");
		 * 
		 * //sql.append(" and auth.id <= 13628 "); params.add(roleId);
		 * params.add(authId); params.add(bizType);//params.add(BizConstant.
		 * COMMON_SECURITY_RESTYPE_P);
		 * params.add(BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_F);
		 * 
		 * sql.append(" order by auth.SortOrder ");
		 * 
		 * List<Authority> allFunList = queryObjectListBySql(sql.toString(),
		 * Authority.class, params.toArray()); return allFunList;
		 */
		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("SELECT auth.ID,auth.Name,auth.authorityCode, auth.parentAuthorityCode,auth.SortOrder  ");// .append(ename);
		sql.append(",ra.Role_ID rId, CASE WHEN ra.Role_ID IS NULL THEN 'N' ELSE 'Y' END isCheck ");

		// sql.append(",(SELECT COUNT(sub.id) FROM CMN_SEC_AUTHORITY sub ");
		// sql.append(" INNER JOIN CMN_SEC_ROLE_AUTHORITY cra2 ON
		// cra2.AUTHORITY_ID = sub.id and cra2.Role_ID =? ");
		// params.add(compAdminRoleId);
		// sql.append(" WHERE sub.BizType= ? and sub.AuthType = ? and
		// sub.TENANTID =auth.TENANTID and sub.parentAuthorityCode =
		// auth.authorityCode ) subCount ");
		// params.add(BizConstant.COMMON_SECURITY_RESTYPE_P);
		// params.add(BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_F);

		sql.append("FROM CMN_SEC_AUTHORITY auth ");
		sql.append("INNER JOIN CMN_SEC_ROLE_AUTHORITY cra ON cra.AUTHORITY_ID = auth.id and cra.Role_ID =? ");
		params.add(compAdminRoleId);

		sql.append("LEFT JOIN CMN_SEC_ROLE_AUTHORITY ra ON ra.AUTHORITY_ID = auth.id and ra.Role_ID =? ");
		params.add(roleId);

		sql.append("WHERE  ");
		if (StringUtils.isNotEmpty(authId) && !authId.equals("0") && !authId.equals("-1")) {// if(authId!=null
																							// &&
																							// authId>0){
			// fei根节点
			sql.append(" auth.parentAuthorityCode = ? ");
			Authority oa = findEntityById(Authority.class, authId);
			params.add(oa.getAuthorityCode());
		} else {
			sql.append(" 1=2 ");
		}
		sql.append(" and auth.TENANTID = ? and auth.BizType= ? and auth.AuthType = ? ");
		// sql.append(" and auth.id <= 13628 ");
		params.add(tenantId);
		params.add(bizType); // params.add(BizConstant.COMMON_SECURITY_RESTYPE_P);
		params.add(BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_F);

		List<Authority> allFunList = queryObjectListBySql(sql.toString(), Authority.class, params.toArray());
		return allFunList;
	}

	@Override
	public void saveForAuthority(String roleId, String bizType, String menuIds, String funIds, String checkedMenuId, Boolean isChangCheckMenu, Boolean isChangeCheckFun, String tenantId) throws Exception {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		List<Object[]> saveArrayParam = new LinkedList<Object[]>();
		// List<Object> paramsList = new LinkedList<Object>();

		if (isChangCheckMenu) {
			// 左侧权限树有变动
			// DELETE a1 FROM ab a1 LEFT JOIN ab2 a2 ON a1.id = a2.id WHERE
			// a2.name='c'

			// 1 先清空所有的menu
			sql.append("delete aim from CMN_SEC_ROLE_AUTHORITY aim ");
			sql.append("inner join cmn_sec_authority au on aim.Authority_ID = au.id ");
			sql.append("where aim.ROLE_ID = :roleId  ");
			sql.append(" and au.BizType = :bizType ");// and a.authType='M'
			sql.append(" and au.AuthType = :AuthType ");
			sql.append(" and au.TENANTID = :tenantId ");

			params.put("roleId", roleId);
			params.put("bizType", bizType);
			params.put("AuthType", BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_M);
			params.put("tenantId", tenantId);

			batchUpdateBySql(sql.toString(), params);
			sql.setLength(0);
			params.clear();

			// 2 插入所有选择的menu
			if (StringUtils.isNotEmpty(menuIds)) {
				sql.append("insert into CMN_SEC_ROLE_AUTHORITY(ROLE_ID,Authority_ID) values (?,?)");
				String[] menuIdsSaveArray = menuIds.split("\\,");
				for (String aid : menuIdsSaveArray) {
					saveArrayParam.add(new Object[] { roleId, aid });
				}

				batchUpdateBySql(sql.toString(), saveArrayParam);
				sql.setLength(0);
				params.clear();
				saveArrayParam.clear();
			}
		}

		if (isChangeCheckFun) {
			// 选中权限树的右侧按钮 有变化
			Authority oa = findEntityById(Authority.class, checkedMenuId);

			// 1 清空左侧选中的menu的所有按钮节点的选择
			sql.append("delete aim from CMN_SEC_ROLE_AUTHORITY aim ");
			sql.append("inner join cmn_sec_authority au on aim.Authority_ID = au.id ");

			sql.append("where aim.ROLE_ID = :roleId  ");
			sql.append(" and au.parentAuthorityCode = :parentAuthorityCode ");
			sql.append(" and au.BizType = :bizType ");// and a.authType='M'
			sql.append(" and au.AuthType = :AuthType ");
			sql.append(" and au.TENANTID = :tenantId ");

			params.put("roleId", roleId);
			params.put("parentAuthorityCode", oa.getAuthorityCode());
			params.put("bizType", bizType);
			params.put("AuthType", BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_F);
			params.put("tenantId", tenantId);

			batchUpdateBySql(sql.toString(), params);
			sql.setLength(0);
			params.clear();

			// 2 添加所有选中按钮
			if (StringUtils.isNotEmpty(funIds)) {
				sql.append("insert into CMN_SEC_ROLE_AUTHORITY(ROLE_ID,Authority_ID) values (?,?)");
				String[] funIdsSaveArray = funIds.split("\\,");
				for (String aid : funIdsSaveArray) {
					saveArrayParam.add(new Object[] { roleId, aid });
				}
				batchUpdateBySql(sql.toString(), saveArrayParam);
				sql.setLength(0);
				saveArrayParam.clear();
			}
		}

		updateRoleUpdateTime(roleId);
	}

	private void updateRoleUpdateTime(String roleId) throws Exception {
		Role role = findEntityById(Role.class, roleId);
		role.setUpdateTime(new Date());
		saveOrUpdateOriginal(role);
	}

	@Override
	public void saveForAuthorityByAuthId(String roleId, String bizType, String authId, String tenantId) throws Exception {
		// String compAdminRoleId =
		// organizationDao.findOrgByUserAccountId(SecurityUtil.getCurrentEmpId(),
		// SecurityUtil.getCurrentUserId());

		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();

		Authority authority = findEntityById(Authority.class, authId);
		String authorityCode = authority.getAuthorityCode();// 所选节点权限编码

		// 先清除
		deleteForAuthorityByAuthId(roleId, bizType, authId, tenantId);

		// 在授权所有的子节点插入
		sql.append("insert into CMN_SEC_ROLE_AUTHORITY(ROLE_ID,Authority_ID)  ");// (?,?)
		sql.append(" select :roleId , au.id from cmn_sec_authority au ");
		params.put("roleId", roleId);
		/*
		 * sql.append(
		 * " INNER JOIN CMN_SEC_ROLE_AUTHORITY cra ON cra.AUTHORITY_ID = au.id and cra.Role_ID = :compAdminRoleId "
		 * ); params.put("compAdminRoleId",compAdminRoleId);
		 */
		sql.append(" where  au.TENANTID = :tenantId and au.AuthorityCode like :AuthorityCode  and au.BizType = :bizType  ");
		params.put("tenantId", tenantId);
		params.put("AuthorityCode", authorityCode + "%");
		params.put("bizType", bizType);
		batchUpdateBySql(sql.toString(), params);
		sql.setLength(0);
		params.clear();
	}

	@Override
	public void deleteForAuthorityByAuthId(String roleId, String bizType, String authId, String tenantId) throws Exception {
		// String compAdminRoleId =
		// organizationDao.findOrgByUserAccountId(SecurityUtil.getCurrentEmpId(),
		// SecurityUtil.getCurrentUserId());

		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();

		Authority authority = findEntityById(Authority.class, authId);
		String authorityCode = authority.getAuthorityCode();// 所选节点权限编码

		// 1 清空左侧选中的menu的所有按钮节点的选择
		sql.append("delete aim from cmn_sec_role_authority aim ");
		sql.append("inner join cmn_sec_authority au on aim.Authority_ID = au.id ");
		/*
		 * sql.append(
		 * "inner join  cmn_sec_role_authority cra ON cra.AUTHORITY_ID = aim.Authority_ID and cra.Role_ID = :compAdminRoleId "
		 * ); params.put("compAdminRoleId",compAdminRoleId);
		 */
		// .Role_ID = :compAdminRoleId
		// params.put("compAdminRoleId",compAdminRoleId);

		sql.append("where au.TENANTID = :tenantId and aim.ROLE_ID = :roleId  ");
		sql.append(" and au.AuthorityCode like :AuthorityCode");
		sql.append(" and au.BizType = :bizType ");
		// sql.append(" and au.AuthType = :AuthType ");//and a.authType='M'

		params.put("tenantId", tenantId);
		params.put("roleId", roleId);
		params.put("AuthorityCode", authorityCode + "%");
		params.put("bizType", bizType);
		// params.put("AuthType",BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_F);

		batchUpdateBySql(sql.toString(), params);
		sql.setLength(0);
		params.clear();
	}

	@Override
	public Role findLastRoleByAccount(String accountId) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("select role from ").append(Role.class.getName()).append(" role");
		sb.append(" inner join role.userAccounts uas");
		// sb.append(" left join fetch userAccount.userGroups ug left join fetch
		// ug.roles role2 left join fetch role2.authoritys ");
		// sb.append(" left join fetch role.authoritys au");
		// sb.append(" left join fetch au.resources ");
		sb.append(" where uas.id = :accountId order by role.updateTime desc ");

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", accountId);
		Role role = findFirstByHqlOrginial(sb.toString(), params);
		return role;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vix.common.security.dao.IRoleDao#saveForAuthorityByAuthIdOnly(java.
	 * lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void saveForAuthorityByAuthIdOnly(String roleId, String bizType, String authId, String tenantId) throws Exception {

		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		// 先清除
		//deleteForAuthorityByAuthId(roleId, bizType, authId, tenantId);
		sql.append("insert into CMN_SEC_ROLE_AUTHORITY(ROLE_ID,Authority_ID)  VALUES ('" + roleId + "','" + authId + "')");// (?,?)
		batchUpdateBySql(sql.toString(), params);
		sql.setLength(0);
		params.clear();
	}
}
