package com.vix.crm.business.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.vixdata.dataappinterface.IDataAccuracy;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.crm.business.controller.CouponController;
import com.vix.crm.business.entity.Coupon;
import com.vix.crm.business.entity.CouponDetail;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channel.entity.StoreType;

@Controller
@Scope("prototype")
public class CouponAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Autowired
	private CouponController couponController;
	@Autowired
	private IDataAccuracy dataAccuracy;

	private String id;
	private String parentId;
	private String treeType;
	private String pageNo;

	private Coupon coupon;
	/**
	 * 店铺
	 */
	private List<ChannelDistributor> channelDistributorList;

	@Override
	public String goList() {
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
						StoreType storeType = couponController.doListStoreTypeById(parentId);
						if (storeType != null) {
							Map<String, Object> p = getParams();
							p.put("type," + SearchCondition.ANYLIKE, "5");
							p.put("storeType.id," + SearchCondition.EQUAL, storeType.getId());
							List<ChannelDistributor> channelDistributorList = couponController.doListChannelDistributorList(p);
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
			String name = getRequestParameter("name");
			if (name != null && !"".equals(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			pager = couponController.doListCoupon(params, pager);
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
			channelDistributorList = couponController.doListChannelDistributorList(p);
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				coupon = couponController.doListCouponById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != coupon.getId() && !"".equals(coupon.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(coupon);
			coupon.setExpenditure(dataAccuracy.getAmountDecimal(coupon.getExpenditure()));
			coupon = couponController.doSaveCoupon(coupon);
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
							//处理修改留痕
							billMarkProcessController.processMark(couponDetail, updateField);
							couponDetail = couponController.doSaveCouponDetail(couponDetail);
						}
					}
				}
			}

			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
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
			Coupon coupon = couponController.doListCouponById(id);
			if (null != coupon) {
				couponController.doDeleteCouponByEntity(coupon);
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
