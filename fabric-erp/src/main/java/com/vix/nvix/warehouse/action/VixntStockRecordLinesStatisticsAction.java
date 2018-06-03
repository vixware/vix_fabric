package com.vix.nvix.warehouse.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.inventory.inbound.entity.StockRecordLines;
import com.vix.inventory.inbound.entity.StockRecords;
import com.vix.nvix.common.base.action.VixntBaseAction;

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
public class VixntStockRecordLinesStatisticsAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private String shopId;

	public String goAllShopList() {
		return "goAllShopList";
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
			String itemCode = getRequestParameter("itemCode");
			if (StringUtils.isNotEmpty(itemCode)) {
				params.put("itemcode," + SearchCondition.ANYLIKE, itemCode);
			}
			String itemName = getDecodeRequestParameter("itemName");
			if (StringUtils.isNotEmpty(itemName)) {
				params.put("itemname," + SearchCondition.ANYLIKE, itemName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, StockRecordLines.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goAllStockRecordLinesList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			Map<String, Object> p = getParams();
			if (StringUtils.isNotEmpty(shopId)) {
				p.put("channelDistributor.id," + SearchCondition.EQUAL, shopId);
				String stockRecordsIds = "";
				List<StockRecords> stockRecordsList = vixntBaseService.findAllByConditions(StockRecords.class, p);
				if (stockRecordsList != null && stockRecordsList.size() > 0) {
					for (StockRecords stockRecords : stockRecordsList) {
						stockRecordsIds += "," + stockRecords.getId();
					}
					if (StringUtils.isNotEmpty(stockRecordsIds)) {
						params.put("stockRecordsId", stockRecordsIds);
					}
					pager = vixntBaseService.findStockRecordLinesPager(pager, params);
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
}