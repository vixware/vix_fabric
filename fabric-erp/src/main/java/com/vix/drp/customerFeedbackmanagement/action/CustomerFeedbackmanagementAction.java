package com.vix.drp.customerFeedbackmanagement.action;

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
import com.vix.drp.customerFeedbackmanagement.controller.CustomerFeedbackController;
import com.vix.drp.customerFeedbackmanagement.entity.CustomerFeedback;

@Controller
@Scope("prototype")
public class CustomerFeedbackmanagementAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private CustomerFeedbackController customerFeedbackController;
	private String id;
	private String ids;
	private CustomerFeedback customerFeedback;
	private List<CustomerFeedback> customerFeedbackList;

	@Override
	public String goList() {
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
			customerFeedbackList = customerFeedbackController.doListCustomerFeedbackList(params);
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
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			// 根据状态查询
			String status = getRequestParameter("status");
			if (StringUtils.isNotEmpty(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (StringUtils.isNotEmpty(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			this.addAdvFilterAndSort(params, pager);
			pager = customerFeedbackController.findPagerByHqlConditions(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				customerFeedback = customerFeedbackController.doListCustomerFeedbackById(id);
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
			if (customerFeedback != null && StringUtils.isNotEmpty(customerFeedback.getId())) {
				isSave = false;
			}
			customerFeedback.setIsTemp("");
			//处理修改留痕
			billMarkProcessController.processMark(customerFeedback, updateField);
			customerFeedback = customerFeedbackController.doSaveCustomerFeedback(customerFeedback);
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
			CustomerFeedback customerFeedback = customerFeedbackController.doListCustomerFeedbackById(id);
			if (null != customerFeedback) {
				customerFeedbackController.doDeleteByEntity(customerFeedback);
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
				customerFeedbackController.doDeleteByIds(delIds);
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

	public CustomerFeedback getCustomerFeedback() {
		return customerFeedback;
	}

	public void setCustomerFeedback(CustomerFeedback customerFeedback) {
		this.customerFeedback = customerFeedback;
	}

	public List<CustomerFeedback> getCustomerFeedbackList() {
		return customerFeedbackList;
	}

	public void setCustomerFeedbackList(List<CustomerFeedback> customerFeedbackList) {
		this.customerFeedbackList = customerFeedbackList;
	}

}
