package com.vix.drp.rebatesManagement.action;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.drp.collectManagement.entity.CollectBill;
import com.vix.drp.rebatesManagement.controller.RebatesManagementController;
import com.vix.drp.rebatesManagement.entity.ReturnBill;
import com.vix.drp.refundRule.entity.RefundRule;
import com.vix.drp.refundRule.entity.RefundRuleDetail;

/**
 * 返款单管理
 * 
 * @author zhanghaibing
 * 
 * @date 2013-11-5
 */
@Controller
@Scope("prototype")
public class RebatesManagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private String ids;
	private String parentId;
	private String pageNo;
	private String treeType;
	private String type;
	/** 经销商返款 */
	private ReturnBill returnBill;

	private List<ReturnBill> returnBillList;
	@Autowired
	private RebatesManagementController rebatesManagementController;
	private List<CurrencyType> currencyTypeList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			returnBillList = rebatesManagementController.doListReturnBillList(params);
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
				params.put("rbTitle," + SearchCondition.ANYLIKE, name.trim());
			}
			String code = getDecodeRequestParameter("code");
			if (code != null && !"".equals(code)) {
				params.put("rbCode," + SearchCondition.EQUAL, code.trim());
			}
			String selectname = getDecodeRequestParameter("selectname");
			if (selectname != null && !"".equals(selectname)) {
				params.put("rbTitle," + SearchCondition.ANYLIKE, selectname.trim());
			}
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			pager = rebatesManagementController.doListRebatesManagement(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			currencyTypeList = rebatesManagementController.doListCurrencyTypeList(params);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				returnBill = rebatesManagementController.doListReturnBillById(id);
			} else {
				returnBill = new ReturnBill();
				returnBill.setRbCode(VixUUID.createCode(12));
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
			if (null != returnBill.getId() && !"".equals(returnBill.getId())) {
				isSave = false;
			}
			String py = ChnToPinYin.getPYString(returnBill.getRbTitle());
			returnBill.setChineseCharacter(py);
			//处理修改留痕
			billMarkProcessController.processMark(returnBill, updateField);
			rebatesManagementController.doSaveStoreInfomation(returnBill);
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
			ReturnBill cd = rebatesManagementController.doListReturnBillById(id);
			if (null != cd) {
				rebatesManagementController.doDeleteByEntity(cd);
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
				rebatesManagementController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	// 跳转到选择规则页面
	public String goRefundRule() {
		return "goRefundRule";
	}

	public String goRefundRuleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = rebatesManagementController.doListRefundRule(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goRefundRuleList";
	}

	public void returnPayMentAmount() {
		Double returnValue = 0D;
		String refundRuleId = getRequestParameter("refundRuleId");
		String customerAccountId = getRequestParameter("customerAccountId");
		Double sumpayment = 0D;
		Double sumreceivable = 0D;
		List<CollectBill> collectBillList = null;
		if (customerAccountId != null) {
			/* 根据客户查询回款记录信息 */
			try {
				collectBillList = rebatesManagementController.doListCollectBillById(customerAccountId);
				if (collectBillList != null && collectBillList.size() > 0) {
					for (CollectBill collectBill : collectBillList) {
						if (collectBill != null && collectBill.getPayment() != null) {
							sumpayment += collectBill.getPayment();
							sumreceivable += collectBill.getReceivable();
						}
					}
				}
			} catch (Exception e) {
			}
		}
		if (refundRuleId != null) {
			/* 查询规则 */
			RefundRule refundRule;
			try {
				refundRule = rebatesManagementController.doListRefundRuleById(refundRuleId);
				if (refundRule != null && refundRule.getRefundRuleDetails() != null && refundRule.getRefundRuleDetails().size() > 0) {
					for (RefundRuleDetail refundRuleDetail : refundRule.getRefundRuleDetails()) {
						if (sumpayment > refundRuleDetail.getFieldFrom() && sumpayment < refundRuleDetail.getFieldTo()) {
							if ("1".equals(refundRule.getComputeStyle())) {
								returnValue = refundRuleDetail.getRatio() * sumreceivable;
							} else
								returnValue = refundRuleDetail.getRatio() * sumpayment;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		DecimalFormat df = new DecimalFormat(".##");
		String st = df.format(returnValue);
		renderJson(st);
	}

	public String goSearch() {
		return "goSearch";
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public ReturnBill getReturnBill() {
		return returnBill;
	}

	public void setReturnBill(ReturnBill returnBill) {
		this.returnBill = returnBill;
	}

	/**
	 * @return the currencyTypeList
	 */
	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	/**
	 * @param currencyTypeList
	 *            the currencyTypeList to set
	 */
	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public List<ReturnBill> getReturnBillList() {
		return returnBillList;
	}

	public void setReturnBillList(List<ReturnBill> returnBillList) {
		this.returnBillList = returnBillList;
	}
}
