package com.vix.mdm.item.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.core.persistence.jdbc.IObjectExpandDBService;
import com.vix.core.persistence.jdbc.dbstruct.Specification;
import com.vix.core.persistence.jdbc.impl.ObjectExpandDBService;
import com.vix.core.utils.SortSet;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.mdm.item.dao.IItemDao;
import com.vix.mdm.item.entity.Item;

@Repository("itemDao")
public class ItemDaoImpl extends BaseHibernateDaoImpl implements IItemDao {

	private static final long serialVersionUID = 1L;

	@Override
	public List<List<Object>> findItemSpecification(String specTableName, String itemId) throws Exception {
		Item ep = this.findEntityById(Item.class, itemId);
		String tenantId = SecurityUtil.getCurrentUserTenantId();
		if (null != ep && null != ep.getObjectExpand() && null != tenantId && !"".equals(tenantId)) {
			StringBuilder sql = new StringBuilder("select ID,SKU,INVENTORY_COUNT,SPEC_CODE,");
			List<Specification> spList = new ArrayList<Specification>(ep.getItemCatalog().getSpecifications());
			SortSet scm = new SortSet("orderBy");
			Collections.sort(spList, scm);
			for (int i = 0; i < spList.size(); i++) {
				Specification s = spList.get(i);
				if (null != s && null != s.getCode()) {
					sql.append(s.getCode());
					if (i < spList.size() - 1) {
						sql.append(",");
					}
				}
			}
			sql.append(" from ");
			sql.append(specTableName);
			sql.append(" where MDM_ITEMCATALOG_ID = ");
			sql.append("'"+ep.getObjectExpand().getId()+"'");
			sql.append(" AND OBJECT_TYPE = 'ItemType' ");
			sql.append(" AND OBJECT_ID = ");
			sql.append("'"+itemId+"'");
			sql.append(" AND TENANTID = '");
			sql.append(tenantId);
			sql.append("'");
			List<Object> objList = findListBySql(sql.toString(), null);
			if (null != objList && objList.size() > 0) {
				List<List<Object>> sList = new ArrayList<List<Object>>();
				for (Object obj : objList) {
					if (null != obj) {
						Object[] objArray = (Object[]) obj;
						List<Object> listItem = new ArrayList<Object>();
						for (Object oItem : objArray) {
							listItem.add(oItem);
						}
						sList.add(listItem);
					}
				}
				return sList;
			}
		}
		return null;
	}
	/*public List<List<Object>> findItemSpecification(String specTableName, String itemId) throws Exception {
		Item ep = this.findEntityById(Item.class, itemId);
		String tenantId = SecurityUtil.getCurrentUserTenantId();
		if (null != ep && null != ep.getObjectExpand() && null != tenantId && !"".equals(tenantId)) {
			StringBuilder sql = new StringBuilder("select ID,SKU,INVENTORY_COUNT,SPEC_CODE,");
			List<Specification> spList = new ArrayList<Specification>(ep.getObjectExpand().getSpecifications());
			SortSet scm = new SortSet("orderBy");
			Collections.sort(spList, scm);
			for (int i = 0; i < spList.size(); i++) {
				Specification s = spList.get(i);
				if (null != s && null != s.getCode()) {
					sql.append(s.getCode());
					if (i < spList.size() - 1) {
						sql.append(",");
					}
				}
			}
			sql.append(" from ");
			sql.append(specTableName);
			sql.append(" where MDM_ITEMCATALOG_ID = ");
			sql.append("'"+ep.getObjectExpand().getId()+"'");
			sql.append(" AND OBJECT_TYPE = 'ItemType' ");
			sql.append(" AND OBJECT_ID = ");
			sql.append("'"+itemId+"'");
			sql.append(" AND TENANTID = '");
			sql.append(tenantId);
			sql.append("'");
			List<Object> objList = findListBySql(sql.toString(), null);
			if (null != objList && objList.size() > 0) {
				List<List<Object>> sList = new ArrayList<List<Object>>();
				for (Object obj : objList) {
					if (null != obj) {
						Object[] objArray = (Object[]) obj;
						List<Object> listItem = new ArrayList<Object>();
						for (Object oItem : objArray) {
							listItem.add(oItem);
						}
						sList.add(listItem);
					}
				}
				return sList;
			}
		}
		return null;
	}*/
	@Override
	public List<Item> findAllItemByItemCatalogId(String id) throws Exception {
		if (null != id && !"".equals(id)) {
			String hql = "from Item i left join i.itemCatalogs as ic where ic.id = '" + id + "'";
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("id," + SearchCondition.EQUAL, id);
			return this.findAllByHql(hql, params);
		} else {
			String hql = "from Item i left join i.itemCatalogs as ic where ic is null";
			Map<String, Object> params = new HashMap<String, Object>();
			return this.findAllByHql(hql, params);
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public Pager findPagerByItemCatalogId(Pager pager, String id, Map<String, Object> params) throws Exception {
		//String itemClass = params.get("itemClass,"+SearchCondition.IN).toString();
		//params.remove("itemClass,"+SearchCondition.IN);
		//StringBuilder hqlBuilder = new StringBuilder("from Item hentity left join hentity.itemCatalogs as ic where ic.id = '" + id + "' and ");
		StringBuilder hqlBuilder = new StringBuilder("from Item hentity left join hentity.itemCatalogs as ic where ic.id = '" + id );
		buildSearchQl(hqlBuilder, params);
		Query query = getSession().createQuery("select count(hentity.id) " + hqlBuilder.toString());
		if (null != params && params.keySet().size() > 0) {
			configQuery(query, params);
		}
		int totalCount = 0;
		Object uniqueR = query.uniqueResult();
		if (null != uniqueR && !"".equals(uniqueR)) {
			totalCount = Integer.parseInt(uniqueR.toString());
		}
		pager.setTotalCount(totalCount);
		StringBuilder searchSql = new StringBuilder(hqlBuilder);
		if (null != pager.getOrderField()) {
			if (null != params && params.keySet().size() > 0) {
				searchSql.append(" order by hentity.");
			} else {
				searchSql.append(" order by ");
			}
			searchSql.append(pager.getOrderField());
			searchSql.append(" ");
			searchSql.append(pager.getOrderBy());
		}
		Query resultQuery = getSession().createQuery(searchSql.toString());
		if (null != params && params.keySet().size() > 0) {
			configQuery(resultQuery, params);
		}
		resultQuery.setFirstResult((pager.getPageNo() - 1) * pager.getPageSize());
		resultQuery.setMaxResults(pager.getPageSize());
		List<Object> list = resultQuery.list();
		if (null != list && list.size() > 0) {
			pager.setResultList(new ArrayList<Item>());
		}
		for (Object obj : list) {
			Object[] o = (Object[]) obj;
			pager.getResultList().add(o[0]);
		}
		return pager;
	}

	@Override
	public boolean checkTableExist(String dbType, String tableName) throws Exception {
		IObjectExpandDBService oedb = new ObjectExpandDBService();
		return oedb.checkTableExist(dbType, tableName);
	}

	@Override
	public Map<String, Object> findProdcutAppend(String sql) throws Exception {
		Map<String, Object> paMap = new HashMap<String, Object>();
		Object obj = findObjectBySql(sql, null);
		Object[] objArray = (Object[]) obj;
		String key = sql.substring(sql.indexOf(" "), sql.indexOf("from"));
		String[] keyArray = key.split(",");
		for (int i = 0; i < keyArray.length; i++) {
			String k = keyArray[i];
			if (null != k && null != objArray && null != objArray[i]) {
				paMap.put(k.trim(), objArray[i]);
			}
		}
		return paMap;
	}

	private void configQuery(Query query, Map<String, Object> params) {
		for (String key : params.keySet()) {
			if (null != key && !"".equals(key)) {
				Object value = params.get(key);
				String[] k = key.split(",");
				if (k.length == 2) {
					if (k[0].contains(".")) {
						if (k[1].equals(SearchCondition.EQUAL) || k[1].equals(SearchCondition.MORETHAN) || k[1].equals(SearchCondition.MORETHANANDEQUAL) || k[1].equals(SearchCondition.LESSTHAN) || k[1].equals(SearchCondition.LESSTHANANDEQUAL)) {
							query.setParameter(StrUtils.fixParamForHql(k[0]), value);
						} else if (k[1].equals(SearchCondition.ANYLIKE)) {
							query.setParameter(StrUtils.fixParamForHql(k[0]), "%" + value + "%");
						} else if (k[1].equals(SearchCondition.STARTLIKE)) {
							query.setParameter(StrUtils.fixParamForHql(k[0]), "%" + value);
						} else if (k[1].equals(SearchCondition.ENDLIKE)) {
							query.setParameter(StrUtils.fixParamForHql(k[0]), value + "%");
						} else if (k[1].equals(SearchCondition.NOEQUAL)) {
							query.setParameter(StrUtils.fixParamForHql(k[0]), value);
						}
					} else {
						if (k[1].equals(SearchCondition.EQUAL) || k[1].equals(SearchCondition.MORETHAN) || k[1].equals(SearchCondition.MORETHANANDEQUAL) || k[1].equals(SearchCondition.LESSTHAN) || k[1].equals(SearchCondition.LESSTHANANDEQUAL)) {
							query.setParameter(k[0], value);
						} else if (k[1].equals(SearchCondition.ANYLIKE)) {
							System.out.println(k[0] + "--" + value);
							query.setParameter(k[0], "%" + value + "%");
						} else if (k[1].equals(SearchCondition.STARTLIKE)) {
							query.setParameter(k[0], "%" + value);
						} else if (k[1].equals(SearchCondition.ENDLIKE)) {
							query.setParameter(k[0], value + "%");
						} else if (k[1].equals(SearchCondition.NOEQUAL)) {
							query.setParameter(StrUtils.fixParamForHql(k[0]), value);
						}
					}
				} else {
					if (key.contains(".")) {
						query.setParameter(StrUtils.fixParamForHql(k[0]), "%" + value + "%");
					} else {
						// 还需要细化
						query.setParameter(key, value);
					}
				}
			}
		}
	}

	/** 根据参数拼接hql */
	private void buildSearchQl(StringBuilder qlBuilder, Map<String, Object> params) {
		int keyCount = params.keySet().size();
		/** 参数里需要移除的参数key */
		StringBuilder needRemoveList = new StringBuilder();
		for (String key : params.keySet()) {
			if (!StrUtils.objectIsNull(key)) {
				String[] k = key.split(",");
				if (k.length == 2) {
					if (k[0].contains(".")) {
						if (k[1].equals(SearchCondition.EQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" = :").append(k[0].split("\\.")[0]);
						} else if (k[1].equals(SearchCondition.ANYLIKE) || k[1].equals(SearchCondition.STARTLIKE) || k[1].equals(SearchCondition.ENDLIKE)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" like :").append(k[0].split("\\.")[0]);
						} else if (k[1].equals(SearchCondition.NOEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" != :").append(k[0].split("\\.")[0]);
						} else if (k[1].equals(SearchCondition.MORETHAN)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" > :").append(k[0].split("\\.")[0]);
						} else if (k[1].equals(SearchCondition.MORETHANANDEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" >= :").append(k[0].split("\\.")[0]);
						} else if (k[1].equals(SearchCondition.LESSTHAN)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" < :").append(k[0].split("\\.")[0]);
						} else if (k[1].equals(SearchCondition.LESSTHANANDEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" <= :").append(k[0].split("\\.")[0]);
						} else if (k[1].equals(SearchCondition.BETWEENAND)) {
							Object v = params.get(key);
							if (null != v && !"".equals(v)) {
								String[] val = v.toString().split("!");
								if (val.length == 2) {
									qlBuilder.append("hentity.").append(k[0]);
									qlBuilder.append(" between '");
									qlBuilder.append(val[0]);
									qlBuilder.append("' and '");
									qlBuilder.append(val[1]);
									qlBuilder.append("'");
									needRemoveList.append(key).append(",");
								}
							}
						} else if (k[1].equals(SearchCondition.IS)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" is null ");
						} else if (k[1].equals(SearchCondition.IN)) {
							Object inValue = params.get(key);
							dealHqlInCondition(inValue, needRemoveList, qlBuilder, key);
						}
					} else {
						if (k[1].equals(SearchCondition.EQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" = :").append(k[0]);
						} else if (k[1].equals(SearchCondition.ANYLIKE) || k[1].equals(SearchCondition.STARTLIKE) || k[1].equals(SearchCondition.ENDLIKE)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" like :").append(k[0]);
						} else if (k[1].equals(SearchCondition.NOEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" != :").append(k[0]);
						} else if (k[1].equals(SearchCondition.MORETHAN)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" > :").append(k[0]);
						} else if (k[1].equals(SearchCondition.MORETHANANDEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" >= :").append(k[0]);
						} else if (k[1].equals(SearchCondition.LESSTHAN)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" < :").append(k[0]);
						} else if (k[1].equals(SearchCondition.LESSTHANANDEQUAL)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" <= :").append(k[0]);
						} else if (k[1].equals(SearchCondition.BETWEENAND)) {
							Object v = params.get(key);
							if (null != v && !"".equals(v)) {
								String[] val = v.toString().split("!");
								if (val.length == 2) {
									qlBuilder.append("hentity.").append(k[0]);
									qlBuilder.append(" between '");
									qlBuilder.append(val[0]);
									qlBuilder.append("' and '");
									qlBuilder.append(val[1]);
									qlBuilder.append("'");
									needRemoveList.append(key).append(",");
								}
							}
						} else if (k[1].equals(SearchCondition.IS)) {
							qlBuilder.append("hentity.").append(k[0]);
							qlBuilder.append(" is null");
						} else if (k[1].equals(SearchCondition.IN)) {
							Object inValue = params.get(key);
							dealHqlInCondition(inValue, needRemoveList, qlBuilder, key);
						}
					}
				} else {
					if (key.contains(".")) {
						qlBuilder.append("hentity.").append(key);
						qlBuilder.append(" like :").append(k[0].split("\\.")[0]);
					} else {
						qlBuilder.append("hentity.").append(key);
						qlBuilder.append(" like :").append(key);
					}
				}
				if (keyCount > 1) {
					qlBuilder.append(" and ");
				}
				keyCount--;
			}
		}
		for (String k : needRemoveList.toString().split(",")) {
			if (null != k && !"".equals(k)) {
				params.remove(k);
			}
		}
	}

	/**
	 * 处理hql语句查询中含有in条件的情况
	 * 
	 * @param inValue
	 *            参数Map中in条件的value
	 * @param inList
	 *            参数Map中的in条件的key，方法执行完要将in条件的key从map中移除，in条件的hql不需要配参数
	 * @param qlBuilder
	 *            构建的hql语句
	 * @param key
	 *            参数Map的key
	 */
	private void dealHqlInCondition(Object inValue, StringBuilder needRemoveList, StringBuilder qlBuilder, String key) {
		String[] k = key.split(",");
		if (null != inValue && !"".equals(inValue)) {
			needRemoveList.append(key).append(",");
			qlBuilder.append("hentity.").append(k[0]);
			qlBuilder.append(" in (");
			String[] ivArray = inValue.toString().split(",");
			for (int i = 0; i < ivArray.length; i++) {
				String iv = ivArray[i];
				if (null != iv && !"".equals(iv)) {
					qlBuilder.append("'");
					qlBuilder.append(iv);
					qlBuilder.append("'");
					if (i < ivArray.length - 1) {
						qlBuilder.append(",");
					}
				}
			}
			qlBuilder.append(") ");
		}
	}
	@Override
	public List<List<Object>> findProductSpecification(String specTableName, Item ep, List<Specification> spList,
			String... sku) throws Exception {
		if(null != ep && null != spList && spList.size() > 0 && null != ep.getItemCatalog()){
			StringBuilder sql = new StringBuilder("select ID,SKU,INVENTORY_COUNT,");
			SortSet scm = new SortSet("orderBy","asc");
			Collections.sort(spList, scm);
			for(int i = 0;i < spList.size() ;i++){
				Specification s = spList.get(i);
				if(null != s && null != s.getCode()){
					sql.append(s.getCode());
					if(i<spList.size()-1){
						sql.append(",");
					}
				}
			}
			sql.append(" from ");
			sql.append(specTableName);
			sql.append(" where MDM_ITEMCATALOG_ID = '");
			sql.append(ep.getItemCatalog().getId());
			sql.append("' AND OBJECT_TYPE = 'EcProductType' ");
			sql.append(" AND OBJECT_ID = '");
			sql.append(ep.getId());
			sql.append("'");
			Map<String,Object> params = new HashMap<String, Object>();
			if(null != sku && sku.length > 0 && StrUtils.objectIsNotNull(sku[0])){
				sql.append(" and SKU = :sku");
				params.put("sku", sku[0]);
			}
			List<Object> objList = findListBySql(sql.toString(),params);
			if(null != objList && objList.size() > 0){
				List<List<Object>> sList = new ArrayList<List<Object>>();
				for(Object obj : objList){
					if(null != obj){
						Object[] objArray = (Object[])obj;
						List<Object> listItem = new ArrayList<Object>();
						for(Object oItem : objArray){
							listItem.add(oItem);
						}
						sList.add(listItem);
					}
				}
				return sList;
			}
		}
		return null;
	}

}
