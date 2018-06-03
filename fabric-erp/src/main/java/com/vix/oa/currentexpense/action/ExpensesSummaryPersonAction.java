package com.vix.oa.currentexpense.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.travelclaims.entity.TravelExpense;

@Controller
@Scope("prototype")
public class ExpensesSummaryPersonAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ExpensesSummaryPersonAction.class);
	private String id;
	private String ids;
	private String pageNo;
	private TravelExpense travelExpense;
	private List<TravelExpense> travelExpenseList;

	@Override
	public String goList() {
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			if (id != null && !"".equals(id)) {
				params.put("employee.id," + SearchCondition.EQUAL, id);
			}
			Pager pager = getPager();
			pager = baseHibernateService.findPagerByHqlConditions(pager, TravelExpense.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<Employee> employeeList = new ArrayList<Employee>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			employeeList = baseHibernateService.findAllByConditions(Employee.class, params);
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = employeeList.size();
			for (int i = 0; i < count; i++) {
				Employee org = employeeList.get(i);
				strSb.append("{id:\"");
				strSb.append(org.getId());
				strSb.append("\",treeType:\"E\"");
				strSb.append(",name:\"");
				strSb.append(org.getName());
				strSb.append("\",open:false,isParent:false}");
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
