package com.vix.common.securityDra.dao;

import java.util.List;

import com.vix.common.security.entity.DataResRowOwner;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

public interface IDataResRowOwnerDao extends IBaseHibernateDao{

	
	/**
	 * 查询用户的 行集权限所有的考评主体
	 * @param userId
	 * @return
	 */
	List<DataResRowOwner> findUserAllDataResRowOwner(String userId)throws Exception;
}
