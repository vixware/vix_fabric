package com.vix.nvix.purchase.action;

import java.io.File;
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
import com.vix.common.share.entity.BaseEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.purchase.apply.entity.PurchaseApply;
import com.vix.mdm.purchase.apply.entity.PurchaseApplyDetails;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.nvix.common.base.action.VixntBaseAction;

import net.sf.json.JSONObject;
@Controller
@Scope("prototype")
public class VixntPurchaseApplyAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private PurchaseApply purchaseApply;
	private PurchaseApplyDetails purchaseApplyDetails;
	private Attachments attachments;
	private String purchaseApplyId;
	private List<CurrencyType> currencyTypeList;
	private String isAllowAudit;// 是否允许提交审批 1：是 0：否
	private String approval;
	@Autowired
	private OrderProcessController orderProcessController;
	private List<BaseEntity> baseEntityList;
	private List<FlowApprovalOpinion> flowApprovalOpinionList;
	private String str;
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
			String requirePerson = getDecodeRequestParameter("requirePerson");
			if (StringUtils.isNotEmpty(requirePerson)) {
				params.put("requirePerson," + SearchCondition.ANYLIKE, requirePerson);
			}
			String chooseSaleOrgDeptId = getDecodeRequestParameter("chooseSaleOrgDeptId");
			if (StringUtils.isNotEmpty(chooseSaleOrgDeptId)) {
				params.put("purchaseOrgId," + SearchCondition.EQUAL, chooseSaleOrgDeptId);
			}
			String createTime = getRequestParameter("createTime");
			if (StringUtils.isNotEmpty(createTime)) {
				params.put("createTime," + SearchCondition.BETWEENAND, DaysUtils.getBeginDay(dateFormat.parse(createTime.trim())) + "!" + DaysUtils.getEndDay(dateFormat.parse(createTime.trim())));
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseApply.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			isAllowAudit = isAllowAudit(BillType.PUR_APPLY);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchaseApply = vixntBaseService.findEntityById(PurchaseApply.class, id);
			} else {
				purchaseApply = new PurchaseApply();
				purchaseApply.setCode(autoCreateCode.getBillNO(BillType.PUR_APPLY));
				purchaseApply.setPurchasePerson(this.currentUserName());
				purchaseApply.setCreateTime(new Date());
				//				purchaseApply = purchaseApplyController.doSavePurchaseApply(purchaseApply);

				Employee employee = getEmployee();
				if (employee != null) {
					purchaseApply.setRequirePerson(employee.getName());
					OrganizationUnit org = employee.getOrganizationUnit();
					if (org != null) {
						purchaseApply.setPurchaseOrgId(org.getId());
						purchaseApply.setPurchaseOrg(org.getFs());
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
			if(StringUtils.isNotEmpty(purchaseApply.getId())){
				isSave = false;
			}
			if("1".equals(isAllowAudit(BillType.PUR_APPLY))){
				purchaseApply.setApprovalStatus("0");
			}
			purchaseApply.setPinyin(ChnToPinYin.getPYString(purchaseApply.getName()));
			initEntityBaseController.initEntityBaseAttribute(purchaseApply);
			purchaseApply = vixntBaseService.merge(purchaseApply);
			if(isSave){
				renderText("1:"+SAVE_SUCCESS+":"+purchaseApply.getId());
			}else{
				renderText("1:"+UPDATE_SUCCESS+":"+purchaseApply.getId());
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
	public void audit() {
		try {
			if("1".equals(isAllowAudit(BillType.PUR_APPLY))){
				purchaseApply.setApprovalStatus("0");
			}
			purchaseApply.setPinyin(ChnToPinYin.getPYString(purchaseApply.getName()));
			initEntityBaseController.initEntityBaseAttribute(purchaseApply);
			purchaseApply = vixntBaseService.merge(purchaseApply);
			if("1".equals(isAllowAudit(BillType.PUR_APPLY))){
				String response = dealStartAndSubmitByBillsCode(BillType.PUR_APPLY, purchaseApply);	
				if(StringUtils.isNotEmpty(response)){
					JSONObject json = JSONObject.fromObject(response);
					if(json.has("status")){
						if("1".equals(json.getString("status"))){
							purchaseApply.setApprovalStatus("1");
							purchaseApply = vixntBaseService.merge(purchaseApply);
							renderText(purchaseApply.getId()+":提交成功!");
						}
					}
				}
			}else {
				renderText("提交失败!没有绑定工作流");
			}
		} catch (Exception e) {
			renderText("提交失败!");
			e.printStackTrace();
		}
	}
	public void deleteById(){
		try {
			if(StringUtils.isNotEmpty(id)){
				purchaseApply = vixntBaseService.findEntityById(PurchaseApply.class, id);
				if(purchaseApply != null){
					vixntBaseService.deleteByEntity(purchaseApply);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			renderText(DELETE_FAIL);
			e.printStackTrace();
		}
	}
	
	public void goSingleListDetails(){
		try {
			Map<String,Object> params = getParams();
			Pager pager = getPager();
			String searchName = getDecodeRequestParameter("searchName");
			if(StringUtils.isNotEmpty(searchName)){
				params.put("itemName,"+SearchCondition.ANYLIKE, searchName);
			}
			if(StringUtils.isNotEmpty(purchaseApplyId)){
				params.put("purchaseApply.id,"+SearchCondition.EQUAL, purchaseApplyId);
				pager = vixntBaseService.findPagerByHqlConditions(pager, PurchaseApplyDetails.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdatePurchaseApplyDetails(){
		try {
			if(StringUtils.isNotEmpty(id)){
				purchaseApplyDetails = vixntBaseService.findEntityById(PurchaseApplyDetails.class, id);
			}else{
				purchaseApplyDetails = new PurchaseApplyDetails();
				if(StringUtils.isNotEmpty(purchaseApplyId)){
					purchaseApply = vixntBaseService.findEntityById(PurchaseApply.class, purchaseApplyId);
					if(purchaseApply != null){
						purchaseApplyDetails.setPurchaseApply(purchaseApply);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdatePurchaseApplyDetails";
	}
	public void saveOrUpdatePurchaseApplyDetails(){
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(purchaseApplyDetails.getId())){
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(purchaseApplyDetails);
			purchaseApplyDetails = vixntBaseService.merge(purchaseApplyDetails);
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
	public void deleteByDetailsId(){
		try {
			if(StringUtils.isNotEmpty(id)){
				PurchaseApplyDetails  applyDetails = vixntBaseService.findEntityById(PurchaseApplyDetails.class, id);
				if(applyDetails != null){
					vixntBaseService.deleteByEntity(applyDetails);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			renderText(DELETE_FAIL);
			e.printStackTrace();
		}
	}
	
	public void goSingleListAttachments(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String searchName = getDecodeRequestParameter("searchName");
			if(StringUtils.isNotEmpty(searchName)){
				params.put("name,"+SearchCondition.ANYLIKE, searchName);
			}
			if(StringUtils.isNotEmpty(purchaseApplyId)){
				params.put("purchaseApply.id,"+SearchCondition.EQUAL, purchaseApplyId);
				pager = vixntBaseService.findPagerByHqlConditions(pager, Attachments.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void saveOrUpdateAttachments(){
		try {
			String[] savePathAndName = saveUploadPic();
			if (savePathAndName != null && savePathAndName.length == 2) {
				Attachments attachments = new Attachments();
				attachments.setName(savePathAndName[1].toString());
				attachments.setPath("/img/ws/" + savePathAndName[1].toString());
				attachments.setCreateTime(new Date());
				if (StringUtils.isNotEmpty(purchaseApplyId)) {
					purchaseApply = vixntBaseService.findEntityById(PurchaseApply.class, purchaseApplyId);
					if (purchaseApply != null) {
						attachments.setPurchaseApply(purchaseApply);
					}
				}
				vixntBaseService.merge(attachments);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteAttachmentsById(){
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
		}
	}
	public String goShowBeforeAndAfter() {
		try {
			if (StrUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				purchaseApply = baseHibernateService.findEntityById(PurchaseApply.class,id);
				if (purchaseApply != null && purchaseApply.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(purchaseApply.getCreateTime()));
					params.put("isTemp", "0");
					params.put("isDelete", "0");
					params.put("tenantId", purchaseApply.getTenantId());
					params.put("companyInnerCode", purchaseApply.getCompanyInnerCode());
					if (StrUtils.isNotEmpty(str)) {
						if ("before".equals(str)) {
							purchaseApply = (PurchaseApply) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseApply.getCreateTime()), params, purchaseApply, "before");
						} else if ("after".equals(str)) {
							purchaseApply = (PurchaseApply) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(purchaseApply.getCreateTime()), params, purchaseApply, "after");
						}
					}
					if (purchaseApply == null || StrUtils.isEmpty(purchaseApply.getId())) {
						purchaseApply = baseHibernateService.findEntityById(PurchaseApply.class,id);
						Map<String, Object> params1 = new HashMap<String, Object>();
						params.put("sourceClassPk," + SearchCondition.EQUAL, purchaseApply.getId());
						flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	public String show() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				purchaseApply = vixntBaseService.findEntityById(PurchaseApply.class, id);
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("sourceClassPk," + SearchCondition.EQUAL, id);
				flowApprovalOpinionList = vixntBaseService.findAllByConditions(FlowApprovalOpinion.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	public String goSourceList() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				purchaseApply = vixntBaseService.findEntityById(PurchaseApply.class, id);
				if (purchaseApply != null) {
					baseEntityList = new ArrayList<>();
					baseEntityList.add(purchaseApply);
					if (StringUtils.isNotEmpty(purchaseApply.getSourceClassName()) && StringUtils.isNotEmpty(purchaseApply.getSourceBillCode())) {
						getSourceBaseEntity(baseEntityList, purchaseApply);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSourceList";
	}
	public String printPurchaseApply() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				purchaseApply = vixntBaseService.findEntityById(PurchaseApply.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "printPurchaseApply";
	}
	public String goAudit() {
		try {
			if(StringUtils.isNotEmpty(id)) {
				purchaseApply = vixntBaseService.findEntityById(PurchaseApply.class, id);
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
	public PurchaseApply getPurchaseApply() {
		return purchaseApply;
	}
	public void setPurchaseApply(PurchaseApply purchaseApply) {
		this.purchaseApply = purchaseApply;
	}
	public PurchaseApplyDetails getPurchaseApplyDetails() {
		return purchaseApplyDetails;
	}
	public void setPurchaseApplyDetails(PurchaseApplyDetails purchaseApplyDetails) {
		this.purchaseApplyDetails = purchaseApplyDetails;
	}
	public Attachments getAttachments() {
		return attachments;
	}
	public void setAttachments(Attachments attachments) {
		this.attachments = attachments;
	}
	public String getPurchaseApplyId() {
		return purchaseApplyId;
	}
	public void setPurchaseApplyId(String purchaseApplyId) {
		this.purchaseApplyId = purchaseApplyId;
	}
	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}
	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
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
