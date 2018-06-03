package com.vix.drp.returnrecordstores.action;

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
import com.vix.drp.returnrecordstores.controller.StoreReturnRecordsController;
import com.vix.hr.organization.entity.Employee;
import com.vix.sales.delivery.entity.SaleReturnForm;
import com.vix.sales.delivery.entity.SaleReturnFormItem;

@Controller
@Scope("prototype")
public class StoreReturnRecordsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private StoreReturnRecordsController storeReturnRecordsController;

	private String id;
	private String ids;
	private String parentId;
	private String pageNo;

	private SaleReturnForm saleReturnForm;

	private List<SaleReturnForm> saleReturnFormList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 获取当前登录用户所在的公司或供应商
			String employeeId = SecurityUtil.getCurrentEmpId();
			// 获取当前员工信息
			Employee employee = storeReturnRecordsController.doListEmployeeById(employeeId);
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
			saleReturnFormList = storeReturnRecordsController.doListSaleReturnFormList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// 获取当前登录用户所在的公司或供应商
			String employeeId = SecurityUtil.getCurrentEmpId();
			// 获取当前员工信息
			Employee employee = storeReturnRecordsController.doListEmployeeById(employeeId);
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
			Pager pager = storeReturnRecordsController.doListSaleReturnForm(params, getPager());
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
				saleReturnForm = storeReturnRecordsController.doListEntityById(id);
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
			if (null != saleReturnForm.getId() && !"".equals(saleReturnForm.getId())) {
				isSave = false;
			}
			// 获取当前登录用户所在的公司或供应商
			String employeeId = SecurityUtil.getCurrentEmpId();
			// 获取当前员工信息
			Employee employee = storeReturnRecordsController.doListEmployeeById(employeeId);
			if (employee.getChannelDistributor() != null) {
				// 如果登录的员工属于经销商或门店
				ChannelDistributor channelDistributor = employee.getChannelDistributor();
				saleReturnForm.setChannelDistributor(channelDistributor);
			}
			storeReturnRecordsController.doSaveSaleReturnForm(saleReturnForm);
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
			SaleReturnForm saleReturnForm = storeReturnRecordsController.doListEntityById(id);
			if (null != saleReturnForm) {
				storeReturnRecordsController.doDeleteByEntity(saleReturnForm);
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
				storeReturnRecordsController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public void getSaleReturnFormItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				SaleReturnForm saleReturnForm = storeReturnRecordsController.doListEntityById(id);
				if (null != saleReturnForm) {
					json = convertListToJson(new ArrayList<SaleReturnFormItem>(saleReturnForm.getSaleReturnFormItems()), saleReturnForm.getSaleReturnFormItems().size());
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
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public SaleReturnForm getSaleReturnForm() {
		return saleReturnForm;
	}

	public void setSaleReturnForm(SaleReturnForm saleReturnForm) {
		this.saleReturnForm = saleReturnForm;
	}

	public List<SaleReturnForm> getSaleReturnFormList() {
		return saleReturnFormList;
	}

	public void setSaleReturnFormList(List<SaleReturnForm> saleReturnFormList) {
		this.saleReturnFormList = saleReturnFormList;
	}

}
