package com.vix.nvix.purchase.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.billtype.BillType;
import com.vix.common.job.entity.FlowApprovalOpinion;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.util.DaysUtils;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.inbound.entity.StockRecordLines;
import com.vix.inventory.inbound.entity.StockRecords;
import com.vix.mdm.purchase.arrivalmgr.entity.PurchaseArrival;
import com.vix.mdm.purchase.arrivalmgr.entity.PurchaseArrivalItems;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.PurchaseOrderLineItem;
import com.vix.mdm.purchase.pursreturn.entity.PurchaseReturn;
import com.vix.mdm.purchase.pursreturn.entity.PurchaseReturnItems;
import com.vix.nvix.common.base.action.VixntBaseAction;

import net.sf.json.JSONObject;
@Controller
@Scope("prototype")
public class VixntPurchaseReturnAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private OrderProcessController orderProcessController;
	private String id;
	private PurchaseReturn purchaseReturn;
	private PurchaseReturnItems purchaseReturnItems;
	private List<CurrencyType> currencyTypeList;
	private List<BaseEntity> baseEntityList;
	private String isAllowAudit;// 是否允许提交审批 1：是 0：否
	private String approval;
	private String str;
	private List<FlowApprovalOpinion> flowApprovalOpinionList;
	public void goSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String name = getDecodeRequestParameter("name");
			if (StringUtils.isNotEmpty(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			String code = getDecodeRequestParameter("code");
			if (StringUtils.isNotEmpty(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			String contactPerson = getDecodeRequestParameter("contactPerson");
			if (StringUtils.isNotEmpty(contactPerson)) {
				params.put("contactPerson," + SearchCondition.ANYLIKE, contactPerson);
			}
			String createTime = getRequestParameter("createTime");
			if (StringUtils.isNotEmpty(createTime)) {
				params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(createTime.trim())) + "!" + DaysUtils.getEndDay(dateFormat.parse(createTime.trim())));
			}
			String requireDepartment = getRequestParameter("requireDepartment");
			if (StringUtils.isNotEmpty(requireDepartment)) {
				params.put("requireDepartment," + SearchCondition.ANYLIKE, requireDepartment);
			}
			String purchasePerson = getRequestParameter("purchasePerson");
			if (StringUtils.isNotEmpty(purchasePerson)) {
				params.put("purchasePerson," + SearchCondition.ANYLIKE, purchasePerson);
			}
			String supplierName = getRequestParameter("supplierName");
			if (StringUtils.isNotEmpty(supplierName)) {
				params.put("supplierName," + SearchCondition.ANYLIKE, supplierName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseReturn.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSaveOrUpdate(){
		try {
			isAllowAudit = isAllowAudit(BillType.PUR_RETURN);
			if(StringUtils.isNotEmpty(id)){
				purchaseReturn = vixntBaseService.findEntityById(PurchaseReturn.class, id);
			}else{
				purchaseReturn = new PurchaseReturn();
				purchaseReturn.setCode(autoCreateCode.getBillNO(BillType.PUR_RETURN));
				purchaseReturn.setPurchasePerson(this.currentUserName());
				purchaseReturn.setPurchasePersonId(String.valueOf(SecurityUtil.getCurrentUserId()));
				Employee employee = getEmployee();
				if(null != employee){
					OrganizationUnit org = employee.getOrganizationUnit();
					if (org != null) {
						purchaseReturn.setPurchaseOrgId(org.getId());
						purchaseReturn.setPurchaseOrg(org.getFs());
					}
				}
			}
			currencyTypeList = vixntBaseService.findAllByEntityClass(CurrencyType.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	public void saveOrUpdate(){
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(purchaseReturn.getId())){
				isSave = false;
				purchaseReturn.setUpdateTime(new Date());
			}else {
				purchaseReturn.setCreateTime(new Date());
			}
			//处理修改留痕
			//billMarkProcessController.processMark(purchaseReturn, updateField);
			if("1".equals(isAllowAudit(BillType.PUR_RETURN))){
				purchaseReturn.setApprovalStatus("0");
			}
			purchaseReturn = vixntBaseService.merge(purchaseReturn);
			if("approval".equals(approval)){
				if("1".equals(isAllowAudit(BillType.PUR_RETURN))){
					String response = dealStartAndSubmitByBillsCode(BillType.PUR_RETURN, purchaseReturn);
					if(StringUtils.isNotEmpty(response)){
						JSONObject json = JSONObject.fromObject(response);
						if(json.has("status")){
							if("1".equals(json.getString("status"))){
								purchaseReturn.setApprovalStatus("1");
								purchaseReturn = vixntBaseService.merge(purchaseReturn);
								renderText(purchaseReturn.getId()+":提交成功!");
							}
						}
					}
				}
			}
			if(isSave){
				renderText("1:"+SAVE_SUCCESS+":"+purchaseReturn.getId());
			}else{
				renderText("1:"+UPDATE_SUCCESS+":"+purchaseReturn.getId());
			}
		} catch (Exception e) {
			if(isSave){
				renderText("0:"+SAVE_FAIL);
			}else{
				renderText("0:"+UPDATE_FAIL);
			}
			e.printStackTrace();
		}
	}
	public void audit(){
		try {
			if(StringUtils.isNotEmpty(purchaseReturn.getId())){
				purchaseReturn.setUpdateTime(new Date());
			}else {
				purchaseReturn.setCreateTime(new Date());
			}
			//处理修改留痕
			//billMarkProcessController.processMark(purchaseReturn, updateField);
			if("1".equals(isAllowAudit(BillType.PUR_RETURN))){
				purchaseReturn.setApprovalStatus("0");
			}
			purchaseReturn = vixntBaseService.merge(purchaseReturn);
			if("1".equals(isAllowAudit(BillType.PUR_RETURN))){
				String response = dealStartAndSubmitByBillsCode(BillType.PUR_RETURN, purchaseReturn);
				if(StringUtils.isNotEmpty(response)){
					JSONObject json = JSONObject.fromObject(response);
					if(json.has("status")){
						if("1".equals(json.getString("status"))){
							purchaseReturn.setApprovalStatus("1");
							purchaseReturn = vixntBaseService.merge(purchaseReturn);
							renderText(purchaseReturn.getId()+":提交成功!");
						}
					}
				}
			}else {
				renderText("提交失败!未绑定审批流程!");
			}
		} catch (Exception e) {
			renderText("提交失败!");
			e.printStackTrace();
		}
	}
	public void deleteById(){
		try {
			if(StringUtils.isNotEmpty(id)){
				purchaseReturn = vixntBaseService.findEntityById(PurchaseReturn.class, id);
				if(purchaseReturn != null){
					vixntBaseService.deleteByEntity(purchaseReturn);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			renderText(DELETE_FAIL);
			e.printStackTrace();
		}
	}
	public void goSinglePurchaseReturnItemsList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if(StringUtils.isNotEmpty(id)){
				params.put("purchaseReturn.id,"+SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseReturnItems.class, params);
			}
			renderDataTable(pager);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSaveOrUpdatePurchaseReturnItem(){
		try {
			if(StringUtils.isNotEmpty(id)){
				purchaseReturnItems = vixntBaseService.findEntityById(PurchaseReturnItems.class, id);
			}else{
				purchaseReturnItems = new PurchaseReturnItems();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdatePurchaseReturnItem";
	}
	public void saveOrUpdatePurchaseReturnItem(){
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(purchaseReturnItems.getId())){
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(purchaseReturnItems);
			purchaseReturnItems = vixntBaseService.merge(purchaseReturnItems);
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
	}
	public void deletePurchaseReturnItemById(){
		try {
			if(StringUtils.isNotEmpty(id)){
				purchaseReturnItems = vixntBaseService.findEntityById(PurchaseReturnItems.class, id);
				if(purchaseReturnItems != null){
					vixntBaseService.deleteByEntity(purchaseReturnItems);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			renderText(DELETE_FAIL);
			e.printStackTrace();
		}
	}
	/**
	 * 选择采购订单
	 * @return
	 */
	public String goChoosePurchaseOrder(){
		return "goChoosePurchaseOrder";
	}
	/**
	 * 选择采购到货单
	 * @return
	 */
	public String goChoosePurchaseArrival(){
		return "goChoosePurchaseArrival";
	}
	/**
	 * 选择采购入库单
	 * @return
	 */
	public String goChoosePurchaseInBound(){
		return "goChoosePurchaseInBound";
	}
	
	public void goStockRecordsSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			params.put("sourceClassName,"+SearchCondition.EQUAL, "com.vix.mdm.purchase.order.entity.PurchaseOrder");
			params.put("flag,"+SearchCondition.EQUAL, "1");
			params.put("type," + SearchCondition.EQUAL, "1");
			String title = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(title)) {
				params.put("name," + SearchCondition.ANYLIKE, title.trim());
			}
			String code = getRequestParameter("code");
			if (StringUtils.isNotEmpty(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code.trim());
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, StockRecords.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void createPurchaseReturnByPurchaseInBound() {
		try {
			if(StringUtils.isNotEmpty(id)){
				StockRecords stockRecords = vixntBaseService.findEntityById(StockRecords.class, id);
				if(stockRecords != null) {
					purchaseReturn = new PurchaseReturn();
					purchaseReturn.setCode(autoCreateCode.getBillNO(BillType.PUR_RETURN));
					purchaseReturn.setCreateTime(new Date());
					purchaseReturn.setName("源自采购入库"+dateFormat.format(new Date()));
					purchaseReturn.setPurchasePerson(this.currentUserName());
					purchaseReturn.setPurchasePersonId(String.valueOf(SecurityUtil.getCurrentUserId()));
					purchaseReturn.setSourceBillCode(stockRecords.getCode());
					purchaseReturn.setSourceClassName(stockRecords.getClass().getName());
					Employee employee = getEmployee();
					if(null != employee){
						OrganizationUnit org = employee.getOrganizationUnit();
						if (org != null) {
							purchaseReturn.setPurchaseOrgId(org.getId());
							purchaseReturn.setPurchaseOrg(org.getFs());
						}
					}
					initEntityBaseController.initEntityBaseAttribute(purchaseReturn);
					purchaseReturn = vixntBaseService.merge(purchaseReturn);
					Map<String, Object> params = getParams();
					params.put("stockRecords.id,"+SearchCondition.EQUAL, stockRecords.getId());
					List<StockRecordLines> stockRecordLinesList = vixntBaseService.findAllByConditions(StockRecordLines.class, params);
					if(stockRecordLinesList != null && stockRecordLinesList.size() > 0) {
						for (StockRecordLines stockRecordLines : stockRecordLinesList) {
							if(stockRecordLines != null){
								PurchaseReturnItems purchaseReturnItems = new PurchaseReturnItems();
								purchaseReturnItems.setPurchaseReturn(purchaseReturn);
								purchaseReturnItems.setItemCode(stockRecordLines.getItemcode());
								purchaseReturnItems.setItemName(stockRecordLines.getItemname());
								purchaseReturnItems.setBarCode(stockRecordLines.getBarCode());
								purchaseReturnItems.setPrice(stockRecordLines.getPrice());
								purchaseReturnItems.setUnit(stockRecordLines.getUnit());
								purchaseReturnItems.setAmount(stockRecordLines.getAmount());
								purchaseReturnItems = vixntBaseService.merge(purchaseReturnItems);
							}
						}
					}
					renderText("1:"+purchaseReturn.getId()+":转单成功!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:"+"转单失败!");
		}
	}
	public void createPurchaseReturnByPurchaseOrder(){
		try {
			if(StringUtils.isNotEmpty(id)){
				PurchaseOrder purchaseOrder = vixntBaseService.findEntityById(PurchaseOrder.class, id);
				if(purchaseOrder != null){
					purchaseReturn = new PurchaseReturn();
					purchaseReturn.setCode(autoCreateCode.getBillNO(BillType.PUR_RETURN));
					purchaseReturn.setCreateTime(new Date());
					purchaseReturn.setName("源自采购订单"+dateFormat.format(new Date()));
					purchaseReturn.setPurchasePerson(this.currentUserName());
					purchaseReturn.setPurchasePersonId(String.valueOf(SecurityUtil.getCurrentUserId()));
					purchaseReturn.setSourceBillCode(purchaseOrder.getCode());
					purchaseReturn.setSourceClassName(purchaseOrder.getClass().getName());
					Employee employee = getEmployee();
					if(null != employee){
						OrganizationUnit org = employee.getOrganizationUnit();
						if (org != null) {
							purchaseReturn.setPurchaseOrgId(org.getId());
							purchaseReturn.setPurchaseOrg(org.getFs());
						}
					}
					initEntityBaseController.initEntityBaseAttribute(purchaseReturn);
					purchaseReturn = vixntBaseService.merge(purchaseReturn);
					Map<String, Object> params = getParams();
					params.put("purchaseOrder.id,"+SearchCondition.EQUAL, purchaseOrder.getId());
					List<PurchaseOrderLineItem> purchaseOrderLineItemList = vixntBaseService.findAllByConditions(PurchaseOrderLineItem.class, params);
					if(purchaseOrderLineItemList != null && purchaseOrderLineItemList.size() > 0){
						for (PurchaseOrderLineItem purchaseOrderLineItem : purchaseOrderLineItemList) {
							if(purchaseOrderLineItem != null){
								PurchaseReturnItems purchaseReturnItems = new PurchaseReturnItems();
								purchaseReturnItems.setPurchaseReturn(purchaseReturn);
								purchaseReturnItems.setItemId(purchaseOrderLineItem.getItemId());
								purchaseReturnItems.setItemName(purchaseOrderLineItem.getItemName());
								purchaseReturnItems.setBarCode(purchaseOrderLineItem.getBarCode());
								purchaseReturnItems.setPrice(purchaseOrderLineItem.getPrice());
								purchaseReturnItems.setUnit(purchaseOrderLineItem.getUnit());
								purchaseReturnItems.setAmount(Math.round(purchaseOrderLineItem.getAmount()));
								purchaseReturnItems.setTaxRate(purchaseOrderLineItem.getTaxRate());
								purchaseReturnItems = vixntBaseService.merge(purchaseReturnItems);
							}
						}
					}
					renderText("1:"+purchaseReturn.getId()+":转单成功!");
				}
			}
		} catch (Exception e) {
			renderText("0:"+"转单失败!");
			e.printStackTrace();
		}
	}
	public void createPurchaseReturnByPurchaseArrival(){
		try {
			if(StringUtils.isNotEmpty(id)){
				PurchaseArrival purchaseArrival = vixntBaseService.findEntityById(PurchaseArrival.class, id);
				if(purchaseArrival != null){
					purchaseReturn = new PurchaseReturn();
					purchaseReturn.setCode(autoCreateCode.getBillNO(BillType.PUR_RETURN));
					purchaseReturn.setCreateTime(new Date());
					purchaseReturn.setName("源自采购到货单"+dateFormat.format(new Date()));
					purchaseReturn.setPurchasePerson(this.currentUserName());
					purchaseReturn.setPurchasePersonId(String.valueOf(SecurityUtil.getCurrentUserId()));
					purchaseReturn.setSourceBillCode(purchaseArrival.getCode());
					purchaseReturn.setSourceClassName(purchaseArrival.getClass().getName());
					Employee employee = getEmployee();
					if(null != employee){
						OrganizationUnit org = employee.getOrganizationUnit();
						if (org != null) {
							purchaseReturn.setPurchaseOrgId(org.getId());
							purchaseReturn.setPurchaseOrg(org.getFs());
						}
					}
					initEntityBaseController.initEntityBaseAttribute(purchaseReturn);
					purchaseReturn = vixntBaseService.merge(purchaseReturn);
					Map<String, Object> params = getParams();
					params.put("purchaseArrival.id,"+SearchCondition.EQUAL, purchaseArrival.getId());
					List<PurchaseArrivalItems> purchaseArrivalItemsList = vixntBaseService.findAllByConditions(PurchaseArrivalItems.class, params);
					if(purchaseArrivalItemsList != null && purchaseArrivalItemsList.size() > 0){
						for (PurchaseArrivalItems purchaseArrivalItems : purchaseArrivalItemsList) {
							if(purchaseArrivalItems != null){
								PurchaseReturnItems purchaseReturnItems = new PurchaseReturnItems();
								purchaseReturnItems.setPurchaseReturn(purchaseReturn);
								purchaseReturnItems.setItemId(purchaseArrivalItems.getItemId());
								purchaseReturnItems.setItemName(purchaseArrivalItems.getItemName());
								purchaseReturnItems.setBarCode(purchaseArrivalItems.getBarCode());
								purchaseReturnItems.setPrice(purchaseArrivalItems.getPrice());
								purchaseReturnItems.setUnit(purchaseArrivalItems.getUnit());
								purchaseReturnItems.setAmount(Math.round(purchaseArrivalItems.getAmount()));
								purchaseReturnItems.setTaxRate(purchaseArrivalItems.getTaxRate());
								purchaseReturnItems = vixntBaseService.merge(purchaseReturnItems);
							}
						}
					}
					renderText("1:"+purchaseReturn.getId()+":转单成功!");
				}
			}
		} catch (Exception e) {
			renderText("0:"+"转单失败!");
			e.printStackTrace();
		}
	}
	public String goShowPurchaseReturn(){
		try {
			if(StringUtils.isNotEmpty(id)){
				purchaseReturn = vixntBaseService.findEntityById(PurchaseReturn.class, id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sourceClassPk," + SearchCondition.EQUAL, id);
				flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPurchaseReturn";
	}
	public String printPurchaseReturn() {
		try {
			if(StringUtils.isNotEmpty(id)){
				purchaseReturn = vixntBaseService.findEntityById(PurchaseReturn.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "printPurchaseReturn";
	}
	public String goSourceList(){
		try {
			try {
				if(StringUtils.isNotEmpty(id)){
					purchaseReturn = vixntBaseService.findEntityById(PurchaseReturn.class, id);
					if(null != purchaseReturn){
						baseEntityList = new ArrayList<>();
						baseEntityList.add(purchaseReturn);
						if(StringUtils.isNotEmpty(purchaseReturn.getSourceBillCode())&&StringUtils.isNotEmpty(purchaseReturn.getSourceClassName())){
							getSourceBaseEntity(baseEntityList, purchaseReturn);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSourceList";
	}
	public String goShowBeforeAndAfter() {
		try {
			if (StrUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				purchaseReturn = baseHibernateService.findEntityById(PurchaseReturn.class,id);
				if (purchaseReturn != null && purchaseReturn.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(purchaseReturn.getCreateTime()));
					params.put("isTemp", "0");
					params.put("isDelete", "0");
					params.put("tenantId", purchaseReturn.getTenantId());
					params.put("companyInnerCode", purchaseReturn.getCompanyInnerCode());
					if (StrUtils.isNotEmpty(str)) {
						if ("before".equals(str)) {
							purchaseReturn = (PurchaseReturn) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseReturn.getCreateTime()), params, purchaseReturn, "before");
						} else if ("after".equals(str)) {
							purchaseReturn = (PurchaseReturn) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseReturn.getCreateTime()), params, purchaseReturn, "after");
						}
					}
					if (purchaseReturn == null || StrUtils.isEmpty(purchaseReturn.getId())) {
						purchaseReturn = baseHibernateService.findEntityById(PurchaseReturn.class,id);
						Map<String, Object> params1 = new HashMap<String, Object>();
						params.put("sourceClassPk," + SearchCondition.EQUAL, purchaseReturn.getId());
						flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPurchaseReturn";
	}
	public String goAudit() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				purchaseReturn = vixntBaseService.findEntityById(PurchaseReturn.class, id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sourceClassPk," + SearchCondition.EQUAL, id);
				flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAudit";
	}
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	public PurchaseReturn getPurchaseReturn() {
		return purchaseReturn;
	}
	public void setPurchaseReturn(PurchaseReturn purchaseReturn) {
		this.purchaseReturn = purchaseReturn;
	}
	public PurchaseReturnItems getPurchaseReturnItems() {
		return purchaseReturnItems;
	}
	public void setPurchaseReturnItems(PurchaseReturnItems purchaseReturnItems) {
		this.purchaseReturnItems = purchaseReturnItems;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public List<BaseEntity> getBaseEntityList() {
		return baseEntityList;
	}

	public void setBaseEntityList(List<BaseEntity> baseEntityList) {
		this.baseEntityList = baseEntityList;
	}

	public String getIsAllowAudit() {
		return isAllowAudit;
	}

	public void setIsAllowAudit(String isAllowAudit) {
		this.isAllowAudit = isAllowAudit;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
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
}
