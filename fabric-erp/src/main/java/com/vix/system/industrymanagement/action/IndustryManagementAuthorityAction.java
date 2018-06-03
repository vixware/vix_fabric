package com.vix.system.industrymanagement.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.orginialMeta.entity.OrginialAuthority;
import com.vix.system.industrymanagement.entity.IndustryManagement;
import com.vix.system.industrymanagement.service.IIndustryManagementAuthService;

@Controller
@Scope("prototype")
public class IndustryManagementAuthorityAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IIndustryManagementAuthService industryManagementAuthService;

	private String id;

	private IndustryManagement industryManagement;

	/**  行业id */
	//private Long industryMgtId;
	
	/**  行业模块id */
	private String industryMgtModuleId;
	
	private Map<String,String> bizTypeMap;
	
	private String bizType;
	
	//private Long treeId;
	
	//private List<Long> checkAuthority;
	
	private String authId;
	private String authCode;
	
	//左侧checked的树的节点
	private String menuIds;
	//右侧checked的按钮
	private	String funIds;
	//左侧点击的菜单节点
	private String checkedMenuId;
	//左侧权限树是否有变化
	private Boolean isChangCheckMenu;
	//右侧按钮是否有变化
	private Boolean isChangeCheckFun;
	
	/*
	public String goAuthorityTreeSingleList(){
		return "goAuthorityTreeData";
	}*/
	
	public void findAuthorityTreeData(){
		try {
			Set<OrginialAuthority> listAuthority = new HashSet<OrginialAuthority>();

			if (null != industryMgtModuleId) {
				listAuthority = industryManagementAuthService.findAllOrginialAuthorityMWithIndustryMgtModule(industryMgtModuleId, bizType);
			}
			
			StringBuilder resBulder = new StringBuilder();
			resBulder.append("{\"authdata\":[");
			
			List<String> objStr = new LinkedList<String>();
			for (OrginialAuthority au : listAuthority) {
				StringBuilder oneStr = new StringBuilder();
				oneStr.append(loadTreeJson(au));
				objStr.add(oneStr.toString());
			}
			if (!objStr.isEmpty()) {
				resBulder.append(StringUtils.join(objStr.iterator(), ","));
			}

			resBulder.append("]}");
			
			renderHtml(resBulder.toString());
			//renderJson(resBulder.toString());
			//renderText(resBulder.toString());
			
			//递归处理
			//renderHtml(convertListToJsonNoPage(listAuthority, listAuthority.size(),"parentAuthority","subAuthoritys","roles","resources"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public String loadTreeJson(OrginialAuthority au){
		StringBuilder oneStr = new StringBuilder("");

		//Long pid = au.getParentId();
		String id = au.getId();
		Set<OrginialAuthority> subs = au.getChildren();
		
		if(id==null) id="0";
		//if(pid==null) pid=0L;
		
		oneStr.append("{\"id\":\"");
		oneStr.append(id);
		//oneStr.append("\",\"pId\":\"");
		//oneStr.append(pid);
		oneStr.append("\",\"name\":\"");
		oneStr.append(au.getName());
		oneStr.append("\",\"authorityCode\":\"");
		oneStr.append(au.getAuthorityCode());
		oneStr.append("\",\"parentAuthorityCode\":\"");
		oneStr.append(au.getParentAuthorityCode());
		oneStr.append("\",\"authType\":\"");
		oneStr.append(au.getAuthType());
		
		oneStr.append("\",\"checkId\":\"");
		oneStr.append(au.getCheckId());
		//oneStr.append(",\"displayName\":\"");
		//oneStr.append(au.getDisplayName());
		
		Long subCount = au.getSubCount();
		
		if(subCount >0 ){	
			oneStr.append("\",\"isParent\":true");// open:true,
		} else {
			oneStr.append("\",\"isParent\":false");// ,open:false
		}
		
		if(au.getIsCheck().equals("Y")){
			//System.out.println("check:"+id);
			//oneStr.append(",\"checked\":true");
			oneStr.append(",\"checked\":true");
		}else{
			//oneStr.append(",\"checked\":false");
			oneStr.append(",\"checked\":false");
		}

		if (subs != null && !subs.isEmpty()) {
			oneStr.append(",\"children\":[");

			List<String> subString = new LinkedList<String>();
			for (OrginialAuthority subAu : subs) {
				String str = loadTreeJson(subAu);
				subString.add(str);
			}
			oneStr.append(StringUtils.join(subString.iterator(), ","));
			oneStr.append("]");
		}

		oneStr.append("}");
		return oneStr.toString();
	}
	
	/**
	 * @Title: findFunGrid
	 * @Description: 行业模块的权限树节点的 按钮列表
	 * @param     设定文件
	 * @return void    返回类型
	 * @throws
	 */
	public void findFunGrid(){
		try {
			List<OrginialAuthority> subAuthorityList = null;
			//System.out.println("industryMgtModuleId:"+industryMgtModuleId+",authId:"+ authId+",bizType:" +bizType);
			subAuthorityList = industryManagementAuthService.findSubOrginialAuthorityFByIndustryMgtModule(industryMgtModuleId, authCode, bizType);
			if(subAuthorityList==null){
				subAuthorityList= new ArrayList<OrginialAuthority>();
			}
			
			Long dataSize = subAuthorityList==null?0L:subAuthorityList.size();
			renderHtml(convertListToJsonNoPage(subAuthorityList, dataSize));
		}catch(Exception e){
			e.printStackTrace();
		}
		//return GO_SINGLE_LIST;
	}
	
	
	/**
	 * 保存行业模块和权限菜单的关系配置
	 * @return
	 */
	public String saveForAuthority(){
		try{
			//System.out.println(roleId);
			//System.out.println(StringUtils.join(checkAuthority, ","));
			
			//Long industryMgtModuleId,String bizType,String menuIds,String funIds,Long checkedMenuId,Boolean isChangCheckMenu,Boolean isChangeCheckFun
			//System.out.println("industryMgtModuleId:"+industryMgtModuleId +",bizType:"+bizType);
			//System.out.println("menuIds:"+menuIds+",funIds:"+funIds);
			//System.out.println("checkedMenuId:"+checkedMenuId);
			//System.out.println("isChangCheckMenu:"+ isChangCheckMenu+",isChangeCheckFun:"+isChangeCheckFun);
			if(industryMgtModuleId!=null && StringUtils.isNotEmpty(bizType)){
				industryManagementAuthService.saveForOrginialAuthority(industryMgtModuleId, bizType, menuIds, funIds, checkedMenuId, isChangCheckMenu, isChangeCheckFun);
				//industryManagementAuthService.saveForAuthority(industryMgtModuleId, bizType, treeId, topAuthorityIds, checkAuthority);
			}
			//ApplicationSecurityCode.refreshObject();
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
		return UPDATE;
	}
	
	
	public String saveForAuthorityByAuthId(){
		try{
			//System.out.println("industryMgtModuleId:"+industryMgtModuleId +",bizType:"+bizType +",authId:"+authId);
			if(industryMgtModuleId!=null && StringUtils.isNotEmpty(bizType) && authId!=null){
				industryManagementAuthService.saveForOrginialAuthorityByAuthId(industryMgtModuleId, bizType, authId);
			}
			//ApplicationSecurityCode.refreshObject();
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
		return UPDATE;
	}
	
	public String deleteForAuthorityByAuthId(){
		try{
			//System.out.println("industryMgtModuleId:"+industryMgtModuleId +",bizType:"+bizType +",authId:"+authId);
			if(industryMgtModuleId!=null && StringUtils.isNotEmpty(bizType) && authId!=null){
				industryManagementAuthService.deleteForOrginialAuthorityByAuthId(industryMgtModuleId, bizType, authId);
			}
			//ApplicationSecurityCode.refreshObject();
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
		return UPDATE;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public IndustryManagement getIndustryManagement() {
		return industryManagement;
	}

	public void setIndustryManagement(IndustryManagement industryManagement) {
		this.industryManagement = industryManagement;
	}

	public String getIndustryMgtModuleId() {
		return industryMgtModuleId;
	}

	public void setIndustryMgtModuleId(String industryMgtModuleId) {
		this.industryMgtModuleId = industryMgtModuleId;
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

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

	public String getFunIds() {
		return funIds;
	}

	public void setFunIds(String funIds) {
		this.funIds = funIds;
	}

	public String getCheckedMenuId() {
		return checkedMenuId;
	}

	public void setCheckedMenuId(String checkedMenuId) {
		this.checkedMenuId = checkedMenuId;
	}

	public Boolean getIsChangCheckMenu() {
		return isChangCheckMenu;
	}

	public void setIsChangCheckMenu(Boolean isChangCheckMenu) {
		this.isChangCheckMenu = isChangCheckMenu;
	}

	public Boolean getIsChangeCheckFun() {
		return isChangeCheckFun;
	}

	public void setIsChangeCheckFun(Boolean isChangeCheckFun) {
		this.isChangeCheckFun = isChangeCheckFun;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	/**
	 * 保存行业模块和权限菜单的关系配置
	 * @return
	 
	public String saveForAuthority(){
		try{
			//System.out.println(roleId);
			//System.out.println(StringUtils.join(checkAuthority, ","));
			
			if(industryMgtModuleId!=null && StringUtils.isNotEmpty(bizType)){
				industryManagementService.saveForAuthority(industryMgtModuleId, bizType, treeId, topAuthorityIds, checkAuthority);
			}
			ApplicationSecurityCode.refreshObject();
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
		return UPDATE;
	}
	*/
	
	
	
	
}
