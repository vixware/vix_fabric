package com.vix.system.industrymanagement.service;

import java.util.List;
import java.util.Set;

import com.vix.common.orginialMeta.entity.OrginialAuthority;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;

public interface IIndustryManagementAuthService extends IBaseHibernateService {
	
	public Set<OrginialAuthority> findAllOrginialAuthorityMWithIndustryMgtModule(String industryMgtModuleId,String bizType)throws Exception;
	
	public List<OrginialAuthority> findSubOrginialAuthorityFByIndustryMgtModule(String industryMgtModuleId,String orginialAuthCode,String bizType)throws Exception;

	/**
	 * @Title: saveForAuthority
	 * @Description: 行业模块的授权的全部保存
	 * @param @param industryMgtModuleId
	 * @param @param bizType
	 * @param @param menuIds
	 * @param @param funIds
	 * @param @param checkedMenuId
	 * @param @param isChangCheckMenu
	 * @param @param isChangeCheckFun
	 * @param @throws Exception    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void saveForOrginialAuthority(String industryMgtModuleId,String bizType,String menuIds,String funIds,String checkedMenuId,Boolean isChangCheckMenu,Boolean isChangeCheckFun)throws Exception;

	/**
	 * @Title: saveForAuthorityByAuthId
	 * @Description: 保存某菜单权限节点的所有子权限  包括功能按钮
	 * @param @param industryMgtModuleId
	 * @param @param bizType
	 * @param @param authId
	 * @param @throws Exception    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void saveForOrginialAuthorityByAuthId(String industryMgtModuleId,String bizType,String orginialAuthId)throws Exception;

	/**
	 * @Title: deleteForAuthorityByAuthId
	 * @Description: 取消某权限菜单节点的所有子权限  包括按钮
	 * 和saveForAuthorityByAuthId方法相反，  
	 * @param @param industryMgtModuleId
	 * @param @param bizType
	 * @param @param authId
	 * @param @throws Exception    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void deleteForOrginialAuthorityByAuthId(String industryMgtModuleId,String bizType,String orginialAuthId)throws Exception;
}
