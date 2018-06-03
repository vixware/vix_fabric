package com.vix.common.orginialMeta.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.orginialMeta.dao.IOrginialBaseMetaDataCategoryDao;
import com.vix.common.orginialMeta.entity.OrginialBaseMetaDataCategory;
import com.vix.common.orginialMeta.hql.OrginialBaseMetaDataCategoryHqlProvider;
import com.vix.common.orginialMeta.service.IOrginialBaseMetaDataCategoryService;
import com.vix.common.orginialMeta.vo.OrginialBaseMetaDataCategoryImpVo;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

@Service("orginialBaseMetaDataCategoryService")
@Transactional
public class OrginialBaseMetaDataCategoryServiceImpl  extends BaseHibernateServiceImpl implements IOrginialBaseMetaDataCategoryService{

	@Resource(name="orginialBaseMetaDataCategoryDao")
	private IOrginialBaseMetaDataCategoryDao baseMetaDataCategoryDao;
	
	@Resource(name="orginialBaseMetaDataCategoryHqlProvider")
	private OrginialBaseMetaDataCategoryHqlProvider baseMetaDataCategoryHqlProvider;

	
	/**
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<OrginialBaseMetaDataCategory> findAllBaseMetaDataCategory()throws Exception{
		  StringBuilder sb = new StringBuilder();
		  Map<String,Object> params = new HashMap<String, Object>();
	 	  String ename = baseMetaDataCategoryHqlProvider.entityAsName();
	 	  sb.append("select ").append(ename);
	 	  sb.append(" from ").append(OrginialBaseMetaDataCategory.class.getName()).append(" ").append(ename);
	 	  sb.append(" where 1=1 ");
	 	  
	 	 HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
	 	 HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
			
	 	  
	 	  List<OrginialBaseMetaDataCategory> all = baseMetaDataCategoryDao.findAllByHql2(sb.toString(),params );
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
		
		HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		
		pager = baseMetaDataCategoryDao.findPager2ByHql(pager, baseMetaDataCategoryHqlProvider.entityAsName(), hql.toString(), params);
		return pager;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.meta.service.IBaseMetaDataCategoryService#saveOrUpdateBaseMetaDataCategory(com.vix.common.meta.entity.BaseMetaDataCategory)
	 */
	@Override
	public OrginialBaseMetaDataCategory saveOrUpdateBaseMetaDataCategory(OrginialBaseMetaDataCategory entityForm)throws Exception{
		OrginialBaseMetaDataCategory entity = null;
		if(entityForm!=null && StrUtils.isNotEmpty(entityForm.getId())){
			entity = baseMetaDataCategoryDao.findEntityById(OrginialBaseMetaDataCategory.class, entityForm.getId());
		}
		if(entity == null){
			entity = new OrginialBaseMetaDataCategory();
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
		
		baseMetaDataCategoryDao.saveOrUpdateOriginal(entity);
		return entity;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.meta.service.IBaseMetaDataCategoryService#saveForImportBaseMetaDataCategory(java.util.List)
	 */
	@Override
	public void saveForImportBaseMetaDataCategory(List<OrginialBaseMetaDataCategoryImpVo> voList)throws Exception{
		baseMetaDataCategoryDao.saveForImportBaseMetaDataCategory(voList);
		
	}

}
