package com.vix.common.orginialMeta.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.metadata.ClassMetadata;

import com.vix.common.orginialMeta.vo.OrginialBaseMetaDataDefineImpVo;
import com.vix.common.orginialMeta.vo.OrginialBaseMetaDataExtendImpVo;
import com.vix.common.orginialMeta.vo.OrginialBaseMetaDataImpVo;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

/**
 * 元数据对象
 * @author Administrator
 *
 */
public interface IOrginialBaseMetaDataDao extends IBaseHibernateDao {

	/**
	 * 导入元数据  和 固有属性  扩展属性
	 * @param metadataVoList
	 * @param metadataDefineVoList
	 * @param metadataExtendVoList
	 * @throws Exception
	 */
	void saveForImportBaseMetaData(List<OrginialBaseMetaDataImpVo> metadataVoList,List<OrginialBaseMetaDataDefineImpVo> metadataDefineVoList,List<OrginialBaseMetaDataExtendImpVo> metadataExtendVoList)throws Exception;

	/**
	 * 所有hbm元数据查询
	 * @return
	 * @throws Exception
	 */
	Map<String,ClassMetadata> findHbmMetadata()throws Exception;
	
	/**
	 * 初始化hbm的元数据
	 * @throws Exception
	
	void saveForInitHbmMetadata()throws Exception;
	 */
}
