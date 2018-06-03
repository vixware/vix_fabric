package com.vix.common.security.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.VixSecAction;
import com.vix.common.security.entity.UserAccount;
import com.vix.common.security.entity.UserAccountProxyApply;
import com.vix.common.security.service.IUserAccountProxyService;
import com.vix.common.security.util.SecuritySettingUtil;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.security.util.handler.VixAuthSuccessHandler;
import com.vix.common.share.entity.BaseEmployee;
import com.vix.core.utils.SpringUtil;
import com.vix.core.web.Pager;

@Controller
@Scope("request")
public class UserAccountProxyAction extends VixSecAction{

	private static final long serialVersionUID = 1L;

	@Resource(name="userAccountProxyService")
	private IUserAccountProxyService userAccountProxyService;
	
	private String id;
	
	private String roleId;

	private UserAccountProxyApply entity;
	
	private UserAccountProxyApply entityForm;
	
	private String treeId;
	
	private List<String> checkAuthority;
	
	private Map<String,String> bizTypeMap;
	
	private String bizType;
	
	
	/** 获取申请代理列表数据  */
	public void goApplySingleList(){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			/*if(StringUtils.isNotEmpty(roleName)){
				roleName = decode(roleName, "UTF-8");
				params.put("roleName", "%"+roleName+"%");
			}*/
			Pager pager = userAccountProxyService.findApplyUserAccountProxyPager(getPager(), params);
			//renderJson(convertListToJson(pager.getResultList(), pager.getTotalCount()));//renderHtml
			//System.out.println("aaa");
			renderJson(convertListToJson(pager.getResultList(), pager.getTotalCount()));
			//setPager(pager);
			//Pager(int pageNo, int pageSize, int totalCount)
		}catch(Exception e){
			e.printStackTrace();
		}
		//return "goApplySingleList";
	}
	
	/** 获取代理列表数据  */
	public void goAcceptSingleList(){
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			/*if(StringUtils.isNotEmpty(roleName)){
				roleName = decode(roleName, "UTF-8");
				params.put("roleName", "%"+roleName+"%");
			}*/
			Pager pager = userAccountProxyService.findAcceptUserAccountProxyPager(getPager(), params);
			System.out.println("bbb");
			renderJson(convertListToJson(pager.getResultList(), pager.getTotalCount()));
			//setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		//return "goAcceptSingleList";
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdateApply() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if(null != id && id.longValue() > 0){
				entity = userAccountProxyService.findEntityById(UserAccountProxyApply.class, id);
				UserAccount ua = entity.getAcceptUserAccount();
				BaseEmployee emp = ua.getEmployee();
				entity.setEmpId(emp.getId());
				entity.setEmpName(emp.getName());
			}else{
				entity = new UserAccountProxyApply();
				entity.setIsEnable("Y");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goSaveOrUpdateApply";
	}

	/** 处理修改操作  */
	public String saveOrUpdateApply() {
		boolean isSave = true;
		try {
			/*if (roleService.isEntityExist(Role.class,entityForm.getId(), "name", entityForm.getName())) {
				setMessage("角色名称已经存在");
				return "update";
			}*/
			if(null != entityForm.getId()){
				isSave = false;
			}
			userAccountProxyService.saveOrUpdateProxyConfig(entityForm);
			
			//ApplicationSecurityCode.refreshObject();
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(SAVE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if(null != id && id.longValue() > 0){
				userAccountProxyService.deleteById(UserAccountProxyApply.class, id);
				renderText(DELETE_SUCCESS);
			}else{
				renderText(DELETE_FAIL);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	/**
	 * @throws Exception 
	 * @Title: proxyLoginById
	 * @Description: 代理登录
	 * @param     设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void proxyLoginById() throws Exception{
		if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if(null != id && id.longValue() > 0){
			
			UserAccount currentUa = SecurityUtil.getCurrentUserAccount();
			BaseEmployee currentEmp = SecurityUtil.getCurrentRealUser();
			
			UserAccountProxyApply proxy = userAccountProxyService.findEntityById(UserAccountProxyApply.class, id);
			UserAccount ua = proxy.getApplyUserAccount();//申请人的账号
			BaseEmployee emp = ua.getEmployee();
			//System.out.println("代理登录:"+ua.getAccount());
			
			ua.setProxyUserAccountId(String.valueOf(currentUa.getId()));//实际登陆人
			ua.setProxyUserAccountLoginName(currentUa.getAccount());
			if(currentEmp!=null){
				ua.setProxyUserAccountEmpName(currentEmp.getName());
			}
			
			Authentication authentication = SecuritySettingUtil.doAuthLogin(ua, getRequest(),getResponse());
			VixAuthSuccessHandler vixAuthSuccessHandler = SpringUtil.getBean("authSuccessHandler");
			//vixAuthSuccessHandler.onAuthenticationSuccess(getRequest(),getResponse(),authentication);spring security会重定向
			vixAuthSuccessHandler.handlAuthenticationData(getRequest(),getResponse(), authentication);
			renderJson(handleBaseMessage(true, OPER_SUCCESS, null));
		}else{
			renderJson(handleBaseMessage(false, OPER_FAIL, null));
		}
	}

	public IUserAccountProxyService getUserAccountProxyService() {
		return userAccountProxyService;
	}

	public void setUserAccountProxyService(
			IUserAccountProxyService userAccountProxyService) {
		this.userAccountProxyService = userAccountProxyService;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public UserAccountProxyApply getEntity() {
		return entity;
	}

	public void setEntity(UserAccountProxyApply entity) {
		this.entity = entity;
	}

	public UserAccountProxyApply getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(UserAccountProxyApply entityForm) {
		this.entityForm = entityForm;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public List<String> getCheckAuthority() {
		return checkAuthority;
	}

	public void setCheckAuthority(List<String> checkAuthority) {
		this.checkAuthority = checkAuthority;
	}

	public Map<String, String> getBizTypeMap() {
		return bizTypeMap;
	}

	public void setBizTypeMap(Map<String, String> bizTypeMap) {
		this.bizTypeMap = bizTypeMap;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	
	
	
	/** 代理登录
	public String saveForAddColConfig(){
		try{
			if(roleId!=null && StringUtils.isNotEmpty(colConfigIds)){
				//System.out.println(roleId + "---" +colConfigIds);
				roleService.saveForColConfig(roleId, colConfigIds);
			}
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
		return UPDATE;
	}
	*/
	
}
