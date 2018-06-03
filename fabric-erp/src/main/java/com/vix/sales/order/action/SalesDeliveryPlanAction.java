package com.vix.sales.order.action;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.sales.order.entity.SalesDeliveryPlan;
import com.vix.sales.order.entity.SalesDeliveryPlanDetail;

@Controller
@Scope("prototype")
public class SalesDeliveryPlanAction extends BaseAction {
	private static final long serialVersionUID = 1L;


	private String id;
	private String ids;
	private SalesDeliveryPlan salesDeliveryPlan;
	private SalesDeliveryPlanDetail salesDeliveryPlanDetail;
	private String pageNo;

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				salesDeliveryPlan = baseHibernateService.findEntityById(
						SalesDeliveryPlan.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdateDetail() {
		try {
			if (null != id && !"".equals(id)) {
				salesDeliveryPlanDetail = baseHibernateService
						.findEntityById(SalesDeliveryPlanDetail.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateDetail";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != salesDeliveryPlan.getId()) {
				isSave = false;
			} else {
				salesDeliveryPlan.setCreateTime(new Date());
				loadCommonData(salesDeliveryPlan);
			}
			salesDeliveryPlan = baseHibernateService
					.merge(salesDeliveryPlan);
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

	/** 处理修改操作 */
	public String saveOrUpdateDetail() {
		boolean isSave = true;
		try {
			if (null != salesDeliveryPlanDetail.getId()) {
				isSave = false;
			}
			salesDeliveryPlanDetail = baseHibernateService
					.merge(salesDeliveryPlanDetail);
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
			SalesDeliveryPlan pb = baseHibernateService.findEntityById(
					SalesDeliveryPlan.class, id);
			if (null != pb) {
				if (null != pb.getSalesDeliveryPlanDetails()
						&& pb.getSalesDeliveryPlanDetails().size() > 0) {
					baseHibernateService.deleteByAttribute(
							SalesDeliveryPlanDetail.class,
							"salesDeliveryPlan.id", pb.getId());
				}
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage("数据不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteDetailById() {
		try {
			SalesDeliveryPlanDetail pb = baseHibernateService
					.findEntityById(SalesDeliveryPlanDetail.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage("数据不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public void getSalesDeliveryPlanDetailJson() {
		try {
			String json = "";
			if (null != id && !"".equals(id)) {
				SalesDeliveryPlan sdp = baseHibernateService
						.findEntityById(SalesDeliveryPlan.class, id);
				if (null != sdp) {
					json = convertListToJson(
							new ArrayList<SalesDeliveryPlanDetail>(
									sdp.getSalesDeliveryPlanDetails()), sdp
									.getSalesDeliveryPlanDetails().size(),
							"salesDeliveryPlan");
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

	public SalesDeliveryPlan getSalesDeliveryPlan() {
		return salesDeliveryPlan;
	}

	public void setSalesDeliveryPlan(SalesDeliveryPlan salesDeliveryPlan) {
		this.salesDeliveryPlan = salesDeliveryPlan;
	}

	public SalesDeliveryPlanDetail getSalesDeliveryPlanDetail() {
		return salesDeliveryPlanDetail;
	}

	public void setSalesDeliveryPlanDetail(
			SalesDeliveryPlanDetail salesDeliveryPlanDetail) {
		this.salesDeliveryPlanDetail = salesDeliveryPlanDetail;
	}
}
