package com.vix.crm.business.action;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.crm.business.entity.Coupon;
import com.vix.crm.business.entity.CouponDetail;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class CouponUseConditionAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;

	
	private String CouponDetailId;
	private CouponDetail couponDetail;
	private Coupon coupon;
	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();

			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			pager = baseHibernateService.findPagerByHqlConditions(pager, Coupon.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	public String goSearch() {
		return "goSearch";
	}


	public String getCouponDetailId() {
		return CouponDetailId;
	}

	public void setCouponDetailId(String couponDetailId) {
		CouponDetailId = couponDetailId;
	}
	public CouponDetail getCouponDetail() {
		return couponDetail;
	}
	public void setCouponDetail(CouponDetail couponDetail) {
		this.couponDetail = couponDetail;
	}
	public Coupon getCoupon() {
		return coupon;
	}
	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
}
