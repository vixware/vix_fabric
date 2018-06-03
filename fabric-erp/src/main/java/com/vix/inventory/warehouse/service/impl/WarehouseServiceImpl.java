/**
 * 
 */
package com.vix.inventory.warehouse.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.vix.core.constant.SearchCondition;
import com.vix.core.persistence.hibernate.dao.IBaseHibernateDao;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.inventory.warehouse.entity.InvShelf;
import com.vix.inventory.warehouse.entity.InvWarehouse;
import com.vix.inventory.warehouse.entity.InvWarehousezone;
import com.vix.inventory.warehouse.hql.InvHqlProvider;
import com.vix.inventory.warehouse.service.IWarehouseService;
import com.vix.inventory.warehouse.vo.InvUnit;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Service("warehouseService")
public class WarehouseServiceImpl extends BaseHibernateServiceImpl implements IWarehouseService {
	private static final long serialVersionUID = 1L;
	@Resource(name = "invHqlProvider")
	private InvHqlProvider invHqlProvider;
	@Resource(name = "baseHibernateDao")
	private IBaseHibernateDao baseHibernateDao;

	@Override
	public List<InvUnit> findInvTreeToList(String treeType, String id, Map<String, Object> params) throws Exception {
		List<InvUnit> invUnitList = new LinkedList<InvUnit>();
		if (treeType.equals("W")) {
			List<InvWarehouse> invWarehouseList = findInvWarehouseList(id, params);
			List<InvWarehousezone> invWarehousezoneList = findInvWarehouseZoneList(id);
			List<InvShelf> invShelfList = findInvShelfListByInvWarehouse(id);
			invUnitList = makeInvTree(invWarehouseList, invWarehousezoneList, invShelfList);
		} else if (treeType.equals("Z")) {
			List<InvShelf> invShelfList = findInvShelfListByInvWarehousezone(id);
			invUnitList = makeInvTree(null, null, invShelfList);
		} else if (treeType.equals("S")) {
			List<InvShelf> invShelfList = findInvShelfListByInvShelf(id);
			invUnitList = makeInvTree(null, null, invShelfList);
		}
		return invUnitList;
	}

	/**
	 * 父节点是仓库的货区
	 */
	@Override
	public List<InvWarehousezone> findInvWarehouseZoneList(String id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (null == id ) {
			params.put("invWarehouse.id" + "," + SearchCondition.IS, "NULL");
		} else {
			params.put("invWarehouse.id" + "," + SearchCondition.EQUAL, id);
		}
		StringBuilder hql = invHqlProvider.findOrgList(params, null, null, null);
		List<InvWarehousezone> invWarehousezoneList = baseHibernateDao.findAllByHql(hql.toString(), params);
		return invWarehousezoneList;
	}

	public List<InvWarehouse> findInvWarehouseList(String id, Map<String, Object> params) throws Exception {
		if (null == id ) {
			params.put("parentInvWarehouse.id" + "," + SearchCondition.IS, "NULL");
		} else {
			params.put("parentInvWarehouse.id" + "," + SearchCondition.EQUAL, id);
		}
		StringBuilder hql = invHqlProvider.findInvWarehouseList(params, null, null, null);
		List<InvWarehouse> invWarehousezoneList = baseHibernateDao.findAllByHql(hql.toString(), params);
		return invWarehousezoneList;
	}

	/**
	 * 父节点是仓库
	 */

	@Override
	public List<InvShelf> findInvShelfListByInvWarehouse(String id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (null == id ) {
			params.put("invWarehouse.id" + "," + SearchCondition.IS, "NULL");
		} else {
			params.put("invWarehouse.id" + "," + SearchCondition.EQUAL, id);
		}
		StringBuilder hql = invHqlProvider.findInvShelfList(params, null, null, null);
		List<InvShelf> invShelfList = baseHibernateDao.findAllByHql(hql.toString(), params);
		return invShelfList;
	}

