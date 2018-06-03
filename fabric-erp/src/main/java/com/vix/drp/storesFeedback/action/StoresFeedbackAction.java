package com.vix.drp.storesFeedback.action;

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
import com.vix.drp.policyfeedback.entity.PolicyInformation;
import com.vix.drp.storesFeedback.controller.StoresFeedbackController;

@Controller
@Scope("prototype")
public class StoresFeedbackAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private StoresFeedbackController storesFeedbackController;
	private String id;
	private String ids;
	private PolicyInformation policyInformation;
	private List<PolicyInformation> policyInformationList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
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
			policyInformationList = storesFeedbackController.doListPolicyInformationList(params);
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
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
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
			this.addAdvFilterAndSort(params, pager);
			pager = storesFeedbackController.findPolicyInformationPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				policyInformation = storesFeedbackController.doListPolicyInformationById(id);
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
			if (null != policyInformation.getId() && !"".equals(policyInformation.getId())) {
				isSave = false;
			}
			PolicyInformation p = storesFeedbackController.doListPolicyInformationById(policyInformation.getId());
			p.setPolicyFeedbackContent(policyInformation.getPolicyFeedbackContent());
			policyInformation = storesFeedbackController.doSavePolicyInformation(p);
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

	public String goSearch() {
		return "goSearch";
	}

	//action
	/** 处理单条删除操作 */
	public String deleteById() {
		try {
			PolicyInformation policyInformation = storesFeedbackController.doListPolicyInformationById(id);
			if (null != policyInformation) {
				storesFeedbackController.doDeleteByEntity(policyInformation);
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
				storesFeedbackController.doDeleteByIds(delIds);
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

	public PolicyInformation getPolicyInformation() {
		return policyInformation;
	}

	public void setPolicyInformation(PolicyInformation policyInformation) {
		this.policyInformation = policyInformation;
	}

	public List<PolicyInformation> getPolicyInformationList() {
		return policyInformationList;
	}

	public void setPolicyInformationList(List<PolicyInformation> policyInformationList) {
		this.policyInformationList = policyInformationList;
	}

}
