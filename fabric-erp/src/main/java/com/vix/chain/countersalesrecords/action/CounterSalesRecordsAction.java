package com.vix.chain.countersalesrecords.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.chain.countersalesrecords.controller.CounterSalesRecordsController;
import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@Controller
@Scope("prototype")
public class CounterSalesRecordsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private CounterSalesRecordsController counterSalesRecordsController;

	private String id;
	private String parentId;
	private String ids;
	private String pageNo;
	private String treeType;
	private SalesOrder salesOrder;

	private List<SalesOrder> salesOrderList;

	private SaleOrderItem saleOrderItem;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			salesOrderList = counterSalesRecordsController.doListSalesOrderIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("bizType," + SearchCondition.ANYLIKE, "S");
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null == id || id == 0) {
				
			} else {
				if ("C".equals(treeType)) { // 点击的树节点是公司
				} else if ("CH".equals(treeType)) { // 点击的树节点是分销体系结构
					params.put("channelDistributor.id," + SearchCondition.EQUAL, id);
				}
			}
			// 获取当前员工信息
			Employee employee = counterSalesRecordsController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
			// 获取登录员工所在的部门信息
			if (employee.getCompanyCode() != null) {
				params.put("companyCode," + SearchCondition.ANYLIKE, employee.getCompanyCode());
			}
			if (employee.getChannelDistributor() != null) {
				// 如果登录的员工属于经销商或门店
				ChannelDistributor channelDistributor = employee.getChannelDistributor();
				params.put("channelDistributor.code," + SearchCondition.ANYLIKE, channelDistributor.getCode());
			}
			Pager pager = counterSalesRecordsController.doListSalesOrder(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null != id && id.longValue() > 0) {
				salesOrder = counterSalesRecordsController.doListEntityById(id);
			} else {
				salesOrder = new SalesOrder();
				salesOrder.setIsTemp("1");
				counterSalesRecordsController.doSaveSalesOrder(salesOrder);
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
			if (null != salesOrder.getId()) {
				isSave = false;
			}
			salesOrder.setIsTemp("");
			salesOrder.setBizType("S");
			String sojson = getRequestParameter("sojson");
			List<SaleOrderItem> soList = null;
			if (sojson != null && !"".equals(sojson)) {
				soList = new JSONDeserializer<List<SaleOrderItem>>().use("values", SaleOrderItem.class).deserialize(sojson);
			}
			initEntityBaseController.initEntityBaseAttribute(salesOrder);
			counterSalesRecordsController.doSaveSalesOrder(salesOrder, soList);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(SAVE_SUCCESS);
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
			SalesOrder salesOrder = counterSalesRecordsController.doListEntityById(id);
			if (null != salesOrder) {
				counterSalesRecordsController.doDeleteByEntity(salesOrder);
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

	public String deleteSaleOrderItemById() {
		try {
			SaleOrderItem saleOrderItem = counterSalesRecordsController.doListSaleOrderItemById(id);
			if (null != saleOrderItem) {
				counterSalesRecordsController.doDeleteSaleOrderItemByEntity(saleOrderItem);
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
			if (null != ids && !"".equals(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				counterSalesRecordsController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotChoose"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public void getSaleOrderItemJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null != id && !"".equals(id)) {
				SalesOrder salesOrder = counterSalesRecordsController.doListEntityById(id);
				if (null != salesOrder) {
					json = convertListToJson(new ArrayList<SaleOrderItem>(salesOrder.getSaleOrderItems()), salesOrder.getSaleOrderItems().size());
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

	public String goSaveSaleOrderItem() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null != id && id.longValue() > 0) {
				saleOrderItem = counterSalesRecordsController.doListSaleOrderItemById(id);
			} else {
				saleOrderItem = new SaleOrderItem();
				counterSalesRecordsController.doSaveSaleOrderItem(saleOrderItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveSaleOrderItem";
	}

	/**
	 * 弹出显示库存信息列表
	 * 
	 * @return
	 */
	public String goInventoryCurrentStock() {
		return "goInventoryCurrentStock";
	}

	/**
	 * 加载显示库存信息列表数据内容
	 * 
	 * @return
	 */
	public String goInventoryCurrentStockList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = counterSalesRecordsController.doListInventoryCurrentStock(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goInventoryCurrentStockList";
	}

	public void getInventoryCurrentStockJson() {
		try {
			String inventoryCurrentStockId = getRequestParameter("inventoryCurrentStockId");
			if (null != inventoryCurrentStockId && !"".equals(inventoryCurrentStockId)) {
				InventoryCurrentStock inventoryCurrentStock = counterSalesRecordsController.doListInventoryCurrentStockById(inventoryCurrentStockId);
				String json = new JSONSerializer().exclude("class").exclude("*.handler").exclude("*.hibernateLazyInitializer").serialize(inventoryCurrentStock);
				renderJson(json);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 保存销售订单明细 单价*数量=金额
	 * 
	 * @return
	 */
	public String saveOrUpdateSaleOrderItem() {
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null != id && id.longValue() > 0) {
				salesOrder = counterSalesRecordsController.doListEntityById(id);
				saleOrderItem.setSalesOrder(salesOrder);
				if (saleOrderItem != null && saleOrderItem.getPrice() != null && saleOrderItem.getAmount() != null) {
					saleOrderItem.setNetTotal(saleOrderItem.getPrice() * saleOrderItem.getAmount());
				}
				counterSalesRecordsController.doSaveSaleOrderItem(saleOrderItem);
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(SAVE_SUCCESS);
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

	/**
	 * 
	 * @return
	 */
	public String saveSaleOrderItem() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null != id && id.longValue() > 0) {
				salesOrder = counterSalesRecordsController.doListEntityById(id);
				SaleOrderItem saleOrderItem = new SaleOrderItem();
				saleOrderItem.setSalesOrder(salesOrder);
				String itemcode = getRequestParameter("itemcode");
				InventoryCurrentStock inventoryCurrentStock = counterSalesRecordsController.doListInventoryCurrentStock(itemcode);
				if (inventoryCurrentStock != null) {
					saleOrderItem.setItemCode(inventoryCurrentStock.getItemcode());
					saleOrderItem.setSpecification(inventoryCurrentStock.getSpecification());
					saleOrderItem.setUnit(inventoryCurrentStock.getUnit());
					saleOrderItem.setPrice(inventoryCurrentStock.getPrice());
					saleOrderItem.setNetTotal(0D);
					counterSalesRecordsController.doSaveSaleOrderItem(saleOrderItem);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public List<SalesOrder> getSalesOrderList() {
		return salesOrderList;
	}

	public void setSalesOrderList(List<SalesOrder> salesOrderList) {
		this.salesOrderList = salesOrderList;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public SaleOrderItem getSaleOrderItem() {
		return saleOrderItem;
	}

	public void setSaleOrderItem(SaleOrderItem saleOrderItem) {
		this.saleOrderItem = saleOrderItem;
	}

}
