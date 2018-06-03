package com.vix.common.security.action;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.VixSecAction;
import com.vix.common.orginialMeta.entity.OrginialAuthority;
import com.vix.common.orginialMeta.entity.OrginialResource;
import com.vix.common.security.service.IOrginialAuthorityService;
import com.vix.common.security.service.IOrginialResourceService;
import com.vix.common.security.util.SecurityUtil;
import com.vix.common.security.vo.OrginialAuthorityImpVo;
import com.vix.core.constant.BizConstant;
import com.vix.core.utils.JSonUtils;
import com.vix.core.utils.StrUtils;
import com.vix.core.utils.excel.ExcelUtil;
import com.vix.core.web.Pager;

@Controller
@Scope("request")
public class OrginialAuthorityAction extends VixSecAction{

	private static final long serialVersionUID = 1L;

	@javax.annotation.Resource(name="orginialAuthorityService")
	private IOrginialAuthorityService authorityService;
	@javax.annotation.Resource(name="orginialResourceService")
	private IOrginialResourceService resourceService;
	
	private String authorityName;
	
	private String id;
	
	private String parentId;
	
	private OrginialAuthority entity;
	
	private OrginialAuthority entityForm;
	
	
	private String treeId;
	private String bizType;
	
	
	private String parentTreeId;
	private String parentTreeName;
	
