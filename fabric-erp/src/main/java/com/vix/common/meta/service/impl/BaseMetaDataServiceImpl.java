package com.vix.common.meta.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.meta.dao.IBaseMetaDataDao;
import com.vix.common.meta.entity.BaseMetaData;
import com.vix.common.meta.entity.BaseMetaDataCategory;
import com.vix.common.meta.hql.BaseMetaDataHqlProvider;
import com.vix.common.meta.service.IBaseMetaDataService;
import com.vix.common.meta.vo.BaseMetaDataDefineImpVo;
import com.vix.common.meta.vo.BaseMetaDataExtendImpVo;
import com.vix.common.meta.vo.BaseMetaDataImpVo;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

@Service("baseMetaDataService")
@Transactional
public class BaseMetaDataServiceImpl  extends BaseHibernateServiceImpl implements IBaseMetaDataService{

	@Resource(name="baseMetaDataDao")
	private IBaseMetaDataDao baseMetaDataDao;
	
	@Resource(name="baseMetaDataHqlProvider")
	private BaseMetaDataHqlProvider baseMetaDataHqlProvider;

	/**
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public Pager findBaseMetaDataPager(Pager pager, Map<String, Object> params) throws Exception{
		StringBuilder hql = baseMetaDataHqlProvider.findBaseMetaDataList(params, pager);
		
		//HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		
		pager = baseMetaDataDao.findPager2ByHql(pager, baseMetaDataHqlProvider.entityAsName(), hql.toString(), params);
		return pager;
	}
	
	@Override
	public BaseMetaData saveOrUpdateBaseMetaData(BaseMetaData entityForm)throws Exception{
		BaseMetaData entity = null;
		if(entityForm!=null && StrUtils.isNotEmpty(entityForm.getId())){
			entity = baseMetaDataDao.findEntityById(BaseMetaData.class, entityForm.getId());
		}
		if(entity == null){
			entity = new BaseMetaData();
		}
		
		Date now = new Date();
		String currentUserAccountId = SecurityUtil.getCurrentUserId();
		
		if(entity.getId()==null){
			entity.setCreateTime(now);
		}
		entity.setUpdateTime(now);
		//entity.setCreateUser(new UserAccount(currentUserAccountId));
		
		//entity.setCategoryName(entityForm.getCategoryName());
		entity.setCode(entityForm.getCode());
		entity.setMetaDataDisplayName(entityForm.getMetaDataDisplayName());
		entity.setMetaDataName(entityForm.getMetaDataName());
		
		BaseMetaDataCategory cate = baseMetaDataDao.findEntityById(BaseMetaDataCategory.class, entityForm.getBaseMetaDataCategory().getId());
		if(cate!=null){
			entity.setBaseMetaDataCategory(cate);
		}
		
		baseMetaDataDao.saveOrUpdate(entity);
		return entity;
	}
	
	
	/* (non-Javadoc)
	 * @see com.vix.common.meta.service.IBaseMetaDataService#saveForImportBaseMetaData(java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public void saveForImportBaseMetaData(List<BaseMetaDataImpVo> metadataVoList,List<BaseMetaDataDefineImpVo> metadataDefineVoList,List<BaseMetaDataExtendImpVo> metadataExtendVoList)throws Exception{
		baseMetaDataDao.saveForImportBaseMetaData(metadataVoList, metadataDefineVoList, metadataExtendVoList);
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.meta.service.IBaseMetaDataService#saveForInitHbmMetadata()
	 */
	@Override
	public void saveForInitHbmMetadata()throws Exception{
		//baseMetaDataDao.saveForInitHbmMetadata();
	}
	
}
