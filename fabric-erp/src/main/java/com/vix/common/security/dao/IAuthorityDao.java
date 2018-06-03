package com.vix.common.security.dao;

import java.util.List;
import java.util.Set;

import com.vix.common.security.entity.Authority;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

public interface IAuthorityDao extends IBaseHibernateDao {

    /**
     * 
     * @Title: findAllMenuAuthorityList
     * @Description: 查找 Authority中的所有是菜单的权限
     * @param @return    设定文件
     * @return List<Authority>    返回类型
     * @throws
    
    public List<Authority> findAllMenuAuthorityList() throws Exception;
     */
    /**
     * @Title: findRoleAuthorityByUserId
     * @Description: 用户的useraccount的id 得到该用户的所有菜单信息
     * @param @param userId
     * @param @return
     * @param @throws Exception    设定文件
     * @return List<Authority>    返回类型
     * @throws
     */
    public List<Authority> findRoleMenuAuthorityByUserId(String userId,String bizType,String tenantId) throws Exception;
    
    public Set<Authority> findRoleMenuAuthorityByUserIdForShop(String roleId,String userId, String bizType, String tenantId) throws Exception;
    
    public List<Authority> findRoleFuncAuthorityByUserId(String userId,String bizType,String tenantId);
    
    /**
     * 导入权限信息
     * @param voList
     * @throws Exception
    public void saveForImportAuthority(List<AuthorityImpVo> voList)throws Exception;
      */
}
