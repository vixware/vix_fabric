package com.vix.nvix.warehouse.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.warehouse.entity.InAndOutStatistics;
import com.vix.nvix.warehouse.vo.InAndOutStatisticsBo;

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
public class VixntInAndOutStatisticsAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	@Override
	public String goList() {
		Map<String, Object> p = getParams();
		dealInAndOutStatistics(p);
		return GO_LIST;
	}

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String searchName = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("itemname," + SearchCondition.ANYLIKE, searchName.trim());
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, InAndOutStatistics.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dealInAndOutStatistics(Map<String, Object> p) {
		try {
			List<InAndOutStatisticsBo> taskStatisticsBoList = vixntBaseService.getInAndOutStatisticsBoList(p);
			if (taskStatisticsBoList != null && taskStatisticsBoList.size() > 0) {
				for (InAndOutStatisticsBo inAndOutStatisticsBo : taskStatisticsBoList) {
					if (inAndOutStatisticsBo != null) {
						InAndOutStatistics inAndOutStatistics = vixntBaseService.findEntityByAttribute(InAndOutStatistics.class, "itemcode", inAndOutStatisticsBo.getItemcode());
						if (inAndOutStatistics == null) {
							inAndOutStatistics = new InAndOutStatistics();
							inAndOutStatistics.setItemcode(inAndOutStatisticsBo.getItemcode());
							inAndOutStatistics.setItemname(inAndOutStatisticsBo.getItemname());
							inAndOutStatistics.setQuantity(inAndOutStatisticsBo.getQuantity());
							inAndOutStatistics.setCreateTime(inAndOutStatisticsBo.getCreateTime());
							initEntityBaseController.initEntityBaseAttribute(inAndOutStatistics);
							inAndOutStatistics = vixntBaseService.merge(inAndOutStatistics);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
