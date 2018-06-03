package com.vix.inventory.warehouse.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.ebusiness.expressFeeRules.entity.Provinces;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.warehouse.entity.InvShelf;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.inventory.warehouse.entity.InvWarehousezone;
import com.vix.inventory.warehouse.service.IWarehouseService;
import com.vix.inventory.warehouse.vo.InvUnit;

@Controller
@Scope("prototype")
public class WarehouseAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private String parentId;
	private String treeType;
	private String provincesId;
	private String shelfId;
	private String ids;
	@Autowired
	private IWarehouseService warehouseService;
	/**
	 * 仓库
	 */
	private InvWarehouse invWarehouse;
	private List<InvWarehouse> invWarehouseList;
	/**
	 * 货位/货架
	 */
	private InvShelf invShelf;
	private List<InvShelf> invShelfList;

	/**
	 * 货区
	 */
	private InvWarehousezone invWarehousezone;
	private List<InvWarehousezone> invWarehousezoneList;
	/**
	 * 省份列表
	 */
	private List<Provinces> provincesList;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			invShelfList = warehouseService.findAllByConditions(InvShelf.class, params);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			String name = getDecodeRequestParameter("name");
			if (null != name && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name.trim());
			}
			String selectCode = getRequestParameter("selectCode");
			if (null != selectCode && !"".equals(selectCode)) {
				params.put("code," + SearchCondition.EQUAL, selectCode.trim());
			}
			String selectName = getDecodeRequestParameter("selectName");
			if (null != selectName && !"".equals(selectName)) {
				params.put("name," + SearchCondition.ANYLIKE, selectName.trim());
			}
			if (SecurityUtil.getCurrentEmpId() != null) {
				Employee employee = warehouseService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
				if (employee != null && employee.getChannelDistributor() != null) {
					// 如果登录的员工属于经销商或门店
					params.put("invWarehouse.channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
				}
			}
			params.put("type," + SearchCondition.EQUAL, 2);
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			/* 根据节点类型 列表页显示相对应的数据 */
			if (treeType != null) {
				if ("W".equals(treeType)) {
					params.put("invWarehouse.id" + "," + SearchCondition.EQUAL, parentId);
				} else if ("Z".equals(treeType)) {
					params.put("invWarehousezone.id" + "," + SearchCondition.EQUAL, parentId);
				} else if ("S".equals(treeType)) {
					params.put("parentInvShelf.id" + "," + SearchCondition.EQUAL, parentId);
				}
			}
			pager = warehouseService.findPagerByHqlConditions(getPager(), InvShelf.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至仓库修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				invWarehouse = warehouseService.findEntityById(InvWarehouse.class, id);

			} else {
				invWarehouse = new InvWarehouse();
				invWarehouse.setCode(VixUUID.createCode(10));
				if ("W".equals(treeType)) {
					if (StringUtils.isNotEmpty(parentId)) {
						InvWarehouse i = warehouseService.findEntityById(InvWarehouse.class, parentId);
						invWarehouse.setParentInvWarehouse(i);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goSaveOrUpdateWarehouse() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				invWarehouse = warehouseService.findEntityById(InvWarehouse.class, id);
			} else {
				invWarehouse = new InvWarehouse();
				invWarehouse.setCode(VixUUID.createCode(10));
				if ("W".equals(treeType)) {
					if (StringUtils.isNotEmpty(parentId)) {
						InvWarehouse i = warehouseService.findEntityById(InvWarehouse.class, parentId);
						invWarehouse.setParentInvWarehouse(i);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateWarehouse";
	}

	/** 跳转至货架修改页面 */
	public String goSaveOrUpdateShelf() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				invShelf = warehouseService.findEntityById(InvShelf.class, id);
			} else {
				invShelf = new InvShelf();
				invShelf.setCode(VixUUID.createCode(5));
				if ("Z".equals(treeType)) {
					if (StringUtils.isNotEmpty(parentId)) {
						invWarehousezone = warehouseService.findEntityById(InvWarehousezone.class, parentId);
						invShelf.setInvWarehousezone(invWarehousezone);
					} else {
						invShelf.setInvWarehousezone(null);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateShelf";
	}

	/** 跳转至货位修改页面 */
	public String goSaveOrUpdateCargoSpace() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				invShelf = warehouseService.findEntityById(InvShelf.class, id);
			} else {
				InvShelf invShelf1 = null;
				invShelf = new InvShelf();
				invShelf.setCode(VixUUID.createCode(5));
				if (StringUtils.isNotEmpty(parentId)) {
					if ("S".equals(treeType)) {
						invShelf1 = warehouseService.findEntityById(InvShelf.class, parentId);
						if (invShelf1 != null) {
							invShelf.setParentInvShelf(invShelf1);
						} else {
							invShelf.setParentInvShelf(null);
						}

					} else if ("W".equals(treeType)) {
						InvWarehouse i = warehouseService.findEntityById(InvWarehouse.class, parentId);
						if (i != null) {
							invShelf.setInvWarehouse(i);
						} else {
							invShelf.setInvWarehouse(null);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateCargoSpace";
	}

	/** 跳转至货区修改页面 */
	public String goSaveOrUpdateGoodsArea() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				invWarehousezone = warehouseService.findEntityById(InvWarehousezone.class, id);
			} else {
				invWarehousezone = new InvWarehousezone();
				invWarehousezone.setCode(VixUUID.createCode(8));
				if (StringUtils.isNotEmpty(parentId)) {
					invWarehouse = warehouseService.findEntityById(InvWarehouse.class, parentId);
					if (invWarehouse != null) {
						invWarehousezone.setInvWarehouse(invWarehouse);
					} else {
						invWarehousezone.setInvWarehouse(null);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateGoodsArea";
	}

	/** 仓库操作 */
	public String saveOrUpdateWarehouse() {
		boolean isSave = true;
		try {
			if (invWarehouse != null && StringUtils.isNotEmpty(invWarehouse.getId()) && !"0".equals(invWarehouse.getId())) {
				isSave = false;
			}
			String parentInvWarehouseId = getRequestParameter("parentInvWarehouseId");
			if (StringUtils.isNotEmpty(parentInvWarehouseId) && !"0".equals(parentInvWarehouseId)) {
				InvWarehouse invhouse = warehouseService.findEntityById(InvWarehouse.class, parentInvWarehouseId);
				if (invhouse != null) {
					invWarehouse.setParentInvWarehouse(invhouse);
				} else {
					invWarehouse.setParentInvWarehouse(null);
				}
			}
			if (invWarehouse.getEmployee() != null && invWarehouse.getEmployee().getId() != null && !"".equals(invWarehouse.getEmployee().getId())) {

			} else {
				invWarehouse.setEmployee(null);
			}

			if (StringUtils.isNotEmpty(provincesId)) {
				//处理省份开始
				Map<String, Object> params = getParams();
				params.put("id," + SearchCondition.IN, provincesId);
				List<Provinces> provincesList = warehouseService.findAllByConditions(Provinces.class, params);
				if (provincesList != null && provincesList.size() > 0) {
					Set<Provinces> subProvincess = new HashSet<Provinces>();
					for (Provinces provinces : provincesList) {
						subProvincess.add(provinces);
					}
					invWarehouse.setSubProvincess(subProvincess);
				}
				//处理省份结束
			}
			invWarehouse.setType("1");
			//处理修改留痕
			billMarkProcessController.processMark(invWarehouse, updateField);
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
		return UPDATE;
	}

	/** 批量处理删除操作 */
	public String deleteByIds() {
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
		return UPDATE;
	}

	/** 货区操作 */
	public String saveOrUpdateGoodsArea() {
		boolean isSave = true;
		try {
			if (invWarehousezone != null && StringUtils.isNotEmpty(invWarehousezone.getId()) && !"0".equals(invWarehousezone.getId())) {
				isSave = false;
			}
			//处理修改留痕
			billMarkProcessController.processMark(invWarehousezone, updateField);
			warehouseService.merge(invWarehousezone);
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

	/** 货架操作 */
	public String saveOrUpdateShelf() {
		boolean isSave = true;
		try {
			if (invShelf != null) {
				if (StringUtils.isNotEmpty(invShelf.getId()) && !"0".equals(invShelf.getId())) {//if (null != invShelf.getId()) {
					isSave = false;
				}
				if (invShelf.getInvWarehousezone() != null && StringUtils.isNotEmpty(invShelf.getInvWarehousezone().getId())) {
				} else {
					invShelf.setInvWarehousezone(null);
				}
				if (invShelf.getParentInvShelf() != null && StringUtils.isNotEmpty(invShelf.getParentInvShelf().getId())) {
				} else {
					invShelf.setParentInvShelf(null);
				}
				//处理修改留痕
				billMarkProcessController.processMark(invShelf, updateField);
				warehouseService.merge(invShelf);
				if (isSave) {
					renderText(SAVE_SUCCESS);
				} else {
					renderText(UPDATE_SUCCESS);
				}
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

	/** 货位操作 */
	public String saveOrUpdateCargoSpace() {
		boolean isSave = true;
		try {
			if (invShelf != null && StringUtils.isNotEmpty(invShelf.getId()) && !"0".equals(invShelf.getId())) {
				isSave = false;
			}
			if (invShelf.getParentInvShelf().getId() == null || "".equals(invShelf.getParentInvShelf().getId())) {
				invShelf.setParentInvShelf(null);
			}
			if (invShelf.getInvWarehousezone() == null || invShelf.getInvWarehousezone().getId() == null || "".equals(invShelf.getInvWarehousezone().getId())) {
			} else {
				invShelf.setInvWarehousezone(null);
			}
			//处理修改留痕
			billMarkProcessController.processMark(invShelf, updateField);
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
		return UPDATE;
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

						if (SecurityUtil.getCurrentEmpId() != null) {
							Employee employee = warehouseService.findEntityById(Employee.class, SecurityUtil.getCurrentEmpId());
							if (employee != null && employee.getChannelDistributor() != null) {
								// 如果登录的员工属于经销商或门店
								params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
							}
						}

						invUnitList = warehouseService.findInvTreeToList(nodeTreeType, nodeId, params);
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

	public String goChooseOrganization() {
		return "goChooseOrganization";
	}

	//选择省份
	public String goChooseProvinces() {
		try {
			Map<String, Object> params = getParams();
			provincesList = warehouseService.findAllByConditions(Provinces.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goChooseProvinces";
	}

	/** 处理单条删除操作 */
	public String deleteById() {
		try {
			InvShelf invShelf = warehouseService.findEntityById(InvShelf.class, id);
			if (null != invShelf) {
				warehouseService.deleteByEntity(invShelf);
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

	public String getProvincesId() {
		return provincesId;
	}

	public void setProvincesId(String provincesId) {
		this.provincesId = provincesId;
	}

	public List<InvShelf> getInvShelfList() {
		return invShelfList;
	}

	public void setInvShelfList(List<InvShelf> invShelfList) {
		this.invShelfList = invShelfList;
	}

	public InvWarehousezone getInvWarehousezone() {
		return invWarehousezone;
	}

	public void setInvWarehousezone(InvWarehousezone invWarehousezone) {
		this.invWarehousezone = invWarehousezone;
	}

	public List<InvWarehousezone> getInvWarehousezoneList() {
		return invWarehousezoneList;
	}

	public void setInvWarehousezoneList(List<InvWarehousezone> invWarehousezoneList) {
		this.invWarehousezoneList = invWarehousezoneList;
	}

}
