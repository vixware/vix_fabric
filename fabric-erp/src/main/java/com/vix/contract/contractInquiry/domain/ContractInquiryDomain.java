package com.vix.contract.contractInquiry.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.security.entity.UserAccount;
import com.vix.contract.contractInquiry.entity.ContractChildItem;
import com.vix.contract.contractInquiry.entity.ContractInquiry;
import com.vix.contract.mamanger.entity.ApplicationForm;
import com.vix.contract.mamanger.entity.ContractMarket;
import com.vix.contract.mamanger.entity.ContractPricingConditions;
import com.vix.contract.mamanger.entity.ContractSubject;
import com.vix.contract.mamanger.entity.ContractWarning;
import com.vix.contract.mamanger.entity.PriceConditions;
import com.vix.core.persistence.hibernate.service.IBaseHibernateService;
import com.vix.core.web.Pager;
import com.vix.mdm.crm.entity.CustomerAccount;
import com.vix.mdm.srm.share.entity.Supplier;

@Transactional
@Component("contractInquirydomain")
public class ContractInquiryDomain extends BaseDomain{

	@Autowired
	public IBaseHibernateService baseHibernateService;
	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, ContractInquiry.class, params);
		return p;
	}

	/** 获取搜索列表数据 */
	public Pager findPagerByOrHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, ContractInquiry.class, params);
		return p;
	}

	/** 获取项目列表数据 */
	public Pager findProjectName(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, Supplier.class, params);
		return p;
	}

	/** 获取供应商列表数据 */
	public Pager findCustomerAccount(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, CustomerAccount.class, params);
		return p;
	}

	public ContractInquiry findEntityById(String id) throws Exception {
		return baseHibernateService.findEntityById(ContractInquiry.class, id);
	}

	public UserAccount findUserAccountById(String id) throws Exception {
		return baseHibernateService.findEntityById(UserAccount.class, id);
	}

	/** 根据id获取合同子项明细数据 */
	public ContractChildItem findContractLineById(String id) throws Exception {
		return baseHibernateService.findEntityById(ContractChildItem.class, id);
	}

	/** 根据id获取合同标的明细数据 */
	public ContractSubject findContractSubjectById(String id) throws Exception {
		return baseHibernateService.findEntityById(ContractSubject.class, id);
	}

	/** 根据id获取合同预警明细数据 */
	public ContractWarning findContractWarningById(String id) throws Exception {
		return baseHibernateService.findEntityById(ContractWarning.class, id);
	}

	/** 根据id获取价格条件明细数据 */
	public PriceConditions findPriceConditionsById(String id) throws Exception {
		return baseHibernateService.findEntityById(PriceConditions.class, id);
	}

	/** 根据id获取采购价格条件明细数据 */
	public ContractPricingConditions findContractPricingConditionsById(String id) throws Exception {
		return baseHibernateService.findEntityById(ContractPricingConditions.class, id);
	}

	/** 根据id获取销售价格条件明细数据 */
	public ContractMarket findContractMarketById(String id) throws Exception {
		return baseHibernateService.findEntityById(ContractMarket.class, id);
	}

	/** 根据id获取合同审批明细数据 */
	public ApplicationForm findApplicationFormById(String id) throws Exception {
		return baseHibernateService.findEntityById(ApplicationForm.class, id);
	}

	public ContractInquiry merge(ContractInquiry contractInquiry) throws Exception {
		return baseHibernateService.merge(contractInquiry);
	}

	public ContractMarket merge(ContractMarket contractMarket) throws Exception {
		return baseHibernateService.merge(contractMarket);
	}

	public ContractPricingConditions merge(ContractPricingConditions contractPricingConditions) throws Exception {
		return baseHibernateService.merge(contractPricingConditions);
	}

	public ContractWarning merge(ContractWarning contractWarning) throws Exception {
		return baseHibernateService.merge(contractWarning);
	}

	public ContractSubject merge(ContractSubject contractSubject) throws Exception {
		return baseHibernateService.merge(contractSubject);
	}

	public ApplicationForm merge(ApplicationForm applicationForm) throws Exception {
		return baseHibernateService.merge(applicationForm);
	}

	public PriceConditions merge(PriceConditions priceConditions) throws Exception {
		return baseHibernateService.merge(priceConditions);
	}

	public Supplier merge(Supplier supplier) throws Exception {
		return baseHibernateService.merge(supplier);
	}

	/** 合同子项明细 */
	public ContractChildItem merge(ContractChildItem childItem) throws Exception {
		return baseHibernateService.merge(childItem);
	}

	public void deleteByEntity(ContractInquiry contractInquiry) throws Exception {
		baseHibernateService.deleteByEntity(contractInquiry);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(ContractInquiry.class, ids);
	}

	/** 索引对象列表 */
	public List<ContractInquiry> findContractIndex() throws Exception {
		return baseHibernateService.findAllByConditions(ContractInquiry.class, null);
	}

	public void deleteContractLineEntity(ContractChildItem childItem) throws Exception {
		baseHibernateService.deleteByEntity(childItem);
	}

	public void deleteContractSubjectEntity(ContractSubject subject) throws Exception {
		baseHibernateService.deleteByEntity(subject);
	}

	public void deleteContractWarningEntity(ContractWarning warning) throws Exception {
		baseHibernateService.deleteByEntity(warning);
	}

	public void deletePriceConditionsEntity(PriceConditions conditions) throws Exception {
		baseHibernateService.deleteByEntity(conditions);
	}

	public void deleteContractPricingConditionsEntity(ContractPricingConditions pricingConditions) throws Exception {
		baseHibernateService.deleteByEntity(pricingConditions);
	}

	public void deleteContractMarketEntity(ContractMarket market) throws Exception {
		baseHibernateService.deleteByEntity(market);
	}

	public void deleteApplicationFormEntity(ApplicationForm application) throws Exception {
		baseHibernateService.deleteByEntity(application);
	}

}
