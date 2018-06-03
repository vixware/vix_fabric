package com.vix.nvix.customer.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.entity.PaymentCategory;
import com.vix.common.share.entity.PaymentType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.DateUtil;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.crm.project.entity.ActionHistory;
import com.vix.crm.project.entity.CrmProject;
import com.vix.crm.salechance.entity.BackSectionRecord;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.sales.order.entity.SalesOrder;

@Controller
@Scope("prototype")
public class NvixBackSectionRecordAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	
	private String id;
	private String name;
	private BackSectionRecord backSectionRecord;
	private String entityName;
	
	public void goListContent(){
		try {
			Pager pager = getPager();
			Map<String,Object> params = getParams();
			String owner = getRequestParameter("owner");
			if(StrUtils.objectIsNotNull(owner)){
				owner = decode(owner,"UTF-8");
				params.put("owner.name,"+ SearchCondition.ANYLIKE, owner);
			}
			String customerName = getRequestParameter("customerName");
			if(StrUtils.objectIsNotNull(customerName)){
				customerName = decode(customerName, "UTF-8");
				params.put("customerAccount.name," +SearchCondition.ANYLIKE, customerName);
			}
			pager = baseHibernateService.findPagerByHqlConditions(getPager(), BackSectionRecord.class, params);
			renderDataTable(pager,null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void goBackSectionRecordListContent(){
		try {
			String startTime = DateUtil.format(new Date());
			Pager pager = getPager();
			Map<String,Object> params = getParams();
			params.put("backSectionDate," + SearchCondition.BETWEENAND, startTime + " 00:00:00!" + startTime + " 23:59:59");
			pager = baseHibernateService.findPagerByHqlConditions(getPager(), BackSectionRecord.class, params);
			renderDataTable(pager,null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 跳转至用户修改页面 */
	private List<PaymentType> paymentTypeList;
	private List<PaymentCategory> paymentCategoryList;
	private List<SalesOrder> salesOrderList;
	private List<CrmProject> crmProjectList;
	public String goSaveOrUpdate() {
		try {
			paymentTypeList = baseHibernateService.findAllByEntityClass(PaymentType.class);
			paymentCategoryList = baseHibernateService.findAllByEntityClass(PaymentCategory.class);
			if(null != id && !"".equals(id)){
				backSectionRecord = baseHibernateService.findEntityById(BackSectionRecord.class,id);
				if(null != backSectionRecord.getCustomerAccount() && StrUtils.isNotEmpty(backSectionRecord.getCustomerAccount().getId())){
					salesOrderList = baseHibernateService.findAllByEntityClassAndAttribute(SalesOrder.class, "customerAccount.id", backSectionRecord.getCustomerAccount().getId());
					crmProjectList = baseHibernateService.findAllByEntityClassAndAttribute(CrmProject.class, "customerAccount.id", backSectionRecord.getCustomerAccount().getId());
				}
			}else{
				backSectionRecord = new BackSectionRecord();
				backSectionRecord.setBackSectionDate(new Date());
				Employee e = getEmployee();
				if(e != null){
					backSectionRecord.setOwner(e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StrUtils.objectIsNotNull(backSectionRecord.getId())){
				isSave = false;
			}else{
				backSectionRecord.setCreateTime(new Date());
				loadCommonData(backSectionRecord);
			}
			if(null == backSectionRecord.getSalesOrder() || StrUtils.isEmpty(backSectionRecord.getSalesOrder().getId())){
				backSectionRecord.setSalesOrder(null);
			}
			if(null == backSectionRecord.getCrmProject() || StrUtils.isEmpty(backSectionRecord.getCrmProject().getId())){
				backSectionRecord.setCrmProject(null);
			}
			String[] attrArray ={"customerAccount","crmProject","owner","paymentCategory","paymentType"};
			checkEntityNullValue(backSectionRecord,attrArray);
			
			if(null != backSectionRecord.getCustomerAccount() && null != backSectionRecord.getCustomerAccount().getId() &&
					!backSectionRecord.getCustomerAccount().getId().equals("") && !backSectionRecord.getCustomerAccount().getId().equals("0")){
				CustomerAccount ca = baseHibernateService.findEntityById(CustomerAccount.class, backSectionRecord.getCustomerAccount().getId());
				String name = ca.getShorterName();
				String py = ChnToPinYin.getPYString(name);
				backSectionRecord.setChineseFirstLetter(py.toUpperCase());
			}
			backSectionRecord = baseHibernateService.merge(backSectionRecord);
			// 增加行动历史和客户更新
			if(backSectionRecord.getCustomerAccount() != null && StrUtils.isNotEmpty(backSectionRecord.getCustomerAccount().getId())){
				CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, backSectionRecord.getCustomerAccount().getId());
				ActionHistory actionHistory = new ActionHistory();
				actionHistory.setSubject("回款记录");
				actionHistory.setActionDate(new Date());
				actionHistory.setCustomerAccount(customerAccount);
				actionHistory.setCrmProject(backSectionRecord.getCrmProject());
				actionHistory.setDescription("回款记录编辑");
				actionHistory = baseHibernateService.merge(actionHistory);
				customerAccount.setUpdateTime(new Date());
				customerAccount.setStagnateDay(0);
				customerAccount = baseHibernateService.merge(customerAccount);
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
	
	/** 处理删除操作 */
	public void deleteById() {
		try {
			BackSectionRecord pb = baseHibernateService.findEntityById(BackSectionRecord.class,id);
			if(null != pb){
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}else{
				renderText(DELETE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public BackSectionRecord getBackSectionRecord() {
		return backSectionRecord;
	}

	public void setBackSectionRecord(BackSectionRecord backSectionRecord) {
		this.backSectionRecord = backSectionRecord;
	}

	public List<PaymentType> getPaymentTypeList() {
		return paymentTypeList;
	}

	public void setPaymentTypeList(List<PaymentType> paymentTypeList) {
		this.paymentTypeList = paymentTypeList;
	}

	public List<PaymentCategory> getPaymentCategoryList() {
		return paymentCategoryList;
	}

	public void setPaymentCategoryList(List<PaymentCategory> paymentCategoryList) {
		this.paymentCategoryList = paymentCategoryList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public List<SalesOrder> getSalesOrderList() {
		return salesOrderList;
	}

	public void setSalesOrderList(List<SalesOrder> salesOrderList) {
		this.salesOrderList = salesOrderList;
	}

	public List<CrmProject> getCrmProjectList() {
		return crmProjectList;
	}

	public void setCrmProjectList(List<CrmProject> crmProjectList) {
		this.crmProjectList = crmProjectList;
	}
}