	/** 获取列表数据  */
	public String goSingleList(){
		try {
			Map<String,Object> params = new HashMap<String, Object>();//getParams();
			
			if(StringUtils.isNotEmpty(authorityName)){
				//params.put("name,"+SearchCondition.ANYLIKE, "%"+authorityName+"%");
				authorityName = decode(authorityName, "UTF-8");
				params.put("name", "%"+authorityName+"%");
				//params.put("bizType", bizType);
			}else if(StringUtils.isNotEmpty(treeId)){
				/*if(null == id || id == 0){
					params.put("parentAuthority.id"+","+SearchCondition.IS, "NULL");
				}else{
					params.put("parentAuthority.id"+","+SearchCondition.EQUAL, id);
				}*/
				if(StringUtils.isNotEmpty(treeId) && !treeId.equals("0")
						&& !BizConstant.COMMON_SECURITY_RESTYPE.containsKey(treeId)){
					//params.put("parentAuthority.id"+","+SearchCondition.EQUAL, Long.parseLong(treeId));
					//params.put("bizType"+","+SearchCondition.EQUAL, bizType);
					OrginialAuthority parAu = authorityService.findEntityById(OrginialAuthority.class, treeId);
					//params.put("parentAuthorityId", Long.parseLong(treeId));
					params.put("parentAuthorityCode", parAu.getAuthorityCode());
					params.put("bizType", bizType);
				
				}else{
					params.put("parentAuthorityCode", null);
					params.put("bizType", bizType);
				}
				/*if(null == id || id == 0){
					params.put("parentAuthority.id"+","+SearchCondition.IS, "NULL");
				}else{
					params.put("parentAuthority.id"+","+SearchCondition.EQUAL, id);
				}*/
			}
			//HqlTenantIdUtil.handleParamMap4TenantId(params);
			
			//Pager pager = authorityService.findPagerByHqlConditions(getPager(), Authority.class, params);
			Pager pager = authorityService.findOrginialAuthorityPager(getPager(), params);
			setPager(pager);
			
			/*
			Set<TreeNode> treeSet = authorityService.findMenuAll();
			for(Iterator<TreeNode> it = treeSet.iterator();it.hasNext();){
			    TreeNode t1 = it.next();
			    System.out.println(t1.getName()+"--");
			    if(!t1.isLeaf()){
			        for(Iterator<TreeNode> it2 = t1.getChildren().iterator();it2.hasNext();){
			            TreeNode t2 = it2.next();
			            System.out.print(t2.getName());System.out.println("##");
			            
			            if(!t2.isLeaf()){
		                    for(Iterator<TreeNode> it3 = t2.getChildren().iterator();it3.hasNext();){
		                        TreeNode t3 = it3.next();
		                        System.out.print(t3.getName());System.out.println();
		                    }
		                }
			        }
			    }
			}*/
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			//getRequest().setAttribute("authorityTypeMap", BizConstant.COMMON_SECURITY_RESTYPE);
			
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if(null != id && id.longValue() > 0){
				//
				//OrginialAuthority parAu = entity.getParentAuthority();
				entity = authorityService.findEntityById(OrginialAuthority.class, id);
				OrginialAuthority parAu = authorityService.findParentOrginialAuthority(id);
				String bizType = entity.getBizType();
				if(parAu == null){
					parentTreeId = bizType ;
					parentTreeName = BizConstant.COMMON_SECURITY_RESTYPE.get(bizType) ;
				}else{
					parentTreeId = parAu.getId().toString();
					parentTreeName = parAu.getName();
				}
			}else if(StringUtils.isNotEmpty(parentTreeId)){
				if(StrUtils.isNotEmpty(parentTreeId) && !parentTreeId.equals("0")
						&& !BizConstant.COMMON_SECURITY_RESTYPE.containsKey(parentTreeId)){
					OrginialAuthority parAu  = authorityService.findEntityById(OrginialAuthority.class, parentTreeId);
					
					parentTreeId = parAu.getId().toString();
					parentTreeName = parAu.getName();
				}else{
					//parentTreeId = bizType;
					parentTreeName = BizConstant.COMMON_SECURITY_RESTYPE.get(parentTreeId);
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != entityForm.getId()){
				isSave = false;
			}
			
			if(StringUtils.isNotEmpty(parentTreeId)){
				if(StrUtils.isNotEmpty(parentTreeId) && !parentTreeId.equals("0")
						&& !BizConstant.COMMON_SECURITY_RESTYPE.containsKey(parentTreeId)){
					OrginialAuthority par = authorityService.findEntityById(OrginialAuthority.class, parentTreeId);
					//entityForm.setParentAuthority(par);
					entityForm.setParentAuthorityCode(par.getAuthorityCode());
					entityForm.setBizType(par.getBizType());
					entityForm.setTopParentCode(par.getTopParentCode());
					/*Authority parParentAu = par.getParentAuthority();
					if(parParentAu==null){
						entityForm.setTopParentCode(topParentCode);
					}*/
				}else{
					entityForm.setParentAuthorityCode(null);
					entityForm.setBizType(parentTreeId);
					entityForm.setTopParentCode(null);
				}
			}
			
			/*Set<Resource> rSet = reLoadResource();
			entityForm.setResources(rSet);
			authorityService.mergeOriginal(entityForm);*/
			
			String addResourceIds = getRequest().getParameter("addResourceIds");
			String deleteResourceIds = getRequest().getParameter("deleteResourceIds");
			authorityService.saveOrginialAuthority(addResourceIds, deleteResourceIds, entityForm);
			
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
			OrginialAuthority auth = authorityService.findEntityById(OrginialAuthority.class, id);
			if(null != auth){
				String parentAuthCode = SecurityUtil.getParentAuthorityCode(auth.getAuthorityCode());
				if(StringUtils.isNotEmpty(parentAuthCode)){
					setMessage("存在子权限不允许删除!");
				}else{
					authorityService.deleteByEntity(auth);
					renderText(DELETE_SUCCESS);
				}
			}else{
				setMessage("权限信息不存在!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	public String goChooseAuthority(){
		return "goChooseAuthority";
	}
	
	public void findTreeToJson(){
		try{
			List<OrginialAuthority> listAuthority = new ArrayList<OrginialAuthority>();
			/** 获取查询参数 */
			Map<String,Object> params = getParams();
			//listAuthority = authorityService.findAllSubEntity(Authority.class, "parentAuthority.id", id, params);
			
			StringBuilder strSb = new StringBuilder();
			
			if(StringUtils.isEmpty(bizType)){
				//查询出三个类型
				StringBuilder strSb2 = new StringBuilder();
				List<String> temString = new LinkedList<String>();
				
				strSb.append("[");
				
				for(Map.Entry<String, String> entry:BizConstant.COMMON_SECURITY_RESTYPE.entrySet()){
					String key = entry.getKey();
					String value = entry.getValue();
					
					strSb2.append("{id:\"");
					strSb2.append(key);
					strSb2.append("\",treeId:\"");
					strSb2.append(key);
					strSb2.append("\",bizType:\"");
					strSb2.append(key);
					strSb2.append("\",name:\"");
					strSb2.append(value);
					strSb2.append("\",open:false,isParent:true}");
					
					temString.add(strSb2.toString());
					strSb2.setLength(0);
				}
				
				strSb.append(StringUtils.join(temString.iterator(), ","));
				strSb.append("]");
				renderHtml(strSb.toString());
			}else if(StringUtils.isNotEmpty(treeId)){
				String tempId = null;
				if(StrUtils.isNotEmpty(treeId) && !treeId.equals("0")
						&& !BizConstant.COMMON_SECURITY_RESTYPE.containsKey(treeId)){
					tempId =treeId;
				}
				
				listAuthority = authorityService.findSubOrginialAuthority(tempId,bizType,BizConstant.COMMON_SECURITY_AUTHORITY_TYPE_M);
				
				strSb.append("[");
				/** 递归方式 **/
				for(int i =0;i<listAuthority.size();i++){
					OrginialAuthority auth = listAuthority.get(i);
					//String parentAuthCode = SecurityUtil.getParentAuthorityCode(auth.getAuthorityCode());
					Long subCount = auth.getSubCount();
					//System.out.println(subCount);
					if(subCount>0){
						strSb.append("{id:\"");
						strSb.append(auth.getId());
						strSb.append("\",treeId:\"");
						strSb.append(auth.getId());
						strSb.append("\",bizType:\"");
						strSb.append(auth.getBizType());
						strSb.append("\",name:\"");
						strSb.append(auth.getDisplayName());
						strSb.append("\",open:false,isParent:true}");
					}else{
						strSb.append("{id:\"");
						strSb.append(auth.getId());
						strSb.append("\",treeId:\"");
						strSb.append(auth.getId());
						strSb.append("\",bizType:\"");
						strSb.append(auth.getBizType());
						strSb.append("\",name:\"");
						strSb.append(auth.getDisplayName());
						strSb.append("\",open:false,isParent:false}");
					}
					if(i < listAuthority.size() -1){
						strSb.append(",");
					}
				}
				strSb.append("]");
				renderHtml(strSb.toString());
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/** 更新权限的资源信息 */
	private Set<OrginialResource> reLoadResource(){
		Set<OrginialResource> rSet = new HashSet<OrginialResource>();
		try{
			if(null != entityForm.getId()){
				OrginialAuthority tempAuthority = authorityService.findEntityById(OrginialAuthority.class, entityForm.getId());
				if(null != tempAuthority){
					rSet = tempAuthority.getResources();
				}
			}
			String addResourceIds = getRequest().getParameter("addResourceIds");
			if(null != addResourceIds && !"".equals(addResourceIds)){
				for(String idS : addResourceIds.split(",")){
					if(null != idS && !"".equals(idS)){
						OrginialResource r = resourceService.findEntityById(OrginialResource.class, idS);
						rSet.add(r);
					}
				}
			}
			String deleteResourceIds = getRequest().getParameter("deleteResourceIds");
			if(null != deleteResourceIds && !"".equals(deleteResourceIds)){
				for(String idS : deleteResourceIds.split(",")){
					if(null != idS && !"".equals(idS)){
						OrginialResource r = resourceService.findEntityById(OrginialResource.class, idS);
						rSet.remove(r);
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return rSet;
	}
	
	
	public void importFile() {
		Map<String,String> msgMap = new HashMap<String, String>();
		FileInputStream fis = null;
		
		try{	
			//System.out.println(taskFileToUpload);
			 //getResponse().setStatus( getResponse().SC_OK);
			//returnJson(true,"",RESULT_MESSAGE_SUCCESS,"");
			/*String fileName = taskFileToUpload.getName();
			System.out.println(fileName+"--"+taskFileToUploadFileName);
			*/
			if(fileToUpload==null){
				msgMap.put("success", "0");
				msgMap.put("error", "没有选择文件!");
			}else{
				//String fileName = fileAtt.getName();
				//String attType = BizConstant.COMMON_ATT_EMAIL_ATT;
				//isTmp = (isTmp==null)?true:isTmp;
				//String bizPath = FileUtil.FILE_STORE_MAP.get(fileAttType);
				//attachmentService.saveForUploadAtt(bizPath, fileAttFileName, fileAtt,businessTag, fileAttType, dataId, isTmp);
				ExcelUtil util;//AuthorityImpVo
				fis = new FileInputStream(fileToUpload);
				util = new ExcelUtil();
				List<OrginialAuthorityImpVo> voList = util.importExcel(OrginialAuthorityImpVo.class,"权限信息", fis);
					
				authorityService.saveForImportOrginialAuthority(voList);
				
				msgMap.put("success", "1");
				msgMap.put("msg", "导入成功!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			msgMap.put("success", "0");
			msgMap.put("error", e.getMessage());
		}  finally{
			if(fis!=null){
				IOUtils.closeQuietly(fis);
			}
		}
		String reMsg = JSonUtils.makeMapToJson(msgMap);
		//renderText(reMsg.toString());
		//render("text/html", reMsg);
		renderHtml(reMsg);
	}
	
	
	
	
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public String getParentTreeId() {
		return parentTreeId;
	}

	public void setParentTreeId(String parentTreeId) {
		this.parentTreeId = parentTreeId;
	}

	public String getParentTreeName() {
		return parentTreeName;
	}

	public void setParentTreeName(String parentTreeName) {
		this.parentTreeName = parentTreeName;
	}

	public OrginialAuthority getEntity() {
		return entity;
	}

	public void setEntity(OrginialAuthority entity) {
		this.entity = entity;
	}

	public OrginialAuthority getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(OrginialAuthority entityForm) {
		this.entityForm = entityForm;
	}

	public void setAuthorityService(IOrginialAuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	public void setResourceService(IOrginialResourceService resourceService) {
		this.resourceService = resourceService;
	}

}
