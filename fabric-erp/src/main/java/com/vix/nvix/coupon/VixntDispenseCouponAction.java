package com.vix.nvix.coupon;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.Coupon;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * 发放优惠券
 * 
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class VixntDispenseCouponAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;

	private List<Coupon> couponList;
	public String goSaveOrUpdate() {
		return GO_SAVE_OR_UPDATE;
	}

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			params.put("endmode," + SearchCondition.EQUAL, "1");
			Pager pager = getPager();
			pager = vixntBaseService.findPagerByHqlConditions(pager, Coupon.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdateBathCoupon() {
		try {
			Map<String, Object> params = getParams();
			params.put("endmode," + SearchCondition.EQUAL, "1");
			couponList = vixntBaseService.findAllByConditions(Coupon.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateBathCoupon";
	}

	public void sendCoupon() {

		try {
			String massage = "";
			if (StringUtils.isNotEmpty(id)) {
				Coupon coupon = vixntBaseService.findEntityById(Coupon.class, id);
				if (coupon != null) {
					if ("1".equals(coupon.getEndmode())) {// 会员领取
						massage = "优惠券生成成功!";
					}
					if ("2".equals(coupon.getEndmode())) {// 直接发放
						massage = "优惠券发放成功!";
					}
				}
			}
			if ("".equals(massage)) {
				setMessage("");
			} else {
				setMessage(massage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public List<Coupon> getCouponList() {
		return couponList;
	}

	public void setCouponList(List<Coupon> couponList) {
		this.couponList = couponList;
	}
}
