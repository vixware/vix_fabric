package com.vix.nvix.customer.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.RepairOrderType;
import com.vix.crm.project.entity.ActionHistory;
import com.vix.crm.service.entity.RepairOrder;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;

@Controller
@Scope("prototype")
public class NvixRepairOrderAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	
	private String id;
	private String name;
	private String companyCode;
	private RepairOrder repairOrder;
	private String entityName;
	
	public void goListContent(){
		try {
			Pager pager = getPager();
			
			Map<String,Object> params = getParams();
			
			String subject = getRequestParameter("subject");
			if(StrUtils.objectIsNotNull(subject)){
				subject = decode(subject, "UTF-8");
				params.put("subject,"+SearchCondition.ANYLIKE, subject);
			}
			String crmProject = getRequestParameter("crmProject");
			if(StrUtils.objectIsNotNull(crmProject)){
				crmProject = decode(crmProject, "UTF-8");
				params.put("crmProject.subject,"+SearchCondition.ANYLIKE, crmProject);
			}
			String customerName = getRequestParameter("customerName");
			if(StrUtils.objectIsNotNull(customerName)){
				customerName = decode(customerName, "UTF-8");
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE, customerName);
			}
			baseHibernateService.findPagerByHqlConditions(pager, RepairOrder.class, params);
			renderDataTable(pager,null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 跳转至用户修改页面 */
	private List<RepairOrderType> repairOrderTypeList;
	private List<ContactPerson> contactPersonList;
	public String goSaveOrUpdate() {
		try {
			repairOrderTypeList = baseHibernateService.findAllByEntityClassAndAttribute(RepairOrderType.class, "enable", "1");
			if(StrUtils.isNotEmpty(id) && !"0".equals(id)){
				repairOrder = baseHibernateService.findEntityById(RepairOrder.class,id);
				if(null != repairOrder.getCustomerAccount().getId() && StrUtils.isNotEmpty(repairOrder.getCustomerAccount().getId())){
					contactPersonList = baseHibernateService.findAllByEntityClassAndAttribute(ContactPerson.class, "customerAccount.id", repairOrder.getCustomerAccount().getId());
				}
			}else{
				repairOrder = new RepairOrder();
				repairOrder.setReceiveDate(new Date());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmsss");
				repairOrder.setSubject("WXGD"+sdf.format(new Date()));
				Employee e = getEmployee();
				if(e != null){
					repairOrder.setEmployee(e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	/** 处理修改操作  */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StrUtils.objectIsNotNull(repairOrder.getId())){
				isSave = false;
			}else{
				repairOrder.setCreateTime(new Date());
				loadCommonData(repairOrder);
			}
			
			String [] attrArray = {"employee","crmProject","customerAccount","contactPerson","repairOrderType","organizationUnit"};
			checkEntityNullValue(repairOrder,attrArray);
			
			repairOrder = baseHibernateService.merge(repairOrder);
			// 增加行动历史和客户更新
			if(repairOrder.getCustomerAccount() != null && StrUtils.isNotEmpty(repairOrder.getCustomerAccount().getId())){
				CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, repairOrder.getCustomerAccount().getId());
				ActionHistory actionHistory = new ActionHistory();
				actionHistory.setSubject("维修工单");
				actionHistory.setActionDate(new Date());
				actionHistory.setCustomerAccount(customerAccount);
				actionHistory.setCrmProject(repairOrder.getCrmProject());
				actionHistory.setDescription("维修工单编辑");
				actionHistory = baseHibernateService.merge(actionHistory);
				customerAccount.setUpdateTime(new Date());
				customerAccount.setStagnateDay(0);
				customerAccount = baseHibernateService.merge(customerAccount);
			}
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
		}
	}
	
	/** 处理删除操作 */
	public void deleteById() {
		try {
			if(StrUtils.isNotEmpty(id)){
				RepairOrder pb = baseHibernateService.findEntityById(RepairOrder.class,id);
				if (null != pb) {
					baseHibernateService.deleteByEntity(pb);
					renderText(DELETE_SUCCESS);
				}else{
					renderText(DELETE_FAIL);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
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

	public RepairOrder getRepairOrder() {
		return repairOrder;
	}

	public void setRepairOrder(RepairOrder repairOrder) {
		this.repairOrder = repairOrder;
	}

	public List<RepairOrderType> getRepairOrderTypeList() {
		return repairOrderTypeList;
	}

	public void setRepairOrderTypeList(List<RepairOrderType> repairOrderTypeList) {
		this.repairOrderTypeList = repairOrderTypeList;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public List<ContactPerson> getContactPersonList() {
		return contactPersonList;
	}

	public void setContactPersonList(List<ContactPerson> contactPersonList) {
		this.contactPersonList = contactPersonList;
	}

}