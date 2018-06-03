package com.vix.sales.delivery.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseBOEntity;
import com.vix.common.share.entity.CMNApprovalRecord;
import com.vix.core.utils.DateUtil;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.sales.common.entity.SalesBillType;
import com.vix.sales.common.entity.SalesBusinessType;

/** 销售发货单(交货文件/出货通知单) */
public class DeliveryDocument extends BaseBOEntity {

	private static final long serialVersionUID = 1L;

	/** 发货单编码 */
	private String ddCode;
	/** 订单编号 */
	private String orderCode;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 单据日期 */
	private Date billDate;
	/** 成组编码 */
	private String groupCode;
	/** 部门编码 */
	private String deptCode;
	/** 业务员编码 */
	private String salePersonCode;
	/** 销售订单编号 */
	private String saleOrderCode;
	/** 业务类型 */
//	private String bizType;
	private SalesBusinessType salesBusinessType;
	/** 单据类型 */
//	private String formType;
	private SalesBillType salesBillType;
	/** 联系人 */
//	private ContactPerson contactPerson;
	/** 销售组织 */
	private String saleOrg;
	/** 销售人员 */
	private Employee salePerson;
	/** 发运地 */
	private String source;
	/** 目的地 */
	private String target;
	/** 金额 */
	private Double amount;
	/** 币种 */
//	private CurrencyType currencyType;
	/** 税率 */
	private Double tax;
	/** 付款条件 */
	private String payCondition;
	/** 发运状态 */
	private String deliveryStatus;
	/** 交货日期 */
	private Date deliveryTime;
	/** 发货日期 */
	private Date shippedDate;
	/** 明细 */
	private Set<DeliveryDocumentItem> deliveryDocumentItems = new HashSet<DeliveryDocumentItem>();
	/** 审批意见 */
	private Set<CMNApprovalRecord> approvalRecords = new HashSet<CMNApprovalRecord>();
	/** 附件 */
	private Set<Attachments> attachments = new HashSet<Attachments>();
	
	public DeliveryDocument(){}

	public String getDdCode() {
		return ddCode;
	}

	public void setDdCode(String ddCode) {
		this.ddCode = ddCode;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
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

	public Date getBillDate() {
		return billDate;
	}
	public String getBillDateStr() {
		if(billDate != null) {
			return DateUtil.format(billDate);
		}
		return "";
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getSalePersonCode() {
		return salePersonCode;
	}

	public void setSalePersonCode(String salePersonCode) {
		this.salePersonCode = salePersonCode;
	}

	public String getSaleOrderCode() {
		return saleOrderCode;
	}

	public void setSaleOrderCode(String saleOrderCode) {
		this.saleOrderCode = saleOrderCode;
	}

	public String getSaleOrg() {
		return saleOrg;
	}

	public void setSaleOrg(String saleOrg) {
		this.saleOrg = saleOrg;
	}

	public Employee getSalePerson() {
		return salePerson;
	}
	public String getSalePersonName() {
		if(salePerson != null){
			return salePerson.getName();
		}
		return "";
	}

	public void setSalePerson(Employee salePerson) {
		this.salePerson = salePerson;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public String getPayCondition() {
		return payCondition;
	}

	public void setPayCondition(String payCondition) {
		this.payCondition = payCondition;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}
	public String getDeliveryTimeStr() {
		if(deliveryTime != null) {
			return DateUtil.format(deliveryTime);
		}
		return "";
	}
	public String getDeliveryTimeTimeStr() {
		if(deliveryTime != null) {
			return DateUtil.formatTime(deliveryTime);
		}
		return "";
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Date getShippedDate() {
		return shippedDate;
	}
	public String getShippedDateStr() {
		if(shippedDate != null) {
			return DateUtil.format(shippedDate);
		}
		return "";
	}
	public String getShippedDateTimeStr() {
		if(shippedDate != null) {
			return DateUtil.formatTime(shippedDate);
		}
		return "";
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public Set<DeliveryDocumentItem> getDeliveryDocumentItems() {
		return deliveryDocumentItems;
	}

	public void setDeliveryDocumentItems(
			Set<DeliveryDocumentItem> deliveryDocumentItems) {
		this.deliveryDocumentItems = deliveryDocumentItems;
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

	public SalesBusinessType getSalesBusinessType() {
		return salesBusinessType;
	}

	public void setSalesBusinessType(SalesBusinessType salesBusinessType) {
		this.salesBusinessType = salesBusinessType;
	}

	public SalesBillType getSalesBillType() {
		return salesBillType;
	}

	public void setSalesBillType(SalesBillType salesBillType) {
		this.salesBillType = salesBillType;
	}
	
	
	
	
	
}
