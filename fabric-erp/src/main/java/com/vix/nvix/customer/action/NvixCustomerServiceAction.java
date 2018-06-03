package com.vix.nvix.customer.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.ServiceMode;
import com.vix.crm.base.entity.ServiceType;
import com.vix.crm.project.entity.ActionHistory;
import com.vix.crm.service.entity.CustomerServices;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;

@Controller
@Scope("prototype")
public class NvixCustomerServiceAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	
	private String id;
	private String name;
	private String companyCode;
	private CustomerServices customerServices;
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
			baseHibernateService.findPagerByHqlConditions(pager, CustomerServices.class, params);
			renderDataTable(pager,null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 跳转至用户修改页面 */
	private List<ServiceType> serviceTypeList;
	private List<ServiceMode> serviceModeList;
	private List<ContactPerson> contactPersonList;
	public String goSaveOrUpdate() {
		try {
			serviceTypeList = baseHibernateService.findAllByEntityClassAndAttribute(ServiceType.class, "enable", "1");
			serviceModeList = baseHibernateService.findAllByEntityClassAndAttribute(ServiceMode.class, "enable", "1");
			if(StrUtils.isNotEmpty(id) && !"0".equals(id)){
				customerServices = baseHibernateService.findEntityById(CustomerServices.class,id);
				if(null != customerServices.getCustomerAccount().getId() && StrUtils.isNotEmpty(customerServices.getCustomerAccount().getId())){
					contactPersonList = baseHibernateService.findAllByEntityClassAndAttribute(ContactPerson.class, "customerAccount.id", customerServices.getCustomerAccount().getId());
				}
			}else{
				customerServices = new CustomerServices();
				customerServices.setStartDate(new Date());
				Employee e = getEmployee();
				if(e != null){
					customerServices.setEmployee(e);
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
			if(StrUtils.objectIsNotNull(customerServices.getId())){
				isSave = false;
			}else{
				customerServices.setCreateTime(new Date());
				loadCommonData(customerServices);
			}
			
			String [] attrArray = {"employee","crmProject","customerAccount","contactPerson","serviceType","serviceMode","customerServiceStatus"};
			checkEntityNullValue(customerServices,attrArray);
			
			customerServices = baseHibernateService.merge(customerServices);
			// 增加行动历史和客户更新
			if(customerServices.getCustomerAccount() != null && StrUtils.isNotEmpty(customerServices.getCustomerAccount().getId())){
				CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, customerServices.getCustomerAccount().getId());
				ActionHistory actionHistory = new ActionHistory();
				actionHistory.setSubject("客户服务");
				actionHistory.setActionDate(new Date());
				actionHistory.setCustomerAccount(customerAccount);
				actionHistory.setCrmProject(customerServices.getCrmProject());
				actionHistory.setDescription("客户服务编辑");
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
				CustomerServices pb = baseHibernateService.findEntityById(CustomerServices.class,id);
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

	public CustomerServices getCustomerServices() {
		return customerServices;
	}

	public void setCustomerServices(CustomerServices customerServices) {
		this.customerServices = customerServices;
	}

	public List<ServiceType> getServiceTypeList() {
		return serviceTypeList;
	}

	public void setServiceTypeList(List<ServiceType> serviceTypeList) {
		this.serviceTypeList = serviceTypeList;
	}

	public List<ServiceMode> getServiceModeList() {
		return serviceModeList;
	}

	public void setServiceModeList(List<ServiceMode> serviceModeList) {
		this.serviceModeList = serviceModeList;
	}

}