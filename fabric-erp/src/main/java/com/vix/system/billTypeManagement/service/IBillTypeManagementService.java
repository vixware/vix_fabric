/**
 * 
 */
package com.vix.system.billTypeManagement.service;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;
import com.vix.system.billTypeManagement.entity.BillsCategory;
import com.vix.system.billTypeManagement.entity.BillsProperty;
import com.vix.system.billTypeManagement.vo.BillTypeUnit;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
public interface IBillTypeManagementService extends IBaseHibernateService {

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
	/*
	 * public List<BillTypeUnit> findOrgAndUnitTreeList(String treeType, Long
	 * id) throws Exception;
	 */

	/**
	 * 公司的分页信息
	 * 
	 * @param pager
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public Pager findBillsTypePager(Pager pager, Map<String, Object> params) throws Exception;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<BillsCategory> findSubBillsCategoryList(Long id) throws Exception;

	/**
	 * 通过公司的ID获取单据分类
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<BillsCategory> findBillsCategoryListByOrgId(String id) throws Exception;

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
	List<BillTypeUnit> findOrgAndUnitTreeList(String treeType, String id) throws Exception;

	List<BillTypeUnit> findBillTypeList(String treeType, String id) throws Exception;

	public List<BillsProperty> findBillsPropertyList(String billsCategoryId) throws Exception;

	public BillsCategory findBillsCategoryByCompanyCodeAndCategoryCode(String companyCode, String categoryCode) throws Exception;

}
