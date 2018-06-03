package com.vix.nvix.mdm.item.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.IOUtils;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xml.XmlAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.reader.ReaderBuilder;
import org.jxls.reader.XLSReader;
import org.jxls.transform.Transformer;
import org.jxls.util.TransformerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.common.share.entity.MeasureUnitGroup;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpand;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpandField;
import com.vix.core.persistence.jdbc.dbstruct.Specification;
import com.vix.core.persistence.jdbc.dbstruct.SpecificationDetail;
import com.vix.core.utils.PropertyConfigLoader;
import com.vix.core.utils.SortSet;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.hr.organization.entity.Employee;
import com.vix.inventory.initInventory.hql.StandingBookHqlProvider;
import com.vix.inventory.option.entity.InventoryParameters;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemBrand;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.mdm.item.entity.ItemImage;
import com.vix.mdm.item.entity.ItemInventoryProperties;
import com.vix.mdm.item.entity.ItemPurchaseProperties;
import com.vix.mdm.item.entity.StoreItem;
import com.vix.mdm.item.service.IItemService;
import com.vix.mdm.purchase.order.entity.OrderType;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.nvix.common.base.template.ExcelTemplate;
import com.vix.nvix.coupon.PromotionTypeConstant;
import com.vix.nvix.mdm.item.controller.UploadItemController;
import com.vix.system.enumeration.entity.EnumValue;
import com.vix.system.enumeration.entity.Enumeration;
import com.vix.system.expand.constant.ExpandConstant;
import com.vix.system.expand.service.ISpecificationService;

