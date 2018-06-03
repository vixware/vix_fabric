package com.vix.WebService.response.impl;

import com.vix.WebService.response.StoreResponse;
import com.vix.WebService.vo.CustomerAccountVo;

/**
 * 
 * com.vix.WebService.response.CustomerAccountResponse
 *
 * @author zhanghaibing
 *
 * @date 2014年10月12日
 */
public class CustomerAccountResponse extends StoreResponse {
	private static final long serialVersionUID = 5961975119861386569L;

	private CustomerAccountVo customerAccountVo;

	public CustomerAccountVo getCustomerAccountVo() {
		return customerAccountVo;
	}

	public void setCustomerAccountVo(CustomerAccountVo customerAccountVo) {
		this.customerAccountVo = customerAccountVo;
	}

}
