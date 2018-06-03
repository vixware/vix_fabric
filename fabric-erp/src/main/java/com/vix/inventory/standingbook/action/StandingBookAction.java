package com.vix.inventory.standingbook.action;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.JSonUtils;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.standingbook.controller.StandingBookController;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.warehouse.entity.InvWarehouse;

@Controller
@Scope("prototype")
public class StandingBookAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(StandingBookAction.class);
	@Autowired
	private StandingBookController standingBookController;
	private String parentId;
	private String treeType;
	private String pageNo;

	/**
	 * 存货档案清单(现存量汇总表)
	 */
	private InventoryCurrentStock inventoryCurrentStock;
	private List<InventoryCurrentStock> inventoryCurrentStockList;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isQualfied," + SearchCondition.EQUAL, 1);
			Employee employee = getEmployee();
			if (employee != null && employee.getChannelDistributor() != null) {
				// 如果登录的员工属于经销商或门店
				params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
			}
			inventoryCurrentStockList = standingBookController.doListInventoryCurrentStockList(params);
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
			String name = getDecodeRequestParameter("name");
			if (name != null && !"".equals(name)) {
				params.put("itemname," + SearchCondition.ANYLIKE, name.trim());
			}
			String itemname = getDecodeRequestParameter("itemname");
			if (itemname != null && !"".equals(itemname)) {
				params.put("itemname," + SearchCondition.ANYLIKE, itemname.trim());
			}
			String itemcode = getRequestParameter("itemcode");
			if (itemcode != null && !"".equals(itemcode)) {
				params.put("itemcode," + SearchCondition.EQUAL, itemcode.trim());
			}
			if (SecurityUtil.getCurrentEmpId() != null) {
				Employee employee = baseHibernateService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null && employee.getChannelDistributor() != null) {
					// 如果登录的员工属于经销商或门店
					params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
				}
			}
			// 倒序排序
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			params.put("isQualfied," + SearchCondition.EQUAL, 1);
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			/* 通过id获取仓库的编码,通过仓库编码过滤商品信息 */
			InvWarehouse warehouse = null;
			if ("W".equals(treeType)) {
				warehouse = baseHibernateService.findEntityById(InvWarehouse.class, parentId);
				if (warehouse != null) {
					params.put("invWarehouse.id," + SearchCondition.EQUAL, warehouse.getId());
				}
			}
			if (warehouse != null) {
				// 需要调整
				if (warehouse.getSubInvWarehouse().size() > 0) {
					Map<String, Object> map = getParams();
					map.put("parentId," + SearchCondition.EQUAL, parentId);
					pager = standingBookController.doListInventoryCurrentStockViewList(map, pager);
				} else {
					pager = standingBookController.doListInventoryCurrentStock(params, pager);
				}
			} else {
				pager = standingBookController.doListInventoryCurrentStock(params, pager);
			}
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public void importFile() {
		Map<String, String> msgMap = new HashMap<String, String>();
		FileInputStream fis = null;
		try {
			if (fileToUpload == null) {
				msgMap.put("success", "0");
				msgMap.put("error", "没有选择文件!");
			} else {
				standingBookController.importShopEcProductPrice(fileToUpload, fileToUploadFileName);
				msgMap.put("success", "1");
				msgMap.put("msg", "导入成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			msgMap.put("success", "0");
			msgMap.put("error", e.getMessage());
		} finally {
			if (fis != null) {
				IOUtils.closeQuietly(fis);
			}
		}
		String reMsg = JSonUtils.makeMapToJson(msgMap);
		renderHtml(reMsg);
	}

	public String goSearch() {
		return "goSearch";
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

	public InventoryCurrentStock getInventoryCurrentStock() {
		return inventoryCurrentStock;
	}

	public void setInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) {
		this.inventoryCurrentStock = inventoryCurrentStock;
	}

	public List<InventoryCurrentStock> getInventoryCurrentStockList() {
		return inventoryCurrentStockList;
	}

	public void setInventoryCurrentStockList(List<InventoryCurrentStock> inventoryCurrentStockList) {
		this.inventoryCurrentStockList = inventoryCurrentStockList;
	}

}
