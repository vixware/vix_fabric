package com.vix.inventory.takestock.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.JSonUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.takestock.controller.TakeStockController;
import com.vix.inventory.takestock.entity.StockTaking;
import com.vix.inventory.takestock.entity.StockTakingItem;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.nvix.common.base.template.ExcelTemplate;

import flexjson.JSONDeserializer;
import flexjson.transformer.DateTransformer;

@Controller
@Scope("prototype")
public class TakeStockAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private TakeStockController takeStockController;
	@Autowired
	private OrderProcessController orderProcessController;
	private String id;
	/**
	 * 仓库ID
	 */
	private String warehouseId;
	/**
	 * 货位ID
	 */
	private String invShelfId;
	private String ids;
	private String pageNo;
	/**
	 * 盘点单
	 */
	private StockTaking stockTaking;
	private List<StockTaking> stockTakingList;
	/**
	 * 盘点单明细
	 */
	private StockTakingItem stockTakingItem;
	private List<StockTakingItem> stockTakingItemList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			String tag = getRequestParameter("tag");
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			if ("0".equals(tag)) {
				// 简单搜索
				String name = getRequestParameter("name");
				if (name != null && !"".equals(name)) {
					params.put("code," + SearchCondition.EQUAL, name.trim());
				}
			} else {
				// 高级搜索
				String stocktakingcode = getRequestParameter("stocktakingcode");
				if (stocktakingcode != null && !"".equals(stocktakingcode)) {
					params.put("code," + SearchCondition.EQUAL, stocktakingcode.trim());
				}
				String warehousename = getDecodeRequestParameter("warehousename");
				if (warehousename != null && !"".equals(warehousename)) {
					params.put("invWarehouse.name," + SearchCondition.ANYLIKE, warehousename.trim());
				}
				String personcode = getRequestParameter("personcode");
				if (personcode != null && !"".equals(personcode)) {
					params.put("personcode," + SearchCondition.EQUAL, personcode.trim());
				}
			}
			stockTakingList = takeStockController.doListStockTakingList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 根据条件查询入库单信息
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			String tag = getRequestParameter("tag");
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			// 倒序排序
			Pager pager = getPager();
			if ("0".equals(tag)) {
				// 简单搜索
				String name = getRequestParameter("name");
				if (name != null && !"".equals(name)) {
					params.put("code," + SearchCondition.EQUAL, name.trim());
				}
			} else {
				// 高级搜索
				String stocktakingcode = getRequestParameter("stocktakingcode");
				if (stocktakingcode != null && !"".equals(stocktakingcode)) {
					params.put("code," + SearchCondition.EQUAL, stocktakingcode.trim());
				}
				String warehousename = getDecodeRequestParameter("warehousename");
				if (warehousename != null && !"".equals(warehousename)) {
					params.put("invWarehouse.name," + SearchCondition.ANYLIKE, warehousename.trim());
				}
				String personcode = getRequestParameter("personcode");
				if (personcode != null && !"".equals(personcode)) {
					params.put("personcode," + SearchCondition.EQUAL, personcode.trim());
				}
			}

			// 搜索加强
			Map<String, Object> stockRecordLinesparams = new HashMap<String, Object>();
			// 根据商品信息获取订单ID
			String itemname = getDecodeRequestParameter("itemname");
			String itemcode = getDecodeRequestParameter("itemcode");
			String stockRecordsIds = "";
			if (StringUtils.isNotEmpty(itemname)) {
				stockRecordLinesparams.put("itemname," + SearchCondition.ANYLIKE, itemname);
			}
			if (StringUtils.isNotEmpty(itemcode)) {
				stockRecordLinesparams.put("itemcode," + SearchCondition.EQUAL, itemcode);
			}
			List<StockTakingItem> stockTakingItemList = takeStockController.doListStockTakingItemList(stockRecordLinesparams);
			if (stockTakingItemList != null && stockTakingItemList.size() > 0) {
				for (StockTakingItem stockTakingItem : stockTakingItemList) {
					if (stockTakingItem != null && stockTakingItem.getStockTaking() != null) {
						stockRecordsIds += stockTakingItem.getStockTaking().getId() + ",";
					}
				}
			}
			if (StringUtils.isNotEmpty(stockRecordsIds)) {
				params.put("id," + SearchCondition.IN, stockRecordsIds);
			}
			// 搜索加强

			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager = takeStockController.doListStockTaking(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 根据盘点条件创建盘点单
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockTaking = takeStockController.doListStockTakingById(id);
			} else {
				stockTaking = new StockTaking();
				stockTaking.setIsTemp("1");
				stockTaking.setCode(VixUUID.createCode(10));
				stockTaking.setPersoncode(SecurityUtil.getCurrentUserName());
				stockTaking.setMaker(SecurityUtil.getCurrentUserName());
				List<StockTakingItem> stockTakingItemList = new ArrayList<StockTakingItem>();
				// 根据条件获取库存信息
				Map<String, Object> params = new HashMap<String, Object>();
				if (warehouseId != null) {
					InvWarehouse invWarehouse = takeStockController.doListInvWarehouseById(warehouseId);
					if (invWarehouse != null) {
						stockTaking.setInvWarehouse(invWarehouse);
					}
					params.put("invWarehouse.id," + SearchCondition.EQUAL, warehouseId);
				}
				if (invShelfId != null) {
					params.put("invShelf.id," + SearchCondition.EQUAL, invShelfId);
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
				stockTaking = takeStockController.doSaveStockTaking(stockTaking, stockTakingItemList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goSaveOrUpdateStockTakingItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockTakingItem = takeStockController.doListStockTakingItemById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goSaveOrUpdateStockTakingItem";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != stockTaking.getId() && !"".equals(stockTaking.getId())) {
				isSave = false;
			}
			stockTaking.setIsTemp("");
			/* 盘点单明细 */
			String dlJson = getRequestParameter("dlJson");
			DateTransformer dateTransformer = new DateTransformer("yyyy-MM-dd");
			List<StockTakingItem> dlList = null;
			if (StringUtils.isNotEmpty(dlJson)) {
				dlList = new JSONDeserializer<List<StockTakingItem>>().use(Date.class, dateTransformer).use("values", StockTakingItem.class).deserialize(dlJson);
			}

			// 处理中文索引
			if (stockTaking.getName() != null) {
				String name = ChnToPinYin.getPYString(stockTaking.getName());
				stockTaking.setChineseCharacter(name.toUpperCase());
			}
			initEntityBaseController.initEntityBaseAttribute(stockTaking);
			billMarkProcessController.processMark(stockTaking, updateField);

			stockTaking = takeStockController.doSaveStockTaking(stockTaking, dlList);
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
			StockTaking pb = takeStockController.doListStockTakingById(id);
			if (null != pb) {
				takeStockController.doDeleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 获取明细的json数据 */

	public void getStockTakingItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id)) {
				StockTaking stockTaking = takeStockController.doListStockTakingById(id);
				if (stockTaking != null) {
					if (stockTaking.getStockTakingItem() != null && stockTaking.getStockTakingItem().size() > 0) {
						json = convertListToJson(new ArrayList<StockTakingItem>(stockTaking.getStockTakingItem()), stockTaking.getStockTakingItem().size());
					}
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
	public String deleteByIds() {
		try {
			if (null != ids && !"".equals(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				takeStockController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 增加盘点单明细
	 * 
	 * @return
	 */
	public String saveOrUpdateStockTakingItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockTaking = takeStockController.doListStockTakingById(id);
				stockTakingItem.setStockTaking(stockTaking);
				takeStockController.doSaveStockTakingItem(stockTakingItem);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteStockTakingItemById() {
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
		return UPDATE;
	}

	/*
	 * public String exportTakeStockExcel() throws Exception { try { String
	 * xlsPath = "resource/export/inv_takestock_template.xls"; String xmlPath =
	 * "resource/export/inv_takestock_template.xml"; File xlsFile = new
	 * File(Thread.currentThread().getContextClassLoader().getResource(xlsPath).
	 * toURI()); File xmlFile = new
	 * File(Thread.currentThread().getContextClassLoader().getResource(xmlPath).
	 * toURI()); ExcelService excelService = new ExcelServiceImpl(this);
	 * HttpServletResponse excelResponse = getResponse();
	 * excelResponse.setContentType("application/octet-stream");
	 * excelResponse.setHeader("Charset", "UTF-8");
	 * excelResponse.setCharacterEncoding("UTF-8");
	 * 
	 * String ua = getRequest().getHeader("user-agent"); String fileName =
	 * "盘点单.xls"; if (ua != null && ua.indexOf("Firefox") >= 0)
	 * excelResponse.addHeader("Content-Disposition",
	 * "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8")
	 * + "\""); else excelResponse.addHeader("Content-Disposition",
	 * "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
	 * excelService.exportExcel(xmlFile, xlsFile,
	 * excelResponse.getOutputStream()); } catch (Exception e) {
	 * e.printStackTrace(); } return NONE; }
	 */

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
			// String xlsPath = "resource/export/inv_takestock_template.xls";
			// String xmlPath = "resource/export/inv_takestock_template.xml";
			//String xlsPath = "resource/export/stocktakingitem_template.xls";
			//File xlsFile = new File(Thread.currentThread().getContextClassLoader().getResource(xlsPath).toURI());
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
			/*
			 * String xlsPath =
			 * "G:/vixnt-workspace/vixnt/vixnt-erp/src/main/resources/resource/export/stocktakingitem_template2.xls";
			 * File xlsFile = new File(xlsPath); try (InputStream is = new
			 * FileInputStream(xlsFile)) { Context context = new Context();
			 * context.putVar("stockTakingItemList", stockTakingItemList);
			 * JxlsHelper.getInstance().processTemplateAtCell(is,
			 * excelResponse.getOutputStream(), context, "盘点单!A1"); }
			 */

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

	/*
	 * public List<Map<String, Object>> loadData(String arg0, String[] arg1) {
	 * List<Map<String, Object>> resList = new LinkedList<Map<String,
	 * Object>>(); try { Map<String, Object> params = getParams(); //
	 * 根据盘点单导出相应数据 params.put("stockTaking.id," + SearchCondition.EQUAL, id);
	 * List<StockTakingItem> stockTakingList =
	 * takeStockController.doListStockTakingItemList(params); for
	 * (StockTakingItem stockTakingItem : stockTakingList) { if (stockTakingItem
	 * != null) { Map<String, Object> reMap = new HashMap<String, Object>();
	 * reMap.put("cvposition", stockTakingItem.getInvShelf().getCode());
	 * reMap.put("itemcode", stockTakingItem.getItemcode());
	 * reMap.put("itemname", stockTakingItem.getItemname());
	 * reMap.put("cvquantity", stockTakingItem.getCvquantity());
	 * reMap.put("cvcquantity", stockTakingItem.getCvcquantity());
	 * reMap.put("twoQuantity", stockTakingItem.getTwoQuantity());
	 * resList.add(reMap); } } } catch (Exception e) { e.printStackTrace(); }
	 * return resList; }
	 */

	/**
	 * 导入
	 */
	/*
	 * public void importFile() { List<StockTakingItemVo> stockTakingItemVoList
	 * = null;
	 * 
	 * Map<String, String> msgMap = new HashMap<String, String>();
	 * FileInputStream fis = null;
	 * 
	 * if (fileToUpload == null) { msgMap.put("success", "0");
	 * msgMap.put("error", "没有选择文件!");
	 * renderHtml(JSonUtils.makeMapToJson(msgMap)); }
	 * 
	 * try { fis = new FileInputStream(fileToUpload);
	 * 
	 * ExcelUtil<StockTakingItemVo> metadataUtil = new
	 * ExcelUtil<StockTakingItemVo>(); stockTakingItemVoList =
	 * metadataUtil.importExcel(StockTakingItemVo.class, "盘点单", fis);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); msgMap.put("success", "0");
	 * msgMap.put("error", e.getMessage());
	 * renderHtml(JSonUtils.makeMapToJson(msgMap)); } finally { if (fis != null)
	 * { IOUtils.closeQuietly(fis); } }
	 * 
	 * try { if (stockTakingItemVoList != null && stockTakingItemVoList.size() >
	 * 0) { if (StringUtils.isNotEmpty(id) && !"0".equals(id)) { stockTaking =
	 * takeStockController.doListStockTakingById(id); for (StockTakingItem s :
	 * stockTaking.getStockTakingItem()) { for (StockTakingItemVo
	 * stockTakingItemvo : stockTakingItemVoList) { if (stockTakingItemvo !=
	 * null) { if (s.getItemcode().equals(stockTakingItemvo.getItemcode())) {
	 * s.setCvcquantity(Float.valueOf(stockTakingItemvo.getCvcquantity()));
	 * takeStockController.doSaveStockTakingItem(s); } } } } } } } catch
	 * (Exception e) { e.printStackTrace(); msgMap.put("success", "0");
	 * msgMap.put("error", e.getMessage());
	 * renderHtml(JSonUtils.makeMapToJson(msgMap)); }
	 * 
	 * msgMap.put("success", "1"); msgMap.put("msg", "导入成功!"); String reMsg =
	 * JSonUtils.makeMapToJson(msgMap); renderHtml(reMsg); }
	 */
	// 初盘导入
	public void importFile() {
		Map<String, String> msgMap = new HashMap<String, String>();
		FileInputStream fis = null;
		try {
			if (fileToUpload == null) {
				msgMap.put("success", "0");
				msgMap.put("error", "没有选择文件!");
			} else {
				// takeStockController.importShopEcProductPrice(fileToUpload,
				// fileToUploadFileName);
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

	/**
	 * 盘点
	 * 
	 * @return
	 */
	public String goTakeStock() {
		return "goTakeStock";
	}

	public String goShowTakeStock() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockTaking = takeStockController.doListStockTakingById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowTakeStock";
	}

	public String goPrintStockTaking() {
		try {
			stockTaking = takeStockController.doListStockTakingById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintStockTaking";
	}

	/**
	 * 获取上一条数据
	 * 
	 * @return
	 */
	public String goBeforeStockTaking() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				stockTaking = takeStockController.doListStockTakingById(id);
				if (stockTaking != null && stockTaking.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(stockTaking.getCreateTime()));
					// 过滤临时数据
					params.put("isTemp", "");
					params.put("tenantId", stockTaking.getTenantId());
					params.put("companyInnerCode", stockTaking.getCompanyInnerCode());
					params.put("creator", stockTaking.getCreator());
					stockTaking = (StockTaking) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(stockTaking.getCreateTime()), params, stockTaking, "before");
				}
				if (stockTaking == null || StringUtils.isEmpty(stockTaking.getId())) {
					stockTaking = takeStockController.doListStockTakingById(id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowTakeStock";
	}

	/**
	 * 
	 * @return
	 */
	public String goAfterStockTaking() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				stockTaking = takeStockController.doListStockTakingById(id);
				if (stockTaking != null && stockTaking.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(stockTaking.getCreateTime()));
					params.put("isTemp", "");
					params.put("tenantId", stockTaking.getTenantId());
					params.put("companyInnerCode", stockTaking.getCompanyInnerCode());
					params.put("creator", stockTaking.getCreator());
					stockTaking = (StockTaking) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(stockTaking.getCreateTime()), params, stockTaking, "after");
					if (stockTaking == null || StringUtils.isEmpty(stockTaking.getId())) {
						stockTaking = takeStockController.doListStockTakingById(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowTakeStock";
	}

	public String goSearch() {
		return "goSearch";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public StockTaking getStockTaking() {
		return stockTaking;
	}

	public void setStockTaking(StockTaking stockTaking) {
		this.stockTaking = stockTaking;
	}

	public List<StockTaking> getStockTakingList() {
		return stockTakingList;
	}

	public void setStockTakingList(List<StockTaking> stockTakingList) {
		this.stockTakingList = stockTakingList;
	}

	public StockTakingItem getStockTakingItem() {
		return stockTakingItem;
	}

	public void setStockTakingItem(StockTakingItem stockTakingItem) {
		this.stockTakingItem = stockTakingItem;
	}

	public List<StockTakingItem> getStockTakingItemList() {
		return stockTakingItemList;
	}

	public void setStockTakingItemList(List<StockTakingItem> stockTakingItemList) {
		this.stockTakingItemList = stockTakingItemList;
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

}
