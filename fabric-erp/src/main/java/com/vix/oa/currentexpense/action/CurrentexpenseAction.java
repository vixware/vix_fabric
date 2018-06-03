package com.vix.oa.currentexpense.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.areaExpensesStandards.entity.AreaExpensesStandards;
import com.vix.oa.currentexpense.controller.CurrentExpenseController;
import com.vix.oa.currentexpense.entity.CurrentExpense;

@Controller
@Scope("prototype")
public class CurrentexpenseAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(CurrentexpenseAction.class);
	@Autowired
	private CurrentExpenseController currentExpenseController;
	private String id;
	private String areaLevelId;
	private String transportId;
	private String orgPositionId;
	private String ids;
	private String pageNo;
	/**
	 * 日常费用
	 */
	private CurrentExpense currentExpense;
	private List<CurrentExpense> currentExpenseList;

	@Override
	public String goList() {
		try {
			currentExpenseList = currentExpenseController.doListCurrentExpenseList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 根据条件查询入库单信息
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = currentExpenseController.doListCurrentExpense(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				currentExpense = currentExpenseController.doListCurrentExpenseById(id);
			} else {
				currentExpense = new CurrentExpense();
				currentExpense.setIsTemp("1");
				if (SecurityUtil.getCurrentEmpId() != null) {
					Employee employee = currentExpenseController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
					if (employee != null) {
						currentExpense.setEmployee(employee);
						currentExpense.setCreator(employee.getName());
						currentExpense.setDepartment(employee.getDepartment());
					}
				}
				currentExpense = currentExpenseController.doSaveCurrentExpense(currentExpense);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 获取报销标准
	 */
	public void getReimbursementAmount() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("areaLevel.id," + SearchCondition.EQUAL, areaLevelId);
			params.put("transport.id," + SearchCondition.EQUAL, transportId);
			params.put("orgPosition.id," + SearchCondition.EQUAL, orgPositionId);
			List<AreaExpensesStandards> areaExpensesStandardsList = currentExpenseController.doListAreaExpensesStandardsList(params);
			if (null != areaExpensesStandardsList) {
				for (AreaExpensesStandards areaExpensesStandards : areaExpensesStandardsList) {
					if (areaExpensesStandards != null) {
						renderJson(areaExpensesStandards.getReimbursementAmount().toString());
						return;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != currentExpense.getId()) {
				isSave = false;
			}
			currentExpense.setIsTemp("");
			currentExpense = currentExpenseController.doSaveCurrentExpense(currentExpense);
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
			CurrentExpense pb = currentExpenseController.doListCurrentExpenseById(id);
			if (null != pb) {
				currentExpenseController.doDeleteByEntity(pb);
				logger.info("");
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 批量处理删除操作 */
	public String deleteByIds() {
		try {
			if (null != ids && !"".equals(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				currentExpenseController.doDeleteByIds(delIds);
				logger.info("删除订单信息");
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotChoose"));
			}
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
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

	public CurrentExpense getCurrentExpense() {
		return currentExpense;
	}

	public void setCurrentExpense(CurrentExpense currentExpense) {
		this.currentExpense = currentExpense;
	}

	public List<CurrentExpense> getCurrentExpenseList() {
		return currentExpenseList;
	}

	public void setCurrentExpenseList(List<CurrentExpense> currentExpenseList) {
		this.currentExpenseList = currentExpenseList;
	}

}
