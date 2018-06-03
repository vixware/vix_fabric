package com.vix.inventory.itemBatch.action;

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
import com.vix.inventory.itemBatch.controller.ItemBatchController;
import com.vix.mdm.item.entity.Item;

@Controller
@Scope("prototype")
public class ItemBatchAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ItemBatchAction.class);
	@Autowired
	private ItemBatchController itemBatchController;
	private String id;
	private String ids;
	private String pageNo;
	/**
	 * 物料
	 */
	private Item item;
	private List<Item> itemList;

	@Override
	public String goList() {
		try {
			itemList = itemBatchController.doListItemIndex();
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
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
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
			Pager pager = itemBatchController.doListItem(params, getPager());
			setPager(pager);
			logger.info("获取服务订单列表数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(item.getId()) && !"0".equals(item.getId())) {
				isSave = false;
			}
			Item i = itemBatchController.doListItemById(item.getId());
			i.setBatchCode(item.getBatchCode());
			item = itemBatchController.doSaveItem(i);
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

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				item = itemBatchController.doListItemById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

}
