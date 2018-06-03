package com.vix.common.security.service;

import java.util.Map;

import com.vix.common.security.entity.Module;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IModuleService extends IBaseHibernateService{

	/**
	 * 模块的分页查询
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findModulePager(Pager pager,Map<String,Object> params)throws Exception;
	
	/**
	 * 保存模块 
	 * @param module
	 * @return
	 * @throws Exception
	 */
	Module saveModule(Module module)throws Exception;

	/**
	 * 查询菜单项  关联行业项
	 * @param industryMgtId
	 * @param authorityId
	 * @param bizType
	 * @return
	 * @throws Exception
	 */
	//public List<Authority> findAuthorityWithModule(Long moduleId,Long authorityId,String bizType)throws Exception;
    
    /**
     * 保存模块和菜单的配置关系
     * @param industryMgtId
     * @param bizType
     * @param topParentAuId
     * @param topAuthorityIds
     * @param authorityIdList
     * @throws Exception
     */
    //public void saveForAuthority(Long moduleId,String bizType,Long topParentAuId,String topAuthorityIds,List<Long> authorityIdList)throws Exception;

    /**
     * 加载承租户的模块树
     * @param compId 承租户id
     * @param bizType 客户端类型  BizConstant COMMON_SECURITY_RESTYPE
     * @return
     * @throws Exception
     
    public List<ModuleVo> findModuleListForTree(Long compId,String bizType)throws Exception;
	*/
}
