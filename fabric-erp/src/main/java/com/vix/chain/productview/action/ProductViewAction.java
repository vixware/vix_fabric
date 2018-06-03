package com.vix.chain.productview.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.chain.productview.controller.ProductViewController;
import com.vix.chain.productview.entity.SettlementStatement;
import com.vix.chain.productview.entity.SettlementStatementItem;
import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

@Controller
@Scope("prototype")
public class ProductViewAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ProductViewController productViewController;
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

	private List<CurrencyType> currencyTypeList;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = productViewController.findSettlementStatementPager(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = getParams();
			currencyTypeList = productViewController.doListCurrencyTypeList(params);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				settlementStatement = productViewController.doListSettlementStatementById(id);
			} else {
				settlementStatement = new SettlementStatement();
				settlementStatement.setIsTemp("1");
				settlementStatement.setCode(VixUUID.createCode(10));
				settlementStatement = productViewController.doSaveSettlementStatement(settlementStatement);
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
			if (null != settlementStatement.getId() && !"".equals(settlementStatement.getId())) {
				isSave = false;
			}
			settlementStatement.setIsTemp("");
			settlementStatement = productViewController.doSaveSettlementStatement(settlementStatement);
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
			SettlementStatement settlementStatement = productViewController.doListSettlementStatementById(id);
			if (null != settlementStatement) {
				productViewController.doDeleteSettlementStatement(settlementStatement);
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
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				SettlementStatement settlementStatement = productViewController.doListSettlementStatementById(id);
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
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				settlementStatementItem = productViewController.doListSettlementStatementItemById(id);
			} else {
				settlementStatementItem = new SettlementStatementItem();
				settlementStatementItem = productViewController.doSaveSettlementStatementItem(settlementStatementItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateSettlementStatementItem";
	}

	public String saveOrUpdateSettlementStatementItem() {
		boolean isSave = true;
		try {
			if (null != settlementStatementItem.getId() && !"".equals(settlementStatementItem.getId())) {
				isSave = false;
			}
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				SettlementStatement settlementStatement = productViewController.doListSettlementStatementById(id);
				if (null != settlementStatement) {
					settlementStatementItem.setSettlementStatement(settlementStatement);
				}
			}
			settlementStatementItem = productViewController.doSaveSettlementStatementItem(settlementStatementItem);
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
			String salesOrderId = getRequestParameter("salesOrderId");
			SalesOrder salesOrder = productViewController.doListSalesOrderById(salesOrderId);

			if (salesOrder != null && salesOrder.getSaleOrderItems() != null) {
				for (SaleOrderItem saleOrderItem : salesOrder.getSaleOrderItems()) {
					if (saleOrderItem != null) {
						SettlementStatementItem settlementStatementItem = new SettlementStatementItem();
						if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
							SettlementStatement settlementStatement = productViewController.doListSettlementStatementById(id);
							if (null != settlementStatement) {
								settlementStatementItem.setSettlementStatement(settlementStatement);
								settlementStatementItem.setItemCode(saleOrderItem.getItemCode());
								settlementStatementItem.setSpecification(saleOrderItem.getSpecification());
								settlementStatementItem.setAmount(saleOrderItem.getAmount());
								settlementStatementItem.setUnit(saleOrderItem.getUnit());
								settlementStatementItem.setPrice(saleOrderItem.getPrice());
								settlementStatementItem.setNetTotal(saleOrderItem.getNetTotal());
								settlementStatementItem = productViewController.doSaveSettlementStatementItem(settlementStatementItem);
							}
						}
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
			SettlementStatementItem settlementStatementItem = productViewController.doListSettlementStatementItemById(id);
			if (null != settlementStatementItem) {
				productViewController.doDeleteSettlementStatementItem(settlementStatementItem);
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
			Pager pager = productViewController.goSalesOrderList(params, getPager());
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

}
