package com.vix.common.securityDra.service;

import java.util.List;
import java.util.Map;

import com.vix.common.meta.entity.BaseMetaDataDefine;
import com.vix.common.security.entity.DataResRowMethodConfig;
import com.vix.common.security.entity.DataResRowPolicyObj;
import com.vix.common.securityDca.vo.MetaDataProperty;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IDataResRowPolicyObjService extends IBaseHibernateService {

	//public DataResRowPolicy saveOrUpdate(String addPlyObjIds, String deletePlyObjIds,DataResRowPolicy plyObj) throws Exception;
	
	/**
	 * 分页查询
	 */
	public Pager findPolicyObjPager(Pager pager,Map<String,Object> params) throws Exception;

	/**
	 * 保存
	 * @param addMetaDataIds
	 * @param policyObj
	 * @return
	 * @throws Exception
	 */
	public DataResRowPolicyObj saveOrUpdate(String addMetaDataIds, DataResRowPolicyObj policyObj) throws Exception;
	
	/**
	 * 保存配置条件
	 *  @param plyObjId  策略配置id
	 * @param propertyName 业务对象属性
	 * @param propertyOperator 业务对象满足条件的操作符
	 * @param mdpValue 业务对象的值
	 * @param mdpRealValue 业务对象的值 隐藏域
	 * @param propertyType 业务对象属性的类型
	 * @param ruleOpertator 业务对象的规则间的操作符
	 * @return
	 * @throws Exception
	 */
	public DataResRowPolicyObj saveOrUpdateObjConfig(String plyObjId,List<String> propertyName,List<String> propertyOperator,List<String> mdpValue,List<String> mdpRealValue,List<String> propertyType,List<String> ruleOpertator) throws Exception;
	
	/**
	 * 查询数据权限策略配置 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public DataResRowPolicyObj findPolicyObj(String id)throws Exception;
	
	/**
	 * 查询元数据的所有属性       和扩展属性 待定 
	 * @param metadataId
	 * @return
	 * @throws Exception
	 */
	public List<MetaDataProperty> findMdbPropertyList(String metadataId)throws Exception;
	
	/**
	 * 查询行集权限的元数据的行集权限的配置关系
	 * @param metaDataId
	 * @return
	 * @throws Exception
	 */
	public List<DataResRowMethodConfig> findDataResRowMethodConfigListByMetaDataId(String metaDataId) throws Exception;
	
	/**
	 * 构造系统常量
	 * @return
	 * @throws Exception
	 */
	public List<MetaDataProperty> findMetaDataProperty()throws Exception;
	
	/**
	 * 根据用户id 得到hql限制的信息
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Map<String,DataResRowPolicyObj> findPolicyObjMapByUserId(String userId) throws Exception;
	
	
	
	/**
	 * 查询用户的所有配置项条件
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Map<String,DataResRowPolicyObj> findPolicyObjMetadataStrMapByUserId(String userId) throws Exception;
	
	/**
	 * 元数据全称和hql约束配置项
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public Map<String,DataResRowPolicyObj> findPolicyObjMetadataMapByUserId(String userId) throws Exception;
	
	/**
	 * 根据元数据对象id 和 对象属性 查找元数据属性对象
	 * @param mdId
	 * @param mdPropertye
	 * @return
	 * @throws Exception
	 */
	public BaseMetaDataDefine findMddByMdId(String mdId,String mdPropertye) throws Exception;
	
	/**
	 * 通用的查询对象列表
	 * @param pager
	 * @param metadataId
	 * @return
	 * @throws Exception
	 */
	public Pager findCommonBizSelectList(Pager pager,String metadataId)throws Exception;
	
	/**
	 * 查询基本属性   id除外
	 * @return
	 * @throws Exception
	 */
	public List<BaseMetaDataDefine> findMdPropertyNotId(String metadataId)throws Exception;
	
	public Map<String,BaseMetaDataDefine> findMdPropertyMap(String metadataId)throws Exception;
}
