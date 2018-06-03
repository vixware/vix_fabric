package com.vix.chain.couponmanagement.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.chain.couponmanagement.controller.CouponManagementController;
import com.vix.common.base.action.BaseAction;
import com.vix.common.id.VixUUID;
import com.vix.common.org.entity.Organization;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.vixdata.dataappinterface.IDataAccuracy;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.Coupon;
import com.vix.crm.business.entity.CouponDetail;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;

@Controller
@Scope("prototype")
public class CouponManagementAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CouponManagementController couponManagementController;
	@Autowired
	private IDataAccuracy dataAccuracy;

	private String id;
	private String parentId;
	private String treeType;
	private String pageNo;

	private Coupon coupon;
	private List<ChannelDistributor> channelDistributorList;

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			if (StringUtils.isNotEmpty(parentId) && !parentId.equals("0") && !parentId.equals("undefined")) {// if (parentId != null && parentId.longValue() > 0 &&
																												// !"undefined".equals(parentId)) {
				if ("CH".equals(treeType)) {
					params.put("channelDistributor.id," + SearchCondition.EQUAL, parentId);
				}
			}
			if (SecurityUtil.getCurrentEmpId() != null) {
				Employee employee = couponManagementController.doListEmployeeById(SecurityUtil.getCurrentEmpId());
				if (employee != null && employee.getChannelDistributor() != null) {
					// 如果登录的员工属于经销商或门店
					params.put("channelDistributor.id," + SearchCondition.EQUAL, employee.getChannelDistributor().getId());
				}
			} // 根据状态查询
			String status = getRequestParameter("status");
			if (status != null && !"".equals(status)) {
				params.put("status," + SearchCondition.ANYLIKE, status);
			}
			// 最近使用
			String days = getRequestParameter("days");
			if (days != null && !"".equals(days)) {
				params.put("updateTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(days));
			}
			pager = couponManagementController.doListCoupon(params, pager);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/**
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			Map<String, Object> p = getParams();
			channelDistributorList = couponManagementController.doListChannelDistributorList(p);
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {// if (id != null && id.longValue() > 0) {
				coupon = couponManagementController.doListCouponById(id);
			} else {
				if (null != parentId && !"".equals(parentId) && !"undefined".equals(parentId)) {
					if ("C".equals(treeType)) {
						/* 要根据orgType 判断组织结构类型 */
						Organization o = baseHibernateService.findEntityById(Organization.class, parentId);
						if (null != o) {
							coupon = new Coupon();
							coupon.setStatus("1");
							coupon.setCode(VixUUID.createCode(10));
							coupon.setOrganization(o);
						}
					} else if ("CH".equals(treeType)) {
						// 点击的树节点是分销体系结构
						ChannelDistributor channelDistributor = baseHibernateService.findEntityById(ChannelDistributor.class, parentId);
						if (channelDistributor != null) {
							coupon = new Coupon();
							coupon.setStatus("1");
							coupon.setCode(VixUUID.createCode(10));
							coupon.setChannelDistributor(channelDistributor);
						}
					}
				} else {
					coupon = new Coupon();
					coupon.setStatus("1");
					coupon.setCode(VixUUID.createCode(10));
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 保存催付短信信息
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != coupon.getId()) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(coupon);
			coupon.setExpenditure(dataAccuracy.getAmountDecimal(coupon.getExpenditure()));
			coupon = couponManagementController.doSaveCoupon(coupon);
			if (coupon != null) {
				if (isSave) {
					if (coupon.getQuantity() != null) {
						for (int i = 0; i < coupon.getQuantity(); i++) {
							CouponDetail couponDetail = new CouponDetail();
							couponDetail.setStatus("1");
							couponDetail.setExpenditure(coupon.getExpenditure());
							couponDetail.setType(coupon.getType());
							couponDetail.setUserule(coupon.getUserule());
							couponDetail.setCoupon(coupon);
							couponDetail.setCouponId(coupon.getId());
							initEntityBaseController.initEntityBaseAttribute(couponDetail);
							couponDetail = couponManagementController.doSaveCouponDetail(couponDetail);
						}
					}
				}
			}

			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(SAVE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
		return UPDATE;
	}

	public String deleteCouponById() {
		try {
			Coupon coupon = couponManagementController.doListCouponById(id);
			if (null != coupon) {
				couponManagementController.doDeleteCouponByEntity(coupon);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
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

	public List<ChannelDistributor> getChannelDistributorList() {
		return channelDistributorList;
	}

	public void setChannelDistributorList(List<ChannelDistributor> channelDistributorList) {
		this.channelDistributorList = channelDistributorList;
	}

}
