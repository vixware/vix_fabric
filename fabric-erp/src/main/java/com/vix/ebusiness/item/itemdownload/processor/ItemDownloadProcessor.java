package com.vix.ebusiness.item.itemdownload.processor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ec.api.adapter.CloudShopsClientAdapter;
import com.ec.api.item.domain.Item;
import com.ec.api.item.domain.Sku;
import com.ec.api.item.request.CloudItemsDownSaleRequest;
import com.ec.api.item.request.ItemsOnsaleGetRequest;
import com.ec.api.item.response.CloudItemsDownSaleResponse;
import com.ec.api.item.response.ItemsOnsaleGetResponse;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.BaseEmployee;
import com.vix.common.vixlog.IOperateLog;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.entity.Goods;
import com.vix.ebusiness.entity.GoodsChannelProp;
import com.vix.ebusiness.entity.Products;
import com.vix.ebusiness.util.channel.CloudShopsChannelGet;

import net.sf.json.JSONArray;

/**
 * 商品下载处理
 * 
 * @author zhanghaibing
 * 
 * @date 2014-3-21
 */
@Controller("itemDownloadProcessor")
public class ItemDownloadProcessor {

	private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Autowired
	private IBaseHibernateService baseHibernateService;
	@Autowired
	public InitEntityBaseController initEntityBaseController;
	@Autowired
	public IOperateLog vixOperateLog;

	public void process(String channelId) throws Exception {

		// 上架商品
		ItemsOnsaleGetRequest itemsOnsaleGetRequest = new ItemsOnsaleGetRequest();
		itemsOnsaleGetRequest.setPage(1L);
		itemsOnsaleGetRequest.setPageSize(100L);
		itemsOnsaleGetRequest.setChannelId(String.valueOf(channelId));
		recursiveOnSaleGetData(itemsOnsaleGetRequest, channelId);
		// 下架商品
		CloudItemsDownSaleRequest cloudItemsDownSaleRequest = new CloudItemsDownSaleRequest();
		cloudItemsDownSaleRequest.setPage(1L);
		cloudItemsDownSaleRequest.setPageSize(100L);
		cloudItemsDownSaleRequest.setChannelId(String.valueOf(channelId));
		recursiveDownSaleGetData(cloudItemsDownSaleRequest, channelId);
	}

	/**
	 * 递归取下架商品数据
	 * 
	 * @param cloudItemsDownSaleRequest
	 * @param channelId
	 * @throws Exception
	 */
	private void recursiveDownSaleGetData(CloudItemsDownSaleRequest cloudItemsDownSaleRequest, String channelId) throws Exception {
		ChannelDistributor channel = baseHibernateService.findEntityById(ChannelDistributor.class, channelId);
		CloudItemsDownSaleResponse cloudItemsDownSaleResponse = CloudShopsClientAdapter.getInstance().execute(cloudItemsDownSaleRequest, CloudShopsChannelGet.getChannel(channel));
		if (cloudItemsDownSaleResponse.isSuccess()) {
			if (cloudItemsDownSaleResponse.getTotalResults() != null && cloudItemsDownSaleResponse.getTotalResults().intValue() != 0) {
				// 获取商品个数
			}
			List<Item> items = cloudItemsDownSaleResponse.getItems();
			if (items != null && !items.isEmpty()) {
				for (Item item : items) {
					addGoods(item, channel);
				}
				if (cloudItemsDownSaleResponse.isHasNext()) {
					// 循环调
					cloudItemsDownSaleRequest.setPage(cloudItemsDownSaleRequest.getPage() + 1);
					recursiveDownSaleGetData(cloudItemsDownSaleRequest, channelId);
				}
			}
		}
	}

