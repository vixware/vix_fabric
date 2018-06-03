package com.vix.nvix.srm.action;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.security.entity.Role;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.share.entity.BaseEmployee;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.accountmanagement.controller.AccountManagementController;
import com.vix.drp.accountmanagement.service.IAccountManagementService;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.project.util.ProjectUnit;

/**
 * 账号信息
 * 
 * @author zhanghaibing
 * 
 * @date 2013-11-15
 */
@Controller
@Scope("prototype")
public class VixntSupplierAccountAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private AccountManagementController accountManagementController;

	private String id;
	private String ids;
	private String treeType;
	private String parentId;
	private String addUserAccountRoleIds;
	private String rolenames;
	private String deleteUserAccountRoleIds;
	private UserAccount entity;
	private UserAccount entityForm;
	@Autowired
	private IAccountManagementService accountManagementService;
	private List<UserAccount> userAccountList;
	private String source;

	@Override
	public String goList() {
		return GO_LIST;
	}

	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			if (StringUtils.isNotEmpty(parentId) && StringUtils.isNotEmpty(treeType)) {
				if ("E".equals(treeType)) {
					params.put("employee.id," + SearchCondition.EQUAL, parentId);
				}
			}
			String employeeName = getDecodeRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(employeeName)) {
				params.put("employee.name," + SearchCondition.ANYLIKE, employeeName);
			}
			params.put("accountBizType," + SearchCondition.EQUAL, "su");
			pager = accountManagementController.doListUserAccount(params, pager);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				entity = accountManagementService.findEntityById(UserAccount.class, id);
				addUserAccountRoleIds = "";
				rolenames = "";
				if (entity != null) {
					for (Role role : entity.getRoles()) {
						addUserAccountRoleIds += "," + role.getId();
						rolenames += " " + role.getName();
					}
				}
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
			if (null != parentId && !"".equals(parentId) && !"undefined".equals(parentId)) {
				Employee employee = accountManagementService.findEntityById(Employee.class, parentId);
				if (employee != null) {
					accountManagementService.saveOrUpdate(addUserAccountRoleIds, deleteUserAccountRoleIds, accountId, "su", entityForm.getAccount(), entityForm.getPassword(), parentId, "1", employee.getCompanyCode());
				}
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

	public void findSupplierTreeToJson() {
		try {
			loadSupplier(id, treeType);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void loadSupplier(String nodeId, String nodeTreeType) {
		try {
			List<ProjectUnit> orgUnitList = null;
			List<Supplier> supplierList = null;
			if (StringUtils.isNotEmpty(nodeId) && StringUtils.isNotEmpty(nodeTreeType)) {
				if (nodeTreeType.equals("S")) {
					orgUnitList = vixntBaseService.findSupplierList(nodeTreeType, nodeId);
				}
			} else {
				Map<String, Object> params = new HashMap<String, Object>();
				supplierList = vixntBaseService.findAllByConditions(Supplier.class, params);
			}
			if (orgUnitList == null) {
				orgUnitList = new LinkedList<ProjectUnit>();
			}
			if (supplierList != null) {
				for (Supplier supplier : supplierList) {
					ProjectUnit ou1 = new ProjectUnit(supplier.getId(), "S", supplier.getName(), supplier.getCode());
					List<Employee> bcList = vixntBaseService.findSupplierEmployeeList(supplier.getId());
					if (bcList != null && bcList.size() > 0) {
						List<ProjectUnit> projectUnitList = new LinkedList<ProjectUnit>();
						for (Employee employee : bcList) {
							if (employee != null) {
								ProjectUnit ou2 = new ProjectUnit(employee.getId(), "E", employee.getName(), employee.getCode());
								projectUnitList.add(ou2);
							}
						}
						ou1.setSubProjectUnits(projectUnitList);
					}
					orgUnitList.add(ou1);
				}
			}

			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = orgUnitList.size();
			for (int i = 0; i < count; i++) {
				ProjectUnit projectUnit = orgUnitList.get(i);
				if (projectUnit.getSubProjectUnits() != null && projectUnit.getSubProjectUnits().size() > 0) {
					strSb.append("{\"id\":\"");
					strSb.append(projectUnit.getId());
					strSb.append("\",\"treeType\":\"");
					strSb.append(projectUnit.getTreeType());
					strSb.append("\",\"name\":\"");
					strSb.append(projectUnit.getTreeName());
					strSb.append("\",open:false,isParent:true}");

				} else {
					strSb.append("{\"id\":\"");
					strSb.append(projectUnit.getId());
					strSb.append("\",\"treeType\":\"");
					strSb.append(projectUnit.getTreeType());
					strSb.append("\",\"name\":\"");
					strSb.append(projectUnit.getTreeName());
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

	@Override
	public String getSource() {
		return source;
	}

	@Override
	public void setSource(String source) {
		this.source = source;
	}

	public String getAddUserAccountRoleIds() {
		return addUserAccountRoleIds;
	}

	public void setAddUserAccountRoleIds(String addUserAccountRoleIds) {
		this.addUserAccountRoleIds = addUserAccountRoleIds;
	}

	public String getRolenames() {
		return rolenames;
	}

	public void setRolenames(String rolenames) {
		this.rolenames = rolenames;
	}

	public String getDeleteUserAccountRoleIds() {
		return deleteUserAccountRoleIds;
	}

	public void setDeleteUserAccountRoleIds(String deleteUserAccountRoleIds) {
		this.deleteUserAccountRoleIds = deleteUserAccountRoleIds;
	}

}
