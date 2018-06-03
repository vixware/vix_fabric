package com.vix.nvix.system.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.security.entity.Authority;
import com.vix.common.security.entity.Role;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.service.IAuthorityService;
import com.vix.common.security.service.IIndexPageDataConfigService;
import com.vix.common.security.service.IRoleService;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.securityDca.service.IDataColSecService;
import com.vix.core.constant.BizConstant;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.entity.HomeTemplate;

@Controller
@Scope("request")
public class NvixntRoleAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IRoleService roleService;
	@Autowired
	private IAuthorityService authorityService;
	@Autowired
	private IDataColSecService dataColSecService;

	@Autowired
	private IIndexPageDataConfigService indexPageDataConfigService;

	private String roleName;

	private String id;

	private String roleId;

	private String homeTemplateId;

	private Role entity;

	private Role entityForm;

	private String treeId;

	private List<String> checkAuthority;

	private Map<String, String> bizTypeMap;

	private String bizType;

	private String authId;

	// 左侧checked的树的节点
	private String menuIds;
	// 右侧checked的按钮
	private String funIds;
	// 左侧点击的菜单节点
	private String checkedMenuId;
	// 左侧权限树是否有变化
	private Boolean isChangCheckMenu;
	// 右侧按钮是否有变化
	private Boolean isChangeCheckFun;

	/** 列权限配置分类 名称 */
	private String colConfigName;

	/** 添加的配置项id */
	private String colConfigIds;
	/** 添加的配置项id */
	private String colConfigId;

	/** 获取列表数据 */
	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(roleName)) {
				roleName = decode(roleName, "UTF-8");
				params.put("roleName", "%" + roleName + "%");
			}
			params.put("roleType", "S");
			Pager pager = roleService.findRolePager(getPager(), params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goShopRoleList() {
		return "goShopRoleList";
	}

	/** 获取列表数据 */
	public void goShopRoleSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(roleName)) {
				roleName = decode(roleName, "UTF-8");
				params.put("roleName", "%" + roleName + "%");
			}
			params.put("roleType", "B");
			Pager pager = roleService.findRolePager(getPager(), params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 供应商角色列表
	 * 
	 * @return
	 */
	public String goSupplierRole() {
		return "goSupplierRole";
	}

	/** 获取列表数据 */
	public void goSupplierRoleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(roleName)) {
				roleName = decode(roleName, "UTF-8");
				params.put("roleName", "%" + roleName + "%");
			}
			params.put("roleType", "SU");
			Pager pager = roleService.findRolePager(getPager(), params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				entity = roleService.findEntityById(Role.class, id);
			} else {
				entity = new Role();
				entity.setRoleType("S");
				entity.setRoleCode(SecurityUtil.getTenantRandomString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdateRole() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				entity = roleService.findEntityById(Role.class, id);
			} else {
				entity = new Role();
				entity.setRoleType("B");
				entity.setRoleCode(SecurityUtil.getTenantRandomString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateRole";
	}
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdateSupplierRole() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				entity = roleService.findEntityById(Role.class, id);
			} else {
				entity = new Role();
				entity.setRoleType("SU");
				entity.setRoleCode(SecurityUtil.getTenantRandomString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateSupplierRole";
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (roleService.isEntityExist(Role.class, entityForm.getId(), "name", entityForm.getName())) {
				renderText("角色名称已经存在");
				return;
			}
			if (StrUtils.isNotEmpty(entityForm.getId())) {
				isSave = false;
			}
			Set<Authority> aSet = reLoadAuthority();
			entityForm.setAuthoritys(aSet);

			entityForm.setUpdateTime(new Date());
			roleService.merge(entityForm);

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
	public String goChooseHomeTemplate() {
		return "goChooseHomeTemplate";
	}
	/**
	 * 角色绑定模板
	 */
	public void bindHomeTemplateToRole() {
		if (StringUtils.isNotEmpty(roleId)) {
			Role role = null;
			try {
				role = vixntBaseService.findEntityById(Role.class, roleId);
				if (role != null) {
					if (StringUtils.isNotEmpty(homeTemplateId)) {
						HomeTemplate homeTemplate = null;
						try {
							homeTemplate = vixntBaseService.findEntityById(HomeTemplate.class, homeTemplateId);
							if (homeTemplate != null) {
								try {
									role.setHomeTemplate(homeTemplate);
									role = vixntBaseService.merge(role);
									renderText(SAVE_SUCCESS);
								} catch (Exception e) {
									e.printStackTrace();
									renderText(SAVE_FAIL);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							renderText(SAVE_FAIL);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				renderText(SAVE_FAIL);
			}
		}
	}

	/** 获取列表数据 */
	public void goHomeTemplateList() {
		try {
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), HomeTemplate.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/** 处理删除操作 */
	public void deleteById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				vixntBaseService.batchDeleteBySql("DELETE from cmn_sec_useraccount_role where Role_ID ='" + id + "'", null);
				roleService.deleteById(Role.class, id);
				renderText(DELETE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	/** 更新角色的权限 */
	private Set<Authority> reLoadAuthority() {
		Set<Authority> aSet = new HashSet<Authority>();
		try {
			String addAuthorityIds = getRequest().getParameter("addAuthorityIds");
			if (null != addAuthorityIds && !"".equals(addAuthorityIds)) {
				for (String idS : addAuthorityIds.split(",")) {
					if (null != idS && !"".equals(idS)) {
						Authority a = authorityService.findEntityById(Authority.class, idS);
						aSet.add(a);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return aSet;
	}

	/**
	 * 角色授权列表
	 * 
	 * @return
	 */
	public String goRoleAuthority() {
		bizTypeMap = new LinkedHashMap<String, String>();
		bizTypeMap.putAll(BizConstant.COMMON_SECURITY_RESTYPE);
		return "goChooseAuthority";
	}

	public String goChooseShopAuthority() {
		bizTypeMap = new LinkedHashMap<String, String>();
		bizTypeMap.putAll(BizConstant.COMMON_SECURITY_RESTYPE);
		return "goChooseShopAuthority";
	}

	public void findAuthorityTreeData() {
		try {
			Set<Authority> listAuthority = new HashSet<Authority>();

			if (null != roleId) {
				listAuthority = roleService.findAllAuthorityMWithRole(roleId, bizType, SecurityUtil.getCurrentUserTenantId());
			}

			StringBuilder resBulder = new StringBuilder();
			resBulder.append("{\"authdata\":[");

			List<String> objStr = new LinkedList<String>();
			for (Authority au : listAuthority) {
				StringBuilder oneStr = new StringBuilder();
				oneStr.append(loadTreeJson(au));
				objStr.add(oneStr.toString());
			}
			if (!objStr.isEmpty()) {
				resBulder.append(StringUtils.join(objStr.iterator(), ","));
			}

			resBulder.append("]}");

			renderHtml(resBulder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void findShopAuthorityTreeData() {
		try {
			UserAccount userAccount = SecurityUtil.getCurrentUserAccount();
			String userId = userAccount.getId();
			Set<Authority> listAuthority = null;
			if (null != roleId) {
				listAuthority = roleService.findAllAuthorityMWithRole(roleId, bizType, SecurityUtil.getCurrentUserTenantId());
			} else {
				listAuthority = this.vixntBaseService.findRoleMenuAuthorityByUserId(roleId, userId, "P", SecurityUtil.getCurrentUserTenantId());
			}
			StringBuilder resBulder = new StringBuilder();
			resBulder.append("{\"authdata\":[");

			List<String> objStr = new LinkedList<String>();
			for (Authority au : listAuthority) {
				StringBuilder oneStr = new StringBuilder();
				oneStr.append(loadTreeJson(au));
				objStr.add(oneStr.toString());
			}
			if (!objStr.isEmpty()) {
				resBulder.append(StringUtils.join(objStr.iterator(), ","));
			}

			resBulder.append("]}");

			renderHtml(resBulder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String loadTreeJson(Authority au) {
		StringBuilder oneStr = new StringBuilder("");

		String pid = au.getParentId();
		String id = au.getId();
		Set<Authority> subs = au.getChildren();

		if (id == null)
			id = "0";
		if (pid == null)
			pid = "0";

		oneStr.append("{\"id\":\"");
		oneStr.append(id);
		oneStr.append("\",\"pId\":\"");
		oneStr.append(pid);
		oneStr.append("\",\"name\":\"");
		oneStr.append(au.getName());
		oneStr.append("\",\"authType\":\"");
		oneStr.append(au.getAuthType());

		oneStr.append("\",\"checkId\":\"");
		oneStr.append(au.getCheckId());

		Long subCount = au.getSubCount();
		if (subCount > 0) {
			oneStr.append("\",\"isParent\":true");// open:true,
		} else {
			oneStr.append("\",\"isParent\":false");// ,open:false
		}
		if (au.getIsCheck().equals("Y")) {
			oneStr.append(",\"checked\":true");
		} else {
			oneStr.append(",\"checked\":false");
		}
		if (subs != null && !subs.isEmpty()) {
			oneStr.append(",\"children\":[");

			List<String> subString = new LinkedList<String>();
			for (Authority subAu : subs) {
				String str = loadTreeJson(subAu);
				subString.add(str);
			}
			oneStr.append(StringUtils.join(subString.iterator(), ","));
			oneStr.append("]");
		}

		oneStr.append("}");
		return oneStr.toString();
	}

	/**
	 * @Title: findFunGrid @Description: 行业模块的权限树节点的 按钮列表 @param 设定文件 @return
	 *         void 返回类型 @throws
	 */
	public void findFunGrid() {
		try {
			List<Authority> subAuthorityList = null;
			subAuthorityList = roleService.findSubAuthorityFByRole(roleId, authId, bizType, SecurityUtil.getCurrentUserTenantId());
			if (subAuthorityList == null) {
				subAuthorityList = new ArrayList<Authority>();
			}

			Long dataSize = subAuthorityList == null ? 0L : subAuthorityList.size();
			renderHtml(convertListToJsonNoPage(subAuthorityList, dataSize));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存行业模块和权限菜单的关系配置
	 * 
	 * @return
	 */
	public void saveForAuthority() {
		try {
			if (StringUtils.isNotEmpty(roleId) && StringUtils.isNotEmpty(bizType)) {
				roleService.saveForAuthority(roleId, bizType, menuIds, funIds, checkedMenuId, isChangCheckMenu, isChangeCheckFun, SecurityUtil.getCurrentUserTenantId());
			}
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
	}

	public void saveForAuthorityByAuthId() {
		try {
			if (roleId != null && StringUtils.isNotEmpty(bizType) && authId != null) {
				roleService.saveForAuthorityByAuthId(roleId, bizType, authId, SecurityUtil.getCurrentUserTenantId());
			}
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
	}

	public void saveForAuthorityByAuthIdOnly() {
		try {
			if (StringUtils.isNotEmpty(roleId) && StringUtils.isNotEmpty(bizType) && StringUtils.isNotEmpty(authId)) {
				roleService.saveForAuthorityByAuthIdOnly(roleId, bizType, authId, SecurityUtil.getCurrentUserTenantId());
			}
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
	}

	public void deleteForAuthorityByAuthId() {
		try {
			if (roleId != null && StringUtils.isNotEmpty(bizType) && authId != null) {
				roleService.deleteForAuthorityByAuthId(roleId, bizType, authId, SecurityUtil.getCurrentUserTenantId());
			}
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
	}

	/** 进入角色 的列权限配置项列表 */
	public String goColConfig() {
		return "goColConfig";
	}

	public String goChooseShopRole() {
		return "goChooseShopRole";
	}
	public String goChooseSupplierRole() {
		return "goChooseSupplierRole";
	}

	/** 列权限配置项列表查询 */
	public String goColConfigList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("roleId", roleId);
			if (StringUtils.isNotEmpty(colConfigName)) {
				colConfigName = decode(colConfigName, "UTF-8");
				params.put("colConfigName", "%" + colConfigName + "%");
			}
			Pager pager = dataColSecService.findColConfigPagerByRoleId(getPager(), params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goColConfigList";
	}

	/** 添加列权限配置项 */
	public String saveForAddColConfig() {
		try {
			if (roleId != null && StringUtils.isNotEmpty(colConfigIds)) {
				roleService.saveForColConfig(roleId, colConfigIds);
			}
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
		return UPDATE;
	}

	/** 删除列权限配置项 */
	public String deleteForAddColConfig() {
		try {
			if (roleId != null && colConfigId != null) {
				roleService.deleteForColConfig(roleId, colConfigId);
			}
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
		return UPDATE;
	}

	// 个性化配置相关
	private String indexPdcName;

	private String divId;

	private String pdcIds;

	private String pdcId;

	public String goIndexPdc() {
		return "goIndexPdc";
	}

	public String goIndexPdcList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("roleId", roleId);

			if (StringUtils.isNotEmpty(indexPdcName)) {
				indexPdcName = decode(indexPdcName, "UTF-8");
				params.put("name", "%" + indexPdcName + "%");
			}
			if (StringUtils.isNotEmpty(divId)) {
				params.put("divId", "%" + divId + "%");
			}
			Pager pager = indexPageDataConfigService.findPdcByRolePager(getPager(), roleId, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goIndexPdcList";
	}

	public String saveForAddIndexPdc() {
		try {
			if (roleId != null && StringUtils.isNotEmpty(pdcIds)) {
				indexPageDataConfigService.saveForAddPdc(roleId, pdcIds);
			}
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
		return UPDATE;
	}

	/** 删除列权限配置项 */
	public String deleteForAddIndexPdc() {
		try {
			if (roleId != null && pdcId != null) {
				indexPageDataConfigService.deletePdcForRole(roleId, pdcId);
			}
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
		return UPDATE;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public Role getEntity() {
		return entity;
	}

	public void setEntity(Role entity) {
		this.entity = entity;
	}

	public Role getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(Role entityForm) {
		this.entityForm = entityForm;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public List<String> getCheckAuthority() {
		return checkAuthority;
	}

	public void setCheckAuthority(List<String> checkAuthority) {
		this.checkAuthority = checkAuthority;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Map<String, String> getBizTypeMap() {
		return bizTypeMap;
	}

	public void setBizTypeMap(Map<String, String> bizTypeMap) {
		this.bizTypeMap = bizTypeMap;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public String getFunIds() {
		return funIds;
	}

	public void setFunIds(String funIds) {
		this.funIds = funIds;
	}

	public String getCheckedMenuId() {
		return checkedMenuId;
	}

	public void setCheckedMenuId(String checkedMenuId) {
		this.checkedMenuId = checkedMenuId;
	}

	public Boolean getIsChangCheckMenu() {
		return isChangCheckMenu;
	}

	public void setIsChangCheckMenu(Boolean isChangCheckMenu) {
		this.isChangCheckMenu = isChangCheckMenu;
	}

	public Boolean getIsChangeCheckFun() {
		return isChangeCheckFun;
	}

	public void setIsChangeCheckFun(Boolean isChangeCheckFun) {
		this.isChangeCheckFun = isChangeCheckFun;
	}

	public String getColConfigName() {
		return colConfigName;
	}

	public void setColConfigName(String colConfigName) {
		this.colConfigName = colConfigName;
	}

	public String getColConfigIds() {
		return colConfigIds;
	}

	public void setColConfigIds(String colConfigIds) {
		this.colConfigIds = colConfigIds;
	}

	public String getColConfigId() {
		return colConfigId;
	}

	public void setColConfigId(String colConfigId) {
		this.colConfigId = colConfigId;
	}

	public String getIndexPdcName() {
		return indexPdcName;
	}

	public void setIndexPdcName(String indexPdcName) {
		this.indexPdcName = indexPdcName;
	}

	public String getDivId() {
		return divId;
	}

	public void setDivId(String divId) {
		this.divId = divId;
	}

	public String getPdcIds() {
		return pdcIds;
	}

	public void setPdcIds(String pdcIds) {
		this.pdcIds = pdcIds;
	}

	public String getPdcId() {
		return pdcId;
	}

	public void setPdcId(String pdcId) {
		this.pdcId = pdcId;
	}

	/**
	 * @return the homeTemplateId
	 */
	public String getHomeTemplateId() {
		return homeTemplateId;
	}

	/**
	 * @param homeTemplateId
	 *            the homeTemplateId to set
	 */
	public void setHomeTemplateId(String homeTemplateId) {
		this.homeTemplateId = homeTemplateId;
	}

}