	/**
	 * 递归获取上架商品数据
	 * 
	 * @param itemsOnsaleGetRequest
	 * @param channelId
	 * @throws Exception
	 */
	private void recursiveOnSaleGetData(ItemsOnsaleGetRequest itemsOnsaleGetRequest, String channelId) throws Exception {
		ChannelDistributor channel = baseHibernateService.findEntityById(ChannelDistributor.class, channelId);
		ItemsOnsaleGetResponse cloudShopsApiResponse = CloudShopsClientAdapter.getInstance().execute(itemsOnsaleGetRequest, CloudShopsChannelGet.getChannel(channel));
		if (cloudShopsApiResponse.isSuccess()) {
			if (cloudShopsApiResponse.getTotalResults() != null && cloudShopsApiResponse.getTotalResults().intValue() != 0) {
			}
			List<Item> items = cloudShopsApiResponse.getItems();
			if (items != null && !items.isEmpty()) {
				for (Item item : items) {
					addGoods(item, channel);
				}
				if (cloudShopsApiResponse.isHasNext()) {
					// 循环调
					itemsOnsaleGetRequest.setPage(itemsOnsaleGetRequest.getPage() + 1);
					recursiveOnSaleGetData(itemsOnsaleGetRequest, channelId);
				}
			}
		}
	}

	/**
	 * 添加商品至本地数据库
	 * 
	 * @param item
	 * @throws Exception
	 */
	private void addGoods(Item item, ChannelDistributor channel) throws Exception {
		Goods goods = buildGoods(item, channel);
		// 组装商品渠道表
		GoodsChannelProp goodsChannelProp = buildGoodsChannelProp(item, goods, channel);
		/*
		 * // 商品图片 List<String> goodsImages = new ArrayList<String>(); Object[]
		 * goodsImage = buildGoodsImage(item, goods.getId(), goodsImages); if
		 * (goodsImage != null) {
		 * goods.setImageId(Long.parseLong(goodsImage[0].toString())); }
		 */
		// 商品服务策略
		/* buildGoodsChannelServicePolicy(item, goods.getId(), channelId); */
		List<Sku> skus = item.getSkus();
		if (skus != null && skus.size() > 0) {
			for (int i = 0; i < skus.size(); i++) {
				Sku sku = skus.get(i);
				// 商品SKU
				Products product = buildProducts(sku, goods, item.getItemId(), item.getCatalogId(), channel);
				if (i == 0)
					goodsChannelProp.setDefaultProductId(product.getId());
			}
		}
	}

