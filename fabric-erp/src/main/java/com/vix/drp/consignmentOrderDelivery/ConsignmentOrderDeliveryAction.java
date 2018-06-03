package com.vix.drp.consignmentOrderDelivery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.drp.advertisingManagement.controller.AdvertisingManagementController;
import com.vix.drp.advertisingManagement.entity.AdvertisingManagement;

@Controller
@Scope("prototype")
public class ConsignmentOrderDeliveryAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private AdvertisingManagementController advertisingManagementController;

	private String id;
	private String ids;
	private String pageNo;
	private AdvertisingManagement advertisingManagement;

	private List<AdvertisingManagement> advertisingManagementList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			advertisingManagementList = advertisingManagementController.doListAdvertisingManagementList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			/* 高级查询 */
			Pager pager = advertisingManagementController.doListAdvertisingManagement(params, getPager());
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
				advertisingManagement = advertisingManagementController.doListAdvertisingManagementById(id);
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
			if (null != advertisingManagement.getId() && !"".equals(advertisingManagement.getId())) {
				isSave = false;
			}
			advertisingManagement = advertisingManagementController.doSaveOrUpdateAdvertisingManagement(advertisingManagement);
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
			AdvertisingManagement cd = advertisingManagementController.doListAdvertisingManagementById(id);
			if (null != cd) {
				advertisingManagementController.doDeleteByAdvertisingManagement(cd);
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

	public AdvertisingManagement getAdvertisingManagement() {
		return advertisingManagement;
	}

	public void setAdvertisingManagement(AdvertisingManagement advertisingManagement) {
		this.advertisingManagement = advertisingManagement;
	}

	public List<AdvertisingManagement> getAdvertisingManagementList() {
		return advertisingManagementList;
	}

	public void setAdvertisingManagementList(List<AdvertisingManagement> advertisingManagementList) {
		this.advertisingManagementList = advertisingManagementList;
	}

}
