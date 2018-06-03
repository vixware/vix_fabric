/**   
* @Title: ContractStatistics.java 
* @Package com.vix.contract.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenzhengwen
* @author w_a_533@163.com   
* @date 2013-6-17 下午2:07:13  
*/
package com.vix.contract.mamanger.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * @ClassName: ContractStatistics
 * @Description:合同统计 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-6-17 下午2:07:13
 */
public class ContractStatistics extends BaseEntity {

	private static final long serialVersionUID = -5040735123831679418L;
	/**
	 * 合同编号
	 */
	private String contractNumber;
	/**
	 * 合同名称
	 */
	private String contractName;
	/**
	 * 完成形式
	 */
	private String completedForms;
	/**
	 * 项目子类
	 */
	private String projectSubclass;
	/**
	 * 单位
	 */
	private String unit;
	/**
	 * 承办人
	 */
	private String undertaker;
	/**
	 * 拟稿人
	 */
	private String draftPeople;
	/**
	 * 总金额
	 */
	private Double totalAmount;
	/**
	 * 已收付金额
	 */
	private Double alreadyIncomePayMoney;
	/**
	 * 剩余金额
	 */
	private Double surplusMoney;
	/**
	 * 签订时间
	 */
	private Date signedTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 供方名称
	 */
	private String suppliersName;
	/**
	 * 需方名称
	 */
	private String demandSideName;
	/**
	 * 备注
	 */
	private String remark;
	
	public ContractStatistics() {
		super();
	}
	
	public ContractStatistics(String id) {
		super();
		setId(id);
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getCompletedForms() {
		return completedForms;
	}

	public void setCompletedForms(String completedForms) {
		this.completedForms = completedForms;
	}

	public String getProjectSubclass() {
		return projectSubclass;
	}

	public void setProjectSubclass(String projectSubclass) {
		this.projectSubclass = projectSubclass;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getUndertaker() {
		return undertaker;
	}

	public void setUndertaker(String undertaker) {
		this.undertaker = undertaker;
	}

	public String getDraftPeople() {
		return draftPeople;
	}

	public void setDraftPeople(String draftPeople) {
		this.draftPeople = draftPeople;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getAlreadyIncomePayMoney() {
		return alreadyIncomePayMoney;
	}

	public void setAlreadyIncomePayMoney(Double alreadyIncomePayMoney) {
		this.alreadyIncomePayMoney = alreadyIncomePayMoney;
	}

	public Double getSurplusMoney() {
		return surplusMoney;
	}

	public void setSurplusMoney(Double surplusMoney) {
		this.surplusMoney = surplusMoney;
	}

	public Date getSignedTime() {
		return signedTime;
	}

	public void setSignedTime(Date signedTime) {
		this.signedTime = signedTime;
	}

	@Override
	public Date getEndTime() {
		return endTime;
	}

	@Override
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getSuppliersName() {
		return suppliersName;
	}

	public void setSuppliersName(String suppliersName) {
		this.suppliersName = suppliersName;
	}

	public String getDemandSideName() {
		return demandSideName;
	}

	public void setDemandSideName(String demandSideName) {
		this.demandSideName = demandSideName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
