package com.vix.ebusiness.order.pickingOut.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.order.pickingOut.controller.PickingOutController;

@Controller
@Scope("prototype")
public class PickingOutAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PickingOutAction.class);
	@Autowired
	private PickingOutController pickingOutController;
	private String id;
	private String pageNo;
	/* 销售订单 */
	private Order order;
	private List<Order> orderList;
	private String orderBatchId;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			orderList = pickingOutController.doListOrderList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public String goWeighingList() {
		return "goWeighingList";
	}

	/**
	 * 获取待配货数据
	 * 
	 * @return
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(orderBatchId) && !"0".equals(orderBatchId)) {
				params.put("orderBatch.id," + SearchCondition.EQUAL, orderBatchId);
			}
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			params.put("dealState," + SearchCondition.NOEQUAL, 3);
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager = pickingOutController.doListOrder(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 待称重列表
	 * 
	 * @return
	 */
	public String goWeighingListContent() {
		try {
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(orderBatchId) && !"0".equals(orderBatchId)) {
				params.put("orderBatch.id," + SearchCondition.EQUAL, orderBatchId);
			}
			params.put("dealState," + SearchCondition.EQUAL, 3);
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = pickingOutController.doListOrder(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goWeighingListContent";
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				order = pickingOutController.doListOrderById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderBatchId() {
		return orderBatchId;
	}

	public void setOrderBatchId(String orderBatchId) {
		this.orderBatchId = orderBatchId;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

}
