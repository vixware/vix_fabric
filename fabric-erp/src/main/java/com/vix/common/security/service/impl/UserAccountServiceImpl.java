package com.vix.common.security.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.vix.common.org.dao.IBaseOrganizationDao;
import com.vix.common.security.dao.IRoleDao;
import com.vix.common.security.dao.IUserAccountDao;
import com.vix.common.security.entity.Authority;
import com.vix.common.security.entity.Role;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.hql.UserAccountHqlProvider;
import com.vix.common.security.service.IUserAccountService;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.BizConstant;
import com.vix.core.encode.Md5Encoder;
import com.vix.core.encode.Md5EncoderImpl;
import com.vix.core.exception.BizException;
import com.vix.core.persistence.hibernate.service.impl.BaseHibernateServiceImpl;
import com.vix.core.utils.NumberUtil;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.code.CodeDesUtil;
import com.vix.core.web.Pager;

@Service("userAccountService")
@Transactional
public class UserAccountServiceImpl extends BaseHibernateServiceImpl implements IUserAccountService,UserDetailsService {//VixUserDetailsService

	private static final long serialVersionUID = 1L;
	
	@Resource(name="userAccountDao")
	private IUserAccountDao userAccountDao;
	@Resource(name="roleDao")
	private IRoleDao roleDao;
	@Resource(name="baseOrganizationDao")
	private IBaseOrganizationDao baseOrganizationDao;
	
	@Resource(name="userAccountHqlProvider")
	private UserAccountHqlProvider userAccountHqlProvider;
	
	//@Resource(name="roleHqlProvider")
	//private RoleHqlProvider roleHqlProvider;
	
