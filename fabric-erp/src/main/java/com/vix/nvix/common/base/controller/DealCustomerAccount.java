package com.vix.nvix.common.base.controller;

import com.vix.crm.business.entity.CrmMember;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.purchase.order.entity.RequireGoodsOrder;

public interface DealCustomerAccount {

	/**
	 * 创建会员消费汇总
	 * 
	 * @param customerAccount
	 * @param channelDistributorId
	 * @return
	 */
	CrmMember saveOrUpdateCrmMember(CustomerAccount customerAccount, String channelDistributorId);

	/**
	 * 更新会员消费记录表
	 * 
	 * @param crmMember
	 * @param requireGoodsOrder
	 * @return
	 * @throws Exception
	 */
	CrmMember updateCrmMember(CrmMember crmMember, RequireGoodsOrder requireGoodsOrder) throws Exception;

}