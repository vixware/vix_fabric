package com.vix.common.security.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vix.common.orginialMeta.entity.OrginialAuthority;
import com.vix.common.security.tree.TreeNode;
import com.vix.common.security.vo.OrginialAuthorityImpVo;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IOrginialAuthorityService extends IBaseHibernateService{
	
	/**
	 * 查询分页
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findOrginialAuthorityPager(Pager pager,Map<String,Object> params)throws Exception;
	
	/**
	 * @Title: saveAuthority
	 * @Description: 保存菜单
	 * @param @param entityForm
	 * @param @throws Exception    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void saveOrginialAuthority(String addResourceIds, String deleteResourceIds,OrginialAuthority entityForm)throws Exception;

	
	public List<OrginialAuthority> findSubOrginialAuthority(String id,String bizType,String authType)throws Exception;
	
	
	public OrginialAuthority findParentOrginialAuthority(String id)throws Exception;
	
	public OrginialAuthority findOrginialAuthorityByCode(String authorityCode,String bizType)throws Exception;
	
	/**
	 * 根据角色id和类型查询权限根节点
	 * @param roleId
	 * @param bizType
	 * @return
	 * @throws Exception
	 */
	//public List<OrginialAuthority> findSubOrginialAuthorityWithRole(Long roleId,String bizType)throws Exception;
	
    /**
     * @Title: findMenuAll
     * @Description: 构造菜单
     * @param @return    设定文件
     * @return Set<TreeNode>    返回类型
     * @throws
     */
    public Set<TreeNode> findMenuAll() throws Exception;
    
    /**
     * 查询所有的权限 抓取角色     容器初始化使用
     * @return
     * @throws Exception
    
    public List<OrginialAuthority> findAllOrginialAuthorityByType(String type) throws Exception;
     */
    
    /**
     * 加载所有的权限编码
     * @return
     * @throws Exception
     */
    //public List<OrginialAuthority> findAllOrginialAuthority() throws Exception;
    
    /**
     * 导入权限数据
     * @param voList
     * @throws Exception
     */
    public void saveForImportOrginialAuthority(List<OrginialAuthorityImpVo> voList)throws Exception;
}
