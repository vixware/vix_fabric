package com.vix.drp.storeInformation.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.org.entity.Organization;
import com.vix.common.share.entity.CurrencyType;
import com.vix.core.web.Pager;
import com.vix.drp.channel.entity.ChannelDistributor;
import com.vix.drp.storeInformation.service.IStoreInformationService;
import com.vix.hr.organization.entity.Employee;
import com.vix.system.code.entity.EncodingRulesTableInTheMiddle;

@Component("storeInformationDomain")
@Transactional
public class StoreInformationDomain extends BaseDomain{

	@Autowired
	private IStoreInformationService storeInformationService;

	/** 获取列表数据 */
	public Pager findPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = storeInformationService.findPagerByHqlConditions(pager, ChannelDistributor.class, params);
		return p;
	}

	public ChannelDistributor findEntityById(String id) throws Exception {
		return storeInformationService.findEntityById(ChannelDistributor.class, id);
	}

	public Employee findEmployeeById(String id) throws Exception {
		return storeInformationService.findEntityById(Employee.class, id);
	}

	public Organization findOrganizationById(String id) throws Exception {
		return storeInformationService.findEntityById(Organization.class, id);
	}

	public ChannelDistributor merge(ChannelDistributor channelDistributor) throws Exception {
		return storeInformationService.merge(channelDistributor);
	}

	public ChannelDistributor saveOrUpdate(ChannelDistributor channelDistributor) throws Exception {
		return storeInformationService.merge(channelDistributor);
	}

	public EncodingRulesTableInTheMiddle saveOrUpdateEncodingRulesTableInTheMiddle(EncodingRulesTableInTheMiddle encodingRulesTableInTheMiddle) throws Exception {
		return storeInformationService.merge(encodingRulesTableInTheMiddle);
	}

	public Employee saveOrUpdateEmployee(Employee employee) throws Exception {
		return storeInformationService.merge(employee);
	}

	public void deleteByEntity(ChannelDistributor channelDistributor) throws Exception {
		storeInformationService.deleteByEntity(channelDistributor);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		storeInformationService.batchDelete(ChannelDistributor.class, ids);
	}

	/** 索引对象列表 */
	public List<ChannelDistributor> findChannelDistributorList(Map<String, Object> params) throws Exception {
		return storeInformationService.findAllByConditions(ChannelDistributor.class, params);
	}

	public List<CurrencyType> findCurrencyTypeList(Map<String, Object> params) throws Exception {
		return storeInformationService.findAllByConditions(CurrencyType.class, params);
	}
}
