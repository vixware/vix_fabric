package com.vix.nvix.warehouse.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.ebusiness.expressFeeRules.entity.Provinces;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.inbound.entity.StockRecordLines;
import com.vix.inventory.inbound.entity.StockRecords;
import com.vix.inventory.takestock.entity.StockTaking;
import com.vix.inventory.takestock.entity.StockTakingItem;
import com.vix.inventory.warehouse.entity.InvShelf;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.inventory.warehouse.entity.InvWarehousezone;
import com.vix.inventory.warehouse.service.IWarehouseService;
import com.vix.inventory.warehouse.vo.InvUnit;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class VixntWarehouseAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private String parentId;
	private String treeType;
	private String shelfId;
	private String wareHouseId;
	private String ids;
	@Autowired
	private IWarehouseService warehouseService;
	private InvWarehouse invWarehouse;
	private List<InvWarehouse> invWarehouseList;
	private InvShelf invShelf;
	private List<InvShelf> invShelfList;
	private List<Provinces> provincesList;

	public void goInvWarehouseList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (StringUtils.isEmpty(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			params.put("type," + SearchCondition.EQUAL, "1");
			String name = getDecodeRequestParameter("selectName");
			if (StringUtils.isNotEmpty(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, InvWarehouse.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至仓库修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				invWarehouse = warehouseService.findEntityById(InvWarehouse.class, id);
			} else {
				invWarehouse = new InvWarehouse();
				invWarehouse.setCode(VixUUID.createCode(10));
				invWarehouse.setIsDefault("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 跳转至货架修改页面 */
	public String goSaveOrUpdateShelf() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				invShelf = vixntBaseService.findEntityById(InvShelf.class, id);
			} else {
				invShelf = new InvShelf();
				invShelf.setCode(VixUUID.createCode(5));
				if (StringUtils.isNotEmpty(parentId)) {
					InvWarehouse i = vixntBaseService.findEntityById(InvWarehouse.class, parentId);
					if (i != null) {
						invShelf.setInvWarehouse(i);
					}
				}
				invShelf.setIsDefault("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateShelf";
	}

	/** 仓库操作 */
	public void saveOrUpdateWarehouse() {
		boolean isSave = true;
		try {
			if (invWarehouse != null && StringUtils.isNotEmpty(invWarehouse.getId())) {
				isSave = false;
			}
			if (invWarehouse.getEmployee() != null && invWarehouse.getEmployee().getId() != null && !"".equals(invWarehouse.getEmployee().getId())) {

			} else {
				invWarehouse.setEmployee(null);
			}

			invWarehouse.setType("1");
			initEntityBaseController.initEntityBaseAttribute(invWarehouse);
			warehouseService.merge(invWarehouse);
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

	/** 批量处理删除操作 */
	public void deleteByIds() {
		try {
			if (StringUtils.isNotEmpty(ids) && !"0".equals(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				warehouseService.batchDelete(InvShelf.class, delIds);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/** 货位操作 */
	public void saveOrUpdateShelf() {
		boolean isSave = true;
		try {
			if (invShelf != null && StringUtils.isNotEmpty(invShelf.getId())) {
				isSave = false;
			}
			// 货位
			invShelf.setType(2);
			initEntityBaseController.initEntityBaseAttribute(invShelf);
			warehouseService.merge(invShelf);
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

	public String goChooseWarehouse() {
		return "goChooseWarehouse";
	}

	public String goChooseShelf() {
		return "goChooseShelf";
	}

	/**
	 * 获取仓库树
	 */
	public void findInvWarehouseTreeToJson() {
		try {
			loadInvWarehouse(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取仓库树
	 */
	private void loadInvWarehouse(String nodeId, String nodeTreeType) {
		try {
			List<InvUnit> invUnitList = null;
			List<InvWarehouse> invWarehouseList = null;
			if (StringUtils.isNotEmpty(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					// 没有根结点 需要加载仓库信息 其实id不为空 则treetype肯定也不为空
					if (nodeTreeType.equals("W")) {
						Map<String, Object> params = new HashMap<String, Object>();
						if (SecurityUtil.getCurrentEmpId() != null) {
							Employee employee = warehouseService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
							if (employee != null && employee.getChannelDistributor() != null) {
								// 如果登录的员工属于经销商或门店
								params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
							}
						}
						invUnitList = warehouseService.findInvWarehouseTreeToList(nodeTreeType, nodeId, params);
					}
				}
			} else {
				// id为空 则类型也为空
				Map<String, Object> params = new HashMap<String, Object>();
				if (SecurityUtil.getCurrentEmpId() != null) {
					Employee employee = warehouseService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
					if (employee != null && employee.getChannelDistributor() != null) {
						// 如果登录的员工属于经销商或门店
						params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
					}
				}
				invWarehouseList = warehouseService.findAllSubEntity(InvWarehouse.class, "parentInvWarehouse.id", null, params);
			}

			if (invUnitList == null) {
				invUnitList = new LinkedList<InvUnit>();
			}
			if (invWarehouseList != null) {
				for (InvWarehouse invWarehouse : invWarehouseList) {
					InvUnit ou1 = new InvUnit(invWarehouse.getId(), "W", invWarehouse.getName(), invWarehouse.getCode());
					if (invWarehouse.getSubInvWarehouse().size() > 0) {
						List<InvUnit> ou2List = new LinkedList<InvUnit>();
						for (InvWarehouse childOrg : invWarehouse.getSubInvWarehouse()) {
							ou2List.add(new InvUnit(childOrg.getId(), "W", childOrg.getName(), childOrg.getCode()));
						}
						ou1.setSubInvUnits(ou2List);
					}
					invUnitList.add(ou1);
				}
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = invUnitList.size();
			for (int i = 0; i < count; i++) {
				InvUnit org = invUnitList.get(i);
				if (org.getSubInvUnits() != null && org.getSubInvUnits().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
					strSb.append("\",code:\"");
					strSb.append(org.getInvCode());
					strSb.append("\",name:\"");
					strSb.append(org.getInvName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
					strSb.append("\",code:\"");
					strSb.append(org.getInvCode());
					strSb.append("\",name:\"");
					strSb.append(org.getInvName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: findInvTreeToJson @Description: 加载仓库的混合树 @param 设定文件 @return void
	 *         返回类型 @throws
	 */
	public void findInvTreeToJson() {
		try {
			loadInv(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param nodeId
	 * @param nodeTreeType
	 */
	private void loadInv(String nodeId, String nodeTreeType) {
		try {
			List<InvUnit> invUnitList = null;
			List<InvWarehouse> invWarehouseList = null;
			if (null != nodeId && !"".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					if (nodeTreeType.equals("W") || nodeTreeType.equals("Z") || nodeTreeType.equals("S")) {
						// id为空 则类型也为空
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("type," + SearchCondition.EQUAL, "1");
						invUnitList = warehouseService.findInvTreeToList(nodeTreeType, nodeId, params);
					}
				}
			} else {
				// id为空 则类型也为空
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("type," + SearchCondition.EQUAL, "1");
				invWarehouseList = warehouseService.findAllSubEntity(InvWarehouse.class, "parentInvWarehouse.id", null, params);
			}
			if (invUnitList == null) {
				invUnitList = new LinkedList<InvUnit>();
			}
			if (invWarehouseList != null) {
				for (InvWarehouse invWarehouse : invWarehouseList) {
					InvUnit ou1 = new InvUnit(invWarehouse.getId(), "W", invWarehouse.getName(), invWarehouse.getCode());
					if (invWarehouse.getSubInvWarehouse().size() > 0) {
						List<InvUnit> subUnitList = new LinkedList<InvUnit>();
						for (InvWarehouse ou : invWarehouse.getSubInvWarehouse()) {
							subUnitList.add(new InvUnit(ou.getId(), "W", ou.getName(), ou.getCode()));
						}
						ou1.setSubInvUnits(subUnitList);
					}
					if (invWarehouse.getInvWarehousezones().size() > 0) {
						List<InvUnit> ou2List = new LinkedList<InvUnit>();
						for (InvWarehousezone childOrg : invWarehouse.getInvWarehousezones()) {
							ou2List.add(new InvUnit(childOrg.getId(), "Z", childOrg.getName(), childOrg.getCode()));
						}
						ou1.setSubInvUnits(ou2List);
					}
					if (invWarehouse.getInvShelfs().size() > 0) {
						List<InvUnit> ou2List = new LinkedList<InvUnit>();
						for (InvShelf invShelf : invWarehouse.getInvShelfs()) {
							ou2List.add(new InvUnit(invShelf.getId(), "S", invShelf.getName(), invShelf.getCode()));
						}
						ou1.setSubInvUnits(ou2List);
					}
					invUnitList.add(ou1);
				}
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = invUnitList.size();
			for (int i = 0; i < count; i++) {
				InvUnit org = invUnitList.get(i);
				if (org.getSubInvUnits() != null && org.getSubInvUnits().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
					strSb.append("\",code:\"");
					strSb.append(org.getInvCode());
					strSb.append("\",name:\"");
					strSb.append(org.getInvName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
					strSb.append("\",code:\"");
					strSb.append(org.getInvCode());
					strSb.append("\",name:\"");
					strSb.append(org.getInvName());
					strSb.append("\",open:false,isParent:false}");
				}
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadWarehouseByShelf() {
		try {
			StringBuilder strSb = new StringBuilder();
			InvShelf invShelf = warehouseService.findEntityById(InvShelf.class, shelfId);
			InvWarehouse invWarehouse = invShelf.getInvWarehouse();
			strSb.append(invWarehouse.getId()).append(",").append(invWarehouse.getName());
			renderText(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteById() {
		try {
			InvWarehouse cd = vixntBaseService.findEntityById(InvWarehouse.class, id);
			if (null != cd) {
				Map<String, Object> stockrecordsparams = getParams();
				stockrecordsparams.put("invWarehouse.id," + SearchCondition.EQUAL, id);
				List<StockRecords> stockRecordsList = this.vixntBaseService.findAllByConditions(StockRecords.class, stockrecordsparams);
				if (stockRecordsList != null && stockRecordsList.size() > 0) {
					for (StockRecords stockRecords : stockRecordsList) {
						Set<StockRecordLines> stockRecordLinesList = stockRecords.getSubStockRecordLines();
						if (stockRecordLinesList != null && stockRecordLinesList.size() > 0) {
							for (StockRecordLines stockRecordLines : stockRecordLinesList) {
								vixntBaseService.batchDeleteBySql("DELETE from inv_stockrecordlines where id ='" + stockRecordLines.getId() + "'", null);
							}
						}
						vixntBaseService.batchDeleteBySql("DELETE from inv_stockrecords where id ='" + stockRecords.getId() + "'", null);
					}
				}
				Map<String, Object> params = getParams();
				params.put("invWarehouse.id," + SearchCondition.EQUAL, id);
				List<StockTaking> stockTakingList = this.vixntBaseService.findAllByConditions(StockTaking.class, params);
				if (stockTakingList != null && stockTakingList.size() > 0) {
					for (StockTaking stockTaking : stockTakingList) {
						Map<String, Object> p = getParams();
						p.put("stockTaking.id," + SearchCondition.EQUAL, stockTaking.getId());
						List<StockTakingItem> stockTakingItemList = this.vixntBaseService.findAllByConditions(StockTakingItem.class, p);
						if (stockTakingItemList != null && stockTakingItemList.size() > 0) {
							for (StockTakingItem stockTakingItem : stockTakingItemList) {
								vixntBaseService.batchDeleteBySql("DELETE from inv_stocktakingitem where id ='" + stockTakingItem.getId() + "'", null);
							}
						}
						vixntBaseService.batchDeleteBySql("DELETE from inv_stocktaking where id ='" + stockTaking.getId() + "'", null);
					}
				}
				vixntBaseService.batchDeleteBySql("DELETE from inv_inventory where INVWAREHOUSE_ID ='" + id + "'", null);
				vixntBaseService.batchDeleteBySql("DELETE from inv_shelf where inv_warehouse_id ='" + id + "'", null);
				vixntBaseService.deleteByEntity(cd);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/**
	 * 选择人员
	 * 
	 * @return
	 */
	public String goEmployeeChoose() {
		return "goEmployeeChoose";
	}

	/**
	 * 人员列表
	 */
	public void goEmployeeList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("empType," + SearchCondition.EQUAL, "S");
			String employeeName = getDecodeRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(employeeName)) {
				params.put("name," + SearchCondition.ANYLIKE, employeeName);
			}
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), Employee.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goInvShelfList() {
		return "goInvShelfList";
	}

	public void goInvShelfSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("type," + SearchCondition.EQUAL, 2);
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String name = getDecodeRequestParameter("shelfName");
			if (StringUtils.isNotEmpty(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			if (StringUtils.isNotEmpty(wareHouseId)) {
				params.put("invWarehouse.id," + SearchCondition.EQUAL, wareHouseId);
				pager = vixntBaseService.findPagerByHqlConditions(pager, InvShelf.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doListInvShelfById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				InvShelf cd = vixntBaseService.findEntityById(InvShelf.class, id);
				if (null != cd) {
					vixntBaseService.deleteByEntity(cd);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	public void getSingleList(){
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			String searchName = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("name," + SearchCondition.ANYLIKE, searchName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, InvWarehouse.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getShelfId() {
		return shelfId;
	}

	public void setShelfId(String shelfId) {
		this.shelfId = shelfId;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public InvWarehouse getInvWarehouse() {
		return invWarehouse;
	}

	public void setInvWarehouse(InvWarehouse invWarehouse) {
		this.invWarehouse = invWarehouse;
	}

	public List<InvWarehouse> getInvWarehouseList() {
		return invWarehouseList;
	}

	public void setInvWarehouseList(List<InvWarehouse> invWarehouseList) {
		this.invWarehouseList = invWarehouseList;
	}

	public List<Provinces> getProvincesList() {
		return provincesList;
	}

	public void setProvincesList(List<Provinces> provincesList) {
		this.provincesList = provincesList;
	}

	public InvShelf getInvShelf() {
		return invShelf;
	}

	public void setInvShelf(InvShelf invShelf) {
		this.invShelf = invShelf;
	}

	public List<InvShelf> getInvShelfList() {
		return invShelfList;
	}

	public void setInvShelfList(List<InvShelf> invShelfList) {
		this.invShelfList = invShelfList;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(String wareHouseId) {
		this.wareHouseId = wareHouseId;
	}

}