package com.vix.common.common.select.base;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.service.IModuleService;
import com.vix.core.web.Pager;
import com.vix.system.industrymanagement.entity.IndustryManagement;
import com.vix.system.industrymanagement.entity.IndustryManagementModule;
import com.vix.system.industrymanagement.service.IIndustryManagementModuleService;
import com.vix.system.industrymanagement.service.IIndustryManagementService;


@Controller
@Scope("prototype")
public class CommonSelectModuleAction extends BaseAction {

	private String moduleName;
	
    @Autowired
    private IModuleService moduleService;
    
    @Autowired
	private IIndustryManagementService industryManagementService;
    
    @Autowired
	private IIndustryManagementModuleService industryManagementModuleService;
    
    @Override
    public String goList(){
    	if(StringUtils.isEmpty(chkStyle)){
			chkStyle = "checkbox";
		}
		return GO_LIST;
	}
    
    /** 获取列表数据  */
    public String goSingleList(){
        try {
        	Map<String,Object> params = new HashMap<String, Object>();
			if(StringUtils.isNotEmpty(moduleName)){
				moduleName = decode(moduleName, "UTF-8");
				params.put("name", "%"+moduleName+"%");
			}
			if(StringUtils.isNotEmpty(industryMgtId) && !industryMgtId.equals("0")){//if(industryMgtId!=null && industryMgtId>0L){
				params.put("industryMgtId", industryMgtId);
			}
			Pager pager = moduleService.findModulePager(getPager(), params);
        	setPager(pager);
        }catch(Exception e){
            e.printStackTrace();
        }
        return GO_SINGLE_LIST;
    }
    
    
    //行业模块的选择
    private String industryMgtId;
    
    /** 树的节点id */
	private String treeId;
	
	private String checkedObjIds;
	
    /**
	 * 选择页面
	 * @return
	 */
	public String goChooseIndustryModule(){
		if(StringUtils.isEmpty(chkStyle)){
			chkStyle = "radio";
		}
        return "goChooseIndustryModule";
    }
	public void findIndustryModuleTreeToJson(){
        try{
            String treeType = getRequestParameter("treeType");
            loadIndustryModule(treeId, treeType);
        }catch(Exception e){
            e.printStackTrace();
        }
        
	}
	private void loadIndustryModule(String nodeId,String nodeTreeType) throws Exception{
		
		StringBuilder resSb = new StringBuilder();
		List<String> jsonList = new LinkedList<String>();
		
		resSb.append("[");
		
		StringBuilder jsonStrSb = new StringBuilder();
		if(StringUtils.isEmpty(nodeId)){
			List<IndustryManagement> industryMgtList = industryManagementService.findAllIndustrymanagement();
			
			if(industryMgtList!=null && industryMgtList.size()>0){
				for(IndustryManagement industryMgt:industryMgtList){
					
					jsonStrSb.append("{treeId:\"");
					jsonStrSb.append(industryMgt.getId());
					jsonStrSb.append("\",treeType:\"");
					jsonStrSb.append("I");
					jsonStrSb.append("\",name:\"");
					jsonStrSb.append(industryMgt.getName());
					jsonStrSb.append("\",open:false,isParent:true");
					jsonStrSb.append(",nocheck:true}");
					
					jsonList.add(jsonStrSb.toString());
					jsonStrSb.setLength(0);
				}
			}
            
            
		}else{
			List<IndustryManagementModule> industryMgtModuleList = industryManagementModuleService.findIndustryManagementModuleByIndustryMgtId(nodeId);
			
			if(industryMgtModuleList!=null && industryMgtModuleList.size()>0){
				String chkTmp = "";
				if(StringUtils.isNotEmpty(checkedObjIds)){
					chkTmp = ","+checkedObjIds+"," ;
                }
				
				
				for(IndustryManagementModule industryMgtModule:industryMgtModuleList){
					
					jsonStrSb.append("{treeId:\"");
					jsonStrSb.append(industryMgtModule.getId());
					jsonStrSb.append("\",treeType:\"");
					jsonStrSb.append("M");
					jsonStrSb.append("\",name:\"");
					jsonStrSb.append(industryMgtModule.getModule().getName());
					//jsonStrSb.append(industryMgtModule.getIndustryManagement().getName()).append("(").append(industryMgtModule.getModule().getName()).append(")");
					
					jsonStrSb.append("\",fullName:\"");
					jsonStrSb.append(industryMgtModule.getIndustryManagement().getName()).append("(").append(industryMgtModule.getModule().getName()).append(")");
					jsonStrSb.append("\",open:false,isParent:false");
					//jsonStrSb.append(",nocheck:false");
					//System.out.println("checkedObjIds:"+checkedObjIds+"####"+industryMgtModule.getId());
					if(StringUtils.isNotEmpty(chkTmp)){
						if(chkTmp.contains(","+industryMgtModule.getId()+",")){
							jsonStrSb.append(",checked:true");
						}
	                }
					
					jsonStrSb.append("}");
					
					jsonList.add(jsonStrSb.toString());
					jsonStrSb.setLength(0);
				}
			}
			
		}
		
		if(jsonList.size()>0){
			resSb.append(StringUtils.join(jsonList.iterator(), ","));
		}
		resSb.append("]");
		renderHtml(resSb.toString());
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getIndustryMgtId() {
		return industryMgtId;
	}

	public void setIndustryMgtId(String industryMgtId) {
		this.industryMgtId = industryMgtId;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public String getCheckedObjIds() {
		return checkedObjIds;
	}

	public void setCheckedObjIds(String checkedObjIds) {
		this.checkedObjIds = checkedObjIds;
	}

}
