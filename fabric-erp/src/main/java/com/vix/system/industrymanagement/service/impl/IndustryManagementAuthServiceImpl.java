package com.vix.system.industrymanagement.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.orginialMeta.entity.OrginialAuthority;
import com.vix.core.constant.BizConstant;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.system.industrymanagement.dao.IIndustryManagementAuthDao;
import com.vix.system.industrymanagement.service.IIndustryManagementAuthService;

@Service("industryManagementAuthService")
@Transactional
public class IndustryManagementAuthServiceImpl extends BaseHibernateServiceImpl implements IIndustryManagementAuthService {

	@Resource(name = "industryManagementAuthDao")
	private IIndustryManagementAuthDao industryManagementAuthDao;

	/*
	 * @see
	 * com.vix.system.industrymanagement.service.IIndustryManagementAuthService#
	 * findAllAuthorityMWithIndustryMgtModule(java.lang.Long, java.lang.String)
	 */
	@Override
	public Set<OrginialAuthority> findAllOrginialAuthorityMWithIndustryMgtModule(String industryMgtModuleId, String bizType) throws Exception {
		return industryManagementAuthDao.findAllOrginialAuthorityMWithIndustryMgtModule(industryMgtModuleId, bizType);
	}

	/*
	 * @see
	 * com.vix.system.industrymanagement.service.IIndustryManagementAuthService#
	 * findSubAuthorityFByIndustryMgtModule(java.lang.Long, java.lang.Long,
	 * java.lang.String)
	 */
	@Override
	public List<OrginialAuthority> findSubOrginialAuthorityFByIndustryMgtModule(String industryMgtModuleId, String orginialAuthCode, String bizType) throws Exception {
		return industryManagementAuthDao.findSubOrginialAuthorityFByIndustryMgtModule(industryMgtModuleId, orginialAuthCode, bizType);
	}

	@Override
	public void saveForOrginialAuthority(String industryMgtModuleId, String bizType, String menuIds, String funIds, String checkedMenuId, Boolean isChangCheckMenu, Boolean isChangeCheckFun) throws Exception {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		List<Object[]> saveArrayParam = new LinkedList<Object[]>();
		// List<Object> paramsList = new LinkedList<Object>();

		if (isChangCheckMenu) {
			// 左侧权限树有变动
			// DELETE a1 FROM ab a1 LEFT JOIN ab2 a2 ON a1.id = a2.id WHERE
			// a2.name='c'

			// 1 先清空所有的menu
			sql.append("delete aim from CMN_SEC_ORGINIAL_AUTH_IND_MODULE aim ");
			sql.append("inner join CMN_SEC_ORGINIAL_AUTH au on aim.Authority_ID = au.id ");
			sql.append("where aim.IndustryManagementModule_ID = :industryMgtModuleId  ");
			sql.append(" and au.BizType = :bizType ");// and a.authType='M'
			sql.append(" and au.AuthType = :AuthType ");

			params.put("industryMgtModuleId", industryMgtModuleId);
			params.put("bizType", bizType);
			params.put("AuthType", BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_M);

			industryManagementAuthDao.batchUpdateBySql(sql.toString(), params);
			sql.setLength(0);
			params.clear();

			// 2 插入所有选择的menu
			if (StringUtils.isNotEmpty(menuIds)) {
				sql.append("insert into CMN_SEC_ORGINIAL_AUTH_IND_MODULE(IndustryManagementModule_ID,Authority_ID) values (?,?)");
				String[] menuIdsSaveArray = menuIds.split("\\,");
				for (String aid : menuIdsSaveArray) {
					saveArrayParam.add(new Object[]{industryMgtModuleId, aid});
				}
				industryManagementAuthDao.batchUpdateBySql(sql.toString(), saveArrayParam);
				sql.setLength(0);
				params.clear();
				saveArrayParam.clear();
			}
		}

		if (isChangeCheckFun) {
			// 选中权限树的右侧按钮 有变化
			OrginialAuthority oa = findEntityById(OrginialAuthority.class, checkedMenuId);

			// 1 清空左侧选中的menu的所有按钮节点的选择
			sql.append("delete aim from CMN_SEC_ORGINIAL_AUTH_IND_MODULE aim ");
			sql.append("inner join CMN_SEC_ORGINIAL_AUTH au on aim.Authority_ID = au.id ");

			sql.append("where aim.IndustryManagementModule_ID = :industryMgtModuleId  ");
			sql.append(" and au.parentAuthorityCode = :parentAuthorityCode ");
			sql.append(" and au.BizType = :bizType ");// and a.authType='M'
			sql.append(" and au.AuthType = :AuthType ");

			params.put("industryMgtModuleId", industryMgtModuleId);
			params.put("parentAuthorityCode", oa.getAuthorityCode());
			params.put("bizType", bizType);
			params.put("AuthType", BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_F);

			industryManagementAuthDao.batchUpdateBySql(sql.toString(), params);
			sql.setLength(0);
			params.clear();

			// 2 添加所有选中按钮
			if (StringUtils.isNotEmpty(funIds)) {
				sql.append("insert into CMN_SEC_ORGINIAL_AUTH_IND_MODULE(IndustryManagementModule_ID,Authority_ID) values (?,?)");
				String[] funIdsSaveArray = funIds.split("\\,");
				for (String aid : funIdsSaveArray) {
					saveArrayParam.add(new Object[]{industryMgtModuleId, aid});
				}
				industryManagementAuthDao.batchUpdateBySql(sql.toString(), saveArrayParam);
				sql.setLength(0);
				saveArrayParam.clear();
			}
		}

	}

