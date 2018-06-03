package com.rest.core.auth.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.service.IUserAccountService;
import com.vix.core.exception.BizException;

/**
 * @ClassName: RestAuthHandlerInterceptorAdapter
 * @Description: MVC REST SERVICE 安全认证拦截器
 * @author wangmingchen
 * @date 2015年2月23日 下午3:46:45
 */
@Deprecated
public class RestAuthHandlerInterceptorAdapter extends
		HandlerInterceptorAdapter{
	private static Logger logger = LoggerFactory.getLogger(RestAuthHandlerInterceptorAdapter.class);
	
	@Resource(name="userAccountService")
    private IUserAccountService userAccountService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String userNameEncode = request.getParameter("userNameEnc");
		String pwdEncode = request.getParameter("pwdEnc");//authParam.getPwdEnc();
		String tenantIdEncode = request.getParameter("tenantIdEnc");//authParam.getTenantIdEnc();
		
		Boolean isAuthSuccess = false;
		UserAccount ua;
		try {
			ua = userAccountService.findUserByAccount4ServiceAuth(userNameEncode, pwdEncode, tenantIdEncode);
			if(ua!=null){
				isAuthSuccess = true;
				if(logger.isDebugEnabled()){
					logger.debug("认证成功! 账号:{},承租户标识:{}", ua.getAccount(),ua.getTenantId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			isAuthSuccess = false;
			throw new BizException(e.getMessage());
		}
		
		return isAuthSuccess;
	}
}
