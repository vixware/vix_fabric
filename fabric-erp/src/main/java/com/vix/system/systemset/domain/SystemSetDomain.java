/**
 * 
 */
package com.vix.system.systemset.domain;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vix.common.base.domain.BaseDomain;
import com.vix.common.mail.entity.PersonalEmail;
import com.vix.common.security.entity.UserAccount;
import com.vix.core.web.Pager;
import com.vix.system.warningSource.entity.ModuleCategory;
import com.vix.system.warningSource.entity.WarningType;

/**
 * @author zhanghaibing
 * 
 * @date 2013-6-27
 */
@Component("systemSetDomain")
@Transactional
public class SystemSetDomain extends BaseDomain {

	/** 获取列表数据 */
	public Pager findPersonalEmailPager(Map<String, Object> params, Pager pager) throws Exception {
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, PersonalEmail.class, params);
		return p;
	}

	public PersonalEmail findPersonalEmailById(String id) throws Exception {
		return baseHibernateService.findEntityById(PersonalEmail.class, id);
	}

	public PersonalEmail mergePersonalEmail(PersonalEmail personalEmail) throws Exception {
		return baseHibernateService.merge(personalEmail);
	}

	public UserAccount mergeUserAccount(UserAccount userAccount) throws Exception {
		return baseHibernateService.merge(userAccount);
	}

	public WarningType mergeWarningType(WarningType warningType) throws Exception {
		return baseHibernateService.merge(warningType);
	}

	public void deleteByEntity(PersonalEmail personalEmail) throws Exception {
		baseHibernateService.deleteByEntity(personalEmail);
	}

	public void deleteModuleCategoryByIds(List<String> ids) throws Exception {
		baseHibernateService.batchDelete(ModuleCategory.class, ids);
	}

	/** 索引对象列表 */
	public List<PersonalEmail> findPersonalEmailIndex() throws Exception {
		return baseHibernateService.findAllByConditions(PersonalEmail.class, null);
	}

}
