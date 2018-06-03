package com.vix.common.security.service;

import java.util.Map;

import com.vix.common.security.entity.IndexPageDataConfig;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IIndexPageDataConfigService extends IBaseHibernateService{

	/**
	 * 的分页查询
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findIndexPageDataConfigPager(Pager pager,Map<String,Object> params)throws Exception;
	
	/**
	 * 保存 
	 * @param module
	 * @return
	 * @throws Exception
	 */
	IndexPageDataConfig saveIndexPageDataConfig(IndexPageDataConfig entityForm)throws Exception;
	
	
	
	public Pager findSelectPdcByRolePager(Pager pager, String roleId,Map<String, Object> params) throws Exception;
	
	/**
	 * 角色的个性化配置的分页查询
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findPdcByRolePager(Pager pager,String roleId,Map<String,Object> params)throws Exception;

	/**
	 * @Title: saveForAddPdc
	 * @Description: 角色分配个性化配置
	 * @param @param roleId
	 * @param @param pdcIds
	 * @param @throws Exception    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void saveForAddPdc(String roleId,String pdcIds)throws Exception;
	
	/**
	 * @Title: deletePdcForRole
	 * @Description: 删除个性化配置项
	 * @param @param roleId
	 * @param @param pdcId
	 * @param @throws Exception    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void deletePdcForRole(String roleId, String pdcId)throws Exception;
	
}
