package com.vix.mdm.item.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseEntity;

/**
 * 商品sku
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.mdm.item.entity.Sku
 *
 * @date 2017年12月22日
 */
public class Sku extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String barCode;// 条形码
	private Double cost;// 成本价
	private Integer totalStore;// 可用库存
	private String storePlace;// 货位
	private Integer state;
	private String spec;// 规格
	private Integer ir;// 已发布
	private String sparebarcode;
	private Integer buyCount = 1;// 购买数量
	private Integer releaseState; // 0发布1同步
	private Integer warningThreshold; // 预警阀值
	private Integer stockOut; // 缺货量
	private Integer warehouseStore; // 仓库库存
	private Long targetProductId;
	private Integer shelf;
	private Integer isGoodsPropMarketable; // 商品属性是否上架
	private String outerOrderId;
	private Double returnAmount; // 退货金额
	private String goodsImg;
	private Long typeId;
	private Double pricingWeight; // 计价重量
	private Integer isRatePlan; // 是否开启资费方案
	private Long ratePlanId; // 一次性费用
	private String specVlaueString;
	private Integer channelTypeId;
	private Integer sheetAmount;// 账面数量
	private Integer realAmount;// 实际数量
	private Integer diffAmount;// 差量
	private String goodsspecname;
	private Long channelRelationId; // 关联网店货品标识
	private String goodsCode;
	private String bn;
	private String outerId;
	private String productOutId;
	private String goodsName;
	private String specStr;
	private Double mktprice;
	private Double price;
	private Integer store;
	private String otherName;
	private Double weight;
	private String url;
	private Integer isMarketable;
	private String channelName;
	private Date lastUpdateTime;
	/**
	 * 商品信息
	 */
	private Item item;

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Integer getTotalStore() {
		return totalStore;
	}

	public void setTotalStore(Integer totalStore) {
		this.totalStore = totalStore;
	}

	public String getStorePlace() {
		return storePlace;
	}

	public void setStorePlace(String storePlace) {
		this.storePlace = storePlace;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getSparebarcode() {
		return sparebarcode;
	}

	public void setSparebarcode(String sparebarcode) {
		this.sparebarcode = sparebarcode;
	}

	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	public Integer getReleaseState() {
		return releaseState;
	}

	public void setReleaseState(Integer releaseState) {
		this.releaseState = releaseState;
	}

	public Integer getWarningThreshold() {
		return warningThreshold;
	}

	public void setWarningThreshold(Integer warningThreshold) {
		this.warningThreshold = warningThreshold;
	}

	public Integer getStockOut() {
		return stockOut;
	}

	public void setStockOut(Integer stockOut) {
		this.stockOut = stockOut;
	}

	public Integer getWarehouseStore() {
		return warehouseStore;
	}

	public void setWarehouseStore(Integer warehouseStore) {
		this.warehouseStore = warehouseStore;
	}

	public Long getTargetProductId() {
		return targetProductId;
	}

	public void setTargetProductId(Long targetProductId) {
		this.targetProductId = targetProductId;
	}

	public Integer getIr() {
		return ir;
	}

	public void setIr(Integer ir) {
		this.ir = ir;
	}

	public Integer getShelf() {
		return shelf;
	}

	public void setShelf(Integer shelf) {
		this.shelf = shelf;
	}

	public Integer getIsGoodsPropMarketable() {
		return isGoodsPropMarketable;
	}

	public void setIsGoodsPropMarketable(Integer isGoodsPropMarketable) {
		this.isGoodsPropMarketable = isGoodsPropMarketable;
	}

	public String getOuterOrderId() {
		return outerOrderId;
	}

	public void setOuterOrderId(String outerOrderId) {
		this.outerOrderId = outerOrderId;
	}

	public Double getReturnAmount() {
		return returnAmount;
	}

	public void setReturnAmount(Double returnAmount) {
		this.returnAmount = returnAmount;
	}

	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Double getPricingWeight() {
		return pricingWeight;
	}

	public void setPricingWeight(Double pricingWeight) {
		this.pricingWeight = pricingWeight;
	}

	public Integer getIsRatePlan() {
		return isRatePlan;
	}

	public void setIsRatePlan(Integer isRatePlan) {
		this.isRatePlan = isRatePlan;
	}

	public Long getRatePlanId() {
		return ratePlanId;
	}

	public void setRatePlanId(Long ratePlanId) {
		this.ratePlanId = ratePlanId;
	}

	public String getSpecVlaueString() {
		return specVlaueString;
	}

	public void setSpecVlaueString(String specVlaueString) {
		this.specVlaueString = specVlaueString;
	}

	public Integer getChannelTypeId() {
		return channelTypeId;
	}

	public void setChannelTypeId(Integer channelTypeId) {
		this.channelTypeId = channelTypeId;
	}

	public Integer getSheetAmount() {
		return sheetAmount;
	}

	public void setSheetAmount(Integer sheetAmount) {
		this.sheetAmount = sheetAmount;
	}

	public Integer getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(Integer realAmount) {
		this.realAmount = realAmount;
	}

	public Integer getDiffAmount() {
		return diffAmount;
	}

	public void setDiffAmount(Integer diffAmount) {
		this.diffAmount = diffAmount;
	}

	public String getGoodsspecname() {
		return goodsspecname;
	}

	public void setGoodsspecname(String goodsspecname) {
		this.goodsspecname = goodsspecname;
	}

	public Long getChannelRelationId() {
		return channelRelationId;
	}

	public void setChannelRelationId(Long channelRelationId) {
		this.channelRelationId = channelRelationId;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getBn() {
		return bn;
	}

	public void setBn(String bn) {
		this.bn = bn;
	}

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public String getProductOutId() {
		return productOutId;
	}

	public void setProductOutId(String productOutId) {
		this.productOutId = productOutId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getSpecStr() {
		return specStr;
	}

	public void setSpecStr(String specStr) {
		this.specStr = specStr;
	}

	public Double getMktprice() {
		return mktprice;
	}

	public void setMktprice(Double mktprice) {
		this.mktprice = mktprice;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStore() {
		return store;
	}

	public void setStore(Integer store) {
		this.store = store;
	}

	public String getOtherName() {
		return otherName;
	}

	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getIsMarketable() {
		return isMarketable;
	}

	public void setIsMarketable(Integer isMarketable) {
		this.isMarketable = isMarketable;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(Item item) {
		this.item = item;
	}

}
