package com.vix.drp.salesStorage.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.salesStorage.controller.SalesStorageController;
import com.vix.inventory.inbound.entity.StockRecordLines;
import com.vix.inventory.inbound.entity.StockRecords;

@Controller
@Scope("prototype")
public class SalesStorageAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private SalesStorageController salesStorageController;
	private String id;
	/**
	 * 销售入库单
	 */
	private StockRecords stockRecords;
	private List<StockRecords> stockRecordsList;
	/**
	 * 销售入库单明细
	 */
	private StockRecordLines stockRecordLines;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// flag 1: 过滤入库单
			params.put("flag," + SearchCondition.ANYLIKE, "1");
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");

			Pager pager = salesStorageController.findStockRecordsPager(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = salesStorageController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setIsTemp("1");
				stockRecords.setCode(VixUUID.createCode(10));
				stockRecords = salesStorageController.doSaveStockRecords(stockRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != stockRecords.getId() && !"".equals(stockRecords.getId())) {
				isSave = false;
			}
			stockRecords.setIsTemp("");
			/*
			 * flag 出入库标志 1：入库 ，2：出库
			 */
			stockRecords.setFlag("1");
			stockRecords = salesStorageController.doSaveStockRecords(stockRecords);
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
	public String deleteById() {
		try {
			StockRecords stockRecords = salesStorageController.doListStockRecordsById(id);
			if (null != stockRecords) {
				salesStorageController.doDeleteSalesOrder(stockRecords);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public void getStockRecordLinesJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				StockRecords stockRecords = salesStorageController.doListStockRecordsById(id);
				if (null != stockRecords) {
					json = convertListToJson(new ArrayList<StockRecordLines>(stockRecords.getSubStockRecordLines()), stockRecords.getSubStockRecordLines().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdateStockRecordLines() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecordLines = salesStorageController.doListStockRecordLinesById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateStockRecordLines";
	}

	public String saveOrUpdateStockRecordLines() {
		boolean isSave = true;
		try {
			if (null != stockRecordLines.getId() && !"".equals(stockRecordLines.getId())) {
				isSave = false;
			}
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				StockRecords stockRecords = salesStorageController.doListStockRecordsById(id);
				if (null != stockRecords) {
					stockRecordLines.setStockRecords(stockRecords);
				}
			}
			stockRecordLines = salesStorageController.doSaveStockRecordLines(stockRecordLines);
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

	public String deleteStockRecordLinesById() {
		try {
			StockRecordLines stockRecordLines = salesStorageController.doListStockRecordLinesById(id);
			if (null != stockRecordLines) {
				salesStorageController.doDeleteSaleOrderItemByEntity(stockRecordLines);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public StockRecords getStockRecords() {
		return stockRecords;
	}

	public void setStockRecords(StockRecords stockRecords) {
		this.stockRecords = stockRecords;
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

}
