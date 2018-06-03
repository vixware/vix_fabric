package com.vix.nvix.customer.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.crm.lead.entity.SaleLead;
import com.vix.crm.project.entity.ActionHistory;
import com.vix.crm.project.entity.CrmProject;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;

@Controller
@Scope("prototype")
public class NvixSaleLeadAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	
	private String id;
	private String name;
	private String companyCode;
	private SaleLead saleLead;
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
			baseHibernateService.findPagerByHqlConditions(pager, SaleLead.class, params);
			renderDataTable(pager,null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private List<CurrencyType> currencyTypeList;
	private List<CrmProject> crmProjectList;
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			if(null != id && StrUtils.isNotEmpty(id)){
				saleLead = baseHibernateService.findEntityById(SaleLead.class,id);
				if(null != saleLead.getCustomerAccount().getId() && StrUtils.isNotEmpty(saleLead.getCustomerAccount().getId())){
					contactPersonList = baseHibernateService.findAllByEntityClassAndAttribute(ContactPerson.class, "customerAccount.id", saleLead.getCustomerAccount().getId());
					crmProjectList = baseHibernateService.findAllByEntityClassAndAttribute(CrmProject.class, "customerAccount.id", saleLead.getCustomerAccount().getId());
				}
				if(null == saleLead.getCreatedBy() || "".equals(saleLead.getCreatedBy())){
					if(null != SecurityUtil.getCurrentEmpId() && !"".equals(SecurityUtil.getCurrentEmpId())){
						Employee emp = baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
						if(null != emp){
							saleLead.setCreatedBy(emp.getName());
						}
					}
				}
			}else{
				saleLead = new SaleLead();
				if(null != SecurityUtil.getCurrentEmpId() && !"".equals(SecurityUtil.getCurrentEmpId())){
					Employee emp = baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
					if(null != emp){
						saleLead.setCreatedBy(emp.getName());
					}
				}
				saleLead.setDateEntered(new Date());
				saleLead.setFindOutTime(new Date());
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
			if(StrUtils.objectIsNotNull(saleLead.getId())){
				isSave = false;
			}else{
				saleLead.setCreateTime(new Date());
				saleLead.setIsDeleted("0");
				loadCommonData(saleLead);
			}
			
			String[] attrArray ={"customerAccount","currencyType","contactPerson","crmProject"};
			checkEntityNullValue(saleLead,attrArray);
			
			String name = saleLead.getSubject();
			String py = ChnToPinYin.getPYString(name);
			saleLead.setChineseFirstLetter(py.toUpperCase());
			saleLead.setDateModified(new Date());
			saleLead = baseHibernateService.merge(saleLead);
			// 增加行动历史和客户更新
			if(saleLead.getCustomerAccount() != null && StrUtils.isNotEmpty(saleLead.getCustomerAccount().getId())){
				CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, saleLead.getCustomerAccount().getId());
				ActionHistory actionHistory = new ActionHistory();
				actionHistory.setSubject("销售线索");
				actionHistory.setActionDate(new Date());
				actionHistory.setCustomerAccount(customerAccount);
				actionHistory.setCrmProject(saleLead.getCrmProject());
				actionHistory.setDescription("销售线索编辑");
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
			SaleLead pb = baseHibernateService.findEntityById(SaleLead.class,id);
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

	public SaleLead getSaleLead() {
		return saleLead;
	}

	public void setSaleLead(SaleLead saleLead) {
		this.saleLead = saleLead;
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

	public List<CrmProject> getCrmProjectList() {
		return crmProjectList;
	}

	public void setCrmProjectList(List<CrmProject> crmProjectList) {
		this.crmProjectList = crmProjectList;
	}
}