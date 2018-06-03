package com.vix.nvix.drp.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.ebusiness.entity.Order;
import com.vix.ebusiness.entity.OrderDetail;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 
 * @类全名 com.vix.nvix.drp.action.VixntOrderAction
 *
 * @author zhanghaibing
 *
 * @date 2016年9月21日
 */
@Controller
@Scope("prototype")
public class VixntOrderAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private Order order;

	@Override
	public String goList() {
		return GO_LIST;
	}

	public String goSupplierList() {
		return "goSupplierList";
	}

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (StringUtils.isEmpty(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			params.put("dealState," + SearchCondition.EQUAL, 1);
			pager = vixntBaseService.findPagerByHqlConditions(pager, Order.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goOrderDetailList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("order.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, OrderDetail.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				order = vixntBaseService.findEntityById(Order.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}