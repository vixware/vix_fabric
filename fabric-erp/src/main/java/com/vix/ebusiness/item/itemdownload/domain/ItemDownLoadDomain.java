/**
 * 
 */
package com.vix.ebusiness.item.itemdownload.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.ebusiness.entity.Goods;
import com.vix.ebusiness.item.itemdownload.hql.ItemProcessHqlProvider;
import com.vix.ebusiness.item.itemdownload.service.IItemDownLoadService;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("itemDownLoadDomain")
@Transactional
public class ItemDownLoadDomain extends BaseDomain{

	@Autowired
	private IItemDownLoadService itemDownLoadService;
	@Autowired
	private ItemProcessHqlProvider itemProcessHqlProvider;

	/** 获取列表数据 */
	public Pager findItemPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = itemDownLoadService.findPagerByHqlConditions(pager, Goods.class, params);
		return p;
	}

	/** 索引对象列表 */
	public List<Goods> findGoodsList(Map<String, Object> params) throws Exception {
		return itemDownLoadService.findAllByConditions(Goods.class, null);
	}

	public List<ChannelDistributor> findChannelDistributorList(Map<String, Object> params) throws Exception {
		return itemDownLoadService.findAllByConditions(ChannelDistributor.class, params);
	}

	public Goods findGoodsById(String id) throws Exception {
		return itemDownLoadService.findEntityById(Goods.class, id);
	}

	public Goods findBeforeGoodsById(String id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		StringBuilder hql = itemProcessHqlProvider.findBeforeGoods(params);
		return itemDownLoadService.findItemByHql(hql.toString(), params);
	}

	public void deleteGoodsByEntity(Goods goods) throws Exception {
		itemDownLoadService.deleteByEntity(goods);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		itemDownLoadService.batchDelete(Goods.class, ids);
	}

	public Goods findAfterGoodsById(String id) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		StringBuilder hql = itemProcessHqlProvider.findAfterGoods(params);
		return itemDownLoadService.findItemByHql(hql.toString(), params);
	}
}
