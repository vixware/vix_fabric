package com.vix.nvix.drp.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrder;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrderItem;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 
 * @类全名 com.vix.nvix.drp.action.VixntRequireGoodsWastageAction
 *
 * @author zhanghaibing
 *
 * @date 2017年2月4日
 */
@Controller
@Scope("prototype")
public class VixntRequireGoodsWastageAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private RequireGoodsOrder requireGoodsOrder;

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (StringUtils.isEmpty(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			params.put("type,"+SearchCondition.EQUAL, "2");
			pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				requireGoodsOrder = vixntBaseService.findEntityById(RequireGoodsOrder.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public void goSaleOrderItemList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 倒序排序
			Pager pager = getPager();
			// 排序
			if (StringUtils.isEmpty(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("requireGoodsOrder.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrderItem.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public RequireGoodsOrder getRequireGoodsOrder() {
		return requireGoodsOrder;
	}

	public void setRequireGoodsOrder(RequireGoodsOrder requireGoodsOrder) {
		this.requireGoodsOrder = requireGoodsOrder;
	}
}
