package com.vix.drp.activityplan.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.drp.activityplan.controller.ActivityPlanController;
import com.vix.drp.activityplan.entity.ActivityPlan;
import com.vix.drp.activityplan.entity.ActivityPlanDetail;

import flexjson.JSONDeserializer;

@Controller
@Scope("prototype")
public class ActivityPlanAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ActivityPlanController activityPlanController;

	private String id;
	private String ids;
	private String pageNo;
	private ActivityPlan activityPlan;

	private List<ActivityPlan> activityPlanList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			activityPlanList = activityPlanController.doListActivityPlanList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();

			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			pager = activityPlanController.doListActivityPlan(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				activityPlan = activityPlanController.doListEntityById(id);
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
			if (null != activityPlan.getId()) {
				isSave = false;
			}
			String activityPlanDetailJson = getRequestParameter("activityPlanDetailJson");
			List<ActivityPlanDetail> activityPlanDetailList = null;
			// 判断入库明细是否为空
			if (activityPlanDetailJson != null && !"".equals(activityPlanDetailJson)) {
				activityPlanDetailList = new JSONDeserializer<List<ActivityPlanDetail>>().use("values", ActivityPlanDetail.class).deserialize(activityPlanDetailJson);
			}
			initEntityBaseController.initEntityBaseAttribute(activityPlan);
			//处理修改留痕
			billMarkProcessController.processMark(activityPlan, updateField);
			activityPlan = activityPlanController.doSaveOrUpdateActivityPlan(activityPlan, activityPlanDetailList);
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
			ActivityPlan cd = activityPlanController.doListEntityById(id);
			if (null != cd) {
				activityPlanController.doDeleteByEntity(cd);
				renderText(DELETE_SUCCESS);
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
			if (StringUtils.isNotEmpty(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				activityPlanController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 获取明细的json数据 */
	public void getRefundRuleDetailJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {//if (null != id && !"".equals(id)) {
				ActivityPlan activityPlan = activityPlanController.doListEntityById(id);
				if (activityPlan != null) {
					json = convertListToJson(new ArrayList<ActivityPlanDetail>(activityPlan.getActivityPlanDetails()), activityPlan.getActivityPlanDetails().size());
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

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
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

	public ActivityPlan getActivityPlan() {
		return activityPlan;
	}

	public void setActivityPlan(ActivityPlan activityPlan) {
		this.activityPlan = activityPlan;
	}

	public List<ActivityPlan> getActivityPlanList() {
		return activityPlanList;
	}

	public void setActivityPlanList(List<ActivityPlan> activityPlanList) {
		this.activityPlanList = activityPlanList;
	}

}
