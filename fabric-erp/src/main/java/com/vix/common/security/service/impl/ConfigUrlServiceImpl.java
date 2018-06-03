package com.vix.common.security.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.security.dao.IConfigUrlDao;
import com.vix.common.security.entity.ConfigUrlAdd;
import com.vix.common.security.service.IConfigUrlService;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

/**
 * 业务层
 * @author shadow
 *
 */
@Service("configUrlService")
@Transactional
public class ConfigUrlServiceImpl extends BaseHibernateServiceImpl implements IConfigUrlService {

	@Resource(name = "configUrlDao")
    private IConfigUrlDao configUrlDao;
	
	/* (non-Javadoc)
	 * @see com.vix.common.security.service.IModuleService#findModulePager(com.vix.core.web.Pager, java.util.Map)
	 */
	@Override
	public Pager findConfigUrlPager(Pager pager, Map<String, Object> params)throws Exception {
		return configUrlDao.findConfigUrlAddPager(pager, params);
	}

	/* (non-Javadoc)
	 * @see com.vix.common.security.service.IModuleService#saveModule(com.vix.common.security.entity.Module)
	 */
	@Override
	public ConfigUrlAdd saveConfigUrl(ConfigUrlAdd entityForm)throws Exception{
		ConfigUrlAdd entity = null;
		if(entityForm!=null && StrUtils.isNotEmpty(entityForm.getId())){
			entity = configUrlDao.findEntityById(ConfigUrlAdd.class, entityForm.getId());
		}
		if(entity == null){
			entity = new ConfigUrlAdd();
		}
		
		Date now = new Date();
		//String currentUserAccountId = SecurityUtil.getCurrentUserId();
		
		if(entity.getId()==null){
			entity.setCreateTime(now);
		}
		entity.setUpdateTime(now);
		//entity.setCreateUser(new UserAccount(currentUserAccountId));
		
		entity.setName(entityForm.getName());
		entity.setCode(entityForm.getCode());
		entity.setIsRedirect(entityForm.getIsRedirect());
		entity.setUrl(entityForm.getUrl());
		
		configUrlDao.saveOrUpdateOriginal(entity);
		return entity;
	}
	
	
}
