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
import com.vix.common.security.entity.UserAccount;
import com.vix.core.web.Pager;


/**
 * @Description: 供应商帐号
 * @author ivan 
 * @date 2013-12-27
 */
@Component("supplierAccountDomain")
@Transactional
public class SupplierAccountDomain extends BaseDomain{
	

	/** 获取列表数据  */
	public Pager findPagerByHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByHqlConditions(pager, UserAccount.class, params);
		return p;
	}
	/** 获取搜索列表数据  */
	public Pager findPagerByOrHqlConditions(Map<String,Object> params,Pager pager) throws Exception{
		Pager p = baseHibernateService.findPagerByOrHqlConditions(pager, UserAccount.class, params);
		return p;
	}

	public UserAccount findUserAccountById(String id) throws Exception{
		return baseHibernateService.findEntityById(UserAccount.class, id);
	}
	
	public UserAccount merge(UserAccount userAccount) throws Exception{
		return baseHibernateService.merge(userAccount);
	}
	
	public void deleteByUserAccount(UserAccount userAccount) throws Exception{
		baseHibernateService.deleteByEntity(userAccount);
	}
	
	public void deleteByIds(List<String> ids) throws Exception{
		baseHibernateService.batchDelete(UserAccount.class,ids);
	}

	/** 索引对象列表 */
	public List<UserAccount> findUserAccountIndex() throws Exception{
		return baseHibernateService.findAllByConditions(UserAccount.class, null);
	}
}
