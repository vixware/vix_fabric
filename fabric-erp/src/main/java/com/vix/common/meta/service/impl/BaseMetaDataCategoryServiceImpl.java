package com.vix.common.meta.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.meta.dao.IBaseMetaDataCategoryDao;
import com.vix.common.meta.entity.BaseMetaDataCategory;
import com.vix.common.meta.hql.BaseMetaDataCategoryHqlProvider;
import com.vix.common.meta.service.IBaseMetaDataCategoryService;
import com.vix.common.meta.vo.BaseMetaDataCategoryImpVo;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

@Service("baseMetaDataCategoryService")
@Transactional
public class BaseMetaDataCategoryServiceImpl  extends BaseHibernateServiceImpl implements IBaseMetaDataCategoryService{

	@Resource(name="baseMetaDataCategoryDao")
	private IBaseMetaDataCategoryDao baseMetaDataCategoryDao;
	
	@Resource(name="baseMetaDataCategoryHqlProvider")
	private BaseMetaDataCategoryHqlProvider baseMetaDataCategoryHqlProvider;

	
	/**
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<BaseMetaDataCategory> findAllBaseMetaDataCategory()throws Exception{
		  StringBuilder sb = new StringBuilder();
		  Map<String,Object> params = new HashMap<String, Object>();
	 	  String ename = baseMetaDataCategoryHqlProvider.entityAsName();
	 	  sb.append("select ").append(ename);
	 	  sb.append(" from ").append(BaseMetaDataCategory.class.getName()).append(" ").append(ename);
	 	  sb.append(" where 1=1 ");
	 	  
	 	  HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
	 	  
	 	  List<BaseMetaDataCategory> all = baseMetaDataCategoryDao.findAllByHql2(sb.toString(),params );
	 	  return all;
	}
	
	/**
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public Pager findBaseMetaDataCategoryPager(Pager pager, Map<String, Object> params) throws Exception{
		StringBuilder hql = baseMetaDataCategoryHqlProvider.findBaseMetaDataCategoryList(params, pager);
		
		//HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		
		pager = baseMetaDataCategoryDao.findPager2ByHql(pager, baseMetaDataCategoryHqlProvider.entityAsName(), hql.toString(), params);
		return pager;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.meta.service.IBaseMetaDataCategoryService#saveOrUpdateBaseMetaDataCategory(com.vix.common.meta.entity.BaseMetaDataCategory)
	 */
	@Override
	public BaseMetaDataCategory saveOrUpdateBaseMetaDataCategory(BaseMetaDataCategory entityForm)throws Exception{
		BaseMetaDataCategory entity = null;
		if(entityForm!=null && StrUtils.isNotEmpty(entityForm.getId())){
			entity = baseMetaDataCategoryDao.findEntityById(BaseMetaDataCategory.class, entityForm.getId());
		}
		if(entity == null){
			entity = new BaseMetaDataCategory();
		}
		
		Date now = new Date();
		String currentUserAccountId = SecurityUtil.getCurrentUserId();
		
		if(entity.getId()==null){
			entity.setCreateTime(now);
		}
		entity.setUpdateTime(now);
		//entity.setCreateUser(new UserAccount(currentUserAccountId));
		
		entity.setCategoryName(entityForm.getCategoryName());
		entity.setCode(entityForm.getCode());
		
		baseMetaDataCategoryDao.saveOrUpdate(entity);
		return entity;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.meta.service.IBaseMetaDataCategoryService#saveForImportBaseMetaDataCategory(java.util.List)
	 */
	@Override
	public void saveForImportBaseMetaDataCategory(List<BaseMetaDataCategoryImpVo> voList)throws Exception{
		baseMetaDataCategoryDao.saveForImportBaseMetaDataCategory(voList);
		
	}
}
