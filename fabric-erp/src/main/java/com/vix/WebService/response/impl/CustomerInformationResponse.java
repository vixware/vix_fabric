package com.vix.WebService.response.impl;

import com.vix.WebService.response.StoreResponse;
import com.vix.WebService.vo.CustomerInformation;

/**
 * 
 * com.vix.WebService.response.CustomerAccountResponse
 *
 * @author zhanghaibing
 *
 * @date 2014年10月12日
 */
public class CustomerInformationResponse extends StoreResponse {
	private static final long serialVersionUID = 5961975119861386569L;

	private CustomerInformation customerInformation;

	public CustomerInformation getCustomerInformation() {
		return customerInformation;
	}

	public void setCustomerInformation(CustomerInformation customerInformation) {
		this.customerInformation = customerInformation;
	}

}
