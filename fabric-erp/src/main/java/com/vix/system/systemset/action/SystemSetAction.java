package com.vix.system.systemset.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.mail.entity.PersonalEmail;
import com.vix.common.mail.entity.UserAccountToPersonalEmail;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.system.systemset.controller.SystemSetController;

import flexjson.JSONDeserializer;

@Controller
@Scope("prototype")
public class SystemSetAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(SystemSetAction.class);
	@Autowired
	private SystemSetController systemSetController;
	private String id;
	private String ids;
	private String pageNo;
	/**
	 * 个人邮箱设置
	 */
	private PersonalEmail personalEmail;
	private List<PersonalEmail> personalEmailList;
	/**
	 * 当前登录账号信息
	 */
	private UserAccount userAccount;

	@Override
	public String goList() {
		try {
			personalEmailList = systemSetController.doListPersonalEmailIndex();
			userAccount = SecurityUtil.getCurrentUserAccount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/**
	 * 
	 * @return
	 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// code 编码
			String code = getRequestParameter("code");
			if (null != code && !"".equals(code)) {
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// status 状态
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			Pager pager = systemSetController.doListPersonalEmail(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				personalEmail = systemSetController.doListPersonalEmailById(id);
				logger.info("");
			} else {
				personalEmail = new PersonalEmail();
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
			if (null != personalEmail.getId()) {
				isSave = false;
			}
			personalEmail = systemSetController.doSavePersonalEmail(personalEmail);
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

	public String saveOrUpdateUserAccount() {
		try {
			UserAccount u = SecurityUtil.getCurrentUserAccount();
			u.setPassword(userAccount.getPassword());
			userAccount = systemSetController.doSaveUserAccount(u);
			renderText(UPDATE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(UPDATE_FAIL);
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			PersonalEmail pb = systemSetController.doListPersonalEmailById(id);
			if (null != pb) {
				systemSetController.doDeleteByEntity(pb);
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

	/**
	 * 获取个人常用邮箱信息列表
	 */
	public void getPersonalEmailJson() {
		try {
			String json = "";
			UserAccount userAccount = SecurityUtil.getCurrentUserAccount();
			List<PersonalEmail> personalEmailList = new ArrayList<PersonalEmail>();
			if (null != userAccount) {
				List<UserAccountToPersonalEmail> userAccountToPersonalEmailList = baseHibernateService.findAllByEntityClassAndAttribute(UserAccountToPersonalEmail.class, "userAccount.id", userAccount.getId());
				if (userAccountToPersonalEmailList != null && userAccountToPersonalEmailList.size() > 0) {
					for (UserAccountToPersonalEmail userAccountToPersonalEmail : userAccountToPersonalEmailList) {
						personalEmailList.add(userAccountToPersonalEmail.getPersonalEmail());
					}
				}
				json = convertListToJson(personalEmailList, personalEmailList.size());
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存个人邮箱
	 * 
	 * @return
	 */
	public String saveOrUpdatePersonalEmail() {
		boolean isSave = true;
		try {
			String personalEmailJson = getRequestParameter("personalEmailJson");
			List<PersonalEmail> personalEmailList = new ArrayList<PersonalEmail>();
			if (personalEmailJson != null && !"".equals(personalEmailJson)) {
				personalEmailList = new JSONDeserializer<List<PersonalEmail>>().use("values", PersonalEmail.class).deserialize(personalEmailJson);
			}
			if (personalEmailList != null && personalEmailList.size() > 0) {
				for (PersonalEmail pe : personalEmailList) {
					personalEmail = baseHibernateService.merge(pe);
					UserAccountToPersonalEmail userAccountToPersonalEmail = new UserAccountToPersonalEmail();
					UserAccount userAccount = SecurityUtil.getCurrentUserAccount();
					userAccountToPersonalEmail.setUserAccount(userAccount);
					userAccountToPersonalEmail.setPersonalEmail(personalEmail);
					userAccountToPersonalEmail.setTenantId(userAccount.getTenantId());
					baseHibernateService.merge(userAccountToPersonalEmail);
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public PersonalEmail getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(PersonalEmail personalEmail) {
		this.personalEmail = personalEmail;
	}

	public List<PersonalEmail> getPersonalEmailList() {
		return personalEmailList;
	}

	public void setPersonalEmailList(List<PersonalEmail> personalEmailList) {
		this.personalEmailList = personalEmailList;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}
