package com.vix.common.meta.service;

import java.util.List;
import java.util.Map;

import com.vix.common.meta.entity.BaseMetaData;
import com.vix.common.meta.vo.BaseMetaDataDefineImpVo;
import com.vix.common.meta.vo.BaseMetaDataExtendImpVo;
import com.vix.common.meta.vo.BaseMetaDataImpVo;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IBaseMetaDataService extends IBaseHibernateService{

	/**
	 * 
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	Pager findBaseMetaDataPager(Pager pager, Map<String, Object> params) throws Exception;
	
	/**
	 * 
	 * @param entityForm
	 * @return
	 * @throws Exception
	 */
	BaseMetaData saveOrUpdateBaseMetaData(BaseMetaData entityForm)throws Exception;
	
	/**
	 * 导入元数据  元数据固有属性  元数据扩展属性
	 * @param metadataVoList
	 * @param metadataDefineVoList
	 * @param metadataExtendVoList
	 * @throws Exception
	 */
	void saveForImportBaseMetaData(List<BaseMetaDataImpVo> metadataVoList,List<BaseMetaDataDefineImpVo> metadataDefineVoList,List<BaseMetaDataExtendImpVo> metadataExtendVoList)throws Exception;
	
	/**
	 * 系统元数据初始化
	 * @throws Exception
	 */
	void saveForInitHbmMetadata()throws Exception;
}
