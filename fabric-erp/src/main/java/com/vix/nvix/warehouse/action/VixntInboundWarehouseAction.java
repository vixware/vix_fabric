package com.vix.nvix.warehouse.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.IOUtils;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.billtype.BillType;
import com.vix.common.id.VixUUID;
import com.vix.common.job.entity.FlowApprovalOpinion;
import com.vix.common.security.util.DaysUtils;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CMNObjectModifyRecord;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.jdbc.dbstruct.Specification;
import com.vix.core.persistence.jdbc.dbstruct.SpecificationDetail;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.inbound.controller.InboundWarehouseController;
import com.vix.inventory.inbound.entity.StockRecordLines;
import com.vix.inventory.inbound.entity.StockRecords;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.inventory.inventorytype.entity.InventoryType;
import com.vix.inventory.option.entity.InventoryParameters;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.mdm.item.entity.StoreItem;
import com.vix.mdm.item.service.IItemService;
import com.vix.mdm.purchase.arrivalmgr.entity.PurchaseArrival;
import com.vix.mdm.purchase.arrivalmgr.entity.PurchaseArrivalItems;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.PurchaseOrderLineItem;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.template.ExcelTemplate;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

import flexjson.JSONSerializer;
import net.sf.cglib.beans.BeanCopier;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class VixntInboundWarehouseAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private InboundWarehouseController inboundWarehouseController;
	@Autowired
	private IItemService itemService;
	@Autowired
	private OrderProcessController orderProcessController;
	private String id;
	private String taskId;
	private String categoryId;
	private String stockRecordsId;
	private String supplierId;
	private String ids;
	private String biztype;
	private String itemCode;
	private StockRecords stockRecords;
	private List<InventoryType> inventoryTypeList;
	private List<SalesOrder> salesOrderList;
	private String salesOrderId;
	private List<StockRecords> stockRecordsList;
	private String purchaseArrivalId;
	/**
	 * 入库单明细
	 */
	private StockRecordLines stockRecordLines;
	private List<StockRecordLines> stockRecordLinesList;
	private List<CMNObjectModifyRecord> objectModifyRecordList;
	/**
	 * 采购订单
	 */
	private PurchaseOrder purchaseOrder;

	private List<PurchaseOrder> purchaseOrderList;
	/**
	 * 采购订单明细
	 */
	private PurchaseOrderLineItem purchaseOrderLineItem;
	private InventoryParameters inventoryParameters;
	private List<PurchaseOrderLineItem> purchaseOrderLineItemList;
	private List<Specification> specList;
	private Item item;
	private String str;
	private String purchaseOrderid;
	private String isDisassembly;
	/**
	 * 计量单位
	 */
	private List<MeasureUnit> measureUnitList;
	/**
	 * 有无批次管理
	 */
	private String isBatch;
	private String isAllowAudit;// 是否允许提交审批 1：是 0：否
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;

	private List<BaseEntity> baseEntityList;
	/**
	 * 根据条件查询入库单信息
	 */
	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 倒序排序
			Pager pager = getPager();
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String title = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(title)) {
				params.put("name," + SearchCondition.ANYLIKE, title.trim());
			}
			String code = getRequestParameter("code");
			if (StringUtils.isNotEmpty(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code.trim());
			}
			String createTime = getRequestParameter("createTime");
			if (StringUtils.isNotEmpty(createTime)) {
				params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(sdf.parse(createTime.trim())) + "!" + DaysUtils.getEndDay(sdf.parse(createTime.trim())));
			}
			String warehouseName = getDecodeRequestParameter("warehouseName");
			if (StringUtils.isNotEmpty(warehouseName)) {
				params.put("invWarehouse.name," + SearchCondition.ANYLIKE, warehouseName.trim());
			}
			String creator = getDecodeRequestParameter("creator");
			if (StringUtils.isNotEmpty(creator)) {
				params.put("creator," + SearchCondition.ANYLIKE, creator.trim());
			}
			String name = getDecodeRequestParameter("name");
			if (StringUtils.isNotEmpty(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name.trim());
			}
			params.put("flag," + SearchCondition.EQUAL, "1");
			params.put("type," + SearchCondition.EQUAL, "1");
			pager = inboundWarehouseController.doListStockrecordsPager(params, pager);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			isAllowAudit = isAllowAudit(BillType.INV_INBOUND);
			Map<String, Object> params = new HashMap<String, Object>();
			inventoryTypeList = inboundWarehouseController.doListInventoryTypeByEntity(params);
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_INBOUND));
				stockRecords.setName("入库单" + dateFormat.format(new Date()));
				Employee employee = getEmployee();
				if (employee != null) {
					stockRecords.setCreator(employee.getName());
					stockRecords.setWarehousePerson(employee.getName());
					stockRecords.setType("1");
				}
				InvWarehouse invWarehouse = getDefaultInvWarehouse();
				if (invWarehouse != null) {
					stockRecords.setInvWarehouse(invWarehouse);
				}
				stockRecords.setCreateTime(new Date());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	private InvWarehouse getDefaultInvWarehouse() throws Exception {
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("type", "1");
		p.put("isDefault", "0");
		StringBuilder itemhql = standingBookHqlProvider.findInvWarehouseHql(p);
		InvWarehouse invWarehouse = vixntBaseService.findObjectByHql(itemhql.toString(), p);
		if (invWarehouse != null) {
			return invWarehouse;
		}
		return null;
	}

	/**
	 * 跳转到用户查看页面
	 * 
	 * @return
	 */
	public String goShowInboundWarehouse() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowInboundWarehouse";
	}

	public void getFlowApprovalOpinion() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("sourceClassPk," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, FlowApprovalOpinion.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查看数据源
	 * 
	 * @return
	 */
	public String goSourceList() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
				if (stockRecords != null) {
					baseEntityList = new ArrayList<>();
					baseEntityList.add(stockRecords);
					if (StringUtils.isNotEmpty(stockRecords.getSourceClassName()) && StringUtils.isNotEmpty(stockRecords.getSourceBillCode())) {
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
	 * 保存入库
	 */
	public void update() {
		try {
			dealUpdateStockRecords(stockRecords);
			stockRecords = inboundWarehouseController.doUpdateInventoryCurrentStockByStockRecords(stockRecords);
			// 更改采购订单的状态
			if (StringUtils.isNotEmpty(purchaseOrderid) && !"undefined".equals(purchaseOrderid)) {
				updatePurchaseOrderStatus(purchaseOrderid);
			}
			if (stockRecords != null && stockRecords.getInvWarehouse() != null) {
				renderText(stockRecords.getCode() + "入库成功;入库仓库:" + stockRecords.getInvWarehouse().getName());
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("入库失败!");
		}
	}

	/**
	 * @throws Exception
	 */
	private void dealUpdateStockRecords(StockRecords stockRecords) throws Exception {
		// biztype 业务类型 1 ：采购入库单, 0 ：产成品入库单, 2：其他入库单,3:红字入库单
		stockRecords.setBiztype(biztype);
		// status 审核状态0：未提交 1：待审核 2：审核中3：审核通过
		stockRecords.setStatus("0");
		// flag 出入库标志 1：入库 ，2：出库
		stockRecords.setFlag("1");
		stockRecords.setIsInventory("0");
		initEntityBaseController.initEntityBaseAttribute(stockRecords);
		stockRecords = vixntBaseService.merge(stockRecords);
	}

	public void saveOrUpdate() {
		try {
			if (stockRecords != null && stockRecords.getInventoryType() != null && StringUtils.isNotEmpty(stockRecords.getInventoryType().getId()) && !"-1".equals(stockRecords.getInventoryType().getId())) {

			} else {
				stockRecords.setInventoryType(null);
			}

			if (StringUtils.isEmpty(stockRecords.getId())) {
				// biztype 业务类型 1 ：采购入库单, 0 ：产成品入库单, 2：其他入库单,3:红字入库单
				stockRecords.setBiztype(biztype);
				// status 审核状态0：未提交 1：待审核 2：审核中3：审核通过
				stockRecords.setStatus("0");
				// flag 出入库标志 1：入库 ，2：出库
				stockRecords.setFlag("1");
				stockRecords.setIsInventory("0");
				initEntityBaseController.initEntityBaseAttribute(stockRecords);
			}
			stockRecords = vixntBaseService.merge(stockRecords);
			// 增加操作日志
			vixOperateLog.saveOperateLog(stockRecords.getClass().getName(), stockRecords.getCode(), "", "新增入库单");
			renderText(stockRecords.getId() + ":" + SAVE_SUCCESS);
		} catch (Exception e) {
			renderText(SAVE_FAIL);
			e.printStackTrace();
		}
	}
	public void audit() {
		try {
			if (stockRecords != null && stockRecords.getInventoryType() != null && StringUtils.isNotEmpty(stockRecords.getInventoryType().getId()) && !"-1".equals(stockRecords.getInventoryType().getId())) {

			} else {
				stockRecords.setInventoryType(null);
			}
			// biztype 业务类型 1 ：采购入库单, 0 ：产成品入库单, 2：其他入库单,3:红字入库单
			stockRecords.setBiztype(biztype);
			// status 审核状态0：未提交 1：待审核 2：审核中3：审核通过
			stockRecords.setStatus("0");
			// flag 出入库标志 1：入库 ，2：出库
			stockRecords.setFlag("1");
			stockRecords.setIsInventory("0");
			initEntityBaseController.initEntityBaseAttribute(stockRecords);
			stockRecords = vixntBaseService.merge(stockRecords);
			// 增加操作日志
			if (true) {
				vixOperateLog.saveOperateLog(stockRecords.getClass().getName(), stockRecords.getCode(), "", "新增入库单");
			}
			// 更改采购订单的状态
			if (StringUtils.isNotEmpty(purchaseOrderid) && !"undefined".equals(purchaseOrderid)) {
				updatePurchaseOrderStatus(purchaseOrderid);
			}
			// 是否启用工作流
			if ("1".equals(isAllowAudit(BillType.INV_INBOUND))) {
				String response = dealStartAndSubmitByBillsCode(BillType.INV_INBOUND, stockRecords);
				if (StringUtils.isNotEmpty(response)) {
					JSONObject json = JSONObject.fromObject(response);
					if (json.has("status")) {
						if ("1".equals(json.getString("status"))) {
							stockRecords.setStatus("1");
							stockRecords = inboundWarehouseController.doUpdateInventoryCurrentStockByStockRecords(stockRecords);
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
	 * 更新采购订单状态
	 * 
	 * @throws Exception
	 */
	private void updatePurchaseOrderStatus(String purchaseOrderid) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id," + SearchCondition.IN, purchaseOrderid);
		List<PurchaseOrder> purchaseOrderList = inboundWarehouseController.doListPurchaseOrderList(params);
		if (purchaseOrderList != null && purchaseOrderList.size() > 0) {
			for (PurchaseOrder purchaseOrder : purchaseOrderList) {
				if (purchaseOrder != null) {
					// 修改采购订单的状态
					purchaseOrder.setStatus("已完成");
					purchaseOrder.setIsInventory("1");
					inboundWarehouseController.doSavePurchaseOrder(purchaseOrder);
				}
			}
		}
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			StockRecords pb = inboundWarehouseController.doListStockRecordsById(id);
			if (null != pb) {
				inboundWarehouseController.doDeleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/** 批量处理删除操作 */
	public void deleteByIds() {
		try {
			if (StringUtils.isNotEmpty(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				inboundWarehouseController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/** 跳转至新增产成本入库单 */
	public String goAddFinishedGoodsInbound() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			inventoryTypeList = inboundWarehouseController.doListInventoryTypeByEntity(params);
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_INBOUND));
				stockRecords.setIsTemp("1");
				stockRecords.setWarehousePerson(SecurityUtil.getCurrentUserName());
				stockRecords.setCreator(SecurityUtil.getCurrentUserName());
				stockRecords = inboundWarehouseController.doSaveOrUpdateStockRecords(stockRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAddFinishedGoodsInbound";
	}

	/**
	 * 跳转到查看产成品入库单页面
	 * 
	 * @return
	 */
	public String goShowFinishedGoodsInbound() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowFinishedGoodsInbound";
	}

	public String goUpdateShelf() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				stockRecordLines = inboundWarehouseController.doListStockRecordLinesById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goUpdateShelf";
	}

	/** 跳转至新增红字入库单 */
	public String goScarletLetterInbound() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			inventoryTypeList = inboundWarehouseController.doListInventoryTypeByEntity(params);
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_INBOUND));
				stockRecords.setIsTemp("1");
				stockRecords.setWarehousePerson(SecurityUtil.getCurrentUserName());
				stockRecords.setCreator(SecurityUtil.getCurrentUserName());
				stockRecords = inboundWarehouseController.doSaveOrUpdateStockRecords(stockRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goScarletLetterInbound";
	}

	// 入库调整单
	public String goAdjustmentInbound() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			inventoryTypeList = inboundWarehouseController.doListInventoryTypeByEntity(params);
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_INBOUND));
				stockRecords.setIsTemp("1");
				stockRecords.setWarehousePerson(SecurityUtil.getCurrentUserName());
				stockRecords.setCreator(SecurityUtil.getCurrentUserName());
				stockRecords = inboundWarehouseController.doSaveOrUpdateStockRecords(stockRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAdjustmentInbound";
	}

	// 入库调整单
	public String goShowAdjustmentInbound() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			inventoryTypeList = inboundWarehouseController.doListInventoryTypeByEntity(params);
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_INBOUND));
				stockRecords.setIsTemp("1");
				stockRecords.setWarehousePerson(SecurityUtil.getCurrentUserName());
				stockRecords.setCreator(SecurityUtil.getCurrentUserName());
				stockRecords = inboundWarehouseController.doSaveOrUpdateStockRecords(stockRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowAdjustmentInbound";
	}

	/** 跳转至新增红字入库单 */
	public String goShowScarletLetterInbound() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowScarletLetterInbound";
	}

	/** 跳转到增加入库单明细页面 */
	public String goAddWimStockRecordLines() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				stockRecordLines = inboundWarehouseController.doListStockRecordLinesById(id);
			} else {
				stockRecordLines = new StockRecordLines();
				stockRecordLines.setBatchcode(VixUUID.createCode(12));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddWimStockRecordLines";
	}

	/**
	 * 增加入库单明细
	 * 
	 * @return
	 */
	public void updateStockRecordLines() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(stockRecordLines.getId())) {
				isSave = false;
			}
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
				if (stockRecords != null) {
					stockRecordLines.setStockRecords(stockRecords);
					stockRecordLines.setFlag("1");
					stockRecordLines.setIsUpdateStore("1");
					if (stockRecordLines.getMeasureUnit() == null || StringUtils.isEmpty(stockRecordLines.getMeasureUnit().getId())) {
						stockRecordLines.setMeasureUnit(null);
					}
					if (stockRecordLines.getSupplier() == null || StringUtils.isEmpty(stockRecordLines.getSupplier().getId())) {
						stockRecordLines.setSupplier(null);
					}
					if (stockRecordLines.getInvShelf() == null || StringUtils.isEmpty(stockRecordLines.getInvShelf().getId())) {
						stockRecordLines.setInvShelf(null);
					}
					initEntityBaseController.initEntityBaseAttribute(stockRecordLines);
					stockRecordLines = inboundWarehouseController.doSaveStockRecordLines(stockRecordLines);
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
			params.put("type," + SearchCondition.EQUAL, "1");

			String name = getDecodeRequestParameter("selectName");
			if (StringUtils.isNotEmpty(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, InvWarehouse.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 */
	public void dealDisassembly() throws Exception {
		// 拆装后商品编码
		String disassemblyItemCode = getRequestParameter("disassemblyItemCode");
		// 拆装后单位
		String otherMeasureUnit = null;
		if (getRequestParameter("otherMeasureUnit") != null && !"".equals(getRequestParameter("otherMeasureUnit"))) {
			otherMeasureUnit = getRequestParameter("otherMeasureUnit");
		}
		// 拆装数量
		Long disassemblyNumber = 0L;
		if (getRequestParameter("disassemblyNumber") != null && !"".equals(getRequestParameter("disassemblyNumber"))) {
			disassemblyNumber = Long.parseLong(getRequestParameter("disassemblyNumber"));
		}
		StockRecordLines sr = new StockRecordLines();
		BeanCopier copier = BeanCopier.create(StockRecordLines.class, StockRecordLines.class, false);
		copier.copy(stockRecordLines, sr, null);
		sr.setItemcode(disassemblyItemCode);
		if (StringUtils.isNotEmpty(otherMeasureUnit)) {
			MeasureUnit measureUnit = itemService.findEntityById(MeasureUnit.class, otherMeasureUnit);
			if (measureUnit != null) {
				sr.setMeasureUnit(measureUnit);
			}
		}
		sr.setQuantity(0D);
		sr.setId(null);
		inboundWarehouseController.doSaveStockRecordLines(sr);
		stockRecordLines.setQuantity(stockRecordLines.getQuantity() - disassemblyNumber);
	}

	/**
	 * 条形码扫描入库
	 * 
	 * @return
	 */
	public void saveOrUpdateStockRecordLines() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
				if (stockRecords != null) {
					String itemcode = getRequestParameter("itemcode");
					Item item = inboundWarehouseController.doListItem(itemcode);
					if (item != null) {
						Map<String, Object> itemparams = new HashMap<String, Object>();
						itemparams.put("itemcode", item.getCode());
						itemparams.put("stockRecordsId", id);
						StringBuilder itemhql = standingBookHqlProvider.findStockRecordLinesByItemCode(itemparams);
						StockRecordLines invStockRecordLines = vixntBaseService.findObjectByHql(itemhql.toString(), itemparams);
						if (invStockRecordLines != null) {
							invStockRecordLines.setQuantity(invStockRecordLines.getQuantity() + 1);
							inboundWarehouseController.doSaveStockRecordLines(invStockRecordLines);
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
							inboundWarehouseController.doSaveStockRecordLines(invStockRecordLines);
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

	/** 处理删除操作 */
	public void deleteStockRecordLinesById() {
		try {
			StockRecordLines wimStockrecordlines = inboundWarehouseController.doListStockRecordLinesById(id);
			if (null != wimStockrecordlines) {
				inboundWarehouseController.deleteStockRecordLinesByEntity(wimStockrecordlines);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public String goAddOtherInbound() {
		try {
			isAllowAudit = isAllowAudit(BillType.INV_INBOUND);
			Map<String, Object> params = new HashMap<String, Object>();
			inventoryTypeList = inboundWarehouseController.doListInventoryTypeByEntity(params);
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_INBOUND));
				stockRecords.setIsTemp("1");
				stockRecords.setStatus("0");
				stockRecords.setWarehousePerson(SecurityUtil.getCurrentUserName());
				stockRecords.setCreator(SecurityUtil.getCurrentUserName());
				stockRecords = inboundWarehouseController.doSaveOrUpdateStockRecords(stockRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAddOtherInbound";
	}

	// 来源要货单
	public String goAddStoreEnquiryRequest() {
		try {
			isAllowAudit = isAllowAudit(BillType.INV_INBOUND);
			Map<String, Object> params = new HashMap<String, Object>();
			inventoryTypeList = inboundWarehouseController.doListInventoryTypeByEntity(params);
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_INBOUND));
				stockRecords.setIsTemp("1");
				stockRecords.setStatus("0");
				stockRecords.setWarehousePerson(SecurityUtil.getCurrentUserName());
				stockRecords.setCreator(SecurityUtil.getCurrentUserName());
				stockRecords = inboundWarehouseController.doSaveOrUpdateStockRecords(stockRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAddStoreEnquiryRequest";
	}

	public String goShowOtherInbound() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			inventoryTypeList = inboundWarehouseController.doListInventoryTypeByEntity(params);
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowOtherInbound";
	}

	public String goAudit() {
		try {
			Map<String, Object> p = new HashMap<String, Object>();
			inventoryTypeList = inboundWarehouseController.doListInventoryTypeByEntity(p);

			if (StringUtils.isNotEmpty(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAudit";
	}

	// 跳转到选择供应商页面
	public String goChooseSupplier() {
		return "goChooseSupplier";
	}

	// 跳转到选择采购订单页面
	public String goChoosePurchaseOrder() {
		return "goChoosePurchaseOrder";
	}

	// 跳转到选择仓库页面
	public String goChooseWarehouse() {
		return "goChooseWarehouse";
	}

	public void goPurchaseOrderList() {
		try {
			Map<String, Object> params = getParams();
			// 倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			params.put("isInventory," + SearchCondition.NOEQUAL, "1");
			pager = inboundWarehouseController.goPurchaseOrder(params, pager);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getPurchaseOrderItemList() {
		try {
			if (StringUtils.isNotEmpty(purchaseOrderid) && !"0".equals(purchaseOrderid)) {
				purchaseOrder = inboundWarehouseController.findPurchaseOrder(purchaseOrderid);
				Set<PurchaseOrderLineItem> purchaseOrderLineItems = purchaseOrder.getPurchaseOrderLineItems();
				if (purchaseOrderLineItems.size() > 0) {
					purchaseOrderLineItemList = new ArrayList<PurchaseOrderLineItem>(purchaseOrderLineItems);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "getPurchaseOrderItemList";
	}
	/**
	 * 将采购到货单转为入库单
	 */
	public void converterPurchaseArrivalToStockrecords() {
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

			Map<String, Object> p = new HashMap<String, Object>();
			p.put("type", "1");
			p.put("isDefault", "0");
			StringBuilder itemhql = standingBookHqlProvider.findInvWarehouseHql(p);
			InvWarehouse invWarehouse = vixntBaseService.findObjectByHql(itemhql.toString(), p);
			if (invWarehouse != null) {
				stockRecords.setInvWarehouse(invWarehouse);
			}
			stockRecords = vixntBaseService.merge(stockRecords);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id," + SearchCondition.IN, purchaseArrivalId);
			List<PurchaseArrival> purchaseArrivalList = vixntBaseService.findAllByConditions(PurchaseArrival.class, params);
			if (purchaseArrivalList != null && purchaseArrivalList.size() > 0) {
				for (PurchaseArrival purchaseArrival : purchaseArrivalList) {
					if (purchaseArrival != null && stockRecords != null) {
						convPurchaseArrivalToStockRecords(stockRecords, purchaseArrival);
					}
				}
			}
			if (null != stockRecords) {
				renderText(stockRecords.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void convPurchaseArrivalToStockRecords(StockRecords stockRecords, PurchaseArrival purchaseArrival) throws Exception {
		if (purchaseArrival != null && purchaseArrival.getPurchaseArrivalItems() != null) {
			Double totalAmount = 0d;
			for (PurchaseArrivalItems purchaseArrivalItems : purchaseArrival.getPurchaseArrivalItems()) {
				// 将采购订单明细转化成入库单明细
				if (purchaseArrivalItems != null) {
					StockRecordLines stockRecordLines = new StockRecordLines();
					stockRecordLines.setPoCode(purchaseArrival.getCode());
					stockRecordLines.setPurchaseOrderItemCode(purchaseArrivalItems.getCode());
					stockRecordLines.setItemcode(purchaseArrivalItems.getItemCode());
					stockRecordLines.setItemname(purchaseArrivalItems.getItemName());
					stockRecordLines.setSkuCode(purchaseArrivalItems.getSkuCode());
					stockRecordLines.setSpecification(purchaseArrivalItems.getSpecification());
					stockRecordLines.setSuppliercode(purchaseArrivalItems.getSupplier());
					stockRecordLines.setWarehouseCode(stockRecords.getWarehousecode());
					stockRecordLines.setUnit(purchaseArrivalItems.getUnit());
					stockRecordLines.setUnitcost(purchaseArrivalItems.getPrice());
					stockRecordLines.setQuantity(purchaseArrivalItems.getAmount());
					Double price = null;
					if (purchaseArrivalItems.getPrice() != null && purchaseArrivalItems.getAmount() != null) {
						price = purchaseArrivalItems.getPrice() * purchaseArrivalItems.getAmount();
						totalAmount += price;
						stockRecordLines.setPrice(price);
					}
					stockRecordLines.setStockRecords(stockRecords);
					stockRecordLines = vixntBaseService.merge(stockRecordLines);
				}
			}
			stockRecords.setType("1");
			stockRecords.setTotalAmount(totalAmount);
			stockRecords = vixntBaseService.merge(stockRecords);
		}
	}

	/**
	 * 将采购订单明细转化成采购入库单明细 同时更改采购订单的状态
	 * 
	 * @return
	 */
	public void converterPurchaseOrderToStockrecords() {
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
			stockRecords.setCreateTime(new Date());
			initEntityBaseController.initEntityBaseAttribute(stockRecords);

			Map<String, Object> p = new HashMap<String, Object>();
			p.put("type", "1");
			p.put("isDefault", "0");
			StringBuilder itemhql = standingBookHqlProvider.findInvWarehouseHql(p);
			InvWarehouse invWarehouse = vixntBaseService.findObjectByHql(itemhql.toString(), p);
			if (invWarehouse != null) {
				stockRecords.setInvWarehouse(invWarehouse);
			}

			stockRecords = vixntBaseService.merge(stockRecords);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id," + SearchCondition.IN, purchaseOrderid);
			List<PurchaseOrder> purchaseOrderList = vixntBaseService.findAllByConditions(PurchaseOrder.class, params);
			if (purchaseOrderList != null && purchaseOrderList.size() > 0) {
				for (PurchaseOrder purchaseOrder : purchaseOrderList) {
					if (purchaseOrder != null && stockRecords != null) {
						convPurchaseOrderToStockRecords(stockRecords, purchaseOrder);
					}
				}
			}
			if (null != stockRecords) {
				renderText(stockRecords.getId() + ":" + purchaseOrderid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 采购订单转化成入库单
	 * 
	 * @param stockRecords
	 * @param purchaseOrder
	 * @throws Exception
	 */
	public void convPurchaseOrderToStockRecords(StockRecords stockRecords, PurchaseOrder purchaseOrder) throws Exception {
		if (purchaseOrder != null) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("purchaseOrder.id," + SearchCondition.EQUAL, purchaseOrder.getId());
			List<PurchaseOrderLineItem> purchaseOrderLineItemList = this.vixntBaseService.findAllByConditions(PurchaseOrderLineItem.class, params);
			Double totalAmount = stockRecords.getTotalAmount();
			if (purchaseOrderLineItemList != null && purchaseOrderLineItemList.size() > 0) {
				for (PurchaseOrderLineItem purchaseOrderLineItem : purchaseOrderLineItemList) {
					// 将采购订单明细转化成入库单明细
					if (purchaseOrderLineItem != null) {
						StockRecordLines stockRecordLines = new StockRecordLines();
						stockRecordLines.setPoCode(purchaseOrder.getCode());
						stockRecordLines.setPurchaseOrderItemCode(purchaseOrderLineItem.getCode());
						stockRecordLines.setItemcode(purchaseOrderLineItem.getItemCode());
						stockRecordLines.setItemname(purchaseOrderLineItem.getItemName());
						stockRecordLines.setSkuCode(purchaseOrderLineItem.getSkuCode());
						stockRecordLines.setSpecification(purchaseOrderLineItem.getSpecification());
						stockRecordLines.setSuppliercode(purchaseOrderLineItem.getSupplier());
						stockRecordLines.setWarehouseCode(stockRecords.getWarehousecode());
						stockRecordLines.setUnit(purchaseOrderLineItem.getUnit());
						stockRecordLines.setUnitcost(purchaseOrderLineItem.getPrice());
						stockRecordLines.setQuantity(purchaseOrderLineItem.getAmount());
						Double price = null;
						if (purchaseOrderLineItem.getPrice() != null && purchaseOrderLineItem.getAmount() != null) {
							price = purchaseOrderLineItem.getPrice() * purchaseOrderLineItem.getAmount();
							totalAmount += price;
							stockRecordLines.setPrice(price);
						}
						stockRecordLines.setStockRecords(stockRecords);
						stockRecordLines.setIsUpdateStore("1");
						vixntBaseService.merge(stockRecordLines);
					}
				}
			}
			stockRecords.setType(purchaseOrder.getType());
			stockRecords.setTotalAmount(totalAmount);
			stockRecords.setSourceClassName(purchaseOrder.getClass().getName());
			stockRecords.setSourceBillCode(purchaseOrder.getCode());
			stockRecords = vixntBaseService.merge(stockRecords);
			purchaseOrder.setIsInventory("1");
			purchaseOrder = vixntBaseService.merge(purchaseOrder);
		}
	}

	// 跳转到添加物料页面
	public String goAddSaleOrderItem() {
		try {
			measureUnitList = inboundWarehouseController.doListMeasureUnitList();
			if (StringUtils.isNotEmpty(id)) {
				stockRecordLines = inboundWarehouseController.doListStockRecordLinesById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAddSaleOrderItem";
	}

	/** 子项价格汇总 */
	public void getStockRecordTotal() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				StockRecords stockRecords = inboundWarehouseController.doListStockRecordsById(id);
				if (null != stockRecords) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goChooseItem() {
		return "goChooseItem";
	}

	/** 获取列表数据 */
	@SuppressWarnings("unchecked")
	public void goChooseListContent() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			// 处理搜索条件
			String selectName = getDecodeRequestParameter("selectName");
			if (StringUtils.isNotEmpty(selectName)) {
				params.put("name," + SearchCondition.ANYLIKE, selectName);
			}
			if (StringUtils.isNotEmpty(categoryId)) {
				params.put("itemCatalog.id," + SearchCondition.EQUAL, categoryId);
			}
			if (StrUtils.isNotEmpty(supplierId)) {
				params.put("supplier.id," + SearchCondition.EQUAL, supplierId);
			}
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			if (isCheck) {
				params.put("isServiceItem," + SearchCondition.EQUAL, "Y");
			}
			params.put("isItem," + SearchCondition.NOEQUAL, "F");
			params.put("isDeleted," + SearchCondition.NOEQUAL, "1");
			pager = itemService.findPagerByHqlConditions(pager, Item.class, params);
			if (pager.getResultList().size() < 10) {
				int listSize = pager.getResultList().size();
				for (int i = 0; i < 10 - listSize; i++) {
					pager.getResultList().add(new Item());
				}
			}
			renderDataTable(pager);
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
			String searchName = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("name", "%" + searchName + "%");
			}
			// 获取当前员工信息
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getChannelDistributor() != null) {
					params.put("channelDistributorsId", employee.getChannelDistributor().getId());
					pager = vixntBaseService.findStoreItemPager(pager, params);
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goInventoryCurrentStockListContent() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			String itemname = getDecodeRequestParameter("name");
			if (StringUtils.isNotEmpty(itemname)) {
				params.put("itemname," + SearchCondition.ANYLIKE, itemname.trim());
			}
			pager = itemService.findPagerByHqlConditions(pager, InventoryCurrentStock.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String chooseSpecification() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				item = itemService.findEntityById(Item.class, id);
				if (null != item.getObjectExpand() && null != item.getObjectExpand().getSpecifications()) {
					Set<Specification> specSet = item.getObjectExpand().getSpecifications();
					if (specSet.size() > 0) {
						specList = new ArrayList<Specification>(specSet);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "chooseSpecification";
	}

	public void getItemEntityJson() {
		try {
			String itemIdSpecId = getRequestParameter("itemIdSpecId");
			if (StringUtils.isNotEmpty(itemIdSpecId)) {
				String[] isIds = itemIdSpecId.split(",");
				Item item = itemService.findEntityById(Item.class, isIds[0]);
				itemService.evict(item);
				StringBuilder spec = new StringBuilder();
				for (int j = 0; j < isIds.length; j++) {
					String s = isIds[j];
					if (j != 0 && null != s && !"".equals(s)) {
						SpecificationDetail sd = itemService.findEntityById(SpecificationDetail.class, s);
						if (null != sd) {
							spec.append(sd.getName());
							spec.append(" ");
						}
					}
				}
				item.setSpecification(spec.toString());
				Map<String, Object> params = new HashMap<String, Object>();
				InventoryCurrentStock inventoryCurrentStock = itemService.findObjectByHql("select inventoryCurrentStock from InventoryCurrentStock inventoryCurrentStock where 1=1 and itemcode ='" + item.getCode() + "' and specification ='" + spec.toString().trim() + "'", params);
				if (inventoryCurrentStock != null) {

				} else {
					inventoryCurrentStock = new InventoryCurrentStock();
					inventoryCurrentStock.setItemcode(item.getCode());
					inventoryCurrentStock.setItemname(item.getName());
					inventoryCurrentStock.setItemtype(item.getType());
					inventoryCurrentStock.setSerialcode(item.getSerialCode());
					inventoryCurrentStock.setProducedate(item.getProduceDate());
					inventoryCurrentStock.setQualityperiod(item.getQualityPeriod());
					inventoryCurrentStock.setSpecification(spec.toString());
					inventoryCurrentStock.setPrice(item.getPrice());
					inventoryCurrentStock.setIsBinding("2");
					inventoryCurrentStock.setIsTemp("");
				}

				String json = new JSONSerializer().exclude("class").serialize(inventoryCurrentStock);
				renderJson(json);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 新增入库单明细
	 * 
	 * @return
	 */
	public String goSaveOrUpdateStockRecordLines() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				stockRecordLines = itemService.findEntityById(StockRecordLines.class, id);
			} else {
				stockRecordLines = new StockRecordLines();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goSaveOrUpdateStockRecordLines";
	}

	public String goListStoreEnquiryRequest() {
		try {
			Map<String, Object> p = new HashMap<String, Object>();
			Employee employee = getEmployee();
			if (employee != null && employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
				p.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
				// 过滤临时数据
				p.put("isTemp," + SearchCondition.NOEQUAL, "1");
				salesOrderList = itemService.findAllByConditions(SalesOrder.class, p);
			}
			measureUnitList = inboundWarehouseController.doListMeasureUnitList();

			// 查询是否开启保质期管理
			inventoryParameters = inboundWarehouseController.doListInventoryParametersByAttribute("tenantId", SecurityUtil.getCurrentUserTenantId());
			if (StringUtils.isNotEmpty(id)) {
				stockRecordLines = itemService.findEntityById(StockRecordLines.class, id);
			} else {
				stockRecordLines = new StockRecordLines();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goListStoreEnquiryRequest";
	}

	public String goListItemList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("itemClass," + SearchCondition.IN, "goods,finishedgoods");
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("id");
				getPager().setOrderBy("desc");
			}
			getPager().setPageSize(10);
			if (StringUtils.isNotEmpty(categoryId) && !"0".equals(categoryId)) {
				Pager pager = itemService.findPagerByItemCatalogId(getPager(), categoryId, params);
				setPager(pager);
			} else {
				itemService.findPagerByHqlConditions(getPager(), Item.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String type = getRequestParameter("type");
		if (null != type && "choose".equals(type)) {
			return "goListContentChoose";
		}
		return "goListItemList";
	}

	public String goListStoreEnquiryRequestContent() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setPageSize(6);
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderField("id");
				pager.setOrderBy("desc");
			}
			if (StringUtils.isNotEmpty(salesOrderId)) {
				params.put("salesOrder.id," + SearchCondition.EQUAL, salesOrderId);
				pager = itemService.findPagerByHqlConditions(pager, SaleOrderItem.class, params);
			}
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListStoreEnquiryRequestContent";
	}

	public String goListSalesOrderContent() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setPageSize(6);
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderField("id");
				pager.setOrderBy("desc");
			}
			Employee employee = getEmployee();
			if (employee != null && employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
				params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
				// 过滤临时数据
				params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			}
			pager = itemService.findPagerByHqlConditions(pager, SalesOrder.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListSalesOrderContent";
	}

	public String goPrintStockRecordLines() {
		try {
			stockRecords = inboundWarehouseController.doListStockRecordsById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintStockRecordLines";
	}

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<ItemCatalog> listItemCatalog = new ArrayList<ItemCatalog>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id)) {
				listItemCatalog = itemService.findAllSubEntity(ItemCatalog.class, "parentItemCatalog.id", id, params);
			} else {
				listItemCatalog = itemService.findAllSubEntity(ItemCatalog.class, "parentItemCatalog.id", null, params);
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

	/**
	 * 查看采购订单源
	 * 
	 * @return
	 */
	public String goShowPurchaseOrder() {
		return "goShowPurchaseOrder";
	}

	/**
	 * 修改留痕查看
	 * 
	 * @return
	 */
	public String goModifiedLeaveMark() {
		return "goModifiedLeaveMark";
	}

	public String goListModifiedLeaveMarkContent() {
		try {
			Pager pager = billMarkProcessController.getCMNObjectModifyRecordPager();
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListModifiedLeaveMarkContent";
	}

	public String goListOrderBatchContent() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("stockRecords.id," + SearchCondition.EQUAL, id);
				purchaseOrderList = inboundWarehouseController.doListPurchaseOrderList(params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListOrderBatchContent";
	}

	/**
	 * 获取上一条数据
	 * 
	 * @return
	 */
	public String goBeforeOrAfterStockRecords() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
				if (stockRecords != null && stockRecords.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(stockRecords.getCreateTime()));
					// flag 1: 过滤入库单
					params.put("flag", "1");
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
						stockRecords = inboundWarehouseController.doListStockRecordsById(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowInboundWarehouse";
	}
	public String printInboundWarehouse() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = vixntBaseService.findEntityById(StockRecords.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "printInboundWarehouse";
	}
	public void goStockRecordLinesList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String searchItemName = getDecodeRequestParameter("searchItemName");
			if (StringUtils.isNotEmpty(searchItemName)) {
				params.put("itemname," + SearchCondition.ANYLIKE, searchItemName.trim());
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("stockRecords.id," + SearchCondition.EQUAL, id);
				pager = inboundWarehouseController.doListStockRecordLinesPager(params, pager);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 批量导入入库
	 */
	public void importFile() {
		FileInputStream fis = null;
		try {
			if (fileToUpload == null) {
			} else {
				try (InputStream xmlInputStream = ExcelTemplate.class.getResourceAsStream("stockRecordLines_template.xml")) {
					XLSReader reader = ReaderBuilder.buildFromXML(xmlInputStream);
					try (InputStream xlsInputStream = new FileInputStream(fileToUpload)) {
						List<StockRecordLines> stockRecordLinesList = new ArrayList<StockRecordLines>();
						Map<String, Object> beans = new HashMap<String, Object>();
						beans.put("stockRecordLinesList", stockRecordLinesList);
						reader.read(xlsInputStream, beans);
						if (stockRecordLinesList != null && stockRecordLinesList.size() > 0) {

							stockRecords = new StockRecords();
							stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_INBOUND));
							stockRecords.setName("入库单" + dateFormat.format(new Date()));
							stockRecords.setBiztype("2");
							// status 审核状态0：未提交 1：待审核 2：审核中3：审核通过
							stockRecords.setStatus("0");
							// flag 出入库标志 1：入库 ，2：出库
							stockRecords.setFlag("1");
							Employee employee = getEmployee();
							if (employee != null) {
								stockRecords.setCreator(employee.getName());
								stockRecords.setWarehousePerson(employee.getName());
								if (employee.getChannelDistributor() != null) {
									stockRecords.setChannelDistributor(employee.getChannelDistributor());
									Map<String, Object> p = new HashMap<String, Object>();
									p.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
									List<InvWarehouse> invWarehouseList = vixntBaseService.findAllByConditions(InvWarehouse.class, p);
									if (invWarehouseList != null && invWarehouseList.size() > 0) {
										for (InvWarehouse invWarehouse : invWarehouseList) {
											if ("0".equals(invWarehouse.getIsDefault())) {
												stockRecords.setInvWarehouse(invWarehouse);
											}
										}
									}
								}
							}
							stockRecords = vixntBaseService.merge(stockRecords);
							for (StockRecordLines stockRecordLines : stockRecordLinesList) {
								if (stockRecordLines != null) {
									// 查询商品信息
									Map<String, Object> itemparams = new HashMap<String, Object>();
									// 过滤临时数据
									itemparams.put("code", stockRecordLines.getItemcode());
									Employee emp = getEmployee();
									if (emp != null && emp.getChannelDistributor() != null) {
										itemparams.put("channelDistributorId", emp.getChannelDistributor().getId());
									}
									StringBuilder itemhql = standingBookHqlProvider.findStoreItemByItemCode(itemparams);
									StoreItem item = vixntBaseService.findObjectByHql(itemhql.toString(), itemparams);
									if (item != null) {
										Map<String, Object> p = new HashMap<String, Object>();
										p.put("itemcode", item.getCode());
										p.put("stockRecordsId", id);
										StringBuilder stockRecordLineshql = standingBookHqlProvider.findStockRecordLinesByItemCode(p);
										StockRecordLines invStockRecordLines = vixntBaseService.findObjectByHql(stockRecordLineshql.toString(), p);
										if (invStockRecordLines != null) {
											invStockRecordLines.setQuantity(invStockRecordLines.getQuantity() + 1);
											vixntBaseService.merge(invStockRecordLines);
										} else {
											invStockRecordLines = new StockRecordLines();
											if (stockRecords != null) {
												invStockRecordLines.setStockRecords(stockRecords);
											}
											if (item != null) {
												invStockRecordLines.setItemcode(item.getCode());
												invStockRecordLines.setItemname(item.getName());
												invStockRecordLines.setSpecification(item.getSpecification());
												invStockRecordLines.setUnit(item.getSaleUnit());
												if (StringUtils.isNotEmpty(item.getSupplierId())) {
													Supplier supplier = vixntBaseService.findEntityById(Supplier.class, item.getSupplierId());
													if (supplier != null) {
														invStockRecordLines.setSupplier(supplier);
													}
												}
												invStockRecordLines.setUnitcost(item.getPrice());
												invStockRecordLines.setSkuCode(item.getSkuCode());
												invStockRecordLines.setQuantity(stockRecordLines.getQuantity());
												invStockRecordLines.setFlag("1");
												initEntityBaseController.initEntityBaseAttribute(invStockRecordLines);
												invStockRecordLines = vixntBaseService.merge(invStockRecordLines);
											}
										}
									}
								}
							}
							Double totalAmount = 0d;
							for (StockRecordLines wimStockrecordlines : stockRecords.getSubStockRecordLines()) {
								totalAmount += wimStockrecordlines.getUnitcost() * wimStockrecordlines.getQuantity();
							}
							stockRecords.setTotalAmount(totalAmount);
							stockRecords = vixntBaseService.merge(stockRecords);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				IOUtils.closeQuietly(fis);
			}
		}
		renderHtml(stockRecords.getId());
	}

	/**
	 * 下载入库单模板
	 * 
	 * @return
	 */
	public String downloadTemplate() {
		try {
			String fileName = "stockrecords_template.xls";
			setOriFileName(fileName);
			InputStream xmlInputStream = ExcelTemplate.class.getResourceAsStream(fileName);
			setInputStream(xmlInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "downloadTemplate";
	}

	/**
	 * @return the id
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the purchaseOrderList
	 */
	public List<PurchaseOrder> getPurchaseOrderList() {
		return purchaseOrderList;
	}

	/**
	 * @param purchaseOrderList
	 *            the purchaseOrderList to set
	 */
	public void setPurchaseOrderList(List<PurchaseOrder> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
	}

	/**
	 * @return the objectModifyRecordList
	 */
	public List<CMNObjectModifyRecord> getObjectModifyRecordList() {
		return objectModifyRecordList;
	}

	/**
	 * @param objectModifyRecordList
	 *            the objectModifyRecordList to set
	 */
	public void setObjectModifyRecordList(List<CMNObjectModifyRecord> objectModifyRecordList) {
		this.objectModifyRecordList = objectModifyRecordList;
	}

	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}

	public List<SalesOrder> getSalesOrderList() {
		return salesOrderList;
	}

	public void setSalesOrderList(List<SalesOrder> salesOrderList) {
		this.salesOrderList = salesOrderList;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getBiztype() {
		return biztype;
	}

	public void setBiztype(String biztype) {
		this.biztype = biztype;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * @return the baseEntityList
	 */
	public List<BaseEntity> getBaseEntityList() {
		return baseEntityList;
	}

	/**
	 * @param baseEntityList
	 *            the baseEntityList to set
	 */
	public void setBaseEntityList(List<BaseEntity> baseEntityList) {
		this.baseEntityList = baseEntityList;
	}

	public StockRecords getStockRecords() {
		return stockRecords;
	}

	public void setStockRecords(StockRecords stockRecords) {
		this.stockRecords = stockRecords;
	}

	public List<InventoryType> getInventoryTypeList() {
		return inventoryTypeList;
	}

	public void setInventoryTypeList(List<InventoryType> inventoryTypeList) {
		this.inventoryTypeList = inventoryTypeList;
	}

	public List<StockRecords> getStockRecordsList() {
		return stockRecordsList;
	}

	public void setStockRecordsList(List<StockRecords> stockRecordsList) {
		this.stockRecordsList = stockRecordsList;
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

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public PurchaseOrderLineItem getPurchaseOrderLineItem() {
		return purchaseOrderLineItem;
	}

	public void setPurchaseOrderLineItem(PurchaseOrderLineItem purchaseOrderLineItem) {
		this.purchaseOrderLineItem = purchaseOrderLineItem;
	}

	public InventoryParameters getInventoryParameters() {
		return inventoryParameters;
	}

	public void setInventoryParameters(InventoryParameters inventoryParameters) {
		this.inventoryParameters = inventoryParameters;
	}

	public List<PurchaseOrderLineItem> getPurchaseOrderLineItemList() {
		return purchaseOrderLineItemList;
	}

	public void setPurchaseOrderLineItemList(List<PurchaseOrderLineItem> purchaseOrderLineItemList) {
		this.purchaseOrderLineItemList = purchaseOrderLineItemList;
	}

	/**
	 * @return the isAllowAudit
	 */
	public String getIsAllowAudit() {
		return isAllowAudit;
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

	/**
	 * @param isAllowAudit
	 *            the isAllowAudit to set
	 */
	public void setIsAllowAudit(String isAllowAudit) {
		this.isAllowAudit = isAllowAudit;
	}

	public String getIsDisassembly() {
		return isDisassembly;
	}

	public void setIsDisassembly(String isDisassembly) {
		this.isDisassembly = isDisassembly;
	}

	public List<Specification> getSpecList() {
		return specList;
	}

	public void setSpecList(List<Specification> specList) {
		this.specList = specList;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public String getPurchaseOrderid() {
		return purchaseOrderid;
	}

	public void setPurchaseOrderid(String purchaseOrderid) {
		this.purchaseOrderid = purchaseOrderid;
	}

	public List<MeasureUnit> getMeasureUnitList() {
		return measureUnitList;
	}

	public void setMeasureUnitList(List<MeasureUnit> measureUnitList) {
		this.measureUnitList = measureUnitList;
	}

	public String getIsBatch() {
		return isBatch;
	}

	public void setIsBatch(String isBatch) {
		this.isBatch = isBatch;
	}

	public String getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	/**
	 * @return the stockRecordsId
	 */
	public String getStockRecordsId() {
		return stockRecordsId;
	}

	/**
	 * @param stockRecordsId
	 *            the stockRecordsId to set
	 */
	public void setStockRecordsId(String stockRecordsId) {
		this.stockRecordsId = stockRecordsId;
	}

	public String getPurchaseArrivalId() {
		return purchaseArrivalId;
	}

	public void setPurchaseArrivalId(String purchaseArrivalId) {
		this.purchaseArrivalId = purchaseArrivalId;
	}

}