	@Override
	public UserAccount findUserByAccount(String userName) throws Exception{
		//StringBuilder hql = userAccountHqlProvider.findUserByAccount(userName);
		/*
		StringBuilder sb = new StringBuilder();
		sb.append(" from ").append(UserAccount.class.getName()).append(" userAccount");
		sb.append(" left join fetch userAccount.roles role");
		  // sb.append(" left join fetch userAccount.userGroups ug left join fetch ug.roles role2 left join fetch role2.authoritys ");
		sb.append(" left join fetch role.authoritys au");
		sb.append(" left join fetch au.resources ");
		sb.append(" where userAccount.account=:account ");
   
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("account", userName);
		UserAccount userAccount = userAccountDao.findObjectByHql(sb.toString(), params);
		return userAccount;
		
		 */	
		return userAccountDao.findUserByAccount(userName);
	}
	
	
	public UserAccount findUserByAccountAndTenantId(String userName) throws Exception{
		//StringBuilder hql = userAccountHqlProvider.findUserByAccount(userName);
		StringBuilder sb = new StringBuilder();
		sb.append("select userAccount from ").append(UserAccount.class.getName()).append(" userAccount");
		sb.append(" left join fetch userAccount.roles role");
		  // sb.append(" left join fetch userAccount.userGroups ug left join fetch ug.roles role2 left join fetch role2.authoritys ");
		sb.append(" left join fetch role.authoritys au");
		sb.append(" left join fetch au.resources ");
		sb.append(" where userAccount.account=:account ");
   
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("account", userName);
		
		
		//HqlTenantIdUtil.handleParamMap4TenantId(params);
		//如果登录用户为admin  则 不加载tenantId
		/*if(!userName.equalsIgnoreCase(BizConstant.USERACCOUNT_SUPERADMIN)){
			
			sb.append(" and userAccount.tenantId = :tenantId ");
			params.put("tenantId", tenantId);
			//不增加tenantId
		}*/
		//UserAccount userAccount = userAccountDao.findObjectByHql(sb.toString(),false, params);
		UserAccount userAccount = userAccountDao.findObjectByHql(sb.toString(),false, params);
		if(userAccount!=null){
			userAccountDao.evict(userAccount);
			Set<Role> uaRoles = userAccount.getRoles();
			if(uaRoles!=null){
				Set<Role> roles = new HashSet<Role>(uaRoles);
				Date now = new Date();
	        	for(Role r:uaRoles){
	        		Date st = r.getStartTime();
	        		Date ed = r.getEndTime();
	        		st = (st==null?now:st);
	        		ed = (ed==null?now:ed);
	        		if( now.compareTo(st)<0
	        			|| now.compareTo(ed)>0){
	        			roles.remove(r);
	        		}
	            }
	        	userAccount.setRoles(roles);
	        	
	        }
		}
		
		return userAccount;
	}
	public UserAccount findUserByAccountPwdAndTenantId(String userName,String pwd) throws Exception{
		//StringBuilder hql = userAccountHqlProvider.findUserByAccount(userName);
		StringBuilder sb = new StringBuilder();
		sb.append("select userAccount from ").append(UserAccount.class.getName()).append(" userAccount");
		sb.append(" left join fetch userAccount.roles role");
		  // sb.append(" left join fetch userAccount.userGroups ug left join fetch ug.roles role2 left join fetch role2.authoritys ");
		sb.append(" left join fetch role.authoritys au");
		sb.append(" left join fetch au.resources ");
		sb.append(" where userAccount.account=:account and userAccount.password = :password ");
   
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("account", userName);
		params.put("password", pwd);
		
		//UserAccount userAccount = userAccountDao.findObjectByHql(sb.toString(),false, params);
		UserAccount userAccount = userAccountDao.findObjectByHql(sb.toString(),false, params);
		if(userAccount!=null){
			userAccountDao.evict(userAccount);
			Set<Role> uaRoles = userAccount.getRoles();
			if(uaRoles!=null){
				Set<Role> roles = new HashSet<Role>(uaRoles);
				Date now = new Date();
	        	for(Role r:uaRoles){
	        		Date st = r.getStartTime();
	        		Date ed = r.getEndTime();
	        		st = (st==null?now:st);
	        		ed = (ed==null?now:ed);
	        		if( now.compareTo(st)<0
	        			|| now.compareTo(ed)>0){
	        			roles.remove(r);
	        		}
	            }
	        	userAccount.setRoles(roles);
	        	
	        }
		}
		
		return userAccount;
	}
	
	
	@Override
	public UserAccount findUserByAccountAndTenantId(String userName,String tenantId) throws Exception{
		//StringBuilder hql = userAccountHqlProvider.findUserByAccount(userName);
		StringBuilder sb = new StringBuilder();
		sb.append("select userAccount from ").append(UserAccount.class.getName()).append(" userAccount");
		sb.append(" left join fetch userAccount.roles role");
		  // sb.append(" left join fetch userAccount.userGroups ug left join fetch ug.roles role2 left join fetch role2.authoritys ");
		sb.append(" left join fetch role.authoritys au");
		sb.append(" left join fetch au.resources ");
		sb.append(" where userAccount.account=:account ");
   
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("account", userName);
		
		
		//HqlTenantIdUtil.handleParamMap4TenantId(params);
		//如果登录用户为admin  则 不加载tenantId
		if(!userName.equalsIgnoreCase(BizConstant.USERACCOUNT_SUPERADMIN)){
			
			sb.append(" and userAccount.tenantId = :tenantId ");
			params.put("tenantId", tenantId);
			//不增加tenantId
		}
		
		//UserAccount userAccount = userAccountDao.findObjectByHql(sb.toString(),false, params);
		UserAccount userAccount = userAccountDao.findObjectByHql(sb.toString(),false, params);
		userAccountDao.evict(userAccount);
		if(userAccount!=null){
			Set<Role> uaRoles = userAccount.getRoles();
			if(uaRoles!=null){
				Set<Role> roles = new HashSet<Role>(uaRoles);
				Date now = new Date();
	        	for(Role r:uaRoles){
	        		Date st = r.getStartTime();
	        		Date ed = r.getEndTime();
	        		st = (st==null?now:st);
	        		ed = (ed==null?now:ed);
	        		if( now.compareTo(st)<0
	        			|| now.compareTo(ed)>0){
	        			roles.remove(r);
	        		}
	            }
	        	userAccount.setRoles(roles);
	        	
	        }
		}
		
		return userAccount;
	}
	
	@Override
	public UserAccount findUserByAccount4ServiceAuth(String userNameEncode,String pwdEncode,String tenantIdEncode)throws Exception{
		Assert.isTrue(StringUtils.isNotEmpty(userNameEncode), "账号不能为空!");
		Assert.isTrue(StringUtils.isNotEmpty(pwdEncode), "密码不能为空!");
		Assert.isTrue(StringUtils.isNotEmpty(tenantIdEncode), "承租户标识不能为空!");
		UserAccount ua = null;
		//解密
		CodeDesUtil des = new CodeDesUtil();
		
		String userAccount =  des.decrypt( userNameEncode);
		//String pwd_enc = des.decrypt( pwdDes);
		String tenantId =  des.decrypt( tenantIdEncode);
		
		ua= findUserByAccountAndTenantId(userAccount, tenantId);
		if(ua==null){
			throw new BizException("账户不存在！");
		}
		
		String pwd = ua.getPassword();
		String pwd_encode = des.encrypt(pwd);
		if(!pwd_encode.equals(pwdEncode)){
			throw new BizException("密码错误！");
		}
		
		return ua;
	}
	