/**
 * 物料管理
 * 
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class NvixntMaterialAction extends VixntBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private IItemService itemService;
	@Autowired
	private ISpecificationService specificationService;
	@Autowired
	private UploadItemController uploadItemController;
	private String id;
	private String name;
	private String code;
	private String chooseName;
	private String treeType;
	private String categoryId;
	private String treeId;
	private String itemName;
	private String itemCode;
	private String sku;
	private String itemIds;
	private Item item;
	private List<Item> itemList;
	private ItemPurchaseProperties itemPurchaseProperties;
	private ItemInventoryProperties itemInventoryProperties;
	private String type;
	private String specificationDetail;
	private ItemImage itemImage;
	private InventoryParameters inventoryParameters;

	@Resource(name = "standingBookHqlProvider")
	private StandingBookHqlProvider standingBookHqlProvider;
	private Map<String, String> promotionTypeMap = PromotionTypeConstant.getItemType();
	@SuppressWarnings("unchecked")
	public void getItemJson() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("isItem," + SearchCondition.NOEQUAL, "T");
			if (isCheck) {
				params.put("isServiceItem," + SearchCondition.EQUAL, "Y");
			}
			params.put("isDeleted," + SearchCondition.NOEQUAL, "1");
			// params.put("type," + SearchCondition.EQUAL,
			// "circulationIndustry");
			if (null != itemName && !"".equals(itemName)) {
				itemName = decode(itemName, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, itemName);
			}
			if (null != itemCode && !"".equals(itemCode)) {
				itemCode = decode(itemCode, "UTF-8");
				params.put("code," + SearchCondition.ANYLIKE, itemCode);
			}
			if (StringUtils.isNotEmpty(treeId)) {
				params.put("itemCatalog.id," + SearchCondition.EQUAL, treeId);
			}
			if (null != categoryId && !"".equals(categoryId)) {
				itemService.findPagerByItemCatalogId(getPager(), categoryId, params);
			} else {
				itemService.findPagerByHqlConditions(getPager(), Item.class, params);
			}
			if (pager.getResultList().size() < 10) {
				int listSize = pager.getResultList().size();
				for (int i = 0; i < 10 - listSize; i++) {
					pager.getResultList().add(new Item());
				}
			}
			renderDataTable(pager);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	private List<ObjectExpand> objectExpandList;// 扩展表列表
	private List<ObjectExpandField> objectExpandFieldList;// 分类下商品扩展属性列表
	private List<CurrencyType> currencyTypeList;
	private List<MeasureUnitGroup> measureUnitGroupList;
	private List<OrderType> orderTypeList;
	private List<ItemBrand> itemBrandList;
	private String source;// item,fastitem
	// private List<Specification> specificationList;//商品规格列表
	public String goSaveOrUpdate() {
		try {
			List<InventoryParameters> inventoryParametersList = vixntBaseService.findAllByEntityClass(InventoryParameters.class);
			if (null != inventoryParametersList && inventoryParametersList.size() == 1) {
				inventoryParameters = inventoryParametersList.get(0);
			}
			// 商品品牌
			itemBrandList = itemService.findAllByEntityClass(ItemBrand.class);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name," + SearchCondition.NOEQUAL, "");
			// 计量单位组
			measureUnitGroupList = itemService.findAllByConditions(MeasureUnitGroup.class, params);
			measureUnitList = itemService.findAllByConditions(MeasureUnit.class, params);
			currencyTypeList = itemService.findAllByEntityClass(CurrencyType.class);
			orderTypeList = itemService.findAllByEntityClass(OrderType.class);
			Map<String, Object> expandParams = new HashMap<String, Object>();
			expandParams.put("isDelete," + SearchCondition.NOEQUAL, "1");
			expandParams.put("extendedObjectCode," + SearchCondition.EQUAL, ExpandConstant.EXPAND_TYPE_ITEM);
			// 商品类型
			objectExpandList = itemService.findAllByConditions(ObjectExpand.class, expandParams);
			if (StrUtils.isNotEmpty(id)) {
				item = itemService.findEntityById(Item.class, id);
				if (null != item && null != item.getObjectExpand() && null != item.getObjectExpand().getId()) {
					loadExpandFieldDetail(item.getId(), item.getObjectExpand().getId());
				}
				itemPurchaseProperties = itemService.findEntityByAttribute(ItemPurchaseProperties.class, "item.id", id);
				if (null == itemPurchaseProperties) {
					itemPurchaseProperties = new ItemPurchaseProperties();
				}
				itemInventoryProperties = itemService.findEntityByAttribute(ItemInventoryProperties.class, "item.id", id);
				if (null == itemInventoryProperties) {
					itemInventoryProperties = new ItemInventoryProperties();
				}
				if (null != item && null != item.getItemCatalog() && null != item.getItemCatalog().getId()) {
					Map<String, Object> specificationParams = getParams();
					specificationParams.put("itemCatalog.id," + SearchCondition.EQUAL, item.getItemCatalog().getId());
					specificationList = vixntBaseService.findAllByConditions(Specification.class, specificationParams);
				}
			} else {
				if (null == item) {
					item = new Item();
				}
				item.setStatus("T");
				item.setIsDiscount("F");
				itemPurchaseProperties = new ItemPurchaseProperties();
				itemInventoryProperties = new ItemInventoryProperties();
				if (StrUtils.isNotEmpty(categoryId)) {
					ItemCatalog ic = itemService.findEntityById(ItemCatalog.class, categoryId);
					item.setItemCatalog(ic);
					item.setCode(autoCreateCode.getBillNO(ic.getCodeRule()));
					Map<String, Object> specificationParams = getParams();
					specificationParams.put("itemCatalog.id," + SearchCondition.EQUAL, item.getItemCatalog().getId());
					specificationList = vixntBaseService.findAllByConditions(Specification.class, specificationParams);
				}
				item.setPlu(autoCreateCode.getBillNO("PLU"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ("Y".equals(item.getIsServiceProduct())) {
			return "goSaveOrUpdateService";
		}
		return GO_SAVE_OR_UPDATE;
	}
	/** 处理修改操作 */
	public void saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StrUtils.isNotEmpty(item.getId())) {
				isSave = false;
			} else {
				item.setCreateTime(new Date());
				loadCommonData(item);
			}
			item.setIsTemp("0");
			item.setIsItem("F");
			item.setIsDeleted("0");
			item.setItemClass("goods");
			item.setType("circulationIndustry");
			item.setIsServiceProduct("N");
			item.setUpdateTime(new Date());
			String[] attrArray = {"objectExpand", "currencyType", "measureUnitGroup", "measureUnit", "assitMeasureUnit", "itemBrand", "itemGroup", "itemCostProperties", "itemGernalProperties", "itemPurchaseProperties", "itemSaleProperties", "itemInventoryProperties", "itemMRPProperties", "itemPackageProperties", "itemPlanProperties", "itemQualityProperties"};
			checkEntityNullValue(item, attrArray);

			String appendFieldValue = getRequestParameter("appendFieldValue");
			dealItemCatalogs(item);
			item = itemService.merge(item);
			/*
			 * List<Item> iList = new ArrayList<Item>(); iList.add(item);
			 */
			// 同步商品到电商
			// uploadItemController.uploadItem(iList, ec_url, ec_tenantid,
			// ec_useraccount, ec_password);
			item = itemService.saveOrUpdateProduct(item, appendFieldValue);
			if (item != null) {
				updateItemName(item.getCode(), item.getName());
			}
			/** 采购 */
			if (null != itemPurchaseProperties) {
				String[] attrArrays = {"supplier", "orderType", "recieveWarehouse", "purchasePerson", "measureUnitGroup", "poUnit", "purAssitUnit", "purBaseUnit", "invShelf"};
				checkEntityNullValue(itemPurchaseProperties, attrArrays);
				if (null != itemPurchaseProperties.getItem()) {

				} else {
					itemPurchaseProperties.setItem(item);
				}
				itemPurchaseProperties = itemService.merge(itemPurchaseProperties);
			}
			/** 库存 */
			if (null != itemInventoryProperties) {
				String[] attrArrays = {"defaultWarehouse", "defaultInvShelf"};
				checkEntityNullValue(itemInventoryProperties, attrArrays);
				if (null != itemInventoryProperties.getItem()) {

				} else {
					itemInventoryProperties.setItem(item);
				}
				itemInventoryProperties = itemService.merge(itemInventoryProperties);
			}
			// String specificationDetail =
			// getDecodeRequestParameter("specificationDetail");
			if (null != specificationDetail && !"".equals(specificationDetail)) {
				dealSpecificationDetail(specificationDetail);
			}

			if (isSave) {
				renderText(item.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText(item.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:" + SAVE_FAIL);
			} else {
				renderText("0:" + UPDATE_FAIL);
			}
		}
	}
	public void uploadItem() {
		try {
			List<Item> iList = new ArrayList<Item>();
			if (StringUtils.isNotEmpty(itemIds)) {
				String[] ids = itemIds.split(",");
				if (ids.length > 0 && ids != null) {
					for (String string : ids) {
						Item item = vixntBaseService.findEntityById(Item.class, string);
						iList.add(item);
					}
				}
				uploadItemController.uploadItem(iList, ec_url, ec_tenantid, ec_useraccount, ec_password);
				renderText("0:同步成功!");
			} else if (StringUtils.isNotEmpty(id)) {
				Item item = vixntBaseService.findEntityById(Item.class, id);
				iList.add(item);
				uploadItemController.uploadItem(iList, ec_url, ec_tenantid, ec_useraccount, ec_password);
				renderText("0:同步成功!");
			} else {
				renderText("1:请选择商品!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("1:同步失败!");
		}
	}
	public void saveOrUpdateService() {
		boolean isSave = true;
		try {
			if (StrUtils.isNotEmpty(item.getId())) {
				isSave = false;
			} else {
				item.setCreateTime(new Date());
				loadCommonData(item);
				item.setIsTemp("0");
				item.setIsDeleted("0");
				item.setIsServiceProduct("Y");
			}
			if (item.getCurrencyType() == null || StringUtils.isEmpty(item.getCurrencyType().getId())) {
				item.setCurrencyType(null);
			}
			item = itemService.merge(item);

			if (isSave) {
				renderText(item.getId() + ":" + SAVE_SUCCESS);
			} else {
				renderText(item.getId() + ":" + UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText("0:" + SAVE_FAIL);
			} else {
				renderText("0:" + UPDATE_FAIL);
			}
		}
	}

	private void updateItemName(String itemCode, String itemName) {
		try {
			Map<String, Object> params = getParams();
			params.put("itemcode," + SearchCondition.EQUAL, itemCode);
			List<InventoryCurrentStock> inventoryCurrentStockList = vixntBaseService.findAllByConditions(InventoryCurrentStock.class, params);
			if (inventoryCurrentStockList.size() > 0) {
				for (InventoryCurrentStock inventoryCurrentStock : inventoryCurrentStockList) {
					if (inventoryCurrentStock != null) {
						inventoryCurrentStock.setItemname(itemName);
						inventoryCurrentStock = vixntBaseService.merge(inventoryCurrentStock);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void checkItemCode() {
		try {
			Map<String, Object> params = getParams();
			if (StringUtils.isNotEmpty(getRequestParameter("itemCode"))) {
				params.put("code," + SearchCondition.EQUAL, itemCode);
				itemList = itemService.findAllByConditions(Item.class, params);
				if (itemList.size() > 0) {
					renderText("商品编码重复,请更改编码!");
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
				Item item = itemService.findEntityById(Item.class, id);
				if (null != item) {
					item.setIsDeleted("1");
					item = itemService.merge(item);
					Map<String, Object> params = getParams();
					params.put("code," + SearchCondition.EQUAL, item.getCode());
					List<StoreItem> storeItemList = itemService.findAllByConditions(StoreItem.class, params);
					for (StoreItem storeItem : storeItemList) {
						if (null != storeItem) {
							vixntBaseService.batchDeleteBySql("DELETE from DRP_STOREITEM_CHANNELDISTRIBUTOR where storeitem_id ='" + id + "'", null);
							vixntBaseService.deleteByEntity(storeItem);
							Employee employee = getEmployee();
							if (employee != null) {
								if (employee.getChannelDistributor() != null) {
									// deleteItem(storeItem,
									// employee.getChannelDistributor());
								} else {
									ChannelDistributor channelDistributor = this.vixntBaseService.findEntityByAttribute(ChannelDistributor.class, "employee.id", employee.getId());
									if (channelDistributor != null) {
										// deleteItem(storeItem,
										// channelDistributor);
									}
								}
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

	public String goChooseMultiItemCatalog() {
		return "goChooseMultiItemCatalog";
	}

	/** 商品分类树形结构JSON */
	public void findTreeToJson() {
		try {
			List<ItemCatalog> listItemCatalog = new ArrayList<ItemCatalog>();
			/** 获取查询参数 */
			Map<String, Object> params = getParams();
			if (null != categoryId && !"".equals(categoryId)) {
				listItemCatalog = itemService.findAllSubEntity(ItemCatalog.class, "parentItemCatalog.id", categoryId, params);
			} else {
				listItemCatalog = itemService.findAllSubEntity(ItemCatalog.class, "parentItemCatalog.id", null, params);
			}
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			strSb = loadAllItemCatalog(strSb, listItemCatalog);
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private StringBuilder loadAllItemCatalog(StringBuilder strSb, List<ItemCatalog> listItemCatalog) throws Exception {
		for (int i = 0; i < listItemCatalog.size(); i++) {
			ItemCatalog ic = listItemCatalog.get(i);
			if (ic.getSubItemCatalogs().size() > 0) {
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getName());
				strSb.append("\",open:true,isParent:true,children:[");
				loadAllItemCatalog(strSb, new ArrayList<ItemCatalog>(ic.getSubItemCatalogs()));
				strSb.append("]}");
			} else {
				strSb.append("{id:\"");
				strSb.append(ic.getId());
				strSb.append("\",name:\"");
				strSb.append(ic.getName());
				strSb.append("\",open:false,isParent:false}");
			}
			if (i < listItemCatalog.size() - 1) {
				strSb.append(",");
			}
		}
		return strSb;
	}

	private List<MeasureUnit> measureUnitList;
	private String measureUnitGroupId;

	public String loadMeasureUnit() {
		try {
			if (StringUtils.isNotEmpty(measureUnitGroupId)) {
				measureUnitList = itemService.findAllByEntityClassAndAttribute(MeasureUnit.class, "measureUnitGroup.id", measureUnitGroupId);
			}
			if (StrUtils.isNotEmpty(id)) {
				item = itemService.findEntityById(Item.class, id);
				itemPurchaseProperties = itemService.findEntityByAttribute(ItemPurchaseProperties.class, "item.id", id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "loadMeasureUnit";
	}

	private void dealItemCatalogs(Item item) throws Exception {
		String icIds = item.getItemCatalogIds();
		if (StrUtils.objectIsNotNull(icIds)) {
			item.getItemCatalogs().clear();
			for (String idStr : icIds.split(",")) {
				if (null != idStr && !"".equals(idStr)) {
					ItemCatalog ic = itemService.findEntityById(ItemCatalog.class, idStr);
					if (null != ic) {
						item.getItemCatalogs().add(ic);
					}
				}
			}
		}
	}

	public String loadObjectExpandField() {
		if (StrUtils.isNotEmpty(id)) {
			try {
				String itemId = getRequestParameter("itemId");
				if (null != itemId && !"".equals(itemId)) {
					loadExpandFieldDetail(itemId, id);
				} else {
					loadExpandFieldDetail("", id);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return "dynamicItem";
	}

	/** 载入扩展属性明细 */
	private Map<String, Object> objectExpandFieldMap; // 商品扩展数据数据

	private void loadExpandFieldDetail(String itemId, String objectExpandId) throws Exception {
		ObjectExpand oe = itemService.findEntityById(ObjectExpand.class, objectExpandId);
		if (null != oe.getExpandTableName() && !"".equals(oe.getExpandTableName()) && itemService.checkTableExist(PropertyConfigLoader.dbType, oe.getExpandTableName())) {
			objectExpandFieldList = itemService.findAllByEntityClassAndAttribute(ObjectExpandField.class, "objectExpand.id", objectExpandId);
			if (null != itemId && !"".equals(itemId) && null != objectExpandFieldList && objectExpandFieldList.size() > 0) {
				String tableName = oe.getExpandTableName();
				StringBuilder sqlBuilder = new StringBuilder("select ");
				for (ObjectExpandField ef : objectExpandFieldList) {
					sqlBuilder.append(ef.getFieldName());
					sqlBuilder.append(",");
				}
				sqlBuilder.append("ID");
				if (null != oe.getForeignKey() && !"".equals(oe.getForeignKey())) {
					sqlBuilder.append("," + oe.getForeignKey());
				}
				sqlBuilder.append(" from ");
				sqlBuilder.append(tableName);
				if (null != oe.getForeignKey() && !"".equals(oe.getForeignKey())) {
					sqlBuilder.append(" where ");
					sqlBuilder.append(oe.getForeignKey());
					sqlBuilder.append(" = ");
					sqlBuilder.append("'" + itemId + "'");
				}
				objectExpandFieldMap = itemService.findProdcutAppend(sqlBuilder.toString());
			}
		}
	}

	/** 载入商品扩展字段值 */
	public String loadObjectExpandFieldValue(String fieldName) {
		if (null != objectExpandFieldMap && objectExpandFieldMap.containsKey(fieldName)) {
			Object obj = objectExpandFieldMap.get(fieldName);
			if (obj instanceof Date) {
				return formatDateToString((Date) obj);
			}
			return obj.toString();
		} else {
			return "";
		}
	}

	/** 载入商品扩展字段中下拉列表list */
	public List<EnumValue> loadObjectExpandFieldSelectList(String refTag) {
		List<EnumValue> evList = null;
		try {
			if (null != refTag && !"".equals(refTag)) {
				Enumeration e = itemService.findEntityByAttribute(Enumeration.class, "code", refTag);
				if (null != e && null != e.getEnumValues()) {
					evList = new ArrayList<EnumValue>(e.getEnumValues());
				} else {
					evList = new ArrayList<EnumValue>();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return evList;
	}

	/** 载入商品扩展字段中下拉列表list的value */
	public String loadObjectExpandFieldSelectValue(String fieldName) {
		if (null != objectExpandFieldMap && objectExpandFieldMap.containsKey(fieldName)) {
			Object obj = objectExpandFieldMap.get(fieldName);
			if (null != obj) {
				return obj.toString();
			} else {
				return "";
			}
		} else {
			return "";
		}
	}

	/** 载入商品扩展字段中checkbox的value */
	public boolean loadObjectExpandFieldCheckboxValue(String fieldNameAndEnumValue) {
		if (null != fieldNameAndEnumValue && !"".equals(fieldNameAndEnumValue)) {
			String[] fe = fieldNameAndEnumValue.split(",");
			if (null != objectExpandFieldMap && objectExpandFieldMap.containsKey(fe[0])) {
				Object obj = objectExpandFieldMap.get(fe[0]);
				if (null != obj && !"".equals(obj)) {
					if (obj.toString().contains("_" + fe[1] + "_")) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/** 载入商品扩展字段中radio的value */
	public boolean loadObjectExpandFieldRadioValue(String fieldNameAndEnumValue) {
		if (null != fieldNameAndEnumValue && !"".equals(fieldNameAndEnumValue)) {
			String[] fe = fieldNameAndEnumValue.split(",");
			if (null != objectExpandFieldMap && objectExpandFieldMap.containsKey(fe[0])) {
				Object obj = objectExpandFieldMap.get(fe[0]);
				if (null != obj && !"".equals(obj)) {
					if (obj.toString().contains(fe[1])) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/** 载入以保存的规格信息 */
	private List<List<Object>> savedSpecificationList;

	public String loadSpecificationDetail() {
		try {
			String itemId = getRequestParameter("itemId");
			String objectExpandId = getRequestParameter("objectExpandId");
			if (StrUtils.objectIsNotNull(objectExpandId)) {
				String oId = objectExpandId;
				ObjectExpand objectExpand = itemService.findEntityById(ObjectExpand.class, oId);
				specificationList = new ArrayList<Specification>(objectExpand.getSpecifications());
				if (StrUtils.objectIsNotNull(itemId)) {
					String pId = itemId;
					String specTableName = "";
					item = itemService.findEntityById(Item.class, pId);
					if (null != item) {
						if (null != specificationList && specificationList.size() > 0) {
							Specification s = specificationList.iterator().next();
							specTableName = s.getSpecificationTableName();
							if (StrUtils.objectIsNotNull(specTableName)) {
								SortSet scm = new SortSet("orderBy");
								Collections.sort(specificationList, scm);
								savedSpecificationList = itemService.findItemSpecification(specTableName, pId);
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "loadSpecificationDetail";
	}

	/**
	 * 规格生成后，提交修改时sku为必填项，sku对应的数据如果没有库存择允许删除，否则不允许删除。
	 */
	/** 定义规格表格容器 */
	private List<Specification> specificationList;// 分类下规格列表
	private List<List<SpecificationDetail>> specificationDetailList = new ArrayList<List<SpecificationDetail>>();

	public String generateSpecificationTable() {
		try {
			/** 获取选中规格数据 例如：':1,2,3,:7,8,' */
			String specificationDetailIds = getRequestParameter("specificationDetailIds");
			String objectExpandIdStr = getRequestParameter("objectExpandId");
			/** 载入表头 */
			String[] sdIdsGroup = specificationDetailIds.split(":");
			if (null != objectExpandIdStr && !"".equals(objectExpandIdStr)) {
				specificationList = itemService.findAllByEntityClassAndAttribute(Specification.class, "objectExpand.id", objectExpandIdStr);
				generateSpecificationTableBody(specificationDetailList, sdIdsGroup);// 生成规格矩阵
				/** 检查生成的规格列表，不允许有重复的数据 */
				String containsSpecDetail = getRequestParameter("containsSpecDetail");
				if (null != containsSpecDetail && !"".equals(containsSpecDetail)) {
					String[] csd = containsSpecDetail.split(":");
					Set<String> csdSet = new HashSet<String>();
					for (String code : csd) {
						if (null != code && !"".equals(code)) {
							csdSet.add(code);
						}
					}
					List<List<SpecificationDetail>> needRemove = new ArrayList<List<SpecificationDetail>>();
					for (List<SpecificationDetail> specList : specificationDetailList) {
						StringBuilder sdIds = new StringBuilder();
						for (SpecificationDetail sd : specList) {
							sdIds.append(sd.getId());
							sdIds.append(",");
						}
						if (csdSet.contains(sdIds.toString())) {
							needRemove.add(specList);
						}
						sdIds.delete(0, sdIds.length());
					}
					specificationDetailList.removeAll(needRemove);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "generateSpecificationTable";
	}

	/** 计算当前索引列每组相同数据的数量 */
	private static int currentSameDataCount(List<String[]> titleIdList, int startIdx) {
		int ret = 1;
		for (int i = startIdx; i < titleIdList.size(); i++) {
			if (titleIdList.get(i).length > 0) {
				ret = ret * titleIdList.get(i).length;
			}
		}
		return ret;
	}

	/** 生成规格表 吴磊实现 */
	private void generateSpecificationTableBody(List<List<SpecificationDetail>> sdl, String[] sdIdsGroup) throws Exception {
		List<String[]> sdIdList = new ArrayList<String[]>();// 将字符串转换成List
		Map<String, SpecificationDetail> sdMap = new HashMap<String, SpecificationDetail>();// 将所有规格遍历进map以便填充二维规格数组。
		for (int i = 0; i < sdIdsGroup.length; i++) {
			String sdIds = sdIdsGroup[i];
			if (null != sdIds && !"".equals(sdIds)) {
				String[] sdIdArray = sdIds.split(",");
				for (String sdId : sdIdArray) {
					if (null != sdId && !"".equals(sdId)) {
						SpecificationDetail sdc = itemService.findEntityById(SpecificationDetail.class, sdId);
						if (null != sdc) {
							sdMap.put(sdId, sdc);
						}
					}
				}
				sdIdList.add(sdIds.split(","));
			}
		}
		if (sdMap.keySet().size() <= 0) {
			return;
		}
		int totalRows = currentSameDataCount(sdIdList, 0);// 生成的规格矩阵总行数
		@SuppressWarnings("unchecked")
		/** 给数组集合赋值 */
		List<String>[] colList = new ArrayList[specificationList.size()];
		for (int colIdx = 0; colIdx < colList.length; colIdx++) {
			colList[colIdx] = new ArrayList<String>();
			if (colIdx < sdIdList.size()) {
				String[] titles = sdIdList.get(colIdx); // 1,2,3
				int subTitleCount = currentSameDataCount(sdIdList, colIdx + 1);// 当前每组相同数据的数量
				for (int rowLoop = 0; rowLoop < totalRows; rowLoop++) {
					int rowIdx = (rowLoop / subTitleCount) % titles.length;
					colList[colIdx].add(titles[rowIdx]);
				}
			} else {
				for (int rowLoop = 0; rowLoop < totalRows; rowLoop++) {
					colList[colIdx].add("");
				}
			}
		}
		/** 将纵向数据转为横向数据 */
		for (int i = 0; i < colList[0].size(); i++) {
			List<SpecificationDetail> sdList = new ArrayList<SpecificationDetail>();
			for (int j = 0; j < colList.length; j++) {
				String key = colList[j].get(i);
				if (null != key && !"".equals(key) && !"0".equals(key)) {
					sdList.add(sdMap.get(key));
				} else {
					sdList.add(new SpecificationDetail());
				}
			}
			sdl.add(sdList);
		}
	}

	/** 删除规格信息 */
	public void deleteItemSpecificationById() {
		try {
			String itemId = getRequestParameter("itemId");
			String specId = getRequestParameter("specId");
			if (StrUtils.objectIsNotNull(specId)) {
				if (StrUtils.objectIsNotNull(itemId)) {
					Item i = itemService.findEntityById(Item.class, itemId);
					String tableName = getSpecificationTableNameByItem(i);
					if (null != tableName && !"".equals(tableName)) {
						StringBuilder sqlBuilder = new StringBuilder("delete from ");
						sqlBuilder.append(tableName);
						sqlBuilder.append(" where id = ");
						sqlBuilder.append(specId);
						itemService.executeSql(sqlBuilder.toString(), null);
						renderText("规格信息删除成功!");
					} else {
						renderText("规格表未获取到!");
					}
				} else {
					renderText("物料id未获取到!");
				}
			} else {
				renderText("物料规格id未获取到!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/** 根据商品获取商品规格扩展表名称 */
	private String getSpecificationTableNameByItem(Item i) throws Exception {
		String specTableName = "";
		if (null == i) {
			return specTableName;
		}
		if (null == i.getItemCatalog()) {
			return specTableName;
		}
		Set<Specification> ls = i.getItemCatalog().getSpecifications();
		if (null != ls && ls.size() > 0) {
			for (Specification s : ls) {
				specTableName = s.getSpecificationTableName();
				break;
			}
		}
		return specTableName;
	}

	/** 检查sku是否重复 */
	public void checkSkuIsExist() {
		try {
			String itemId = getRequestParameter("ecProductId");
			String sku = getRequestParameter("sku");
			if (StrUtils.objectIsNotNull(itemId) && StrUtils.objectIsNotNull(sku)) {
				Item i = itemService.findEntityById(Item.class, itemId);
				String tableName = specificationService.findTableNameByProductCategoryId(i.getItemCatalog().getId());
				if (null != tableName && !"".equals(tableName)) {
					StringBuilder sqlBuilder = new StringBuilder("select * from ");
					sqlBuilder.append(tableName);
					sqlBuilder.append(" where sku = '");
					sqlBuilder.append(sku);
					sqlBuilder.append("'");
					List<Object> list = itemService.findListBySql(sqlBuilder.toString());
					if (null != list && list.size() > 0) {
						renderText("exist");
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private String specJson;

	public String loadContainsSkuJson() {
		try {
			String itemId = getRequestParameter("itemId");
			if (StrUtils.objectIsNotNull(itemId)) {
				Item i = itemService.findEntityById(Item.class, itemId);
				String tableName = getSpecificationTableNameByItem(i);
				savedSpecificationList = itemService.findItemSpecification(tableName, i.getId());
				if (null != savedSpecificationList && savedSpecificationList.size() > 0) {
					StringBuilder jsonBuilder = new StringBuilder();
					for (int j = 0; j < savedSpecificationList.size(); j++) {
						List<Object> specList = savedSpecificationList.get(j);
						if (specList.size() > 3) {
							jsonBuilder.append(specList.get(1));
							jsonBuilder.append("!");
							for (int k = 4; k < specList.size(); k++) {
								jsonBuilder.append(specList.get(k));
								jsonBuilder.append(",");
							}
							if (j < savedSpecificationList.size() - 1) {
								jsonBuilder.append(":");
							}
						}
					}
					specJson = jsonBuilder.toString();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "specJson";
	}

	/** 规格数据格式： sku!specCode(,)!specName(,): */
	public void dealSpecificationDetail(String specificationDetail) throws Exception {
		List<Specification> sList = new ArrayList<Specification>(item.getItemCatalog().getSpecifications());
		String tableName = specificationService.findTableNameByProductCategoryId(item.getItemCatalog().getId());
		String itemId = item.getId();
		String objectExpandId = item.getItemCatalog().getId();
		String tenantId = SecurityUtil.getCurrentUserTenantId();
		SortSet scm = new SortSet("orderBy");
		Collections.sort(sList, scm);
		String[] sds = specificationDetail.split(":");
		for (String sd : sds) {
			if (null != sd && !"".equals(sd)) {
				String[] specDetail = sd.split("!");
				String[] specSingle = specDetail[2].split(",");
				StringBuilder sqlBuilder = new StringBuilder("update ");
				sqlBuilder.append(tableName);
				sqlBuilder.append(" set SKU = '");
				sqlBuilder.append(specDetail[1]);
				sqlBuilder.append("', OBJECT_ID = '");
				sqlBuilder.append(itemId);
				sqlBuilder.append("', MDM_ITEMCATALOG_ID = '");
				sqlBuilder.append(objectExpandId);
				sqlBuilder.append("',");
				sqlBuilder.append("TENANTID = '");
				sqlBuilder.append(tenantId + "', INVENTORY_COUNT = ");
				for (int i = 0; i < specSingle.length; i++) {
					if (!"PlaceHolder".equals(specSingle[i])) {
						sqlBuilder.append(specSingle[i]);
					} else {
						sqlBuilder.append(0d);
					}
				}
				sqlBuilder.append(" where id = '" + specDetail[0] + "'");
				itemService.executeSql(sqlBuilder.toString(), null);
			}
		}
	}

	/** 选择导入物料的文件 */
	public String chooseItemFile() {
		return "chooseItemFile";
	}
	
	/** 选择物料  */
	public String chooseItem() {
		return "chooseItem";
	}
	
	public void getChooseItemJson() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("isItem," + SearchCondition.NOEQUAL, "T");
			params.put("isDeleted," + SearchCondition.NOEQUAL, "1");
			if (null != itemName && !"".equals(itemName)) {
				itemName = decode(itemName, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, itemName);
			}
			if (null != itemCode && !"".equals(itemCode)) {
				itemCode = decode(itemCode, "UTF-8");
				params.put("code," + SearchCondition.ANYLIKE, itemCode);
			}
			if (null != categoryId && !"".equals(categoryId)) {
				params.put("itemCatalog.id," + SearchCondition.EQUAL, categoryId);
				//itemService.findPagerByItemCatalogId(getPager(), categoryId, params);
			} 
			itemService.findPagerByHqlConditions(getPager(), Item.class, params);
			renderDataTable(pager);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 批量导入商品
	 */
	public void importFile() {
		FileInputStream fis = null;
		try {
			if (fileToUpload == null) {
			} else {
				try (InputStream xmlInputStream = ExcelTemplate.class.getResourceAsStream("item_template.xml")) {
					XLSReader reader = ReaderBuilder.buildFromXML(xmlInputStream);
					try (InputStream xlsInputStream = new FileInputStream(fileToUpload)) {
						List<Item> itemList = new ArrayList<Item>();
						List<Item> iList = new ArrayList<Item>();
						Map<String, Object> beans = new HashMap<String, Object>();
						beans.put("itemList", itemList);
						reader.read(xlsInputStream, beans);
						if (itemList != null && itemList.size() > 0) {
							for (Item item : itemList) {
								Item i = vixntBaseService.findEntityByAttribute(Item.class, "code", item.getCode());
								if (i != null) {
									i.setPurchasePrice(item.getPurchasePrice());
									i.setPrice(item.getPrice());
									i.setVipPrice(item.getVipPrice());
									i = vixntBaseService.merge(i);
									iList.add(item);
								} else {
									if (StringUtils.isNotEmpty(item.getItemCataCode())) {
										ItemCatalog itemCatalog = vixntBaseService.findEntityByAttribute(ItemCatalog.class, "codeRule", item.getItemCataCode());
										if (itemCatalog != null) {
											item.setItemCatalog(itemCatalog);
											item.setItemCatalogName(itemCatalog.getName());
										}
									}
									Map<String, Object> itemparams = new HashMap<String, Object>();
									itemparams.put("name", item.getSaleUnit());
									StringBuilder itemhql = standingBookHqlProvider.findMeasureUnitByName(itemparams);
									MeasureUnit measureUnit = vixntBaseService.findObjectByHql(itemhql.toString(), itemparams);
									if (measureUnit != null) {
										item.setMeasureUnit(measureUnit);
										if (measureUnit.getMeasureUnitGroup() != null) {
											item.setMeasureUnitGroup(measureUnit.getMeasureUnitGroup());
										}
									}
									currencyTypeList = itemService.findAllByEntityClass(CurrencyType.class);
									if (currencyTypeList != null && currencyTypeList.size() > 0) {
										for (CurrencyType currencyType : currencyTypeList) {
											if (currencyType != null && "1".equals(currencyType.getIsBaseCurrency())) {
												item.setCurrencyType(currencyType);
											}
										}
									}
									item.setCreateTime(new Date());
									item.setIsTemp("0");
									item.setIsDeleted("0");
									item.setItemClass("goods");
									item.setType("circulationIndustry");
									item.setUpdateTime(new Date());
									initEntityBaseController.initEntityBaseAttribute(item);
									item = vixntBaseService.merge(item);
									iList.add(item);
								}
							}
							// 同步商品到电商
							uploadItemController.uploadItem(iList, ec_url, ec_tenantid, ec_useraccount, ec_password);
							renderText("成功导入" + iList.size() + "条数据.");
						}
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

	public String exportItemExcel() throws Exception {
		try {
			HttpServletResponse excelResponse = getResponse();
			excelResponse.setContentType("application/octet-stream");
			excelResponse.setHeader("Charset", "UTF-8");
			excelResponse.setCharacterEncoding("UTF-8");

			String ua = getRequest().getHeader("user-agent");
			String fileName = "商品表.xls";
			if (ua != null && ua.indexOf("Firefox") >= 0)
				excelResponse.addHeader("Content-Disposition", "attachment; filename*=\"UTF-8''" + URLEncoder.encode(fileName, "UTF-8") + "\"");
			else
				excelResponse.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

			Map<String, Object> params = getParams();
			List<Item> itemList = vixntBaseService.findAllByConditions(Item.class, params);
			try (InputStream is = ExcelTemplate.class.getResourceAsStream("item_export_template.xls")) {
				Transformer transformer = TransformerFactory.createTransformer(is, excelResponse.getOutputStream());
				try (InputStream configInputStream = ExcelTemplate.class.getResourceAsStream("item_export_template.xml")) {
					AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
					List<Area> xlsAreaList = areaBuilder.build();
					Area xlsArea = xlsAreaList.get(0);
					Context context = new Context();
					context.putVar("itemList", itemList);
					xlsArea.applyAt(new CellRef("item!A1"), context);
					transformer.write();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}

	/**
	 * 下载商品模板
	 * 
	 * @return
	 */
	public String downloadTemplate() {
		try {
			String fileName = "item_template.xls";
			setOriFileName(fileName);
			InputStream xmlInputStream = ExcelTemplate.class.getResourceAsStream(fileName);
			setInputStream(xmlInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "downloadTemplate";
	}

	/**
	 * 加载规格组合列表
	 */
	public void loadSpecificationDetailJson() {
		try {
			if (StrUtils.objectIsNotNull(categoryId)) {
				String specTableName = specificationService.findTableNameByProductCategoryId(categoryId);
				/** 加载规格 */
				specificationList = specificationService.findSpecificationByProductCatetoryId(categoryId, getParams());
				SortSet ss = new SortSet("orderBy", "asc");
				Collections.sort(specificationList, ss);
				if (null != specificationList && specificationList.size() > 0) {
					if (StrUtils.objectIsNotNull(specTableName)) {
						SortSet scm = new SortSet("orderBy", "asc");
						Collections.sort(specificationList, scm);
						if (StrUtils.objectIsNotNull(id)) {
							savedSpecificationList = itemService.findProductSpecification(specTableName, id, sku);
						}
					}
				}
			}
			renderDataToJson(savedSpecificationList, null);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/** 添加规格组合 */
	public void addSpecificationGroup() {
		try {
			/** 获取选中规格数据 例如：':1,2,3,:7,8,' */
			String specificationDetailIds = getRequestParameter("specificationDetailIds");
			/** 分类id */
			String productCategoryIdStr = getRequestParameter("productCategoryId");
			/** 之前保存的规格数据 */
			String specData = getRequestParameter("specData");
			/** 载入表头 */
			String[] sdIdsGroup = specificationDetailIds.split(":");
			if (StrUtils.objectIsNotNull(productCategoryIdStr) && StrUtils.objectIsNotNull(id)) {
				specificationList = specificationService.findSpecificationByProductCatetoryId(productCategoryIdStr, getParams());
				SortSet ss = new SortSet("orderBy", "asc");
				Collections.sort(specificationList, ss);
				generateSpecificationTableBody(specificationDetailList, sdIdsGroup);// 生成规格矩阵
				boolean tag = specificationService.addSpecificationGroup(id, productCategoryIdStr, specificationDetailList, specData, getParams());
				if (tag) {
					renderJson("1");
					return;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		renderJson("0");
	}

	/** 删除规格组合 */
	public void deleteSpecificationGroup() {
		try {
			String sgId = getRequestParameter("sgId");
			String ecProductId = getRequestParameter("ecProductId");
			String productCategoryId = getRequestParameter("productCategoryId");
			if (StrUtils.objectIsNotNull(sgId) && StrUtils.objectIsNotNull(ecProductId) && StrUtils.objectIsNotNull(productCategoryId)) {
				boolean tag = specificationService.deleteSpecificationGroup(sgId, ecProductId, productCategoryId);
				if (tag) {
					renderJson("1");
					return;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		renderJson("0");
	}
	/**
	 * 获取商品图片
	 */
	public void getItemImageList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			String searchName = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("name," + SearchCondition.ANYLIKE, searchName);
			}
			if (StringUtils.isNotEmpty(id)) {
				params.put("item.id," + SearchCondition.EQUAL, id);
				pager = vixntBaseService.findPagerByHqlConditions(pager, ItemImage.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdateImage() {
		try {
			String itemImageId = getDecodeRequestParameter("itemImageId");
			if (StringUtils.isNotEmpty(itemImageId)) {
				itemImage = vixntBaseService.findEntityById(ItemImage.class, itemImageId);
			} else {
				itemImage = new ItemImage();
				if (StringUtils.isNotEmpty(id)) {
					item = vixntBaseService.findEntityById(Item.class, id);
					if (null != item) {
						itemImage.setItem(item);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateImage";
	}
	/**
	 * 上传商品图片
	 */
	public void uploadEcPrudoctImage() {
		try {
			String[] savePathAndName = saveUploadPic();
			if (savePathAndName != null && savePathAndName.length == 2) {
				ItemImage itemImage = new ItemImage();
				itemImage.setImgPath("/img/ws/" + savePathAndName[1].toString());
				itemImage.setCreateTime(new Date());
				itemImage = vixntBaseService.merge(itemImage);
				if (itemImage != null) {
					renderText(itemImage.getId() + ":" + "/img/ws/" + savePathAndName[1].toString() + ":" + savePathAndName[1].toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:上传失败!");
		}
	}
	/**
	 * 上传商品主图
	 */
	public void uploadItemImage() {
		try {
			String[] savePathAndName = saveUploadPic();
			if (StringUtils.isNotEmpty(id)) {
				item = vixntBaseService.findEntityById(Item.class, id);
				if (item != null) {
					if (StringUtils.isNotEmpty(item.getImageFilePath())) {// 是否上传过主图
						String fileName = item.getImageFilePath().substring(item.getImageFilePath().lastIndexOf("/"), item.getImageFilePath().length());
						File f = new File("c:/img/" + fileName); // 输入要删除的文件位置
						if (f.exists()) {
							f.delete();
						}
					}
					item.setImageFilePath("/img/ws/" + savePathAndName[1].toString());
					item = vixntBaseService.merge(item);
					renderText("1:" + item.getImageFilePath());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText("0:上传失败!");
		}
	}
	/**
	 * 保存图片信息
	 */
	public void saveOrUpdateImage() {
		try {
			if (StringUtils.isNotEmpty(itemImage.getId())) {
				itemImage = vixntBaseService.merge(itemImage);
				renderText(SAVE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
	}
	/**
	 * 删除上传图片
	 */
	public void deleteImageById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				itemImage = vixntBaseService.findEntityById(ItemImage.class, id);
				if (null != itemImage) {
					String fileName = itemImage.getPersisName();
					String baseFolder = "c:/img/";
					String downloadFile = baseFolder + fileName;
					File f = new File(downloadFile); // 输入要删除的文件位置
					if (f.exists()) {
						f.delete();
					}
					vixntBaseService.deleteByEntity(itemImage);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ItemPurchaseProperties getItemPurchaseProperties() {
		return itemPurchaseProperties;
	}

	public void setItemPurchaseProperties(ItemPurchaseProperties itemPurchaseProperties) {
		this.itemPurchaseProperties = itemPurchaseProperties;
	}

	public List<ObjectExpand> getObjectExpandList() {
		return objectExpandList;
	}

	public void setObjectExpandList(List<ObjectExpand> objectExpandList) {
		this.objectExpandList = objectExpandList;
	}

	public List<ObjectExpandField> getObjectExpandFieldList() {
		return objectExpandFieldList;
	}

	public void setObjectExpandFieldList(List<ObjectExpandField> objectExpandFieldList) {
		this.objectExpandFieldList = objectExpandFieldList;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public ItemInventoryProperties getItemInventoryProperties() {
		return itemInventoryProperties;
	}

	public void setItemInventoryProperties(ItemInventoryProperties itemInventoryProperties) {
		this.itemInventoryProperties = itemInventoryProperties;
	}

	public List<MeasureUnitGroup> getMeasureUnitGroupList() {
		return measureUnitGroupList;
	}

	public void setMeasureUnitGroupList(List<MeasureUnitGroup> measureUnitGroupList) {
		this.measureUnitGroupList = measureUnitGroupList;
	}

	public List<OrderType> getOrderTypeList() {
		return orderTypeList;
	}

	public void setOrderTypeList(List<OrderType> orderTypeList) {
		this.orderTypeList = orderTypeList;
	}

	public List<ItemBrand> getItemBrandList() {
		return itemBrandList;
	}

	public void setItemBrandList(List<ItemBrand> itemBrandList) {
		this.itemBrandList = itemBrandList;
	}

	public Map<String, Object> getObjectExpandFieldMap() {
		return objectExpandFieldMap;
	}

	public void setObjectExpandFieldMap(Map<String, Object> objectExpandFieldMap) {
		this.objectExpandFieldMap = objectExpandFieldMap;
	}

	public List<List<Object>> getSavedSpecificationList() {
		return savedSpecificationList;
	}

	public void setSavedSpecificationList(List<List<Object>> savedSpecificationList) {
		this.savedSpecificationList = savedSpecificationList;
	}

	public List<Specification> getSpecificationList() {
		return specificationList;
	}

	public void setSpecificationList(List<Specification> specificationList) {
		this.specificationList = specificationList;
	}

	public List<List<SpecificationDetail>> getSpecificationDetailList() {
		return specificationDetailList;
	}

	public void setSpecificationDetailList(List<List<SpecificationDetail>> specificationDetailList) {
		this.specificationDetailList = specificationDetailList;
	}

	public String getSpecJson() {
		return specJson;
	}

	public void setSpecJson(String specJson) {
		this.specJson = specJson;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	@Override
	public String getSource() {
		return source;
	}

	@Override
	public void setSource(String source) {
		this.source = source;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getChooseName() {
		return chooseName;
	}

	public void setChooseName(String chooseName) {
		this.chooseName = chooseName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<MeasureUnit> getMeasureUnitList() {
		return measureUnitList;
	}

	public void setMeasureUnitList(List<MeasureUnit> measureUnitList) {
		this.measureUnitList = measureUnitList;
	}

	public String getMeasureUnitGroupId() {
		return measureUnitGroupId;
	}

	public void setMeasureUnitGroupId(String measureUnitGroupId) {
		this.measureUnitGroupId = measureUnitGroupId;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getSpecificationDetail() {
		return specificationDetail;
	}

	public void setSpecificationDetail(String specificationDetail) {
		this.specificationDetail = specificationDetail;
	}

	public String getItemIds() {
		return itemIds;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public ItemImage getItemImage() {
		return itemImage;
	}

	public void setItemImage(ItemImage itemImage) {
		this.itemImage = itemImage;
	}

	public InventoryParameters getInventoryParameters() {
		return inventoryParameters;
	}

	public void setInventoryParameters(InventoryParameters inventoryParameters) {
		this.inventoryParameters = inventoryParameters;
	}

	public Map<String, String> getPromotionTypeMap() {
		return promotionTypeMap;
	}

	public void setPromotionTypeMap(Map<String, String> promotionTypeMap) {
		this.promotionTypeMap = promotionTypeMap;
	}
}
