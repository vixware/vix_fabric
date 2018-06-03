package com.vix.nvix.inventory.action;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.billtype.BillType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.limitstake.entity.StockLimitedTaking;
import com.vix.inventory.limitstake.entity.StockLimitedTakingItem;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class VixntLimitsTakeAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	
	Logger logger = Logger.getLogger(VixntLimitsTakeAction.class);
	
	private StockLimitedTaking stockLimitedTaking;
	private StockLimitedTakingItem stockLimitedTakingItem;
    private String stockLimitedTakingId;

	/** 获取列表数据 */
	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String title = getDecodeRequestParameter("title");
			if (StringUtils.isNotEmpty(title)) {
				params.put("name," + SearchCondition.ANYLIKE, title);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, StockLimitedTaking.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goStockLimitedTakingItemList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String searchProductName = getDecodeRequestParameter("searchProductName");
			if (StringUtils.isNotEmpty(searchProductName)) {
				params.put("itemname," + SearchCondition.ANYLIKE, searchProductName);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("stockLimitedTaking.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, StockLimitedTakingItem.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 子项价格汇总 */
	public void getStockLimitedTakingTotal() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				StockLimitedTaking stockLimitedTaking = vixntBaseService.findEntityById(StockLimitedTaking.class, id);
				if (null != stockLimitedTaking) {
					Double totalAmount = 0d;
					for (StockLimitedTakingItem stockLimitedTakingItem : stockLimitedTaking.getStockLimitedTakingItem()) {
						totalAmount += stockLimitedTakingItem.getPrice() * stockLimitedTakingItem.getRequisitionCount();
					}
					stockLimitedTaking.setTotalAmount(totalAmount);
					stockLimitedTaking = vixntBaseService.merge(stockLimitedTaking);
					DecimalFormat df = new DecimalFormat(".##");
					String st = df.format(totalAmount);
					renderJson(st);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				 stockLimitedTaking = vixntBaseService.findEntityById(StockLimitedTaking.class, id);
			} else {
				stockLimitedTaking = new StockLimitedTaking();
				stockLimitedTaking.setCode(autoCreateCode.getBillNO(BillType.INV_OUTBOUND));
				Employee employee = getEmployee();
				if (employee != null) {
					stockLimitedTaking.setCreator(employee.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		try {
			initEntityBaseController.initEntityBaseAttribute(stockLimitedTaking);
			stockLimitedTaking = vixntBaseService.merge(stockLimitedTaking);
			renderText(stockLimitedTaking.getId()+":"+SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			StockLimitedTaking pb = vixntBaseService.findEntityById(StockLimitedTaking.class, id);
			if (null != pb) {
				vixntBaseService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}



	public void  saveOrUpdateStockLimitedTakingItem() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(id)) {
				stockLimitedTaking = vixntBaseService.findEntityById(StockLimitedTaking.class, id);
				if (stockLimitedTaking != null) {
					stockLimitedTakingItem.setStockLimitedTaking(stockLimitedTaking);
				}

				initEntityBaseController.initEntityBaseAttribute(stockLimitedTakingItem);
				vixntBaseService.merge(stockLimitedTakingItem);
			}
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
	}



	/**
	 * @return
	 */
	public String goSaveOrUpdateStockLimitedTakingItem() {
		try {
			if (StringUtils.isNotEmpty(stockLimitedTakingId)) {
				stockLimitedTaking = vixntBaseService.findEntityById(StockLimitedTaking.class, stockLimitedTakingId);
			}
			if (StringUtils.isNotEmpty(id)) {
				stockLimitedTakingItem = vixntBaseService.findEntityById(StockLimitedTakingItem.class, id);
			} else {
				stockLimitedTakingItem = new StockLimitedTakingItem();
				if (stockLimitedTaking != null) {
					stockLimitedTakingItem.setStockLimitedTaking(stockLimitedTaking);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goSaveOrUpdateStockLimitedTakingItem";
	}

	public void goInventoryCurrentStockList() {
		try {
			Map<String, Object> params = getParams();
			// 倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager.setPageSize(7);
			pager = vixntBaseService.findPagerByHqlConditions(pager, InventoryCurrentStock.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