	/**
	 * 商品表数据
	 * 
	 * @param item
	 * @param goodsId
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	private Goods buildGoods(Item item, ChannelDistributor channel) throws Exception {

		Goods goods = null;
		if (item.getOuterId() != null) {
			goods = baseHibernateService.findEntityByAttribute(Goods.class, "outerId", item.getItemId());
		}
		if (goods != null) {

		} else {
			goods = new Goods();
			initEntityBaseController.initEntityBaseAttribute(goods);
		}
		// 获取当前员工信息
		BaseEmployee e = SecurityUtil.getCurrentRealUser();
		if (e != null && e.getCompanyCode() != null) {
			goods.setCompanyCode(e.getCompanyCode());
		}
		goods.setChannelDistributor(channel);
		goods.setGoodName(item.getItemName());
		// 处理中文索引
		String py = ChnToPinYin.getPYString(item.getItemName());
		goods.setChineseCharacter(py.toUpperCase());
		//
		goods.setGoodsClass(0);
		goods.setOuterId(item.getItemId());
		goods.setCatalogId(item.getCatalogId());
		goods.setGoodsCode(item.getOuterId());
		goods.setBarCode(item.getBarCode());
		goods.setManufacturers(null);
		goods.setWrap(item.getWrap());
		if (item.getLength() != null && !"".equals(item.getLength())) {
			goods.setLength(Double.parseDouble(item.getLength()));
		}
		if (item.getWide() != null && !"".equals(item.getWide())) {
			goods.setWide(Double.parseDouble(item.getWide()));
		}
		if (item.getHeight() != null && !"".equals(item.getHeight())) {
			goods.setHeight(Double.parseDouble(item.getHeight()));
		}
		if (item.getWeight() != null && !"".equals(item.getWeight())) {
			goods.setWeight(Double.parseDouble(item.getWeight()));
		}
		goods.setPackListing(item.getPackListing());
		goods.setService(item.getService());
		goods.setIsMarketable(item.getIsMarketable());
		goods.setItemImage(item.getItemImage());
		goods.setIntro(item.getIntro());
		goods.setMktprice(Double.parseDouble(item.getMktprice()));
		goods.setPrice(Double.parseDouble(item.getPrice()));
		if (item.getUpTime() != null && !"".endsWith(item.getUpTime()))
			goods.setUpTime(dateformat.parse(item.getUpTime()));
		else
			goods.setUpTime(new Date());
		if (item.getDownTime() != null && !"".endsWith(item.getDownTime()))
			goods.setDownTime(dateformat.parse(item.getDownTime()));
		else
			goods.setDownTime(new Date());
		goods.setState(item.getState());
		goods.setStorenum(item.getStoreNum());
		goods = baseHibernateService.mergeOriginal(goods);
		vixOperateLog.saveOperateLog("", "", "OrderProcessAction", "网店:" + channel.getName() + "商品:" + goods.getGoodName() + "下载成功");
		return goods;
	}

	/**
	 * 商品渠道表 通过渠道 商品编码来控制 商品的唯一性
	 * 
	 * @param item
	 * @param goodsId
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	private GoodsChannelProp buildGoodsChannelProp(Item item, Goods goods, ChannelDistributor channelDistributor) throws Exception {
		GoodsChannelProp goodsChannelProp = new GoodsChannelProp();
		goodsChannelProp.setChannelDistributor(channelDistributor);
		goodsChannelProp.setGoods(goods);
		goodsChannelProp.setOuterId(item.getItemId());
		goodsChannelProp.setTypeId(item.getCatalogId());
		goodsChannelProp.setFreightPayer(item.getFreightPayer());
		goodsChannelProp.setPostageId(item.getPostageId());
		goodsChannelProp.setIsSpec(item.getIsSpec());
		goodsChannelProp.setSpec(item.getSpec());
		goodsChannelProp.setProp(item.getProps());
		goodsChannelProp.setMktprice(Double.parseDouble(item.getMktprice()));
		goodsChannelProp.setPrice(Double.parseDouble(item.getPrice()));
		goodsChannelProp.setStoreNum(item.getStoreNum());
		goodsChannelProp.setIsMarketable(item.getIsMarketable());
		goodsChannelProp.setReleaseState(item.getReleaseState());
		goodsChannelProp.setState(item.getState());
		goodsChannelProp.setLastUpdateTime(new Date());
		goodsChannelProp.setLastSyscTime(new Date());
		initEntityBaseController.initEntityBaseAttribute(goodsChannelProp);
		goodsChannelProp = baseHibernateService.mergeOriginal(goodsChannelProp);
		return goodsChannelProp;
	}

	/**
	 * 商品图片
	 * 
	 * @param item
	 * @param goodsId
	 * @param goodsImgs
	 * @return
	 */
	/*
	 * private Object[] buildGoodsImage(Item item, Long goodsId, List<String>
	 * goodsImgs) {
	 * 
	 * Long defaultImgId = null; String defaultImgUrl = null; ContinueFTP ftp =
	 * new ContinueFTP(); StringBuilder finalName = null; List<ItemImg> imgs =
	 * item.getItemImgs(); if (imgs != null && !imgs.isEmpty()) { for (ItemImg
	 * itemImg : imgs) { try { finalName = new StringBuilder(); String logo =
	 * itemImg.getUrl(); if (logo != null && !"".equals(logo)) { URL url = new
	 * URL(logo); url.openConnection(); InputStream ins = url.openStream();
	 * finalName.append("goods"); finalName.append("/"); String str =
	 * dateFormator.format(new Date()); finalName.append(str);
	 * finalName.append("/");
	 * finalName.append(UUID.randomUUID().toString().replaceAll("-", ""));
	 * finalName.append(".");
	 * finalName.append(logo.substring(logo.lastIndexOf(".") + 1,
	 * logo.length())); ftp.connect(httpservice, port, name, password);
	 * System.out.println(ftp.uploadFile("" + "/" + finalName.toString(), ins));
	 * 
	 * Long imgId = 0L; if (item.getItemImage() != null &&
	 * item.getItemImage().equals(itemImg.getUrl())) { defaultImgId = imgId;
	 * defaultImgUrl = finalName.toString(); }
	 * 
	 * GoodsImage goodsImage = new GoodsImage();
	 * goodsImage.setSource(finalName.toString());
	 * goodsImage.setGoodsId(goodsId); goodsImage.setUpDateTime(new Date());
	 * goodsImgs.add(finalName.toString()); } } catch (Exception e) {
	 * e.printStackTrace(); } } } else { try { if (item.getItemImage() != null
	 * && !"".equals(item.getItemImage())) { defaultImgId = null; finalName =
	 * new StringBuilder(); URL url = new URL(item.getItemImage());
	 * url.openConnection(); InputStream ins = url.openStream();
	 * finalName.append("goods"); finalName.append("/"); String str =
	 * dateFormator.format(new Date()); finalName.append(str);
	 * finalName.append("/");
	 * finalName.append(UUID.randomUUID().toString().replaceAll("-", ""));
	 * finalName.append(".");
	 * finalName.append(item.getItemImage().substring(item
	 * .getItemImage().lastIndexOf(".") + 1, item.getItemImage().length()));
	 * ftp.connect(httpservice, port, name, password);
	 * System.out.println(ftp.uploadFile("" + "/" + finalName.toString(), ins));
	 * 
	 * GoodsImage goodsImage = new GoodsImage();
	 * goodsImage.setSource(finalName.toString());
	 * goodsImage.setGoodsId(goodsId); goodsImage.setUpDateTime(new Date());
	 * defaultImgUrl = finalName.toString();
	 * goodsImgs.add(finalName.toString()); } } catch (Exception e) {
	 * e.printStackTrace(); } } return new Object[] { defaultImgId,
	 * defaultImgUrl };
	 * 
	 * return null; }
	 */

