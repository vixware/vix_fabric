package com.vix.sales.report.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.ebusiness.entity.OrderDetail;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 
 * @类全名 com.vix.sales.report.action.SalesReportAction
 *
 * @author zhanghaibing
 *
 * @date 2016年8月20日
 */
@Controller
@Scope("prototype")
public class SalesReportAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			pager = vixntBaseService.findPagerByHqlConditions(pager, OrderDetail.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
}
