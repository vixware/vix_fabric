package com.vix.common.meta.service;

import java.util.List;
import java.util.Map;

import com.vix.common.meta.entity.BaseMetaDataCategory;
import com.vix.common.meta.vo.BaseMetaDataCategoryImpVo;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IBaseMetaDataCategoryService extends IBaseHibernateService{

	List<BaseMetaDataCategory> findAllBaseMetaDataCategory()throws Exception;
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
	BaseMetaDataCategory saveOrUpdateBaseMetaDataCategory(BaseMetaDataCategory entityForm)throws Exception;
	
	/**
	 * 导入元数据分类
	 * @param voList
	 * @throws Exception
	 */
	public void saveForImportBaseMetaDataCategory(List<BaseMetaDataCategoryImpVo> voList)throws Exception;
	
}
