package com.vix.drp.membershipInformation.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;

/**
 * 竞争者信息
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-9
 */
@Component("membershipInformationDomain")
@Transactional
public class MembershipInformationDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findCustomerAccountPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, CustomerAccount.class, params);
		return p;
	}

	public CustomerAccount findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(CustomerAccount.class, id);
	}

	public ChannelDistributor findChannelDistributorById(String id) throws Exception {
		return baseHibernateService.findEntityById(ChannelDistributor.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return baseHibernateService.findEntityById(Employee.class, id);
	}

	public CustomerAccount mergeCustomerAccount(CustomerAccount customerAccount) throws Exception {
		return baseHibernateService.merge(customerAccount);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(CustomerAccount.class, ids);
	}

	public void deleteByEntity(CustomerAccount customerAccount) throws Exception {
		baseHibernateService.deleteByEntity(customerAccount);
	}

	/** 索引对象列表 */
	public List<CustomerAccount> findCustomerAccountList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(CustomerAccount.class, params);
	}
}
