package com.vix.crm.business.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.crm.business.controller.FollowPaymentController;
import com.vix.crm.business.entity.MessageLog;
import com.vix.crm.business.entity.MyAffairs;
import com.vix.sales.order.entity.SalesOrder;

@Controller
@Scope("prototype")
public class FollowPaymentAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private FollowPaymentController followPaymentController;

	private String pageNo;
	/**
	 * 订单ID
	 */
	private String salesOrderId;
	/**
	 * 短信催付内容
	 */
	private MessageLog messageLog;
	/**
	 * 采购订单
	 */
	private SalesOrder salesOrder;
	/**
	 * 我的事务
	 */
	private MyAffairs myAffairs;

	@Override
	public String goList() {
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager = followPaymentController.doListSalesOrder(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 短信催付
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(salesOrderId)) {
				salesOrder = followPaymentController.doListSalesOrderById(salesOrderId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 事务跟进
	 * 
	 * @return
	 */
	public String goSaveOrUpdateFollowing() {
		return "goSaveOrUpdateFollowing";
	}

	/**
	 * 保存催付短信信息
	 * 
	 * @return
	 */
	public String saveOrUpdateMessage() {
		boolean isSave = true;
		try {
			if (null != messageLog.getId() && !"".equals(messageLog.getId())) {
				isSave = false;
			}
			if (StringUtils.isNotEmpty(salesOrderId)) {
				salesOrder = followPaymentController.doListSalesOrderById(salesOrderId);
				if (salesOrder != null) {
					messageLog.setOrderCode(salesOrder.getOuterId());
					messageLog.setBuyerNick(salesOrder.getBuyerNick());
				}
			}
			initEntityBaseController.initEntityBaseAttribute(messageLog);
			messageLog = followPaymentController.doSaveMessageLog(messageLog);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	/**
	 * 保存事务
	 * 
	 * @return
	 */
	public String saveOrUpdateTransactions() {
		boolean isSave = true;
		try {
			if (null != myAffairs.getId() && !"".equals(myAffairs.getId())) {
				isSave = false;
			}
			if (StringUtils.isNotEmpty(salesOrderId)) {
				salesOrder = followPaymentController.doListSalesOrderById(salesOrderId);
				if (salesOrder != null) {
					myAffairs.setSalesOrder(salesOrder);
				}
			}
			initEntityBaseController.initEntityBaseAttribute(myAffairs);
			myAffairs = followPaymentController.doSaveMyAffairs(myAffairs);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	public MessageLog getMessageLog() {
		return messageLog;
	}

	public void setMessageLog(MessageLog messageLog) {
		this.messageLog = messageLog;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public MyAffairs getMyAffairs() {
		return myAffairs;
	}

	public void setMyAffairs(MyAffairs myAffairs) {
		this.myAffairs = myAffairs;
	}

}
