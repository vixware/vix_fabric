package com.vix.nvix.customer.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.SaleChanceStatus;
import com.vix.crm.base.entity.SaleSource;
import com.vix.crm.base.entity.SaleStage;
import com.vix.crm.base.entity.SaleType;
import com.vix.crm.project.entity.ActionHistory;
import com.vix.crm.project.entity.CrmProject;
import com.vix.crm.salechance.entity.SaleChance;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;

@Controller
@Scope("prototype")
public class NvixChanceAndTrackingAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	
	private String id;
	private String name;
	private String companyCode;
	private SaleChance saleChance;
	private String entityName;
	private List<ContactPerson> contactPersonList;
	
	public void goListContent(){
		try {
			Pager pager = getPager();
			
			Map<String,Object> params = getParams();
			params.put("isDeleted,"+SearchCondition.NOEQUAL, "1");
			
			String subject = getRequestParameter("subject");
			if(StrUtils.objectIsNotNull(subject)){
				subject = decode(subject, "UTF-8");
				params.put("subject,"+SearchCondition.ANYLIKE, subject);
			}
			
			String customerName = getRequestParameter("customerName");
			if(StrUtils.objectIsNotNull(customerName)){
				customerName = decode(customerName, "UTF-8");
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE, customerName);
			}
			baseHibernateService.findPagerByHqlConditions(pager, SaleChance.class, params);
			renderDataTable(pager,null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private List<CurrencyType> currencyTypeList;
	private List<SaleStage> saleStageList;
	private List<SaleChanceStatus> saleChanceStatusList;
	private List<SaleType> saleTypeList;
	private List<SaleSource> saleSourceList;
	private List<CrmProject> crmProjectList;
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			saleTypeList = baseHibernateService.findAllByEntityClass(SaleType.class);
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			saleStageList =  baseHibernateService.findAllByEntityClass(SaleStage.class);
			saleChanceStatusList = baseHibernateService.findAllByEntityClass(SaleChanceStatus.class);
			saleSourceList = baseHibernateService.findAllByEntityClass(SaleSource.class);
			if(null != id && StrUtils.isNotEmpty(id)){
				saleChance = baseHibernateService.findEntityById(SaleChance.class,id);
				if(null != saleChance.getCustomerAccount().getId() && StrUtils.isNotEmpty(saleChance.getCustomerAccount().getId())){
					contactPersonList = baseHibernateService.findAllByEntityClassAndAttribute(ContactPerson.class, "customerAccount.id", saleChance.getCustomerAccount().getId());
					crmProjectList = baseHibernateService.findAllByEntityClassAndAttribute(CrmProject.class, "customerAccount.id", saleChance.getCustomerAccount().getId());
				}
				/*if(null == saleChance.getCreatedBy() || "".equals(saleChance.getCreatedBy())){
					if(null != SecurityUtil.getCurrentEmpId() && !"".equals(SecurityUtil.getCurrentEmpId())){
						Employee emp = baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
						if(null != emp){
							saleChance.setCreatedBy(emp.getName());
						}else{
							saleChance.setCreatedBy(SecurityUtil.getCurrentUserName());
						}
					}else{
						saleChance.setCreatedBy(SecurityUtil.getCurrentUserName());
					}
				}*/
			}else{
				saleChance = new SaleChance();
				//saleChance.setCreatedBy(SecurityUtil.getCurrentUserName());
				Employee employee = getEmployee();
				if(employee != null){
					saleChance.setEmployee(employee);
				}
				saleChance.setFindDate(new Date());
				saleChance.setDateEntered(new Date());
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
			if(StrUtils.objectIsNotNull(saleChance.getId())){
				isSave = false;
				saleChance.setUpdateTime(new Date());
			}else{
				saleChance.setCreateTime(new Date());
				saleChance.setUpdateTime(new Date());
				saleChance.setIsDeleted("0");
				loadCommonData(saleChance);
			}
			
			String[] attrArray ={"saleChanceStatus","currencyType","customerAccount","contactPerson","saleStage","crmProject","saleType","saleSource","employee"};
			checkEntityNullValue(saleChance,attrArray);
			
			String name = saleChance.getSubject();
			String py = ChnToPinYin.getPYString(name);
			saleChance.setChineseFirstLetter(py.toUpperCase());
			saleChance.setDateModified(new Date());
			saleChance = baseHibernateService.merge(saleChance);
			// 增加行动历史和客户更新
			if(saleChance.getCustomerAccount() != null && StrUtils.isNotEmpty(saleChance.getCustomerAccount().getId())){
				CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, saleChance.getCustomerAccount().getId());
				ActionHistory actionHistory = new ActionHistory();
				actionHistory.setSubject("销售机会");
				actionHistory.setActionDate(new Date());
				actionHistory.setCustomerAccount(customerAccount);
				actionHistory.setCrmProject(saleChance.getCrmProject());
				actionHistory.setDescription("销售机会编辑");
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
			SaleChance pb = baseHibernateService.findEntityById(SaleChance.class,id);
			if(null != pb){
				//pb.setIsDeleted("1");
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
	

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public List<SaleStage> getSaleStageList() {
		return saleStageList;
	}

	public void setSaleStageList(List<SaleStage> saleStageList) {
		this.saleStageList = saleStageList;
	}

	public List<SaleChanceStatus> getSaleChanceStatusList() {
		return saleChanceStatusList;
	}

	public void setSaleChanceStatusList(List<SaleChanceStatus> saleChanceStatusList) {
		this.saleChanceStatusList = saleChanceStatusList;
	}

	public List<SaleType> getSaleTypeList() {
		return saleTypeList;
	}

	public void setSaleTypeList(List<SaleType> saleTypeList) {
		this.saleTypeList = saleTypeList;
	}

	public SaleChance getSaleChance() {
		return saleChance;
	}

	public void setSaleChance(SaleChance saleChance) {
		this.saleChance = saleChance;
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

	public List<ContactPerson> getContactPersonList() {
		return contactPersonList;
	}

	public void setContactPersonList(List<ContactPerson> contactPersonList) {
		this.contactPersonList = contactPersonList;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public List<SaleSource> getSaleSourceList() {
		return saleSourceList;
	}

	public void setSaleSourceList(List<SaleSource> saleSourceList) {
		this.saleSourceList = saleSourceList;
	}

	public List<CrmProject> getCrmProjectList() {
		return crmProjectList;
	}

	public void setCrmProjectList(List<CrmProject> crmProjectList) {
		this.crmProjectList = crmProjectList;
	}
}