	/**
	 * 服务策略
	 * 
	 * @param item
	 * @param goodsId
	 * @param channelId
	 * @return
	 */
	/*
	 * private GoodsChannelServicePolicy buildGoodsChannelServicePolicy(Item
	 * item, Long goodsId, Long channelId) { GoodsChannelServicePolicy policy =
	 * new GoodsChannelServicePolicy(); policy.setChannelId(channelId); return
	 * policy; }
	 */

	/**
	 * sku
	 * 
	 * @param sku
	 * @param goodsId
	 * @param itemId
	 * @param cId
	 * @param channelId
	 * @return
	 * @throws Exception
	 */
	private Products buildProducts(Sku sku, Goods goods, String itemId, Long cId, ChannelDistributor channelDistributor) throws Exception {
		Products products = null;
		if (sku.getOuterId() != null) {
			products = baseHibernateService.findEntityByAttribute(Products.class, "outerId", sku.getSkuId());
		}
		if (products != null) {

		} else {
			products = new Products();
			initEntityBaseController.initEntityBaseAttribute(products);
		}
		products.setChannelDistributor(channelDistributor);
		products.setGoods(goods);
		products.setOuterId(sku.getSkuId());
		products.setBarCode(sku.getOuterId());
		products.setBn(sku.getOuterId());
		products.setMktprice(Double.parseDouble(sku.getMktprice()));
		products.setPrice(Double.parseDouble(sku.getPrice()));
		products.setCost(Double.parseDouble(sku.getCost()));
		products.setStore(sku.getStore());
		products.setState(sku.getState());
		products.setReleaseState(1);
		if (sku.getSpec() != null && !"[]".equals(sku.getSpec())) {
			JSONArray jsonarray = JSONArray.fromObject(sku.getSpec());
			products.setSpec(jsonarray.toString());
		}
		products.setLastUpdateTime(new Date());
		products = baseHibernateService.mergeOriginal(products);
		return products;
	}
}
