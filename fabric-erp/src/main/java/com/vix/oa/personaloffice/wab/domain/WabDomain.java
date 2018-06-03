package com.vix.oa.personaloffice.wab.domain;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.oa.personaloffice.wab.entity.Wab;

/**
 * 
 * @ClassName: WabDomain
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-5-26 上午10:47:43
 */
@Component("wabDomain")
@Transactional
public class WabDomain extends BaseDomain{

	
	/** 获取删除个人或公共通讯簿列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,Wab.class, params);
		return p;
	}
	
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, Wab.class, params);
		return p;
	}
	
	/** 索引对象列表 */
	public List<Wab> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(Wab.class, null);
	}
	
	/** 删除个人或公共通讯簿 */
	public Wab findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(Wab.class, id);
	}
	/** 删除个人或公共通讯簿 */
	public void deleteByEntity(Wab wab) throws Exception {
		baseHibernateService.deleteByEntity(wab);
	}
	
	public Wab merge(Wab wab) throws Exception {
		return baseHibernateService.merge(wab);
	}
	
}
