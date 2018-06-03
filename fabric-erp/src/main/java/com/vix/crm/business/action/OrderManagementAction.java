package com.vix.crm.business.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.controller.OrderManagementController;

@Controller
@Scope("prototype")
public class OrderManagementAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String pageNo;
	@Autowired
	private OrderManagementController orderManagementController;

	@Override
	public String goList() {
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			params.put("dealState," + SearchCondition.EQUAL, 1);
			//处理查询条件
			String orderCode = getRequestParameter("orderCode");
			if (null != orderCode && !"".equals(orderCode)) {
				params.put("outerId," + SearchCondition.EQUAL, orderCode);
			}
			String buyerNick = getDecodeRequestParameter("buyerNick");
			if (null != buyerNick && !"".equals(buyerNick)) {
				params.put("buyerNick," + SearchCondition.ANYLIKE, buyerNick);
			}
			String receiverMobile = getRequestParameter("receiverMobile");
			if (null != receiverMobile && !"".equals(receiverMobile)) {
				params.put("receiverMobile," + SearchCondition.EQUAL, receiverMobile);
			}
			String payTypeCn = getDecodeRequestParameter("payTypeCn");
			if (null != payTypeCn && !"".equals(payTypeCn)) {
				params.put("payTypeCn," + SearchCondition.ANYLIKE, payTypeCn);
			}
			//处理查询条件
			pager = orderManagementController.doListSalesOrderPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

}
