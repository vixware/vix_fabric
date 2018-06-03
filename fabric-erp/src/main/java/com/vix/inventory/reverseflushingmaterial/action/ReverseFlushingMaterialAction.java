package com.vix.inventory.reverseflushingmaterial.action;

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
import com.vix.inventory.reverseflushingmaterial.controller.ReverseFlushingMaterialController;
import com.vix.inventory.reverseflushingmaterial.entity.ReverseFlushingMaterial;
import com.vix.inventory.reverseflushingmaterial.entity.ReverseFlushingMaterialItem;

import flexjson.JSONDeserializer;

@Controller
@Scope("prototype")
public class ReverseFlushingMaterialAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ReverseFlushingMaterialAction.class);
	@Autowired
	private ReverseFlushingMaterialController reverseFlushingMaterialController;
	private String id;
	private String ids;
	private String pageNo;
	/**
	 * 领料单
	 */
	private ReverseFlushingMaterial reverseFlushingMaterial;
	private List<ReverseFlushingMaterial> reverseFlushingMaterialList;
	/**
	 * 领料单明细
	 */
	private ReverseFlushingMaterialItem reverseFlushingMaterialItem;
	private List<ReverseFlushingMaterialItem> reverseFlushingMaterialItemList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			// code 编码
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			reverseFlushingMaterialList = reverseFlushingMaterialController.doListreverseFlushingMaterialList(params);
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
			Map<String, Object> params = new HashMap<String, Object>();
			// code 编码
			String code = getRequestParameter("code");
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
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			Pager pager = reverseFlushingMaterialController.doListReverseFlushingMaterial(params, getPager());
			setPager(pager);
			logger.info("获取服务订单列表数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	// 搜索
	public String goSearchReverseFlushingMaterialList() {
		try {
			Map<String, Object> params = getParams();
			String tag = getRequestParameter("tag");
			Pager pager = null;
			if ("0".equals(tag)) {
				// 简单搜索
				pager = reverseFlushingMaterialController.doListReverseFlushingMaterialByCon(params, getPager());
			} else {
				// 高级搜索
				pager = reverseFlushingMaterialController.doListReverseFlushingMaterial(params, getPager());
			}
			setPager(pager);
			logger.info("获取服务订单列表数据");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至新增修改盘点单 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				reverseFlushingMaterial = reverseFlushingMaterialController.doListReverseFlushingMaterialById(id);
				logger.info("");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goSaveOrUpdateReverseFlushingMaterialItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				reverseFlushingMaterialItem = reverseFlushingMaterialController.doListReverseFlushingMaterialItemById(id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goSaveOrUpdateReverseFlushingMaterialItem";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != reverseFlushingMaterial.getId()) {
				isSave = false;
			}
			/* 调拨单明细 */
			String dlJson = getRequestParameter("dlJson");
			List<ReverseFlushingMaterialItem> dlList = new JSONDeserializer<List<ReverseFlushingMaterialItem>>().use("values", ReverseFlushingMaterialItem.class).deserialize(dlJson);
			reverseFlushingMaterial = reverseFlushingMaterialController.doSaveReverseFlushingMaterial(reverseFlushingMaterial, dlList);
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
			ReverseFlushingMaterial pb = reverseFlushingMaterialController.doListReverseFlushingMaterialById(id);
			if (null != pb) {
				reverseFlushingMaterialController.doDeleteReverseFlushingMaterialByEntity(pb);
				logger.info("");
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除订单信息");
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 获取明细的json数据 */

	public void getReverseFlushingMaterialItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				ReverseFlushingMaterial reverseFlushingMaterial = reverseFlushingMaterialController.doListReverseFlushingMaterialById(id);
				if (reverseFlushingMaterial != null) {
					json = convertListToJson(new ArrayList<ReverseFlushingMaterialItem>(reverseFlushingMaterial.getReverseFlushingMaterialItem()), reverseFlushingMaterial.getReverseFlushingMaterialItem().size(), "reverseFlushingMaterial");
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
				reverseFlushingMaterialController.doDeleteByIds(delIds);
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

	/**
	 * 增加领料单明细
	 * 
	 * @return
	 */
	public String saveOrUpdateReverseFlushingMaterialItem() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				reverseFlushingMaterial = reverseFlushingMaterialController.doListReverseFlushingMaterialById(id);
				reverseFlushingMaterialItem.setReverseFlushingMaterial(reverseFlushingMaterial);
				reverseFlushingMaterialController.doSaveReverseFlushingMaterialItem(reverseFlushingMaterialItem);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteReverseFlushingMaterialItemById() {
		try {
			ReverseFlushingMaterialItem reverseFlushingMaterialItem = reverseFlushingMaterialController.doListReverseFlushingMaterialItemById(id);
			if (null != reverseFlushingMaterialItem) {
				reverseFlushingMaterialController.deleteReverseFlushingMaterialItem(reverseFlushingMaterialItem);
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

	public ReverseFlushingMaterial getReverseFlushingMaterial() {
		return reverseFlushingMaterial;
	}

	public void setReverseFlushingMaterial(ReverseFlushingMaterial reverseFlushingMaterial) {
		this.reverseFlushingMaterial = reverseFlushingMaterial;
	}

	public List<ReverseFlushingMaterial> getReverseFlushingMaterialList() {
		return reverseFlushingMaterialList;
	}

	public void setReverseFlushingMaterialList(List<ReverseFlushingMaterial> reverseFlushingMaterialList) {
		this.reverseFlushingMaterialList = reverseFlushingMaterialList;
	}

	public ReverseFlushingMaterialItem getReverseFlushingMaterialItem() {
		return reverseFlushingMaterialItem;
	}

	public void setReverseFlushingMaterialItem(ReverseFlushingMaterialItem reverseFlushingMaterialItem) {
		this.reverseFlushingMaterialItem = reverseFlushingMaterialItem;
	}

	public List<ReverseFlushingMaterialItem> getReverseFlushingMaterialItemList() {
		return reverseFlushingMaterialItemList;
	}

	public void setReverseFlushingMaterialItemList(List<ReverseFlushingMaterialItem> reverseFlushingMaterialItemList) {
		this.reverseFlushingMaterialItemList = reverseFlushingMaterialItemList;
	}

}
