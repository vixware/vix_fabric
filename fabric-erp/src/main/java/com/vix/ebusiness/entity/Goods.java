package com.vix.ebusiness.entity;

import java.util.Date;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 
 * com.vix.E_business.entity.Goods
 *
 * @author zhanghaibing
 *
 * @date 2014年9月14日
 */
public class Goods extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long catalogId;// 分类标识
	private Long typeId;// 类型标识
	private Integer goodsClass;// 商品种类 0普通商品 1捆绑商品
	private String goodName;// 商品名称
	private String goodsCode;// 商品编号
	private Long defaultImageId;// 默认图片
	private Integer isMarketable;// 是否上架 1上架 0未上架
	private String intro;// 详细介绍
	private String unit;// 计量单位
	private Integer scoreSetting;// 积分设置
	private Double score;// 积分
	private String spec;// 规格
	private Date upTime;// 上架时间
	private Date downTime;// 下架时间
	private Integer state;// 状态
	private String goodsKeyWords;// 商品关键词
	private Integer isSpec;// 是否开启规格
	private Integer totalStore;// 库存（货品库存的总和）
	private String props;// 商品属性
	private Long defaultProductId;// 默认货品ID
	private String defaultProductOuterId; // 默认货品outerID
	private String params;// 详情参数
	private Integer warningthreshold;// 阀值
	private Integer storenum;
	private Integer probabilityofstockout;
	private String outerId;
	private String itemImage;//商品主图绝对地址
	private Integer releaseState; // 发布状态0未发布1已发布
	private String barCode; // 条形码
	private String manufacturers;// 生产厂商
	private String wrap; // 包装规格
	private Double length; // 长
	private Double wide; // 宽
	private Double height; // 高
	private String packListing; // 包装清单
	private String service; // 售后服务
	private String anotherName; // 别名
	private Double pricingWeight; // 计价重量
	private Integer isRatePlan; // 是否开启资费方案
	private Long ratePlanId; // 资费方案标识

	// 货品属性
	private Double mktprice;// 市场价
	private Double cost;// 成本价
	private Double price;// 销售价
	private Double weight;// 重量
	private String catalogName;// 分类名称
	private String typeName;// 类型名称
	private Date createTime;// 创建时间
	private String defaultImageUrl;
	private String channelName;
	private Long channelRelationId; // 关联关系

	private Long imageId;
	private Set<Products> subProducts;

	/*private Channel channel;*/

	private ChannelDistributor channelDistributor;

	public Long getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public Integer getGoodsClass() {
		return goodsClass;
	}

	public void setGoodsClass(Integer goodsClass) {
		this.goodsClass = goodsClass;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public Long getDefaultImageId() {
		return defaultImageId;
	}

	public void setDefaultImageId(Long defaultImageId) {
		this.defaultImageId = defaultImageId;
	}

	public Integer getIsMarketable() {
		return isMarketable;
	}

	public void setIsMarketable(Integer isMarketable) {
		this.isMarketable = isMarketable;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getScoreSetting() {
		return scoreSetting;
	}

	public void setScoreSetting(Integer scoreSetting) {
		this.scoreSetting = scoreSetting;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getItemImage() {
		return itemImage;
	}

	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}

	public Date getUpTime() {
		return upTime;
	}

	public void setUpTime(Date upTime) {
		this.upTime = upTime;
	}

	public Date getDownTime() {
		return downTime;
	}

	public void setDownTime(Date downTime) {
		this.downTime = downTime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getGoodsKeyWords() {
		return goodsKeyWords;
	}

	public void setGoodsKeyWords(String goodsKeyWords) {
		this.goodsKeyWords = goodsKeyWords;
	}

	public Integer getIsSpec() {
		return isSpec;
	}

	public void setIsSpec(Integer isSpec) {
		this.isSpec = isSpec;
	}

	public Integer getTotalStore() {
		return totalStore;
	}

	public void setTotalStore(Integer totalStore) {
		this.totalStore = totalStore;
	}

	public String getProps() {
		return props;
	}

	public void setProps(String props) {
		this.props = props;
	}

	public Long getDefaultProductId() {
		return defaultProductId;
	}

	public void setDefaultProductId(Long defaultProductId) {
		this.defaultProductId = defaultProductId;
	}

	public String getDefaultProductOuterId() {
		return defaultProductOuterId;
	}

	public void setDefaultProductOuterId(String defaultProductOuterId) {
		this.defaultProductOuterId = defaultProductOuterId;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Integer getWarningthreshold() {
		return warningthreshold;
	}

	public void setWarningthreshold(Integer warningthreshold) {
		this.warningthreshold = warningthreshold;
	}

	public Integer getStorenum() {
		return storenum;
	}

	public void setStorenum(Integer storenum) {
		this.storenum = storenum;
	}

	public Integer getProbabilityofstockout() {
		return probabilityofstockout;
	}

	public void setProbabilityofstockout(Integer probabilityofstockout) {
		this.probabilityofstockout = probabilityofstockout;
	}

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public Integer getReleaseState() {
		return releaseState;
	}

	public void setReleaseState(Integer releaseState) {
		this.releaseState = releaseState;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(String manufacturers) {
		this.manufacturers = manufacturers;
	}

	public String getWrap() {
		return wrap;
	}

	public void setWrap(String wrap) {
		this.wrap = wrap;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getWide() {
		return wide;
	}

	public void setWide(Double wide) {
		this.wide = wide;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public String getPackListing() {
		return packListing;
	}

	public void setPackListing(String packListing) {
		this.packListing = packListing;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getAnotherName() {
		return anotherName;
	}

	public void setAnotherName(String anotherName) {
		this.anotherName = anotherName;
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

	public Double getMktprice() {
		return mktprice;
	}

	public void setMktprice(Double mktprice) {
		this.mktprice = mktprice;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDefaultImageUrl() {
		return defaultImageUrl;
	}

	public void setDefaultImageUrl(String defaultImageUrl) {
		this.defaultImageUrl = defaultImageUrl;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Long getChannelRelationId() {
		return channelRelationId;
	}

	public void setChannelRelationId(Long channelRelationId) {
		this.channelRelationId = channelRelationId;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public Set<Products> getSubProducts() {
		return subProducts;
	}

	public void setSubProducts(Set<Products> subProducts) {
		this.subProducts = subProducts;
	}

	/*	public Channel getChannel() {
			return channel;
		}

		public void setChannel(Channel channel) {
			this.channel = channel;
		}*/

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}
