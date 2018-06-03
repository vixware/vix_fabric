/**
 * 
 */
package com.vix.inventory.inbound.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.batchStandingBook.entity.InvMainBatch;
import com.vix.inventory.inbound.domain.InboundWarehouseDomain;
import com.vix.inventory.inbound.entity.StockRecordLines;
import com.vix.inventory.inbound.entity.StockRecords;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.inventory.inventorytype.entity.InventoryType;
import com.vix.inventory.option.entity.InventoryParameters;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.standingbook.entity.MasterInventoryCurrentStock;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemInventoryProperties;
import com.vix.mdm.item.entity.StoreItem;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.PurchaseOrderLineItem;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("inboundWarehouseController")
@Scope("prototype")
public class InboundWarehouseController {

	Logger logger = Logger.getLogger(InboundWarehouseController.class);

	@Autowired
	private InboundWarehouseDomain inboundWarehouseDomain;
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;
	@Autowired
	private InitEntityBaseController initEntityBaseController;

	public StockRecords doUpdateInventoryCurrentStockByStockRecords(StockRecords stockRecords) throws Exception {
		StockRecords stockRecords1 = null;
		if (stockRecords != null) {
			// 保存入库单
			stockRecords1 = inboundWarehouseDomain.findStockRecordsById(stockRecords.getId());
			if (stockRecords1 != null && StringUtils.isNotEmpty(stockRecords1.getId())) {
				// 获取入库单明细
				List<StockRecordLines> stockRecordLinesList = getStockRecordLinesList(stockRecords1);
				if (stockRecordLinesList != null && stockRecordLinesList.size() > 0) {
					for (StockRecordLines stockRecordLines1 : stockRecordLinesList) {
						if (stockRecordLines1 != null) {
							InventoryCurrentStock inventoryCurrentStock = getInventoryCurrentStock(stockRecords1, stockRecordLines1);
							if (inventoryCurrentStock != null) {
								// 处理库存中存在该商品的情况
								if (stockRecordLines1.getQuantity() != null) {
									if (inventoryCurrentStock.getQuantity() != null) {
										inventoryCurrentStock.setQuantity(inventoryCurrentStock.getQuantity() + stockRecordLines1.getQuantity());
									}
									if (inventoryCurrentStock.getAvaquantity() != null) {
										inventoryCurrentStock.setAvaquantity(inventoryCurrentStock.getAvaquantity() + stockRecordLines1.getQuantity());
									}
								}
								inboundWarehouseDomain.saveOrUpdateInventoryCurrentStock(inventoryCurrentStock);
							} else {
								dealInventoryCurrentStockUpdate(stockRecords1, stockRecordLines1);
							}
							stockRecordLines1.setIsUpdateStore("2");
							stockRecordLines1 = inboundWarehouseDomain.mergeStockRecordLines(stockRecordLines1);
						}
					}
				}
			}
			stockRecords.setIsInventory("1");
			stockRecords = inboundWarehouseDomain.merge(stockRecords);
		}
		return stockRecords1;
	}

