package com.vix.drp.retailPriceSurvey.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.BaseEmployee;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.retailPriceSurvey.controller.RetailPriceSurveyController;
import com.vix.drp.retailPriceSurvey.entity.RetailPriceSurvey;
import com.vix.drp.retailPriceSurvey.entity.RetailPriceSurveyDetail;

/**
 * 零售价格调查
 * 
 * @author zhanghaibing
 * 
 * @date 2014-2-7
 */
@Controller
@Scope("prototype")
public class RetailPriceSurveyAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private RetailPriceSurveyController retailPriceSurveyController;

	private String id;
	private RetailPriceSurvey retailPriceSurvey;
	private List<RetailPriceSurvey> retailPriceSurveyList;
	private RetailPriceSurveyDetail retailPriceSurveyDetail;

	/** 获取列表数据 */
	public String goSingleList() {
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
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = retailPriceSurveyController.findPagerByHqlConditions(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				retailPriceSurvey = retailPriceSurveyController.doListRetailPriceSurveyById(id);
			} else {
				/* 获取当前登录用户的信息 */
				BaseEmployee employee = SecurityUtil.getCurrentRealUser();
				String investigator = "";
				if (employee != null) {
					investigator = employee.getName();
				}
				retailPriceSurvey = new RetailPriceSurvey();
				retailPriceSurvey.setIsTemp("1");
				/* 调查人为当前登录用户 */
				retailPriceSurvey.setInvestigator(investigator);
				retailPriceSurvey = retailPriceSurveyController.doSaveRetailPriceSurvey(retailPriceSurvey);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goSaveOrUpdateRetailPriceSurveyDetail() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				retailPriceSurveyDetail = retailPriceSurveyController.doListRetailPriceSurveyDetailById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateRetailPriceSurveyDetail";
	}

	/**
	 * 保存零售价格
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (retailPriceSurvey != null && StringUtils.isNotEmpty(retailPriceSurvey.getId()) && !"0".equals(retailPriceSurvey.getId())) {
				isSave = false;
			}
			retailPriceSurvey.setIsTemp("");
			retailPriceSurvey = retailPriceSurveyController.doSaveRetailPriceSurvey(retailPriceSurvey);
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
	public String saveOrUpdateRetailPriceSurveyDetail() {
		boolean isSave = true;
		try {
			if (null != retailPriceSurveyDetail.getId() && !"".equals(retailPriceSurveyDetail.getId())) {
				isSave = false;
			}
			retailPriceSurvey = retailPriceSurveyController.doListRetailPriceSurveyById(id);
			retailPriceSurveyDetail.setRetailPriceSurvey(retailPriceSurvey);
			retailPriceSurveyDetail = retailPriceSurveyController.doSaveRetailPriceSurveyDetail(retailPriceSurveyDetail);
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
			RetailPriceSurvey retailPriceSurvey = retailPriceSurveyController.doListRetailPriceSurveyById(id);
			if (null != retailPriceSurvey) {
				retailPriceSurveyController.doDeleteByEntity(retailPriceSurvey);
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
	public String deleteRetailPriceSurveyDetailById() {
		try {
			RetailPriceSurveyDetail retailPriceSurveyDetail = retailPriceSurveyController.doListRetailPriceSurveyDetailById(id);
			if (null != retailPriceSurveyDetail) {
				retailPriceSurveyController.doDeleteRetailPriceSurveyDetailByEntity(retailPriceSurveyDetail);
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

	public void getRetailPriceSurveyDetailJson() {
		try {

			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				RetailPriceSurvey retailPriceSurvey = retailPriceSurveyController.doListRetailPriceSurveyById(id);
				if (retailPriceSurvey != null) {
					json = convertListToJson(new ArrayList<RetailPriceSurveyDetail>(retailPriceSurvey.getRetailPriceSurveyDetails()), retailPriceSurvey.getRetailPriceSurveyDetails().size());
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

	public RetailPriceSurvey getRetailPriceSurvey() {
		return retailPriceSurvey;
	}

	public void setRetailPriceSurvey(RetailPriceSurvey retailPriceSurvey) {
		this.retailPriceSurvey = retailPriceSurvey;
	}

	public List<RetailPriceSurvey> getRetailPriceSurveyList() {
		return retailPriceSurveyList;
	}

	public void setRetailPriceSurveyList(List<RetailPriceSurvey> retailPriceSurveyList) {
		this.retailPriceSurveyList = retailPriceSurveyList;
	}

	public RetailPriceSurveyDetail getRetailPriceSurveyDetail() {
		return retailPriceSurveyDetail;
	}

	public void setRetailPriceSurveyDetail(RetailPriceSurveyDetail retailPriceSurveyDetail) {
		this.retailPriceSurveyDetail = retailPriceSurveyDetail;
	}

}