   /* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	UserAccount userAccount = null;
    	
    	try {
			userAccount = findUserByAccount(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
        if (userAccount == null) {
            return null;
        }
        
        //ContextUtil.setSessionAttr("aaa", 111);
        
        Set<Role> roles = userAccount.getRoles();
        //logger.debug("登录用户的角色信息："+roles);
        //System.out.println(roles);
        if(roles!=null){
        	for(Role r:roles){
        		//logger.debug("权限信息："+r.getAuthoritys());
        		 System.out.println("Authority:"+r.getAuthoritys());
        		 Set<Authority> aus = r.getAuthoritys();
        		 if(aus!=null){
        			 for(Authority au:aus){
        				 logger.debug("资源："+au.getResources());
        			 }
        		 }
            }
        }
        //这里不能设定其他信息  不然不能登录
        //加载用户的
        //ContextUtil.setSessionAttr("aaa", 111);
        
        //System.out.println(userAccount.getRoles());
        //Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(userAccount);
        boolean enabled = false;
        if("1".equals(userAccount.getEnable())){
            enabled = true;
        }
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        // 封装成spring security的user
        UserDetails userdetail = new org.springframework.security.core.userdetails.User(userAccount.getAccount(),
                                                                                        userAccount.getPassword(), 
                                                                                        enabled, 
                                                                                        accountNonExpired, 
                                                                                        credentialsNonExpired,
                                                                                        accountNonLocked, 
                                                                                        grantedAuths);
        return userAccount;
        
        UserAccount userAccount = findUserByAccount(account);
        if (userAccount == null) {
            return null;
        }
        User user = userAccount.getUser();
        
        Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(user);
        boolean enabled = false;
        if("1".equals(userAccount.getEnable())){
            enabled = true;
        }
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        // 封装成spring security的user
       
        UserDetails userdetail = new org.springframework.security.core.userdetails.User(userAccount.getAccount(),
                                                                                        userAccount.getPassword(), 
                                                                                        enabled, 
                                                                                        accountNonExpired, 
                                                                                        credentialsNonExpired,
                                                                                        accountNonLocked, 
                                                                                        grantedAuths);
       
        
          return userdetail;
            
        
    }
	*/
    /* */
    private Set<GrantedAuthority> obtionGrantedAuthorities(UserAccount userAccount) {
        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
        if(userAccount.getRoles()!=null){
        	for (Role role : userAccount.getRoles()) {
                Set<Authority> roleAuthority = role.getAuthoritys();
                for (Authority roa : roleAuthority) {
                    if (null != roa && null != roa.getResources() && roa.getResources().size() > 0) {
                        for (com.vix.common.security.entity.Resource rr : roa.getResources()) {
                            //authSet.add(new SimpleGrantedAuthority(rr.getName()));
                        	authSet.add(new SimpleGrantedAuthority(rr.getResourceCode()));
                        }
                    }
                }
            }
        }
        
       /* if(userAccount.getUserGroups()!=null){
        	Set<UserGroup> userGrps = userAccount.getUserGroups();
        	
        	if(userGrps!=null){
        		for(UserGroup ug:userGrps){
        			Set<Role> ugRoles = ug.getRoles();
        			for (Role role : ugRoles) {
                        Set<Authority> roleAuthority = role.getAuthoritys();
                        for (Authority roa : roleAuthority) {
                            if (null != roa && null != roa.getResources() && roa.getResources().size() > 0) {
                                for (com.vix.common.security.entity.Resource rr : roa.getResources()) {
                                    authSet.add(new SimpleGrantedAuthority(rr.getName()));
                                }
                            }
                        }
                    }
        			
        		}
        	}
        	
        }*/
        return authSet;
    }
   
    
    
    
    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {//, String tenantId
    	UserAccount userAccount = null;
    	
    	try {
			//userAccount = findUserByAccountAndTenantId(username,tenantId);
    		userAccount = findUserByAccountAndTenantId(username);
    	} catch (Exception e) {
			e.printStackTrace();
		}
        if (userAccount == null) {
            return null;
        }
        
        //判断承租户是否过期 
        //baseOrganizationDao.findTenantByTenantId(tenantId);
        
        
        
        Date now = new Date();
        Date st = userAccount.getStartTime();
		Date ed = userAccount.getEndTime();
		st = (st==null?now:st);
		ed = (ed==null?now:ed);
		if( now.compareTo(st)<0
    			|| now.compareTo(ed)>0){
			return null;
		}
		
        //ContextUtil.setSessionAttr("aaa", 111);
        
        Set<Role> roles = userAccount.getRoles();
        //logger.debug("登录用户的角色信息："+roles);
        //System.out.println(roles);
        if(roles!=null && !roles.isEmpty()){
        	userAccount.setFtRoleCode(roles.iterator().next().getRoleCode());
        	
        	for(Role r:roles){
        		//logger.debug("权限信息："+r.getAuthoritys());
        		 //System.out.println("Authority:"+r.getAuthoritys());
        		Set<Authority> aus = r.getAuthoritys();
        		if(aus!=null){
        			for(Authority au:aus){
        				logger.debug("资源："+au.getResources());
        			}
        		}
           }
        }
        return userAccount;
        
    }
    
    
    @Override
    public UserAccount findUserByUsernameAndPwd(String username,String password)throws UsernameNotFoundException {//, String tenantId
    	UserAccount userAccount = null;
    	
    	try {
    		userAccount = findUserByAccountPwdAndTenantId(username,password);
    	} catch (Exception e) {
			e.printStackTrace();
		}
        if (userAccount == null) {
            return null;
        }
        Date now = new Date();
        Date st = userAccount.getStartTime();
		Date ed = userAccount.getEndTime();
		st = (st==null?now:st);
		ed = (ed==null?now:ed);
		if( now.compareTo(st)<0
    			|| now.compareTo(ed)>0){
			return null;
		}
		
        //ContextUtil.setSessionAttr("aaa", 111);
        
        Set<Role> roles = userAccount.getRoles();
        //logger.debug("登录用户的角色信息："+roles);
        //System.out.println(roles);
        if(roles!=null){
        	for(Role r:roles){
        		//logger.debug("权限信息："+r.getAuthoritys());
        		 //System.out.println("Authority:"+r.getAuthoritys());
        		Set<Authority> aus = r.getAuthoritys();
        		if(aus!=null){
        			for(Authority au:aus){
        				logger.debug("资源："+au.getResources());
        			}
        		}
           }
        }
        return userAccount;
        
    }
    

