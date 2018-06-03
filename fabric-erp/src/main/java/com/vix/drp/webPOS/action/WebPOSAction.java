package com.vix.drp.webPOS.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

/**
 * 
 * com.vix.drp.webPOS.action.WebPOSAction
 * 
 * @author zhanghaibing
 * 
 * @date 2014-6-11
 */
@Controller
@Scope("prototype")
public class WebPOSAction extends BaseAction {

	private static final long serialVersionUID = 1L;


	private String id;
	/**
	 * 销售订单
	 */
	private SalesOrder salesOrder;
	private List<SalesOrder> salesOrderList;

	@Override
	public String goList() {
		return GO_LIST;
	}

	public String goItemList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), InventoryCurrentStock.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goItemList";
	}

	public String goItemCatalogList() {
		try {
			Map<String, Object> params = getParams();
			Pager p = getPager();
			p.setPageSize(6);
			Pager pager = baseHibernateService.findPagerByHqlConditions(p, ItemCatalog.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goItemCatalogList";
	}

	/**
	 * 保存收银台销售订单
	 * 
	 * @return
	 */
	public void saveOrUpdate() {
		try {
			SalesOrder salesOrder = new SalesOrder();
			salesOrder.setIsTemp("");
			salesOrder.setBizType("S");
			salesOrder.setBillDate(new Date());
			initEntityBaseController.initEntityBaseAttribute(salesOrder);
			salesOrder = baseHibernateService.merge(salesOrder);
			String saleOrderItemDataList = getRequestParameter("saleOrderItemDataList");

			DateTransformer dateTransformer = new DateTransformer("yyyy-MM-dd");
			List<SaleOrderItem> saleOrderItemList = new JSONDeserializer<List<SaleOrderItem>>().use(Date.class, dateTransformer).use("values", SaleOrderItem.class).deserialize(saleOrderItemDataList);

			if (saleOrderItemList != null && !"".equals(saleOrderItemList)) {

				for (SaleOrderItem saleOrderItem : saleOrderItemList) {
					saleOrderItem.setSalesOrder(salesOrder);
					initEntityBaseController.initEntityBaseAttribute(saleOrderItem);
					saleOrderItem = baseHibernateService.merge(saleOrderItem);
				}
			}
			renderText(salesOrder.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goPrintSalesOrder() {
		try {
			salesOrder = baseHibernateService.findEntityById(SalesOrder.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goPrintSalesOrder";
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				baseHibernateService.deleteById(SalesOrder.class, id);
			}
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public void getInventoryCurrentStockJson() {
		try {
			InventoryCurrentStock inventoryCurrentStock = baseHibernateService.findEntityById(InventoryCurrentStock.class, id);
			if (inventoryCurrentStock != null) {
				String json = new JSONSerializer().exclude(new String[] { "*.handler", "*.hibernateLazyInitializer" }).serialize(inventoryCurrentStock);
				renderJson(json);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

}
