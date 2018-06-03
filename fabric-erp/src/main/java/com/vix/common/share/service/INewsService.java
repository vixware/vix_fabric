package com.vix.common.share.service;

import java.util.Map;

import com.vix.common.share.entity.News;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface INewsService extends IBaseHibernateService{

	/**
	 * 
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	Pager findNewsPager(Pager pager, Map<String, Object> params) throws Exception;
	
	/**
	 * 
	 * @param entityForm
	 * @return
	 * @throws Exception
	 */
	News saveOrUpdateNews(News entityForm)throws Exception;
	
}
