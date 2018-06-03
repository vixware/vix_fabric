package com.vix.mdm.item.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.MeasureUnit;
import com.vix.common.share.entity.MeasureUnitGroup;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpand;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpandField;
import com.vix.core.persistence.jdbc.dbstruct.Specification;
import com.vix.core.persistence.jdbc.dbstruct.SpecificationDetail;
import com.vix.core.utils.NumberUtil;
import com.vix.core.utils.PropertyConfigLoader;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.inventory.warehouse.entity.InvShelf;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemAttachFile;
import com.vix.mdm.item.entity.ItemBrand;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.mdm.item.entity.ItemCostProperties;
import com.vix.mdm.item.entity.ItemFinanceProperties;
import com.vix.mdm.item.entity.ItemGernalProperties;
import com.vix.mdm.item.entity.ItemGroup;
import com.vix.mdm.item.entity.ItemInventoryProperties;
import com.vix.mdm.item.entity.ItemMRPProperties;
import com.vix.mdm.item.entity.ItemPlanProperties;
import com.vix.mdm.item.entity.ItemPurchaseProperties;
import com.vix.mdm.item.entity.ItemQualityProperties;
import com.vix.mdm.item.entity.ItemReplace;
import com.vix.mdm.item.entity.ItemSaleProperties;
import com.vix.mdm.item.service.IItemService;
import com.vix.mdm.purchase.order.entity.OrderType;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.system.code.entity.EncodingRulesTableInTheMiddle;
import com.vix.system.enumeration.entity.EnumValue;
import com.vix.system.enumeration.entity.Enumeration;
import com.vix.system.expand.constant.ExpandConstant;

import flexjson.JSONSerializer;

