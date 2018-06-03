package com.vix.mdm.item.action.transport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PriceFixEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	/** 名称 :物料名称或整单*/
	private String name;
	/** 价格条件类型*/
	private String type;
	/** 优惠类型 返款,折扣,特殊客户 */
	private String discountType;
	/** 原始价格 */
	private Double price;
	/** 优惠后价格 */
	private Double disCountPrice;
	/** 折扣 */
	private Double disCount;
	/** 赠品信息 */
	private List<PriceFixGift> pfgList = new ArrayList<PriceFixGift>();
	
	public PriceFixEntity(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDisCountPrice() {
		return disCountPrice;
	}

	public void setDisCountPrice(Double disCountPrice) {
		this.disCountPrice = disCountPrice;
	}

	public Double getDisCount() {
		return disCount;
	}

	public void setDisCount(Double disCount) {
		this.disCount = disCount;
	}

	public List<PriceFixGift> getPfgList() {
		return pfgList;
	}

	public void setPfgList(List<PriceFixGift> pfgList) {
		this.pfgList = pfgList;
	}
	
	public boolean getHasGifts(){
		if(pfgList.size() > 0){
			return true;
		}else{
			return false;
		}
	}
}
