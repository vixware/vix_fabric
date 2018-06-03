package com.vix.common.security.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: UserAccountProxyApply
 * @Description: 代理账号信息
 * @author wangmingchen
 * @date 2015年2月15日 下午1:47:52
 */
public class UserAccountProxyApply extends BaseEntity {
	
	/** 代理申请的账号 */
	private UserAccount applyUserAccount;
	
	/** 代理登录    （被请求的代理 使用applyUserAccount登录） */
	private UserAccount acceptUserAccount;
	
	//memo
	/** COMMON_BOOLEAN_YES COMMON_BOOLEAN_NO */
	private String isEnable;
	
	
	//非持久化属性   用于数据展示
	//账号
	private String userAccount;
	
	private String empId;
	//职员姓名
	private String empName;
	
	private String startTimeStr;
	private String endTimeStr;
	
	public UserAccount getApplyUserAccount() {
		return applyUserAccount;
	}

	public void setApplyUserAccount(UserAccount applyUserAccount) {
		this.applyUserAccount = applyUserAccount;
	}

	public UserAccount getAcceptUserAccount() {
		return acceptUserAccount;
	}

	public void setAcceptUserAccount(UserAccount acceptUserAccount) {
		this.acceptUserAccount = acceptUserAccount;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	@Override
	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	@Override
	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
}