	/**
	 * 入库
	 * 
	 * @param stockRecords
	 * @param stockRecordLines
	 * @throws Exception
	 */
	private void dealInventoryCurrentStockUpdate(StockRecords stockRecords, StockRecordLines stockRecordLines) throws Exception {
		InventoryCurrentStock inventory = new InventoryCurrentStock();
		inventory.setItemcode(stockRecordLines.getItemcode());
		inventory.setItemname(stockRecordLines.getItemname());
		// 合格品
		inventory.setIsQualfied(1);
		// 处理库存商品名称拼音化
		String py = ChnToPinYin.getPYString(stockRecordLines.getItemname());
		inventory.setChineseCharacter(py.toUpperCase());
		//
		inventory.setMassunitEndTime(stockRecordLines.getMassunitEndTime());
		inventory.setQuantity(stockRecordLines.getQuantity());
		inventory.setAvaquantity(stockRecordLines.getQuantity());
		if (stockRecordLines.getMeasureUnit() != null) {
			inventory.setMeasureUnit(stockRecordLines.getMeasureUnit());
		}
		inventory.setUnit(stockRecordLines.getUnit());
		if (stockRecords.getChannelDistributor() != null) {
			inventory.setChannelDistributor(stockRecords.getChannelDistributor());
		}

		// 批次号
		inventory.setBatchcode(stockRecordLines.getBatchcode());
		inventory.setIsTemp("");

		if (StringUtils.isNotEmpty(stockRecordLines.getItemcode())) {
			Item item = inboundWarehouseDomain.findItem(stockRecordLines.getItemcode());
			ItemInventoryProperties itemInventoryProperties = inboundWarehouseDomain.findItemInventoryPropertiesByItemId(item.getId());
			if (itemInventoryProperties != null) {
				if (itemInventoryProperties.getDefaultInvShelf() != null) {
					inventory.setInvShelf(itemInventoryProperties.getDefaultInvShelf());
					inventory.setInvShelfName(itemInventoryProperties.getDefaultInvShelf().getName());
				}
				if (itemInventoryProperties.getDefaultWarehouse() != null) {
					inventory.setInvWarehouse(itemInventoryProperties.getDefaultWarehouse());
					inventory.setWarehouse(itemInventoryProperties.getDefaultWarehouse().getName());
					inventory.setWarehousecode(itemInventoryProperties.getDefaultWarehouse().getCode());
				}
			}
		}
		if (stockRecords.getInvWarehouse() != null) {
			inventory.setInvWarehouse(stockRecords.getInvWarehouse());
			inventory.setWarehouse(stockRecords.getInvWarehouse().getName());
			inventory.setWarehousecode(stockRecords.getInvWarehouse().getCode());
		}
		if (stockRecordLines.getInvShelf() != null) {
			inventory.setInvShelf(stockRecordLines.getInvShelf());
			inventory.setInvShelfName(stockRecordLines.getInvShelf().getName());
		}
		if (stockRecordLines.getSupplier() != null) {
			inventory.setSupplier(stockRecordLines.getSupplier());
		}
		inventory.setSkuCode(stockRecordLines.getSkuCode());
		inventory.setSpecification(stockRecordLines.getSpecification());
		inventory.setPrice(stockRecordLines.getUnitcost());
		initEntityBaseController.initEntityBaseAttribute(inventory);
		inboundWarehouseDomain.saveOrUpdateInventoryCurrentStock(inventory);
	}

	/**
	 * 查看库存是否存在该商品
	 * 
	 * @param stockRecords
	 * @param stockRecordLines
	 * @return
	 * @throws Exception
	 */
	private InventoryCurrentStock getInventoryCurrentStock(StockRecords stockRecords, StockRecordLines stockRecordLines) throws Exception {
		// 需要通过仓库 及sku编码进行唯一存储
		Map<String, Object> params = new HashMap<String, Object>();
		// 过滤临时数据
		params.put("isQualfied", 1);
		// 商品编码
		if (StringUtils.isNotEmpty(stockRecordLines.getItemcode())) {
			params.put("itemcode", stockRecordLines.getItemcode());
		}
		// SKU编码
		if (StringUtils.isNotEmpty(stockRecordLines.getSkuCode())) {
			params.put("skuCode", stockRecordLines.getSkuCode());
		}
		// 仓库
		if (stockRecords.getInvWarehouse() != null && StringUtils.isNotEmpty(stockRecords.getInvWarehouse().getId())) {
			params.put("invWarehouseId", stockRecords.getInvWarehouse().getId());
		}
		// 查询是否开启保质期管理
		InventoryParameters inventoryParameters = inboundWarehouseDomain.findInventoryParametersByAttribute("tenantId", stockRecords.getTenantId());
		if (inventoryParameters != null) {
			// 如果开启保质期需处理
			if ("1".equals(inventoryParameters.getIsShelfLife())) {
				params.put("massunitEndTime", stockRecordLines.getMassunitEndTime());
			}
			// 批次号
			if ("1".equals(inventoryParameters.getIsBatch())) {
				params.put("batchcode", stockRecordLines.getBatchcode());
			}
		}
		StringBuilder hql = standingBookHqlProvider.findInventoryCurrentStockByItemcode(params);
		return inboundWarehouseDomain.findInventoryCurrentStockByHql(hql.toString(), params);
	}

	/**
	 * 获取入库单明细
	 * 
	 * @param stockRecords
	 * @return
	 * @throws Exception
	 */
	private List<StockRecordLines> getStockRecordLinesList(StockRecords stockRecords) throws Exception {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("stockRecords.id," + SearchCondition.EQUAL, stockRecords.getId());
		p.put("isUpdateStore," + SearchCondition.NOEQUAL, "2");
		List<StockRecordLines> stockRecordLinesList = inboundWarehouseDomain.findStockRecordLinesList(p);
		return stockRecordLinesList;
	}

