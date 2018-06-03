package com.vix.common.orginialMeta.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.orginialMeta.dao.IOrginialBaseMetaDataDefineDao;
import com.vix.common.orginialMeta.entity.OrginialBaseMetaData;
import com.vix.common.orginialMeta.entity.OrginialBaseMetaDataDefine;
import com.vix.common.orginialMeta.entity.OrginialBaseMetaDataExtend;
import com.vix.common.orginialMeta.hql.OrginialBaseMetaDataDefineHqlProvider;
import com.vix.common.orginialMeta.hql.OrginialBaseMetaDataExtendHqlProvider;
import com.vix.common.orginialMeta.service.IOrginialBaseMetaDataDefineService;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.persistence.hibernate.util.HqlTenantIdUtil;
import com.vix.core.utils.StrUtils;

@Service("orginialBaseMetaDataDefineService")
@Transactional
public class OrginialBaseMetaDataDefineServiceImpl  extends BaseHibernateServiceImpl implements IOrginialBaseMetaDataDefineService{

	@Resource(name="orginialBaseMetaDataDefineDao")
	private IOrginialBaseMetaDataDefineDao baseMetaDataDefineDao;
	
	@Resource(name="orginialBaseMetaDataDefineHqlProvider")
	private OrginialBaseMetaDataDefineHqlProvider baseMetaDataDefineHqlProvider;

	@Resource(name="orginialBaseMetaDataExtendHqlProvider")
	private OrginialBaseMetaDataExtendHqlProvider baseMetaDataExtendHqlProvider;

