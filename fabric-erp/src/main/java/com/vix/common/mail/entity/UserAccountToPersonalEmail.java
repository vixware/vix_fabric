package com.vix.common.mail.entity;

import com.vix.common.security.entity.UserAccount;
import com.vix.common.share.entity.BaseEntity;

/**
 * 员工邮箱关联表
 * 
 * @author zhanghaibing
 * 
 * @date 2014-5-14
 */
public class UserAccountToPersonalEmail extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 账号
	 */
	private UserAccount userAccount;
	/**
	 * 个人邮箱
	 */
	private PersonalEmail personalEmail;

	public PersonalEmail getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(PersonalEmail personalEmail) {
		this.personalEmail = personalEmail;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}
