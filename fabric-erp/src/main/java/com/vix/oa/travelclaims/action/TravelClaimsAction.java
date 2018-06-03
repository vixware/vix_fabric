package com.vix.oa.travelclaims.action;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.vix.common.base.action.BaseAction;
import com.vix.common.billtype.BillType;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.oa.currentexpense.action.CurrentexpenseAction;
import com.vix.oa.currentexpense.entity.CurrentExpenseDetail;
import com.vix.oa.travelclaims.controller.TravelExpenseController;
import com.vix.oa.travelclaims.entity.AccommodationFee;
import com.vix.oa.travelclaims.entity.TransportCosts;
import com.vix.oa.travelclaims.entity.TravelExpense;
import com.vix.system.billTypeManagement.entity.BillsProperty;

@Controller
@Scope("prototype")
public class TravelClaimsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(CurrentexpenseAction.class);
	@Autowired
	private TravelExpenseController travelExpenseController;
	private String id;
	private String ids;
	private String pageNo;
	private TravelExpense travelExpense;
	private List<TravelExpense> travelExpenseList;
	private String isAllowAudit;//是否允许提交审批 1：是 0：否
	private String taskId;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			travelExpenseList = travelExpenseController.doListTravelExpenseIndex(params);
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
			//倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			String name = getDecodeRequestParameter("name");
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.EQUAL, name);
			}
			String employeeName = getDecodeRequestParameter("employeeName");
			if (employeeName != null && !"".equals(employeeName)) {
				params.put("employee.name," + SearchCondition.EQUAL, employeeName);
			}
			pager = travelExpenseController.doListTravelExpense(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			isAllowAudit = isAllowAudit(BillType.OA_EXPENSE_ACCOUNT);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				travelExpense = travelExpenseController.doListTravelExpenseById(id);
			} else {
				travelExpense = new TravelExpense();
				travelExpense.setIsTemp("1");
				if (SecurityUtil.getCurrentEmpId() != null) {
					Employee employee = travelExpenseController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
					if (employee != null) {
						travelExpense.setEmployee(employee);
						travelExpense.setCreator(employee.getName());
						travelExpense.setDepartment(employee.getDepartment());
						travelExpense.setOrganizationUnit(employee.getOrganizationUnit());
					}
				}
				travelExpense = travelExpenseController.doSaveTravelExpense(travelExpense);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goAudit() {
		try {
			isAllowAudit = isAllowAudit(BillType.OA_EXPENSE_ACCOUNT);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				travelExpense = travelExpenseController.doListTravelExpenseById(id);
			} else {
				travelExpense = new TravelExpense();
				travelExpense.setIsTemp("1");
				if (SecurityUtil.getCurrentEmpId() != null) {
					Employee employee = travelExpenseController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
					if (employee != null) {
						travelExpense.setEmployee(employee);
						travelExpense.setCreator(employee.getName());
						travelExpense.setDepartment(employee.getDepartment());
						travelExpense.setOrganizationUnit(employee.getOrganizationUnit());
					}
				}
				travelExpense = travelExpenseController.doSaveTravelExpense(travelExpense);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goAudit";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != travelExpense.getId()) {
				isSave = false;
			}
			travelExpense.setIsTemp("");
			travelExpense.setStatus("0");
			travelExpense = travelExpenseController.doSaveTravelExpense(travelExpense);
			Double amount = 0D;
			if (travelExpense.getTransportCostss() != null && travelExpense.getTransportCostss().size() > 0) {
				for (TransportCosts transportCosts : travelExpense.getTransportCostss()) {
					amount += transportCosts.getCost();
				}
			}
			if (travelExpense.getAccommodationFees() != null && travelExpense.getAccommodationFees().size() > 0) {
				for (AccommodationFee accommodationFee : travelExpense.getAccommodationFees()) {
					if (accommodationFee != null && accommodationFee.getCost() != null) {
						amount += accommodationFee.getCost();
					}
				}
			}

			if (travelExpense.getSubCurrentExpenseDetails() != null && travelExpense.getSubCurrentExpenseDetails().size() > 0) {
				for (CurrentExpenseDetail currentExpenseDetail : travelExpense.getSubCurrentExpenseDetails()) {
					if (currentExpenseDetail != null && currentExpenseDetail.getExpensesAmountDetail() != null) {
						amount += currentExpenseDetail.getExpensesAmountDetail();
					}
				}
			}
			travelExpense.setAmount(amount);
			travelExpense = travelExpenseController.doSaveTravelExpense(travelExpense);
			String tag = getRequestParameter("tag");
			if (tag != null && "1".equals(tag)) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("propertyCode," + SearchCondition.EQUAL, BillType.OA_EXPENSE_ACCOUNT);
				params.put("tenantId," + SearchCondition.EQUAL, travelExpense.getTenantId());
				List<BillsProperty> billsPropertyList = travelExpenseController.doListBillsPropertyList(params);
				if (billsPropertyList != null && billsPropertyList.size() > 0) {
					for (BillsProperty billsProperty : billsPropertyList) {
						RestTemplate restTemplate = new RestTemplate();
						Map<String, Object> urlVariables = new HashMap<String, Object>();
						String billsPropertyId = String.valueOf(billsProperty.getId());
						String billTypeCode = BillType.OA_EXPENSE_ACCOUNT;
						String billTitle = travelExpense.getName();
						String billId = String.valueOf(travelExpense.getId());
						urlVariables.put("billsPropertyId", billsPropertyId);
						urlVariables.put("billTypeCode", billTypeCode);
						urlVariables.put("billTitle", billTitle);
						urlVariables.put("billId", billId);

						Object objUser = getSession().getAttribute("userInfo");
						UserAccount user = null;
						if (null != objUser && objUser instanceof UserAccount) {
							user = (UserAccount) objUser;
							urlVariables.put("userId", user.getId());
						}

						restTemplate.postForObject("http://localhost:8080/vform/activiti/deployment/startAndSubmitByBillsCode?billsPropertyId=" + billsPropertyId + "&billTypeCode=" + billTypeCode + "&billId=" + billId + "&billTitle=" + URLEncoder.encode(billTitle, "UTF-8") + "&userId=" + user.getId(), String.class, String.class, urlVariables);
						travelExpense.setStatus("1");
						travelExpense = travelExpenseController.doSaveTravelExpense(travelExpense);
						break;
					}
				}
			}
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
			TravelExpense travelExpense = travelExpenseController.doListTravelExpenseById(id);
			if (null != travelExpense) {
				travelExpenseController.doDeleteByEntity(travelExpense);
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
				travelExpenseController.doDeleteByIds(delIds);
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

	public String goSearch() {
		return "goSearch";
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

	public TravelExpense getTravelExpense() {
		return travelExpense;
	}

	public void setTravelExpense(TravelExpense travelExpense) {
		this.travelExpense = travelExpense;
	}

	public List<TravelExpense> getTravelExpenseList() {
		return travelExpenseList;
	}

	public void setTravelExpenseList(List<TravelExpense> travelExpenseList) {
		this.travelExpenseList = travelExpenseList;
	}

	/**
	 * @return the taskId
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId
	 *            the taskId to set
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the isAllowAudit
	 */
	public String getIsAllowAudit() {
		return isAllowAudit;
	}

	/**
	 * @param isAllowAudit
	 *            the isAllowAudit to set
	 */
	public void setIsAllowAudit(String isAllowAudit) {
		this.isAllowAudit = isAllowAudit;
	}

}
