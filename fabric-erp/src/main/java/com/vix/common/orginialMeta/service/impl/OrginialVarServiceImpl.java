package com.vix.common.orginialMeta.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.orginialMeta.dao.IOrginialVarDao;
import com.vix.common.orginialMeta.entity.OrginialVar;
import com.vix.common.orginialMeta.service.IOrginialVarService;
import com.vix.common.orginialMeta.util.VixVarUtil;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

@Service("orginialVarService")
@Transactional
public class OrginialVarServiceImpl  extends BaseHibernateServiceImpl implements IOrginialVarService{

	@Resource(name="orginialVarDao")
	private IOrginialVarDao orginialVarDao;
	
	@Override
	public Pager findOrginialVarPager(Pager pager, Map<String, Object> params)throws Exception {
		return orginialVarDao.findOrginialVarPager(pager, params);
	}

	@Override
	public OrginialVar saveOrginialVar(OrginialVar entityForm)throws Exception{
		OrginialVar entity = null;
		if(entityForm!=null && StrUtils.isNotEmpty(entityForm.getId())){
			entity = orginialVarDao.findEntityById(OrginialVar.class, entityForm.getId());
		}
		if(entity == null){
			entity = new OrginialVar();
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
		orginialVarDao.saveOrUpdateOriginal(entity);
		
		VixVarUtil.refreshVixTenantVar("");
		
		return entity;
	}
}
