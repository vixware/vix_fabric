package com.vix.oa.accountMaintenance.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.oa.accountMaintenance.entity.ExpenseAccount;

@Controller
@Scope("prototype")
public class AccountMaintenanceAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private ExpenseAccount expenseAccount;


	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String searchContent = getRequestParameter("searchContent");
			if (searchContent != null && !"".equals(searchContent)) {
				params.put("code," + SearchCondition.ANYLIKE, searchContent);
			}
			String code = getRequestParameter("code");
			if (code != null && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			String name = getRequestParameter("name");
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), ExpenseAccount.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null != id && id.longValue() > 0) {
				expenseAccount = baseHibernateService.findEntityById(ExpenseAccount.class, id);
			} else {
				expenseAccount = new ExpenseAccount();
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
			if (null != expenseAccount.getId()) {
				isSave = false;
			}
			String py = ChnToPinYin.getPYString(expenseAccount.getName());
			expenseAccount.setChineseCharacter(py.toUpperCase());
			initEntityBaseController.initEntityBaseAttribute(expenseAccount);
			expenseAccount = baseHibernateService.merge(expenseAccount);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ExpenseAccount getExpenseAccount() {
		return expenseAccount;
	}

	public void setExpenseAccount(ExpenseAccount expenseAccount) {
		this.expenseAccount = expenseAccount;
	}

}
