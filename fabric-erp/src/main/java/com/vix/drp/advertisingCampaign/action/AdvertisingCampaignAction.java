package com.vix.drp.advertisingCampaign.action;

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
import com.vix.drp.advertisingCampaign.controller.AdvertisingCampaignController;
import com.vix.drp.advertisingCampaign.entity.AdvertisingCampaign;
import com.vix.drp.channel.entity.ChannelDistributor;

@Controller
@Scope("prototype")
public class AdvertisingCampaignAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private AdvertisingCampaignController advertisingCampaignController;
	private String id;
	private ChannelDistributor channelDistributor;
	private List<ChannelDistributor> channelDistributorList;

	private AdvertisingCampaign advertisingCampaign;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = advertisingCampaignController.findPagerByHqlConditions(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				channelDistributor = advertisingCampaignController.doListChannelDistributorById(id);
			} else {
				channelDistributor = new ChannelDistributor();
				channelDistributor.setIsTemp("1");
				channelDistributor = advertisingCampaignController.doSaveChannelDistributor(channelDistributor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goSaveOrUpdateAdvertisingCampaign() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				advertisingCampaign = advertisingCampaignController.doListAdvertisingCampaignById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateAdvertisingCampaign";
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
			channelDistributor = advertisingCampaignController.doSaveChannelDistributor(channelDistributor);
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
	public String saveOrUpdateAdvertisingCampaign() {
		boolean isSave = true;
		try {
			if (null != advertisingCampaign.getId() && !"".equals(advertisingCampaign.getId())) {
				isSave = false;
			}
			channelDistributor = advertisingCampaignController.doListChannelDistributorById(id);
			advertisingCampaign.setChannelDistributor(channelDistributor);
			advertisingCampaign = advertisingCampaignController.doSaveAdvertisingCampaign(advertisingCampaign);
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
			ChannelDistributor channelDistributor = advertisingCampaignController.doListChannelDistributorById(id);
			if (null != channelDistributor) {
				advertisingCampaignController.doDeleteByEntity(channelDistributor);
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

	/** 处理删除操作 */
	public String deleteAdvertisingCampaignById() {
		try {
			AdvertisingCampaign advertisingCampaign = advertisingCampaignController.doListAdvertisingCampaignById(id);
			if (null != advertisingCampaign) {
				advertisingCampaignController.doDeleteAdvertisingCampaignByEntity(advertisingCampaign);
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

	public void getAdvertisingCampaignJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {//if (null != id && !"".equals(id)) {
				ChannelDistributor channelDistributor = advertisingCampaignController.doListChannelDistributorById(id);
				if (channelDistributor != null) {
					json = convertListToJson(new ArrayList<AdvertisingCampaign>(channelDistributor.getAdvertisingCampaigns()), channelDistributor.getAdvertisingCampaigns().size());
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

	public AdvertisingCampaign getAdvertisingCampaign() {
		return advertisingCampaign;
	}

	public void setAdvertisingCampaign(AdvertisingCampaign advertisingCampaign) {
		this.advertisingCampaign = advertisingCampaign;
	}

}
