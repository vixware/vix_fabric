package com.vix.nvix.system.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.security.entity.Role;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.service.IUserAccountService;
import com.vix.core.constant.BizConstant;
import com.vix.core.constant.SearchCondition;
import com.vix.core.constant.SecurityConfigConstant;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.drp.accountmanagement.service.IAccountManagementService;
import com.vix.hr.organization.entity.Employee;
import com.vix.nvix.common.base.action.VixntBaseAction;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("request")
public class NvixntUserAccountAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "userAccountService")
	private IUserAccountService userAccountService;
	@Autowired
	private IAccountManagementService accountManagementService;
	/** 用户账户id */
	private String id;
	private String parentId;

	private UserAccount entity;

	private UserAccount entityForm;

	private String account;

	private String password;
	private String passwordConfirm;

	private Role roleList;

	/** 是否超级管理员 或者 承租户管理员 */
	private Boolean isAdmin;
	private String addUserAccountRoleIds;
	private String rolenames;
	private String username;

	/** 获取列表数据 */
	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			if (StringUtils.isNotEmpty(account)) {
				account = decode(account, "UTF-8");
				params.put("account," + SearchCondition.ANYLIKE, account);
			}
			if (StringUtils.isNotEmpty(username)) {
				username = decode(username, "UTF-8");
				params.put("employee.name," + SearchCondition.ANYLIKE, username);
			}
			params.put("accountBizType," + SearchCondition.EQUAL, "sys");
			pager = vixntBaseService.findPagerByHqlConditions(pager, UserAccount.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户账户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				entity = userAccountService.findEntityById(UserAccount.class, id);
				isAdmin = userAccountService.findIsSuperAdmin(id);
				addUserAccountRoleIds = "";
				rolenames = "";
				if (entity != null) {
					for (Role role : entity.getRoles()) {
						addUserAccountRoleIds += "," + role.getId();
						rolenames += " " + role.getName();
					}
				}
			} else {
				entity = new UserAccount();
				entity.setUserResourceReadType(BizConstant.COMMON_SECURITY_USERMENUTYPE_U);
				isAdmin = false;
			}

			getRequest().setAttribute("lpList", SecurityConfigConstant.SECURITY_LOGIN_PAGE_NOTE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public void checkUserAccount() {
		try {
			Boolean isEdit = (StrUtils.isNotEmpty(id)) ? true : false;
			Boolean hasUa = userAccountService.findHasUserAccount(isEdit, id, account, null);
			if (hasUa) {
				renderText("账号重复，请重新填写！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		String accountId = null;
		try {
			if (null != entityForm.getId()) {
				accountId = entityForm.getId();
				isSave = false;
			}
			String addRoleIds = getRequest().getParameter("addUserAccountRoleIds");
			String deleteRoleIds = getRequest().getParameter("deleteUserAccountRoleIds");
			if (StringUtils.isNotEmpty(parentId) && !"undefined".equals(parentId)) {
				String[] empIds = parentId.split(",");
				for (String empId : empIds) {
					if (StringUtils.isNotEmpty(empId)) {
						Employee employee = userAccountService.findEntityById(Employee.class, empId);
						if (employee != null) {
							accountManagementService.saveOrUpdate(addRoleIds, deleteRoleIds, accountId, "sys", entityForm.getAccount(), entityForm.getPassword(), empId, "1", employee.getCompanyCode());
						}
					}
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
	}

	/** 跳转至用户账户修改页面 */
	public String goResetPwd() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				entity = userAccountService.findEntityById(UserAccount.class, id);
				return "goResetPwd";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void resetPwd() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				if (StringUtils.isEmpty(password) || StringUtils.isEmpty(passwordConfirm) || !password.endsWith(passwordConfirm)) {
					renderText(UPDATE_FAIL);
				} else {
					userAccountService.update4RestPwd(id, password);
					renderText(UPDATE_SUCCESS);
				}

			} else {
				renderText(UPDATE_FAIL);
			}

		} catch (Exception e) {
			handleException(e, UPDATE_FAIL);
		}
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			userAccountService.deleteById(UserAccount.class, id);
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public String goChooseRole() {
		return "goChooseRole";
	}

	/**
	 * 
	 * @return
	 */
	public void goChooseRoleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), Role.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	public String goChooseUserAccount() {
		return "goChooseUserAccount";
	}

	/**
	 * 
	 * @return
	 */
	public String goChooseUserAccountList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = vixntBaseService.findPagerByHqlConditions(getPager(), UserAccount.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goChooseUserAccountList";
	}

	/**
	 * 选择人员
	 * 
	 * @return
	 */
	public String goEmployeeChoose() {
		return "goEmployeeChoose";
	}

	/**
	 * 人员列表
	 */
	public void goEmployeeList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("empType," + SearchCondition.EQUAL, "S");

			String searchName = getDecodeRequestParameter("employeeName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("name," + SearchCondition.ANYLIKE, searchName);
			}
			String orgId = getRequestParameter("orgId");
			if(StrUtils.isNotEmpty(orgId)){
				params.put("organizationUnit.id," + SearchCondition.ANYLIKE, orgId);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, Employee.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 禁用
	 */
	public void disabledAccountById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				UserAccount userAccount = vixntBaseService.findEntityById(UserAccount.class, id);
				if (null != userAccount) {
					userAccount.setEnable("0");
					userAccount = vixntBaseService.merge(userAccount);
					renderText("禁用成功!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("禁用失败!");
		}
	}

	/**
	 * 启用
	 */
	public void enableAccountById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				UserAccount userAccount = vixntBaseService.findEntityById(UserAccount.class, id);
				if (null != userAccount) {
					userAccount.setEnable("1");
					userAccount = vixntBaseService.merge(userAccount);
					renderText("启用成功!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("启用失败!");
		}
	}
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Role getRoleList() {
		return roleList;
	}

	public void setRoleList(Role roleList) {
		this.roleList = roleList;
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
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
}