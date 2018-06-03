package com.vix.common.meta.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.meta.dao.IBaseMetaDataDefineDao;
import com.vix.common.meta.entity.BaseMetaData;
import com.vix.common.meta.entity.BaseMetaDataDefine;
import com.vix.common.meta.entity.BaseMetaDataExtend;
import com.vix.common.meta.hql.BaseMetaDataDefineHqlProvider;
import com.vix.common.meta.hql.BaseMetaDataExtendHqlProvider;
import com.vix.common.meta.service.IBaseMetaDataDefineService;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.StrUtils;

@Service("baseMetaDataDefineService")
@Transactional
public class BaseMetaDataDefineServiceImpl  extends BaseHibernateServiceImpl implements IBaseMetaDataDefineService{

	@Resource(name="baseMetaDataDefineDao")
	private IBaseMetaDataDefineDao baseMetaDataDefineDao;
	
	@Resource(name="baseMetaDataDefineHqlProvider")
	private BaseMetaDataDefineHqlProvider baseMetaDataDefineHqlProvider;

	@Resource(name="baseMetaDataExtendHqlProvider")
	private BaseMetaDataExtendHqlProvider baseMetaDataExtendHqlProvider;

	/**
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<BaseMetaDataDefine> findBaseMetaDataDefineList(Map<String, Object> params) throws Exception{
		StringBuilder hql = baseMetaDataDefineHqlProvider.findBaseMetaDataCategoryList(params);
		//pager = baseMetaDataDefineDao.findPager2ByHql(pager, baseMetaDataDefineHqlProvider.entityAsName(), hql.toString(), params);
		
		//HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		
		List<BaseMetaDataDefine> list = baseMetaDataDefineDao.findAllByHql2(hql.toString(), params);
		return list;
	}
	
	@Override
	public List<BaseMetaDataExtend> findBaseMetaDataExtendList(Map<String, Object> params) throws Exception{
		StringBuilder hql = baseMetaDataExtendHqlProvider.findBaseMetaDataCategoryList(params);
		//pager = baseMetaDataDefineDao.findPager2ByHql(pager, baseMetaDataDefineHqlProvider.entityAsName(), hql.toString(), params);
		
		//HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		
		List<BaseMetaDataExtend> list = baseMetaDataDefineDao.findAllByHql2(hql.toString(), params);
		return list;
	}
	
	@Override
	public BaseMetaDataDefine saveOrUpdateBaseMetaDataDefine(BaseMetaDataDefine entityForm,String metaDataId)throws Exception{
		BaseMetaDataDefine entity = null;
		if(entityForm!=null && StrUtils.isNotEmpty(entityForm.getId())){
			entity = baseMetaDataDefineDao.findEntityById(BaseMetaDataDefine.class, entityForm.getId());
		}
		if(entity == null){
			entity = new BaseMetaDataDefine();
		}
		
		Date now = new Date();
		String currentUserAccountId = SecurityUtil.getCurrentUserId();
		
		if(entity.getId()==null){
			entity.setCreateTime(now);
		}
		entity.setUpdateTime(now);
		entity.setCreator(String.valueOf(currentUserAccountId));
		
		//entity.setCategoryName(entityForm.getCategoryName());
		BaseMetaData metaData = baseMetaDataDefineDao.findEntityById(BaseMetaData.class, metaDataId);
		entity.setBaseMetaData(metaData);
		
		entity.setPropertyName(entityForm.getPropertyName());
		entity.setPropertyCode(entityForm.getPropertyCode());
		entity.setProperty(entityForm.getProperty());
		entity.setIsCollectionType(entityForm.getIsCollectionType());
		entity.setPropertyType(entityForm.getPropertyType());
		entity.setIsSelectView(entityForm.getIsSelectView());
		entity.setDefaultValue(entityForm.getDefaultValue());
		entity.setDataType(entityForm.getDataType());
		
		baseMetaDataDefineDao.saveOrUpdate(entity);
		return entity;
	}
	
	
	@Override
	public List<BaseMetaDataExtend> findBaseMetaDataExtByMetaNameList(Map<String, Object> params) throws Exception{
		StringBuilder hql = baseMetaDataExtendHqlProvider.findBaseMetaDataByMetaDataNameList(params);
		//pager = baseMetaDataDefineDao.findPager2ByHql(pager, baseMetaDataDefineHqlProvider.entityAsName(), hql.toString(), params);
		
		//HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		
		List<BaseMetaDataExtend> list = baseMetaDataDefineDao.findAllByHql2(hql.toString(), params);
		return list;
	}
	
	
	@Override
	public void saveOrUpdateBaseMetaDataExt(List<BaseMetaDataExtend> extList,String metaDataId)throws Exception{
		List<BaseMetaDataExtend> addList = new ArrayList<BaseMetaDataExtend>();
		List<BaseMetaDataExtend> updateList = new ArrayList<BaseMetaDataExtend>();
		
		if(extList!=null && !extList.isEmpty()){
			for(BaseMetaDataExtend ext:extList){
				String id = ext.getId();
				if(StringUtils.isNotEmpty(id)){
					//编辑
					BaseMetaDataExtend obj = baseMetaDataDefineDao.findEntityById(BaseMetaDataExtend.class, id);
					obj.setPropertyName(ext.getPropertyName());
					obj.setPropertyCode(ext.getPropertyCode());
					obj.setProperty(ext.getProperty());
					obj.setPropertyType(null);
					obj.setDataType(ext.getDataType());
					obj.setIsSelectView(null);
					obj.setIsCollectionType("0");
					obj.setDefaultValue(ext.getDefaultValue());
					
					updateList.add(obj);
					
				}else{
					//新建
					//BaseMetaDataExtend obj = new BaseMetaDataExtend();
					ext.setBaseMetaData(baseMetaDataDefineDao.findEntityById(BaseMetaData.class, metaDataId));
					ext.setPropertyType(null);
					ext.setIsSelectView(null);
					ext.setIsCollectionType("0");
					
					addList.add(ext);
				}
				
			}
		}
		
		if(!addList.isEmpty()){
			baseMetaDataDefineDao.batchSave(addList);
		}
		if(!updateList.isEmpty()){
			baseMetaDataDefineDao.batchUpdateByList(updateList);
		}
	}
	
	/**
	 * 扩展属性保存
	 * @param entityForm
	 * @param metaDataId
	 * @return
	 * @throws Exception
	 */
	@Override
	public BaseMetaDataExtend saveOrUpdateBaseMetaDataExt(BaseMetaDataExtend entityForm,String metaDataId)throws Exception{
		
		BaseMetaDataExtend entity = null;
		if(entityForm!=null && StrUtils.isNotEmpty(entityForm.getId())){
			entity = baseMetaDataDefineDao.findEntityById(BaseMetaDataExtend.class, entityForm.getId());
		}
		if(entity == null){
			entity = new BaseMetaDataExtend();
		}
		
		Date now = new Date();
		String currentUserAccountId = SecurityUtil.getCurrentUserId();
		
		if(entity.getId()==null){
			entity.setCreateTime(now);
		}
		entity.setUpdateTime(now);
		entity.setCreator(String.valueOf(currentUserAccountId));
		
		//entity.setCategoryName(entityForm.getCategoryName());
		BaseMetaData metaData = baseMetaDataDefineDao.findEntityById(BaseMetaData.class, metaDataId);
		entity.setBaseMetaData(metaData);
		
		entity.setPropertyName(entityForm.getPropertyName());
		entity.setPropertyCode(entityForm.getPropertyCode());
		entity.setProperty(entityForm.getProperty());
		entity.setIsCollectionType(entityForm.getIsCollectionType());
		entity.setPropertyType(entityForm.getPropertyType());
		entity.setIsSelectView(entityForm.getIsSelectView());
		entity.setDefaultValue(entityForm.getDefaultValue());
		entity.setDataType(entityForm.getDataType());
		
		baseMetaDataDefineDao.saveOrUpdate(entity);
		return entity;
	}
	
	@Override
	public List<BaseMetaDataDefine> findBaseMetaDataDefineListByProName(Map<String, Object> params) throws Exception{
		StringBuilder hql = baseMetaDataDefineHqlProvider.findBaseMetaDataByMetaDataNameList(params);
		//pager = baseMetaDataDefineDao.findPager2ByHql(pager, baseMetaDataDefineHqlProvider.entityAsName(), hql.toString(), params);
		
		//HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		
		List<BaseMetaDataDefine> list = baseMetaDataDefineDao.findAllByHql2(hql.toString(), params);
		return list;
	}
	
}
