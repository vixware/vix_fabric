package com.vix.common.securityDra.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.vix.common.meta.entity.BaseMetaData;
import com.vix.common.meta.entity.BaseMetaDataDefine;
import com.vix.common.security.entity.DataResRowPolicyObj;
import com.vix.common.securityDra.dao.IDataResRowPolicyObjDao;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

/**
 * 行集数据权限的 sql条件配置
 * 
 * @author Administrator
 *
 */
@Repository("dataResRowPolicyObjDao")
public class DataResRowPolicyObjDaoImpl extends BaseHibernateDaoImpl implements IDataResRowPolicyObjDao {

	@Override
	public List<DataResRowPolicyObj> findPolicyObjListByUserId(String userId) throws Exception {
		StringBuilder sb = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		sb.append(" select drPolicyObj from DataResRowPolicyObj drPolicyObj ");
		sb.append(" inner join drPolicyObj.dataResRowPolicys drPolicy ");
		sb.append(" inner join drPolicy.dataResRowOwners drOwner ");
		sb.append(" inner join drOwner.roles role ");
		sb.append(" inner join role.userAccounts ua ");
		// sb.append(" left join fetch drPolicyObj.baseMetaData ");
		sb.append(" left join drPolicyObj.baseMetaData  ");

		sb.append(" where ");
		sb.append(" ua.id=:userAccountId ");
		params.put("userAccountId", userId);

		// List<DataResRowPolicyObj> drList = findAllByHql2(sb.toString(),
		// params);
		List<DataResRowPolicyObj> drList = findAllByHql2NoTenantId(sb.toString(), "drPolicyObj", params);

		return drList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.common.securityDra.dao.IDataResRowPolicyObjDao#
	 * findPolicyObjMapByUserId(java.lang.String)
	 */
	@Override
	public Map<String, DataResRowPolicyObj> findPolicyObjMapByUserId(String userId) throws Exception {
		// key 元数据对象id value DataResRowPolicyObj
		Map<String, DataResRowPolicyObj> map = new HashMap<String, DataResRowPolicyObj>();

		List<DataResRowPolicyObj> drList = findPolicyObjListByUserId(userId);
		if (drList != null) {
			for (DataResRowPolicyObj drp : drList) {
				// String metadatasrcName = drp.getMetaDataSrcName();
				String metadataId = drp.getBaseMetaData().getId();
				if (map.containsKey(metadataId)) {
					DataResRowPolicyObj oldDrp = map.get(metadataId);
					if (drp.getPriory() > oldDrp.getPriory()) {
						map.put(metadataId, drp);
					}
				} else {
					map.put(metadataId, drp);
				}
			}
		}

		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.common.securityDra.dao.IDataResRowPolicyObjDao#
	 * findPolicyObjMapStrByUserId(java.lang.String)
	 */
	@Override
	public Map<String, DataResRowPolicyObj> findPolicyObjMapStrByUserId(String userId) throws Exception {
		// key 元数据对象全称 value DataResRowPolicyObj
		Map<String, DataResRowPolicyObj> map = new HashMap<String, DataResRowPolicyObj>();

		List<DataResRowPolicyObj> drList = findPolicyObjListByUserId(userId);
		if (drList != null) {
			for (DataResRowPolicyObj drp : drList) {
				// String metadatasrcName = drp.getMetaDataSrcName();
				String metadataId = drp.getBaseMetaData().getId();
				String metadataName = drp.getBaseMetaData().getMetaDataName();
				if (map.containsKey(metadataName)) {
					DataResRowPolicyObj oldDrp = map.get(metadataName);
					if (drp.getPriory() > oldDrp.getPriory()) {
						map.put(metadataName, drp);
					}
				} else {
					map.put(metadataName, drp);
				}
			}
		}

		return map;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.common.securityDra.dao.IDataResRowPolicyObjDao#
	 * findBaseMetadataDefineList(java.lang.String)
	 */
	@Override
	public List<BaseMetaDataDefine> findBaseMetadataDefineList(String metadataId) throws Exception {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", metadataId);
		hql.append("select md from BaseMetaDataDefine md where md.baseMetaData.id = :id ");
		List<BaseMetaDataDefine> mdList = findAllByHql2(hql.toString(), params);
		return mdList;
	}

	@Override
	public List<BaseMetaDataDefine> findBaseMetadataDefineListNoId(String metadataId) throws Exception {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", metadataId);
		params.put("proName", "id");
		hql.append("select md from BaseMetaDataDefine md where md.baseMetaData.id=:id and md.property != :proName ");
		List<BaseMetaDataDefine> mdList = findAllByHql2(hql.toString(), params);
		return mdList;
	}

	@Override
	public BaseMetaData findBaseMetaDataByMetadataName(String metadataName) throws Exception {
		StringBuilder hql = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("metadataName", metadataName);
		hql.append("select md from BaseMetaData md where md.metaDataName = :metadataName ");
		List<BaseMetaData> mdList = findAllByHql2(hql.toString(), params);
		if (mdList != null && !mdList.isEmpty()) {
			return mdList.get(0);
		}
		return null;
	}
}
