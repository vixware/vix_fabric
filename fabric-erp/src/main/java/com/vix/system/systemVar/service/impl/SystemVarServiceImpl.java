package com.vix.system.systemVar.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.security.util.SecurityUtil;
import com.vix.common.tag.util.VixVarTenantUtil;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.system.systemVar.dao.ISystemVarDao;
import com.vix.system.systemVar.entity.SystemVar;
import com.vix.system.systemVar.service.ISystemVarService;

@Service("systemVarService")
@Transactional
public class SystemVarServiceImpl  extends BaseHibernateServiceImpl implements ISystemVarService{

	@Resource(name="systemVarDao")
	private ISystemVarDao systemVarDao;
	
	@Override
	public Pager findSystemVarPager(Pager pager, Map<String, Object> params)throws Exception {
		return systemVarDao.findSystemVarPager(pager, params);
	}

	@Override
	public SystemVar saveSystemVar(SystemVar entityForm)throws Exception{
		SystemVar entity = null;
		if(entityForm!=null && StrUtils.isNotEmpty(entityForm.getId())){
			entity = systemVarDao.findEntityById(SystemVar.class, entityForm.getId());
		}
		if(entity == null){
			entity = new SystemVar();
		}
		
		Date now = new Date();
		String currentUserAccountId = SecurityUtil.getCurrentUserId();
		
		if(entity.getId()==null){
			entity.setCreateTime(now);
		}
		entity.setUpdateTime(now);
		//entity.setCreateUser(new UserAccount(currentUserAccountId));
		
		entity.setVarCode(entityForm.getVarCode());
		entity.setDefaultValue(entityForm.getDefaultValue());
		entity.setDescription(entityForm.getDescription());
		systemVarDao.saveOrUpdate(entity);
		
		VixVarTenantUtil.refreshVixTenantVar(entity.getTenantId());
		
		return entity;
	}
}
