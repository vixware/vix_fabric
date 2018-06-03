package com.vix.common.security.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.vix.common.security.dao.IUserAccountProxyDao;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.entity.UserAccountProxyApply;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.exception.BizException;
import com.vix.core.persistence.hibernate.dao.impl.BaseHibernateDaoImpl;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

@Repository("userAccountProxyDao")
public class UserAccountProxyDaoImpl extends BaseHibernateDaoImpl 
										implements IUserAccountProxyDao {
	
	@Override
	public Pager findAcceptUserAccountProxyPager(Pager pager, Map<String, Object> params) throws Exception{
		/*String ename = "uap";
		StringBuilder hql = new StringBuilder();
		Map<String,Object> param = new HashMap<String,Object>();
		
		hql.append("select ").append(ename).append(" from ").append(UserAccountProxyApply.class.getName());
		hql.append(" ").append(ename).append(" where ");
		hql.append(ename).append(".acceptUserAccount.id = :curUserAccountId ");
		param.put("curUserAccountId", SecurityUtil.getCurrentUserId());
		
		pager = findPager2ByHql(pager, ename, hql.toString(), params);
		return pager;*/
		StringBuilder sql = new StringBuilder();
		List<Object> sqlParams = new LinkedList<Object>();
		//Map<String,Object> sqlParams = new HashMap<String,Object>();
		sql.append("SELECT scua.*, csu.account userAccount, he.name empName ");//.append(ename);
		sql.append(" from CMN_SEC_USERACCOUNT_APPLY scua  ");
		sql.append(" INNER JOIN CMN_SEC_USERACCOUNT csu ON csu.ID = scua.ApplyUserAccount_ID ");
		sql.append(" LEFT JOIN HR_ORG_EMPLOYEE he ON he.ID = csu.Employee_ID ");
		
		sql.append(" where scua.AcceptUserAccount_ID=? ");//:curuseraccountid 
		sqlParams.add(SecurityUtil.getCurrentUserId());
		//sqlParams.put("curuseraccountid", SecurityUtil.getCurrentUserId());
		
		//pager = findPagerBySqlFull(pager, sql.toString(), sqlParams);
		pager = queryPagerListBySql(pager, UserAccountProxyApply.class, sql.toString(), null, sqlParams.toArray());
		return pager;
	}
	
	@Override
	public Pager findApplyUserAccountProxyPager(Pager pager, Map<String, Object> params) throws Exception{
		/*
		使用HQL
		String ename = "uap";
		StringBuilder hql = new StringBuilder();
		Map<String,Object> param = new HashMap<String,Object>();
		
		hql.append("select ").append(ename).append(" from ").append(UserAccountProxyApply.class.getName());
		hql.append(" ").append(ename).append(" where ");
		hql.append(ename).append(".applyUserAccount.id = :curUserAccountId ");
		param.put("curUserAccountId", SecurityUtil.getCurrentUserId());
		
		pager = findPager2ByHql(pager, ename, hql.toString(), params);
		return pager;*/
		
		//使用SQL查询  前端用easyui展示  不方便使用hql
		StringBuilder sql = new StringBuilder();
		List<Object> sqlParams = new LinkedList<Object>();
		//Map<String,Object> sqlParams = new HashMap<String,Object>();
		sql.append("SELECT scua.*, csu.account userAccount, he.name empName ");//.append(ename);
		sql.append(" from CMN_SEC_USERACCOUNT_APPLY scua  ");
		sql.append(" INNER JOIN CMN_SEC_USERACCOUNT csu ON csu.ID = scua.AcceptUserAccount_ID ");
		sql.append(" LEFT JOIN HR_ORG_EMPLOYEE he ON he.ID = csu.Employee_ID ");
		
		sql.append(" where scua.ApplyUserAccount_ID=? ");//:curuseraccountid 
		sqlParams.add(SecurityUtil.getCurrentUserId());
		//sqlParams.put("curuseraccountid", SecurityUtil.getCurrentUserId());
		
		//pager = findPagerBySqlFull(pager, sql.toString(), sqlParams);
		pager = queryPagerListBySql(pager, UserAccountProxyApply.class, sql.toString(), null, sqlParams.toArray());
		return pager;
	}
	
	@Override
	public void saveOrUpdateProxyConfig(UserAccountProxyApply userAccountProxyApply) throws Exception{
		/*if(userAccountProxyApply.getAcceptUserAccount()==null){
			throw new BizException("代理人账号信息必须填写！");
		}*/
		String empId = userAccountProxyApply.getEmpId();
		if(empId==null){
			throw new BizException("代理人职员信息必须选择！");
		}
		UserAccount acceptUserAccount = findUserAccountByEmpId(empId);
		if(acceptUserAccount==null){
			throw new BizException("所选职员信息没有账号信息，请重新选择！");
		}
		
		UserAccountProxyApply uap = null;
		if(StrUtils.isNotEmpty(userAccountProxyApply.getId())){
			uap = findEntityById(UserAccountProxyApply.class, userAccountProxyApply.getId());
		}
		Date now = new Date();
		if(uap == null){
			uap = new UserAccountProxyApply();
		}
		
		if(uap.getId()==null){
			uap.setCreateTime(now);
		}
		uap.setUpdateTime(now);
		
		uap.setApplyUserAccount(findEntityById(UserAccount.class, SecurityUtil.getCurrentUserId()));
		uap.setAcceptUserAccount(acceptUserAccount);
		uap.setIsEnable(userAccountProxyApply.getIsEnable());
		uap.setMemo(userAccountProxyApply.getMemo());
		
		saveOrUpdate(uap);
	}
	
	private UserAccount findUserAccountByEmpId(String empId) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append(" select userAccount from ").append(UserAccount.class.getName()).append(" userAccount");
		sb.append(" where userAccount.employee.id = :empId ");
   
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("empId", empId);
		UserAccount userAccount = findObjectByHqlNoTenantId(sb.toString(), params);
		return userAccount;
	}
}
