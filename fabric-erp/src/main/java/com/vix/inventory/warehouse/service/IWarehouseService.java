/**
 * 
 */
package com.vix.inventory.warehouse.service;

import java.util.List;
import java.util.Map;

import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.inventory.warehouse.entity.InvShelf;
import com.vix.inventory.warehouse.entity.InvWarehousezone;
import com.vix.inventory.warehouse.vo.InvUnit;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
public interface IWarehouseService extends IBaseHibernateService {
	/**
	 * @Title: findInvTreeToList
	 * @Description: 加载仓库数据
	 * @return
	 * @throws
	 */
	List<InvUnit> findInvTreeToList(String treeType, String id, Map<String, Object> params) throws Exception;

	/**
	 * 获取仓库数据
	 * 
	 * @param treeType
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<InvUnit> findInvWarehouseTreeToList(String treeType, String id, Map<String, Object> params) throws Exception;

	/**
	 * 获取货区列表
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<InvWarehousezone> findInvWarehouseZoneList(String id) throws Exception;

	/**
	 * 获取(货架/货位)列表
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<InvShelf> findInvShelfListByInvWarehouse(String id) throws Exception;
}
