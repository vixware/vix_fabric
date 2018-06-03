package com.vix.common.security.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.RequestMatcher;

import com.vix.common.security.entity.Authority;
import com.vix.common.security.entity.Resource;
import com.vix.common.security.service.IAuthorityService;
import com.vix.common.security.service.IResourceService;
import com.vix.common.securityDra.service.IDataResRowMethodConfigService;
import com.vix.core.constant.SecurityScope;

//加载资源与权限的对应关系
public class VixSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	private static final Logger log = Logger.getLogger(VixSecurityMetadataSource.class);
	
	public VixSecurityMetadataSource(){
		//loadResourceDefine();
	}
	
	//由spring调用
	/*public MySecurityMetadataSource() {
		this.resourceService = resourceService;
		loadResourceDefine();
	}*/
	
	//@Autowired
	//@javax.annotation.Resource(name="resourceService")
	private IResourceService resourceService;
	
	private IAuthorityService authorityService;
	
	private IDataResRowMethodConfigService dataResRowMethodConfigService;
	  
    /**
     * 功能点的map
     */
    //private static Map<String,Collection<ConfigAttribute>> functionMap = null;

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		
		return null;
		/*
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
		//注意初始化  为空则说明系统刚启动  严禁放到构造函数中 会造成生命周期问题不能注入
		if(ApplicationSecurityCode.resourceMap==null || ApplicationSecurityCode.permissionCodeMap==null){
			loadResourceDefine();
		}
		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : ApplicationSecurityCode.resourceMap .entrySet()) {
            allAttributes.addAll(entry.getValue());
        }
        return allAttributes;
		 */
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	//加载所有资源与权限的关系
	@Deprecated
	private void loadResourceDefine() {
		//if(ApplicationSecurityCode.resourceMap == null) {
			try{
				log.info("开始初始化VIX平台菜单权限.............");
				//先加载所有的权限信息
				//ApplicationSecurityCode.resourceMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
				//ApplicationSecurityCode.resourceMap = new ConcurrentHashMap<RequestMatcher, Collection<ConfigAttribute>>();
				
				List<Resource> resource = resourceService.findAllResource();
				for (Resource res : resource) {
					Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
					//以权限名封装为Spring的security Object
					/*ConfigAttribute configAttribute = new SecurityConfig(res.getResourceCode());
					configAttributes.add(configAttribute);
					*/
					Authority au = res.getAuthority();
					if(au!=null){
						ConfigAttribute configAttribute = new SecurityConfig(au.getAuthorityCode());
						configAttributes.add(configAttribute);
						//ApplicationSecurityCode.resourceMap.put(new AntPathRequestMatcher(res.getUrl()), configAttributes);
					}
					
				}
				
				/*//把超级管理员纳入系统所有链接的访问权限   没必要
				Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
				ConfigAttribute ca = new SecurityConfig(BizConstant.ROLE_SUPERADMIN);
				atts.add(ca);
				resourceMap.put(new AntPathRequestMatcher("/**"), atts);*/
				if(log.isDebugEnabled()){
					//log.debug("RESOURCEMAP:"+ApplicationSecurityCode.resourceMap);
				}
				log.info("完成VIX平台菜单权限加载!");
				//System.out.println("RESOURCEMAP:"+resourceMap);
			}catch(Exception ex){
				log.error("平台菜单权限加载失败！");
				ex.printStackTrace();
			}
		//}
		
		//if(ApplicationSecurityCode.permissionCodeMap == null) {
			//再加载功能点信息 
			try {
				log.info("开始初始化VIX平台功能点权限.............");
				//ApplicationSecurityCode.permissionCodeMap = findAuthorityFunctionCodeMap();
				if(log.isDebugEnabled()){
					//log.debug("functionMap:"+ApplicationSecurityCode.permissionCodeMap);
				}
				log.info("完成VIX平台功能点权限信息加载!");
			} catch (Exception e) {
				log.error("平台功能点权限加载失败！");
				e.printStackTrace();
			}
		//}
		
		
		//加载hql拦截信息
		/*if(ApplicationSecurityCode.hqlMethodMetadataMap == null) {
			//再加载功能点信息 
			try {
				log.info("开始初始化VIX数据（行）权限.............");
				ApplicationSecurityCode.hqlMethodMetadataMap = dataResRowMethodConfigService.findDataResRowMethodMap();
				if(log.isDebugEnabled()){
					log.debug("hqlMethodMetadataMap:"+ApplicationSecurityCode.hqlMethodMetadataMap);
				}
				log.info("完成VIX平台数据（行）权限信息加载!");
			} catch (Exception e) {
				log.error("平台数据（行）权限失败！");
				e.printStackTrace();
			}
		}*/
		
	}
	
	//返回所请求资源所需要的权限
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		/*
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		System.out.println("requestUrl is " + requestUrl);
		
		loadResourceDefine();
		return resourceMap.get(requestUrl);
		*/
		//System.out.println("验证资源！");
		
		FilterInvocation fi = (FilterInvocation) object;
		HttpServletRequest request = fi.getRequest();
		Map<RequestMatcher, Collection<ConfigAttribute>> caMap = getVixLoginUserResCABySession(request);
		
		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : caMap.entrySet()) {
			if (entry.getKey().matches(request)) {
				return entry.getValue();
			}
		}
		return null;
	        
		/*
		loadResourceDefine();
		HttpServletRequest request = ((FilterInvocation) object).getRequest();
        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : ApplicationSecurityCode.resourceMap .entrySet()) {
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }
        return null;
		 */
	
	}
	private Map<RequestMatcher, Collection<ConfigAttribute>> getVixLoginUserResCABySession(HttpServletRequest request){
		Map<RequestMatcher, Collection<ConfigAttribute>> caMap = new ConcurrentHashMap<RequestMatcher, Collection<ConfigAttribute>>();
		
		HttpSession si = request.getSession();
		if(si!=null){
			Object caObj = si.getAttribute(SecurityScope.USERINFO_AUTH_RES);
			if(caObj!=null){
				caMap = (Map<RequestMatcher, Collection<ConfigAttribute>>) caObj;
			}
		}
		return caMap;
	}
	/*
	private Map<String,Collection<ConfigAttribute>> findAuthorityFunctionCodeMap() throws Exception{
		Map<String,Collection<ConfigAttribute>> functionMap = new LinkedHashMap<String,Collection<ConfigAttribute>>();
		
		//List<Authority> authorityList = authorityService.findAllAuthorityByType(BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_F);
		
		//List<Authority> authorityList = authorityService.findAllAuthority();
		List<Authority> authorityList = authorityService.findAllAuthorityByType(BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_F);
		
		//Map<String,Set<Role>> authorityRoleMap=new LinkedHashMap<String,Set<Role>>();
		Map<String,Set<Role>> authorityRoleMap=new ConcurrentHashMap<String, Set<Role>>();
		
		Set<String> allAuthorityCodeSet=new ConcurrentSkipListSet<String>();
		for(Authority authority:authorityList){
			allAuthorityCodeSet.add(authority.getAuthorityCode());
				
			if(authorityRoleMap.containsKey(authority.getAuthorityCode())){
				Set<Role> roleSetTemp = authorityRoleMap.get(authority.getAuthorityCode());
				roleSetTemp.addAll(authority.getRoles());
				
				authorityRoleMap.put(authority.getAuthorityCode(), roleSetTemp);
			}else{
				Set<Role> noHasRoleTmp = authority.getRoles();
				if(noHasRoleTmp!=null && !noHasRoleTmp.isEmpty()){
					//Set<Role> roleSetTemp = Sets.newHashSet(noHasRoleTmp);
					authorityRoleMap.put(authority.getAuthorityCode(), new HashSet<Role>(noHasRoleTmp));
				}else{
					authorityRoleMap.put(authority.getAuthorityCode(), new HashSet<Role>());
				}
			}
		}
		
		Set<String> hasAdminSet = new HashSet<String>();
		
		for(String code:allAuthorityCodeSet){
			if(authorityRoleMap.containsKey(code)){
				Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
				
				for(Role role:authorityRoleMap.get(code)){
					if(role!=null){
						hasAdminSet.add(role.getRoleCode());
						ConfigAttribute ca = new SecurityConfig(role.getRoleCode());
						atts.add(ca);
					}else{
						ConfigAttribute ca = new SecurityConfig(BizConstant.ROLE_SUPERADMIN);
						atts.add(ca);
					}
				}
				functionMap.put(code, atts);
			}else{
				Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
				ConfigAttribute ca = new SecurityConfig(BizConstant.ROLE_SUPERADMIN);
				atts.add(ca);
				functionMap.put(code, atts);
			}
		}
		return functionMap;
	}
	*/
	public IResourceService getResourceService() {
        return resourceService;
    }

    public void setResourceService(IResourceService resourceService) {
        this.resourceService = resourceService;
    }
    
    public IAuthorityService getAuthorityService() {
		return authorityService;
	}

	public void setAuthorityService(IAuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public IDataResRowMethodConfigService getDataResRowMethodConfigService() {
		return dataResRowMethodConfigService;
	}

	public void setDataResRowMethodConfigService(
			IDataResRowMethodConfigService dataResRowMethodConfigService) {
		this.dataResRowMethodConfigService = dataResRowMethodConfigService;
	}

}
