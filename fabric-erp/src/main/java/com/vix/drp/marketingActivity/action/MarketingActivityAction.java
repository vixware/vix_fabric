package com.vix.drp.marketingActivity.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.marketingActivity.controller.MarketingActivityController;
import com.vix.drp.marketingActivity.entity.MarketActivity;

@Controller
@Scope("prototype")
public class MarketingActivityAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private MarketingActivityController marketingActivityController;
	private String id;
	private ChannelDistributor channelDistributor;
	private List<ChannelDistributor> channelDistributorList;

	private MarketActivity marketActivity;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = marketingActivityController.findPagerByHqlConditions(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				channelDistributor = marketingActivityController.doListChannelDistributorById(id);
			} else {
				channelDistributor = new ChannelDistributor();
				channelDistributor.setIsTemp("1");
				channelDistributor = marketingActivityController.doSaveChannelDistributor(channelDistributor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goSaveOrUpdateMarketActivity() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				marketActivity = marketingActivityController.doListMarketActivityById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateMarketActivity";
	}

	/**
	 * 保存渠道
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != channelDistributor.getId() && !"".equals(channelDistributor.getId())) {
				isSave = false;
			}
			channelDistributor.setIsTemp("");
			channelDistributor = marketingActivityController.doSaveChannelDistributor(channelDistributor);
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

	/**
	 * 保存产品投放信息
	 * 
	 * @return
	 */
	public String saveOrUpdateMarketActivity() {
		boolean isSave = true;
		try {
			if (null != marketActivity.getId() && !"".equals(marketActivity.getId())) {
				isSave = false;
			}
			channelDistributor = marketingActivityController.doListChannelDistributorById(id);
			marketActivity.setChannelDistributor(channelDistributor);
			marketActivity = marketingActivityController.doSaveMarketActivity(marketActivity);
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
			ChannelDistributor channelDistributor = marketingActivityController.doListChannelDistributorById(id);
			if (null != channelDistributor) {
				marketingActivityController.doDeleteByEntity(channelDistributor);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteMarketActivityById() {
		try {
			MarketActivity marketActivity = marketingActivityController.doListMarketActivityById(id);
			if (null != marketActivity) {
				marketingActivityController.doDeleteMarketActivityByEntity(marketActivity);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public void getMarketActivityJson() {
		try {

			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				ChannelDistributor channelDistributor = marketingActivityController.doListChannelDistributorById(id);
				if (channelDistributor != null) {
					json = convertListToJson(new ArrayList<MarketActivity>(channelDistributor.getMarketActivitys()), channelDistributor.getMarketActivitys().size());
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

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public List<ChannelDistributor> getChannelDistributorList() {
		return channelDistributorList;
	}

	public void setChannelDistributorList(List<ChannelDistributor> channelDistributorList) {
		this.channelDistributorList = channelDistributorList;
	}

	public MarketActivity getMarketActivity() {
		return marketActivity;
	}

	public void setMarketActivity(MarketActivity marketActivity) {
		this.marketActivity = marketActivity;
	}

}
