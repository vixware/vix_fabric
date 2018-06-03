package com.vix.inventory.positionadjustment.action;

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
import com.vix.common.billtype.BillType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.ebusiness.order.orderProcess.controller.OrderProcessController;
import com.vix.inventory.positionadjustment.controller.PositionAdjustmentController;
import com.vix.inventory.positionadjustment.entity.WimAdjustpvouch;
import com.vix.inventory.positionadjustment.entity.WimAdjustpvouchItem;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.warehouse.entity.InvShelf;

@Controller
@Scope("prototype")
public class PositionAdjustmentAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(PositionAdjustmentAction.class);
	@Autowired
	private PositionAdjustmentController positionAdjustmentController;
	@Autowired
	private OrderProcessController orderProcessController;
	private String id;
	private String ids;
	private String pageNo;
	private String parentId;
	private String inventoryCurrentStockId;
	private String wimAdjustpvouchId;
	/**
	 * 货位调整单
	 */
	private WimAdjustpvouch wimAdjustpvouch;
	private List<WimAdjustpvouch> wimAdjustpvouchList;
	private WimAdjustpvouchItem wimAdjustpvouchItem;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			wimAdjustpvouchList = positionAdjustmentController.doListWimAdjustpvouchList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 根据条件查询
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
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
			//处理搜索条件
			String name = getRequestParameter("name");
			String code = getRequestParameter("code");
			String wimAdjustpvouchName = getDecodeRequestParameter("wimAdjustpvouchName");
			if (name != null && !"".equals(name)) {
				params.put("code," + SearchCondition.EQUAL, name);
			}
			if (code != null && !"".equals(code)) {
				params.put("code," + SearchCondition.EQUAL, code);
			}
			if (wimAdjustpvouchName != null && !"".equals(wimAdjustpvouchName)) {
				params.put("name," + SearchCondition.ANYLIKE, wimAdjustpvouchName);
			}
			//处理搜索条件

			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");

			//搜索加强
			Map<String, Object> stockRecordLinesparams = new HashMap<String, Object>();
			//根据商品信息获取订单ID
			String itemname = getDecodeRequestParameter("itemname");
			String itemcode = getDecodeRequestParameter("itemcode");
			String stockRecordsIds = "";
			if (StringUtils.isNotEmpty(itemname)) {
				stockRecordLinesparams.put("itemname," + SearchCondition.ANYLIKE, itemname);
			}
			if (StringUtils.isNotEmpty(itemcode)) {
				stockRecordLinesparams.put("itemcode," + SearchCondition.ANYLIKE, itemcode);
			}
			List<WimAdjustpvouchItem> wimAdjustpvouchItemList = positionAdjustmentController.doListWimAdjustpvouchItemList(stockRecordLinesparams);
			if (wimAdjustpvouchItemList != null && wimAdjustpvouchItemList.size() > 0) {
				for (WimAdjustpvouchItem wimAdjustpvouchItem : wimAdjustpvouchItemList) {
					if (wimAdjustpvouchItem != null && wimAdjustpvouchItem.getWimAdjustpvouch() != null) {
						stockRecordsIds += wimAdjustpvouchItem.getWimAdjustpvouch().getId() + ",";
					}
				}
			}
			if (StringUtils.isNotEmpty(stockRecordsIds)) {
				params.put("id," + SearchCondition.IN, stockRecordsIds);
			}
			//搜索加强

			pager = positionAdjustmentController.doListWimAdjustpvouchPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至新增修改货位调整单 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				wimAdjustpvouch = positionAdjustmentController.doListWimAdjustpvouchById(id);
			} else {
				wimAdjustpvouch = new WimAdjustpvouch();
				wimAdjustpvouch.setIsTemp("1");
				wimAdjustpvouch.setCode(autoCreateCode.getBillNO(BillType.INV_INBOUND));
				wimAdjustpvouch = positionAdjustmentController.doSaveOrUpdateWimAdjustpvouch(wimAdjustpvouch);
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
			if (StringUtils.isNotEmpty(wimAdjustpvouch.getId()) && !"0".equals(wimAdjustpvouch.getId())) {
				isSave = false;
			}
			wimAdjustpvouch.setIsTemp("");

			//中文拼音化处理
			if (wimAdjustpvouch.getName() != null) {
				String py = ChnToPinYin.getPYString(wimAdjustpvouch.getName());
				wimAdjustpvouch.setChineseCharacter(py.toUpperCase());
			}
			initEntityBaseController.initEntityBaseAttribute(wimAdjustpvouch);
			billMarkProcessController.processMark(wimAdjustpvouch, updateField);
			wimAdjustpvouch = positionAdjustmentController.doSaveOrUpdateWimAdjustpvouch(wimAdjustpvouch);
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
			WimAdjustpvouch pb = positionAdjustmentController.doListWimAdjustpvouchById(id);
			if (null != pb) {
				positionAdjustmentController.doDeleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/** 获取明细的json数据 */

	public void getWimAdjustpvouchItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				WimAdjustpvouch wimAdjustpvouch = positionAdjustmentController.doListWimAdjustpvouchById(id);
				if (wimAdjustpvouch != null) {
					json = convertListToJson(new ArrayList<WimAdjustpvouchItem>(wimAdjustpvouch.getWimAdjustpvouchItem()), wimAdjustpvouch.getWimAdjustpvouchItem().size());
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
			if (StringUtils.isNotEmpty(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				positionAdjustmentController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	//弹出选择商品界面
	public String goInventoryCurrentStockList() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				wimAdjustpvouchItem = positionAdjustmentController.doListWimAdjustpvouchItemById(id);
			} else {
				wimAdjustpvouchItem = new WimAdjustpvouchItem();
				if (parentId != null && !"".equals(parentId)) {
					WimAdjustpvouch wimAdjustpvouch = positionAdjustmentController.doListWimAdjustpvouchById(id);
					wimAdjustpvouchItem.setWimAdjustpvouch(wimAdjustpvouch);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goInventoryCurrentStockList";
	}

	public String goInventoryCurrentStockListContent() {
		try {
			Map<String, Object> params = getParams();
			//倒序排序
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderField("id");
				pager.setOrderBy("desc");
			}
			pager.setPageSize(6);
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("isQualfied," + SearchCondition.EQUAL, 1);
			positionAdjustmentController.doListInventoryCurrentStockPager(params, pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goInventoryCurrentStockListContent";
	}

	public String saveOrUpdateWimAdjustpvouchItem() {
		boolean isSave = true;
		try {
			if (wimAdjustpvouchItem != null) {
				if (StringUtils.isNotEmpty(wimAdjustpvouchItem.getId())) {
					isSave = false;
				}
				if (StringUtils.isNotEmpty(inventoryCurrentStockId) && !"0".equals(inventoryCurrentStockId)) {
					InventoryCurrentStock inventoryCurrentStock = positionAdjustmentController.doListInventoryCurrentStockById(inventoryCurrentStockId);
					if (inventoryCurrentStock != null) {
						wimAdjustpvouchItem.setItemcode(inventoryCurrentStock.getItemcode());
						wimAdjustpvouchItem.setItemname(inventoryCurrentStock.getItemname());
						//保存调整前货位
						wimAdjustpvouchItem.setOldInvShelf(inventoryCurrentStock.getInvShelf());
						wimAdjustpvouchItem.setCbposcode(inventoryCurrentStock.getInvShelf().getCode());

						if (wimAdjustpvouchItem.getNewInvShelf() != null && StringUtils.isNotEmpty(wimAdjustpvouchItem.getNewInvShelf().getId())) {
							InvShelf newInvShelf = positionAdjustmentController.doListInvShelfById(wimAdjustpvouchItem.getNewInvShelf().getId());
							//保存 调整后货位
							wimAdjustpvouchItem.setNewInvShelf(newInvShelf);
							wimAdjustpvouchItem.setCaposcode(newInvShelf.getCode());

							//将库存商品的货位调整为新货位 ,貌似没更新成功
							inventoryCurrentStock.setInvShelf(newInvShelf);
							inventoryCurrentStock = positionAdjustmentController.doSaveOrUpdateInventoryCurrentStock(inventoryCurrentStock);
						}
					}
				}
				wimAdjustpvouchItem = positionAdjustmentController.doSaveWimAdjustpvouchItem(wimAdjustpvouchItem);
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
		return UPDATE;
	}

	public String goShowPositionAdjustment() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				wimAdjustpvouch = positionAdjustmentController.doListWimAdjustpvouchById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPositionAdjustment";
	}

	public String goSearch() {
		return "goSearch";
	}

	/**
	 * 获取上一条数据
	 * 
	 * @return
	 */
	public String goBeforeWimAdjustpvouch() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				wimAdjustpvouch = positionAdjustmentController.doListWimAdjustpvouchById(id);
				if (wimAdjustpvouch != null && wimAdjustpvouch.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(wimAdjustpvouch.getCreateTime()));
					// 过滤临时数据
					params.put("isTemp", "");
					params.put("tenantId", wimAdjustpvouch.getTenantId());
					params.put("companyInnerCode", wimAdjustpvouch.getCompanyInnerCode());
					params.put("creator", wimAdjustpvouch.getCreator());
					wimAdjustpvouch = (WimAdjustpvouch) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(wimAdjustpvouch.getCreateTime()), params, wimAdjustpvouch, "before");
					if (wimAdjustpvouch == null || StringUtils.isEmpty(wimAdjustpvouch.getId())) {
						wimAdjustpvouch = positionAdjustmentController.doListWimAdjustpvouchById(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPositionAdjustment";
	}

	/**
	 * 
	 * @return
	 */
	public String goAfterWimAdjustpvouch() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				wimAdjustpvouch = positionAdjustmentController.doListWimAdjustpvouchById(id);
				if (wimAdjustpvouch != null && wimAdjustpvouch.getCreateTime() != null) {
					params.put("createTime", formatDateToTimeString(wimAdjustpvouch.getCreateTime()));
					params.put("isTemp", "");
					params.put("tenantId", wimAdjustpvouch.getTenantId());
					params.put("companyInnerCode", wimAdjustpvouch.getCompanyInnerCode());
					params.put("creator", wimAdjustpvouch.getCreator());
					wimAdjustpvouch = (WimAdjustpvouch) orderProcessController.doListBeforeAndAfterEntity(formatDateToTimeString(wimAdjustpvouch.getCreateTime()), params, wimAdjustpvouch, "after");
					if (wimAdjustpvouch == null || StringUtils.isEmpty(wimAdjustpvouch.getId())) {
						wimAdjustpvouch = positionAdjustmentController.doListWimAdjustpvouchById(id);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goShowPositionAdjustment";
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

	public WimAdjustpvouch getWimAdjustpvouch() {
		return wimAdjustpvouch;
	}

	public void setWimAdjustpvouch(WimAdjustpvouch wimAdjustpvouch) {
		this.wimAdjustpvouch = wimAdjustpvouch;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getWimAdjustpvouchId() {
		return wimAdjustpvouchId;
	}

	public void setWimAdjustpvouchId(String wimAdjustpvouchId) {
		this.wimAdjustpvouchId = wimAdjustpvouchId;
	}

	public String getInventoryCurrentStockId() {
		return inventoryCurrentStockId;
	}

	public void setInventoryCurrentStockId(String inventoryCurrentStockId) {
		this.inventoryCurrentStockId = inventoryCurrentStockId;
	}

	public WimAdjustpvouchItem getWimAdjustpvouchItem() {
		return wimAdjustpvouchItem;
	}

	public void setWimAdjustpvouchItem(WimAdjustpvouchItem wimAdjustpvouchItem) {
		this.wimAdjustpvouchItem = wimAdjustpvouchItem;
	}

	public List<WimAdjustpvouch> getWimAdjustpvouchList() {
		return wimAdjustpvouchList;
	}

	public void setWimAdjustpvouchList(List<WimAdjustpvouch> wimAdjustpvouchList) {
		this.wimAdjustpvouchList = wimAdjustpvouchList;
	}

}
