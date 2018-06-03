package com.vix.sales.commission.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.sales.commission.entity.CommissionPlanItem;
import com.vix.sales.common.entity.SalesPerformanceEvaluationMethod;

@Controller
@Scope("prototype")
public class CommissionPlanItemAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	private CommissionPlanItem commissionPlanItem;
	private String pageNo;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(
					getPager(), CommissionPlanItem.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 获取列表数据 */
	public String goSubListContent() {
		try {
			Map<String, Object> params = getParams();
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			getPager().setPageSize(6);
			Pager pager = baseHibernateService.findPagerByHqlConditions(
					getPager(), CommissionPlanItem.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	private List<SalesPerformanceEvaluationMethod> salesPerformanceEvaluationMethodList;

	public String goSaveOrUpdate() {
		try {
			salesPerformanceEvaluationMethodList = baseHibernateService.findAllByEntityClass(SalesPerformanceEvaluationMethod.class);
			if (null != id && !"".equals(id)) {
				commissionPlanItem = baseHibernateService.findEntityById(CommissionPlanItem.class, id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
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
			if (null != commissionPlanItem.getId()) {
				isSave = false;
			} else {
				commissionPlanItem.setCreateTime(new Date());
				loadCommonData(commissionPlanItem);
			}
			commissionPlanItem = baseHibernateService.merge(commissionPlanItem);
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
			CommissionPlanItem pb = baseHibernateService.findEntityById(
					CommissionPlanItem.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
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

	public String goChooseCommissionPlanItem() {
		return "chooseCommissionPlanItem";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public CommissionPlanItem getCommissionPlanItem() {
		return commissionPlanItem;
	}

	public void setCommissionPlanItem(CommissionPlanItem commissionPlanItem) {
		this.commissionPlanItem = commissionPlanItem;
	}

	public List<SalesPerformanceEvaluationMethod> getSalesPerformanceEvaluationMethodList() {
		return salesPerformanceEvaluationMethodList;
	}

	public void setSalesPerformanceEvaluationMethodList(
			List<SalesPerformanceEvaluationMethod> salesPerformanceEvaluationMethodList) {
		this.salesPerformanceEvaluationMethodList = salesPerformanceEvaluationMethodList;
	}

}