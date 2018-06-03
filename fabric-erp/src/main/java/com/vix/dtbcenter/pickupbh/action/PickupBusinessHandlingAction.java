package com.vix.dtbcenter.pickupbh.action;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.vix.dtbcenter.pickupbh.controller.PickupBusinessHandlingController;
import com.vix.dtbcenter.pickupbh.entity.LoadBill;
import com.vix.dtbcenter.pickupbh.entity.LoadBillItem;
import com.vix.mdm.item.entity.Item;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@Controller
@Scope("prototype")
public class PickupBusinessHandlingAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PickupBusinessHandlingAction.class);
	@Autowired
	private PickupBusinessHandlingController pickupBusinessHandlingController;

	private String id;
	private String ids;
	private String pageNo;

	/**
	 * 提货单
	 */

	private LoadBill loadBill;
	private List<LoadBill> loadBillList;

	private LoadBillItem loadBillItem;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			// code 编码
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
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
			loadBillList = pickupBusinessHandlingController.doListLoadBillList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			// code 编码
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			String name = getDecodeRequestParameter("name");
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			if (null != pageNo && !"".equals(pageNo) && !"undefined".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
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
			Pager pager = pickupBusinessHandlingController.doListTakeDelivery(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				loadBill = pickupBusinessHandlingController.doListLoadBillById(id);
			} else {
				loadBill = new LoadBill();
				loadBill.setIsTemp("1");
				loadBill = pickupBusinessHandlingController.doSaveLoadBill(loadBill, null);
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
			if (null != loadBill.getId()) {
				isSave = false;
			}
			loadBill.setIsTemp("");
			// 货物明细
			String loadBillItemJson = getRequestParameter("loadBillItemJson");
			List<LoadBillItem> loadBillItemList = null;
			if (loadBillItemJson != null && !"".equals(loadBillItemJson)) {
				loadBillItemList = new JSONDeserializer<List<LoadBillItem>>().use("values", LoadBillItem.class).deserialize(loadBillItemJson);
			}
			//处理修改留痕
			billMarkProcessController.processMark(loadBill, updateField);
			loadBill = pickupBusinessHandlingController.doSaveLoadBill(loadBill, loadBillItemList);
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

	// 跳转到添加物料页面
	public String goUpdateLoadBillItem() {
		return "goUpdateLoadBillItem";
	}

	public String goChooseItem() {
		return "goChooseItem";
	}

	public void getItemEntityJson() {
		try {
			String itemId = getRequestParameter("itemId");
			if (null != itemId && !"".equals(itemId)) {
				Item i = pickupBusinessHandlingController.findItemById(itemId);
				String json = new JSONSerializer().exclude("class").serialize(i);
				renderJson(json);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/** 获取列表数据 */
	public String goChooseListContent() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(10);
			Pager pager = pickupBusinessHandlingController.goSingleItemList(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goChooseListContent";
	}

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdateLoadBillItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				loadBill = pickupBusinessHandlingController.doListLoadBillById(id);
				loadBillItem.setLoadBill(loadBill);
				pickupBusinessHandlingController.doSaveLoadBillItem(loadBillItem);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return UPDATE;
	}

	public void getLoadBillItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				LoadBill loadBill = pickupBusinessHandlingController.doListLoadBillById(id);
				if (null != loadBill) {
					json = convertListToJson(new ArrayList<LoadBillItem>(loadBill.getLoadBillItems()), loadBill.getLoadBillItems().size(), "loadBill");
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

	public String goSearch() {
		return "goSearch";
	}

	public String getId() {
		return id;
	}

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

	public LoadBill getLoadBill() {
		return loadBill;
	}

	public void setLoadBill(LoadBill loadBill) {
		this.loadBill = loadBill;
	}

	public List<LoadBill> getLoadBillList() {
		return loadBillList;
	}

	public void setLoadBillList(List<LoadBill> loadBillList) {
		this.loadBillList = loadBillList;
	}

	public LoadBillItem getLoadBillItem() {
		return loadBillItem;
	}

	public void setLoadBillItem(LoadBillItem loadBillItem) {
		this.loadBillItem = loadBillItem;
	}

}
