package com.vix.common.orginialMeta.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.orginialMeta.dao.IOrginialBillsCategoryDao;
import com.vix.common.orginialMeta.entity.OrginialBillsCategory;
import com.vix.common.orginialMeta.service.IOrginialBillsCategoryService;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

/**
 * 模块的业务层
 * @author shadow
 *
 */
@Service("orginialBillsCategoryService")
@Transactional
public class OrginialBillsCategoryServiceImpl extends BaseHibernateServiceImpl implements IOrginialBillsCategoryService {

	@Resource(name = "orginialBillsCategoryDao")
    private IOrginialBillsCategoryDao orginialBillsCategoryDao;
	
	/* (non-Javadoc)
	 * @see com.vix.common.security.service.IModuleService#findModulePager(com.vix.core.web.Pager, java.util.Map)
	 */
	@Override
	public Pager findOrginialBillsCategoryPager(Pager pager, Map<String, Object> params)throws Exception {
		return orginialBillsCategoryDao.findOrginialBillsCategoryPager(pager, params);
	}

	/* (non-Javadoc)
	 * @see com.vix.common.security.service.IModuleService#saveModule(com.vix.common.security.entity.Module)
	 */
	@Override
	public OrginialBillsCategory saveOrginialBillsCategory(OrginialBillsCategory entityForm)throws Exception{
		OrginialBillsCategory entity = null;
		if(entityForm!=null && StrUtils.isNotEmpty(entityForm.getId())){
			entity = orginialBillsCategoryDao.findEntityById(OrginialBillsCategory.class, entityForm.getId());
		}
		if(entity == null){
			entity = new OrginialBillsCategory();
		}
		
		Date now = new Date();
		String currentUserAccountId = SecurityUtil.getCurrentUserId();
		
		if(entity.getId()==null){
			entity.setCreateTime(now);
		}
		entity.setUpdateTime(now);
		//entity.setCreateUser(new UserAccount(currentUserAccountId));
		
		entity.setCategoryName(entityForm.getCategoryName());
		entity.setCategoryCode(entityForm.getCategoryCode());
		//entity.setCategoryDescription(entityForm.getCategoryDescription());
		orginialBillsCategoryDao.saveOrUpdateOriginal(entity);
		return entity;
	}
	
}
