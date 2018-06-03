package com.vix.sales.salepayment.action;

import java.util.ArrayList;
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
import com.vix.hr.organization.entity.Employee;
import com.vix.sales.salepayment.entity.Expenses;
import com.vix.sales.salepayment.entity.ExpensesDetail;

@Controller
@Scope("prototype")
public class ExpensesAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private String ids;
	private Expenses expenses;
	private String pageNo;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			String subject = getRequestParameter("subject");
			if (StrUtils.objectIsNotNull(subject)) {
				subject = decode(subject, "UTF-8");
				params.put("subject," + SearchCondition.ANYLIKE, subject);
			}
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), Expenses.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), Expenses.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	private List<CurrencyType> currencyTypeList;

	public String goSaveOrUpdate() {
		try {
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			if (null != id && !"".equals(id)) {
				expenses = baseHibernateService.findEntityById(Expenses.class, id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			} else {
				expenses = new Expenses();
				expenses.setIsTemp("1");
				expenses.setExpensesDate(new Date());
				Employee baseEmp = getEmployee();
				if (null != baseEmp) {
					Employee emp = baseHibernateService.findEntityById(Employee.class, baseEmp.getId());
					expenses.setEmployee(emp);
				}
				expenses = baseHibernateService.merge(expenses);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != expenses.getId()) {
				isSave = false;
			} else {
				expenses.setCreateTime(new Date());
				loadCommonData(expenses);
			}
			if (null == expenses.getEmployee() || null == expenses.getEmployee().getId() || !expenses.getEmployee().getId().equals("") || !expenses.getEmployee().getId().equals("0")) {
				expenses.setEmployee(null);
			}
			if (null == expenses.getCurrencyType() || null == expenses.getCurrencyType().getId() || !expenses.getCurrencyType().getId().equals("") || !expenses.getCurrencyType().getId().equals("0")) {
				expenses.setCurrencyType(null);
			}
			if (null == expenses.getCustomerAccount() || null == expenses.getCustomerAccount().getId() || !expenses.getCustomerAccount().getId().equals("") || !expenses.getCustomerAccount().getId().equals("0")) {
				expenses.setCustomerAccount(null);
			}
			expenses.setIsTemp("0");
			expenses = baseHibernateService.merge(expenses);
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
	public String deleteById() {
		try {
			Expenses pb = baseHibernateService.findEntityById(Expenses.class, id);
			if (null != pb) {
				if (null != pb.getExpensesDetails() && pb.getExpensesDetails().size() > 0) {
					setMessage("费用支出存在明细,不允许删除!");
				} else {
					baseHibernateService.deleteByEntity(pb);
					renderText(DELETE_SUCCESS);
				}
			} else {
				setMessage("费用支出不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteByIds() {
		try {
			if (null != ids && !"".equals(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				baseHibernateService.batchDelete(Expenses.class, delIds);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotChoose"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public void getExpensesDetailJson() {
		try {
			String json = "";
			if (null != id && !"".equals(id)) {
				Expenses dc = baseHibernateService.findEntityById(Expenses.class, id);
				if (null != dc) {
					json = convertListToJson(new ArrayList<ExpensesDetail>(dc.getExpensesDetails()), dc.getExpensesDetails().size(), "expenses");
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goChooseType() {
		return "goChooseType";
	}

	public String goTopDynamicMenuContent() {
		return "goTopDynamicMenuContent";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public Expenses getExpenses() {
		return expenses;
	}

	public void setExpenses(Expenses expenses) {
		this.expenses = expenses;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}
}
