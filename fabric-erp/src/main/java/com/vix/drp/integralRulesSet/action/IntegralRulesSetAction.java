package com.vix.drp.integralRulesSet.action;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.vo.OrgUnit;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.distributermanagement.service.IDistributerManagementService;
import com.vix.drp.integralRulesSet.controller.IntegralRulesSetController;
import com.vix.drp.integralRulesSet.entity.IntegralRules;

@Controller
@Scope("prototype")
public class IntegralRulesSetAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IntegralRulesSetController integralRulesSetController;
	@Autowired
	private IDistributerManagementService distributerManagementService;
	private String id;
	private String treeType;
	private String source;
	/**
	 * 门店ID
	 */
	private String storeId;
	/**
	 * 积分规则
	 */
	private IntegralRules integralRules;

	/**
	 * 跳转到数据列表页面
	 * 
	 */
	@Override
	public String goList() {
		return GO_LIST;
	}

	/**
	 * 不执行页面跳转及刷新 只将数据显示到输入框中
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
			} else {
				if (StringUtils.isNotEmpty(storeId) && !"0".equals(storeId)) {
					integralRules = distributerManagementService.findEntityByAttributeNoTenantId(IntegralRules.class, "channelDistributor.id", storeId);
					if (integralRules != null) {
					} else {
						ChannelDistributor channelDistributor = new ChannelDistributor(storeId);
						integralRules = new IntegralRules();
						integralRules.setChannelDistributor(channelDistributor);
					}
				} else {
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
			if (null != integralRules.getId() && !"".equals(integralRules.getId())) {
				isSave = false;
			}
			if (StringUtils.isNotEmpty(storeId) && !"0".equals(storeId)) {
				ChannelDistributor channelDistributor = new ChannelDistributor(storeId);
				if (integralRules != null) {
					integralRules.setChannelDistributor(channelDistributor);
				}
			}
			integralRules = integralRulesSetController.doSaveIntegralRules(integralRules);
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

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {
			List<Organization> listOrganization = new ArrayList<Organization>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != id && !"".equals(id)) {
				listOrganization = integralRulesSetController.doListAllOrganization("parentOrganization.id", id, params);
			} else {
				listOrganization = integralRulesSetController.doListAllOrganization("parentOrganization.id", "0", params);
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

	/**
	 * 
	 * @Title: findOrgAndUnitTreeToJson @Description: 加载公司和部门的混合树 @param
	 * 设定文件 @return void 返回类型 @throws
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
					OrgUnit ou1 = new OrgUnit(orgTmp.getId(), "C", orgTmp.getOrgName());
					if (orgTmp.getSubOrganizations().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (Organization childOrg : orgTmp.getSubOrganizations()) {
							ou2List.add(new OrgUnit(childOrg.getId(), "C", childOrg.getOrgName()));
						}
						ou1.setSubOrgUnits(ou2List);
					}
					if (orgTmp.getChannelDistributors().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (ChannelDistributor channelDistributor : orgTmp.getChannelDistributors()) {
							ou2List.add(new OrgUnit(channelDistributor.getId(), "CH", channelDistributor.getName()));
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

	public IntegralRules getIntegralRules() {
		return integralRules;
	}

	public void setIntegralRules(IntegralRules integralRules) {
		this.integralRules = integralRules;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
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
	 * @return the storeId
	 */
	public String getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId
	 *            the storeId to set
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
