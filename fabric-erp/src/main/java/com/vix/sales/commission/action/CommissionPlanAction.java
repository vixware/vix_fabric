package com.vix.sales.commission.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.sales.commission.entity.CommissionPlan;
import com.vix.sales.commission.entity.CommissionPlanItem;

@Controller
@Scope("prototype")
public class CommissionPlanAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String id;
	private CommissionPlan commissionPlan;
	private String pageNo;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			params.put("saleOrg.fs," + SearchCondition.NOEQUAL, "");
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), CommissionPlan.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				commissionPlan = baseHibernateService.findEntityById(CommissionPlan.class, id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			} else {
				commissionPlan = new CommissionPlan();
				commissionPlan = baseHibernateService.merge(commissionPlan);
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
			if (null != commissionPlan.getId()) {
				isSave = false;
			} else {
				commissionPlan.setCreateTime(new Date());
				loadCommonData(commissionPlan);
			}
			commissionPlan = baseHibernateService.merge(commissionPlan);
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
			CommissionPlan pb = baseHibernateService.findEntityById(CommissionPlan.class, id);
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

	public void getCommissionPlanItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				CommissionPlan cp = baseHibernateService.findEntityById(CommissionPlan.class, id);
				if (null != cp) {
					json = convertListToJson(new ArrayList<CommissionPlanItem>(cp.getCommissionPlanItems()), cp.getCommissionPlanItems().size(), "commissionPlan");
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

	public CommissionPlan getCommissionPlan() {
		return commissionPlan;
	}

	public void setCommissionPlan(CommissionPlan commissionPlan) {
		this.commissionPlan = commissionPlan;
	}

}