	@Override
	public void saveForOrginialAuthorityByAuthId(String industryMgtModuleId, String bizType, String orginialAuthId) throws Exception {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();

		OrginialAuthority authority = industryManagementAuthDao.findEntityById(OrginialAuthority.class, orginialAuthId);
		String authorityCode = authority.getAuthorityCode();// 所选节点权限编码

		// 先清除
		deleteForOrginialAuthorityByAuthId(industryMgtModuleId, bizType, orginialAuthId);

		// 在授权所有的子节点插入
		sql.append("insert into CMN_SEC_ORGINIAL_AUTH_IND_MODULE(IndustryManagementModule_ID,Authority_ID)  ");// (?,?)
		sql.append(" select :industryMgtModuleId , au.id from CMN_SEC_ORGINIAL_AUTH au ");
		sql.append(" where au.AuthorityCode like :AuthorityCode  and au.BizType = :bizType  ");

		params.put("industryMgtModuleId", industryMgtModuleId);
		params.put("AuthorityCode", authorityCode + "%");
		params.put("bizType", bizType);
		industryManagementAuthDao.batchUpdateBySql(sql.toString(), params);
		sql.setLength(0);
		params.clear();
	}

	@Override
	public void deleteForOrginialAuthorityByAuthId(String industryMgtModuleId, String bizType, String orginialAuthId) throws Exception {
		StringBuilder sql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();

		OrginialAuthority authority = industryManagementAuthDao.findEntityById(OrginialAuthority.class, orginialAuthId);
		String authorityCode = authority.getAuthorityCode();// 所选节点权限编码

		// 1 清空左侧选中的menu的所有按钮节点的选择
		sql.append("delete aim from CMN_SEC_ORGINIAL_AUTH_IND_MODULE aim ");
		sql.append("inner join CMN_SEC_ORGINIAL_AUTH au on aim.Authority_ID = au.id ");

		sql.append("where aim.IndustryManagementModule_ID = :industryMgtModuleId  ");
		sql.append(" and au.AuthorityCode like :AuthorityCode");
		sql.append(" and au.BizType = :bizType ");
		// sql.append(" and au.AuthType = :AuthType ");//and a.authType='M'

		params.put("industryMgtModuleId", industryMgtModuleId);
		params.put("AuthorityCode", authorityCode + "%");
		params.put("bizType", bizType);
		// params.put("AuthType",BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_F);

		industryManagementAuthDao.batchUpdateBySql(sql.toString(), params);
		sql.setLength(0);
		params.clear();
	}
	/**
	 * Map<String,Object> params = new HashMap<String, Object>();
	 * sql.append("delete from CMN_SEC_ROLE_AUTHORITY where Role_ID=:roleId");
	 * params.put("roleId", roleId);
	 * //authorityDao.jdbcBatchUpdate(sql.toString(), roleId);
	 * authorityDao.batchUpdateBySql(sql.toString(), params); params.clear();
	 * 
	 * sql.setLength(0); sql.append("insert into
	 * CMN_SEC_ROLE_AUTHORITY(Role_ID,Authority_ID) values (?,?)");
	 * List<Object[]> saveArrayParam = new LinkedList<Object[]>();
	 * if(authorityIdList!=null){ for(Long aid:authorityIdList){
	 * saveArrayParam.add(new Object[]{roleId,aid}); } }
	 * 
	 * //authorityDao.jdbcBatchUpdate(sql.toString(), saveArrayParam);
	 * authorityDao.batchUpdateBySql(sql.toString(), saveArrayParam);
	 */

}
