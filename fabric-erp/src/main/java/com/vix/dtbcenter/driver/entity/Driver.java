/**
 * 
 */
package com.vix.dtbcenter.driver.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 司机
 * 
 * @author zhanghaibing
 * 
 * @date 2013-9-2
 */
public class Driver extends BaseEntity {
	private static final long serialVersionUID = 5979708367044193539L;
	/** 助记码 */
	private String scode;
	/** 姓 */
	private String lastName;
	/** 名 */
	private String firstName;
	/** 英文名 */
	private String englishName;
	/** 性别 */
	private String sex;
	/** 身份证号 */
	private String identityNumber;
	/** 学历 */
	private String educationalBackground;
	/** 职务 */
	private String position;
	/** 工号 */
	private String workNumber;
	/** 手机号码 */
	private String mobile;
	/** 联系电话 */
	private String telephone;
	/** E-MAIL */
	private String email;
	/** 任职部门 */
	private String jobDepartment;
	/** 关系 */
	private String relationship;
	/** 生日 */
	private Date birthday;
	/** 有效期至 */
	private Date validUntil;
	/** 证件 */
	private Set<Card> cards = new HashSet<Card>();

	public String getScode() {
		return scode;
	}

	public void setScode(String scode) {
		this.scode = scode;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public String getEducationalBackground() {
		return educationalBackground;
	}

	public void setEducationalBackground(String educationalBackground) {
		this.educationalBackground = educationalBackground;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getWorkNumber() {
		return workNumber;
	}

	public void setWorkNumber(String workNumber) {
		this.workNumber = workNumber;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJobDepartment() {
		return jobDepartment;
	}

	public void setJobDepartment(String jobDepartment) {
		this.jobDepartment = jobDepartment;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getValidUntil() {
		return validUntil;
	}

	public void setValidUntil(Date validUntil) {
		this.validUntil = validUntil;
	}

	public Set<Card> getCards() {
		return cards;
	}

	public void setCards(Set<Card> cards) {
		this.cards = cards;
	}

}
