package com.vix.nvix.inventory.action;

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
import com.vix.common.share.entity.CMNObjectModifyRecord;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.jdbc.dbstruct.Specification;
import com.vix.core.persistence.jdbc.dbstruct.SpecificationDetail;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
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
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.template.ExcelTemplate;

import flexjson.JSONSerializer;
import net.sf.cglib.beans.BeanCopier;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class VixntInitInventoryAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private InboundWarehouseController inboundWarehouseController;
	@Autowired
	private IItemService itemService;
	private String taskId;
	private String stockRecordsId;
	private String categoryId;
	private String supplierId;
	private String ids;
	private String itemCode;
	private StockRecords stockRecords;
	private List<InventoryType> inventoryTypeList;
	/**
	 * 入库单明细
	 */
	private StockRecordLines stockRecordLines;
	private List<StockRecordLines> stockRecordLinesList;
	private List<CMNObjectModifyRecord> objectModifyRecordList;

	private InventoryParameters inventoryParameters;
	private List<Specification> specList;
	private Item item;
	private String str;
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
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;

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
			params.put("flag," + SearchCondition.EQUAL, "1");
			params.put("biztype," + SearchCondition.EQUAL, "4");
			String title = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(title)) {
				params.put("name," + SearchCondition.ANYLIKE, title.trim());
			}
			pager = inboundWarehouseController.doListStockrecordsPager(params, pager);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			inventoryTypeList = inboundWarehouseController.doListInventoryTypeByEntity(params);
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_INBOUND));
				stockRecords.setName("期初入库单" + dateFormat.format(new Date()));
				stockRecords.setIsInventory("0");
				Employee employee = getEmployee();
				if (employee != null) {
					stockRecords.setCreator(employee.getName());
					stockRecords.setWarehousePerson(employee.getName());
					stockRecords.setType("1");
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

	/** 处理修改操作 */
	public void update() {
		try {
			stockRecords = inboundWarehouseController.doUpdateInventoryCurrentStockByStockRecords(stockRecords);
			stockRecords.setIsInventory("1");
			stockRecords = vixntBaseService.merge(stockRecords);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveOrUpdate() {
		try {
			if (stockRecords != null && stockRecords.getInventoryType() != null && StringUtils.isNotEmpty(stockRecords.getInventoryType().getId()) && !"-1".equals(stockRecords.getInventoryType().getId())) {

			} else {
				stockRecords.setInventoryType(null);
			}
			// biztype 业务类型 1 ：采购入库单, 0 ：产成品入库单, 2：其他入库单,3:红字入库单
			stockRecords.setBiztype("4");
			// status 审核状态0：未提交 1：待审核 2：审核中3：审核通过
			stockRecords.setStatus("0");
			// flag 出入库标志 1：入库 ，2：出库
			stockRecords.setFlag("1");
			initEntityBaseController.initEntityBaseAttribute(stockRecords);
			stockRecords = this.vixntBaseService.merge(stockRecords);
			// 增加操作日志
			if (true) {
				vixOperateLog.saveOperateLog(stockRecords.getClass().getName(), stockRecords.getCode(), "", "新增入库单");
			}
			String tag = getRequestParameter("tag");
			if (tag != null && "1".equals(tag)) {
				String response = dealStartAndSubmitByBillsCode(BillType.INV_INBOUND, stockRecords);
				JSONObject json = JSONObject.fromObject(response);
				if (json.has("status")) {
					if ("1".equals(json.getString("status"))) {
						stockRecords.setStatus("1");
						stockRecords = inboundWarehouseController.doSaveOrUpdateStockRecords(stockRecords);
					}
				}
			}
			renderText(stockRecords.getId() + ":" + SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
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
			if (StringUtils.isNotEmpty(stockRecordsId)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(stockRecordsId);
				if (stockRecords != null) {
					stockRecordLines.setStockRecords(stockRecords);
					stockRecordLines.setFlag("1");
					stockRecordLines.setIsUpdateStore("1");// 设置是否同步库存标志
					if (stockRecordLines.getMeasureUnit() == null || StringUtils.isEmpty(stockRecordLines.getMeasureUnit().getId())) {
						stockRecordLines.setMeasureUnit(null);
					}
					if (stockRecordLines.getSupplier() == null || StringUtils.isEmpty(stockRecordLines.getSupplier().getId())) {
						stockRecordLines.setSupplier(null);
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
	public void deleteWimStockrecordlinesById() {
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

	public String goAudit() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			inventoryTypeList = inboundWarehouseController.doListInventoryTypeByEntity(params);
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

	// 跳转到选择仓库页面
	public String goChooseWarehouse() {
		return "goChooseWarehouse";
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
			pager = itemService.findPagerByHqlConditions(pager, Item.class, params);
			/*
			 * if (pager.getResultList().size() < 10) { int listSize =
			 * pager.getResultList().size(); for (int i = 0; i < 10 - listSize;
			 * i++) { pager.getResultList().add(new Item()); } }
			 */
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
			if (StringUtils.isNotEmpty(itemIdSpecId) && !"0".equals(itemIdSpecId)) {
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

	public String goListModifiedLeaveMarkContent() {
		try {
			Pager pager = billMarkProcessController.getCMNObjectModifyRecordPager();
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListModifiedLeaveMarkContent";
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

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
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

	public InventoryParameters getInventoryParameters() {
		return inventoryParameters;
	}

	public void setInventoryParameters(InventoryParameters inventoryParameters) {
		this.inventoryParameters = inventoryParameters;
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

}
