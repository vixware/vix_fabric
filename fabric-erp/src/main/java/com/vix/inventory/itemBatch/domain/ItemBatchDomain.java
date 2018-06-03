/**
 * 
 */
package com.vix.inventory.itemBatch.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.mdm.item.entity.Item;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("itemBatchDomain")
@Transactional
public class ItemBatchDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findItemPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Item.class, params);
		return p;
	}

	public Item findItemById(String id) throws Exception {
		return baseHibernateService.findEntityById(Item.class, id);
	}

	/**
	 * 保存
	 */
	public Item mergeItem(Item item) throws Exception {
		return baseHibernateService.merge(item);
	}

	/** 索引对象列表 */
	public List<Item> findItemIndex() throws Exception {
		return baseHibernateService.findAllByConditions(Item.class, null);
	}

}
