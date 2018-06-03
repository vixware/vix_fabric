package com.vix.common.securityDra.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.vix.common.security.entity.DataResRowOwner;
import com.vix.common.securityDra.dao.IDataResRowOwnerDao;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

/**
 * 行集权限主体
 * 
 * @author Administrator
 *
 */
@Repository("dataResRowOwnerDao")
public class DataResRowOwnerDaoImpl extends BaseHibernateDaoImpl implements IDataResRowOwnerDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vix.common.securityDra.dao.IDataResRowOwnerDao#
	 * findUserAllDataResRowOwner(java.lang.Long)
	 */
	@Override
	public List<DataResRowOwner> findUserAllDataResRowOwner(String userId) throws Exception {
		StringBuilder sb = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		sb.append(" select dr from DataResRowOwner dr inner join dr.roles role inner join role.userAccounts ua where ");
		sb.append(" ua.id=:userAccountId ");
		params.put("userAccountId", userId);
		List<DataResRowOwner> drList = findAllByHql2(sb.toString(), params);
		return drList;
	}
}