	/**
	 * 父节点是货区
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<InvShelf> findInvShelfListByInvWarehousezone(String id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (null == id ) {
			params.put("invWarehousezone.id" + "," + SearchCondition.IS, "NULL");
		} else {
			params.put("invWarehousezone.id" + "," + SearchCondition.EQUAL, id);
		}
		StringBuilder hql = invHqlProvider.findInvShelfList(params, null, null, null);
		List<InvShelf> invShelfList = baseHibernateDao.findAllByHql(hql.toString(), params);
		return invShelfList;
	}

	/**
	 * 父节点是货架
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<InvShelf> findInvShelfListByInvShelf(String id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		if (null == id ) {
			params.put("parentInvShelf.id" + "," + SearchCondition.IS, "NULL");
		} else {
			params.put("parentInvShelf.id" + "," + SearchCondition.EQUAL, id);
		}
		StringBuilder hql = invHqlProvider.findInvShelfList(params, null, null, null);
		List<InvShelf> invShelfList = baseHibernateDao.findAllByHql(hql.toString(), params);
		return invShelfList;
	}

	/**
	 * 拼装仓库的树结构 W仓库 Z货区 S(货架/货位)
	 */
	private List<InvUnit> makeInvTree(List<InvWarehouse> invWarehouseList, List<InvWarehousezone> invWarehouseZoneList, List<InvShelf> invShelfList) {
		List<InvUnit> orgUnitList = new LinkedList<InvUnit>();
		if (invWarehouseList != null) {
			for (InvWarehouse invWarehouse : invWarehouseList) {
				InvUnit invUnit = new InvUnit(invWarehouse.getId(), "W", invWarehouse.getName(), invWarehouse.getCode());
				if (invWarehouse.getSubInvWarehouse().size() > 0) {
					List<InvUnit> subUnitList = new LinkedList<InvUnit>();
					for (InvWarehouse ou : invWarehouse.getSubInvWarehouse()) {
						subUnitList.add(new InvUnit(ou.getId(), "W", ou.getName(), ou.getCode()));
					}
					invUnit.setSubInvUnits(subUnitList);
				}
				if (invWarehouse.getInvShelfs().size() > 0) {
					List<InvUnit> subInvUnitList = new LinkedList<InvUnit>();
					for (InvShelf invShelf : invWarehouse.getInvShelfs()) {
						subInvUnitList.add(new InvUnit(invShelf.getId(), "S", invShelf.getName(), invShelf.getCode()));
					}
					invUnit.setSubInvUnits(subInvUnitList);
				}
				orgUnitList.add(invUnit);
			}
		}
		if (invWarehouseZoneList != null) {
			for (InvWarehousezone invWarehousezone : invWarehouseZoneList) {
				InvUnit invUnit = new InvUnit(invWarehousezone.getId(), "Z", invWarehousezone.getName(), invWarehousezone.getCode());
				if (invWarehousezone.getSubInvWarehousezone().size() > 0) {
					List<InvUnit> subUnitList = new LinkedList<InvUnit>();
					for (InvWarehousezone ou : invWarehousezone.getSubInvWarehousezone()) {
						subUnitList.add(new InvUnit(ou.getId(), "Z", ou.getName(), ou.getCode()));
					}
					invUnit.setSubInvUnits(subUnitList);
				}
				if (invWarehousezone.getSubInvShelf().size() > 0) {
					List<InvUnit> subInvUnitList = new LinkedList<InvUnit>();
					for (InvShelf invShelf : invWarehousezone.getSubInvShelf()) {
						subInvUnitList.add(new InvUnit(invShelf.getId(), "S", invShelf.getName(), invShelf.getCode()));
					}
					invUnit.setSubInvUnits(subInvUnitList);
				}
				orgUnitList.add(invUnit);
			}
		}
		if (invShelfList != null) {
			for (InvShelf invShelf : invShelfList) {
				InvUnit invUnit = new InvUnit(invShelf.getId(), "S", invShelf.getName(), invShelf.getCode());
				if (invShelf.getSubInvShelfs().size() > 0) {
					List<InvUnit> subInvUnitList = new LinkedList<InvUnit>();
					for (InvShelf ou : invShelf.getSubInvShelfs()) {
						subInvUnitList.add(new InvUnit(ou.getId(), "S", ou.getName(), ou.getCode()));
					}
					invUnit.setSubInvUnits(subInvUnitList);
				}
				orgUnitList.add(invUnit);
			}
		}
		return orgUnitList;
	}

	@Override
	public List<InvUnit> findInvWarehouseTreeToList(String treeType, String id, Map<String, Object> params) throws Exception {

		List<InvUnit> invUnitList = new LinkedList<InvUnit>();
		if (treeType.equals("W")) {
			List<InvWarehouse> invWarehouseList = findInvWarehouseList(id, params);
			invUnitList = makeInvWarehouseTree(invWarehouseList);
		}
		return invUnitList;

	}

	private List<InvUnit> makeInvWarehouseTree(List<InvWarehouse> invWarehouseList) {
		List<InvUnit> orgUnitList = new LinkedList<InvUnit>();
		if (invWarehouseList != null) {
			for (InvWarehouse invWarehouse : invWarehouseList) {
				InvUnit invUnit = new InvUnit(invWarehouse.getId(), "W", invWarehouse.getName(), invWarehouse.getCode());
				if (invWarehouse.getSubInvWarehouse().size() > 0) {
					List<InvUnit> subUnitList = new LinkedList<InvUnit>();
					for (InvWarehouse ou : invWarehouse.getSubInvWarehouse()) {
						subUnitList.add(new InvUnit(ou.getId(), "W", ou.getName(), ou.getCode()));
					}
					invUnit.setSubInvUnits(subUnitList);
				}
				orgUnitList.add(invUnit);
			}
		}
		return orgUnitList;
	}
}
