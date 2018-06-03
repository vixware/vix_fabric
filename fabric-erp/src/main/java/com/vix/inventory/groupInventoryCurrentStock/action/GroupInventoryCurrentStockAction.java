package com.vix.inventory.groupInventoryCurrentStock.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.inventory.groupInventoryCurrentStock.controller.GroupInventoryCurrentStockController;
import com.vix.inventory.standingbook.entity.GroupInventoryCurrentStock;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;

/**
 * 虚拟组合商品管理
 * 
 * @author zhanghaibing
 * 
 * @date 2013-12-11
 */
@Controller
@Scope("prototype")
public class GroupInventoryCurrentStockAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(GroupInventoryCurrentStockAction.class);
	@Autowired
	private GroupInventoryCurrentStockController groupInventoryCurrentStockController;
	private String id;
	private String groupInventoryCurrentStockId;
	private String ids;
	private String pageNo;
	/**
	 * 虚拟商品
	 */
	private GroupInventoryCurrentStock groupInventoryCurrentStock;
	private List<GroupInventoryCurrentStock> groupInventoryCurrentStockList;
	private InventoryCurrentStock inventoryCurrentStock;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			groupInventoryCurrentStockList = groupInventoryCurrentStockController.doListGroupInventoryCurrentStockList(params);
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
			Map<String, Object> params = getParams();
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			String tag = getRequestParameter("tag");
			if ("0".equals(tag)) {
				String content = getDecodeRequestParameter("content");
				if (content != null && !"".equals(content)) {
					params.put("itemname," + SearchCondition.ANYLIKE, content);
				}
			} else {
				String itemCode = getRequestParameter("itemCode");
				String itemName = getDecodeRequestParameter("itemName");
				if (itemCode != null && !"".equals(itemCode)) {
					params.put("itemcode," + SearchCondition.EQUAL, itemCode);
				}
				if (itemName != null && !"".equals(itemName)) {
					params.put("itemname," + SearchCondition.ANYLIKE, itemName);
				}
			}
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			pager = groupInventoryCurrentStockController.doListGroupInventoryCurrentStockPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				groupInventoryCurrentStock = groupInventoryCurrentStockController.doListGroupInventoryCurrentStockById(id);
			} else {
				groupInventoryCurrentStock = new GroupInventoryCurrentStock();
				groupInventoryCurrentStock.setIsTemp("1");
				groupInventoryCurrentStock.setItemcode(VixUUID.createCode(10));
				groupInventoryCurrentStock = groupInventoryCurrentStockController.doSaveOrUpdateGroupInventoryCurrentStock(groupInventoryCurrentStock);
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
			if (StringUtils.isNotEmpty(groupInventoryCurrentStock.getId()) && !"0".equals(groupInventoryCurrentStock.getId())) {
				isSave = false;
			}
			groupInventoryCurrentStock.setIsTemp("");
			initEntityBaseController.initEntityBaseAttribute(groupInventoryCurrentStock);
			//处理修改留痕
			billMarkProcessController.processMark(groupInventoryCurrentStock, updateField);
			groupInventoryCurrentStock = groupInventoryCurrentStockController.doSaveOrUpdateGroupInventoryCurrentStock(groupInventoryCurrentStock);
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

	/**
	 * 获取组合商品子项列表
	 */
	public void getInventoryCurrentStockJson() {
		try {
			String json = "";
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				GroupInventoryCurrentStock groupInventoryCurrentStock = groupInventoryCurrentStockController.doListGroupInventoryCurrentStockById(id);
				if (groupInventoryCurrentStock != null) {
					json = convertListToJson(new ArrayList<InventoryCurrentStock>(groupInventoryCurrentStock.getSubInventoryCurrentStocks()), groupInventoryCurrentStock.getSubInventoryCurrentStocks().size());
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goInventoryCurrentStockList() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				inventoryCurrentStock = groupInventoryCurrentStockController.doListInventoryCurrentStockById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goInventoryCurrentStockList";
	}

	public String goInventoryCurrentStockListContent() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = getPager();
			pager.setPageSize(6);
			pager = groupInventoryCurrentStockController.doListInventoryCurrentStockPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goInventoryCurrentStockListContent";
	}

	public String saveOrUpdateInventoryCurrentStock() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(inventoryCurrentStock.getId()) && !"0".equals(inventoryCurrentStock.getId())) {
				isSave = false;
			}
			if (StringUtils.isNotEmpty(groupInventoryCurrentStockId) && !"0".equals(groupInventoryCurrentStockId)) {
				groupInventoryCurrentStock = groupInventoryCurrentStockController.doListGroupInventoryCurrentStockById(groupInventoryCurrentStockId);
				inventoryCurrentStock = groupInventoryCurrentStockController.doListInventoryCurrentStockById(inventoryCurrentStock.getId());
				if (inventoryCurrentStock != null) {
					if (groupInventoryCurrentStock != null) {

						inventoryCurrentStock.setGroupInventoryCurrentStock(groupInventoryCurrentStock);
						groupInventoryCurrentStockController.doSaveOrUpdateInventoryCurrentStock(inventoryCurrentStock);
					}
				}
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			GroupInventoryCurrentStock groupInventoryCurrentStock = groupInventoryCurrentStockController.doListGroupInventoryCurrentStockById(id);
			if (null != groupInventoryCurrentStock) {
				groupInventoryCurrentStockController.doDeleteByEntity(groupInventoryCurrentStock);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
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
				groupInventoryCurrentStockController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String goSearch() {
		return "goSearch";
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
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

	/**
	 * @return the groupInventoryCurrentStockId
	 */
	public String getGroupInventoryCurrentStockId() {
		return groupInventoryCurrentStockId;
	}

	/**
	 * @param groupInventoryCurrentStockId
	 *            the groupInventoryCurrentStockId to set
	 */
	public void setGroupInventoryCurrentStockId(String groupInventoryCurrentStockId) {
		this.groupInventoryCurrentStockId = groupInventoryCurrentStockId;
	}

	public GroupInventoryCurrentStock getGroupInventoryCurrentStock() {
		return groupInventoryCurrentStock;
	}

	public void setGroupInventoryCurrentStock(GroupInventoryCurrentStock groupInventoryCurrentStock) {
		this.groupInventoryCurrentStock = groupInventoryCurrentStock;
	}

	public List<GroupInventoryCurrentStock> getGroupInventoryCurrentStockList() {
		return groupInventoryCurrentStockList;
	}

	public void setGroupInventoryCurrentStockList(List<GroupInventoryCurrentStock> groupInventoryCurrentStockList) {
		this.groupInventoryCurrentStockList = groupInventoryCurrentStockList;
	}

	public InventoryCurrentStock getInventoryCurrentStock() {
		return inventoryCurrentStock;
	}

	public void setInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) {
		this.inventoryCurrentStock = inventoryCurrentStock;
	}

}
