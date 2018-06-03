package com.vix.drp.storereceivingrecords.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.storereceivingrecords.controller.StoreReceiveRecordsController;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.purchase.order.entity.PurchaseOrder;

@Controller
@Scope("prototype")
public class StoreReceivingrecordsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private StoreReceiveRecordsController storeReceiveRecordsController;

	private String id;
	private String ids;
	private String pageNo;

	private PurchaseOrder purchaseOrder;

	private List<PurchaseOrder> purchaseOrderList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			// 获取当前登录用户所在的公司或供应商
			String employeeId = SecurityUtil.getCurrentEmpId();
			// 获取当前员工信息
			Employee employee = storeReceiveRecordsController.doListEmployeeById(employeeId);
			if (employee.getChannelDistributor() != null) {
				// 如果登录的员工属于经销商或门店
				ChannelDistributor channelDistributor = employee.getChannelDistributor();
				params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
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
			purchaseOrderList = storeReceiveRecordsController.doListPurchaseOrderList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			// 获取当前登录用户所在的公司或供应商
			String employeeId = SecurityUtil.getCurrentEmpId();
			// 获取当前员工信息
			Employee employee = storeReceiveRecordsController.doListEmployeeById(employeeId);
			if (employee.getChannelDistributor() != null) {
				// 如果登录的员工属于经销商或门店
				ChannelDistributor channelDistributor = employee.getChannelDistributor();
				params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
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
			Pager pager = storeReceiveRecordsController.doListPurchaseOrder(params, getPager());
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
				purchaseOrder = storeReceiveRecordsController.doListEntityById(id);
			} else {
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
			if (null != purchaseOrder.getId() && !"".equals(purchaseOrder.getId())) {
				isSave = false;
			}
			// 获取当前登录用户所在的公司或供应商
			String employeeId = SecurityUtil.getCurrentEmpId();
			// 获取当前员工信息
			Employee employee = storeReceiveRecordsController.doListEmployeeById(employeeId);
			if (employee.getChannelDistributor() != null) {
				// 如果登录的员工属于经销商或门店
				ChannelDistributor channelDistributor = employee.getChannelDistributor();
				purchaseOrder.setChannelDistributor(channelDistributor);
			}
			storeReceiveRecordsController.doSavePurchaseOrder(purchaseOrder);
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
			PurchaseOrder purchaseOrder = storeReceiveRecordsController.doListEntityById(id);
			if (null != purchaseOrder) {
				storeReceiveRecordsController.doDeleteByEntity(purchaseOrder);
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
				storeReceiveRecordsController.doDeleteByIds(delIds);
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

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public List<PurchaseOrder> getPurchaseOrderList() {
		return purchaseOrderList;
	}

	public void setPurchaseOrderList(List<PurchaseOrder> purchaseOrderList) {
		this.purchaseOrderList = purchaseOrderList;
	}

}
