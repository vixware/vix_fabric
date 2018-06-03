package com.vix.inventory.barcode.action;

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
import com.vix.inventory.barcode.controller.BarcodeController;
import com.vix.inventory.inbound.entity.StockRecords;

@Controller
@Scope("prototype")
public class BarcodeAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(BarcodeAction.class);
	@Autowired
	private BarcodeController barcodeController;

	private String id;
	private String ids;
	private String pageNo;
	private StockRecords wimStockrecords;

	private List<StockRecords> wimStockrecordsList;

	@Override
	public String goList() {
		try {
			wimStockrecordsList = barcodeController.doListSalesOrderIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = barcodeController.doListSalesOrder(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			Pager pager = barcodeController.doSubSingleList(params, getPager());
			setPager(pager);
			logger.info("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				wimStockrecords = barcodeController.doListEntityById(id);
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
			if (StringUtils.isNotEmpty(wimStockrecords.getId()) && !"0".equals(wimStockrecords.getId())) {
				isSave = false;
			}
			wimStockrecords = barcodeController.doSaveSalesOrder(wimStockrecords);
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
			StockRecords pb = barcodeController.doListEntityById(id);
			if (null != pb) {
				barcodeController.doDeleteByEntity(pb);
				logger.info("");
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
	public String deleteByIds() {
		try {
			if (StringUtils.isNotEmpty(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				barcodeController.doDeleteByIds(delIds);
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

	public List<StockRecords> getSalesOrderList() {
		return wimStockrecordsList;
	}

	public void setSalesOrderList(List<StockRecords> salesOrderList) {
		this.wimStockrecordsList = salesOrderList;
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

	public StockRecords getSalesOrder() {
		return wimStockrecords;
	}

	public void setSalesOrder(StockRecords salesOrder) {
		this.wimStockrecords = salesOrder;
	}

	public StockRecords getWimStockrecords() {
		return wimStockrecords;
	}

	public void setWimStockrecords(StockRecords wimStockrecords) {
		this.wimStockrecords = wimStockrecords;
	}

	public List<StockRecords> getWimStockrecordsList() {
		return wimStockrecordsList;
	}

	public void setWimStockrecordsList(List<StockRecords> wimStockrecordsList) {
		this.wimStockrecordsList = wimStockrecordsList;
	}
}
