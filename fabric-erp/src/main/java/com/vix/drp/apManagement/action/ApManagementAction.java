package com.vix.drp.apManagement.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.order.controller.DistributorOrderController;
import com.vix.hr.organization.entity.Employee;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

import flexjson.JSONDeserializer;

@Controller
@Scope("prototype")
public class ApManagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private DistributorOrderController distributorOrderController;

	private String id;
	private String parentId;
	private String ids;
	private String pageNo;
	private String treeType;
	private SalesOrder salesOrder;

	private List<SalesOrder> salesOrderList;

	private SaleOrderItem saleOrderItem;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();

			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			if (null == id || "0".equals(id)) {
			} else {
				if ("C".equals(treeType)) { // 点击的树节点是公司
				} else if ("CH".equals(treeType)) { // 点击的树节点是分销体系结构
					params.put("channelDistributor.id," + SearchCondition.EQUAL, id);
				}
			}
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = distributorOrderController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					// 获取登录员工所在的部门信息
					if (employee.getChannelDistributor() != null) {
						// 如果登录的员工属于经销商或门店
						ChannelDistributor channelDistributor = employee.getChannelDistributor();
						params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
					}
				}
			}

			//处理搜索条件
			String name = getDecodeRequestParameter("name");
			String title = getDecodeRequestParameter("title");
			String customerAccountName = getDecodeRequestParameter("customerAccountName");
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			if (title != null && !"".equals(title)) {
				params.put("name," + SearchCondition.ANYLIKE, title);
			}
			if (customerAccountName != null && !"".equals(customerAccountName)) {
				params.put("customerAccount.name," + SearchCondition.ANYLIKE, customerAccountName);
			}
			//处理搜索条件
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			salesOrderList = distributorOrderController.doListSalesOrderList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			//倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");

			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			if (null == id || "0".equals(id)) {
			} else {
				if ("C".equals(treeType)) { // 点击的树节点是公司
				} else if ("CH".equals(treeType)) { // 点击的树节点是分销体系结构
					params.put("channelDistributor.id," + SearchCondition.EQUAL, id);
				}
			}
			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = distributorOrderController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					// 获取登录员工所在的部门信息
					if (employee.getChannelDistributor() != null) {
						// 如果登录的员工属于经销商或门店
						ChannelDistributor channelDistributor = employee.getChannelDistributor();
						params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
					}
				}
			}

			//处理搜索条件
			String name = getDecodeRequestParameter("name");
			String title = getDecodeRequestParameter("title");
			String customerAccountName = getDecodeRequestParameter("customerAccountName");
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			if (title != null && !"".equals(title)) {
				params.put("name," + SearchCondition.ANYLIKE, title);
			}
			if (customerAccountName != null && !"".equals(customerAccountName)) {
				params.put("customerAccount.name," + SearchCondition.ANYLIKE, customerAccountName);
			}
			//处理搜索条件
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			pager = distributorOrderController.doListSalesOrder(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				salesOrder = distributorOrderController.doListSalesOrderById(id);
			} else {
				salesOrder = new SalesOrder();
				salesOrder.setIsTemp("1");
				if (parentId != null && !"".equals(parentId)) {
					if ("C".equals(treeType)) { // 点击的树节点是公司
					} else if ("CH".equals(treeType)) { // 点击的树节点是分销体系结构
						ChannelDistributor channelDistributor = distributorOrderController.doListChannelDistributorById(parentId);
						if (channelDistributor != null) {
							salesOrder.setChannelDistributor(channelDistributor);
						}
					}
				}
				distributorOrderController.doSaveSalesOrder(salesOrder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != salesOrder.getId() && !"".equals(salesOrder.getId())) {
				isSave = false;
			}
			salesOrder.setIsTemp("");
			salesOrder.setBizType("S");
			String sojson = getRequestParameter("sojson");
			List<SaleOrderItem> soList = null;
			if (sojson != null && !"".equals(sojson)) {
				soList = new JSONDeserializer<List<SaleOrderItem>>().use("values", SaleOrderItem.class).deserialize(sojson);
			}
			initEntityBaseController.initEntityBaseAttribute(salesOrder);
			distributorOrderController.doSaveSalesOrder(salesOrder, soList);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			SalesOrder salesOrder = distributorOrderController.doListSalesOrderById(id);
			if (null != salesOrder) {
				distributorOrderController.doDeleteByEntity(salesOrder);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String deleteSaleOrderItemById() {
		try {
			SaleOrderItem saleOrderItem = distributorOrderController.doListSaleOrderItemById(id);
			if (null != saleOrderItem) {
				distributorOrderController.doDeleteSaleOrderItemByEntity(saleOrderItem);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public void getSaleOrderItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {//if (null != id && !"".equals(id)) {
				SalesOrder salesOrder = distributorOrderController.doListSalesOrderById(id);
				if (null != salesOrder) {
					json = convertListToJson(new ArrayList<SaleOrderItem>(salesOrder.getSaleOrderItems()), salesOrder.getSaleOrderItems().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取上一条数据
	 * 
	 * @return
	 */
	public String goBeforeSalesOrder() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				salesOrder = distributorOrderController.doListBeforeSalesOrderById(id);
				if (salesOrder == null) {
					salesOrder = distributorOrderController.doListSalesOrderById(id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 获取下一条数据
	 * 
	 * @return
	 */
	public String goAfterSalesOrder() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				salesOrder = distributorOrderController.doListAfterSalesOrderById(id);
				if (salesOrder == null) {
					salesOrder = distributorOrderController.doListSalesOrderById(id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public List<SalesOrder> getSalesOrderList() {
		return salesOrderList;
	}

	public void setSalesOrderList(List<SalesOrder> salesOrderList) {
		this.salesOrderList = salesOrderList;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public SaleOrderItem getSaleOrderItem() {
		return saleOrderItem;
	}

	public void setSaleOrderItem(SaleOrderItem saleOrderItem) {
		this.saleOrderItem = saleOrderItem;
	}

}
