package com.vix.drp.consignmentOrder.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseBOEntity;
import com.vix.common.share.entity.CurrencyType;

/**
 * 代销订单
 * 
 * @author zhanghaibing
 * 
 * @date 2014-5-27
 */
public class ConsignmentOrder extends BaseBOEntity {

	private static final long serialVersionUID = 1L;

	/** 主题 */
	private String title;
	/** 订单创建日期 */
	private Date orderCreateDate;
	/** 单据日期 */
	private Date billDate;
	/** 成组编码 */
	private String groupCode;
	/** 客户公司 */
	private String customerCompany;
	/** 使用客户名称 */
	private String usageCustomer;
	/** 使用客户联系人 */
	private String ucContact;
	/** 使用客户区号 */
	private String ucPostCode;
	/** 使用客户地址 */
	private String ucAddress;
	/** 使用客户移动电话 */
	private String ucMobile;
	/** 使用客户电话 */
	private String ucPhone;
	/** 使用客户电子邮件 */
	private String ucEmail;
	/** 部门编码 */
	private String deptCode;
	/** 业务员编码 */
	private String salePersonCode;
	/** 采购订单标识ID */
	private String purchaseOrderCode;
	/** 业务类型 */
	private String bizType;
	/** 单据类型 */
	private String formType;
	/** 销售渠道 */
	private String saleChannel;
	/** 销售组织 */
	private String saleOrg;
	/** 是否冻结 */
	private String isFreeze;
	/** 订单日期 */
	private Date createTime;
	/** 交货库存组织 */
	private String deliveryOrg;
	/** 交货仓库 */
	private String deliveryWarehouse;
	/** 装运点 */
	private String loadPoint;
	/** 发运地 */
	private String source;
	/** 目的地 */
	private String target;
	/** 承运商 */
	private String carrier;
	/** 承运商编码 */
	private String carrierCode;
	/** 运输方式 */
	private String transferStyle;
	/** 计划发运日期 */
	private Date deliveryTimeInPlan;
	/** 承诺日期 */
	private Date promiseTime;
	/** 币种 */
	private CurrencyType currencyType;
	/** 付款条件 */
	private String payCondition;
	/** 版本 */
	private String version;
	/** 变更原因 */
	private String changeReason;
	/** 过账日期 */
	private Date postingTime;
	/** 交货日期 */
	private Date deliveryTime;
	/** 使用机器IP */
	private String ip;
	/** 流程阶段 */
	private String flowPhase;
	/** 金额 */
	private Double amount;
	/** 运费 */
	private Double freight;
	/** 税率 */
	private Double taxRate;
	/** 税额 */
	private Double tax;
	/** 金额总计 */
	private Double amountTotal;
	/** 删除标志 */
	private String isDeleted;
	/** 是否为临时对象 临时为：1 实际使用为空 */
	private String isTemp;
	/** 是否已经参与佣金几钻 参与为：1 未参与为：0 */
	private String isCommissionCalculation;
	/** 订单明细 */
	private Set<ConsignmentOrderItem> consignmentOrderItems = new HashSet<ConsignmentOrderItem>();

	public ConsignmentOrder() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getOrderCreateDate() {
		return orderCreateDate;
	}

	public void setOrderCreateDate(Date orderCreateDate) {
		this.orderCreateDate = orderCreateDate;
	}

