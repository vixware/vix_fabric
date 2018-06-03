package com.vix.nvix.warehouse.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.billtype.BillType;
import com.vix.common.job.entity.FlowApprovalOpinion;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.BaseEntity;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.inbound.entity.StockRecordLines;
import com.vix.inventory.inbound.entity.StockRecords;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.inventory.option.entity.InventoryParameters;
import com.vix.inventory.outbound.controller.OutBoundController;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.service.IItemService;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class VixntOutBoundAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(VixntOutBoundAction.class);
	@Autowired
	private OrderProcessController orderProcessController;
	@Autowired
	private OutBoundController outBoundController;
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;
	private String id;
	private String ids;
	private String taskId;
	private String salesOrderId;
	private StockRecords stockRecords;
	private List<StockRecords> stockRecordsList;

	private String categoryId;
	private String masterInventoryCurrentStockId;
	/**
	 * 销售订单
	 */
	private SalesOrder salesOrder;
	/**
	 * 销售订单明细
	 */
	private SaleOrderItem saleOrderItem;
	private List<SaleOrderItem> saleOrderItemList;
	private List<SalesOrder> salesOrderList;
	/**
	 * 出库单明细
	 */
	private StockRecordLines stockRecordLines;
	private InventoryParameters inventoryParameters;
	private List<StockRecordLines> stockRecordLinesList;
	private String purchaseOrderid;
	private String isAllowAudit;// 是否允许提交审批 1：是 0：否
	@Autowired
	private IItemService itemService;
	private String str;
	private List<BaseEntity> baseEntityList;
	private List<FlowApprovalOpinion> flowApprovalOpinionList;

	/** 获取列表数据 */
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			// 过滤出库单flag 出入库标志
			params.put("flag," + SearchCondition.EQUAL, "2");
			String title = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(title)) {
				params.put("name," + SearchCondition.ANYLIKE, title);
			}
			pager = outBoundController.doListStockrecords(params, pager);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goStockRecordLinesList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String searchProductName = getDecodeRequestParameter("searchProductName");
			if (StringUtils.isNotEmpty(searchProductName)) {
				params.put("itemname," + SearchCondition.ANYLIKE, searchProductName);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("stockRecords.id," + SearchCondition.EQUAL, id);
				pager = outBoundController.doListStockRecordLinesPager(params, pager);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 子项价格汇总 */
	public void getStockRecordTotal() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				StockRecords stockRecords = outBoundController.doListStockRecordsById(id);
				if (null != stockRecords) {
					Double totalAmount = 0d;
					for (StockRecordLines wimStockrecordlines : stockRecords.getSubStockRecordLines()) {
						totalAmount += wimStockrecordlines.getUnitcost() * wimStockrecordlines.getQuantity();
					}
					stockRecords.setTotalAmount(totalAmount);
					stockRecords = outBoundController.doSaveOrUpdateStockRecords(stockRecords);
					DecimalFormat df = new DecimalFormat(".##");
					String st = df.format(totalAmount);
					renderJson(st);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			isAllowAudit = isAllowAudit(BillType.INV_OUTBOUND);
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = outBoundController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_OUTBOUND));
				Employee employee = getEmployee();
				if (employee != null) {
					stockRecords.setCreator(employee.getName());
				}
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("type", "1");
				p.put("isDefault", "0");
				StringBuilder itemhql = standingBookHqlProvider.findInvWarehouseHql(p);
				InvWarehouse invWarehouse = vixntBaseService.findObjectByHql(itemhql.toString(), p);
				if (invWarehouse != null) {
					stockRecords.setInvWarehouse(invWarehouse);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		try {
			/** 出库明细 */
			String biztype = getRequestParameter("biztype");
			// biztype 业务类型 1 ：销售出库, 2 ：材料出库, 3：其他出库单
			stockRecords.setBiztype(biztype);
			// status 审核状态0：未提交 ,1：待审核, 2：审核通过
			// 新建的入库单默认为未提交状态
			stockRecords.setStatus("0");
			// flag 出入库标志 1：入库 ，2：出库
			stockRecords.setFlag("2");
			initEntityBaseController.initEntityBaseAttribute(stockRecords);
			if (stockRecords != null && StringUtils.isNotEmpty(updateField)) {
				// 调用修改留痕
				billMarkProcessController.processMark(stockRecords, updateField);
			}
			stockRecords = outBoundController.doSaveOrUpdateStockRecords(stockRecords);

			// 更改采购订单的状态
			if (purchaseOrderid != null && !"".equals(purchaseOrderid)) {
				updatePurchaseOrderStatus();
			}
			renderText(stockRecords.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void audit() {
		try {
			/** 出库明细 */
			String biztype = getRequestParameter("biztype");
			// biztype 业务类型 1 ：销售出库, 2 ：材料出库, 3：其他出库单
			stockRecords.setBiztype(biztype);
			// status 审核状态0：未提交 ,1：待审核, 2：审核通过
			// 新建的入库单默认为未提交状态
			stockRecords.setStatus("0");
			// flag 出入库标志 1：入库 ，2：出库
			stockRecords.setFlag("2");
			initEntityBaseController.initEntityBaseAttribute(stockRecords);
			if (stockRecords != null && StringUtils.isNotEmpty(updateField)) {
				// 调用修改留痕
				billMarkProcessController.processMark(stockRecords, updateField);
			}
			stockRecords = outBoundController.doSaveOrUpdateStockRecords(stockRecords);

			// 更改采购订单的状态
			if (purchaseOrderid != null && !"".equals(purchaseOrderid)) {
				updatePurchaseOrderStatus();
			}
			if ("1".equals(isAllowAudit(BillType.INV_OUTBOUND))) {
				String response = dealStartAndSubmitByBillsCode(BillType.INV_OUTBOUND, stockRecords);
				if (StringUtils.isNotEmpty(response)) {
					JSONObject json = JSONObject.fromObject(response);
					if (json.has("status")) {
						if ("1".equals(json.getString("status"))) {
							stockRecords.setStatus("1");
							stockRecords = outBoundController.doSaveOrUpdateStockRecords(stockRecords);
							renderText(stockRecords.getId() + ":" + "提交成功!");
						}
					}
				}
			} else {
				renderText("提交失败!没有绑定工作流");
			}
		} catch (Exception e) {
			renderText("提交失败!");
			e.printStackTrace();
		}
	}
	/**
	 * @throws Exception
	 */
	private void updatePurchaseOrderStatus() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id," + SearchCondition.IN, purchaseOrderid);
		List<SalesOrder> salesOrderList = outBoundController.doListSalesOrderList(params);
		if (salesOrderList != null && salesOrderList.size() > 0) {
			for (SalesOrder salesOrder : salesOrderList) {
				if (salesOrder != null) {
					// 修改采购订单的状态
					salesOrder.setStatus("已完成");
					outBoundController.doSaveSalesOrder(salesOrder);
				}
			}
		}
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			StockRecords pb = outBoundController.doListStockRecordsById(id);
			if (null != pb) {
				outBoundController.doDeleteStockRecords(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/** 获取明细的json数据 */

	public void getDeliveryAddress() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				StockRecords so = outBoundController.doListStockRecordsById(id);
				if (so != null) {
					json = convertListToJson(new ArrayList<StockRecordLines>(so.getSubStockRecordLines()), so.getSubStockRecordLines().size());
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

	/** 批量处理删除操作 */
	public void deleteByIds() {
		try {
			if (StringUtils.isNotEmpty(ids) && !"0".equals(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				outBoundController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/** 跳转至新增物料出库单 */
	public String goMaterialOutBound() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = outBoundController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setIsTemp("1");
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_OUTBOUND));
				stockRecords = outBoundController.doSaveOrUpdateStockRecords(stockRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMaterialOutBound";
	}

	/** 跳转至新增其他出库单 */
	public String goOtherOutBound() {
		try {
			isAllowAudit = isAllowAudit(BillType.INV_OUTBOUND);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = outBoundController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setIsTemp("1");
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_OUTBOUND));
				stockRecords = outBoundController.doSaveOrUpdateStockRecords(stockRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goOtherOutBound";
	}

	/** 跳转至新增网店出库单 */
	public String goBusinessOrder() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = outBoundController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setIsTemp("1");
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_OUTBOUND));
				stockRecords = outBoundController.doSaveOrUpdateStockRecords(stockRecords);
				businessOrderToStockrecords(stockRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goBusinessOrder";
	}

	/**
	 * 增加出库单明细
	 * 
	 * @return
	 */
	public void saveOrUpdateWimStockRecordLines() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = outBoundController.doListStockRecordsById(id);
				if (stockRecords != null) {
					stockRecordLines.setStockRecords(stockRecords);
				}
				stockRecordLines.setFlag("2");
				// 计算金额 ：金额 = 单价 * 数量
				Double price = null;
				if (stockRecordLines.getQuantity() != null && stockRecordLines.getUnitcost() != null) {
					price = stockRecordLines.getQuantity() * stockRecordLines.getUnitcost();
					if (price != null) {
						stockRecordLines.setPrice(price);
					}
				}
				// 单位为空的处理方式
				if (stockRecordLines.getMeasureUnit() == null || StringUtils.isEmpty(stockRecordLines.getMeasureUnit().getId())) {
					stockRecordLines.setMeasureUnit(null);
				}
				// 货位为空的处理方式
				if (stockRecordLines.getInvShelf() == null || StringUtils.isEmpty(stockRecordLines.getInvShelf().getId())) {
					stockRecordLines.setInvShelf(null);
				}

				initEntityBaseController.initEntityBaseAttribute(stockRecordLines);
				outBoundController.doSaveStockRecordLines(stockRecordLines);
			}
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
	}

	/**
	 * 条形码扫描入库
	 * 
	 * @return
	 */
	public void saveOrUpdateStockRecordLines() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = outBoundController.doListStockRecordsById(id);
				if (stockRecords != null) {
					String itemcode = getRequestParameter("itemcode");
					Item item = outBoundController.doListItem(itemcode);
					if (item != null) {
						Map<String, Object> itemparams = new HashMap<String, Object>();
						itemparams.put("itemcode", item.getCode());
						itemparams.put("stockRecordsId", id);
						StringBuilder itemhql = standingBookHqlProvider.findStockRecordLinesByItemCode(itemparams);
						StockRecordLines invStockRecordLines = vixntBaseService.findObjectByHql(itemhql.toString(), itemparams);
						if (invStockRecordLines != null) {
							invStockRecordLines.setQuantity(invStockRecordLines.getQuantity() + 1);
							outBoundController.doSaveStockRecordLines(invStockRecordLines);
						} else {
							invStockRecordLines = new StockRecordLines();
							invStockRecordLines.setStockRecords(stockRecords);
							invStockRecordLines.setItemcode(item.getCode());
							invStockRecordLines.setItemname(item.getName());
							invStockRecordLines.setSpecification(item.getSpecification());
							if (item.getMeasureUnit() != null) {
								invStockRecordLines.setUnit(item.getMeasureUnit().getName());
								invStockRecordLines.setMeasureUnit(item.getMeasureUnit());
							}
							if (StringUtils.isNotEmpty(item.getSupplierId())) {
								Supplier supplier = vixntBaseService.findEntityById(Supplier.class, item.getSupplierId());
								if (supplier != null) {
									invStockRecordLines.setSupplier(supplier);
								}
							}
							invStockRecordLines.setUnitcost(item.getPrice());
							invStockRecordLines.setSkuCode(item.getSkuCode());
							invStockRecordLines.setQuantity(1D);
							invStockRecordLines.setFlag("1");
							initEntityBaseController.initEntityBaseAttribute(invStockRecordLines);
							outBoundController.doSaveStockRecordLines(invStockRecordLines);
						}
						Double totalAmount = 0d;
						for (StockRecordLines wimStockrecordlines : stockRecords.getSubStockRecordLines()) {
							totalAmount += wimStockrecordlines.getUnitcost() * wimStockrecordlines.getQuantity();
						}
						stockRecords.setTotalAmount(totalAmount);
						stockRecords = vixntBaseService.merge(stockRecords);
						DecimalFormat df = new DecimalFormat(".##");
						String st = df.format(totalAmount);
						renderJson(st);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/** 子项价格汇总 */
	public void getOrderItemTotal() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				StockRecords stockRecords = outBoundController.doListStockRecordsById(id);
				if (null != stockRecords) {
					Double totalAmount = 0d;
					for (StockRecordLines stockRecordLines : stockRecords.getSubStockRecordLines()) {
						totalAmount += stockRecordLines.getUnitcost() * stockRecordLines.getQuantity();
					}
					DecimalFormat df = new DecimalFormat(".##");
					String st = df.format(totalAmount);
					renderJson(st);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getOrderItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				StockRecords stockRecords = outBoundController.doListStockRecordsById(id);
				if (null != stockRecords) {
					json = convertListToJson(new ArrayList<StockRecordLines>(stockRecords.getSubStockRecordLines()), stockRecords.getSubStockRecordLines().size());
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

	/** 处理删除操作 */
	public void deleteWimStockrecordlinesById() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				StockRecordLines stockRecordLines = outBoundController.doListStockRecordLinesById(id);
				if (null != stockRecordLines) {
					outBoundController.deleteStockRecordLines(stockRecordLines);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	// 跳转到添加物料页面
	public String goAddSaleOrderItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecordLines = itemService.findEntityById(StockRecordLines.class, id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddSaleOrderItem";
	}

	// 跳转到选择销售订单页面
	public String goChooseSalesOrder() {
		return "goChooseSalesOrder";
	}

	/** 获取销售订单列表数据 */
	public String goSalesOrderList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 倒序排序
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			// 按状态
			params.put("status," + SearchCondition.NOEQUAL, "已完成");
			// 按最近使用
			pager = outBoundController.goPurchaseOrder(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSalesOrderList";
	}

	public String getSalesOrderItemList() {
		try {
			if (StringUtils.isNotEmpty(purchaseOrderid) && !"0".equals(purchaseOrderid)) {
				salesOrder = outBoundController.findSalesOrder(purchaseOrderid);
				Set<SaleOrderItem> saleOrderItems = salesOrder.getSaleOrderItems();
				if (saleOrderItems.size() > 0) {
					saleOrderItemList = new ArrayList<SaleOrderItem>(saleOrderItems);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "getSalesOrderItemList";
	}

	/**
	 * 将销售订单明细转化成采购入库单明细
	 * 
	 * @return
	 */
	public void converterSalesOrderToStockrecords() {
		try {
			stockRecords = outBoundController.doListStockRecordsById(id);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id," + SearchCondition.IN, purchaseOrderid);
			List<SalesOrder> salesOrderList = outBoundController.doListSalesOrderList(params);
			if (salesOrderList != null && salesOrderList.size() > 0) {
				for (SalesOrder salesOrder : salesOrderList) {
					if (salesOrder != null) {
						outBoundController.convPurchaseOrderToStockrecords(stockRecords, salesOrder);
						renderText(SAVE_SUCCESS);
					}
				}
			}
		} catch (Exception e) {
			renderText(SAVE_FAIL);
			e.printStackTrace();
		}
	}

	/**
	 * 将网店订单转化成出库单
	 * 
	 * @return
	 */
	public void businessOrderToStockrecords(StockRecords wimStockrecords) {
		String businussOrderIds = getRequestParameter("businussOrderId");
		if (businussOrderIds != null && !"".equals(businussOrderIds)) {
			String[] businussOrderIdArr = businussOrderIds.split(",");
			if (businussOrderIdArr != null && businussOrderIdArr.length > 0) {
				for (String businussOrderId : businussOrderIdArr) {
					if (businussOrderId != null && !"".equals(businussOrderId)) {
						// 根据id查询网店订单
						try {
							Order order = outBoundController.findOrder(businussOrderId);
							if (order != null) {
								outBoundController.businessOrderToStockrecords(wimStockrecords, order);
								renderText(SAVE_SUCCESS);
							}
						} catch (Exception e) {
							renderText(SAVE_FAIL);
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	/**
	 * @return
	 */
	public String goSaveOrUpdateStockRecordLines() {
		try {
			inventoryParameters = outBoundController.doListInventoryParametersByAttribute("tenantId", SecurityUtil.getCurrentUserTenantId());
			if (StringUtils.isNotEmpty(salesOrderId)) {
				stockRecords = outBoundController.doListStockRecordsById(salesOrderId);
			}
			if (StringUtils.isNotEmpty(id)) {
				stockRecordLines = itemService.findEntityById(StockRecordLines.class, id);
			} else {
				stockRecordLines = new StockRecordLines();
				if (stockRecords != null) {
					stockRecordLines.setStockRecords(stockRecords);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goSaveOrUpdateStockRecordLines";
	}

	public String goListItemList() {
		try {
			Map<String, Object> params = getParams();
			// 倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager.setPageSize(7);
			pager = outBoundController.doListMasterInventoryCurrentStockPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListItemList";
	}

	public String goInventoryCurrentStockList() {
		return "goInventoryCurrentStockList";
	}

	@SuppressWarnings("unchecked")
	public void goInventoryCurrentStockListContent() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			String itemname = getDecodeRequestParameter("name");
			if (StringUtils.isNotEmpty(itemname)) {
				params.put("itemname," + SearchCondition.ANYLIKE, itemname.trim());
			}
			String warehouseId = getDecodeRequestParameter("warehouseId");
			if (StringUtils.isNotEmpty(warehouseId)) {
				InvWarehouse warehouse = vixntBaseService.findEntityById(InvWarehouse.class, warehouseId);
				if (null != warehouse) {
					params.put("warehousecode," + SearchCondition.EQUAL, warehouse.getCode());
					params.put("warehouse," + SearchCondition.EQUAL, warehouse.getName());
				}
			}
			pager = itemService.findPagerByHqlConditions(pager, InventoryCurrentStock.class, params);
			if (pager.getResultList().size() < 10) {
				int listSize = pager.getResultList().size();
				for (int i = 0; i < 10 - listSize; i++) {
					pager.getResultList().add(new InventoryCurrentStock());
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goPrintStockRecords() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = outBoundController.doListStockRecordsById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintStockRecords";
	}

	/**
	 * 
	 * @return
	 */
	public String goShowOutBound() {

		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = outBoundController.doListStockRecordsById(id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sourceClassPk," + SearchCondition.EQUAL, id);
				flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowOutBound";
	}

	/**
	 * 查看销售订单源
	 * 
	 * @return
	 */
	public String goShowSalesOrder() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = outBoundController.doListStockRecordsById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowSalesOrder";
	}

	public String goListSalesOrderContent() {
		try {
			Map<String, Object> params = getParams();
			salesOrderList = outBoundController.doListSalesOrderList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListSalesOrderContent";
	}

	public String goShowBeforeAndAfter() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				stockRecords = outBoundController.doListStockRecordsById(id);
				if (stockRecords != null && stockRecords.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(stockRecords.getCreateTime()));
					// flag 2: 过滤
					params.put("flag", "2");
					// 过滤临时数据
					params.put("isTemp", "");
					params.put("tenantId", stockRecords.getTenantId());
					params.put("companyInnerCode", stockRecords.getCompanyInnerCode());
					params.put("creator", stockRecords.getCreator());
					if ("before".equals(str)) {
						stockRecords = (StockRecords) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(stockRecords.getCreateTime()), params, stockRecords, "before");
					} else if ("after".equals(str)) {
						stockRecords = (StockRecords) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(stockRecords.getCreateTime()), params, stockRecords, "after");
					}
					if (stockRecords == null || StringUtils.isEmpty(stockRecords.getId())) {
						stockRecords = outBoundController.doListStockRecordsById(id);
					}
				}
				Map<String, Object> params1 = new HashMap<String, Object>();
				params.put("sourceClassPk," + SearchCondition.EQUAL, stockRecords.getId());
				flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowOutBound";
	}
	public String goSourceList() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = vixntBaseService.findEntityById(StockRecords.class, id);
				if (stockRecords != null) {
					baseEntityList = new ArrayList<>();
					baseEntityList.add(stockRecords);
					if (StringUtils.isNotEmpty(stockRecords.getSourceBillCode()) && StringUtils.isNotEmpty(stockRecords.getSourceClassName())) {
						getSourceBaseEntity(baseEntityList, stockRecords);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSourceList";
	}

	/**
	 * 查看销售订单源
	 * 
	 * @return
	 */
	public String goShowPurchaseOrder() {
		return "goShowPurchaseOrder";
	}
	public String goAudit() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = outBoundController.doListStockRecordsById(id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sourceClassPk," + SearchCondition.EQUAL, id);
				flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAudit";
	}
	/**
	 * @return the isAllowAudit
	 */
	public String getIsAllowAudit() {
		return isAllowAudit;
	}

	/**
	 * @param isAllowAudit
	 *            the isAllowAudit to set
	 */
	public void setIsAllowAudit(String isAllowAudit) {
		this.isAllowAudit = isAllowAudit;
	}

	public String goSearch() {
		return "goSearch";
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<SalesOrder> getSalesOrderList() {
		return salesOrderList;
	}

	public void setSalesOrderList(List<SalesOrder> salesOrderList) {
		this.salesOrderList = salesOrderList;
	}

	public StockRecords getStockRecords() {
		return stockRecords;
	}

	public void setStockRecords(StockRecords stockRecords) {
		this.stockRecords = stockRecords;
	}

	public String getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public List<StockRecords> getStockRecordsList() {
		return stockRecordsList;
	}

	public void setStockRecordsList(List<StockRecords> stockRecordsList) {
		this.stockRecordsList = stockRecordsList;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public SaleOrderItem getSaleOrderItem() {
		return saleOrderItem;
	}

	public void setSaleOrderItem(SaleOrderItem saleOrderItem) {
		this.saleOrderItem = saleOrderItem;
	}

	public List<SaleOrderItem> getSaleOrderItemList() {
		return saleOrderItemList;
	}

	public void setSaleOrderItemList(List<SaleOrderItem> saleOrderItemList) {
		this.saleOrderItemList = saleOrderItemList;
	}

	public StockRecordLines getStockRecordLines() {
		return stockRecordLines;
	}

	public void setStockRecordLines(StockRecordLines stockRecordLines) {
		this.stockRecordLines = stockRecordLines;
	}

	public List<StockRecordLines> getStockRecordLinesList() {
		return stockRecordLinesList;
	}

	public void setStockRecordLinesList(List<StockRecordLines> stockRecordLinesList) {
		this.stockRecordLinesList = stockRecordLinesList;
	}

	public String getMasterInventoryCurrentStockId() {
		return masterInventoryCurrentStockId;
	}

	public void setMasterInventoryCurrentStockId(String masterInventoryCurrentStockId) {
		this.masterInventoryCurrentStockId = masterInventoryCurrentStockId;
	}

	public String getPurchaseOrderid() {
		return purchaseOrderid;
	}

	public void setPurchaseOrderid(String purchaseOrderid) {
		this.purchaseOrderid = purchaseOrderid;
	}

	public InventoryParameters getInventoryParameters() {
		return inventoryParameters;
	}

	public void setInventoryParameters(InventoryParameters inventoryParameters) {
		this.inventoryParameters = inventoryParameters;
	}

	/**
	 * @return the taskId
	 */
	@Override
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId
	 *            the taskId to set
	 */
	@Override
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public List<BaseEntity> getBaseEntityList() {
		return baseEntityList;
	}

	public void setBaseEntityList(List<BaseEntity> baseEntityList) {
		this.baseEntityList = baseEntityList;
	}

	public List<FlowApprovalOpinion> getFlowApprovalOpinionList() {
		return flowApprovalOpinionList;
	}

	public void setFlowApprovalOpinionList(List<FlowApprovalOpinion> flowApprovalOpinionList) {
		this.flowApprovalOpinionList = flowApprovalOpinionList;
	}
}
