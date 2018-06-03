package com.vix.common.security.service;

import java.util.List;
import java.util.Map;

import com.vix.common.security.entity.Resource;
import com.vix.common.security.vo.ResourceVO;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IResourceService extends IBaseHibernateService{

	/**
	 * 加载系统系统所有的权限资源
	 * @return
	 * @throws Exception
	 */
	public List<Resource> findAllResource() throws Exception;
	
	/**
	 * @Title: findAllResourceByUserAccountId
	 * @Description: TODO
	 * @param @param userAccountId
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return List<Resource>    返回类型
	 * @throws
	 */
	public List<ResourceVO> findAllResourceByUserAccountId(String userAccountId,String tenantId);
	
	/**
	 * 分页查询
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findResourcePager(Pager pager,Map<String,Object> params)throws Exception;
	
	/**
	 * 提供权限的资源选择
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findResourceForSelect(Pager pager,Map<String,Object> params)throws Exception;
}
