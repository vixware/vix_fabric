package com.vix.crm.business.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.process.vreport.service.FlowTaskService;
import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.controller.ChangePostAndPriceController;
import com.vix.sales.order.entity.SalesOrder;

@Controller
@Scope("prototype")
public class ChangePostAndPriceAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private ChangePostAndPriceController changePostAndPriceController;
	@Autowired
	private FlowTaskService flowTaskService;
	private String id;
	private String pageNo;
	/**
	 * 订单
	 */
	private SalesOrder salesOrder;
	/**
	 * 邮费
	 */
	private Double postFee;

	@Override
	public String goList() {
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");

			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			/*params.put("dealState," + SearchCondition.EQUAL, 1);*/

			pager = changePostAndPriceController.doListSalesOrderPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				salesOrder = changePostAndPriceController.doListSalesOrderById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String updatePrice() {
		boolean isSave = true;
		//更改邮费价格
		try {
			if (null != salesOrder.getId() && !"".equals(salesOrder.getId())) {
				isSave = false;
			}
			if (null != salesOrder.getId() && !"".equals(salesOrder.getId())) {
				//更改本地订单邮费信息
				salesOrder = changePostAndPriceController.doListSalesOrderById(salesOrder.getId());
				salesOrder.setPostFee(String.valueOf(postFee));
				salesOrder = changePostAndPriceController.doSaveSalesOrder(salesOrder);
			}
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getPostFee() {
		return postFee;
	}

	public void setPostFee(Double postFee) {
		this.postFee = postFee;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

}
