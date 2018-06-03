package com.vix.crm.business.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.Coupon;
import com.vix.crm.business.entity.CouponDetail;
import com.vix.mdm.crm.entity.CustomerAccount;

@Controller
@Scope("prototype")
public class DispenseCouponAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String customerAccountId;
	private String couponId;
	private String couponDetailId;
	private String pageNo;
	private CustomerAccount customerAccount;
	private List<Coupon> couponList;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");

			String name = getDecodeRequestParameter("name");
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, CustomerAccount.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 选择优惠券
	 * 
	 * @return
	 */
	public String goChooseCoupon() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			couponList = baseHibernateService.findAllByConditions(Coupon.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goChooseCoupon";
	}

	public String goListCouponDetail() {
		return "goListCouponDetail";
	}

	public String goSingleListCouponDetail() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			params.put("coupon.id," + SearchCondition.EQUAL, couponId);
			params.put("status," + SearchCondition.EQUAL, "1");
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager.setPageSize(6);
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, CouponDetail.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSingleListCouponDetail";
	}

	/**
	 * 发放
	 */
	public void giveOutCoupon() {
		try {
			if (StringUtils.isNotEmpty(customerAccountId) && StringUtils.isNotEmpty(couponDetailId)) {
				CustomerAccount customerAccount = baseHibernateService.findEntityById(CustomerAccount.class, customerAccountId);
				CouponDetail couponDetail = baseHibernateService.findEntityById(CouponDetail.class, couponDetailId);
				if (customerAccount != null && couponDetail != null) {
					//绑定优惠券状态到会员 并更改优惠券明细的状态
					couponDetail.setStatus("2");
					couponDetail.setCustomerAccount(customerAccount);
					couponDetail = baseHibernateService.merge(couponDetail);
					//增加优惠券已发放数量
					Coupon coupon = couponDetail.getCoupon();
					if (coupon.getHasGiveOut() != null) {
						coupon.setHasGiveOut(coupon.getHasGiveOut() + 1);
					} else {
						coupon.setHasGiveOut(1L);
					}
					coupon = baseHibernateService.merge(coupon);
					setMessage("发放成功");
				} else {
					this.setMessage("发放失败");
				}
			} else {
				this.setMessage("发放失败");
			}
		} catch (Exception e) {
			this.setMessage("发放失败");
			e.printStackTrace();
		}
	}

	public String goSearch() {
		return "goSearch";
	}

	public String getCustomerAccountId() {
		return customerAccountId;
	}

	public void setCustomerAccountId(String customerAccountId) {
		this.customerAccountId = customerAccountId;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getCouponDetailId() {
		return couponDetailId;
	}

	public void setCouponDetailId(String couponDetailId) {
		this.couponDetailId = couponDetailId;
	}

	public List<Coupon> getCouponList() {
		return couponList;
	}

	public void setCouponList(List<Coupon> couponList) {
		this.couponList = couponList;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

}
