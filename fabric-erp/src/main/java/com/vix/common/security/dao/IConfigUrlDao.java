package com.vix.common.security.dao;

import java.util.List;
import java.util.Map;

import com.vix.common.security.entity.ConfigUrlAdd;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.web.Pager;

/**
 * 分页DAO
 * @author shadow
 *
 */
public interface IConfigUrlDao extends IBaseHibernateDao {

	/**
	 * 分页查询
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findConfigUrlAddPager(Pager pager,Map<String,Object> params)throws Exception;
	
	/**
	 * 查询所有列表
	 * @return
	 * @throws Exception
	 */
	public List<ConfigUrlAdd> findAllConfigUrlAddList()throws Exception;
	
}
