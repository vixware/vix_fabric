package com.vix.nvix.fabric.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 票据
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.nvix.fabric.entity.FabricBill
 *
 * @date 2018年3月8日
 */
public class FabricBill extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String billInfoId;// 票据号码
	private String billInfoAmt;// 票据金额
	private String billInfoType;// 票据类型
	private String billInfoIsseDate;// 票据出票日期
	private String billInfoDueDate;// 票据到期日期
	private String drwrCmId;// 出票人证件号码
	private String drwrAcct;// 出票人名称
	private String accptrCmId;// 承兑人证件号码
	private String accptrAcct;// 承兑人名称
	private String pyeeCmId;// 收款人证件号码
	private String pyeeAcct;// 收款人名称
	private String hodrCmId;// 持票人证件号码
	private String hodrAcct;// 持票人名称
	private String waitEndorserCmId;// 待背书人证件号码
	private String waitEndorserAcct;// 待背书人名称
	private String rejectEndorserCmId;// 拒绝背书人证件号码
	private String rejectEndorserAcct;// 拒绝背书人名称
	private String state;// 票据状态
	/**
	 * 票据背书历史
	 */
	private Set<FabricHistoryItem> subFabricHistoryItems = new HashSet<FabricHistoryItem>();

	public FabricBill() {
	}

	/**
	 * @return the billInfoId
	 */
	public String getBillInfoId() {
		return billInfoId;
	}

	/**
	 * @param billInfoId
	 *            the billInfoId to set
	 */
	public void setBillInfoId(String billInfoId) {
		this.billInfoId = billInfoId;
	}

	/**
	 * @return the billInfoAmt
	 */
	public String getBillInfoAmt() {
		return billInfoAmt;
	}

	/**
	 * @param billInfoAmt
	 *            the billInfoAmt to set
	 */
	public void setBillInfoAmt(String billInfoAmt) {
		this.billInfoAmt = billInfoAmt;
	}

	/**
	 * @return the billInfoType
	 */
	public String getBillInfoType() {
		return billInfoType;
	}

	/**
	 * @param billInfoType
	 *            the billInfoType to set
	 */
	public void setBillInfoType(String billInfoType) {
		this.billInfoType = billInfoType;
	}

	/**
	 * @return the billInfoIsseDate
	 */
	public String getBillInfoIsseDate() {
		return billInfoIsseDate;
	}

	/**
	 * @param billInfoIsseDate
	 *            the billInfoIsseDate to set
	 */
	public void setBillInfoIsseDate(String billInfoIsseDate) {
		this.billInfoIsseDate = billInfoIsseDate;
	}

	/**
	 * @return the billInfoDueDate
	 */
	public String getBillInfoDueDate() {
		return billInfoDueDate;
	}

	/**
	 * @param billInfoDueDate
	 *            the billInfoDueDate to set
	 */
	public void setBillInfoDueDate(String billInfoDueDate) {
		this.billInfoDueDate = billInfoDueDate;
	}

	/**
	 * @return the drwrCmId
	 */
	public String getDrwrCmId() {
		return drwrCmId;
	}

	/**
	 * @param drwrCmId
	 *            the drwrCmId to set
	 */
	public void setDrwrCmId(String drwrCmId) {
		this.drwrCmId = drwrCmId;
	}

	/**
	 * @return the drwrAcct
	 */
	public String getDrwrAcct() {
		return drwrAcct;
	}

	/**
	 * @param drwrAcct
	 *            the drwrAcct to set
	 */
	public void setDrwrAcct(String drwrAcct) {
		this.drwrAcct = drwrAcct;
	}

	/**
	 * @return the accptrCmId
	 */
	public String getAccptrCmId() {
		return accptrCmId;
	}

	/**
	 * @param accptrCmId
	 *            the accptrCmId to set
	 */
	public void setAccptrCmId(String accptrCmId) {
		this.accptrCmId = accptrCmId;
	}

	/**
	 * @return the accptrAcct
	 */
	public String getAccptrAcct() {
		return accptrAcct;
	}

	/**
	 * @param accptrAcct
	 *            the accptrAcct to set
	 */
	public void setAccptrAcct(String accptrAcct) {
		this.accptrAcct = accptrAcct;
	}

	/**
	 * @return the pyeeCmId
	 */
	public String getPyeeCmId() {
		return pyeeCmId;
	}

	/**
	 * @param pyeeCmId
	 *            the pyeeCmId to set
	 */
	public void setPyeeCmId(String pyeeCmId) {
		this.pyeeCmId = pyeeCmId;
	}

	/**
	 * @return the pyeeAcct
	 */
	public String getPyeeAcct() {
		return pyeeAcct;
	}

	/**
	 * @param pyeeAcct
	 *            the pyeeAcct to set
	 */
	public void setPyeeAcct(String pyeeAcct) {
		this.pyeeAcct = pyeeAcct;
	}

	/**
	 * @return the hodrCmId
	 */
	public String getHodrCmId() {
		return hodrCmId;
	}

	/**
	 * @param hodrCmId
	 *            the hodrCmId to set
	 */
	public void setHodrCmId(String hodrCmId) {
		this.hodrCmId = hodrCmId;
	}

	/**
	 * @return the hodrAcct
	 */
	public String getHodrAcct() {
		return hodrAcct;
	}

	/**
	 * @param hodrAcct
	 *            the hodrAcct to set
	 */
	public void setHodrAcct(String hodrAcct) {
		this.hodrAcct = hodrAcct;
	}

	/**
	 * @return the waitEndorserCmId
	 */
	public String getWaitEndorserCmId() {
		return waitEndorserCmId;
	}

	/**
	 * @param waitEndorserCmId
	 *            the waitEndorserCmId to set
	 */
	public void setWaitEndorserCmId(String waitEndorserCmId) {
		this.waitEndorserCmId = waitEndorserCmId;
	}

	/**
	 * @return the waitEndorserAcct
	 */
	public String getWaitEndorserAcct() {
		return waitEndorserAcct;
	}

	/**
	 * @param waitEndorserAcct
	 *            the waitEndorserAcct to set
	 */
	public void setWaitEndorserAcct(String waitEndorserAcct) {
		this.waitEndorserAcct = waitEndorserAcct;
	}

	/**
	 * @return the rejectEndorserCmId
	 */
	public String getRejectEndorserCmId() {
		return rejectEndorserCmId;
	}

	/**
	 * @param rejectEndorserCmId
	 *            the rejectEndorserCmId to set
	 */
	public void setRejectEndorserCmId(String rejectEndorserCmId) {
		this.rejectEndorserCmId = rejectEndorserCmId;
	}

	/**
	 * @return the rejectEndorserAcct
	 */
	public String getRejectEndorserAcct() {
		return rejectEndorserAcct;
	}

	/**
	 * @param rejectEndorserAcct
	 *            the rejectEndorserAcct to set
	 */
	public void setRejectEndorserAcct(String rejectEndorserAcct) {
		this.rejectEndorserAcct = rejectEndorserAcct;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the subFabricHistoryItems
	 */
	public Set<FabricHistoryItem> getSubFabricHistoryItems() {
		return subFabricHistoryItems;
	}

	/**
	 * @param subFabricHistoryItems
	 *            the subFabricHistoryItems to set
	 */
	public void setSubFabricHistoryItems(Set<FabricHistoryItem> subFabricHistoryItems) {
		this.subFabricHistoryItems = subFabricHistoryItems;
	}

}
