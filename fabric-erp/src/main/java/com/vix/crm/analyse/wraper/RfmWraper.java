package com.vix.crm.analyse.wraper;

import java.text.DecimalFormat;

/**
 * RFM 数据传输类 
 *
 */
public class RfmWraper {

	/** 客户数量 */
	private Long customerAccountCount;
	/** 客户百分比 */
	private Double customerAccountPersent;
	
	public RfmWraper(){}

	public Long getCustomerAccountCount() {
		return customerAccountCount;
	}

	public void setCustomerAccountCount(Long customerAccountCount) {
		this.customerAccountCount = customerAccountCount;
	}

	public Double getCustomerAccountPersent() {
		return customerAccountPersent;
	}
	
	public String getCustomerAccountPersentStr() {
		if(null == customerAccountPersent || customerAccountPersent <= 0){
			return "0";
		}
		DecimalFormat myFormatter = new DecimalFormat("0.00");
		return myFormatter.format(customerAccountPersent * 100);   //注意,这句整体作为String
	}


	public void setCustomerAccountPersent(Double customerAccountPersent) {
		this.customerAccountPersent = customerAccountPersent;
	}
}
