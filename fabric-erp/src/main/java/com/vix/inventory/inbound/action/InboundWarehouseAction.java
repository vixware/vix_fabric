package com.vix.inventory.inbound.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
import com.vix.common.cache.IUseCache;
import com.vix.common.id.VixUUID;
import com.vix.common.report.ExportReport;
import com.vix.common.report.PersonAction;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.CMNObjectModifyRecord;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.jdbc.dbstruct.Specification;
import com.vix.core.persistence.jdbc.dbstruct.SpecificationDetail;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.inbound.controller.InboundWarehouseController;
import com.vix.inventory.inbound.entity.StockRecordLines;
import com.vix.inventory.inbound.entity.StockRecords;
import com.vix.inventory.inventorytype.entity.InventoryType;
import com.vix.inventory.option.entity.InventoryParameters;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.mdm.item.service.IItemService;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.PurchaseOrderLineItem;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;
import com.vix.system.billTypeManagement.entity.BillsProperty;

import flexjson.JSONSerializer;
import net.sf.cglib.beans.BeanCopier;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class InboundWarehouseAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private InboundWarehouseController inboundWarehouseController;
	@Autowired
	private IItemService itemService;
	@Autowired
	private OrderProcessController orderProcessController;
	@Resource
	private IUseCache useCache;
	private String id;
	private String taskId;
	private String categoryId;
	private String ids;
	private String pageNo;
	private String biztype;
	private String itemCode;
	/**
	 * 查询条件
	 */
	private StringBuilder searchCriteria = new StringBuilder();
	private StockRecords stockRecords;
	private List<InventoryType> inventoryTypeList;
	private List<SalesOrder> salesOrderList;
	private String salesOrderId;
	private List<StockRecords> stockRecordsList;
	/**
	 * 入库单明细
	 */
	private StockRecordLines stockRecordLines;
	private List<StockRecordLines> stockRecordLinesList;
	private List<CMNObjectModifyRecord> objectModifyRecordList;
	/**
	 * 采购订单
	 */
	private PurchaseOrder purchaseOrder;

	private List<PurchaseOrder> purchaseOrderList;
	/**
	 * 采购订单明细
	 */
	private PurchaseOrderLineItem purchaseOrderLineItem;
	private InventoryParameters inventoryParameters;
	private List<PurchaseOrderLineItem> purchaseOrderLineItemList;
	private List<Specification> specList;
	private Item item;
	private String str;
	private String purchaseOrderid;
	/**
	 * 计量单位
	 */
	private List<MeasureUnit> measureUnitList;
	/**
	 * 有无批次管理
	 */
	private String isBatch;
	private String isAllowAudit;// 是否允许提交审批 1：是 0：否

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// flag 1: 过滤入库单
			params.put("flag," + SearchCondition.ANYLIKE, "1");
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			// 获取当前员工信息
			Employee employee = getEmployee();
			if (employee != null && employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
				// 如果登录的员工属于经销商或门店
				params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
			}
			stockRecordsList = inboundWarehouseController.doListStockRecordsList(params);
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
			Map<String, Object> params = new HashMap<String, Object>();
			// 倒序排序
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			// flag 1: 过滤入库单
			params.put("flag," + SearchCondition.ANYLIKE, "1");
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");

			if (null != pageNo && !"".equals(pageNo) && !"undefined".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			// 根据状态查询
			String status = getRequestParameter("status");
			if (StringUtils.isNotEmpty(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (StringUtils.isNotEmpty(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			String name = getDecodeRequestParameter("name");
			if (StringUtils.isNotEmpty(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name.trim());
			}
			String createTime = getRequestParameter("createTime");
			if (StringUtils.isNotEmpty(createTime)) {
				params.put("createTime," + SearchCondition.MORETHAN, createTime);
			}
			String warehousePerson = getDecodeRequestParameter("warehousePerson");
			if (StringUtils.isNotEmpty(warehousePerson)) {
				params.put("warehousePerson," + SearchCondition.ANYLIKE, warehousePerson.trim());
			}
			String checkperson = getDecodeRequestParameter("checkperson");
			if (StringUtils.isNotEmpty(checkperson)) {
				params.put("checkperson," + SearchCondition.ANYLIKE, checkperson.trim());
			}
			String tag = getRequestParameter("tag");
			if ("0".equals(tag)) {
				// 简单搜索
				String content = getRequestParameter("content");
				if (StringUtils.isNotEmpty(content)) {
					params.put("code," + SearchCondition.EQUAL, content.trim());
				}
			} else {
				// 高级搜索
				String code1 = getRequestParameter("code1");// 入库单号
				if (StringUtils.isNotEmpty(code1)) {
					params.put("code," + SearchCondition.EQUAL, code1.trim());
					searchCriteria.append(code1);
				}
				String invWarehouseName = getDecodeRequestParameter("warehousecode"); // 仓库名称
				if (StringUtils.isNotEmpty(invWarehouseName)) {
					params.put("invWarehouse.name," + SearchCondition.ANYLIKE, invWarehouseName.trim());
					searchCriteria.append(",").append(invWarehouseName);
				}
			}

			// 搜索加强
			Map<String, Object> stockRecordLinesparams = new HashMap<String, Object>();
			// 根据商品信息获取订单ID
			String itemname = getDecodeRequestParameter("itemname");
			String itemcode = getRequestParameter("itemcode");
			String specification = getDecodeRequestParameter("specification");
			String skuCode = getRequestParameter("skuCode");
			String stockRecordsIds = "";
			if (StringUtils.isNotEmpty(itemname)) {
				stockRecordLinesparams.put("itemname," + SearchCondition.ANYLIKE, itemname.trim());
			}
			if (StringUtils.isNotEmpty(itemcode)) {
				stockRecordLinesparams.put("itemcode," + SearchCondition.ANYLIKE, itemcode.trim());
			}
			if (StringUtils.isNotEmpty(specification)) {
				stockRecordLinesparams.put("specification," + SearchCondition.ANYLIKE, specification.trim());
			}
			if (StringUtils.isNotEmpty(skuCode)) {
				stockRecordLinesparams.put("skuCode," + SearchCondition.ANYLIKE, skuCode.trim());
			}
			if (stockRecordLinesparams != null && !stockRecordLinesparams.isEmpty()) {
				List<StockRecordLines> stockRecordLinesList = inboundWarehouseController.doListStockRecordLinesList(stockRecordLinesparams);
				if (stockRecordLinesList != null && stockRecordLinesList.size() > 0) {
					for (StockRecordLines stockRecordLines : stockRecordLinesList) {
						if (stockRecordLines != null && stockRecordLines.getStockRecords() != null) {
							stockRecordsIds += stockRecordLines.getStockRecords().getId() + ",";
						}
					}
				}
			}
			if (StringUtils.isNotEmpty(stockRecordsIds)) {
				params.put("id," + SearchCondition.IN, stockRecordsIds);
			}
			// 搜索加强

			// 获取当前登录用户所在的公司或供应商
			Employee employee = getEmployee();
			if (employee != null && employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
				// 如果登录的员工属于经销商或门店
				params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
			}
			pager = inboundWarehouseController.doListStockrecordsPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			inventoryTypeList = inboundWarehouseController.doListInventoryTypeByEntity(params);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_INBOUND));
				stockRecords.setCreator(SecurityUtil.getCurrentUserName());
				stockRecords.setIsTemp("1");
				stockRecords.setWarehousePerson(SecurityUtil.getCurrentUserName());
				stockRecords.setCreator(SecurityUtil.getCurrentUserName());
				stockRecords = inboundWarehouseController.doSaveOrUpdateStockRecords(stockRecords);
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
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowInboundWarehouse";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(stockRecords.getId()) && !"0".equals(stockRecords.getId())) {
				isSave = false;
			}
			// 获取当前登录用户所在的公司或供应商
			// 获取当前员工信息
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getChannelDistributor() != null) {
					// 如果登录的员工属于经销商或门店
					stockRecords.setChannelDistributor(employee.getChannelDistributor());
				}
			}
			if (stockRecords != null && stockRecords.getInventoryType() != null && StringUtils.isNotEmpty(stockRecords.getInventoryType().getId()) && !"-1".equals(stockRecords.getInventoryType().getId())) {

			} else {
				stockRecords.setInventoryType(null);
			}
			stockRecords.setIsTemp("");
			// biztype 业务类型 1 ：采购入库单, 0 ：产成品入库单, 2：其他入库单,3:红字入库单
			stockRecords.setBiztype(biztype);
			// status 审核状态0：未提交 1：待审核 2：审核中3：审核通过
			stockRecords.setStatus("0");
			// flag 出入库标志 1：入库 ，2：出库
			stockRecords.setFlag("1");
			initEntityBaseController.initEntityBaseAttribute(stockRecords);
			// 处理中文索引
			String py = ChnToPinYin.getPYString(stockRecords.getName());
			stockRecords.setChineseCharacter(py.toUpperCase());
			if (stockRecords != null && StringUtils.isNotEmpty(updateField)) {
				billMarkProcessController.processMark(stockRecords, updateField);
			}
			stockRecords = inboundWarehouseController.doSaveOrUpdateStockRecords(stockRecords);
			// 增加操作日志
			if (true) {
				vixOperateLog.saveOperateLog(stockRecords.getClass().getName(), stockRecords.getCode(), "", "新增入库单");
			}
			// 更改采购订单的状态
			if (StringUtils.isNotEmpty(purchaseOrderid)) {
				updatePurchaseOrderStatus(purchaseOrderid);
			}
			String tag = getRequestParameter("tag");
			if (tag != null && "1".equals(tag)) {
				dealStartAndSubmitByBillsCode();
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
		return UPDATE;
	}

	/**
	 * @throws Exception
	 */
	private void updatePurchaseOrderStatus(String purchaseOrderid) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id," + SearchCondition.IN, purchaseOrderid);
		List<PurchaseOrder> purchaseOrderList = inboundWarehouseController.doListPurchaseOrderList(params);
		if (purchaseOrderList != null && purchaseOrderList.size() > 0) {
			for (PurchaseOrder purchaseOrder : purchaseOrderList) {
				if (purchaseOrder != null) {
					// 修改采购订单的状态
					purchaseOrder.setStatus("已完成");
					inboundWarehouseController.doSavePurchaseOrder(purchaseOrder);
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

				String billsPropertyId = billsProperty.getId();
				String billTypeCode = BillType.INV_INBOUND;
				String billTitle = stockRecords.getName();
				String billId = stockRecords.getId();

				MultiValueMap<String, Object> formData = new LinkedMultiValueMap<String, Object>();
				formData.add("billsPropertyId", billsPropertyId);
				formData.add("billTypeCode", billTypeCode);
				formData.add("billTitle", URLEncoder.encode(billTitle, "utf-8"));
				formData.add("billId", billId);
				Object objUser = getSession().getAttribute("userInfo");
				UserAccount user = null;
				if (null != objUser && objUser instanceof UserAccount) {
					user = (UserAccount) objUser;
					formData.add("userId", user.getId());
				}
				String url = "http://localhost:8080/vform/activiti/deployment/startAndSubmitByBillsCode";
				String response = postData(formData, url);
				JSONObject json = JSONObject.fromObject(response);
				if (json.has("status")) {
					if ("1".equals(json.getString("status"))) {
						stockRecords.setStatus("1");
						stockRecords = inboundWarehouseController.doSaveOrUpdateStockRecords(stockRecords);
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
			StockRecords pb = inboundWarehouseController.doListStockRecordsById(id);
			if (null != pb) {
				inboundWarehouseController.doDeleteByEntity(pb);
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
				StockRecords so = inboundWarehouseController.doListStockRecordsById(id);
				if (so != null) {
					json = convertListToJson(new ArrayList<StockRecordLines>(so.getSubStockRecordLines()), so.getSubStockRecordLines().size());
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
				inboundWarehouseController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 跳转至新增产成本入库单 */
	public String goAddFinishedGoodsInbound() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			inventoryTypeList = inboundWarehouseController.doListInventoryTypeByEntity(params);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_INBOUND));
				stockRecords.setIsTemp("1");
				stockRecords.setWarehousePerson(SecurityUtil.getCurrentUserName());
				stockRecords.setCreator(SecurityUtil.getCurrentUserName());
				stockRecords = inboundWarehouseController.doSaveOrUpdateStockRecords(stockRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAddFinishedGoodsInbound";
	}

	/**
	 * 跳转到查看产成品入库单页面
	 * 
	 * @return
	 */
	public String goShowFinishedGoodsInbound() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowFinishedGoodsInbound";
	}

	/** 跳转至新增红字入库单 */
	public String goScarletLetterInbound() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			inventoryTypeList = inboundWarehouseController.doListInventoryTypeByEntity(params);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_INBOUND));
				stockRecords.setIsTemp("1");
				stockRecords.setWarehousePerson(SecurityUtil.getCurrentUserName());
				stockRecords.setCreator(SecurityUtil.getCurrentUserName());
				stockRecords = inboundWarehouseController.doSaveOrUpdateStockRecords(stockRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goScarletLetterInbound";
	}

	// 入库调整单
	public String goAdjustmentInbound() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			inventoryTypeList = inboundWarehouseController.doListInventoryTypeByEntity(params);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_INBOUND));
				stockRecords.setIsTemp("1");
				stockRecords.setWarehousePerson(SecurityUtil.getCurrentUserName());
				stockRecords.setCreator(SecurityUtil.getCurrentUserName());
				stockRecords = inboundWarehouseController.doSaveOrUpdateStockRecords(stockRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAdjustmentInbound";
	}

	// 入库调整单
	public String goShowAdjustmentInbound() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			inventoryTypeList = inboundWarehouseController.doListInventoryTypeByEntity(params);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_INBOUND));
				stockRecords.setIsTemp("1");
				stockRecords.setWarehousePerson(SecurityUtil.getCurrentUserName());
				stockRecords.setCreator(SecurityUtil.getCurrentUserName());
				stockRecords = inboundWarehouseController.doSaveOrUpdateStockRecords(stockRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowAdjustmentInbound";
	}

	/** 跳转至新增红字入库单 */
	public String goShowScarletLetterInbound() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowScarletLetterInbound";
	}

	/** 跳转到增加入库单明细页面 */
	public String goAddWimStockRecordLines() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
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
	public String saveOrUpdateWimStockRecordLines() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(stockRecordLines.getId()) && !"0".equals(stockRecordLines.getId())) {
				isSave = false;
			}
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
				if (stockRecords != null) {
					stockRecordLines.setStockRecords(stockRecords);
				}
				// 是否拆分
				String isDisassembly = getRequestParameter("isDisassembly");
				if ("1".equals(isDisassembly)) {
					dealDisassembly();
				}
				stockRecordLines.setFlag("1");
				initEntityBaseController.initEntityBaseAttribute(stockRecordLines);
				inboundWarehouseController.doSaveStockRecordLines(stockRecordLines);
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
		return UPDATE;
	}

	/**
	 * @throws Exception
	 */
	private void dealDisassembly() throws Exception {
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
	public String saveOrUpdateStockRecordLines() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
				StockRecordLines invStockRecordLines = new StockRecordLines();
				invStockRecordLines.setStockRecords(stockRecords);
				String itemcode = getRequestParameter("itemcode");
				Item item = inboundWarehouseController.doListItem(itemcode);
				if (item != null) {
					invStockRecordLines.setItemcode(item.getCode());
					invStockRecordLines.setItemname(item.getName());
					invStockRecordLines.setSpecification(item.getSpecification());
					invStockRecordLines.setUnit(item.getMeasureUnit().getName());
					invStockRecordLines.setUnitcost(item.getPrice());
					invStockRecordLines.setSkuCode(item.getSkuCode());
					invStockRecordLines.setQuantity(1D);
					invStockRecordLines.setFlag("1");
					inboundWarehouseController.doSaveStockRecordLines(invStockRecordLines);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return UPDATE;
	}

	/** 获取json数据 */
	public void getWimStockRecordLinesJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				StockRecords wimStockrecords = inboundWarehouseController.doListStockRecordsById(id);
				if (null != wimStockrecords) {
					json = convertListToJson(new ArrayList<StockRecordLines>(wimStockrecords.getSubStockRecordLines()), wimStockrecords.getSubStockRecordLines().size());
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
			StockRecordLines wimStockrecordlines = inboundWarehouseController.doListStockRecordLinesById(id);
			if (null != wimStockrecordlines) {
				inboundWarehouseController.deleteStockRecordLinesByEntity(wimStockrecordlines);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String goAddOtherInbound() {
		try {
			isAllowAudit = isAllowAudit(BillType.INV_INBOUND);
			Map<String, Object> params = new HashMap<String, Object>();
			inventoryTypeList = inboundWarehouseController.doListInventoryTypeByEntity(params);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_INBOUND));
				stockRecords.setIsTemp("1");
				stockRecords.setStatus("0");
				stockRecords.setWarehousePerson(SecurityUtil.getCurrentUserName());
				stockRecords.setCreator(SecurityUtil.getCurrentUserName());
				stockRecords = inboundWarehouseController.doSaveOrUpdateStockRecords(stockRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAddOtherInbound";
	}

	// 来源要货单
	public String goAddStoreEnquiryRequest() {
		try {
			isAllowAudit = isAllowAudit(BillType.INV_INBOUND);
			Map<String, Object> params = new HashMap<String, Object>();
			inventoryTypeList = inboundWarehouseController.doListInventoryTypeByEntity(params);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			} else {
				stockRecords = new StockRecords();
				stockRecords.setCode(autoCreateCode.getBillNO(BillType.INV_INBOUND));
				stockRecords.setIsTemp("1");
				stockRecords.setStatus("0");
				stockRecords.setWarehousePerson(SecurityUtil.getCurrentUserName());
				stockRecords.setCreator(SecurityUtil.getCurrentUserName());
				stockRecords = inboundWarehouseController.doSaveOrUpdateStockRecords(stockRecords);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAddStoreEnquiryRequest";
	}

	public String goShowOtherInbound() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			inventoryTypeList = inboundWarehouseController.doListInventoryTypeByEntity(params);
			if (StringUtils.isNotEmpty(id)) {
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowOtherInbound";
	}

	public String goAudit() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			inventoryTypeList = inboundWarehouseController.doListInventoryTypeByEntity(params);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
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

	// 跳转到选择采购订单页面
	public String goChoosePurchaseOrder() {
		return "goChoosePurchaseOrder";
	}

	// 跳转到选择仓库页面
	public String goChooseWarehouse() {
		return "goChooseWarehouse";
	}

	/**
	 * 加载顶端工具栏
	 * 
	 * @return
	 */

	public String goTopDynamicMenuContent() {
		return "goTopDynamicMenuContent";
	}

	/** 获取供应商列表数据 */
	public String goSubRadioSingleList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			// 按状态
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 按最近使用
			Pager pager = inboundWarehouseController.goSupplierPager(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSubRadioSingleList";
	}

	/** 获取供应商列表数据 */
	public String goPurchaseOrderList() {
		try {
			Map<String, Object> params = getParams();
			// 倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager.setPageSize(6);
			// 按状态
			/* params.put("status," + SearchCondition.NOEQUAL, "已完成"); */
			// 按最近使用
			pager = inboundWarehouseController.goPurchaseOrder(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPurchaseOrderList";
	}

	public String getPurchaseOrderItemList() {
		try {
			if (StringUtils.isNotEmpty(purchaseOrderid) && !"0".equals(purchaseOrderid)) {
				purchaseOrder = inboundWarehouseController.findPurchaseOrder(purchaseOrderid);
				Set<PurchaseOrderLineItem> purchaseOrderLineItems = purchaseOrder.getPurchaseOrderLineItems();
				if (purchaseOrderLineItems.size() > 0) {
					purchaseOrderLineItemList = new ArrayList<PurchaseOrderLineItem>(purchaseOrderLineItems);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "getPurchaseOrderItemList";
	}

	/**
	 * 将采购订单明细转化成采购入库单明细 同时更改采购订单的状态
	 * 
	 * @return
	 */
	public String converterPurchaseOrderToStockrecords() {
		try {
			stockRecords = inboundWarehouseController.doListStockRecordsById(id);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id," + SearchCondition.IN, purchaseOrderid);
			List<PurchaseOrder> purchaseOrderList = inboundWarehouseController.doListPurchaseOrderList(params);
			if (purchaseOrderList != null && purchaseOrderList.size() > 0) {
				for (PurchaseOrder purchaseOrder : purchaseOrderList) {
					if (purchaseOrder != null) {
						inboundWarehouseController.convPurchaseOrderToStockrecords(stockRecords, purchaseOrder);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UPDATE;
	}

	public void getOrderItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				StockRecords wimStockrecords = inboundWarehouseController.doListStockRecordsById(id);
				if (null != wimStockrecords) {
					json = convertListToJson(new ArrayList<StockRecordLines>(wimStockrecords.getSubStockRecordLines()), wimStockrecords.getSubStockRecordLines().size());
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

	// 跳转到添加物料页面
	public String goAddSaleOrderItem() {
		try {
			measureUnitList = inboundWarehouseController.doListMeasureUnitList();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
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
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				StockRecords wimStockrecords = inboundWarehouseController.doListStockRecordsById(id);
				if (null != wimStockrecords) {
					Double totalAmount = 0d;
					for (StockRecordLines wimStockrecordlines : wimStockrecords.getSubStockRecordLines()) {
						totalAmount += wimStockrecordlines.getUnitcost() * wimStockrecordlines.getQuantity();
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

	public String goChooseItem() {
		return "goChooseItem";
	}

	/** 获取列表数据 */
	public String goChooseListContent() {
		try {
			Map<String, Object> params = getParams();
			getParams().put("isTemp," + SearchCondition.NOEQUAL, "1");
			getPager().setPageSize(10);
			if (StringUtils.isNotEmpty(categoryId) && !"0".equals(categoryId)) {
				itemService.findPagerByItemCatalogId(getPager(), categoryId, params);
			} else {
				itemService.findPagerByHqlConditions(getPager(), Item.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goChooseListContent";
	}

	public String chooseSpecification() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
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

	public String findJasper() throws Exception {
		str = "";
		Map<String, Object> params = new HashMap<String, Object>();
		// 过滤入库单
		params.put("flag," + SearchCondition.ANYLIKE, "1");
		params.put("isTemp," + SearchCondition.NOEQUAL, "1");
		List<StockRecords> ds = inboundWarehouseController.doListStockRecordsList(params);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ds, false);
		JasperReport jasperReport = JasperCompileManager.compileReport(PersonAction.class.getResource("/").toString().substring(6) + "stockRecords.jrxml");
		Map<String, Object> parameters = new HashMap<String, Object>();
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		str = ExportReport.exportToHTML(jasperPrint);
		return "findStockRecordsReport";
	}

	/**
	 * 新增入库单明细
	 * 
	 * @return
	 */
	public String goSaveOrUpdateStockRecordLines() {
		try {
			measureUnitList = inboundWarehouseController.doListMeasureUnitList();

			// 查询是否开启保质期管理
			inventoryParameters = inboundWarehouseController.doListInventoryParametersByAttribute("tenantId", SecurityUtil.getCurrentUserTenantId());
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecordLines = itemService.findEntityById(StockRecordLines.class, id);
			} else {
				stockRecordLines = new StockRecordLines();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goSaveOrUpdateStockRecordLines";
	}

	public String goListStoreEnquiryRequest() {
		try {
			Map<String, Object> p = new HashMap<String, Object>();
			Employee employee = getEmployee();
			if (employee != null && employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
				p.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
				// 过滤临时数据
				p.put("isTemp," + SearchCondition.NOEQUAL, "1");
				salesOrderList = itemService.findAllByConditions(SalesOrder.class, p);
			}
			measureUnitList = inboundWarehouseController.doListMeasureUnitList();

			// 查询是否开启保质期管理
			inventoryParameters = inboundWarehouseController.doListInventoryParametersByAttribute("tenantId", SecurityUtil.getCurrentUserTenantId());
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockRecordLines = itemService.findEntityById(StockRecordLines.class, id);
			} else {
				stockRecordLines = new StockRecordLines();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goListStoreEnquiryRequest";
	}

	// 获取批次号
	public void getBatchCode() {
		try {
			String batchCode = "";
			if (StringUtils.isNotEmpty(itemCode) && !"0".equals(itemCode)) {
				// 查询是否开启保质期管理
				inventoryParameters = inboundWarehouseController.doListInventoryParametersByAttribute("tenantId", SecurityUtil.getCurrentUserTenantId());
				if (inventoryParameters != null) {
					if ("1".equals(inventoryParameters.getIsBatch())) {
						batchCode = autoCreateCode.getBillNO(itemCode);
						renderJson(batchCode);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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

	public String goListStoreEnquiryRequestContent() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setPageSize(6);
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderField("id");
				pager.setOrderBy("desc");
			}
			if (StringUtils.isNotEmpty(salesOrderId)) {
				params.put("salesOrder.id," + SearchCondition.EQUAL, salesOrderId);
				pager = itemService.findPagerByHqlConditions(pager, SaleOrderItem.class, params);
			}
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListStoreEnquiryRequestContent";
	}

	public String goListSalesOrderContent() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setPageSize(6);
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderField("id");
				pager.setOrderBy("desc");
			}
			Employee employee = getEmployee();
			if (employee != null && employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
				params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
				// 过滤临时数据
				params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			}
			pager = itemService.findPagerByHqlConditions(pager, SalesOrder.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListSalesOrderContent";
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
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
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

	/**
	 * 查看采购订单源
	 * 
	 * @return
	 */
	public String goShowPurchaseOrder() {
		return "goShowPurchaseOrder";
	}

	/**
	 * 修改留痕查看
	 * 
	 * @return
	 */
	public String goModifiedLeaveMark() {
		return "goModifiedLeaveMark";
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

	public String goListOrderBatchContent() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("stockRecords.id," + SearchCondition.EQUAL, id);
				purchaseOrderList = inboundWarehouseController.doListPurchaseOrderList(params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListOrderBatchContent";
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
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
				if (stockRecords != null && stockRecords.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(stockRecords.getCreateTime()));
					// flag 1: 过滤入库单
					params.put("flag", "1");
					// 过滤临时数据
					params.put("isTemp", "");
					params.put("tenantId", stockRecords.getTenantId());
					params.put("companyInnerCode", stockRecords.getCompanyInnerCode());
					params.put("creator", stockRecords.getCreator());
					stockRecords = (StockRecords) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(stockRecords.getCreateTime()), params, stockRecords, "before");
					if (stockRecords == null || StringUtils.isEmpty(stockRecords.getId())) {
						stockRecords = inboundWarehouseController.doListStockRecordsById(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowOtherInbound";
	}

	/**
	 * 
	 * @return
	 */
	public String goAfterStockRecords() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				stockRecords = inboundWarehouseController.doListStockRecordsById(id);
				if (stockRecords != null && stockRecords.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(stockRecords.getCreateTime()));
					// flag 1: 过滤入库单
					params.put("flag", "1");
					// 过滤临时数据
					params.put("isTemp", "");
					params.put("tenantId", stockRecords.getTenantId());
					params.put("companyInnerCode", stockRecords.getCompanyInnerCode());
					params.put("creator", stockRecords.getCreator());
					stockRecords = (StockRecords) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(stockRecords.getCreateTime()), params, stockRecords, "after");
					if (stockRecords == null || StringUtils.isEmpty(stockRecords.getId())) {
						stockRecords = inboundWarehouseController.doListStockRecordsById(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowOtherInbound";
	}

	public String goSearch() {
		return "goSearch";
	}

	public String gohelp() {
		return "gohelp";
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the purchaseOrderList
	 */
	public List<PurchaseOrder> getPurchaseOrderList() {
		return purchaseOrderList;
	}

	/**
	 * @param purchaseOrderList
	 *            the purchaseOrderList to set
	 */
	public void setPurchaseOrderList(List<PurchaseOrder> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
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

	public List<SalesOrder> getSalesOrderList() {
		return salesOrderList;
	}

	public void setSalesOrderList(List<SalesOrder> salesOrderList) {
		this.salesOrderList = salesOrderList;
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

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getBiztype() {
		return biztype;
	}

	public void setBiztype(String biztype) {
		this.biztype = biztype;
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

	public List<StockRecordLines> getStockRecordLinesList() {
		return stockRecordLinesList;
	}

	public void setStockRecordLinesList(List<StockRecordLines> stockRecordLinesList) {
		this.stockRecordLinesList = stockRecordLinesList;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public PurchaseOrderLineItem getPurchaseOrderLineItem() {
		return purchaseOrderLineItem;
	}

	public void setPurchaseOrderLineItem(PurchaseOrderLineItem purchaseOrderLineItem) {
		this.purchaseOrderLineItem = purchaseOrderLineItem;
	}

	public InventoryParameters getInventoryParameters() {
		return inventoryParameters;
	}

	public void setInventoryParameters(InventoryParameters inventoryParameters) {
		this.inventoryParameters = inventoryParameters;
	}

	public List<PurchaseOrderLineItem> getPurchaseOrderLineItemList() {
		return purchaseOrderLineItemList;
	}

	public void setPurchaseOrderLineItemList(List<PurchaseOrderLineItem> purchaseOrderLineItemList) {
		this.purchaseOrderLineItemList = purchaseOrderLineItemList;
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

	/**
	 * @param isAllowAudit
	 *            the isAllowAudit to set
	 */
	public void setIsAllowAudit(String isAllowAudit) {
		this.isAllowAudit = isAllowAudit;
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

	public String getPurchaseOrderid() {
		return purchaseOrderid;
	}

	public void setPurchaseOrderid(String purchaseOrderid) {
		this.purchaseOrderid = purchaseOrderid;
	}

	/**
	 * @return the searchCriteria
	 */
	public StringBuilder getSearchCriteria() {
		return searchCriteria;
	}

	/**
	 * @param searchCriteria
	 *            the searchCriteria to set
	 */
	public void setSearchCriteria(StringBuilder searchCriteria) {
		this.searchCriteria = searchCriteria;
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

	public String getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

}
