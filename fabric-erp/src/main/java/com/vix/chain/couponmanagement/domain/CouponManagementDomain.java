/**
 * 
 */
package com.vix.chain.couponmanagement.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.Coupon;
import com.vix.crm.business.entity.CouponDetail;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channel.entity.StoreType;
import com.vix.hr.organization.entity.Employee;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("couponManagementDomain")
@Transactional
public class CouponManagementDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findCouponPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Coupon.class, params);
		return p;
	}

	public Coupon saveCoupon(Coupon coupon) throws Exception {
		return baseHibernateService.merge(coupon);
	}

	public CouponDetail saveCouponDetail(CouponDetail couponDetail) throws Exception {
		return baseHibernateService.merge(couponDetail);
	}

	public List<ChannelDistributor> findChannelDistributorList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(ChannelDistributor.class, params);
	}

	public StoreType findStoreTypeById(String id) throws Exception {
		return baseHibernateService.findEntityById(StoreType.class, id);
	}

	public Coupon findCouponById(String id) throws Exception {
		return baseHibernateService.findEntityById(Coupon.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return baseHibernateService.findEntityById(Employee.class, id);
	}

	public void deleteCouponByEntity(Coupon coupon) throws Exception {
		baseHibernateService.deleteByEntity(coupon);
	}
}
