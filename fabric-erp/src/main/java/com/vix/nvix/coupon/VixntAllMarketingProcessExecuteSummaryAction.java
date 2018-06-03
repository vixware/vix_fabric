package com.vix.nvix.coupon;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.controller.MarketingProcessManagementController;
import com.vix.crm.business.entity.MarketingActivities;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class VixntAllMarketingProcessExecuteSummaryAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private MarketingProcessManagementController marketingProcessManagementController;
	private String id;
	private String activityFlow;
	/**
	 * 活动流程
	 */
	private MarketingActivities marketingActivities;

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				marketingActivities = marketingProcessManagementController.doListMarketingActivitiesById(id);
				if (marketingActivities != null) {
					activityFlow = marketingActivities.getActivityFlow();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public void goListContent() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (null != pageNo) {
				pager.setPageNo(pageNo);
			}
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
			String name = getDecodeRequestParameter("name"); // 活动主题
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			pager = marketingProcessManagementController.doListMarketingActivities(params, pager);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MarketingActivities getMarketingActivities() {
		return marketingActivities;
	}

	public void setMarketingActivities(MarketingActivities marketingActivities) {
		this.marketingActivities = marketingActivities;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getActivityFlow() {
		return activityFlow;
	}

	public void setActivityFlow(String activityFlow) {
		this.activityFlow = activityFlow;
	}

}
