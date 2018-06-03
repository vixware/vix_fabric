package com.vix.ebusiness.itemcats.itemcatsdownload.processor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ec.api.adapter.CloudShopsClientAdapter;
import com.ec.api.item.domain.CloudBrand;
import com.ec.api.itemcats.domain.CloudItemCat;
import com.ec.api.itemcats.domain.CloudSellerAuthorize;
import com.ec.api.itemcats.request.CloudItemcatsAuthorizeGetRequest;
import com.ec.api.itemcats.request.CloudItemcatsGetRequest;
import com.ec.api.itemcats.response.CloudItemcatsAuthorizeGetResponse;
import com.ec.api.itemcats.response.CloudItemcatsGetResponse;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.entity.ItemCat;
import com.vix.ebusiness.entity.ShopBrand;
import com.vix.ebusiness.order.orderProcess.service.IOrderProcessService;
import com.vix.ebusiness.util.channel.CloudShopsChannelGet;

/**
 * 
 * 
 * com.vix.ebusiness.itemcats.itemcatsdownload.processor.
 * ItemCatsAuthorizeDownloadProcessor
 *
 * @author zhanghaibing
 *
 * @date 2014年9月21日
 */
@Controller("itemCatsAuthorizeDownloadProcessor")
@Scope("prototype")
public class ItemCatsAuthorizeDownloadProcessor {
	@Autowired
	private IOrderProcessService orderProcessService;

	public void process(String channelId) {
		try {
			CloudItemcatsAuthorizeGetRequest tradesSoldGetRequest = new CloudItemcatsAuthorizeGetRequest();
			ChannelDistributor channel = orderProcessService.findEntityById(ChannelDistributor.class, channelId);
			CloudItemcatsAuthorizeGetResponse tradesSoldGetResponse = CloudShopsClientAdapter.getInstance().execute(tradesSoldGetRequest, CloudShopsChannelGet.getChannel(channel));
			if (tradesSoldGetResponse.isSuccess()) {
				CloudSellerAuthorize cloudSellerAuthorize = tradesSoldGetResponse.getAuthorize();
				List<CloudItemCat> cloudItemCats = cloudSellerAuthorize.getItemCats();
				if (cloudItemCats != null && cloudItemCats.size() > 0) {
					for (CloudItemCat cloudItemCat : cloudItemCats) {
						Long channelTypeId = cloudItemCat.getChannelTypeId();
						insertGoodsType(cloudItemCat, channelTypeId, channel);
						if (channelTypeId == 1) { // 淘宝，递归查询
							getNextItemCats(tradesSoldGetRequest, cloudItemCat, channel);
						}
					}
				}
				List<CloudBrand> cloudBrands = cloudSellerAuthorize.getBrands();
				if (cloudBrands != null && cloudBrands.size() > 0) {
					for (CloudBrand cloudBrand : cloudBrands) {
						addCloudBrand(cloudBrand, channel);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param cloudBrand
	 * @param channelId
	 */
	private void addCloudBrand(CloudBrand cloudBrand, ChannelDistributor channelDistributor) {
		// 添加渠道授权品牌
		ShopBrand shopBrand = new ShopBrand();
		shopBrand.setName(cloudBrand.getName());
		shopBrand.setPid(cloudBrand.getpId());
		shopBrand.setPropName(cloudBrand.getPropName());
		shopBrand.setVid(cloudBrand.getvId());
		shopBrand.setChannelDistributor(channelDistributor);

	}

	private void getNextItemCats(CloudItemcatsAuthorizeGetRequest cloudItemcatsGetRequest, CloudItemCat cloudItemCat, ChannelDistributor channel) {

		try {
			CloudItemcatsGetRequest request = new CloudItemcatsGetRequest();
			request.setChannelId(cloudItemcatsGetRequest.getChannelId());
			request.setParentCid(cloudItemCat.getTypeId());
			request.setFields("cid,parent_cid,name,is_parent");
			CloudItemcatsGetResponse cloudItemcatsGetResponse = CloudShopsClientAdapter.getInstance().execute(request, CloudShopsChannelGet.getChannel(channel));
			if (cloudItemcatsGetResponse != null && cloudItemcatsGetResponse.isSuccess()) {
				List<CloudItemCat> cloudItemCats = cloudItemcatsGetResponse.getItemCats();
				if (cloudItemCats != null && cloudItemCats.size() > 0) {
					for (CloudItemCat cloudItemCat1 : cloudItemCats) {
						Long channelTypeId = cloudItemCat1.getChannelTypeId();
						insertGoodsType(cloudItemCat1, channelTypeId, channel);
						getNextItemCats(cloudItemcatsGetRequest, cloudItemCat1, channel);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertGoodsType(CloudItemCat cloudItemCat, Long channelTypeId, ChannelDistributor channelDistributor) {
		try {
			// 添加渠道授权类目
			ItemCat itemCat = new ItemCat();
			itemCat.setTypeId(cloudItemCat.getTypeId());
			itemCat.setTypeName(cloudItemCat.getTypeName());
			itemCat.setChannelTypeId(channelTypeId);
			itemCat.setParentTypeId(cloudItemCat.getParentTypeId());
			itemCat.setStatus("normal");
			itemCat.setTypeProps(cloudItemCat.getTypeProps());
			itemCat.setType_spec(cloudItemCat.getType_spec());
			itemCat.setChannelDistributor(channelDistributor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
