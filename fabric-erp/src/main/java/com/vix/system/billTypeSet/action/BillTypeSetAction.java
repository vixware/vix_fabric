package com.vix.system.billTypeSet.action;

import java.util.ArrayList;
import java.util.Date;
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
import com.vix.system.billTypeSet.controller.BillTypeSetController;
import com.vix.system.billTypeSet.entity.BillsCategoryDictionary;
import com.vix.system.billTypeSet.entity.BillsPropertyDictionary;

import flexjson.JSONDeserializer;
import flexjson.transformer.DateTransformer;

@Controller
@Scope("prototype")
public class BillTypeSetAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(BillTypeSetAction.class);
	@Autowired
	private BillTypeSetController billTypeSetController;
	private String id;
	private String ids;
	private String pageNo;
	/**
	 * 单据分类
	 */
	private BillsCategoryDictionary billsCategoryDictionary;
	private List<BillsCategoryDictionary> billsCategoryDictionaryList;

	@Override
	public String goList() {
		try {
			billsCategoryDictionaryList = billTypeSetController.doListBillsCategoryDictionaryIndex();
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
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			// code 编码
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			pager = billTypeSetController.doListBillsCategoryDictionary(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				billsCategoryDictionary = billTypeSetController.doListBillsCategoryDictionaryById(id);
				logger.info("");
			} else {
				billsCategoryDictionary = new BillsCategoryDictionary();
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
			if (null != billsCategoryDictionary.getId() && !"".equals(billsCategoryDictionary.getId())) {
				isSave = false;
			}
			/* 调拨单明细 */
			String dlJson = getRequestParameter("dlJson");
			List<BillsPropertyDictionary> dlList = new ArrayList<BillsPropertyDictionary>();
			if (dlJson != null && !"".equals(dlJson)) {
				DateTransformer dateTransformer = new DateTransformer("yyyy-MM-dd");
				dlList = new JSONDeserializer<List<BillsPropertyDictionary>>().use(Date.class, dateTransformer).use("values", BillsPropertyDictionary.class).deserialize(dlJson);
			}
			billsCategoryDictionary.setStatus("1");
			billsCategoryDictionary = billTypeSetController.doSaveBillsCategoryDictionary(billsCategoryDictionary, dlList);
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
			BillsCategoryDictionary pb = billTypeSetController.doListBillsCategoryDictionaryById(id);
			if (null != pb) {
				billTypeSetController.doDeleteByEntity(pb);
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

	public void getBillsPropertyDictionaryJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (null != id && !"".equals(id)) {
				BillsCategoryDictionary billsCategoryDictionary = billTypeSetController.doListBillsCategoryDictionaryById(id);
				if (billsCategoryDictionary != null) {
					json = convertListToJson(new ArrayList<BillsPropertyDictionary>(billsCategoryDictionary.getBillsPropertyDictionarys()), billsCategoryDictionary.getBillsPropertyDictionarys().size());
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
				billTypeSetController.doDeleteBillsCategoryDictionaryByIds(delIds);
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

	public BillsCategoryDictionary getBillsCategoryDictionary() {
		return billsCategoryDictionary;
	}

	public void setBillsCategoryDictionary(BillsCategoryDictionary billsCategoryDictionary) {
		this.billsCategoryDictionary = billsCategoryDictionary;
	}

	public List<BillsCategoryDictionary> getBillsCategoryDictionaryList() {
		return billsCategoryDictionaryList;
	}

	public void setBillsCategoryDictionaryList(List<BillsCategoryDictionary> billsCategoryDictionaryList) {
		this.billsCategoryDictionaryList = billsCategoryDictionaryList;
	}

}
