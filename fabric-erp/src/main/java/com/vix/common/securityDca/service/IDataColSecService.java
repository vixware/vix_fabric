package com.vix.common.securityDca.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vix.common.meta.entity.BaseMetaData;
import com.vix.common.security.entity.DataResColPolicy;
import com.vix.common.securityDca.vo.MetaDataProperty;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

/**
 * 列级权限业务接口
 * @author Administrator
 *
 */
public interface IDataColSecService extends IBaseHibernateService{

	/**
	 * 查询元数据列表
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findMetaDataPagerByHqlConditions(Pager pager,Map<String,Object> params) throws Exception;
	
	/**
	 * 加载元数据对象和其所有的属性、扩展属性信息
	 * @param mddId
	 * @return
	 * @throws Exception
	 */
	public BaseMetaData findMetaDataDefine(String mddId)throws Exception;
	
	
	/**
	 * 根据列级权限人事范围id 和  元数据定义id 得到列级权限数据配置策略列表
	 * @param colConfigCode
	 * @param baseMetaDataId
	 * @return
	 * @throws Exception
	 */
	public List<DataResColPolicy> findDataColPolicyList(String colConfigId,String baseMetaDataId)throws Exception;
	
	/**
	 * 保存列级权限配置
	 * @param metaDataObjId
	 * @param rangeId
	 * @param dataList
	 * @throws Exception
	 */
	public void saveColPly(String metaDataObjId,String rangeId,List<MetaDataProperty> dataList)throws Exception;
	
	/**
	 * @Title: findColConfigPagerByRoleId
	 * @Description: 根据角色Id 查询角色的列权限配置
	 * @param @param pager
	 * @param @param params roleId
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return Pager    返回类型
	 * @throws
	 */
	public Pager findColConfigPagerByRoleId(Pager pager,Map<String,Object> params) throws Exception;
	
	/**
	 * @Title: findColMetaInfoByUserAccount
	 * @Description: 查询账户的元数据权限（不可视元数据的属性配置信息）  
 					key  元数据全称
	 * 				value   key  列表：List   详情 :Detail        编辑 :Edit 暂不用
	 *        				 value  元数据属性的Set
	 * @param @param userAccount
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return Map<String,Map<String,Set<String>>>    返回类型
	 * @throws
	 */
	public Map<String,Map<String,Set<String>>> findColMetaInfoByUserAccount(String userAccountId);
}
