/**
 * 
 */
package com.vix.nvix.common.base.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vix.common.org.vo.OrgUnit;
import com.vix.common.security.entity.Authority;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.bom.entity.BomNode;
import com.vix.mdm.bom.util.BomUnit;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.OrderDetaiVo;
import com.vix.mdm.item.entity.StoreItem;
import com.vix.mdm.pm.common.entity.ProjectRole;
import com.vix.nvix.common.base.vo.EmpCardRecords;
import com.vix.nvix.common.base.vo.EmpDayCardRecords;
import com.vix.nvix.common.base.vo.EmpMonthCardRecords;
import com.vix.nvix.oa.bo.AllWorkLogStatisticsBo;
import com.vix.nvix.oa.bo.TaskStatisticsBo;
import com.vix.nvix.oa.bo.WorkLogStatisticsBo;
import com.vix.nvix.project.util.ProjectUnit;
import com.vix.nvix.task.vo.TaskStatisticsVo;
import com.vix.nvix.warehouse.vo.CustomerSalesVo;
import com.vix.nvix.warehouse.vo.InAndOutStatisticsBo;
import com.vix.nvix.warehouse.vo.OrderDetailStatisticsBo;
import com.vix.nvix.warehouse.vo.SalesDataVo;
import com.vix.nvix.warehouse.vo.SalesOrderItemDay;
import com.vix.nvix.warehouse.vo.SalesOrderItemVo;
import com.vix.nvix.warehouse.vo.StockRecordLinesVo;
import com.vix.oa.task.taskDefinition.entity.VixTask;

/**
 * 
 * @ClassFullName com.vix.nvix.common.base.service.IVixntBaseService
 *
 * @author bjitzhang
 *
 * @date 2016年4月7日
 *
 */
public interface IVixntBaseService extends IBaseHibernateService {

	public <T> List<T> findProjectByHql(Map<String, Object> params) throws Exception;

	public List<ProjectUnit> findProjectUnitList(String treeType, String id) throws Exception;
	
	public List<BomUnit> findBomUnitList(String treeType, String id) throws Exception;
	
	public List<BomNode> findBomNodeList(String bomStructId) throws Exception;

	public List<ProjectUnit> findSupplierList(String treeType, String id) throws Exception;

	public List<Employee> findSupplierEmployeeList(String supplierId) throws Exception;

	public List<ProjectUnit> findProjectRoleList(String treeType, String id) throws Exception;

	public List<VixTask> findVixTaskList(String projectId) throws Exception;

	public List<ProjectRole> findParkingCarDistrictAndCountyList(String projectId) throws Exception;
	/**
	 * 
	 * @param treeType
	 * @param id
	 * @param type
	 *            type为 O的时候不查询员工信息
	 * @return
	 * @throws Exception
	 */
	public List<OrgUnit> findOrgAndUnitTreeList(String treeType, String id, String type) throws Exception;

	public List<Employee> findBusinessOrgDetailBo4ReportLine(String tenantId, String queryBusinessOrgDetailBoId) throws Exception;

	/**
	 * 获取上级领导
	 * 
	 * @param tenantId
	 * @param queryBusinessOrgDetailBoId
	 * @return
	 * @throws Exception
	 */
	public List<Employee> findHigherLevelBusinessOrgDetailBo4ReportLine(String tenantId, String queryBusinessOrgDetailBoId) throws Exception;

	public Pager findTaskPager(Pager pager, Map<String, Object> reqParams) throws Exception;

	public Pager findStockRecordLinesPager(Pager pager, Map<String, Object> reqParams) throws Exception;

	public Pager findUploaderPager(Pager pager, Map<String, Object> reqParams) throws Exception;

	public Pager findItemPager(Pager pager, Map<String, Object> reqParams) throws Exception;

	public Pager findItemPagerByHql(Pager pager, Map<String, Object> reqParams) throws Exception;

	public Pager findStoreItemPager(Pager pager, Map<String, Object> reqParams) throws Exception;

	/**
	 * 获取待办任务列表
	 * 
	 * @param reqParams
	 * @return
	 * @throws Exception
	 */
	public List<VixTask> findVixTaskListBySql(Map<String, Object> reqParams) throws Exception;

