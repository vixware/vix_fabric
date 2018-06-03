package com.vix.nvix.coupon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.camel.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.process.vreport.model.NodeMonitor;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.controller.MarketingAutomationProcessController;
import com.vix.crm.business.entity.MarketingActivities;
import com.vix.crm.business.entity.MembershipSubdivision;
import com.vix.ebusiness.order.orderProcess.hql.OrderProcessHqlProvider;
import com.vix.ebusiness.order.orderProcess.service.IOrderProcessService;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class VixntMarketingAutomationProcessAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	private String id;
	private String activityFlow;
	private MarketingActivities marketingActivities;
	@Autowired
	private MarketingAutomationProcessController marketingAutomationProcessController;
	// 获取正在执行的流程
	private String executingProcessData;
	@Autowired
	private IOrderProcessService orderProcessService;
	@Autowired
	private OrderProcessHqlProvider orderProcessHqlProvider;
	private List<MembershipSubdivision> membershipSubdivisionList;

	public void goSingleList() {
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
			pager = marketingAutomationProcessController.doListMarketingActivities(params, pager);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goNodesLogList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			if (null != pageNo) {
				pager.setPageNo(pageNo);
			}
			pager = marketingAutomationProcessController.doListNodesLogPager(params, pager);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() throws Exception {
		Map<String, Object> p = getParams();
		// 过滤临时数据
		p.put("isTemp," + SearchCondition.NOEQUAL, "1");
		membershipSubdivisionList = marketingAutomationProcessController.doListMembershipSubdivision(p);
		if (id != null && !"".equals(id)) {
			marketingActivities = marketingAutomationProcessController.doListMarketingActivitiesById(id);
			if (marketingActivities != null) {
				activityFlow = marketingActivities.getActivityFlow();
				ServiceStatus serviceStatus = flowTaskService.getFlowTaskStatus(marketingActivities.getId());
				if (serviceStatus != null && serviceStatus.isStarted()) {
					marketingActivities.setStatus("1");
					marketingActivities = vixntBaseService.merge(marketingActivities);
				} else {
					marketingActivities.setStatus("2");
					marketingActivities = vixntBaseService.merge(marketingActivities);
				}

				if (marketingActivities != null) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("routeId", marketingActivities.getId());
					StringBuilder hql = orderProcessHqlProvider.findNodeMonitor(params);
					NodeMonitor nodeMonitor = orderProcessService.findObjectByHqlNoTenantId(hql.toString(), params);
					if (nodeMonitor != null) {
						executingProcessData = "{" + nodeMonitor.getLinkId() + ": {fromNodeId : '" + nodeMonitor.getFormNodeId() + "',nodeId : '" + nodeMonitor.getNodeId() + "'}}";
					}
				}
			}
		}
		return GO_SAVE_OR_UPDATE;
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

	public MarketingActivities getMarketingActivities() {
		return marketingActivities;
	}

	public void setMarketingActivities(MarketingActivities marketingActivities) {
		this.marketingActivities = marketingActivities;
	}

	public String getExecutingProcessData() {
		return executingProcessData;
	}

	public void setExecutingProcessData(String executingProcessData) {
		this.executingProcessData = executingProcessData;
	}

	public List<MembershipSubdivision> getMembershipSubdivisionList() {
		return membershipSubdivisionList;
	}

	public void setMembershipSubdivisionList(List<MembershipSubdivision> membershipSubdivisionList) {
		this.membershipSubdivisionList = membershipSubdivisionList;
	}

}
