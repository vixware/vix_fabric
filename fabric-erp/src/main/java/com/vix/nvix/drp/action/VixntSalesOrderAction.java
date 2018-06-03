package com.vix.nvix.drp.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.billtype.BillType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.dtbcenter.vehiclemanagement.entity.Vehicle;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.mdm.item.entity.StoreItem;
import com.vix.mdm.purchase.order.entity.NvixOrderBatch;
import com.vix.mdm.purchase.order.entity.NvixPickingListDetail;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.PurchaseOrderLineItem;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrder;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrderItem;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class VixntSalesOrderAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private String ids;
	private String treeType;
	private String parentId;
	private String salesOrderId;
	private String vehicleId;
	private String purchaseOrderId;
	private RequireGoodsOrder requireGoodsOrder;
	private List<RequireGoodsOrder> requireGoodsOrderList;
	private PurchaseOrder purchaseOrder;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private RequireGoodsOrderItem requireGoodsOrderItem;
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;
	private NvixOrderBatch nvixOrderBatch;

	// 门店要货单
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("deliveryTime");
			}
			params.put("status," + SearchCondition.EQUAL, "0");
			params.put("type,"+SearchCondition.EQUAL, "2");
			String selectname = getDecodeRequestParameter("selectname");
			if (StringUtils.isNotEmpty(selectname)) {
				params.put("name," + SearchCondition.ANYLIKE, selectname);
			}
			if (StringUtils.isNotEmpty(parentId) && StringUtils.isNotEmpty(treeType) && !"undefined".equals(parentId)) {
				if ("CH".equals(treeType)) {
					params.put("channelDistributor.id," + SearchCondition.EQUAL, parentId);
					pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 已汇总
	public void goOverSingleList() {
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
			pager = vixntBaseService.findPagerByHqlConditions(pager, NvixOrderBatch.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 待发货
	public void goToSellSalesOrderList() {
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
			pager = vixntBaseService.findPagerByHqlConditions(pager, NvixOrderBatch.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goRequireGoodsOrderList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("nvixOrderBatch.id," + SearchCondition.EQUAL, id);
			}
			params.put("type,"+SearchCondition.EQUAL, "2");
			pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goFinishOrderList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			params.put("status," + SearchCondition.MORETHAN, "3");
			params.put("type,"+SearchCondition.EQUAL, "2");
			String selectname = getDecodeRequestParameter("selectname");
			if (StringUtils.isNotEmpty(selectname)) {
				params.put("name," + SearchCondition.ANYLIKE, selectname);
			}
			if (StringUtils.isNotEmpty(parentId) && StringUtils.isNotEmpty(treeType) && !"undefined".equals(parentId)) {
				if ("CH".equals(treeType)) {
					params.put("channelDistributor.id," + SearchCondition.EQUAL, parentId);
					pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 配货完成
	public void prepareItemOver() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				NvixOrderBatch nvixOrderBatch = vixntBaseService.findEntityById(NvixOrderBatch.class, id);
				nvixOrderBatch.setStatus("1");
				nvixOrderBatch = vixntBaseService.merge(nvixOrderBatch);
			}
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

	public void goPurOrderSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String itemName = getDecodeRequestParameter("itemName");
			if (StringUtils.isNotEmpty(itemName)) {
				params.put("name," + SearchCondition.ANYLIKE, itemName);
			}
			params.put("type,"+SearchCondition.EQUAL, "2");
			pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 要货单汇总,生成采购订单
	 */
	public void collectRequireGoodsOrder() {
		try {
			Map<String, Object> params = getParams();
			params.put("status," + SearchCondition.EQUAL, "0");
			params.put("type,"+SearchCondition.EQUAL, "2");
			List<RequireGoodsOrder> requireGoodsOrderList = vixntBaseService.findAllByConditions(RequireGoodsOrder.class, params);
			Map<String, List<RequireGoodsOrderItem>> map = new HashMap<String, List<RequireGoodsOrderItem>>();
			if (requireGoodsOrderList != null && requireGoodsOrderList.size() > 0) {
				for (RequireGoodsOrder requireGoodsOrder : requireGoodsOrderList) {
					if (requireGoodsOrder != null) {
						if (requireGoodsOrder.getSubRequireGoodsOrderItems() != null && requireGoodsOrder.getSubRequireGoodsOrderItems().size() > 0) {
							for (RequireGoodsOrderItem requireGoodsOrderItem : requireGoodsOrder.getSubRequireGoodsOrderItems()) {
								if (requireGoodsOrderItem != null) {
									Map<String, Object> par = new HashMap<String, Object>();
									par.put("code", requireGoodsOrderItem.getItemCode());
									par.put("channelDistributorId", requireGoodsOrder.getChannelDistributor().getId());
									StringBuilder phql = standingBookHqlProvider.findStoreItemByItemCode(par);
									StoreItem storeItem = vixntBaseService.findObjectByHqlNoTenantId(phql.toString(), par);
									if (storeItem != null) {
										if (StringUtils.isNotEmpty(storeItem.getSupplierId())) {
											Supplier supplier = vixntBaseService.findEntityByAttributeNoTenantId(Supplier.class, "id", storeItem.getSupplierId());
											if (supplier != null) {
												if (map.containsKey(supplier.getId())) {
													map.get(supplier.getId()).add(requireGoodsOrderItem);
												} else {
													List<RequireGoodsOrderItem> l = new ArrayList<RequireGoodsOrderItem>();
													l.add(requireGoodsOrderItem);
													map.put(supplier.getId(), l);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			if (map != null && map.size() > 0) {
				for (Map.Entry<String, List<RequireGoodsOrderItem>> entry : map.entrySet()) {
					PurchaseOrder purchaseOrder = new PurchaseOrder();
					purchaseOrder.setCode(autoCreateCode.getBillNO(BillType.PUR_ORDER));
					purchaseOrder.setName("采购订单" + dateFormat.format(new Date()));
					purchaseOrder.setStatus("0");
					purchaseOrder.setType("0");
					Supplier supplier = vixntBaseService.findEntityById(Supplier.class, entry.getKey());
					if (supplier != null) {
						purchaseOrder.setSupplier(supplier);
					}
					Employee employee = getEmployee();
					if (employee != null) {
						purchaseOrder.setPurchasePerson(employee.getName());
						purchaseOrder.setPurchasePersonId(employee.getId());
					}
					purchaseOrder.setCreateTime(new Date());
					initEntityBaseController.initEntityBaseAttribute(purchaseOrder);
					purchaseOrder = vixntBaseService.merge(purchaseOrder);
					List<RequireGoodsOrderItem> requireGoodsOrderItemList = entry.getValue();
					for (RequireGoodsOrderItem requireGoodsOrderItem : requireGoodsOrderItemList) {
						if (requireGoodsOrderItem != null) {
							Map<String, Object> p = new HashMap<String, Object>();
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
				}
				for (RequireGoodsOrder requireGoodsOrder : requireGoodsOrderList) {
					requireGoodsOrder.setStatus("1");
					requireGoodsOrder = vixntBaseService.merge(requireGoodsOrder);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goShowRequireGoodsOrder() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				nvixOrderBatch = vixntBaseService.findEntityById(NvixOrderBatch.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowRequireGoodsOrder";
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

	/**
	 * 
	 */
	public void saveOrUpdate() {
		try {
			initEntityBaseController.initEntityBaseAttribute(requireGoodsOrder);
			requireGoodsOrder = vixntBaseService.merge(requireGoodsOrder);
			if (requireGoodsOrder != null) {
				renderText(requireGoodsOrder.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			RequireGoodsOrder pb = vixntBaseService.findEntityById(RequireGoodsOrder.class, id);
			if (null != pb) {
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
	 * 
	 * @return
	 */
	public void saveOrUpdateSaleOrderItem() {
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
	}

	// 发往车辆
	public void sendToVehicle() {
		try {
			if (StringUtils.isNotEmpty(salesOrderId)) {
				RequireGoodsOrder salesOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, salesOrderId);
				if (salesOrder != null) {
					if (StringUtils.isNotEmpty(vehicleId)) {
						Vehicle vehicle = vixntBaseService.findEntityById(Vehicle.class, vehicleId);
						if (vehicle != null) {
							if (salesOrder.getVehicle() == null) {
								salesOrder.setVehicle(vehicle);
								salesOrder.setStatus("2");
							}
							salesOrder = vixntBaseService.merge(salesOrder);
							vehicle.setStatus("2");
							vehicle = vixntBaseService.merge(vehicle);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理删除操作 */
	public void deleteSaleOrderItemById() {
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
				pager.setOrderField("createTime");
			}
			if (StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(treeType)) {
				if ("C".equals(treeType)) {
					params.put("organization.id," + SearchCondition.EQUAL, id);
					// 点击的树节点是公司
				} else if ("CH".equals(treeType)) {
					// 点击的树节点是分销体系结构
					params.put("parentChannelDistributor.id," + SearchCondition.EQUAL, id);
				}
			} else {
				// 获取当前员工信息
				Employee employee = getEmployee();
				if (employee != null && employee.getChannelDistributor() != null && employee.getChannelDistributor().getId() != null && !"".equals(employee.getChannelDistributor().getId())) {
					params.put("parentChannelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
				}
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

	public void goSupplierList() {
		try {
			Map<String, Object> params = getParams();
			params.put("status," + SearchCondition.EQUAL, "3");
			Pager pager = getPager();
			pager = vixntBaseService.findPagerByHqlConditions(pager, Supplier.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void confirmReceipt() {
		try {
			if (StringUtils.isNotEmpty(purchaseOrderId)) {
				PurchaseOrder purchaseOrder = vixntBaseService.findEntityById(PurchaseOrder.class, purchaseOrderId);
				if (purchaseOrder != null) {
					purchaseOrder.setStatus("3");
					purchaseOrder = vixntBaseService.merge(purchaseOrder);
				}
			}
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

	// 汇总门店要货单
	public void pickRequireGoodsOrder() {
		try {
			Map<String, Object> params = getParams();
			params.put("status," + SearchCondition.EQUAL, "0");
			params.put("type,"+SearchCondition.EQUAL, "2");
			List<RequireGoodsOrder> requireGoodsOrderList = vixntBaseService.findAllByConditions(RequireGoodsOrder.class, params);
			if (requireGoodsOrderList != null && requireGoodsOrderList.size() > 0) {
				dealRequireGoodsOrder(requireGoodsOrderList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param requireGoodsOrderList
	 * @throws Exception
	 */
	private void dealRequireGoodsOrder(List<RequireGoodsOrder> requireGoodsOrderList) throws Exception {
		// 创建批次单
		NvixOrderBatch nvixOrderBatch = vixntBaseService.findEntityByAttribute(NvixOrderBatch.class, "code", sdf.format(new Date()));
		if (nvixOrderBatch != null) {
		} else {
			nvixOrderBatch = new NvixOrderBatch();
			nvixOrderBatch.setCode(sdf.format(new Date()));
			nvixOrderBatch.setName("分拣单" + dateFormat.format(new Date()));
			nvixOrderBatch.setStatus("0");
			initEntityBaseController.initEntityBaseAttribute(nvixOrderBatch);
			nvixOrderBatch = vixntBaseService.merge(nvixOrderBatch);
		}
		if (requireGoodsOrderList != null && requireGoodsOrderList.size() > 0) {
			for (RequireGoodsOrder requireGoodsOrder : requireGoodsOrderList) {

				Map<String, Object> params = getParams();
				params.put("requireGoodsOrder.id," + SearchCondition.EQUAL, requireGoodsOrder.getId());
				List<RequireGoodsOrderItem> purchaseOrderLineItemList = vixntBaseService.findAllByConditions(RequireGoodsOrderItem.class, params);

				for (RequireGoodsOrderItem requireGoodsOrderItem : purchaseOrderLineItemList) {
					if (requireGoodsOrderItem != null) {
						Map<String, Object> p = new HashMap<String, Object>();
						NvixPickingListDetail nvixPickingListDetail = null;
						if (StringUtils.isNotEmpty(requireGoodsOrderItem.getItemCode())) {
							nvixPickingListDetail = vixntBaseService.findObjectByHql("select nvixPickingListDetail from NvixPickingListDetail nvixPickingListDetail where nvixPickingListDetail.itemCode ='" + requireGoodsOrderItem.getItemCode() + "' and nvixPickingListDetail.nvixOrderBatch.id ='" + nvixOrderBatch.getId() + "'", p);
						}
						if (nvixPickingListDetail != null) {
							nvixPickingListDetail.setAmount(nvixPickingListDetail.getAmount() + requireGoodsOrderItem.getAmount());
							nvixPickingListDetail = vixntBaseService.merge(nvixPickingListDetail);
						} else {
							nvixPickingListDetail = new NvixPickingListDetail();
							nvixPickingListDetail.setItemCode(requireGoodsOrderItem.getItemCode());
							nvixPickingListDetail.setItemName(requireGoodsOrderItem.getTitle());
							nvixPickingListDetail.setSpecification(requireGoodsOrderItem.getSpecification());
							nvixPickingListDetail.setUnit(requireGoodsOrderItem.getUnit());
							nvixPickingListDetail.setPrice(requireGoodsOrderItem.getPrice());
							nvixPickingListDetail.setAmount(requireGoodsOrderItem.getAmount());
							if (nvixOrderBatch != null) {
								nvixPickingListDetail.setNvixOrderBatch(nvixOrderBatch);
							}
							initEntityBaseController.initEntityBaseAttribute(nvixPickingListDetail);
							nvixPickingListDetail = vixntBaseService.merge(nvixPickingListDetail);
						}
					}
				}
				// 同步更改要货单状态
				requireGoodsOrder.setStatus("1");
				requireGoodsOrder.setNvixOrderBatch(nvixOrderBatch);
				requireGoodsOrder = vixntBaseService.merge(requireGoodsOrder);
			}
		}
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

	public String goChooseSupplier() {
		return "goChooseSupplier";
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
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

	public List<RequireGoodsOrder> getRequireGoodsOrderList() {
		return requireGoodsOrderList;
	}

	public void setRequireGoodsOrderList(List<RequireGoodsOrder> requireGoodsOrderList) {
		this.requireGoodsOrderList = requireGoodsOrderList;
	}

	public RequireGoodsOrderItem getRequireGoodsOrderItem() {
		return requireGoodsOrderItem;
	}

	public void setRequireGoodsOrderItem(RequireGoodsOrderItem requireGoodsOrderItem) {
		this.requireGoodsOrderItem = requireGoodsOrderItem;
	}

}
