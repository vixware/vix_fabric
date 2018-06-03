package com.vix.nvix.warehouse.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.security.util.SecurityUtil;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.warehouse.vo.SalesOrderItemVo;
import com.vix.nvix.warehouse.vo.StockRecordLinesVo;

/**
 * 
 * @类全名 com.vix.nvix.warehouse.action.VixntInventoryStatisticsAction
 *
 * @author zhanghaibing
 *
 * @date 2016年9月7日
 */
@Controller
@Scope("prototype")
public class VixntInventoryStatisticsAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String itemNames;
	// 入库数量
	private String itemQuantitys;
	private String itemNames2;
	// 入库金额
	private String itemPrices;
	//
	private String salesQuantityItemNames;
	private String itemSalesQuantitys;
	private String salesPriceItemNames;
	private String salesItemPrices;
	private String dateType;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("dateType", dateType);
			List<StockRecordLinesVo> taskStatisticsBoList = vixntBaseService.getStockRecordLinesVoList(params);
			if (taskStatisticsBoList != null && taskStatisticsBoList.size() > 0) {
				itemNames = "";
				itemQuantitys = "";
				for (StockRecordLinesVo stockRecordLinesVo : taskStatisticsBoList) {
					itemNames += "\"" + stockRecordLinesVo.getItemname() + "\"" + ",";
					itemQuantitys += stockRecordLinesVo.getQuantity() + ",";
				}
				itemNames = itemNames.substring(0, itemNames.length() - 1);
				itemQuantitys = itemQuantitys.substring(0, itemQuantitys.length() - 1);
			}
			Map<String, Object> params2 = new HashMap<String, Object>();
			params2.put("dateType", dateType);
			List<StockRecordLinesVo> taskStatisticsBoList2 = vixntBaseService.getStockRecordLinesPriceVoList(params2);
			if (taskStatisticsBoList2 != null && taskStatisticsBoList2.size() > 0) {
				itemNames2 = "";
				itemPrices = "";
				for (StockRecordLinesVo stockRecordLinesVo : taskStatisticsBoList2) {
					itemNames2 += "\"" + stockRecordLinesVo.getItemname() + "\"" + ",";
					itemPrices += stockRecordLinesVo.getPrice() + ",";
				}
				itemNames2 = itemNames2.substring(0, itemNames2.length() - 1);
				itemPrices = itemPrices.substring(0, itemPrices.length() - 1);
			}
			// 门店销售数量
			getSalesOrderItemQuantityList();
			// 门店销售金额
			getSalesOrderItemPriceList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	// 商品销售数量
	private void getSalesOrderItemQuantityList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("dateType", dateType);
			List<SalesOrderItemVo> salesOrderItemVoList = vixntBaseService.getSalesOrderItemNumVoList(params, SecurityUtil.getCurrentUserTenantId());
			if (salesOrderItemVoList != null && salesOrderItemVoList.size() > 0) {
				salesQuantityItemNames = "";
				itemSalesQuantitys = "";
				for (SalesOrderItemVo stockRecordLinesVo : salesOrderItemVoList) {
					salesQuantityItemNames += "\"" + stockRecordLinesVo.getItemname() + "\"" + ",";
					itemSalesQuantitys += stockRecordLinesVo.getQuantity() + ",";
				}
				salesQuantityItemNames = salesQuantityItemNames.substring(0, salesQuantityItemNames.length() - 1);
				itemSalesQuantitys = itemSalesQuantitys.substring(0, itemSalesQuantitys.length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 商品销售金额
	private void getSalesOrderItemPriceList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("dateType", dateType);
			List<SalesOrderItemVo> salesOrderItemVoList = vixntBaseService.getSalesOrderItemVoList(params, SecurityUtil.getCurrentUserTenantId());
			if (salesOrderItemVoList != null && salesOrderItemVoList.size() > 0) {
				salesPriceItemNames = "";
				salesItemPrices = "";
				for (SalesOrderItemVo stockRecordLinesVo : salesOrderItemVoList) {
					salesPriceItemNames += "\"" + stockRecordLinesVo.getItemname() + "\"" + ",";
					salesItemPrices += stockRecordLinesVo.getPrice() + ",";
				}
				salesPriceItemNames = salesPriceItemNames.substring(0, salesPriceItemNames.length() - 1);
				salesItemPrices = salesItemPrices.substring(0, salesItemPrices.length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getSalesQuantityItemNames() {
		return salesQuantityItemNames;
	}

	public void setSalesQuantityItemNames(String salesQuantityItemNames) {
		this.salesQuantityItemNames = salesQuantityItemNames;
	}

	public String getItemSalesQuantitys() {
		return itemSalesQuantitys;
	}

	public void setItemSalesQuantitys(String itemSalesQuantitys) {
		this.itemSalesQuantitys = itemSalesQuantitys;
	}

	public String getSalesPriceItemNames() {
		return salesPriceItemNames;
	}

	public void setSalesPriceItemNames(String salesPriceItemNames) {
		this.salesPriceItemNames = salesPriceItemNames;
	}

	public String getSalesItemPrices() {
		return salesItemPrices;
	}

	public void setSalesItemPrices(String salesItemPrices) {
		this.salesItemPrices = salesItemPrices;
	}

	public String getItemNames() {
		return itemNames;
	}

	public void setItemNames(String itemNames) {
		this.itemNames = itemNames;
	}

	public String getItemQuantitys() {
		return itemQuantitys;
	}

	public void setItemQuantitys(String itemQuantitys) {
		this.itemQuantitys = itemQuantitys;
	}

	public String getItemPrices() {
		return itemPrices;
	}

	public void setItemPrices(String itemPrices) {
		this.itemPrices = itemPrices;
	}

	public String getItemNames2() {
		return itemNames2;
	}

	public void setItemNames2(String itemNames2) {
		this.itemNames2 = itemNames2;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

}
