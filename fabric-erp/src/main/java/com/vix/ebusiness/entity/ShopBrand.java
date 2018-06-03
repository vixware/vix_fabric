package com.vix.ebusiness.entity;

import com.vix.common.share.entity.BaseEntity;
import com.vix.drp.channel.entity.ChannelDistributor;

/**
 * 品牌
 * 
 * com.vix.ebusiness.entity.ShopBrand
 *
 * @author zhanghaibing
 *
 * @date 2014年9月21日
 */
public class ShopBrand extends BaseEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * 对应属性的 pid:vid 串中的vid
	 */
	private Long vid;

	/**
	 * vid的值
	 */
	private String name;

	/**
	 * 品牌的属性id
	 */
	private Long pid;

	/**
	 * 品牌的属性名
	 */
	private String propName;

	private ChannelDistributor channelDistributor;

	public Long getVid() {
		return vid;
	}

	public void setVid(Long vid) {
		this.vid = vid;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getPropName() {
		return propName;
	}

	public void setPropName(String propName) {
		this.propName = propName;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

}
