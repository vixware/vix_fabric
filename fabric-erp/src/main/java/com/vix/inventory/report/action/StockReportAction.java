package com.vix.inventory.report.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.inventory.inbound.entity.StockRecordLines;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 出入库统计
 * 
 * @类全名 com.vix.inventory.report.action.StockReportAction
 *
 * @author zhanghaibing
 *
 * @date 2016年8月20日
 */
@Controller
@Scope("prototype")
public class StockReportAction extends VixntBaseAction {

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
			pager = vixntBaseService.findPagerByHqlConditions(pager, StockRecordLines.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
}
