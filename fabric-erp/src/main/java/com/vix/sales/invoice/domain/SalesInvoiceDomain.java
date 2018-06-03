/**   
* @Title: ContractDomain.java 
* @Package com.vix.contract.domain 
* @Description: TODO(用一句话描述该文件做什么) 
* @author chenzhengwen
* @author w_a_533@163.com   
* @date 2013-6-24 下午4:18:31  
*/
package com.vix.sales.invoice.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.core.web.Pager;
import com.vix.sales.invoice.entity.SalesInvoice;
import com.vix.sales.invoice.entity.SalesInvoiceItem;


/**
 * @Description: 销售发票
 * @author ivan 
 * @date 2013-09-04
 */
@Component("salesInvoiceDomain")
@Transactional
public class SalesInvoiceDomain extends BaseDomain{
	

	/** 获取列表数据  */
	public Pager findPagerByHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, SalesInvoice.class, params);
		return p;
	}
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, SalesInvoice.class, params);
		return p;
	}
	
	public SalesInvoice findEntityById(String id) throws Exception{
		return baseHibernateService.findEntityById(SalesInvoice.class, id);
	}
	
	public SalesInvoice merge(SalesInvoice salesInvoice) throws Exception{
		return baseHibernateService.merge(salesInvoice);
	}
	
	public SalesInvoiceItem merge(SalesInvoiceItem salesInvoiceItem) throws Exception{
		return baseHibernateService.merge(salesInvoiceItem);
	}
	
	public void deleteByEntity(SalesInvoice salesInvoice) throws Exception{
		baseHibernateService.deleteByEntity(salesInvoice);
	}
	
	public void deleteByIds(List<String> ids) throws Exception{
		baseHibernateService.batchDelete(SalesInvoice.class,ids);
	}

	/** 索引对象列表 */
	public List<SalesInvoice> findSalesInvoiceIndex() throws Exception{
		return baseHibernateService.findAllByConditions(SalesInvoice.class, null);
	}
	
}
