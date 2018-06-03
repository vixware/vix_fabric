package com.vix.drp.marketingCampaignTracking.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.marketingCampaign.entity.MarketingCampaign;
import com.vix.drp.marketingCampaign.entity.MarketingCampaignTask;
import com.vix.drp.marketingCampaignTracking.controller.MarketingCampaignTrackingController;

@Controller
@Scope("prototype")
public class MarketingCampaignTrackingAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private MarketingCampaignTrackingController marketingCampaignTrackingController;

	private String id;
	private String ids;
	private String pageNo;
	private MarketingCampaignTask marketingCampaignTask;
	private String parentId;
	private List<MarketingCampaignTask> marketingCampaignTaskList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (parentId != null && !"".equals(parentId)) {
				params.put("marketingCampaign.id," + SearchCondition.EQUAL, parentId);
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
			marketingCampaignTaskList = marketingCampaignTrackingController.doListMarketingCampaignTaskList(params);
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
			if (parentId != null && !"".equals(parentId)) {
				params.put("marketingCampaign.id," + SearchCondition.EQUAL, parentId);
			}
			//处理搜索条件
			String name = getDecodeRequestParameter("name");
			if (name != null && !"".equals(name)) {
				params.put("marketingCampaign.name," + SearchCondition.ANYLIKE, name);
			}
			String selectContent = getDecodeRequestParameter("selectContent");
			if (selectContent != null && !"".equals(selectContent)) {
				params.put("marketingCampaign.name," + SearchCondition.ANYLIKE, selectContent);
			}
			String feedbackContent = getDecodeRequestParameter("feedbackContent");
			if (feedbackContent != null && !"".equals(feedbackContent)) {
				params.put("feedbackContent," + SearchCondition.ANYLIKE, feedbackContent);
			} // 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			//处理搜索条件
			pager = marketingCampaignTrackingController.doListMarketingCampaignTask(params, pager);
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
				marketingCampaignTask = marketingCampaignTrackingController.doListMarketingCampaignTaskById(id);
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
			if (null != marketingCampaignTask.getId() && !"".equals(marketingCampaignTask.getId())) {
				isSave = false;
			}
			MarketingCampaignTask p = marketingCampaignTrackingController.doListMarketingCampaignTaskById(marketingCampaignTask.getId());
			p.setFeedbackContent(marketingCampaignTask.getFeedbackContent());
			marketingCampaignTask = marketingCampaignTrackingController.doSaveOrUpdateMarketingCampaignTask(p);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				setMessage("反馈成功");
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

	public void findMarketingCampaignTreeToJson() {
		try {
			List<MarketingCampaign> marketingCampaignList = new ArrayList<MarketingCampaign>();
			marketingCampaignList = baseHibernateService.findAllByEntityClass(MarketingCampaign.class);
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = marketingCampaignList.size();
			for (int i = 0; i < count; i++) {
				MarketingCampaign org = marketingCampaignList.get(i);
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

	//action
	/** 处理单条删除操作 */
	public String deleteById() {
		try {
			MarketingCampaignTask marketingCampaignTask = marketingCampaignTrackingController.doListMarketingCampaignTaskById(id);
			if (null != marketingCampaignTask) {
				marketingCampaignTrackingController.doDeleteByEntity(marketingCampaignTask);
				logger.info("");
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
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
				marketingCampaignTrackingController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
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

	/**
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public MarketingCampaignTask getMarketingCampaignTask() {
		return marketingCampaignTask;
	}

	public void setMarketingCampaignTask(MarketingCampaignTask marketingCampaignTask) {
		this.marketingCampaignTask = marketingCampaignTask;
	}

	public List<MarketingCampaignTask> getMarketingCampaignTaskList() {
		return marketingCampaignTaskList;
	}

	public void setMarketingCampaignTaskList(List<MarketingCampaignTask> marketingCampaignTaskList) {
		this.marketingCampaignTaskList = marketingCampaignTaskList;
	}

}
