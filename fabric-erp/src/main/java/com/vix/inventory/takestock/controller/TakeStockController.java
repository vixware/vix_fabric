/**
 * 
 */
package com.vix.inventory.takestock.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.vixdata.util.NumberUtil;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.takestock.domain.TakeStockDomain;
import com.vix.inventory.takestock.entity.StockTaking;
import com.vix.inventory.takestock.entity.StockTakingItem;
import com.vix.inventory.warehouse.entity.InvWarehouse;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Controller("takeStockController")
@Scope("prototype")
public class TakeStockController {

	Logger logger = Logger.getLogger(TakeStockController.class);

	@Autowired
	private TakeStockDomain takeStockDomain;

	/**
	 * 
	 * 
	 * @param wimStockrecords
	 * @return
	 * @throws Exception
	 */
	public StockTaking doSaveStockTaking(StockTaking stockTaking, List<StockTakingItem> stockTakingItemList) throws Exception {
		StockTaking stockTaking1 = null;
		// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();
		// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 执行保存操作
		stockTaking1 = takeStockDomain.mergeStockTaking(stockTaking);
		if (stockTakingItemList != null && stockTakingItemList.size() > 0) {
			for (StockTakingItem stockTakingItem : stockTakingItemList) {
				if (stockTaking1 != null) {
					stockTakingItem.setStockTaking(stockTaking1);
				}
				// 保存调拨单明细
				takeStockDomain.mergeStockTakingItem(stockTakingItem);
			}
		}
		// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return stockTaking1;
	}

	/**
	 * 保存盘点单明细
	 * 
	 * @param stockTakingItem
	 * @return
	 * @throws Exception
	 */
	public StockTakingItem doSaveStockTakingItem(StockTakingItem stockTakingItem) throws Exception {
		StockTakingItem stockTakingItem1 = null;
		// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 执行保存操作
		stockTakingItem1 = takeStockDomain.mergeStockTakingItem(stockTakingItem);
		// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");
		// 访问功能和业务对象记录操作 visitBORecord(parameter);
		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return stockTakingItem1;
	}

	public List<StockTakingItem> doSaveStockTakingItemList(StockTaking stockTaking, List<InventoryCurrentStock> inventoryCurrentStockList) throws Exception {
		List<StockTakingItem> stockTakingItemList = new ArrayList<StockTakingItem>();
		if (inventoryCurrentStockList != null && inventoryCurrentStockList.size() > 0) {
			for (InventoryCurrentStock inventoryCurrentStock : inventoryCurrentStockList) {
				StockTakingItem stockTakingItem = new StockTakingItem();
				stockTakingItem.setItemcode(inventoryCurrentStock.getItemcode());
				stockTakingItem.setItemname(inventoryCurrentStock.getItemname());
				stockTakingItem.setItemtype(inventoryCurrentStock.getItemtype());
				stockTakingItem.setSpecification(inventoryCurrentStock.getSpecification());
				stockTakingItem.setUnit(inventoryCurrentStock.getUnit());
				stockTakingItem.setCvquantity(inventoryCurrentStock.getQuantity());
				stockTakingItem.setCbarcode(inventoryCurrentStock.getBatchcode());
				// 执行保存操作
				takeStockDomain.mergeStockTakingItem(stockTakingItem);
				stockTakingItemList.add(stockTakingItem);
			}
		}
		return stockTakingItemList;
	}

