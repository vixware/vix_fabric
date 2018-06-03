package com.vix.nvix.warehouse.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrder;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 
 * @类全名 com.vix.nvix.warehouse.action.VixntLogisticsAction
 *
 * @author zhanghaibing
 *
 * @date 2016年10月28日
 */
@Controller
@Scope("prototype")
public class VixntLogisticsAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String selectname = getDecodeRequestParameter("selectname");
			if (StringUtils.isNotEmpty(selectname)) {
				params.put("name," + SearchCondition.ANYLIKE, selectname);
			}
			params.put("vehicle.id," + SearchCondition.ISNOT, null);
			params.put("vehicle.id," + SearchCondition.NOEQUAL, "");
			params.put("type," + SearchCondition.EQUAL, "2");
			pager = vixntBaseService.findPagerByHqlConditions(pager, RequireGoodsOrder.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}