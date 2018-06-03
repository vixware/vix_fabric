package com.vix.nvix.customer.action;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.crm.project.entity.ActionHistory;
import com.vix.crm.project.entity.CrmProject;
import com.vix.crm.salechance.entity.BackSectionPlan;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.sales.order.entity.SalesOrder;

@Controller
@Scope("prototype")
public class NvixBackSectionPlanAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	
	private String id;
	private String name;
	private BackSectionPlan backSectionPlan;
	private String entityName;
	private String customerId;
	
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
			pager = baseHibernateService.findPagerByHqlConditions(getPager(), BackSectionPlan.class, params);
			renderDataTable(pager,null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void goBackSectionPlanListContent(){
		try {
			Pager pager = getPager();
			Map<String,Object> params = getParams();
			params.put("backSectionPlanStatus," + SearchCondition.EQUAL, "0");
			pager = baseHibernateService.findPagerByHqlConditions(getPager(), BackSectionPlan.class, params);
			renderDataTable(pager,null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 跳转至用户修改页面 */
	private List<SalesOrder> salesOrderList;
	private List<CrmProject> crmProjectList;
	public String goSaveOrUpdate() {
		try {
			if(null != id && !"".equals(id)){
				backSectionPlan = baseHibernateService.findEntityById(BackSectionPlan.class,id);
				if(null != backSectionPlan.getCustomerAccount() && StrUtils.isNotEmpty(backSectionPlan.getCustomerAccount().getId())){
					salesOrderList = baseHibernateService.findAllByEntityClassAndAttribute(SalesOrder.class, "customerAccount.id", backSectionPlan.getCustomerAccount().getId());
					crmProjectList = baseHibernateService.findAllByEntityClassAndAttribute(CrmProject.class, "customerAccount.id", backSectionPlan.getCustomerAccount().getId());
				}
			}else{
				backSectionPlan = new BackSectionPlan();
				backSectionPlan.setBackSectionPlanDate(new Date());
				Employee e = getEmployee();
				if(e != null){
					backSectionPlan.setOwner(e);
					backSectionPlan.setCharger(e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	public String goSaveOrUpdateAdd() {
		try {
			if(null != id && !"".equals(id)){
				BackSectionPlan plan = baseHibernateService.findEntityById(BackSectionPlan.class,id);
				if(null != plan.getCustomerAccount() && StrUtils.isNotEmpty(plan.getCustomerAccount().getId())){
					salesOrderList = baseHibernateService.findAllByEntityClassAndAttribute(SalesOrder.class, "customerAccount.id", plan.getCustomerAccount().getId());
					crmProjectList = baseHibernateService.findAllByEntityClassAndAttribute(CrmProject.class, "customerAccount.id", plan.getCustomerAccount().getId());
				}
				backSectionPlan = new BackSectionPlan();
				backSectionPlan.setBackSectionPlanDate(new Date());
				backSectionPlan.setCustomerAccount(plan.getCustomerAccount());
				backSectionPlan.setSalesOrder(plan.getSalesOrder());
				backSectionPlan.setCrmProject(plan.getCrmProject());
				backSectionPlan.setOwner(plan.getOwner());
				backSectionPlan.setCharger(plan.getCharger());
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
			if(StrUtils.objectIsNotNull(backSectionPlan.getId())){
				isSave = false;
			}else{
				backSectionPlan.setCreateTime(new Date());
				loadCommonData(backSectionPlan);
			}
			if(null == backSectionPlan.getSalesOrder() || StrUtils.isEmpty(backSectionPlan.getSalesOrder().getId())){
				backSectionPlan.setSalesOrder(null);
			}
			if(null == backSectionPlan.getCrmProject() || StrUtils.isEmpty(backSectionPlan.getCrmProject().getId())){
				backSectionPlan.setCrmProject(null);
			}
			String[] attrArray ={"customerAccount","crmProject","charger","owner"};
			checkEntityNullValue(backSectionPlan,attrArray);
			
			if(null != backSectionPlan.getCustomerAccount() && null != backSectionPlan.getCustomerAccount().getId() &&
					!backSectionPlan.getCustomerAccount().getId().equals("") && !backSectionPlan.getCustomerAccount().getId().equals("0")){
				CustomerAccount ca = baseHibernateService.findEntityById(CustomerAccount.class, backSectionPlan.getCustomerAccount().getId());
				String name = ca.getShorterName();
				String py = ChnToPinYin.getPYString(name);
				backSectionPlan.setChineseFirstLetter(py.toUpperCase());
			}
			backSectionPlan = baseHibernateService.merge(backSectionPlan);
			// 增加行动历史和客户更新
			if(backSectionPlan.getCustomerAccount() != null && StrUtils.isNotEmpty(backSectionPlan.getCustomerAccount().getId())){
				CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, backSectionPlan.getCustomerAccount().getId());
				ActionHistory actionHistory = new ActionHistory();
				actionHistory.setSubject("回款计划");
				actionHistory.setActionDate(new Date());
				actionHistory.setCustomerAccount(customerAccount);
				actionHistory.setCrmProject(backSectionPlan.getCrmProject());
				actionHistory.setDescription("回款计划编辑");
				actionHistory = baseHibernateService.merge(actionHistory);
				customerAccount.setUpdateTime(new Date());
				customerAccount.setStagnateDay(0);
				customerAccount = baseHibernateService.merge(customerAccount);
			}
			if (isSave) {
				renderText(SAVE_SUCCESS + ":" + backSectionPlan.getId());
			} else {
				renderText(UPDATE_SUCCESS + ":" + backSectionPlan.getId());
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
			BackSectionPlan pb = baseHibernateService.findEntityById(BackSectionPlan.class,id);
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
	
	/** 获取项目列表 */
	public String goChooseCrmProject() {
		return "goChooseCrmProject";
	}

	/** 选择项目列表 */
	public void goCrmProjectSingleListJson() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != name && !"".equals(name)) {
				params.put("suject," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
			}
			String customerId = getRequestParameter("customerId");
			if (StrUtils.isNotEmpty(customerId)) {
				params.put("customerAccount.id," + SearchCondition.EQUAL, customerId.trim());
				pager = baseHibernateService.findPagerByHqlConditions(pager, CrmProject.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 获取订单列表 */
	public String goChooseSaleOrder() {
		return "goChooseSaleOrder";
	}

	/** 选择订单列表 */
	public void goSaleOrderSingleListJson() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			if (null != name && !"".equals(name)) {
				params.put("title," + SearchCondition.ANYLIKE, URLDecoder.decode(name.trim(), "UTF-8"));
			}
			String customerId = getRequestParameter("customerId");
			if (StrUtils.isNotEmpty(customerId)) {
				params.put("customerAccount.id," + SearchCondition.EQUAL, customerId.trim());
				pager = baseHibernateService.findPagerByHqlConditions(pager, SalesOrder.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BackSectionPlan getBackSectionPlan() {
		return backSectionPlan;
	}

	public void setBackSectionPlan(BackSectionPlan backSectionPlan) {
		this.backSectionPlan = backSectionPlan;
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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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