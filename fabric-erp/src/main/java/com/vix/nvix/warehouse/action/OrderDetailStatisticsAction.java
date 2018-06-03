package com.vix.nvix.warehouse.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.warehouse.entity.OrderDetailStatistics;
import com.vix.nvix.warehouse.vo.OrderDetailStatisticsBo;

/**
 * 
 * @类全名 com.vix.nvix.warehouse.action.VixntStockRecordLinesStatisticsAction
 *
 * @author zhanghaibing
 *
 * @date 2016年9月8日
 */
@Controller
@Scope("prototype")
public class OrderDetailStatisticsAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	@Override
	public String goList() {
		Map<String, Object> p = new HashMap<String, Object>();
		dealInAndOutStatistics(p);
		return GO_LIST;
	}

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = new Pager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String searchName = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("title," + SearchCondition.ANYLIKE, searchName.trim());
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, OrderDetailStatistics.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dealInAndOutStatistics(Map<String, Object> p) {
		try {
			List<OrderDetailStatisticsBo> orderDetailStatisticsBoList = vixntBaseService.getOrderDetailStatisticsBoList(p);
			if (orderDetailStatisticsBoList != null && orderDetailStatisticsBoList.size() > 0) {
				for (OrderDetailStatisticsBo orderDetailStatisticsBo : orderDetailStatisticsBoList) {
					if (orderDetailStatisticsBo != null) {
						OrderDetailStatistics orderDetailStatistics = vixntBaseService.findEntityByAttribute(OrderDetailStatistics.class, "outerGoodsId", orderDetailStatisticsBo.getOuterGoodsId());
						if (orderDetailStatistics == null) {
							orderDetailStatistics = new OrderDetailStatistics();
							orderDetailStatistics.setOuterGoodsId(orderDetailStatisticsBo.getOuterGoodsId());
							orderDetailStatistics.setTitle(orderDetailStatisticsBo.getTitle());
							orderDetailStatistics.setPrice(orderDetailStatisticsBo.getPrice());
							orderDetailStatistics.setNum(orderDetailStatisticsBo.getNum());
							orderDetailStatistics.setCreateTime(orderDetailStatisticsBo.getCreateTime());
							orderDetailStatistics.setPayment(orderDetailStatisticsBo.getPayment());
							initEntityBaseController.initEntityBaseAttribute(orderDetailStatistics);
							orderDetailStatistics = vixntBaseService.merge(orderDetailStatistics);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
