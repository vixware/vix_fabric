package com.vix.nvix.channel.action;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.IOUtils;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.common.share.entity.MeasureUnitGroup;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.channel.entity.ChannelDistributorSet;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.mdm.item.constant.ItemConstant;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.mdm.item.entity.StoreItem;
import com.vix.mdm.item.service.IItemService;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.nvix.common.base.hql.VixntBaseHqlProvider;
import com.vix.nvix.common.base.template.ExcelTemplate;
import com.vix.nvix.drp.action.VixntDrpBaseAction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 渠道商品管理
 * 
 * @author zhanghaibing
 * 
 * @类全名 com.vix.nvix.channel.action.VixntChannelItemAction
 *
 * @date 2017年12月28日
 */
@Controller
@Scope("prototype")
public class VixntChannelItemAction extends VixntDrpBaseAction {

	private static final long serialVersionUID = 1L;
	@Autowired
	private IItemService itemService;
	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;
	@Resource(name = "vixntBaseHqlProvider")
	private VixntBaseHqlProvider vixntBaseHqlProvider;
	private String id;
	private String parentId;
	private String treeType;
	private String treeId;
	private String categoryId;
	private String itemids;
	private String storeItemId;
	private String channelDistributorId;
	private String supplierId;
	private String itemIds;
	private Double price;
	private Double vipPrice;
	/** 产品 */
	private Item item;

	private StoreItem storeItem;
	private List<Item> itemList;
	/**
	 * 经销商
	 */
	private ChannelDistributor channelDistributor;
	private List<MeasureUnitGroup> measureUnitGroupList;
	private List<MeasureUnit> measureUnitList;
	private List<CurrencyType> currencyTypeList;
	private String type;

	public String goStoreItemList() {
		return "goStoreItemList";
	}

	public String goSupplierItemList() {
		return "goSupplierItemList";
	}

