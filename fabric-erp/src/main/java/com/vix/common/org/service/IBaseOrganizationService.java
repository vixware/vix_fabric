package com.vix.common.org.service;

import java.util.List;
import java.util.Map;

import com.vix.common.security.entity.ConfigUrlAdd;
import com.vix.common.share.entity.BaseOrganization;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IBaseOrganizationService extends IBaseHibernateService {
    
	/**
	 * 公司的分页信息  平台管理使用
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findOrganizationPager4Comp(Pager pager,Map<String,Object> params) throws Exception;
	
	/**
	 * 承租户的组织框架使用
	 * @Title: findOrganizationPager4NoComp
	 * @Description: TODO
	 * @param @param pager
	 * @param @param params
	 * @param @return
	 * @param @throws Exception    设定文件
	 * @return Pager    返回类型
	 * @throws
	 */
	public Pager findOrganizationPager4NoComp(Pager pager,Map<String,Object> params) throws Exception;
	
    /**
     * @Title: findSubOrganizationList
     * @Description: 查询子公司
     * @param @param id
     * @param @return
     * @param @throws Exception    设定文件
     * @return List<Organization>    返回类型
     * @throws
     */
    List<BaseOrganization> findSubBaseOrganizationList(String id) throws Exception;
    
    List<BaseOrganization> findSubBaseOrganizationListNoTenantId(String id) throws Exception;
    
    /**
     * 根据公司编码查询公司
     * @param compcode
     * @return
     * @throws Exception
     */
    //Organization findOrganizationByCompCode(String compcode)throws Exception;
    
    /**
     * 根据员工id 得到所在公司的对象
     * @param empId
     * @return
     * @throws Exception
     */
    BaseOrganization findCompByEmpId(String empId)throws Exception;
    /**
     * 根据员工id 得到所在公司的根节点集团公司的对象
     * @param empId
     * @return
     * @throws Exception
     */
    BaseOrganization findCompTopByCompInnerCode(String innerCode)throws Exception;
    
    /**
     * 根据登录账号id 查询所在公司   可能返回null
     * @param compAdminAccountId
     * @return
     * @throws Exception
     */
    BaseOrganization findCompByCompSuperAdminId(String compAdminAccountId)throws Exception;
    
    /**
     * @Title: findAllConfigUrlList
     * @Description: 查询所有url
     * @param @return
     * @param @throws Exception    设定文件
     * @return List<ConfigUrlAdd>    返回类型
     * @throws
     */
    List<ConfigUrlAdd> findAllConfigUrlList()throws Exception;
    
    /**
     * 根据承租户id 获得默认登录页面
     * @Title: findTargetUrlOfTenantByTenantId
     * @Description: TODO
     * @param @param tenantId
     * @param @return
     * @param @throws Exception    设定文件
     * @return String    返回类型
     * @throws
     */
    ConfigUrlAdd findTargetUrlOfTenantByTenantId(String tenantId)throws Exception;
}
