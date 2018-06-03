package com.vix.nvix.srm.action;

import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xml.XmlAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.util.TransformerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.security.util.DaysUtils;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.dtbcenter.vehiclemanagement.entity.Vehicle;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.purchase.arrivalmgr.entity.PurchaseArrival;
import com.vix.mdm.purchase.arrivalmgr.entity.PurchaseArrivalItems;
import com.vix.mdm.purchase.order.entity.NvixOrderBatch;
import com.vix.mdm.purchase.order.entity.NvixPickingListDetail;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.PurchaseOrderLineItem;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrder;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrderItem;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.template.ExcelTemplate;

@Controller
@Scope("prototype")
public class VixntSupplierSalesOrderAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private String purchaseOrderId;
	private String vehicleId;
	private PurchaseOrder purchaseOrder;
	private List<PurchaseOrder> purchaseOrderList;
	private List<NvixPickingListDetail> nvixPickingListDetailList;
	private NvixOrderBatch nvixOrderBatch;
	private RequireGoodsOrder requireGoodsOrder;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	public String goAllDataList() {
		return "goAllDataList";
	}

	public String goAllToDoDataList() {
		return "goAllToDoDataList";
	}

	public String goAllToSellDataList() {
		return "goAllToSellDataList";
	}

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String selectname = getDecodeRequestParameter("selectname");
			if (StringUtils.isNotEmpty(selectname)) {
				params.put("name," + SearchCondition.ANYLIKE, selectname);
			}
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getSupplier() != null && StringUtils.isNotEmpty(employee.getSupplier().getId())) {
					params.put("supplier.id," + SearchCondition.EQUAL, employee.getSupplier().getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goTadayDataList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			params.put("status," + SearchCondition.EQUAL, "4");
			String selectname = getDecodeRequestParameter("selectname");
			if (StringUtils.isNotEmpty(selectname)) {
				params.put("name," + SearchCondition.ANYLIKE, selectname);
			}
			params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(new Date()) + "!" + DaysUtils.getEndDay(new Date()));
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getSupplier() != null && StringUtils.isNotEmpty(employee.getSupplier().getId())) {
					params.put("supplier.id," + SearchCondition.EQUAL, employee.getSupplier().getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
				} else {
					Supplier supplier = vixntBaseService.findEntityByAttribute(Supplier.class, "employee.id", employee.getId());
					if (supplier != null) {
						params.put("supplier.id," + SearchCondition.EQUAL, supplier.getId());
						pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goTadayAllToDoDataList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			params.put("status," + SearchCondition.EQUAL, "0");
			String selectname = getDecodeRequestParameter("selectname");
			if (StringUtils.isNotEmpty(selectname)) {
				params.put("name," + SearchCondition.ANYLIKE, selectname);
			}
			params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(new Date()) + "!" + DaysUtils.getEndDay(new Date()));
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getSupplier() != null && StringUtils.isNotEmpty(employee.getSupplier().getId())) {
					params.put("supplier.id," + SearchCondition.EQUAL, employee.getSupplier().getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
				} else {
					Supplier supplier = vixntBaseService.findEntityByAttribute(Supplier.class, "employee.id", employee.getId());
					if (supplier != null) {
						params.put("supplier.id," + SearchCondition.EQUAL, supplier.getId());
						pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 待配货
	public void goAfterPickingList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			params.put("status," + SearchCondition.EQUAL, "0");

			String selectname = getDecodeRequestParameter("selectname");
			if (StringUtils.isNotEmpty(selectname)) {
				params.put("name," + SearchCondition.ANYLIKE, selectname);
			}
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getSupplier() != null && StringUtils.isNotEmpty(employee.getSupplier().getId())) {
					params.put("supplier.id," + SearchCondition.EQUAL, employee.getSupplier().getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, NvixOrderBatch.class, params);
				} else {
					Supplier supplier = vixntBaseService.findEntityByAttribute(Supplier.class, "employee.id", employee.getId());
					if (supplier != null) {
						params.put("supplier.id," + SearchCondition.EQUAL, supplier.getId());
						pager = vixntBaseService.findPagerByHqlConditions(pager, NvixOrderBatch.class, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 待发货
	public void goToBeShippedList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			params.put("status," + SearchCondition.EQUAL, "1");

			String selectname = getDecodeRequestParameter("selectname");
			if (StringUtils.isNotEmpty(selectname)) {
				params.put("name," + SearchCondition.ANYLIKE, selectname);
			}
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getSupplier() != null && StringUtils.isNotEmpty(employee.getSupplier().getId())) {
					params.put("supplier.id," + SearchCondition.EQUAL, employee.getSupplier().getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, NvixOrderBatch.class, params);
				} else {
					Supplier supplier = vixntBaseService.findEntityByAttribute(Supplier.class, "employee.id", employee.getId());
					if (supplier != null) {
						params.put("supplier.id," + SearchCondition.EQUAL, supplier.getId());
						pager = vixntBaseService.findPagerByHqlConditions(pager, NvixOrderBatch.class, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 配送中
	public void goDispatchingList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			params.put("status," + SearchCondition.EQUAL, "2");
			String selectname = getDecodeRequestParameter("selectname");
			if (StringUtils.isNotEmpty(selectname)) {
				params.put("name," + SearchCondition.ANYLIKE, selectname);
			}
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getSupplier() != null && StringUtils.isNotEmpty(employee.getSupplier().getId())) {
					params.put("supplier.id," + SearchCondition.EQUAL, employee.getSupplier().getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
				} else {
					Supplier supplier = vixntBaseService.findEntityByAttribute(Supplier.class, "employee.id", employee.getId());
					if (supplier != null) {
						params.put("supplier.id," + SearchCondition.EQUAL, supplier.getId());
						pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goAllFinishDataList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			params.put("status," + SearchCondition.EQUAL, "3");
			String selectname = getDecodeRequestParameter("selectname");
			if (StringUtils.isNotEmpty(selectname)) {
				params.put("name," + SearchCondition.ANYLIKE, selectname);
			}
			// params.put("createTime," + SearchCondition.BETWEENAND,
			// DaysUtils.getBeginDay(new Date()) + "!" + DaysUtils.getEndDay(new
			// Date()));
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getSupplier() != null && StringUtils.isNotEmpty(employee.getSupplier().getId())) {
					params.put("supplier.id," + SearchCondition.EQUAL, employee.getSupplier().getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
				} else {
					Supplier supplier = vixntBaseService.findEntityByAttribute(Supplier.class, "employee.id", employee.getId());
					if (supplier != null) {
						params.put("supplier.id," + SearchCondition.EQUAL, supplier.getId());
						pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goTadayAllToSellDataList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String selectname = getDecodeRequestParameter("selectname");
			if (StringUtils.isNotEmpty(selectname)) {
				params.put("name," + SearchCondition.ANYLIKE, selectname);
			}
			params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(new Date()) + "!" + DaysUtils.getEndDay(new Date()));
			params.put("status," + SearchCondition.BETWEENAND, "1" + "!" + "3");
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getSupplier() != null && StringUtils.isNotEmpty(employee.getSupplier().getId())) {
					params.put("supplier.id," + SearchCondition.EQUAL, employee.getSupplier().getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
				} else {
					Supplier supplier = vixntBaseService.findEntityByAttribute(Supplier.class, "employee.id", employee.getId());
					if (supplier != null) {
						params.put("supplier.id," + SearchCondition.EQUAL, supplier.getId());
						pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goTadayAllSellDataList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			params.put("status," + SearchCondition.EQUAL, "3");
			String selectname = getDecodeRequestParameter("selectname");
			if (StringUtils.isNotEmpty(selectname)) {
				params.put("name," + SearchCondition.ANYLIKE, selectname);
			}
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getSupplier() != null && StringUtils.isNotEmpty(employee.getSupplier().getId())) {
					params.put("supplier.id," + SearchCondition.EQUAL, employee.getSupplier().getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
				} else {
					Supplier supplier = vixntBaseService.findEntityByAttribute(Supplier.class, "employee.id", employee.getId());
					if (supplier != null) {
						params.put("supplier.id," + SearchCondition.EQUAL, supplier.getId());
						pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goRequireGoodsOrderSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("deliveryTime");
			}
			String selectname = getDecodeRequestParameter("selectname");
			if (StringUtils.isNotEmpty(selectname)) {
				params.put("name," + SearchCondition.ANYLIKE, selectname);
			}
			params.put("status," + SearchCondition.EQUAL, "0");
			params.put("type," + SearchCondition.EQUAL, "2");
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getSupplier() != null && StringUtils.isNotEmpty(employee.getSupplier().getId())) {
					params.put("supplier.id," + SearchCondition.EQUAL, employee.getSupplier().getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
				} else {
					Supplier supplier = vixntBaseService.findEntityByAttribute(Supplier.class, "employee.id", employee.getId());
					if (supplier != null) {
						params.put("supplier.id," + SearchCondition.EQUAL, supplier.getId());
						pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goOverRequireGoodsOrderSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String selectname = getDecodeRequestParameter("selectname");
			if (StringUtils.isNotEmpty(selectname)) {
				params.put("name," + SearchCondition.ANYLIKE, selectname);
			}
			params.put("status," + SearchCondition.NOEQUAL, "0");
			params.put("type," + SearchCondition.EQUAL, "2");
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getSupplier() != null && StringUtils.isNotEmpty(employee.getSupplier().getId())) {
					params.put("supplier.id," + SearchCondition.EQUAL, employee.getSupplier().getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
				} else {
					Supplier supplier = vixntBaseService.findEntityByAttribute(Supplier.class, "employee.id", employee.getId());
					if (supplier != null) {
						params.put("supplier.id," + SearchCondition.EQUAL, supplier.getId());
						pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goPurchaseOrderLineItemList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String itemName = getDecodeRequestParameter("itemName");
			if (StringUtils.isNotEmpty(itemName)) {
				params.put("itemName," + SearchCondition.ANYLIKE, itemName);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("purchaseOrder.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseOrderLineItem.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goNvixPickingListDetailList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String itemName = getDecodeRequestParameter("itemName");
			if (StringUtils.isNotEmpty(itemName)) {
				params.put("itemName," + SearchCondition.ANYLIKE, itemName);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("nvixOrderBatch.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, NvixPickingListDetail.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goPurchaseOrderList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String itemName = getDecodeRequestParameter("itemName");
			if (StringUtils.isNotEmpty(itemName)) {
				params.put("itemName," + SearchCondition.ANYLIKE, itemName);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("nvixOrderBatch.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseOrder.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	// 按批次打印发货单
	public String goPrintOrder() {
		try {
			Map<String, Object> params = getParams();
			params.put("nvixOrderBatch.id," + SearchCondition.EQUAL, id);
			purchaseOrderList = vixntBaseService.findAllByConditions(PurchaseOrder.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintOrder";
	}

	// 按批次打印发货单
	public String goPrintOrderBatch() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				nvixOrderBatch = vixntBaseService.findEntityById(NvixOrderBatch.class, id);
				Map<String, Object> params = getParams();
				params.put("nvixOrderBatch.id," + SearchCondition.EQUAL, id);
				nvixPickingListDetailList = vixntBaseService.findAllByConditions(NvixPickingListDetail.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintOrderBatch";
	}

	// 配货完成
	public void prepareItemOver() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				NvixOrderBatch nvixOrderBatch = vixntBaseService.findEntityById(NvixOrderBatch.class, id);
				nvixOrderBatch.setStatus("1");
				nvixOrderBatch = vixntBaseService.merge(nvixOrderBatch);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("nvixOrderBatch.id," + SearchCondition.EQUAL, id);
				List<PurchaseOrder> purchaseOrderList = vixntBaseService.findAllByConditions(PurchaseOrder.class, params);
				if (purchaseOrderList != null && purchaseOrderList.size() > 0) {
					for (PurchaseOrder purchaseOrder : purchaseOrderList) {
						if (purchaseOrder != null) {
							purchaseOrder.setStatus("1");
							purchaseOrder = vixntBaseService.merge(purchaseOrder);
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goSaleOrderItemList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("requireGoodsOrder.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrderItem.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdatePurchaseOrder() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				purchaseOrder = vixntBaseService.findEntityById(PurchaseOrder.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdatePurchaseOrder";
	}

	// 显示批次单
	public String goShowNvixOrderBatch() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				nvixOrderBatch = vixntBaseService.findEntityById(NvixOrderBatch.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowNvixOrderBatch";
	}

	public String goShowPurchaseOrder() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				nvixOrderBatch = vixntBaseService.findEntityById(NvixOrderBatch.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPurchaseOrder";
	}

	/**
	 * 
	 */
	public void saveOrUpdate() {
		try {
			initEntityBaseController.initEntityBaseAttribute(purchaseOrder);
			purchaseOrder = vixntBaseService.merge(purchaseOrder);
			if (purchaseOrder != null) {
				renderText(purchaseOrder.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goVehicleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String searchvehicleno = getRequestParameter("searchvehicleno");
			if (StringUtils.isNotEmpty(searchvehicleno)) {
				params.put("vehicleNO" + SearchCondition.ANYLIKE, searchvehicleno);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), Vehicle.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goChooseVehicle() {
		return "goChooseVehicle";
	}

	// 派车配货
	public void sendToVehicle() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				NvixOrderBatch nvixOrderBatch = vixntBaseService.findEntityById(NvixOrderBatch.class, id);
				nvixOrderBatch.setStatus("2");
				nvixOrderBatch = vixntBaseService.merge(nvixOrderBatch);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("nvixOrderBatch.id," + SearchCondition.EQUAL, id);
				List<PurchaseOrder> purchaseOrderList = vixntBaseService.findAllByConditions(PurchaseOrder.class, params);
				if (purchaseOrderList != null && purchaseOrderList.size() > 0) {
					for (PurchaseOrder purchaseOrder : purchaseOrderList) {
						if (purchaseOrder != null) {
							if (StringUtils.isNotEmpty(vehicleId)) {
								Vehicle vehicle = vixntBaseService.findEntityById(Vehicle.class, vehicleId);
								if (vehicle != null) {
									Map<String, Object> params1 = getParams();
									params.put("purchaseOrder.id,"+SearchCondition.EQUAL, purchaseOrder.getId());
									purchaseOrder.setVehicle(vehicle);
									purchaseOrder.setStatus("2");
									purchaseOrder = vixntBaseService.merge(purchaseOrder);
									vehicle.setStatus("2");
									vehicle = vixntBaseService.merge(vehicle);
									//生成采购到货单
									PurchaseArrival purchaseArrival = new PurchaseArrival();
									purchaseArrival.setName("到货订单" + dateFormat.format(new Date()));
									purchaseArrival.setCode(sdf.format(new Date()));
									if(null != purchaseOrder.getSupplier()){
										purchaseArrival.setSupplierId(purchaseOrder.getSupplier().getId());
										purchaseArrival.setSupplierName(purchaseOrder.getSupplier().getName());
									}
									purchaseArrival.setCreateTime(new Date());
									Employee employee = getEmployee();
									if (employee != null) {
										purchaseArrival.setCreator(employee.getName());
										purchaseArrival.setPurchasePerson(employee.getName());
										purchaseArrival.setPurchasePersonId(employee.getId());
									}
									purchaseArrival.setTotalAmount(purchaseOrder.getTotalAmount());
									purchaseArrival = vixntBaseService.merge(purchaseArrival);
									List<PurchaseOrderLineItem> purchaseOrderLineItemList = vixntBaseService.findAllByConditions(PurchaseOrderLineItem.class, params1);
									if(purchaseOrderLineItemList != null && purchaseOrderLineItemList.size() >0){
										for (PurchaseOrderLineItem purchaseOrderLineItem : purchaseOrderLineItemList) {
											if(purchaseOrderLineItem != null){
												PurchaseArrivalItems temp = new PurchaseArrivalItems();
												temp.setPoCode(purchaseOrder.getCode());
												temp.setPoItemsCode(purchaseOrderLineItem.getCode());
												temp.setItemCode(purchaseOrderLineItem.getItemCode());
												temp.setItemName(purchaseOrderLineItem.getItemName());
												temp.setSpecification(purchaseOrderLineItem.getSpecification());
												temp.setItemType(purchaseOrderLineItem.getItemType());
												temp.setUnit(purchaseOrderLineItem.getUnit());
												temp.setPrice(purchaseOrderLineItem.getPrice());
												temp.setAmount(purchaseOrderLineItem.getAmount());
												temp.setNetTotal(purchaseOrderLineItem.getNetTotal());
												temp.setSupplier(purchaseOrder.getSupplierName());
												temp.setRecieveWareHouse(purchaseOrderLineItem.getRecieveAddress());
												temp.setRequireTime(purchaseOrder.getCreateTime());
												temp.setPurchaseArrival(purchaseArrival);
												temp = vixntBaseService.merge(temp);
											}
										}
									}
									if (purchaseOrder.getRequireGoodsOrder() != null) {
										RequireGoodsOrder requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, purchaseOrder.getRequireGoodsOrder().getId());
										if (requireGoodsOrder != null) {
											requireGoodsOrder.setStatus("2");
											requireGoodsOrder.setVehicle(vehicle);
											requireGoodsOrder = vixntBaseService.merge(requireGoodsOrder);
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String exportPurchaseOrderExcel() throws Exception {
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");

			String ua = getRequest().getHeader("user-agent");
			String fileName = "配送商品列表.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			Map<String, Object> params = getParams();
			params.put("nvixOrderBatch.id," + SearchCondition.EQUAL, id);
			List<PurchaseOrder> purchaseOrderList = vixntBaseService.findAllByConditions(PurchaseOrder.class, params);
			List<PurchaseOrderLineItem> itemList = new ArrayList<PurchaseOrderLineItem>();
			for (PurchaseOrder purchaseOrder : purchaseOrderList) {
				Map<String, Object> p = getParams();
				p.put("purchaseOrder.id," + SearchCondition.EQUAL, purchaseOrder.getId());
				List<PurchaseOrderLineItem> purchaseOrderLineItemList = vixntBaseService.findAllByConditions(PurchaseOrderLineItem.class, p);
				if (purchaseOrderLineItemList != null && purchaseOrderLineItemList.size() > 0) {
					itemList.addAll(purchaseOrderLineItemList);
				}
			}

			try (InputStream is = ExcelTemplate.class.getResourceAsStream("purchaseorder_collection_xmlbuilder.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("purchaseorder_collection_xmlbuilder.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("purchaseOrderLineItemList", itemList);
					xlsArea.applyAt(new CellRef("purchaseOrderLineItem!A1"), context);
					transformer.write();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 分拣
	 */
	public void pickOrder() {
		try {
			Map<String, Object> params = getParams();
			params.put("status," + SearchCondition.EQUAL, "4");
			Employee employee = getEmployee();
			Supplier supplier = null;
			if (employee != null) {
				if (employee.getSupplier() != null && StringUtils.isNotEmpty(employee.getSupplier().getId())) {
					params.put("supplier.id," + SearchCondition.EQUAL, employee.getSupplier().getId());
					supplier = employee.getSupplier();
				} else {
					supplier = vixntBaseService.findEntityByAttribute(Supplier.class, "employee.id", employee.getId());
					if (supplier != null) {
						params.put("supplier.id," + SearchCondition.EQUAL, supplier.getId());
					}
				}
				List<PurchaseOrder> purchaseOrderList = vixntBaseService.findAllByConditions(PurchaseOrder.class, params);

				if (purchaseOrderList != null && purchaseOrderList.size() > 0) {
					dealOrder(supplier, purchaseOrderList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param purchaseOrderList
	 * @throws Exception
	 */
	private void dealOrder(Supplier supplier, List<PurchaseOrder> purchaseOrderList) throws Exception {
		// 创建批次单
		NvixOrderBatch nvixOrderBatch = null;
		nvixOrderBatch = vixntBaseService.findEntityByAttribute(NvixOrderBatch.class, "code", sdf.format(new Date()));
		if (nvixOrderBatch != null) {

		} else {
			nvixOrderBatch = new NvixOrderBatch();
			nvixOrderBatch.setCode(sdf.format(new Date()));
			nvixOrderBatch.setName("拣货单" + dateFormat.format(new Date()));
			nvixOrderBatch.setStatus("0");
			nvixOrderBatch.setSupplier(supplier);
			initEntityBaseController.initEntityBaseAttribute(nvixOrderBatch);
			nvixOrderBatch = vixntBaseService.merge(nvixOrderBatch);
		}

		if (purchaseOrderList != null && purchaseOrderList.size() > 0) {
			for (PurchaseOrder purchaseOrder : purchaseOrderList) {
				Set<PurchaseOrderLineItem> purchaseOrderLineItemList = purchaseOrder.getPurchaseOrderLineItems();
				for (PurchaseOrderLineItem purchaseOrderLineItem : purchaseOrderLineItemList) {
					if (purchaseOrderLineItem != null) {
						Map<String, Object> p = getParams();
						NvixPickingListDetail nvixPickingListDetail = null;
						if (StringUtils.isNotEmpty(purchaseOrderLineItem.getItemCode())) {
							nvixPickingListDetail = vixntBaseService.findObjectByHql("select nvixPickingListDetail from NvixPickingListDetail nvixPickingListDetail where nvixPickingListDetail.itemCode ='" + purchaseOrderLineItem.getItemCode() + "' and nvixPickingListDetail.nvixOrderBatch.id ='" + nvixOrderBatch.getId() + "'", p);
						}
						if (nvixPickingListDetail != null) {
							nvixPickingListDetail.setAmount(nvixPickingListDetail.getAmount() + purchaseOrderLineItem.getAmount());
							nvixPickingListDetail = vixntBaseService.merge(nvixPickingListDetail);
						} else {
							nvixPickingListDetail = new NvixPickingListDetail();
							nvixPickingListDetail.setItemCode(purchaseOrderLineItem.getItemCode());
							nvixPickingListDetail.setItemName(purchaseOrderLineItem.getItemName());
							nvixPickingListDetail.setSpecification(purchaseOrderLineItem.getSpecification());
							nvixPickingListDetail.setUnit(purchaseOrderLineItem.getUnit());
							nvixPickingListDetail.setPrice(purchaseOrderLineItem.getPrice());
							nvixPickingListDetail.setAmount(purchaseOrderLineItem.getAmount());
							if (nvixOrderBatch != null) {
								nvixPickingListDetail.setNvixOrderBatch(nvixOrderBatch);
							}
							initEntityBaseController.initEntityBaseAttribute(nvixPickingListDetail);
							nvixPickingListDetail = vixntBaseService.merge(nvixPickingListDetail);
						}
					}
				}
				// 更改订单状态
				purchaseOrder.setNvixOrderBatch(nvixOrderBatch);
				purchaseOrder.setStatus("1");
				purchaseOrder = vixntBaseService.merge(purchaseOrder);
				RequireGoodsOrder requireGoodsOrder = purchaseOrder.getRequireGoodsOrder();
				if (requireGoodsOrder != null) {
					// 同步更改要货单状态
					requireGoodsOrder.setStatus("1");
					requireGoodsOrder = vixntBaseService.merge(requireGoodsOrder);
				}
			}
		}
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public RequireGoodsOrder getRequireGoodsOrder() {
		return requireGoodsOrder;
	}

	public void setRequireGoodsOrder(RequireGoodsOrder requireGoodsOrder) {
		this.requireGoodsOrder = requireGoodsOrder;
	}

	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public NvixOrderBatch getNvixOrderBatch() {
		return nvixOrderBatch;
	}

	public void setNvixOrderBatch(NvixOrderBatch nvixOrderBatch) {
		this.nvixOrderBatch = nvixOrderBatch;
	}

	public List<PurchaseOrder> getPurchaseOrderList() {
		return purchaseOrderList;
	}

	public void setPurchaseOrderList(List<PurchaseOrder> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
	}

	public List<NvixPickingListDetail> getNvixPickingListDetailList() {
		return nvixPickingListDetailList;
	}

	public void setNvixPickingListDetailList(List<NvixPickingListDetail> nvixPickingListDetailList) {
		this.nvixPickingListDetailList = nvixPickingListDetailList;
	}

}
