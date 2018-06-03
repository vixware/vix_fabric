package com.vix.oa.adminMg.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

public class ResourceRequest extends BaseEntity {
	private static final long serialVersionUID = -3943610172581798396L;
	/** 资源类别 */
	private String type;
	/** 资源型号 */
	private String model;
	/** 申请数量 */
	private Double amount;
	/** 计量单位 */
	private String unit;
	/** 申请日期 */
	private Date requestDate;
	/** 归还日期 */
	private Date repayDate;
	/** 使用年限 */
	private String life;
	/** 简介 */
	private String introduction;
	/** 存放地点 */
	private String address;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}

	public String getLife() {
		return life;
	}

	public void setLife(String life) {
		this.life = life;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
