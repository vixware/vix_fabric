
package com.vix.contract.mamanger.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.contract.mamanger.entity.ContractAssociateTemplate;
import com.vix.contract.mamanger.entity.ProtocolTemplete;
import com.vix.core.web.Pager;

/**
 * @ClassName: 
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2013-6-24 下午4:18:31
 */
@Component("contractAssociateTemplateDomain")
@Transactional
public class ContractAssociateTemplateDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,ContractAssociateTemplate.class, params);
		return p;
	}
	/** 获取列表数据 */
	public Pager findPagerByHqlConditions1(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,ProtocolTemplete.class, params);
		return p;
	}
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, ContractAssociateTemplate.class, params);
		return p;
	}

	public ContractAssociateTemplate findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(ContractAssociateTemplate.class, id);
	}
	public ProtocolTemplete findEntityById1(String id) throws Exception {
		return baseHibernateService.findEntityById(ProtocolTemplete.class, id);
	}

	public ContractAssociateTemplate merge(ContractAssociateTemplate contractAssociateTemplate) throws Exception {
		return baseHibernateService.merge(contractAssociateTemplate);
	}
	public ProtocolTemplete merge(ProtocolTemplete protocolTemplete) throws Exception {
		return baseHibernateService.merge(protocolTemplete);
	}

	public void deleteByEntity(ContractAssociateTemplate contractAssociateTemplate) throws Exception {
		baseHibernateService.deleteByEntity(contractAssociateTemplate);
	}
	public void deleteByEntity1(ProtocolTemplete protocolTemplete) throws Exception {
		baseHibernateService.deleteByEntity(protocolTemplete);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(ContractAssociateTemplate.class, ids);
	}

	/** 索引对象列表 */
	public List<ContractAssociateTemplate> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(ContractAssociateTemplate.class, null);
	}
	/** 索引对象列表 */
	public List<ProtocolTemplete> findSalesOrderIndex1() throws Exception {
		return baseHibernateService.findAllByConditions(ProtocolTemplete.class, null);
	}
}
