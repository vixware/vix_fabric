package com.vix.drp.tocargoplan.action;

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
import com.vix.mdm.purchase.plan.entity.PurchasePlan;
import com.vix.mdm.purchase.plan.entity.PurchasePlanItems;
import com.vix.sales.order.entity.SaleOrderItem;
import com.vix.sales.order.entity.SalesOrder;

@Controller
@Scope("prototype")
public class TocargoPlanAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private String ids;
	private String pageNo;
	private String parentId;
	private String treeType;
	private PurchasePlan purchasePlan;
	private List<PurchasePlan> purchasePlanList;

	private PurchasePlanItems purchasePlanItems;
	private List<PurchasePlanItems> purchasePlanItemsList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			if (parentId != null) {
				params.put("channelDistributor.id," + SearchCondition.EQUAL, parentId);
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
			// 获取当前登录用户所在的公司或供应商
			// 获取当前员工信息
			if (SecurityUtil.getCurrentEmpId() != null) {
				Employee employee = baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null && employee.getChannelDistributor() != null) {
					// 如果登录的员工属于经销商或门店
					params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
				}
			}
			purchasePlanList = baseHibernateService.findAllByConditions(PurchasePlan.class, params);
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
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			if (parentId != null) {
				params.put("channelDistributor.id," + SearchCondition.EQUAL, parentId);
			}
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
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
			String code = getDecodeRequestParameter("code");
			String name = getDecodeRequestParameter("name");

			if (StringUtils.isNotEmpty(getRequest().getParameter("selectName"))) {
				String selectName = new String(getRequest().getParameter("selectName").getBytes("UTF-8"));
				if (selectName != null && !"".equals(selectName)) {
					params.put("purchasePlanName," + SearchCondition.ANYLIKE, selectName.trim());
				}
			}

			if (code != null && !"".equals(code)) {
				params.put("purchasePlanCode," + SearchCondition.EQUAL, code.trim());
			}
			if (name != null && !"".equals(name)) {
				params.put("purchasePlanName," + SearchCondition.ANYLIKE, name.trim());
			}
			// 获取当前登录用户所在的公司或供应商
			// 获取当前员工信息
			if (SecurityUtil.getCurrentEmpId() != null) {
				Employee employee = baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null && employee.getChannelDistributor() != null) {
					// 如果登录的员工属于经销商或门店
					params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
				}
			}
			//处理搜索条件
			pager = baseHibernateService.findPagerByHqlConditions(pager, PurchasePlan.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchasePlan = baseHibernateService.findEntityById(PurchasePlan.class, id);
			} else {
				purchasePlan = new PurchasePlan();
				purchasePlan.setIsTemp("1");

				if (StringUtils.isNotEmpty(parentId) && StringUtils.isNotEmpty(treeType) && !"undefined".equals(parentId) && !"undefined".equals(treeType)) {
					if ("CH".equals(treeType)) {
						// 点击的树节点是分销体系结构
						ChannelDistributor channelDistributor = baseHibernateService.findEntityById(ChannelDistributor.class, parentId);
						if (channelDistributor != null) {
							purchasePlan.setChannelDistributor(channelDistributor);
						}
					}
				}
				if (SecurityUtil.getCurrentEmpId() != null) {
					Employee employee = baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
					if (employee != null) {
						purchasePlan.setPlanMaker(employee.getName());
					}
				}
				purchasePlan.setPurchasePlanCode(VixUUID.createCodeByNumber(10));
				purchasePlan = baseHibernateService.merge(purchasePlan);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != purchasePlan.getId() && !"".equals(purchasePlan.getId())) {
				isSave = false;
			}
			purchasePlan.setIsTemp("");
			purchasePlan.setIsCreateSalesOrder("1");
			String py = ChnToPinYin.getPYString(purchasePlan.getPurchasePlanName());
			purchasePlan.setChineseCharacter(py);

			//处理修改留痕
			billMarkProcessController.processMark(purchasePlan, updateField);
			purchasePlan = baseHibernateService.merge(purchasePlan);
			//转化成销售订单
			if (purchasePlan != null && "1".equals(purchasePlan.getIsCreateSalesOrder())) {
				SalesOrder salesOrder = new SalesOrder();
				salesOrder.setCode(purchasePlan.getPurchasePlanCode());
				salesOrder.setName(purchasePlan.getPurchasePlanName());
				salesOrder.setIsTemp("");
				salesOrder.setIsDeleted("0");
				salesOrder.setCreateTime(purchasePlan.getCreateTime());
				salesOrder.setChannelDistributor(purchasePlan.getChannelDistributor());
				salesOrder.setCreator(purchasePlan.getPlanMaker());
				String chineseCharacter = ChnToPinYin.getPYString(purchasePlan.getPurchasePlanName());
				salesOrder.setChineseCharacter(chineseCharacter);
				initEntityBaseController.initEntityBaseAttribute(salesOrder);
				salesOrder = baseHibernateService.merge(salesOrder);
				Double amount = 0d;
				if (purchasePlan.getPurchasePlanItems() != null && purchasePlan.getPurchasePlanItems().size() > 0) {
					for (PurchasePlanItems purchasePlanItems : purchasePlan.getPurchasePlanItems()) {
						SaleOrderItem saleOrderItem = new SaleOrderItem();
						saleOrderItem.setItemCode(purchasePlanItems.getItemCode());
						saleOrderItem.setTitle(purchasePlanItems.getItemName());
						saleOrderItem.setAmount(purchasePlanItems.getAmount());
						saleOrderItem.setPrice(purchasePlanItems.getUnitcost());
						saleOrderItem.setNetTotal(purchasePlanItems.getPrice());
						saleOrderItem.setSalesOrder(salesOrder);
						initEntityBaseController.initEntityBaseAttribute(saleOrderItem);
						saleOrderItem = baseHibernateService.merge(saleOrderItem);
						amount += saleOrderItem.getNetTotal();
					}
				}
				salesOrder.setAmount(amount);
				salesOrder = baseHibernateService.merge(salesOrder);
				purchasePlan.setIsCreateSalesOrder("2");
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

	/** 处理删除操作 */
	public String deleteById() {
		try {
			PurchasePlan pb = baseHibernateService.findEntityById(PurchasePlan.class, id);
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

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdatePurchasePlanItems() {
		boolean isSave = true;
		try {
			if (null != purchasePlanItems.getId() && !"".equals(purchasePlanItems.getId())) {
				isSave = false;
			}
			if (purchasePlanItems.getAmount() != null && purchasePlanItems.getUnitcost() != null) {
				purchasePlanItems.setPrice(purchasePlanItems.getAmount() * purchasePlanItems.getUnitcost());
			}
			if (purchasePlanItems.getPurchasePlan() != null && purchasePlanItems.getPurchasePlan().getId() != null && !"".equals(purchasePlanItems.getPurchasePlan().getId())) {
			} else {
				purchasePlanItems.setPurchasePlan(null);
			}
			baseHibernateService.merge(purchasePlanItems);
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
	public void getPurchasePlanItemsJson() {
		try {
			String json = "";
			String id = getRequestParameter("id");
			if (StringUtils.isNotEmpty(id)) {
				PurchasePlan purchasePlan = baseHibernateService.findEntityById(PurchasePlan.class, id);
				if (null != purchasePlan) {
					json = convertListToJson(new ArrayList<PurchasePlanItems>(purchasePlan.getPurchasePlanItems()), purchasePlan.getPurchasePlanItems().size());
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
	public String deletePurchasePlanItemsById() {
		try {
			PurchasePlanItems purchasePlanItems = baseHibernateService.findEntityById(PurchasePlanItems.class, id);
			if (null != purchasePlanItems) {
				baseHibernateService.deleteByEntity(purchasePlanItems);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 弹出库存信息
	 * 
	 * @return
	 */
	public String goInventoryList() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				purchasePlanItems = baseHibernateService.findEntityById(PurchasePlanItems.class, id);
			} else {
				purchasePlanItems = new PurchasePlanItems();
				if (StringUtils.isNotEmpty(parentId)) {
					PurchasePlan purchasePlan = baseHibernateService.findEntityById(PurchasePlan.class, parentId);
					purchasePlanItems.setPurchasePlan(purchasePlan);
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
			if (channelDistributor != null) {
				Set<Item> itemSet = channelDistributor.getMdmItem();
				String itemId = "";
				for (Item item : itemSet) {
					itemId += "," + item.getId();
				}
				if (itemId != "") {
					params.put("id," + SearchCondition.IN, itemId);
				}
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, Item.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goInventoryListContent";
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
				baseHibernateService.batchDelete(PurchasePlan.class, delIds);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
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

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public PurchasePlan getPurchasePlan() {
		return purchasePlan;
	}

	public void setPurchasePlan(PurchasePlan purchasePlan) {
		this.purchasePlan = purchasePlan;
	}

	public List<PurchasePlan> getPurchasePlanList() {
		return purchasePlanList;
	}

	public void setPurchasePlanList(List<PurchasePlan> purchasePlanList) {
		this.purchasePlanList = purchasePlanList;
	}

	public PurchasePlanItems getPurchasePlanItems() {
		return purchasePlanItems;
	}

	public void setPurchasePlanItems(PurchasePlanItems purchasePlanItems) {
		this.purchasePlanItems = purchasePlanItems;
	}

	public List<PurchasePlanItems> getPurchasePlanItemsList() {
		return purchasePlanItemsList;
	}

	public void setPurchasePlanItemsList(List<PurchasePlanItems> purchasePlanItemsList) {
		this.purchasePlanItemsList = purchasePlanItemsList;
	}

}
