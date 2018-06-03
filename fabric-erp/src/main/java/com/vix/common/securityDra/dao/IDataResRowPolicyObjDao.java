package com.vix.common.securityDra.dao;

import java.util.List;
import java.util.Map;

import com.vix.common.meta.entity.BaseMetaData;
import com.vix.common.meta.entity.BaseMetaDataDefine;
import com.vix.common.security.entity.DataResRowPolicyObj;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

/**
 * 行集数据权限的  sql条件配置
 * @author Administrator
 *
 */
public interface IDataResRowPolicyObjDao extends IBaseHibernateDao {

	/**
	 * 根据用户id得到数据权限配置信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public List<DataResRowPolicyObj> findPolicyObjListByUserId(String userId) throws Exception;
	
	/**
	 * 根据用户id 得到 元数据id 和 DataResRowPolicyObj 的映射map  其中是取DataResRowPolicyObj中优先级最高的
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Map<String,DataResRowPolicyObj> findPolicyObjMapByUserId(String userId) throws Exception;
	
	/**
	 * 根据用户id 得到 元数据类全称 和 DataResRowPolicyObj 的映射map  其中是取DataResRowPolicyObj中优先级最高的
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Map<String,DataResRowPolicyObj> findPolicyObjMapStrByUserId(String userId) throws Exception;
	
	/**
	 * 查询属性列表
	 * @param metadataId
	 * @return
	 * @throws Exception
	 */
	public List<BaseMetaDataDefine> findBaseMetadataDefineList(String metadataId)throws Exception;
	
	public List<BaseMetaDataDefine> findBaseMetadataDefineListNoId(String metadataId)throws Exception;
	
	/**
	 * 根据对象全称查询元数据对象
	 * @param metadataName
	 * @return
	 */
	public BaseMetaData findBaseMetaDataByMetadataName(String metadataName)throws Exception;
	
}
