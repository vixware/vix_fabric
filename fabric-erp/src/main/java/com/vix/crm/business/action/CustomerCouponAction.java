package com.vix.crm.business.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.controller.CustomerCouponController;
import com.vix.crm.business.entity.CouponDetail;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channel.entity.StoreType;
import com.vix.mdm.crm.entity.CustomerAccount;

/**
 * 会员优惠券 com.vix.crm.business.action.CustomerCouponAction
 *
 * @author zhanghaibing
 *
 * @date 2015年1月28日
 */
@Controller
@Scope("prototype")
public class CustomerCouponAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CustomerCouponController customerCouponController;

	private String id;
	private String parentId;
	private String treeType;
	private String pageNo;
	private List<CustomerAccount> customerAccountList;
	private CustomerAccount customerAccount;

	@Override
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			customerAccountList = customerCouponController.doListCustomerAccountList(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			//排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				pager.setPageNo(Integer.parseInt(pageNo));
			}
			if (treeType != null && !"".equals(treeType)) {
				if ("S".equals(treeType)) {
					//根据商城类型获取商城店铺
					if (parentId != null && !"".equals(parentId)) {
						StoreType storeType = customerCouponController.doListStoreTypeById(parentId);
						if (storeType != null) {
							Map<String, Object> p = getParams();
							p.put("type," + SearchCondition.ANYLIKE, "5");
							p.put("storeType.id," + SearchCondition.EQUAL, storeType.getId());
							List<ChannelDistributor> channelDistributorList = customerCouponController.doListChannelDistributorList(p);
							String channelDistributorIds = "";
							if (channelDistributorList != null && channelDistributorList.size() > 0) {
								for (ChannelDistributor channelDistributor : channelDistributorList) {
									channelDistributorIds += "," + channelDistributor.getId();
								}
							}
							params.put("channelDistributor.id," + SearchCondition.IN, channelDistributorIds);
						}
					}
				} else if ("CH".equals(treeType)) {
					if (parentId != null && !"".equals(parentId)) {
						params.put("channelDistributor.id," + SearchCondition.EQUAL, parentId);
					}
				}
			}
			pager = customerCouponController.doListCustomerAccount(params, pager);
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
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				customerAccount = customerCouponController.doListCustomerAccountById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String deleteCouponDetailById() {
		try {
			CouponDetail couponDetail = customerCouponController.doListCouponDetailById(id);
			if (null != couponDetail) {
				customerCouponController.doDeleteCouponDetailByEntity(couponDetail);
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

	public String goSearch() {
		return "goSearch";
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

	public List<CustomerAccount> getCustomerAccountList() {
		return customerAccountList;
	}

	public void setCustomerAccountList(List<CustomerAccount> customerAccountList) {
		this.customerAccountList = customerAccountList;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

}
