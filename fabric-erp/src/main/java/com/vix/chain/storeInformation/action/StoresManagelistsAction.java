package com.vix.chain.storeInformation.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.chain.storeInformation.controller.StoreController;
import com.vix.chain.storeInformation.service.IStoreService;
import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.vo.OrgUnit;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;

@Controller
@Scope("prototype")
public class StoresManagelistsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private StoreController storeController;

	private String id;
	private String parentId;
	private String ids;
	private String pageNo;
	private String treeType;
	private String type;
	/** 经销商 */
	private ChannelDistributor channelDistributor;

	private List<ChannelDistributor> channelDistributorList;
	@Autowired
	private IStoreService storeService;

	/**
	 * 跳转到数据列表页面
	 */
	@Override
	public String goList() {
		try {
			channelDistributorList = storeController.doListChannelDistributorIndex();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 计算最近使用日期 */
	public String getUpdateTime(String up) {
		String updateTime = null;
		Calendar calendar = Calendar.getInstance();
		if ("T1".equals(up)) {
			calendar.add(Calendar.DATE, -2);
		} else if ("T2".equals(up)) {
			calendar.add(Calendar.DATE, -6);
		} else if ("T3".equals(up)) {
			calendar.add(Calendar.DATE, -29);
		} else {
			calendar.add(Calendar.DATE, -89);
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		updateTime = dateFormat.format(calendar.getTime());
		return updateTime;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("type," + SearchCondition.EQUAL, "4");
			if(StringUtils.isEmpty(id) || id.equals("0")){//if (null == id || id == 0) {
			
			} else {
				if ("C".equals(treeType)) {
					// 点击的树节点是公司
					params.put("organizationId", id);
				} else if ("CH".equals(treeType)) {
					// 点击的树节点是分销体系结构
					params.put("channelDistributorId", id);
				}
			}
			Pager pager = storeController.doListChannelDistributor(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null != id && id.longValue() > 0) {
				channelDistributor = storeController.doListEntityById(id);
			} else {
				String parentId = getRequestParameter("parentId");
				if (null != parentId && !"".equals(parentId)) {
					/* 要根据orgType 判断组织结构类型 */
					Organization o = storeService.findEntityById(Organization.class, parentId);
					if (null != o) {
						channelDistributor = new ChannelDistributor();
						channelDistributor.setCode(VixUUID.getUUID());
						channelDistributor.setOrganization(o);
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
			if (null != channelDistributor.getId()) {
				isSave = false;
			}
			// 需要根据组织机构的类型来判断上级公司机构
			String organizationId = getRequestParameter("organizationId");

			if (null != organizationId && !"".equals(organizationId) && !"undefined".equals(organizationId)) {
				Organization organization = storeController.doListOrganizationById(organizationId);
				channelDistributor.setOrganization(organization);
			} else {
				channelDistributor.setOrganization(null);
			}
			channelDistributor.setType("4");
			storeController.doSaveStoreInfomation(channelDistributor);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(SAVE_SUCCESS);
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
			ChannelDistributor cd = storeController.doListEntityById(id);
			if (null != cd) {
				storeController.doDeleteByEntity(cd);
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

	/** 处理删除操作 */
	public String deleteByIds() {
		try {
			if (null != ids && !"".equals(ids)) {
				List<String> delIds = new ArrayList<String>();
				for (String idStr : ids.split(",")) {
					if (null != idStr && !"".equals(idStr) && !"undefined".equals(idStr)) {
						delIds.add(idStr);
					}
				}
				storeController.doDeleteByIds(delIds);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotChoose"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	/**
	 * 
	 * @Title: findOrgAndUnitTreeToJson
	 * @Description: 加载公司和部门的混合树
	 * @param 设定文件
	 * @return void 返回类型
	 * @throws
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
						orgUnitList = storeService.findOrgAndUnitTreeList(nodeTreeType, nodeId);
					}
				}
			} else {
				// id为空 则类型也为空
				// 加载公司信息
				Map<String, Object> params = getParams();
				orgList = storeService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
			}

			if (orgUnitList == null) {
				orgUnitList = new LinkedList<OrgUnit>();
			}

			if (orgList != null) {
				for (Organization orgTmp : orgList) {
					OrgUnit ou1 = new OrgUnit(orgTmp.getId(), "C", orgTmp.getOrgName());
					if (orgTmp.getSubOrganizations().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (Organization childOrg : orgTmp.getSubOrganizations()) {
							ou2List.add(new OrgUnit(childOrg.getId(), "C", childOrg.getOrgName()));
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
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
					strSb.append("\",treeType:\"");
					strSb.append(org.getTreeType());
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
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null != id && !"".equals(id)) {
				listOrganization = storeService.findAllSubEntity(Organization.class, "parentOrganization.id", id, params);
			} else {
				listOrganization = storeService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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
					strSb.append("\",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:false,isParent:true}");
				} else {
					strSb.append("{id:\"");
					strSb.append(org.getId());
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
