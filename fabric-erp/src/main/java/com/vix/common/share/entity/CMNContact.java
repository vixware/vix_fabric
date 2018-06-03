package com.vix.common.share.entity;


/**
 * 公共-联系人/机制
 * @author Administrator
 *
 */
public class CMNContact extends BaseEntity{
 
	private static final long serialVersionUID = 1L;

	/** 业务对象名称 */
	private String boCode;
	/** 业务对象类型 */
	private String boName;
	/** 业务对象类型*/
	private String boStyle;
	/** 姓名 */
	private String name;
	/** 电话 */
	private String telephone;
	/** Email */
	private String email;
	/** Email2 */
	private String email2;	
	/** 传真 */
	private String fax;	
	/** 移动电话 */
	private String mobile;	
	/** 移动电话2 */
	private String mobie2;
	/** 地址信息 */
	private String address;
	/** QQ */
	private String qq;
	
	public CMNContact(){}

	public String getBoCode() {
		return boCode;
	}

	public void setBoCode(String boCode) {
		this.boCode = boCode;
	}

	public String getBoName() {
		return boName;
	}

	public void setBoName(String boName) {
		this.boName = boName;
	}

	public String getBoStyle() {
		return boStyle;
	}

	public void setBoStyle(String boStyle) {
		this.boStyle = boStyle;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
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

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobie2() {
		return mobie2;
	}

	public void setMobie2(String mobie2) {
		this.mobie2 = mobie2;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
}
