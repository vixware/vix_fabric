package com.vix.ebusiness.item.itemdownload.dao;

import java.util.Map;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

public interface IItemProcessDao extends IBaseHibernateDao {
	public <T> T findItemByHql(String hql, Map<String, Object> params) throws Exception;
}
