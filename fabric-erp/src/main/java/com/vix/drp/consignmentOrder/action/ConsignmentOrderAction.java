package com.vix.drp.consignmentOrder.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.consignmentOrder.controller.ConsignmentOrderController;
import com.vix.drp.consignmentOrder.entity.ConsignmentOrder;
import com.vix.drp.consignmentOrder.entity.ConsignmentOrderItem;

@Controller
@Scope("prototype")
public class ConsignmentOrderAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ConsignmentOrderController consignmentOrderController;
	private String id;
	private ConsignmentOrder consignmentOrder;
	private List<ConsignmentOrder> consignmentOrderList;

	private ConsignmentOrderItem consignmentOrderItem;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = consignmentOrderController.findPagerByHqlConditions(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				consignmentOrder = consignmentOrderController.doListConsignmentOrderById(id);
			} else {
				consignmentOrder = new ConsignmentOrder();
				consignmentOrder.setIsTemp("1");
				consignmentOrder = consignmentOrderController.doSaveConsignmentOrder(consignmentOrder);
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
			if (null != consignmentOrder.getId() && !"".equals(consignmentOrder.getId())) {
				isSave = false;
			}
			consignmentOrder.setIsTemp("");
			consignmentOrder = consignmentOrderController.doSaveConsignmentOrder(consignmentOrder);
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

	/**
	 * 
	 * @return
	 */
	public String deleteById() {
		try {
			ConsignmentOrder consignmentOrder = consignmentOrderController.doListConsignmentOrderById(id);
			if (null != consignmentOrder) {
				consignmentOrderController.doDeleteByEntity(consignmentOrder);
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

	public void getConsignmentOrderItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {//if (null != id && !"".equals(id)) {
				ConsignmentOrder consignmentOrder = consignmentOrderController.doListConsignmentOrderById(id);
				if (null != consignmentOrder) {
					json = convertListToJson(new ArrayList<ConsignmentOrderItem>(consignmentOrder.getConsignmentOrderItems()), consignmentOrder.getConsignmentOrderItems().size());
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

	public String goSaveOrUpdateConsignmentOrderItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				consignmentOrderItem = consignmentOrderController.doListConsignmentOrderItemById(id);
			} else {
				consignmentOrderItem = new ConsignmentOrderItem();
				consignmentOrderItem = consignmentOrderController.doSaveConsignmentOrderItem(consignmentOrderItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateConsignmentOrderItem";
	}

	public String saveOrUpdateConsignmentOrderItem() {
		boolean isSave = true;
		try {
			if (null != consignmentOrderItem.getId() && !"".equals(consignmentOrderItem.getId())) {
				isSave = false;
			}
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				ConsignmentOrder consignmentOrder = consignmentOrderController.doListConsignmentOrderById(id);
				if (null != consignmentOrder) {
					consignmentOrderItem.setConsignmentOrder(consignmentOrder);
				}
			}
			if (consignmentOrderItem != null && consignmentOrderItem.getPrice() != null && consignmentOrderItem.getAmount() != null) {
				consignmentOrderItem.setNetTotal(consignmentOrderItem.getPrice() * consignmentOrderItem.getAmount());
			}
			consignmentOrderItem = consignmentOrderController.doSaveConsignmentOrderItem(consignmentOrderItem);
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

	public String deleteConsignmentOrderItemById() {
		try {
			ConsignmentOrderItem consignmentOrderItem = consignmentOrderController.doListConsignmentOrderItemById(id);
			if (null != consignmentOrderItem) {
				consignmentOrderController.doDeleteConsignmentOrderItemByEntity(consignmentOrderItem);
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

	public ConsignmentOrder getConsignmentOrder() {
		return consignmentOrder;
	}

	public void setConsignmentOrder(ConsignmentOrder consignmentOrder) {
		this.consignmentOrder = consignmentOrder;
	}

	public List<ConsignmentOrder> getConsignmentOrderList() {
		return consignmentOrderList;
	}

	public void setConsignmentOrderList(List<ConsignmentOrder> consignmentOrderList) {
		this.consignmentOrderList = consignmentOrderList;
	}

	public ConsignmentOrderItem getConsignmentOrderItem() {
		return consignmentOrderItem;
	}

	public void setConsignmentOrderItem(ConsignmentOrderItem consignmentOrderItem) {
		this.consignmentOrderItem = consignmentOrderItem;
	}

}
