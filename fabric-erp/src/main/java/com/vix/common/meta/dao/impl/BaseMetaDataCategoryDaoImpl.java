package com.vix.common.meta.dao.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.vix.common.meta.dao.IBaseMetaDataCategoryDao;
import com.vix.common.meta.entity.BaseMetaDataCategory;
import com.vix.common.meta.vo.BaseMetaDataCategoryImpVo;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

@Repository("baseMetaDataCategoryDao")
public class BaseMetaDataCategoryDaoImpl extends BaseHibernateDaoImpl implements IBaseMetaDataCategoryDao {

	/* (non-Javadoc)
	 * @see com.vix.common.meta.dao.IBaseMetaDataCategoryDao#saveForImportBaseMetaDataCategory(List)
	 */
	@Override
	public void saveForImportBaseMetaDataCategory(List<BaseMetaDataCategoryImpVo> voList)throws Exception{
		List<BaseMetaDataCategory> saveBoList = new ArrayList<BaseMetaDataCategory>(voList.size());
		List<BaseMetaDataCategory> updateBoList = new ArrayList<BaseMetaDataCategory>(voList.size());
		
    	if(voList!=null && !voList.isEmpty()){
    		transferToBo(voList, saveBoList,updateBoList);
    	}
    	//System.out.println("Start:" + boList.size());
    	saveImportBaseMetaDataCategory(saveBoList,updateBoList);
	}
	
	/**
	 * 处理业务对象 转换
	 * @param voList
	 * @param saveBoList
	 * @param updateBoList
	 * @throws Exception
	 */
    private void transferToBo(List<BaseMetaDataCategoryImpVo> voList,List<BaseMetaDataCategory> saveBoList,List<BaseMetaDataCategory> updateBoList) throws Exception{
    	//List<BaseMetaDataCategory> boListTmp = new ArrayList<BaseMetaDataCategory>();
    	BaseMetaDataCategory savedBo = null;
    	for(BaseMetaDataCategoryImpVo vo:voList){
    		BaseMetaDataCategory bo = new BaseMetaDataCategory();
    		
    		bo.setCode(vo.getCode());
    		bo.setCategoryName(vo.getCategoryName());
    		
    		savedBo = findEntityByAttribute(BaseMetaDataCategory.class, "code", vo.getCode());
    		if(savedBo==null){
    			saveBoList.add(bo);
    		}else{
    			bo.setId(savedBo.getId());
    			updateBoList.add(bo);
    		}
    		
    		evict(savedBo);
    		savedBo=null;
    	}
    }
    
    /**
     * 批量保存
     * @param saveBoList
     * @param updateBoList
     * @throws Exception
     */
    private void saveImportBaseMetaDataCategory(List<BaseMetaDataCategory> saveBoList,List<BaseMetaDataCategory> updateBoList) throws Exception{
    	StringBuffer sql = new StringBuffer();
    	List<Object[]> arrayParam = new LinkedList<Object[]>();
    	
    	String tenantId = SecurityUtil.getCurrentUserTenantId();
    	
    	if(saveBoList!=null && !saveBoList.isEmpty()){
    		sql.append("insert into CMN_MET_METADATACATEGORY(code,categoryName,tenantId) values (?,?,?)");
    		for(BaseMetaDataCategory bo:saveBoList){
    			arrayParam.add(new Object[]{bo.getCode(),bo.getCategoryName(),tenantId});
        	}
        	batchUpdateBySql(sql.toString(), arrayParam);
    	}
    	
    	if(updateBoList!=null && !updateBoList.isEmpty()){
    		arrayParam.clear();
    		sql.setLength(0);
    		
    		sql.append("update CMN_MET_METADATACATEGORY meta set meta.code=? , meta.categoryName=? where meta.id=? ");
    		for(BaseMetaDataCategory bo:updateBoList){
    			arrayParam.add(new Object[]{bo.getCode(),bo.getCategoryName(),bo.getId()});
        	}
        	batchUpdateBySql(sql.toString(), arrayParam);
    	}
    	
    }
    
    
}
