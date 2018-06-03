package com.vix.WebService.response.impl;

import java.util.List;

import com.vix.WebService.response.StoreResponse;
import com.vix.WebService.vo.CustomerAccountVo;
import com.vix.WebService.vo.MemberShipCardVo;

/**
 * 
 * com.vix.WebService.response.CustomerAccountListResponse
 *
 * @author zhanghaibing
 *
 * @date 2014年10月12日
 */
public class MemberShipCardResponse extends StoreResponse {
	private static final long serialVersionUID = 5961975119861386569L;

	private List<MemberShipCardVo> memberShipCardVoList;
	private List<CustomerAccountVo> customerAccountVoList;

	private Long totalResults;

	public Long getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(Long totalResults) {
		this.totalResults = totalResults;
	}

	public List<MemberShipCardVo> getMemberShipCardVoList() {
		return memberShipCardVoList;
	}

	public void setMemberShipCardVoList(List<MemberShipCardVo> memberShipCardVoList) {
		this.memberShipCardVoList = memberShipCardVoList;
	}

	public List<CustomerAccountVo> getCustomerAccountVoList() {
		return customerAccountVoList;
	}

	public void setCustomerAccountVoList(List<CustomerAccountVo> customerAccountVoList) {
		this.customerAccountVoList = customerAccountVoList;
	}

}