	/**
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<OrginialBaseMetaDataDefine> findBaseMetaDataDefineList(Map<String, Object> params) throws Exception{
		StringBuilder hql = baseMetaDataDefineHqlProvider.findBaseMetaDataCategoryList(params);
		//pager = baseMetaDataDefineDao.findPager2ByHql(pager, baseMetaDataDefineHqlProvider.entityAsName(), hql.toString(), params);
		
		HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		
		List<OrginialBaseMetaDataDefine> list = baseMetaDataDefineDao.findAllByHql2(hql.toString(), params);
		return list;
	}
	
	@Override
	public List<OrginialBaseMetaDataExtend> findBaseMetaDataExtendList(Map<String, Object> params) throws Exception{
		StringBuilder hql = baseMetaDataExtendHqlProvider.findBaseMetaDataCategoryList(params);
		//pager = baseMetaDataDefineDao.findPager2ByHql(pager, baseMetaDataDefineHqlProvider.entityAsName(), hql.toString(), params);
		
		HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		
		List<OrginialBaseMetaDataExtend> list = baseMetaDataDefineDao.findAllByHql2(hql.toString(), params);
		return list;
	}
	
	@Override
	public OrginialBaseMetaDataDefine saveOrUpdateBaseMetaDataDefine(OrginialBaseMetaDataDefine entityForm,String metaDataId)throws Exception{
		OrginialBaseMetaDataDefine entity = null;
		if(entityForm!=null && StrUtils.isNotEmpty(entityForm.getId())){
			entity = baseMetaDataDefineDao.findEntityById(OrginialBaseMetaDataDefine.class, entityForm.getId());
		}
		if(entity == null){
			entity = new OrginialBaseMetaDataDefine();
		}
		
		Date now = new Date();
		String currentUserAccountId = SecurityUtil.getCurrentUserId();
		
		if(entity.getId()==null){
			entity.setCreateTime(now);
		}
		entity.setUpdateTime(now);
		entity.setCreator(String.valueOf(currentUserAccountId));
		
		//entity.setCategoryName(entityForm.getCategoryName());
		OrginialBaseMetaData metaData = baseMetaDataDefineDao.findEntityById(OrginialBaseMetaData.class, metaDataId);
		entity.setBaseMetaData(metaData);
		
		entity.setPropertyName(entityForm.getPropertyName());
		entity.setPropertyCode(entityForm.getPropertyCode());
		entity.setProperty(entityForm.getProperty());
		entity.setIsCollectionType(entityForm.getIsCollectionType());
		entity.setPropertyType(entityForm.getPropertyType());
		entity.setIsSelectView(entityForm.getIsSelectView());
		entity.setDefaultValue(entityForm.getDefaultValue());
		entity.setDataType(entityForm.getDataType());
		
		baseMetaDataDefineDao.saveOrUpdateOriginal(entity);
		return entity;
	}
	
	
	@Override
	public List<OrginialBaseMetaDataExtend> findBaseMetaDataExtByMetaNameList(Map<String, Object> params) throws Exception{
		StringBuilder hql = baseMetaDataExtendHqlProvider.findBaseMetaDataByMetaDataNameList(params);
		//pager = baseMetaDataDefineDao.findPager2ByHql(pager, baseMetaDataDefineHqlProvider.entityAsName(), hql.toString(), params);
		
		HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		
		List<OrginialBaseMetaDataExtend> list = baseMetaDataDefineDao.findAllByHql2(hql.toString(), params);
		return list;
	}
	
	
	@Override
	public void saveOrUpdateBaseMetaDataExt(List<OrginialBaseMetaDataExtend> extList,String metaDataId)throws Exception{
		List<OrginialBaseMetaDataExtend> addList = new ArrayList<OrginialBaseMetaDataExtend>();
		List<OrginialBaseMetaDataExtend> updateList = new ArrayList<OrginialBaseMetaDataExtend>();
		
		if(extList!=null && !extList.isEmpty()){
			for(OrginialBaseMetaDataExtend ext:extList){
				String id = ext.getId();
				if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if(id!=null && id>0){
					//编辑
					OrginialBaseMetaDataExtend obj = baseMetaDataDefineDao.findEntityById(OrginialBaseMetaDataExtend.class, id);
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
					ext.setBaseMetaData(baseMetaDataDefineDao.findEntityById(OrginialBaseMetaData.class, metaDataId));
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
	public OrginialBaseMetaDataExtend saveOrUpdateBaseMetaDataExt(OrginialBaseMetaDataExtend entityForm,String metaDataId)throws Exception{
		
		OrginialBaseMetaDataExtend entity = null;
		if(entityForm!=null && entityForm.getId()!=null){
			entity = baseMetaDataDefineDao.findEntityById(OrginialBaseMetaDataExtend.class, entityForm.getId());
		}
		if(entity == null){
			entity = new OrginialBaseMetaDataExtend();
		}
		
		Date now = new Date();
		String currentUserAccountId = SecurityUtil.getCurrentUserId();
		
		if(entity.getId()==null){
			entity.setCreateTime(now);
		}
		entity.setUpdateTime(now);
		entity.setCreator(String.valueOf(currentUserAccountId));
		
		//entity.setCategoryName(entityForm.getCategoryName());
		OrginialBaseMetaData metaData = baseMetaDataDefineDao.findEntityById(OrginialBaseMetaData.class, metaDataId);
		entity.setBaseMetaData(metaData);
		
		entity.setPropertyName(entityForm.getPropertyName());
		entity.setPropertyCode(entityForm.getPropertyCode());
		entity.setProperty(entityForm.getProperty());
		entity.setIsCollectionType(entityForm.getIsCollectionType());
		entity.setPropertyType(entityForm.getPropertyType());
		entity.setIsSelectView(entityForm.getIsSelectView());
		entity.setDefaultValue(entityForm.getDefaultValue());
		entity.setDataType(entityForm.getDataType());
		
		baseMetaDataDefineDao.saveOrUpdateOriginal(entity);
		return entity;
	}
	
	@Override
	public List<OrginialBaseMetaDataDefine> findBaseMetaDataDefineListByProName(Map<String, Object> params) throws Exception{
		StringBuilder hql = baseMetaDataDefineHqlProvider.findBaseMetaDataByMetaDataNameList(params);
		//pager = baseMetaDataDefineDao.findPager2ByHql(pager, baseMetaDataDefineHqlProvider.entityAsName(), hql.toString(), params);
		
		HqlTenantIdUtil.handleParamMap4TenantId(params);//不增加tenantId
		
		List<OrginialBaseMetaDataDefine> list = baseMetaDataDefineDao.findAllByHql2(hql.toString(), params);
		return list;
	}
	
}
