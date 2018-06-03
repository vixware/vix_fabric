package com.vix.nvix.customer.action;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.crm.base.entity.ExpenseType;
import com.vix.crm.project.entity.ActionHistory;
import com.vix.crm.project.entity.CrmProject;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.sales.salepayment.entity.Expenses;
import com.vix.sales.salepayment.entity.ExpensesDetail;

@Controller
@Scope("prototype")
public class NvixExpensesAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	
	private String id;
	private String name;
	private Expenses expenses;
	private ExpensesDetail expensesDetail;
	private String entityName;
	
	public void goListContent(){
		try {
			Pager pager = getPager();
			Map<String,Object> params = getParams();
			String customerName = getRequestParameter("customerName");
			if(StrUtils.objectIsNotNull(customerName)){
				customerName = decode(customerName, "UTF-8");
				params.put("customerAccount.name,"+SearchCondition.ANYLIKE, customerName);
			}
			if(StrUtils.objectIsNotNull(name)){
				name = decode(name, "UTF-8");
				params.put("employee.name,"+SearchCondition.ANYLIKE, name);
			}
			pager = baseHibernateService.findPagerByHqlConditions(getPager(), Expenses.class, params);
			renderDataTable(pager,null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 跳转至用户修改页面 */
	private List<CurrencyType> currencyTypeList;
	private List<ExpenseType> expenseTypeList;
	private List<CrmProject> crmProjectList;
	public String goSaveOrUpdate() {
		try {
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			expenseTypeList = baseHibernateService.findAllByEntityClassAndAttribute(ExpenseType.class, "enable", "1");
			if (null != id && !"".equals(id)) {
				expenses = baseHibernateService.findEntityById(Expenses.class, id);
				if(null != expenses.getCustomerAccount() && StrUtils.isNotEmpty(expenses.getCustomerAccount().getId())){
					crmProjectList = baseHibernateService.findAllByEntityClassAndAttribute(CrmProject.class, "customerAccount.id", expenses.getCustomerAccount().getId());
				}
			} else {
				expenses = new Expenses();
				expenses.setExpensesDate(new Date());
				Employee baseEmp = getEmployee();
				if (null != baseEmp) {
					Employee emp = baseHibernateService.findEntityById(Employee.class, baseEmp.getId());
					expenses.setEmployee(emp);
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
			if (null != expenses.getId()) {
				isSave = false;
			} else {
				expenses.setCreateTime(new Date());
				loadCommonData(expenses);
			}
			if(null == expenses.getCrmProject() || StrUtils.isEmpty(expenses.getCrmProject().getId())){
				expenses.setCrmProject(null);
			}
			expenses = baseHibernateService.merge(expenses);
			// 增加行动历史和客户更新
			if(expenses.getCustomerAccount() != null && StrUtils.isNotEmpty(expenses.getCustomerAccount().getId())){
				CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, expenses.getCustomerAccount().getId());
				ActionHistory actionHistory = new ActionHistory();
				actionHistory.setSubject("费用支出");
				actionHistory.setActionDate(new Date());
				actionHistory.setCustomerAccount(customerAccount);
				//actionHistory.setCrmProject(expenses.getCrmProject());
				actionHistory.setDescription("费用支出编辑");
				actionHistory = baseHibernateService.merge(actionHistory);
				customerAccount.setUpdateTime(new Date());
				customerAccount.setStagnateDay(0);
				customerAccount = baseHibernateService.merge(customerAccount);
			}
			if (isSave) {
				renderText(expenses.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText(expenses.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0" + SAVE_FAIL);
			} else {
				renderText("0" + UPDATE_FAIL);
			}
		}
		return UPDATE;
	}
	
	/** 处理删除操作 */
	public void deleteById() {
		try {
			Expenses pb = baseHibernateService.findEntityById(Expenses.class, id);
			if (null != pb) {
				if (null != pb.getExpensesDetails() && pb.getExpensesDetails().size() > 0) {
					renderText("费用支出存在明细,不允许删除!");
				} else {
					baseHibernateService.deleteByEntity(pb);
					renderText(DELETE_SUCCESS);
				}
			} else {
				renderText(DELETE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	
	/** 获取发票明细*/
	public void getExpensesDetailJson(){
		try {
			Pager pager = getPager();
			Map<String,Object> params = getParams();
			if(null != id && StrUtils.isNotEmpty(id)){
				params.put("expenses.id,"+SearchCondition.EQUAL, id);
				if(null != name && !"".equals(name)){
					params.put("goodsName,"+SearchCondition.ANYLIKE,URLDecoder.decode(name.trim(), "UTF-8"));
				}
				baseHibernateService.findPagerByHqlConditions(pager, ExpensesDetail.class, params);
			}
			renderDataTable(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String addExpensesDetail(){
		try{
			if (null != id && !"".equals(id)) {
				expensesDetail = baseHibernateService.findEntityById(ExpensesDetail.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "addExpensesDetail";
	}
	
	public void saveOrUpdateExpensesDetail(){
		boolean isSave = true;
		try {
			if (null != expensesDetail.getId()) {
				isSave = false;
			} else {
				expensesDetail.setCreateTime(new Date());
				loadCommonData(expensesDetail);
			}
			baseHibernateService.merge(expensesDetail);
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
	}
	
	public void deleteExpensesDetailById() {
		try {
			if (null != id && StrUtils.isNotEmpty(id)) {
				ExpensesDetail pb = baseHibernateService.findEntityById(ExpensesDetail.class, id);
				if (null != pb) {
					baseHibernateService.deleteByEntity(pb);
					renderText(DELETE_SUCCESS);
				} else {
					renderText(DELETE_FAIL);
				}
			} else {
				renderText(DELETE_FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
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

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public Expenses getExpenses() {
		return expenses;
	}

	public void setExpenses(Expenses expenses) {
		this.expenses = expenses;
	}

	public ExpensesDetail getExpensesDetail() {
		return expensesDetail;
	}

	public void setExpensesDetail(ExpensesDetail expensesDetail) {
		this.expensesDetail = expensesDetail;
	}

	public List<ExpenseType> getExpenseTypeList() {
		return expenseTypeList;
	}

	public void setExpenseTypeList(List<ExpenseType> expenseTypeList) {
		this.expenseTypeList = expenseTypeList;
	}

	public List<CrmProject> getCrmProjectList() {
		return crmProjectList;
	}

	public void setCrmProjectList(List<CrmProject> crmProjectList) {
		this.crmProjectList = crmProjectList;
	}
}