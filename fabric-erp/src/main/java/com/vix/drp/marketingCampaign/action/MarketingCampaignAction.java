package com.vix.drp.marketingCampaign.action;

import java.util.ArrayList;
import java.util.Date;
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
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.marketingCampaign.controller.MarketingCampaignController;
import com.vix.drp.marketingCampaign.entity.MarketingCampaign;
import com.vix.drp.marketingCampaign.entity.MarketingCampaignDetail;
import com.vix.drp.marketingCampaign.entity.MarketingCampaignTask;
import com.vix.hr.organization.entity.Employee;

import flexjson.JSONDeserializer;
import flexjson.transformer.DateTransformer;

@Controller
@Scope("prototype")
public class MarketingCampaignAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private MarketingCampaignController marketingCampaignController;

	private String id;
	private String ids;
	private String pageNo;
	private MarketingCampaign marketingCampaign;

	private List<MarketingCampaign> marketingCampaignList;

	private String channelDistributorId;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
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
			marketingCampaignList = marketingCampaignController.doListMarketingCampaignList(params);
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
			String name = getDecodeRequestParameter("name");
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
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
			pager = marketingCampaignController.doListMarketingCampaignPager(params, pager);
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
				marketingCampaign = marketingCampaignController.doListMarketingCampaignById(id);
			} else {
				marketingCampaign = new MarketingCampaign();
				Employee employee = getEmployee();
				if (employee != null) {
					marketingCampaign.setCreator(employee.getName());
				} else {
					marketingCampaign.setCreator(SecurityUtil.getCurrentUserName());
				}
				marketingCampaign = marketingCampaignController.doSaveOrUpdateMarketingCampaign(marketingCampaign, null);
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
			if (null != marketingCampaign.getId() && !"".equals(marketingCampaign.getId())) {
				isSave = false;
			}
			String marketingCampaignDetailJson = getRequestParameter("marketingCampaignDetailJson");
			List<MarketingCampaignDetail> marketingCampaignDetailList = null;
			if (marketingCampaignDetailJson != null && !"".equals(marketingCampaignDetailJson)) {
				DateTransformer dateTransformer = new DateTransformer("yyyy-MM-dd");
				marketingCampaignDetailList = new JSONDeserializer<List<MarketingCampaignDetail>>().use(Date.class, dateTransformer).use("values", MarketingCampaignDetail.class).deserialize(marketingCampaignDetailJson);
			}
			String chineseCharacter = ChnToPinYin.getPYString(marketingCampaign.getName());
			marketingCampaign.setChineseCharacter(chineseCharacter);
			initEntityBaseController.initEntityBaseAttribute(marketingCampaign);
			//处理修改留痕
			billMarkProcessController.processMark(marketingCampaign, updateField);
			marketingCampaign = marketingCampaignController.doSaveOrUpdateMarketingCampaign(marketingCampaign, marketingCampaignDetailList);
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
			MarketingCampaign marketingCampaign = marketingCampaignController.doListMarketingCampaignById(id);
			if (null != marketingCampaign) {
				marketingCampaignController.doDeleteMarketingCampaignByEntity(marketingCampaign);
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

	/** 获取明细的json数据 */
	public void getMarketingCampaignDetailJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				MarketingCampaign marketingCampaign = marketingCampaignController.doListMarketingCampaignById(id);
				if (marketingCampaign != null) {
					json = convertListToJson(new ArrayList<MarketingCampaignDetail>(marketingCampaign.getMarketingCampaignDetails()), marketingCampaign.getMarketingCampaignDetails().size());
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
	 * 活动下达
	 * 
	 * @return
	 */
	public String goMarketingCampaign() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				marketingCampaign = marketingCampaignController.doListMarketingCampaignById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goMarketingCampaign";
	}

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdateMarketingCampaign() {
		boolean isSave = true;
		try {
			if (null != marketingCampaign.getId() && !"".equals(marketingCampaign.getId())) {
				isSave = false;
			}
			Map<String, Object> params = new HashMap<String, Object>();

			if (channelDistributorId != null && !"".equals(channelDistributorId)) {
				params.put("id," + SearchCondition.IN, channelDistributorId);
			}
			marketingCampaign = marketingCampaignController.doListMarketingCampaignById(marketingCampaign.getId());
			List<ChannelDistributor> channelDistributorList = marketingCampaignController.doListChannelDistributorList(params);
			if (channelDistributorList != null && channelDistributorList.size() > 0) {
				for (ChannelDistributor c : channelDistributorList) {
					/**
					 * 活动下达 ,即创建任务
					 */
					MarketingCampaignTask marketingCampaignTask = new MarketingCampaignTask();
					marketingCampaignTask.setMarketingCampaign(marketingCampaign);
					marketingCampaignTask.setChannelDistributor(c);
					marketingCampaignTask.setCreateTime(new Date());
					String chineseCharacter = ChnToPinYin.getPYString(marketingCampaign.getName());
					marketingCampaignTask.setName(marketingCampaign.getName());
					marketingCampaign.setChineseCharacter(chineseCharacter);
					marketingCampaignTask.setStatus("1");
					marketingCampaignController.doSaveOrUpdateMarketingCampaignTask(marketingCampaignTask);
				}
			}
			// 活动已下达
			marketingCampaign.setStatus("2");
			marketingCampaign = marketingCampaignController.doSaveOrUpdateMarketingCampaign(marketingCampaign);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				setMessage("活动下达成功");
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
				marketingCampaignController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String goChooseChannelDistributor() {
		return "goChooseChannelDistributor";
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getChannelDistributorId() {
		return channelDistributorId;
	}

	public void setChannelDistributorId(String channelDistributorId) {
		this.channelDistributorId = channelDistributorId;
	}

	public MarketingCampaign getMarketingCampaign() {
		return marketingCampaign;
	}

	public void setMarketingCampaign(MarketingCampaign marketingCampaign) {
		this.marketingCampaign = marketingCampaign;
	}

	public List<MarketingCampaign> getMarketingCampaignList() {
		return marketingCampaignList;
	}

	public void setMarketingCampaignList(List<MarketingCampaign> marketingCampaignList) {
		this.marketingCampaignList = marketingCampaignList;
	}

}
