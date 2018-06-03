package com.vix.chain.suppliersView.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.chain.productview.entity.SettlementStatement;
import com.vix.chain.productview.entity.SettlementStatementItem;
import com.vix.chain.suppliersView.controller.SuppliersViewController;
import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

@Controller
@Scope("prototype")
public class SuppliersViewAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private SuppliersViewController suppliersViewController;
	private String id;
	/**
	 * 结算单
	 */
	private SettlementStatement settlementStatement;
	private List<SettlementStatement> settlementStatementList;
	/**
	 * 结算单明细
	 */
	private SettlementStatementItem settlementStatementItem;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = suppliersViewController.findSettlementStatementPager(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {//if (null != id && id.longValue() > 0) {
				settlementStatement = suppliersViewController.doListSettlementStatementById(id);
			} else {
				settlementStatement = new SettlementStatement();
				settlementStatement.setIsTemp("1");
				settlementStatement.setCode(VixUUID.createCode(10));
				settlementStatement = suppliersViewController.doSaveSettlementStatement(settlementStatement);
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
			if (null != settlementStatement.getId()) {
				isSave = false;
			}
			settlementStatement.setIsTemp("");
			settlementStatement = suppliersViewController.doSaveSettlementStatement(settlementStatement);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(SAVE_SUCCESS);
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

	/**
	 * 
	 * @return
	 */
	public String deleteById() {
		try {
			SettlementStatement settlementStatement = suppliersViewController.doListSettlementStatementById(id);
			if (null != settlementStatement) {
				suppliersViewController.doDeleteSettlementStatement(settlementStatement);
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

	public void getSettlementStatementItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {//if (null != id && !"".equals(id)) {
				SettlementStatement settlementStatement = suppliersViewController.doListSettlementStatementById(id);
				if (null != settlementStatement) {
					json = convertListToJson(new ArrayList<SettlementStatementItem>(settlementStatement.getSettlementStatementItems()), settlementStatement.getSettlementStatementItems().size());
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

	public String goSaveOrUpdateSettlementStatementItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {//if (null != id && id.longValue() > 0) {
				settlementStatementItem = suppliersViewController.doListSettlementStatementItemById(id);
			} else {
				settlementStatementItem = new SettlementStatementItem();
				settlementStatementItem = suppliersViewController.doSaveSettlementStatementItem(settlementStatementItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateSettlementStatementItem";
	}

	public String saveOrUpdateSettlementStatementItem() {
		boolean isSave = true;
		try {
			if (null != settlementStatementItem.getId()) {
				isSave = false;
			}
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {//if (null != id && !"".equals(id)) {
				SettlementStatement settlementStatement = suppliersViewController.doListSettlementStatementById(id);
				if (null != settlementStatement) {
					settlementStatementItem.setSettlementStatement(settlementStatement);
				}
			}
			settlementStatementItem = suppliersViewController.doSaveSettlementStatementItem(settlementStatementItem);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(SAVE_SUCCESS);
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

	public String toSettlementStatementItem() {
		boolean isSave = true;
		try {
			String id = getRequestParameter("id");
			String salesOrderId = getRequestParameter("salesOrderId");
			SalesOrder salesOrder = suppliersViewController.doListSalesOrderById(salesOrderId);

			if (salesOrder != null && salesOrder.getSaleOrderItems() != null) {
				for (SaleOrderItem saleOrderItem : salesOrder.getSaleOrderItems()) {
					if (saleOrderItem != null) {
						SettlementStatementItem settlementStatementItem = new SettlementStatementItem();
						if (StringUtils.isNotEmpty(id) && !id.equals("0")) {//if (null != id && !"".equals(id)) {
							SettlementStatement settlementStatement = suppliersViewController.doListSettlementStatementById(id);
							if (null != settlementStatement) {
								settlementStatementItem.setSettlementStatement(settlementStatement);
							}
						}
						settlementStatementItem.setItemCode(saleOrderItem.getItemCode());
						settlementStatementItem.setSpecification(saleOrderItem.getSpecification());
						settlementStatementItem.setAmount(saleOrderItem.getAmount());
						settlementStatementItem.setUnit(saleOrderItem.getUnit());
						settlementStatementItem.setPrice(saleOrderItem.getPrice());
						settlementStatementItem.setNetTotal(saleOrderItem.getNetTotal());
						settlementStatementItem = suppliersViewController.doSaveSettlementStatementItem(settlementStatementItem);
					}
				}
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(SAVE_SUCCESS);
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

	public String deleteSettlementStatementById() {
		try {
			SettlementStatementItem settlementStatementItem = suppliersViewController.doListSettlementStatementItemById(id);
			if (null != settlementStatementItem) {
				suppliersViewController.doDeleteSettlementStatementItem(settlementStatementItem);
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

	/**
	 * 跳转到选择订单页面
	 * 
	 * @return
	 */
	public String goChooseSalesOrder() {
		return "goChooseSalesOrder";
	}

	/**
	 * 订单页面显示
	 * 
	 * @return
	 */
	public String goSalesOrderList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = suppliersViewController.goSalesOrderList(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSalesOrderList";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public SettlementStatement getSettlementStatement() {
		return settlementStatement;
	}

	public void setSettlementStatement(SettlementStatement settlementStatement) {
		this.settlementStatement = settlementStatement;
	}

	public List<SettlementStatement> getSettlementStatementList() {
		return settlementStatementList;
	}

	public void setSettlementStatementList(List<SettlementStatement> settlementStatementList) {
		this.settlementStatementList = settlementStatementList;
	}

	public SettlementStatementItem getSettlementStatementItem() {
		return settlementStatementItem;
	}

	public void setSettlementStatementItem(SettlementStatementItem settlementStatementItem) {
		this.settlementStatementItem = settlementStatementItem;
	}

}
