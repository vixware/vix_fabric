package com.vix.inventory.beginDefectiveItem.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpand;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpandField;
import com.vix.core.persistence.jdbc.dbstruct.Specification;
import com.vix.core.persistence.jdbc.dbstruct.SpecificationDetail;
import com.vix.core.utils.PropertyConfigLoader;
import com.vix.core.utils.china.ChnToPinYin;
import com.vix.core.web.Pager;
import com.vix.inventory.beginDefectiveItem.controller.BeginDefectiveItemController;
import com.vix.inventory.beginDefectiveItem.dao.IBeginDefectiveItemDao;
import com.vix.inventory.initInventory.util.SpecUtil;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.service.IItemService;
import com.vix.system.enumeration.entity.EnumValue;
import com.vix.system.enumeration.entity.Enumeration;

import flexjson.JSONSerializer;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@Scope("prototype")
public class BeginDefectiveItemAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(BeginDefectiveItemAction.class);
	@Autowired
	private BeginDefectiveItemController beginDefectiveItemController;
	@Autowired
	private IItemService itemService;
	private List<ObjectExpandField> objectExpandFieldList;// 分类下商品扩展属性列表
	private String id;
	private String objectExpandId;
	private String ids;
	private String specificationDetailids;
	private String pageNo;
	/**
	 * 存货档案清单(现存量汇总表)
	 */
	private InventoryCurrentStock inventoryCurrentStock;
	@Autowired
	private IBeginDefectiveItemDao beginDefectiveItemDao;
	/**
	 * 对象类型
	 */
	private List<ObjectExpand> objectExpandList;

	private List<Specification> specificationList = new ArrayList<Specification>();

	private List<SpecificationDetail> specificationDetailList;

	private List<SpecUtil> specUtilList;
	/* 规格组合 */
	private SpecUtil titlespecUtil;
	/**
	 * 页面返回的SKU数据
	 */
	private String skudata;

	@Override
	public String goList() {
		try {
			objectExpandList = beginDefectiveItemController.doListObjectExpandList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != inventoryCurrentStock.getId()) {
				isSave = false;
			}
			if (skudata != null && !"".equals(skudata)) {
				JSONArray array = JSONArray.fromObject(skudata);
				if (array != null && array.size() > 0) {
					for (int i = 0; i < array.size(); i++) {
						JSONObject json = array.getJSONObject(i);
						if (json != null && json.size() > 0) {
							SpecUtil specUtil = new SpecUtil();
							String filds1 = null;
							if (json.has("filds1")) {
								filds1 = json.getString("filds1");
								specUtil.setFilds1(filds1);
							}
							String filds2 = null;
							if (json.has("filds2")) {
								filds2 = json.getString("filds2");
								specUtil.setFilds2(filds2);
							}
							String skuPrice = null;
							if (json.has("skuprice")) {
								skuPrice = json.getString("skuprice");
							}
							String skuAmount = null;
							if (json.has("skuamount")) {
								skuAmount = json.getString("skuamount");
							}
							String skuCode = null;
							if (json.has("skucode")) {
								skuCode = json.getString("skucode");
							}
							InventoryCurrentStock inventory = itemService.findEntityByAttribute(InventoryCurrentStock.class, "skuCode", skuCode);
							if (inventory == null)
								inventory = new InventoryCurrentStock();
							if (inventoryCurrentStock.getInvWarehouse().getId() != null) {
								InvWarehouse invWarehouse = itemService.findEntityById(InvWarehouse.class, inventoryCurrentStock.getInvWarehouse().getId());
								if ("5".equals(invWarehouse.getProperties())) {
									if (inventory.getQuantity() != null) {
										inventory.setQuantity(inventory.getQuantity() + inventoryCurrentStock.getQuantity());
									} else {
										inventory.setQuantity(inventoryCurrentStock.getQuantity());
									}
									if (inventory.getNonqualifiedquantity() != null) {
										inventory.setNonqualifiedquantity(inventory.getNonqualifiedquantity() + inventoryCurrentStock.getQuantity());
									} else {
										inventory.setNonqualifiedquantity(inventoryCurrentStock.getQuantity());
									}
								}
							}
							inventory.setItemcode(inventoryCurrentStock.getItemcode());
							inventory.setItemname(inventoryCurrentStock.getItemname());
							if (inventoryCurrentStock.getItemname() != null) {
								String py = ChnToPinYin.getPYString(inventoryCurrentStock.getItemname());
								inventory.setChineseCharacter(py.toUpperCase());
							}
							inventory.setMasterUnit(inventoryCurrentStock.getMasterUnit());
							inventory.setItemtype(inventoryCurrentStock.getItemtype());
							inventory.setBatchcode(inventoryCurrentStock.getBatchcode());
							inventory.setSerialcode(inventoryCurrentStock.getSerialcode());
							inventory.setProducedate(inventoryCurrentStock.getProducedate());
							inventory.setQualityperiod(inventoryCurrentStock.getQualityperiod());
							inventory.setWarehouse(inventoryCurrentStock.getWarehouse());
							StringBuffer spec = new StringBuffer();
							if (filds1 != null && !"".equals(filds1)) {
								spec.append(filds1);
							}
							if (filds2 != null && !"".equals(filds2)) {
								spec.append(" " + filds2);
							}

							inventory.setSpecification(spec.toString());
							if (skuPrice != null) {
								inventory.setPrice(Double.valueOf(skuPrice));
							}
							if (skuAmount != null) {
								inventory.setQuantity(Double.valueOf(skuAmount));
								inventory.setAvaquantity(Double.valueOf(skuAmount));
							}
							inventory.setSkuCode(skuCode);
							inventory.setIsBinding("2");
							inventory.setIsTemp("");
							inventory.setIsQualfied(2);
							initEntityBaseController.initEntityBaseAttribute(inventory);
							// 处理修改留痕
							billMarkProcessController.processMark(inventory, updateField);
							beginDefectiveItemController.doSaveInventoryCurrentStock(inventory);
							Item item = itemService.findEntityByAttribute(Item.class, "code", inventoryCurrentStock.getItemcode());
							if (item != null) {
								initSpec(item, specUtil);
							}
						}
					}
				}
			} else {
				InventoryCurrentStock inventory = itemService.findEntityByAttribute(InventoryCurrentStock.class, "itemcode", inventoryCurrentStock.getItemcode());
				if (inventory != null) {

				} else {
					inventoryCurrentStock.setIsBinding("2");
					inventoryCurrentStock.setIsTemp("");
					inventoryCurrentStock.setIsQualfied(2);
					if (inventoryCurrentStock.getInvShelf().getId() != null) {
					} else {
						inventoryCurrentStock.setInvShelf(null);
					}
					initEntityBaseController.initEntityBaseAttribute(inventoryCurrentStock);
					// 处理修改留痕
					billMarkProcessController.processMark(inventoryCurrentStock, updateField);
					beginDefectiveItemController.doSaveInventoryCurrentStock(inventoryCurrentStock);
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
		return UPDATE;
	}

	/* 加载组合后的规格型号 */
	public String goUpdateSpecification() {
		Map<String, List<SpecificationDetail>> specificationDetailMap = new HashMap<String, List<SpecificationDetail>>();
		if (null != objectExpandId) {
			Map<String, Object> params = new HashMap<String, Object>();
			/* 获取商品规格 */
			specificationList = beginDefectiveItemController.doListSpecification("objectExpand.id", objectExpandId, params);
			if (specificationList != null && specificationList.size() > 0) {
				for (int i = 0; i < specificationList.size(); i++) {
					Specification specification = specificationList.get(i);
					List<SpecificationDetail> specificationDetailList = new ArrayList<SpecificationDetail>();
					if (null != specificationDetailids && !"".equals(specificationDetailids)) {
						String[] specificationDetailidArray = specificationDetailids.split(",");
						if (specificationDetailidArray != null && specificationDetailidArray.length > 0) {
							for (String specificationDetailid : specificationDetailidArray) {
								SpecificationDetail specificationDetail = beginDefectiveItemController.doListSpecificationDetailById(specificationDetailid);
								if (specificationDetail.getSpecification().getId() == specification.getId()) {
									specificationDetailList.add(specificationDetail);
								}
							}
						}
					}
					if (specificationDetailList != null && specificationDetailList.size() > 0) {
						specificationDetailMap.put(String.valueOf(i), specificationDetailList);
					}
				}
			}
		}
		specUtilList = new ArrayList<SpecUtil>();
		titlespecUtil = new SpecUtil();
		if (specificationDetailMap.size() == 2) {
			List<SpecificationDetail> specificationDetailList1 = specificationDetailMap.get("0");
			List<SpecificationDetail> specificationDetailList2 = specificationDetailMap.get("1");
			if (specificationDetailList1 != null && specificationDetailList1.size() > 0 && specificationDetailList2 != null && specificationDetailList2.size() > 0) {
				for (SpecificationDetail specificationDetail1 : specificationDetailList1) {
					int i = 0;
					for (SpecificationDetail specificationDetail2 : specificationDetailList2) {
						SpecUtil specUtil = new SpecUtil();
						if (i == 0) {
							titlespecUtil.setFilds1(specificationDetail1.getSpecification().getName());
							titlespecUtil.setFilds2(specificationDetail2.getSpecification().getName());
						}
						specUtil.setFilds1(specificationDetail1.getName());
						specUtil.setFilds2(specificationDetail2.getName());
						specUtilList.add(specUtil);
						i++;
					}
				}
			}

		} else if (specificationDetailMap.size() == 1) {
			List<SpecificationDetail> specificationDetailList1 = specificationDetailMap.get("0");
			int j = 0;
			if (specificationDetailList1 != null && specificationDetailList1.size() > 0) {
				for (SpecificationDetail specificationDetail : specificationDetailList1) {
					SpecUtil specUtil = new SpecUtil();
					specUtil.setFilds1(specificationDetail.getName());
					if (j == 0) {
						titlespecUtil.setFilds1(specificationDetail.getSpecification().getName());
					}
					specUtilList.add(specUtil);
					j++;
				}
			}
		}

		return "goUpdateSpecification";
	}

	private void initSpec(Item item, SpecUtil specUtil) {
		String fildsql = null;
		ObjectExpand objectExpand = item.getObjectExpand();
		Set<Specification> specifications = objectExpand.getSpecifications();
		StringBuffer sql = new StringBuffer();
		String specificationTableName = null;
		for (Specification specification : specifications) {
			specificationTableName = specification.getSpecificationTableName();
			specification.getCode();
			if (specification.getCode() != null && !"".equals(specification.getCode())) {
				sql.append(specification.getCode());
			}
			sql.append(",");
		}
		fildsql = sql.substring(0, sql.length() - 1);

		Map<String, Object> params = new HashMap<String, Object>();
		/** 语句sql */
		StringBuilder insertSql = new StringBuilder("insert into ");
		insertSql.append(specificationTableName);
		insertSql.append(" (");
		insertSql.append(fildsql);
		/** 数据sql */
		StringBuilder iSql = new StringBuilder();

		if (specUtil.getFilds1() != null && !"".equals(specUtil.getFilds1())) {
			iSql.append("'" + specUtil.getFilds1() + "'");
		}
		if (specUtil.getFilds2() != null && !"".equals(specUtil.getFilds2())) {
			iSql.append(",");
			iSql.append("'" + specUtil.getFilds2() + "'");
		}

		insertSql.append(") values(");
		insertSql.append(iSql);
		insertSql.append(") ");
		try {
			beginDefectiveItemDao.executeSql(insertSql.toString(), params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String loadSpecification() {
		if (StringUtils.isNotEmpty(id)) {
			try {
				loadSpecification(id);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return "specificationDetail";
	}

	/**
	 * 获取商品规格明细
	 * 
	 * @param objectExpandId
	 * @throws Exception
	 */
	private void loadSpecification(String objectExpandId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		/* 获取商品规格 */
		specificationList = beginDefectiveItemController.doListSpecification("objectExpand.id", objectExpandId, params);
	}

	/** 载入商品扩展字段中下拉列表list */
	public List<SpecificationDetail> loadSpecificationDetail(String id) {
		List<SpecificationDetail> specificationDetailList = null;
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				Map<String, Object> params = new HashMap<String, Object>();
				specificationDetailList = beginDefectiveItemController.doListSpecificationDetail("specification.id", id, params);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return specificationDetailList;
	}

	public String loadObjectExpandField() {
		if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
			try {
				loadExpandFieldDetail(null, id);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return "loadObjectExpandField";
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
					sqlBuilder.append(itemId);
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
			if (StringUtils.isNotEmpty(refTag)) {
				Enumeration e = itemService.findEntityByAttribute(Enumeration.class, "name", refTag);
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

	/** 树形结构JSON */
	public void findTreeToJson() {
		try {

			Map<String, Object> params = new HashMap<String, Object>();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			List<ObjectExpand> listObjectExpand = itemService.findAllByConditions(ObjectExpand.class, params);
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			for (int i = 0; i < listObjectExpand.size(); i++) {
				ObjectExpand oe = listObjectExpand.get(i);
				strSb.append("{id:\"");
				strSb.append(oe.getId());
				strSb.append("\",name:\"");
				strSb.append(oe.getName());
				strSb.append("\",open:true,isParent:false}");
				if (i < listObjectExpand.size() - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 弹出显示库存信息列表
	 * 
	 * @return
	 */
	public String goItem() {
		return "goItem";
	}

	/**
	 * 加载显示库存信息列表数据内容
	 * 
	 * @return
	 */
	public String goItemList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = beginDefectiveItemController.doListItem(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goItemList";
	}

	/**
	 * 获取选择后的库存信息
	 */
	public void getItemListJson() {
		try {
			String itemId = getRequestParameter("itemId");
			if (StringUtils.isNotEmpty(itemId) && !"0".equals(itemId)) {
				Item item = beginDefectiveItemController.doListItemById(itemId);
				String json = new JSONSerializer().exclude("class").serialize(item);
				renderJson(json);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public String goSearch() {
		return "goSearch";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public InventoryCurrentStock getInventoryCurrentStock() {
		return inventoryCurrentStock;
	}

	public void setInventoryCurrentStock(InventoryCurrentStock inventoryCurrentStock) {
		this.inventoryCurrentStock = inventoryCurrentStock;
	}

	public List<ObjectExpand> getObjectExpandList() {
		return objectExpandList;
	}

	public void setObjectExpandList(List<ObjectExpand> objectExpandList) {
		this.objectExpandList = objectExpandList;
	}

	public List<Specification> getSpecificationList() {
		return specificationList;
	}

	public void setSpecificationList(List<Specification> specificationList) {
		this.specificationList = specificationList;
	}

	public List<SpecificationDetail> getSpecificationDetailList() {
		return specificationDetailList;
	}

	public void setSpecificationDetailList(List<SpecificationDetail> specificationDetailList) {
		this.specificationDetailList = specificationDetailList;
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

	public String getSpecificationDetailids() {
		return specificationDetailids;
	}

	public void setSpecificationDetailids(String specificationDetailids) {
		this.specificationDetailids = specificationDetailids;
	}

	public String getObjectExpandId() {
		return objectExpandId;
	}

	public void setObjectExpandId(String objectExpandId) {
		this.objectExpandId = objectExpandId;
	}

	public List<SpecUtil> getSpecUtilList() {
		return specUtilList;
	}

	public void setSpecUtilList(List<SpecUtil> specUtilList) {
		this.specUtilList = specUtilList;
	}

	public SpecUtil getTitlespecUtil() {
		return titlespecUtil;
	}

	public void setTitlespecUtil(SpecUtil titlespecUtil) {
		this.titlespecUtil = titlespecUtil;
	}

	public String getSkudata() {
		return skudata;
	}

	public void setSkudata(String skudata) {
		this.skudata = skudata;
	}

}
