package com.vix.nvix.wx.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpTradeAddress
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */

public class WxpTradeAddress extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String openId; // 用户的标识，对当前公众号唯一
	String vipMemberInfoId; // 对应VipMemberInfo的id，可能注册的用户并没有办法获取到openId(订阅号)

	String country; // 国家，不需要用户选择，默认中国
	String province; // 省市
	String area1; // 下级区域1
	String area2; // 下级区域2
	String address; // 地址
	String postcode; // 邮编
	String person; // 收件人
	String telphone; // 联系电话
	Integer isDefault; // 是否默认

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getArea1() {
		return area1;
	}

	public void setArea1(String area1) {
		this.area1 = area1;
	}

	public String getArea2() {
		return area2;
	}

	public void setArea2(String area2) {
		this.area2 = area2;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getVipMemberInfoId() {
		return vipMemberInfoId;
	}

	public void setVipMemberInfoId(String vipMemberInfoId) {
		this.vipMemberInfoId = vipMemberInfoId;
	}
}
