package com.vix.drp.storeenquiryrequest.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.item.entity.Item;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

@Controller
@Scope("prototype")
public class StoreEnquiryrequestAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private String ids;
	private String pageNo;
	private String treeType;
	private String parentId;
	private String salesOrderId;
	private SalesOrder salesOrder;
	private List<SalesOrder> salesOrderList;

	private SaleOrderItem saleOrderItem;
	private List<SaleOrderItem> saleOrderItemList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("bizType," + SearchCondition.EQUAL, "mdyh");
			if (parentId != null && !"undefined".equals(parentId)) {
				if ("CH".equals(treeType)) {
					params.put("channelDistributor.id," + SearchCondition.EQUAL, parentId);
				}
			}
			// 获取当前登录用户所在的公司或供应商
			if (SecurityUtil.getCurrentEmpId() != null) {
				Employee employee = baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null && employee.getChannelDistributor() != null) {
					// 如果登录的员工属于经销商或门店
					params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
				}
			} // 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			salesOrderList = baseHibernateService.findAllByConditions(SalesOrder.class, params);
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
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("bizType," + SearchCondition.EQUAL, "mdyh");

			//处理搜索条件
			String name = getDecodeRequestParameter("name");
			String code = getDecodeRequestParameter("code");
			String selectname = getDecodeRequestParameter("selectname");
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			if (code != null && !"".equals(code)) {
				params.put("code," + SearchCondition.EQUAL, code);
			}
			if (selectname != null && !"".equals(selectname)) {
				params.put("name," + SearchCondition.ANYLIKE, selectname);
			}

			if (parentId != null && !"undefined".equals(parentId)) {
				if ("CH".equals(treeType)) {
					params.put("channelDistributor.id," + SearchCondition.EQUAL, parentId);
				}
			}
			// 获取当前登录用户所在的公司或供应商
			if (SecurityUtil.getCurrentEmpId() != null) {
				Employee employee = baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null && employee.getChannelDistributor() != null) {
					// 如果登录的员工属于经销商或门店
					params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
				}
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
			//处理搜索条件
			pager = baseHibernateService.findPagerByHqlConditions(pager, SalesOrder.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				salesOrder = baseHibernateService.findEntityById(SalesOrder.class, id);
			} else {
				salesOrder = new SalesOrder();
				salesOrder.setIsTemp("1");
				if (StringUtils.isNotEmpty(parentId) && !"undefined".equals(parentId)) {
					ChannelDistributor channelDistributor = baseHibernateService.findEntityById(ChannelDistributor.class, parentId);
					salesOrder.setChannelDistributor(channelDistributor);
				}
				salesOrder.setCode(VixUUID.createCodeByNumber(10));
				salesOrder.setBizType("mdyh");
				Employee employee = getEmployee();
				if (employee != null) {
					salesOrder.setCreator(employee.getName());
				} else {
					salesOrder.setCreator(SecurityUtil.getCurrentUserName());
				}
				salesOrder = baseHibernateService.merge(salesOrder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 没处理完
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != salesOrder.getId() && !"".equals(salesOrder.getId())) {
				isSave = false;
			}
			salesOrder.setIsTemp("");
			//处理中文索引
			String py = ChnToPinYin.getPYString(salesOrder.getName());
			salesOrder.setChineseCharacter(py.toUpperCase());
			//处理修改留痕
			billMarkProcessController.processMark(salesOrder, updateField);
			salesOrder = baseHibernateService.merge(salesOrder);
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
			SalesOrder pb = baseHibernateService.findEntityById(SalesOrder.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}
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
						delIds.add(new String(idStr));
					}
				}
				baseHibernateService.deleteById(SalesOrder.class, id);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdateSaleOrderItem() {
		boolean isSave = true;
		try {
			if (null != saleOrderItem.getId() && !"".equals(saleOrderItem.getId())) {
				isSave = false;
			}

			if (saleOrderItem.getAmount() != null && saleOrderItem.getPrice() != null) {
				saleOrderItem.setNetTotal(saleOrderItem.getAmount() * saleOrderItem.getPrice());
			}

			baseHibernateService.merge(saleOrderItem);
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

	/** 获取json数据 */
	public void getSaleOrderItemJson() {
		try {
			String json = "";
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				SalesOrder salesOrder = baseHibernateService.findEntityById(SalesOrder.class, id);
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

	/** 处理删除操作 */
	public String deleteSaleOrderItemById() {
		try {
			SaleOrderItem saleOrderItem = baseHibernateService.findEntityById(SaleOrderItem.class, id);
			if (null != saleOrderItem) {
				baseHibernateService.deleteByEntity(saleOrderItem);
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

	/**
	 * 弹出库存信息
	 * 
	 * @return
	 */
	public String goInventoryList() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				saleOrderItem = baseHibernateService.findEntityById(SaleOrderItem.class, id);
			} else {
				saleOrderItem = new SaleOrderItem();
				if (salesOrderId != null && !"".equals(salesOrderId)) {
					SalesOrder saleOrder = baseHibernateService.findEntityById(SalesOrder.class, salesOrderId);
					saleOrderItem.setSalesOrder(saleOrder);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goInventoryList";
	}

	public String goInventoryListContent() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = getPager();
			//获取所选经销商信息
			ChannelDistributor channelDistributor = null;
			if (null != parentId && !"".equals(parentId) && !"undefined".equals(parentId)) {
				channelDistributor = baseHibernateService.findEntityById(ChannelDistributor.class, parentId);
			} else {
				if (SecurityUtil.getCurrentEmpId() != null) {
					Employee employee = baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
					if (employee != null && employee.getChannelDistributor() != null) {
						channelDistributor = baseHibernateService.findEntityById(ChannelDistributor.class, employee.getChannelDistributor().getId());
					}
				}
			}
			if (channelDistributor != null && channelDistributor.getMdmItem() != null) {
				Set<Item> itemSet = channelDistributor.getMdmItem();
				String itemId = "";
				for (Item item : itemSet) {
					itemId += "," + item.getId();
				}
				params.put("id," + SearchCondition.IN, itemId);
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, Item.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "goInventoryListContent";
	}

	/**
	 * @return the treeType
	 */
	public String getTreeType() {
		return treeType;
	}

	/**
	 * @param treeType
	 *            the treeType to set
	 */
	public void setTreeType(String treeType) {
		this.treeType = treeType;
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getSalesOrderId() {
		return salesOrderId;
	}

	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
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

	public SaleOrderItem getSaleOrderItem() {
		return saleOrderItem;
	}

	public void setSaleOrderItem(SaleOrderItem saleOrderItem) {
		this.saleOrderItem = saleOrderItem;
	}

	public List<SaleOrderItem> getSaleOrderItemList() {
		return saleOrderItemList;
	}

	public void setSaleOrderItemList(List<SaleOrderItem> saleOrderItemList) {
		this.saleOrderItemList = saleOrderItemList;
	}

}