    //@Resource(name="employeeHrHqlProvider")
	//private EmployeeHrHqlProvider employeeHrHqlProvider;
    
    /*
     * <p>Title: findUserAccountPager</p>
     * <p>Description: </p>
     * @param pager
     * @param params
     * @return
     * @throws Exception
     * @see com.vix.common.security.service.IUserAccountService#findUserAccountPager(com.vix.core.web.Pager, java.util.Map)
     */
    @Override
	public Pager findUserAccountPager(Pager pager,Map<String,Object> params) throws Exception{
        StringBuilder hql = userAccountHqlProvider.findUserList(params, pager);
        /** 基础hql封装  示例
        String h = this.findUserListHql();
        */
        //pager = userAccountDao.findPagerByHql(pager,userAccountHqlProvider.entityAsName(), hql.toString(), params);
        pager = userAccountDao.findPager2ByHql(pager, userAccountHqlProvider.entityAsName(), hql.toString(), params);
        //System.out.println(SecurityUtil.getCurrentUserName());
        //Map<String,Object> params2 = new HashMap<String, Object>();
        //System.out.println("beforeParams2:"+params2);
        //StringBuilder testHql = roleHqlProvider.findSelectRoleForUser(0L, params2, "id", "desc");
        //System.out.println("afterParams2__:"+params2);
        //System.out.println("AFTER:"+testHql);
        
        //测试
       /* Map<String,Object> params2 = new HashMap<String, Object>();
        params2.put("orgUnitId", 1L);
		StringBuilder hql2 = employeeHrHqlProvider.testEmp(params2);*/
		
		/*hql2.setLength(0);
		hql2.append("select employee from Employee employee inner join employee.organizationUnit orgUnit  where orgUnit.id=:orgUnitId and (  employee.name =  'test'  and employee.id =  2 and :_SysUserAccount_ in (  6,4 ) ) ");
		params2.clear();
		 params2.put("orgUnitId", 1L);*/
		// and (  employee.name =  'test'  and employee.id =  2 and :_SysUserAccount_in (  6,4 )  ) 
      
		
		/*List<Employee> empList   = userAccountDao.findAllByHql2(hql2.toString(), params2);
        System.out.println(empList);
        System.out.println(hql2);
        System.out.println(params2);
  */      
        return pager;
    }