	public Date getBillDate() {
		return billDate;
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

	public String getCustomerCompany() {
		return customerCompany;
	}

	public void setCustomerCompany(String customerCompany) {
		this.customerCompany = customerCompany;
	}

	public String getUsageCustomer() {
		return usageCustomer;
	}

	public void setUsageCustomer(String usageCustomer) {
		this.usageCustomer = usageCustomer;
	}

	public String getUcContact() {
		return ucContact;
	}

	public void setUcContact(String ucContact) {
		this.ucContact = ucContact;
	}

	public String getUcPostCode() {
		return ucPostCode;
	}

	public void setUcPostCode(String ucPostCode) {
		this.ucPostCode = ucPostCode;
	}

	public String getUcAddress() {
		return ucAddress;
	}

	public void setUcAddress(String ucAddress) {
		this.ucAddress = ucAddress;
	}

	public String getUcMobile() {
		return ucMobile;
	}

	public void setUcMobile(String ucMobile) {
		this.ucMobile = ucMobile;
	}

	public String getUcPhone() {
		return ucPhone;
	}

	public void setUcPhone(String ucPhone) {
		this.ucPhone = ucPhone;
	}

	public String getUcEmail() {
		return ucEmail;
	}

	public void setUcEmail(String ucEmail) {
		this.ucEmail = ucEmail;
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

	public String getPurchaseOrderCode() {
		return purchaseOrderCode;
	}

	public void setPurchaseOrderCode(String purchaseOrderCode) {
		this.purchaseOrderCode = purchaseOrderCode;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getSaleChannel() {
		return saleChannel;
	}

	public void setSaleChannel(String saleChannel) {
		this.saleChannel = saleChannel;
	}

	public String getSaleOrg() {
		return saleOrg;
	}

	public void setSaleOrg(String saleOrg) {
		this.saleOrg = saleOrg;
	}

	public String getIsFreeze() {
		return isFreeze;
	}

	public void setIsFreeze(String isFreeze) {
		this.isFreeze = isFreeze;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDeliveryOrg() {
		return deliveryOrg;
	}

	public void setDeliveryOrg(String deliveryOrg) {
		this.deliveryOrg = deliveryOrg;
	}

	public String getDeliveryWarehouse() {
		return deliveryWarehouse;
	}

	public void setDeliveryWarehouse(String deliveryWarehouse) {
		this.deliveryWarehouse = deliveryWarehouse;
	}

	public String getLoadPoint() {
		return loadPoint;
	}

	public void setLoadPoint(String loadPoint) {
		this.loadPoint = loadPoint;
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

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public String getCarrierCode() {
		return carrierCode;
	}

	public void setCarrierCode(String carrierCode) {
		this.carrierCode = carrierCode;
	}

	public String getTransferStyle() {
		return transferStyle;
	}

	public void setTransferStyle(String transferStyle) {
		this.transferStyle = transferStyle;
	}

	public Date getDeliveryTimeInPlan() {
		return deliveryTimeInPlan;
	}

	public void setDeliveryTimeInPlan(Date deliveryTimeInPlan) {
		this.deliveryTimeInPlan = deliveryTimeInPlan;
	}

	public Date getPromiseTime() {
		return promiseTime;
	}

	public void setPromiseTime(Date promiseTime) {
		this.promiseTime = promiseTime;
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

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public void setVersion(String version) {
		this.version = version;
	}

	public String getChangeReason() {
		return changeReason;
	}

	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}

	public Date getPostingTime() {
		return postingTime;
	}

	public void setPostingTime(Date postingTime) {
		this.postingTime = postingTime;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getFlowPhase() {
		return flowPhase;
	}

	public void setFlowPhase(String flowPhase) {
		this.flowPhase = flowPhase;
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

	public Double getFreight() {
		return freight;
	}

	public void setFreight(Double freight) {
		this.freight = freight;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public Double getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(Double amountTotal) {
		this.amountTotal = amountTotal;
	}

	public String getIsCommissionCalculation() {
		return isCommissionCalculation;
	}

	public void setIsCommissionCalculation(String isCommissionCalculation) {
		this.isCommissionCalculation = isCommissionCalculation;
	}

	public Set<ConsignmentOrderItem> getConsignmentOrderItems() {
		return consignmentOrderItems;
	}

	public void setConsignmentOrderItems(Set<ConsignmentOrderItem> consignmentOrderItems) {
		this.consignmentOrderItems = consignmentOrderItems;
	}

}
