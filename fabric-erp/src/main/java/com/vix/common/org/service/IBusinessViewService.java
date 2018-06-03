package com.vix.common.org.service;

import java.util.List;
import java.util.Map;

import com.vix.common.common.select.bizOrg.vo.BizOrgEmpVO;
import com.vix.common.org.entity.BusinessOrgEmpView;
import com.vix.common.org.entity.BusinessOrgView;
import com.vix.common.org.entity.BusinessView;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IBusinessViewService extends IBaseHibernateService{

	/**
	 * 
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	Pager findBusinessViewPager(Pager pager, Map<String, Object> params) throws Exception;
	
	/**
	 * 
	 * @param entityForm
	 * @return
	 * @throws Exception
	 */
	BusinessView saveOrUpdateBusinessView(BusinessView entityForm)throws Exception;
	
	/**
	 * @Title: findBizOrgViewByCode
	 * @Description: 根据编码查询业务组织视图
	 * @param @param bizCode
	 * @param @return    设定文件
	 * @return BusinessOrgView    返回类型
	 * @throws
	 */
	BusinessView findBizOrgViewByCode(String bizCode)throws Exception;
	
	/**
	 * @Title: findEmpByBizOrg
	 * @Description: 查询某个业务组织视图下的人员树形结构   异步查询
	 * @param @param bizOrgViewId
	 * @param @return    设定文件
	 * @return List<BizOrgEmpVO>    返回类型
	 * @throws
	 */
	List<BizOrgEmpVO> findEmpByBizOrg(String bizOrgIdStr,String bizOrgViewId,String tenantId);
	
	//查询直接上级
	List<BizOrgEmpVO> findLeaderByEmpId(String empId )throws Exception;
	
	//查询直接下属
	List<BizOrgEmpVO> findSubByEmpId(String empId )throws Exception;
	
	//查询所有下属
	List<BizOrgEmpVO> findAllSubByEmpId(String empId )throws Exception;
	
	//查询职员所在承租户的业务视图
	BusinessView findDefaultBizViewByEmpId(String empId) throws Exception;
	//根据承租户查询承租户默认视图
	BusinessView findDefaultBizViewByTenantId(String tenantId) throws Exception;
	
	/**
	 * 业务组织视图和业务组织的树
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<BusinessOrgView> findOrgViewList(String id) throws Exception;
	List<BusinessOrgEmpView> findBusinessOrgEmpViewList(String id) throws Exception;
	
	/**
	 * @Title: saveOrUpdateBizViewHasDefaultRelation
	 * @Description: 创建承租户默认职员上下级关系
	 * @param @throws Exception    设定文件
	 * @return void    返回类型
	 * @throws
	 */
	void saveOrUpdateBizViewHasDefaultRelation() throws Exception;
	
}