	@Override
	public UserAccount saveOrUpdate(String addRoleIds, String deleteRoleIds,UserAccount userAccount) throws Exception {
		//return userAccountDao.saveOrUpdate(null, "6", 10L, "sys", "xin", "1", 9L, "1");
		UserAccount uaTmp = null;
		Boolean isEdit = false;
		if(StrUtils.isNotEmpty(userAccount.getId())){
			uaTmp = userAccountDao.findEntityById(UserAccount.class, userAccount.getId());
			isEdit = true;
		}
		Date now = new Date();
		if(uaTmp == null){
			uaTmp = new UserAccount();
		}
		
		//验证
		Boolean hasUa = findHasUserAccount(isEdit, uaTmp.getId(), userAccount.getAccount(), SecurityUtil.getCurrentUserTenantId());
		if(hasUa){
			throw new BizException("账号重复，请重新填写！");
		}
		if (userAccountDao.synCheckUserAccountIsRepeatErp2Oc(userAccount)) {
			throw new BizException("账号在OC平台已存在！");
		}
		
		
		BeanUtils.copyProperties(userAccount, uaTmp, new String[]{"password","roles"});//"employee",
		
		if(!isEdit){
			Md5Encoder md5 = new Md5EncoderImpl();
			uaTmp.setPassword(md5.encodeString(uaTmp.getAccount()));//默认密码
		}
		
		if(uaTmp.getId()==null){
			uaTmp.setCreateTime(now);
			uaTmp.setAccountBizType(BizConstant.COMMON_SECURITY_ACCOUNT_BizType_Sys);
		}else{
			uaTmp.setUpdateTime(now);
		}
		
		
		Set<Role> rSet=uaTmp.getRoles();
		if( rSet== null){
			rSet= new HashSet<Role>();
		}
		//userAccountDao.evict(uaTmp);
		
		if(StringUtils.isNotEmpty(addRoleIds)){
			for(String idS : addRoleIds.split(",")){
				if(null != idS && !"".equals(idS)){
					Role r = userAccountDao.findEntityById(Role.class, idS);
					rSet.add(r);
				}
			}
		}
		if(StringUtils.isNotEmpty(deleteRoleIds)){
			for(String idS : deleteRoleIds.split(",")){
				if(null != idS && !"".equals(idS)){
					Role r = userAccountDao.findEntityById(Role.class, idS);
					rSet.remove(r);
				}
			}
		}
		
		uaTmp.setRoles(rSet);
		userAccountDao.merge(uaTmp);
		
		userAccountDao.synUserAccountErp2Oc(!isEdit,userAccount);
		return userAccount;
	}
	
	/* 
	 * @see com.vix.common.security.service.IUserAccountService#findHasUserAccount(java.lang.Boolean, java.lang.Long, java.lang.String, java.lang.String)
	 */ 
	@Override
	public Boolean findHasUserAccount(Boolean isEdit,String userAccountId,String account,String tenantId) throws Exception{
		Assert.isTrue(StringUtils.isNotEmpty(account), "账号不能为空!");
		Boolean hasUserAccount = false;
		StringBuilder hql = new StringBuilder();
		Map<String,Object> param = new HashMap<String,Object>();
		
		hql.append("select count(ua.id) from ").append(UserAccount.class.getName());
		hql.append(" ua where ua.account = :account ");
		param.put("account", account);
		
		/*
		hql.append(" and ua.tenantId = :tenantId ");
		param.put("tenantId", tenantId);
		*/
		if(isEdit){
			hql.append(" and ua.id != :userAccountId ");
			param.put("userAccountId", userAccountId);
		}
		 
		Long l = userAccountDao.findDataCountByHqlOrginial(hql, "ua", param);
		
		if(l>0){
			hasUserAccount = true;
		}
		return hasUserAccount;
	}
	
