package com.vix.common.orginialMeta.service;

import java.util.Map;

import com.vix.common.orginialMeta.entity.OrginialBillsCategory;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IOrginialBillsCategoryService extends IBaseHibernateService{

	/**
	 * 单据分类的分页查询
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findOrginialBillsCategoryPager(Pager pager,Map<String,Object> params)throws Exception;
	
	/**
	 * 保存OrginialBillsCategory
	 * @param module
	 * @return
	 * @throws Exception
	 */
	OrginialBillsCategory saveOrginialBillsCategory(OrginialBillsCategory orginialBillsCategory)throws Exception;

}
