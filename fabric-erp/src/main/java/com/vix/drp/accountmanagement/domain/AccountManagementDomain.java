package com.vix.drp.accountmanagement.domain;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.security.entity.UserAccount;
import com.vix.core.web.Pager;
import com.vix.drp.accountmanagement.service.IAccountManagementService;
import com.vix.drp.storePersonnelinformation.entity.ChannelDistributorEmployee;

@Component("accountManagementDomain")
@Transactional
public class AccountManagementDomain extends BaseDomain{
	@Autowired
	private IAccountManagementService accountManagementService;

	/** 获取列表数据 */
	public Pager findUserAccountPagerByHqlConditions(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = accountManagementService.findPagerByHqlConditions(pager, UserAccount.class, params);
		return p;
	}

	public UserAccount findEntityById(String id) throws Exception {
		return accountManagementService.findEntityById(UserAccount.class, id);
	}

	public ChannelDistributorEmployee merge(ChannelDistributorEmployee distributorEmployee) throws Exception {
		return accountManagementService.merge(distributorEmployee);
	}

	public void saveOrUpdate(UserAccount userAccount) throws Exception {
		accountManagementService.saveOrUpdate(userAccount);
	}

	public void deleteByEntity(UserAccount userAccount) throws Exception {
		accountManagementService.deleteByEntity(userAccount);
	}

	public void deleteByIds(List<String> ids) throws Exception {
		accountManagementService.batchDelete(UserAccount.class, ids);
	}

	/** 索引对象列表 */
	public List<UserAccount> findStoreInfomationIndex() throws Exception {
		return accountManagementService.findAllByConditions(UserAccount.class, null);
	}
}
