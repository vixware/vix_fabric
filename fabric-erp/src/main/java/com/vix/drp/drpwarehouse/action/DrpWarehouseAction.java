package com.vix.drp.drpwarehouse.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.vo.OrgUnit;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.drpwarehouse.controller.DrpWarehouseController;
import com.vix.drp.drpwarehouse.service.IDrpWarehouseService;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.warehouse.entity.InvWarehouse;

@Controller
@Scope("prototype")
public class DrpWarehouseAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private DrpWarehouseController drpWarehouseController;

	private String id;
	private String parentId;
	private String ids;
	private String pageNo;
	private String treeType;
	private String type;
	private InvWarehouse invWarehouse;

	private List<InvWarehouse> invWarehouseList;
	@Autowired
	private IDrpWarehouseService drpWarehouseService;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
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
			invWarehouseList = drpWarehouseController.doListInvWarehouseList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();

			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (StringUtils.isEmpty(parentId) || StringUtils.isEmpty(treeType)) {
				if (SecurityUtil.getCurrentEmpId() != null) {
					// 获取当前员工信息
					Employee employee = drpWarehouseController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
					if (employee != null) {
						if (employee.getChannelDistributor() != null && employee.getChannelDistributor().getId() != null && !"".equals(employee.getChannelDistributor().getId())) {
							params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
						}
					}
				}
			} else {
				if ("C".equals(treeType)) {
					if (SecurityUtil.getCurrentEmpId() != null) {
						// 获取当前员工信息
						Employee employee = drpWarehouseController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
						if (employee != null) {
							if (employee.getChannelDistributor() != null && employee.getChannelDistributor().getId() != null && !"".equals(employee.getChannelDistributor().getId())) {
								params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
							}
						}
					}
				} else if ("CH".equals(treeType)) {
					// 点击的树节点是分销体系结构
					params.put("channelDistributor.id," + SearchCondition.EQUAL, parentId);
				}
			}
			params.put("type," + SearchCondition.EQUAL, "2");
			String searchContent = getDecodeRequestParameter("searchContent");
			if (searchContent != null && !"".equals(searchContent)) {
				params.put("name," + SearchCondition.ANYLIKE, searchContent);
			}
			String name = getDecodeRequestParameter("name");
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			String code = getRequestParameter("code");
			if (code != null && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
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
			pager = drpWarehouseController.doListInvWarehousePager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				invWarehouse = drpWarehouseController.doListInvWarehouseById(id);
			} else {
				invWarehouse = new InvWarehouse();
				invWarehouse.setCode(VixUUID.createCode(10));
				if (null != parentId && !"".equals(parentId)) {
					if ("C".equals(treeType)) {
						Organization organization = drpWarehouseService.findEntityById(Organization.class, parentId);
						if (organization != null) {
							invWarehouse.setType("1");
							invWarehouse.setOrganization(organization);
						}
					} else if ("CH".equals(treeType)) {
						// 点击的树节点是分销体系结构
						ChannelDistributor channelDistributor = drpWarehouseService.findEntityById(ChannelDistributor.class, parentId);
						if (null != channelDistributor) {
							invWarehouse.setType("2");
							invWarehouse.setChannelDistributor(channelDistributor);
						}
					}
				}
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
			if (invWarehouse != null) {
				if (null != invWarehouse.getId() && !"".equals(invWarehouse.getId())) {
					isSave = false;
				}
				if (invWarehouse.getChannelDistributor() != null && invWarehouse.getChannelDistributor().getId() != null && !"".equals(invWarehouse.getChannelDistributor().getId())) {
				} else {
					invWarehouse.setChannelDistributor(null);
				}
				if (invWarehouse.getOrganization() != null && invWarehouse.getOrganization().getId() != null && !"".equals(invWarehouse.getOrganization().getId())) {
				} else {
					invWarehouse.setOrganization(null);
				}

				String chineseCharacter = ChnToPinYin.getPYString(invWarehouse.getName());
				invWarehouse.setChineseCharacter(chineseCharacter);
				initEntityBaseController.initEntityBaseAttribute(invWarehouse);
				//处理修改留痕
				billMarkProcessController.processMark(invWarehouse, updateField);
				drpWarehouseController.doSaveInvWarehouse(invWarehouse);
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
			InvWarehouse cd = drpWarehouseController.doListInvWarehouseById(id);
			if (null != cd) {
				drpWarehouseController.doDeleteInvWarehouse(cd);
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
				drpWarehouseController.doDeleteByIds(delIds);
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
	 * @Title: findOrgAndUnitTreeToJson @Description: 加载公司和部门的混合树 @param
	 *         设定文件 @return void 返回类型 @throws
	 */
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
						orgUnitList = drpWarehouseService.findOrgAndUnitTreeList(nodeTreeType, nodeId);
					}
				}
			} else {
				// id为空 则类型也为空
				// 加载公司信息
				Map<String, Object> params = getParams();
				orgList = drpWarehouseService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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

	public String goSearch() {
		return "goSearch";
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
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
