package com.vix.inventory.batchStandingBook.action;

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
import com.vix.inventory.batchStandingBook.controller.BatchStandingBookController;
import com.vix.inventory.batchStandingBook.entity.InvMainBatch;
import com.vix.inventory.inbound.entity.StockRecordLines;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;

/**
 * 批次台账
 * 
 * @author zhanghaibing
 * 
 * @date 2014-1-4
 */
@Controller
@Scope("prototype")
public class BatchStandingBookAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(BatchStandingBookAction.class);
	@Autowired
	private BatchStandingBookController batchStandingBookController;

	private String id;
	private String ids;
	private String pageNo;
	private InvMainBatch invMainBatch;
	private List<InvMainBatch> invMainBatchList;

	@Override
	public String goList() {
		try {
			invMainBatchList = batchStandingBookController.doListInvMainBatchIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			String code = getRequestParameter("code");
			if (code != null && !"".equals(code)) {
				params.put("code," + SearchCondition.EQUAL, code);
			}
			String itemName = getRequestParameter("itemName");
			if (itemName != null && !"".equals(itemName)) {
				params.put("itemName," + SearchCondition.ANYLIKE, itemName);
			}

			pager = batchStandingBookController.doListInvMainBatch(params, pager);
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
				invMainBatch = batchStandingBookController.doListInvMainBatchById(id);
				logger.info("");
			} else {
				invMainBatch = new InvMainBatch();
				invMainBatch.setIsTemp("1");
				invMainBatch = batchStandingBookController.doSaveInvMainBatch(invMainBatch);
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
			if (StringUtils.isNotEmpty(invMainBatch.getId()) && !"0".equals(invMainBatch.getId())) {
				isSave = false;
			}
			invMainBatch = batchStandingBookController.doSaveInvMainBatch(invMainBatch);
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

	public String goChooseInvStockRecordLines() {
		return "goChooseInvStockRecordLines";
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public String goInvStockRecordLinesList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = batchStandingBookController.doListInvStockRecordLines(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goInvStockRecordLinesList";
	}

	/**
	 * 
	 * 
	 * @return
	 */
	public String createRelationship() {
		String salesOrderids = getRequestParameter("salesOrderid");
		invMainBatch = batchStandingBookController.doListInvMainBatchById(id);
		// 将选择的订单编号转存成数组格式
		if (StringUtils.isNotEmpty(salesOrderids)) {
			String[] salesOrderidArr = salesOrderids.split(",");
			if (salesOrderidArr != null && !"".equals(salesOrderidArr) && salesOrderidArr.length > 0) {
				for (String salesOrderidid : salesOrderidArr) {
					// 遍历订单编号数组
					if (salesOrderidid != null && !"".equals(salesOrderidid)) {
						// 取出订单编号 通过订单编号获取对应的订单信息
						StockRecordLines invStockRecordLines = batchStandingBookController.doListInvStockRecordLinesById(salesOrderidid);
						if (invStockRecordLines != null) {
							try {
								batchStandingBookController.createRelationship(invMainBatch, invStockRecordLines);
								renderText(SAVE_SUCCESS);
							} catch (Exception e) {
								renderText(SAVE_FAIL);
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			InvMainBatch pb = batchStandingBookController.doListInvMainBatchById(id);
			if (null != pb) {
				batchStandingBookController.doDeleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 获取json数据 */
	public void getInvStockRecordLinesJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				invMainBatch = batchStandingBookController.doListInvMainBatchById(id);
				if (null != invMainBatch) {
					json = convertListToJson(new ArrayList<InventoryCurrentStock>(invMainBatch.getSubInventoryCurrentStocks()), invMainBatch.getSubInventoryCurrentStocks().size());
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

	public InvMainBatch getInvMainBatch() {
		return invMainBatch;
	}

	public void setInvMainBatch(InvMainBatch invMainBatch) {
		this.invMainBatch = invMainBatch;
	}

	public List<InvMainBatch> getInvMainBatchList() {
		return invMainBatchList;
	}

	public void setInvMainBatchList(List<InvMainBatch> invMainBatchList) {
		this.invMainBatchList = invMainBatchList;
	}

}
