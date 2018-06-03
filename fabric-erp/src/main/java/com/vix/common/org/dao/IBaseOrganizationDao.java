package com.vix.common.org.dao;

import com.vix.common.org.entity.OrgView;
import com.vix.common.share.entity.BaseOrganization;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;

public interface IBaseOrganizationDao extends IBaseHibernateDao {
	
	/**
	 * 根据承租户id  获取  承租户信息 
	 * @param tenantId
	 * @return
	 * @throws Exception
	 */
	BaseOrganization findTenantByTenantId(String tenantId)throws Exception;
	
	/**
	 * 生成公司内部编码
	 * @return
	 * @throws Exception
	 */
	String find4CompCode(String parentCode)throws Exception;
	
	/**
	 * 根据职员id获得公司信息
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	BaseOrganization findCompByEmpId(String empId)throws Exception;
	
	/**
	 * 根据编码查询公司
	 * @param innerCode
	 * @return
	 * @throws Exception
	 */
	BaseOrganization findCompByInnerCode(String innerCode)throws Exception;
	
	/**
	 * 根据编码查询所在公司
	 * @param innerCode
	 * @return
	 * @throws Exception
	 */
	OrgView findOrgByInnerCode(String innerCode)throws Exception;
	
	/**
	 * 根据行业 对角色的菜单进行授权 
	 * 不使用了
	 * @param roleId
	 * @param industryId
	 * @throws Exception
	 
	void saveForRoleAuthorityByIndustry(Long roleId,Long industryId)throws Exception;
	*/
	/**
	 * 根据模块编码 对角色的菜单进行授权
	 * @param roleId
	 * @param tenantId 承租户id
	 * @param moduleCodeArray   0 pc  1 pad  2 module
	 * @throws Exception
	 不使用了
	void saveForRoleAuthorityByModuleCode(Long roleId,String[] moduleCodeArray)throws Exception;
	*/
	
	void saveForRoleAuthorityByImIds(String roleId,String tenantId,String industryModuleIds)throws Exception;
	
	/**
	 * 根据职员或者账号id 得到 公司信息（或者顶层公司）的超级管理员的角色
	 * 首先跟据职员id查找，如果职员id为空，则根据userAccountId查找顶级公司信息
	 * 
	 * @param empId
	 * @param userAccountId
	 * @return
	 * @throws Exception
	 */
	public String findOrgByUserAccountId(String empId,String userAccountId)throws Exception;
	
	/**
	 * @Title: saveForAddHbAndDw
	 * @Description: 承租户的计量单位和货币的初始化，只能是创建的的时候才会进行初始化
	 * @param @param compid
	 * @param @throws Exception    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	//public void saveForAddHbAndDw(Long compid)throws Exception;
}
