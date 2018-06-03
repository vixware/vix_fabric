/**
 * 
 */
package com.vix.drp.channel.entity;

import java.util.HashSet;
import java.util.Set;

import com.vix.common.share.entity.BaseEntity;

/**
 * 店铺类型
 * 
 * com.vix.drp.channel.entity.StoreType
 * 
 * @author zhanghaibing
 * 
 * @date 2014-7-3
 */
public class StoreType extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 网店类型
	 */
	private Long type;

	private Set<ChannelDistributor> channelDistributors = new HashSet<ChannelDistributor>();

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Set<ChannelDistributor> getChannelDistributors() {
		return channelDistributors;
	}

	public void setChannelDistributors(Set<ChannelDistributor> channelDistributors) {
		this.channelDistributors = channelDistributors;
	}

}
