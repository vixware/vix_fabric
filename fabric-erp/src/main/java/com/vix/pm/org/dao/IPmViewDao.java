package com.vix.pm.org.dao;

import java.util.List;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.pm.org.entity.PmOrgView;

public interface IPmViewDao extends IBaseHibernateDao {

	/**
	 * 查询业务组织视图和业务组织的 联合视图
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<PmOrgView> findOrgViewList(String id) throws Exception;
}
