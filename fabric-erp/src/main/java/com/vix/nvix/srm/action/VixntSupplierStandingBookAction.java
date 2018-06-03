package com.vix.nvix.srm.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class VixntSupplierStandingBookAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String parentId;
	private String treeType;

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			String itemname = getDecodeRequestParameter("itemname");
			if (itemname != null && !"".equals(itemname)) {
				params.put("itemname," + SearchCondition.ANYLIKE, itemname.trim());
			}
			String itemcode = getRequestParameter("itemcode");
			if (StringUtils.isNotEmpty(itemcode)) {
				params.put("itemcode," + SearchCondition.ANYLIKE, itemcode.trim());
			}
			String skuCode = getRequestParameter("skuCode");
			if (StringUtils.isNotEmpty(skuCode)) {
				params.put("skuCode," + SearchCondition.ANYLIKE, skuCode.trim());
			}
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			params.put("isQualfied," + SearchCondition.EQUAL, 1);
			Employee employee = getEmployee();
			Supplier supplier = null;
			if (employee != null) {
				if (employee.getSupplier() != null) {
					supplier = employee.getSupplier();
				} else {
					supplier = vixntBaseService.findEntityByAttribute(Supplier.class, "employee.id", employee.getId());
				}
				if (supplier != null) {
					params.put("supplier.id," + SearchCondition.EQUAL, supplier.getId());
					if (StringUtils.isNotEmpty(parentId) && StringUtils.isNotEmpty(treeType)) {
						if ("CH".equals(treeType)) {
							Map<String, Object> p = getParams();
							p.put("channelDistributor.id," + SearchCondition.EQUAL, parentId);
							List<InvWarehouse> invWarehouseList = vixntBaseService.findAllByConditions(InvWarehouse.class, p);
							if (invWarehouseList != null && invWarehouseList.size() > 0) {
								String invWarehouseIds = "";
								for (InvWarehouse invWarehouse : invWarehouseList) {
									if (invWarehouse != null) {
										invWarehouseIds += invWarehouse.getId() + ",";
									}
								}
								params.put("invWarehouse.id," + SearchCondition.IN, invWarehouseIds);
								pager = vixntBaseService.findPagerByHqlConditions(pager, InventoryCurrentStock.class, params);
							}
						}
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
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

}
