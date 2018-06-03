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
import com.vix.sales.salepayment.entity.DisbursementCost;
import com.vix.sales.salepayment.entity.DisbursementCostDetail;

@Controller
@Scope("prototype")
public class DisbursementCostAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private String ids;
	private DisbursementCost disbursementCost;
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
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), DisbursementCost.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	private List<CurrencyType> currencyTypeList;

	public String goSaveOrUpdate() {
		try {
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			if (null != id && !"".equals(id)) {
				disbursementCost = baseHibernateService.findEntityById(DisbursementCost.class, id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			} else {
				disbursementCost = new DisbursementCost();
				disbursementCost.setIsTemp("1");
				disbursementCost.setDisbursementDate(new Date());
				Employee baseEmp = getEmployee();
				if (null != baseEmp) {
					Employee emp = baseHibernateService.findEntityById(Employee.class, baseEmp.getId());
					disbursementCost.setEmployee(emp);
				}
				disbursementCost = baseHibernateService.merge(disbursementCost);
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
			if (null != disbursementCost.getId()) {
				isSave = false;
			} else {
				disbursementCost.setCreateTime(new Date());
				loadCommonData(disbursementCost);
			}
			if (null == disbursementCost.getEmployee() || null == disbursementCost.getEmployee().getId() || !disbursementCost.getEmployee().getId().equals("") || !disbursementCost.getEmployee().getId().equals("0")) {
				disbursementCost.setEmployee(null);
			}
			if (null == disbursementCost.getCurrencyType() || null == disbursementCost.getCurrencyType().getId() || !disbursementCost.getCurrencyType().getId().equals("") || !disbursementCost.getCurrencyType().getId().equals("0")) {
				disbursementCost.setCurrencyType(null);
			}
			if (null == disbursementCost.getCustomerAccount() || null == disbursementCost.getCustomerAccount().getId() || !disbursementCost.getCustomerAccount().getId().equals("") || !disbursementCost.getCustomerAccount().getId().equals("0")) {
				disbursementCost.setCustomerAccount(null);
			}
			disbursementCost.setIsTemp("0");
			disbursementCost = baseHibernateService.merge(disbursementCost);
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
			DisbursementCost pb = baseHibernateService.findEntityById(DisbursementCost.class, id);
			if (null != pb) {
				if (null != pb.getDisbursementCostDetails() && pb.getDisbursementCostDetails().size() > 0) {
					setMessage("代垫费用存在明细,不允许删除!");
				} else {
					baseHibernateService.deleteByEntity(pb);
					renderText(DELETE_SUCCESS);
				}
			} else {
				setMessage("代垫费用不存在!");
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
				baseHibernateService.batchDelete(DisbursementCost.class, delIds);
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

	public void getDisbursementCostDetailJson() {
		try {
			String json = "";
			if (null != id && !"".equals(id)) {
				DisbursementCost dc = baseHibernateService.findEntityById(DisbursementCost.class, id);
				if (null != dc) {
					json = convertListToJson(new ArrayList<DisbursementCostDetail>(dc.getDisbursementCostDetails()), dc.getDisbursementCostDetails().size(), "disbursementCost");
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

	public DisbursementCost getDisbursementCost() {
		return disbursementCost;
	}

	public void setDisbursementCost(DisbursementCost disbursementCost) {
		this.disbursementCost = disbursementCost;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}
}
