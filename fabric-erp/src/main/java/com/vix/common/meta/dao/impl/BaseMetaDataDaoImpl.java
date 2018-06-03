package com.vix.common.meta.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.Type;
import org.springframework.stereotype.Repository;

import com.vix.common.meta.dao.IBaseMetaDataDao;
import com.vix.common.meta.entity.BaseMetaData;
import com.vix.common.meta.entity.BaseMetaDataCategory;
import com.vix.common.meta.entity.BaseMetaDataDefine;
import com.vix.common.meta.entity.BaseMetaDataExtend;
import com.vix.common.meta.vo.BaseMetaDataDefineImpVo;
import com.vix.common.meta.vo.BaseMetaDataExtendImpVo;
import com.vix.common.meta.vo.BaseMetaDataImpVo;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.exception.BizException;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

@Repository("baseMetaDataDao")
public class BaseMetaDataDaoImpl extends BaseHibernateDaoImpl implements IBaseMetaDataDao {


	/* (non-Javadoc)
	 * @see com.vix.common.meta.dao.IBaseMetaDataDao#saveForImportBaseMetaData(java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public void saveForImportBaseMetaData(List<BaseMetaDataImpVo> metadataVoList,List<BaseMetaDataDefineImpVo> metadataDefineVoList,List<BaseMetaDataExtendImpVo> metadataExtendVoList)throws Exception{
		
		//1 先处理元数据
		List<BaseMetaData> metadataBoSaveList = new ArrayList<BaseMetaData>();
		List<BaseMetaData> metadataBoUpdateList = new ArrayList<BaseMetaData>();
		
		if(metadataVoList!=null && !metadataVoList.isEmpty()){
    		transferMetadataToBo(metadataVoList, metadataBoSaveList,metadataBoUpdateList);
    	}
		
		//2 在处理 固有属性
		List<BaseMetaDataDefine> metadataDefineBoSaveList = new ArrayList<BaseMetaDataDefine>();
		List<BaseMetaDataDefine> metadataDefineBoUpdateList = new ArrayList<BaseMetaDataDefine>();
		
		if(metadataDefineVoList!=null && !metadataDefineVoList.isEmpty()){
			transferMetadataDefineToBo(metadataDefineVoList, metadataDefineBoSaveList,metadataDefineBoUpdateList);
    	}
		
		saveImportBaseMetaDataAndProperty(metadataBoSaveList, metadataBoUpdateList, metadataDefineBoSaveList, metadataDefineBoUpdateList, null, null);
		
	}
	
	/**
	 * 处理元数据
	 * @param metadataVoList
	 * @param metadataBoSaveList
	 * @param metadataBoUpdateList
	 * @throws Exception
	 */
	private void transferMetadataToBo(List<BaseMetaDataImpVo> metadataVoList,List<BaseMetaData> metadataBoSaveList ,List<BaseMetaData> metadataBoUpdateList ) throws Exception{
		BaseMetaData savedBo = null;
		 
		for(BaseMetaDataImpVo vo:metadataVoList){
			BaseMetaData bo = new BaseMetaData();

			savedBo = findEntityByAttributeNoTenantId(BaseMetaData.class, "code", vo.getCode());
    		
			bo.setCode(vo.getCode());
			bo.setMetaDataName(vo.getMetaDataName());
			bo.setMetaDataDisplayName(vo.getMetaDataDisplayName());
			
			bo.setBaseMetaDataCategoryCode(vo.getBaseMetaDataCategoryCode());
			
			if(savedBo==null){
    			metadataBoSaveList.add(bo);
    		}else{
    			
    			bo.setId(savedBo.getId());
    			metadataBoUpdateList.add(bo);
    		}
    		evict(savedBo);
    		savedBo=null;
		}
	    	
	}
	
