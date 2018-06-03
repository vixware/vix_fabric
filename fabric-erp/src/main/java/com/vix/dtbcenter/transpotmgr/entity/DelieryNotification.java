/**
 * 
 */
package com.vix.dtbcenter.transpotmgr.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 发货通知单
 * 
 * @author zhanghaibing
 * 
 * @date 2013-9-8
 */
public class DelieryNotification extends BaseEntity {
	private static final long serialVersionUID = -6299023177132242380L;
	/** 发货通知单编码 */
	private String dnCode;
	/** 客户代码 */
	private String customerCode;
	/** 客户名称 */
	private String customerName;
	/** 联系人 */
	private String contract;
	/** 电话 */
	private String telephone;
	/** 传真 */
	private String fax;
	/** 电子邮件 */
	private String email;
	/** 对应订单编号 */
	private String saleOrderCode;
	/** 内容 */
	private String content;
	/** 说明 */
	private String description;
	/** 通知时间 */
	private Date noticeTime;

	public String getDnCode() {
		return dnCode;
	}

	public void setDnCode(String dnCode) {
		this.dnCode = dnCode;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSaleOrderCode() {
		return saleOrderCode;
	}

	public void setSaleOrderCode(String saleOrderCode) {
		this.saleOrderCode = saleOrderCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	public Date getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}

}
