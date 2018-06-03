package com.vix.sales.refund.action;

import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.sales.refund.entity.ReturnRule;

@Controller
@Scope("prototype")
public class ReturnRuleAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String id;
	private ReturnRule returnRule;
	private String pageNo;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			baseHibernateService.findPagerByHqlConditions(getPager(), ReturnRule.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				returnRule = baseHibernateService.findEntityById(ReturnRule.class, id);
			} else {
				returnRule = new ReturnRule();
				returnRule.setIsTemp("1");
				returnRule.setCreateTime(new Date());
				loadCommonData(returnRule);
				returnRule = baseHibernateService.merge(returnRule);
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
			if (null != returnRule.getId()) {
				isSave = false;
			} else {
				returnRule.setCreateTime(new Date());
				loadCommonData(returnRule);
			}
			if (null == returnRule.getCustomerAccount() || null == returnRule.getCustomerAccount().getId() || !returnRule.getCustomerAccount().getId().equals("") || !returnRule.getCustomerAccount().getId().equals("0")) {
				returnRule.setCustomerAccount(null);
			}
			if (null == returnRule.getItem() || null == returnRule.getItem().getId() || !returnRule.getItem().getId().equals("") || !returnRule.getItem().getId().equals("0")) {
				returnRule.setItem(null);
			}
			returnRule.setIsTemp("0");
			baseHibernateService.merge(returnRule);
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
			ReturnRule pb = baseHibernateService.findEntityById(ReturnRule.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
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

	public ReturnRule getReturnRule() {
		return returnRule;
	}

	public void setReturnRule(ReturnRule returnRule) {
		this.returnRule = returnRule;
	}
}