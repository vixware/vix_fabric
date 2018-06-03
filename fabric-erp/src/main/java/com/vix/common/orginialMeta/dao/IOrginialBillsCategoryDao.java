package com.vix.common.orginialMeta.dao;

import java.util.List;
import java.util.Map;

import com.vix.common.orginialMeta.entity.OrginialBillsCategory;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.web.Pager;

/**
 * DAO
 * @author shadow
 *
 */
public interface IOrginialBillsCategoryDao extends IBaseHibernateDao {

	/**
	 * 分页查询
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findOrginialBillsCategoryPager(Pager pager,Map<String,Object> params)throws Exception;
	
	/**
	 * 查询所有列表
	 * @return
	 * @throws Exception
	 */
	public List<OrginialBillsCategory> findAllOrginialBillsCategoryList()throws Exception;
	
}
