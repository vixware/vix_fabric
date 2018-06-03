package com.vix.common.security.dao;

import java.util.List;
import java.util.Map;

import com.vix.common.security.entity.Module;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.web.Pager;

/**
 * 分页DAO
 * @author shadow
 *
 */
public interface IModuleDao extends IBaseHibernateDao {

	/**
	 * 模块的分页查询
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findModulePager(Pager pager,Map<String,Object> params)throws Exception;
	
	/**
	 * 查询所有模块列表
	 * @return
	 * @throws Exception
	 */
	public List<Module> findAllModuleList()throws Exception;
	
	/**
     * 加载承租户的模块树
     * @param compId 承租户id
     * @param bizType 客户端类型  BizConstant COMMON_SECURITY_RESTYPE
     * @return
     * @throws Exception
    
    public List<ModuleVo> findModuleListForTree(Long compId,String bizType)throws Exception;
	 */
}