	/* (non-Javadoc)
	 * @see com.vix.common.security.service.IUserAccountService#findUserAccountByTicket(java.lang.String)
	 */
	@Override
	public UserAccount findUserAccountByTicket(String ticket)throws Exception{
		UserAccount userAccount = null;
		if(StrUtils.isEmpty(ticket)){
			return userAccount;
		}
		
		//String[] ticketArray = CodeUtil.decodeTicket(ticket);
		String[] ticketArray = CodeDesUtil.decodeTicket(ticket);
		
		String tenantId = ticketArray[0];
		String userName =  ticketArray[1];
			
		userAccount = findUserByAccountAndTenantId(userName, tenantId);
		return userAccount;
	}
	
	@Override
	public Boolean findIsSuperAdmin(String userAccountId)throws Exception{
		UserAccount userAccount = userAccountDao.findEntityById(UserAccount.class, userAccountId); 
		if(userAccount==null){
			return false;
		}
		Set<Role> roles = userAccount.getRoles();
		//logger.debug("登录用户的角色信息："+roles);
		//System.out.println(roles);
		if(roles!=null){
			for(Role role:roles){
				//logger.debug("权限信息："+r.getAuthoritys());
				String roleCode = role.getRoleCode();
				if(roleCode.equals(BizConstant.ROLE_SUPERADMIN) || roleCode.contains(BizConstant.ROLE_COMP_PRE_SUPERADMIN)){
					return true;
				}
			}
		}
		return false;
	}


	@Override
	public Boolean update4ActiveAccount(String encodeAccount,String encodeActiveToken)throws Exception{
		CodeDesUtil des = new CodeDesUtil();
		if(StringUtils.isEmpty(encodeAccount) || StringUtils.isEmpty(encodeActiveToken)){
			throw new BizException("待激活的账户不存在或已经激活！");
		}
		String account = des.decrypt(encodeAccount);
		String activeToken = des.decrypt(encodeActiveToken);
		
		UserAccount uaTmp = findUserByAccountAndTenantId(account);
		UserAccount userAccount = findEntityById(UserAccount.class, uaTmp.getId());
		if(userAccount!=null){
			//存在账户
			String enable = userAccount.getEnable();
			if(StringUtils.isNotEmpty(enable) && enable.equals("1")){
				throw new BizException("账户已经激活！");
			}
			Long activeTokenTime = NumberUtil.convertoLong(activeToken);
			if(activeTokenTime==null){
				throw new BizException("您的激活连接已过期或账号已经激活！");
			}
			Long nowTime = new Date().getTime();
			if(activeTokenTime<nowTime){
				throw new BizException("您的激活连接已过期！");
			}
			
			//激活个人信息
			userAccount.setEnable("1");
			userAccount.setActiveEndTime(null);
			userAccountDao.saveOrUpdateOriginal(userAccount);
			return true;
		}
		return false;
	}
	
	@Override
	public Boolean update4RestPwd(String id,String encodePassword)throws Exception{
		UserAccount userAccount = findEntityById(UserAccount.class, id);
		userAccount.setPassword(encodePassword);
		mergeOriginal(userAccount);
		
		Boolean isSynToOc = userAccountDao.synUserAccountErp2Oc(false,userAccount);
		return isSynToOc;
	}
	
	@Override
	public Date findLastUpdateAccountTime(String account)throws Exception{
		Date date = null;
		if(StrUtils.isNotEmpty(account)){
			UserAccount ua = findUserByAccount(account);
			if(ua!=null){
				date = ua.getUpdateTime();
				
				Role role = roleDao.findLastRoleByAccount(ua.getId());
				if(null != role){
					Date roleDate = role.getUpdateTime();
					if(roleDate.after(date)){
						date = roleDate;
					}
				}
			}
		}
		return date;
	}


	@Override
	public UserAccount saveUserAccount(UserAccount userInfo) throws Exception {
		UserAccount entity = null;
		if(userInfo.getId() != null && !"".equals(userInfo.getId())){
			entity = userAccountDao.findEntityById(UserAccount.class, userInfo.getId());
		}
		if(entity == null){
			entity = new UserAccount();
		}
		Date now = new Date();
		if(entity.getId() == null){
			entity.setCreateTime(now);
		}
		entity.setUpdateTime(now);
		
		entity.setWxAccount(userInfo.getWxAccount());
		if(!userInfo.getMobile().contains("*")){
			entity.setMobile(userInfo.getMobile());
		}
		entity.setEmail(userInfo.getEmail());
		entity.setQqNumber(userInfo.getQqNumber());
		userAccountDao.saveOrUpdateOriginal(entity);
		return entity;
	}
}
