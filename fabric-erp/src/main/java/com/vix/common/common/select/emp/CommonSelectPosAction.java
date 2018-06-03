package com.vix.common.common.select.emp;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.OrgDrpView;
import com.vix.common.org.service.IOrgDrpViewService;
import com.vix.core.web.Pager;
import com.vix.hr.position.service.IOrgPositionService;

/**
 * 岗位选择
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class CommonSelectPosAction extends BaseAction{

	private String id;
	
	/** 树的节点id */
	private String treeId;
	/** 树的节点名称 */
	private String treeName;
	
	@Autowired
	private IOrgDrpViewService orgDrpViewService;
	
	@Resource(name="orgPositionService")
	private IOrgPositionService orgPositionService;
	
	/** 部门id */
	private String orgUnitId;
	/** 部门名称 */
	private String orgUnitName;
	/** 公司id */
	private String orgId;
	
	/** 岗位名称  */
	private String posName;
	
	//private String checkedObjIds;
	
	/**
	 * 选择页面
	 * @return
	
	public String goChooseOrgUnit(){
		if(StringUtils.isEmpty(chkStyle)){
			chkStyle = "radio";
		}
        return "goChooseOrgPos";
    } */
	
    /**
     * 
     * @Title: findOrgAndUnitTreeToJson
     * @Description: 加载公司和部门、分销的混合树
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
        	//List<OrgView> orgUnitList = organizationUnitService.findOrgView4OwnCompList(nodeId);
        	List<OrgDrpView> orgUnitList = orgDrpViewService.findOrgDrpViewList(nodeId);
        	
            if(orgUnitList==null){
                orgUnitList = new LinkedList<OrgDrpView>();
            }
            
            StringBuilder strSb = new StringBuilder();
            strSb.append("[");
            /** 递归方式 **/
            int count = orgUnitList.size();
            for(int i =0; i<count; i++){
            	OrgDrpView org = orgUnitList.get(i);
            	
            	List<OrgDrpView> subList =orgDrpViewService.findOrgDrpViewListByParentId(org.getId());
            	String treeId = org.getId();
                if( subList!=null && subList.size() > 0){
                    strSb.append("{treeId:\"");
                    strSb.append(treeId);
                    strSb.append("\",treeType:\"");
                    strSb.append(org.getOrgType());
                    strSb.append("\",code:\"");
                    strSb.append(org.getCode());
                    strSb.append("\",name:\"");
                    strSb.append(org.getOrgName());
                    strSb.append("\",open:false,isParent:true");
                    
                    /*if(canCheckComp==0){
                    	if(org.getOrgType().equals("C") || org.getOrgType().equals("O")){
                    		strSb.append(",nocheck:true");
                        }
                    }*/
                }else{
                    strSb.append("{treeId:\"");
                    strSb.append(treeId);
                    strSb.append("\",treeType:\"");
                    strSb.append(org.getOrgType());
                    strSb.append("\",code:\"");
                    strSb.append(org.getCode());
                    strSb.append("\",name:\"");
                    strSb.append(org.getOrgName());
                    strSb.append("\",open:false,isParent:false");
                    
                   /* if(canCheckComp==0){
                    	if(org.getOrgType().equals("C") || org.getOrgType().equals("O")){
                    		strSb.append(",nocheck:true");
                        }
                    }*/
                }
                
            	strSb.append(",nocheck:true");
                
                /*if(checkedIdSet.contains(treeId)){
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
    
    
    /** 获取列表数据  */
    public String goSingleList(){
        try {
        	if(StringUtils.isEmpty(chkStyle)){
    			chkStyle = "checkbox";
    		}
        	
        	 Pager pager = getPager();
             //if(StringUtils.isNotEmpty(treeType) && (null != id || id != 0l) ){
             String treeTypeChar = null;
             String orgViewId = null;
             if(StringUtils.isNotEmpty(treeId)){
             	String orgViewIdStr = treeId.substring(0, treeId.length()-1);
              	orgViewId = orgViewIdStr;
              	treeTypeChar = String.valueOf(treeId.charAt( treeId.length()-1));
             }
             if(StringUtils.isNotEmpty(posName)){
            	 posName = decode(posName, "UTF-8");
             }
             
             if(treeTypeChar.equals("O") || StringUtils.isNotEmpty(posName)){
            	 pager =  orgPositionService.findOrgPositionPager(pager, orgViewId,posName);
             //pager = employeeHrService.findEmp4OrgDrpPager(pager, String.valueOf(treeTypeChar), orgViewId, empName);
             }
             setPager(pager);
        }catch(Exception e){
            e.printStackTrace();
        }
        return GO_SINGLE_LIST;
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

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}
	
}
