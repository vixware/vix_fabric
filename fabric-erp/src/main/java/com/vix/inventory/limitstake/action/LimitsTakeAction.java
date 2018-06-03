package com.vix.inventory.limitstake.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.inventory.limitstake.controller.LimitsTakeController;
import com.vix.inventory.limitstake.entity.StockLimitedTaking;
import com.vix.inventory.limitstake.entity.StockLimitedTakingItem;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.service.IItemService;

@Controller
@Scope("prototype")
public class LimitsTakeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(LimitsTakeAction.class);
	@Autowired
	private LimitsTakeController limitsTakeController;
	@Autowired
	private IItemService itemService;
	private String categoryId;
	private String id;
	private String ids;
	private String pageNo;
	private StockLimitedTaking stockLimitedTaking;
	private List<StockLimitedTaking> stockLimitedTakingList;

	private StockLimitedTakingItem stockLimitedTakingItem;
	private List<StockLimitedTakingItem> stockLimitedTakingItemList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			stockLimitedTakingList = limitsTakeController.doListStockLimitedTakingList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 根据条件查询入库单信息
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// code 编码
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			Pager pager = limitsTakeController.doListStockLimitedTaking(params, getPager());
			setPager(pager);
			logger.info("获取服务订单列表数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockLimitedTaking = limitsTakeController.doListStockLimitedTakingById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(stockLimitedTaking.getId()) && !"0".equals(stockLimitedTaking.getId())) {
				isSave = false;
			}
			stockLimitedTaking = limitsTakeController.doSaveStockLimitedTaking(stockLimitedTaking);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			StockLimitedTaking pb = limitsTakeController.doListStockLimitedTakingById(id);
			if (null != pb) {
				limitsTakeController.doDeleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 获取明细的json数据 */

	public void getStockLimitedTakingItemJson() {
		try {

			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				StockLimitedTaking stockLimitedTaking = limitsTakeController.doListStockLimitedTakingById(id);
				if (stockLimitedTaking != null) {
					json = convertListToJson(new ArrayList<StockLimitedTakingItem>(stockLimitedTaking.getStockLimitedTakingItem()), stockLimitedTaking.getStockLimitedTakingItem().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	public String goListItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockLimitedTakingItem = limitsTakeController.doListStockLimitedTakingItemById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListItem";
	}

	public String goSingleListItem() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("itemClass," + SearchCondition.IN, "goods,finishedgoods");
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("id");
				getPager().setOrderBy("desc");
			}
			getPager().setPageSize(6);
			if (null != categoryId && !"".equals(categoryId)) {
				Pager pager = itemService.findPagerByItemCatalogId(getPager(), categoryId, params);
				setPager(pager);
			} else {
				itemService.findPagerByHqlConditions(getPager(), Item.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSingleListItem";
	}

	/** 批量处理删除操作 */
	public String deleteByIds() {
		try {
			if (StringUtils.isNotEmpty(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				limitsTakeController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotChoose"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String saveOrUpdateWimStockRecordLines() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				stockLimitedTaking = limitsTakeController.doListStockLimitedTakingById(id);
				stockLimitedTakingItem.setStockLimitedTaking(stockLimitedTaking);
				limitsTakeController.doSaveStockLimitedTakingItem(stockLimitedTakingItem);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteWimStockrecordlinesById() {
		try {
			StockLimitedTakingItem stockLimitedTakingItem = limitsTakeController.doListStockLimitedTakingItemById(id);
			if (null != stockLimitedTakingItem) {
				limitsTakeController.deleteWimStockrecordlinesEntity(stockLimitedTakingItem);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	/**
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public StockLimitedTaking getStockLimitedTaking() {
		return stockLimitedTaking;
	}

	public void setStockLimitedTaking(StockLimitedTaking stockLimitedTaking) {
		this.stockLimitedTaking = stockLimitedTaking;
	}

	public List<StockLimitedTaking> getStockLimitedTakingList() {
		return stockLimitedTakingList;
	}

	public void setStockLimitedTakingList(List<StockLimitedTaking> stockLimitedTakingList) {
		this.stockLimitedTakingList = stockLimitedTakingList;
	}

	public StockLimitedTakingItem getStockLimitedTakingItem() {
		return stockLimitedTakingItem;
	}

	public void setStockLimitedTakingItem(StockLimitedTakingItem stockLimitedTakingItem) {
		this.stockLimitedTakingItem = stockLimitedTakingItem;
	}

	public List<StockLimitedTakingItem> getStockLimitedTakingItemList() {
		return stockLimitedTakingItemList;
	}

	public void setStockLimitedTakingItemList(List<StockLimitedTakingItem> stockLimitedTakingItemList) {
		this.stockLimitedTakingItemList = stockLimitedTakingItemList;
	}

}
