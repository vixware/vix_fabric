package com.vix.mdm.item.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpand;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpandField;
import com.vix.core.persistence.jdbc.dbstruct.Specification;
import com.vix.core.utils.PropertyConfigLoader;
import com.vix.core.web.Pager;
import com.vix.mdm.item.dao.IItemDao;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.item.service.IItemService;
import com.vix.nvix.wx.util.StrUtils;
import com.vix.system.expand.dao.IObjectExpandDao;
import com.vix.system.expand.service.ISpecificationService;

@Service("itemService")
public class ItemServiceImpl extends BaseHibernateServiceImpl implements IItemService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IItemDao itemDao;
	@Autowired
	private IObjectExpandDao objectExpandDao;
	@Autowired
	private ISpecificationService specificationService;
	@Override
	public List<List<Object>> findItemSpecification(String specTableName, String itemId) throws Exception {
		return itemDao.findItemSpecification(specTableName, itemId);
	}

	@Override
	public List<Object> findListBySql(String sql) throws Exception {
		return itemDao.findListBySql(sql, null);
	}

	@Override
	public List<Item> findAllItemByItemCatalogId(String id) throws Exception {
		return itemDao.findAllItemByItemCatalogId(id);
	}

	@Override
	public Pager findPagerByItemCatalogId(Pager pager, String id, Map<String, Object> params) throws Exception {
		return itemDao.findPagerByItemCatalogId(pager, id, params);
	}

	@Override
	public boolean checkTableExist(String dbType, String tableName) throws Exception {
		return itemDao.checkTableExist(dbType, tableName);
	}

	@Override
	public Map<String, Object> findProdcutAppend(String sql) throws Exception {
		return itemDao.findProdcutAppend(sql);
	}

	@Override
	public Item saveOrUpdateProduct(Item item, String objectExpandValue) throws Exception {
		String oeId = "";
		if (null == item.getObjectExpand() || "".equals(item.getObjectExpand().getId())) {
			item.setObjectExpand(null);
		} else {
			oeId = item.getObjectExpand().getId();
		}
		item = itemDao.mergeOriginal(item);
		/** 检查扩展对象是否有数据 */
		if (null != objectExpandValue && !"".equals(objectExpandValue)) {
			boolean hasData = false;
			String[] fieldValues = objectExpandValue.split(",");
			for (String str : fieldValues) {
				if (null != str && !"".equals(str)) {
					if (str.split(":").length == 2) {
						hasData = true;
						break;
					}
				}
			}
			/** 检查对象是否引用了扩展对象,并保存外键到扩展类型 */
			if (hasData) {
				ObjectExpand oe = itemDao.findEntityById(ObjectExpand.class, oeId);
				if (null != oe) {
					String fk = getTableNameByClass(Item.class) + "_ID";
					if (!objectExpandDao.checkColumnExist(PropertyConfigLoader.dbType, oe.getExpandTableName(), fk)) {
						oe.setForeignKey(fk);
						oe.setIsReference("1");
						oe = itemDao.merge(oe);
						objectExpandDao.createForeignKey(PropertyConfigLoader.dbType, Item.class, oe.getExpandTableName());
					}
					dealExpandField(item,fk, objectExpandValue);
				}
			}
		}
		return item;
	}

	private void dealExpandField(Item item,String fk, String objectExpandValue) throws Exception {
		if (null != item.getObjectExpand() && null != item.getObjectExpand().getObjectExpandFields() && item.getObjectExpand().getObjectExpandFields().size() > 0) {
			/** 获取扩展字段的字段名称和类型 */
			Map<String, String> oefMap = new HashMap<String, String>();
			for (ObjectExpandField oef : item.getObjectExpand().getObjectExpandFields()) {
				oefMap.put(oef.getFieldName(), oef.getFieldType());
			}
			String tableName = item.getObjectExpand().getExpandTableName();
			if (null != tableName && objectExpandDao.checkTableExist(PropertyConfigLoader.dbType, tableName)) {
				if (null != objectExpandValue && !"".equals(objectExpandValue)) {
					String[] fieldValues = objectExpandValue.split(",");
					String[] apendIdFieldValue = fieldValues[0].split(":");
					if (apendIdFieldValue.length == 1) {
						Map<String, Object> params = new HashMap<String, Object>();
						/** 语句sql */
						StringBuilder insertSql = new StringBuilder("insert into ");
						insertSql.append(tableName);
						insertSql.append(" (");
						/** 数据sql */
						StringBuilder iSql = new StringBuilder();
						/** 开始插入id */
						insertSql.append("id,");
						iSql.append(":id,");
						params.put("id", UUID.randomUUID().toString());
						/** 开始插入外键值SQL */
						insertSql.append(fk + ",");
						iSql.append(":" + fk + ",");
						params.put(fk, item.getId());
						/** 结束插入外键值SQL */
						for (int i = 0; i < fieldValues.length; i++) {
							String[] fv = fieldValues[i].split(":");
							if ("append_ID".equals(fv[0])) {
								continue;
							}
							if (fv.length != 1) {
								insertSql.append(fv[0]);
								iSql.append(":" + fv[0]);
								params.put(fv[0], fv[1]);
								if (i < fieldValues.length - 1) {
									insertSql.append(",");
									iSql.append(",");
								}
							}
						}
						insertSql.append(") values(");
						insertSql.append(iSql);
						insertSql.append(") ");
						itemDao.executeSql(insertSql.toString(), params);
					} else {
						Map<String, Object> params = new HashMap<String, Object>();
						StringBuilder updateSql = new StringBuilder("update ");
						updateSql.append(tableName);
						String appendId = "";
						for (int i = 0; i < fieldValues.length; i++) {
							String[] fv = fieldValues[i].split(":");
							if ("append_ID".equals(fv[0])) {
								appendId = fv[1].trim();
								continue;
							}
							if (updateSql.indexOf("set") == -1) {
								updateSql.append(" set ");
							}
							if (fv.length == 1) {
								String type = oefMap.get(fv[0]);
								if (null != type && !"date".equals(type)) {
									updateSql.append(fv[0]);
									updateSql.append(" = :");
									updateSql.append(fv[0]);
									params.put(fv[0], "");
								} else {
									updateSql.append(fv[0]);
									updateSql.append(" = null ");
								}
							} else {
								updateSql.append(fv[0]);
								updateSql.append(" = :");
								updateSql.append(fv[0]);
								params.put(fv[0], fv[1]);
							}
							if (i < fieldValues.length - 1) {
								updateSql.append(",");
							}
						}
						updateSql.append(" where ID = :ID");
						params.put("ID", "'" + appendId + "'");
						itemDao.executeSql(updateSql.toString(), params);
					}
				}
			}
		}
	}

	@Override
	public List<List<Object>> findProductSpecification(String specTableName, String id, String sku) throws Exception {
		if(StrUtils.objectIsNull(specTableName) || StrUtils.objectIsNull(id)){
			return null;
		}
		
		/** 校验商品 */
		Item ep = this.findEntityById(Item.class, id);
		if(null == ep || null == ep.getItemCatalog() || null == ep.getItemCatalog().getId()){return null;}
		
		/** 获取当前商品绑定的规格 */
		Map<String,Object> params = new HashMap<String, Object>();
		List<Specification> spList = specificationService.findSpecificationByProductCatetoryId(ep.getItemCatalog().getId(), params);
		if(null == spList || spList.size() <= 0){return null;}
		
		return itemDao.findProductSpecification(specTableName,ep,spList,sku);
	}


}