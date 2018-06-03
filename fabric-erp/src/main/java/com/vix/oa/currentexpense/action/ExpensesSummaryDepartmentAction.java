package com.vix.oa.currentexpense.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.oa.currentexpense.hql.ExpensesSummaryPersonHqlProvider;
import com.vix.oa.travelclaims.entity.TravelExpense;

@Controller
@Scope("prototype")
public class ExpensesSummaryDepartmentAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private String ids;
	private String pageNo;
	private TravelExpense travelExpense;
	private List<TravelExpense> travelExpenseList;
	@Resource(name = "expensesSummaryPersonHqlProvider")
	private ExpensesSummaryPersonHqlProvider expensesSummaryPersonHqlProvider;

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			if (id != null && !"".equals(id)) {
				params.put("organizationUnit.id," + SearchCondition.EQUAL, id);
			}
			Pager pager = getPager();
			pager = baseHibernateService.findPagerByHqlConditions(pager, TravelExpense.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
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

	/**
	 * @return the travelExpense
	 */
	public TravelExpense getTravelExpense() {
		return travelExpense;
	}

	/**
	 * @param travelExpense
	 *            the travelExpense to set
	 */
	public void setTravelExpense(TravelExpense travelExpense) {
		this.travelExpense = travelExpense;
	}

	/**
	 * @return the travelExpenseList
	 */
	public List<TravelExpense> getTravelExpenseList() {
		return travelExpenseList;
	}

	/**
	 * @param travelExpenseList
	 *            the travelExpenseList to set
	 */
	public void setTravelExpenseList(List<TravelExpense> travelExpenseList) {
		this.travelExpenseList = travelExpenseList;
	}

}
