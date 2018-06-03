package com.vix.drp.MembershipCardmanagement.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.MembershipCardmanagement.entity.MemberShipCard;
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
@Component("membershipCardmanagementDomain")
@Transactional
public class MembershipCardmanagementDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, MemberShipCard.class, params);
		return p;
	}

	public Pager findCustomerAccountPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, CustomerAccount.class, params);
		return p;
	}

	public MemberShipCard findMemberShipCardById(String id) throws Exception {
		return baseHibernateService.findEntityById(MemberShipCard.class, id);
	}

	public CustomerAccount findCustomerAccountById(String id) throws Exception {
		return baseHibernateService.findEntityById(CustomerAccount.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return baseHibernateService.findEntityById(Employee.class, id);
	}

	public ChannelDistributor findChannelDistributorById(String id) throws Exception {
		return baseHibernateService.findEntityById(ChannelDistributor.class, id);
	}

	public MemberShipCard mergeMemberShipCard(MemberShipCard memberShipCard) throws Exception {
		return baseHibernateService.merge(memberShipCard);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(MemberShipCard.class, ids);
	}

	public void deleteMemberShipCard(MemberShipCard memberShipCard) throws Exception {
		baseHibernateService.deleteByEntity(memberShipCard);
	}

	/** 索引对象列表 */
	public List<MemberShipCard> findMemberShipCardList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(MemberShipCard.class, params);
	}
}
