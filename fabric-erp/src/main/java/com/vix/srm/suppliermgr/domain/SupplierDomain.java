/**   
 * @Title: ContractDomain.java 
 * @Package com.vix.contract.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:18:31  
 */
package com.vix.srm.suppliermgr.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;
import com.vix.mdm.srm.share.entity.Attachments;
import com.vix.mdm.srm.share.entity.Supplier;
import com.vix.mdm.srm.share.entity.SupplierAccountingInfo;
import com.vix.mdm.srm.share.entity.SupplierAddress;
import com.vix.mdm.srm.share.entity.SupplierAptitudeInfo;
import com.vix.mdm.srm.share.entity.SupplierBankInfo;
import com.vix.mdm.srm.share.entity.SupplierCategory;
import com.vix.mdm.srm.share.entity.SupplierCreditInfo;
import com.vix.mdm.srm.share.entity.SupplierIndicators;

/**
 * @Description:
 * @author ivan
 * @date 2013-06-27
 */
@Component("supplierDomain")
@Transactional
public class SupplierDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,
				Supplier.class, params);
		return p;
	}
	/** 获取列表数据 */
	public Pager findPagerByHqlConditions2(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,
				Employee.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params,
			Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager,
				Supplier.class, params);
		return p;
	}

	public Supplier findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(Supplier.class, id);
	}
	
	public SupplierCategory findSupplierCategoryById(String id) throws Exception {
		return baseHibernateService.findEntityById(SupplierCategory.class, id);
	}

	public Supplier merge(Supplier supplier) throws Exception {
		return baseHibernateService.merge(supplier);
	}
	
	public Employee merge(Employee employee) throws Exception {
		return baseHibernateService.merge(employee);
	}
	
	public SupplierCategory merge(SupplierCategory SupplierCategory) throws Exception {
		return baseHibernateService.merge(SupplierCategory);
	}

	// 资质信息
	public SupplierAptitudeInfo merge(SupplierAptitudeInfo aptitudeInfo)
			throws Exception {
		return baseHibernateService.merge(aptitudeInfo);
	}
	
	// 地址
	public SupplierAddress merge(SupplierAddress supplierAddress)
			throws Exception {
		return baseHibernateService.merge(supplierAddress);
	}
	
	// 银行信息
	public SupplierBankInfo merge(SupplierBankInfo supplierBankInfo)
			throws Exception {
		return baseHibernateService.merge(supplierBankInfo);
	}
	
	// 财务信息
	public SupplierAccountingInfo merge(SupplierAccountingInfo supplierAccountingInfo)
			throws Exception {
		return baseHibernateService.merge(supplierAccountingInfo);
	}
	
	// 信用
	public SupplierCreditInfo merge(SupplierCreditInfo supplierCreditInfo)
			throws Exception {
		return baseHibernateService.merge(supplierCreditInfo);
	}
	
	// 指标
	public SupplierIndicators merge(SupplierIndicators supplierIndicators)
			throws Exception {
		return baseHibernateService.merge(supplierIndicators);
	}

	/** 附件 */
	public Attachments merge(Attachments attachments) throws Exception {
		return baseHibernateService.merge(attachments);
	}

	public Attachments findAttachmentsEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(Attachments.class, id);
	}
	public void deleteAttachmentsEntity(Attachments attachments)
			throws Exception {
		baseHibernateService.deleteByEntity(attachments);
	}

	public void deleteByEntity(Supplier supplier) throws Exception {
		baseHibernateService.deleteByEntity(supplier);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(Supplier.class, ids);
	}

	/** 索引对象列表 */
	public List<Supplier> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(Supplier.class, null);
	}
	
	public List<SupplierCategory> findAllSubEntity(String attributeName,String parentId,Map<String, Object> params) throws Exception {
		return baseHibernateService.findAllSubEntity(SupplierCategory.class, attributeName, parentId, params);
	}
}
