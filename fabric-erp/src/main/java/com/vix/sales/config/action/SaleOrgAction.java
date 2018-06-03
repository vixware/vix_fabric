package com.vix.sales.config.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.service.IBusinessOrgService;
import com.vix.common.org.service.IOrganizationService;
import com.vix.common.org.service.IOrganizationUnitService;
import com.vix.common.org.vo.OrgUnit;
import com.vix.core.constant.BizConstant;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

@Controller
@Scope("prototype")
public class SaleOrgAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IOrganizationService organizationService;

	@Autowired
	private IOrganizationUnitService organizationUnitService;

	@Autowired
	private IBusinessOrgService businessOrgService;

	private String fullName;

	private String id;

	private String parentId;

	/** C O */
	private String parentTreeType;

	private String parentTreeName;

	/** 组织机构公司 */
	private Organization organization;

	/** 部门 */
	private OrganizationUnit organizationUnit;

	private OrganizationUnit entity;

	private OrganizationUnit entityForm;

	private String treeType;

	/**
	 * 新建修改时 业务组织树的的json（包括check）
	 */
	private String entityBusinessOrgJsonStr;
	/**
	 * 新建修改时 业务组织的名称显示
	 */
	private String entityBusinessOrgName;
	/**
	 * 新建修改时 业务组织的id记录
	 */
	private String entityBusinessOrgId;

	/**
	 * 加载顶端工具栏
	 * 
	 * @return
	 */

	public String goTopDynamicMenuContent() {
		return "goTopDynamicMenuContent";
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();

			Pager pager = getPager();
			if (StringUtils.isNotEmpty(fullName)) {
				params.put("fullName", fullName);
				pager = organizationUnitService
						.findOrganizationUnitListByOrgId(getPager(), params);
			} else if (StringUtils.isNotEmpty(treeType)
					&& (null != id && !id.equals(""))) {
				if (treeType.equals("C")) {
					params.put("orgId", id);
				} else {
					params.put("orgUnitId", id);
				}
				pager = organizationUnitService
						.findOrganizationUnitListByOrgId(getPager(), params);
			}

			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id && !id.equals("") && !id.equals("0")) {
				entity = organizationUnitService.findOrganizationUnitAll(id);
				parentTreeType = entity.getParentType();
				parentId = entity.getParentTypeId();
				parentTreeName = entity.getParentTypeName();
			} else {
				if (null != parentId && !"".equals(parentId)) {
					entity = new OrganizationUnit();
					entity.setStartUsingDate(new Date());
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					entity.setStopUsingDate(df.parse("9999-12-31"));
					if (StringUtils.isNotEmpty(treeType)) {
						if (treeType.equals("C")) {
							Organization org = organizationUnitService
									.findEntityById(Organization.class,
											parentId);

							if (null != org) {
								parentTreeType = BizConstant.COMMON_ORG_C;
								parentId = org.getId();
								parentTreeName = org.getOrgName();
							}
						} else {
							OrganizationUnit orgUnit = organizationUnitService
									.findEntityById(OrganizationUnit.class,
											parentId);

							if (null != orgUnit) {
								parentTreeType = BizConstant.COMMON_ORG_O;
								parentId = orgUnit.getId();
								parentTreeName = orgUnit.getFullName();
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String goChooseBusinessOrg() {
		return "goChooseBusinessOrg";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StrUtils.objectIsNotNull(entityForm.getId())) {
				isSave = false;
			} else {
				entityForm.setCreateTime(new Date());
				loadCommonData(entityForm);
			}

			if (StringUtils.isNotEmpty(parentTreeType)) {
				if (parentTreeType.equals("C")) {
					entityForm.setOrganization(new Organization(parentId));
				} else if (parentTreeType.equals("O")) {
					entityForm.setParentOrganizationUnit(new OrganizationUnit(
							parentId));
				}

				organizationUnitService.saveOrUpdateOrgUnit(entityForm,
						entityBusinessOrgId);
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
			OrganizationUnit org = organizationUnitService
					.findOrganizationUnitAll(id);
			if (null != org) {
				if (org.getSubOrganizationUnits().size() > 0) {
					setMessage("存在子部门不允许删除!");
				} else {
					organizationService.deleteByEntity(org);
					renderText(DELETE_SUCCESS);
				}
			} else {
				setMessage("部门信息不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
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
				listOrganization = organizationService
						.findAllSubEntity(Organization.class,
								"parentOrganization.id", id, params);
			} else {
				listOrganization = organizationService.findAllSubEntity(
						Organization.class, "parentOrganization.id", null,
						params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = listOrganization.size();
			for (int i = 0; i < count; i++) {
				Organization org = listOrganization.get(i);
				if (org.getSubOrganizations().size() > 0) {
					strSb.append("{id:");
					strSb.append(org.getId());
					strSb.append(",name:\"");
					strSb.append(org.getOrgName());
					strSb.append("\",open:true,isParent:true}");
				} else {
					strSb.append("{id:");
					strSb.append(org.getId());
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
			// String treeType = getRequestParameter("treeType");
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
					if (nodeTreeType.equals("C") || nodeTreeType.equals("O")) {
						// 加载公司信息和部门信息
						orgUnitList = organizationUnitService
								.findOrgAndUnitTreeList(nodeTreeType, nodeId);
					}
				}
			} else {
				// id为空 则类型也为空
				// 加载公司信息
				Map<String, Object> params = getParams();
				orgList = organizationService.findAllSubEntity(
						Organization.class, "parentOrganization.id", null,
						params);
			}

			if (orgUnitList == null) {
				orgUnitList = new LinkedList<OrgUnit>();
			}

			if (orgList != null) {
				for (Organization orgTmp : orgList) {
					// OrgUnit ou1=new OrgUnit(orgTmp.getId(), "C",
					// orgTmp.getBriefName());
					OrgUnit ou1 = new OrgUnit(orgTmp.getId(), "C",
							orgTmp.getOrgName());

					if (orgTmp.getSubOrganizations().size() > 0) {
						List<OrgUnit> ou2List = new LinkedList<OrgUnit>();
						for (Organization childOrg : orgTmp
								.getSubOrganizations()) {
							ou2List.add(new OrgUnit(childOrg.getId(), "C",
									childOrg.getOrgName()));
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
				if (org.getSubOrgUnits() != null
						&& org.getSubOrgUnits().size() > 0) {
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public OrganizationUnit getOrganizationUnit() {
		return organizationUnit;
	}

	public void setOrganizationUnit(OrganizationUnit organizationUnit) {
		this.organizationUnit = organizationUnit;
	}

	public OrganizationUnit getEntity() {
		return entity;
	}

	public void setEntity(OrganizationUnit entity) {
		this.entity = entity;
	}

	public OrganizationUnit getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(OrganizationUnit entityForm) {
		this.entityForm = entityForm;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentTreeType() {
		return parentTreeType;
	}

	public void setParentTreeType(String parentTreeType) {
		this.parentTreeType = parentTreeType;
	}

	public String getParentTreeName() {
		return parentTreeName;
	}

	public void setParentTreeName(String parentTreeName) {
		this.parentTreeName = parentTreeName;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEntityBusinessOrgJsonStr() {
		return entityBusinessOrgJsonStr;
	}

	public void setEntityBusinessOrgJsonStr(String entityBusinessOrgJsonStr) {
		this.entityBusinessOrgJsonStr = entityBusinessOrgJsonStr;
	}

	public String getEntityBusinessOrgName() {
		return entityBusinessOrgName;
	}

	public void setEntityBusinessOrgName(String entityBusinessOrgName) {
		this.entityBusinessOrgName = entityBusinessOrgName;
	}

	public String getEntityBusinessOrgId() {
		return entityBusinessOrgId;
	}

	public void setEntityBusinessOrgId(String entityBusinessOrgId) {
		this.entityBusinessOrgId = entityBusinessOrgId;
	}
}
