package com.vix.mdm.item.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.security.util.SecurityUtil;
import com.vix.common.share.entity.CurrencyType;
import com.vix.common.share.entity.MeasureUnitGroup;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpand;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpandField;
import com.vix.core.persistence.jdbc.dbstruct.Specification;
import com.vix.core.persistence.jdbc.dbstruct.SpecificationDetail;
import com.vix.core.utils.NumberUtil;
import com.vix.core.utils.PropertyConfigLoader;
import com.vix.core.utils.SortSet;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.entity.ItemBrand;
import com.vix.mdm.item.entity.ItemCatalog;
import com.vix.mdm.item.entity.ItemInventoryProperties;
import com.vix.mdm.item.entity.ItemPurchaseProperties;
import com.vix.mdm.item.service.IItemService;
import com.vix.mdm.purchase.order.entity.OrderType;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.system.code.entity.EncodingRulesTableInTheMiddle;
import com.vix.system.enumeration.entity.EnumValue;
import com.vix.system.enumeration.entity.Enumeration;
import com.vix.system.expand.constant.ExpandConstant;

@Controller
@Scope("prototype")
public class FastAddItemAction extends VixntBaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IItemService itemService;

	private String id;
	private String name;
	private String pageNo;
	private String categoryId;
	private Item item;
	private List<Item> itemList;
	private ItemPurchaseProperties itemPurchaseProperties;
	private ItemInventoryProperties itemInventoryProperties;

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
			params.put("type," + SearchCondition.EQUAL, "circulationIndustry");
			itemService.findPagerByHqlConditions(getPager(), Item.class, params);
			itemList = getPager().getResultList();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "goList";
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
			params.put("type," + SearchCondition.EQUAL, "circulationIndustry");
			if (null != name && !"".equals(name)) {
				name = decode(name, "UTF-8");
				params.put("name," + SearchCondition.ANYLIKE, name);
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

	/** 跳转至用户修改页面 */
	/** 跳转至用户修改页面 */
	private List<ObjectExpand> objectExpandList;// 扩展表列表
	private List<ObjectExpandField> objectExpandFieldList;// 分类下商品扩展属性列表
	private List<CurrencyType> currencyTypeList;
	private List<MeasureUnitGroup> measureUnitGroupList;
	private List<OrderType> orderTypeList;
	private List<ItemBrand> itemBrandList;
	private String source;// item,fastitem

	public String goSaveOrUpdateCirculationIndustryItem() {
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
			itemBrandList = itemService.findAllByEntityClass(ItemBrand.class);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("name," + SearchCondition.NOEQUAL, "");
			measureUnitGroupList = itemService.findAllByConditions(MeasureUnitGroup.class, params);
			currencyTypeList = itemService.findAllByEntityClass(CurrencyType.class);
			orderTypeList = itemService.findAllByEntityClass(OrderType.class);
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
				itemPurchaseProperties = itemService.findEntityByAttribute(ItemPurchaseProperties.class, "item.id", id);
				itemInventoryProperties = itemService.findEntityByAttribute(ItemInventoryProperties.class, "item.id", id);
			} else {
				if (null == item) {
					item = new Item();
					item.setCode(autoCreateCode.getBillNO("ItemCode"));
				}
				if (null != categoryId && !categoryId.equals("") && !categoryId.equals("0")) {
					ItemCatalog ic = itemService.findEntityById(ItemCatalog.class, categoryId);
					item.getItemCatalogs().add(ic);
				}
				item = itemService.merge(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateCirculationIndustryItem";
	}

	/** 处理修改操作 */
	public String saveOrUpdateCirculationIndustryItem() {
		boolean isSave = true;
		try {
			if (null != item.getId() && !"".equals(item.getId())) {
				isSave = false;
			} else {
				item.setCreateTime(new Date());
				loadCommonData(item);
			}
			item.setIsTemp("0");
			item.setIsDeleted("0");
			item.setItemClass("goods");
			item.setType("circulationIndustry");

			String[] attrArray = {"objectExpand", "currencyType", "measureUnitGroup", "measureUnit", "assitMeasureUnit", "itemBrand"};
			checkEntityNullValue(item, attrArray);

			String appendFieldValue = getRequestParameter("appendFieldValue");
			dealItemCatalogs(item);

			item = itemService.saveOrUpdateProduct(item, appendFieldValue);
			/** 采购 */
			if (null != itemPurchaseProperties) {

				String[] attrArrays = {"supplier", "orderType", "recieveWarehouse", "purchasePerson", "measureUnitGroup", "poUnit", "purAssitUnit", "purBaseUnit", "invShelf"};
				checkEntityNullValue(itemPurchaseProperties, attrArrays);

				itemPurchaseProperties.setItem(item);
				itemPurchaseProperties = itemService.merge(itemPurchaseProperties);
			}
			if (null != itemInventoryProperties) {

				String[] attrArrays = {"defaultWarehouse", "invShelf"};
				checkEntityNullValue(itemInventoryProperties, attrArrays);

				itemInventoryProperties.setItem(item);
				itemInventoryProperties = itemService.merge(itemInventoryProperties);
			}
			String specificationDetail = getRequestParameter("specificationDetail");
			if (null != specificationDetail && !"".equals(specificationDetail)) {
				dealSpecificationDetail(specificationDetail);
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
		return UPDATE;
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
	public String deleteItemSpecificationById() {
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
						setMessage("规格信息删除成功!");
					} else {
						setMessage("规格表未获取到!");
					}
				} else {
					setMessage("物料id未获取到!");
				}
			} else {
				setMessage("物料规格id未获取到!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return UPDATE;
	}

	/** 根据商品获取商品规格扩展表名称 */
	private String getSpecificationTableNameByItem(Item i) throws Exception {
		String specTableName = "";
		if (null == i) {
			return specTableName;
		}
		if (null == i.getObjectExpand()) {
			return specTableName;
		}
		Set<Specification> ls = i.getObjectExpand().getSpecifications();
		if (null != ls && ls.size() > 0) {
			for (Specification s : ls) {
				specTableName = s.getSpecificationTableName();
				break;
			}
		}
		return specTableName;
	}

	/** 检查sku是否重复 */
	public String checkSkuIsExist() {
		try {
			String itemId = getRequestParameter("itemId");
			String sku = getRequestParameter("sku");
			if (StrUtils.objectIsNotNull(itemId) && StrUtils.objectIsNotNull(sku)) {
				Item i = itemService.findEntityById(Item.class, itemId);
				String tableName = getSpecificationTableNameByItem(i);
				if (null != tableName && !"".equals(tableName)) {
					StringBuilder sqlBuilder = new StringBuilder("select * from ");
					sqlBuilder.append(tableName);
					sqlBuilder.append(" where sku = '");
					sqlBuilder.append(sku);
					sqlBuilder.append("'");
					List<Object> list = itemService.findListBySql(sqlBuilder.toString());
					if (null != list && list.size() > 0) {
						this.setMessage("exist");
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return UPDATE;
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
		List<Specification> sList = new ArrayList<Specification>(item.getObjectExpand().getSpecifications());
		String tableName = getSpecificationTableNameByItem(item);
		String itemId = item.getId();
		String objectExpandId = item.getObjectExpand().getId();
		String tenantId = SecurityUtil.getCurrentUserTenantId();
		SortSet scm = new SortSet("orderBy");
		Collections.sort(sList, scm);
		String[] sds = specificationDetail.split(":");
		for (String sd : sds) {
			if (null != sd && !"".equals(sd)) {
				String[] specDetail = sd.split("!");
				String[] specSingle = specDetail[2].split(",");
				StringBuilder sqlBuilder = new StringBuilder("insert into ");
				sqlBuilder.append(tableName);
				sqlBuilder.append(" (SKU,OBJECT_ID,OBJECT_TYPE,SPEC_CODE,SYS_OBJECTEXPAND_ID,");
				for (Specification spec : sList) {
					sqlBuilder.append(spec.getCode());
					sqlBuilder.append(",");
				}
				sqlBuilder.append("TENANTID) values(");
				sqlBuilder.append("'");
				sqlBuilder.append(specDetail[0]);
				sqlBuilder.append("',");
				sqlBuilder.append(itemId);
				sqlBuilder.append(",'ItemType','");
				sqlBuilder.append(specDetail[1]);
				sqlBuilder.append("',");
				sqlBuilder.append(objectExpandId);
				sqlBuilder.append(",");
				for (int i = 0; i < specSingle.length; i++) {
					if (!"PlaceHolder".equals(specSingle[i])) {
						sqlBuilder.append("'");
						sqlBuilder.append(specSingle[i]);
						sqlBuilder.append("'");
					} else {
						sqlBuilder.append("''");
					}
					sqlBuilder.append(",");
				}
				sqlBuilder.append("'");
				sqlBuilder.append(tenantId);
				sqlBuilder.append("')");
				itemService.executeSql(sqlBuilder.toString(), null);
			}
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
}
