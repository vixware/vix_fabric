package com.vix.drp.CustomerSurveymanagement.action;

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
import com.vix.drp.CustomerSurveymanagement.controller.CustomerSurveyController;
import com.vix.drp.CustomerSurveymanagement.entity.CustomerSurvey;

@Controller
@Scope("prototype")
public class CustomerSurveyAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private CustomerSurveyController customerSurveyController;
	private String id;
	private String ids;
	private CustomerSurvey customerSurvey;
	private List<CustomerSurvey> customerSurveyList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
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
			customerSurveyList = customerSurveyController.doListCustomerSurveyList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
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
			this.addAdvFilterAndSort(params, pager);
			pager = customerSurveyController.findPagerByHqlConditions(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				customerSurvey = customerSurveyController.doListCustomerSurveyById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != customerSurvey.getId() && !"".equals(customerSurvey.getId())) {
				isSave = false;
			}
			//处理修改留痕
			billMarkProcessController.processMark(customerSurvey, updateField);
			customerSurvey = customerSurveyController.doSaveCustomerSurvey(customerSurvey);
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
			CustomerSurvey customerSurvey = customerSurveyController.doListCustomerSurveyById(id);
			if (null != customerSurvey) {
				customerSurveyController.doDeleteByEntity(customerSurvey);
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
				customerSurveyController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String getId() {
		return id;
	}

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

	public CustomerSurvey getCustomerSurvey() {
		return customerSurvey;
	}

	public void setCustomerSurvey(CustomerSurvey customerSurvey) {
		this.customerSurvey = customerSurvey;
	}

	public List<CustomerSurvey> getCustomerSurveyList() {
		return customerSurveyList;
	}

	public void setCustomerSurveyList(List<CustomerSurvey> customerSurveyList) {
		this.customerSurveyList = customerSurveyList;
	}

}
