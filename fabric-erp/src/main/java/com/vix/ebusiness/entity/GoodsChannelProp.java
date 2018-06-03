package com.vix.ebusiness.entity;

import java.util.Date;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 
 * com.vix.E_business.entity.GoodsChannelProp
 *
 * @author zhanghaibing
 *
 * @date 2014年9月14日
 */
public class GoodsChannelProp extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String outerId;

	private Long typeId;

	private String freightPayer = "seller";

	private Long postageId;

	private String spec;

	private String prop;

	private Date lastUpdateTime;

	private Date lastSyscTime;

	private Integer isSpec;

	private Double mktprice;

	private Double price;

	private Integer storeNum;

	private Integer isMarketable;

	private Integer state;

	private String defaultProductId;

	private Set<Products> subProducts;

	private Long region;

	private Integer releaseState;

	private String goodsName;

	private String channelName;

	private Integer selected;

	/*private Channel channel;*/

	private Goods goods;

	private ChannelDistributor channelDistributor;

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getFreightPayer() {
		return freightPayer;
	}

	public void setFreightPayer(String freightPayer) {
		this.freightPayer = freightPayer;
	}

	public Long getPostageId() {
		return postageId;
	}

	public void setPostageId(Long postageId) {
		this.postageId = postageId;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getProp() {
		return prop;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Date getLastSyscTime() {
		return lastSyscTime;
	}

	public void setLastSyscTime(Date lastSyscTime) {
		this.lastSyscTime = lastSyscTime;
	}

	public Integer getIsSpec() {
		return isSpec;
	}

	public void setIsSpec(Integer isSpec) {
		this.isSpec = isSpec;
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

	public Integer getStoreNum() {
		return storeNum;
	}

	public void setStoreNum(Integer storeNum) {
		this.storeNum = storeNum;
	}

	public Integer getIsMarketable() {
		return isMarketable;
	}

	public void setIsMarketable(Integer isMarketable) {
		this.isMarketable = isMarketable;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * @return the defaultProductId
	 */
	public String getDefaultProductId() {
		return defaultProductId;
	}

	/**
	 * @param defaultProductId
	 *            the defaultProductId to set
	 */
	public void setDefaultProductId(String defaultProductId) {
		this.defaultProductId = defaultProductId;
	}

	public Set<Products> getSubProducts() {
		return subProducts;
	}

	public void setSubProducts(Set<Products> subProducts) {
		this.subProducts = subProducts;
	}

	public Long getRegion() {
		return region;
	}

	public void setRegion(Long region) {
		this.region = region;
	}

	public Integer getReleaseState() {
		return releaseState;
	}

	public void setReleaseState(Integer releaseState) {
		this.releaseState = releaseState;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

	/*public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}*/

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}
