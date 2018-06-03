package com.vix.common.orginialMeta.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.orginialMeta.dao.IOrginialBaseMetaDataDao;
import com.vix.common.orginialMeta.entity.OrginialBaseMetaData;
import com.vix.common.orginialMeta.entity.OrginialBaseMetaDataCategory;
import com.vix.common.orginialMeta.hql.OrginialBaseMetaDataHqlProvider;
import com.vix.common.orginialMeta.service.IOrginialBaseMetaDataService;
import com.vix.common.orginialMeta.vo.OrginialBaseMetaDataDefineImpVo;
import com.vix.common.orginialMeta.vo.OrginialBaseMetaDataExtendImpVo;
import com.vix.common.orginialMeta.vo.OrginialBaseMetaDataImpVo;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

@Service("orginialBaseMetaDataService")
@Transactional
public class OrginialBaseMetaDataServiceImpl  extends BaseHibernateServiceImpl implements IOrginialBaseMetaDataService{

	@Resource(name="orginialBaseMetaDataDao")
	private IOrginialBaseMetaDataDao baseMetaDataDao;
	
	@Resource(name="orginialBaseMetaDataHqlProvider")
	private OrginialBaseMetaDataHqlProvider baseMetaDataHqlProvider;

	/**
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public Pager findBaseMetaDataPager(Pager pager, Map<String, Object> params) throws Exception{
		StringBuilder hql = baseMetaDataHqlProvider.findBaseMetaDataList(params, pager);
		
		HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		HqlTenantIdUtil.handleParamMap4CompanyInnerCode(params);//不处理CompanyInnerCode;
		
		pager = baseMetaDataDao.findPager2ByHql(pager, baseMetaDataHqlProvider.entityAsName(), hql.toString(), params);
		return pager;
	}
	
	@Override
	public OrginialBaseMetaData saveOrUpdateBaseMetaData(OrginialBaseMetaData entityForm)throws Exception{
		OrginialBaseMetaData entity = null;
		if(entityForm!=null && StrUtils.isNotEmpty(entityForm.getId())){
			entity = baseMetaDataDao.findEntityById(OrginialBaseMetaData.class, entityForm.getId());
		}
		if(entity == null){
			entity = new OrginialBaseMetaData();
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
		
		OrginialBaseMetaDataCategory cate = baseMetaDataDao.findEntityById(OrginialBaseMetaDataCategory.class, entityForm.getBaseMetaDataCategory().getId());
		if(cate!=null){
			entity.setBaseMetaDataCategory(cate);
		}
		
		baseMetaDataDao.saveOrUpdateOriginal(entity);
		return entity;
	}
	
	
	/* (non-Javadoc)
	 * @see com.vix.common.meta.service.IBaseMetaDataService#saveForImportBaseMetaData(java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public void saveForImportBaseMetaData(List<OrginialBaseMetaDataImpVo> metadataVoList,List<OrginialBaseMetaDataDefineImpVo> metadataDefineVoList,List<OrginialBaseMetaDataExtendImpVo> metadataExtendVoList)throws Exception{
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
