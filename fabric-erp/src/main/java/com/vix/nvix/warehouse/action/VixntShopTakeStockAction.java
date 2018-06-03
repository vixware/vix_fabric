package com.vix.nvix.warehouse.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.IOUtils;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xml.XmlAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;
import org.jxls.util.TransformerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.JSonUtils;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.inbound.domain.InboundWarehouseDomain;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.takestock.controller.TakeStockController;
import com.vix.inventory.takestock.entity.StockTaking;
import com.vix.inventory.takestock.entity.StockTakingItem;
import com.vix.inventory.warehouse.entity.InvShelf;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.mdm.item.entity.StoreItem;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.template.ExcelTemplate;

/**
 * 
 * @类全名 com.vix.nvix.warehouse.action.VixntShopTakeStockAction
 *
 * @author zhanghaibing
 *
 * @date 2017年1月22日
 */
@Controller
@Scope("prototype")
public class VixntShopTakeStockAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private TakeStockController takeStockController;
	private String id;
	@Autowired
	private InboundWarehouseDomain inboundWarehouseDomain;
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;
	/**
	 * 仓库ID
	 */
	private String warehouseId;
	/**
	 * 货位ID
	 */
	private String invShelfId;
	/**
	 * 分类ID
	 */
	private String itemCatalogId;
	/**
	 * 盘点单
	 */
	private StockTaking stockTaking;
	SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/DD");

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String title = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(title)) {
				params.put("name," + SearchCondition.ANYLIKE, title.trim());
			}
			Pager pager = getPager();
			// 排序
			if (StringUtils.isEmpty(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
					params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, StockTaking.class, params);
				} else {
					pager = vixntBaseService.findPagerByHqlConditions(pager, StockTaking.class, params);
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取列表数据 */
	public void goInvShelfSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String wareHouseId = getRequestParameter("wareHouseId");
			params.put("type," + SearchCondition.EQUAL, 2);
			String invShelfName = getDecodeRequestParameter("invShelfName");
			if (StringUtils.isNotEmpty(invShelfName)) {
				params.put("name," + SearchCondition.ANYLIKE, invShelfName.trim());
			}
			Pager pager = getPager();
			// 排序
			if (StringUtils.isEmpty(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			if (StringUtils.isNotEmpty(wareHouseId)) {
				params.put("invWarehouse.id," + SearchCondition.EQUAL, wareHouseId);
				pager = vixntBaseService.findPagerByHqlConditions(pager, InvShelf.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
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
			String warehouseName = getDecodeRequestParameter("warehouseName");
			if (StringUtils.isNotEmpty(warehouseName)) {
				params.put("name," + SearchCondition.ANYLIKE, warehouseName.trim());
			}
			// 获取当前员工信息
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
					params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, InvWarehouse.class, params);
				} else {
					ChannelDistributor channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
					if (channelDistributor != null) {
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

	/**
	 * 根据盘点条件创建盘点单
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				stockTaking = takeStockController.doListStockTakingById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * @throws Exception
	 */
	public void takeStockByCondition() throws Exception {

		List<InventoryCurrentStock> invList = vixntBaseService.findAllByEntityClass(InventoryCurrentStock.class);
		if (invList != null) {
			for (InventoryCurrentStock inventoryCurrentStock : invList) {
				if (inventoryCurrentStock.getItemCatalog() != null) {

				} else {
					Item item = vixntBaseService.findEntityByAttribute(Item.class, "code", inventoryCurrentStock.getItemcode());
					if (item != null && item.getItemCatalog() != null) {
						inventoryCurrentStock.setItemCatalog(item.getItemCatalog());
						inventoryCurrentStock = vixntBaseService.merge(inventoryCurrentStock);
					}
				}
			}
		}
		stockTaking = new StockTaking();
		Employee employee = getEmployee();
		if (employee != null && employee.getChannelDistributor() != null) {
			stockTaking.setChannelDistributor(employee.getChannelDistributor());
			stockTaking.setPersoncode(employee.getName());
			stockTaking.setMaker(employee.getName());
			stockTaking.setCreator(employee.getName());
		}
		stockTaking.setCode(VixUUID.createCode(10));
		stockTaking.setName("盘点单" + sdf.format(new Date()));
		stockTaking.setCreateTime(new Date());
		List<StockTakingItem> stockTakingItemList = new ArrayList<StockTakingItem>();
		// 根据条件获取库存信息
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(warehouseId)) {
			InvWarehouse invWarehouse = takeStockController.doListInvWarehouseById(warehouseId);
			if (invWarehouse != null) {
				stockTaking.setInvWarehouse(invWarehouse);
			}
			params.put("invWarehouse.id," + SearchCondition.EQUAL, warehouseId);
		}
		if (StringUtils.isNotEmpty(invShelfId)) {
			params.put("invShelf.id," + SearchCondition.EQUAL, invShelfId);
		}
		if (StringUtils.isNotEmpty(itemCatalogId)) {
			List<ItemCatalog> itemCatalogList = new ArrayList<ItemCatalog>();
			String itemCatalogIds = "";
			ItemCatalog itemCatalog = vixntBaseService.findEntityById(ItemCatalog.class, itemCatalogId);
			if (itemCatalog != null) {
				itemCatalogList.add(itemCatalog);
				getItemCatalogList(itemCatalogList, itemCatalog);
			}
			if (itemCatalogList != null && itemCatalogList.size() > 0) {
				for (ItemCatalog i : itemCatalogList) {
					itemCatalogIds += "," + i.getId();
				}
			}
			params.put("itemCatalog.id," + SearchCondition.IN, itemCatalogIds);
		}
		List<InventoryCurrentStock> inventoryCurrentStockList = takeStockController.doListInventoryCurrentStockList(params);
		if (inventoryCurrentStockList != null && inventoryCurrentStockList.size() > 0) {
			for (InventoryCurrentStock inventoryCurrentStock : inventoryCurrentStockList) {
				if (inventoryCurrentStock != null) {
					StockTakingItem s = new StockTakingItem();
					s.setItemcode(inventoryCurrentStock.getItemcode());
					s.setItemname(inventoryCurrentStock.getItemname());
					s.setItemtype(inventoryCurrentStock.getItemtype());
					s.setSpecification(inventoryCurrentStock.getSpecification());
					s.setUnit(inventoryCurrentStock.getUnit());
					s.setCvquantity(inventoryCurrentStock.getQuantity());
					s.setCbarcode(inventoryCurrentStock.getBatchcode());
					if (inventoryCurrentStock.getInvWarehouse() != null) {
						s.setInvWarehouse(inventoryCurrentStock.getInvWarehouse());
					}
					if (inventoryCurrentStock.getInvShelf() != null) {
						s.setInvShelf(inventoryCurrentStock.getInvShelf());
					}
					stockTakingItemList.add(s);
				}
			}
		}
		initEntityBaseController.initEntityBaseAttribute(stockTaking);
		stockTaking = takeStockController.doSaveStockTaking(stockTaking, stockTakingItemList);
		if (stockTaking != null && StringUtils.isNotEmpty(stockTaking.getId())) {
			renderText(stockTaking.getId());
		}
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(stockTaking.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(stockTaking);
			if (stockTaking.getChannelDistributor() == null || StringUtils.isEmpty(stockTaking.getChannelDistributor().getId())) {
				stockTaking.setChannelDistributor(null);
			}
			stockTaking = vixntBaseService.merge(stockTaking);
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

	public void goStockTakingItemList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 倒序排序
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String itemname = getDecodeRequestParameter("itemname");
			if (StringUtils.isNotEmpty(itemname)) {
				params.put("itemname," + SearchCondition.ANYLIKE, itemname.trim());
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("stockTaking.id," + SearchCondition.EQUAL, id);
				pager = takeStockController.doListStockTakingItem(params, pager);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 盘点
	 * 
	 * @return
	 */
	public String goChooseWarehouseAndShelf() {
		return "goChooseWarehouseAndShelf";
	}

	public String goChooseWarehouse() {
		return "goChooseWarehouse";
	}

	public String goChooseShelf() {
		return "goChooseShelf";
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			StockTaking pb = takeStockController.doListStockTakingById(id);
			if (null != pb) {
				takeStockController.doDeleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/** 处理删除操作 */
	public void deleteStockTakingItemById() {
		try {
			StockTakingItem stockTakingItem = takeStockController.doListStockTakingItemById(id);
			if (null != stockTakingItem) {
				takeStockController.deleteStockTakingItem(stockTakingItem);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	private void getItemCatalogList(List<ItemCatalog> itemCatalogList, ItemCatalog itemCatalog) {
		try {
			if (itemCatalog != null) {
				itemCatalogList.add(itemCatalog);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("parentItemCatalog.id," + SearchCondition.EQUAL, itemCatalog.getId());
				List<ItemCatalog> iList = vixntBaseService.findAllByConditions(ItemCatalog.class, params);
				if (iList != null && iList.size() > 0) {
					for (ItemCatalog i : iList) {
						itemCatalogList.add(i);
						getItemCatalogList(itemCatalogList, i);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String exportTakeStockExcel2() throws Exception {
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");

			String ua = getRequest().getHeader("user-agent");
			String fileName = "盘点单.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

			Map<String, Object> params = getParams();
			// 根据盘点单导出相应数据
			params.put("stockTaking.id," + SearchCondition.EQUAL, id);
			List<StockTakingItem> stockTakingItemList = takeStockController.doListStockTakingItemList(params);
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("stocktakingitem_template.xls")) {
				Context context = new Context();
				context.putVar("stockTakingItemList", stockTakingItemList);
				JxlsHelper.getInstance().processTemplateAtCell(is, excelResponse.getOutputStream(), context, "stockTakingItem!A1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 导出盘点对比单
	 * 
	 * @return
	 * @throws Exception
	 */

	public String exportTakeStockExcel1() throws Exception {
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");

			String ua = getRequest().getHeader("user-agent");
			String fileName = "盘点差异表.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

			Map<String, Object> params = getParams();
			// 根据盘点单导出相应数据
			params.put("stockTaking.id," + SearchCondition.EQUAL, id);
			List<StockTakingItem> stockTakingItemList = takeStockController.doListStockTakingItemList(params);
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("object_collection_xmlbuilder_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("object_collection_xmlbuilder.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("stockTakingItemList", stockTakingItemList);
					xlsArea.applyAt(new CellRef("stockTakingItem!A1"), context);
					transformer.write();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	// 初盘导入
	public void importFile() {
		Map<String, String> msgMap = new HashMap<String, String>();
		FileInputStream fis = null;
		try {
			if (fileToUpload == null) {
				msgMap.put("success", "0");
				msgMap.put("error", "没有选择文件!");
			} else {
				try (InputStream xmlInputStream = ExcelTemplate.class.getResourceAsStream("takestock_template.xml")) {
					XLSReader reader = ReaderBuilder.buildFromXML(xmlInputStream);
					try (InputStream xlsInputStream = new FileInputStream(fileToUpload)) {
						List<StockTakingItem> stockTakingItemList = new ArrayList<StockTakingItem>();
						Map<String, Object> beans = new HashMap<String, Object>();
						beans.put("stockTakingItemList", stockTakingItemList);
						reader.read(xlsInputStream, beans);
						for (StockTakingItem stockTakingItem : stockTakingItemList) {
							Map<String, Object> params = getParams();
							params.put("itemcode," + SearchCondition.EQUAL, stockTakingItem.getItemcode());
							if (StringUtils.isNotEmpty(id)) {
								params.put("stockTaking.id," + SearchCondition.EQUAL, id);
							}
							List<StockTakingItem> sList = takeStockController.doListStockTakingItemList(params);
							if (sList != null && sList.size() > 0) {
								for (StockTakingItem s : sList) {
									s.setCvcquantity(stockTakingItem.getCvcquantity());
									s = takeStockController.doSaveStockTakingItem(s);
									dealStockTakingItem(s);
								}
							}
						}
					}
				}
				msgMap.put("success", "1");
				msgMap.put("msg", "导入成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msgMap.put("success", "0");
			msgMap.put("error", e.getMessage());
		} finally {
			if (fis != null) {
				IOUtils.closeQuietly(fis);
			}
		}
		String reMsg = JSonUtils.makeMapToJson(msgMap);
		renderHtml(reMsg);
	}

	public void dealStockTakingItem(StockTakingItem s) {

		try {
			// 需要通过仓库 及sku编码进行唯一存储
			Map<String, Object> params = new HashMap<String, Object>();
			// 过滤临时数据
			params.put("isQualfied", 1);
			params.put("itemcode", s.getItemcode());
			if (s.getInvWarehouse() != null && s.getInvWarehouse().getId() != null) {
				params.put("invWarehouseId", s.getInvWarehouse().getId());
			}
			StringBuilder hql = standingBookHqlProvider.findInventoryCurrentStockByItemcode(params);
			InventoryCurrentStock inventoryCurrentStock = inboundWarehouseDomain.findInventoryCurrentStockByHql(hql.toString(), params);

			StoreItem t = null;
			Map<String, Object> itemparams = new HashMap<String, Object>();
			itemparams.put("code", inventoryCurrentStock.getItemcode());

			Employee employee = super.getEmployee();
			if (employee != null) {
				if (employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
					itemparams.put("channelDistributorId", employee.getChannelDistributor().getId());
				} else {
					ChannelDistributor channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
					if (channelDistributor != null) {
						itemparams.put("channelDistributorId", channelDistributor.getId());
					}
				}
			}
			StringBuilder itemhql = standingBookHqlProvider.findStoreItemByItemCode(itemparams);
			t = vixntBaseService.findObjectByHql(itemhql.toString(), itemparams);
			if (inventoryCurrentStock != null && s.getCvcquantity() != null) {
				inventoryCurrentStock.setQuantity(s.getCvcquantity());
				inventoryCurrentStock.setAvaquantity(s.getCvcquantity());
				Double costPrice = 0D;
				if (t != null && s.getCvcquantity() != null && t.getPurchasePrice() != null) {
					costPrice = s.getCvcquantity() * t.getPurchasePrice();
					inventoryCurrentStock.setCostPrice(costPrice);
				}
				inboundWarehouseDomain.saveOrUpdateInventoryCurrentStock(inventoryCurrentStock);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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

	public StockTaking getStockTaking() {
		return stockTaking;
	}

	public void setStockTaking(StockTaking stockTaking) {
		this.stockTaking = stockTaking;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getInvShelfId() {
		return invShelfId;
	}

	public void setInvShelfId(String invShelfId) {
		this.invShelfId = invShelfId;
	}

	public String getItemCatalogId() {
		return itemCatalogId;
	}

	public void setItemCatalogId(String itemCatalogId) {
		this.itemCatalogId = itemCatalogId;
	}
}