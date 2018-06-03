package com.vix.drp.accountmanagement.action;

import java.util.HashMap;
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
import com.vix.common.org.vo.OrgUnit;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.BaseEmployee;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.accountmanagement.controller.AccountManagementController;
import com.vix.drp.accountmanagement.service.IAccountManagementService;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;

/**
 * 账号信息
 * 
 * @author zhanghaibing
 * 
 * @date 2013-11-15
 */
@Controller
@Scope("prototype")
public class AccountManagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AccountManagementController accountManagementController;

	private String id;
	private String ids;
	private String treeType;
	private String parentId;
	private UserAccount entity;
	private UserAccount entityForm;
	@Autowired
	private IAccountManagementService accountManagementService;
	private List<UserAccount> userAccountList;
	private String source;

	@Override
	public String goList() {
		try {
			userAccountList = accountManagementController.doListStoreInfomationIndex();
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
			// 获取当前员工信息
			if (SecurityUtil.getCurrentEmpId() != null) {
				params.put("employee.id," + SearchCondition.EQUAL, SecurityUtil.getCurrentEmpId());
			}
			if (treeType != null && !"".equals(treeType)) {
				if ("E".equals(treeType)) {
					// 点击的树节点是员工
					params.put("employee.id," + SearchCondition.EQUAL, id);
				} else if ("C".equals(treeType)) {
					// 点击的树节点是公司
				} else if ("CH".equals(treeType)) {
					// 点击的树节点是分销体系结构
					/*params.put("employee.channelDistributor.id," + SearchCondition.EQUAL, id);*/
				}
			}
			//处理搜索条件
			String name = getDecodeRequestParameter("name");
			if (name != null && !"".equals(name)) {
				params.put("employee.name," + SearchCondition.ANYLIKE, name);
			}
			//处理搜索条件
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			params.put("accountBizType," + SearchCondition.EQUAL, "fx");
			pager = accountManagementController.doListUserAccount(params, pager);
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
				entity = accountManagementService.findEntityById(UserAccount.class, id);
			} else {
				if (null != parentId && !"".equals(parentId) && !"undefined".equals(parentId)) {
					Employee employee = accountManagementService.findEntityById(Employee.class, parentId);
					if (employee != null) {
						if (employee.getUserAccounts() != null && employee.getUserAccounts().size() > 0) {
							for (UserAccount userAccount : employee.getUserAccounts()) {
								entity = userAccount;
							}
						} else {
							entity = new UserAccount();
							entity.setEmployee(new BaseEmployee(parentId));
							entity.setEnable("0");
						}
					}
				} else {
					entity = new UserAccount();
					entity.setEnable("0");
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
		String accountId = null;
		try {
			if (accountManagementService.isEntityExist(UserAccount.class, entityForm.getId(), "account", "")) {
				setMessage("账号已经存在");
				return "update";
			}
			if (null != entityForm.getId()) {
				accountId = entityForm.getId();
				isSave = false;
			}
			String addRoleIds = getRequest().getParameter("addUserAccountRoleIds");
			String deleteRoleIds = getRequest().getParameter("deleteUserAccountRoleIds");
			if (null != parentId && !"".equals(parentId) && !"undefined".equals(parentId)) {
				Employee employee = accountManagementService.findEntityById(Employee.class, parentId);
				if (employee != null) {
					accountManagementService.saveOrUpdate(addRoleIds, deleteRoleIds, accountId, "fx", entityForm.getAccount(), entityForm.getPassword(), parentId, "1", employee.getCompanyCode());
				} else {
					//accountManagementService.saveOrUpdate(addRoleIds, deleteRoleIds, accountId, "fx", entityForm.getAccount(), entityForm.getPassword(), parentId, "1", null);
				}
			} else {
				//accountManagementService.saveOrUpdate(addRoleIds, deleteRoleIds, accountId, "fx", entityForm.getAccount(), entityForm.getPassword(), parentId, "1", null);
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
			UserAccount cde = accountManagementController.doListEntityById(id);
			if (null != cde) {
				accountManagementController.doDeleteByEntity(cde);
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
			if (null != nodeId && !"".equals(nodeId) && !"undefined".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					// 没有根结点 需要加载公司信息 其实id不为空 则treetype肯定也不为空
					if (nodeTreeType.equals("C") || nodeTreeType.equals("CH")) {
						// 加载公司信息和部门信息
						orgUnitList = accountManagementService.findOrgAndUnitTreeList(nodeTreeType, nodeId);
					}
				}
			} else {
				// id为空 则类型也为空
				// 加载公司信息
				Map<String, Object> params = getParams();
				orgList = accountManagementService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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

	//获取公司部门人员的树
	public void findEmployeeTree() {
		try {
			loadTree(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	//获取公司部门人员的树
	private void loadTree(String nodeId, String nodeTreeType) {
		try {
			List<OrgUnit> orgUnitList = null;
			List<Organization> orgList = null;
			if (null != nodeId && !"".equals(nodeId) && !"undefined".equals(nodeId)) {
				if (StringUtils.isNotEmpty(nodeTreeType)) {
					// 没有根结点 需要加载公司信息 其实id不为空 则treetype肯定也不为空
					if (nodeTreeType.equals("C") || nodeTreeType.equals("O")) {
						// 加载公司信息和部门信息
						orgUnitList = accountManagementService.findEmpTreeList(nodeTreeType, nodeId);
					}
				}
			} else {
				// id为空 则类型也为空
				// 加载公司信息
				Map<String, Object> params = getParams();
				orgList = accountManagementService.findAllSubEntity(Organization.class, "parentOrganization.id", null, params);
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
							ou2List.add(new OrgUnit(organizationUnit.getId(), "O", organizationUnit.getName()));
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

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
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

	public UserAccount getEntity() {
		return entity;
	}

	public void setEntity(UserAccount entity) {
		this.entity = entity;
	}

	public UserAccount getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(UserAccount entityForm) {
		this.entityForm = entityForm;
	}

	public List<UserAccount> getUserAccountList() {
		return userAccountList;
	}

	public void setUserAccountList(List<UserAccount> userAccountList) {
		this.userAccountList = userAccountList;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
