package com.vix.common.securityDra.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.securityDra.dao.IDataResRowMethodDao;
import com.vix.common.securityDra.hql.DataResRowMethodHqlProvider;
import com.vix.common.securityDra.service.IDataResRowMethodService;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.web.Pager;

/**
 * 拦截方法配置业务层
 * @author Administrator
 *
 */
@Service("dataResRowMethodService")
@Transactional
public class DataResRowMethodServiceImpl extends BaseHibernateServiceImpl	implements IDataResRowMethodService {

	@Resource(name="dataResRowMethodDao")
	private IDataResRowMethodDao dataResRowMethodDao;
	
	@Resource(name="dataResRowMethodHqlProvider")
	private DataResRowMethodHqlProvider dataResRowMethodHqlProvider;
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowMethodService#findDataResRowMethodPager(com.vix.core.web.Pager, java.util.Map)
	 */
	@Override
	public Pager findDataResRowMethodPager(Pager pager,Map<String,Object> params) throws Exception{
		
		 StringBuilder hql = dataResRowMethodHqlProvider.findDataResRowMethodList(params, pager);
		 //pager = dataResRowPolicyObjDao.findPagerByHql2(pager,dataResRowPolicyObjHqlProvider.entityAsName(),hql.toString(), params);
		 pager = dataResRowMethodDao.findPager2ByHql(pager, dataResRowMethodHqlProvider.entityAsName(), hql.toString(), params);
		 //pager = dataResRowPolicyObjDao.findPagerByHql(pager,hql.toString(), params);
		 return pager;
	}
	
	
	/* (non-Javadoc)
	 * @see com.vix.common.securityDra.service.IDataResRowMethodService#findSelectDataResRowMethodPager(com.vix.core.web.Pager, java.util.Map)
	 */
	@Override
	public Pager findSelectDataResRowMethodPager(Pager pager,Map<String,Object> params) throws Exception{
		
		 StringBuilder hql = dataResRowMethodHqlProvider.findSelectDataResRowMethodList(params, pager);
		 //pager = dataResRowPolicyObjDao.findPagerByHql2(pager,dataResRowPolicyObjHqlProvider.entityAsName(),hql.toString(), params);
		 pager = dataResRowMethodDao.findPager2ByHql(pager, dataResRowMethodHqlProvider.entityAsName(), hql.toString(), params);
		 //pager = dataResRowPolicyObjDao.findPagerByHql(pager,hql.toString(), params);
		 return pager;
	}
}