	public Pager findApplicationMgPager(Pager pager, Map<String, Object> reqParams) throws Exception;

	public List<StoreItem> findStoreItemListByHql(Map<String, Object> reqParams) throws Exception;

	public Pager findWorkLogPager(Pager pager, Map<String, Object> reqParams) throws Exception;

	public Pager findCommunicationPager(Pager pager, Map<String, Object> reqParams) throws Exception;

	public Pager findProjectManagementPager(Pager pager, Map<String, Object> reqParams) throws Exception;

	public Pager findTripRecordPager(Pager pager, Map<String, Object> reqParams) throws Exception;

	public Pager findProjectPager(Pager pager, Map<String, Object> reqParams) throws Exception;

	public Pager findAllTaskPager(Pager pager, Map<String, Object> reqParams) throws Exception;

	public List<Object[]> findEmpCardRecords(String hql) throws Exception;

	public List<EmpCardRecords> getEmpCardRecordsList(Map<String, Object> params) throws Exception;

	public List<EmpDayCardRecords> getEmpDayCardRecordsList(Map<String, Object> params) throws Exception;

	public List<EmpMonthCardRecords> getEmpMonthCardRecordsList(Map<String, Object> params) throws Exception;

	public List<TaskStatisticsBo> getTaskStatisticsBoList(Map<String, Object> params, String syncTag) throws Exception;
	
	public List<TaskStatisticsVo> getCompleteTaskStatisticsVoList() throws Exception;
	
	public List<TaskStatisticsVo> getTimeOutTaskStatisticsVoList() throws Exception;

	public List<WorkLogStatisticsBo> getWorkLogStatisticsBoList(Map<String, Object> params) throws Exception;

	public List<AllWorkLogStatisticsBo> getAllWorkLogStatisticsBoList(Map<String, Object> params) throws Exception;

	public List<InAndOutStatisticsBo> getInAndOutStatisticsBoList(Map<String, Object> params) throws Exception;

	public List<OrderDetailStatisticsBo> getOrderDetailStatisticsBoList(Map<String, Object> params) throws Exception;

	public List<StockRecordLinesVo> getStockRecordLinesVoList(Map<String, Object> params) throws Exception;

	public List<StockRecordLinesVo> getStockRecordLinesPriceVoList(Map<String, Object> params) throws Exception;

	public List<SalesOrderItemVo> getSalesOrderItemVoList(Map<String, Object> params, String tenantId) throws Exception;

	public List<SalesOrderItemVo> getSalesOrderItemNumVoList(Map<String, Object> params, String tenantId) throws Exception;

	/**
	 * 客户购买前十
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<CustomerSalesVo> getCustomerSalesVoList(Map<String, Object> params) throws Exception;

	public List<SalesOrderItemDay> getSalesOrderItemDayList(Map<String, Object> params) throws Exception;

	public List<SalesDataVo> getSalesDataVoList(Map<String, Object> params) throws Exception;

	public List<StockRecordLinesVo> getOutStockRecordLinesVoList(Map<String, Object> params) throws Exception;

	public List<Item> findAllItemByHql(String string, Map<String, Object> params) throws Exception;

	public List<MeasureUnit> findAllMeasureUnitByHql(String string, Map<String, Object> params) throws Exception;

	/**
	 * 门店销售毛利计算
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List<OrderDetaiVo> getOrderDetaiVoList(Map<String, Object> params) throws Exception;

	public Authority findObjectBySql(String sql, Map<String, Object> params) throws Exception;

	public Set<Authority> findRoleMenuAuthorityByUserId(String roleId, String userId, String bizType, String tenantId) throws Exception;

	public <T> List<T> findAllDataByConditions(Class<T> entityClass, Map<String, Object> params) throws Exception;

	public <T> T findObjectByHqlNoTenantId(String hql, Map<String, Object> params) throws Exception;

	/** 查询数量 */
	public long findDataCountByHql(StringBuilder hql, String alilasName, Map<String, Object> params) throws Exception;
}
