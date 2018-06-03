package com.vix.chain.couponsend.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.chain.couponsend.controller.CouponSendController;
import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.Coupon;
import com.vix.crm.business.entity.CouponDetail;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;

@Controller
@Scope("prototype")
public class CouponSendAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CouponSendController couponSendController;

	private String customerAccountIds;
	private String couponId;
	private String id;
	private String parentId;
	private String treeType;
	private String pageNo;

	private Coupon coupon;

	@Override
	public String goList() {
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			if (StringUtils.isNotEmpty(parentId) && !parentId.equals("0") && !parentId.equals("undefined")) {//if (parentId != null && parentId.longValue() > 0 && !"undefined".equals(parentId)) {
				if ("CH".equals(treeType)) {
					params.put("channelDistributor.id," + SearchCondition.EQUAL, parentId);
				}
			}
			if (SecurityUtil.getCurrentEmpId() != null) {
				Employee employee = couponSendController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
				if (employee != null && employee.getChannelDistributor() != null) {
					// 如果登录的员工属于经销商或门店
					params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
				}
			}
			// 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			pager = couponSendController.doListCouponPager(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goChooseCustomerAccount() {
		return "goChooseCustomerAccount";
	}

	public String goCustomerAccountList() {

		try {
			Map<String, Object> params = getParams();
			/* 高级查询 */
			params.put("type," + SearchCondition.ANYLIKE, "personal");

			if (SecurityUtil.getCurrentEmpId() != null) {
				// 获取当前员工信息
				Employee employee = couponSendController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
				if (employee != null) {
					if (employee.getChannelDistributor() != null) {
						// 如果登录的员工属于经销商或门店
						ChannelDistributor channelDistributor = employee.getChannelDistributor();
						params.put("channelDistributor.id," + SearchCondition.EQUAL, channelDistributor.getId());
					}

				}
			}
			String customerName = getDecodeRequestParameter("customerName");
			if (customerName != null && !"".equals(customerName)) {
				params.put("name," + SearchCondition.ANYLIKE, customerName);
			}
			//处理查询条件
			Pager pager = couponSendController.doListCustomerAccountPager(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "goCustomerAccountList";
	}

	public String bindToCustomerAccount() {
		try {
			Coupon coupon = couponSendController.doListCouponById(couponId);
			if (null != coupon) {
				Map<String, Object> params = getParams();
				params.put("id," + SearchCondition.IN, customerAccountIds);
				List<CustomerAccount> customerAccountList = couponSendController.doListCustomerAccountList(params);
				List<CouponDetail> couponDetailList = new ArrayList<CouponDetail>();
				Set<CouponDetail> couponDetails = coupon.getSubCouponDetails();
				if (couponDetails != null && couponDetails.size() > 0) {
					for (CouponDetail couponDetail : couponDetails) {
						couponDetailList.add(couponDetail);
					}
				}
				couponing(coupon, couponDetailList, customerAccountList);
				setMessage("发放成功!");
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage("发放失败!");
		}
		return UPDATE;
	}

	private void couponing(Coupon coupon, List<CouponDetail> couponDetailList, List<CustomerAccount> customerAccountList) throws Exception {
		if (couponDetailList != null && customerAccountList != null && couponDetailList.size() > customerAccountList.size()) {
			for (int i = 0; i < customerAccountList.size(); i++) {
				for (int j = i; j <= i; j++) {
					if (j < couponDetailList.size()) {

						CustomerAccount customerAccount = customerAccountList.get(j);
						CouponDetail couponDetail = couponDetailList.get(j);
						if (!"2".equals(couponDetail.getStatus())) {
							couponDetail.setCustomerAccount(customerAccount);
							couponDetail.setStatus("2");
							couponSendController.doSaveCouponDetail(couponDetail);
							if (coupon.getQuantity() != null) {
								if (coupon.getQuantity() >= 1) {
									coupon.setQuantity(coupon.getQuantity() - 1);
									coupon = couponSendController.doSaveCoupon(coupon);
									if (coupon.getQuantity() == 0) {
										coupon.setStatus("2");
										coupon = couponSendController.doSaveCoupon(coupon);
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public String getCustomerAccountIds() {
		return customerAccountIds;
	}

	public void setCustomerAccountIds(String customerAccountIds) {
		this.customerAccountIds = customerAccountIds;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

}
