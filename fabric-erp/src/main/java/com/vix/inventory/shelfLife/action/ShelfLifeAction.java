package com.vix.inventory.shelfLife.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.inventory.shelfLife.controller.ShelfLifeController;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;

@Controller
@Scope("prototype")
public class ShelfLifeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ShelfLifeAction.class);
	@Autowired
	private ShelfLifeController shelfLifeController;
	private String pageNo;
	/**
	 * 存货档案清单(现存量汇总表)
	 */
	private List<InventoryCurrentStock> inventoryCurrentStockList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			inventoryCurrentStockList = shelfLifeController.doListInventoryCurrentStockList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 
	 * @return
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");

			//处理查询条件
			String searchContent = getRequestParameter("searchContent");
			if (null != searchContent && !"".equals(searchContent)) {
				params.put("itemcode," + SearchCondition.EQUAL, searchContent);
			}
			String goodsCode = getRequestParameter("goodsCode");
			if (null != goodsCode && !"".equals(goodsCode)) {
				params.put("itemcode," + SearchCondition.EQUAL, goodsCode);
			}
			String goodsName = getDecodeRequestParameter("goodsName");
			if (null != goodsName && !"".equals(goodsName)) {
				params.put("itemname," + SearchCondition.ANYLIKE, goodsName);
			}
			//处理查询条件
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			pager = shelfLifeController.doListInventoryCurrentStock(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSearch() {
		return "goSearch";
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public List<InventoryCurrentStock> getInventoryCurrentStockList() {
		return inventoryCurrentStockList;
	}

	public void setInventoryCurrentStockList(List<InventoryCurrentStock> inventoryCurrentStockList) {
		this.inventoryCurrentStockList = inventoryCurrentStockList;
	}

}
