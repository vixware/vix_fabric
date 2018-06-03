package com.vix.mdm.purchase.order.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.vix.common.share.entity.BaseEntity;
import com.vix.core.utils.DateUtil;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.srm.share.entity.Supplier;

/**
 * 
 * @类全名 com.vix.mdm.purchase.order.entity.RequireGoodsOrderItem
 *
 * @author zhanghaibing
 *
 * @date 2016年9月12日
 */
public class RequireGoodsOrderItem extends BaseEntity {

	private static final long serialVersionUID = 1L;
	/** 行类型 */
	private String orderItemTyoe;
	/** 销售订单编号 */
	private String poCode;
	/** 成组编码 */
	private String groupCode;
	/** 销售或者服务内容 */
	private String content;
	/** 物料名称 */
	private Item item;
	/** 物料编码 */
	private String itemCode;
	/** 规格型号 */
	private String specification;
	/** 要货数量 */
	private Double amount;
	/**
	 * 实际到货数量
	 */
	private Double actualAmount;
	/** 辅计算量 */
	private String assitAmount;
	/** 计量单位 */
	private String unit;
	/** 辅助计量单位 */
	private String assitUnit;
	/** 主辅计量单位换算率 */
	private String unitExchange;
	/** 税率 */
	private Double tax;
	/** 税额 */
	private Double taxAmount;
	/** 单价 */
	private Double price;
	/** 折扣 */
	private Double discount;
	/** 净单价 */
	private Double netPrice;
	/** 含税单价 */
	private Double taxPrice;
	/** 金额(净单价*数量) */
	private Double netTotal;
	/** 价税合计金额 */
	private Double taxTotal;
	/** 累计出库数量 */
	private Double sumInventoryAmount;
	/** 需求日期 */
	private Date requireDate;
	/** 项目 */
	private String project;
	/** 仓库 */
	private String wareHouse;
	/** 收货仓库 */
	private String recieveWareHouse;
	/** 仓库组织 */
	private String wareHouseOrg;
	/** 收获地址 */
	private String recieveAddress;
	/** 是否允许替换物料 */
	private Integer isUseReplace;
	/** 辅计算量 */
	private String replaceItemCode;
	/** 替换物料名称 */
	private String replaceItemName;
	/**
	 * 状态 private String stauts;
	 */
	/** 是否已经参与佣金计算 参与为：1 未参与为：0 */
	private String isCommissionCalculation;
	/** 是否已经退款计算 参与为：1 未参与为：0 */
	private String isSaleReturnBillCalculation;
	/** 销售订单 */
	private RequireGoodsOrder requireGoodsOrder;
	/** 是否是赠品 */
	private String isGift;
	/** 是否有赠品 */
	private String isHasGift;
	/** 对应的订单子项id */
	private String saleOrderItemId;

	/**
	 * 卖家手工调整金额，精确到2位小数，单位：元。
	 */
	private String adjustFee;
	/**
	 * 买家昵称
	 */
	private String buyerNick;
	/**
	 * 买家是否已经评价
	 */
	private Boolean buyerRate;
	/**
	 * 交易商品对应的类目标识
	 */
	private Long cid;
	/**
	 * 确认状态
	 */
	private Integer confirm;
	/**
	 * 支付状态
	 */
	private Integer payStatus;
	/**
	 * 发货状态
	 */
	private Integer shipStatus;
	/**
	 * 用户反馈
	 */
	private Integer userStatus;
	/**
	 * 是否配送
	 */
	private Integer isDelivery;
	/**
	 * 是否超卖
	 */
	private Integer isOverSold;
	/**
	 * 套餐标识
	 */
	private String itemMealId;
	/**
	 * 套餐名称
	 */
	private String itemMealName;

	/**
	 * 主订单编号
	 */
	private String tradeNo;

	/**
	 * 购买数量
	 */
	private Long num;
	/**
	 * 配货数量
	 */
	private Long pickingNum;
	/**
	 * 货号/商品编码
	 */
	private String bn;

	/**
	 * 商品名称
	 */
	private String title;

	/**
	 * 商家外部编码
	 */
	private String outerId;
	/**
	 * 外部商品标识
	 */
	private String outerGoodsId;
	/**
	 * 商家外部SKU编号
	 */
	private String outerSkuId;
	/**
	 * 实付金额
	 */
	private Double payment;
	/**
	 * 商品图片绝对路径
	 */
	private String picPath;
	/**
	 * 卖家昵称
	 */
	private String sellerNick;

