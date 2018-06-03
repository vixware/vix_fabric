package com.vix.nvix.coupon;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.camel.ServiceStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.process.vreport.model.FlowTask;
import com.process.vreport.model.Node;
import com.vix.common.id.VixUUID;
import com.vix.common.message.entity.MessageSendTemplate;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.controller.MarketingProcessManagementController;
import com.vix.crm.business.entity.Coupon;
import com.vix.crm.business.entity.EmailTemplate;
import com.vix.crm.business.entity.MarketingActivities;
import com.vix.crm.business.entity.MembershipSubdivision;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class VixntMarketingProcessManagementAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	private String id;
	private String activityFlow;
	private List<MembershipSubdivision> membershipSubdivisionList;
	private String serverStatus;
	/**
	 * 活动流程
	 */
	private MarketingActivities marketingActivities;
	/**
	 * 店铺
	 */
	private List<ChannelDistributor> channelDistributorList;
	/**
	 * 短信模板
	 */
	private List<MessageSendTemplate> messageSendTemplateList;
	/**
	 * 邮件模板
	 */
	private List<EmailTemplate> emailTemplateList;
	/**
	 * 优惠券
	 */
	private List<Coupon> couponList;
	@Autowired
	private MarketingProcessManagementController marketingProcessManagementController;

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

	public String goSaveOrUpdate() {
		try {
			// 邮件
			Map<String, Object> e = getParams();
			emailTemplateList = marketingProcessManagementController.doListEmailTemplateList(e);
			// 门店
			Map<String, Object> cparams = getParams();
			cparams.put("type," + SearchCondition.ANYLIKE, "5");
			channelDistributorList = marketingProcessManagementController.doListChannelDistributorList(cparams);
			// 短信
			Map<String, Object> p = getParams();
			messageSendTemplateList = marketingProcessManagementController.doListMessageSendTemplateList(p);
			// 优惠券
			Map<String, Object> couponMap = getParams();
			couponList = marketingProcessManagementController.doListCouponList(couponMap);
			// 会员细分
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			membershipSubdivisionList = marketingProcessManagementController.doListMembershipSubdivision(params);
			if (id != null && !"".equals(id)) {
				marketingActivities = marketingProcessManagementController.doListMarketingActivitiesById(id);
				if (marketingActivities != null) {
					activityFlow = marketingActivities.getActivityFlow();
				}
			} else {
				marketingActivities = new MarketingActivities();
				marketingActivities.setCode(VixUUID.getUUID());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != marketingActivities.getId() && !"".equals(marketingActivities.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(marketingActivities);
			marketingActivities.setStatus("2");
			marketingActivities = marketingProcessManagementController.doSaveMarketingActivities(marketingActivities);
			if (marketingActivities != null && StringUtils.isNotEmpty(marketingActivities.getActivityFlow())) {
				FlowTask f = flowTaskService.parseFlowTask(marketingActivities.getActivityFlow());
				Map<String, Node> nodeMap = f.getNodeMap();
				Set<Map.Entry<String, Node>> set = nodeMap.entrySet();
				for (Iterator<Map.Entry<String, Node>> it = set.iterator(); it.hasNext();) {
					Map.Entry<String, Node> entry = it.next();
					if ("com.process.vreport.processor.ClientSelectionProcessor".equals(entry.getValue().getValue())) {
						if (entry.getValue().getParam() != null && !"".equals(entry.getValue().getParam())) {
							MembershipSubdivision membershipSubdivision = marketingProcessManagementController.doListMembershipSubdivisionById(entry.getValue().getParam());
							if (membershipSubdivision != null) {
								Set<MarketingActivities> marketingActivitiesSet = new HashSet<MarketingActivities>();
								marketingActivitiesSet.add(marketingActivities);
								membershipSubdivision.setSubMarketingActivities(marketingActivitiesSet);
								membershipSubdivision = marketingProcessManagementController.doSaveMembershipSubdivision(membershipSubdivision);
							}
						}
					}
				}
			}
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
	}

	/**
	 * 启动流程
	 * 
	 * @return
	 */
	public void goStartFlowTask() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				marketingActivities = marketingProcessManagementController.doListMarketingActivitiesById(id);
				FlowTask f = flowTaskService.parseFlowTask(marketingActivities.getId(), marketingActivities.getActivityFlow());
				flowTaskService.startFlowTask(f);
				renderText(marketingActivities.getName() + " 启动了...");
			}
		} catch (Exception e) {
			renderText("启动失败!");
			e.printStackTrace();
		}
	}

	/**
	 * 停止流程
	 * 
	 * @return
	 */
	public void goStopFlowTask() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				flowTaskService.stopFLowTask(id);
				marketingActivities = vixntBaseService.findEntityById(MarketingActivities.class, id);
				if (marketingActivities != null) {
					ServiceStatus serviceStatus = flowTaskService.getFlowTaskStatus(id);
					if (serviceStatus != null && serviceStatus.isStopped()) {
						marketingActivities.setStatus("2");
						marketingActivities = vixntBaseService.merge(marketingActivities);
					}
				}
				renderText("停止成功!");
			}

		} catch (Exception e) {
			renderText("停止失败!");
			e.printStackTrace();
		}
	}

	public void deleteById() {
		try {
			MarketingActivities marketingActivities = vixntBaseService.findEntityById(MarketingActivities.class, id);
			if (marketingActivities != null) {
				vixntBaseService.deleteByEntity(marketingActivities);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
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

	public String getServerStatus() {
		return serverStatus;
	}

	public void setServerStatus(String serverStatus) {
		this.serverStatus = serverStatus;
	}

	public List<Coupon> getCouponList() {
		return couponList;
	}

	public void setCouponList(List<Coupon> couponList) {
		this.couponList = couponList;
	}

	public List<ChannelDistributor> getChannelDistributorList() {
		return channelDistributorList;
	}

	public void setChannelDistributorList(List<ChannelDistributor> channelDistributorList) {
		this.channelDistributorList = channelDistributorList;
	}

	public List<EmailTemplate> getEmailTemplateList() {
		return emailTemplateList;
	}

	public void setEmailTemplateList(List<EmailTemplate> emailTemplateList) {
		this.emailTemplateList = emailTemplateList;
	}

	public List<MessageSendTemplate> getMessageSendTemplateList() {
		return messageSendTemplateList;
	}

	public void setMessageSendTemplateList(List<MessageSendTemplate> messageSendTemplateList) {
		this.messageSendTemplateList = messageSendTemplateList;
	}

	public MarketingActivities getMarketingActivities() {
		return marketingActivities;
	}

	public void setMarketingActivities(MarketingActivities marketingActivities) {
		this.marketingActivities = marketingActivities;
	}

	public List<MembershipSubdivision> getMembershipSubdivisionList() {
		return membershipSubdivisionList;
	}

	public void setMembershipSubdivisionList(List<MembershipSubdivision> membershipSubdivisionList) {
		this.membershipSubdivisionList = membershipSubdivisionList;
	}
}