package com.vix.nvix.drp.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.vo.OrgUnit;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.inbound.entity.StockRecordLines;
import com.vix.inventory.inbound.entity.StockRecords;
import com.vix.inventory.takestock.entity.StockTaking;
import com.vix.inventory.takestock.entity.StockTakingItem;
import com.vix.inventory.warehouse.entity.InvShelf;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class VixntDrpWarehouseAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private String parentId;
	private String wareHouseId;
	private String treeType;
	private InvWarehouse invWarehouse;
	private InvShelf invShelf;
	private List<InvWarehouse> invWarehouseList;


	/** 获取列表数据 */
	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();

			Pager pager = getPager();
			// 排序
			if (StringUtils.isEmpty(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			params.put("type," + SearchCondition.EQUAL, "2");

			String name = getDecodeRequestParameter("selectName");
			if (StringUtils.isNotEmpty(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}

			// 获取当前登录用户所在的公司或供应商
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
					// 如果登录的员工属于经销商或门店
					params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, InvWarehouse.class, params);
				} else {
					ChannelDistributor channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
					if (channelDistributor != null) {
						// 如果登录的员工属于经销商或门店
						params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
						pager = vixntBaseService.findPagerByHqlConditions(pager, InvWarehouse.class, params);
					}
				}
			}
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

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				invWarehouse = vixntBaseService.findEntityById(InvWarehouse.class, id);
			} else {
				invWarehouse = new InvWarehouse();
				invWarehouse.setCode(VixUUID.createCode(10));
				Employee employee = getEmployee();
				if (employee != null) {
					if (employee.getChannelDistributor() != null) {
						// 如果登录的员工属于经销商或门店
						invWarehouse.setChannelDistributor(employee.getChannelDistributor());
					} else {
						ChannelDistributor channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
						if (channelDistributor != null) {
							invWarehouse.setChannelDistributor(channelDistributor);
						}
					}
				}
				invWarehouse.setIsDefault("1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(invWarehouse.getId())) {
				isSave = false;
			}
			invWarehouse.setType("2");
			initEntityBaseController.initEntityBaseAttribute(invWarehouse);
			vixntBaseService.merge(invWarehouse);
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

	public void saveOrUpdateInvShelf() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(invShelf.getId())) {
				isSave = false;
			}
			// 货位
			invShelf.setType(2);
			initEntityBaseController.initEntityBaseAttribute(invShelf);
			vixntBaseService.merge(invShelf);
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

	/** 处理删除操作 */
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

	public void doListInvShelfById() {
		try {
			InvShelf cd = vixntBaseService.findEntityById(InvShelf.class, id);
			if (null != cd) {
				vixntBaseService.deleteByEntity(cd);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public void findOrgAndUnitTreeToJson() {
		try {
			loadOrg(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void loadOrg(String nodeId, String nodeTreeType) {
		try {
			List<OrgUnit> orgUnitList = null;
			List<Organization> orgList = null;
			if (null != nodeId && !"".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					// 没有根结点 需要加载公司信息 其实id不为空 则treetype肯定也不为空
					if (nodeTreeType.equals("C") || nodeTreeType.equals("O") || nodeTreeType.equals("CH")) {
						// 加载公司信息和部门信息
						orgUnitList = vixntBaseService.findOrgAndUnitTreeList(nodeTreeType, nodeId, null);
					}
				}
			} else {
				// id为空 则类型也为空
				// 加载公司信息
				Map<String, Object> params = getParams();
				orgList = vixntBaseService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}

			if (orgUnitList == null) {
				orgUnitList = new LinkedList<OrgUnit>();
			}

			if (orgList != null) {
				for (Organization orgTmp : orgList) {
					OrgUnit ou1 = new OrgUnit(orgTmp.getId(), "C", orgTmp.getOrgName(), orgTmp.getCompanyCode());
					if (orgTmp.getSubOrganizations().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (Organization childOrg : orgTmp.getSubOrganizations()) {
							ou2List.add(new OrgUnit(childOrg.getId(), "C", childOrg.getOrgName(), childOrg.getCompanyCode()));
						}
						ou1.setSubOrgUnits(ou2List);
					}
					if (orgTmp.getChannelDistributors().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (ChannelDistributor channelDistributor : orgTmp.getChannelDistributors()) {
							ou2List.add(new OrgUnit(channelDistributor.getId(), "CH", channelDistributor.getName(), channelDistributor.getCode()));
						}
						ou1.setSubOrgUnits(ou2List);
					}
					orgUnitList.add(ou1);
				}
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = orgUnitList.size();
			for (int i = 0; i < count; i++) {
				OrgUnit org = orgUnitList.get(i);
				if (org.getSubOrgUnits() != null && org.getSubOrgUnits().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
					strSb.append("\",code:\"");
					strSb.append(org.getOrgCode());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
					strSb.append("\",code:\"");
					strSb.append(org.getOrgCode());
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
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
			Pager pager = getPager();
			// 排序
			if (StringUtils.isNotEmpty(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			params.put("empType," + SearchCondition.EQUAL, "ST");
			String employeeName = getDecodeRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(employeeName)) {
				params.put("name," + SearchCondition.ANYLIKE, employeeName.trim());
			}
			Employee employee = super.getEmployee();
			if (employee != null) {
				ChannelDistributor channelDistributor = vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
				if (channelDistributor != null) {
					params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
					pager = vixntBaseService.findPagerByHqlConditions(pager, Employee.class, params);
				} else {
					channelDistributor = employee.getChannelDistributor();
					if (channelDistributor != null) {
						params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
						pager = vixntBaseService.findPagerByHqlConditions(pager, Employee.class, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the id
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	@Override
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public InvShelf getInvShelf() {
		return invShelf;
	}

	public void setInvShelf(InvShelf invShelf) {
		this.invShelf = invShelf;
	}

	public String getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(String wareHouseId) {
		this.wareHouseId = wareHouseId;
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
}
