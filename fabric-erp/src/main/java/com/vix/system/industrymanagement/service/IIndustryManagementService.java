package com.vix.system.industrymanagement.service;

import java.util.List;
import java.util.Map;

import com.vix.common.orginialMeta.entity.OrginialAuthority;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;
import com.vix.system.industrymanagement.entity.IndustryManagement;

public interface IIndustryManagementService extends IBaseHibernateService {
	
	/**
	 * 行业的IndustryManagementModule查询
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findModulePagerByIndustryMgt(Pager pager,Map<String,Object> params) throws Exception;
	/**
	 * 
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 
	public Pager findModulePagerByIndustryMgt2(Pager pager,Map<String,Object> params) throws Exception;*/
	
	/**
	 * 行业添加模块
	 * @param industryMgtId
	 * @param selectIds
	 * @throws Exception
	 */
	public void saveForAddModule(String industryMgtId,String selectIds)throws Exception;
	
	
	
	
	/**
	 * 分页查询   选择
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findIndustrymanagementPager(Pager pager,Map<String,Object> params) throws Exception;
	
	/**
	 * 查询所有的行业
	 * @return
	 * @throws Exception
	 */
	public List<IndustryManagement> findAllIndustrymanagement() throws Exception;
	
	/**
	 * 查询行业模块子菜单
	 * @param industryMgtId
	 * @param bizType
	 * @return
	 * @throws Exception
	 */
	public List<OrginialAuthority> findSubOrginialAuthorityWithIndustryMgtModule(String industryMgtModuleId,String bizType)throws Exception;
	
	
	/**
	 * 查询菜单项  关联行业项
	 * @param industryMgtId
	 * @param authorityId
	 * @param bizType
	 * @return
	 * @throws Exception
	 */
	//public List<Authority> findAuthorityWithIndustryMgt(Long industryMgtId,Long authorityId,String bizType)throws Exception;
	/**
     * 对上面方法的在封装   如果包含行业  则设定权限是否选中 isCheck="Y"
     * @param industryMgtId
     * @param authorityId
     * @return
     * @throws Exception
     
    public List<Authority> findAuthorityWithIndustryMgtForTreeGrid(Long industryMgtId,Long authorityId,String bizType)throws Exception;
    */
    
    /**
     * 查询行业菜单
     * @param industryMgtId
     * @param authorityId
     * @param bizType
     * @return
     * @throws Exception
     
    public Set<OrginialAuthority> findAllOrginialAuthorityWithIndustryMgtModuleForTreeGrid(Long industryMgtModuleId,Long authorityId,String bizType)throws Exception;
    */
    /**
     * 保存行业和菜单的配置关系
     * @param industryMgtId
     * @param bizType
     * @param topParentAuId
     * @param topAuthorityIds
     * @param authorityIdList
     * @throws Exception
    
    public void saveForAuthority(Long industryMgtId,String bizType,Long topParentAuId,String topAuthorityIds,List<Long> authorityIdList)throws Exception;
     */

    /**
     * 查询行业模块的元数据
     * @param pager
     * @param params
     * @return
     * @throws Exception
     */
    public Pager findMetaDataPagerByIndustryMgt(Pager pager,Map<String,Object> params) throws Exception;
    
    /**
     * 查询行业模块的待选择元数据  不能包括以选择的
     * @param pager
     * @param params
     * @return
     * @throws Exception
     */
    public Pager findSelectMetaDataPagerByIndustryMgt(Pager pager,Map<String,Object> params) throws Exception;

    /**
     * 添加元数据
     * @param industryMgtId
     * @param addMetaDataIds
     * @throws Exception
     */
    public void saveForAddMetaData(String industryMgtId,String addMetaDataIds)throws Exception;
    
    /**
     * 删除模块的元数据
     * @param industryMgtId
     * @param metadataIds
     * @throws Exception
     */
    public void deleteModuleMetaDataByImIdAndMdId(String industryMgtId,String metadataIds)throws Exception;
    
}
