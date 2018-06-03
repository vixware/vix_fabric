package com.vix.common.security.dao;

import java.util.List;

import com.vix.common.orginialMeta.entity.OrginialAuthority;
import com.vix.common.security.vo.OrginialAuthorityImpVo;
import com.vix.common.share.entity.BaseOrganization;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

public interface IOrginialAuthorityDao extends IBaseHibernateDao {

    /**
     * 
     * @Title: findAllMenuAuthorityList
     * @Description: 查找 Authority中的所有是菜单的权限
     * @param @return    设定文件
     * @return List<Authority>    返回类型
     * @throws
     */
    public List<OrginialAuthority> findAllMenuOrginialAuthorityList() throws Exception;
    
    /**
     * 导入权限信息
     * @param voList
     * @throws Exception
     */
    public void saveForImportOrginialAuthority(List<OrginialAuthorityImpVo> voList)throws Exception;
    
    
    
    /**
     * 根据承租户公司id 得到 所有权限
     * @param tenantOrgId
     * @return
     * @throws Exception
     */
    //public List<OrginialAuthority> findAllOrginialAuthorityByTenantOrg(Long tenantOrgId)throws Exception;
    
	public List<BaseOrganization> findAllOrg()throws Exception;
}
