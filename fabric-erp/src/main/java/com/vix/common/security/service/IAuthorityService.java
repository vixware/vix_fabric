package com.vix.common.security.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vix.common.security.entity.Authority;
import com.vix.common.security.tree.TreeNode;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IAuthorityService extends IBaseHibernateService{
	
	/**
	 * 查询分页
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findAuthorityPager(Pager pager,Map<String,Object> params)throws Exception;
	
	/**
	 * @Title: saveAuthority
	 * @Description: 保存菜单
	 * @param @param entityForm
	 * @param @throws Exception    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void saveAuthority(String addResourceIds, String deleteResourceIds,Authority entityForm)throws Exception;

	
	public List<Authority> findSubAuthority(String id,String bizType,String authType,String tenantId)throws Exception;
	
	//查询父权限
	public Authority findParentAuthority(String id,String tenantId)throws Exception;
	
	/**
	 * 根据角色id和类型查询权限根节点
	 * @param roleId
	 * @param bizType
	 * @return
	 * @throws Exception
	
	public List<Authority> findSubAuthorityWithRole(Long roleId,String bizType)throws Exception;
	 */
	/**
	 * 根据角色id和类型查询根节点权限信息   （权限必须是compAdminRole的权限的子集）
	 * @param currentRoleId
	 * @param bizType
	 * @param compAdminRoleId
	 * @return
	 * @throws Exception
	public List<Authority> findSubAuthorityWithRoleByCompAdminRole(Long currentRoleId,String bizType)throws Exception;
	 */
	
    /**
     * @Title: findMenuAll
     * @Description: 构造菜单
     * @param @return    设定文件
     * @return Set<TreeNode>    返回类型
     * @throws
     */
    public Set<TreeNode> findMenuAll() throws Exception;
    
    /**
     * @Title: findMenuByUser
     * @Description:正常的读取菜单方式
     * @param @param userId
     * @param @return
     * @param @throws Exception    设定文件
     * @return Set<TreeNode>    返回类型
     * @throws
     */
    public Set<TreeNode> findMenuByUser(String userId,String bizType) throws Exception;
    /**
     * 升级菜单显示的方式
     * @param userId
     * @return
     * @throws Exception
     */
    public Set<TreeNode> findMenuByUser2(String userId,String bizType) throws Exception;
    
    /**
     * @Title: findAuthFunCodeSetByUserAccount
     * @Description: 查询账户的
     * @param @param userId
     * @param @param bizType
     * @param @return
     * @param @throws Exception    设定文件
     * @return ImmutableSet<String>    返回类型 ImmutableSet
     * @throws
     */
    public Set<String> findAuthFunCodeSetByUserAccount(String userId,String bizType,String tenantId);
    
    
    
    
    
    
    
    
    
    
    /**
     * 查群权限菜单 外联接角色 
     * @param roleId
     * @param authorityId
     * @return
     * @throws Exception
    
    public List<Authority> findAuthorityWithRole(Long roleId,Long authorityId,String bizType)throws Exception;
     */
    /**
     * 对上面方法的在封装   如果包含角色 则设定权限是否选中 isCheck="Y"
     * @param roleId
     * @param authorityId
     * @return
     * @throws Exception
     
    public List<Authority> findAuthorityWithRoleForTreeGrid(Long roleId,Long authorityId,String bizType)throws Exception;
    */
    
    /**
     * 查询所有
     * @param roleId
     * @param authorityId
     * @param bizType
     * @return
     * @throws Exception
     */
    //public Set<Authority> findAllAuthorityWithRoleForTreeGrid(Long roleId,Long authorityId,String bizType)throws Exception;
    
    /**
     * 查询子权限   但子权限列表必须是承租户公司的管理员的权限的子集
     * @param roleId
     * @param authorityId
     * @param bizType
     * @return
     * @throws Exception
    
    public Set<Authority> findAllAuthorityWithRoleForTreeGridByCompSuperAdminRole(Long roleId,Long authorityId,String bizType)throws Exception;
     */
    
    /**
     * 角色授权
     * @param roleId
     * @param authorityIdList
     * @throws Exception
    
    public void saveForAuthority(Long roleId,List<Long> authorityIdList)throws Exception;
     */
    
    //public void saveForAuthority(Long roleId,String bizType,Long topParentAuId,String topAuthorityIds,List<Long> authorityIdList)throws Exception;
    
    /**
     * 查询所有的权限 抓取角色     容器初始化使用
     * @return
     * @throws Exception
    
    public List<Authority> findAllAuthorityByType(String type) throws Exception;
     */
    /**
     * 加载所有的权限编码
     * @return
     * @throws Exception
    
    public List<Authority> findAllAuthority() throws Exception;
     */
}
