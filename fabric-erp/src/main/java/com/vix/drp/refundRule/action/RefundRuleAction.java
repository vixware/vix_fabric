package com.vix.drp.refundRule.action;

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
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.drp.refundRule.controller.RefundRuleController;
import com.vix.drp.refundRule.entity.RefundRule;
import com.vix.drp.refundRule.entity.RefundRuleDetail;

import flexjson.JSONDeserializer;

@Controller
@Scope("prototype")
public class RefundRuleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private RefundRuleController refundRuleController;

	private String id;
	private String ids;
	private String pageNo;
	private RefundRule refundRule;

	private List<RefundRule> refundRuleList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1"); // status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			refundRuleList = refundRuleController.doListRefundRuleIndex(params);
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
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");

			//处理搜索条件
			String name = getDecodeRequestParameter("name");
			if (name != null && !"".equals(name)) {
				params.put("rrName," + SearchCondition.EQUAL, name.trim());
			} // status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			pager = refundRuleController.doListRefundRule(params, pager);
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
				refundRule = refundRuleController.doListEntityById(id);
			} else {
				refundRule = new RefundRule();
				refundRule.setCode(VixUUID.createCode(10));
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
			if (refundRule != null && StringUtils.isNotEmpty(refundRule.getId()) && !"0".equals(refundRule.getId())) {
				isSave = false;
			}
			String refundRuleDetailJson = getRequestParameter("refundRuleDetailJson");
			List<RefundRuleDetail> refundRuleDetailList = null;
			if (refundRuleDetailJson != null && !"".equals(refundRuleDetailJson)) {
				refundRuleDetailList = new JSONDeserializer<List<RefundRuleDetail>>().use("values", RefundRuleDetail.class).deserialize(refundRuleDetailJson);
			}
			refundRule.setIsTemp("");

			String py = ChnToPinYin.getPYString(refundRule.getRrName());
			refundRule.setChineseCharacter(py);
			//处理修改留痕
			billMarkProcessController.processMark(refundRule, updateField);
			refundRule = refundRuleController.doSaveOrUpdateRefundRule(refundRule, refundRuleDetailList);
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
			RefundRule cd = refundRuleController.doListEntityById(id);
			if (null != cd) {
				refundRuleController.doDeleteByEntity(cd);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 获取明细的json数据 */
	public void getRefundRuleDetailJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				RefundRule refundRule = refundRuleController.doListEntityById(id);
				if (refundRule != null) {
					json = convertListToJson(new ArrayList<RefundRuleDetail>(refundRule.getRefundRuleDetails()), refundRule.getRefundRuleDetails().size());
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

	// 跳转到选择会员页面
	public String goChooseInventoryCurrentStock() {
		return "goChooseInventoryCurrentStock";
	}

	public String goInventoryCurrentStockList() {
		try {
			Map<String, Object> params = getParams();
			String itemcode = getRequestParameter("searchContent");
			if (itemcode != null) {
				params.put("itemcode," + SearchCondition.EQUAL, itemcode);
			}
			// 按最近使用
			Pager pager = refundRuleController.doListInventoryCurrentStockPager(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goInventoryCurrentStockList";
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
				refundRuleController.doDeleteByIds(delIds);
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

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public RefundRule getRefundRule() {
		return refundRule;
	}

	public void setRefundRule(RefundRule refundRule) {
		this.refundRule = refundRule;
	}

	public List<RefundRule> getRefundRuleList() {
		return refundRuleList;
	}

	public void setRefundRuleList(List<RefundRule> refundRuleList) {
		this.refundRuleList = refundRuleList;
	}
}
