package com.vix.nvix.purchase.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.billtype.BillType;
import com.vix.common.job.entity.FlowApprovalOpinion;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.service.IItemService;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.order.entity.PurchaseOrderLineItem;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.purchase.plan.entity.PurchasePlanItems;
import com.vix.mdm.purchase.plan.entity.PurchasePlanPackage;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.nvix.common.base.action.VixntBaseAction;

import net.sf.json.JSONObject;
@Controller
@Scope("prototype")
public class VixntPurchasePlanAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IItemService itemService;
	@Autowired
	private OrderProcessController orderProcessController;
	private String id;
	private PurchasePlan purchasePlan;
	private PurchasePlanItems purchasePlanItems;
	private Attachments attachments;
	private List<MeasureUnit> measureUnitList;
	private String purchasePlanId;
	private String organdempid;
	private String isAllowAudit;// 是否允许提交审批 1：是 0：否
	private PurchaseOrder purchaseOrder;
	private String approval;
	private List<BaseEntity> baseEntityList;
	private String str;
	private List<FlowApprovalOpinion> flowApprovalOpinionList;
	public void goSingleList(){
		try {
			Map<String, Object> params = getParams();
			params.put("isReport," + SearchCondition.EQUAL, "0");
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, PurchasePlan.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String goSaveOrUpdate(){
		try {
			isAllowAudit = isAllowAudit(BillType.PUR_PLAN);
			if(StringUtils.isNotEmpty(id)){
				purchasePlan = vixntBaseService.findEntityById(PurchasePlan.class, id);
			}else{
				purchasePlan = new PurchasePlan();
				purchasePlan.setStatus("1");
				purchasePlan.setCode(autoCreateCode.getBillNO(BillType.PUR_PLAN));
				Employee employee = getEmployee();
				if (employee != null) {
					purchasePlan.setCreator(employee.getName());
				} else {
					purchasePlan.setCreator(SecurityUtil.getCurrentUserName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(purchasePlan.getId())) {
				isSave = false;
			}
			// 设置采购订单状态为未审批
			purchasePlan.setIsReport("0");
			// 采购计划明细
			initEntityBaseController.initEntityBaseAttribute(purchasePlan);
			//计划
			if (StringUtils.isEmpty(purchasePlan.getStatus())) {
				purchasePlan.setStatus("1");
			}
			// 获取当前员工信息
			Employee employee = getEmployee();
			if (employee != null) {
				purchasePlan.setEmployee(employee);
			}
			if (purchasePlan.getPurchasePlanPackage() == null || StringUtils.isEmpty(purchasePlan.getPurchasePlanPackage().getId())) {
				purchasePlan.setPurchasePlanPackage(null);
			}
			if("1".equals(isAllowAudit(BillType.PUR_PLAN))){
				purchasePlan.setApprovalStatus("0");
			}
			purchasePlan.setCreateTime(new Date());
			purchasePlan.setUpdateTime(new Date());
			purchasePlan = vixntBaseService.merge(purchasePlan);
			if ("approval".equals(approval)) {
				if("1".equals(isAllowAudit(BillType.PUR_PLAN))){
					String response = dealStartAndSubmitByBillsCode(BillType.PUR_PLAN, purchasePlan);
					if(StringUtils.isNotEmpty(response)){
						JSONObject json = JSONObject.fromObject(response);
						if(json.has("status")){
							if("1".equals(json.getString("status"))){
								purchasePlan.setApprovalStatus("1");//设置订单提交了审批
								purchasePlan = vixntBaseService.merge(purchasePlan);
								renderText(purchasePlan.getId()+":提交成功!");
							}
						}
					}
				}
			}
			if (isSave) {
				renderText("1:"+purchasePlan.getId()+":"+SAVE_SUCCESS);
			} else {
				renderText("1:"+purchasePlan.getId()+":"+UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:"+SAVE_FAIL);
			} else {
				renderText("0:"+UPDATE_FAIL);
			}
		}
	}
	/** 处理修改操作 */
	public void audit() {
		try {
			if (StringUtils.isNotEmpty(purchasePlan.getId())) {
				purchasePlan.setUpdateTime(new Date());
			}else {
				purchasePlan.setCreateTime(new Date());
			}
			// 设置采购订单状态为未审批
			purchasePlan.setIsReport("0");
			// 采购计划明细
			initEntityBaseController.initEntityBaseAttribute(purchasePlan);
			//计划
			if (StringUtils.isEmpty(purchasePlan.getStatus())) {
				purchasePlan.setStatus("1");
			}
			// 获取当前员工信息
			Employee employee = getEmployee();
			if (employee != null) {
				purchasePlan.setEmployee(employee);
			}
			if (purchasePlan.getPurchasePlanPackage() == null || StringUtils.isEmpty(purchasePlan.getPurchasePlanPackage().getId())) {
				purchasePlan.setPurchasePlanPackage(null);
			}
			if("1".equals(isAllowAudit(BillType.PUR_PLAN))){
				purchasePlan.setApprovalStatus("0");
			}
			purchasePlan = vixntBaseService.merge(purchasePlan);
			if("1".equals(isAllowAudit(BillType.PUR_PLAN))){
				String response = dealStartAndSubmitByBillsCode(BillType.PUR_PLAN, purchasePlan);
				if(StringUtils.isNotEmpty(response)){
					JSONObject json = JSONObject.fromObject(response);
					if(json.has("status")){
						if("1".equals(json.getString("status"))){
							purchasePlan.setApprovalStatus("1");//设置订单提交了审批
							purchasePlan = vixntBaseService.merge(purchasePlan);
							renderText(purchasePlan.getId()+":提交成功!");
						}
					}
				}
			}else {
				renderText("提交失败!未绑定审批流程!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("提交失败!");
		}
	}
	
	public String goAudit() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				purchasePlan = vixntBaseService.findEntityById(PurchasePlan.class, id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sourceClassPk," + SearchCondition.EQUAL, id);
				flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAudit";
	}
	public void changePurchasePlanPrice(){
		try {
			if(StringUtils.isNotEmpty(id)){
				purchasePlan = vixntBaseService.findEntityById(PurchasePlan.class, id);
				Double price = 0D;
				Double amount = 0D;
				if(purchasePlan != null){
					Map<String, Object> params = getParams();
					params.put("purchasePlan.id,"+SearchCondition.EQUAL, purchasePlan.getId());
					List<PurchasePlanItems> purchasePlanItemsset = vixntBaseService.findAllByConditions(PurchasePlanItems.class, params);
					if(purchasePlanItemsset != null && purchasePlanItemsset.size() > 0){
						for (PurchasePlanItems purchasePlanItems : purchasePlanItemsset) {
							price += purchasePlanItems.getPrice();
							amount += purchasePlanItems.getAmount();
						}
					}
				}
				purchasePlan.setPrice(price);
				purchasePlan.setAmount(amount);
				purchasePlan = vixntBaseService.merge(purchasePlan);
				renderText(purchasePlan.getPrice()+":"+purchasePlan.getAmount());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 处理删除操作 */
	public void deleteById() {
		try {
			PurchasePlan purchasePlan = vixntBaseService.findEntityById(PurchasePlan.class, id);
			if (null != purchasePlan) {
				vixntBaseService.deleteByEntity(purchasePlan);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	/**
	 * 获取明细列表
	 */
	public void goPurchasePlanItemsSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String searchName = getDecodeRequestParameter("searchName");
			if(StringUtils.isNotEmpty(searchName)){
				params.put("itemName,"+SearchCondition.ANYLIKE, searchName);
			}
			if(StringUtils.isNotEmpty(id)){
				params.put("purchasePlan.id,"+SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, PurchasePlanItems.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增计划明细
	 * @return
	 */
	public String goSaveOrUpdatePurchasePlanItems() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			measureUnitList = vixntBaseService.findAllByConditions(MeasureUnit.class, params);
			if (StringUtils.isNotEmpty(id) && !"undefined".equals(id)) {
				purchasePlanItems = vixntBaseService.findEntityById(PurchasePlanItems.class, id);
			} else {
				purchasePlanItems = new PurchasePlanItems();
				if(StringUtils.isNotEmpty(purchasePlanId)){
					purchasePlan = vixntBaseService.findEntityById(PurchasePlan.class, purchasePlanId);
					if(purchasePlan != null){
						purchasePlanItems.setPurchasePlan(purchasePlan);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goSaveOrUpdatePurchasePlanItems";
	}
	/**
	 * 保存计划明细
	 * @return
	 */
	public void saveOrUpdatePurchasePlanItems() {
		boolean isSave = true;
		try {
			if (null != purchasePlanItems.getId() && !"".equals(purchasePlanItems.getId())) {
				isSave = false;
			}
			if(StringUtils.isNotEmpty(purchasePlanItems.getPurchasePlan().getId())){
				Map<String, Object> params = getParams();
				params.put("purchasePlan.id,"+SearchCondition.EQUAL, purchasePlanItems.getPurchasePlan().getId());
				List<PurchasePlanItems> purchasePlanItemsList = vixntBaseService.findAllByConditions(PurchasePlanItems.class, params);
				for (PurchasePlanItems purchasePlanItems1 : purchasePlanItemsList) {
					if(purchasePlanItems1.getItemCode().equals(purchasePlanItems.getItemCode())){
						purchasePlanItems1.setAmount(purchasePlanItems1.getAmount()+purchasePlanItems.getAmount());
						purchasePlanItems1.setPrice(purchasePlanItems1.getAmount() * purchasePlanItems1.getUnitcost());
						vixntBaseService.merge(purchasePlanItems1);
						if (isSave) {
							renderText(SAVE_SUCCESS);
						} else {
							renderText(UPDATE_SUCCESS);
						}
						return;
					}
				}
			}
			Double price = 0D;
			if (purchasePlanItems.getAmount() != null && purchasePlanItems.getUnitcost() != null) {
				price = purchasePlanItems.getAmount() * purchasePlanItems.getUnitcost();
				purchasePlanItems.setPrice(price);
			}
			
			//
			vixntBaseService.merge(purchasePlanItems);
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
	}
	
	public void deletePurchasePlanItemsById(){
		try {
			if(StringUtils.isNotEmpty(id)){
				PurchasePlanItems purchasePlanItems = vixntBaseService.findEntityById(PurchasePlanItems.class, id);
				if(purchasePlanItems != null){
					vixntBaseService.deleteByEntity(purchasePlanItems);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			renderText(DELETE_FAIL);
			e.printStackTrace();
		}
	}
	
	public void goDetailSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			params.put("isTemp,"+SearchCondition.NOEQUAL, "1");
			params.put("itemClass," + SearchCondition.IN, "goods,finishedgoods");
			String categoryId = getDecodeRequestParameter("categoryId");
			if (null != categoryId && !"".equals(categoryId)) {
				itemService.findPagerByItemCatalogId(getPager(), categoryId, params);
			} else {
				itemService.findPagerByHqlConditions(getPager(),Item.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void  getAttachmentsList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String searchName = getDecodeRequestParameter("searchName");
			if(StringUtils.isNotEmpty(searchName)){
				params.put("name,"+SearchCondition.ANYLIKE, searchName);
			}
			if(StringUtils.isNotEmpty(id)){
				params.put("purchasePlan.id,"+SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, Attachments.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存附件
	 */
	public void saveOrUpdateAttachments() {
		try {
			String[] savePathAndName = saveUploadPic();
			if (savePathAndName != null && savePathAndName.length == 2) {
				Attachments attachments = new Attachments();
				attachments.setName(savePathAndName[1].toString());
				attachments.setPath("/img/ws/" + savePathAndName[1].toString());
				attachments.setCreateTime(new Date());
				if (StringUtils.isNotEmpty(id)) {
					purchasePlan = vixntBaseService.findEntityById(PurchasePlan.class, id);
					if (purchasePlan != null) {
						attachments.setPurchasePlan(purchasePlan);
					}
				}
				vixntBaseService.merge(attachments);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除附件
	 */
	public void deleteUploaderById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				Attachments attachments = vixntBaseService.findEntityById(Attachments.class, id);
				if (null != attachments) {
					String fileName = attachments.getName();
					String baseFolder = "c:/img/";
					String downloadFile = baseFolder + fileName;
					File f = new File(downloadFile); // 输入要删除的文件位置
					if (f.exists()) {
						f.delete();
					}
					vixntBaseService.deleteByEntity(attachments);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
	}
	
	/**
	 * 计划任务
	 * 
	 * @return
	 */
	public String goWriteTaskPlan() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchasePlan = vixntBaseService.findEntityById(PurchasePlan.class, id);
			} else {
				purchasePlan = new PurchasePlan();
				purchasePlan.setStatus("2");
				purchasePlan.setPurchasePlanCode(autoCreateCode.getBillNO(BillType.PUR_PLAN));
				Employee employee = getEmployee();
				if (employee != null) {
					purchasePlan.setCreator(employee.getName());
				} else {
					purchasePlan.setCreator(SecurityUtil.getCurrentUserName());
				}
				//purchasePlan = purchasePlanController.doSavePurchasePlan(purchasePlan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goWriteTaskPlan";
	}
	//计划下发到部门或人
	public String planToDoP() {
		boolean isSave = true;
		try {
			PurchasePlanPackage purchasePlanPackage = new PurchasePlanPackage();
			purchasePlanPackage.setCode(autoCreateCode.getBillNO(BillType.PUR_PLAN));
			purchasePlanPackage.setName(purchasePlan.getPurchasePlanName());
			purchasePlanPackage.setPlanType("T");
			purchasePlanPackage.setStatus("0");
			initEntityBaseController.initEntityBaseAttribute(purchasePlanPackage);
			purchasePlanPackage = vixntBaseService.merge(purchasePlanPackage);
			if (StringUtils.isNotEmpty(purchasePlan.getExecuteType())) {
				//下发计划到部门或个人
				if ("D".equals(purchasePlan.getExecuteType())) {
					organdempid = StringUtils.substring(organdempid, 0, organdempid.length() - 1);
					OrganizationUnit organizationUnit = vixntBaseService.findEntityById(OrganizationUnit.class, organdempid);
					if (organizationUnit != null && organizationUnit.getId() != null) {
						Map<String, Object> pa = new HashMap<String, Object>();
						pa.put("organizationUnit.id," + SearchCondition.EQUAL, organizationUnit.getId());
						List<Employee> employeeList = vixntBaseService.findAllByConditions(Employee.class, pa);
						for (Employee e : employeeList) {
							if (e != null) {
								PurchasePlan p = new PurchasePlan();
								BeanUtils.copyProperties(purchasePlan, p, new String[] { "id" });
								// 设置采购订单状态为未审批
								p.setStatus("S1");
								p.setIsReport("0");
								initEntityBaseController.initEntityBaseAttribute(p);
								//处理修改留痕
								billMarkProcessController.processMark(p, updateField);
								//计划任务
								p.setStatus("2");
								p.setEmployee(e);
								if (p.getSupplier() == null || StringUtils.isEmpty(p.getSupplier().getId())) {
									p.setSupplier(null);
								}
								p.setPurchasePlanCode(autoCreateCode.getBillNO(BillType.PUR_PLAN));
								p.setPurchasePlanPackage(purchasePlanPackage);
								p.setCreator(e.getName());
								p = vixntBaseService.merge(p);
							}
						}
					}
				} else if ("P".equals(purchasePlan.getExecuteType())) {
					Employee employee = vixntBaseService.findEntityById(Employee.class, organdempid);
					if (employee != null) {
						purchasePlan.setEmployee(employee);
						// 设置采购订单状态为未审批
						purchasePlan.setStatus("S1");
						purchasePlan.setIsReport("0");
						// 采购计划明细
						initEntityBaseController.initEntityBaseAttribute(purchasePlan);
						//处理修改留痕
						billMarkProcessController.processMark(purchasePlan, updateField);
						//计划任务
						purchasePlan.setStatus("2");
						purchasePlan.setCreator(employee.getName());
						purchasePlan.setPurchasePlanCode(autoCreateCode.getBillNO(BillType.PUR_PLAN));
						purchasePlan.setPurchasePlanPackage(purchasePlanPackage);
						purchasePlan = vixntBaseService.merge(purchasePlan);
					}
				}
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
	 * 生成采购订单
	 */
	public void createPurchaseOrder(){
		try {
			if(StringUtils.isNotEmpty(id)){
				purchasePlan = vixntBaseService.findEntityById(PurchasePlan.class, id);
				if(null != purchasePlan){
					Map<String, Object> params = getParams();
					params.put("purchasePlan.id,"+SearchCondition.EQUAL, purchasePlan.getId());
					PurchaseOrder purchaseOrder = new PurchaseOrder();
					purchaseOrder.setName("采购订单" + dateFormat.format(new Date()));
					purchaseOrder.setCode(autoCreateCode.getBillNO(BillType.PUR_ORDER));
					purchaseOrder.setCreateTime(new Date());
					Employee employee = getEmployee();
					if (employee != null) {
						purchaseOrder.setCreator(employee.getName());
						purchaseOrder.setPurchasePerson(employee.getName());
						purchaseOrder.setPurchasePersonId(employee.getId());
					}
					purchaseOrder.setSupplier(purchasePlan.getSupplier());
					purchaseOrder.setMark(1L);
					purchaseOrder.setStatus("4");
					purchaseOrder.setType("1");
					purchaseOrder.setTotalAmount(purchasePlan.getPrice());
					purchaseOrder.setSourceBillCode(purchasePlan.getCode());
					purchaseOrder.setSourceClassName(purchasePlan.getClass().getName());
					purchaseOrder = vixntBaseService.merge(purchaseOrder);
					List<PurchasePlanItems> purchasePlanItemsList = vixntBaseService.findAllByConditions(PurchasePlanItems.class, params);
					Double totalAmount = 0d;
					for (PurchasePlanItems purchasePlanItems : purchasePlanItemsList) {
						if(null != purchasePlanItems){
							PurchaseOrderLineItem purchaseOrderLineItem = new PurchaseOrderLineItem();
							purchaseOrderLineItem.setPurchaseOrder(purchaseOrder);
							purchaseOrderLineItem.setItemCode(purchasePlanItems.getItemCode());
							purchaseOrderLineItem.setItemName(purchasePlanItems.getItemName());
							purchaseOrderLineItem.setSpecification(purchasePlanItems.getSpecification());
							purchaseOrderLineItem.setUnit(purchasePlanItems.getMeasureUnit().getName());
							purchaseOrderLineItem.setCreateTime(new Date());
							purchaseOrderLineItem.setUpdateTime(new Date());
							purchaseOrderLineItem.setAmount(purchasePlanItems.getAmount());
							purchaseOrderLineItem.setPrice(purchasePlanItems.getUnitcost());
							purchaseOrderLineItem = vixntBaseService.merge(purchaseOrderLineItem);
							totalAmount += purchaseOrderLineItem.getPrice() * purchaseOrderLineItem.getAmount();
						}
					}
					purchasePlan.setStatus("2");
					purchasePlan = vixntBaseService.merge(purchasePlan);
					renderText("1:生成成功!:"+purchaseOrder.getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:生成失败!");
		}
	}
	public String goSourceList() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				purchasePlan = vixntBaseService.findEntityById(PurchasePlan.class, id);
				if (purchasePlan != null) {
					baseEntityList = new ArrayList<>();
					baseEntityList.add(purchasePlan);
					if (StringUtils.isNotEmpty(purchasePlan.getSourceClassName()) && StringUtils.isNotEmpty(purchasePlan.getSourceBillCode())) {
						getSourceBaseEntity(baseEntityList, purchasePlan);
					}
				}
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
				purchasePlan = baseHibernateService.findEntityById(PurchasePlan.class,id);
				if (purchasePlan != null && purchasePlan.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(purchasePlan.getCreateTime()));
					params.put("isTemp", "0");
					params.put("isDelete", "0");
					params.put("tenantId", purchasePlan.getTenantId());
					params.put("companyInnerCode", purchasePlan.getCompanyInnerCode());
					if (StrUtils.isNotEmpty(str)) {
						if ("before".equals(str)) {
							purchasePlan = (PurchasePlan) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchasePlan.getCreateTime()), params, purchasePlan, "before");
						} else if ("after".equals(str)) {
							purchasePlan = (PurchasePlan) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchasePlan.getCreateTime()), params, purchasePlan, "after");
						}
					}
					if (purchasePlan == null || StrUtils.isEmpty(purchasePlan.getId())) {
						purchasePlan = baseHibernateService.findEntityById(PurchasePlan.class,id);
						Map<String, Object> params1 = new HashMap<String, Object>();
						params.put("sourceClassPk," + SearchCondition.EQUAL, purchasePlan.getId());
						flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	public String show(){
		try {
			if(StringUtils.isNotEmpty(id)){
				purchasePlan = vixntBaseService.findEntityById(PurchasePlan.class, id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sourceClassPk," + SearchCondition.EQUAL, id);
				flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	public String printPurchasePlan() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				purchasePlan = vixntBaseService.findEntityById(PurchasePlan.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "printPurchasePlan";
	}
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	public PurchasePlan getPurchasePlan() {
		return purchasePlan;
	}
	public void setPurchasePlan(PurchasePlan purchasePlan) {
		this.purchasePlan = purchasePlan;
	}

	public PurchasePlanItems getPurchasePlanItems() {
		return purchasePlanItems;
	}

	public void setPurchasePlanItems(PurchasePlanItems purchasePlanItems) {
		this.purchasePlanItems = purchasePlanItems;
	}

	public Attachments getAttachments() {
		return attachments;
	}

	public void setAttachments(Attachments attachments) {
		this.attachments = attachments;
	}

	public List<MeasureUnit> getMeasureUnitList() {
		return measureUnitList;
	}

	public void setMeasureUnitList(List<MeasureUnit> measureUnitList) {
		this.measureUnitList = measureUnitList;
	}

	public String getPurchasePlanId() {
		return purchasePlanId;
	}

	public void setPurchasePlanId(String purchasePlanId) {
		this.purchasePlanId = purchasePlanId;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
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
}
