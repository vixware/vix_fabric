package com.vix.purchase.main.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.mdm.purchase.apply.entity.PurchaseApply;
import com.vix.mdm.purchase.arrivalmgr.entity.PurchaseArrival;
import com.vix.mdm.purchase.inbound.entity.PurchaseInbound;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.purchase.pursreturn.entity.PurchaseReturn;
import com.vix.system.remindsCenter.base.service.IRemindsCenterService;

@Controller
@Scope("prototype")
public class PurchaseManagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IRemindsCenterService remindsCenterService;
	private List<PurchasePlan> purchasePlanList;
	private List<PurchaseApply> purchaseApplyList;
	private List<PurchaseOrder> purchaseOrderList;
	private List<PurchaseArrival> purchaseArrivalList;
	private List<PurchaseInbound> purchaseInboundList;
	private List<PurchaseReturn> purchaseReturnList;
	private Integer purchasePlanNum = 0;
	private Integer purchaseApplyNum = 0;
	private Integer purchaseOrderNum = 0;
	private Integer purchaseArrivalNum = 0;
	private Integer purchaseInboundNum = 0;
	private Integer purchaseReturnNum = 0;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("isReport," + SearchCondition.EQUAL, "0");
			purchasePlanList = remindsCenterService.findAllByConditions(PurchasePlan.class, params);
			if (purchasePlanList != null && purchasePlanList.size() > 0) {
				purchasePlanNum = purchasePlanList.size();
			}
			Map<String, Object> purchaseApplyParams = new HashMap<String, Object>();
			purchaseApplyList = remindsCenterService.findAllByConditions(PurchaseApply.class, purchaseApplyParams);
			if (purchaseApplyList != null && purchaseApplyList.size() > 0) {
				purchaseApplyNum = purchaseApplyList.size();
			}
			Map<String, Object> purchaseOrderParams = new HashMap<String, Object>();
			purchaseOrderList = remindsCenterService.findAllByConditions(PurchaseOrder.class, purchaseOrderParams);
			if (purchaseOrderList != null && purchaseOrderList.size() > 0) {
				purchaseOrderNum = purchaseOrderList.size();
			}
			Map<String, Object> purchaseArrivalParams = new HashMap<String, Object>();
			purchaseArrivalList = remindsCenterService.findAllByConditions(PurchaseArrival.class, purchaseArrivalParams);
			if (purchaseArrivalList != null && purchaseArrivalList.size() > 0) {
				purchaseArrivalNum = purchaseArrivalList.size();
			}
			Map<String, Object> purchaseInboundParams = new HashMap<String, Object>();
			purchaseInboundList = remindsCenterService.findAllByConditions(PurchaseInbound.class, purchaseInboundParams);
			if (purchaseInboundList != null && purchaseInboundList.size() > 0) {
				purchaseInboundNum = purchaseInboundList.size();
			}
			Map<String, Object> purchaseReturnParams = new HashMap<String, Object>();
			purchaseReturnList = remindsCenterService.findAllByConditions(PurchaseReturn.class, purchaseReturnParams);
			if (purchaseReturnList != null && purchaseReturnList.size() > 0) {
				purchaseReturnNum = purchaseReturnList.size();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * @return the purchasePlanList
	 */
	public List<PurchasePlan> getPurchasePlanList() {
		return purchasePlanList;
	}

	/**
	 * @param purchasePlanList
	 *            the purchasePlanList to set
	 */
	public void setPurchasePlanList(List<PurchasePlan> purchasePlanList) {
		this.purchasePlanList = purchasePlanList;
	}

	/**
	 * @return the purchaseApplyList
	 */
	public List<PurchaseApply> getPurchaseApplyList() {
		return purchaseApplyList;
	}

	/**
	 * @param purchaseApplyList
	 *            the purchaseApplyList to set
	 */
	public void setPurchaseApplyList(List<PurchaseApply> purchaseApplyList) {
		this.purchaseApplyList = purchaseApplyList;
	}

	/**
	 * @return the purchaseOrderList
	 */
	public List<PurchaseOrder> getPurchaseOrderList() {
		return purchaseOrderList;
	}

	/**
	 * @param purchaseOrderList
	 *            the purchaseOrderList to set
	 */
	public void setPurchaseOrderList(List<PurchaseOrder> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
	}

	/**
	 * @return the purchaseArrivalList
	 */
	public List<PurchaseArrival> getPurchaseArrivalList() {
		return purchaseArrivalList;
	}

	/**
	 * @param purchaseArrivalList
	 *            the purchaseArrivalList to set
	 */
	public void setPurchaseArrivalList(List<PurchaseArrival> purchaseArrivalList) {
		this.purchaseArrivalList = purchaseArrivalList;
	}

	/**
	 * @return the purchaseInboundList
	 */
	public List<PurchaseInbound> getPurchaseInboundList() {
		return purchaseInboundList;
	}

	/**
	 * @param purchaseInboundList
	 *            the purchaseInboundList to set
	 */
	public void setPurchaseInboundList(List<PurchaseInbound> purchaseInboundList) {
		this.purchaseInboundList = purchaseInboundList;
	}

	/**
	 * @return the purchaseReturnList
	 */
	public List<PurchaseReturn> getPurchaseReturnList() {
		return purchaseReturnList;
	}

	/**
	 * @param purchaseReturnList
	 *            the purchaseReturnList to set
	 */
	public void setPurchaseReturnList(List<PurchaseReturn> purchaseReturnList) {
		this.purchaseReturnList = purchaseReturnList;
	}

	/**
	 * @return the purchasePlanNum
	 */
	public Integer getPurchasePlanNum() {
		return purchasePlanNum;
	}

	/**
	 * @param purchasePlanNum
	 *            the purchasePlanNum to set
	 */
	public void setPurchasePlanNum(Integer purchasePlanNum) {
		this.purchasePlanNum = purchasePlanNum;
	}

	/**
	 * @return the purchaseApplyNum
	 */
	public Integer getPurchaseApplyNum() {
		return purchaseApplyNum;
	}

	/**
	 * @param purchaseApplyNum
	 *            the purchaseApplyNum to set
	 */
	public void setPurchaseApplyNum(Integer purchaseApplyNum) {
		this.purchaseApplyNum = purchaseApplyNum;
	}

	/**
	 * @return the purchaseOrderNum
	 */
	public Integer getPurchaseOrderNum() {
		return purchaseOrderNum;
	}

	/**
	 * @param purchaseOrderNum
	 *            the purchaseOrderNum to set
	 */
	public void setPurchaseOrderNum(Integer purchaseOrderNum) {
		this.purchaseOrderNum = purchaseOrderNum;
	}

	/**
	 * @return the purchaseArrivalNum
	 */
	public Integer getPurchaseArrivalNum() {
		return purchaseArrivalNum;
	}

	/**
	 * @param purchaseArrivalNum
	 *            the purchaseArrivalNum to set
	 */
	public void setPurchaseArrivalNum(Integer purchaseArrivalNum) {
		this.purchaseArrivalNum = purchaseArrivalNum;
	}

	/**
	 * @return the purchaseInboundNum
	 */
	public Integer getPurchaseInboundNum() {
		return purchaseInboundNum;
	}

	/**
	 * @param purchaseInboundNum
	 *            the purchaseInboundNum to set
	 */
	public void setPurchaseInboundNum(Integer purchaseInboundNum) {
		this.purchaseInboundNum = purchaseInboundNum;
	}

	/**
	 * @return the purchaseReturnNum
	 */
	public Integer getPurchaseReturnNum() {
		return purchaseReturnNum;
	}

	/**
	 * @param purchaseReturnNum
	 *            the purchaseReturnNum to set
	 */
	public void setPurchaseReturnNum(Integer purchaseReturnNum) {
		this.purchaseReturnNum = purchaseReturnNum;
	}

}