	public StockRecords doSaveOrUpdateStockRecords(StockRecords stockRecords) throws Exception {
		StockRecords stockRecords1 = inboundWarehouseDomain.merge(stockRecords);
		// 查询是否开启保质期管理
		InventoryParameters inventoryParameters = inboundWarehouseDomain.findInventoryParametersByAttribute("tenantId", stockRecords1.getTenantId());
		if (stockRecords1 != null && StringUtils.isNotEmpty(stockRecords1.getId())) {

			Map<String, Object> p = new HashMap<String, Object>();
			p.put("stockRecords.id," + SearchCondition.EQUAL, stockRecords1.getId());
			List<StockRecordLines> stockRecordLinesList = inboundWarehouseDomain.findStockRecordLinesList(p);
			if (stockRecordLinesList != null && stockRecordLinesList.size() > 0) {
				for (StockRecordLines stockRecordLines1 : stockRecordLinesList) {
					if (stockRecordLines1 != null) {
						// 根据库存设置如果启用批次管理,要创建批次主文件 ,同时入库单明细上增加批次号
						if (inventoryParameters != null && "1".equals(inventoryParameters.getIsBatch())) {
							InvMainBatch invMainBatch = null;
							if (stockRecordLines1.getBatchcode() != null && !"".equals(stockRecordLines1.getBatchcode())) {
								invMainBatch = inboundWarehouseDomain.findInvMainBatch("batchCode", stockRecordLines1.getBatchcode());
								if (invMainBatch != null) {
								} else {
									invMainBatch = new InvMainBatch();
									/* 向批次主文件赋值 */
									invMainBatch.setItemName(stockRecordLines1.getItemname());
									invMainBatch.setBatchCode(stockRecordLines1.getBatchcode());
									invMainBatch.setIsTemp("");
									invMainBatch = inboundWarehouseDomain.saveOrUpdateInvMainBatch(invMainBatch);
								}
								/* 将入库单明细与批次主文件建立关系 */
								stockRecordLines1.setInvMainBatch(invMainBatch);
							}
							// 保存入库单明细
							stockRecordLines1 = inboundWarehouseDomain.mergeStockRecordLines(stockRecordLines1);
						}

						// 查询商品信息
						Map<String, Object> itemparams = new HashMap<String, Object>();
						// 过滤临时数据
						itemparams.put("itemcode", stockRecordLines1.getItemcode());
						if (stockRecordLines1.getInvWarehouse() != null && stockRecordLines1.getInvWarehouse().getId() != null && !"".equals(stockRecordLines1.getInvWarehouse().getId())) {
							itemparams.put("invWarehouseId", stockRecordLines1.getInvWarehouse().getId());
						} else if (stockRecords1.getInvWarehouse() != null && stockRecords1.getInvWarehouse().getId() != null && !"".equals(stockRecords1.getInvWarehouse().getId())) {
							itemparams.put("invWarehouseId", stockRecords1.getInvWarehouse().getId());
						}
						StringBuilder itemhql = standingBookHqlProvider.findInventoryCurrentStockByItemCode(itemparams);
						MasterInventoryCurrentStock masterInventoryCurrentStock = inboundWarehouseDomain.findMasterInventoryCurrentStockByHql(itemhql.toString(), itemparams);
						if (masterInventoryCurrentStock != null) {
							if (stockRecordLines1.getQuantity() != null) {
								if (masterInventoryCurrentStock.getQuantity() != null) {
									masterInventoryCurrentStock.setQuantity(masterInventoryCurrentStock.getQuantity() + stockRecordLines1.getQuantity());
								}
								if (masterInventoryCurrentStock.getAvaquantity() != null) {
									masterInventoryCurrentStock.setAvaquantity(masterInventoryCurrentStock.getAvaquantity() + stockRecordLines1.getQuantity());
								}
							}
							masterInventoryCurrentStock = inboundWarehouseDomain.saveOrUpdateMasterInventoryCurrentStock(masterInventoryCurrentStock);
						} else {
							masterInventoryCurrentStock = new MasterInventoryCurrentStock();
							masterInventoryCurrentStock.setItemcode(stockRecordLines1.getItemcode());
							masterInventoryCurrentStock.setItemname(stockRecordLines1.getItemname());
							masterInventoryCurrentStock.setInvWarehouse(stockRecords1.getInvWarehouse());
							masterInventoryCurrentStock.setPrice(stockRecordLines1.getUnitcost());
							masterInventoryCurrentStock.setQuantity(stockRecordLines1.getQuantity());
							masterInventoryCurrentStock.setAvaquantity(stockRecordLines1.getQuantity());
							initEntityBaseController.initEntityBaseAttribute(masterInventoryCurrentStock);
							masterInventoryCurrentStock = inboundWarehouseDomain.saveOrUpdateMasterInventoryCurrentStock(masterInventoryCurrentStock);
						}
						// 需要通过仓库 及sku编码进行唯一存储
						Map<String, Object> params = new HashMap<String, Object>();
						// 过滤临时数据
						params.put("isQualfied", 1);
						if (stockRecordLines1.getSkuCode() != null) {
							params.put("skuCode", stockRecordLines1.getSkuCode());
						} else {
							params.put("itemcode", stockRecordLines1.getItemcode());
						}
						// 如果开启保质期需处理
						if (inventoryParameters != null) {
							if ("1".equals(inventoryParameters.getIsShelfLife())) {
								params.put("massunitEndTime", stockRecordLines1.getMassunitEndTime());
							}
						}
						if (stockRecordLines1.getInvWarehouse() != null && stockRecordLines1.getInvWarehouse().getId() != null && !"".equals(stockRecordLines1.getInvWarehouse().getId())) {
							params.put("invWarehouseId", stockRecordLines1.getInvWarehouse().getId());
						} else if (stockRecords1.getInvWarehouse() != null && stockRecords1.getInvWarehouse().getId() != null) {
							params.put("invWarehouseId", stockRecords1.getInvWarehouse().getId());
						}
						StringBuilder hql = standingBookHqlProvider.findInventoryCurrentStockBySkuCode(params);
						InventoryCurrentStock inventoryCurrentStock = inboundWarehouseDomain.findInventoryCurrentStockByHql(hql.toString(), params);
						if (inventoryCurrentStock != null) {
							if (stockRecords1 != null && stockRecords1.getBiztype() != null && "3".equals(stockRecords1.getBiztype())) {
								if (inventoryCurrentStock.getQuantity() != null && stockRecordLines1.getQuantity() != null) {
									inventoryCurrentStock.setQuantity(inventoryCurrentStock.getQuantity() - stockRecordLines1.getQuantity());
									inventoryCurrentStock.setAvaquantity(inventoryCurrentStock.getAvaquantity() - stockRecordLines1.getQuantity());
								}
							} else {
								if (stockRecordLines1.getQuantity() != null) {
									if (inventoryCurrentStock.getQuantity() != null) {
										inventoryCurrentStock.setQuantity(inventoryCurrentStock.getQuantity() + stockRecordLines1.getQuantity());
									}
									if (inventoryCurrentStock.getAvaquantity() != null) {
										inventoryCurrentStock.setAvaquantity(inventoryCurrentStock.getAvaquantity() + stockRecordLines1.getQuantity());
									}
								}
							}
							inboundWarehouseDomain.saveOrUpdateInventoryCurrentStock(inventoryCurrentStock);
						} else {
							InventoryCurrentStock inventory = new InventoryCurrentStock();
							inventory.setItemcode(stockRecordLines1.getItemcode());
							inventory.setItemname(stockRecordLines1.getItemname());
							// 合格品
							inventory.setIsQualfied(1);
							// 处理库存商品名称拼音化
							String py = ChnToPinYin.getPYString(stockRecordLines1.getItemname());
							inventory.setChineseCharacter(py.toUpperCase());
							//
							inventory.setMassunitEndTime(stockRecordLines1.getMassunitEndTime());
							inventory.setQuantity(stockRecordLines1.getQuantity());
							inventory.setAvaquantity(stockRecordLines1.getQuantity());
							inventory.setUnit(stockRecordLines1.getUnit());
							ChannelDistributor channelDistributor = null;
							if (stockRecords1.getChannelDistributor() != null) {
								channelDistributor = stockRecords1.getChannelDistributor();
							} else {
								if (SecurityUtil.getCurrentEmpId() != null) {
									Employee employee = inboundWarehouseDomain.findEmployeeById(SecurityUtil.getCurrentEmpId());
									if (employee != null && employee.getChannelDistributor() != null) {
										// 如果登录的员工属于经销商或门店
										channelDistributor = employee.getChannelDistributor();
									}
								}
							}
							if (channelDistributor != null) {
								inventory.setChannelDistributor(channelDistributor);
							}
							if (stockRecords1.getInvWarehouse() != null) {
								inventory.setInvWarehouse(stockRecords1.getInvWarehouse());
								inventory.setWarehouse(stockRecords1.getInvWarehouse().getName());
								inventory.setWarehousecode(stockRecords1.getInvWarehouse().getCode());
							}
							StoreItem t = null;
							Map<String, Object> p1 = new HashMap<String, Object>();
							p1.put("code", stockRecordLines1.getItemcode());
							p1.put("channelDistributorId", channelDistributor.getId());
							StringBuilder hql1 = standingBookHqlProvider.findStoreItemByItemCode(p1);
							t = inboundWarehouseDomain.findStoreItemByHql(hql1.toString(), p1);
							if (t != null) {
								inventory.setStoreItem(t);
							}
							// 批次号
							inventory.setBatchcode(stockRecordLines1.getBatchcode());
							inventory.setIsTemp("");
							if (stockRecordLines1.getInvShelf() != null) {
								inventory.setInvShelfName(stockRecordLines1.getInvShelf().getName());
								inventory.setInvShelf(stockRecordLines1.getInvShelf());
							}
							if (stockRecordLines1.getSupplier() != null) {
								inventory.setSupplier(stockRecordLines1.getSupplier());
							}
							inventory.setSkuCode(stockRecordLines1.getSkuCode());
							inventory.setSpecification(stockRecordLines1.getSpecification());
							inventory.setPrice(stockRecordLines1.getUnitcost());
							inventory.setMasterInventoryCurrentStock(masterInventoryCurrentStock);
							initEntityBaseController.initEntityBaseAttribute(inventory);
							inboundWarehouseDomain.saveOrUpdateInventoryCurrentStock(inventory);
						}
					}
				}
			}
		}

		return stockRecords1;
	}

