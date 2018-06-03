package com.vix.common.orginialMeta.service;

import java.util.List;
import java.util.Map;

import com.vix.common.orginialMeta.entity.OrginialBaseMetaDataDefine;
import com.vix.common.orginialMeta.entity.OrginialBaseMetaDataExtend;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

public interface IOrginialBaseMetaDataDefineService extends IBaseHibernateService{

	/**
	 * 基本属性列表查询
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	List<OrginialBaseMetaDataDefine> findBaseMetaDataDefineList(Map<String, Object> params) throws Exception;
	
	List<OrginialBaseMetaDataExtend> findBaseMetaDataExtendList(Map<String, Object> params) throws Exception;
	
	/**
	 * 基本属性保存
	 * @param entityForm
	 * @return
	 * @throws Exception
	 */
	OrginialBaseMetaDataDefine saveOrUpdateBaseMetaDataDefine(OrginialBaseMetaDataDefine entityForm,String metaDataId)throws Exception;
	
	/**
	 * 扩展属性列表查询
	 * @param params
	 * @return
	 * @throws Exception
	 */
	List<OrginialBaseMetaDataExtend> findBaseMetaDataExtByMetaNameList(Map<String, Object> params) throws Exception;
	
	
	/**
	 * 元数据属性编辑的  扩展属性可编辑列表的保存
	 * @param extList
	 * @param metaDataId
	 * @throws Exception
	 */
	void saveOrUpdateBaseMetaDataExt(List<OrginialBaseMetaDataExtend> extList,String metaDataId)throws Exception;
	
	
	/**
	 * 扩展属性保存
	 * @param entityForm
	 * @param metaDataId
	 * @return
	 * @throws Exception
	 */
	OrginialBaseMetaDataExtend saveOrUpdateBaseMetaDataExt(OrginialBaseMetaDataExtend entityForm,String metaDataId)throws Exception;
	
	
	/**
	 * 根据元数据对象名称得到元数据的所有属性
	 * @param params
	 * @return
	 * @throws Exception
	 */
	List<OrginialBaseMetaDataDefine> findBaseMetaDataDefineListByProName(Map<String, Object> params) throws Exception;
}
