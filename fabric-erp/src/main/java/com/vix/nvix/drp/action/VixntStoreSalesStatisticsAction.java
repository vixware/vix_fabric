package com.vix.nvix.drp.action;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.security.util.SecurityUtil;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.warehouse.vo.CustomerSalesVo;
import com.vix.nvix.warehouse.vo.SalesDataVo;
import com.vix.nvix.warehouse.vo.SalesOrderItemDay;
import com.vix.nvix.warehouse.vo.SalesOrderItemVo;

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
public class VixntStoreSalesStatisticsAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String itemNames;
	private String itemPrices;

	private String dayItemNames;
	private String dayItemPrices;

	private String customerNames;
	private String customerPrices;

	private String salesDataNames;
	private String salesDataPrices;
	private String salesDataDate;
	SimpleDateFormat sdf = new SimpleDateFormat("mm/dd");
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm");
	private String dateType;

	@Override
	public String goList() {
		getSalesOrderItemList();
		getSalesOrderItemDayList();
		getCustomerSalesList();
		return GO_LIST;
	}

	public String goAllSalesList() {
		getSalesDataVoList();
		return "goAllSalesList";
	}

	/**
	 * @throws Exception
	 */
	private void getSalesOrderItemList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("dateType", dateType);
			List<SalesOrderItemVo> salesOrderItemVoList = vixntBaseService.getSalesOrderItemVoList(params, SecurityUtil.getCurrentUserTenantId());
			if (salesOrderItemVoList != null && salesOrderItemVoList.size() > 0) {
				itemNames = "";
				itemPrices = "";
				for (SalesOrderItemVo stockRecordLinesVo : salesOrderItemVoList) {
					itemNames += "\"" + stockRecordLinesVo.getItemname() + "\"" + ",";
					itemPrices += stockRecordLinesVo.getPrice() + ",";
				}
				itemNames = itemNames.substring(0, itemNames.length() - 1);
				itemPrices = itemPrices.substring(0, itemPrices.length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getCustomerSalesList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			List<CustomerSalesVo> customerSalesVoList = vixntBaseService.getCustomerSalesVoList(params);
			if (customerSalesVoList != null && customerSalesVoList.size() > 0) {
				customerNames = "";
				customerPrices = "";
				for (CustomerSalesVo customerSalesVo : customerSalesVoList) {
					customerNames += "\"" + customerSalesVo.getCustomerName() + "\"" + ",";
					customerPrices += customerSalesVo.getPrice() + ",";
				}
				customerNames = customerNames.substring(0, customerNames.length() - 1);
				customerPrices = customerPrices.substring(0, customerPrices.length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 */
	private void getSalesOrderItemDayList() {
		try {
			Map<String, Object> p = new HashMap<String, Object>();
			List<SalesOrderItemDay> salesOrderItemDayList = vixntBaseService.getSalesOrderItemDayList(p);
			if (salesOrderItemDayList != null && salesOrderItemDayList.size() > 0) {
				dayItemNames = "";
				dayItemPrices = "";
				for (SalesOrderItemDay stockRecordLinesVo : salesOrderItemDayList) {
					dayItemNames += "\"" + sdf.format(stockRecordLinesVo.getCreateTime()) + "\"" + ",";
					dayItemPrices += stockRecordLinesVo.getPrice() + ",";
				}
				dayItemNames = dayItemNames.substring(0, dayItemNames.length() - 1);
				dayItemPrices = dayItemPrices.substring(0, dayItemPrices.length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getSalesDataVoList() {
		try {
			Map<String, Object> p = new HashMap<String, Object>();
			List<SalesDataVo> salesDataVoList = vixntBaseService.getSalesDataVoList(p);
			if (salesDataVoList != null && salesDataVoList.size() > 0) {
				salesDataNames = "";
				salesDataDate = "";
				salesDataPrices = "";
				for (SalesDataVo salesDataVo : salesDataVoList) {
					salesDataNames += "\"" + salesDataVo.getShopname() + "\"" + ",";
					salesDataDate += "\"" + simpleDateFormat.format(salesDataVo.getCreateTime()) + "\"" + ",";
					salesDataPrices += salesDataVo.getPrice() + ",";
				}
				salesDataNames = salesDataNames.substring(0, salesDataNames.length() - 1);
				salesDataDate = salesDataDate.substring(0, salesDataDate.length() - 1);
				salesDataPrices = salesDataPrices.substring(0, salesDataPrices.length() - 1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getItemNames() {
		return itemNames;
	}

	public void setItemNames(String itemNames) {
		this.itemNames = itemNames;
	}

	public String getItemPrices() {
		return itemPrices;
	}

	public void setItemPrices(String itemPrices) {
		this.itemPrices = itemPrices;
	}

	public String getDayItemNames() {
		return dayItemNames;
	}

	public void setDayItemNames(String dayItemNames) {
		this.dayItemNames = dayItemNames;
	}

	public String getDayItemPrices() {
		return dayItemPrices;
	}

	public void setDayItemPrices(String dayItemPrices) {
		this.dayItemPrices = dayItemPrices;
	}

	public String getCustomerNames() {
		return customerNames;
	}

	public void setCustomerNames(String customerNames) {
		this.customerNames = customerNames;
	}

	public String getCustomerPrices() {
		return customerPrices;
	}

	public void setCustomerPrices(String customerPrices) {
		this.customerPrices = customerPrices;
	}

	public String getSalesDataNames() {
		return salesDataNames;
	}

	public void setSalesDataNames(String salesDataNames) {
		this.salesDataNames = salesDataNames;
	}

	public String getSalesDataPrices() {
		return salesDataPrices;
	}

	public void setSalesDataPrices(String salesDataPrices) {
		this.salesDataPrices = salesDataPrices;
	}

	public String getSalesDataDate() {
		return salesDataDate;
	}

	public void setSalesDataDate(String salesDataDate) {
		this.salesDataDate = salesDataDate;
	}

	public String getDateType() {
		return dateType;
	}

	public void setDateType(String dateType) {
		this.dateType = dateType;
	}

}
