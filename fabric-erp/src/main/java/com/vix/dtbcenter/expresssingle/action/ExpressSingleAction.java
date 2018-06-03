package com.vix.dtbcenter.expresssingle.action;

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
import com.vix.dtbcenter.expresssingle.controller.ExpressSingleController;
import com.vix.dtbcenter.expresssingle.entity.ExpressSingle;
import com.vix.dtbcenter.expresssingle.entity.ExpressSingleDetail;
import com.vix.mdm.item.entity.Item;

import flexjson.JSONDeserializer;

@Controller
@Scope("prototype")
public class ExpressSingleAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(ExpressSingleAction.class);
	@Autowired
	private ExpressSingleController expressSingleController;

	private String id;
	private String ids;
	private String pageNo;
	/**
	 * 快递单
	 */
	private ExpressSingle expressSingle;
	private List<ExpressSingle> expressSingleList;
	/**
	 * 
	 */
	private ExpressSingleDetail expressSingleDetail;
	private List<ExpressSingleDetail> expressSingleDetailList;
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
			expressSingleList = expressSingleController.doListExpressSingleList(params);
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
				params.put("code," + SearchCondition.EQUAL, code);
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
			Pager pager = expressSingleController.doListExpressSingle(params, getPager());
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
				expressSingle = expressSingleController.doListExpressSingleById(id);
				logger.info("");
			} else {
				expressSingle = new ExpressSingle();
				expressSingle.setIsTemp("1");
				expressSingle = expressSingleController.doSaveExpressSingleDetail(expressSingle, null);
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
			if (null != expressSingle.getId()) {
				isSave = false;
			}
			expressSingle.setIsTemp("");
			// 销售订单明细
			String expressSingleDetailjson = getRequestParameter("expressSingleDetailjson");
			List<ExpressSingleDetail> expressSingleDetailList = null;
			if (expressSingleDetailjson != null && expressSingleDetailjson.length() > 0) {
				expressSingleDetailList = new JSONDeserializer<List<ExpressSingleDetail>>().use("values", ExpressSingleDetail.class).deserialize(expressSingleDetailjson);
			}
			//处理修改留痕
			billMarkProcessController.processMark(expressSingle, updateField);

			expressSingle = expressSingleController.doSaveExpressSingleDetail(expressSingle, expressSingleDetailList);
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
			ExpressSingle pb = expressSingleController.doListExpressSingleById(id);
			if (null != pb) {
				expressSingleController.doDeleteExpressSingle(pb);
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

	/** 处理删除操作 */
	public String deleteExpressSingleDetailById() {
		try {
			ExpressSingleDetail pb = expressSingleController.doListExpressSingleDetailById(id);
			if (null != pb) {
				expressSingleController.doDeleteExpressSingleDetail(pb);
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
	public void getExpressSingleDetailJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				ExpressSingle expressSingle = expressSingleController.doListExpressSingleById(id);
				if (expressSingle != null) {
					json = convertListToJson(new ArrayList<ExpressSingleDetail>(expressSingle.getExpressSingleDetails()), expressSingle.getExpressSingleDetails().size());
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
			Pager pager = expressSingleController.getItem(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goItemList";
	}

	public String saveOrUpdateExpressSingleDetail() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				expressSingle = expressSingleController.doListExpressSingleById(id);
				expressSingleDetail.setExpressSingle(expressSingle);
				expressSingleController.doSaveExpressSingleDetail(expressSingleDetail);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return UPDATE;
	}

	// 跳转到添加物料页面
	public String goSaveOrUpdateExpressSingleDetail() {

		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				expressSingleDetail = expressSingleController.doListExpressSingleDetailById(id);
				logger.info("");
			} else {
				expressSingleDetail = new ExpressSingleDetail();
				expressSingleDetail = expressSingleController.doSaveExpressSingleDetail(expressSingleDetail);
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

	public ExpressSingle getExpressSingle() {
		return expressSingle;
	}

	public void setExpressSingle(ExpressSingle expressSingle) {
		this.expressSingle = expressSingle;
	}

	public List<ExpressSingle> getExpressSingleList() {
		return expressSingleList;
	}

	public void setExpressSingleList(List<ExpressSingle> expressSingleList) {
		this.expressSingleList = expressSingleList;
	}

	public ExpressSingleDetail getExpressSingleDetail() {
		return expressSingleDetail;
	}

	public void setExpressSingleDetail(ExpressSingleDetail expressSingleDetail) {
		this.expressSingleDetail = expressSingleDetail;
	}

	public List<ExpressSingleDetail> getExpressSingleDetailList() {
		return expressSingleDetailList;
	}

	public void setExpressSingleDetailList(List<ExpressSingleDetail> expressSingleDetailList) {
		this.expressSingleDetailList = expressSingleDetailList;
	}

}
