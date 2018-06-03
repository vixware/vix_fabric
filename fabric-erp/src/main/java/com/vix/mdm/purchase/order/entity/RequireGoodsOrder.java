package com.vix.mdm.purchase.order.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.vix.common.org.entity.Organization;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.share.entity.BaseBOEntity;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.Regional;
import com.vix.core.utils.DateUtil;
import com.vix.crm.business.entity.CouponDetail;
import com.vix.crm.project.entity.CrmProject;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.dtbcenter.vehiclemanagement.entity.Vehicle;
import com.vix.ebusiness.entity.DistributionCenter;
import com.vix.ebusiness.entity.OrderBatch;
import com.vix.ebusiness.entity.OrderPrintData;
import com.vix.ebusiness.option.entity.Logistics;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.mdm.crm.entity.ContactPerson;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.sales.order.entity.SalesAttachFile;
import com.vix.sales.order.entity.SalesDeliveryPlan;
import com.vix.sales.order.entity.SalesTicket;

/**
 * 
 * @类全名 com.vix.mdm.purchase.order.entity.RequireGoodsOrder
 *
 * @author zhanghaibing
 *
 * @date 2016年9月12日
 */
public class RequireGoodsOrder extends BaseBOEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * status 6退货
	 */
	/** 主题 */
	private String title;
	/** 中文首字母 */
	private String chineseFirstLetter;
	/** 订单创建日期 */
	private Date orderCreateDate;
	/** 项目 */
	private CrmProject crmProject;
	/** 单据日期 */
	private Date billDate;
	/** 成组编码 */
	private String groupCode;
	/** 客户 */
	private CustomerAccount customerAccount;
	/** 客户公司 */
	private String customerCompany;
	/** 地域 */
	private Regional regional;
	/** 使用客户名称 */
	private String usageCustomer;
	/** 使用客户联系人 */
	private String ucContact;
	/** 收银的批次 */
	private String cashbatch;
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
	/** 联系人 */
	private ContactPerson contactPerson;
	/** 销售渠道 */
	private String saleChannel;
	/** 销售组织 */
	private OrganizationUnit saleOrg;
	/** 销售人员 */
	private Employee salePerson;
	/** 是否冻结 0 未冻结 1 冻结 */
	private String isFreeze;
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
	/** 实付金额 */
	private Double payAmount;
	/** 满减金额 */
	private Double reduceAmount;
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
	/** 是否已经参与佣金计算 参与为：1 未参与为：0 */
	private String isCommissionCalculation;
	/** 是否已经退款计算 参与为：1 未参与为：0 */
	private String isSaleReturnBillCalculation;
	/** 是否完成 完成：1 未完成：0 */
	private String isComplate;
	/** 审批状态 0：未提交审批，1：审批中，2：审批完成 ，3：废弃 */
	private String auditStatus;
	/** 是否结算 0,未结算,1,已结算 */
	private String isSettlement;
	/** 是否使用优惠券 0,未使用,1,已使用  */
	private String isUsed;
	/** 支付方式 */
	private String payType;
	/** 订单明细 */
	private Set<RequireGoodsOrderItem> subRequireGoodsOrderItems = new HashSet<RequireGoodsOrderItem>();
	/** 单据附件 */
	private Set<SalesAttachFile> salesAttachFiles = new HashSet<SalesAttachFile>();
	/** 发运计划 */
	private Set<SalesDeliveryPlan> salesDeliveryPlans = new HashSet<SalesDeliveryPlan>();
	/** 发运计划 */
	private Set<SalesTicket> salesTickets = new HashSet<SalesTicket>();

	/** 网店 */
	private ChannelDistributor channelDistributor;
	/**
	 * 员工
	 */
	private Employee employee;
	/** 网店类型 */
	private Long channelTypeId;
	/**
	 * 订单外部标识
	 */
	private String outerId;
	/**
	 * 商品购买数量
	 */
	private Long num;
	/**
	 * 交易类型(POS会员消费1;门店消费2;)
	 */
	private String type;
	/**
	 * 商品价格
	 */
	private String price;
	/**
	 * 交易备注
	 */
	private String tradeMemo;
	/**
	 * 系统优惠金额
	 */
	private String discountFee;
	/**
	 * 商品总金额
	 */
	private String totalFee;
	/**
	 * 交易创建时间
	 */
	private Date created;
	/**
	 * 付款时间
	 */
	private Date payTime;
	/**
	 * 交易修改时间
	 */
	private Date modified;
	/**
	 * 买家留言
	 */
	private String buyerMessage;
	/**
	 * 卖家备注
	 */
	private String sellerMemo;
	/**
	 * 卖家备注旗帜
	 */
	private Long sellerFlag;
	/**
	 * 买家昵称
	 */
	private String buyerNick;
	/**
	 * 买家下单的地区
	 */
	private String buyerArea;
	/**
	 * 物流方式
	 */
	private String shippingType;
	/**
	 * 卖家手工调整金额
	 */
	private String adjustFee;
	/**
	 * 卖家昵称
	 */
	private String sellerNick;
	/**
	 * 买家备注
	 */
	private String buyerMemo;
	/**
	 * 实付金额
	 */
	private Double payment;
	/**
	 * 货到付款服务费
	 */
	private Long codFee;
	/**
	 * 卖家是否已评价
	 */
	private Integer sellerRate;
	/**
	 * 买家是否已评价
	 */
	private Integer buyerRate;
	/**
	 * 邮费
	 */
	private String postFee;
	/**
	 * 买家货到付款服务费。
	 */
	private String buyerCodFee;
	/**
	 * 卖家货到付款服务费。
	 */
	private String sellerCodFee;
	/**
	 * 收货人的姓名
	 */
	private String receiverName;
	/**
	 * 收货人省份
	 */
	private String receiverState;
	/**
	 * 交易号,用此字段记录拆单前的订单编码
	 */
	private String payNo;
	/**
	 * 收货人城市
	 */
	private String receiverCity;
	/**
	 * 收货人的所在地区
	 */
	private String receiverDistrict;
	/**
	 * 收货人的详细地址
	 */
	private String receiverAddress;
	/**
	 * 收货人的邮编
	 */
	private String receiverZip;
	/**
	 * 收货人的手机号码
	 */
	private String receiverMobile;
	/**
	 * 收货人的电话号码
	 */
	private String receiverPhone;
	/**
	 * 卖家发货时间
	 */
	private Date consignTime;
	/**
	 * 发票抬头
	 */
	private String invoiceName;
	/**
	 * 商品图片绝对途径
	 */
	private String picPath;

	/**
	 * 支付类型中文
	 */
	private String payTypeCn;
	/**
	 * 买家备注旗帜
	 */
	private Long buyerFlag;
	/**
	 * 快递代收款
	 */
	private Long expressAgencyfee;
	/**
	 * 买家使用积分
	 */
	private Integer pointFee;
	/**
	 * 买家实际使用积分
	 */
	private Integer realPointFee;
	/**
	 * 促销详细信息
	 */
	private String promotion;
	/**
	 * 处理状态: 1,未处理 2,已分拣 3,代发货 4,已发货 5,已完成 6,分拣中 7,已分仓 8,已打单
	 */
	private Integer dealState;
	/**
	 * 买家获得积分
	 */
	private Integer buyerObtainPointFee;
	/**
	 * 卖家配送方式
	 */
	private Integer sellerShippingType;
	/**
	 * 打印面单数据
	 */
	private OrderPrintData orderPrintData;

	private Integer orderDetailsNum;
	private Integer tradeClass;

	private String distributionCenterName;
	private String partnerName;
	private String sortingCode;
	private String distributionCenterCode;
	private String shippingCompanyCode;
	private String shippingCompanyName;

	private Integer packageNum;// 包裹数量
	private String shippingNo; // 物流单号
	private Integer outInteractingState;
	private String latestInteractingError; // 最近一次交互错误
	private Date latestInteractingErrorTime;// 最近一次交互错误时间
	/**
	 * 重量
	 */
	private Double weight;
	/**
	 * 区域
	 */
	private DistributionCenter distributionCenter;
	private Organization organization;
	/**
	 * 仓库
	 */
	private InvWarehouse invWarehouse;
	/**
	 * 物流
	 */
	private Logistics logistics;
	/**
	 * 订单批次
	 */
	private OrderBatch orderBatch;
	/**
	 * 订单明细
	 */
	private String orderDetails;

	/**
	 * 车辆
	 */
	private Vehicle vehicle;

	private String isSendToSupplier = "1";
	/**
	 * 供应商
	 */
	private Supplier supplier;
	/**
	 * 开始配送时间
	 */
	private Date startSendTime;
	/**
	 * 结束配送时间
	 */
	private Date endSendTime;
	/**
	 * 批次单
	 */
	private NvixOrderBatch nvixOrderBatch;
	/**
	 * 优惠券
	 */
	private CouponDetail couponDetail;
	/**
	 * 支付二维码地址
	 */
	private String qrCodePath;
	/**
	 * 会员折扣
	 */
	private Double discount;

	public RequireGoodsOrder() {
	}

	public String getTitle() {
		return title;
	}

	public String getWarehouseName() {
		if (invWarehouse != null) {
			return invWarehouse.getName();
		}
		return "";
	}

	public String getVehicleNO() {
		if (vehicle != null) {
			return vehicle.getVehicleNO();
		}
		return "";
	}

	public String getChannelDistributorName() {
		if (channelDistributor != null) {
			return channelDistributor.getName();
		}
		return "";
	}

	public String getIsSendToSupplier() {
		return isSendToSupplier;
	}

	public Double getWastageTotal() {
		double wastageTotal = 0;
		if (subRequireGoodsOrderItems != null && subRequireGoodsOrderItems.size() > 0) {
			for (RequireGoodsOrderItem requireGoodsOrderItem : subRequireGoodsOrderItems) {
				if (requireGoodsOrderItem != null && requireGoodsOrderItem.getAmount() != null && requireGoodsOrderItem.getPrice() != null && requireGoodsOrderItem.getActualAmount() != null) {
					wastageTotal += (requireGoodsOrderItem.getAmount() - requireGoodsOrderItem.getActualAmount()) * requireGoodsOrderItem.getPrice();
				}
			}
		}
		return wastageTotal;
	}

	public void setIsSendToSupplier(String isSendToSupplier) {
		this.isSendToSupplier = isSendToSupplier;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getChineseFirstLetter() {
		return chineseFirstLetter;
	}

	public void setChineseFirstLetter(String chineseFirstLetter) {
		this.chineseFirstLetter = chineseFirstLetter;
	}

	public CrmProject getCrmProject() {
		return crmProject;
	}

	public void setCrmProject(CrmProject crmProject) {
		this.crmProject = crmProject;
	}

	public Date getOrderCreateDate() {
		return orderCreateDate;
	}

	public String getOrderCreateDateStr() {
		if (null != orderCreateDate) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(orderCreateDate);
		}
		return "";
	}

	public String getDeliveryTimeStr() {
		if (null != deliveryTime) {
			return DateUtil.format(deliveryTime);
		}
		return "";
	}

	public String getDeliveryTimeTimeStr() {
		if (null != deliveryTime) {
			return DateUtil.formatTime(deliveryTime);
		}
		return "";
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

	public String getBillDateStr() {
		if (null != billDate) {
			return DateUtil.format(billDate);
		}
		return "";
	}

	public String getStartSendTimeStr() {
		if (null != startSendTime) {
			return DateUtil.format(startSendTime);
		}
		return "";
	}

	public String getStartSendTimeTimeStr() {
		if (null != startSendTime) {
			return DateUtil.formatTime(startSendTime);
		}
		return "";
	}

	public String getEndSendTimeStr() {
		if (null != endSendTime) {
			return DateUtil.format(endSendTime);
		}
		return "";
	}

	public String getEndSendTimeTimeStr() {
		if (null != endSendTime) {
			return DateUtil.formatTime(endSendTime);
		}
		return "";
	}

	public Date getStartSendTime() {
		return startSendTime;
	}

	public void setStartSendTime(Date startSendTime) {
		this.startSendTime = startSendTime;
	}

	public Date getEndSendTime() {
		return endSendTime;
	}

	public void setEndSendTime(Date endSendTime) {
		this.endSendTime = endSendTime;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}
	
	public String getCustomerName(){
		if(customerAccount != null){
			return customerAccount.getName();
		}
		return "";
	}
	public String getCustomerPhone(){
		if(customerAccount != null){
			return customerAccount.getMobilePhone();
		}
		return "";
	}
	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}

	public Regional getRegional() {
		return regional;
	}

	public void setRegional(Regional regional) {
		this.regional = regional;
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

	public String getCustomerCompany() {
		return customerCompany;
	}

	public void setCustomerCompany(String customerCompany) {
		this.customerCompany = customerCompany;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
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

	public String getCashbatch() {
		return cashbatch;
	}

	public void setCashbatch(String cashbatch) {
		this.cashbatch = cashbatch;
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

	public ContactPerson getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(ContactPerson contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getSaleChannel() {
		return saleChannel;
	}

	public void setSaleChannel(String saleChannel) {
		this.saleChannel = saleChannel;
	}

	public OrganizationUnit getSaleOrg() {
		return saleOrg;
	}

	public void setSaleOrg(OrganizationUnit saleOrg) {
		this.saleOrg = saleOrg;
	}

	public Employee getSalePerson() {
		return salePerson;
	}

	public void setSalePerson(Employee salePerson) {
		this.salePerson = salePerson;
	}

	public String getIsFreeze() {
		return isFreeze;
	}

	public void setIsFreeze(String isFreeze) {
		this.isFreeze = isFreeze;
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

	public Double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	public Double getReduceAmount() {
		return reduceAmount;
	}

	public void setReduceAmount(Double reduceAmount) {
		this.reduceAmount = reduceAmount;
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

	public String getIsSaleReturnBillCalculation() {
		return isSaleReturnBillCalculation;
	}

	public void setIsSaleReturnBillCalculation(String isSaleReturnBillCalculation) {
		this.isSaleReturnBillCalculation = isSaleReturnBillCalculation;
	}

	public String getIsComplate() {
		return isComplate;
	}

	public void setIsComplate(String isComplate) {
		this.isComplate = isComplate;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public Set<RequireGoodsOrderItem> getSubRequireGoodsOrderItems() {
		return subRequireGoodsOrderItems;
	}

	public void setSubRequireGoodsOrderItems(Set<RequireGoodsOrderItem> subRequireGoodsOrderItems) {
		this.subRequireGoodsOrderItems = subRequireGoodsOrderItems;
	}

	public Set<SalesAttachFile> getSalesAttachFiles() {
		return salesAttachFiles;
	}

	public void setSalesAttachFiles(Set<SalesAttachFile> salesAttachFiles) {
		this.salesAttachFiles = salesAttachFiles;
	}

	public Set<SalesDeliveryPlan> getSalesDeliveryPlans() {
		return salesDeliveryPlans;
	}

	public void setSalesDeliveryPlans(Set<SalesDeliveryPlan> salesDeliveryPlans) {
		this.salesDeliveryPlans = salesDeliveryPlans;
	}

	public Set<SalesTicket> getSalesTickets() {
		return salesTickets;
	}

	public void setSalesTickets(Set<SalesTicket> salesTickets) {
		this.salesTickets = salesTickets;
	}

	@Override
	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	@Override
	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Long getChannelTypeId() {
		return channelTypeId;
	}

	public void setChannelTypeId(Long channelTypeId) {
		this.channelTypeId = channelTypeId;
	}

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTradeMemo() {
		return tradeMemo;
	}

	public void setTradeMemo(String tradeMemo) {
		this.tradeMemo = tradeMemo;
	}

	public String getDiscountFee() {
		return discountFee;
	}

	public void setDiscountFee(String discountFee) {
		this.discountFee = discountFee;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getPayTime() {
		return payTime;
	}
	
	public String getPayDateStr() {
		if(payTime != null){
			return DateUtil.formatTime(payTime);
		}
		return "";
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getBuyerMessage() {
		return buyerMessage;
	}

	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}

	public String getSellerMemo() {
		return sellerMemo;
	}

	public void setSellerMemo(String sellerMemo) {
		this.sellerMemo = sellerMemo;
	}

	public Long getSellerFlag() {
		return sellerFlag;
	}

	public void setSellerFlag(Long sellerFlag) {
		this.sellerFlag = sellerFlag;
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public String getBuyerArea() {
		return buyerArea;
	}

	public void setBuyerArea(String buyerArea) {
		this.buyerArea = buyerArea;
	}

	public String getShippingType() {
		return shippingType;
	}

	public void setShippingType(String shippingType) {
		this.shippingType = shippingType;
	}

	public String getAdjustFee() {
		return adjustFee;
	}

	public void setAdjustFee(String adjustFee) {
		this.adjustFee = adjustFee;
	}

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	public String getBuyerMemo() {
		return buyerMemo;
	}

	public void setBuyerMemo(String buyerMemo) {
		this.buyerMemo = buyerMemo;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public Long getCodFee() {
		return codFee;
	}

	public void setCodFee(Long codFee) {
		this.codFee = codFee;
	}

	public Integer getSellerRate() {
		return sellerRate;
	}

	public void setSellerRate(Integer sellerRate) {
		this.sellerRate = sellerRate;
	}

	public Integer getBuyerRate() {
		return buyerRate;
	}

	public void setBuyerRate(Integer buyerRate) {
		this.buyerRate = buyerRate;
	}

	public String getPostFee() {
		return postFee;
	}

	public void setPostFee(String postFee) {
		this.postFee = postFee;
	}

	public String getBuyerCodFee() {
		return buyerCodFee;
	}

	public void setBuyerCodFee(String buyerCodFee) {
		this.buyerCodFee = buyerCodFee;
	}

	public String getSellerCodFee() {
		return sellerCodFee;
	}

	public void setSellerCodFee(String sellerCodFee) {
		this.sellerCodFee = sellerCodFee;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverState() {
		return receiverState;
	}

	public void setReceiverState(String receiverState) {
		this.receiverState = receiverState;
	}

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public String getReceiverCity() {
		return receiverCity;
	}

	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}

	public String getReceiverDistrict() {
		return receiverDistrict;
	}

	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public String getReceiverZip() {
		return receiverZip;
	}

	public void setReceiverZip(String receiverZip) {
		this.receiverZip = receiverZip;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public String getReceiverPhone() {
		return receiverPhone;
	}

	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}

	public Date getConsignTime() {
		return consignTime;
	}

	public void setConsignTime(Date consignTime) {
		this.consignTime = consignTime;
	}

	public String getInvoiceName() {
		return invoiceName;
	}

	public void setInvoiceName(String invoiceName) {
		this.invoiceName = invoiceName;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getPayTypeCn() {
		return payTypeCn;
	}

	public void setPayTypeCn(String payTypeCn) {
		this.payTypeCn = payTypeCn;
	}

	public Long getBuyerFlag() {
		return buyerFlag;
	}

	public void setBuyerFlag(Long buyerFlag) {
		this.buyerFlag = buyerFlag;
	}

	public Long getExpressAgencyfee() {
		return expressAgencyfee;
	}

	public void setExpressAgencyfee(Long expressAgencyfee) {
		this.expressAgencyfee = expressAgencyfee;
	}

	public Integer getPointFee() {
		return pointFee;
	}

	public void setPointFee(Integer pointFee) {
		this.pointFee = pointFee;
	}

	public Integer getRealPointFee() {
		return realPointFee;
	}

	public void setRealPointFee(Integer realPointFee) {
		this.realPointFee = realPointFee;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public Integer getDealState() {
		return dealState;
	}

	public void setDealState(Integer dealState) {
		this.dealState = dealState;
	}

	public Integer getBuyerObtainPointFee() {
		return buyerObtainPointFee;
	}

	public void setBuyerObtainPointFee(Integer buyerObtainPointFee) {
		this.buyerObtainPointFee = buyerObtainPointFee;
	}

	public Integer getSellerShippingType() {
		return sellerShippingType;
	}

	public void setSellerShippingType(Integer sellerShippingType) {
		this.sellerShippingType = sellerShippingType;
	}

	public OrderPrintData getOrderPrintData() {
		return orderPrintData;
	}

	public void setOrderPrintData(OrderPrintData orderPrintData) {
		this.orderPrintData = orderPrintData;
	}

	public Integer getOrderDetailsNum() {
		return orderDetailsNum;
	}

	public void setOrderDetailsNum(Integer orderDetailsNum) {
		this.orderDetailsNum = orderDetailsNum;
	}

	public Integer getTradeClass() {
		return tradeClass;
	}

	public void setTradeClass(Integer tradeClass) {
		this.tradeClass = tradeClass;
	}

	public String getDistributionCenterName() {
		return distributionCenterName;
	}

	public void setDistributionCenterName(String distributionCenterName) {
		this.distributionCenterName = distributionCenterName;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public String getSortingCode() {
		return sortingCode;
	}

	public void setSortingCode(String sortingCode) {
		this.sortingCode = sortingCode;
	}

	public String getDistributionCenterCode() {
		return distributionCenterCode;
	}

	public void setDistributionCenterCode(String distributionCenterCode) {
		this.distributionCenterCode = distributionCenterCode;
	}

	public String getShippingCompanyCode() {
		return shippingCompanyCode;
	}

	public void setShippingCompanyCode(String shippingCompanyCode) {
		this.shippingCompanyCode = shippingCompanyCode;
	}

	public String getShippingCompanyName() {
		return shippingCompanyName;
	}

	public void setShippingCompanyName(String shippingCompanyName) {
		this.shippingCompanyName = shippingCompanyName;
	}

	public Integer getPackageNum() {
		return packageNum;
	}

	public void setPackageNum(Integer packageNum) {
		this.packageNum = packageNum;
	}

	public String getShippingNo() {
		return shippingNo;
	}

	public void setShippingNo(String shippingNo) {
		this.shippingNo = shippingNo;
	}

	public Integer getOutInteractingState() {
		return outInteractingState;
	}

	public void setOutInteractingState(Integer outInteractingState) {
		this.outInteractingState = outInteractingState;
	}

	public String getLatestInteractingError() {
		return latestInteractingError;
	}

	public void setLatestInteractingError(String latestInteractingError) {
		this.latestInteractingError = latestInteractingError;
	}

	public Date getLatestInteractingErrorTime() {
		return latestInteractingErrorTime;
	}

	public void setLatestInteractingErrorTime(Date latestInteractingErrorTime) {
		this.latestInteractingErrorTime = latestInteractingErrorTime;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public DistributionCenter getDistributionCenter() {
		return distributionCenter;
	}

	public void setDistributionCenter(DistributionCenter distributionCenter) {
		this.distributionCenter = distributionCenter;
	}

	public InvWarehouse getInvWarehouse() {
		return invWarehouse;
	}

	public void setInvWarehouse(InvWarehouse invWarehouse) {
		this.invWarehouse = invWarehouse;
	}

	public Logistics getLogistics() {
		return logistics;
	}

	public void setLogistics(Logistics logistics) {
		this.logistics = logistics;
	}

	public OrderBatch getOrderBatch() {
		return orderBatch;
	}

	public void setOrderBatch(OrderBatch orderBatch) {
		this.orderBatch = orderBatch;
	}

	/**
	 * @return the isSettlement
	 */
	public String getIsSettlement() {
		return isSettlement;
	}

	/**
	 * @param isSettlement
	 *            the isSettlement to set
	 */
	public void setIsSettlement(String isSettlement) {
		this.isSettlement = isSettlement;
	}

	public String getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}

	public NvixOrderBatch getNvixOrderBatch() {
		return nvixOrderBatch;
	}

	public void setNvixOrderBatch(NvixOrderBatch nvixOrderBatch) {
		this.nvixOrderBatch = nvixOrderBatch;
	}

	public CouponDetail getCouponDetail() {
		return couponDetail;
	}

	public void setCouponDetail(CouponDetail couponDetail) {
		this.couponDetail = couponDetail;
	}

	public String getQrCodePath() {
		return qrCodePath;
	}

	public void setQrCodePath(String qrCodePath) {
		this.qrCodePath = qrCodePath;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

}