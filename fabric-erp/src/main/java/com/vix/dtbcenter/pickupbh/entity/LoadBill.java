/**
 * 
 */
package com.vix.dtbcenter.pickupbh.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseBOEntity;

/**
 * 提货单
 * 
 * @author zhanghaibing
 * 
 * @date 2013-9-1
 */
public class LoadBill extends BaseBOEntity {
	private static final long serialVersionUID = -480897768142444116L;
	/** 托单编号 */
	private String loadBillCode;
	/** 委托日期 */
	private Date delegateDate;
	/** 主要运输方式 */
	private String mainTransferType;
	/** 托运方 */
	private String shipper;
	/** 紧急程度 */
	private String emergency;
	/** 结算方式 */
	private String settlingAccountsMethod;
	/** 业务员 */
	private String counterman;
	/** 作业要求 */
	private String operateRequirement;
	/** 运输性质 */
	private String transferProperty;

	private Set<LoadBillItem> loadBillItems = new HashSet<LoadBillItem>();

	public String getLoadBillCode() {
		return loadBillCode;
	}

	public void setLoadBillCode(String loadBillCode) {
		this.loadBillCode = loadBillCode;
	}

	public Date getDelegateDate() {
		return delegateDate;
	}

	public void setDelegateDate(Date delegateDate) {
		this.delegateDate = delegateDate;
	}

	public String getMainTransferType() {
		return mainTransferType;
	}

	public void setMainTransferType(String mainTransferType) {
		this.mainTransferType = mainTransferType;
	}

	public String getShipper() {
		return shipper;
	}

	public void setShipper(String shipper) {
		this.shipper = shipper;
	}

	public String getEmergency() {
		return emergency;
	}

	public void setEmergency(String emergency) {
		this.emergency = emergency;
	}

	public String getSettlingAccountsMethod() {
		return settlingAccountsMethod;
	}

	public void setSettlingAccountsMethod(String settlingAccountsMethod) {
		this.settlingAccountsMethod = settlingAccountsMethod;
	}

	public String getCounterman() {
		return counterman;
	}

	public void setCounterman(String counterman) {
		this.counterman = counterman;
	}

	public String getOperateRequirement() {
		return operateRequirement;
	}

	public void setOperateRequirement(String operateRequirement) {
		this.operateRequirement = operateRequirement;
	}

	public String getTransferProperty() {
		return transferProperty;
	}

	public void setTransferProperty(String transferProperty) {
		this.transferProperty = transferProperty;
	}

	public Set<LoadBillItem> getLoadBillItems() {
		return loadBillItems;
	}

	public void setLoadBillItems(Set<LoadBillItem> loadBillItems) {
		this.loadBillItems = loadBillItems;
	}

}
