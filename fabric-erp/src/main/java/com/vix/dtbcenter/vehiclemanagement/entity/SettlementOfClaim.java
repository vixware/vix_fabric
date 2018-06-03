/**
 * 
 */
package com.vix.dtbcenter.vehiclemanagement.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 理赔
 * 
 * @author zhanghaibing
 * 
 * @date 2013-9-2
 */
public class SettlementOfClaim extends BaseEntity {
	private static final long serialVersionUID = 4863315238220578611L;
	/** 理赔编号 */
	private String scCode;
	/** 理赔类型 */
	private String scType;
	/** 事故编号 */
	private String accidentCode;
	/** 车牌号 */
	private String vechileNubmer;
	/** 保险办理人 */
	private String InsurancePeople;
	/** 直接损失 */
	private String directLoss;
	/** 申请理赔时间 */
	private Date scTime;
	/** 申请理赔金额 */
	private String scAmount;
	/** 赔偿比率 */
	private String scRate;
	/** 赔偿方式 */
	private String scStyle;
	/** 赔偿金额 */
	private String claimAmount;
	/** 赔偿时间 */
	private Date claimTime;
	/** 领取人 */
	private String taker;
	/** 身份证号码 */
	private String identityCardNumber;
	/** 实际赔偿金额 */
	private String actualClaimAmount;
	/** 车辆 */
	private Vehicle vehicle;

	public String getScCode() {
		return scCode;
	}

	public void setScCode(String scCode) {
		this.scCode = scCode;
	}

	public String getScType() {
		return scType;
	}

	public void setScType(String scType) {
		this.scType = scType;
	}

	public String getAccidentCode() {
		return accidentCode;
	}

	public void setAccidentCode(String accidentCode) {
		this.accidentCode = accidentCode;
	}

	public String getVechileNubmer() {
		return vechileNubmer;
	}

	public void setVechileNubmer(String vechileNubmer) {
		this.vechileNubmer = vechileNubmer;
	}

	public String getInsurancePeople() {
		return InsurancePeople;
	}

	public void setInsurancePeople(String insurancePeople) {
		InsurancePeople = insurancePeople;
	}

	public String getDirectLoss() {
		return directLoss;
	}

	public void setDirectLoss(String directLoss) {
		this.directLoss = directLoss;
	}

	public Date getScTime() {
		return scTime;
	}

	public void setScTime(Date scTime) {
		this.scTime = scTime;
	}

	public String getScAmount() {
		return scAmount;
	}

	public void setScAmount(String scAmount) {
		this.scAmount = scAmount;
	}

	public String getScRate() {
		return scRate;
	}

	public void setScRate(String scRate) {
		this.scRate = scRate;
	}

	public String getScStyle() {
		return scStyle;
	}

	public void setScStyle(String scStyle) {
		this.scStyle = scStyle;
	}

	public String getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(String claimAmount) {
		this.claimAmount = claimAmount;
	}

	public Date getClaimTime() {
		return claimTime;
	}

	public void setClaimTime(Date claimTime) {
		this.claimTime = claimTime;
	}

	public String getTaker() {
		return taker;
	}

	public void setTaker(String taker) {
		this.taker = taker;
	}

	public String getIdentityCardNumber() {
		return identityCardNumber;
	}

	public void setIdentityCardNumber(String identityCardNumber) {
		this.identityCardNumber = identityCardNumber;
	}

	public String getActualClaimAmount() {
		return actualClaimAmount;
	}

	public void setActualClaimAmount(String actualClaimAmount) {
		this.actualClaimAmount = actualClaimAmount;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

}
