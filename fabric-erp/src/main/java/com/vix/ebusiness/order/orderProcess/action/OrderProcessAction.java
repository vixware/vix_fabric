package com.vix.ebusiness.order.orderProcess.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.entity.OrderDetail;
import com.vix.ebusiness.option.entity.Logistics;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.ebusiness.order.orderProcess.processor.OrderDownloadProcessor;
import com.vix.ebusiness.order.orderProcess.processor.OrderPickingProcessor;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.service.IItemService;

/**
 * 
 * com.vix.ebusiness.order.orderProcess.action.OrderProcessAction
 *
 * @author zhanghaibing
 *
 * @date 2014年9月23日
 */
@Controller
@Scope("prototype")
public class OrderProcessAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(OrderProcessAction.class);
	@Autowired
	private OrderProcessController orderProcessController;
	@Autowired
	private OrderDownloadProcessor orderDownloadProcessor;
	@Autowired
	private OrderPickingProcessor orderPickingProcessor;
	@Autowired
	private IItemService itemService;
	private String id;
	//仓库id
	private String warehouseId;
	//员工id
	private String employeesId;
	//
	private String employeeId;
	//物流公司ID
	private String logisticsId;
	//分类ID
	private String categoryId;
	private String ids;
	private String pageNo;
	/* 销售订单 */
	private Order order;
	private List<Order> orderList;
	private String parentId;
	private String treeType;

	private List<InvWarehouse> invWarehouseList;
	private String channelId;
	private List<Logistics> logisticsList;
	private List<Employee> employeeList;
	private String orderId;
	private String orderIds;
	private OrderDetail orderDetail;
	private List<ChannelDistributor> channelDistributorList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			params.put("type," + SearchCondition.ANYLIKE, "5");
			channelDistributorList = orderProcessController.doListChannelDistributorList(params);
			orderList = orderProcessController.doListOrderIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 未处理的订单
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			pager.setPageSize(100);
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			params.put("dealState," + SearchCondition.EQUAL, 1);
			if (treeType != null && !"".equals(treeType) && "CH".equals(treeType)) {
				if (StringUtils.isNotEmpty(parentId)) {
					params.put("channelDistributor.id," + SearchCondition.EQUAL, parentId);
				}
			}
			pager = orderProcessController.doListOrder(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 搜索
	 * 
	 * @return
	 */
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager.setPageSize(100);
			params.put("dealState," + SearchCondition.EQUAL, 1);
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			//处理查询条件
			String outerId = getRequestParameter("outerId");
			if (null != outerId && !"".equals(outerId)) {
				params.put("outerId," + SearchCondition.EQUAL, outerId);
			}
			String receiverName = getDecodeRequestParameter("receiverName");
			if (null != receiverName && !"".equals(receiverName)) {
				params.put("buyerNick," + SearchCondition.ANYLIKE, receiverName);
			}
			String receiverMobile = getRequestParameter("receiverMobile");
			if (null != receiverMobile && !"".equals(receiverMobile)) {
				params.put("receiverMobile," + SearchCondition.EQUAL, receiverMobile);
			}
			String payTypeCn = getDecodeRequestParameter("payTypeCn");
			if (null != payTypeCn && !"".equals(payTypeCn)) {
				params.put("payTypeCn," + SearchCondition.ANYLIKE, payTypeCn);
			}
			String createTime = getRequestParameter("createTime");
			if (null != createTime && !"".equals(createTime)) {
				params.put("createTime," + SearchCondition.EQUAL, formatStringToDate(createTime));
			}
			String channelName = getDecodeRequestParameter("channelName");
			if (null != channelName && !"".equals(channelName)) {
				params.put("channelDistributor.name," + SearchCondition.ANYLIKE, channelName);
			}
			String receiverAddress = getDecodeRequestParameter("receiverAddress");
			if (null != receiverAddress && !"".equals(receiverAddress)) {
				params.put("receiverAddress," + SearchCondition.ANYLIKE, receiverAddress);
			}
			String num = getRequestParameter("num");
			if (null != num && !"".equals(num)) {
				params.put("num," + SearchCondition.EQUAL, num);
			}
			//处理查询条件
			if (treeType != null && !"".equals(treeType) && "CH".equals(treeType)) {
				params.put("channelDistributor.id," + SearchCondition.EQUAL, parentId);
			}
			pager = orderProcessController.doListOrder(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 已分拣的订单
	 * 
	 * @return
	 */
	public String goOutStandingOrdersList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager.setPageSize(100);
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			params.put("dealState," + SearchCondition.EQUAL, 7);
			pager = orderProcessController.doListOrder(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSalesOrder1List";
	}

	public String updateOrderDetail() {
		boolean isSave = true;
		try {
			if (null != order.getId() && !"".equals(order.getId())) {
				Order o = orderProcessController.doListOrderById(order.getId());
				if (o != null) {
					o.setSellerMemo(order.getSellerMemo());
					order = orderProcessController.doSaveOrder(o);
				}
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	public String dealOrder() {
		try {
			Employee employee = null;
			if (StringUtils.isNotEmpty(employeesId) && !"0".equals(employeesId)) {
				employee = orderProcessController.doListEmployeeById(employeesId);
			}
			if (StringUtils.isNotEmpty(orderIds)) {
				String[] orderidArr = orderIds.split(",");
				if (orderidArr != null && !"".equals(orderidArr) && orderidArr.length > 0) {
					for (String orderid : orderidArr) {
						if (orderid != null && !"".equals(orderid)) {
							Order order = orderProcessController.doListOrderById(orderid);
							if (order != null) {
								if (employee != null) {
									order.setEmployee(employee);
									orderProcessController.doSaveOrder(order);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UPDATE;
	}

	/**
	 * 待发货
	 * 
	 * @return
	 */
	public String goSalesOrder2List() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager.setPageSize(100);
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			params.put("dealState," + SearchCondition.EQUAL, 3);
			pager = orderProcessController.doListOrder(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSalesOrder2List";
	}

	/**
	 * 已发货
	 * 
	 * @return
	 */
	public String goSalesOrder3List() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setPageSize(100);
			pager.setOrderField("id");
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			params.put("dealState," + SearchCondition.EQUAL, 4);
			pager = orderProcessController.doListOrder(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSalesOrder3List";
	}

	public String goSalesOrder4List() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager.setPageSize(100);
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			params.put("dealState," + SearchCondition.EQUAL, 5);
			pager = orderProcessController.doListOrder(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSalesOrder4List";
	}

	// 搜索
	public String goSearchItemList() {
		try {
			Map<String, Object> params = getParams();
			String tag = getRequestParameter("tag");
			Pager pager = getPager();
			pager.setPageSize(100);
			if ("0".equals(tag)) {
				// 简单搜索
				pager = orderProcessController.doListOrderByCon(params, pager);
			} else {
				pager = orderProcessController.doListOrder(params, pager);
			}
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				order = orderProcessController.doListOrderById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 获取上一条数据
	 * 
	 * @return
	 */
	public String goBeforeOrder() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				Order order = orderProcessController.doListOrderById(id);
				if (order != null && order.getCreateTime() != null) {
					params.put("createTime", order.getCreateTime());
					order = (Order) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(order.getCreateTime()), params, order, "before");
					if (order == null || StringUtils.isEmpty(order.getId())) {
						order = orderProcessController.doListOrderById(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 
	 * @return
	 */
	public String goAfterOrder() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				Order order = orderProcessController.doListOrderById(id);
				if (order != null && order.getCreateTime() != null) {
					params.put("createTime", order.getCreateTime());
					order = (Order) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(order.getCreateTime()), params, order, "after");
					if (order == null || StringUtils.isEmpty(order.getId())) {
						order = orderProcessController.doListOrderById(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goCheckList() {
		try {
			employeeList = orderProcessController.doListEmployeeList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goCheckList";
	}

	public String saveOrUpdateOrder() {
		boolean isSave = true;
		try {
			Employee employee = null;
			if (StringUtils.isNotEmpty(employeeId) && !"0".equals(employeeId)) {
				employee = orderProcessController.doListEmployeeById(employeeId);
			}
			if (StringUtils.isNotEmpty(orderId) && !"0".equals(orderId)) {
				String[] orderIds = orderId.split(",");
				if (orderIds != null && orderIds.length > 0) {
					for (String orderid : orderIds) {
						if (orderid != null && !"".equals(orderid)) {
							Order order = orderProcessController.doListOrderById(orderid);
							if (employee != null) {
								orderProcessController.doSaveOrder(order);
							}
						}
					}
				}
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	/**
	 * 订单分拣
	 * 
	 * @return
	 */
	public String goOrderPicking() {
		return "goOrderPicking";
	}

	/**
	 * 批量分拣 并将订单处理状态改成分拣中状态
	 * 
	 * @return
	 */
	public String goOrderPickingList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager.setPageSize(100);
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			params.put("dealState," + SearchCondition.EQUAL, 6);
			if (orderIds != null && !"".equals(orderIds)) {
				params.put("id," + SearchCondition.IN, orderIds);
				String[] orderidarr = orderIds.split(",");
				for (String orderid : orderidarr) {
					if (orderid != null && !"".equals(orderid)) {
						Order order = orderProcessController.doListOrderById(orderid);
						if (order != null) {
							order.setDealState(6);
							order = orderProcessController.doSaveOrder(order);
						}
					}
				}
			}
			pager = orderProcessController.doListOrder(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goOrderPickingList";
	}

	/**
	 * 按仓库分拣 客服处理
	 * 
	 * @return
	 */
	public String saveOrUpdateTwoSorting() {
		boolean isSave = true;
		try {
			InvWarehouse invWarehouse = null;
			if (StringUtils.isNotEmpty(warehouseId) && !"0".equals(warehouseId)) {
				invWarehouse = orderProcessController.doListInvWarehouseById(warehouseId);
			}
			if (StringUtils.isNotEmpty(orderId)) {
				String[] orderIds = orderId.split(",");
				if (orderIds != null && orderIds.length > 0) {
					for (String orderid : orderIds) {
						if (orderid != null && !"".equals(orderid)) {
							Order order = orderProcessController.doListOrderById(orderid);
							if (invWarehouse != null) {
								order.setInvWarehouse(invWarehouse);
								// 已分仓处理
								order.setDealState(7);
								orderProcessController.doSaveOrder(order);
							}
						}
					}
				}
			}
			if (isSave) {
				setMessage("分拣完成");
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				this.setMessage("分拣失败");
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	/**
	 * 分物流
	 * 
	 * @return
	 */
	public String saveOrUpdateLogistics() {
		boolean isSave = true;
		try {
			Logistics logistics = null;
			if (logisticsId != null && !"".equals(logisticsId)) {
				logistics = orderProcessController.doListLogisticsById(logisticsId);
			}
			if (orderId != null && !"".equals(orderId)) {
				Map<String, Object> params = getParams();
				params.put("id," + SearchCondition.IN, orderId);
				List<Order> orderList = orderProcessController.doListOrderList(params);
				if (orderList != null && orderList.size() > 0) {
					for (Order order : orderList) {
						order.setLogistics(logistics);
						orderProcessController.doSaveOrder(order);
					}
				}
			}
			if (isSave) {
				setMessage("分物流完成");
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				this.setMessage("分物流失败");
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	/*分拣*/
	public String saveOrUpdateThreeSorting() {
		boolean isSave = true;
		try {
			if (orderId != null && !"".equals(orderId)) {
				Map<String, Object> params = getParams();
				params.put("id," + SearchCondition.IN, orderId);
				if (logisticsId != null && !"".equals(logisticsId)) {
					params.put("logistics.id," + SearchCondition.EQUAL, logisticsId);
				}
				List<Order> orderList = orderProcessController.doListOrderList(params);
				if (orderList != null && orderList.size() > 0) {
					orderPickingProcessor.preproccessOrderData(orderList);
				}
			}
			if (isSave) {
				setMessage("分拣完成");
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				this.setMessage("分拣失败");
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	/**
	 * 二次分拣
	 * 
	 * @return
	 */
	public String goTwoSorting() {
		try {
			invWarehouseList = orderProcessController.doListInvWarehouseList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goTwoSorting";
	}

	/**
	 * 三次分拣
	 * 
	 * @return
	 */
	public String goThreeSorting() {
		try {
			logisticsList = orderProcessController.doListLogisticsList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goThreeSorting";
	}

	/**
	 * 弹出查询条件界面
	 * 
	 * @return
	 */
	public String goSearchOrder() {
		return "goSearchOrder";
	}

	public String searchOrder() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager = orderProcessController.doListOrder(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public void getOrderDetailJson() {
		try {
			String json = "";
			if (null != id && !"".equals(id)) {
				Order order = orderProcessController.doListOrderById(id);
				if (order != null) {
					json = convertListToJson(new ArrayList<OrderDetail>(order.getOrderDetailList()), order.getOrderDetailList().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 下载订单
	 * 
	 * @return
	 */
	public String goDownloadOrder() {
		try {
			if (StringUtils.isNotEmpty(channelId) && !"0".equals(channelId)) {
				orderDownloadProcessor.process(channelId);
			} else {
				Map<String, Object> params = getParams();
				params.put("type," + SearchCondition.ANYLIKE, "5");
				List<ChannelDistributor> channelDistributorList = orderProcessController.doListChannelDistributorList(params);
				if (channelDistributorList != null) {
					for (ChannelDistributor channelDistributor : channelDistributorList) {
						if (channelDistributor != null) {
							orderDownloadProcessor.process(channelDistributor.getId());
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return UPDATE;
	}

	//手动添加订单明细
	public String goSaveOrUpdateOrderDetail() {
		return "goSaveOrUpdateOrderDetail";
	}

	//手动添加订单明细
	public String goOrderDetailList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("itemClass," + SearchCondition.IN, "goods,finishedgoods");
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("id");
				getPager().setOrderBy("desc");
			}
			getPager().setPageSize(5);
			if (null != categoryId && !"".equals(categoryId)) {
				Pager pager = itemService.findPagerByItemCatalogId(getPager(), categoryId, params);
				setPager(pager);
			} else {
				itemService.findPagerByHqlConditions(getPager(), Item.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goOrderDetailList";
	}

	// 保存手动创建的订单明细
	public String saveOrUpdateOrderDetail() {
		boolean isSave = true;
		try {
			if (null != orderDetail.getId() && !"".equals(orderDetail.getId())) {
				isSave = false;
			}
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Order order = orderProcessController.doListOrderById(id);
				if (order != null) {
					orderDetail.setOrder(order);
					orderDetail = orderProcessController.doSaveOrderDetail(orderDetail);
				}
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	public String goSearch() {
		return "goSearch";
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public String getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(String orderIds) {
		this.orderIds = orderIds;
	}

	public List<InvWarehouse> getInvWarehouseList() {
		return invWarehouseList;
	}

	public void setInvWarehouseList(List<InvWarehouse> invWarehouseList) {
		this.invWarehouseList = invWarehouseList;
	}

	public List<Logistics> getLogisticsList() {
		return logisticsList;
	}

	public void setLogisticsList(List<Logistics> logisticsList) {
		this.logisticsList = logisticsList;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public List<ChannelDistributor> getChannelDistributorList() {
		return channelDistributorList;
	}

	public void setChannelDistributorList(List<ChannelDistributor> channelDistributorList) {
		this.channelDistributorList = channelDistributorList;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the warehouseId
	 */
	public String getWarehouseId() {
		return warehouseId;
	}

	/**
	 * @param warehouseId
	 *            the warehouseId to set
	 */
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	/**
	 * @return the employeesId
	 */
	public String getEmployeesId() {
		return employeesId;
	}

	/**
	 * @param employeesId
	 *            the employeesId to set
	 */
	public void setEmployeesId(String employeesId) {
		this.employeesId = employeesId;
	}

	/**
	 * @return the employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId
	 *            the employeeId to set
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the logisticsId
	 */
	public String getLogisticsId() {
		return logisticsId;
	}

	/**
	 * @param logisticsId
	 *            the logisticsId to set
	 */
	public void setLogisticsId(String logisticsId) {
		this.logisticsId = logisticsId;
	}

	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the channelId
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * @param channelId
	 *            the channelId to set
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public OrderDetail getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}

}
