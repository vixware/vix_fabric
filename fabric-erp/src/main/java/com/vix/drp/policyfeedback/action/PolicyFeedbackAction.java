package com.vix.drp.policyfeedback.action;

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
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.policyfeedback.controller.PolicyFeedbackController;
import com.vix.drp.policyfeedback.entity.PolicyInformation;

@Controller
@Scope("prototype")
public class PolicyFeedbackAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private PolicyFeedbackController policyFeedbackController;
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
			policyInformationList = policyFeedbackController.doListPolicyInformationList(params);
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
			pager = policyFeedbackController.findPolicyInformationPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				policyInformation = policyFeedbackController.doListPolicyInformationById(id);
			} else {
				policyInformation = new PolicyInformation();
				policyInformation.setIsTemp("1");
				policyInformation = policyFeedbackController.doSavePolicyInformation(policyInformation);
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
			policyInformation.setIsTemp("");

			//处理中文索引
			String py = ChnToPinYin.getPYString(policyInformation.getName());
			policyInformation.setChineseCharacter(py.toUpperCase());
			//处理修改留痕
			billMarkProcessController.processMark(policyInformation, updateField);
			policyInformation = policyFeedbackController.doSavePolicyInformation(policyInformation);
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
			PolicyInformation policyInformation = policyFeedbackController.doListPolicyInformationById(id);
			if (null != policyInformation) {
				policyFeedbackController.doDeletePolicyInformation(policyInformation);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 政策下达
	 * 
	 * @return
	 */
	public String goPoliciesIssued() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				policyInformation = policyFeedbackController.doListPolicyInformationById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPoliciesIssued";
	}

	/**
	 * 政策下达保存 ,即把政策与经销商,门店关联
	 * 
	 * @return
	 */
	public String saveOrUpdatePoliciesIssued() {
		boolean isSave = true;
		try {
			if (null != policyInformation.getId()) {
				isSave = false;
			}
			PolicyInformation pInformation = policyFeedbackController.doListPolicyInformationById(policyInformation.getId());
			String channelDistributorId = getRequestParameter("channelDistributorId");
			ChannelDistributor channelDistributor = policyFeedbackController.doListChannelDistributorById(channelDistributorId);
			if (channelDistributor != null) {
				pInformation.setChannelDistributor(channelDistributor);
			}
			policyFeedbackController.doSavePolicyInformation(pInformation);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				setMessage("政策下达成功");
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
				policyFeedbackController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String goSearch() {
		return "goSearch";
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
