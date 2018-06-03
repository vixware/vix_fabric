package com.vix.common.org.dao;

import com.vix.common.org.entity.OrgDrpView;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

/**
 * 组织机构    分销 dao
 * @author shadow
 *
 */
public interface IOrgDrpViewDao extends IBaseHibernateDao {

	/**
	 * 查询根节点
	 * @param innerCode
	 * @return
	 * @throws Exception
	 */
	OrgDrpView findOrgByInnerCode(String innerCode)throws Exception;
}
