package com.vix.restService.shop.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.restService.shop.service.IItemCurrencyDateRestService;

/**
 * @ClassName: SubmoduleRestServiceImpl
 * @Description: 模块的RestService业务层
 * @author wangmingchen
 * @date 2015年2月25日 上午8:31:45
 */
@Service("itemCurrencyDateRestService")
@Transactional
public class ItemCurrencyDateRestServiceImpl extends BaseHibernateServiceImpl implements IItemCurrencyDateRestService {

	private static final long serialVersionUID = 1L;

	@Resource(name = "baseHibernateDao")
	private IBaseHibernateDao baseHibernateDao;

	@Override
	public <T> T findObjectByHqlNoTenantId(String hql, Map<String, Object> params) throws Exception {
		return baseHibernateDao.findObjectByHqlNoTenantId(hql, params);
	}
}
