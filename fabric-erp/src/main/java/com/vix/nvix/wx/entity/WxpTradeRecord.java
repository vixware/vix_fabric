package com.vix.nvix.wx.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @类全名 com.vix.nvix.wx.entity.WxpTradeRecord
 *
 * @author zhanghaibing
 *
 * @date 2017年10月19日
 */

public class WxpTradeRecord extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int pay_status_paid = 2;
	public static final int pay_status_not_paid = 1;
	public static final int pay_status_cancel = -1;

	public static final int trade_type_jsapi_pay = 1;
	public static final int trade_type_native_pay = 2;
	public static final int trade_type_scan_pay = 3;

	public static final String trade_pay_type_weixin = "weixin";

	/** * 商品销售 */
	public static final String trade_tag_sale_goods = "sale_goods";
	/** * 会员卡(开卡)充值，包括二次充值 */
	public static final String trade_tag_member_card = "member_card";

	String transactionId; // 微信支付交易号

	String tradeNo; // 交易单号
	String openId; // 购买者微信id
	Integer payStatus; // 1：未付款；2已付款；-1：已取消
	Date payDate; // 付款记录时间
	Date createDate; // 创建时间
	Date expireDate; // 过期时间

	String productMendianId; // 产品门店标识
	String productMendianTitle; // 产品门店名称
	String productMendianNo; // 产品门店编号（外联）
	String productCategoryId; // 产品分类id
	String productCategoryTitle; // 产品分类名称
	String productCategoryNo; // 产品分类编号（外联使用）
	String productId; // 产品id(本地系统，数字也保存成字符串）
	String productTitle; // 产品标题
	String productNo; // 产品编号（外联使用）

	Integer tradeType; // 1：jsapi付款；2：native付款；3：员工手持设备扫码支付(native)
	Double totalFee; // 付款总费用
	Double transportFee; // 物流费用
	Double productFee; // 商品费用

	String payType; // 支付渠道,当前是weixin

	String tradeTag; // 预留，交易标签，商品、充值、会员卡等等
	String notifyEvent; // 交易完成后，通知事件
	String notifyParam; // 交易完成后，通知事件参数
	Integer needPost; // 是否需要邮寄
	String country; // 邮寄，国家
	String province; // 邮寄，省市
	String area1; // 邮寄，下级区域1
	String area2; // 邮寄，下级区域2
	String addressStr; // 邮寄，地址
	String postcode; // 邮寄，邮编
	String person; // 邮寄，联系人
	String phone; // 邮寄，电话
	String note; // 备注

	WxpTradeAddress address; // 地址

	Set<WxpTradeRecordGoods> recordGoods = new HashSet<WxpTradeRecordGoods>();

	public boolean isExpired() {
		if (this.payStatus == pay_status_paid)
			return false;
		else if (this.expireDate == null)
			return false;
		else {
			Date now = new Date();
			if (this.expireDate.getTime() < now.getTime())
				return true;
		}
		return false;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Integer getTradeType() {
		return tradeType;
	}

	public void setTradeType(Integer tradeType) {
		this.tradeType = tradeType;
	}

	public Double getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public Double getTransportFee() {
		return transportFee;
	}

	public void setTransportFee(Double transportFee) {
		this.transportFee = transportFee;
	}

	public Double getProductFee() {
		return productFee;
	}

	public void setProductFee(Double productFee) {
		this.productFee = productFee;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductCategoryTitle() {
		return productCategoryTitle;
	}

	public void setProductCategoryTitle(String productCategoryTitle) {
		this.productCategoryTitle = productCategoryTitle;
	}

	public String getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(String productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getProductCategoryNo() {
		return productCategoryNo;
	}

	public void setProductCategoryNo(String productCategoryNo) {
		this.productCategoryNo = productCategoryNo;
	}

	public String getProductMendianId() {
		return productMendianId;
	}

	public void setProductMendianId(String productMendianId) {
		this.productMendianId = productMendianId;
	}

	public String getProductMendianTitle() {
		return productMendianTitle;
	}

	public void setProductMendianTitle(String productMendianTitle) {
		this.productMendianTitle = productMendianTitle;
	}

	public String getProductMendianNo() {
		return productMendianNo;
	}

	public void setProductMendianNo(String productMendianNo) {
		this.productMendianNo = productMendianNo;
	}

	public Set<WxpTradeRecordGoods> getRecordGoods() {
		return recordGoods;
	}

	public void setRecordGoods(Set<WxpTradeRecordGoods> recordGoods) {
		this.recordGoods = recordGoods;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public WxpTradeAddress getAddress() {
		return address;
	}

	public void setAddress(WxpTradeAddress address) {
		this.address = address;
	}

	public String getTradeTag() {
		return tradeTag;
	}

	public void setTradeTag(String tradeTag) {
		this.tradeTag = tradeTag;
	}

	public String getNotifyEvent() {
		return notifyEvent;
	}

	public void setNotifyEvent(String notifyEvent) {
		this.notifyEvent = notifyEvent;
	}

	public String getNotifyParam() {
		return notifyParam;
	}

	public void setNotifyParam(String notifyParam) {
		this.notifyParam = notifyParam;
	}

	public Integer getNeedPost() {
		return needPost;
	}

	public void setNeedPost(Integer needPost) {
		this.needPost = needPost;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getAddressStr() {
		return addressStr;
	}

	public void setAddressStr(String addressStr) {
		this.addressStr = addressStr;
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

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
