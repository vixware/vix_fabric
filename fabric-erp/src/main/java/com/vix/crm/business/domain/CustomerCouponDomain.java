/**
 * 
 */
package com.vix.crm.business.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.Coupon;
import com.vix.crm.business.entity.CouponDetail;
import com.vix.crm.business.service.IOrderManagementService;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channel.entity.StoreType;
import com.vix.mdm.crm.entity.CustomerAccount;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("customerCouponDomain")
@Transactional
public class CustomerCouponDomain extends BaseDomain{

	@Autowired
	private IOrderManagementService orderManagementService;

	/** 获取列表数据 */
	public Pager findCustomerAccountPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = orderManagementService.findPagerByHqlConditions(pager, CustomerAccount.class, params);
		return p;
	}

	public Coupon saveCoupon(Coupon coupon) throws Exception {
		return orderManagementService.merge(coupon);
	}

	public CouponDetail saveCouponDetail(CouponDetail couponDetail) throws Exception {
		return orderManagementService.merge(couponDetail);
	}

	public List<ChannelDistributor> findChannelDistributorList(Map<String, Object> params) throws Exception {
		return orderManagementService.findAllByConditions(ChannelDistributor.class, params);
	}

	public List<CustomerAccount> findCustomerAccountList(Map<String, Object> params) throws Exception {
		return orderManagementService.findAllByConditions(CustomerAccount.class, params);
	}

	public StoreType findStoreTypeById(String id) throws Exception {
		return orderManagementService.findEntityById(StoreType.class, id);
	}

	public CustomerAccount findCustomerAccountById(String id) throws Exception {
		return orderManagementService.findEntityById(CustomerAccount.class, id);
	}

	public CouponDetail findCouponDetailById(String id) throws Exception {
		return orderManagementService.findEntityById(CouponDetail.class, id);
	}

	public void deleteCouponDetailByEntity(CouponDetail couponDetail) throws Exception {
		orderManagementService.deleteByEntity(couponDetail);
	}
}
