package com.vix.common.security.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import com.vix.common.base.action.VixSecAction;
import com.vix.common.security.entity.Authority;
import com.vix.common.security.entity.Module;
import com.vix.common.security.service.IModuleService;
import com.vix.core.constant.BizConstant;
import com.vix.core.web.Pager;

@Controller
@Scope("request")
public class ModuleAction extends VixSecAction{

	private static final long serialVersionUID = 1L;

	@Autowired
	private IModuleService moduleService;
	
	private String moduleName;
	
	private String id;
	
	private Module entity;
	
	private Module entityForm;
	
	private static String base_url="http://127.0.0.1:8080/vixnt2/restService/common/module";
	private static String GET_All=base_url + "/findAll.rs";
	@Resource(name="restTemplate")
	private RestTemplate restTemplate;
	private static class ModuleList extends ArrayList<Module> {
	}
	/** 获取列表数据  */
	public String goSingleList(){
		try {
			/*Object mlObject = restTemplate.getForObject(GET_All, ModuleList.class);
			System.out.println("VV  ID:"+mlObject);*/
			
			Map<String,Object> params = new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(moduleName)){
				params.put("name", "%"+moduleName+"%");
			}
			Pager pager = moduleService.findModulePager(getPager(), params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
 
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//null != id && id.longValue() > 0
				entity = moduleService.findEntityById(Module.class, id);
			}
			//getRequest().setAttribute("moduleTypeMap", BizConstant.COMMON_SECURITY_RESTYPE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (moduleService.isEntityExist(Module.class,entityForm.getId(), "moduleCode", entityForm.getModuleCode())) {
				setMessage("模块编码已经存在！");
				return "update";
			}
			if(null != entityForm.getId()){
				isSave = false;
			}
			
			moduleService.saveModule(entityForm);
			//保存
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
			moduleService.deleteById(Module.class, id);
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	
	
	private Map<String,String> bizTypeMap;
	
	private String bizType;
	
	/**  模块id */
	private String moduleId;
	
	private String topAuthorityIds;
	
	private String treeId;
	
	private List<String> checkAuthority;
	
	/**
	 * 跳转行业和菜单的关系设定界面
	 * @return
	 */
	public String goModuleAuthority(){
		bizTypeMap = new LinkedHashMap<String, String>();
		bizTypeMap.putAll(BizConstant.COMMON_SECURITY_RESTYPE);
		return "goChooseAuthority";
	}
	
	public String goAuthorityTreeSingleList(){
		return "goAuthorityTreeData";
	}
	
	public String loadTreeJson(Authority au){
		StringBuilder oneStr = new StringBuilder("");

		String pid = au.getParentId();
		String id = au.getId();
		Set<Authority> subs = au.getChildren();
		
		oneStr.append("{\"id\":\"");
		oneStr.append(id);
		oneStr.append("\",\"pId\":\"");
		oneStr.append(pid);
		oneStr.append("\",\"name\":\"");
		oneStr.append(au.getName());
		oneStr.append("\",\"authType\":\"");
		oneStr.append(au.getAuthType());
		
		oneStr.append("\",\"checkId\":\"");
		oneStr.append(au.getCheckId());
		oneStr.append("\",\"displayName\":\"");
		oneStr.append(au.getDisplayName());
		oneStr.append("\",\"memo\":\"");
		oneStr.append(StringUtils.isEmpty(au.getMemo())?"":au.getMemo());
		//if (subs != null && !subs.isEmpty()) {
		String state = au.getState();
		oneStr.append("\",\"state\":\"");
		oneStr.append(state);
		
		if(state.equals("closed")){	
			oneStr.append("\",\"isParent\":true");// open:true,
		} else {
			oneStr.append("\",\"isParent\":false");// ,open:false
		}
		
		if(au.getIsCheck().equals("Y")){
			//oneStr.append(",\"checked\":true");
			oneStr.append(",\"isCheck\":true");
		}else{
			//oneStr.append(",\"checked\":false");
			oneStr.append(",\"isCheck\":false");
		}

		if (subs != null && !subs.isEmpty()) {
			oneStr.append(",\"children\":[");

			List<String> subString = new LinkedList<String>();
			for (Authority subAu : subs) {
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
	 * 保存行业和权限菜单的关系配置
	 * @return
	 
	public String saveForAuthority(){
		try{
			if(moduleId!=null && StringUtils.isNotEmpty(bizType)){
				//authorityService.saveForAuthority(roleId,bizType, treeId,topAuthorityIds, checkAuthority);
				moduleService.saveForAuthority(moduleId, bizType, treeId, topAuthorityIds, checkAuthority);
			}
			renderText(SAVE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(SAVE_FAIL);
		}
		return UPDATE;
	}
	 */
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

	public String getTopAuthorityIds() {
		return topAuthorityIds;
	}

	public void setTopAuthorityIds(String topAuthorityIds) {
		this.topAuthorityIds = topAuthorityIds;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Module getEntity() {
		return entity;
	}

	public void setEntity(Module entity) {
		this.entity = entity;
	}

	public Module getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(Module entityForm) {
		this.entityForm = entityForm;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
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

}