	/**
	 * 处理元数据固有属性
	 * @param metadataDefineVoList
	 * @param metadataDefineBoSaveList
	 * @param metadataDefineBoUpdateList
	 * @throws Exception
	 */
	private void transferMetadataDefineToBo(List<BaseMetaDataDefineImpVo> metadataDefineVoList,List<BaseMetaDataDefine> metadataDefineBoSaveList ,List<BaseMetaDataDefine> metadataDefineBoUpdateList ) throws Exception{
		BaseMetaDataDefine savedBo = null;
		 
		for(BaseMetaDataDefineImpVo vo:metadataDefineVoList){
			BaseMetaDataDefine bo = new BaseMetaDataDefine();
    		
			bo.setPropertyName(vo.getPropertyName());
			bo.setPropertyCode(vo.getPropertyCode());
			bo.setProperty(vo.getProperty());
			bo.setPropertyType(vo.getPropertyType());
			bo.setDataType(vo.getDataType());
			bo.setIsCollectionType(vo.getIsCollectionType());
			bo.setIsSelectView(vo.getIsSelectView());
			
			bo.setBaseMetaDataCode(vo.getMetaDataCode());
			
    		savedBo = findEntityByAttributeNoTenantId(BaseMetaDataDefine.class, "propertyCode", vo.getPropertyCode());
    		if(savedBo==null){
    			metadataDefineBoSaveList.add(bo);
    		}else{
    			
    			bo.setId(savedBo.getId());
    			metadataDefineBoUpdateList.add(bo);
    		}
    		evict(savedBo);
    		savedBo=null;
		}
	    	
	}
	
	private void transferMetadataExtendToBo(List<BaseMetaDataExtendImpVo> metadataExtendVoList,List<BaseMetaDataExtend> metadataExtendBoSaveList ,List<BaseMetaDataExtend> metadataExtendBoUpdateList ) throws Exception{
	    	
	}
	
