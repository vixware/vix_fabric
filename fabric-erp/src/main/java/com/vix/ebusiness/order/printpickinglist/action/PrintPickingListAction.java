package com.vix.ebusiness.order.printpickinglist.action;

import java.util.HashMap;
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
import com.vix.ebusiness.entity.OrderDetail;
import com.vix.ebusiness.entity.PickingList;
import com.vix.ebusiness.entity.PickingListDetail;
import com.vix.ebusiness.order.printpickinglist.controller.PrintPickingListController;

@Controller
@Scope("prototype")
public class PrintPickingListAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PrintPickingListAction.class);
	@Autowired
	private PrintPickingListController printPickingListController;
	private String id;
	private String ids;
	private String pageNo;
	/* 销售订单 */
	private Order order;
	private List<Order> orderList;
	private List<OrderDetail> orderDetailList;
	private String orderBatchId;
	private String businussOrderId;
	/**
	 * 拣货单
	 */
	private PickingList pickingList;
	/**
	 * 拣货单明细
	 */
	private List<PickingListDetail> pickingListDetailList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			orderList = printPickingListController.doListOrderList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 根据条件查询入库单信息
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("orderBatch.id," + SearchCondition.EQUAL, orderBatchId);
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager = printPickingListController.doListOrder(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				order = printPickingListController.doListOrderById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 打印
	 * 
	 * @return
	 */
	public String goPrint() {
		try {
			Map<String, Object> params = getParams();
			params.put("id," + SearchCondition.IN, businussOrderId);
			orderList = printPickingListController.doListOrderList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrint";
	}

	/**
	 * 获取拣货单,拣货单明细
	 * 
	 * @return
	 */
	public String goPrintPickingList() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				pickingList = printPickingListController.doListPickingList(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrint";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	public String getBusinussOrderId() {
		return businussOrderId;
	}

	public void setBusinussOrderId(String businussOrderId) {
		this.businussOrderId = businussOrderId;
	}

	public List<OrderDetail> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public PickingList getPickingList() {
		return pickingList;
	}

	public void setPickingList(PickingList pickingList) {
		this.pickingList = pickingList;
	}

	public List<PickingListDetail> getPickingListDetailList() {
		return pickingListDetailList;
	}

	public void setPickingListDetailList(List<PickingListDetail> pickingListDetailList) {
		this.pickingListDetailList = pickingListDetailList;
	}

}
