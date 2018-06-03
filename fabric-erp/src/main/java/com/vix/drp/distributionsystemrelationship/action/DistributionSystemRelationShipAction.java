package com.vix.drp.distributionsystemrelationship.action;

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
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.distributermanagement.service.IDistributerManagementService;
import com.vix.drp.distributionsystemrelationship.controller.DistributionSystemRelationShipController;
import com.vix.hr.organization.entity.Employee;

@Controller
@Scope("prototype")
public class DistributionSystemRelationShipAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private DistributionSystemRelationShipController distributionSystemRelationShipController;

	private String id;
	private String ids;
	private String parentId;
	private String pageNo;
	private String treeType;
	private String type;
	private ChannelDistributor channelDistributor;

	private List<ChannelDistributor> channelDistributorList;
	@Autowired
	private IDistributerManagementService distributerManagementService;

	private List<CurrencyType> currencyTypeList;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (null == id) {
			} else {
				if ("C".equals(treeType)) {
					params.put("organization.id," + SearchCondition.EQUAL, id);
					// 点击的树节点是公司
				} else if ("CH".equals(treeType)) {
					// 点击的树节点是分销体系结构
					params.put("parentChannelDistributor.id," + SearchCondition.EQUAL, id);
				}
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
			channelDistributorList = distributionSystemRelationShipController.doListChannelDistributorList(params);
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
			if (StringUtils.isNotEmpty(id) && StringUtils.isNotEmpty(treeType)) {
				if ("C".equals(treeType)) {
					params.put("organization.id," + SearchCondition.EQUAL, id);
					// 点击的树节点是公司
				} else if ("CH".equals(treeType)) {
					// 点击的树节点是分销体系结构
					params.put("parentChannelDistributor.id," + SearchCondition.EQUAL, id);
				}
			} else {
				if (SecurityUtil.getCurrentEmpId() != null) {
					// 获取当前员工信息
					Employee employee = distributionSystemRelationShipController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
					if (employee != null) {
						if (employee.getChannelDistributor() != null && employee.getChannelDistributor().getId() != null && !"".equals(employee.getChannelDistributor().getId())) {
							params.put("parentChannelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
						}
					}
				}
			}
			//处理搜索条件
			String name = getDecodeRequestParameter("name");
			String code = getRequestParameter("code");
			String selectName = getDecodeRequestParameter("selectName");
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			if (code != null && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			if (selectName != null && !"".equals(selectName)) {
				params.put("name," + SearchCondition.ANYLIKE, selectName);
			}
			//处理搜索条件
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
			//倒序排序
			pager = distributionSystemRelationShipController.doListChannelDistributor(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = getParams();
			currencyTypeList = distributionSystemRelationShipController.doListCurrencyTypeList(params);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				channelDistributor = distributionSystemRelationShipController.doListEntityById(id);
			} else {
				if (null != parentId && !"".equals(parentId) && !"undefined".equals(parentId)) {
					if ("C".equals(treeType)) {
						/* 要根据orgType 判断组织结构类型 */
						Organization o = distributerManagementService.findEntityById(Organization.class, parentId);
						if (null != o) {
							channelDistributor = new ChannelDistributor();
							channelDistributor.setCode(VixUUID.getUUID());
							channelDistributor.setType(type);
							channelDistributor.setOrganization(o);
							channelDistributor.setIsHasParentChannelDistributor("0");
						}
					} else if ("CH".equals(treeType)) {
						// 点击的树节点是分销体系结构
						ChannelDistributor parentChannelDistributor = distributerManagementService.findEntityById(ChannelDistributor.class, parentId);
						if (parentChannelDistributor != null) {
							channelDistributor = new ChannelDistributor();
							channelDistributor.setCode(VixUUID.getUUID());
							channelDistributor.setType(type);
							channelDistributor.setParentChannelDistributor(parentChannelDistributor);
							channelDistributor.setOrganization(parentChannelDistributor.getOrganization());
							channelDistributor.setIsHasParentChannelDistributor("1");
						}
					}
				} else {
					channelDistributor = new ChannelDistributor();
					channelDistributor.setCode(VixUUID.getUUID());
					channelDistributor.setType(type);
					channelDistributor.setIsHasParentChannelDistributor("0");
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
			if (null != channelDistributor.getId() && !"".equals(channelDistributor.getId())) {
				isSave = false;
			}
			String py = ChnToPinYin.getPYString(channelDistributor.getName());
			channelDistributor.setChineseCharacter(py);
			initEntityBaseController.initEntityBaseAttribute(channelDistributor);
			if (channelDistributor.getParentChannelDistributor() != null && channelDistributor.getParentChannelDistributor().getId() != null && !"".equals(channelDistributor.getParentChannelDistributor().getId())) {
				channelDistributor.setIsHasParentChannelDistributor("1");
			} else {
				channelDistributor.setParentChannelDistributor(null);
				channelDistributor.setIsHasParentChannelDistributor("0");
			}
			//处理修改留痕
			billMarkProcessController.processMark(channelDistributor, updateField);
			distributionSystemRelationShipController.doSaveChannelDistributor(channelDistributor);
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
			ChannelDistributor cd = distributionSystemRelationShipController.doListEntityById(id);
			if (null != cd) {
				distributionSystemRelationShipController.doDeleteByEntity(cd);
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
			if (StringUtils.isNotEmpty(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				distributionSystemRelationShipController.doDeleteByIds(delIds);
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
			if (null != nodeId && !"".equals(nodeId) && !"undefined".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					// 没有根结点 需要加载公司信息 其实id不为空 则treetype肯定也不为空
					if (nodeTreeType.equals("C") || nodeTreeType.equals("CH")) {
						// 加载公司信息和部门信息
						orgUnitList = distributerManagementService.findOrgAndUnitTreeList(nodeTreeType, nodeId);
					}
				}
			} else {
				// id为空 则类型也为空
				// 加载公司信息
				Map<String, Object> params = getParams();
				orgList = distributerManagementService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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
							if (channelDistributor.getParentChannelDistributor() != null) {
							} else {
								OrgUnit ou2 = new OrgUnit(channelDistributor.getId(), "CH", channelDistributor.getName(), channelDistributor.getCode());
								if (channelDistributor.getSubChannelDistributors().size() > 0) {
									List<OrgUnit> ou3List = new LinkedList<OrgUnit>();
									for (ChannelDistributor c : channelDistributor.getSubChannelDistributors()) {
										ou3List.add(new OrgUnit(c.getId(), "CH", c.getName(), c.getCode()));
									}
									ou2.setSubOrgUnits(ou3List);
								}
								ou2List.add(ou2);
							}
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

	public String goChooseOrganization() {
		return "goChooseOrganization";
	}

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				listOrganization = distributerManagementService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = distributerManagementService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = listOrganization.size();
			for (int i = 0; i < count; i++) {
				Organization org = listOrganization.get(i);
				if (org.getSubOrganizations().size() > 0) {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"C\"");
					strSb.append(",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"C\"");
					strSb.append(",name:\"");
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

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public List<ChannelDistributor> getChannelDistributorList() {
		return channelDistributorList;
	}

	public void setChannelDistributorList(List<ChannelDistributor> channelDistributorList) {
		this.channelDistributorList = channelDistributorList;
	}

}
