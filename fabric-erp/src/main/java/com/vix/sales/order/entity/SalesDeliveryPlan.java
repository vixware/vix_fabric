package com.vix.sales.order.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

/**
 * 销售订单明细发运计划
 * @author Administrator
 *
 */
public class SalesDeliveryPlan extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 发运时间 */
	private Date deliveryTime;
	/** 国家 */
	private String country;
	/** 省 */
	private String province;
	/** 城市 */
	private String city;
	/** 目的地 */
	private String target;
	/** 承运人 */
	private String carrier;
	/** 收货人 */
	private String reciever;
	/** 销售订单 */
	private SalesOrder salesOrder;
	/** 发运计划明细 */
	private Set<SalesDeliveryPlanDetail> salesDeliveryPlanDetails = new HashSet<SalesDeliveryPlanDetail>();

	public SalesDeliveryPlan(){}

	public Date getDeliveryTime() {
		return deliveryTime;
	}
	
	public String getDeliveryTimeStr(){
		if(null != deliveryTime){
			return DateUtil.format(deliveryTime);
		}
		return "";
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getReciever() {
		return reciever;
	}

	public void setReciever(String reciever) {
		this.reciever = reciever;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}

	public Set<SalesDeliveryPlanDetail> getSalesDeliveryPlanDetails() {
		return salesDeliveryPlanDetails;
	}

	public void setSalesDeliveryPlanDetails(
			Set<SalesDeliveryPlanDetail> salesDeliveryPlanDetails) {
		this.salesDeliveryPlanDetails = salesDeliveryPlanDetails;
	}
}
