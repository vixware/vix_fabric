package com.vix.system.industrymanagement.action;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.orginialMeta.entity.OrginialAuthority;
import com.vix.common.security.service.IModuleService;
import com.vix.core.constant.BizConstant;
import com.vix.core.web.Pager;
import com.vix.system.industrymanagement.entity.IndustryManagement;
import com.vix.system.industrymanagement.entity.IndustryManagementModule;
import com.vix.system.industrymanagement.service.IIndustryManagementService;

@Controller
@Scope("prototype")
public class IndustryManagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private IIndustryManagementService industryManagementService;

	@Autowired
	private IModuleService moduleService;
	
	private String id;

	private IndustryManagement industryManagement;

	private List<IndustryManagement> industryManagementList;
	
	private String moduleName;
	
	private String selectIds;
	
	/**  行业id */
	private String industryMgtId;
	
	private IndustryManagementModule imm;
	
	@Override
	public String goList() {
		try {
			//industryManagementList = industryManagementService.findAllByEntityClass(IndustryManagement.class);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			/*
			Map<String, Object> params = getParams();
			Pager pager = industryManagementService.findPagerByHqlConditions(getPager(), IndustryManagement.class, params);
			setPager(pager);
			 */
			Map<String,Object> params = new HashMap<String, Object>();
			//params.put("roleCode,"+SearchCondition.NOEQUAL, BizConstant.ROLE_SUPERADMIN);
			//roleName
			if(StringUtils.isNotEmpty(moduleName)){
				//params.put("moduleName", "%"+moduleName+"%");
				moduleName = decode(moduleName, "UTF-8");
				params.put("moduleName", "%"+moduleName+"%");
			}
			
			if(industryMgtId!=null){
				params.put("industryMgtId", industryMgtId);
			}
			
			
			Pager pager = industryManagementService.findModulePagerByIndustryMgt(getPager(), params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (null != id && !"".equals(id)) {
				industryManagement = industryManagementService.findEntityById(IndustryManagement.class, id);
			} else {
				industryManagement = new IndustryManagement();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != industryManagement.getId()) {
				isSave = false;
			}else{
				industryManagement.setVixCode(RandomStringUtils.randomAlphabetic(64));
			}
			industryManagement = industryManagementService.mergeOriginal(industryManagement);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除操作 */
	public String deleteById() {
		try {
			if(id!=null && !"".equals(id)){
				industryManagementService.deleteById(IndustryManagement.class, id);
			}
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	
	public void findTreeToJson() {
		try {
			//List<BaseMetaDataCategory> listMetaDataCate = baseMetaDataService.findAllByEntityClassNoTenantId(BaseMetaDataCategory.class);
			//List<BaseMetaDataCategory> listMetaDataCate = baseMetaDataService.findAllByEntityClass(BaseMetaDataCategory.class);
			
			industryManagementList = industryManagementService.findAllByEntityClass(IndustryManagement.class);
			
			StringBuilder strSb = new StringBuilder();
			strSb.append("[");
			/** 递归方式 **/
			int count = industryManagementList.size();
			for (int i = 0; i < count; i++) {
				IndustryManagement im = industryManagementList.get(i);
				strSb.append("{id:\"");
				strSb.append(im.getId());
				strSb.append("\",name:\"");
				strSb.append(im.getName());
				strSb.append("\",isParent:false}");
					
				if (i < count - 1) {
					strSb.append(",");
				}
			}
			strSb.append("]");
			renderHtml(strSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加模块的保存
	 * @return
	 */
	public String saveForAddModule() {
		try {
			if(industryMgtId!=null && !industryMgtId.equals("") && StringUtils.isNotEmpty(selectIds)){
				industryManagementService.saveForAddModule(industryMgtId, selectIds);
			}
			setMessage(OPER_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			this.setMessage(OPER_FAIL);
		}
		return UPDATE;
	}
	
	/**
	 * 删除已选的模块
	 * @return
	 */
	public String deleteIndustryMgtModuleById() {
		try {
			if(id!=null && !"".equals(id)){
				industryManagementService.deleteById(IndustryManagementModule.class, id);
			}
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	
	
	
	
	/**  行业模块id */
	private String industryMgtModuleId;
	
	private Map<String,String> bizTypeMap;
	
	private String bizType;
	
	private String topAuthorityIds;
	
	private Long treeId;
	
	private List<Long> checkAuthority;
	
	/**
	 * 跳转行业和菜单的关系设定界面
	 * @return
	 * @throws Exception 
	 */
	public String goIndustryAuthority() throws Exception{
		bizTypeMap = new LinkedHashMap<String, String>();
		bizTypeMap.putAll(BizConstant.COMMON_SECURITY_RESTYPE);
		
		// id industryMgtModuleId
		if(id!=null && !"".equals(id)){
			imm = industryManagementService.findEntityById(IndustryManagementModule.class, id);
			imm.getIndustryManagement();
			imm.getModule();
		}
		
		return "goChooseAuthority";
	}
	
	/**
	 * 菜单列表的查询
	 * @return
	public String findIndustryAuthority(){
		try {
			List<Authority> listAuthority = new ArrayList<Authority>();
			if (null != industryMgtId) {
				if(id!=null && id==-1) id=null;
				listAuthority = industryManagementService.findAuthorityWithIndustryMgtForTreeGrid(industryMgtId, id, bizType);//id为权限id
			}
			renderHtml(convertListToJsonNoPage(listAuthority, listAuthority.size(),"parentAuthority","subAuthoritys","roles","resources"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
	
	/** 菜单树  */
	public void findAuthorityTree(){
		try{
			List<OrginialAuthority> listAuthority = null;//new ArrayList<Authority>();
			
			StringBuilder strSb = new StringBuilder();
			if(StringUtils.isNotEmpty(bizType)){
				//查询出三个类型
				StringBuilder strSb2 = new StringBuilder();
				List<String> temString = new LinkedList<String>();
				
				strSb.append("[");
				
				//listAuthority = authorityService.findSubAuthorityWithRole(roleId, bizType);
				listAuthority = industryManagementService.findSubOrginialAuthorityWithIndustryMgtModule(industryMgtModuleId, bizType);
				if(listAuthority!=null){
					for(OrginialAuthority auth:listAuthority){
						
						strSb2.append("{id:\"");
						strSb2.append(auth.getId());
						strSb2.append("\",treeId:\"");
						strSb2.append(auth.getId());
						strSb2.append("\",bizType:\"");
						strSb2.append(bizType);
						strSb2.append("\",name:\"");
						strSb2.append(auth.getName());
						
						strSb2.append("\",checked:");
						strSb2.append(auth.getIsCheck().equals("Y")?true:false);
						strSb2.append(",open:false,isParent:false}");
						
						temString.add(strSb2.toString());
						strSb2.setLength(0);
					}
				}
				
				
				strSb.append(StringUtils.join(temString.iterator(), ","));
				strSb.append("]");
				renderHtml(strSb.toString());
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/*
	public String goAuthorityTreeSingleList(){
		return "goAuthorityTreeData";
	}
	
	public void findAuthorityTreeData(){
		try {
			Set<OrginialAuthority> listAuthority = new HashSet<OrginialAuthority>();

			if (null != industryMgtModuleId) {
				listAuthority = industryManagementService.findAllOrginialAuthorityWithIndustryMgtModuleForTreeGrid(industryMgtModuleId, treeId, bizType);
			}
			
			StringBuilder resBulder = new StringBuilder();
			resBulder.append("[");
			
			

			List<String> objStr = new LinkedList<String>();
			for (OrginialAuthority au : listAuthority) {
				StringBuilder oneStr = new StringBuilder();
				oneStr.append(loadTreeJson(au));
				objStr.add(oneStr.toString());
			}
			if (!objStr.isEmpty()) {
				resBulder.append(StringUtils.join(objStr.iterator(), ","));
			}

			resBulder.append("]");
			
			renderHtml(resBulder.toString());
			//递归处理
			//renderHtml(convertListToJsonNoPage(listAuthority, listAuthority.size(),"parentAuthority","subAuthoritys","roles","resources"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	*/
	
	public String loadTreeJson(OrginialAuthority au){
		StringBuilder oneStr = new StringBuilder("");

		//Long pid = au.getParentId();
		String id = au.getId();
		Set<OrginialAuthority> subs = au.getChildren();
		
		oneStr.append("{\"id\":");
		oneStr.append(id);
		//oneStr.append(",\"pId\":");
		//oneStr.append(pid);
		oneStr.append(",\"name\":\"");
		oneStr.append(au.getName());
		oneStr.append("\",\"authType\":\"");
		oneStr.append(au.getAuthType());
		
		oneStr.append("\",\"checkId\":");
		oneStr.append(au.getCheckId());
		oneStr.append(",\"displayName\":\"");
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
	
	
	
	//元数据相关
	private String addMetaDataIds;
	
	private String deleteMetaDataIds;
	
	private String metadataDisplayName;
	
	public String goIndustryMetaData(){
		return "goChooseMetaData";
	}
	/** 获取列表数据 */
	public String goMetaDataList() {
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("industryMgtModuleId", industryMgtModuleId);
			
			if(StringUtils.isNotEmpty(metadataDisplayName)){
				//params.put("moduleName", "%"+moduleName+"%");
				metadataDisplayName = decode(metadataDisplayName, "UTF-8");
				params.put("metadataDisplayName", "%"+metadataDisplayName+"%");
			}
			
			Pager pager = industryManagementService.findMetaDataPagerByIndustryMgt(getPager(), params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goChooseMetaDataList";
	}
	
	public String saveForAddMetaData() {
		try {
			if(industryMgtModuleId!=null && !industryMgtModuleId.equals("") && StringUtils.isNotEmpty(addMetaDataIds)){
				industryManagementService.saveForAddMetaData(industryMgtModuleId, addMetaDataIds);
			}
			setMessage(OPER_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			this.setMessage(OPER_FAIL);
		}
		return UPDATE;
	}

	public String deleteMetaDataById() {
		try {
			if(industryMgtModuleId!=null && !industryMgtModuleId.equals("") && StringUtils.isNotEmpty(deleteMetaDataIds)){
				
				//System.out.println(industryMgtModuleId+"----"+deleteMetaDataIds);
				//industryManagementService.deleteById(IndustryManagementModule.class, id);
				industryManagementService.deleteModuleMetaDataByImIdAndMdId(industryMgtModuleId, deleteMetaDataIds);
			}
			renderText(DELETE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
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

	public List<IndustryManagement> getIndustryManagementList() {
		return industryManagementList;
	}

	public void setIndustryManagementList(List<IndustryManagement> industryManagementList) {
		this.industryManagementList = industryManagementList;
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

	public String getIndustryMgtId() {
		return industryMgtId;
	}

	public void setIndustryMgtId(String industryMgtId) {
		this.industryMgtId = industryMgtId;
	}

	public String getTopAuthorityIds() {
		return topAuthorityIds;
	}

	public void setTopAuthorityIds(String topAuthorityIds) {
		this.topAuthorityIds = topAuthorityIds;
	}

	public Long getTreeId() {
		return treeId;
	}

	public void setTreeId(Long treeId) {
		this.treeId = treeId;
	}

	public List<Long> getCheckAuthority() {
		return checkAuthority;
	}

	public void setCheckAuthority(List<Long> checkAuthority) {
		this.checkAuthority = checkAuthority;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getSelectIds() {
		return selectIds;
	}

	public void setSelectIds(String selectIds) {
		this.selectIds = selectIds;
	}

	public String getIndustryMgtModuleId() {
		return industryMgtModuleId;
	}

	public void setIndustryMgtModuleId(String industryMgtModuleId) {
		this.industryMgtModuleId = industryMgtModuleId;
	}

	public String getAddMetaDataIds() {
		return addMetaDataIds;
	}

	public void setAddMetaDataIds(String addMetaDataIds) {
		this.addMetaDataIds = addMetaDataIds;
	}

	public String getDeleteMetaDataIds() {
		return deleteMetaDataIds;
	}

	public void setDeleteMetaDataIds(String deleteMetaDataIds) {
		this.deleteMetaDataIds = deleteMetaDataIds;
	}

	public String getMetadataDisplayName() {
		return metadataDisplayName;
	}

	public void setMetadataDisplayName(String metadataDisplayName) {
		this.metadataDisplayName = metadataDisplayName;
	}

	public IndustryManagementModule getImm() {
		return imm;
	}

	public void setImm(IndustryManagementModule imm) {
		this.imm = imm;
	}

}
