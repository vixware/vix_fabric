package com.vix.nvix.system.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.sales.order.entity.SalesOrder;

/**
 * 退货回收站
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.nvix.system.action.VixntBillRecycleBinAction
 *
 * @date 2018年3月5日
 */
@Controller
@Scope("prototype")
public class VixntBillRecycleBinAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SalesOrder salesOrder;
	public void goSingleList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.EQUAL, "0");
			params.put("isDeleted," + SearchCondition.EQUAL, "1");
			String title = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(title)) {
				params.put("title," + SearchCondition.ANYLIKE, title);
			}
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("billDate");
			}
			vixntBaseService.findPagerByHqlConditions(pager, SalesOrder.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void recovery() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				salesOrder = vixntBaseService.findEntityById(SalesOrder.class, id);
				if (null != salesOrder) {
					salesOrder.setIsDeleted("0");
					salesOrder = vixntBaseService.merge(salesOrder);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 处理删除操作 */
	public void deleteById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				vixntBaseService.deleteById(SalesOrder.class, id);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	/**
	 * @return the salesOrder
	 */
	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	/**
	 * @param salesOrder
	 *            the salesOrder to set
	 */
	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

}
