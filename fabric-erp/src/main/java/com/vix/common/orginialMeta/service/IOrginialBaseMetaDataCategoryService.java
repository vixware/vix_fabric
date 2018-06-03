package com.vix.common.orginialMeta.service;

import java.util.List;
import java.util.Map;

import com.vix.common.orginialMeta.entity.OrginialBaseMetaDataCategory;
import com.vix.common.orginialMeta.vo.OrginialBaseMetaDataCategoryImpVo;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IOrginialBaseMetaDataCategoryService extends IBaseHibernateService{

	List<OrginialBaseMetaDataCategory> findAllBaseMetaDataCategory()throws Exception;
	/**
	 * 
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	Pager findBaseMetaDataCategoryPager(Pager pager, Map<String, Object> params) throws Exception;
	
	/**
	 * 
	 * @param entityForm
	 * @return
	 * @throws Exception
	 */
	OrginialBaseMetaDataCategory saveOrUpdateBaseMetaDataCategory(OrginialBaseMetaDataCategory entityForm)throws Exception;
	
	/**
	 * 导入元数据分类
	 * @param voList
	 * @throws Exception
	 */
	public void saveForImportBaseMetaDataCategory(List<OrginialBaseMetaDataCategoryImpVo> voList)throws Exception;
	
}
