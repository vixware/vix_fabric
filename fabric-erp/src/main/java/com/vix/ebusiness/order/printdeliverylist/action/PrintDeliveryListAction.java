package com.vix.ebusiness.order.printdeliverylist.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.order.printdeliverylist.controller.PrintDeliveryListController;

@Controller
@Scope("prototype")
public class PrintDeliveryListAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private PrintDeliveryListController printDeliveryListController;
	private String pageNo;
	private List<Order> orderList;
	private String orderBatchId;
	private String id;
	private String ids;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			orderList = printDeliveryListController.doListOrderList(params);
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
			pager = printDeliveryListController.doListOrderPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	//action
	/** 处理单条删除操作 */
	public String deleteById() {
		try {
			Order order = printDeliveryListController.doListOrderById(id);
			if (null != order) {
				printDeliveryListController.doDeleteByEntity(order);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
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
				printDeliveryListController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * @return the orderBatchId
	 */
	public String getOrderBatchId() {
		return orderBatchId;
	}

	/**
	 * @param orderBatchId
	 *            the orderBatchId to set
	 */
	public void setOrderBatchId(String orderBatchId) {
		this.orderBatchId = orderBatchId;
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

	/**
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

}
