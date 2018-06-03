/**   
 * @Title: ContractDomain.java 
 * @Package com.vix.contract.domain 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author chenzhengwen
 * @author w_a_533@163.com   
 * @date 2013-6-24 下午4:18:31  
 */
package com.vix.contract.mamanger.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.contract.mamanger.entity.ApplicationForm;
import com.vix.contract.mamanger.entity.Contract;
import com.vix.contract.mamanger.entity.ContractAssociateTemplate;
import com.vix.contract.mamanger.entity.ContractLine;
import com.vix.contract.mamanger.entity.ContractMarket;
import com.vix.contract.mamanger.entity.ContractPricingConditions;
import com.vix.contract.mamanger.entity.ContractSubject;
import com.vix.contract.mamanger.entity.ContractTemplate;
import com.vix.contract.mamanger.entity.ContractWarning;
import com.vix.contract.mamanger.entity.PmContract;
import com.vix.contract.mamanger.entity.PriceConditions;
import com.vix.contract.mamanger.entity.ProtocolTemplete;
import com.vix.core.web.Pager;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.srm.share.entity.Supplier;

/**
 * @ClassName: ContractDomain
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author chenzhengwen
 * @author w_a_533@163.com
 * @date 2013-6-24 下午4:18:31
 */
@Component("contractDomain")
@Transactional
public class ContractDomain extends BaseDomain{


	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params,Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager,Contract.class, params);
		return p;
	}
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, Contract.class, params);
		return p;
	}
	
	/**根据id获取合同标的明细数据*/
	public ContractSubject findContractSubjectById(String id) throws Exception{
		return baseHibernateService.findEntityById(ContractSubject.class, id);
	}
	/**根据id获取项目合同明细数据*/
	public PmContract findPmContractById(String id) throws Exception{
		return baseHibernateService.findEntityById(PmContract.class, id);
	}
	/**根据id获取销售定价条件明细数据*/
	public ContractMarket findContractMarketById(String id) throws Exception{
		return baseHibernateService.findEntityById(ContractMarket.class, id);
	}
	/**根据id获取合同定价明细数据*/
	public ContractPricingConditions findContractPricingConditionsById(String id) throws Exception{
		return baseHibernateService.findEntityById(ContractPricingConditions.class, id);
	}
	/**根据id获取合同项明细数据*/
	public ContractLine findContractLineById(String id) throws Exception{
		return baseHibernateService.findEntityById(ContractLine.class, id);
	}
	/**根据id获取合同预警明细数据*/
	public ContractWarning findContractWarningById(String id) throws Exception{
		return baseHibernateService.findEntityById(ContractWarning.class, id);
	}
	/**根据id获取合同审批明细数据*/
	public ApplicationForm findApplicationFormById(String id) throws Exception{
		return baseHibernateService.findEntityById(ApplicationForm.class, id);
	}
	/**根据id获取合同审批明细数据*/
	public PriceConditions findPriceConditionsById(String id) throws Exception{
		return baseHibernateService.findEntityById(PriceConditions.class, id);
	}
	
	/**合同标的*/
	public ContractSubject merge(ContractSubject contractSubject)
			throws Exception {
		return baseHibernateService.merge(contractSubject);
	}
	/**项目合同*/
	public PmContract merge(PmContract pmContract)throws Exception {
		return baseHibernateService.merge(pmContract);
	}
	
	/**销售定价条件*/
	public ContractMarket merge(ContractMarket contractMarket)throws Exception {
		return baseHibernateService.merge(contractMarket);
	}
	
	/**合同定价条件*/
	public ContractPricingConditions merge(ContractPricingConditions contractPricingConditions)throws Exception {
		return baseHibernateService.merge(contractPricingConditions);
	}
	public PriceConditions merge(PriceConditions priceConditions)throws Exception {
		return baseHibernateService.merge(priceConditions);
	}
	public Supplier merge(Supplier supplier) throws Exception{
		return baseHibernateService.merge(supplier);
	}
	public CustomerAccount merge(CustomerAccount customerAccount) throws Exception{
		return baseHibernateService.merge(customerAccount);
	}
	
	public void deleteContractSubjectEntity(ContractSubject contractSubject) throws Exception {
		baseHibernateService.deleteByEntity(contractSubject);
	}
	public void deletePmContractEntity(PmContract pmContract) throws Exception {
		baseHibernateService.deleteByEntity(pmContract);
	}
	public void deleteContractMarketEntity(ContractMarket contractMarket) throws Exception {
		baseHibernateService.deleteByEntity(contractMarket);
	}
	public void deleteContractPricingConditionsEntity(ContractPricingConditions contractPricingConditions) throws Exception {
		baseHibernateService.deleteByEntity(contractPricingConditions);
	}
	public void deleteContractLineEntity(ContractLine contractLine) throws Exception {
		baseHibernateService.deleteByEntity(contractLine);
	}
	public void deleteContractWarningEntity(ContractWarning contractWarning) throws Exception {
		baseHibernateService.deleteByEntity(contractWarning);
	}
	public void deleteApplicationFormEntity(ApplicationForm applicationForm) throws Exception {
		baseHibernateService.deleteByEntity(applicationForm);
	}
	public void deletePriceConditionsEntity(PriceConditions priceConditions) throws Exception {
		baseHibernateService.deleteByEntity(priceConditions);
	}
	public Pager findPagerByHqlConditions2(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Supplier.class, params);
		return p;
	}
	public Pager findPagerByHqlConditions3(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, CustomerAccount.class, params);
		return p;
	}

	public Contract findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(Contract.class, id);
	}
	public ContractAssociateTemplate findEntityById2(String id) throws Exception {
		return baseHibernateService.findEntityById(ContractAssociateTemplate.class, id);
	}
	public ProtocolTemplete findEntityById3(String id) throws Exception {
		return baseHibernateService.findEntityById(ProtocolTemplete.class, id);
	}
	
	public Contract findEntityById1(String id) throws Exception {
		return baseHibernateService.findEntityById(Contract.class, id);
	}

	public Contract merge(Contract contract) throws Exception {
		return baseHibernateService.merge(contract);
	}
	
	public ContractTemplate merge(ContractTemplate contractTemplate) throws Exception{
		return baseHibernateService.merge(contractTemplate);
	}

	public ContractLine merge(ContractLine contractLine) throws Exception {
		return baseHibernateService.merge(contractLine);
	}
	
	public ApplicationForm merge(ApplicationForm applicationForm) throws Exception {
		return baseHibernateService.merge(applicationForm);
	}

	public ContractWarning merge(ContractWarning contractWarning)
			throws Exception {
		return baseHibernateService.merge(contractWarning);
	}

	public void deleteByEntity(Contract contract) throws Exception {
		baseHibernateService.deleteByEntity(contract);
	}
	
	public void deleteContractTemplateEntity(ContractTemplate contractTemplate) throws Exception{
		baseHibernateService.deleteByEntity(contractTemplate);
	}
	
	public ContractTemplate findContractTemplateEntityById(String id) throws Exception{
		return baseHibernateService.findEntityById(ContractTemplate.class, id);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(Contract.class, ids);
	}

	/** 索引对象列表 */
	public List<Contract> findSalesOrderIndex() throws Exception {
		return baseHibernateService.findAllByConditions(Contract.class, null);
	}
}
