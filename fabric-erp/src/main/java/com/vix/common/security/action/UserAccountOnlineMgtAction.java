package com.vix.common.security.action;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.VixSecAction;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.utils.StrUtils;

@Controller
@Scope("prototype")
public class UserAccountOnlineMgtAction extends VixSecAction{

	private static final long serialVersionUID = 1L;
	
	@Resource(name = "sessionRegistry")
	private SessionRegistry sessionRegistry; 
	
	
	private List<Object> userAccountOnlineList;
	
	private String account;
	
	private String sessionId;
	
	
	/** 获取列表数据  */
	public String goSingleList(){
		try {
			
			/*String filePath =  (String) CustomizedPropertyPlaceholderConfigurer.getContextProperty("file_basePath");
			System.out.println(filePath);*/
			
			//List<Authority> authorityList = authorityService.findAllAuthorityByType(BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_F);
			//System.out.println(authorityList.size());
			
			
			//Pager pager = roleService.findRolePager(getPager(), params);
			//setPager(pager);
			
			/* userAccountOnlineList = new LinkedList<UserAccount>();
			 if(userList!=null){
				 for(Object o : userList){
					 UserAccount userAccount = (UserAccount) o;  
				 }
			 }*/
			Map<Object,Date> userAccountOnlineMap = new LinkedHashMap<Object, Date>(); 
			
			
			UserAccount curUserAccount = SecurityUtil.getCurrentUserAccount();
			Boolean isSuperAdmin = SecurityUtil.isSuperAdmin();
			getAllUserAccount(userAccountOnlineMap,!isSuperAdmin, curUserAccount.getTenantId());
			
			getRequest().setAttribute("userAccountOnlineMap", userAccountOnlineMap);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	private void getAllUserAccount(Map<Object,Date> userAccountOnlineMap,boolean isListByTenantId,String tenantId){
		
		
		//查找tenantId下的
		List<Object> allUserList = sessionRegistry.getAllPrincipals();
		userAccountOnlineList = new LinkedList<Object>();
		for(Object o : allUserList){
			UserAccount ua = (UserAccount)o;
			String accountName = ua.getAccount();
			
			if(isListByTenantId){		
				if(ua.getTenantId().equals(tenantId)){
					if(StrUtils.isEmpty(account) || (StrUtils.isNotEmpty(account) && accountName.contains(account)) ){
						handleUserAccountOnline(userAccountOnlineMap, ua);
					}
				}
				
			}else{
				if(StrUtils.isEmpty(account) || (StrUtils.isNotEmpty(account) && accountName.contains(account)) ){
					handleUserAccountOnline(userAccountOnlineMap, ua);
				}
			}
			
		}
		
	}
	
	/**
	 * 查询 在线人员
	 * @param userAccountOnlineMap  人员在线列表Map
	 * @param ua 循环的每个UserAccount
	 * @param accountName 循环的每个UserAccount的 accountName
	 * @param account 前台查询的account
	 */
	private void handleUserAccountOnline(Map<Object,Date> userAccountOnlineMap, UserAccount ua){
		for(SessionInformation session : sessionRegistry.getAllSessions(ua, false)){
			if(userAccountOnlineMap.get(ua) == null){ 
				ua.setLastRequestDate(session.getLastRequest());
				ua.setSessionId(session.getSessionId());
				
				userAccountOnlineMap.put(ua, session.getLastRequest());  
			}else{
				Date prevLastRequest = userAccountOnlineMap.get(ua);
				
				if(session.getLastRequest().after(prevLastRequest)) {  
					ua.setLastRequestDate(session.getLastRequest());
					ua.setSessionId(session.getSessionId());
					
					userAccountOnlineMap.put(ua, session.getLastRequest()); 
				}
			}
		}
	}
 
	/** 处理删除操作 */
	public String removeUserAccountOnlineBy() {
		try {
			//roleService.deleteById(Role.class, id);
			if(StrUtils.isNotEmpty(sessionId)){
				SessionInformation session= sessionRegistry.getSessionInformation(sessionId);
				if(session!=null){
					session.expireNow();
				}
			}
			
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public SessionRegistry getSessionRegistry() {
		return sessionRegistry;
	}

	public void setSessionRegistry(SessionRegistry sessionRegistry) {
		this.sessionRegistry = sessionRegistry;
	}

	public List<Object> getUserAccountOnlineList() {
		return userAccountOnlineList;
	}

	public void setUserAccountOnlineList(List<Object> userAccountOnlineList) {
		this.userAccountOnlineList = userAccountOnlineList;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
}
