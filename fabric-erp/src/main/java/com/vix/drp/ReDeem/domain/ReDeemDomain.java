package com.vix.drp.ReDeem.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.drp.MembershipCardmanagement.entity.MemberShipCard;
import com.vix.drp.setRedeemGoods.entity.SetRedeemGoods;
import com.vix.sales.order.entity.SalesOrder;

/**
 * 竞争者信息
 * 
 * @author zhanghaibing
 * 
 * @date 2013-10-9
 */
@Component("reDeemDomain")
@Transactional
public class ReDeemDomain extends BaseDomain{

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, MemberShipCard.class, params);
		return p;
	}

	public Pager findSetRedeemGoodsPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, SetRedeemGoods.class, params);
		return p;
	}

	public MemberShipCard findMemberShipCardById(String id) throws Exception {
		return baseHibernateService.findEntityById(MemberShipCard.class, id);
	}

	public MemberShipCard mergeMemberShipCard(MemberShipCard memberShipCard) throws Exception {
		return baseHibernateService.merge(memberShipCard);
	}

	public SalesOrder mergeSalesOrder(SalesOrder salesOrder) throws Exception {
		return baseHibernateService.merge(salesOrder);
	}

	public void deleteMemberShipCard(MemberShipCard memberShipCard) throws Exception {
		baseHibernateService.deleteByEntity(memberShipCard);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(MemberShipCard.class, ids);
	}

	/** 索引对象列表 */
	public List<MemberShipCard> findMemberShipCardList(Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllByConditions(MemberShipCard.class, params);
	}
}
