package com.vix.oa.adminMg.officeSupplies.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 
 * @ClassName: OfficeSuppliesBorrow
 * @Description: 办公用品借用明细 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-5-20 下午2:26:38
 */
public class OfficeSuppliesBorrow extends BaseEntity {
	private static final long serialVersionUID = -8891289921069733348L;

	/** 计量单位 */
	public String prickle;
	/** 办公用品名称 */
	public String officeName;
	/**借用数量 */
	public Double borrowNumber;
	/**归还数量 */
	public Double returnNumber;
	public Integer isDeduction;
	/** 当前库存数量 */
	public Double numberOfRecipients;
	/** 规格/型号 */
	public String model;
	/** 办公用品编码 */
	public Long officeSuppliesCode;
	/** 供应商 */
	public String supplier;
	/** 最低警戒库存 */
	public String lowestVigilance;
	/** 最高警戒库存 */
	public String maximumVigilance;
	/** 办公用品领用、借用、归还*/
	private OfficeSuppliesRegister officeSuppliesRegister;
	/**办公用品 */
	private OfficeSupplies officeSupplies;
	/** 办公用品日志明细*/
	private Set<OfficeList> officeList = new HashSet<OfficeList>();

	public String getPrickle() {
		return prickle;
	}

	public void setPrickle(String prickle) {
		this.prickle = prickle;
	}

	public Double getBorrowNumber() {
		return borrowNumber;
	}

	public void setBorrowNumber(Double borrowNumber) {
		this.borrowNumber = borrowNumber;
	}

	public OfficeSuppliesRegister getOfficeSuppliesRegister() {
		return officeSuppliesRegister;
	}

	public void setOfficeSuppliesRegister(OfficeSuppliesRegister officeSuppliesRegister) {
		this.officeSuppliesRegister = officeSuppliesRegister;
	}

	public OfficeSupplies getOfficeSupplies() {
		return officeSupplies;
	}

	public void setOfficeSupplies(OfficeSupplies officeSupplies) {
		this.officeSupplies = officeSupplies;
	}

	public Integer getIsDeduction() {
		return isDeduction;
	}

	public void setIsDeduction(Integer isDeduction) {
		this.isDeduction = isDeduction;
	}

	public Double getNumberOfRecipients() {
		return numberOfRecipients;
	}

	public void setNumberOfRecipients(Double numberOfRecipients) {
		this.numberOfRecipients = numberOfRecipients;
	}

	public Double getReturnNumber() {
		return returnNumber;
	}

	public void setReturnNumber(Double returnNumber) {
		this.returnNumber = returnNumber;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Long getOfficeSuppliesCode() {
		return officeSuppliesCode;
	}

	public void setOfficeSuppliesCode(Long officeSuppliesCode) {
		this.officeSuppliesCode = officeSuppliesCode;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getLowestVigilance() {
		return lowestVigilance;
	}

	public void setLowestVigilance(String lowestVigilance) {
		this.lowestVigilance = lowestVigilance;
	}

	public String getMaximumVigilance() {
		return maximumVigilance;
	}

	public void setMaximumVigilance(String maximumVigilance) {
		this.maximumVigilance = maximumVigilance;
	}

	public Set<OfficeList> getOfficeList() {
		return officeList;
	}

	public void setOfficeList(Set<OfficeList> officeList) {
		this.officeList = officeList;
	}

}
