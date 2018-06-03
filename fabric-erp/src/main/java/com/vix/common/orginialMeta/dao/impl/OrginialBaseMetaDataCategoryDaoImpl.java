package com.vix.common.orginialMeta.dao.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.vix.common.orginialMeta.dao.IOrginialBaseMetaDataCategoryDao;
import com.vix.common.orginialMeta.entity.OrginialBaseMetaDataCategory;
import com.vix.common.orginialMeta.vo.OrginialBaseMetaDataCategoryImpVo;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;

@Repository("orginialBaseMetaDataCategoryDao")
public class OrginialBaseMetaDataCategoryDaoImpl extends BaseHibernateDaoImpl implements IOrginialBaseMetaDataCategoryDao {

	/* (non-Javadoc)
	 * @see com.vix.common.meta.dao.IBaseMetaDataCategoryDao#saveForImportBaseMetaDataCategory(List)
	 */
	@Override
	public void saveForImportBaseMetaDataCategory(List<OrginialBaseMetaDataCategoryImpVo> voList)throws Exception{
		List<OrginialBaseMetaDataCategory> saveBoList = new ArrayList<OrginialBaseMetaDataCategory>(voList.size());
		List<OrginialBaseMetaDataCategory> updateBoList = new ArrayList<OrginialBaseMetaDataCategory>(voList.size());
		
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
    private void transferToBo(List<OrginialBaseMetaDataCategoryImpVo> voList,List<OrginialBaseMetaDataCategory> saveBoList,List<OrginialBaseMetaDataCategory> updateBoList) throws Exception{
    	//List<BaseMetaDataCategory> boListTmp = new ArrayList<BaseMetaDataCategory>();
    	OrginialBaseMetaDataCategory savedBo = null;
    	for(OrginialBaseMetaDataCategoryImpVo vo:voList){
    		OrginialBaseMetaDataCategory bo = new OrginialBaseMetaDataCategory();
    		
    		bo.setCode(vo.getCode());
    		bo.setCategoryName(vo.getCategoryName());
    		
    		savedBo = findEntityByAttribute(OrginialBaseMetaDataCategory.class, "code", vo.getCode());
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
    private void saveImportBaseMetaDataCategory(List<OrginialBaseMetaDataCategory> saveBoList,List<OrginialBaseMetaDataCategory> updateBoList) throws Exception{
    	StringBuffer sql = new StringBuffer();
    	List<Object[]> arrayParam = new LinkedList<Object[]>();
    	
    	//String tenantId = SecurityUtil.getCurrentUserTenantId();
    	if(saveBoList!=null && !saveBoList.isEmpty()){
    		sql.append("insert into CMN_MET_ORGINIAL_METADATACATEGORY(code,categoryName) values (?,?)");
    		for(OrginialBaseMetaDataCategory bo:saveBoList){
    			arrayParam.add(new Object[]{bo.getCode(),bo.getCategoryName()});
        	}
        	batchUpdateBySql(sql.toString(), arrayParam);
    	}
    	
    	if(updateBoList!=null && !updateBoList.isEmpty()){
    		arrayParam.clear();
    		sql.setLength(0);
    		
    		sql.append("update CMN_MET_ORGINIAL_METADATACATEGORY meta set meta.code=? , meta.categoryName=? where meta.id=? ");
    		for(OrginialBaseMetaDataCategory bo:updateBoList){
    			arrayParam.add(new Object[]{bo.getCode(),bo.getCategoryName(),bo.getId()});
        	}
        	batchUpdateBySql(sql.toString(), arrayParam);
    	}
    	
    }
    
    
}
