package com.vix.nvix.sales.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xml.XmlAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.util.TransformerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.billtype.BillType;
import com.vix.common.job.entity.FlowApprovalOpinion;
import com.vix.common.security.util.DaysUtils;
import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.BillGroupCode;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.Regional;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.NumberUtil;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.crm.project.entity.CrmProject;
import com.vix.crm.salechance.entity.SaleChance;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.purchase.order.entity.BizType;
import com.vix.mdm.purchase.order.entity.OrderType;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.template.ExcelTemplate;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesAttachFile;
import com.vix.sales.order.entity.SalesDeliveryPlan;
import com.vix.sales.order.entity.SalesDeliveryPlanDetail;
import com.vix.sales.order.entity.SalesOrder;
import com.vix.sales.order.entity.SalesTicket;
import com.vix.sales.order.entity.SalesTicketDetail;
import com.vix.sales.order.service.ISalesOrderService;
import com.vix.sales.quotes.entity.SalesQuotation;
import com.vix.sales.quotes.entity.SalesQuotationItem;
import com.vix.system.billTypeManagement.entity.BillsType;

import net.sf.json.JSONObject;

/**
 * 销售单
 * @author Bluesnow
 * 2015年12月15日
 */
@Controller
@Scope("prototype")
public class NvixSalesOrderAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ISalesOrderService salesOrderService;
	
	private String id;
	private String salesOrderId;
	private String title;
	private String subject;
	private String entityName;
	private String types;
	private String name;
	private String barCode;
	private String isAllowAudit;// 是否允许提交审批 1：是 0：否
	private SalesOrder salesOrder;
	private ContactPerson contactPerson;
	private SalesDeliveryPlan salesDeliveryPlan;
	private SalesTicket salesTicket;
	private SalesTicketDetail salesTicketDetail;
	private SalesDeliveryPlanDetail salesDeliveryPlanDetail;
	private BillGroupCode billGroupCode;// 成组码
	private List<CurrencyType> currencyTypeList;
	private List<Regional> regionalList;
	private List<BillsType> billsTypeList;
	private List<ContactPerson> contactPersonList;
	private SaleOrderItem saleOrderItem;
	private List<SaleOrderItem> salesOrderItemList;
	private List<SalesTicket> salesTicketList;
	@Autowired
	private OrderProcessController orderProcessController;
	private List<BaseEntity> baseEntityList;
	private String str;
	private List<FlowApprovalOpinion> flowApprovalOpinionList;
	
	private List<BizType> bizTypeList;
	private List<OrderType> orderTypeList;
	@Override
	public String goList(){
		getBizTypeAndOrderType();
		return GO_LIST;
	}
	
	public void goListContentJson(){
		try {
			Pager pager = getPager();
			Map<String,Object> params = getParams();
			params.put("isTemp," + SearchCondition.EQUAL, "0");
			params.put("isDeleted," + SearchCondition.EQUAL, "0");
			if (StringUtils.isNotEmpty(title)) {
				params.put("title," + SearchCondition.ANYLIKE, URLDecoder.decode(title.trim(), "UTF-8"));
			}
			if(StringUtils.isNotEmpty(name)){
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE,URLDecoder.decode(name.trim(), "UTF-8"));
			}
			String code = getDecodeRequestParameter("code");
			if(StringUtils.isNotEmpty(code)){
				params.put("code,"+SearchCondition.ANYLIKE,code);
			}
			String salePerson = getDecodeRequestParameter("salePerson");
			if(StringUtils.isNotEmpty(salePerson)){
				params.put("salePerson,"+SearchCondition.ANYLIKE,salePerson);
			}
			String bizTypeId = getDecodeRequestParameter("bizTypeId");
			if(StringUtils.isNotEmpty(bizTypeId)){
				params.put("biz.id,"+SearchCondition.ANYLIKE,bizTypeId);
			}
			String orderTypeId = getDecodeRequestParameter("orderTypeId");
			if(StringUtils.isNotEmpty(orderTypeId)){
				params.put("orderType.id,"+SearchCondition.ANYLIKE,orderTypeId);
			}
			String createTime = getDecodeRequestParameter("createTime");
			if(StringUtils.isNotEmpty(createTime)){
				params.put("billDate," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(createTime.trim())) + "!" + DaysUtils.getEndDay(dateFormat.parse(createTime.trim())));
			}
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("billDate");
			}
			salesOrderService.findPagerByHqlConditions(pager, SalesOrder.class, params);
			String[] excludes = {" "};
			renderDataTable(pager, excludes);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private List<SaleChance> saleChanceList;
	public String goSaveOrUpdate(){
		try {
			getBizTypeAndOrderType();
			isAllowAudit = isAllowAudit(BillType.SA_ORDER);
			currencyTypeList = salesOrderService.findAllByEntityClass(CurrencyType.class);
			regionalList = salesOrderService.findAllByEntityClass(Regional.class);
			billsTypeList = salesOrderService.findAllByEntityClassAndAttribute(BillsType.class, "billsProperty.propertyCode", BillType.SA_ORDER);
			if (null != id && StrUtils.isNotEmpty(id)) {
				salesOrder = salesOrderService.findEntityById(SalesOrder.class, id);
				if(null != salesOrder){
					if(null != salesOrder.getCustomerAccount() && StrUtils.isNotEmpty(salesOrder.getCustomerAccount().getId())){
						saleChanceList = baseHibernateService.findAllByEntityClassAndAttribute(SaleChance.class, "customerAccount.id", salesOrder.getCustomerAccount().getId());
					}
					if(null != salesOrder.getSaleOrderItems() && salesOrder.getSaleOrderItems().size() > 0){
						Double count = 0.00;
						Set<SaleOrderItem> soiSet = salesOrder.getSaleOrderItems();
						for(SaleOrderItem soi : soiSet){
							/*if(null != soi.getItem().getPrice()){
								count += soi.getItem().getPrice() * soi.getAmount();
							}else {
								count += soi.getPrice() * soi.getAmount();
							}*/
							count += soi.getPrice() * soi.getAmount();
						}
						salesOrder.setAmount(NumberUtil.round4(count));
					}
					if(null != salesOrder.getCustomerAccount()){
						if(null != salesOrder.getCustomerAccount().getId() && StrUtils.isNotEmpty(salesOrder.getCustomerAccount().getId())){
							contactPersonList = salesOrderService.findAllByEntityClassAndAttribute(ContactPerson.class, "customerAccount.id", salesOrder.getCustomerAccount().getId());
						}
					}
					if (null != salesOrder.getAuditStatus() && "1".equals(salesOrder.getAuditStatus())) {
						return "showOrder";
					}
				}
			} else {
				salesOrder = new SalesOrder();
				salesOrder.setCode(autoCreateCode.getBillNO(BillType.SA_ORDER));
				salesOrder.setIsTemp("1");
				salesOrder.setIsDeleted("0");
				salesOrder.setBillDate(new Date());
				salesOrder.setStatus("1");
				Employee baseEmp = getEmployee();
				if (null != baseEmp) {
					Employee emp = salesOrderService.findEntityById(Employee.class, baseEmp.getId());
					salesOrder.setSalePerson(emp);
					salesOrder.setSalePersonCode(emp.getCode());
					if (null != emp) {
						salesOrder.setSaleOrg(emp.getOrganizationUnit());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			setError("销售订单获取失败!");
		}
		return GO_SAVE_OR_UPDATE;
	}
	public String goChooseSalesQuotation() {
		return "goChooseSalesQuotation";
	}
	/** 销售报价转销售订单 */
	public void convertSalesQuotationToSalesOrder() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				SalesQuotation salesQuotation = vixntBaseService.findEntityById(SalesQuotation.class, id);
				if(salesQuotation != null) {
					salesOrder = new SalesOrder();
					salesOrder.setCode(autoCreateCode.getBillNO(BillType.SA_ORDER));
					salesOrder.setTitle("源自报价单"+dateFormat.format(new Date()));
					salesOrder.setIsTemp("1");
					salesOrder.setIsDeleted("0");
					salesOrder.setBillDate(new Date());
					salesOrder.setStatus("1");
					salesOrder.setIsComplate("0");
					salesOrder.setIsFreeze("0");
					salesOrder.setCreateTime(new Date());
					salesOrder.setUpdateTime(new Date());
					salesOrder.setSourceBillCode(salesQuotation.getCode());
					salesOrder.setSourceClassName(salesQuotation.getClass().getName());
					salesOrder.setCustomerAccount(salesQuotation.getCustomerAccount());
					Employee baseEmp = getEmployee();
					if (null != baseEmp) {
						Employee emp = salesOrderService.findEntityById(Employee.class, baseEmp.getId());
						salesOrder.setSalePerson(emp);
						salesOrder.setSalePersonCode(emp.getCode());
						if (null != emp) {
							salesOrder.setSaleOrg(emp.getOrganizationUnit());
						}
					}
					if ("1".equals(isAllowAudit(BillType.SA_ORDER))) {
						salesOrder.setAuditStatus("0");
					}
					salesOrder = vixntBaseService.merge(salesOrder);
					Map<String, Object> params = getParams();
					params.put("salesQuotation.id,"+SearchCondition.EQUAL, salesQuotation.getId());
					List<SalesQuotationItem> salesQuotationItemList = vixntBaseService.findAllByConditions(SalesQuotationItem.class, params);
					if(salesQuotationItemList != null && salesQuotationItemList.size() > 0) {
						for (SalesQuotationItem salesQuotationItem : salesQuotationItemList) {
							if(salesQuotationItem != null) {
								SaleOrderItem saleOrderItem = new SaleOrderItem();
								saleOrderItem.setItem(salesQuotationItem.getItem());
								if(salesQuotationItem.getItem() != null) {
									saleOrderItem.setItemCode(salesQuotationItem.getItem().getCode());
								}
								saleOrderItem.setIsSaleReturnBillCalculation("0");
								saleOrderItem.setIsCommissionCalculation("0");
								saleOrderItem.setItem(salesQuotationItem.getItem());
								saleOrderItem.setSpecification(salesQuotationItem.getSpecification());
								saleOrderItem.setUnit(salesQuotationItem.getUnit());
								saleOrderItem.setAmount(salesQuotationItem.getAmount());
								saleOrderItem.setTaxPrice(salesQuotationItem.getTaxPrice());
								saleOrderItem.setPrice(salesQuotationItem.getPrice());
								saleOrderItem.setNetPrice(salesQuotationItem.getNetPrice());
								saleOrderItem.setNetTotal(salesQuotationItem.getNetTotal());
								saleOrderItem.setTax(salesQuotationItem.getTax());
								saleOrderItem.setDiscount(salesQuotationItem.getDiscount());
								saleOrderItem.setRequireDate(salesQuotationItem.getRequireDate());
								saleOrderItem.setSalesOrder(salesOrder);
								saleOrderItem = vixntBaseService.merge(saleOrderItem);
							}
						}
					}
					List<SaleOrderItem> soiList = salesOrderService.findAllByEntityClassAndAttribute(SaleOrderItem.class, "salesOrder.id", salesOrder.getId());
					if(soiList.size() > 0 && soiList != null){
						for(SaleOrderItem soi : soiList){
							if(soi.getItem() != null && StringUtils.isNotEmpty(soi.getItem().getCode())) {
								List<InventoryCurrentStock> invList = vixntBaseService.findAllByEntityClassAndAttribute(InventoryCurrentStock.class, "itemcode", soi.getItem().getCode());
								//接入出库规则
								if(invList != null && invList.size() > 0) {
									for (InventoryCurrentStock inventoryCurrentStock : invList) {
										//通过规则确定是哪个物料
										if(inventoryCurrentStock != null) {
											inventoryCurrentStock.setIsfreezeflag(1);
											inventoryCurrentStock.setFreezequantity(soi.getAmount());
											inventoryCurrentStock.setAvaquantity(inventoryCurrentStock.getAvaquantity() - soi.getAmount());
											inventoryCurrentStock = vixntBaseService.merge(inventoryCurrentStock);
										}
									}
								}
							}
						}
					}
					renderText("0:转单成功!:"+salesOrder.getId());
				}else {
					renderText("1:报价单未取到!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("转换失败!");
		}
	}
	
	public void saveOrUpdate(){
		boolean isSave = true;
		try {
			if (null != salesOrder.getId() && StrUtils.isNotEmpty(salesOrder.getId())) {
				isSave = false;
				salesOrder.setUpdateTime(new Date());
			} else {
				salesOrder.setCreateTime(new Date());
				loadCommonData(salesOrder);
			}
			String[] attrArray ={"customerAccount","contactPerson","salePerson","currencyType","saleOrg","regional","crmProject","channelDistributor",
					"employee","orderPrintData","distributionCenter","organization","invWarehouse","logistics","orderBatch","saleChance",""};
			checkEntityNullValue(salesOrder,attrArray);
		
			billGroupCode = new BillGroupCode();
			billGroupCode.setIsTemp("1");
			billGroupCode.setBillType(BillType.SAL_QUOTATION);
			billGroupCode.setGroupCode(UUID.randomUUID().toString());
			loadCommonData(billGroupCode);
			
			String title = salesOrder.getTitle();
			String py = ChnToPinYin.getPYString(title);
			salesOrder.setChineseFirstLetter(py.toUpperCase());
			salesOrder.setIsTemp("0");
			salesOrder.setIsComplate("0");
			salesOrder.setIsFreeze("0");
			salesOrder.setIsDeleted("0");
			salesOrder.setGroupCode(billGroupCode.getGroupCode());
			List<SaleOrderItem> soiList = salesOrderService.findAllByEntityClassAndAttribute(SaleOrderItem.class, "salesOrder.id", salesOrder.getId());
			Double amount = 0D;
			if(soiList.size() > 0 && soiList != null){
				for(SaleOrderItem soi : soiList){
					if(null != soi){
						/*if(null != soi.getItem().getPrice()){
							amount += soi.getItem().getPrice() * soi.getAmount();
						}else {
							amount += soi.getPrice() * soi.getAmount();
						}*/
						amount += soi.getPrice() * soi.getAmount();
						if(soi.getItem() != null && StringUtils.isNotEmpty(soi.getItem().getCode())) {
							List<InventoryCurrentStock> invList = vixntBaseService.findAllByEntityClassAndAttribute(InventoryCurrentStock.class, "itemcode", soi.getItem().getCode());
							//接入出库规则
							if(invList != null && invList.size() > 0) {
								for (InventoryCurrentStock inventoryCurrentStock : invList) {
									//通过规则确定是哪个物料
									if(inventoryCurrentStock != null) {
										inventoryCurrentStock.setIsfreezeflag(1);
										inventoryCurrentStock.setFreezequantity(soi.getAmount());
										inventoryCurrentStock.setAvaquantity(inventoryCurrentStock.getAvaquantity() - soi.getAmount());
										inventoryCurrentStock = vixntBaseService.merge(inventoryCurrentStock);
									}
								}
							}
						}
					}
				}
				salesOrder.setAmountTotal(amount);
			}
			salesOrder = salesOrderService.merge(salesOrder);
			dealBillGroupCode();
			billGroupCode.setBillCode(salesOrder.getId());
			billGroupCode = salesOrderService.merge(billGroupCode);
			if ("approval".equals(getRequestParameter("approval"))) {
				if ("1".equals(isAllowAudit(BillType.SA_ORDER))) {
					String response = dealStartAndSubmitByBillsCode(BillType.INV_INBOUND, salesOrder);
					if(StringUtils.isNotEmpty(response)){
						JSONObject json = JSONObject.fromObject(response);
						if (json.has("status")) {
							if("1".equals(json.getString("status"))){
								salesOrder.setAuditStatus("1");
								salesOrder = vixntBaseService.merge(salesOrder);
								renderText(salesOrder.getId()+":提交成功!");
							}
						}
					}
				}
			} else {
				if (isSave) {
					renderText(salesOrder.getId()+ ":" + SAVE_SUCCESS);
				} else {
					renderText(salesOrder.getId()+ ":" + UPDATE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:" + SAVE_FAIL);
			} else {
				renderText("0:" + UPDATE_FAIL);
			}
		}
	}
	public void audit(){
		try {
			if (null != salesOrder.getId() && StrUtils.isNotEmpty(salesOrder.getId())) {
				salesOrder.setUpdateTime(new Date());
			} else {
				salesOrder.setCreateTime(new Date());
				loadCommonData(salesOrder);
			}
			String[] attrArray ={"customerAccount","contactPerson","salePerson","currencyType","saleOrg","regional","crmProject","channelDistributor",
					"employee","orderPrintData","distributionCenter","organization","invWarehouse","logistics","orderBatch","saleChance",""};
			checkEntityNullValue(salesOrder,attrArray);
			
			billGroupCode = new BillGroupCode();
			billGroupCode.setIsTemp("1");
			billGroupCode.setBillType(BillType.SAL_QUOTATION);
			billGroupCode.setGroupCode(UUID.randomUUID().toString());
			loadCommonData(billGroupCode);
			
			String title = salesOrder.getTitle();
			String py = ChnToPinYin.getPYString(title);
			salesOrder.setChineseFirstLetter(py.toUpperCase());
			salesOrder.setIsTemp("0");
			salesOrder.setIsComplate("0");
			salesOrder.setIsFreeze("0");
			salesOrder.setIsDeleted("0");
			salesOrder.setGroupCode(billGroupCode.getGroupCode());
			List<SaleOrderItem> soiList = salesOrderService.findAllByEntityClassAndAttribute(SaleOrderItem.class, "salesOrder.id", salesOrder.getId());
			Double amount = 0D;
			if(soiList.size() > 0){
				for(SaleOrderItem soi : soiList){
					if(null != soi){
						/*if(null != soi.getItem().getPrice()){
							amount += soi.getItem().getPrice() * soi.getAmount();
						}else {
							amount += soi.getPrice() * soi.getAmount();
						}*/
						amount += soi.getPrice() * soi.getAmount();
					}
				}
				salesOrder.setAmountTotal(amount);
			}
			salesOrder = salesOrderService.merge(salesOrder);
			dealBillGroupCode();
			billGroupCode.setBillCode(salesOrder.getId());
			billGroupCode = salesOrderService.merge(billGroupCode);
			if ("1".equals(isAllowAudit(BillType.SA_ORDER))) {
				String response = dealStartAndSubmitByBillsCode(BillType.INV_INBOUND, salesOrder);
				if(StringUtils.isNotEmpty(response)){
					JSONObject json = JSONObject.fromObject(response);
					if (json.has("status")) {
						if("1".equals(json.getString("status"))){
							salesOrder.setAuditStatus("1");
							salesOrder = vixntBaseService.merge(salesOrder);
							renderText(salesOrder.getId()+":提交成功!");
						}
					}
				}
			} else {
				renderText("提交失败!未绑定审批流程!");
			}
		} catch (Exception e) {
			e.printStackTrace();
				renderText("提交失败!");
			}
		}
	
	/** FIXME : 单据分组，逻辑最后统一测试 */
	private void dealBillGroupCode() throws Exception {
		if (null != salesOrder && null != salesOrder.getId()) {
			SalesOrder so = salesOrderService.findEntityById(SalesOrder.class, salesOrder.getId());
			Set<String> bgcSet = new HashSet<String>();
			for (SaleOrderItem soi : so.getSaleOrderItems()) {
				if (null != soi && null != soi.getGroupCode() && !"".equals(soi.getGroupCode()) && !soi.getGroupCode().equals(salesOrder.getGroupCode())) {
					bgcSet.add(soi.getGroupCode());
				}
			}
			if (bgcSet.size() > 0) {
				Map<String, Object> p = new HashMap<String, Object>();
				p.put("billType," + SearchCondition.EQUAL, BillType.SA_ORDER);
				p.put("billCode," + SearchCondition.EQUAL, salesOrder.getId().toString());
				List<BillGroupCode> bgcList = salesOrderService.findAllByConditions(BillGroupCode.class, p);
				for (BillGroupCode bgc : bgcList) {
					salesOrderService.deleteByEntity(bgc);
				}
				for (String bgc : bgcSet) {
					BillGroupCode billGroupCode = new BillGroupCode();
					billGroupCode.setBillType(BillType.SA_ORDER);
					billGroupCode.setBillCode(so.getId().toString());
					billGroupCode.setGroupCode(bgc);
					loadCommonData(billGroupCode);
					salesOrderService.merge(billGroupCode);
				}
			}
		}
	}
	
	/** 选择项目*/
	public String goChooseCrmProject(){
		return "goChooseCrmProject";
	}
	
	public void getCrmProjectJson(){
		try {
			Pager pager = getPager();
			Map<String,Object> params = getParams();
			
			if(null != subject && !"".equals(subject)){
				params.put("subject,"+SearchCondition.ANYLIKE,URLDecoder.decode(subject.trim(), "UTF-8"));
			}
			salesOrderService.findPagerByHqlConditions(pager, CrmProject.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 选择业务员*/
	public String goChooseEmployee(){
		return "goChooseEmployee";
	}
	
	public void getEmployeeJson(){
		try {
			Pager pager = getPager();
			Map<String,Object> params = getParams();
			
			if(null != name && !"".equals(name)){
				params.put("name,"+SearchCondition.ANYLIKE,URLDecoder.decode(name.trim(), "UTF-8"));
			}
			salesOrderService.findPagerByHqlConditions(pager, Employee.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/** 处理删除操作 */
	public String deleteById() {
		try {
			if (null != id && StrUtils.isNotEmpty(id)) {
				salesOrderService.deleteById(SalesOrder.class, id);
				renderText(DELETE_SUCCESS);
			} else {
				renderText(getText("ec_brandNotChoose"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	/** 获取明细*/
	public void getSaleOrderItemJson(){
		try {
			Pager pager = getPager();
			
			Map<String,Object> params = getParams();
			if(null != id && StrUtils.isNotEmpty(id)){
				params.put("salesOrder.id,"+SearchCondition.EQUAL, id);
				if(null != name && !"".equals(name)){
					params.put("item.name,"+SearchCondition.ANYLIKE,URLDecoder.decode(name.trim(), "UTF-8"));
				}
				salesOrderService.findPagerByHqlConditions(pager, SaleOrderItem.class, params);
			}
			String[] excludes = {" "};
			renderDataTable(pager, excludes);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 添加明细*/
	public String goSaveOrUpdateSaleOrderItem() {
		try {
			if (null != id && StrUtils.isNotEmpty(id)) {
				saleOrderItem = salesOrderService.findEntityById(SaleOrderItem.class, id);
			}
			if(null != types && StrUtils.isNotEmpty(types)){
				return "scanUpdateSaleOrderItem";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goSaveOrUpdateSaleOrderItem";
	}
	
	/** 保存明细*/
	public String saveOrUpdateOrderItem() {
		boolean isSave = true;
		try {
			salesOrder = salesOrderService.findEntityById(SalesOrder.class,saleOrderItem.getSalesOrder().getId());
			if(null == salesOrder){
				renderText(SAVE_FAIL);
				return UPDATE;
			}
			String barCode = getRequestParameter("barCode");
			if (null != barCode && !"".equals(barCode)) {
				Item item = salesOrderService.findEntityByAttribute(Item.class, "code", barCode);
				if (null != item) {
					SaleOrderItem soi = new SaleOrderItem();
					soi.setSalesOrder(salesOrder);
					soi.setItem(item);
					soi.setAmount(1D);
					soi.setPrice(item.getPrice());
					soi.setIsSaleReturnBillCalculation("0");
					soi.setIsCommissionCalculation("0");
					soi.setGroupCode(salesOrder.getGroupCode());
					loadCommonData(soi);
					salesOrderService.merge(soi);
				}
			} else {
				if (null != saleOrderItem.getId() && StrUtils.isNotEmpty(saleOrderItem.getId())) {
					isSave = false;
				}
				saleOrderItem.setIsSaleReturnBillCalculation("0");
				saleOrderItem.setIsCommissionCalculation("0");
				saleOrderItem.setSalesOrder(salesOrder);
				String giftJson = getRequestParameter("giftJson");
				if (null != giftJson && !"".equals(giftJson)) {
					saleOrderItem.setIsHasGift("1");
				}
				saleOrderItem.setGroupCode(salesOrder.getGroupCode());
				List<SaleOrderItem> soiList = salesOrderService.findAllByEntityClassAndAttribute(SaleOrderItem.class, "salesOrder.id", salesOrder.getId());
				if(soiList != null && soiList.size() > 0){
					for (SaleOrderItem saleOrderItem1 : soiList) {
						if(saleOrderItem1 != null && saleOrderItem1.getItem() != null){
							if(saleOrderItem1.getItem().getId().equals(saleOrderItem.getItem().getId())){
								saleOrderItem1.setAmount(saleOrderItem.getAmount()+saleOrderItem1.getAmount());
								saleOrderItem1 = salesOrderService.merge(saleOrderItem1);
								if (isSave) {
									renderText("1:" + SAVE_SUCCESS);
								} else {
									renderText("1:" + UPDATE_SUCCESS);
								}
								return UPDATE;
							}
						}
					}
				}
				saleOrderItem = salesOrderService.merge(saleOrderItem);
				if (null != giftJson && !"".equals(giftJson)) {
					String[] gifts = giftJson.split("_");
					for (String gift : gifts) {
						if (null != gift && !"".equals(gift)) {
							String[] giftItem = gift.split("-");
							SaleOrderItem soi = new SaleOrderItem();
							soi.setSalesOrder(saleOrderItem.getSalesOrder());
							Item i = salesOrderService.findEntityById(Item.class, giftItem[0]);
							if (null != i) {
								soi.setItem(i);
								if (!",".equals(giftItem[1])) {
									soi.setSpecification(giftItem[1]);
								}
								soi.setAmount(Double.parseDouble(giftItem[2]));
								soi.setPrice(Double.parseDouble(giftItem[3]));
								loadCommonData(soi);
								soi.setIsGift("1");
								soi.setSaleOrderItemId(saleOrderItem.getId());
								if (soi.getPrice() > 0) {
									soi.setMemo("买赠");
								} else {
									soi.setMemo("赠品");
								}
								salesOrderService.merge(soi);
							}
						}
					}
				}
			}
			if (isSave) {
				renderText("1:" + SAVE_SUCCESS);
			} else {
				renderText("1:" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:" + SAVE_FAIL);
			} else {
				renderText("0:" + UPDATE_FAIL);
			}
		}
		return UPDATE;
	}
	
	/**  删除明细*/
	public String deleteSalesOrderItemById() {
		try {
			if (null != id && StrUtils.isNotEmpty(id)) {
				SaleOrderItem soi = salesOrderService.findEntityById(SaleOrderItem.class, id);
				if (null != soi) {
					if (null != soi.getIsHasGift() && "1".equals(soi.getIsHasGift())) {
						salesOrderService.deleteByAttribute(SaleOrderItem.class, "saleOrderItemId", id);
					}
					salesOrder = salesOrderService.findEntityById(SalesOrder.class, soi.getSalesOrder().getId());
					salesOrderService.deleteByEntity(soi);
					/** 删除明细之后  重新计算订单总价格*/
					if(null != salesOrder){
						List<SaleOrderItem> soiList = salesOrderService.findAllByEntityClassAndAttribute(SaleOrderItem.class, "salesOrder.id", salesOrder.getId());
						Double amount = 0.0;
						if(soiList.size() > 0){
							for(SaleOrderItem s : soiList){
								if(null != s){
									amount += s.getPrice() * s.getAmount();
								}
							}
							salesOrder.setAmountTotal(amount);
						}
						salesOrder = salesOrderService.merge(salesOrder);
					}
					renderText(DELETE_SUCCESS);
				} else {
					renderText("数据未找到!");
				}
			} else {
				renderText("数据未找到!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	/** 获取发运计划*/
	public void getSalesDeliveryPlanJson(){
		try {
			Pager pager = getPager();
			
			Map<String,Object> params = getParams();
			if(null != id && StrUtils.isNotEmpty(id)){
				params.put("salesOrder.id,"+SearchCondition.EQUAL, id);
				if(null != name && !"".equals(name)){
					params.put("reciever,"+SearchCondition.ANYLIKE,URLDecoder.decode(name.trim(), "UTF-8"));
				}
				salesOrderService.findPagerByHqlConditions(pager, SalesDeliveryPlan.class, params);
			}
			renderDataTable(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 获取发运计划明细*/
	public void getSalesDeliveryPlanDetailJson(){
		try {
			Pager pager = getPager();
			
			Map<String,Object> params = getParams();
			if(null != id && StrUtils.isNotEmpty(id)){
				params.put("salesDeliveryPlan.id,"+SearchCondition.EQUAL, id);
				if(null != name && !"".equals(name)){
					params.put("saleOrderItem.item.name,"+SearchCondition.ANYLIKE,URLDecoder.decode(name.trim(), "UTF-8"));
				}
				salesOrderService.findPagerByHqlConditions(pager, SalesDeliveryPlanDetail.class, params);
			}
			renderDataTable(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 新增修改发运计划*/
	public String addDeliveryPlan(){
		try{
			if(null != id && StrUtils.isNotEmpty(id)){
				salesDeliveryPlan = salesOrderService.findEntityById(SalesDeliveryPlan.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addDeliveryPlan";
	}
	
	/** 新增修改发运计划明细*/
	public String addSalesDeliveryPlanDetail(){
		try{
			if(null != id && StrUtils.isNotEmpty(id)){
				salesDeliveryPlanDetail = salesOrderService.findEntityById(SalesDeliveryPlanDetail.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addSalesDeliveryPlanDetail";
	}
	
	/** 选择订单明细*/
	public String goChooseSalesOrderItem(){
		return "goChooseSalesOrderItem";
	}
	
	/** 保存发运计划明细*/
	public String saveOrUpdateSalesDeliveryPlanDetail(){
		boolean isSave = true;
		try {
			if (null != salesDeliveryPlanDetail.getId() && StrUtils.isNotEmpty(salesDeliveryPlanDetail.getId())) {
				isSave = false;
			} else {
				salesDeliveryPlanDetail.setCreateTime(new Date());
				loadCommonData(salesDeliveryPlanDetail);
			}
			salesDeliveryPlanDetail = salesOrderService.merge(salesDeliveryPlanDetail);
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
	
	/**  删除发运计划明细*/
	public String deleteSalesOrderDeliveryPlanDetailById() {
		try {
			if (null != id && StrUtils.isNotEmpty(id)) {
				salesDeliveryPlanDetail = salesOrderService.findEntityById(SalesDeliveryPlanDetail.class, id);
				if (null != salesDeliveryPlanDetail) {
					salesOrderService.deleteByEntity(salesDeliveryPlanDetail);
					renderText(DELETE_SUCCESS);
				} else {
					renderText("数据未找到!");
				}
			} else {
				renderText("数据未找到!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	/** 保存发运计划*/
	public String saveOrUpdateDeliveryPlan(){
		boolean isSave = true;
		try {
			if (null != salesDeliveryPlan.getId() && StrUtils.isNotEmpty(salesDeliveryPlan.getId())) {
				isSave = false;
			} else {
				salesDeliveryPlan.setCreateTime(new Date());
				loadCommonData(salesDeliveryPlan);
			}
			salesDeliveryPlan = salesOrderService.merge(salesDeliveryPlan);
			if (isSave) {
				renderText(salesDeliveryPlan.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText(salesDeliveryPlan.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:" + SAVE_FAIL);
			} else {
				renderText("0:" + UPDATE_FAIL);
			}
		}
		return UPDATE;
	}
	
	/**  删除发运计划*/
	public String deleteSalesOrderDeliveryPlanById() {
		try {
			if (null != id && StrUtils.isNotEmpty(id)) {
				salesDeliveryPlan = salesOrderService.findEntityById(SalesDeliveryPlan.class, id);
				if (null != salesDeliveryPlan) {
					salesOrderService.deleteByEntity(salesDeliveryPlan);
					renderText(DELETE_SUCCESS);
				} else {
					renderText("数据未找到!");
				}
			} else {
				renderText("数据未找到!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	/** 获取发票*/
	public void getSalesTicketJson(){
		try {
			Pager pager = getPager();
			
			Map<String,Object> params = getParams();
			if(null != id && StrUtils.isNotEmpty(id)){
				params.put("salesOrder.id,"+SearchCondition.EQUAL, id);
				if(null != name && !"".equals(name)){
					params.put("title,"+SearchCondition.ANYLIKE,URLDecoder.decode(name.trim(), "UTF-8"));
				}
				salesOrderService.findPagerByHqlConditions(pager, SalesTicket.class, params);
			}
			renderDataTable(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 获取发票明细*/
	public void getSalesTicketDetailJson(){
		try {
			Pager pager = getPager();
			
			Map<String,Object> params = getParams();
			if(null != id && StrUtils.isNotEmpty(id)){
				params.put("salesTicket.id,"+SearchCondition.EQUAL, id);
				if(null != name && !"".equals(name)){
					params.put("saleOrderItem.item.name,"+SearchCondition.ANYLIKE,URLDecoder.decode(name.trim(), "UTF-8"));
				}
				salesOrderService.findPagerByHqlConditions(pager, SalesTicketDetail.class, params);
			}
			renderDataTable(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 新增修改发票*/
	public String addSalesTicket() {
		try {
			if (null != id && StrUtils.isNotEmpty(id)) {
				salesTicket = salesOrderService.findEntityById(SalesTicket.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addSalesTicket";
	}
	
	/** 保存发票*/
	public String saveOrUpdateSalesTicket(){
		boolean isSave = true;
		try {
			if (null != salesTicket.getId() && StrUtils.isNotEmpty(salesTicket.getId())) {
				isSave = false;
			} else {
				salesTicket.setCreateTime(new Date());
				loadCommonData(salesTicket);
			}
			salesTicket = salesOrderService.merge(salesTicket);
			if (isSave) {
				renderText(salesTicket.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText(salesTicket.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:" + SAVE_FAIL);
			} else {
				renderText("0:" + UPDATE_FAIL);
			}
		}
		return UPDATE;
	}
	
	/** 新增修改发票明细*/
	public String addSalesTicketDetail() {
		try {
			if (null != id && StrUtils.isNotEmpty(id)) {
				salesTicketDetail = salesOrderService.findEntityById(SalesTicketDetail.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addSalesTicketDetail";
	}
	
	/** 保存发票明细 */
	public String saveOrUpdateSalesTicketDetail() {
		boolean isSave = true;
		try {
			if (null != salesTicketDetail.getId() && StrUtils.isNotEmpty(salesTicketDetail.getId())) {
				isSave = false;
			}
			salesTicketDetail = salesOrderService.merge(salesTicketDetail);
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
	
	/**  删除发票*/
	public String deleteSalesTicketById() {
		try {
			if (null != id && StrUtils.isNotEmpty(id)) {
				salesTicket = salesOrderService.findEntityById(SalesTicket.class, id);
				if (null != salesTicket) {
					salesOrderService.deleteByEntity(salesTicket);
					renderText(DELETE_SUCCESS);
				} else {
					renderText("数据未找到!");
				}
			} else {
				renderText("数据未找到!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	/**  删除发票明细*/
	public String deleteSalesTicketDetailById() {
		try {
			if (null != id && StrUtils.isNotEmpty(id)) {
				salesTicketDetail = salesOrderService.findEntityById(SalesTicketDetail.class, id);
				if (null != salesTicketDetail) {
					salesOrderService.deleteByEntity(salesTicketDetail);
					renderText(DELETE_SUCCESS);
				} else {
					renderText("数据未找到!");
				}
			} else {
				renderText("数据未找到!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	
	/** 获取附件*/
	public void getAttachFileJson() {
		try {
			Pager pager = getPager();
			
			Map<String,Object> params = getParams();
			if(null != id && StrUtils.isNotEmpty(id)){
				params.put("salesOrder.id,"+SearchCondition.EQUAL, id);
				if(null != name && !"".equals(name)){
					params.put("fileName,"+SearchCondition.ANYLIKE,URLDecoder.decode(name.trim(), "UTF-8"));
				}
				salesOrderService.findPagerByHqlConditions(pager, SalesAttachFile.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存附件
	 */
	public void saveOrUpdateUploader() {
		try {
			String[] saveDocPathAndName = saveDocUploadPic();
			if (saveDocPathAndName != null && saveDocPathAndName.length == 4) {
				SalesAttachFile salesAttachFile = new SalesAttachFile();
				salesAttachFile.setFileName(saveDocPathAndName[1].toString());
				Employee employee = getEmployee();
				if (employee != null) {
					salesAttachFile.setCreator(employee.getName());
				}
				salesAttachFile.setPath("/img/document/" + saveDocPathAndName[1].toString());
				salesAttachFile.setCreateTime(new Date());
				if (StringUtils.isNotEmpty(id)) {
					salesOrder = salesOrderService.findEntityById(SalesOrder.class, id);
					if (salesOrder != null) {
						salesAttachFile.setSalesOrder(salesOrder);
					}
				}
				salesAttachFile.setType(saveDocPathAndName[3].toString());
				salesAttachFile = salesOrderService.merge(salesAttachFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 下载附件
	 * 
	 * @return
	 */
	public String downloadUploader() {
		if (StringUtils.isNotEmpty(id)) {
			try {
				SalesAttachFile salesAttachFile = salesOrderService.findEntityById(SalesAttachFile.class, this.id);
				String fileName = salesAttachFile.getFileName();
				this.setOriFileName(fileName);
				String baseFolder = "c:/img/";
				String downloadFile = baseFolder + fileName;
				this.setInputStream(new FileInputStream(downloadFile));
				if (salesAttachFile.getDownloadNum() != null) {
					salesAttachFile.setDownloadNum(salesAttachFile.getDownloadNum() + 1);
				} else {
					salesAttachFile.setDownloadNum(1);
				}
				salesAttachFile = salesOrderService.merge(salesAttachFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			return NONE;
		}
		return "downloadUploader";
	}
	
	// 删除附件
	public void deleteUploaderById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				SalesAttachFile salesAttachFile = salesOrderService.findEntityById(SalesAttachFile.class, id);
				if (null != salesAttachFile) {
					String fileName = salesAttachFile.getFileName();
					String baseFolder = "c:/img/";
					String downloadFile = baseFolder + fileName;
					File f = new File(downloadFile); // 输入要删除的文件位置
					if (f.exists()) {
						f.delete();
					}
					salesOrderService.deleteByEntity(salesAttachFile);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	public void getOrderItemTotal(){
		try {
			if(StringUtils.isNotEmpty(id)){
				salesOrder = salesOrderService.findEntityById(SalesOrder.class, id);
				if(salesOrder != null){
					Double totalAmount = 0.00d;
					List<SaleOrderItem> soiList = salesOrderService.findAllByEntityClassAndAttribute(SaleOrderItem.class, "salesOrder.id", salesOrder.getId());
					if(null != soiList && soiList.size() > 0){
						for (SaleOrderItem saleOrderItem : soiList) {
							totalAmount += saleOrderItem.getAmount()*saleOrderItem.getPrice();
						}
						DecimalFormat df = new DecimalFormat(".##");
						if (totalAmount != 0) {
							String st = df.format(totalAmount);
							renderJson(st);
						} else {
							renderJson(totalAmount.toString());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("计算失败!");
		}
	}
	public void exportSalesOrderExcel(){
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");

			String ua = getRequest().getHeader("user-agent");
			String fileName = "销售单.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

			Map<String, Object> params = getParams();
			List<SalesOrder> salesOrderList = salesOrderService.findAllByConditions(SalesOrder.class, params);
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("salesOrder_export_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("salesOrder_export_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("salesOrderList", salesOrderList);
					xlsArea.applyAt(new CellRef("salesOrder!A1"), context);
					transformer.write();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goShowBeforeAndAfter() {
		try {
			if (StrUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				salesOrder = baseHibernateService.findEntityById(SalesOrder.class,id);
				if (salesOrder != null && salesOrder.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(salesOrder.getCreateTime()));
					params.put("isTemp", "0");
					params.put("isDelete", "0");
					if (StrUtils.isNotEmpty(str)) {
						if ("before".equals(str)) {
							salesOrder = (SalesOrder) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(salesOrder.getCreateTime()), params, salesOrder, "before");
						} else if ("after".equals(str)) {
							salesOrder = (SalesOrder) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(salesOrder.getCreateTime()), params, salesOrder, "after");
						}
					}
					if (salesOrder == null || StrUtils.isEmpty(salesOrder.getId())) {
						salesOrder = baseHibernateService.findEntityById(SalesOrder.class,id);
						Map<String, Object> params1 = new HashMap<String, Object>();
						params.put("sourceClassPk," + SearchCondition.EQUAL, salesOrder.getId());
						flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowSalesOrder";
	}
	/** 
	 * 打印销售单
	 * @return
	 */
	public String goPrintOrder(){
		try {
			if(StringUtils.isNotEmpty(id)){
				salesOrder = salesOrderService.findEntityById(SalesOrder.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(OPER_FAIL);
		}
		return "goPrintOrder";
	}
	public String goShowSalesOrder() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				salesOrder = salesOrderService.findEntityById(SalesOrder.class, id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sourceClassPk," + SearchCondition.EQUAL, id);
				flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowSalesOrder";
	}
	public String goAudit() {
		try {
			if(StringUtils.isNotEmpty(id)){
				salesOrder = salesOrderService.findEntityById(SalesOrder.class,id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sourceClassPk," + SearchCondition.EQUAL, id);
				flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAudit";
	}
	public String goSourceList() {
		try {
			if(StringUtils.isNotEmpty(id)){
				salesOrder = vixntBaseService.findEntityById(SalesOrder.class, id);
				if(null != salesOrder){
					baseEntityList = new ArrayList<>();
					baseEntityList.add(salesOrder);
					if(StringUtils.isNotEmpty(salesOrder.getSourceBillCode())&&StringUtils.isNotEmpty(salesOrder.getSourceClassName())){
						getSourceBaseEntity(baseEntityList, salesOrder);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSourceList";
	}
	public void  getBizTypeAndOrderType() {
		try {
			Map<String, Object> params = getParams();
			params.put("status,"+SearchCondition.EQUAL, "SAL");
			bizTypeList = vixntBaseService.findAllByConditions(BizType.class, params);
			orderTypeList = vixntBaseService.findAllByConditions(OrderType.class, params);
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

	public ContactPerson getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BillGroupCode getBillGroupCode() {
		return billGroupCode;
	}

	public void setBillGroupCode(BillGroupCode billGroupCode) {
		this.billGroupCode = billGroupCode;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public List<Regional> getRegionalList() {
		return regionalList;
	}

	public void setRegionalList(List<Regional> regionalList) {
		this.regionalList = regionalList;
	}

	public List<BillsType> getBillsTypeList() {
		return billsTypeList;
	}

	public void setBillsTypeList(List<BillsType> billsTypeList) {
		this.billsTypeList = billsTypeList;
	}

	public String getIsAllowAudit() {
		return isAllowAudit;
	}

	public void setIsAllowAudit(String isAllowAudit) {
		this.isAllowAudit = isAllowAudit;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Override
	public String getEntityName() {
		return entityName;
	}

	@Override
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public List<ContactPerson> getContactPersonList() {
		return contactPersonList;
	}

	public void setContactPersonList(List<ContactPerson> contactPersonList) {
		this.contactPersonList = contactPersonList;
	}

	public SaleOrderItem getSaleOrderItem() {
		return saleOrderItem;
	}

	public void setSaleOrderItem(SaleOrderItem saleOrderItem) {
		this.saleOrderItem = saleOrderItem;
	}

	public String getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public SalesDeliveryPlan getSalesDeliveryPlan() {
		return salesDeliveryPlan;
	}

	public void setSalesDeliveryPlan(SalesDeliveryPlan salesDeliveryPlan) {
		this.salesDeliveryPlan = salesDeliveryPlan;
	}

	public SalesDeliveryPlanDetail getSalesDeliveryPlanDetail() {
		return salesDeliveryPlanDetail;
	}

	public void setSalesDeliveryPlanDetail(
			SalesDeliveryPlanDetail salesDeliveryPlanDetail) {
		this.salesDeliveryPlanDetail = salesDeliveryPlanDetail;
	}

	public SalesTicket getSalesTicket() {
		return salesTicket;
	}

	public void setSalesTicket(SalesTicket salesTicket) {
		this.salesTicket = salesTicket;
	}

	public SalesTicketDetail getSalesTicketDetail() {
		return salesTicketDetail;
	}

	public void setSalesTicketDetail(SalesTicketDetail salesTicketDetail) {
		this.salesTicketDetail = salesTicketDetail;
	}

	public List<SaleOrderItem> getSalesOrderItemList() {
		return salesOrderItemList;
	}

	public void setSalesOrderItemList(List<SaleOrderItem> salesOrderItemList) {
		this.salesOrderItemList = salesOrderItemList;
	}

	public List<SalesTicket> getSalesTicketList() {
		return salesTicketList;
	}

	public void setSalesTicketList(List<SalesTicket> salesTicketList) {
		this.salesTicketList = salesTicketList;
	}

	public List<SaleChance> getSaleChanceList() {
		return saleChanceList;
	}

	public void setSaleChanceList(List<SaleChance> saleChanceList) {
		this.saleChanceList = saleChanceList;
	}

	public List<BaseEntity> getBaseEntityList() {
		return baseEntityList;
	}

	public void setBaseEntityList(List<BaseEntity> baseEntityList) {
		this.baseEntityList = baseEntityList;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public List<FlowApprovalOpinion> getFlowApprovalOpinionList() {
		return flowApprovalOpinionList;
	}

	public void setFlowApprovalOpinionList(List<FlowApprovalOpinion> flowApprovalOpinionList) {
		this.flowApprovalOpinionList = flowApprovalOpinionList;
	}

	public List<BizType> getBizTypeList() {
		return bizTypeList;
	}

	public void setBizTypeList(List<BizType> bizTypeList) {
		this.bizTypeList = bizTypeList;
	}

	public List<OrderType> getOrderTypeList() {
		return orderTypeList;
	}

	public void setOrderTypeList(List<OrderType> orderTypeList) {
		this.orderTypeList = orderTypeList;
	}
}