@Controller
@Scope("prototype")
public class ItemAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IItemService itemService;

	private String id;
	private String name;
	private String code;
	private String categoryId;
	private String ids;
	private String tag;// multi为多选，默认为单选
	private String type;// 单位类型
	private Item item;
	private ItemGernalProperties itemGernalProperties;
	private String pageNo;
	private ItemReplace itemReplace;
	private List<Item> itemList;
	/** 附件 */
	private File fileToUpload;
	/** 附件的名称 */
	private String fileToUploadFileName;

	@Override
	@SuppressWarnings("unchecked")
	public String goList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(20);
			getPager().setOrderField("id");
			getPager().setOrderBy("desc");
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("isDeleted," + SearchCondition.NOEQUAL, "1");
			itemService.findPagerByHqlConditions(getPager(), Item.class, params);
			itemList = getPager().getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goList";
	}

	// 高级查询
	public String goSearch() {
		return "goSearch";
	}

	/** 获取列表数据 */
	public String goListContent() {
		try {
			Map<String, Object> params = getParams();
			if (null != pageNo && NumberUtil.isNumeric(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("isDeleted," + SearchCondition.NOEQUAL, "1");
			if (null != name && !"".equals(name)) {
				name = decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			if (null != code && !"".equals(code)) {
				code = decode(code, "UTF-8");
				params.put("code," + SearchCondition.ANYLIKE, code);
			}
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("id");
				getPager().setOrderBy("desc");
			}
			if (null != categoryId && !"".equals(categoryId)) {
				Pager pager = itemService.findPagerByItemCatalogId(getPager(), categoryId, params);
				setPager(pager);
			} else {
				itemService.findPagerByHqlConditions(getPager(), Item.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListContent";
	}

	/** 获取列表数据 */
	public String goChooseListContent() {
		try {
			Map<String, Object> params = getParams();
			String name = getRequestParameter("name");
			if (null != name && !"".equals(name)) {
				name = decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
			}
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			params.put("isDeleted," + SearchCondition.NOEQUAL, "1");
			getPager().setPageSize(10);
			if (null != categoryId && !"".equals(categoryId)) {
				Pager pager = itemService.findPagerByItemCatalogId(getPager(), categoryId, params);
				setPager(pager);
			} else {
				itemService.findPagerByHqlConditions(getPager(), Item.class, params);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goChooseListContent";
	}

	public String goSubSingleList() {
		try {
			Map<String, Object> params = getParams();
			getPager().setPageSize(6);
			getParams().put("isTemp," + SearchCondition.NOEQUAL, "1");
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = itemService.findPagerByHqlConditions(getPager(), Item.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SUB_SINGLE_LIST;
	}

	/** 跳转至用户修改页面 */
	private List<ObjectExpand> objectExpandList;// 扩展表列表
	private List<ObjectExpandField> objectExpandFieldList;// 分类下商品扩展属性列表

	public String goSaveOrUpdate() {
		try {
			EncodingRulesTableInTheMiddle erttm = itemService.findEntityByAttribute(EncodingRulesTableInTheMiddle.class, "billType", "ItemCode");
			if (null == erttm) {
				EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle = new EncodingRulesTableInTheMiddle();
				initEntityBaseController.initEntityBaseAttribute(encodingRulesTableInTheMiddle);
				encodingRulesTableInTheMiddle.setBillType("ItemCode");
				encodingRulesTableInTheMiddle.setCodeLength(10);
				encodingRulesTableInTheMiddle.setEnableSeries(2);
				encodingRulesTableInTheMiddle.setCodeType("C");
				encodingRulesTableInTheMiddle.setSerialNumberBegin(1l);
				encodingRulesTableInTheMiddle.setSequenceID("9");
				encodingRulesTableInTheMiddle.setSerialNumberEnd(999999999L);
				encodingRulesTableInTheMiddle.setSerialNumberStep(1);
				itemService.merge(encodingRulesTableInTheMiddle);
			}
			if (null != id && !id.equals("") && !id.equals("0")) {
				item = itemService.findEntityById(Item.class, id);
			} else {
				if (null == item) {
					item = new Item();
					item.setCode(autoCreateCode.getBillNO("ItemCode"));
				}
				item.setIsTemp("1");
				item.setIsDeleted("0");
				item.setCreateTime(new Date());
				if (null != categoryId && !categoryId.equals("") && !categoryId.equals("0")) {
					ItemCatalog ic = itemService.findEntityById(ItemCatalog.class, categoryId);
					item.getItemCatalogs().add(ic);
				}
				loadCommonData(item);
				item = itemService.merge(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 跳转至用户修改页面 */
	private List<CurrencyType> currencyTypeList;
	private List<MeasureUnitGroup> measureUnitGroupList;
	private List<ItemGroup> itemGroupList;
	private List<ItemBrand> itemBrandList;

	public String goSaveOrUpdateItemTabOne() {
		try {
			currencyTypeList = itemService.findAllByEntityClass(CurrencyType.class);
			itemGroupList = itemService.findAllByEntityClassAndAttribute(ItemGroup.class, "isTemp", "0");
			itemBrandList = itemService.findAllByEntityClass(ItemBrand.class);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name," + SearchCondition.NOEQUAL, "");
			measureUnitGroupList = itemService.findAllByConditions(MeasureUnitGroup.class, params);
			Map<String, Object> expandParams = new HashMap<String, Object>();
			expandParams.put("isTemp," + SearchCondition.NOEQUAL, "1");
			expandParams.put("isDelete," + SearchCondition.NOEQUAL, "1");
			expandParams.put("extendedObjectCode," + SearchCondition.EQUAL, ExpandConstant.EXPAND_TYPE_ITEM);
			objectExpandList = itemService.findAllByConditions(ObjectExpand.class, expandParams);
			if (null != id && !id.equals("") && !id.equals("0")) {
				item = itemService.findEntityById(Item.class, id);
				if (null != item && null != item.getObjectExpand() && null != item.getObjectExpand().getId()) {
					loadExpandFieldDetail(item.getId(), item.getObjectExpand().getId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateItemTabOne";
	}

	/** 跳转至用户修改页面（采购，销售，库存） */
	private ItemPurchaseProperties itemPurchaseProperties;
	private ItemSaleProperties itemSaleProperties;
	private ItemInventoryProperties itemInventoryProperties;
	private List<OrderType> orderTypeList;

	public String goSaveOrUpdateItemTabTwo() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name," + SearchCondition.NOEQUAL, "");
			measureUnitGroupList = itemService.findAllByConditions(MeasureUnitGroup.class, params);
			orderTypeList = itemService.findAllByEntityClass(OrderType.class);
			if (null != id && !id.equals("") && !id.equals("0")) {
				item = itemService.findEntityById(Item.class, id);
				itemPurchaseProperties = itemService.findEntityByAttribute(ItemPurchaseProperties.class, "item.id", id);
				itemSaleProperties = itemService.findEntityByAttribute(ItemSaleProperties.class, "item.id", id);
				itemInventoryProperties = itemService.findEntityByAttribute(ItemInventoryProperties.class, "item.id", id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateItemTabTwo";
	}

	/** 跳转至用户修改页面 财务 */
	private ItemCostProperties itemCostProperties;
	private ItemFinanceProperties itemFinanceProperties;

	public String goSaveOrUpdateItemTabThree() {
		try {
			if (null != id && !id.equals("") && !id.equals("0")) {
				item = itemService.findEntityById(Item.class, id);
				itemCostProperties = itemService.findEntityByAttribute(ItemCostProperties.class, "item.id", id);
				itemFinanceProperties = itemService.findEntityByAttribute(ItemFinanceProperties.class, "item.id", id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateItemTabThree";
	}

	/** 跳转至用户修改页面 MRP 计划 质量 */
	private ItemMRPProperties itemMRPProperties;
	private ItemPlanProperties itemPlanProperties;
	private ItemQualityProperties itemQualityProperties;

	public String goSaveOrUpdateItemTabFour() {
		try {
			if (null != id && !id.equals("") && !id.equals("0")) {
				item = itemService.findEntityById(Item.class, id);
				itemMRPProperties = itemService.findEntityByAttribute(ItemMRPProperties.class, "item.id", id);
				itemPlanProperties = itemService.findEntityByAttribute(ItemPlanProperties.class, "item.id", id);
				itemQualityProperties = itemService.findEntityByAttribute(ItemQualityProperties.class, "item.id", id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateItemTabFour";
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != item && null != item.getId() && !"".equals(item.getId())) {
				isSave = false;
			}
			/** 采购 */
			if (null != itemPurchaseProperties) {
				String[] attrArray = {"item", "supplier", "orderType", "recieveWarehouse", "purchasePerson", "measureUnitGroup", "poUnit", "purAssitUnit", "purBaseUnit", "invShelf"};
				checkEntityNullValue(itemPurchaseProperties, attrArray);
				itemPurchaseProperties = itemService.merge(itemPurchaseProperties);
				renderText(UPDATE_SUCCESS);

			}
			/** 销售 */
			if (null != itemSaleProperties) {
				String[] attrArray = {"item", "salePerson"};
				checkEntityNullValue(itemSaleProperties, attrArray);
				itemSaleProperties = itemService.merge(itemSaleProperties);
				renderText(UPDATE_SUCCESS);

			}
			/** 库存 */
			if (null != itemInventoryProperties) {
				String[] attrArray = {"item", "defaultWarehouse", "invShelf"};
				checkEntityNullValue(itemInventoryProperties, attrArray);
				itemInventoryProperties = itemService.merge(itemInventoryProperties);
				renderText(UPDATE_SUCCESS);
			}
			/** 成本 */
			if (null != itemCostProperties) {
				String[] attrArray = {"item"};
				checkEntityNullValue(itemCostProperties, attrArray);
				itemCostProperties = itemService.merge(itemCostProperties);
				renderText(UPDATE_SUCCESS);
			}
			/** 财务 */
			if (null != itemFinanceProperties) {
				String[] attrArray = {"item"};
				checkEntityNullValue(itemFinanceProperties, attrArray);
				itemFinanceProperties = itemService.merge(itemFinanceProperties);
				renderText(UPDATE_SUCCESS);
			}
			/** MRP */
			if (null != itemMRPProperties) {
				String[] attrArray = {"item"};
				checkEntityNullValue(itemMRPProperties, attrArray);
				itemMRPProperties = itemService.merge(itemMRPProperties);
				renderText(UPDATE_SUCCESS);

			}
			/** 计划 */
			if (null != itemPlanProperties) {
				String[] attrArray = {"item"};
				checkEntityNullValue(itemPlanProperties, attrArray);
				itemPlanProperties = itemService.merge(itemPlanProperties);
				renderText(UPDATE_SUCCESS);
			}
			/** 质量 */
			if (null != itemQualityProperties) {
				String[] attrArray = {"item"};
				checkEntityNullValue(itemQualityProperties, attrArray);
				itemQualityProperties = itemService.merge(itemQualityProperties);
				renderText(UPDATE_SUCCESS);
			}
			if (null != item) {
				Item i = itemService.findEntityById(Item.class, item.getId());
				if (null != i && null != item) {
					item.setItemCatalogs(i.getItemCatalogs());
				}
				String[] attrArray = {"currencyType", "itemGroup", "itemBrand", "measureUnitGroup", "measureUnit", "assitMeasureUnit"};
				checkEntityNullValue(item, attrArray);
				String appendFieldValue = getRequestParameter("appendFieldValue");
				item.setIsTemp("0");
				item.setIsDeleted("0");
				dealItemCatalogs(item);
				item = itemService.saveOrUpdateProduct(item, appendFieldValue);
				item = itemService.merge(item);
				if (isSave) {
					renderText(SAVE_SUCCESS);
				} else {
					renderText(UPDATE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	private List<Specification> specList;

	public String chooseSpecification() {
		try {
			if (null != id && !id.equals("") && !id.equals("0")) {
				item = itemService.findEntityById(Item.class, id);
				if (null != item.getObjectExpand() && null != item.getObjectExpand().getSpecifications()) {
					Set<Specification> specSet = item.getObjectExpand().getSpecifications();
					if (specSet.size() > 0) {
						specList = new ArrayList<Specification>(specSet);
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "chooseSpecification";
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			Item i = itemService.findEntityById(Item.class, id);
			if (null != i) {
				i.setIsDeleted("1");
				itemService.merge(i);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public void getItemEntityJson() {
		try {
			String itemIdSpecId = getRequestParameter("itemIdSpecId");
			if (null != itemIdSpecId && !"".equals(itemIdSpecId)) {
				String[] isIds = itemIdSpecId.split(",");
				Item i = itemService.findEntityById(Item.class, isIds[0]);
				itemService.evict(i);
				StringBuilder spec = new StringBuilder();
				for (int j = 0; j < isIds.length; j++) {
					String s = isIds[j];
					if (j != 0 && null != s && !"".equals(s)) {
						SpecificationDetail sd = itemService.findEntityById(SpecificationDetail.class, s);
						if (null != sd) {
							spec.append(sd.getName());
							spec.append(" ");
						}
					}
				}
				i.setSpecification(spec.toString());
				String json = new JSONSerializer().exclude("class").serialize(i);
				renderJson(json);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String loadObjectExpandField() {
		if (null != id && !id.equals("") && !id.equals("0")) {
			try {
				String itemId = getRequestParameter("itemId");
				if (null != itemId && !"".equals(itemId)) {
					loadExpandFieldDetail(itemId, id);
				} else {
					loadExpandFieldDetail("0", id);
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

	private List<MeasureUnit> measureUnitList;
	private String measureUnitGroupId;

	public String loadMeasureUnit() {
		try {
			if (StringUtils.isNotEmpty(measureUnitGroupId) && !measureUnitGroupId.equals("0")) {
				measureUnitList = itemService.findAllByEntityClassAndAttribute(MeasureUnit.class, "measureUnitGroup.id", measureUnitGroupId);
			}
			if (null != id && !id.equals("") && !id.equals("0")) {
				item = itemService.findEntityById(Item.class, id);
				itemPurchaseProperties = itemService.findEntityByAttribute(ItemPurchaseProperties.class, "item.id", id);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return type;
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

	private void dealItemCatalogs(Item item) throws Exception {
		String icIds = getRequestParameter("itemCatalogIds");
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

	public String goSaveOrUpdateItemReplace() {
		if (null != id && !id.equals("") && !id.equals("0")) {
			try {
				itemReplace = itemService.findEntityById(ItemReplace.class, id);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return "goSaveOrUpdateItemReplace";
	}

	public String saveOrUpdateItemReplace() {
		boolean isSave = true;
		try {
			if (null != itemReplace && null != itemReplace.getId()) {
				isSave = false;
			}
			itemService.merge(itemReplace);
			if (isSave) {
				setMessage("保存成功!");
			} else {
				setMessage("修改成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage("操作失败!");
		}
		return UPDATE;
	}

	public void deleteItemReplace() {
		try {
			if (null != id && !"".equals(id)) {
				ItemReplace i = itemService.findEntityById(ItemReplace.class, id);
				if (null != i) {
					itemService.deleteByEntity(i);
					setMessage("删除成功!");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage("删除失败!");
		}
	}

	public void getItemReplaceJson() {
		try {
			String json = "";
			if (null != id && !"".equals(id)) {
				Item i = itemService.findEntityById(Item.class, id);
				if (null != i) {
					json = convertListToJson(new ArrayList<ItemReplace>(i.getItemReplaces()), i.getItemReplaces().size(), "item");
				}
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 获取json数据 */
	public void getAttachFileJson() {
		try {
			String json = "";
			if (null != id && !"".equals(id)) {
				Item i = itemService.findEntityById(Item.class, id);
				json = convertListToJson(new ArrayList<ItemAttachFile>(i.getItemAttachFiles()), i.getItemAttachFiles().size(), "item");
			}
			if (!"".equals(json)) {
				renderJson(json);
			} else {
				renderJson("{\"total\":0,\"rows\":[]}");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<Specification> specificationList;

	public String loadSpecification() {
		try {
			if (null != id && !id.equals("") && !id.equals("0")) {
				ObjectExpand oe = itemService.findEntityById(ObjectExpand.class, id);
				if (null != oe) {
					specificationList = new ArrayList<Specification>(oe.getSpecifications());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "loadSpecification";
	}

	public String goChooseItem() {
		return "goChooseItem";
	}

	/** 选择导入物料的文件 */
	public String chooseItemFile() {
		return "chooseItemFile";
	}

	/** 物料导入 */
	public String importItemFile() {
		try {
			if (null != fileToUpload) {
				List<Item> itemList = itemService.findAllByEntityClass(Item.class);
				Set<String> barCodeSet = new HashSet<String>();
				for (Item i : itemList) {
					if (null != i && null != i.getBarCode() && !"".equals(i.getBarCode())) {
						barCodeSet.add(i.getBarCode());
					}
				}
				InputStream is = new FileInputStream(fileToUpload);
				String fileExName = fileToUploadFileName.split("\\.")[1];
				if (null != fileExName && "xls".equals(fileExName.toLowerCase())) {
					importXlsItem(is, barCodeSet);
				}
				if (null != fileExName && "xlsx".equals(fileExName.toLowerCase())) {
					importXlsxItem(is, barCodeSet);
				}
			}
			renderJson("物料导入成功!");
		} catch (Exception ex) {
			ex.printStackTrace();
			renderJson("物料导入失败!");
		}
		return UPDATE;
	}

	private void importXlsxItem(InputStream is, Set<String> barCodeSet) throws Exception {
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			// 循环行Row
			for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow == null || rowNum == 0) {
					continue;
				}
				Item xlsItem = new Item();
				InvShelf invShelf = null;
				// 循环列Cell
				for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
					XSSFCell xssfCell = xssfRow.getCell(cellNum);
					if (xssfCell == null) {
						continue;
					}
					String value = getXlsxValue(xssfCell);
					value = value.trim();
					/** 物料类型 */
					/*
					 * if (cellNum == 0 && null != value && !"".equals(value)) {
					 * xlsItem.setItemClass(value); }
					 *//** 品类 */
					/*
					 * if (cellNum == 1 && null != value && !"".equals(value)) {
					 * ItemCatalog ic = itemService.findEntityByAttribute(
					 * ItemCatalog.class, "code", value); if (null != ic) {
					 * xlsItem.getItemCatalogs().add(ic); } }
					 *//** 库位 */
					/*
					 * if (cellNum == 2 && null != value && !"".equals(value) &&
					 * !barCodeSet.contains(value)) { invShelf =
					 * itemService.findEntityByAttribute( InvShelf.class,
					 * "code", value); }
					 *//** SKU */
					/*
					 * if (cellNum == 3 && null != value && !"".equals(value)) {
					 * xlsItem.setSkuCode(value); }
					 *//** BAR */
					/*
					 * if (cellNum == 4 && null != value && !"".equals(value) &&
					 * !barCodeSet.contains(value)) { xlsItem.setBarCode(value);
					 * barCodeSet.add(value); }
					 *//** 名称 */
					/*
					 * if (cellNum == 5 && null != value && !"".equals(value)) {
					 * xlsItem.setName(value); }
					 *//** 规格 */
					/*
					 * if (cellNum == 6 && null != value && !"".equals(value)) {
					 * xlsItem.setSpecification(value); }
					 * 
					 *//** 采购价 */
					/*
					 * if (cellNum == 8 && null != value && !"".equals(value)) {
					 * xlsItem.setCostPrice(Double.parseDouble(value)); }
					 *//** 零售价 */
					/*
					 * if (cellNum == 9 && null != value && !"".equals(value)) {
					 * xlsItem.setPrice(Double.parseDouble(value)); }
					 *//** 毛重 *//*
								 * if (cellNum == 10 && null != value &&
								 * !"".equals(value)) {
								 * xlsItem.setGrossWeight(Double.parseDouble(
								 * value)); }
								 */

					/** 编码 */
					if (cellNum == 0 && null != value && !"".trim().equals(value)) {
						xlsItem = itemService.findEntityByAttribute(Item.class, "code", value);
						if (null == xlsItem) {
							xlsItem = new Item();
							loadCommonData(xlsItem);
							xlsItem.setCode(value);
						}
					}
					/** 名称 */
					if (cellNum == 1 && null != value && !"".equals(value)) {
						xlsItem.setName(value);
					}
					/** SKU */
					if (cellNum == 2 && null != value && !"".equals(value)) {
						xlsItem.setSkuCode(value);
					}
					/** 条形码 */
					if (cellNum == 3 && null != value && !"".equals(value)) {
						xlsItem.setBarCode(value);
					}
					/** 采购价格 */
					if (cellNum == 4 && NumberUtil.isNumeric(value)) {
						xlsItem.setPurchasePrice(Double.parseDouble(value.trim()));
					}
					/** 销售价格 */
					if (cellNum == 5 && NumberUtil.isNumeric(value)) {
						xlsItem.setPrice(Double.parseDouble(value.trim()));
					}
					/** 市场价格 */
					if (cellNum == 6 && NumberUtil.isNumeric(value)) {
						// xlsItem.setMarketPrice(Double.parseDouble(value.trim()));
					}
					/** 规格 */
					if (cellNum == 7 && null != value && !"".equals(value)) {

					}
					/** 分类 */
					if (cellNum == 9 && null != value && !"".equals(value)) {
						ItemCatalog ic = itemService.findEntityByAttribute(ItemCatalog.class, "code", value);
						if (null != ic) {
							Set<ItemCatalog> icSet = new HashSet<ItemCatalog>();
							icSet.add(ic);
							xlsItem.setItemCatalogs(icSet);
						}
					}
					/** 单位 */
					if (cellNum == 10 && null != value && !"".equals(value)) {
						// fix me
					}
					/** 币种 */
					if (cellNum == 11 && null != value && !"".equals(value)) {
						CurrencyType ct = itemService.findEntityByAttribute(CurrencyType.class, "code", value);
						if (null != ct) {
							xlsItem.setCurrencyType(ct);
						}
					}
					/** 库位 */
					if (cellNum == 12 && null != value && !"".equals(value) && !barCodeSet.contains(value)) {
						invShelf = itemService.findEntityByAttribute(InvShelf.class, "code", value);
					}
				}
				if (null != xlsItem.getBarCode() && !"".equals(xlsItem.getBarCode())) {
					if (null != invShelf) {
						ItemInventoryProperties ip = new ItemInventoryProperties();
						ip.setDefaultInvShelf(invShelf);
						if (null != invShelf.getInvWarehouse()) {
							ip.setDefaultWarehouse(invShelf.getInvWarehouse());
						}
						ip.setItem(xlsItem);
						itemService.merge(ip);
						ip = null;
						invShelf = null;
					}
					xlsItem = null;
				}
				if (null != xlsItem.getCode() && !"".trim().equals(xlsItem.getCode())) {
					loadCommonData(xlsItem);
					xlsItem.setIsDeleted("0");
					xlsItem.setIsTemp("0");
					xlsItem.setStatus("1");
					xlsItem = itemService.merge(xlsItem);
				}
			}
		}
	}

	private String getXlsxValue(XSSFCell xssfCell) {
		if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfCell.getBooleanCellValue());
		} else if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			double d = xssfCell.getNumericCellValue();
			String s = NumberFormat.getInstance().format(d);
			return s.replaceAll(",", "");
		} else {
			xssfCell.setCellType(Cell.CELL_TYPE_STRING);
			return String.valueOf(xssfCell.getStringCellValue());
		}
	}

	private void importXlsItem(InputStream is, Set<String> barCodeSet) throws Exception {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 循环行Row
			for (int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null || rowNum == 0) {
					continue;
				}
				Item xlsItem = new Item();
				InvShelf invShelf = null;
				// 循环列Cell
				/*
				 * for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum();
				 * cellNum++) { HSSFCell hssfCell = hssfRow.getCell(cellNum); if
				 * (hssfCell == null) { continue; } String value =
				 * getXlsValue(hssfCell);
				 *//** 物料类型 */
				/*
				 * if (cellNum == 0 && null != value && !"".equals(value)) {
				 * xlsItem.setItemClass(value); }
				 *//** 品类 */
				/*
				 * if (cellNum == 1 && null != value && !"".equals(value)) {
				 * ItemCatalog ic = itemService.findEntityByAttribute(
				 * ItemCatalog.class, "code", value); if (null != ic) {
				 * xlsItem.getItemCatalogs().add(ic); } }
				 *//** 库位 */
				/*
				 * if (cellNum == 2 && null != value && !"".equals(value) &&
				 * !barCodeSet.contains(value)) { invShelf =
				 * itemService.findEntityByAttribute( InvShelf.class, "code",
				 * value); }
				 *//** SKU */
				/*
				 * if (cellNum == 3 && null != value && !"".equals(value)) {
				 * xlsItem.setSkuCode(value); }
				 *//** BAR */
				/*
				 * if (cellNum == 4 && null != value && !"".equals(value) &&
				 * !barCodeSet.contains(value)) { xlsItem.setBarCode(value);
				 * barCodeSet.add(value); }
				 *//** 名称 */
				/*
				 * if (cellNum == 5 && null != value && !"".equals(value)) {
				 * xlsItem.setName(value); }
				 *//** 规格 */
				/*
				 * if (cellNum == 6 && null != value && !"".equals(value)) {
				 * xlsItem.setSpecification(value); }
				 * 
				 *//** 采购价 */
				/*
				 * if (cellNum == 8 && null != value && !"".equals(value)) {
				 * xlsItem.setCostPrice(Double.parseDouble(value)); }
				 *//** 零售价 */
				/*
				 * if (cellNum == 9 && null != value && !"".equals(value)) {
				 * xlsItem.setPrice(Double.parseDouble(value)); }
				 *//** 毛重 *//*
							 * if (cellNum == 10 && null != value &&
							 * !"".equals(value)) {
							 * xlsItem.setGrossWeight(Double.parseDouble(value))
							 * ; } }
							 */
				for (int cellNum = 0; cellNum <= hssfRow.getLastCellNum(); cellNum++) {
					HSSFCell hssfCell = hssfRow.getCell(cellNum);
					if (hssfCell == null) {
						continue;
					}
					String value = getXlsValue(hssfCell);
					value = value.trim();
					/** 编码 */
					if (cellNum == 0 && null != value && !"".equals(value)) {
						xlsItem = itemService.findEntityByAttribute(Item.class, "code", value);
						if (null == xlsItem) {
							xlsItem = new Item();
							loadCommonData(xlsItem);
							xlsItem.setCode(value);
						}
					}
					/** 名称 */
					if (cellNum == 1 && null != value && !"".equals(value)) {
						xlsItem.setName(value);
					}
					/** SKU */
					if (cellNum == 2 && null != value && !"".equals(value)) {
						xlsItem.setSkuCode(value);
					}
					/** 条形码 */
					if (cellNum == 3 && null != value && !"".equals(value)) {
						xlsItem.setBarCode(value);
					}
					/** 采购价格 */
					if (cellNum == 4 && NumberUtil.isNumeric(value)) {
						xlsItem.setPurchasePrice(Double.parseDouble(value.trim()));
					}
					/** 销售价格 */
					if (cellNum == 5 && NumberUtil.isNumeric(value)) {
						xlsItem.setPrice(Double.parseDouble(value.trim()));
					}
					/** 市场价格 */
					if (cellNum == 6 && NumberUtil.isNumeric(value)) {
						// xlsItem.setMarketPrice(Double.parseDouble(value.trim()));
					}
					/** 规格 */
					if (cellNum == 7 && null != value && !"".equals(value)) {

					}
					/** 分类 */
					if (cellNum == 9 && null != value && !"".equals(value)) {
						ItemCatalog ic = itemService.findEntityByAttribute(ItemCatalog.class, "code", value);
						if (null != ic) {
							Set<ItemCatalog> icSet = new HashSet<ItemCatalog>();
							icSet.add(ic);
							xlsItem.setItemCatalogs(icSet);
						}
					}
					/** 单位 */
					if (cellNum == 10 && null != value && !"".equals(value)) {
						// fix me
					}
					/** 币种 */
					if (cellNum == 11 && null != value && !"".equals(value)) {
						CurrencyType ct = itemService.findEntityByAttribute(CurrencyType.class, "code", value);
						if (null != ct) {
							xlsItem.setCurrencyType(ct);
						}
					}
					/** 库位 */
					if (cellNum == 12 && null != value && !"".equals(value) && !barCodeSet.contains(value)) {
						invShelf = itemService.findEntityByAttribute(InvShelf.class, "code", value);
					}
				}
				if (null != xlsItem.getBarCode() && !"".equals(xlsItem.getBarCode())) {
					if (null != invShelf) {
						ItemInventoryProperties ip = new ItemInventoryProperties();
						ip.setDefaultInvShelf(invShelf);
						if (null != invShelf.getInvWarehouse()) {
							ip.setDefaultWarehouse(invShelf.getInvWarehouse());
						}
						ip.setItem(xlsItem);
						itemService.merge(ip);
						ip = null;
						invShelf = null;
					}
					xlsItem = null;
				}
				if (null != xlsItem.getCode() && !"".trim().equals(xlsItem.getCode())) {
					loadCommonData(xlsItem);
					xlsItem.setIsDeleted("0");
					xlsItem.setIsTemp("0");
					xlsItem.setStatus("1");
					xlsItem = itemService.merge(xlsItem);
				}
			}
		}
	}

	private String getXlsValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			double d = hssfCell.getNumericCellValue();
			String s = NumberFormat.getInstance().format(d);
			return s.replaceAll(",", "");
		} else if (hssfCell.getCellType() == Cell.CELL_TYPE_STRING) {
			return String.valueOf(hssfCell.getStringCellValue());
		} else {
			hssfCell.setCellType(Cell.CELL_TYPE_STRING);
			return String.valueOf(hssfCell.getStringCellValue());
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Specification> getSpecificationList() {
		return specificationList;
	}

	public void setSpecificationList(List<Specification> specificationList) {
		this.specificationList = specificationList;
	}

	public String getIds() {
		return ids;
	}

	public List<Specification> getSpecList() {
		return specList;
	}

	public void setSpecList(List<Specification> specList) {
		this.specList = specList;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<ObjectExpandField> getObjectExpandFieldList() {
		return objectExpandFieldList;
	}

	public void setObjectExpandFieldList(List<ObjectExpandField> objectExpandFieldList) {
		this.objectExpandFieldList = objectExpandFieldList;
	}

	public Map<String, Object> getObjectExpandFieldMap() {
		return objectExpandFieldMap;
	}

	public void setObjectExpandFieldMap(Map<String, Object> objectExpandFieldMap) {
		this.objectExpandFieldMap = objectExpandFieldMap;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public ItemCostProperties getItemCostProperties() {
		return itemCostProperties;
	}

	public void setItemCostProperties(ItemCostProperties itemCostProperties) {
		this.itemCostProperties = itemCostProperties;
	}

	public ItemPurchaseProperties getItemPurchaseProperties() {
		return itemPurchaseProperties;
	}

	public void setItemPurchaseProperties(ItemPurchaseProperties itemPurchaseProperties) {
		this.itemPurchaseProperties = itemPurchaseProperties;
	}

	public ItemSaleProperties getItemSaleProperties() {
		return itemSaleProperties;
	}

	public void setItemSaleProperties(ItemSaleProperties itemSaleProperties) {
		this.itemSaleProperties = itemSaleProperties;
	}

	public ItemInventoryProperties getItemInventoryProperties() {
		return itemInventoryProperties;
	}

	public void setItemInventoryProperties(ItemInventoryProperties itemInventoryProperties) {
		this.itemInventoryProperties = itemInventoryProperties;
	}

	public ItemMRPProperties getItemMRPProperties() {
		return itemMRPProperties;
	}

	public void setItemMRPProperties(ItemMRPProperties itemMRPProperties) {
		this.itemMRPProperties = itemMRPProperties;
	}

	public ItemPlanProperties getItemPlanProperties() {
		return itemPlanProperties;
	}

	public void setItemPlanProperties(ItemPlanProperties itemPlanProperties) {
		this.itemPlanProperties = itemPlanProperties;
	}

	public ItemQualityProperties getItemQualityProperties() {
		return itemQualityProperties;
	}

	public void setItemQualityProperties(ItemQualityProperties itemQualityProperties) {
		this.itemQualityProperties = itemQualityProperties;
	}

	public ItemGernalProperties getItemGernalProperties() {
		return itemGernalProperties;
	}

	public void setItemGernalProperties(ItemGernalProperties itemGernalProperties) {
		this.itemGernalProperties = itemGernalProperties;
	}

	public ItemFinanceProperties getItemFinanceProperties() {
		return itemFinanceProperties;
	}

	public void setItemFinanceProperties(ItemFinanceProperties itemFinanceProperties) {
		this.itemFinanceProperties = itemFinanceProperties;
	}

	public List<ObjectExpand> getObjectExpandList() {
		return objectExpandList;
	}

	public void setObjectExpandList(List<ObjectExpand> objectExpandList) {
		this.objectExpandList = objectExpandList;
	}

	public ItemReplace getItemReplace() {
		return itemReplace;
	}

	public void setItemReplace(ItemReplace itemReplace) {
		this.itemReplace = itemReplace;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
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

	public String getMeasureUnitGroupId() {
		return measureUnitGroupId;
	}

	public void setMeasureUnitGroupId(String measureUnitGroupId) {
		this.measureUnitGroupId = measureUnitGroupId;
	}

	public List<ItemGroup> getItemGroupList() {
		return itemGroupList;
	}

	public void setItemGroupList(List<ItemGroup> itemGroupList) {
		this.itemGroupList = itemGroupList;
	}

	public List<ItemBrand> getItemBrandList() {
		return itemBrandList;
	}

	public void setItemBrandList(List<ItemBrand> itemBrandList) {
		this.itemBrandList = itemBrandList;
	}

	public List<OrderType> getOrderTypeList() {
		return orderTypeList;
	}

	public void setOrderTypeList(List<OrderType> orderTypeList) {
		this.orderTypeList = orderTypeList;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public File getFileToUpload() {
		return fileToUpload;
	}

	@Override
	public void setFileToUpload(File fileToUpload) {
		this.fileToUpload = fileToUpload;
	}

	@Override
	public String getFileToUploadFileName() {
		return fileToUploadFileName;
	}

	@Override
	public void setFileToUploadFileName(String fileToUploadFileName) {
		this.fileToUploadFileName = fileToUploadFileName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}