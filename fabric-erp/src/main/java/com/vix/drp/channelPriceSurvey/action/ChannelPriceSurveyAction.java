package com.vix.drp.channelPriceSurvey.action;

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
import com.vix.drp.channelPriceSurvey.controller.ChannelPriceSurveyController;
import com.vix.drp.channelPriceSurvey.entity.ChannelPrice;

@Controller
@Scope("prototype")
public class ChannelPriceSurveyAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ChannelPriceSurveyController channelPriceSurveyController;
	private String id;
	private ChannelDistributor channelDistributor;
	private List<ChannelDistributor> channelDistributorList;

	private ChannelPrice channelPrice;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
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
			Pager pager = channelPriceSurveyController.findPagerByHqlConditions(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				channelDistributor = channelPriceSurveyController.doListChannelDistributorById(id);
			} else {
				channelDistributor = new ChannelDistributor();
				channelDistributor.setIsTemp("1");
				channelDistributor = channelPriceSurveyController.doSaveChannelDistributor(channelDistributor);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goSaveOrUpdateChannelPrice() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				channelPrice = channelPriceSurveyController.doListChannelPriceById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateChannelPrice";
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
			channelDistributor = channelPriceSurveyController.doSaveChannelDistributor(channelDistributor);
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
	 * 保存零售价格明细
	 * 
	 * @return
	 */
	public String saveOrUpdateChannelPrice() {
		boolean isSave = true;
		try {
			if (null != channelPrice.getId() && !"".equals(channelPrice.getId())) {
				isSave = false;
			}
			channelDistributor = channelPriceSurveyController.doListChannelDistributorById(id);
			channelPrice.setChannelDistributor(channelDistributor);
			channelPrice = channelPriceSurveyController.doSaveChannelPrice(channelPrice);
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
			ChannelDistributor channelDistributor = channelPriceSurveyController.doListChannelDistributorById(id);
			if (null != channelDistributor) {
				channelPriceSurveyController.doDeleteByEntity(channelDistributor);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteChannelPriceById() {
		try {
			ChannelPrice channelPrice = channelPriceSurveyController.doListChannelPriceById(id);
			if (null != channelPrice) {
				channelPriceSurveyController.doDeleteChannelPriceByEntity(channelPrice);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public void getRetailPriceSurveyDetailJson() {
		try {

			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {//if (null != id && !"".equals(id)) {
				ChannelDistributor channelDistributor = channelPriceSurveyController.doListChannelDistributorById(id);
				if (channelDistributor != null) {
					json = convertListToJson(new ArrayList<ChannelPrice>(channelDistributor.getChannelPrices()), channelDistributor.getChannelPrices().size());
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

	public ChannelPrice getChannelPrice() {
		return channelPrice;
	}

	public void setChannelPrice(ChannelPrice channelPrice) {
		this.channelPrice = channelPrice;
	}

}
