package com.vix.nvix.chain.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.DateUtil;
import com.vix.core.web.Pager;
import com.vix.crm.business.entity.Coupon;
import com.vix.crm.business.entity.CouponDetail;
import com.vix.crm.member.entity.MemberTag;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.nvix.common.base.action.VixntBaseAction;
/**
 * 优惠券管理
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class NvixCouponManagementAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;

	private String id;

	private Coupon coupon;

	public void goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			String selectName = getDecodeRequestParameter("selectName");
			if(StringUtils.isNotEmpty(selectName)){
				params.put("name,"+SearchCondition.ANYLIKE, selectName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, Coupon.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				coupon = vixntBaseService.findEntityById(Coupon.class, id);
			} else {
				coupon = new Coupon();
				coupon.setStatus("1");
				coupon.setCode(VixUUID.createCode(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 保存优惠券方案
	 * @return
	 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(coupon.getId())) {
				isSave = false;
			}
			coupon.setStatus("1");
			coupon.setCode(VixUUID.createCode(10));
			initEntityBaseController.initEntityBaseAttribute(coupon);
			coupon = vixntBaseService.merge(coupon);
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
	}
	/**
	 * 生成优惠券
	 */
	public void createCoupon(){
		try {
			if(StringUtils.isNotEmpty(id)){
				coupon = vixntBaseService.findEntityById(Coupon.class, id);
				if(coupon != null){//根据会员标签发放优惠券
					if("1".equals(coupon.getEndmode())){
						String ids = coupon.getMemberTagsIds();
						if(null != ids && !"".equals(ids)){
							String [] tagsIds = ids.split(",");
							List<CustomerAccount> customerAccountList = new ArrayList<CustomerAccount>();
							for (String tagId : tagsIds) {
								if(null != tagId && !"".equals(tagId)){
									Map<String, Object> params = getParams();
									params.put("memberTag,"+SearchCondition.ANYLIKE, tagId);
									List<CustomerAccount> list = vixntBaseService.findAllByConditions(CustomerAccount.class, params);
									customerAccountList.addAll(list);
								}
							}
							if(customerAccountList.size() > 0){
								for (CustomerAccount customerAccount : customerAccountList) {
									if(null != customerAccount){
										CouponDetail couponDetail = new CouponDetail();
										SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
										couponDetail.setCode("C_"+sdf.format(new Date()));
										couponDetail.setCustomerAccount(customerAccount);
										couponDetail.setCoupon(coupon);
										couponDetail.setAmount(coupon.getAmount());
										couponDetail.setUserule(coupon.getCouponUseCondition());
										couponDetail.setIsUsed("0");
										couponDetail.setStatus("2");
										if("1".equals(coupon.getCouponUseCondition()) && coupon.getExpenditure() > 0){
											couponDetail.setExpenditure(coupon.getExpenditure());
										}
										couponDetail.setEffectiveDate(new Date());
										couponDetail.setCutOffDate(DateUtil.getNextday(new Date(), coupon.getCouponValidatePeriod()));
										couponDetail = vixntBaseService.merge(couponDetail);
									}
								}
								coupon.setQuantity((long)customerAccountList.size());
								coupon.setStatus("2");
								coupon.setUserStartDate(new Date());
								coupon = vixntBaseService.merge(coupon);
								renderText("优惠券发放成功!");
							}
						}
					}
					//会员领取
					if("2".equals(coupon.getEndmode())){
						if(null != coupon.getQuantity() && coupon.getQuantity() > 0){
							for (int i = 0; i < coupon.getQuantity(); i++) {
								CouponDetail couponDetail = new CouponDetail();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
								couponDetail.setStatus("2");
								couponDetail.setCode("C_"+sdf.format(new Date()));
								couponDetail.setCoupon(coupon);
								couponDetail.setIsUsed("0");
								couponDetail.setAmount(coupon.getAmount());
								couponDetail.setExpenditure(coupon.getExpenditure());
								couponDetail.setUserule(coupon.getCouponUseCondition());
								couponDetail.setEffectiveDate(new Date());
								couponDetail.setCutOffDate(DateUtil.getNextday(new Date(),coupon.getCouponValidatePeriod()));
								initEntityBaseController.initEntityBaseAttribute(couponDetail);
								couponDetail = vixntBaseService.merge(couponDetail);
							}
							//标识优惠券已经生成并已经发放
							coupon.setStatus("2");
							coupon.setUserStartDate(new Date());
							coupon.setUserEndDate(DateUtil.getNextday(new Date(),coupon.getCouponValidatePeriod()));
							coupon = vixntBaseService.merge(coupon);
							renderText("优惠券发放成功!");
						}
					}
					//营销引擎发放优惠券
					if("3".equals(coupon.getEndmode())){
						if(null != coupon.getQuantity() && coupon.getQuantity() > 0){
							for (int i = 0; i < coupon.getQuantity(); i++) {
								CouponDetail couponDetail = new CouponDetail();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSS");
								couponDetail.setStatus("2");
								couponDetail.setCode("C_"+sdf.format(new Date()));
								couponDetail.setCoupon(coupon);
								couponDetail.setIsUsed("0");
								couponDetail.setAmount(coupon.getAmount());
								couponDetail.setExpenditure(coupon.getExpenditure());
								couponDetail.setUserule(coupon.getCouponUseCondition());
								couponDetail.setEffectiveDate(new Date());
								couponDetail.setCutOffDate(DateUtil.getNextday(new Date(),coupon.getCouponValidatePeriod()));
								initEntityBaseController.initEntityBaseAttribute(couponDetail);
								couponDetail = vixntBaseService.merge(couponDetail);
							}
							//标识优惠券已经生成并已经发放
							coupon.setStatus("2");
							coupon.setUserStartDate(new Date());
							coupon.setUserEndDate(DateUtil.getNextday(new Date(),coupon.getCouponValidatePeriod()));
							coupon = vixntBaseService.merge(coupon);
							renderText("优惠券发放成功!");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteCouponById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				Coupon coupon = vixntBaseService.findEntityById(Coupon.class, id);
				if (null != coupon) {
					vixntBaseService.deleteByEntity(coupon);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public String goChooseMemberTag() {
		return "goChooseMemberTag";
	}

	/** 获取列表数据 */
	public void goMemberTagList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			String selectName = getDecodeRequestParameter("selectName");
			if (StringUtils.isNotEmpty(selectName)) {
				params.put("name," + SearchCondition.ANYLIKE, selectName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, MemberTag.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getCouponDetailList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			String customerAccountId = getDecodeRequestParameter("customerAccountId");
			if(StringUtils.isNotEmpty(customerAccountId)){
				params.put("customerAccount.id,"+SearchCondition.EQUAL, customerAccountId);
				pager = vixntBaseService.findPagerByHqlConditions(pager, CouponDetail.class, params);
			}
			String couponId = getDecodeRequestParameter("couponId");
			if(StringUtils.isNotEmpty(couponId)){
				params.put("coupon.id,"+SearchCondition.EQUAL, couponId);
				pager = vixntBaseService.findPagerByHqlConditions(pager, CouponDetail.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String show(){
		try {
			if (StringUtils.isNotEmpty(id)) {
				coupon = vixntBaseService.findEntityById(Coupon.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	public String goCouponDetailList(){
		
		return "goCouponDetailList";
	}
	public void getCouponDetailSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			pager.setOrderBy("desc");
			pager.setOrderField("id");
			pager = vixntBaseService.findPagerByHqlConditions(pager, CouponDetail.class, params);
			renderDataTable(pager);
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

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}
}
