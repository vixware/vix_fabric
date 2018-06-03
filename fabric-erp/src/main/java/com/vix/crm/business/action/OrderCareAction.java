package com.vix.crm.business.action;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.controller.OrderManagementController;
import com.vix.crm.business.entity.ExpeditingSetting;
import com.vix.crm.business.entity.MessageTemplate;
import com.vix.drp.channel.entity.ChannelDistributor;

@Controller
@Scope("prototype")
public class OrderCareAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String pageNo;
	@Autowired
	private OrderManagementController orderManagementController;
	/**
	 * 店铺
	 */
	private List<ChannelDistributor> channelDistributorList;
	private ExpeditingSetting expeditingSetting;
	/**
	 * 短信模板
	 */
	private List<MessageTemplate> messageTemplateList;

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
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			params.put("dealState," + SearchCondition.EQUAL, 1);
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
			pager = orderManagementController.doListSalesOrderPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != expeditingSetting.getId() && !"".equals(expeditingSetting.getId())) {
				isSave = false;
			}
			expeditingSetting.setType("2");
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