	/**
	 * 保存入库单明细
	 * 
	 * @param stockRecordLines
	 * @return
	 * @throws Exception
	 */
	public StockRecordLines doSaveStockRecordLines(StockRecordLines stockRecordLines) throws Exception {
		// 计算金额 ：金额 = 单价 * 数量
		Double price = null;
		if (stockRecordLines.getQuantity() != null && stockRecordLines.getUnitcost() != null) {
			price = stockRecordLines.getQuantity() * stockRecordLines.getUnitcost();
		}
		if (price != null) {
			stockRecordLines.setPrice(price);
		} else {
			stockRecordLines.setPrice(0d);
		}
		return inboundWarehouseDomain.mergeStockRecordLines(stockRecordLines);

	}

	public PurchaseOrder doSavePurchaseOrder(PurchaseOrder purchaseOrder) throws Exception {
		return inboundWarehouseDomain.saveOrUpdatePurchaseOrder(purchaseOrder);
	}

	public InventoryParameters doListInventoryParametersByAttribute(String attribute, String value) throws Exception {
		InventoryParameters inventoryParameters = inboundWarehouseDomain.findInventoryParametersByAttribute(attribute, value);
		return inventoryParameters;
	}

	public void convPurchaseOrderToStockrecords(StockRecords stockRecords, PurchaseOrder purchaseOrder) throws Exception {
		// 采购订单明细
		Set<PurchaseOrderLineItem> purchaseOrderLineItemSet = new HashSet<PurchaseOrderLineItem>();
		// 入库单明细
		List<StockRecordLines> stockrecordlinesList = new ArrayList<StockRecordLines>();
		if (purchaseOrder != null) {
			purchaseOrderLineItemSet = purchaseOrder.getPurchaseOrderLineItems();
			for (PurchaseOrderLineItem purchaseOrderLineItem : purchaseOrderLineItemSet) {
				// 将采购订单明细转化成入库单明细
				if (purchaseOrderLineItem != null) {
					StockRecordLines stockrecordlines = new StockRecordLines();
					stockrecordlines.setPoCode(purchaseOrder.getCode());
					stockrecordlines.setPurchaseOrderItemCode(purchaseOrderLineItem.getCode());
					stockrecordlines.setItemcode(purchaseOrderLineItem.getItemCode());
					stockrecordlines.setItemname(purchaseOrderLineItem.getItemName());
					if (stockRecords.getInvWarehouse() != null) {
						stockrecordlines.setInvWarehouse(stockRecords.getInvWarehouse());
					}
					stockrecordlines.setSkuCode(purchaseOrderLineItem.getSkuCode());
					stockrecordlines.setSpecification(purchaseOrderLineItem.getSpecification());
					stockrecordlines.setSuppliercode(purchaseOrderLineItem.getSupplier());
					stockrecordlines.setWarehouseCode(stockRecords.getWarehousecode());
					stockrecordlines.setUnit(purchaseOrderLineItem.getUnit());
					stockrecordlines.setUnitcost(purchaseOrderLineItem.getPrice());
					stockrecordlines.setQuantity(purchaseOrderLineItem.getAmount());
					/*
					 * Double price = null; if (purchaseOrderLineItem.getPrice()
					 * != null && !"".equals(purchaseOrderLineItem.getPrice())
					 * && purchaseOrderLineItem.getAmount() != null &&
					 * !"".equals(purchaseOrderLineItem.getAmount())) { price =
					 * purchaseOrderLineItem.getPrice() *
					 * purchaseOrderLineItem.getAmount(); }
					 */
					// stockrecordlines.setPrice(purchaseOrderLineItem.getNetPrice().orElse(0D));
					stockrecordlines.setPrice(purchaseOrderLineItem.getNetPrice());
					/*
					 * if (purchaseOrderLineItem.getNetPrice() != null &&
					 * !"".equals(purchaseOrderLineItem.getNetPrice())) {
					 * stockrecordlines.setPrice(purchaseOrderLineItem.
					 * getNetPrice()); } else {
					 * stockrecordlines.setPrice(price); }
					 */
					stockrecordlinesList.add(stockrecordlines);
				}
			}
		}
		if (stockrecordlinesList != null && stockrecordlinesList.size() > 0) {
			for (StockRecordLines stockrecordlines : stockrecordlinesList) {
				if (stockrecordlines != null) {
					stockrecordlines.setStockRecords(stockRecords);
					inboundWarehouseDomain.mergeStockRecordLines(stockrecordlines);
				}
			}
		}
		// 建立入库单与采购订单的关系
		purchaseOrder.setStockRecords(stockRecords);
		inboundWarehouseDomain.saveOrUpdatePurchaseOrder(purchaseOrder);

	}

