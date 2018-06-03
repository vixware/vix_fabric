package com.vix.common.security.util.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.util.StringUtils;

import com.vix.common.log.service.ILogService;
import com.vix.common.security.entity.DataResRowPolicyObj;
import com.vix.common.security.entity.IndexPageDataConfig;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.service.IAuthorityService;
import com.vix.common.security.service.IResourceService;
import com.vix.common.security.service.IRoleService;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.security.vo.ResourceVO;
import com.vix.common.securityDca.service.IDataColSecService;
import com.vix.common.securityDra.service.IDataResRowPolicyObjService;
import com.vix.core.constant.SecurityConfigConstant;
import com.vix.core.constant.SecurityScope;
import com.vix.core.utils.CodeUtil;
import com.vix.core.utils.ContextUtil;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.code.CodeDesUtil;

/**
 * An authentication success strategy which can make use of the {@link DefaultSavedRequest} which may have been stored in
 * the session by the {@link ExceptionTranslationFilter}. When such a request is intercepted and requires authentication,
 * the request data is stored to record the original destination before the authentication process commenced, and to
 * allow the request to be reconstructed when a redirect to the same URL occurs. This class is responsible for
 * performing the redirect to the original URL if appropriate.
 * <p>
 * Following a successful authentication, it decides on the redirect destination, based on the following scenarios:
 * <ul>
 * <li>
 * If the {@code alwaysUseDefaultTargetUrl} property is set to true, the {@code defaultTargetUrl}
 * will be used for the destination. Any {@code DefaultSavedRequest} stored in the session will be
 * removed.
 * </li>
 * <li>
 * If the {@code targetUrlParameter} has been set on the request, the value will be used as the destination.
 * Any {@code DefaultSavedRequest} will again be removed.
 * </li>
 * <li>
 * If a {@link SavedRequest} is found in the {@code RequestCache} (as set by the {@link ExceptionTranslationFilter} to
 * record the original destination before the authentication process commenced), a redirect will be performed to the
 * Url of that original destination. The {@code SavedRequest} object will remain cached and be picked up
 * when the redirected request is received
 * (See {@link org.springframework.security.web.savedrequest.SavedRequestAwareWrapper SavedRequestAwareWrapper}).
 * </li>
 * <li>
 * If no {@code SavedRequest} is found, it will delegate to the base class.
 * </li>
 * </ul>
 *
 * @author Luke Taylor
 * @since 3.0
 */
