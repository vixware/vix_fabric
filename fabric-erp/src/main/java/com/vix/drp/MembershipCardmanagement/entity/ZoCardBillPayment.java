package com.vix.drp.MembershipCardmanagement.entity;

import com.vix.common.share.entity.BaseEntity;

/**
 * 会员卡账单付款方式
 * 
 * @author zhanghaibing
 * 
 * @date 2014-4-14
 */
public class ZoCardBillPayment extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6368383477555493403L;
	/**
	 * 账单号
	 */
	private String billid;
	/**
	 * 科目编码
	 */
	private String subiectid;
	/**
	 * 科目名称
	 */
	private String subietname;
	/**
	 * 付款金额
	 */
	private String paysum;

	public String getBillid() {
		return billid;
	}

	public void setBillid(String billid) {
		this.billid = billid;
	}

	public String getSubiectid() {
		return subiectid;
	}

	public void setSubiectid(String subiectid) {
		this.subiectid = subiectid;
	}

	public String getSubietname() {
		return subietname;
	}

	public void setSubietname(String subietname) {
		this.subietname = subietname;
	}

	public String getPaysum() {
		return paysum;
	}

	public void setPaysum(String paysum) {
		this.paysum = paysum;
	}

}
