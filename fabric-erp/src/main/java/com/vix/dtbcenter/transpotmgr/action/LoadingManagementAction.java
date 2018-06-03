package com.vix.dtbcenter.transpotmgr.action;

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
import com.vix.dtbcenter.transpotmgr.controller.LoadingManagementController;
import com.vix.dtbcenter.transpotmgr.entity.LoadingManagement;

import flexjson.JSONDeserializer;

@Controller
@Scope("prototype")
public class LoadingManagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(LoadingManagementAction.class);
	@Autowired
	private LoadingManagementController loadingManagementController;
	private String id;
	private String ids;
	private String pageNo;
	/**
	 * 派车单
	 */
	private LoadingManagement loadingManagement;
	private List<LoadingManagement> loadingManagementList;

	@Override
	public String goList() {
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
			loadingManagementList = loadingManagementController.doListTruckingOrderList(params);
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
			Pager pager = loadingManagementController.doListTruckingOrder(params, getPager());
			setPager(pager);
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至新增修改派车单 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				loadingManagement = loadingManagementController.doListTruckingOrderById(id);
				logger.info("");
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
			if (null != loadingManagement.getId()) {
				isSave = false;
			}
			/* 派车单明细 */
			String dlJson = getRequestParameter("dlJson");
			List<Object> dlList = new ArrayList<Object>();
			if (dlJson != null && !"".equals(dlJson)) {
				dlList = new JSONDeserializer<List<Object>>().use("values", Object.class).deserialize(dlJson);
			}
			//处理修改留痕
			billMarkProcessController.processMark(loadingManagement, updateField);
			loadingManagement = loadingManagementController.doSaveTruckingOrder(loadingManagement, dlList);
			logger.info("对派车单进行了修改！");
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
			LoadingManagement pb = loadingManagementController.doListTruckingOrderById(id);
			if (null != pb) {
				loadingManagementController.doDeleteByTruckingOrder(pb);
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
				loadingManagementController.doDeleteByIds(delIds);
				logger.info("");
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

	public LoadingManagement getTruckingOrder() {
		return loadingManagement;
	}

	public void setTruckingOrder(LoadingManagement truckingOrder) {
		this.loadingManagement = truckingOrder;
	}

	public List<LoadingManagement> getTruckingOrderList() {
		return loadingManagementList;
	}

	public void setTruckingOrderList(List<LoadingManagement> truckingOrderList) {
		this.loadingManagementList = truckingOrderList;
	}

}
