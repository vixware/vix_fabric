/**   
 * @Title: ContractDomain.java 
 * @Package com.vix.contract.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:18:31  
 */
package com.vix.srm.portal.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.mdm.srm.share.entity.SupplierVMI;

/**
 * @Description: 供应商管理库存
 * @author ivan 
 * @date 2013-08-14
 */
@Component("supplierVMIDomain")
@Transactional
public class SupplierVMIDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,
				SupplierVMI.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager,
				SupplierVMI.class, params);
		return p;
	}

	public SupplierVMI findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(SupplierVMI.class, id);
	}

	public SupplierVMI merge(SupplierVMI supplierVMI) throws Exception {
		return baseHibernateService.merge(supplierVMI);
	}

	public void deleteByEntity(SupplierVMI supplierVMI) throws Exception {
		baseHibernateService.deleteByEntity(supplierVMI);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(SupplierVMI.class, ids);
	}


	/** 索引对象列表 */
	public List<SupplierVMI> findSupplierVMIIndex() throws Exception {
		return baseHibernateService.findAllByConditions(SupplierVMI.class, null);
	}
}
