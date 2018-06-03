package com.vix.inventory.outbound.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.vix.common.base.action.BaseAction;
import com.vix.common.billtype.BillType;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.util.DaysUtils;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.inbound.entity.StockRecordLines;
import com.vix.inventory.inbound.entity.StockRecords;
import com.vix.inventory.option.entity.InventoryParameters;
import com.vix.inventory.outbound.controller.OutBoundController;
import com.vix.mdm.item.service.IItemService;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;
import com.vix.system.billTypeManagement.entity.BillsProperty;

import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class OutBoundAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(OutBoundAction.class);
	@Autowired
	private OrderProcessController orderProcessController;
	@Autowired
	private OutBoundController outBoundController;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private String id;
	private String ids;
	private String taskId;
	private String pageNo;
	private StockRecords stockRecords;
	private List<StockRecords> stockRecordsList;

	private String categoryId;
	private String masterInventoryCurrentStockId;
	/**
	 * 销售订单
	 */
	private SalesOrder salesOrder;
	/**
	 * 销售订单明细
	 */
	private SaleOrderItem saleOrderItem;
	private List<SaleOrderItem> saleOrderItemList;
	private List<SalesOrder> salesOrderList;
	/**
	 * 出库单明细
	 */
	private StockRecordLines stockRecordLines;
	private InventoryParameters inventoryParameters;
	private List<StockRecordLines> stockRecordLinesList;
	private String purchaseOrderid;
	private String isAllowAudit;// 是否允许提交审批 1：是 0：否
	@Autowired
	private IItemService itemService;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤出库单flag 出入库标志
			params.put("flag," + SearchCondition.ANYLIKE, "2");
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");

			// 根据状态显示
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLastUsedTime(days));
			}
			// 获取当前员工信息
			Employee employee = getEmployee();
			if (employee != null && employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
				// 如果登录的员工属于经销商或门店
				params.put("channelDistributor.id," + SearchCondition.ANYLIKE, employee.getChannelDistributor().getId());
			}
			stockRecordsList = outBoundController.doListStockRecordsList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 倒序排序
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			// 过滤出库单flag 出入库标志
			params.put("flag," + SearchCondition.ANYLIKE, "2");
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");

			String content = getRequestParameter("content");
			String code = getRequestParameter("code");
			String name = getDecodeRequestParameter("name");
			String warehousename = getDecodeRequestParameter("warehousename");
			String createTime = getRequestParameter("createTime");
			String warehousePerson = getDecodeRequestParameter("warehousePerson");
			// 根据状态显示
			String status = getRequestParameter("status");
			// 最近使用
			String days = getRequestParameter("days");
			dealParams(params, pager, content, code, name, warehousename, createTime, warehousePerson, status, days);
			// 获取当前员工信息
			Employee employee = getEmployee();
			if (employee != null && employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
				// 如果登录的员工属于经销商或门店
				params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
			}
			// 搜索加强
			Map<String, Object> stockRecordLinesparams = new HashMap<String, Object>();
			// 根据商品信息获取订单ID
			String itemname = getDecodeRequestParameter("itemname");
			String itemcode = getDecodeRequestParameter("itemcode");
			searchParams(params, stockRecordLinesparams, itemname, itemcode);
			// 搜索加强

			pager = outBoundController.doListStockrecords(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * @param params
	 * @param pager
	 * @param content
	 * @param code
	 * @param name
	 * @param warehousename
	 * @param createTime
	 * @param warehousePerson
	 * @param status
	 * @param days
	 * @throws ParseException
	 */
	private void dealParams(Map<String, Object> params, Pager pager, String content, String code, String name, String warehousename, String createTime, String warehousePerson, String status, String days) throws ParseException {
		if (content != null && !"".equals(content)) {
			params.put("code," + SearchCondition.ANYLIKE, content);
		}
		if (code != null && !"".equals(code)) {
			params.put("code," + SearchCondition.EQUAL, code);
		}
		if (name != null && !"".equals(name)) {
			params.put("name," + SearchCondition.ANYLIKE, name);
		}
		if (null != pageNo && !"".equals(pageNo) && !"undefined".equals(pageNo)) {
			pager.setPageNo(Integer.parseInt(pageNo));
		}
		if (warehousename != null && !"".equals(warehousename)) {
			params.put("invWarehouse.name," + SearchCondition.ANYLIKE, warehousename);
		}
		if (createTime != null && !"".equals(createTime)) {
			params.put("createTime," + SearchCondition.ANYLIKE, createTime);
		}
		if (warehousePerson != null && !"".equals(warehousePerson)) {
			params.put("warehousePerson," + SearchCondition.ANYLIKE, warehousePerson);
		}
		if (status != null && !"".equals(status)) {
			params.put("status," + SearchCondition.ANYLIKE, status);
		}
		if (days != null && !"".equals(days)) {
			params.put("updateTime," + SearchCondition.MORETHAN, getLastUsedTime(days));
		}
	}

	/**
	 * @param params
	 * @param stockRecordLinesparams
	 * @param itemname
	 * @param itemcode
	 * @throws Exception
	 */
	private void searchParams(Map<String, Object> params, Map<String, Object> stockRecordLinesparams, String itemname, String itemcode) throws Exception {
		String stockRecordsIds = "";
		if (StringUtils.isNotEmpty(itemname)) {
			stockRecordLinesparams.put("itemname," + SearchCondition.ANYLIKE, itemname);
		}
		if (StringUtils.isNotEmpty(itemcode)) {
			stockRecordLinesparams.put("itemcode," + SearchCondition.ANYLIKE, itemcode);
		}
		List<StockRecordLines> stockRecordLinesList = outBoundController.doListStockRecordLinesList(stockRecordLinesparams);
		if (stockRecordLinesList != null && stockRecordLinesList.size() > 0) {
			for (StockRecordLines stockRecordLines : stockRecordLinesList) {
				if (stockRecordLines != null && stockRecordLines.getStockRecords() != null) {
					stockRecordsIds += stockRecordLines.getStockRecords().getId() + ",";
				}
			}
		}
		if (StringUtils.isNotEmpty(stockRecordsIds)) {
			params.put("id," + SearchCondition.IN, stockRecordsIds);
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = outBoundController.doListStockRecordsById(id);
				logger.info("");
			} else {
				stockRecords = new StockRecords();
				stockRecords.setIsTemp("1");
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_OUTBOUND));
				stockRecords = outBoundController.doSaveOrUpdateStockRecords(stockRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(stockRecords.getId()) && !"0".equals(stockRecords.getId())) {
				isSave = false;
			}
			stockRecords.setIsTemp("");
			/** 出库明细 */
			String biztype = getRequestParameter("biztype");
			// 获取当前登录用户所在的公司或供应商
			Employee employee = getEmployee();
			// 获取当前员工信息
			if (employee != null && employee.getChannelDistributor() != null) {
				// 如果登录的员工属于经销商或门店
				ChannelDistributor channelDistributor = employee.getChannelDistributor();
				stockRecords.setChannelDistributor(channelDistributor);
			}
			// biztype 业务类型 1 ：销售出库, 2 ：材料出库, 3：其他出库单
			stockRecords.setBiztype(biztype);
			// status 审核状态0：未提交 ,1：待审核, 2：审核通过
			// 新建的入库单默认为未提交状态
			stockRecords.setStatus("0");
			// flag 出入库标志 1：入库 ，2：出库
			stockRecords.setFlag("2");
			initEntityBaseController.initEntityBaseAttribute(stockRecords);
			if (stockRecords != null && StringUtils.isNotEmpty(updateField)) {
				// 调用修改留痕
				billMarkProcessController.processMark(stockRecords, updateField);
			}
			stockRecords = outBoundController.doSaveOrUpdateStockRecords(stockRecords);

			String tag = getRequestParameter("tag");
			if (tag != null && "1".equals(tag)) {
				dealStartAndSubmitByBillsCode();
			}

			// 更改采购订单的状态
			if (purchaseOrderid != null && !"".equals(purchaseOrderid)) {
				updatePurchaseOrderStatus();
			}
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
	 * @throws Exception
	 */
	private void updatePurchaseOrderStatus() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id," + SearchCondition.IN, purchaseOrderid);
		List<SalesOrder> salesOrderList = outBoundController.doListSalesOrderList(params);
		if (salesOrderList != null && salesOrderList.size() > 0) {
			for (SalesOrder salesOrder : salesOrderList) {
				if (salesOrder != null) {
					// 修改采购订单的状态
					salesOrder.setStatus("已完成");
					outBoundController.doSaveSalesOrder(salesOrder);
				}
			}
		}
	}

	/**
	 * @throws Exception
	 * @throws UnsupportedEncodingException
	 */
	private void dealStartAndSubmitByBillsCode() throws Exception, UnsupportedEncodingException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("propertyCode," + SearchCondition.EQUAL, BillType.INV_INBOUND);
		params.put("tenantId," + SearchCondition.EQUAL, stockRecords.getTenantId());
		List<BillsProperty> billsPropertyList = itemService.findAllByConditions(BillsProperty.class, params);
		if (billsPropertyList != null && billsPropertyList.size() > 0) {
			for (BillsProperty billsProperty : billsPropertyList) {

				// VixRestTemplate restTemplate = new VixRestTemplate();
				// Map<String, Object> urlVariables = new HashMap<String,
				// Object>();
				String billsPropertyId = billsProperty.getId();
				String billTypeCode = BillType.INV_OUTBOUND;
				String billTitle = stockRecords.getName();
				String billId = stockRecords.getId();
				// urlVariables.put("billsPropertyId", billsPropertyId);
				// urlVariables.put("billTypeCode", billTypeCode);
				// urlVariables.put("billTitle", URLEncoder.encode(billTitle,
				// "utf-8"));
				// urlVariables.put("billId", billId);

				// String temp =
				// restTemplate.postForObject("http://localhost:8080/vform/activiti/deployment/startAndSubmitByBillsCode?billsPropertyId="
				// + billsPropertyId + "&billTypeCode=" + billTypeCode +
				// "&billId=" + billId + "&billTitle=" + billTitle + "&userId="
				// + user.getId(), String.class, String.class, urlVariables);
				// System.out.println(temp);

				MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
				formData.add("billsPropertyId", billsPropertyId);
				formData.add("billTypeCode", billTypeCode);
				formData.add("billTitle", URLEncoder.encode(billTitle, "utf-8"));
				formData.add("billId", billId);
				Object objUser = getSession().getAttribute("userInfo");
				UserAccount user = null;
				if (null != objUser && objUser instanceof UserAccount) {
					user = (UserAccount) objUser;
					// urlVariables.put("userId", user.getId());
					formData.add("userId", user.getId());
				}
				// HttpHeaders requestHeaders = createHttpHeader();

				// requestHeaders.setContentType(org.springframework.http.MediaType.MULTIPART_FORM_DATA);

				// HttpEntity<MultiValueMap<String, Object>> requestEntity = new
				// HttpEntity<MultiValueMap<String, Object>>(formData,
				// requestHeaders);
				String url = "http://localhost:8080/vform/activiti/deployment/startAndSubmitByBillsCode";
				String response = postData(formData, url);
				// ResponseEntity<String> response =
				// restTemplate.exchange("http://localhost:8080/vform/activiti/deployment/startAndSubmitByBillsCode",
				// HttpMethod.POST, requestEntity, String.class, formData);
				// System.out.println(response.getBody());
				JSONObject json = JSONObject.fromObject(response);
				if (json.has("status")) {
					if ("1".equals(json.getString("status"))) {
						stockRecords.setStatus("1");
						stockRecords = outBoundController.doSaveOrUpdateStockRecords(stockRecords);
						break;
					}
				}
			}
		}
	}

	public String postData(MultiValueMap<String, Object> formData, String url) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Accept-Charset", "utf-8");
		requestHeaders.setContentType(org.springframework.http.MediaType.MULTIPART_FORM_DATA);
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(formData, requestHeaders);
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class, formData);
		return response.getBody();
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			StockRecords pb = outBoundController.doListStockRecordsById(id);
			if (null != pb) {
				outBoundController.doDeleteStockRecords(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 获取明细的json数据 */

	public void getDeliveryAddress() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				StockRecords so = outBoundController.doListStockRecordsById(id);
				if (so != null) {
					json = convertListToJson(new ArrayList<StockRecordLines>(so.getSubStockRecordLines()), so.getSubStockRecordLines().size());
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
			if (StringUtils.isNotEmpty(ids) && !"0".equals(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				outBoundController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 跳转至新增物料出库单 */
	public String goMaterialOutBound() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = outBoundController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setIsTemp("1");
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_OUTBOUND));
				stockRecords = outBoundController.doSaveOrUpdateStockRecords(stockRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMaterialOutBound";
	}

	/** 跳转至新增其他出库单 */
	public String goOtherOutBound() {
		try {
			isAllowAudit = isAllowAudit(BillType.INV_OUTBOUND);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = outBoundController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setIsTemp("1");
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_OUTBOUND));
				stockRecords = outBoundController.doSaveOrUpdateStockRecords(stockRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goOtherOutBound";
	}

	/** 跳转至新增网店出库单 */
	public String goBusinessOrder() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = outBoundController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setIsTemp("1");
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_OUTBOUND));
				stockRecords = outBoundController.doSaveOrUpdateStockRecords(stockRecords);
				businessOrderToStockrecords(stockRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goBusinessOrder";
	}

	/**
	 * 增加出库单明细
	 * 
	 * @return
	 */
	public String saveOrUpdateWimStockRecordLines() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				isSave = false;
				stockRecords = outBoundController.doListStockRecordsById(id);
				if (stockRecords != null) {
					stockRecordLines.setStockRecords(stockRecords);
				}
				stockRecordLines.setFlag("2");
				// 计算金额 ：金额 = 单价 * 数量
				Double price = null;
				if (stockRecordLines.getQuantity() != null && !"".equals(stockRecordLines.getQuantity()) && stockRecordLines.getUnitcost() != null && !"".equals(stockRecordLines.getUnitcost())) {
					price = stockRecordLines.getQuantity() * stockRecordLines.getUnitcost();
					if (price != null) {
						stockRecordLines.setPrice(price);
					}
				}
				initEntityBaseController.initEntityBaseAttribute(stockRecordLines);
				outBoundController.doSaveStockRecordLines(stockRecordLines);
			}
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

	/** 子项价格汇总 */
	public void getOrderItemTotal() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				StockRecords stockRecords = outBoundController.doListStockRecordsById(id);
				if (null != stockRecords) {
					Double totalAmount = 0d;
					for (StockRecordLines stockRecordLines : stockRecords.getSubStockRecordLines()) {
						totalAmount += stockRecordLines.getUnitcost() * stockRecordLines.getQuantity();
					}
					DecimalFormat df = new DecimalFormat(".##");
					String st = df.format(totalAmount);
					renderJson(st);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getOrderItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				StockRecords stockRecords = outBoundController.doListStockRecordsById(id);
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

	/** 处理删除操作 */
	public String deleteWimStockrecordlinesById() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				StockRecordLines stockRecordLines = outBoundController.doListStockRecordLinesById(id);
				if (null != stockRecordLines) {
					outBoundController.deleteStockRecordLines(stockRecordLines);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	// 跳转到添加物料页面
	public String goAddSaleOrderItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecordLines = itemService.findEntityById(StockRecordLines.class, id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goAddSaleOrderItem";
	}

	private Date getLastUsedTime(String days) throws ParseException {

		if ("THREEDAYS".equals(days)) {
			return dateFormat.parse(DaysUtils.getThreeDays());
		} else if ("SEVENDAYS".equals(days)) {
			return dateFormat.parse(DaysUtils.getSevenDays());
		} else if ("ONEMONTH".equals(days)) {
			return dateFormat.parse(DaysUtils.getOneMonth());
		} else {
			return dateFormat.parse(DaysUtils.getThreeMonths());
		}
	}

	// 跳转到选择销售订单页面
	public String goChooseSalesOrder() {
		return "goChooseSalesOrder";
	}

	/** 获取销售订单列表数据 */
	public String goSalesOrderList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 倒序排序
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			// 按状态
			params.put("status," + SearchCondition.NOEQUAL, "已完成");
			// 按最近使用
			pager = outBoundController.goPurchaseOrder(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSalesOrderList";
	}

	public String getSalesOrderItemList() {
		try {
			if (StringUtils.isNotEmpty(purchaseOrderid) && !"0".equals(purchaseOrderid)) {
				salesOrder = outBoundController.findSalesOrder(purchaseOrderid);
				Set<SaleOrderItem> saleOrderItems = salesOrder.getSaleOrderItems();
				if (saleOrderItems.size() > 0) {
					saleOrderItemList = new ArrayList<SaleOrderItem>(saleOrderItems);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "getSalesOrderItemList";
	}

	/**
	 * 将销售订单明细转化成采购入库单明细
	 * 
	 * @return
	 */
	public String converterSalesOrderToStockrecords() {
		try {
			stockRecords = outBoundController.doListStockRecordsById(id);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id," + SearchCondition.IN, purchaseOrderid);
			List<SalesOrder> salesOrderList = outBoundController.doListSalesOrderList(params);
			if (salesOrderList != null && salesOrderList.size() > 0) {
				for (SalesOrder salesOrder : salesOrderList) {
					if (salesOrder != null) {
						outBoundController.convPurchaseOrderToStockrecords(stockRecords, salesOrder);
						renderText(SAVE_SUCCESS);
					}
				}
			}
		} catch (Exception e) {
			renderText(SAVE_FAIL);
			e.printStackTrace();
		}
		return UPDATE;
	}

	/**
	 * 将网店订单转化成出库单
	 * 
	 * @return
	 */
	public String businessOrderToStockrecords(StockRecords wimStockrecords) {
		String businussOrderIds = getRequestParameter("businussOrderId");
		if (businussOrderIds != null && !"".equals(businussOrderIds)) {
			String[] businussOrderIdArr = businussOrderIds.split(",");
			if (businussOrderIdArr != null && !"".equals(businussOrderIdArr) && businussOrderIdArr.length > 0) {
				for (String businussOrderId : businussOrderIdArr) {
					if (businussOrderId != null && !"".equals(businussOrderId)) {
						// 根据id查询网店订单
						try {
							Order order = outBoundController.findOrder(businussOrderId);
							if (order != null) {
								outBoundController.businessOrderToStockrecords(wimStockrecords, order);
								renderText(SAVE_SUCCESS);
							}
						} catch (Exception e) {
							renderText(SAVE_FAIL);
							e.printStackTrace();
						}
					}
				}
			}
		}
		return UPDATE;
	}

	/**
	 * @return
	 */
	public String goSaveOrUpdateStockRecordLines() {
		try {
			// 查询是否开启保质期管理
			inventoryParameters = outBoundController.doListInventoryParametersByAttribute("tenantId", SecurityUtil.getCurrentUserTenantId());
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecordLines = itemService.findEntityById(StockRecordLines.class, id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goSaveOrUpdateStockRecordLines";
	}

	public String goListItemList() {
		try {
			Map<String, Object> params = getParams();
			// 倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			if (StringUtils.isNotEmpty(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager.setPageSize(7);
			pager = outBoundController.doListMasterInventoryCurrentStockPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListItemList";
	}

	public String goInventoryCurrentStockList() {
		return "goInventoryCurrentStockList";
	}

	public String goInventoryCurrentStockListContent() {
		try {
			Map<String, Object> params = getParams();
			// 倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager.setPageSize(5);
			params.put("masterInventoryCurrentStock.id," + SearchCondition.EQUAL, masterInventoryCurrentStockId);
			pager = outBoundController.doListInventoryCurrentStockPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goInventoryCurrentStockListContent";
	}

	public String goPrintStockRecords() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = outBoundController.doListStockRecordsById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintStockRecords";
	}

	/**
	 * 
	 * @return
	 */
	public String goShowOutBound() {

		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = outBoundController.doListStockRecordsById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowOutBound";
	}

	/**
	 * 查看销售订单源
	 * 
	 * @return
	 */
	public String goShowSalesOrder() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = outBoundController.doListStockRecordsById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowSalesOrder";
	}

	public String goListSalesOrderContent() {
		try {
			Map<String, Object> params = getParams();
			salesOrderList = outBoundController.doListSalesOrderList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListSalesOrderContent";
	}

	/**
	 * 获取上一条数据
	 * 
	 * @return
	 */
	public String goBeforeStockRecords() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				stockRecords = outBoundController.doListStockRecordsById(id);
				if (stockRecords != null && stockRecords.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(stockRecords.getCreateTime()));
					// flag 2: 过滤
					params.put("flag", "2");
					// 过滤临时数据
					params.put("isTemp", "");
					params.put("tenantId", stockRecords.getTenantId());
					params.put("companyInnerCode", stockRecords.getCompanyInnerCode());
					params.put("creator", stockRecords.getCreator());
					stockRecords = (StockRecords) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(stockRecords.getCreateTime()), params, stockRecords, "before");
					if (stockRecords == null || StringUtils.isEmpty(stockRecords.getId())) {
						stockRecords = outBoundController.doListStockRecordsById(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowOutBound";
	}

	/**
	 * 
	 * @return
	 */
	public String goAfterStockRecords() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				stockRecords = outBoundController.doListStockRecordsById(id);
				if (stockRecords != null && stockRecords.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(stockRecords.getCreateTime()));
					params.put("flag", "2");
					params.put("isTemp", "");
					params.put("tenantId", stockRecords.getTenantId());
					params.put("companyInnerCode", stockRecords.getCompanyInnerCode());
					params.put("creator", stockRecords.getCreator());
					stockRecords = (StockRecords) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(stockRecords.getCreateTime()), params, stockRecords, "after");
					if (stockRecords == null || StringUtils.isEmpty(stockRecords.getId())) {
						stockRecords = outBoundController.doListStockRecordsById(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowOutBound";
	}

	/**
	 * 查看销售订单源
	 * 
	 * @return
	 */
	public String goShowPurchaseOrder() {
		return "goShowPurchaseOrder";
	}

	/**
	 * @return the isAllowAudit
	 */
	public String getIsAllowAudit() {
		return isAllowAudit;
	}

	/**
	 * @param isAllowAudit
	 *            the isAllowAudit to set
	 */
	public void setIsAllowAudit(String isAllowAudit) {
		this.isAllowAudit = isAllowAudit;
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

	public List<SalesOrder> getSalesOrderList() {
		return salesOrderList;
	}

	public void setSalesOrderList(List<SalesOrder> salesOrderList) {
		this.salesOrderList = salesOrderList;
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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public SaleOrderItem getSaleOrderItem() {
		return saleOrderItem;
	}

	public void setSaleOrderItem(SaleOrderItem saleOrderItem) {
		this.saleOrderItem = saleOrderItem;
	}

	public List<SaleOrderItem> getSaleOrderItemList() {
		return saleOrderItemList;
	}

	public void setSaleOrderItemList(List<SaleOrderItem> saleOrderItemList) {
		this.saleOrderItemList = saleOrderItemList;
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

	public String getMasterInventoryCurrentStockId() {
		return masterInventoryCurrentStockId;
	}

	public void setMasterInventoryCurrentStockId(String masterInventoryCurrentStockId) {
		this.masterInventoryCurrentStockId = masterInventoryCurrentStockId;
	}

	public String getPurchaseOrderid() {
		return purchaseOrderid;
	}

	public void setPurchaseOrderid(String purchaseOrderid) {
		this.purchaseOrderid = purchaseOrderid;
	}

	public InventoryParameters getInventoryParameters() {
		return inventoryParameters;
	}

	public void setInventoryParameters(InventoryParameters inventoryParameters) {
		this.inventoryParameters = inventoryParameters;
	}

	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId
	 *            the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

}
