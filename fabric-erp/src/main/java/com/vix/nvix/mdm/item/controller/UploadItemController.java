/**
 * 
 */
package com.vix.nvix.mdm.item.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.jdbc.dbstruct.Specification;
import com.vix.core.persistence.jdbc.dbstruct.SpecificationDetail;
import com.vix.core.utils.SortSet;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channel.entity.ChannelDistributorSet;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.mdm.item.entity.StoreItem;
import com.vix.mdm.item.service.IItemService;
import com.vix.nvix.common.base.controller.VixntBaseController;
import com.vix.system.expand.service.ISpecificationService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 同步商品到电商
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.nvix.mdm.item.controller.UploadItemController
 *
 * @date 2017年12月22日
 */
@Controller("uploadItemController")
@Scope("prototype")
public class UploadItemController extends VixntBaseController {
	@Autowired
	private IItemService itemService;

	@Autowired
	private ISpecificationService specificationService;

	/**
	 * 更新商品到电商
	 * 
	 * @param itemList
	 * @param ec_url
	 * @param ec_tenantid
	 * @param ec_useraccount
	 * @param ec_password
	 */
	public String uploadItem(List<Item> itemList, String ec_url, String ec_tenantid, String ec_useraccount, String ec_password) {
		if (StringUtils.isNotEmpty(ec_url) && StringUtils.isNotEmpty(ec_tenantid) && StringUtils.isNotEmpty(ec_useraccount) && StringUtils.isNotEmpty(ec_password)) {
			JSONObject json = new JSONObject();
			json.put("TENANTID", ec_tenantid);
			JSONArray jsonarray = new JSONArray();
			if (itemList != null && itemList.size() > 0) {
				for (Item item : itemList) {
					// 同步商品信息
					JSONObject itemJson = new JSONObject();
					itemJson.put("CODE", item.getCode());
					itemJson.put("NAME", item.getName());
					itemJson.put("MEASUREUNITNAME", item.getMeasureUnitName());
					String ItemCatalogNames=loadItemCatalogNames(item.getItemCatalog().getId());
					itemJson.put("TYPENAME",ItemCatalogNames);
					itemJson.put("SKU", item.getSkuCode());
					itemJson.put("BARCODE", item.getBarCode());
					itemJson.put("BRAND", item.getItemBrand().getName());
					itemJson.put("ORIGIN", item.getOrigin());
					itemJson.put("REMARK", item.getMemo());
					itemJson.put("PRICE", item.getPrice().toString());
					itemJson.put("PURCHASEPRICE",item.getPurchasePrice().toString());
					itemJson.put("VIPPRICE", item.getVipPrice());
					// 同步规格表
					List<Specification> specificationList = null;
					if (null != item && null != item.getItemCatalog() && null != item.getItemCatalog().getId()) {
						Map<String, Object> specificationParams = new HashMap<String, Object>();
						specificationParams.put("itemCatalog.id," + SearchCondition.EQUAL, item.getItemCatalog().getId());
						try {
							specificationList = vixntBaseService.findAllByConditions(Specification.class, specificationParams);
							// 规格明细列表
							JSONArray specificationArray = new JSONArray();
							if (specificationList != null && specificationList.size() > 0) {
								for (Specification specification : specificationList) {
									if (specification != null) {

										JSONObject specificationJson = new JSONObject();
										specificationJson.put("code", specification.getCode());
										specificationJson.put("name", specification.getName());
										specificationJson.put("specificationTableName", specification.getSpecificationTableName());
										Map<String, Object> specificationDetailParams = new HashMap<String, Object>();
										specificationDetailParams.put("specification.id," + SearchCondition.EQUAL, specification.getId());
										List<SpecificationDetail> specificationDetailList = vixntBaseService.findAllByConditions(SpecificationDetail.class, specificationDetailParams);
										// 规格明细列表
										JSONArray specificationDetailArray = new JSONArray();
										if (specificationDetailList != null && specificationDetailList.size() > 0) {
											for (SpecificationDetail specificationDetail : specificationDetailList) {
												if (specificationDetail != null) {
													JSONObject specificationDetailJson = new JSONObject();
													specificationDetailJson.put("code", specificationDetail.getCode());
													specificationDetailJson.put("name", specificationDetail.getName());
													specificationDetailJson.put("imageFileName", specificationDetail.getImageFileName());
													specificationDetailJson.put("imageFileRealName", specificationDetail.getImageFileRealName());
													specificationDetailJson.put("imageFilePath", specificationDetail.getImageFilePath());
													specificationDetailArray.add(specificationDetailJson);
												}
											}
											specificationJson.put("specificationDetailList", specificationDetailArray);
										}
										specificationArray.add(specificationJson);
									}
								}
								itemJson.put("specificationList", specificationArray);
							}else{
								itemJson.put("specificationList", specificationArray);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}

						// 商品规格
						/*
						 * List<List<Object>> savedSpecificationList =
						 * loadSpecificationDetailList(item.getId(),
						 * item.getItemCatalog().getId(), specificationList); if
						 * (savedSpecificationList != null &&
						 * savedSpecificationList.size() > 0) { for
						 * (List<Object> list : savedSpecificationList) { // 待定
						 * } }
						 */
						jsonarray.add(itemJson);
					}
				}
			}
			json.put("ITEMS", jsonarray);
			System.out.println(json);
			try {
				String resp = postToPos(ec_url + "restService/erpEcProduct/receiveErpEcProduct.rs", json.toString(), ec_useraccount, ec_password);
				if (StringUtils.isNotEmpty(resp)) {
					return resp;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/** 加载商品的上两级分类 拼接名称 :（第三级，第二级，第一级）*/
	private String loadItemCatalogNames(String ItemCatalogId) {
		String IcNames = "";
		try {
			if(StrUtils.objectIsNotNull(ItemCatalogId)){
				ItemCatalog thirdIc = itemService.findEntityById(ItemCatalog.class,ItemCatalogId);
				if(null != thirdIc){
					IcNames += thirdIc.getName();
					if(null != thirdIc.getParentItemCatalog() && null != thirdIc.getParentItemCatalog().getId()){
						IcNames += "," + thirdIc.getParentItemCatalog().getName();
						ItemCatalog secondIc = itemService.findEntityById(ItemCatalog.class,thirdIc.getParentItemCatalog().getId());
						if(null != secondIc.getParentItemCatalog() && null != secondIc.getParentItemCatalog().getId()){
							IcNames += "," + secondIc.getParentItemCatalog().getName();
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return IcNames;
	}

	public List<List<Object>> loadSpecificationDetailList(String id, String categoryId, List<Specification> specificationList) {
		List<List<Object>> savedSpecificationList = new ArrayList<List<Object>>();
		try {
			if (StrUtils.objectIsNotNull(categoryId)) {
				String specTableName = specificationService.findTableNameByProductCategoryId(categoryId);
				Map<String, Object> params = new HashMap<String, Object>();
				/** 加载规格 */
				specificationList = specificationService.findSpecificationByProductCatetoryId(categoryId, params);
				SortSet ss = new SortSet("orderBy", "asc");
				Collections.sort(specificationList, ss);
				if (null != specificationList && specificationList.size() > 0) {
					if (StrUtils.objectIsNotNull(specTableName)) {
						SortSet scm = new SortSet("orderBy", "asc");
						Collections.sort(specificationList, scm);
						if (StrUtils.objectIsNotNull(id)) {
							savedSpecificationList = itemService.findProductSpecification(specTableName, id, null);
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return savedSpecificationList;
	}
	/**
	 * 删除POS端的商品
	 * 
	 * @param storeItem
	 * @param channelDistributor
	 * @throws Exception
	 */
	public void deleteItem(StoreItem storeItem, ChannelDistributor channelDistributor) throws Exception {
		JSONObject json = new JSONObject();
		ChannelDistributorSet channelDistributorSet = getChannelDistributorSet(channelDistributor.getId());
		if (channelDistributorSet != null) {
			json.put("SHOPCODE", channelDistributorSet.getInnerCode());
			JSONArray jsonarray = new JSONArray();
			JSONObject itemJson = new JSONObject();
			itemJson.put("CODE", storeItem.getCode());
			itemJson.put("NAME", storeItem.getName());
			itemJson.put("MEASUREUNITID", storeItem.getSaleUnit());
			itemJson.put("TYPEID", storeItem.getCodeRule());
			itemJson.put("SPECIFICATION", storeItem.getSpecDescription());
			itemJson.put("BARCODE", storeItem.getBarCode());
			itemJson.put("PRICE", storeItem.getPrice());
			itemJson.put("VIPPRICE", storeItem.getVipPrice());
			itemJson.put("BRAND", storeItem.getBrand());
			itemJson.put("ORIGIN", storeItem.getOrigin());
			itemJson.put("ISUSED", storeItem.getStatus());
			itemJson.put("ISDISCOUNT", storeItem.getIsDiscount());
			itemJson.put("REVACCOUNTID", storeItem.getRevAccountId());
			String py = ChnToPinYin.getPinYinHeadChar(storeItem.getName()).toUpperCase();
			itemJson.put("PINYIN", py);
			itemJson.put("ISPLU", storeItem.getIsplu());
			itemJson.put("PLU", storeItem.getPlu());
			itemJson.put("PLUMODE", storeItem.getPluMode());
			itemJson.put("REMARK", storeItem.getMemo());
			itemJson.put("IS_COMMON", storeItem.getIsCommon());
			itemJson.put("FLAG", "D");
			jsonarray.add(itemJson);
			json.put("ITEMS", jsonarray);
			System.out.println(json);
			String resp = postToPos(channelDistributorSet.getZf_pos_menu_upload(), json.toString(), channelDistributorSet.getZf_pos_useraccount(), channelDistributorSet.getZf_pos_password());
			if (StringUtils.isNotEmpty(resp)) {
				System.out.println(resp);
			}
		}
	}

	private ChannelDistributorSet getChannelDistributorSet(String id) {
		ChannelDistributorSet channelDistributorSet = null;
		try {
			if (StringUtils.isNotEmpty(id)) {
				channelDistributorSet = vixntBaseService.findEntityByAttribute(ChannelDistributorSet.class, "channelDistributor.id", id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return channelDistributorSet;
	}
}
