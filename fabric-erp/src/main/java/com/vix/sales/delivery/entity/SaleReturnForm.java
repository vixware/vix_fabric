package com.vix.sales.delivery.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseBOEntity;
import com.vix.common.share.entity.CMNApprovalRecord;
import com.vix.core.utils.DateUtil;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.sales.common.entity.ReturnGoodsType;

/** 销售退货单  */
public class SaleReturnForm extends BaseBOEntity {

	private static final long serialVersionUID = 1L;
	
	/** 退货订单编号 */
	private String returnOrderCode;
	/** 退货订单类型 */
//	private String returnOrderType;
	private ReturnGoodsType returnGoodsType;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 成组编码 */
	private String groupCode;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 开票客户 */
	private String billingCustomer;
	/** 开票客户编号 */
	private String billingCustomerCode;
	/** 来源 */
	private String source;
	/** 币种 */
//	private CurrencyType currencyType;
	/** 汇率 */
	private Double rate;
	/** 退货原因 */
	private String returnReason;
	/** 退货申请时间 */
	private Date returnAppTime;
	/** 退货时间 */
	private Date returnTime;
	/** 运输方式 */
	private String transferStyle;
	/** 销售人员 */
//	private Employee salePerson;
	/** 审核状态 */
	private String approvalStatus;		
	/** 明细 */
	private Set<SaleReturnFormItem> saleReturnFormItems = new HashSet<SaleReturnFormItem>();
	/** 审批意见 */
	private Set<CMNApprovalRecord> approvalRecords = new HashSet<CMNApprovalRecord>();
	/** 附件 */
	private Set<Attachments> attachments = new HashSet<Attachments>();
	
	/** 删除标志 */
	private String isDeleted;
	/** 是否为临时对象 临时为：1 实际使用为空 */
	private String isTemp;

	public SaleReturnForm(){}

	public String getReturnOrderCode() {
		return returnOrderCode;
	}

	public void setReturnOrderCode(String returnOrderCode) {
		this.returnOrderCode = returnOrderCode;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}
	public String getCustomerAccountName() {
		if(customerAccount != null) {
			return customerAccount.getName();
		}
		return "";
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getBillingCustomer() {
		return billingCustomer;
	}

	public void setBillingCustomer(String billingCustomer) {
		this.billingCustomer = billingCustomer;
	}

	public String getBillingCustomerCode() {
		return billingCustomerCode;
	}

	public void setBillingCustomerCode(String billingCustomerCode) {
		this.billingCustomerCode = billingCustomerCode;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public String getReturnReason() {
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public Date getReturnAppTime() {
		return returnAppTime;
	}

	public void setReturnAppTime(Date returnAppTime) {
		this.returnAppTime = returnAppTime;
	}

	public Date getReturnTime() {
		return returnTime;
	}
	public String getReturnTimeStr() {
		if(returnTime != null) {
			return DateUtil.format(returnTime);
		}
		return "";
	}
	public String getReturnAppTimeStr() {
		if(returnAppTime != null) {
			return DateUtil.format(returnAppTime);
		}
		return "";
	}
	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public String getTransferStyle() {
		return transferStyle;
	}

	public void setTransferStyle(String transferStyle) {
		this.transferStyle = transferStyle;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Set<SaleReturnFormItem> getSaleReturnFormItems() {
		return saleReturnFormItems;
	}

	public void setSaleReturnFormItems(Set<SaleReturnFormItem> saleReturnFormItems) {
		this.saleReturnFormItems = saleReturnFormItems;
	}

	public Set<CMNApprovalRecord> getApprovalRecords() {
		return approvalRecords;
	}

	public void setApprovalRecords(Set<CMNApprovalRecord> approvalRecords) {
		this.approvalRecords = approvalRecords;
	}

	public Set<Attachments> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachments> attachments) {
		this.attachments = attachments;
	}

	public ReturnGoodsType getReturnGoodsType() {
		return returnGoodsType;
	}

	public void setReturnGoodsType(ReturnGoodsType returnGoodsType) {
		this.returnGoodsType = returnGoodsType;
	}

	@Override
	public String getIsTemp() {
		return isTemp;
	}

	@Override
	public void setIsTemp(String isTemp) {
		this.isTemp = isTemp;
	}

	@Override
	public String getIsDeleted() {
		return isDeleted;
	}

	@Override
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
