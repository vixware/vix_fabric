package com.vix.drp.setRedeemGoods.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.setRedeemGoods.controller.SetRedeemGoodsController;
import com.vix.drp.setRedeemGoods.entity.SetRedeemGoods;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;

@Controller
@Scope("prototype")
public class SetRedeemGoodsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private SetRedeemGoodsController setRedeemGoodsController;

	private String id;
	private String parentId;
	private String pageNo;
	private SetRedeemGoods setRedeemGoods;

	private List<SetRedeemGoods> setRedeemGoodsList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
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
			setRedeemGoodsList = setRedeemGoodsController.doListSetRedeemGoodsList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
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
			pager = setRedeemGoodsController.doListSetRedeemGoods(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				setRedeemGoods = setRedeemGoodsController.doListSetRedeemGoodsById(id);
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
			if (null != setRedeemGoods.getId() && !"".equals(setRedeemGoods.getId())) {
				isSave = false;
			}
			String inventoryCurrentStockId = getRequestParameter("inventoryCurrentStockId");
			InventoryCurrentStock inventoryCurrentStock = setRedeemGoodsController.doListInventoryCurrentStockById(inventoryCurrentStockId);
			if (inventoryCurrentStock != null) {
				setRedeemGoods.setInventoryCurrentStock(inventoryCurrentStock);
			}
			//处理修改留痕
			billMarkProcessController.processMark(setRedeemGoods, updateField);
			setRedeemGoodsController.doSaveSetRedeemGoods(setRedeemGoods);
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

	// 跳转到选择会员页面
	public String goChooseInventoryCurrentStock() {
		return "goChooseInventoryCurrentStock";
	}

	public String goInventoryCurrentStockList() {
		try {
			Map<String, Object> params = getParams();
			// 按最近使用
			Pager pager = setRedeemGoodsController.doListInventoryCurrentStock(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goInventoryCurrentStockList";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public SetRedeemGoods getSetRedeemGoods() {
		return setRedeemGoods;
	}

	public void setSetRedeemGoods(SetRedeemGoods setRedeemGoods) {
		this.setRedeemGoods = setRedeemGoods;
	}

	public List<SetRedeemGoods> getSetRedeemGoodsList() {
		return setRedeemGoodsList;
	}

	public void setSetRedeemGoodsList(List<SetRedeemGoods> setRedeemGoodsList) {
		this.setRedeemGoodsList = setRedeemGoodsList;
	}

}
