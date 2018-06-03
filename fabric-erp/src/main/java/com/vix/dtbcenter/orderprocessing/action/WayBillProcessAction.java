package com.vix.dtbcenter.orderprocessing.action;

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
import com.vix.dtbcenter.orderprocessing.controller.WayBillProcessController;
import com.vix.dtbcenter.orderprocessing.entity.WayBill;
import com.vix.dtbcenter.orderprocessing.entity.WayBillItem;
import com.vix.mdm.item.entity.Item;

import flexjson.JSONDeserializer;

@Controller
@Scope("prototype")
public class WayBillProcessAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(WayBillProcessAction.class);
	@Autowired
	private WayBillProcessController wayBillProcessController;

	private String id;
	private String ids;
	private String pageNo;
	/**
	 * 销售订单(服务单)
	 */
	private WayBill wayBill;
	private List<WayBill> wayBillList;
	private WayBillItem wayBillItem;
	private List<WayBillItem> wayBillItemList;
	/**
	 * /** 商品信息
	 */
	private Item item;

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
			wayBillList = wayBillProcessController.doListWayBillList(params);
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
			Pager pager = wayBillProcessController.doListWayBill(params, getPager());
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
				wayBill = wayBillProcessController.doListWayBillById(id);
			} else {
				wayBill = new WayBill();
				wayBill.setIsTemp("1");
				wayBill = wayBillProcessController.doSaveWayBill(wayBill, null);
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
			if (null != wayBill.getId() && !"".equals(wayBill.getId())) {
				isSave = false;
			}
			wayBill.setIsTemp("");
			// 销售订单明细
			String wayBillItemjson = getRequestParameter("wayBillItemjson");
			List<WayBillItem> saleOrderItemList = null;
			if (wayBillItemjson != null && wayBillItemjson.length() > 0) {
				saleOrderItemList = new JSONDeserializer<List<WayBillItem>>().use("values", WayBillItem.class).deserialize(wayBillItemjson);
			}
			//处理修改留痕
			billMarkProcessController.processMark(wayBill, updateField);
			wayBill = wayBillProcessController.doSaveWayBill(wayBill, saleOrderItemList);
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
			WayBill pb = wayBillProcessController.doListWayBillById(id);
			if (null != pb) {
				wayBillProcessController.doDeleteWayBill(pb);
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

	/** 获取销售订单明细发运计划的json数据 */
	public void getWayBillItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				WayBill wayBill = wayBillProcessController.doListWayBillById(id);
				if (wayBill != null) {
					json = convertListToJson(new ArrayList<WayBillItem>(wayBill.getWayBillItems()), wayBill.getWayBillItems().size());
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

	// 跳转到选择商品页面
	public String goChooseItem() {
		return "goChooseItem";
	}

	/** 获取供应商列表数据 */
	public String goItemList() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("searchContent");
			String status = getRequestParameter("status");
			// 按状态
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			// 按状态
			if (null != status && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 按最近使用
			Pager pager = wayBillProcessController.getItem(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goItemList";
	}

	public String saveOrUpdateWayBillItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				wayBill = wayBillProcessController.doListWayBillById(id);
				wayBillItem.setWayBill(wayBill);
				wayBillProcessController.doSaveWayBillItem(wayBillItem);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return UPDATE;
	}

	// 跳转到添加物料页面
	public String goAddSaleOrderItem() {

		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				wayBillItem = wayBillProcessController.doListWayBillItemById(id);
				logger.info("");
			} else {
				wayBillItem = new WayBillItem();
				wayBillItem = wayBillProcessController.doSaveWayBillItem(wayBillItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "goAddSaleOrderItem";
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

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public WayBill getWayBill() {
		return wayBill;
	}

	public void setWayBill(WayBill wayBill) {
		this.wayBill = wayBill;
	}

	public List<WayBill> getWayBillList() {
		return wayBillList;
	}

	public void setWayBillList(List<WayBill> wayBillList) {
		this.wayBillList = wayBillList;
	}

	public WayBillItem getWayBillItem() {
		return wayBillItem;
	}

	public void setWayBillItem(WayBillItem wayBillItem) {
		this.wayBillItem = wayBillItem;
	}

	public List<WayBillItem> getWayBillItemList() {
		return wayBillItemList;
	}

	public void setWayBillItemList(List<WayBillItem> wayBillItemList) {
		this.wayBillItemList = wayBillItemList;
	}

}
