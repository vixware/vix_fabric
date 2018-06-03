package com.vix.sales.refund.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.share.entity.CurrencyType;
import com.vix.sales.refund.entity.ReturnRule;
import com.vix.sales.refund.entity.ReturnRuleItem;

@Controller
@Scope("prototype")
public class ReturnRuleItemAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String id;
	private ReturnRuleItem returnRuleItem;
	private String pageNo;

	/** 跳转至用户修改页面 */
	private List<CurrencyType> currencyTypeList;

	public String goSaveOrUpdate() {
		try {
			currencyTypeList = baseHibernateService.findAllByEntityClass(CurrencyType.class);
			if (null != id && !"".equals(id)) {
				returnRuleItem = baseHibernateService.findEntityById(ReturnRuleItem.class, id);
				String pageNo = getRequestParameter("pageNo");
				getRequest().setAttribute("pageNo", pageNo);
			} else {
				returnRuleItem = new ReturnRuleItem();
				returnRuleItem.setIsTemp("1");
				returnRuleItem.setCreateTime(new Date());
				loadCommonData(returnRuleItem);
				returnRuleItem = baseHibernateService.merge(returnRuleItem);
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
			if (null != returnRuleItem.getId()) {
				isSave = false;
			} else {
				returnRuleItem.setCreateTime(new Date());
				loadCommonData(returnRuleItem);
			}
			if (null == returnRuleItem.getCurrencyType() || null != returnRuleItem.getCurrencyType().getId() || !returnRuleItem.getCurrencyType().getId().equals("") || !returnRuleItem.getCurrencyType().getId().equals("0")) {
				returnRuleItem.setCurrencyType(null);
			}
			returnRuleItem.setIsTemp("0");
			baseHibernateService.merge(returnRuleItem);
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
			ReturnRuleItem pb = baseHibernateService.findEntityById(ReturnRuleItem.class, id);
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

	public void getReturnRuleItemJson() {
		try {
			String json = "";
			if (null != id && !"".equals(id)) {
				ReturnRule rr = baseHibernateService.findEntityById(ReturnRule.class, id);
				if (null != rr) {
					json = convertListToJson(new ArrayList<ReturnRuleItem>(rr.getReturnRuleItems()), rr.getReturnRuleItems().size(), "returnRule");
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

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public ReturnRuleItem getReturnRuleItem() {
		return returnRuleItem;
	}

	public void setReturnRuleItem(ReturnRuleItem returnRuleItem) {
		this.returnRuleItem = returnRuleItem;
	}
}