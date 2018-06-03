package com.vix.mdm.item.entity;

/**
 * 服务
 * 
 * @类全名 com.vix.mdm.item.entity.ServiceItem
 *
 * @author zhanghaibing
 *
 * @date 2017年9月27日
 */
public class ServiceItem extends ItemBaseEntity {

	private static final long serialVersionUID = 1L;

	/** status 是否启用 是，T 否,F */

	/** 通用名称 */
	private String gernalName;
	/** 服务内容 */
	private String serviceContent;
	/** 中文拼音简写 */
	private String chinaShortName;
	/** 英文名 */
	private String englishName;
	/** 服务助记码 */
	private String scode;
	/** 价格 */
	private Double price;
	/** 会员价.保留2位小数 */
	private Double vipPrice;
	/** 积分兑换 */
	private Long coinExchange;
	/** 赠送积分 */
	private Long giveIntegral;

	public ServiceItem() {
	}

	public String getServiceContent() {
		return serviceContent;
	}

	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
	}

	public String getChinaShortName() {
		return chinaShortName;
	}

	public void setChinaShortName(String chinaShortName) {
		this.chinaShortName = chinaShortName;
	}

	public String getScode() {
		return scode;
	}

	public void setScode(String scode) {
		this.scode = scode;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getGernalName() {
		return gernalName;
	}

	public void setGernalName(String gernalName) {
		this.gernalName = gernalName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public Double getVipPrice() {
		return vipPrice;
	}

	public void setVipPrice(Double vipPrice) {
		this.vipPrice = vipPrice;
	}

	public Long getCoinExchange() {
		return coinExchange;
	}

	public void setCoinExchange(Long coinExchange) {
		this.coinExchange = coinExchange;
	}

	public Long getGiveIntegral() {
		return giveIntegral;
	}

	public void setGiveIntegral(Long giveIntegral) {
		this.giveIntegral = giveIntegral;
	}
}