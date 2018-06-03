/**
 * 
 */
package com.vix.drp.rides.entity;

import java.util.Date;

import com.vix.common.share.entity.BaseBOEntity;

/**
 * 游乐设施 com.vix.drp.rides.entity.Rides
 * 
 * @author zhanghaibing
 * 
 * @date 2014-6-15
 */
public class Rides extends BaseBOEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 客户端
	 */
	private String mandt;
	/**
	 * 编码
	 */
	private String parentid;
	/**
	 * 机台编码
	 */
	private String macid;
	/**
	 * 机台名称
	 */
	private String macname;
	/**
	 * 机台类型编码
	 */
	private String mactypeid;
	/**
	 * 机台厂商编号
	 */
	private String macfactoryId;
	/**
	 * 是否启用
	 */
	private String isused;
	/**
	 * 价格
	 */
	private Double price;
	/**
	 * 时间
	 */
	private Long useTime;
	/**
	 * 图片
	 */
	private String picture;
	/**
	 * 机种
	 */
	private String model;
	/**
	 * 厂家
	 */
	private String producer;

	private Date manufactureDate;

	public String getMandt() {
		return mandt;
	}

	public void setMandt(String mandt) {
		this.mandt = mandt;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getMacid() {
		return macid;
	}

	public void setMacid(String macid) {
		this.macid = macid;
	}

	public String getMacname() {
		return macname;
	}

	public void setMacname(String macname) {
		this.macname = macname;
	}

	public String getMactypeid() {
		return mactypeid;
	}

	public void setMactypeid(String mactypeid) {
		this.mactypeid = mactypeid;
	}

	public String getMacfactoryId() {
		return macfactoryId;
	}

	public void setMacfactoryId(String macfactoryId) {
		this.macfactoryId = macfactoryId;
	}

	public String getIsused() {
		return isused;
	}

	public void setIsused(String isused) {
		this.isused = isused;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getUseTime() {
		return useTime;
	}

	public void setUseTime(Long useTime) {
		this.useTime = useTime;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Date getManufactureDate() {
		return manufactureDate;
	}

	public void setManufactureDate(Date manufactureDate) {
		this.manufactureDate = manufactureDate;
	}

}
