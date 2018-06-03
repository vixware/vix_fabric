package com.vix.contract.mamanger.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.contract.mamanger.entity.ContractWarning;
import com.vix.core.web.Pager;


/**
 * @ClassName: ContractDomain
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2013-6-24 下午4:18:31
 */
@Component("contractWarningDomain")
@Transactional
public class ContractWarningDomain extends BaseDomain{
	

	/** 获取列表数据  */
	public Pager findPagerByHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ContractWarning.class, params);
		return p;
	}
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, ContractWarning.class, params);
		return p;
	}
	
	public ContractWarning findEntityById(String id) throws Exception{
		return baseHibernateService.findEntityById(ContractWarning.class, id);
	}
	
	
	public ContractWarning merge(ContractWarning contractWarning) throws Exception{
		return baseHibernateService.merge(contractWarning);
	}
	
	public void deleteByEntity(ContractWarning contractWarning) throws Exception{
		baseHibernateService.deleteByEntity(contractWarning);
	}
	
	public void deleteByIds(List<String> ids) throws Exception{
		baseHibernateService.batchDelete(ContractWarning.class,ids);
	}

	/** 索引对象列表 */
	public List<ContractWarning> findContractWarningIndex() throws Exception{
		return baseHibernateService.findAllByConditions(ContractWarning.class, null);
	}
}
