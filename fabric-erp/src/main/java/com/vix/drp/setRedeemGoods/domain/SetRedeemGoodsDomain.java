package com.vix.drp.setRedeemGoods.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.setRedeemGoods.entity.SetRedeemGoods;
import com.vix.inventory.standingbook.entity.InventoryCurrentStock;

@Component("setRedeemGoodsDomain")
@Transactional
public class SetRedeemGoodsDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, SetRedeemGoods.class, params);
		return p;
	}

	public Pager findInventoryCurrentStockPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, InventoryCurrentStock.class, params);
		return p;
	}

	public SetRedeemGoods findSetRedeemGoodsById(String id) throws Exception {
		return baseHibernateService.findEntityById(SetRedeemGoods.class, id);
	}

	public InventoryCurrentStock findInventoryCurrentStockById(String id) throws Exception {
		return baseHibernateService.findEntityById(InventoryCurrentStock.class, id);
	}

	public SetRedeemGoods saveOrUpdate(SetRedeemGoods setRedeemGoods) throws Exception {
		return baseHibernateService.merge(setRedeemGoods);
	}

	/** 索引对象列表 */
	public List<SetRedeemGoods> findSetRedeemGoodsList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(SetRedeemGoods.class, params);
	}
}
