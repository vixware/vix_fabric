package com.vix.common.security.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.vix.core.constant.BizConstant;
import com.vix.core.constant.SecurityConfigConstant;

public class VixAccessDecisionManager implements AccessDecisionManager {
	
	@Override
	public void decide( Authentication authentication, Object object,Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException{
		Authentication aa = SecurityUtil.getAuthentication();
		
		Set<ConfigAttribute> caSet = SecurityUtil.getUserAccountRoles();
		for (ConfigAttribute attribute : caSet) {
			if( attribute.getAttribute().equals(BizConstant.ROLE_SUPERADMIN) ){
				//System.out.println("MyAccessDecisionManager:1,return");
            	return ;
           }
		}
		
		if( configAttributes == null ) {
			//System.out.println("MyAccessDecisionManager:2 null,return");
			return ;
		}
		
		if(configAttributes.size()==1){
			for(ConfigAttribute ca:configAttributes){
				String needAuthority = ((SecurityConfig)ca).getAttribute();
				if(needAuthority.equals(SecurityConfigConstant.SPRING_SECURITY_NO_AUTH_CONST)){
					 throw new AccessDeniedException("没有授权，禁止访问!");
				}
			}
		}
		
		
		Iterator<ConfigAttribute> ite = configAttributes.iterator();
		while( ite.hasNext()){
			ConfigAttribute ca = ite.next();
			//String needRole = ((SecurityConfig)ca).getAttribute();
			String needAuthority = ((SecurityConfig)ca).getAttribute();
			//ga 为用户所被赋予的权限。 needRole 为访问相应的资源应该具有的权限。
			for( GrantedAuthority ga: authentication.getAuthorities()){
				if(needAuthority.trim().equals(ga.getAuthority().trim())){
					//System.out.println("MyAccessDecisionManager:3,no");
					return;
				}
			}
	   }
	   //System.out.println("MyAccessDecisionManager:4,throws ");
	   throw new AccessDeniedException("");
	}
	
	@Override
	public boolean supports( ConfigAttribute attribute ){
	   return true;
	}
	  
	@Override
	public boolean supports(Class<?> clazz){
		return true;
	}
}
