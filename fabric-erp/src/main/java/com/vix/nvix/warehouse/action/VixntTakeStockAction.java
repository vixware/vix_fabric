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
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.takestock.controller.TakeStockController;
import com.vix.inventory.takestock.entity.StockTaking;
import com.vix.inventory.takestock.entity.StockTakingItem;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.template.ExcelTemplate;

/**
 * 
 * @类全名 com.vix.nvix.warehouse.action.VixntTakeStockAction
 *
 * @author zhanghaibing
 *
 * @date 2016年8月26日
 */
@Controller
@Scope("prototype")
public class VixntTakeStockAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private TakeStockController takeStockController;
	private String id;
	/**
	 * 仓库ID
	 */
	private String warehouseId;
	/**
	 * 货位ID
	 */
	private String invShelfId;
	/**
	 * 盘点单
	 */
	private StockTaking stockTaking;
	private SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/DD");

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
				pager.setOrderBy("asc");
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
	public void takeStockByCondition() {
		stockTaking = new StockTaking();
		Employee employee = getEmployee();
		if (employee != null) {
			stockTaking.setPersoncode(employee.getName());
			stockTaking.setMaker(employee.getName());
			stockTaking.setCreator(employee.getName());
			if (employee.getChannelDistributor() != null) {
				stockTaking.setChannelDistributor(employee.getChannelDistributor());
			}
		}
		stockTaking.setCode(VixUUID.createCode(10));
		stockTaking.setName("盘点单" + sdf.format(new Date()));
		stockTaking.setCreateTime(new Date());
		List<StockTakingItem> stockTakingItemList = new ArrayList<StockTakingItem>();
		// 根据条件获取库存信息
		Map<String, Object> params = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(warehouseId)) {
			InvWarehouse invWarehouse = null;
			try {
				invWarehouse = takeStockController.doListInvWarehouseById(warehouseId);
			} catch (Exception e) {
				e.printStackTrace();
				renderText(SAVE_FAIL + e.getStackTrace());
			}
			if (invWarehouse != null) {
				stockTaking.setInvWarehouse(invWarehouse);
				params.put("invWarehouse.id," + SearchCondition.EQUAL, warehouseId);
			}
		}
		if (StringUtils.isNotEmpty(invShelfId)) {
			params.put("invShelf.id," + SearchCondition.EQUAL, invShelfId);
		}
		List<InventoryCurrentStock> inventoryCurrentStockList = null;
		try {
			inventoryCurrentStockList = takeStockController.doListInventoryCurrentStockList(params);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL + e.getStackTrace());
		}
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
		try {
			initEntityBaseController.initEntityBaseAttribute(stockTaking);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL + e.getStackTrace());
		}
		try {
			stockTaking = takeStockController.doSaveStockTaking(stockTaking, stockTakingItemList);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL + e.getStackTrace());
		}
		if (stockTaking != null && StringUtils.isNotEmpty(stockTaking.getId())) {
			renderText(stockTaking.getId() + ":" + SAVE_SUCCESS);
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

}
