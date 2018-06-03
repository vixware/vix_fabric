package com.vix.crm.business.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.crm.business.controller.OrderManagementController;
import com.vix.crm.business.entity.ExpeditingSetting;
import com.vix.crm.business.entity.MessageTemplate;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.sales.order.entity.SalesOrder;

@Controller
@Scope("prototype")
public class OrderExpeditingAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String pageNo;
	@Autowired
	private OrderManagementController orderManagementController;
	/**
	 * 店铺
	 */
	private List<ChannelDistributor> channelDistributorList;
	/**
	 * 短信模板
	 */
	private List<MessageTemplate> messageTemplateList;

	private ExpeditingSetting expeditingSetting;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			params.put("type," + SearchCondition.ANYLIKE, "5");
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
			channelDistributorList = orderManagementController.doListChannelDistributorList(params);
			Map<String, Object> p = getParams();

			messageTemplateList = orderManagementController.doListMessageTemplateList(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public String goSingleList() {
		return GO_SINGLE_LIST;
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != expeditingSetting.getId() && !"".equals(expeditingSetting.getId())) {
				isSave = false;
			}
			expeditingSetting.setType("1");
			initEntityBaseController.initEntityBaseAttribute(expeditingSetting);
			expeditingSetting = orderManagementController.doSaveExpeditingSetting(expeditingSetting);

			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	@Scheduled(cron = "0 5/5 17 * * ?*")
	void expediting() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			List<SalesOrder> salesOrderList = orderManagementController.doListSalesOrderList(params);
			if (salesOrderList != null) {

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSearch() {
		return "goSearch";
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public ExpeditingSetting getExpeditingSetting() {
		return expeditingSetting;
	}

	public void setExpeditingSetting(ExpeditingSetting expeditingSetting) {
		this.expeditingSetting = expeditingSetting;
	}

	public List<MessageTemplate> getMessageTemplateList() {
		return messageTemplateList;
	}

	public void setMessageTemplateList(List<MessageTemplate> messageTemplateList) {
		this.messageTemplateList = messageTemplateList;
	}

	public List<ChannelDistributor> getChannelDistributorList() {
		return channelDistributorList;
	}

	public void setChannelDistributorList(List<ChannelDistributor> channelDistributorList) {
		this.channelDistributorList = channelDistributorList;
	}

}
