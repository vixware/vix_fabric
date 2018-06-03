package com.vix.ebusiness.itemcats.itemcatsdownload.processor;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ec.api.CloudShopsChannelConstants;
import com.ec.api.adapter.CloudShopsClientAdapter;
import com.ec.api.itemcats.domain.CloudItemCat;
import com.ec.api.itemcats.request.CloudItemcatsGetRequest;
import com.ec.api.itemcats.response.CloudItemcatsGetResponse;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.entity.GoodsType;
import com.vix.ebusiness.order.orderProcess.service.IOrderProcessService;
import com.vix.ebusiness.util.channel.CloudShopsChannelGet;

/**
 * 商品类目下载
 * 
 * com.vix.E_business.itemcats.itemcatsdownload.processor.
 * ItemcatsDownloadProcessor
 * 
 * @author zhanghaibing
 *
 * @date 2014年7月22日
 */
@Controller("itemcatsDownloadProcessor")
@Scope("prototype")
public class ItemcatsDownloadProcessor {
	@Autowired
	private IOrderProcessService orderProcessService;

	public void process(String channelId) {
		try {
			ChannelDistributor channel = orderProcessService.findEntityById(ChannelDistributor.class, channelId);
			CloudItemcatsGetRequest cloudItemcatsGetRequest = new CloudItemcatsGetRequest();
			CloudItemcatsGetResponse cloudItemcatsGetResponse = CloudShopsClientAdapter.getInstance().execute(cloudItemcatsGetRequest, CloudShopsChannelGet.getChannel(channel));

			if (cloudItemcatsGetResponse != null && cloudItemcatsGetResponse.isSuccess()) {
				List<CloudItemCat> cloudItemCats = cloudItemcatsGetResponse.getItemCats();
				if (cloudItemCats != null && cloudItemCats.size() > 0) {
					for (CloudItemCat cloudItemCat : cloudItemCats) {
						Long channelTypeId = cloudItemCat.getChannelTypeId();
						try {
							insertGoodsType(cloudItemCat);
							// 如果渠道类型是京东，则同时将类目下载至租户空间
							if (channelTypeId.equals(CloudShopsChannelConstants.JINGDONG))
								insertGoodsType(cloudItemCat);
							if (cloudItemcatsGetResponse.isHasNext()) {
								boolean isLast = cloudItemcatsGetResponse.isLast();
								getNextItemCats(isLast, cloudItemcatsGetRequest, cloudItemCat, channel);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getNextItemCats(boolean isLast, CloudItemcatsGetRequest cloudItemcatsGetRequest, CloudItemCat cloudItemCat, ChannelDistributor channel) throws Exception {
		if (isLast)
			cloudItemcatsGetRequest.setCids(String.valueOf(cloudItemCat.getTypeId()));
		else
			cloudItemcatsGetRequest.setParentCid(cloudItemCat.getParentTypeId());
		CloudItemcatsGetResponse cloudItemcatsGetResponse = CloudShopsClientAdapter.getInstance().execute(cloudItemcatsGetRequest, CloudShopsChannelGet.getChannel(channel));
		if (cloudItemcatsGetResponse != null && cloudItemcatsGetResponse.isSuccess()) {
			List<CloudItemCat> cloudItemCats = cloudItemcatsGetResponse.getItemCats();
			if (cloudItemCats != null && cloudItemCats.size() > 0) {
				for (CloudItemCat cloudItemCat1 : cloudItemCats) {
					Long channelTypeId = cloudItemCat1.getChannelTypeId();
					insertGoodsType(cloudItemCat1);
					// 如果渠道类型是京东，则同时将类目下载至租户空间
					if (channelTypeId.equals(CloudShopsChannelConstants.JINGDONG))
						insertGoodsType(cloudItemCat1);
				}
			}
		}

	}

	private void insertGoodsType(CloudItemCat cloudItemCat) throws Exception {
		GoodsType goodsType = new GoodsType();
		goodsType.setTypeId(cloudItemCat.getTypeId());
		goodsType.setChannelTypeId(cloudItemCat.getChannelTypeId());
		goodsType.setTypeName(cloudItemCat.getTypeName());
		goodsType.setIsPhysical(cloudItemCat.getIsPhysical());
		goodsType.setSchemaId(cloudItemCat.getSchemaId());
		goodsType.setTypeProps(cloudItemCat.getTypeProps());
		goodsType.setTypeSpec(cloudItemCat.getType_spec());
		goodsType.setTypeParams(cloudItemCat.getTypeParams());
		goodsType.setParentTypeId(cloudItemCat.getParentTypeId());
		goodsType.setIsParent(cloudItemCat.getIsParent());
		goodsType.setSortOrder(cloudItemCat.getSortOrder());
		goodsType.setSettings(cloudItemCat.getSettings());
		if ("normal".equals(cloudItemCat.getState())) {
			goodsType.setStatus("1");
		} else
			goodsType.setStatus("0");
		if (cloudItemCat.getLastUpdateTfime() != null && !"".equals(cloudItemCat.getLastUpdateTfime()))
			goodsType.setUpdateTime(cloudItemCat.getLastUpdateTfime());
		else
			goodsType.setUpdateTime(new Date());
		orderProcessService.merge(goodsType);
	}

}