	/**
	 * 保存导入 元数据  和 固有属性   扩展属性 暂时没实现
	 * @param metadataBoSaveList
	 * @param metadataBoUpdateList
	 * @param metadataDefineBoSaveList
	 * @param metadataDefineBoUpdateList
	 * @param metadataExtendBoSaveList
	 * @param metadataExtendBoUpdateList
	 * @throws Exception
	 */
	private void saveImportBaseMetaDataAndProperty(List<BaseMetaData> metadataBoSaveList ,List<BaseMetaData> metadataBoUpdateList,  List<BaseMetaDataDefine> metadataDefineBoSaveList ,List<BaseMetaDataDefine> metadataDefineBoUpdateList,  List<BaseMetaDataExtend> metadataExtendBoSaveList ,List<BaseMetaDataExtend> metadataExtendBoUpdateList)throws Exception{
		StringBuffer sql = new StringBuffer();
    	List<Object[]> arrayParam = new LinkedList<Object[]>();
		
    	String tenantId = SecurityUtil.getCurrentUserTenantId();
    	
		//1 处理 元数据对象
    	if(metadataBoSaveList!=null && !metadataBoSaveList.isEmpty()){
    		sql.append("insert into CMN_MET_METADATA(code,metaDataName,metaDataDisplayName,BaseMetaDataCategory_ID,TENANTID) values (?,?,?,?,?)");
    		
    		BaseMetaDataCategory cate =null;
    		
    		for(BaseMetaData bo:metadataBoSaveList){
    			cate= findEntityByAttribute(BaseMetaDataCategory.class, "code", bo.getBaseMetaDataCategoryCode());
    			if(cate==null){
    				throw new BizException("元数据的类别编码 " +bo.getBaseMetaDataCategoryCode()+ " 不存在！");
    			}
    			arrayParam.add(new Object[]{bo.getCode(),bo.getMetaDataName(), bo.getMetaDataDisplayName(),cate.getId(),tenantId});
    			
    			evict(cate);
    			cate=null;
        	}
        	batchUpdateBySql(sql.toString(), arrayParam);
    	}
    	
    	if(metadataBoUpdateList!=null && !metadataBoUpdateList.isEmpty()){
    		arrayParam.clear();
    		sql.setLength(0);
    		
    		sql.append("update CMN_MET_METADATA meta set meta.code=? , meta.metaDataName=? , meta.metaDataDisplayName=?, meta.BaseMetaDataCategory_ID=? where meta.id=? ");
    		BaseMetaDataCategory cate =null;
    		for(BaseMetaData bo:metadataBoUpdateList){
    			cate= findEntityByAttribute(BaseMetaDataCategory.class, "code", bo.getBaseMetaDataCategoryCode());
    			if(cate==null){
    				throw new BizException("元数据的类别编码 " +bo.getBaseMetaDataCategoryCode()+ " 不存在！");
    			}
    			
    			arrayParam.add(new Object[]{bo.getCode(),bo.getMetaDataName(), bo.getMetaDataDisplayName(),cate.getId(),bo.getId()});
    			
    			evict(cate);
    			cate=null;
        	}
        	batchUpdateBySql(sql.toString(), arrayParam);
    	}
    	
    	
		
		//2 固有属性
    	if(metadataDefineBoSaveList!=null && !metadataDefineBoSaveList.isEmpty()){
    		arrayParam.clear();
    		sql.setLength(0);
    		
    		sql.append("insert into CMN_MET_METADATADEFINE(propertyName,propertyCode,property,propertyType, dataType,isCollectionType,isSelectView, Metadata_ID,TENANTID) values (?,?,?,?, ?,?,?,?,?)");
    		
    		BaseMetaData meta =null;
    		for(BaseMetaDataDefine bo:metadataDefineBoSaveList){
    			meta= findEntityByAttribute(BaseMetaData.class, "code", bo.getBaseMetaDataCode());
    			if(meta==null){
    				throw new BizException("元数据基本属性的元数据编码 " + bo.getBaseMetaDataCode() + " 不存在！");
    			}
    			arrayParam.add(new Object[]{bo.getPropertyName(), bo.getPropertyCode(), bo.getProperty(), bo.getPropertyType(), bo.getDataType(), bo.getIsCollectionType(), bo.getIsSelectView(), meta.getId(),tenantId});
    			
    			evict(meta);
    			meta=null;
    			
        	}
        	batchUpdateBySql(sql.toString(), arrayParam);
    	}
    	if(metadataDefineBoUpdateList!=null && !metadataDefineBoUpdateList.isEmpty()){
    		arrayParam.clear();
    		sql.setLength(0);
    		
    		sql.append("update CMN_MET_METADATADEFINE meta set meta.propertyName=? , meta.propertyCode=? , meta.property=?, meta.propertyType =?,  meta.dataType =?,  meta.isCollectionType =?,  meta.isSelectView =?,  meta.Metadata_ID =?,  meta.Metadata_ID=? where meta.id=? ");
    		
    		BaseMetaData meta =null;
    		for(BaseMetaDataDefine bo:metadataDefineBoUpdateList){
    			meta= findEntityByAttribute(BaseMetaData.class, "code", bo.getBaseMetaDataCode());
    			if(meta==null){
    				throw new BizException("元数据基本属性的元数据编码 " + bo.getBaseMetaDataCode() + " 不存在！");
    			}
    			arrayParam.add(new Object[]{bo.getPropertyName(), bo.getPropertyCode(), bo.getProperty(), bo.getPropertyType(), bo.getDataType(), bo.getIsCollectionType(), bo.getIsSelectView(), meta.getId(),bo.getId()});
    			
    			evict(meta);
    			meta=null;
    			
        	}
        	batchUpdateBySql(sql.toString(), arrayParam);
    	}
    	
		
		
		
		//3 扩展属性
	}
	
	
	/* 
	 * @see com.vix.common.meta.dao.IBaseMetaDataDao#saveForUpdateInitCompMetaData(java.lang.String, java.lang.String, java.lang.String)
	 */ 
	@Override
	public void saveForUpdateInitCompMetaData(String tenantId,String companyInnerCode,String industryManagementModuleIds) throws Exception{
		
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("tenantId", tenantId);
		params.put("companyInnerCode", companyInnerCode);
		params.put("industryManagementModuleIds", industryManagementModuleIds);
		
		executeSql("call pro_sys_company_im_metadata(:tenantId, :companyInnerCode , :industryManagementModuleIds);", params);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 读取系统所有的元数据
	 * @return
	 */
	public Map<String,ClassMetadata> findHbmMetadata()throws Exception{
		Map<String,ClassMetadata> metaMap = getSession().getSessionFactory().getAllClassMetadata();
		return metaMap;
	}
	
	
	
	public void saveForInitHbmMetadata()throws Exception{
		//Map<String,ClassMetadata> allMetadataMap = findHbmMetadata();
		Map<String,ClassMetadata> allMetadataMap = new HashMap<String, ClassMetadata>();
		
		
		//!!!!  2014-05-04注释掉，需要名臣根据需求处理下，吴磊
		//allMetadataMap.put(OrgPosition.class.getName(), getSession().getSessionFactory().getClassMetadata(OrgPosition.class));
		
		
		BaseMetaData metadata = null;
		BaseMetaDataDefine metadataDefine = null;
		
		//StringBuffer sql = new StringBuffer();
    	//List<Object[]> arrayParam = new LinkedList<Object[]>();
    	
		int i =0;
		for(Map.Entry<String,ClassMetadata> entry:allMetadataMap.entrySet()){
			i++;

			
			String metadataKey = entry.getKey();
			ClassMetadata metadataValue = entry.getValue();
			
			
			String idName = metadataValue.getIdentifierPropertyName();
			Type idType = metadataValue.getIdentifierType();
			
			
			
			//得到属性 和 类型
			String[] propertyNames = metadataValue.getPropertyNames();
			Type[] propertyeTypes = metadataValue.getPropertyTypes();
			
			/*
			for(String propertyeName:propertyeNames){
				Type propertyeType = metadataValue.getPropertyType(propertyeName);
				Boolean isCollectionType = propertyeType.isCollectionType();
			}
			*/
			metadata = new BaseMetaData();
			metadata.setMetaDataName(metadataKey);
			metadata.setMetaDataDisplayName(metadataValue.getEntityName());
			metadata.setCode(metadataKey);
			
			
			//sql.append("insert into CMN_MET_METADATA(code,metaDataName,metaDataDisplayName) values (?,?,?)");//,BaseMetaDataCategory_ID
			//arrayParam.add(new Object[]{metadata.getCode(), metadata.getMetaDataName(), metadata.getMetaDataDisplayName()});
			
			//saveOrUpdate(metadata);
			
			
			//处理id
			metadataDefine = new BaseMetaDataDefine();
			//metadataDefine.setPro
			
			
			int size = propertyNames.length;
			for(int j=0;j<size;j++){
				
				metadataDefine = new BaseMetaDataDefine();
				
				String propertyName = propertyNames[j];
				Type propertyType = propertyeTypes[j];
				Boolean isCollection = propertyType.isCollectionType();
				Boolean isEntityType = propertyType.isEntityType();
				
				
				
				String propertyTypeVal = null;
				String dataTypeVal = null;
				String isCollectionVal = null;
				
				if(!isEntityType && !isCollection){
					//简单类型
					dataTypeVal = StringUtils.capitalize(propertyType.getName());
					//isCollectionVal = null;
				}else{
					//propertyTypeVal = propertyType.getName();
					
					
					if(isEntityType){
						isCollectionVal="1";
						propertyTypeVal = propertyType.getName();
						
					}else if(isCollection){
						isCollectionVal="2";
						
						Class propertyTypeClazz = propertyType.getReturnedClass();
						//Class propertyTypeClazz = propertyType.getClass();
						//propertyType.get
						//ParameterizedType pt = (ParameterizedType) fc
						//TypeVariable[] fc = propertyTypeClazz.getTypeParameters();
						//if(fc!=null){
							
							//System.out.println("VVV"+fc[0].getBounds()[0]);
						//}
						//System.out.println(bb.getAnnotations());
						propertyTypeVal = StringUtils.substringBetween(propertyType.getName(), "(", ")");//propertyType.getName();
					}
					
				}
				
				
				

				System.out.println("propertyName:"+propertyName + ",dataTypeVal:"+dataTypeVal+",propertyType:" + propertyTypeVal +",isCollection:"+isCollectionVal+",isEntityType:"+isEntityType);
				
				//(propertyName,propertyCode,property,propertyType, dataType,isCollectionType,isSelectView, Metadata_ID) values (?,?,?,?, ?,?,?,?)");
	    		
				metadataDefine.setPropertyCode(metadataValue.getEntityName()+"_"+propertyName);
				metadataDefine.setPropertyName(propertyName);
				metadataDefine.setProperty(propertyName);
				
				metadataDefine.setPropertyType(propertyTypeVal);
				metadataDefine.setDataType(dataTypeVal);
				metadataDefine.setIsCollectionType(isCollectionVal);
				
				//System.out.println("ss");
			}
			
			if(i>10){
				break;
			}
		}
	
	}
}
