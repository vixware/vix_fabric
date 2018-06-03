/**
 * 
 */
package com.vix.inventory.initInventory.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpand;
import com.vix.core.persistence.jdbc.dbstruct.ObjectExpandField;
import com.vix.core.utils.PropertyConfigLoader;
import com.vix.core.web.Pager;
import com.vix.inventory.initInventory.dao.IInitInventoryDao;
import com.vix.inventory.initInventory.service.IInitInventoryService;
import com.vix.mdm.item.entity.Item;
import com.vix.system.expand.dao.IObjectExpandDao;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Service("initInventoryService")
public class InitInventoryServiceImpl extends BaseHibernateServiceImpl implements IInitInventoryService {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IInitInventoryDao initInventoryDao;
	@Autowired
	private IObjectExpandDao objectExpandDao;

	@Override
	public List<Item> findAllItemByItemCatalogId(Long id) throws Exception {
		return initInventoryDao.findAllItemByItemCatalogId(id);
	}

	@Override
	public Pager findPagerByItemCatalogId(Pager pager, Long id) throws Exception {
		return initInventoryDao.findPagerByItemCatalogId(pager, id);
	}

	@Override
	public boolean checkTableExist(String dbType, String tableName) throws Exception {
		return initInventoryDao.checkTableExist(dbType, tableName);
	}

	@Override
	public Map<String, Object> findProdcutAppend(String sql) throws Exception {
		return initInventoryDao.findProdcutAppend(sql);
	}

	@Override
	public Item saveOrUpdateProduct(Item item, String objectExpandValue) throws Exception {
		String oeId = "0";
		if (null == item.getObjectExpand() || null == item.getObjectExpand().getId()) {
			item.setObjectExpand(null);
		} else {
			oeId = item.getObjectExpand().getId();
		}
		item = initInventoryDao.merge(item);
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
				ObjectExpand oe = initInventoryDao.findEntityById(ObjectExpand.class, oeId);
				if (null != oe) {
					String fk = getTableNameByClass(Item.class) + "_ID";
					oe.setIsReference("1");
					oe.setForeignKey(fk);
					initInventoryDao.merge(oe);
					if (!objectExpandDao.checkColumnExist(PropertyConfigLoader.dbType, oe.getExpandTableName(), fk)) {
						oe.setForeignKey(fk);
						oe.setIsReference("1");
						oe = initInventoryDao.merge(oe);
						objectExpandDao.createForeignKey(PropertyConfigLoader.dbType, Item.class, oe.getExpandTableName());
					}
					dealExpandField(item, objectExpandValue);
				}
			}
		}
		return item;
	}

	private void dealExpandField(Item item, String objectExpandValue) throws Exception {
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
						/** 开始插入外键值SQL */
						ObjectExpand oe = objectExpandDao.findEntityById(ObjectExpand.class, item.getObjectExpand().getId());
						insertSql.append(oe.getForeignKey() + ",");
						iSql.append(":" + oe.getForeignKey() + ",");
						params.put(item.getObjectExpand().getForeignKey(), item.getId());
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
						initInventoryDao.executeSql(insertSql.toString(), params);
					} else {
						Map<String, Object> params = new HashMap<String, Object>();
						StringBuilder updateSql = new StringBuilder("update ");
						updateSql.append(tableName);
						Long appendId = 0l;
						for (int i = 0; i < fieldValues.length; i++) {
							String[] fv = fieldValues[i].split(":");
							if ("append_ID".equals(fv[0])) {
								appendId = Long.parseLong(fv[1]);
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
						params.put("ID", appendId);
						initInventoryDao.executeSql(updateSql.toString(), params);
					}
				}
			}
		}
	}

}
