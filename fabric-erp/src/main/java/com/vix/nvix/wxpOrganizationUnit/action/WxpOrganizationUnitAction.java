package com.vix.nvix.wxpOrganizationUnit.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.vo.OrgUnit;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.entity.OrganizationUnitType;
import com.vix.wechat.base.entity.WxpQyWeixinSite;
import com.vix.wechat.base.util.WxQyUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class WxpOrganizationUnitAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private String treeType;
	private WxpQyWeixinSite wxpWeixinSite;
	private OrganizationUnit organizationUnit;
	private String parentId;
	private List<OrganizationUnitType> organizationUnitTypeList;
	/** 获取列表数据 */
	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			if (StringUtils.isNotEmpty(treeType) && StringUtils.isNotEmpty(parentId)) {
				if ("C".equals(treeType)) {
					params.put("organization.id," + SearchCondition.ANYLIKE, parentId);
				} else if ("O".equals(treeType)) {
					params.put("parentOrganizationUnit.id," + SearchCondition.ANYLIKE, parentId);
				}
			}
			String searchCode = getDecodeRequestParameter("searchCode");
			if (StringUtils.isNotEmpty(searchCode)) {
				params.put("fs," + SearchCondition.ANYLIKE, searchCode);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, OrganizationUnit.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			organizationUnitTypeList = vixntBaseService.findAllByConditions(OrganizationUnitType.class, params);
			if (StringUtils.isNotEmpty(id)) {
				organizationUnit = vixntBaseService.findEntityById(OrganizationUnit.class, id);
			} else {
				organizationUnit = new OrganizationUnit();
				if (StringUtils.isNotEmpty(treeType) && StringUtils.isNotEmpty(parentId)) {
					if ("C".equals(treeType)) {
						Organization organization = vixntBaseService.findEntityById(Organization.class, parentId);
						if (organization != null) {
							organizationUnit.setOrganization(organization);
						}
					} else if ("O".equals(treeType)) {
						OrganizationUnit parentOrganizationUnit = vixntBaseService.findEntityById(OrganizationUnit.class, parentId);
						if (parentOrganizationUnit != null) {
							organizationUnit.setParentOrganizationUnit(parentOrganizationUnit);
						}
					}
				}
				organizationUnit.setOrgCode(VixUUID.createCode(10));
				Employee employee = getEmployee();
				if (employee != null) {
					organizationUnit.setCreator(employee.getName());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			String py = ChnToPinYin.getPinYinHeadChar(organizationUnit.getFs()).toUpperCase();
			if (organizationUnit != null && StringUtils.isNotEmpty(organizationUnit.getId()) && !"0".equals(organizationUnit.getId())) {
				isSave = false;
			}
			if (organizationUnit.getOrganization() == null || StringUtils.isEmpty(organizationUnit.getOrganization().getId())) {
				organizationUnit.setOrganization(null);
			}
			if (organizationUnit.getParentOrganizationUnit() == null || StringUtils.isEmpty(organizationUnit.getParentOrganizationUnit().getId())) {
				organizationUnit.setParentOrganizationUnit(null);
			}
			organizationUnit.setChineseCharacter(py);
			initEntityBaseController.initEntityBaseAttribute(organizationUnit);
			organizationUnit = vixntBaseService.merge(organizationUnit);
			// 处理微信部门
			departmentCreate();
			//
			createEncodingRulesTableInTheMiddle(py, organizationUnit.getTenantId());
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText("更新成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText("更新失败");

			}
		}
		return UPDATE;
	}

	/**
	 * @throws Exception
	 */
	private void departmentCreate() throws Exception {
		WxpQyWeixinSite w = vixntBaseService.findEntityByAttribute(WxpQyWeixinSite.class, "qiyeCorpId", api_qiye_corpid);
		if (organizationUnit.getSyncId() != null) {
			organizationUnit = vixntBaseService.findEntityByAttribute(OrganizationUnit.class, "syncId", organizationUnit.getSyncId());
		} else {
			// 调用微信企业号接口
			String pId = "";
			if (organizationUnit.getOrganization() != null && StringUtils.isNotEmpty(organizationUnit.getOrganization().getId())) {
				pId = "1";
			} else if (organizationUnit.getParentOrganizationUnit() != null && StringUtils.isNotEmpty(organizationUnit.getParentOrganizationUnit().getId())) {
				OrganizationUnit o = vixntBaseService.findEntityById(OrganizationUnit.class, organizationUnit.getParentOrganizationUnit().getId());
				pId = String.valueOf(o.getSyncId());
			}
			if (w != null) {
				String request = WxQyUtil.departmentCreate(null, null, organizationUnit.getFs(), pId, w.getQiyeAccessToken());
				if (StringUtils.isNotEmpty(request)) {
					JSONObject jsonObject = JSONObject.fromObject(request);
					Long id = 0L;
					if (jsonObject.has("errcode")) {
						String errcode = jsonObject.getString("errcode");
						if ("0".equals(errcode)) {
							if (jsonObject.has("id")) {
								id = Long.parseLong(jsonObject.getString("id"));
								organizationUnit.setSyncId(id);
							}
						}
					}
				}
			}
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
			if (null != nodeId && !"".equals(nodeId) && !"undefined".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					// 没有根结点 需要加载公司信息 其实id不为空 则treetype肯定也不为空
					if (nodeTreeType.equals("C") || nodeTreeType.equals("O")) {
						// 加载公司信息和部门信息
						orgUnitList = vixntBaseService.findOrgAndUnitTreeList(nodeTreeType, nodeId, "O");
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
					OrgUnit ou1 = new OrgUnit(orgTmp.getId(), "C", orgTmp.getOrgName());
					if (orgTmp.getSubOrganizations().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (Organization childOrg : orgTmp.getSubOrganizations()) {
							ou2List.add(new OrgUnit(childOrg.getId(), "C", childOrg.getOrgName()));
						}
						ou1.setSubOrgUnits(ou2List);
					}
					if (orgTmp.getOrganizationUnits().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (OrganizationUnit organizationUnit : orgTmp.getOrganizationUnits()) {
							ou2List.add(new OrgUnit(organizationUnit.getId(), "O", organizationUnit.getFs()));
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
	public void findOrgJson() {
		try {
			List<OrgUnit> orgUnitList = null;
			List<Organization> orgList = null;
			// id为空 则类型也为空
			// 加载公司信息
			Map<String, Object> params = getParams();
			orgList = vixntBaseService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);

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
					if (orgTmp.getOrganizationUnits().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (OrganizationUnit organizationUnit : orgTmp.getOrganizationUnits()) {
							ou2List.add(new OrgUnit(organizationUnit.getId(), "O", organizationUnit.getFs()));

						}
						ou1.setSubOrgUnits(ou2List);
					}
					orgUnitList.add(ou1);
				}
			}
			renderJson(getJson(orgUnitList, 0L));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getJson(List<OrgUnit> orgUnitList, Long pid) {
		JSONObject jsonData = new JSONObject();
		JSONArray ja = dealJson(orgUnitList, pid);
		jsonData.put("data", ja);
		System.out.println(jsonData.toString());
		return jsonData.toString();
	}

	/**
	 * @param orgUnitList
	 */
	private JSONArray dealJson(List<OrgUnit> orgUnitList, Long pid) {
		JSONArray ja = new JSONArray();
		for (OrgUnit orgUnit : orgUnitList) {
			Long id = Long.parseLong(VixUUID.createCodeByNumber(10));
			if (orgUnit != null) {
				JSONObject json = new JSONObject();
				json.put("name", orgUnit.getOrgName());
				if (pid == 0) {
					json.put("pid", "null");
				} else {
					json.put("pid", pid);
				}
				json.put("id", id);
				if (orgUnit.getSubOrgUnits() != null && orgUnit.getSubOrgUnits().size() > 0) {
					json.put("childrens", dealJson(orgUnit.getSubOrgUnits(), id));
				} else {
					json.put("childrens", "[]");
				}
				ja.add(json);
			}
		}
		return ja;
	}

	/** 处理删除操作 */
	public String deleteEntityById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				OrganizationUnit organizationUnit = vixntBaseService.findEntityById(OrganizationUnit.class, id);
				if (organizationUnit != null) {
					vixntBaseService.deleteByEntity(organizationUnit);
					WxpQyWeixinSite w = vixntBaseService.findEntityByAttribute(WxpQyWeixinSite.class, "qiyeCorpId", api_qiye_corpid);
					if (w != null) {
						String request = WxQyUtil.departmentDelete(organizationUnit.getSyncId(), w.getQiyeAccessToken());
						if (StringUtils.isNotEmpty(request)) {
							JSONObject jsonObject = JSONObject.fromObject(request);
							if (jsonObject.has("errcode")) {
								String errcode = jsonObject.getString("errcode");
								if ("0".equals(errcode)) {
									System.out.println("企业号部门删除成功!");
								}
							}
						}
					}
				}
			}
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	@Override
	public String getId() {
		return id;
	}

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

	/**
	 * @return the treeType
	 */
	public String getTreeType() {
		return treeType;
	}

	/**
	 * @param treeType
	 *            the treeType to set
	 */
	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	/**
	 * @return the organizationUnit
	 */
	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	/**
	 * @param organizationUnit
	 *            the organizationUnit to set
	 */
	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	/**
	 * @return the wxpWeixinSite
	 */
	public WxpQyWeixinSite getWxpWeixinSite() {
		return wxpWeixinSite;
	}

	/**
	 * @param wxpWeixinSite
	 *            the wxpWeixinSite to set
	 */
	public void setWxpWeixinSite(WxpQyWeixinSite wxpWeixinSite) {
		this.wxpWeixinSite = wxpWeixinSite;
	}

	/**
	 * @return the organizationUnitTypeList
	 */
	public List<OrganizationUnitType> getOrganizationUnitTypeList() {
		return organizationUnitTypeList;
	}

	/**
	 * @param organizationUnitTypeList
	 *            the organizationUnitTypeList to set
	 */
	public void setOrganizationUnitTypeList(List<OrganizationUnitType> organizationUnitTypeList) {
		this.organizationUnitTypeList = organizationUnitTypeList;
	}

}
