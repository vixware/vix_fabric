package com.vix.drp.marketingExecutive.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.marketingCampaign.entity.MarketingCampaignTask;
import com.vix.drp.marketingExecutive.controller.MarketingExecutiveController;

@Controller
@Scope("prototype")
public class MarketingExecutiveAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private MarketingExecutiveController marketingExecutiveController;

	private String id;
	private String ids;
	private String pageNo;
	private MarketingCampaignTask marketingCampaignTask;

	private List<MarketingCampaignTask> marketingCampaignTaskList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			//处理搜索条件
			String name = getDecodeRequestParameter("name");
			if (name != null && !"".equals(name)) {
				params.put("marketingCampaign.name," + SearchCondition.ANYLIKE, name);
			}
			String selectContent = getDecodeRequestParameter("selectContent");
			if (selectContent != null && !"".equals(selectContent)) {
				params.put("marketingCampaign.name," + SearchCondition.ANYLIKE, selectContent);
			}
			String feedbackContent = getDecodeRequestParameter("feedbackContent");
			if (feedbackContent != null && !"".equals(feedbackContent)) {
				params.put("feedbackContent," + SearchCondition.ANYLIKE, feedbackContent);
			}
			//处理搜索条件
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			marketingCampaignTaskList = marketingExecutiveController.doListMarketingCampaignTaskList(params);
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
			//处理搜索条件
			String name = getDecodeRequestParameter("name");
			if (name != null && !"".equals(name)) {
				params.put("marketingCampaign.name," + SearchCondition.ANYLIKE, name);
			}
			String selectContent = getDecodeRequestParameter("selectContent");
			if (selectContent != null && !"".equals(selectContent)) {
				params.put("marketingCampaign.name," + SearchCondition.ANYLIKE, selectContent);
			}
			String feedbackContent = getDecodeRequestParameter("feedbackContent");
			if (feedbackContent != null && !"".equals(feedbackContent)) {
				params.put("feedbackContent," + SearchCondition.ANYLIKE, feedbackContent);
			}
			//处理搜索条件
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			pager = marketingExecutiveController.doListMarketingCampaignTask(params, pager);
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
				marketingCampaignTask = marketingExecutiveController.doListMarketingCampaignTaskById(id);
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
			if (null != marketingCampaignTask.getId() && !"".equals(marketingCampaignTask.getId())) {
				isSave = false;
			}
			MarketingCampaignTask p = marketingExecutiveController.doListMarketingCampaignTaskById(marketingCampaignTask.getId());
			p.setFeedbackContent(marketingCampaignTask.getFeedbackContent());
			marketingCampaignTask.setCreator(SecurityUtil.getCurrentUserName());
			marketingCampaignTask.setStatus("2");
			marketingCampaignTask = marketingExecutiveController.doSaveOrUpdateMarketingCampaignTask(p);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				setMessage("反馈成功");
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

	//action
	/** 处理单条删除操作 */
	public String deleteById() {
		try {
			MarketingCampaignTask marketingCampaignTask = marketingExecutiveController.doListMarketingCampaignTaskById(id);
			if (null != marketingCampaignTask) {
				marketingExecutiveController.doDeleteByEntity(marketingCampaignTask);
				logger.info("");
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
				marketingExecutiveController.doDeleteByIds(delIds);
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
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
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

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public MarketingCampaignTask getMarketingCampaignTask() {
		return marketingCampaignTask;
	}

	public void setMarketingCampaignTask(MarketingCampaignTask marketingCampaignTask) {
		this.marketingCampaignTask = marketingCampaignTask;
	}

	public List<MarketingCampaignTask> getMarketingCampaignTaskList() {
		return marketingCampaignTaskList;
	}

	public void setMarketingCampaignTaskList(List<MarketingCampaignTask> marketingCampaignTaskList) {
		this.marketingCampaignTaskList = marketingCampaignTaskList;
	}
}
