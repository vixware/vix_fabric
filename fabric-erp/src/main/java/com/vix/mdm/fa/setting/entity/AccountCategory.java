package com.vix.mdm.fa.setting.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * @Description: 会计科目分类
 * @author ivan
 * @date 2014-01-08
 */
public class AccountCategory extends BaseEntity {

	private static final long serialVersionUID = 3330612824822821979L;
	/** 对象类型 */
	private String objectType;
	/** 父分类 */
	private AccountCategory accountCategory;
	/** 子分类 */
	private Set<AccountCategory> accountCategories = new HashSet<AccountCategory>();
	/** 会计科目 */
	private Set<Account> accounts = new HashSet<Account>();

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public AccountCategory getAccountCategory() {
		return accountCategory;
	}

	public void setAccountCategory(AccountCategory accountCategory) {
		this.accountCategory = accountCategory;
	}

	public Set<AccountCategory> getAccountCategories() {
		return accountCategories;
	}

	public void setAccountCategories(Set<AccountCategory> accountCategories) {
		this.accountCategories = accountCategories;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
}
