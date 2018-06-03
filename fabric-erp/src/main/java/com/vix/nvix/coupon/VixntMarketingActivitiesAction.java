package com.vix.nvix.coupon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.process.vreport.model.FlowTask;
import com.process.vreport.service.FlowTaskService;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.controller.MarketingActivitiesController;
import com.vix.crm.business.entity.MarketingActivities;
import com.vix.crm.business.entity.MembershipSubdivision;
import com.vix.crm.business.entity.MessageTemplate;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.order.orderProcess.service.IOrderProcessService;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class VixntMarketingActivitiesAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;
	private String id;
	private String parentId;
	private String memberShipSubdivisionId;
	private String pageNo;
	/**
	 * 活动流程
	 */
	private MarketingActivities marketingActivities;
	private String activityFlow;
	private List<MembershipSubdivision> membershipSubdivisionList;
	@Autowired
	private MarketingActivitiesController marketingActivitiesController;
	@Autowired
	private FlowTaskService flowTaskService;
	@Autowired
	private IOrderProcessService orderProcessService;
	/**
	 * 短信模板
	 */
	private List<MessageTemplate> messageTemplateList;
	private String executingProcessData;

	public void goListContent() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}

			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
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
			pager = marketingActivitiesController.doListMarketingActivities(params, pager);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取会员细分条件
	 * 
	 * @return
	 */
	public String goMemberShipSubdivisionList() {
		return "goMemberShipSubdivisionList";
	}

	public String goMemberShipSubdivisionListContent() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = marketingActivitiesController.doListMembershipSubdivisionPager(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMemberShipSubdivisionListContent";
	}

	public String goSaveOrUpdate() {
		try {
			Map<String, Object> p = getParams();
			messageTemplateList = marketingActivitiesController.doListMessageTemplateList(p);
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			membershipSubdivisionList = marketingActivitiesController.doListMembershipSubdivision(params);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				marketingActivities = marketingActivitiesController.doListMarketingActivitiesById(id);
				if (marketingActivities != null) {
					activityFlow = marketingActivities.getActivityFlow();
				}
			} else {
				marketingActivities = new MarketingActivities();
				marketingActivities.setIsTemp("1");
				marketingActivities = marketingActivitiesController.doSaveMarketingActivities(marketingActivities);
				if (memberShipSubdivisionId != null && !"".equals(memberShipSubdivisionId)) {
					MembershipSubdivision membershipSubdivision = marketingActivitiesController.doListMembershipSubdivisionById(memberShipSubdivisionId);
					if (membershipSubdivision != null) {
						Set<MarketingActivities> marketingActivitiesSet = new HashSet<MarketingActivities>();
						marketingActivitiesSet.add(marketingActivities);
						membershipSubdivision.setSubMarketingActivities(marketingActivitiesSet);
						marketingActivitiesController.doSaveMembershipSubdivision(membershipSubdivision);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != marketingActivities.getId() && !"".equals(marketingActivities.getId())) {
				isSave = false;
			}
			marketingActivities.setIsTemp("");
			initEntityBaseController.initEntityBaseAttribute(marketingActivities);
			// 处理修改留痕
			billMarkProcessController.processMark(marketingActivities, updateField);
			marketingActivities = marketingActivitiesController.doSaveMarketingActivities(marketingActivities);
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

	public String goStartFlowTask() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				marketingActivities = marketingActivitiesController.doListMarketingActivitiesById(id);
				FlowTask f = flowTaskService.parseFlowTask(String.valueOf(marketingActivities.getId()), marketingActivities.getActivityFlow());
				flowTaskService.startFlowTask(f);
				setMessage(marketingActivities.getName() + " 启动了...");
			}
		} catch (Exception e) {
			this.setMessage("启动失败!");
			e.printStackTrace();
		}
		return UPDATE;
	}

	public String goStopFlowTask() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				flowTaskService.stopFLowTask(String.valueOf(id));
			}
		} catch (Exception e) {
			this.setMessage("停止失败!");
			e.printStackTrace();
		}
		return UPDATE;
	}

	public String saveOrUpdateMessage() {
		boolean isSave = true;
		try {
			if (null != marketingActivities.getId() && !"".equals(marketingActivities.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(marketingActivities);
			marketingActivities = marketingActivitiesController.doSaveMarketingActivities(marketingActivities);
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

	/**
	 * 获取会员细分树结构
	 */
	public void findMembershipSubdivisionTree() {
		try {
			List<MembershipSubdivision> membershipSubdivisionList = new ArrayList<MembershipSubdivision>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			membershipSubdivisionList = orderProcessService.findAllByConditions(MembershipSubdivision.class, params);
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = membershipSubdivisionList.size();
			for (int i = 0; i < count; i++) {
				MembershipSubdivision org = membershipSubdivisionList.get(i);
				strSb.append("{id:\"");
				strSb.append(org.getId());
				strSb.append("\",name:\"");
				strSb.append(org.getName());
				strSb.append("\",open:false,isParent:false}");
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void findStoreTypeTreeToJson() {
		try {
			List<ChannelDistributor> channelDistributorList = new ArrayList<ChannelDistributor>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			channelDistributorList = vixntBaseService.findAllByConditions(ChannelDistributor.class, params);
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = channelDistributorList.size();
			for (int i = 0; i < count; i++) {
				ChannelDistributor channelDistributor = channelDistributorList.get(i);
				strSb.append("{id:\"");
				strSb.append(channelDistributor.getId());
				strSb.append("\",name:\"");
				strSb.append(channelDistributor.getName());
				strSb.append("\",open:false,isParent:false}");
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
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

	public MarketingActivities getMarketingActivities() {
		return marketingActivities;
	}

	public void setMarketingActivities(MarketingActivities marketingActivities) {
		this.marketingActivities = marketingActivities;
	}

	public String getMemberShipSubdivisionId() {
		return memberShipSubdivisionId;
	}

	public void setMemberShipSubdivisionId(String memberShipSubdivisionId) {
		this.memberShipSubdivisionId = memberShipSubdivisionId;
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

	public List<MessageTemplate> getMessageTemplateList() {
		return messageTemplateList;
	}

	public void setMessageTemplateList(List<MessageTemplate> messageTemplateList) {
		this.messageTemplateList = messageTemplateList;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
