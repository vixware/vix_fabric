package com.vix.common.meta.dao;

import java.util.List;

import com.vix.common.meta.vo.BaseMetaDataDefineImpVo;
import com.vix.common.meta.vo.BaseMetaDataExtendImpVo;
import com.vix.common.meta.vo.BaseMetaDataImpVo;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

/**
 * 元数据对象
 * @author Administrator
 *
 */
public interface IBaseMetaDataDao extends IBaseHibernateDao {

	/**
	 * 导入元数据  和 固有属性  扩展属性
	 * @param metadataVoList
	 * @param metadataDefineVoList
	 * @param metadataExtendVoList
	 * @throws Exception
	 */
	void saveForImportBaseMetaData(List<BaseMetaDataImpVo> metadataVoList,List<BaseMetaDataDefineImpVo> metadataDefineVoList,List<BaseMetaDataExtendImpVo> metadataExtendVoList)throws Exception;

	
	/**
	 * @Title: saveForUpdateInitCompMetaData
	 * @Description: 承租户的元数据初始化
	 * @param @param tenantId
	 * @param @param companyInnerCode
	 * @param @param industryManagementModuleIds    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void saveForUpdateInitCompMetaData(String tenantId,String companyInnerCode,String industryManagementModuleIds)throws Exception;
	/**
	 * 所有hbm元数据查询
	 * @return
	 * @throws Exception
	 */
	//Map<String,ClassMetadata> findHbmMetadata()throws Exception;
	
	/**
	 * 初始化hbm的元数据
	 * @throws Exception
	 */
	//void saveForInitHbmMetadata()throws Exception;
	
	
}
