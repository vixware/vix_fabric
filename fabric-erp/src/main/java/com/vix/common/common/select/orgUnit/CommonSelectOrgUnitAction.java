package com.vix.common.common.select.orgUnit;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.OrgView;
import com.vix.common.org.service.IOrganizationUnitService;
import com.vix.core.constant.BizConstant;

/**
 * 组织机构部门的选择
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class CommonSelectOrgUnitAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	/** 树的节点id */
	private String treeId;
	/** 树的节点名称 */
	private String treeName;
	
	@Autowired
	private IOrganizationUnitService organizationUnitService;
	
	/** 部门id */
	private String orgUnitId;
	/** 部门名称 */
	private String orgUnitName;
	/** 公司id */
	private String orgId;
	
	/**
	 * 节点的公司是否可以选择
	 * 默认1  可以选择
	 * 0  不可以选择
	 */
	private Integer canCheckComp = 1 ;
	
	private String checkedObjIds;
	
	/**  
	 * 部门类型
	 */
	private String unitType;
	
	/**
	 * 选择页面
	 * @return
	 */
	public String goChooseOrgUnit(){
		if(StringUtils.isEmpty(chkStyle)){
			chkStyle = "radio";
		}
        return "goChooseOrgUnit";
    }
	
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
            String treeType = getRequestParameter("treeType");
            loadOrg(treeId, treeType);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    private void loadOrg(String nodeId,String nodeTreeType){
        try{
        	//oneStr.append(",\"checked\":true");
        	Set<String> checkedIdSet = new HashSet<String>();
        	if(StringUtils.isNotEmpty(checkedObjIds)){
        		String[] checkIdArray = checkedObjIds.split("\\,");
        		for(String checkId:checkIdArray){
        			checkedIdSet.add(checkId+"O");
        		}
        	}
        	
        	
        	
        	
            List<OrgView> orgUnitList = organizationUnitService.findOrgViewList4CmnSelect(nodeId,unitType);
            
            if(orgUnitList==null){
                orgUnitList = new LinkedList<OrgView>();
            }
            
            StringBuilder strSb = new StringBuilder();
            strSb.append("[");
            /** 递归方式 **/
            int count = orgUnitList.size();
            for(int i =0; i<count; i++){
            	OrgView org = orgUnitList.get(i);
            	
            	List<OrgView> subList = organizationUnitService.findOrgViewList4CmnSelect(org.getId(),unitType);
            	String treeId = org.getId();
                if( subList!=null && subList.size() > 0){
                    strSb.append("{treeId:\"");
                    strSb.append(treeId);
                    strSb.append("\",realId:\"");
                    strSb.append(org.getRealId());
                    strSb.append("\",treeType:\"");
                    strSb.append(org.getOrgType());
                    strSb.append("\",name:\"");
                    strSb.append(org.getOrgName());
                    strSb.append("\",open:false,isParent:true");
                    
                    if(canCheckComp==0){
                    	if(org.getOrgType().equals("C")){
                    		strSb.append(",nocheck:true");
                        }
                    }
                    if( org.getOrgType().equals("O") && StringUtils.isNotEmpty(unitType)){
                    	if(unitType.equals("XS")){
                    		if(BizConstant.COMMON_ORGUNIT_TYPE_XS_SET.contains(org.getOrgUnitType())){
                    			strSb.append(",nocheck:false");
                    		}else{
                    			strSb.append(",nocheck:true");
                    		}
                    	}else{
                    		if(org.getOrgUnitType().equalsIgnoreCase(unitType)){
                        		strSb.append(",nocheck:false");
                        	}else{
                        		strSb.append(",nocheck:true");
                        	}
                    	}
                    	
                    }
                }else{
                    strSb.append("{treeId:\"");
                    strSb.append(treeId);
                    strSb.append("\",realId:\"");
                    strSb.append(org.getRealId());
                    strSb.append("\",treeType:\"");
                    strSb.append(org.getOrgType());
                    strSb.append("\",name:\"");
                    strSb.append(org.getOrgName());
                    strSb.append("\",open:false,isParent:false");
                    
                    if(canCheckComp==0){
                    	if(org.getOrgType().equals("C")){
                    		strSb.append(",nocheck:true");
                        }
                    }
                    if( org.getOrgType().equals("O") && StringUtils.isNotEmpty(unitType)){
                    	if(unitType.equals("XS")){
                    		if(BizConstant.COMMON_ORGUNIT_TYPE_XS_SET.contains(org.getOrgUnitType())){
                    			strSb.append(",nocheck:false");
                    		}else{
                    			strSb.append(",nocheck:true");
                    		}
                    	}else{
                    		if(org.getOrgUnitType().equalsIgnoreCase(unitType)){
                        		strSb.append(",nocheck:false");
                        	}else{
                        		strSb.append(",nocheck:true");
                        	}
                    	}
                    	
                    }
                }
                
                if(checkedIdSet.contains(treeId)){
                	strSb.append(",checked:true");
                }
                
                strSb.append("}");//strSb.append("}");
                
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
    
	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

	public String getTreeId() {
		return treeId;
	}


	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}


	public String getTreeName() {
		return treeName;
	}


	public void setTreeName(String treeName) {
		this.treeName = treeName;
	}

	public String getOrgUnitId() {
		return orgUnitId;
	}


	public void setOrgUnitId(String orgUnitId) {
		this.orgUnitId = orgUnitId;
	}


	public String getOrgUnitName() {
		return orgUnitName;
	}


	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
	}


	public String getOrgId() {
		return orgId;
	}


	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getCheckedObjIds() {
		return checkedObjIds;
	}

	public void setCheckedObjIds(String checkedObjIds) {
		this.checkedObjIds = checkedObjIds;
	}

	public Integer getCanCheckComp() {
		return canCheckComp;
	}

	public void setCanCheckComp(Integer canCheckComp) {
		this.canCheckComp = canCheckComp;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

}
