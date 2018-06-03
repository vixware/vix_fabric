package com.vix.mdm.purchase.order.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.Organization;
import com.vix.common.share.entity.BaseBOEntity;
import com.vix.core.utils.NumberUtil;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.dtbcenter.vehiclemanagement.entity.Vehicle;
import com.vix.inventory.inbound.entity.StockRecords;
import com.vix.mdm.purchase.invoice.entity.PurchaseInvoice;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.mdm.srm.share.entity.Supplier;

/**
 * 采购订单
 * 
 * @类全名 com.vix.mdm.purchase.order.entity.PurchaseOrder
 *
 * @author zhanghaibing
 *
 * @date 2016年12月3日
 */
public class PurchaseOrder extends BaseBOEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * status 0,待配货 1,代发货 2,配送中 3,已完成 4,待分拣
	 */
	/** 单据成组编码 */
	private String groupCode;
	/** 业务类型编码 */
	private String businessTypeCode;
	/** 业务类型 */
	private String businessType;
	/** 单据类型编码 */
	private String orderTypeCode;
	/** 单据类型 */
	private String orderType;
	/**
	 * 供应商
	 */
	private Supplier supplier;
	/** 询价编码 */
	private String inquireCode;
	/** 询价主题 */
	private String inquireName;
	/** 联系人 */
	private String contactPerson;
	/** 需求部门代码 */
	private String requireDepartmentCode;
	/** 需求部门 */
	private String requireDepartment;
	/** 采购组织 */
	private String purchaseOrg;
	/** 采购组织code */
	private String purchaseOrgId;
	/** 采购人代码 */
	private String purchasePersonId;
	/** 采购人 */
	private String purchasePerson;
	/** 总额 */
	private Double totalAmount;
	/** 币种编码 */
	private String currencyCode;
	/** 币种 */
	private String currency;
	/** 税率 */
	private Double taxRate;
	/** 过账日期 */
	private Date postingDate;
	/** 交货日期 */
	private Date deliveryDate;
	/** 是否进口 */
	private String isImport;
	/**
	 * 采购订单创建类型标识 0：普通采购订单 1：来源自采购计划 2：来源自采购申请 3：来源自采购询价 4:来源于销售订单
	 */
	private Long mark;
	/** 项目 */
	private String project;
	/** 汇率 */
	private Double rate;
	/** 预付账款 */
	private Double prepayments;
	/** 付款周期 */
	private String payPeriod;
	/** 会计科目 */
	private String account;
	/** 付款人编码 */
	private String payerCode;
	/** 付款人名称 */
	private String payerName;
	/** 合同日期 */
	private Date contractTime;
	/** 承运人 */
	private String carrier;
	/** 小计 */
	private Double subtotal;
	/** 运费 */
	private Double shipping;
	/** 税额 */
	private Double tax;
	/**
	 * 合同ID
	 */
	private String contractId;
	/** 合同编码 */
	private String contractCode;
	/** 合同名称 */
	private String contractName;
	/** 采购订单明细 */
	private Set<PurchaseOrderLineItem> purchaseOrderLineItems = new HashSet<PurchaseOrderLineItem>();
	/** 交货地址 */
	private Set<ReceivedAddress> receivedAddresses = new HashSet<ReceivedAddress>();
	/** 发运计划 */
	private Set<DeliveryPlan> deliveryPlans = new HashSet<DeliveryPlan>();
	/** 采购发票 */
	private Set<PurchaseInvoice> purchaseInvoices = new HashSet<PurchaseInvoice>();
	/** 价格条件 */
	private Set<PriceConditions> priceConditions = new HashSet<PriceConditions>();
	/** 附件 */
	private Set<Attachments> attachments = new HashSet<Attachments>();
	/** 审批 */
	private Set<ApprovalOpinion> approvalOpinions = new HashSet<ApprovalOpinion>();

	private StockRecords stockRecords;

	private NvixOrderBatch nvixOrderBatch;
	/**
	 * 供货方
	 */
	private ChannelDistributor salesChannelDistributor;
	private Organization organization;
	/**
	 * 车辆
	 */
	private Vehicle vehicle;

	private double total;

	private RequireGoodsOrder requireGoodsOrder;
	/**
	 * 订单类型0,门店采购订单;1,总部采购订单;
	 */
	private String type;

	/**
	 * 是否进行入库操作:0,否;1,是 ;用来处理采购订单转入库单操作
	 */
	private String isInventory;
	/**
	 * 审批状态: 0:未提交 1:审批中 2:审批完成
	 */
	private String  approvalType;
	public double getTotal() {
		if (purchaseOrderLineItems != null) {
			double totalTax = 0;
			double totalFee = 0;
			for (PurchaseOrderLineItem detailItem : purchaseOrderLineItems) {
				Double price = detailItem.getPrice();
				Double amount = detailItem.getAmount();
				if (price != null && price > 0 && amount != null && amount > 0) {
					Double tax = detailItem.getTaxRate();
					if (tax == null)
						tax = 0d;

					double itemTotal = price * amount;
					double itemTax = itemTotal * tax / 100;

					totalFee += itemTotal;
					totalTax += itemTax;
				}
			}
			this.total = NumberUtil.round(totalFee + totalTax, 2, BigDecimal.ROUND_HALF_UP);
		}
		return total;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public StockRecords getStockRecords() {
		return stockRecords;
	}

	public void setStockRecords(StockRecords stockRecords) {
		this.stockRecords = stockRecords;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getRequireDepartmentCode() {
		return requireDepartmentCode;
	}

	public void setRequireDepartmentCode(String requireDepartmentCode) {
		this.requireDepartmentCode = requireDepartmentCode;
	}

	public String getRequireDepartment() {
		return requireDepartment;
	}

	public void setRequireDepartment(String requireDepartment) {
		this.requireDepartment = requireDepartment;
	}

	/**
	 * @return the organization
	 */
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * @param organization
	 *            the organization to set
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getPurchasePersonId() {
		return purchasePersonId;
	}

	public void setPurchasePersonId(String purchasePersonId) {
		this.purchasePersonId = purchasePersonId;
	}

	public String getPurchasePerson() {
		return purchasePerson;
	}

	public void setPurchasePerson(String purchasePerson) {
		this.purchasePerson = purchasePerson;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public Date getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(Date postingDate) {
		this.postingDate = postingDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getIsImport() {
		return isImport;
	}

	public void setIsImport(String isImport) {
		this.isImport = isImport;
	}

	public Long getMark() {
		return mark;
	}

	public void setMark(Long mark) {
		this.mark = mark;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getPrepayments() {
		return prepayments;
	}

	public void setPrepayments(Double prepayments) {
		this.prepayments = prepayments;
	}

	public String getPayPeriod() {
		return payPeriod;
	}

	public void setPayPeriod(String payPeriod) {
		this.payPeriod = payPeriod;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPayerCode() {
		return payerCode;
	}

	public void setPayerCode(String payerCode) {
		this.payerCode = payerCode;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public Date getContractTime() {
		return contractTime;
	}

	public void setContractTime(Date contractTime) {
		this.contractTime = contractTime;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getShipping() {
		return shipping;
	}

	public void setShipping(Double shipping) {
		this.shipping = shipping;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public Set<PurchaseOrderLineItem> getPurchaseOrderLineItems() {
		return purchaseOrderLineItems;
	}

	public void setPurchaseOrderLineItems(Set<PurchaseOrderLineItem> purchaseOrderLineItems) {
		this.purchaseOrderLineItems = purchaseOrderLineItems;
	}

	public Set<ReceivedAddress> getReceivedAddresses() {
		return receivedAddresses;
	}

	public void setReceivedAddresses(Set<ReceivedAddress> receivedAddresses) {
		this.receivedAddresses = receivedAddresses;
	}

	public Set<DeliveryPlan> getDeliveryPlans() {
		return deliveryPlans;
	}

	public void setDeliveryPlans(Set<DeliveryPlan> deliveryPlans) {
		this.deliveryPlans = deliveryPlans;
	}

	public Set<PurchaseInvoice> getPurchaseInvoices() {
		return purchaseInvoices;
	}

	public void setPurchaseInvoices(Set<PurchaseInvoice> purchaseInvoices) {
		this.purchaseInvoices = purchaseInvoices;
	}

	public Set<PriceConditions> getPriceConditions() {
		return priceConditions;
	}

	public void setPriceConditions(Set<PriceConditions> priceConditions) {
		this.priceConditions = priceConditions;
	}

	public Set<Attachments> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachments> attachments) {
		this.attachments = attachments;
	}

	public Set<ApprovalOpinion> getApprovalOpinions() {
		return approvalOpinions;
	}

	public void setApprovalOpinions(Set<ApprovalOpinion> approvalOpinions) {
		this.approvalOpinions = approvalOpinions;
	}

	public String getInquireCode() {
		return inquireCode;
	}

	public void setInquireCode(String inquireCode) {
		this.inquireCode = inquireCode;
	}

	public String getInquireName() {
		return inquireName;
	}

	public void setInquireName(String inquireName) {
		this.inquireName = inquireName;
	}

	public String getOrderTypeCode() {
		return orderTypeCode;
	}

	public void setOrderTypeCode(String orderTypeCode) {
		this.orderTypeCode = orderTypeCode;
	}

	public String getBusinessTypeCode() {
		return businessTypeCode;
	}

	public void setBusinessTypeCode(String businessTypeCode) {
		this.businessTypeCode = businessTypeCode;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getPurchaseOrg() {
		return purchaseOrg;
	}

	public void setPurchaseOrg(String purchaseOrg) {
		this.purchaseOrg = purchaseOrg;
	}

	/**
	 * @return the purchaseOrgId
	 */
	public String getPurchaseOrgId() {
		return purchaseOrgId;
	}

	/**
	 * @param purchaseOrgId
	 *            the purchaseOrgId to set
	 */
	public void setPurchaseOrgId(String purchaseOrgId) {
		this.purchaseOrgId = purchaseOrgId;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	/**
	 * @return the salesChannelDistributor
	 */
	public ChannelDistributor getSalesChannelDistributor() {
		return salesChannelDistributor;
	}

	public String getChannelDistributorName() {
		if (channelDistributor != null) {
			return channelDistributor.getName();
		}
		return "";
	}

	public String getSupplierName() {
		if (supplier != null) {
			return supplier.getName();
		}
		return "";
	}

	/**
	 * @param salesChannelDistributor
	 *            the salesChannelDistributor to set
	 */
	public void setSalesChannelDistributor(ChannelDistributor salesChannelDistributor) {
		this.salesChannelDistributor = salesChannelDistributor;
	}

	public NvixOrderBatch getNvixOrderBatch() {
		return nvixOrderBatch;
	}

	public void setNvixOrderBatch(NvixOrderBatch nvixOrderBatch) {
		this.nvixOrderBatch = nvixOrderBatch;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public RequireGoodsOrder getRequireGoodsOrder() {
		return requireGoodsOrder;
	}

	public void setRequireGoodsOrder(RequireGoodsOrder requireGoodsOrder) {
		this.requireGoodsOrder = requireGoodsOrder;
	}

	/**
	 * @return the isInventory
	 */
	public String getIsInventory() {
		return isInventory;
	}

	/**
	 * @param isInventory
	 *            the isInventory to set
	 */
	public void setIsInventory(String isInventory) {
		this.isInventory = isInventory;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getApprovalType() {
		return approvalType;
	}

	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}
}