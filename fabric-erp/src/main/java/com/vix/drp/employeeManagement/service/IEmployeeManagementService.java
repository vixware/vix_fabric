package com.vix.drp.employeeManagement.service;

import java.util.List;
import java.util.Map;

import com.vix.common.org.vo.OrgUnit;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;

public interface IEmployeeManagementService extends IBaseHibernateService {

	// 根据条件查询列表数据
	

	/**
	 * @Title: findOrgAndUnitTreeList
	 * @Description: 加载公司和组织机构数据
	 * @param @param treeType 公司 C 部门 O
	 * @param @param id 公司或者部门id
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return List<OrgUnit> 返回类型
	 * @throws
	 */
	List<OrgUnit> findOrgAndUnitTreeList(String treeType, String id) throws Exception;

	/**
	 * 公司的分页信息
	 * 
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findOrganizationPager(Pager pager, Map<String, Object> params) throws Exception;

}
