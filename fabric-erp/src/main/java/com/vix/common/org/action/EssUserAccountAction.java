package com.vix.common.org.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.service.IUserAccountService;
import com.vix.common.share.entity.BaseEmployee;

@Controller("essUserAccountAction")
@Scope("prototype")
public class EssUserAccountAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private IEmployeeHrService employeeHrService;

	@Autowired
	private IUserAccountService userAccountService;

	private UserAccount entity;

	private UserAccount entityForm;

	private String employeeId;

	private String id;

	/**
	 * 基本信息的 联系方式
	 * 
	 * @return
	 */
	public String findEssOfUserAccount() {
		try {
			List<UserAccount> empUserAccountList = null;
			if (StringUtils.isNotEmpty(employeeId) && !employeeId.equals("0")) {// if
																				// (employeeId
																				// !=
																				// null
																				// &&
																				// employeeId
																				// >
																				// 0)
																				// {
				empUserAccountList = employeeHrService.findEssOfUserAccountList(employeeId);
			} else {
				empUserAccountList = new ArrayList<UserAccount>();
			}

			renderHtml(convertListToJsonNoPage(empUserAccountList, empUserAccountList.size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加帐号的界面
	 * 
	 * @return
	 */
	public String toAddEssOfUserAccount() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {// if (id !=
																// null && id >
																// 0) {
				entity = employeeHrService.findEntityById(UserAccount.class, id);
			} else {
				entity = new UserAccount();
				entity.setEnable("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toAddEssOfUserAccount";
	}

	/**
	 * 保存账户界面
	 * 
	 * @return
	 */
	public String saveOrUpdateEssOfUserAccount() {
		boolean isSave = true;
		try {
			if (null != entityForm.getId()) {
				isSave = false;
			}
			if (userAccountService.isEntityExist(UserAccount.class, entityForm.getId(), "account", "")) {
				setMessage("账号已经存在");
				return UPDATE;
			}

			if (null != entityForm.getId()) {
				isSave = false;
			}
			String addRoleIds = getRequest().getParameter("addUserAccountRoleIds");
			String deleteRoleIds = getRequest().getParameter("deleteUserAccountRoleIds");
			BaseEmployee eTemp = userAccountService.findEntityById(BaseEmployee.class, employeeId);
			entityForm.setEmployee(eTemp);
			entityForm = userAccountService.saveOrUpdate(addRoleIds, deleteRoleIds, entityForm);
			if (isSave) {
				setMessage(SAVE_SUCCESS);
			} else {
				setMessage(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				this.setMessage(SAVE_FAIL);
			} else {
				this.setMessage(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			userAccountService.deleteById(UserAccount.class, id);
			// employeeHrService
			setMessage(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	public IEmployeeHrService getEmployeeHrService() {
		return employeeHrService;
	}

	public void setEmployeeHrService(IEmployeeHrService employeeHrService) {
		this.employeeHrService = employeeHrService;
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

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IUserAccountService getUserAccountService() {
		return userAccountService;
	}

	public void setUserAccountService(IUserAccountService userAccountService) {
		this.userAccountService = userAccountService;
	}

}
