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

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.mdm.item.entity.Item;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.mdm.srm.share.entity.SupplierAptitudeInfo;

/**
 * @Description: 供应商网上注册
 * @author ivan 
 * @date 2013-08-20
 */
@Component("registeredSupplierDomain")
@Transactional
public class RegisteredSupplierDomain extends BaseDomain{


	public Supplier findSupplierById(String id) throws Exception {
		return baseHibernateService.findEntityById(Supplier.class, id);
	}
	
	public SupplierAptitudeInfo findSupplierAptitudeInfoById(String id) throws Exception {
		return baseHibernateService.findEntityById(SupplierAptitudeInfo.class, id);
	}
	
	public Item findItemById(String id) throws Exception {
		return baseHibernateService.findEntityById(Item.class, id);
	}

	public Supplier merge(Supplier supplier) throws Exception {
		return baseHibernateService.merge(supplier);
	}
	
	public SupplierAptitudeInfo merge(SupplierAptitudeInfo supplierAptitudeInfo) throws Exception {
		return baseHibernateService.merge(supplierAptitudeInfo);
	}
	
	public Item merge(Item item) throws Exception {
		return baseHibernateService.merge(item);
	}

	public void deleteBySupplier(Supplier supplier) throws Exception {
		baseHibernateService.deleteByEntity(supplier);
	}

	public void deleteBySupplierIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(Supplier.class, ids);
	}
}