	/**
	 * 
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doListStockTaking(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 执行保存操作
		p = takeStockDomain.findStockTakingPagerByHqlConditions(params, pager);

		// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return p;
	}

	public Pager doListStockTakingItem(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 执行保存操作
		p = takeStockDomain.findStockTakingItemPagerByHqlConditions(params, pager);

		// 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return p;
	}

	/**
	 * 简单查询
	 * 
	 * @param params
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public Pager doListStockTakingByCon(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		p = takeStockDomain.findStockTakingPagerByOrHqlConditions(params, pager);

		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return p;
	}

	/**
	 * 
	 * 
	 * @param stockTaking
	 * @throws Exception
	 */
	public void doDeleteByEntity(StockTaking stockTaking) throws Exception {
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3. 执行删除操作
		takeStockDomain.deleteByEntity(stockTaking);
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
		takeStockDomain.deleteByIds(ids);
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
	public StockTaking doListStockTakingById(String id) throws Exception {
		StockTaking stockTaking = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		stockTaking = takeStockDomain.findStockTakingById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return stockTaking;
	}

	public int importShopEcProductPrice(File file, String fileName) throws Exception {
		if (null == file || StrUtils.objectIsNull(fileName)) {
			return 0;
		}
		int count = 0;
		InputStream is = new FileInputStream(file);
		String fileExName = fileName.split("\\.")[1];
		if (null != fileExName && "xls".equals(fileExName.toLowerCase())) {
			count = importXlsEcShopProductPrice(is);
		}
		if (null != fileExName && "xlsx".equals(fileExName.toLowerCase())) {
			count = importXlsxEcShopProductPrice(is);
		}
		is.close();
		return count;
	}

	private int importXlsEcShopProductPrice(InputStream is) throws Exception {
		int importCount = 0;
		Map<String, Object> params = new HashMap<String, Object>();
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 循环行Row
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null || rowNum == 0) {
					continue;
				}
				// 循环列Cell
				for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
					HSSFCell hssfCell = hssfRow.getCell(cellNum);
					if (hssfCell == null) {
						continue;
					}
					/** 编码为空，则不导入该数据 */
					if (cellNum > 0) {
						break;
					}
					String value = getXlsValue(hssfCell);
					value = value.trim();
					if (cellNum == 0 && null != value && !"".equals(value)) {
						params.clear();
					}
					if (cellNum == 3 && NumberUtil.isDoubleNumeric(value)) {
					}
				}
			}
		}
		hssfWorkbook.close();
		return importCount;
	}

	private int importXlsxEcShopProductPrice(InputStream is) throws Exception {
		int importCount = 0;
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			// 循环行Row
			InventoryCurrentStock inventoryCurrentStock = null;
			for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow == null || rowNum == 0) {
					continue;
				}
				// 循环列Cell
				for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
					XSSFCell xssfCell = xssfRow.getCell(cellNum);
					if (xssfCell == null) {
						continue;
					}
					String value = getXlsxValue(xssfCell);
					value = value.trim();
					if (cellNum == 0 && StringUtils.isNotEmpty(value)) {
						inventoryCurrentStock = takeStockDomain.findInventoryCurrentStockByAttribute(value);
					}
					if (cellNum == 3 && NumberUtil.isDoubleNumeric(value)) {
						if (inventoryCurrentStock != null) {
						}
					}
				}
			}
			if (inventoryCurrentStock != null) {
				inventoryCurrentStock = takeStockDomain.mergeInventoryCurrentStock(inventoryCurrentStock);
			}
		}
		xssfWorkbook.close();
		return importCount;
	}

	private String getXlsxValue(XSSFCell xssfCell) {
		if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			double d = xssfCell.getNumericCellValue();
			String s = NumberFormat.getInstance().format(d);
			return s.replaceAll(",", "");
		} else {
			xssfCell.setCellType(Cell.CELL_TYPE_STRING);
			return String.valueOf(xssfCell.getStringCellValue());
		}
	}

	private String getXlsValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			double d = hssfCell.getNumericCellValue();
			String s = NumberFormat.getInstance().format(d);
			return s.replaceAll(",", "");
		} else if (hssfCell.getCellType() == Cell.CELL_TYPE_STRING) {
			return String.valueOf(hssfCell.getStringCellValue());
		} else {
			hssfCell.setCellType(Cell.CELL_TYPE_STRING);
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

	public InvWarehouse doListInvWarehouseById(String id) throws Exception {
		InvWarehouse invWarehouse = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		invWarehouse = takeStockDomain.findInvWarehouseById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return invWarehouse;
	}

	public List<InvWarehouse> doListInvWarehouseList() throws Exception {
		List<InvWarehouse> invWarehouseList = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		invWarehouseList = takeStockDomain.findInvWarehouseList();
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		return invWarehouseList;
	}

	public List<InventoryCurrentStock> doListInventoryCurrentStock() throws Exception {
		List<InventoryCurrentStock> inventoryCurrentStockList = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		inventoryCurrentStockList = takeStockDomain.findInventoryCurrentStock();
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return inventoryCurrentStockList;
	}

	public List<InventoryCurrentStock> doListInventoryCurrentStockList(Map<String, Object> params) throws Exception {
		List<InventoryCurrentStock> inventoryCurrentStockList = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		inventoryCurrentStockList = takeStockDomain.findInventoryCurrentStockList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;

		return inventoryCurrentStockList;
	}

	public StockTakingItem doListStockTakingItemById(String id) throws Exception {
		StockTakingItem stockTakingItem = null;
		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");

		// 3.执行查询操作
		stockTakingItem = takeStockDomain.findStockTakingItemById(id);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return stockTakingItem;
	}

	public void deleteStockTakingItem(StockTakingItem stockTakingItem) throws Exception {
		takeStockDomain.deleteStockTakingItemEntity(stockTakingItem);
	}

	/**
	 * 
	 * 
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public List<StockTaking> doListStockTakingList(Map<String, Object> params) throws Exception {
		List<StockTaking> stockTaking = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		stockTaking = takeStockDomain.findStockTakingList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return stockTaking;
	}

	public List<StockTakingItem> doListStockTakingItemList(Map<String, Object> params) throws Exception {
		List<StockTakingItem> stockTakingItem = null;

		// 1. 处理传入参数 executeParameterProcess(); 抽象方法，需要重载；
		// Map parameters = baExecutor.executeParameterProcess();

		// 2. 触发事件 beforeEventSaveOrder(parameters); 抽象方法，需要重载；
		beforeEventTrigger("PO_CREATE_BEFORE");
		// 3. 执行查询操作
		stockTakingItem = takeStockDomain.findStockTakingItemList(params);
		// 4. 触发事件 afterEventSaveOrder(parameter); 抽象方法，需要重载；
		afterEventTrigger("PO_CREATE_AFTER");

		// 5. 访问功能和业务对象记录操作 visitBORecord(parameter);

		// 6. 日志处理 log(); 每个Action中独立定义;记录操作日志、记录性能数据;
		return stockTakingItem;
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