	/**
	 * 
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doListStockrecordsPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		p = inboundWarehouseDomain.findStockRecordsPager(params, pager);

		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return p;
	}

	public Pager doListStockRecordLinesPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		p = inboundWarehouseDomain.findStockRecordLinesPager(params, pager);

		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return p;
	}

	/**
	 * 
	 * 
	 * @param stockRecords
	 * @throws Exception
	 */
	public void doDeleteByEntity(StockRecords stockRecords) throws Exception {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3. 执行删除操作
		inboundWarehouseDomain.deleteStockRecordsByEntity(stockRecords);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

	}

	/**
	 * 
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void doDeleteByIds(List<String> ids) throws Exception {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3. 执行删除操作
		inboundWarehouseDomain.deleteByIds(ids);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

	}

	/**
	 * 
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public StockRecords doListStockRecordsById(String id) throws Exception {
		StockRecords stockRecords = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		stockRecords = inboundWarehouseDomain.findStockRecordsById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return stockRecords;
	}

	public InventoryCurrentStock doListInventoryCurrentStock(String itemcode) throws Exception {
		InventoryCurrentStock inventoryCurrentStock = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		inventoryCurrentStock = inboundWarehouseDomain.findInventoryCurrentStock(itemcode);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return inventoryCurrentStock;
	}

	public Item doListItem(String itemcode) throws Exception {
		Item item = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		item = inboundWarehouseDomain.findItem(itemcode);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return item;
	}

	public PurchaseOrder findPurchaseOrder(String id) throws Exception {
		PurchaseOrder purchaseOrder = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		purchaseOrder = inboundWarehouseDomain.findPurchaseOrderById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return purchaseOrder;
	}

	public StockRecordLines doListStockRecordLinesById(String id) throws Exception {
		StockRecordLines stockRecordLines = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		stockRecordLines = inboundWarehouseDomain.findStockRecordLinesById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return stockRecordLines;
	}

	public Employee doListEmployeeById(String id) throws Exception {
		Employee employee = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		employee = inboundWarehouseDomain.findEmployeeById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return employee;
	}

	public PurchaseOrder doListPurchaseOrderById(String id) throws Exception {
		PurchaseOrder purchaseOrder = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		purchaseOrder = inboundWarehouseDomain.findPurchaseOrderById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		executeLogger(true, "查询入库单明细信息成功！");

		return purchaseOrder;
	}

	public List<InventoryType> doListInventoryTypeByEntity(Map<String, Object> params) throws Exception {
		List<InventoryType> inventoryTypeList = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		inventoryTypeList = inboundWarehouseDomain.findInventoryTypeList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		executeLogger(true, "查询入库单明细信息成功！");

		return inventoryTypeList;
	}

	public void deleteStockRecordLinesByEntity(StockRecordLines stockRecordLines) throws Exception {
		inboundWarehouseDomain.deleteStockRecordLines(stockRecordLines);
	}

	/** 获取列表数据 */
	public Pager goSupplierPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = inboundWarehouseDomain.findSupplierPager(params, pager);
		return p;
	}

	/** 获取采购订单列表数据 */
	public Pager goPurchaseOrder(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = inboundWarehouseDomain.findPurchaseOrderPager(params, pager);
		return p;
	}

	/**
	 * 
	 * 
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public List<StockRecords> doListStockRecordsList(Map<String, Object> params) throws Exception {
		List<StockRecords> stockRecords = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		stockRecords = inboundWarehouseDomain.findStockRecordsList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return stockRecords;
	}

	public List<StockRecordLines> doListStockRecordLinesList(Map<String, Object> params) throws Exception {
		List<StockRecordLines> stockRecordLinesList = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		stockRecordLinesList = inboundWarehouseDomain.findStockRecordLinesList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return stockRecordLinesList;
	}

	public List<PurchaseOrder> doListPurchaseOrderList(Map<String, Object> params) throws Exception {
		List<PurchaseOrder> purchaseOrderList = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		purchaseOrderList = inboundWarehouseDomain.findPurchaseOrderList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return purchaseOrderList;
	}

	public List<MeasureUnit> doListMeasureUnitList() throws Exception {
		List<MeasureUnit> measureUnitList = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		measureUnitList = inboundWarehouseDomain.findMeasureUnitList();
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return measureUnitList;
	}

	/**
	 * beforeEventTrigger 用于触发在执行业务逻辑前的事件产生, 将产生一个业务对象执行动作的before事件.
	 * 使用Esper引擎处理,事件体执行过程为异步.afterEventTrigger采用相同的处理方式.
	 * 
	 * @param eventName
	 *            根据业务对象来定义
	 */
	private void beforeEventTrigger(String eventName) {
		// to do something
	}

	/**
	 * afterEventTrigger 用于触发在执行业务逻辑后的事件产生, 将产生一个业务对象执行动作的after事件.
	 * 
	 * @param eventName
	 *            根据业务对象来定义
	 */
	private void afterEventTrigger(String eventName) {
		// to do something
	}

	/**
	 * 输入信息到日志文件中,可以考虑是用Helper类提供服务.
	 * 
	 * @param message
	 */
	public void executeLogger(boolean isShow, String... message) {
		if (isShow) {
			logger.info(message);
		}
	}

	/**
	 * 异常错误日志方便程序员调试
	 * 
	 * @param message
	 */
	public void executeErrorLogger(String... errorMessage) {
	}

	/**
	 * bizFlowExecute 需要对流程进行操作时调用
	 * 
	 * @param flowName
	 * @param flowParameter
	 */
	protected void bizFlowExecute(String flowName, Map<String, Object> flowParameter) {
		// to do something
	}

	/**
	 * bizRuleExecute 的定义还没有考虑清楚,涉及业务时重构.
	 * 
	 * @param ruleName
	 * @param flowParameter
	 */
	protected void bizRuleExecute(String ruleName, Map<String, Object> flowParameter) {
		// to do something

	}

	/**
	* 
	*/
	public void doProcessEvent() {
		// to do something

	}

	/**
	 * 清除缓存对象
	 * 
	 * @param obj
	 */
	public void evict(Object obj) {
		inboundWarehouseDomain.evict(obj);
	}

	/**
	 * 
	 */
	public void doExecute() {
		// to do something
	}

	/**
	 * 
	 * @return
	 */
	public Object getIndustryOrderAction() {
		return null;
	}

	/**
	 * 
	 */
	public void doPrint() {
		// to do something
	}

	/**
	 * 
	 */
	public void doDrools() {
		// to do something
	}
}
