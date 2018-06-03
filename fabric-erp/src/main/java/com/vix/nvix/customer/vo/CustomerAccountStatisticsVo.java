package com.vix.nvix.customer.vo;

/**
 * 
 * @类全名 com.vix.diandoc.base.vo.OrderSucessStatisticsVo
 *
 * @author zhanghaibing
 *
 * @date 2017年6月1日
 */
public class CustomerAccountStatisticsVo {

	private String customerTypeName;// 客户类型
	private String customerStageName;// 客户阶段
	private Long regnum;// 客户数量

	public String getCustomerTypeName() {
		return customerTypeName;
	}

	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
	}

	public String getCustomerStageName() {
		return customerStageName;
	}

	public void setCustomerStageName(String customerStageName) {
		this.customerStageName = customerStageName;
	}

	public Long getRegnum() {
		return regnum;
	}

	public void setRegnum(Long regnum) {
		this.regnum = regnum;
	}
}