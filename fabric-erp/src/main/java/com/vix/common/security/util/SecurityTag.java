package com.vix.common.security.util;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.vix.core.constant.SecurityScope;
import com.vix.core.utils.ContextUtil;

/**
 * 用户的按钮功能点权限
 * @author Administrator
 *
 */
public class SecurityTag extends TagSupport{
	
	private static final long serialVersionUID = 6164670808401095759L;

	private String functionCode;

	//public static Map<String, Collection<ConfigAttribute>> permissionCodeMap=null;
	
	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	@Override
	public int doStartTag() throws JspException {
		//ServletActionContext.getServletContext().setAttribute("permissionCodeMap", permissionCodeMap);
		//if(permissionCodeMap==null){
			/////permissionCodeMap =	ApplicationSecurityCode.permissionCodeMap;
		//}
		if(SecurityUtil.isSuperAdmin()){
			return EVAL_BODY_INCLUDE;
		}
		
		
		Set<String> funSet = new HashSet<String>();
		Object funObjSet = ContextUtil.getSessionAttr(SecurityScope.USERINFO_AUTH_FUN);
		if(funObjSet!=null){
			funSet = (Set<String>) funObjSet;
		}	
		//System.out.println(StringUtils.join(permissionCodeMap.keySet(),","));
		//return SKIP_BODY;
		//取得用户的认证信息
		//Collection<GrantedAuthority> userAuthorities = SecurityUtil.getCurrentUserAccount().getUserAuthorities();
		if (funSet.contains(functionCode)) {
			return EVAL_BODY_INCLUDE;
			
		}		
		return SKIP_BODY;
	}
	
	
	/**
	 if (permissionCodeMap.containsKey(functionCode)) {
			Iterator<ConfigAttribute> ite = permissionCodeMap.get(functionCode).iterator();
			if(ite.hasNext()){
				while (ite.hasNext()) {
					ConfigAttribute ca = ite.next();
					String needRole = ((SecurityConfig) ca).getAttribute();
					
					for (GrantedAuthority ga : userAuthorities) {
						if (ga.getAuthority().equals(BizConstant.ROLE_SUPERADMIN)) {
							//return EVAL_PAGE;
							return EVAL_BODY_INCLUDE;
						}
						if (needRole.equals(ga.getAuthority())) {
							return EVAL_BODY_INCLUDE;
						}
					}
				}
			}else{
				for (GrantedAuthority ga : userAuthorities) {
					if (ga.getAuthority().equals(BizConstant.ROLE_SUPERADMIN)) {
						return EVAL_BODY_INCLUDE;
					}
				}
			}
			
		}else{
			//超级管理员的话直接显示
			for (GrantedAuthority ga : userAuthorities) {
				if (ga.getAuthority().equals(BizConstant.ROLE_SUPERADMIN)) {
					return EVAL_BODY_INCLUDE;
				}
			}
		}
		
		return SKIP_BODY;
	}
	 */
}
