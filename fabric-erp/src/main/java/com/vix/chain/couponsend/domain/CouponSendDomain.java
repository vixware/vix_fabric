/**
 * 
 */
package com.vix.chain.couponsend.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.Coupon;
import com.vix.crm.business.entity.CouponDetail;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("couponSendDomain")
@Transactional
public class CouponSendDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findCouponPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Coupon.class, params);
		return p;
	}

	/** 获取列表数据 */
	public Pager findCustomerAccountPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, CustomerAccount.class, params);
		return p;
	}

	public CouponDetail saveCouponDetail(CouponDetail couponDetail) throws Exception {
		return baseHibernateService.merge(couponDetail);
	}

	public Coupon saveCoupon(Coupon coupon) throws Exception {
		return baseHibernateService.merge(coupon);
	}

	public List<CustomerAccount> findCustomerAccountList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(CustomerAccount.class, params);
	}

	public Coupon findCouponById(String id) throws Exception {
		return baseHibernateService.findEntityById(Coupon.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return baseHibernateService.findEntityById(Employee.class, id);
	}

}
