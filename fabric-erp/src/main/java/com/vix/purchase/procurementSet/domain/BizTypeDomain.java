/**   
 * @Title: ContractDomain.java 
 * @Package com.vix.contract.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:18:31  
 */
package com.vix.purchase.procurementSet.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.mdm.purchase.order.entity.BizType;

/**
 * @Description: 采购业务类型
 * @author ivan
 * @date 2014-01-22
 */
@Component("bizTypeDomain")
@Transactional
public class BizTypeDomain extends BaseDomain {

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, BizType.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, BizType.class, params);
		return p;
	}

	public BizType findBizTypeById(String id) throws Exception {
		return baseHibernateService.findEntityById(BizType.class, id);
	}

	public BizType merge(BizType bizType) throws Exception {
		return baseHibernateService.merge(bizType);
	}

	public void deleteByBizType(BizType bizType) throws Exception {
		baseHibernateService.deleteByEntity(bizType);
	}

	/** 索引对象列表 */
	public List<BizType> findBizTypeIndex() throws Exception {
		return baseHibernateService.findAllByConditions(BizType.class, null);
	}

}
