package com.vix.WebService.vo;

import java.util.Date;

public class CustomerVo {

	/**
	 * 会员标识
	 */
	private Long customerId;
	/**
	 * 会员名称
	 */
	private String customerName;

	/**
	 * 外部标识
	 */
	private String outerId;
	/**
	 * 登陆密码
	 */
	private String password;
	/**
	 * 密码问题
	 */

	private String passwordQuestion;
	/**
	 * 密码答案
	 */
	private String passwordAnswer;
	/**
	 * 保密邮箱
	 */
	private String securityEmail;
	/**
	 * 所属区域
	 */
	private String region;
	/**
	 * 手机
	 */
	private String mobile;
	/**
	 * 固定电话
	 */
	private String tel;
	/**
	 * 常用邮箱
	 */
	private String email;
	/**
	 * 邮编
	 */
	private String zip;
	/**
	 * 联系地址
	 */
	private String addr;
	/**
	 * 出生年
	 */
	private Integer birthYear;
	/**
	 * 出生月
	 */
	private Integer birthMonth;
	/**
	 * 出生日
	 */
	private Integer birthDay;
	/**
	 * 性别
	 */
	private Integer sex;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 状态时间
	 */
	private Date stateTime;
	/**
	 * 是否删除
	 */
	private Integer isDelete;
	/**
	 * 等级标识
	 */
	private Long levelId;

	/**
	 * 积分历史
	 */
	private Long pointHistory;
	/**
	 * 冻结积分
	 */
	private Long pointFreeze;
	/**
	 * 可用积分
	 */
	private Long point;
	/**
	 * 来源渠道
	 */
	private Long channelId;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordQuestion() {
		return passwordQuestion;
	}

	public void setPasswordQuestion(String passwordQuestion) {
		this.passwordQuestion = passwordQuestion;
	}

	public String getPasswordAnswer() {
		return passwordAnswer;
	}

	public void setPasswordAnswer(String passwordAnswer) {
		this.passwordAnswer = passwordAnswer;
	}

	public String getSecurityEmail() {
		return securityEmail;
	}

	public void setSecurityEmail(String securityEmail) {
		this.securityEmail = securityEmail;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Integer getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(Integer birthYear) {
		this.birthYear = birthYear;
	}

	public Integer getBirthMonth() {
		return birthMonth;
	}

	public void setBirthMonth(Integer birthMonth) {
		this.birthMonth = birthMonth;
	}

	public Integer getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Integer birthDay) {
		this.birthDay = birthDay;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getStateTime() {
		return stateTime;
	}

	public void setStateTime(Date stateTime) {
		this.stateTime = stateTime;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	public Long getPointHistory() {
		return pointHistory;
	}

	public void setPointHistory(Long pointHistory) {
		this.pointHistory = pointHistory;
	}

	public Long getPointFreeze() {
		return pointFreeze;
	}

	public void setPointFreeze(Long pointFreeze) {
		this.pointFreeze = pointFreeze;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

}
