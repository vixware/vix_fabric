package com.vix.common.org.action;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.BusinessOrg;
import com.vix.common.org.entity.BusinessOrgView;
import com.vix.common.org.entity.BusinessView;
import com.vix.common.org.entity.OrganizationUnit;
import com.vix.common.org.service.IBusinessOrgService;
import com.vix.common.org.service.IBusinessViewService;
import com.vix.common.security.entity.Role;
import com.vix.core.constant.BizConstant;
import com.vix.core.web.Pager;
import com.vix.hr.organization.entity.Employee;

/**
 * 业务组织的action
 * @author Administrator
 *
 */
/**
 * @author shadow
 *
 */
@Controller
@Scope("request")
public class BusinessOrgAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Autowired
    private IBusinessOrgService businessOrgService;
    
    @Autowired
    private IBusinessViewService businessViewService;
    
    //@Autowired
    //private IOrganizationUnitService organizationUnitService;
    
    /** 业务组织名称 */
    private String businessName;
    
    private String id;
    
    private String parentIdStr;
    
    private String parentId;
    
    private BusinessOrg entity;

    private BusinessOrg entityForm;
    
    private String treeId;
    
    private String treeType;
    
    //V O
    private String parentTreeType;
    
    private String parentTreeName;
    
    private String bizOrgType;
    private String bizOrgIds;
    private String bizOrgNames;
    
    private String detailBizOrgId;
    
    public void executeDrools() throws Exception{
        
    }
    
    public void executeEsper() throws Exception{
        
    }
    
    /** 获取列表数据  */
    public String goSingleList(){
        try {
        	/*Map<String, Object> params = new HashMap<String, Object>();
			if (StringUtils.isNotEmpty(businessName)) {
				params.put("businessOrgName", "%"+businessName+"%");
			} else {
				 if(null == id || id == 0){
                     params.put("businessId", null);
                 }else{
                     params.put("businessId", id);
                 }
			}
			
			Pager pager = businessOrgService.findBusinessOrgPager(getPager(), params);
            setPager(pager);*/
			Map<String,Object> params = getParams();
			 
			Pager pager = getPager();
			if( StringUtils.isNotEmpty(businessName) ){
			 	params.put("businessName", businessName);
			 	pager =  businessOrgService.findBusinessOrgPager(getPager(), params);
			}else if(StringUtils.isNotEmpty(treeId) && StringUtils.isNotEmpty(treeType) ){
				String viewIdStr = treeId.substring(0, treeId.length()-1);
             	String viewId = viewIdStr;
             	
				if(treeType.equals("V")){
            		//params.put("organization.id"+","+SearchCondition.EQUAL, id);
            		params.put("bizOrgViewId", viewId);
            	}else{
            		//treeType.equals("O");
            		//params.put("parentOrganizationUnit.id"+","+SearchCondition.EQUAL, id);
            		params.put("bizOrgId", viewId);
            	}
            	pager =  businessOrgService.findBusinessOrgPager(getPager(), params);
			 }
			
			 setPager(pager);
        }catch(Exception e){
            e.printStackTrace();
        }
        return GO_SINGLE_LIST;
    }
    
    /** 跳转至用户修改页面 */
    public String goSaveOrUpdate() {
        try {
        	if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null != id && id.longValue() > 0) {
				entity = businessOrgService.findEntityById(BusinessOrg.class,id);
				
				BusinessOrg parentOrg = entity.getParentBusinessOrg();
				BusinessView parentView = entity.getBusinessView();
				if(parentOrg!=null){
					parentIdStr = parentOrg.getId()+"O";
					parentTreeName = parentOrg.getBusinessOrgName();
				}else if(parentView!=null){
					parentIdStr = parentView.getId()+"V";
					parentTreeName = parentView.getName();
				}
				
				/*String bizType = entity.getBizOrgType();
				if(bizType.equalsIgnoreCase(BizConstant.COMMON_ORG_O)){
					OrganizationUnit unit = entity.getOrganizationUnit();
					if(unit!=null){
						bizOrgIds = unit.getId()+"O";
						bizOrgNames = unit.getFullName();
					}
					
				}else if(bizType.equalsIgnoreCase(BizConstant.COMMON_ORG_R)){
					Role role = entity.getRole();
					if(role!=null){
						bizOrgIds = role.getId().toString();
						bizOrgNames = role.getName();
					}
				}else if(bizType.equalsIgnoreCase(BizConstant.COMMON_ORG_E)){
					Employee emp = entity.getEmployee();
					if(emp!=null){
						bizOrgIds = emp.getId().toString();
						bizOrgNames = emp.getName();
					}
				}
				*/
			} else {
				
				if (StringUtils.isNotEmpty(parentIdStr)) {
					entity = new BusinessOrg();
					
					String parentIdStrTmp = parentIdStr.substring(0, parentIdStr.length()-1);
	             	String parentIdLong = parentIdStrTmp;
	             	
	             	char treeTypeChar = parentIdStr.charAt( parentIdStr.length()-1);
	             	if(treeTypeChar=='V'){
	             		BusinessView orgView = businessOrgService.findEntityById(BusinessView.class, parentIdLong);
	             		entity.setBusinessView(orgView);
	             		
	             		parentTreeType = BizConstant.COMMON_BIZORG_V;
                        //parentId = orgView.getId();
                        parentTreeName = orgView.getName();
	             	}else if(treeTypeChar=='O'){
	             		BusinessOrg o = businessOrgService.findEntityById(BusinessOrg.class, parentIdLong);
	             		entity.setParentBusinessOrg(o);
	             		
	             		parentTreeType = BizConstant.COMMON_BIZORG_O;
                        //parentId = o.getId();
                        parentTreeName = o.getBusinessOrgName();
	             	}
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
        	
        	if(StringUtils.isNotEmpty(parentIdStr)){
        		 //BusinessOrg obj = businessOrgService.saveUpdateBusinessOrg(entityForm);
        		 String parentIdStrTmp = parentIdStr.substring(0, parentIdStr.length()-1);
        		 
	             String parentIdLong = parentIdStrTmp;
	             char treeTypeChar = parentIdStr.charAt( parentIdStr.length()-1);
	             
	             //bizOrgIds
	             businessOrgService.saveUpdateBusinessOrg(entityForm,treeTypeChar,parentIdLong);
        	
	             if(isSave){
	                 renderText(SAVE_SUCCESS);
	             }else{
	                 renderText(SAVE_SUCCESS);
	             }
        	}else{
        		this.setMessage("请先建立业务视图，再创建业务组织，" + UPDATE_FAIL);
        	}
            
           
           /* BusinessOrg parentObj = obj.getParentBusinessOrg();
            
            StringBuilder objString = new StringBuilder();
            objString.append("{");
            
            objString.append("\"obj\":");
            obj.setParentBusinessOrg(null);
            obj.setSubBusinessOrgs(null);
            objString.append(JSonUtils.makeBeanToJson(obj));
            
            objString.append(",");
            
            objString.append("\"parentObj\":");
            if(parentObj==null){
            	objString.append("null");
            }else{
            	parentObj.setParentBusinessOrg(parentBusinessOrg);
            	parentObj.setSubBusinessOrgs(null);
            	objString.append(JSonUtils.makeBeanToJson(parentObj));
            }
            
            objString.append("}");
            
            //renderHtml(objString.toString());
            makeMsgObjHtml(true, message, null, objString.toString());
            */
        } catch (Exception e) {
            e.printStackTrace();
            //makeMsgObjHtml(false, UPDATE_FAIL, null, null);
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
        	
        	BusinessOrg busOrg = businessOrgService.findEntityById(BusinessOrg.class, id);
			if (null != busOrg) {
				if (busOrg.getSubBusinessOrgs().size() > 0) {
					setMessage("存在子业务组织不允许删除!");
				} else {
					businessOrgService.deleteByEntity(busOrg);
					renderText(DELETE_SUCCESS);
				}
			} else {
				setMessage("业务组织信息不存在!");
			}
        } catch (Exception e) {
            e.printStackTrace();
            renderText(DELETE_FAIL);
        }
        return UPDATE;
    }
    
    
    
    
    
    
    /** 树形结构JSON */
   /* public void findTreeToJson(){
        try{
            String str = findAllBoStr();
            //renderJson(strSb.toString());
            renderHtml(str);
            //renderHtml("[{id:1,pId:0,name:\"测试根节点1\",isParent:true,children:[{id:2,pId:1,name:\"BO1\",isParent:true,children:[{id:4,pId:2,name:\"bo11\",isParent:false},{id:5,pId:2,name:\"bo12\",isParent:false}]}]}]");
            //renderHtml("[{\"id\":1,\"pId\":0,\"name\":\"aaaaa\",\"isParent\":false}]");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private String findAllBoStr(){
    	StringBuilder strSb = new StringBuilder("");
         *//** 获取查询参数 *//*
         //Map<String,Object> params = getParams();
         try {
        	Set<BusinessOrg> listBusinessOrg = new HashSet<BusinessOrg>();
			listBusinessOrg = businessOrgService.findBusinessOrgAll();
			
			strSb.append("[");

			List<String> objStr = new LinkedList<String>();
			*//** 递归方式 **//*
			for (BusinessOrg bo : listBusinessOrg) {
				StringBuilder oneStr = new StringBuilder();
				oneStr.append(makeBusinessOne(bo));
				objStr.add(oneStr.toString());
			}
			if (!objStr.isEmpty()) {
				strSb.append(StringUtils.join(objStr.iterator(), ","));
			}

			strSb.append("]");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strSb.toString();
    }
    
    
    private String makeBusinessOne(BusinessOrg bo){
    	StringBuilder oneStr = new StringBuilder("");
    	
    	BusinessOrg parent = bo.getParentBusinessOrg();
    	Set<BusinessOrg> subs =  bo.getSubBusinessOrgs();
    	Long pid = (parent==null?0L:parent.getId());
    	
    	oneStr.append("{\"id\":");
    	oneStr.append(bo.getId());
    	oneStr.append(",\"pId\":");
    	oneStr.append(pid);
    	oneStr.append(",\"name\":\"");
    	oneStr.append(bo.getBusinessOrgName());
    	if(subs!=null && !subs.isEmpty()){
    		oneStr.append("\",\"isParent\":true");//open:true,
    	}else{
    		oneStr.append("\",\"isParent\":false");//,open:false
    	}
    	
    	
    	
    	
    	if(subs!=null && !subs.isEmpty()){
    		oneStr.append(",\"children\":[");
    		
    		List<String> subString = new LinkedList<String>();
        	for(BusinessOrg subBo:subs){
        		String str = makeBusinessOne(subBo);
        		subString.add(str);
        	}
        	oneStr.append(StringUtils.join(subString.iterator(), ","));
        	oneStr.append("]");
    	}
    	
    	oneStr.append("}");
    	return oneStr.toString();
    }
    */
    
    /**
     * 
     * @Title: findOrgAndUnitTreeToJson
     * @Description: 加载公司和部门的混合树
     * @param     设定文件
     * @return void    返回类型
     * @throws
     */
    public void findOrgAndUnitTreeToJson(){
        try{
			//String str = findAllBoStr();
			// renderJson(strSb.toString());
			//renderHtml(str);
            //String treeType = getRequestParameter("treeType");
            loadBizOrg(treeId, treeType);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    private void loadBizOrg(String nodeId,String nodeTreeType){
        try{
            List<BusinessOrgView> bizOrgViewList = businessViewService.findOrgViewList(nodeId);
            
            if(bizOrgViewList==null){
            	bizOrgViewList = new LinkedList<BusinessOrgView>();
            }
            
            
            StringBuilder strSb = new StringBuilder();
            strSb.append("[");
            /** 递归方式 **/
            int count = bizOrgViewList.size();
            for(int i =0; i<count; i++){
            	BusinessOrgView orgView = bizOrgViewList.get(i);
            	
            	List<BusinessOrgView> subList = businessViewService.findOrgViewList(orgView.getId());
                if( subList!=null && subList.size() > 0){
                    strSb.append("{treeId:\"");
                    strSb.append(orgView.getId());
                    strSb.append("\",treeType:\"");
                    strSb.append(orgView.getViewType());
                    strSb.append("\",name:\"");
                    strSb.append(orgView.getName());
                    strSb.append("\",open:false,isParent:true}");
                }else{
                    strSb.append("{treeId:\"");
                    strSb.append(orgView.getId());
                    strSb.append("\",treeType:\"");
                    strSb.append(orgView.getViewType());
                    strSb.append("\",name:\"");
                    strSb.append(orgView.getName());
                    strSb.append("\",open:false,isParent:false}");
                }
                if(i < count -1){
                    strSb.append(",");
                }
            }
            strSb.append("]");
            renderHtml(strSb.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
       
    public String goChooseBusinessOrg(){
        return "goChooseBusinessOrg";
    }
    
    
    
    
    
    
    //以下为业务组织详情相关方法
    /** 跳转至用户修改页面 */
    public String goSaveOrUpdateDetail() {
    	return "goSaveOrUpdateDetail";
    }
    
    /**
     * 部门列表
     */
    public void goBoOrgUnitList(){
		try {
			//params.put("roleCode,"+SearchCondition.NOEQUAL, BizConstant.ROLE_SUPERADMIN);
			/*if(StringUtils.isNotEmpty(metaDataName)){
				params.put("metaDataName", "%"+metaDataName+"%");
			}*/
			List<OrganizationUnit> orgUnitList = businessOrgService.findBoOrgUnitDetailByBoId(id);
			Long dataSize = orgUnitList==null?0L:orgUnitList.size();
			renderHtml(convertListToJsonNoPage(orgUnitList, dataSize));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    /**
     * 职员列表
     */
    public void goBoEmpList(){
		try {
			//params.put("roleCode,"+SearchCondition.NOEQUAL, BizConstant.ROLE_SUPERADMIN);
			/*if(StringUtils.isNotEmpty(metaDataName)){
				params.put("metaDataName", "%"+metaDataName+"%");
			}*/
			List<Employee> empList = businessOrgService.findBoEmpDetailByBoId(id);
			Long dataSize = empList==null?0L:empList.size();
			renderHtml(convertListToJsonNoPage(empList, dataSize));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    /**
     * 角色列表
     */
    public void goBoRoleList(){
		try {
			//params.put("roleCode,"+SearchCondition.NOEQUAL, BizConstant.ROLE_SUPERADMIN);
			/*if(StringUtils.isNotEmpty(metaDataName)){
				params.put("metaDataName", "%"+metaDataName+"%");
			}*/
			List<Role> roleList = businessOrgService.findBoRoleDetailByBoId(id);
			Long dataSize = roleList==null?0L:roleList.size();
			renderHtml(convertListToJsonNoPage(roleList, dataSize));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
 
    /**
     * 保存业务组织详情
     * @return
     */
    public String saveOrUpdateDetail() {
        try {
    		 //BusinessOrg obj = businessOrgService.saveUpdateBusinessOrg(entityForm);
        	
        	if(StringUtils.isNotEmpty(bizOrgIds) && StringUtils.isNotEmpty(bizOrgType) && id!=null){
        		bizOrgIds = StringUtils.replace(bizOrgIds, "O", "");
        		
        		String[] bizOrgIdsArray = StringUtils.split(bizOrgIds,"\\,");
        		businessOrgService.saveBoDetail(id, bizOrgIdsArray, bizOrgType);
        		setMessage(OPER_SUCCESS);
        	}else{
        		setMessage("没有可以保存的数据！");
        	}
        } catch (Exception e) {
            e.printStackTrace();
            //makeMsgObjHtml(false, UPDATE_FAIL, null, null);
            this.setMessage(OPER_FAIL);
        }
        return UPDATE;
    }
    
    /** 处理删除操作 */
    public String deleteDetailById() {
        try {
        	if(id!=null && detailBizOrgId!=null && StringUtils.isNotEmpty(bizOrgType)){
        		businessOrgService.deleteDetail(id,detailBizOrgId,bizOrgType);
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

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public IBusinessOrgService getBusinessOrgService() {
		return businessOrgService;
	}

	public void setBusinessOrgService(IBusinessOrgService businessOrgService) {
		this.businessOrgService = businessOrgService;
	}

	public BusinessOrg getEntity() {
		return entity;
	}

	public String getParentIdStr() {
		return parentIdStr;
	}

	public void setParentIdStr(String parentIdStr) {
		this.parentIdStr = parentIdStr;
	}

	public String getParentTreeType() {
		return parentTreeType;
	}

	public void setParentTreeType(String parentTreeType) {
		this.parentTreeType = parentTreeType;
	}

	public String getParentTreeName() {
		return parentTreeName;
	}

	public void setParentTreeName(String parentTreeName) {
		this.parentTreeName = parentTreeName;
	}

	public void setEntity(BusinessOrg entity) {
		this.entity = entity;
	}

	public BusinessOrg getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(BusinessOrg entityForm) {
		this.entityForm = entityForm;
	}


	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public void setBusinessViewService(IBusinessViewService businessViewService) {
		this.businessViewService = businessViewService;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public String getBizOrgIds() {
		return bizOrgIds;
	}

	public void setBizOrgIds(String bizOrgIds) {
		this.bizOrgIds = bizOrgIds;
	}

	public String getBizOrgType() {
		return bizOrgType;
	}

	public void setBizOrgType(String bizOrgType) {
		this.bizOrgType = bizOrgType;
	}

	public String getBizOrgNames() {
		return bizOrgNames;
	}

	public void setBizOrgNames(String bizOrgNames) {
		this.bizOrgNames = bizOrgNames;
	}

	public String getDetailBizOrgId() {
		return detailBizOrgId;
	}

	public void setDetailBizOrgId(String detailBizOrgId) {
		this.detailBizOrgId = detailBizOrgId;
	}


}