public class VixAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    protected final Log logger = LogFactory.getLog(this.getClass());

    private RequestCache requestCache = new HttpSessionRequestCache();
    
    private IDataResRowPolicyObjService dataResRowPolicyObjService;
    
    private ILogService logService;
    
    private IResourceService resourceService;
    
    private IAuthorityService authorityService;
    
    private IDataColSecService dataColSecService;
    
    private IRoleService roleService;
    
    //用户权限的存储  用于url的验证
    private void handlerUserResourceAuth(HttpServletRequest request,String userAccountId,String tenantId){
    	Map<RequestMatcher, Collection<ConfigAttribute>> userResMap = new ConcurrentHashMap<RequestMatcher, Collection<ConfigAttribute>>();
    	
    	List<ResourceVO> resList = resourceService.findAllResourceByUserAccountId(userAccountId,tenantId);
    	if(resList!=null && !resList.isEmpty()){
    		for(ResourceVO rv:resList){
    			Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
    			
    			ConfigAttribute configAttribute = null;
    			if(StringUtils.hasText(rv.getAuthorityCode())){
    				configAttribute = new SecurityConfig(rv.getAuthorityCode());
    			}else{
    				configAttribute = new SecurityConfig(SecurityConfigConstant.SPRING_SECURITY_NO_AUTH_CONST);
    			}
    			configAttributes.add(configAttribute);
    			userResMap.put(new AntPathRequestMatcher(rv.getUrl()), configAttributes);
    		}
    	}
    	
    	request.getSession().setAttribute(SecurityScope.USERINFO_AUTH_RES, userResMap);
    }
    
    private void handlerUserFunctionAuth(HttpServletRequest request,String userAccountId,String bizType,String tenantId){
    	Set<String> authCodeSet = authorityService.findAuthFunCodeSetByUserAccount(userAccountId, bizType, tenantId);
    	request.getSession().setAttribute(SecurityScope.USERINFO_AUTH_FUN, authCodeSet);
    }
    
    //处理帐号的列元数据权限
    private void handlerUserSecCol(HttpServletRequest request,String userAccountId,String tenantId){
    	Map<String,Map<String,Set<String>>> userAccountMetaConfigMap = dataColSecService.findColMetaInfoByUserAccount(userAccountId);
    	request.getSession().setAttribute(SecurityScope.USERINFO_AUTH_COLMETA, userAccountMetaConfigMap);
    }
    
    //处理帐号首页面的div显示 20150427
    private void handlerUserIndexPageDiv(HttpServletRequest request,String userAccountId,String tenantId){
    	List<IndexPageDataConfig> pdcList = null;
		try {
			pdcList = roleService.findPdcByUserAccount(userAccountId);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	request.getSession().setAttribute(SecurityScope.USERINFO_INDEX_PDC, pdcList);
    }
    
    public void handlAuthenticationData(HttpServletRequest request, HttpServletResponse response,
    		 Authentication authentication){
    	String bizType = ContextUtil.getUserMenuContextType(request);
        
        //保存登陆人的 hql拦截方法 到session中
        //ContextUtil.setSessionAttr("VVV", 111);
        //request.getSession().setAttribute("VVV", 111);
        Object userObj = authentication.getPrincipal();
        if(userObj!=null && userObj instanceof UserAccount){
        	 UserAccount u = (UserAccount)userObj;
        	 
        	 //认证成功日志
             // 登录成功 
             logService.saveOrUpdateLoginLog(u.getAccount(), StrUtils.getRemoteIpAddress(request), u.getTenantId(), u.getCompanyInnerCode(), u.getEmployee(),true, null);
             
        	 UserAccount user =  SecurityUtil.getCurrentUserAccount();
        	 request.getSession().setAttribute(SecurityScope.USER_LOGIN_SUCCESS_USERINFO, user);
			 
        	 //存储URL权限
        	 handlerUserResourceAuth(request, user.getId(),u.getTenantId());
        	 //存储功能点权限
        	 handlerUserFunctionAuth(request, user.getId(), bizType, u.getTenantId());
        	 //存储登陆后的列权限元数据配置
        	 handlerUserSecCol(request, user.getId(), u.getTenantId());
        	 //登陆后首页面的配置项
        	 handlerUserIndexPageDiv(request, user.getId(), u.getTenantId());
        	 
        	 //Map<String,DataResRowPolicyObj> policyObjMap=null;//key hql类方法全称
        	 Map<String,DataResRowPolicyObj> policyObjMetadataNameMap=null;//key 为元数据对象全称
        	 try {
				//policyObjMap = dataResRowPolicyObjService.findPolicyObjMetadataMapByUserId(u.getId());
        		 policyObjMetadataNameMap = new HashMap<String, DataResRowPolicyObj>();
        		 request.getSession().setAttribute(SecurityScope.HQL_USER_OBJ, policyObjMetadataNameMap);
				
				
        		 policyObjMetadataNameMap =  dataResRowPolicyObjService.findPolicyObjMetadataStrMapByUserId(u.getId());
        		 request.getSession().setAttribute(SecurityScope.METADATA_USER_OBJ, policyObjMetadataNameMap);
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        
        handlAuthenticationData(request, response, authentication);
        
        //System.out.println(request.getParameter("tenantId"));
        String service = request.getParameter("service");
        if(StrUtils.isNotEmpty(service)){
        	UserAccount ua = SecurityUtil.getCurrentUserAccount();
			String tenantId = ua.getTenantId();
			
			//String ticket = ua.getAccount()+"_"+tenantId;//暂时先测试  后面在整理规则 并加密 
			String ticket = CodeDesUtil.encodeTicket(ua.getAccount(), tenantId);
			logger.info("登录信息："+ ua.getAccount() + ",tenantId:" + tenantId+",单点登录验证，ticket：" + ticket);
			
			service = CodeUtil.appendParam4URL(service, "ticket", ticket, false);
        	
			//setDefaultTargetUrl(service);//重定向过去
			handleForVix(request, response, authentication, service);
			return;
        }
        
        if (savedRequest == null) {
            super.onAuthenticationSuccess(request, response, authentication);

            return;
        }
        String targetUrlParameter = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
            requestCache.removeRequest(request, response);
            super.onAuthenticationSuccess(request, response, authentication);

            return;
        }

        clearAuthenticationAttributes(request);
        
        
        // Use the DefaultSavedRequest URL
        String targetUrl = savedRequest.getRedirectUrl();
        logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
    
    /**
     * vix的重定向扩展
     * @param request
     * @param response
     * @param authentication
     * @param targetUrl
     * @throws IOException
     * @throws ServletException
     */
    protected void handleForVix(HttpServletRequest request, HttpServletResponse response, Authentication authentication,String targetUrl) throws IOException, ServletException {
		//String targetUrl = determineTargetUrl(request, response);
		
		if (response.isCommitted()) {
		    logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
		    return;
		}
		
		getRedirectStrategy().sendRedirect(request, response, targetUrl);
		
		clearAuthenticationAttributes(request);
	}

    public void setRequestCache(RequestCache requestCache) {
        this.requestCache = requestCache;
    }

	public IDataResRowPolicyObjService getDataResRowPolicyObjService() {
		return dataResRowPolicyObjService;
	}

	public void setDataResRowPolicyObjService(
			IDataResRowPolicyObjService dataResRowPolicyObjService) {
		this.dataResRowPolicyObjService = dataResRowPolicyObjService;
	}

	public void setLogService(ILogService logService) {
		this.logService = logService;
	}

	public void setResourceService(IResourceService resourceService) {
		this.resourceService = resourceService;
	}

	public void setAuthorityService(IAuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public void setDataColSecService(IDataColSecService dataColSecService) {
		this.dataColSecService = dataColSecService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
}
