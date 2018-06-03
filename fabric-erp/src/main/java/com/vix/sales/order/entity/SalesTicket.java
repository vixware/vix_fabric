package com.vix.sales.order.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;

public class SalesTicket extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Date planTicketDate;  //计划开票日期
	private String title;         //名称（发票抬头）
	private String content;       //开票内容[物料或者服务项目]
	private String taxpayerPlayer;//纳税人识别号[非必填项]
	private String bank;          //开户行及帐号
	private String bankAccount;   //税率
	private Double taxRate;       //税额
	private Double ticketAmount;  //发票金额
	private Long ticketCount;     //开票数量
	private String memo;          //描述[可不填]
	private String ticketType;    //开票类型
	private String isFreeze;      //是否冻结
	/** 销售订单 */
	private SalesOrder salesOrder;
	/** 发票明细 */
	private Set<SalesTicketDetail> salesTicketDetails = new HashSet<SalesTicketDetail>();
	
	public SalesTicket(){}

	public Date getPlanTicketDate() {
		return planTicketDate;
	}
	
	public String getPlanTicketDateStr(){
		if(null != planTicketDate){
			return DateUtil.format(planTicketDate);
		}
		return "";
	}

	public void setPlanTicketDate(Date planTicketDate) {
		this.planTicketDate = planTicketDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTaxpayerPlayer() {
		return taxpayerPlayer;
	}

	public void setTaxpayerPlayer(String taxpayerPlayer) {
		this.taxpayerPlayer = taxpayerPlayer;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public Double getTicketAmount() {
		return ticketAmount;
	}

	public void setTicketAmount(Double ticketAmount) {
		this.ticketAmount = ticketAmount;
	}

	public Long getTicketCount() {
		return ticketCount;
	}

	public void setTicketCount(Long ticketCount) {
		this.ticketCount = ticketCount;
	}

	@Override
	public String getMemo() {
		return memo;
	}

	@Override
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getIsFreeze() {
		return isFreeze;
	}

	public void setIsFreeze(String isFreeze) {
		this.isFreeze = isFreeze;
	}

	public Set<SalesTicketDetail> getSalesTicketDetails() {
		return salesTicketDetails;
	}

	public void setSalesTicketDetails(Set<SalesTicketDetail> salesTicketDetails) {
		this.salesTicketDetails = salesTicketDetails;
	}

	public SalesOrder getSalesOrder() {
		return salesOrder;
	}

	public void setSalesOrder(SalesOrder salesOrder) {
		this.salesOrder = salesOrder;
	}
}
