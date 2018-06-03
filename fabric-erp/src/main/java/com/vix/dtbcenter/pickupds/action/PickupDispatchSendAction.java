package com.vix.dtbcenter.pickupds.action;

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
import com.vix.dtbcenter.pickupds.controller.PickupDispatchSendController;
import com.vix.dtbcenter.pickupds.entity.DeliveryReceipt;
import com.vix.dtbcenter.transpotmgr.entity.DeliveryReceiptToDelieryNotification;

import flexjson.JSONDeserializer;

@Controller
@Scope("prototype")
public class PickupDispatchSendAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PickupDispatchSendAction.class);
	@Autowired
	private PickupDispatchSendController pickupDispatchSendController;
	private String id;
	private String ids;
	private String pageNo;
	/**
	 * 派车单
	 */
	private DeliveryReceipt deliveryReceipt;
	private List<DeliveryReceipt> deliveryReceiptList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
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
			deliveryReceiptList = pickupDispatchSendController.doListDeliveryReceiptList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 根据条件查询派车单信息
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
			Pager pager = pickupDispatchSendController.doListDeliveryReceipt(params, getPager());
			setPager(pager);
			logger.info("获取派车单列表数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	// 搜索
	public String goSearchDeliveryReceiptList() {
		try {
			Map<String, Object> params = getParams();
			/*params.put("isTemp," + SearchCondition.NOEQUAL, "1");*/
			String tag = getRequestParameter("tag");
			Pager pager = null;
			if ("0".equals(tag)) {
				// 简单搜索
				pager = pickupDispatchSendController.doListDeliveryReceiptByCon(params, getPager());
			} else {
				// 高级搜索
				pager = pickupDispatchSendController.doListDeliveryReceipt(params, getPager());
			}
			setPager(pager);
			logger.info("获取派车单列表数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至新增修改派车单 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				deliveryReceipt = pickupDispatchSendController.doListDeliveryReceiptById(id);
				logger.info("");
			}/* else {
				deliveryReceipt = new DeliveryReceipt();
				deliveryReceipt.setIsTemp("1");
				deliveryReceipt = pickupDispatchSendController.doSaveDeliveryReceipt(deliveryReceipt, null);
				}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != deliveryReceipt.getId()) {
				isSave = false;
			}
			/*deliveryReceipt.setIsTemp("");*/
			/* 派车单明细 */
			String dlJson = getRequestParameter("dlJson");
			List<Object> dlList = new ArrayList<Object>();
			if (dlJson != null && !"".equals(dlJson)) {
				dlList = new JSONDeserializer<List<Object>>().use("values", Object.class).deserialize(dlJson);
			}
			//处理修改留痕
			billMarkProcessController.processMark(deliveryReceipt, updateField);
			deliveryReceipt = pickupDispatchSendController.doSaveDeliveryReceipt(deliveryReceipt, dlList);
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
			DeliveryReceipt pb = pickupDispatchSendController.doListDeliveryReceiptById(id);
			if (null != pb) {
				pickupDispatchSendController.doDeleteByEntity(pb);
				logger.info("");
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除派车单信息");
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 获取明细的json数据 */

	public void getDeliveryReceiptToDelieryNotificationJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				DeliveryReceipt deliveryReceipt = pickupDispatchSendController.doListDeliveryReceiptById(id);
				if (deliveryReceipt != null) {
					json = convertListToJson(new ArrayList<Object>(null), 0, "deliveryReceipt");
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

	/** 批量处理删除操作 */
	public String deleteByIds() {
		try {
			if (null != ids && !"".equals(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				pickupDispatchSendController.doDeleteByIds(delIds);
				logger.info("删除订单信息");
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotChoose"));
			}
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteDeliveryReceiptToDelieryNotificationById() {
		try {
			DeliveryReceiptToDelieryNotification deliveryReceiptToDelieryNotification = pickupDispatchSendController.doListDeliveryReceiptToDelieryNotificationById(id);
			if (null != deliveryReceiptToDelieryNotification) {
				pickupDispatchSendController.deleteDeliveryReceiptToDelieryNotification(deliveryReceiptToDelieryNotification);
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

	public DeliveryReceipt getDeliveryReceipt() {
		return deliveryReceipt;
	}

	public void setDeliveryReceipt(DeliveryReceipt deliveryReceipt) {
		this.deliveryReceipt = deliveryReceipt;
	}

	public List<DeliveryReceipt> getDeliveryReceiptList() {
		return deliveryReceiptList;
	}

	public void setDeliveryReceiptList(List<DeliveryReceipt> deliveryReceiptList) {
		this.deliveryReceiptList = deliveryReceiptList;
	}

}