	/**
	 * 卖家是否已经评价
	 */
	private Boolean sellerRate;

	/**
	 * 卖家类型
	 */
	private String sellerType;

	/**
	 * 商品最小库存单位标识
	 */
	private String skuId;
	/**
	 * SKU属性名称
	 */
	private String skuPropertiesName;
	/**
	 * 应付金额
	 */
	private String totalFee;

	/**
	 * 优惠金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
	 */
	private String discountFee;
	/**
	 * 会员信息
	 */
	private CustomerAccount customerAccount;
	/**
	 * 供应商
	 */
	private Supplier supplier;

	public RequireGoodsOrderItem() {
	}

	public String getOrderItemTyoe() {
		return orderItemTyoe;
	}

	public Double getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(Double actualAmount) {
		this.actualAmount = actualAmount;
	}

	public void setOrderItemTyoe(String orderItemTyoe) {
		this.orderItemTyoe = orderItemTyoe;
	}

	public String getPoCode() {
		return poCode;
	}

	public void setPoCode(String poCode) {
		this.poCode = poCode;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		if (null == amount) {
			return 0D;
		}
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getAssitAmount() {
		return assitAmount;
	}

	public void setAssitAmount(String assitAmount) {
		this.assitAmount = assitAmount;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getAssitUnit() {
		return assitUnit;
	}

	public void setAssitUnit(String assitUnit) {
		this.assitUnit = assitUnit;
	}

	public String getUnitExchange() {
		return unitExchange;
	}

	public void setUnitExchange(String unitExchange) {
		this.unitExchange = unitExchange;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	public Double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Double getPrice() {
		if (null == price) {
			return 0D;
		}
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(Double netPrice) {
		this.netPrice = netPrice;
	}

	public Double getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(Double taxPrice) {
		this.taxPrice = taxPrice;
	}

	public Double getNetTotal() {
		if (netTotal != null) {
			BigDecimal bg = new BigDecimal(netTotal);
			return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		}
		return netTotal;
	}

	public void setNetTotal(Double netTotal) {
		this.netTotal = netTotal;
	}

	public Double getTaxTotal() {
		return taxTotal;
	}

	public void setTaxTotal(Double taxTotal) {
		this.taxTotal = taxTotal;
	}

	public Double getSumInventoryAmount() {
		return sumInventoryAmount;
	}

	public void setSumInventoryAmount(Double sumInventoryAmount) {
		this.sumInventoryAmount = sumInventoryAmount;
	}

	public Date getRequireDate() {
		return requireDate;
	}

	public String getRequireDateStr() {
		if (null != requireDate) {
			return DateUtil.format(requireDate);
		}
		return "";
	}

	public void setRequireDate(Date requireDate) {
		this.requireDate = requireDate;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getWareHouse() {
		return wareHouse;
	}

	public void setWareHouse(String wareHouse) {
		this.wareHouse = wareHouse;
	}

	public String getRecieveWareHouse() {
		return recieveWareHouse;
	}

	public void setRecieveWareHouse(String recieveWareHouse) {
		this.recieveWareHouse = recieveWareHouse;
	}

	public String getWareHouseOrg() {
		return wareHouseOrg;
	}

	public void setWareHouseOrg(String wareHouseOrg) {
		this.wareHouseOrg = wareHouseOrg;
	}

	public String getRecieveAddress() {
		return recieveAddress;
	}

	public void setRecieveAddress(String recieveAddress) {
		this.recieveAddress = recieveAddress;
	}

	public Integer getIsUseReplace() {
		return isUseReplace;
	}

	public void setIsUseReplace(Integer isUseReplace) {
		this.isUseReplace = isUseReplace;
	}

	public String getReplaceItemCode() {
		return replaceItemCode;
	}

	public void setReplaceItemCode(String replaceItemCode) {
		this.replaceItemCode = replaceItemCode;
	}

	public String getReplaceItemName() {
		return replaceItemName;
	}

	public void setReplaceItemName(String replaceItemName) {
		this.replaceItemName = replaceItemName;
	}

	public RequireGoodsOrder getRequireGoodsOrder() {
		return requireGoodsOrder;
	}

	public void setRequireGoodsOrder(RequireGoodsOrder requireGoodsOrder) {
		this.requireGoodsOrder = requireGoodsOrder;
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

	public String getIsGift() {
		return isGift;
	}

	public void setIsGift(String isGift) {
		this.isGift = isGift;
	}

	public String getIsHasGift() {
		return isHasGift;
	}

	public void setIsHasGift(String isHasGift) {
		this.isHasGift = isHasGift;
	}

	public String getSaleOrderItemId() {
		return saleOrderItemId;
	}

	public void setSaleOrderItemId(String saleOrderItemId) {
		this.saleOrderItemId = saleOrderItemId;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getMeasureUnit() {
		if (null != item && null != item.getMeasureUnit()) {
			return item.getMeasureUnit().getName();
		}
		return unit;
	}

	public String getSkuCode() {
		if (null != item) {
			return item.getSkuCode();
		}
		return "";
	}

	public String getBarCode() {
		if (null != item) {
			return item.getBarCode();
		}
		return itemCode;
	}

	public String getItemCode() {
		return itemCode;
	}

	public String getItemName() {
		if (null != item) {
			return item.getName();
		}
		return "";
	}

	public String getAdjustFee() {
		return adjustFee;
	}

	public void setAdjustFee(String adjustFee) {
		this.adjustFee = adjustFee;
	}

	public String getBuyerNick() {
		return buyerNick;
	}

	public void setBuyerNick(String buyerNick) {
		this.buyerNick = buyerNick;
	}

	public Boolean getBuyerRate() {
		return buyerRate;
	}

	public void setBuyerRate(Boolean buyerRate) {
		this.buyerRate = buyerRate;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public Integer getConfirm() {
		return confirm;
	}

	public void setConfirm(Integer confirm) {
		this.confirm = confirm;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getShipStatus() {
		return shipStatus;
	}

	public void setShipStatus(Integer shipStatus) {
		this.shipStatus = shipStatus;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public Integer getIsDelivery() {
		return isDelivery;
	}

	public void setIsDelivery(Integer isDelivery) {
		this.isDelivery = isDelivery;
	}

	public Integer getIsOverSold() {
		return isOverSold;
	}

	public void setIsOverSold(Integer isOverSold) {
		this.isOverSold = isOverSold;
	}

	public String getItemMealId() {
		return itemMealId;
	}

	public void setItemMealId(String itemMealId) {
		this.itemMealId = itemMealId;
	}

	public String getItemMealName() {
		return itemMealName;
	}

	public void setItemMealName(String itemMealName) {
		this.itemMealName = itemMealName;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public Long getPickingNum() {
		return pickingNum;
	}

	public void setPickingNum(Long pickingNum) {
		this.pickingNum = pickingNum;
	}

	public String getBn() {
		return bn;
	}

	public void setBn(String bn) {
		this.bn = bn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public String getOuterGoodsId() {
		return outerGoodsId;
	}

	public void setOuterGoodsId(String outerGoodsId) {
		this.outerGoodsId = outerGoodsId;
	}

	public String getOuterSkuId() {
		return outerSkuId;
	}

	public void setOuterSkuId(String outerSkuId) {
		this.outerSkuId = outerSkuId;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getSellerNick() {
		return sellerNick;
	}

	public void setSellerNick(String sellerNick) {
		this.sellerNick = sellerNick;
	}

	public Boolean getSellerRate() {
		return sellerRate;
	}

	public void setSellerRate(Boolean sellerRate) {
		this.sellerRate = sellerRate;
	}

	public String getSellerType() {
		return sellerType;
	}

	public void setSellerType(String sellerType) {
		this.sellerType = sellerType;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getSkuPropertiesName() {
		return skuPropertiesName;
	}

	public void setSkuPropertiesName(String skuPropertiesName) {
		this.skuPropertiesName = skuPropertiesName;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}

	public String getDiscountFee() {
		return discountFee;
	}

	public void setDiscountFee(String discountFee) {
		this.discountFee = discountFee;
	}

	public CustomerAccount getCustomerAccount() {
		return customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}
	
	public String getIsServiceItem(){
		if(item != null){
			return item.getIsServiceProduct();
		}
		return "";
	}
}
