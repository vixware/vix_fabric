package com.vix.inventory.inventoryManagement.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.inventory.inbound.entity.StockRecords;
import com.vix.inventory.positionadjustment.entity.WimAdjustpvouch;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.takestock.entity.StockTaking;
import com.vix.inventory.transfer.entity.WimTransvouch;
import com.vix.system.remindsCenter.base.service.IRemindsCenterService;

@Controller
@Scope("prototype")
public class InventoryManagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IRemindsCenterService remindsCenterService;

	private Integer stockRecordsNum = 0;
	private Integer outStockRecordsNum = 0;
	private Integer stockTakingNum = 0;
	private Integer transvouchNum = 0;
	private Integer inventoryCurrentStockNum = 0;
	private Integer adjustpvouchNum = 0;
	private List<StockRecords> stockRecordsList;
	private List<StockRecords> outStockRecordsList;
	private List<StockTaking> stockTakingList;
	private List<InventoryCurrentStock> inventoryCurrentStockList;
	private List<WimTransvouch> transvouchList;
	private List<WimAdjustpvouch> wimAdjustpvouchList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			//获取入库单数量
			Map<String, Object> params = new HashMap<String, Object>();
			// flag 1: 过滤入库单
			params.put("flag," + SearchCondition.ANYLIKE, "1");
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			stockRecordsList = remindsCenterService.findAllByConditions(StockRecords.class, params);
			if (stockRecordsList != null && stockRecordsList.size() > 0) {
				stockRecordsNum = stockRecordsList.size();
			}

			//获取出库单数量
			Map<String, Object> outparams = getParams();
			// 过滤出库单flag 出入库标志
			outparams.put("flag," + SearchCondition.ANYLIKE, "2");
			// 过滤临时数据
			outparams.put("isTemp," + SearchCondition.NOEQUAL, "1");
			outStockRecordsList = remindsCenterService.findAllByConditions(StockRecords.class, outparams);
			if (outStockRecordsList != null && outStockRecordsList.size() > 0) {
				outStockRecordsNum = outStockRecordsList.size();
			}

			//获取盘点单数量
			Map<String, Object> stockTakingParams = getParams();
			stockTakingParams.put("isTemp," + SearchCondition.NOEQUAL, "1");
			stockTakingList = remindsCenterService.findAllByConditions(StockTaking.class, stockTakingParams);
			if (stockTakingList != null && stockTakingList.size() > 0) {
				stockTakingNum = stockTakingList.size();
			}
			//获取调拨单数量
			Map<String, Object> transvouchParams = getParams();
			transvouchParams.put("isTemp," + SearchCondition.NOEQUAL, "1");
			transvouchList = remindsCenterService.findAllByConditions(WimTransvouch.class, transvouchParams);
			if (transvouchList != null && transvouchList.size() > 0) {
				transvouchNum = transvouchList.size();
			}
			//获取台账
			Map<String, Object> inventoryCurrentStockParams = getParams();
			inventoryCurrentStockParams.put("isQualfied," + SearchCondition.EQUAL, 1);
			inventoryCurrentStockList = remindsCenterService.findAllByConditions(InventoryCurrentStock.class, inventoryCurrentStockParams);
			if (inventoryCurrentStockList != null && inventoryCurrentStockList.size() > 0) {
				inventoryCurrentStockNum = inventoryCurrentStockList.size();
			}
			//获取货位调整单数量
			Map<String, Object> adjustpvouchParams = getParams();
			adjustpvouchParams.put("isTemp," + SearchCondition.NOEQUAL, "1");
			wimAdjustpvouchList = remindsCenterService.findAllByConditions(WimAdjustpvouch.class, adjustpvouchParams);
			if (wimAdjustpvouchList != null && wimAdjustpvouchList.size() > 0) {
				adjustpvouchNum = wimAdjustpvouchList.size();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * @return the stockRecordsNum
	 */
	public Integer getStockRecordsNum() {
		return stockRecordsNum;
	}

	/**
	 * @param stockRecordsNum
	 *            the stockRecordsNum to set
	 */
	public void setStockRecordsNum(Integer stockRecordsNum) {
		this.stockRecordsNum = stockRecordsNum;
	}

	/**
	 * @return the outStockRecordsNum
	 */
	public Integer getOutStockRecordsNum() {
		return outStockRecordsNum;
	}

	/**
	 * @param outStockRecordsNum
	 *            the outStockRecordsNum to set
	 */
	public void setOutStockRecordsNum(Integer outStockRecordsNum) {
		this.outStockRecordsNum = outStockRecordsNum;
	}

	/**
	 * @return the stockTakingNum
	 */
	public Integer getStockTakingNum() {
		return stockTakingNum;
	}

	/**
	 * @param stockTakingNum
	 *            the stockTakingNum to set
	 */
	public void setStockTakingNum(Integer stockTakingNum) {
		this.stockTakingNum = stockTakingNum;
	}

	/**
	 * @return the transvouchNum
	 */
	public Integer getTransvouchNum() {
		return transvouchNum;
	}

	/**
	 * @param transvouchNum
	 *            the transvouchNum to set
	 */
	public void setTransvouchNum(Integer transvouchNum) {
		this.transvouchNum = transvouchNum;
	}

	/**
	 * @return the inventoryCurrentStockNum
	 */
	public Integer getInventoryCurrentStockNum() {
		return inventoryCurrentStockNum;
	}

	/**
	 * @param inventoryCurrentStockNum
	 *            the inventoryCurrentStockNum to set
	 */
	public void setInventoryCurrentStockNum(Integer inventoryCurrentStockNum) {
		this.inventoryCurrentStockNum = inventoryCurrentStockNum;
	}

	/**
	 * @return the adjustpvouchNum
	 */
	public Integer getAdjustpvouchNum() {
		return adjustpvouchNum;
	}

	/**
	 * @param adjustpvouchNum
	 *            the adjustpvouchNum to set
	 */
	public void setAdjustpvouchNum(Integer adjustpvouchNum) {
		this.adjustpvouchNum = adjustpvouchNum;
	}

	/**
	 * @return the stockRecordsList
	 */
	public List<StockRecords> getStockRecordsList() {
		return stockRecordsList;
	}

	/**
	 * @param stockRecordsList
	 *            the stockRecordsList to set
	 */
	public void setStockRecordsList(List<StockRecords> stockRecordsList) {
		this.stockRecordsList = stockRecordsList;
	}

	/**
	 * @return the outStockRecordsList
	 */
	public List<StockRecords> getOutStockRecordsList() {
		return outStockRecordsList;
	}

	/**
	 * @param outStockRecordsList
	 *            the outStockRecordsList to set
	 */
	public void setOutStockRecordsList(List<StockRecords> outStockRecordsList) {
		this.outStockRecordsList = outStockRecordsList;
	}

	/**
	 * @return the stockTakingList
	 */
	public List<StockTaking> getStockTakingList() {
		return stockTakingList;
	}

	/**
	 * @param stockTakingList
	 *            the stockTakingList to set
	 */
	public void setStockTakingList(List<StockTaking> stockTakingList) {
		this.stockTakingList = stockTakingList;
	}

	/**
	 * @return the inventoryCurrentStockList
	 */
	public List<InventoryCurrentStock> getInventoryCurrentStockList() {
		return inventoryCurrentStockList;
	}

	/**
	 * @param inventoryCurrentStockList
	 *            the inventoryCurrentStockList to set
	 */
	public void setInventoryCurrentStockList(List<InventoryCurrentStock> inventoryCurrentStockList) {
		this.inventoryCurrentStockList = inventoryCurrentStockList;
	}

	/**
	 * @return the transvouchList
	 */
	public List<WimTransvouch> getTransvouchList() {
		return transvouchList;
	}

	/**
	 * @param transvouchList
	 *            the transvouchList to set
	 */
	public void setTransvouchList(List<WimTransvouch> transvouchList) {
		this.transvouchList = transvouchList;
	}

	/**
	 * @return the wimAdjustpvouchList
	 */
	public List<WimAdjustpvouch> getWimAdjustpvouchList() {
		return wimAdjustpvouchList;
	}

	/**
	 * @param wimAdjustpvouchList
	 *            the wimAdjustpvouchList to set
	 */
	public void setWimAdjustpvouchList(List<WimAdjustpvouch> wimAdjustpvouchList) {
		this.wimAdjustpvouchList = wimAdjustpvouchList;
	}
}
