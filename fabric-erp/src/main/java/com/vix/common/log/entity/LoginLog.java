package com.vix.common.log.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/** 
 * 登陆日志
 * @author arron
 */
public class LoginLog extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/** 登陆账号 */
	private String loginName;
	/** 登录IP */
	private String loginIp;
	/** 登录时间 */
	private Date loginTime;
	
	/**  
	 * 登录状态
	 * Y 成功
	 * N 失败
	 * */
	private String loginStatus;
	
	/** 登录失败原因 */
	private String loginFailMsg;
	
	/**
	 * 某时间间隔内 登录次数
	 */
	private Long loginCounts;
	
	/** 登陆人员id 
	 * 未使用
	 */
	private String employeeId;
	/** 登陆人员
	 * 未使用
	 */
	private String employeeName;
	/** 登陆人员身份证 
	 * 未使用 
	 */
	private String employeeIdCard;

	public LoginLog() {}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public Long getLoginCounts() {
		return loginCounts;
	}

	public void setLoginCounts(Long loginCounts) {
		this.loginCounts = loginCounts;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginFailMsg() {
		return loginFailMsg;
	}

	public void setLoginFailMsg(String loginFailMsg) {
		this.loginFailMsg = loginFailMsg;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeIdCard() {
		return employeeIdCard;
	}

	public void setEmployeeIdCard(String employeeIdCard) {
		this.employeeIdCard = employeeIdCard;
	}
}