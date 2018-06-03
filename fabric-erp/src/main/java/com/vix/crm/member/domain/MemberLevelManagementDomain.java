/**
 * 
 */
package com.vix.crm.member.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.crm.member.entity.MemberLevel;
import com.vix.mdm.crm.entity.CustomerAccount;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("memberLevelManagementDomain")
@Transactional
public class MemberLevelManagementDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findCustomerAccountPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, CustomerAccount.class, params);
		return p;
	}

	public CustomerAccount findCustomerAccountById(String id) throws Exception {
		return baseHibernateService.findEntityById(CustomerAccount.class, id);
	}

	public MemberLevel findMemberLevelById(String id) throws Exception {
		return baseHibernateService.findEntityById(MemberLevel.class, id);
	}

	public List<MemberLevel> findMemberLevelList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(MemberLevel.class, params);
	}

	public CustomerAccount saveOrUpdateCustomerAccount(CustomerAccount customerAccount) throws Exception {
		return baseHibernateService.merge(customerAccount);
	}

}
