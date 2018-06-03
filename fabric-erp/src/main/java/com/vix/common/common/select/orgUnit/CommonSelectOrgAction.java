package com.vix.common.common.select.orgUnit;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.Organization;
import com.vix.common.org.service.IOrganizationService;

/**
 * 公司组织架构树的展示
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class CommonSelectOrgAction extends BaseAction{

	/** 树的节点id */
	private String treeId;
	
	@Resource(name="organizationService")
	private IOrganizationService organizationService;
	
	/**
	 * 选择页面
	 * @return
	 */
	public String goChooseOrgUnit(){
		if(StringUtils.isEmpty(chkStyle)){
			chkStyle = "radio";
		}
        return "goChooseOrg";
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
            List<Organization> orgUnitList = organizationService.findSubOrganizationList(nodeId);
            //orgUnitList = organizationService.findSubOrganizationListNoTenantId(nodeId);
            if(orgUnitList==null){
                orgUnitList = new LinkedList<Organization>();
            }
            
            StringBuilder strSb = new StringBuilder();
            strSb.append("[");
            /** 递归方式 **/
            int count = orgUnitList.size();
            for(int i =0; i<count; i++){
            	Organization org = orgUnitList.get(i);
            	
            	List<Organization> subList = organizationService.findSubOrganizationList(org.getId());
            	//subList = organizationService.findSubOrganizationListNoTenantId(org.getId());
                if( subList!=null && subList.size() > 0){
                    strSb.append("{treeId:\"");
                    strSb.append(org.getId());
                    strSb.append("\",treeType:\"");
                    strSb.append(org.getOrgType());
                    strSb.append("\",name:\"");
                    strSb.append(org.getOrgName());
                    strSb.append("\",open:false,isParent:true");
                    
                   /* if(canCheckComp==0){
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
                    	
                    }*/
                }else{
                    strSb.append("{treeId:\"");
                    strSb.append(org.getId());
                    strSb.append("\",treeType:\"");
                    strSb.append(org.getOrgType());
                    strSb.append("\",name:\"");
                    strSb.append(org.getOrgName());
                    strSb.append("\",open:false,isParent:false");
                    
                    /*if(canCheckComp==0){
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
                    	
                    }*/
                }
                
               /* if(checkedIdSet.contains(treeId)){
                	strSb.append(",checked:true");
                }*/
                
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

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}
    
}
