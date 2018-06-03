package com.vix.common.security.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.security.dao.IUserAccountProxyDao;
import com.vix.common.security.entity.UserAccountProxyApply;
import com.vix.common.security.service.IUserAccountProxyService;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.web.Pager;

@Service("userAccountProxyService")
@Transactional
public class UserAccountProxyServiceImpl extends BaseHibernateServiceImpl
		implements IUserAccountProxyService {

	@javax.annotation.Resource(name = "userAccountProxyDao")
	private IUserAccountProxyDao userAccountProxyDao;

	@Override
	@Transactional(readOnly=true)
	public Pager findAcceptUserAccountProxyPager(Pager pager,Map<String, Object> params) throws Exception {
		return userAccountProxyDao.findAcceptUserAccountProxyPager(pager, params);
	}

	@Override
	@Transactional(readOnly=true)
	public Pager findApplyUserAccountProxyPager(Pager pager,	Map<String, Object> params) throws Exception {
		return userAccountProxyDao.findApplyUserAccountProxyPager(pager, params);
	}

	@Override
	public void saveOrUpdateProxyConfig(UserAccountProxyApply userAccountProxyApply) throws Exception {
		userAccountProxyDao.saveOrUpdateProxyConfig(userAccountProxyApply);
	}

}
