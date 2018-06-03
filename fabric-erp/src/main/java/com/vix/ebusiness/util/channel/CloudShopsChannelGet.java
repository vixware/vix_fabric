package com.vix.ebusiness.util.channel;

import com.ec.api.CloudShopsAccountToken;
import com.ec.api.CloudShopsChannel;
import com.ec.api.CloudShopsException;
import com.vix.drp.channel.entity.ChannelDistributor;

import net.sf.json.JSONObject;

public class CloudShopsChannelGet {

	/**
	 * 获取渠道信息
	 * 
	 * @param channelIds
	 * @throws CloudShopsException
	 */
	public static CloudShopsChannel getChannel(ChannelDistributor channelDistributor) throws CloudShopsException {
		CloudShopsChannel cloudShopsChannel = null;
		/*
		 * MultiKey key = new MultiKey(CloudShopsClientAdapter.class.getName(),
		 * channel.getTenantId(), channel.getId()); Cache cache =
		 * CacheManager.getContent(key); if (cache != null &&
		 * !cache.isExpired()) { cloudShopsChannel = (CloudShopsChannel)
		 * cache.getValue(); return cloudShopsChannel; }
		 */
		try {
			if (channelDistributor != null) {
				cloudShopsChannel = new CloudShopsChannel();
				/**
				 * @TODO
				 */
				// cloudShopsChannel.setChannelId(channelDistributor.getId());
				cloudShopsChannel.setChannelProps(channelDistributor.getChannelProps());
				cloudShopsChannel.setChannelTypeId(channelDistributor.getChannelTypeId());
				cloudShopsChannel.setChannelClass(channelDistributor.getChannelClass());
				cloudShopsChannel.setChannelCode(channelDistributor.getChannelCode());
				cloudShopsChannel.setChannelName(channelDistributor.getChannelName());
				CloudShopsAccountToken token = new CloudShopsAccountToken();
				try {
					JSONObject channelProps = JSONObject.fromObject(cloudShopsChannel.getChannelProps());
					if (channelProps != null) {
						if (channelProps.has("app_id")) {
							token.setAppKey(channelProps.getString("app_id"));
						}
						if (channelProps.has("app_secret")) {
							token.setAppSecret(channelProps.getString("app_secret"));
						}
						if (channelProps.has("nick")) {
							token.setNick(channelProps.getString("nick"));
						}
						if (channelProps.has("marketPlaceId")) {
							token.setMarketPlaceId(channelProps.getString("marketPlaceId"));
						}
						if (channelProps.has("sellerId")) {
							token.setSellerId(channelProps.getString("sellerId"));
						}
						if (channelProps.has("session_key")) {
							token.setSession(channelProps.getString("session_key"));
						}
						if (channelProps.has("apiVersion")) {
							int varsion = channelProps.getInt("apiVersion");
							if (varsion == 0)
								varsion = 1;
							token.setApiVersion(varsion);
						}
						if (channelProps.has("mallType")) {
							cloudShopsChannel.setMallType(channelProps.getInt("mallType"));
						}
					}
				} catch (Exception e) {
				}
				cloudShopsChannel.setAccountToken(token);
			}
		} catch (Exception e) {
			throw new CloudShopsException("cloudshops-0001", "获取渠道信息失败,详细信息为 :" + e.getMessage());
		}
		/*
		 * if (cloudShopsChannel != null) CacheManager.putContent(key,
		 * cloudShopsChannel, 5 * 60 * 1000);
		 */
		return cloudShopsChannel;
	}
}
