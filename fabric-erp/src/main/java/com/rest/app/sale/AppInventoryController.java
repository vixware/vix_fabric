package com.rest.app.sale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rest.core.base.BaseRestController;
import com.vix.core.web.Pager;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.traceability.service.ITraceabilityService;

/**
 * 库存量接口
 * 
 * @ClassFullName com.rest.app.sale.AppInventoryController
 *
 * @author bjitzhang
 *
 * @date 2016年4月14日
 *
 */

@Controller
@RequestMapping(value = "restService/app/inventoryCurrentStock")
public class AppInventoryController extends BaseRestController {

	@Resource(name = "traceabilityService")
	private ITraceabilityService traceabilityService;

	/**
	 * 获取库存列表
	 * 
	 * @param request
	 * @param response
	 * @param param
	 * @throws Exception
	 */
	@RequestMapping(value = "findInventoryCurrentStockList.rs", method = RequestMethod.POST)
	public void findInventoryCurrentStockList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		List<InventoryCurrentStock> inventoryCurrentStockList = traceabilityService.findAllDataByConditions(InventoryCurrentStock.class, params);
		List<InventoryCurrentStock> tList = new ArrayList<InventoryCurrentStock>();
		for (InventoryCurrentStock inventoryCurrentStock : inventoryCurrentStockList) {
			InventoryCurrentStock t = new InventoryCurrentStock();
			BeanUtils.copyProperties(inventoryCurrentStock, t, new String[] { "inventoryCurrentStock", "groupInventoryCurrentStock", "invWarehouse", "invMainBatch", "invShelf", "item", "masterInventoryCurrentStock", "inventoryCurrentStockList", "channelDistributor", "measureUnit" });
			tList.add(t);
		}
		renderResult(response, tList);
	}

	/**
	 * 获取库存分页列表
	 * 
	 * @param request
	 * @param response
	 * @param pager
	 * @param inventoryCurrentStock
	 * @throws Exception
	 */
	@RequestMapping(value = "findInventoryCurrentStockPager.rs", method = RequestMethod.POST)
	public void findInventoryCurrentStockPager(HttpServletRequest request, HttpServletResponse response, Pager pager, InventoryCurrentStock inventoryCurrentStock) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		Pager pagerRes = traceabilityService.findPagerByHqlConditions(pager, InventoryCurrentStock.class, params);
		renderResultPager(response, pagerRes);
	}
}
