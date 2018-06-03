package com.vix.common.orginialMeta.dao;

import java.util.Map;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.web.Pager;

public interface IOrginialVarDao extends IBaseHibernateDao {

	/**
	 * @Title: findOrginialVarPager
	 * @Description: 系统变量的分页查询
	 * @param @param pager
	 * @param @param params
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return Pager    返回类型
	 * @throws
	 */
	Pager findOrginialVarPager(Pager pager, Map<String, Object> params)throws Exception;
}