	public String goSaveOrUpdate() {
		try {
			measureUnitGroupList = itemService.findAllByEntityClass(MeasureUnitGroup.class);
			measureUnitList = itemService.findAllByEntityClass(MeasureUnit.class);
			currencyTypeList = itemService.findAllByEntityClass(CurrencyType.class);
			if (StrUtils.isNotEmpty(id)) {
				storeItem = itemService.findEntityById(StoreItem.class, id);
			} else {
				storeItem = new StoreItem();
				storeItem.setStatus("T");
				storeItem.setIsDiscount("F");
				if (StringUtils.isNotEmpty(parentId)) {
					ItemCatalog itemCatalog = vixntBaseService.findEntityById(ItemCatalog.class, parentId);
					if (itemCatalog != null) {
						storeItem.setItemCatalog(itemCatalog);
						storeItem.setCode(autoCreateCode.getBillNO(itemCatalog.getCodeRule()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdate";
	}

	private String measureUnitGroupId;

	public String loadMeasureUnit() {
		try {
			if (StringUtils.isNotEmpty(measureUnitGroupId)) {
				measureUnitList = itemService.findAllByEntityClassAndAttribute(MeasureUnit.class, "measureUnitGroup.id", measureUnitGroupId);
			}
			if (StrUtils.isNotEmpty(id)) {
				item = itemService.findEntityById(Item.class, id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return type;
	}

	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			String searchName = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("name", "%" + searchName + "%");
			}
			if (StringUtils.isNotEmpty(parentId) && StringUtils.isNotEmpty(treeType) && "CH".equals(treeType)) {
				params.put("channelDistributorsId", parentId);
				pager = vixntBaseService.findStoreItemPager(pager, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goStoreItemSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			// 处理搜索条件
			String searchName = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("name", "%" + searchName + "%");
			}
			params.put("flag", "1");
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
					params.put("channelDistributorsId", employee.getChannelDistributor().getId());
					pager = vixntBaseService.findStoreItemPager(pager, params);
				} else {
					ChannelDistributor channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
					if (channelDistributor != null) {
						params.put("channelDistributorsId", channelDistributor.getId());
						pager = vixntBaseService.findStoreItemPager(pager, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goNoStoreItemSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("createTime");
			}
			// 处理搜索条件
			String searchName = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("name", "%" + searchName + "%");
			}
			params.put("flag", "0");
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getChannelDistributor() != null && StringUtils.isNotEmpty(employee.getChannelDistributor().getId())) {
					params.put("channelDistributorsId", employee.getChannelDistributor().getId());
					pager = vixntBaseService.findStoreItemPager(pager, params);
				} else {
					ChannelDistributor channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
					if (channelDistributor != null) {
						params.put("channelDistributorsId", channelDistributor.getId());
						pager = vixntBaseService.findStoreItemPager(pager, params);
					}
				}
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goSupplierItemSingleList() {
		try {
			Pager pager = getPager();
			Map<String, Object> params = getParams();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("status," + SearchCondition.EQUAL, "F");
			// 处理搜索条件
			String selectName = getDecodeRequestParameter("selectName");
			if (selectName != null && !"".equals(selectName)) {
				params.put("name," + SearchCondition.ANYLIKE, selectName);
			}
			if (StringUtils.isNotEmpty(treeId)) {
				params.put("supplier.id," + SearchCondition.EQUAL, treeId);
				pager = itemService.findPagerByHqlConditions(pager, Item.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goChooseItem() {
		return "goChooseItem";
	}

	public void goChooseListContent() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("isDeleted," + SearchCondition.NOEQUAL, "1");
			params.put("type," + SearchCondition.EQUAL, "circulationIndustry");
			params.put("parentItem.id," + SearchCondition.IS, null);
			// 处理搜索条件
			String selectName = getDecodeRequestParameter("selectName");
			if (StringUtils.isNotEmpty(selectName)) {
				params.put("name," + SearchCondition.ANYLIKE, selectName);
			}
			params.put("status," + SearchCondition.EQUAL, "T");
			if (StringUtils.isNotEmpty(categoryId)) {
				params.put("itemCatalog.id," + SearchCondition.EQUAL, categoryId);
			}
			pager = itemService.findPagerByHqlConditions(pager, Item.class, params);
			renderDataTable(pager);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Item> getStoreItemList() throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();

		if (StringUtils.isNotEmpty(parentId)) {
			params.put("channelDistributorsId", parentId);
		}
		String itemCodes = "";
		List<StoreItem> storeItemList = vixntBaseService.findStoreItemListByHql(params);
		if (storeItemList != null && storeItemList.size() > 0) {
			for (StoreItem storeItem : storeItemList) {
				itemCodes += "," + storeItem.getCode();
			}
		}
		Map<String, Object> p = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(itemCodes)) {
			p.put("code," + SearchCondition.IN, itemCodes);
		}
		List<Item> itemList = vixntBaseService.findAllByConditions(Item.class, p);
		return itemList;
	}

	public void updatePrice() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(id)) {
				StoreItem storeItem = vixntBaseService.findEntityById(StoreItem.class, id);
				if (storeItem != null) {
					if (price != null) {
						storeItem.setPrice(price);
					}
					if (vipPrice != null) {
						storeItem.setVipPrice(vipPrice);
					}
					storeItem.setStatus("T");
					storeItem = vixntBaseService.merge(storeItem);
					List<StoreItem> storeItemList = new ArrayList<StoreItem>();
					storeItemList.add(storeItem);
					// 获取当前员工信息
					Employee employee = getEmployee();
					if (employee != null) {
						if (employee.getChannelDistributor() != null) {
							uploadItem(storeItemList, "A", employee.getChannelDistributor());
							uploadItem(storeItemList, "U", employee.getChannelDistributor());
						} else {
							ChannelDistributor channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
							if (channelDistributor != null) {
								uploadItem(storeItemList, "A", channelDistributor);
								uploadItem(storeItemList, "U", channelDistributor);
							}
						}
					}
				}
			}
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
	}

	public void itemToChannelDistributor() {
		try {
			if (StringUtils.isNotEmpty(parentId)) {
				channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, parentId);
				if (channelDistributor != null && StringUtils.isNotEmpty(itemids)) {
					Map<String, Object> params = getParams();
					params.put("id," + SearchCondition.IN, itemids);
					List<Item> itemList = itemService.findAllByConditions(Item.class, params);
					List<StoreItem> storeItemAddList = new ArrayList<StoreItem>();
					List<StoreItem> storeItemUpdateList = new ArrayList<StoreItem>();
					if (itemList != null && itemList.size() > 0) {
						for (Item item : itemList) {
							if (item != null) {
								StoreItem t = null;
								Map<String, Object> itemparams = new HashMap<String, Object>();
								itemparams.put("code", item.getCode());
								itemparams.put("channelDistributorId", channelDistributor.getId());
								StringBuilder itemhql = standingBookHqlProvider.findStoreItemByItemCode(itemparams);
								MeasureUnit measureUnit = item.getMeasureUnit();
								t = vixntBaseService.findObjectByHql(itemhql.toString(), itemparams);
								if (t != null) {
									String id = t.getId();
									String codeRule = t.getCodeRule();
									String saleUnit = t.getSaleUnit();
									Set<ChannelDistributor> subChannelDistributors = t.getSubChannelDistributors();
									BeanUtils.copyProperties(item, t, new String[]{"id", "subChannelDistributors"});
									t.setId(id);
									t.setSubChannelDistributors(subChannelDistributors);
									t.setCodeRule(codeRule);
									t.setSaleUnit(saleUnit);
									t.setMeasureUnit(measureUnit);
									t = vixntBaseService.merge(t);
									storeItemUpdateList.add(t);
								} else {
									t = new StoreItem();
									BeanUtils.copyProperties(item, t, new String[]{"id", "subChannelDistributors"});
									if (StringUtils.isNotEmpty(item.getItemCatalogIds())) {
										ItemCatalog itemCatalog = vixntBaseService.findEntityById(ItemCatalog.class, item.getItemCatalogIds());
										if (itemCatalog != null) {
											t.setCodeRule(itemCatalog.getCodeRule());
										}
									}
									Set<ChannelDistributor> subChannelDistributors = t.getSubChannelDistributors();
									subChannelDistributors.add(channelDistributor);
									t.setSubChannelDistributors(subChannelDistributors);
									if (item.getMeasureUnit() != null) {
										t.setSaleUnit(item.getMeasureUnit().getName());
									}
									t.setMeasureUnit(measureUnit);
									t = vixntBaseService.merge(t);
									storeItemAddList.add(t);
								}
							}
						}
					}
					ChannelDistributorSet channelDistributorSet = getChannelDistributorSet(channelDistributor.getId());
					if (channelDistributorSet != null) {
						if ("0".equals(channelDistributorSet.getIsUpload())) {
							// 新增
							uploadItem(storeItemAddList, "A", channelDistributor);
							// 修改
							uploadItem(storeItemUpdateList, "U", channelDistributor);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void shopItemToChannelDistributor() {
		try {
			ChannelDistributor channelDistributor = null;
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getChannelDistributor() != null) {
					channelDistributor = employee.getChannelDistributor();
				} else {
					channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
				}
			}
			if (channelDistributor != null && StringUtils.isNotEmpty(itemids)) {
				Map<String, Object> params = getParams();
				params.put("id," + SearchCondition.IN, itemids);
				List<Item> itemList = itemService.findAllByConditions(Item.class, params);
				List<StoreItem> storeItemAddList = new ArrayList<StoreItem>();
				List<StoreItem> storeItemUpdateList = new ArrayList<StoreItem>();
				if (itemList != null && itemList.size() > 0) {
					for (Item item : itemList) {
						if (item != null) {
							StoreItem t = null;
							Map<String, Object> itemparams = new HashMap<String, Object>();
							itemparams.put("code", item.getCode());
							itemparams.put("channelDistributorId", channelDistributor.getId());
							StringBuilder itemhql = standingBookHqlProvider.findStoreItemByItemCode(itemparams);
							t = vixntBaseService.findObjectByHql(itemhql.toString(), itemparams);
							MeasureUnit measureUnit = item.getMeasureUnit();
							if (t != null) {
								String id = t.getId();
								String codeRule = t.getCodeRule();
								String saleUnit = t.getSaleUnit();
								Set<ChannelDistributor> subChannelDistributors = t.getSubChannelDistributors();
								BeanUtils.copyProperties(item, t, new String[]{"id", "subChannelDistributors"});
								t.setId(id);
								t.setSubChannelDistributors(subChannelDistributors);
								t.setCodeRule(codeRule);
								t.setSaleUnit(saleUnit);
								t.setMeasureUnit(measureUnit);
								t = vixntBaseService.merge(t);
								storeItemUpdateList.add(t);
							} else {
								t = new StoreItem();
								BeanUtils.copyProperties(item, t, new String[]{"id", "subChannelDistributors"});
								if (StringUtils.isNotEmpty(item.getItemCatalogIds())) {
									ItemCatalog itemCatalog = vixntBaseService.findEntityById(ItemCatalog.class, item.getItemCatalogIds());
									if (itemCatalog != null) {
										t.setCodeRule(itemCatalog.getCodeRule());
									}
								}
								Set<ChannelDistributor> subChannelDistributors = t.getSubChannelDistributors();
								subChannelDistributors.add(channelDistributor);
								t.setSubChannelDistributors(subChannelDistributors);
								if (measureUnit != null) {
									t.setSaleUnit(measureUnit.getName());
								}
								t.setMeasureUnit(measureUnit);
								t.setFlag("0");
								t = vixntBaseService.merge(t);
								storeItemAddList.add(t);
							}
						}
					}
				}
				ChannelDistributorSet channelDistributorSet = getChannelDistributorSet(channelDistributor.getId());
				if (channelDistributorSet != null) {
					if ("0".equals(channelDistributorSet.getIsUpload())) {
						// 新增
						uploadItem(storeItemAddList, "A", channelDistributor);
						// 修改
						uploadItem(storeItemUpdateList, "U", channelDistributor);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void prepareStoreItem() {
		try {
			ChannelDistributor channelDistributor = null;
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getChannelDistributor() != null) {
					channelDistributor = employee.getChannelDistributor();
				} else {
					channelDistributor = vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
				}
			}
			if (channelDistributor != null) {
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("channelDistributorsId", channelDistributor.getId());
				params.put("itemIds", itemids);
				List<StoreItem> storeItemList = vixntBaseService.findStoreItemListByHql(params);
				if (storeItemList != null && storeItemList.size() > 0) {
					uploadItem(storeItemList, "A", channelDistributor);
					uploadItem(storeItemList, "U", channelDistributor);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void uploadItem(List<StoreItem> storeItemList, String flag, ChannelDistributor channelDistributor) throws Exception {
		if (channelDistributor != null) {
			ChannelDistributorSet channelDistributorSet = getChannelDistributorSet(channelDistributor.getId());
			if (channelDistributorSet != null) {
				JSONObject json = new JSONObject();
				json.put("SHOPCODE", channelDistributorSet.getInnerCode());
				JSONArray jsonarray = new JSONArray();
				List<ItemCatalog> itemCatalogList = new ArrayList<ItemCatalog>();
				if (storeItemList != null && storeItemList.size() > 0) {
					for (StoreItem storeItem : storeItemList) {
						if (storeItem != null) {
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
							itemJson.put("FLAG", flag);
							jsonarray.add(itemJson);
							if (StringUtils.isNotEmpty(storeItem.getCodeRule())) {
								ItemCatalog itemCatalog = vixntBaseService.findEntityByAttribute(ItemCatalog.class, "codeRule", storeItem.getCodeRule());
								if (itemCatalog != null) {
									itemCatalogList.add(itemCatalog);
									getItemCatalogList(itemCatalogList, itemCatalog);
								}
							}
						}
					}
				}
				// 先同步
				uploadItemCatalog(itemCatalogList, "A", channelDistributor);
				uploadItemCatalog(itemCatalogList, "U", channelDistributor);
				json.put("ITEMS", jsonarray);
				System.out.println(json);
				String resp = postToPos(channelDistributorSet.getZf_pos_menu_upload(), json.toString(), channelDistributorSet.getZf_pos_useraccount(), channelDistributorSet.getZf_pos_password());
				if (StringUtils.isNotEmpty(resp)) {
					System.out.println(resp);
					JSONObject returnjson = JSONObject.fromObject(resp);
					String returnValue = returnjson.getString("RETURNVALUE");
					JSONArray returnValueList = JSONArray.fromObject(returnValue);
					for (int i = 0; i < returnValueList.size(); i++) {
						JSONObject value = returnValueList.getJSONObject(i);
						if (value.containsKey("SUCCESS")) {
							if ("TRUE".equals(value.getString("SUCCESS"))) {
								StoreItem t = null;
								Map<String, Object> itemparams = new HashMap<String, Object>();
								itemparams.put("code", value.getString("ITEMCODE"));
								itemparams.put("channelDistributorId", channelDistributor.getId());
								StringBuilder itemhql = standingBookHqlProvider.findStoreItemByItemCode(itemparams);
								t = vixntBaseService.findObjectByHql(itemhql.toString(), itemparams);
								if (t != null) {
									t.setFlag("1");
									t = vixntBaseService.merge(t);
								}
							}
						}
					}
				}
			}
		}
	}

	private void getItemCatalogList(List<ItemCatalog> itemCatalogList, ItemCatalog itemCatalog) {
		try {
			if (itemCatalog != null) {
				if (StringUtils.isNotEmpty(itemCatalog.getParentCode())) {
					ItemCatalog catalog = vixntBaseService.findEntityByAttribute(ItemCatalog.class, "codeRule", itemCatalog.getParentCode());
					if (catalog != null) {
						itemCatalogList.add(catalog);
						getItemCatalogList(itemCatalogList, catalog);
					}
				} else {
					itemCatalogList.add(itemCatalog);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void deleteItem(StoreItem storeItem, ChannelDistributor channelDistributor) throws Exception {
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

	public void bathItemToChannelDistributor() {
		try {
			if (StringUtils.isNotEmpty(parentId)) {
				channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, parentId);
				if (channelDistributor != null) {
					Map<String, Object> params = getParams();
					List<Item> itemList = itemService.findAllByConditions(Item.class, params);
					List<StoreItem> storeItemAddList = new ArrayList<StoreItem>();
					List<StoreItem> storeItemUpdateList = new ArrayList<StoreItem>();
					if (itemList != null && itemList.size() > 0) {
						for (Item item : itemList) {
							StoreItem t = null;
							Map<String, Object> itemparams = new HashMap<String, Object>();
							itemparams.put("code", item.getCode());
							itemparams.put("channelDistributorId", channelDistributor.getId());
							StringBuilder itemhql = standingBookHqlProvider.findStoreItemByItemCode(itemparams);
							t = vixntBaseService.findObjectByHql(itemhql.toString(), itemparams);
							if (t != null) {
								String id = t.getId();
								String codeRule = t.getCodeRule();
								String saleUnit = t.getSaleUnit();
								Set<ChannelDistributor> subChannelDistributors = t.getSubChannelDistributors();
								BeanUtils.copyProperties(item, t, new String[]{"id", "subChannelDistributors"});
								t.setId(id);
								t.setSubChannelDistributors(subChannelDistributors);
								t.setCodeRule(codeRule);
								t.setSaleUnit(saleUnit);
								t = vixntBaseService.merge(t);
								storeItemUpdateList.add(t);
							} else {
								t = new StoreItem();
								BeanUtils.copyProperties(item, t, new String[]{"id", "subChannelDistributors"});
								if (StringUtils.isNotEmpty(item.getItemCatalogIds())) {
									ItemCatalog itemCatalog = vixntBaseService.findEntityById(ItemCatalog.class, item.getItemCatalogIds());
									if (itemCatalog != null) {
										t.setCodeRule(itemCatalog.getCodeRule());
									}
								}
								Set<ChannelDistributor> subChannelDistributors = t.getSubChannelDistributors();
								subChannelDistributors.add(channelDistributor);
								t.setSubChannelDistributors(subChannelDistributors);
								if (item.getMeasureUnit() != null) {
									t.setSaleUnit(item.getMeasureUnit().getName());
								}
								t.setFlag("0");
								t = vixntBaseService.merge(t);
								storeItemAddList.add(t);
							}
						}
					}
					ChannelDistributorSet channelDistributorSet = getChannelDistributorSet(channelDistributor.getId());
					if (channelDistributorSet != null) {
						if ("0".equals(channelDistributorSet.getIsUpload())) {
							// 新增
							uploadItem(storeItemAddList, "A", channelDistributor);
							// 修改
							uploadItem(storeItemUpdateList, "U", channelDistributor);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void uploadItemCatalogToStore() {
		Map<String, Object> params = getParams();
		try {
			List<ItemCatalog> itemCatalogList = vixntBaseService.findAllByConditions(ItemCatalog.class, params);
			if (StringUtils.isNotEmpty(parentId)) {
				ChannelDistributor channelDistributor = vixntBaseService.findEntityById(ChannelDistributor.class, parentId);
				if (channelDistributor != null) {
					uploadItemCatalog(itemCatalogList, "A", channelDistributor);
				}
			}
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void uploadItemCatalog(List<ItemCatalog> itemCatalogList, String flag, ChannelDistributor channelDistributor) throws Exception {
		ChannelDistributorSet channelDistributorSet = getChannelDistributorSet(channelDistributor.getId());
		if (channelDistributorSet != null) {
			JSONObject json = new JSONObject();
			json.put("SHOPCODE", channelDistributorSet.getInnerCode());
			JSONArray jsonarray = new JSONArray();
			for (ItemCatalog itemCatalog : itemCatalogList) {
				if (itemCatalog != null) {
					JSONObject catalogjson = new JSONObject();
					if (StringUtils.isNotEmpty(itemCatalog.getParentCode())) {
						catalogjson.put("SUPERID", itemCatalog.getParentCode());
					} else {
						catalogjson.put("SUPERID", "");
					}
					catalogjson.put("CODE", itemCatalog.getCodeRule());
					catalogjson.put("NAME", itemCatalog.getName());
					catalogjson.put("ISUSED", "T");
					catalogjson.put("REVACCOUNTID", "");
					catalogjson.put("REMARK", "");
					catalogjson.put("FLAG", flag);
					jsonarray.add(catalogjson);
				}
			}
			json.put("ITEMCATALOGS", jsonarray);

			System.out.println(json);

			String resp = postToPos(channelDistributorSet.getZf_pos_menutype_upload(), json.toString(), channelDistributorSet.getZf_pos_useraccount(), channelDistributorSet.getZf_pos_password());
			if (StringUtils.isNotEmpty(resp)) {
				System.out.println(resp);
			}
		}
	}

	/**
	 * 给门店商品分配供应商
	 */
	public void bindSupplierToStoreItem() {
		try {
			if (StringUtils.isNotEmpty(storeItemId)) {
				StoreItem storeItem = vixntBaseService.findEntityById(StoreItem.class, storeItemId);
				if (storeItem != null) {
					if (StringUtils.isNotEmpty(supplierId)) {
						Supplier supplier = vixntBaseService.findEntityById(Supplier.class, supplierId);
						if (supplier != null) {
							storeItem.setSupplier(supplier);
							storeItem = vixntBaseService.merge(storeItem);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 批量给门店商品绑定供应商
	 */
	public void bathBindSupplierToStoreItem() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(channelDistributorId)) {
				params.put("channelDistributorsId", channelDistributorId);
				params.put("itemIds", itemIds);
				List<StoreItem> storeItemList = vixntBaseService.findStoreItemListByHql(params);
				if (storeItemList != null && storeItemList.size() > 0) {
					for (StoreItem storeItem : storeItemList) {
						if (storeItem != null) {
							if (StringUtils.isNotEmpty(supplierId)) {
								Supplier supplier = vixntBaseService.findEntityById(Supplier.class, supplierId);
								if (supplier != null) {
									storeItem.setSupplier(supplier);
									storeItem = vixntBaseService.merge(storeItem);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void bathShopItemToChannelDistributor() {
		try {
			ChannelDistributor channelDistributor = null;
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getChannelDistributor() != null) {
					channelDistributor = employee.getChannelDistributor();
				} else {
					channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
				}
			}
			if (channelDistributor != null) {
				Map<String, Object> params = getParams();
				List<Item> itemList = itemService.findAllByConditions(Item.class, params);
				List<StoreItem> storeItemAddList = new ArrayList<StoreItem>();
				List<StoreItem> storeItemUpdateList = new ArrayList<StoreItem>();
				if (itemList != null && itemList.size() > 0) {
					for (Item item : itemList) {
						StoreItem t = null;
						Map<String, Object> itemparams = new HashMap<String, Object>();
						itemparams.put("code", item.getCode());
						itemparams.put("channelDistributorId", channelDistributor.getId());
						StringBuilder itemhql = standingBookHqlProvider.findStoreItemByItemCode(itemparams);
						t = vixntBaseService.findObjectByHql(itemhql.toString(), itemparams);
						if (t != null) {
							String id = t.getId();
							String codeRule = t.getCodeRule();
							String saleUnit = t.getSaleUnit();
							Set<ChannelDistributor> subChannelDistributors = t.getSubChannelDistributors();
							BeanUtils.copyProperties(item, t, new String[]{"id", "subChannelDistributors"});
							t.setId(id);
							t.setSubChannelDistributors(subChannelDistributors);
							t.setCodeRule(codeRule);
							t.setSaleUnit(saleUnit);
							t = vixntBaseService.merge(t);
							storeItemUpdateList.add(t);
						} else {
							t = new StoreItem();
							BeanUtils.copyProperties(item, t, new String[]{"id", "subChannelDistributors"});
							if (StringUtils.isNotEmpty(item.getItemCatalogIds())) {
								ItemCatalog itemCatalog = vixntBaseService.findEntityById(ItemCatalog.class, item.getItemCatalogIds());
								if (itemCatalog != null) {
									t.setCodeRule(itemCatalog.getCodeRule());
								}
							}
							Set<ChannelDistributor> subChannelDistributors = t.getSubChannelDistributors();
							subChannelDistributors.add(channelDistributor);
							t.setSubChannelDistributors(subChannelDistributors);
							if (item.getMeasureUnit() != null) {
								t.setSaleUnit(item.getMeasureUnit().getName());
							}
							t.setFlag("0");
							t = vixntBaseService.merge(t);
							storeItemAddList.add(t);
						}
					}
				}
				ChannelDistributorSet channelDistributorSet = getChannelDistributorSet(channelDistributor.getId());
				if (channelDistributorSet != null) {
					if ("0".equals(channelDistributorSet.getIsUpload())) {
						// 新增
						uploadItem(storeItemAddList, "A", channelDistributor);
						// 修改
						uploadItem(storeItemUpdateList, "U", channelDistributor);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 处理删除操作 */
	public void deleteById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				StoreItem storeItem = vixntBaseService.findEntityById(StoreItem.class, id);
				if (null != storeItem) {
					vixntBaseService.batchDeleteBySql("DELETE from DRP_STOREITEM_CHANNELDISTRIBUTOR where storeitem_id ='" + id + "'", null);
					vixntBaseService.deleteByEntity(storeItem);
					Employee employee = getEmployee();
					if (employee != null) {
						if (employee.getChannelDistributor() != null) {
							deleteItem(storeItem, employee.getChannelDistributor());
						} else {
							ChannelDistributor channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
							if (channelDistributor != null) {
								deleteItem(storeItem, channelDistributor);
							}
						}
					}
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public void deleteByIds() {
		try {
			ChannelDistributor channelDistributor = null;
			Employee employee = getEmployee();
			if (employee != null) {
				if (employee.getChannelDistributor() != null) {
					channelDistributor = employee.getChannelDistributor();
				} else {
					channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
				}
			}
			Map<String, Object> itemparams = new HashMap<String, Object>();
			itemparams.put("channelDistributorsId", channelDistributor.getId());
			List<StoreItem> storeItemList = vixntBaseService.findStoreItemListByHql(itemparams);
			if (storeItemList != null && storeItemList.size() > 0) {
				for (StoreItem storeItem : storeItemList) {
					if (null != storeItem) {
						vixntBaseService.batchDeleteBySql("DELETE from DRP_STOREITEM_CHANNELDISTRIBUTOR where storeitem_id ='" + storeItem.getId() + "'", null);
						vixntBaseService.deleteByEntity(storeItem);
						deleteItem(storeItem, channelDistributor);
						renderText(DELETE_SUCCESS);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StrUtils.isNotEmpty(storeItem.getId())) {
				isSave = false;
			}
			storeItem.setCreateTime(new Date());
			storeItem.setIsTemp("0");
			storeItem.setIsDeleted("0");
			storeItem.setItemClass(ItemConstant.GOODS);
			storeItem.setType("circulationIndustry");
			storeItem.setUpdateTime(new Date());
			initEntityBaseController.initEntityBaseAttribute(storeItem);

			MeasureUnit measureUnit = vixntBaseService.findEntityById(MeasureUnit.class, storeItem.getMeasureUnit().getId());
			if (measureUnit != null) {
				storeItem.setSaleUnit(measureUnit.getName());
			}
			ChannelDistributor channelDistributor = null;
			Employee employee = getEmployee();
			if (employee != null) {
				Set<ChannelDistributor> subChannelDistributors = new HashSet<ChannelDistributor>();
				if (employee.getChannelDistributor() != null) {
					channelDistributor = employee.getChannelDistributor();
					subChannelDistributors.add(employee.getChannelDistributor());
				} else {
					channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
					if (channelDistributor != null) {
						subChannelDistributors.add(channelDistributor);
					}
				}
				storeItem.setSubChannelDistributors(subChannelDistributors);
			}
			storeItem.setFlag("0");
			List<StoreItem> storeItemAddList = new ArrayList<StoreItem>();
			storeItem = itemService.merge(storeItem);
			storeItemAddList.add(storeItem);
			// 新增
			uploadItem(storeItemAddList, "A", channelDistributor);
			uploadItem(storeItemAddList, "U", channelDistributor);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
	}

	/** 树形结构JSON */
	public void findSupplierTree() {
		try {
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			List<Supplier> supplierList = vixntBaseService.findAllByConditions(Supplier.class, params);
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			strSb = loadAllSupplier(strSb, supplierList);
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private StringBuilder loadAllSupplier(StringBuilder strSb, List<Supplier> supplierList) throws Exception {
		for (int i = 0; i < supplierList.size(); i++) {
			Supplier ic = supplierList.get(i);
			strSb.append("{id:\"");
			strSb.append(ic.getId());
			strSb.append("\",name:\"");
			strSb.append(ic.getName());
			strSb.append("\",open:false,isParent:false}");
			if (i < supplierList.size() - 1) {
				strSb.append(",");
			}
		}
		return strSb;
	}

	/**
	 * 批量导入商品
	 */
	public void importFile() {
		FileInputStream fis = null;
		try {
			if (fileToUpload == null) {
			} else {
				try (InputStream xmlInputStream = ExcelTemplate.class.getResourceAsStream("storeitem_template.xml")) {
					XLSReader reader = ReaderBuilder.buildFromXML(xmlInputStream);
					try (InputStream xlsInputStream = new FileInputStream(fileToUpload)) {
						List<StoreItem> storeItemList = new ArrayList<StoreItem>();
						List<StoreItem> iList = new ArrayList<StoreItem>();
						Map<String, Object> beans = new HashMap<String, Object>();
						beans.put("storeItemList", storeItemList);
						reader.read(xlsInputStream, beans);
						ChannelDistributor channelDistributor = null;
						Employee employee = getEmployee();
						if (employee != null) {
							if (employee.getChannelDistributor() != null) {
								channelDistributor = employee.getChannelDistributor();
							} else {
								channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
							}
						}
						if (storeItemList != null && storeItemList.size() > 0) {
							for (StoreItem storeItem : storeItemList) {
								if (storeItem != null) {
									Map<String, Object> storeItemParams = new HashMap<String, Object>();
									storeItemParams.put("code", storeItem.getCode());
									storeItemParams.put("channelDistributorId", channelDistributor.getId());
									StringBuilder storeItemHql = standingBookHqlProvider.findStoreItemByItemCode(storeItemParams);
									StoreItem i = vixntBaseService.findObjectByHql(storeItemHql.toString(), storeItemParams);
									if (i != null) {
										i.setPurchasePrice(storeItem.getPurchasePrice());
										i.setPrice(storeItem.getPrice());
										i.setName(storeItem.getName());
										i.setVipPrice(storeItem.getVipPrice());
										i.setUpdateTime(new Date());
										i = vixntBaseService.merge(i);
										iList.add(i);
									} else {
										if (StringUtils.isNotEmpty(storeItem.getCodeRule())) {
											ItemCatalog itemCatalog = vixntBaseService.findEntityByAttribute(ItemCatalog.class, "codeRule", storeItem.getCodeRule());
											if (itemCatalog != null) {
												storeItem.setItemCatalog(itemCatalog);
											}
										}
										Map<String, Object> itemparams = new HashMap<String, Object>();
										itemparams.put("name", storeItem.getSaleUnit());
										StringBuilder itemhql = standingBookHqlProvider.findMeasureUnitByName(itemparams);
										MeasureUnit measureUnit = vixntBaseService.findObjectByHql(itemhql.toString(), itemparams);
										if (measureUnit != null) {
											storeItem.setMeasureUnit(measureUnit);
											if (measureUnit.getMeasureUnitGroup() != null) {
												storeItem.setMeasureUnitGroup(measureUnit.getMeasureUnitGroup());
											}
										}
										currencyTypeList = itemService.findAllByEntityClass(CurrencyType.class);
										if (currencyTypeList != null && currencyTypeList.size() > 0) {
											for (CurrencyType currencyType : currencyTypeList) {
												if (currencyType != null && "1".equals(currencyType.getIsBaseCurrency())) {
													storeItem.setCurrencyType(currencyType);
												}
											}
										}
										storeItem.setCreateTime(new Date());
										storeItem.setIsTemp("0");
										storeItem.setIsDeleted("0");
										storeItem.setItemClass(ItemConstant.GOODS);
										storeItem.setType("circulationIndustry");
										storeItem.setUpdateTime(new Date());
										initEntityBaseController.initEntityBaseAttribute(storeItem);
										storeItem.setFlag("0");
										Set<ChannelDistributor> subChannelDistributors = new HashSet<ChannelDistributor>();
										subChannelDistributors.add(channelDistributor);
										storeItem.setSubChannelDistributors(subChannelDistributors);
										storeItem = vixntBaseService.merge(storeItem);
										iList.add(storeItem);
									}
								}
							}
						}
						// 新增
						uploadItem(iList, "A", channelDistributor);
						uploadItem(iList, "U", channelDistributor);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				IOUtils.closeQuietly(fis);
			}
		}
	}

	public String goUpdatePrice() {
		return "goUpdatePrice";
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getItemIds() {
		return itemIds;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public String getMeasureUnitGroupId() {
		return measureUnitGroupId;
	}

	public void setMeasureUnitGroupId(String measureUnitGroupId) {
		this.measureUnitGroupId = measureUnitGroupId;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getItemids() {
		return itemids;
	}

	public void setItemids(String itemids) {
		this.itemids = itemids;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getVipPrice() {
		return vipPrice;
	}

	public void setVipPrice(Double vipPrice) {
		this.vipPrice = vipPrice;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public ChannelDistributor getChannelDistributor() {
		return channelDistributor;
	}

	public void setChannelDistributor(ChannelDistributor channelDistributor) {
		this.channelDistributor = channelDistributor;
	}

	public String getStoreItemId() {
		return storeItemId;
	}

	public void setStoreItemId(String storeItemId) {
		this.storeItemId = storeItemId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getChannelDistributorId() {
		return channelDistributorId;
	}

	public void setChannelDistributorId(String channelDistributorId) {
		this.channelDistributorId = channelDistributorId;
	}

	public StoreItem getStoreItem() {
		return storeItem;
	}

	public void setStoreItem(StoreItem storeItem) {
		this.storeItem = storeItem;
	}

	public List<MeasureUnitGroup> getMeasureUnitGroupList() {
		return measureUnitGroupList;
	}

	public void setMeasureUnitGroupList(List<MeasureUnitGroup> measureUnitGroupList) {
		this.measureUnitGroupList = measureUnitGroupList;
	}

	public List<MeasureUnit> getMeasureUnitList() {
		return measureUnitList;
	}

	public void setMeasureUnitList(List<MeasureUnit> measureUnitList) {
		this.measureUnitList = measureUnitList;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

}
