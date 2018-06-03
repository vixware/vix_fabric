package com.vix.drp.activitybudget.action;

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
import com.vix.drp.activitybudget.controller.ActivityBudgetController;
import com.vix.drp.activitybudget.entity.ActivityBudget;

@Controller
@Scope("prototype")
public class ActivityBudgetAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private ActivityBudgetController activityBudgetController;

	private String id;
	private String ids;
	private String pageNo;
	private ActivityBudget activityBudget;

	private List<ActivityBudget> activityBudgetList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			activityBudgetList = activityBudgetController.doListActivityBudgetList(params);
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
			pager = activityBudgetController.doListActivityBudget(params, pager);
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
				activityBudget = activityBudgetController.doListEntityById(id);
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
			if (activityBudget != null) {
				if (null != activityBudget.getId() && !"".equals(activityBudget.getId())) {
					isSave = false;
				}
				initEntityBaseController.initEntityBaseAttribute(activityBudget);
				//处理修改留痕
				billMarkProcessController.processMark(activityBudget, updateField);
				activityBudget = activityBudgetController.doSaveOrUpdateActivityPlan(activityBudget);
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
			ActivityBudget cd = activityBudgetController.doListEntityById(id);
			if (null != cd) {
				activityBudgetController.doDeleteByEntity(cd);
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
				activityBudgetController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public ActivityBudget getActivityBudget() {
		return activityBudget;
	}

	public void setActivityBudget(ActivityBudget activityBudget) {
		this.activityBudget = activityBudget;
	}

	public List<ActivityBudget> getActivityBudgetList() {
		return activityBudgetList;
	}

	public void setActivityBudgetList(List<ActivityBudget> activityBudgetList) {
		this.activityBudgetList = activityBudgetList;
	}

}
