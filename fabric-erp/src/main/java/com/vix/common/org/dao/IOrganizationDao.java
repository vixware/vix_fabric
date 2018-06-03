package com.vix.common.org.dao;

import java.util.List;

import com.vix.common.org.entity.OrgView;
import com.vix.common.org.entity.Organization;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.system.base.compOperation.vo.CompanyOperationVO;

public interface IOrganizationDao extends IBaseHibernateDao {

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
	Organization findCompByEmpId(String empId)throws Exception;
	
	/**
	 * 根据编码查询公司
	 * @param innerCode
	 * @return
	 * @throws Exception
	 */
	Organization findCompByInnerCode(String innerCode)throws Exception;
	
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
	 
	void saveForRoleAuthorityByIndustry(String roleId,String industryId)throws Exception;
	*/
	/**
	 * 根据模块编码 对角色的菜单进行授权
	 * @param roleId
	 * @param tenantId 承租户id
	 * @param moduleCodeArray   0 pc  1 pad  2 module
	 * @throws Exception
	 不使用了
	void saveForRoleAuthorityByModuleCode(String roleId,String[] moduleCodeArray)throws Exception;
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
	public void saveForAddHbAndDw(String compid)throws Exception;
	
	/**
	 * @Title: saveCompanyOperation
	 * @Description: 应用服务器的承租户初始化
	 * @param @param vo
	 * @param @throws Exception    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public Organization saveCompanyOperation(CompanyOperationVO vo,Boolean isFromOc)throws Exception;
	
	/**
	 * @Title: findAllOrg
	 * @Description: 查询所有的承租户
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return List<Organization>    返回类型
	 * @throws
	 */
	public List<Organization> findAllOrg()throws Exception;
	
	/**
	 * @Title: findOrganizationByTenantId
	 * @Description: 根据承租户标识查询承租户
	 * @param @param tenantId
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return Organization    返回类型
	 * @throws
	 */
	public Organization findOrganizationByTenantId(String tenantId)throws Exception;
	
}
