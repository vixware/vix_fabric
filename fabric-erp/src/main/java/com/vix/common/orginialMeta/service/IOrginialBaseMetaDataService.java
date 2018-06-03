package com.vix.common.orginialMeta.service;

import java.util.List;
import java.util.Map;

import com.vix.common.orginialMeta.entity.OrginialBaseMetaData;
import com.vix.common.orginialMeta.vo.OrginialBaseMetaDataDefineImpVo;
import com.vix.common.orginialMeta.vo.OrginialBaseMetaDataExtendImpVo;
import com.vix.common.orginialMeta.vo.OrginialBaseMetaDataImpVo;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IOrginialBaseMetaDataService extends IBaseHibernateService{

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
	OrginialBaseMetaData saveOrUpdateBaseMetaData(OrginialBaseMetaData entityForm)throws Exception;
	
	/**
	 * 导入元数据  元数据固有属性  元数据扩展属性
	 * @param metadataVoList
	 * @param metadataDefineVoList
	 * @param metadataExtendVoList
	 * @throws Exception
	 */
	void saveForImportBaseMetaData(List<OrginialBaseMetaDataImpVo> metadataVoList,List<OrginialBaseMetaDataDefineImpVo> metadataDefineVoList,List<OrginialBaseMetaDataExtendImpVo> metadataExtendVoList)throws Exception;
	
	/**
	 * 系统元数据初始化
	 * @throws Exception
	 */
	void saveForInitHbmMetadata()throws Exception;
}
