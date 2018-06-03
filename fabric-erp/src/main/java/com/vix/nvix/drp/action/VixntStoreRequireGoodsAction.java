package com.vix.nvix.drp.action;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.billtype.BillType;
import com.vix.common.scheduling.entity.Calendars;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.inbound.entity.StockRecordLines;
import com.vix.inventory.inbound.entity.StockRecords;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.mdm.item.entity.StoreItem;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.PurchaseOrderLineItem;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrder;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrderItem;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.restService.test.submodule.service.ISubmoduleRestService;
import com.vix.wechat.base.service.IWechatBaseService;

@Controller
@Scope("prototype")
public class VixntStoreRequireGoodsAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private String ids;
	private String salesOrderId;
	private String requireGoodsOrderId;
	private RequireGoodsOrder requireGoodsOrder;
	private StockRecords stockRecords;
	private RequireGoodsOrderItem requireGoodsOrderItem;
	@Autowired
	public ISubmoduleRestService submoduleRestService;
	@Autowired
	private StandingBookHqlProvider standingBookHqlProvider;
	@Autowired
	public IWechatBaseService wechatBaseService;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	/**
	 * 价格
	 */
	private Double price;

	private Double vipPrice;
	/**
	 * 可用数量
	 */
	private Double avaquantity;

	public void goSingleList() {
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
			params.put("type,"+SearchCondition.EQUAL, "2");
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
					// 如果登录的员工属于经销商或门店
					params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
				} else {
					ChannelDistributor channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
					if (channelDistributor != null) {
						// 如果登录的员工属于经销商或门店
						params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
						pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 待收货
	public void goToRevList() {
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
			params.put("status," + SearchCondition.EQUAL, "2");
			params.put("type,"+SearchCondition.EQUAL, "2");
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
					// 如果登录的员工属于经销商或门店
					params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
				} else {
					ChannelDistributor channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
					if (channelDistributor != null) {
						// 如果登录的员工属于经销商或门店
						params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
						pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 待入库
	public void goToInvList() {
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
			params.put("status," + SearchCondition.EQUAL, "3");
			params.put("type,"+SearchCondition.EQUAL, "2");
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
					// 如果登录的员工属于经销商或门店
					params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
				} else {
					ChannelDistributor channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
					if (channelDistributor != null) {
						// 如果登录的员工属于经销商或门店
						params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
						pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// tofin
	public void goToFinList() {
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
			params.put("status," + SearchCondition.EQUAL, "4");
			params.put("type,"+SearchCondition.EQUAL, "2");
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
					// 如果登录的员工属于经销商或门店
					params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
				} else {
					ChannelDistributor channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
					if (channelDistributor != null) {
						// 如果登录的员工属于经销商或门店
						params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
						pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goAllSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("deliveryTime");
			}
			params.put("status," + SearchCondition.EQUAL, "1");
			params.put("type,"+SearchCondition.EQUAL, "2");
			String selectname = getDecodeRequestParameter("selectname");
			if (StringUtils.isNotEmpty(selectname)) {
				params.put("name," + SearchCondition.ANYLIKE, selectname);
			}
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
					// 如果登录的员工属于经销商或门店
					params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
				} else {
					ChannelDistributor channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
					if (channelDistributor != null) {
						// 如果登录的员工属于经销商或门店
						params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
						pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goSaleOrderItemList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 倒序排序
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String itemName = getDecodeRequestParameter("itemName");
			if (StringUtils.isNotEmpty(itemName)) {
				params.put("title," + SearchCondition.ANYLIKE, itemName);
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

	public void goPurchaseOrderList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 倒序排序
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String itemName = getDecodeRequestParameter("itemName");
			if (StringUtils.isNotEmpty(itemName)) {
				params.put("title," + SearchCondition.ANYLIKE, itemName);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("requireGoodsOrder.id," + SearchCondition.EQUAL, id);
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
			} else {
				requireGoodsOrder = new RequireGoodsOrder();
				requireGoodsOrder.setName("要货单" + dateFormat.format(new Date()));
				requireGoodsOrder.setDeliveryTime(new Date());
				Employee employee = getEmployee();
				if (employee != null) {
					requireGoodsOrder.setCreator(employee.getName());
					if (employee.getChannelDistributor() != null) {
						requireGoodsOrder.setChannelDistributor(employee.getChannelDistributor());
						requireGoodsOrder.setCode(autoCreateCode.getBillNO(employee.getChannelDistributor().getChineseCharacter()));
					} else {
						ChannelDistributor channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
						if (channelDistributor != null) {
							requireGoodsOrder.setCode(autoCreateCode.getBillNO(channelDistributor.getChineseCharacter()));
							requireGoodsOrder.setChannelDistributor(channelDistributor);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public void goInvWarehouseList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();

			Pager pager = getPager();
			// 排序
			if (StringUtils.isEmpty(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			params.put("type," + SearchCondition.EQUAL, "2");

			String name = getDecodeRequestParameter("selectName");
			if (StringUtils.isNotEmpty(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}

			// 获取当前登录用户所在的公司或供应商
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
					// 如果登录的员工属于经销商或门店
					params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, InvWarehouse.class, params);
				} else {
					ChannelDistributor channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
					if (channelDistributor != null) {
						// 如果登录的员工属于经销商或门店
						params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
						pager = vixntBaseService.findPagerByHqlConditions(pager, InvWarehouse.class, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String show() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}

	/**
	 * 确认收货
	 * 
	 * @return
	 */
	public String goConfirmReceipt() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goConfirmReceipt";
	}

	public String goUpdatePriceAndAmount() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goUpdatePriceAndAmount";
	}

	public String goSaveOrUpdatePurchaseOrder() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdatePurchaseOrder";
	}

	/**
	 * 
	 */
	public void saveOrUpdate() {
		try {
			initEntityBaseController.initEntityBaseAttribute(requireGoodsOrder);
			requireGoodsOrder.setStatus("0");
			requireGoodsOrder.setType("2");
			requireGoodsOrder = vixntBaseService.merge(requireGoodsOrder);
			if (requireGoodsOrder != null) {
				List<RequireGoodsOrder> requireGoodsOrderList = new ArrayList<RequireGoodsOrder>();
				requireGoodsOrderList.add(requireGoodsOrder);

				ChannelDistributor channelDistributor = null;
				Employee employee = getEmployee();
				if (employee != null) {
					if (employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
						channelDistributor = employee.getChannelDistributor();
					} else {
						channelDistributor = vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
					}
				}
				collectRequireGoodsOrder(requireGoodsOrder, channelDistributor);
				renderText(requireGoodsOrder.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 要货单拆单
	 */
	public void collectRequireGoodsOrder(RequireGoodsOrder requireGoodsOrder, ChannelDistributor channelDistributor) {
		try {
			Map<String, List<RequireGoodsOrderItem>> map = new HashMap<String, List<RequireGoodsOrderItem>>();
			if (requireGoodsOrder != null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("requireGoodsOrder.id", requireGoodsOrder.getId());
				List<RequireGoodsOrderItem> requireGoodsOrderItemList = wechatBaseService.findAllDataByConditions(RequireGoodsOrderItem.class, params);
				if (requireGoodsOrderItemList != null && requireGoodsOrderItemList.size() > 0) {
					for (RequireGoodsOrderItem requireGoodsOrderItem : requireGoodsOrderItemList) {
						if (requireGoodsOrderItem != null) {
							Map<String, Object> par = new HashMap<String, Object>();
							par.put("code", requireGoodsOrderItem.getItemCode());
							par.put("channelDistributorId", channelDistributor.getId());
							StringBuilder phql = standingBookHqlProvider.findStoreItemByItemCode(par);
							StoreItem storeItem = submoduleRestService.findObjectByHqlNoTenantId(phql.toString(), par);
							String supplierId = "";
							if (storeItem != null && StringUtils.isNotEmpty(storeItem.getSupplierId())) {
								Supplier supplier = submoduleRestService.findEntityById(Supplier.class, storeItem.getSupplierId());
								if (supplier != null) {
									supplierId = supplier.getId();
								}
							}
							if (map.containsKey(supplierId)) {
								map.get(supplierId).add(requireGoodsOrderItem);
							} else {
								List<RequireGoodsOrderItem> l = new ArrayList<RequireGoodsOrderItem>();
								l.add(requireGoodsOrderItem);
								map.put(supplierId, l);
							}
						}
					}
				}
				dealBill(channelDistributor, map, requireGoodsOrder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param channelDistributor
	 * @param map
	 * @param requireGoodsOrder
	 * @throws Exception
	 */
	private void dealBill(ChannelDistributor channelDistributor, Map<String, List<RequireGoodsOrderItem>> map, RequireGoodsOrder requireGoodsOrder) throws Exception {
		if (map != null && map.size() > 0) {
			for (Map.Entry<String, List<RequireGoodsOrderItem>> entry : map.entrySet()) {
				Supplier supplier = vixntBaseService.findEntityById(Supplier.class, entry.getKey());
				PurchaseOrder purchaseOrder = null;
				if (supplier != null) {
					purchaseOrder = vixntBaseService.findEntityByAttribute(PurchaseOrder.class, "code", supplier.getChineseCharacter() + sdf.format(new Date()));
					if (purchaseOrder != null) {

					} else {
						purchaseOrder = new PurchaseOrder();
						purchaseOrder.setCode(supplier.getChineseCharacter() + sdf.format(new Date()));
						purchaseOrder.setSupplier(supplier);
						purchaseOrder.setName("采购订单" + dateFormat.format(new Date()));
						purchaseOrder.setStatus("4");
						purchaseOrder.setCreateTime(new Date());
						purchaseOrder.setPurchasePerson(channelDistributor.getName());
						purchaseOrder.setPurchasePersonId(channelDistributor.getId());
						purchaseOrder.setChannelDistributor(channelDistributor);
						purchaseOrder.setRequireGoodsOrder(requireGoodsOrder);
						purchaseOrder.setType("0");
						purchaseOrder.setSourceBillCode(requireGoodsOrder.getCode());
						purchaseOrder.setSourceClassName(requireGoodsOrder.getClass().getName());
						initEntityBaseController.initEntityBaseAttribute(purchaseOrder);
						purchaseOrder = vixntBaseService.merge(purchaseOrder);
					}
				}
				List<RequireGoodsOrderItem> requireGoodsOrderItemList = entry.getValue();
				for (RequireGoodsOrderItem requireGoodsOrderItem : requireGoodsOrderItemList) {
					if (requireGoodsOrderItem != null) {
						Map<String, Object> p = getParams();
						PurchaseOrderLineItem purchaseOrderLineItem = vixntBaseService.findObjectByHql("select purchaseOrderLineItem from PurchaseOrderLineItem purchaseOrderLineItem where itemCode ='" + requireGoodsOrderItem.getItemCode() + "' and purchaseOrderLineItem.purchaseOrder.id ='" + purchaseOrder.getId() + "'", p);
						if (purchaseOrderLineItem != null) {
							purchaseOrderLineItem.setAmount(purchaseOrderLineItem.getAmount() + requireGoodsOrderItem.getAmount());
							purchaseOrderLineItem = vixntBaseService.merge(purchaseOrderLineItem);
						} else {
							purchaseOrderLineItem = new PurchaseOrderLineItem();
							purchaseOrderLineItem.setItemCode(requireGoodsOrderItem.getItemCode());
							purchaseOrderLineItem.setItemName(requireGoodsOrderItem.getTitle());
							purchaseOrderLineItem.setSkuCode(requireGoodsOrderItem.getSkuCode());
							purchaseOrderLineItem.setPrice(requireGoodsOrderItem.getPrice());
							purchaseOrderLineItem.setAmount(requireGoodsOrderItem.getAmount());
							purchaseOrderLineItem.setUnit(requireGoodsOrderItem.getUnit());
							purchaseOrderLineItem.setCreateTime(new Date());
							initEntityBaseController.initEntityBaseAttribute(purchaseOrderLineItem);
							if (purchaseOrder != null) {
								purchaseOrderLineItem.setPurchaseOrder(purchaseOrder);
							}
							purchaseOrderLineItem = vixntBaseService.merge(purchaseOrderLineItem);
						}
					}
				}
				Double totalAmount = 0d;
				for (PurchaseOrderLineItem purchaseOrderLineItem : purchaseOrder.getPurchaseOrderLineItems()) {
					totalAmount += purchaseOrderLineItem.getTatalFee();
				}
				purchaseOrder.setTotalAmount(totalAmount);
				purchaseOrder = vixntBaseService.merge(purchaseOrder);
				// 创建消息
				Calendars calendars = new Calendars();
				calendars.setScheduleName(purchaseOrder.getName());
				calendars.setStartDay(purchaseOrder.getCreateTime());
				calendars.setTaskType("3");
				calendars.setEndDay(purchaseOrder.getCreateTime());
				calendars.setPriority("bg-color-orange txt-color-white");
				calendars.setCalendarsContent(purchaseOrder.getName());
				calendars.setIsHasRemind("1");
				calendars.setStatus("0");
				if(supplier != null&&StringUtils.isNotEmpty(supplier.getEmployeeId())){
					Employee employee = vixntBaseService.findEntityById(Employee.class, supplier.getEmployeeId());
					if(employee != null){
						calendars.setEmployee(employee);
						calendars.setCreator(employee.getName());
					}
				}
				initEntityBaseController.initEntityBaseAttribute(calendars);
				defaultCalendarsAdapter.updateCalendars(calendars);
			}
		}
	}

	/**
	 * 处理入库
	 */
	public String converterPurchaseOrderToStockrecords() {
		try {
			stockRecords = new StockRecords();
			stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_INBOUND));
			stockRecords.setName("采购入库" + dateFormat.format(new Date()));
			Employee employee = getEmployee();
			if (employee != null) {
				stockRecords.setCreator(employee.getName());
				stockRecords.setWarehousePerson(employee.getName());
				if (employee.getChannelDistributor() != null) {
					// 如果登录的员工属于经销商或门店
					stockRecords.setChannelDistributor(employee.getChannelDistributor());
				}
			}
			stockRecords.setBiztype("1");
			// status 审核状态0：未提交 1：待审核 2：审核中3：审核通过
			stockRecords.setStatus("0");
			// flag 出入库标志 1：入库 ，2：出库
			stockRecords.setFlag("1");
			initEntityBaseController.initEntityBaseAttribute(stockRecords);
			// 获取默认仓库
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("type," + SearchCondition.EQUAL, "2");
			if (employee.getChannelDistributor() != null) {
				p.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
			} else {
				ChannelDistributor channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
				if (channelDistributor != null) {
					p.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
				}
			}
			List<InvWarehouse> invWarehouseList = vixntBaseService.findAllByConditions(InvWarehouse.class, p);
			if (invWarehouseList != null && invWarehouseList.size() > 0) {
				for (InvWarehouse invWarehouse : invWarehouseList) {
					if ("0".equals(invWarehouse.getIsDefault())) {
						stockRecords.setInvWarehouse(invWarehouse);
					}
				}
			}
			stockRecords = vixntBaseService.merge(stockRecords);
			requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, requireGoodsOrderId);
			if (requireGoodsOrder != null && stockRecords != null) {
				convPurchaseOrderToStockRecords(stockRecords, requireGoodsOrder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateInbond";
	}

	public void convPurchaseOrderToStockRecords(StockRecords stockRecords, RequireGoodsOrder requireGoodsOrder) throws Exception {
		if (requireGoodsOrder != null && requireGoodsOrder.getSubRequireGoodsOrderItems() != null) {
			Double totalAmount = 0d;
			for (RequireGoodsOrderItem requireGoodsOrderItem : requireGoodsOrder.getSubRequireGoodsOrderItems()) {
				// 将要货单明细转化成入库单明细
				if (requireGoodsOrderItem != null) {
					StockRecordLines stockRecordLines = new StockRecordLines();
					stockRecordLines.setPoCode(requireGoodsOrder.getCode());
					stockRecordLines.setPurchaseOrderItemCode(requireGoodsOrderItem.getCode());
					stockRecordLines.setItemcode(requireGoodsOrderItem.getItemCode());
					stockRecordLines.setItemname(requireGoodsOrderItem.getTitle());
					stockRecordLines.setSkuCode(requireGoodsOrderItem.getSkuCode());
					stockRecordLines.setSpecification(requireGoodsOrderItem.getSpecification());
					stockRecordLines.setWarehouseCode(stockRecords.getWarehousecode());
					stockRecordLines.setUnit(requireGoodsOrderItem.getUnit());
					stockRecordLines.setUnitcost(requireGoodsOrderItem.getPrice());
					stockRecordLines.setQuantity(requireGoodsOrderItem.getActualAmount());
					stockRecordLines.setSupplier(requireGoodsOrderItem.getSupplier());
					Double price = null;
					if (requireGoodsOrderItem.getPrice() != null && !"".equals(requireGoodsOrderItem.getPrice()) && requireGoodsOrderItem.getAmount() != null && !"".equals(requireGoodsOrderItem.getAmount())) {
						price = requireGoodsOrderItem.getPrice() * requireGoodsOrderItem.getAmount();
						totalAmount += price;
						stockRecordLines.setPrice(price);
					}
					stockRecordLines.setStockRecords(stockRecords);
					vixntBaseService.merge(stockRecordLines);
				}
			}
			stockRecords.setTotalAmount(totalAmount);
			stockRecords = vixntBaseService.merge(stockRecords);
		}
	}

	public StringBuilder findInventoryCurrentStockHql(Map<String, Object> params) {
		StringBuilder hql = new StringBuilder();
		String ename = "inventoryCurrentStock";
		hql.append(" from InventoryCurrentStock ").append(ename);
		hql.append(" where 1=1 ");
		if (params != null) {
			if (params.containsKey("itemcode")) {
				Object itemcode = params.get("itemcode");
				if (itemcode == null) {
					hql.append(" and ").append(ename).append(".itemcode is null");
					params.remove("itemcode");
				} else {
					hql.append(" and ").append(ename).append(".itemcode = :itemcode ");
				}
			}
			if (params.containsKey("channelDistributorId")) {
				Object channelDistributorId = params.get("channelDistributorId");
				if (channelDistributorId == null) {
					hql.append(" and ").append(ename).append(".channelDistributor.id is null");
					params.remove("channelDistributorId");
				} else {
					hql.append(" and ").append(ename).append(".channelDistributor.id = :channelDistributorId ");
				}
			}
		}
		return hql;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			RequireGoodsOrder pb = vixntBaseService.findEntityById(RequireGoodsOrder.class, id);
			if (null != pb) {
				vixntBaseService.batchDeleteBySql("DELETE from drp_requiregoodsorderitem where REQUIREGOODSORDER_ID ='" + id + "'", null);
				vixntBaseService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 批量处理删除操作 */
	public String deleteByIds() {
		try {
			if (null != ids && !"".equals(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(new String(idStr));
					}
				}
				vixntBaseService.deleteById(RequireGoodsOrder.class, id);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 门店确认收货
	 */
	public void confirmReceipt() {
		try {
			if (StringUtils.isNotEmpty(requireGoodsOrderId)) {
				RequireGoodsOrder requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, requireGoodsOrderId);
				if (requireGoodsOrder != null) {
					requireGoodsOrder.setStatus("3");
					requireGoodsOrder = vixntBaseService.merge(requireGoodsOrder);
					PurchaseOrder purchaseOrder = vixntBaseService.findEntityByAttribute(PurchaseOrder.class, "requireGoodsOrder.id", requireGoodsOrder.getId());
					if (purchaseOrder != null) {
						purchaseOrder.setStatus("3");
						purchaseOrder = vixntBaseService.merge(purchaseOrder);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void confirmInbound() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				RequireGoodsOrder requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, id);
				if (requireGoodsOrder != null) {
					requireGoodsOrder.setStatus("4");
					requireGoodsOrder = vixntBaseService.merge(requireGoodsOrder);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String updatePriceAndAmount() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(id)) {
				RequireGoodsOrderItem requireGoodsOrderItem = vixntBaseService.findEntityById(RequireGoodsOrderItem.class, id);
				if (requireGoodsOrderItem != null) {
					if (avaquantity != null) {
						requireGoodsOrderItem.setActualAmount(avaquantity);
						requireGoodsOrderItem = vixntBaseService.merge(requireGoodsOrderItem);
					}
					Employee employee = getEmployee();
					if (employee != null && employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
						Map<String, Object> p = getParams();
						StoreItem storeItem = vixntBaseService.findObjectByHql("select storeItem from StoreItem storeItem where storeItem.code ='" + requireGoodsOrderItem.getItemCode() + "' and storeItem.channelDistributor.id ='" + employee.getChannelDistributor().getId() + "'", p);
						if (storeItem != null) {
							if (price != null && !"".equals(price)) {
								storeItem.setPrice(price);
							}
							if (vipPrice != null && !"".equals(vipPrice)) {
								storeItem.setVipPrice(vipPrice);
							}
							storeItem = vixntBaseService.merge(storeItem);
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
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdateSaleOrderItem() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(requireGoodsOrderItem.getId())) {
				isSave = false;
			}
			if (StringUtils.isNotEmpty(id)) {
				requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, id);
				if (requireGoodsOrder != null) {
					requireGoodsOrderItem.setRequireGoodsOrder(requireGoodsOrder);
				}
			}
			if (requireGoodsOrderItem.getAmount() != null && requireGoodsOrderItem.getPrice() != null) {
				requireGoodsOrderItem.setNetTotal(requireGoodsOrderItem.getAmount() * requireGoodsOrderItem.getPrice());
			}
			initEntityBaseController.initEntityBaseAttribute(requireGoodsOrderItem);
			vixntBaseService.merge(requireGoodsOrderItem);
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

	/** 子项价格汇总 */
	public void getStockRecordTotal() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				RequireGoodsOrder requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, id);
				if (null != requireGoodsOrder) {
					Double totalAmount = 0d;
					for (RequireGoodsOrderItem requireGoodsOrderItem : requireGoodsOrder.getSubRequireGoodsOrderItems()) {
						totalAmount += requireGoodsOrderItem.getPrice() * requireGoodsOrderItem.getAmount();
					}
					requireGoodsOrder.setAmount(totalAmount);
					requireGoodsOrder = vixntBaseService.merge(requireGoodsOrder);
					DecimalFormat df = new DecimalFormat(".##");
					String st = df.format(totalAmount);
					renderJson(st);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goChooseStoreItemList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			// 处理搜索条件
			String itemname = getDecodeRequestParameter("itemname");
			if (StringUtils.isNotEmpty(itemname)) {
				params.put("name", "%" + itemname + "%");
			}
			String productCategoryId = getDecodeRequestParameter("productCategoryId");
			if (StringUtils.isNotEmpty(productCategoryId)) {
				params.put("itemCatalogId", productCategoryId);
			}
			// 获取当前员工信息
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getChannelDistributor() != null) {
					params.put("channelDistributorsId", employee.getChannelDistributor().getId());
					pager = vixntBaseService.findStoreItemPager(pager, params);
				} else {
					ChannelDistributor channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
					if (channelDistributor != null) {
						params.put("channelDistributorsId", channelDistributor.getId());
						pager = vixntBaseService.findStoreItemPager(pager, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<ItemCatalog> listItemCatalog = new ArrayList<ItemCatalog>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id)) {
				listItemCatalog = vixntBaseService.findAllSubEntity(ItemCatalog.class, "parentItemCatalog.id", id, params);
			} else {
				listItemCatalog = vixntBaseService.findAllSubEntity(ItemCatalog.class, "parentItemCatalog.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			strSb = loadAllItemCatalog(strSb, listItemCatalog);
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private StringBuilder loadAllItemCatalog(StringBuilder strSb, List<ItemCatalog> listItemCatalog) throws Exception {
		for (int i = 0; i < listItemCatalog.size(); i++) {
			ItemCatalog ic = listItemCatalog.get(i);
			if (ic.getSubItemCatalogs().size() > 0) {
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getName());
				strSb.append("\",open:true,isParent:true,children:[");
				loadAllItemCatalog(strSb, new ArrayList<ItemCatalog>(ic.getSubItemCatalogs()));
				strSb.append("]}");
			} else {
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getName());
				strSb.append("\",open:false,isParent:false}");
			}
			if (i < listItemCatalog.size() - 1) {
				strSb.append(",");
			}
		}
		return strSb;
	}

	/** 处理删除操作 */
	public String deleteSaleOrderItemById() {
		try {
			RequireGoodsOrderItem saleOrderItem = vixntBaseService.findEntityById(RequireGoodsOrderItem.class, id);
			if (null != saleOrderItem) {
				vixntBaseService.deleteByEntity(saleOrderItem);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String goSearch() {
		return "goSearch";
	}

	public String goSaveOrUpdateSaleOrderItem() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				requireGoodsOrderItem = vixntBaseService.findEntityById(RequireGoodsOrderItem.class, id);
			} else {
				requireGoodsOrderItem = new RequireGoodsOrderItem();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateSaleOrderItem";
	}

	/** 获取列表数据 */
	public void goChannelDistributorList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			String selectName = getDecodeRequestParameter("selectName");
			if (StringUtils.isNotEmpty(selectName)) {
				params.put("name," + SearchCondition.ANYLIKE, selectName);
			}
			// 倒序排序
			pager = vixntBaseService.findPagerByHqlConditions(pager, ChannelDistributor.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goChooseChannelDistributor() {
		return "goChooseChannelDistributor";
	}

	public String getRequireGoodsOrderId() {
		return requireGoodsOrderId;
	}

	public void setRequireGoodsOrderId(String requireGoodsOrderId) {
		this.requireGoodsOrderId = requireGoodsOrderId;
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

	public String getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public RequireGoodsOrder getRequireGoodsOrder() {
		return requireGoodsOrder;
	}

	public void setRequireGoodsOrder(RequireGoodsOrder requireGoodsOrder) {
		this.requireGoodsOrder = requireGoodsOrder;
	}

	public RequireGoodsOrderItem getRequireGoodsOrderItem() {
		return requireGoodsOrderItem;
	}

	public void setRequireGoodsOrderItem(RequireGoodsOrderItem requireGoodsOrderItem) {
		this.requireGoodsOrderItem = requireGoodsOrderItem;
	}

	public StockRecords getStockRecords() {
		return stockRecords;
	}

	public void setStockRecords(StockRecords stockRecords) {
		this.stockRecords = stockRecords;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getVipPrice() {
		return vipPrice;
	}

	public void setVipPrice(Double vipPrice) {
		this.vipPrice = vipPrice;
	}

	public Double getAvaquantity() {
		return avaquantity;
	}

	public void setAvaquantity(Double avaquantity) {
		this.avaquantity = avaquantity;
	}

}
