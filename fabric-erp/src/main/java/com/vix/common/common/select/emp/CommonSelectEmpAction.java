package com.vix.common.common.select.emp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.entity.OrgView;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.common.org.service.IOrganizationUnitService;
import com.vix.core.web.Pager;

/**
 * 职员选择类
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class CommonSelectEmpAction extends BaseAction {

	@Autowired
	private IEmployeeHrService employeeHrService;

	@Autowired
	private IOrganizationUnitService organizationUnitService;
	
	/**  职员姓名 */
	private String empName;
	
	private String id;
    
    private String parentId;
    
    /**
     * 判断节点是否选中
     */
    private String checkedObjIds;
    
    @Override
    public String goList(){
    	if(StringUtils.isEmpty(chkStyle)){
			chkStyle = "checkbox";
		}
    	    
		return GO_LIST;
	}
    
    /** 获取列表数据 */
    public String goSingleList(){
        try {
        	if(StringUtils.isEmpty(chkStyle)){
    			chkStyle = "checkbox";
    		}
        	    
        	
            Map<String,Object> params = new HashMap<String, Object>();
            //String treeType = getRequestParameter("treeType");
            
            Pager pager = getPager();
            //if(StringUtils.isNotEmpty(treeType) && (null != id || id != 0l) ){
            if(StringUtils.isNotEmpty(empName)){
            	empName = decode(empName, "UTF-8");
            	params.put("epmName", "%"+empName+"%");//"%"+posName+"%"
        		pager =  employeeHrService.findEmpAccountPager(pager,null, params);
        	}else if(StringUtils.isNotEmpty(id)){	
            	String orgViewIdStr = id.substring(0, id.length()-1);
            	String orgViewId = orgViewIdStr;
            	
            	char treeTypeChar = id.charAt( id.length()-1);
            	if(treeTypeChar=='O'){
            		pager =  employeeHrService.findEmpAccountPager(pager, orgViewId,null);//(pager, orgViewId);
            	}
            	
            	
            }
            setPager(pager);

            
            if(StringUtils.isNotEmpty(checkedObjIds)){
            	char lastOne = checkedObjIds.charAt(checkedObjIds.length()-1);
            	if(lastOne!=','){
            		checkedObjIds = checkedObjIds + ",";
            	}
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return GO_SINGLE_LIST;
    }
     
   /* 
    
    public String goSingleList(){
        try {
            Map<String,Object> params = new HashMap<String, Object>();
            //String treeType = getRequestParameter("treeType");
            
            Pager pager = getPager();
            pager =  employeeHrService.findEmpAccountPager(pager, params);
            //if(StringUtils.isNotEmpty(treeType) && (null != id || id != 0l) ){
            setPager(pager);

        }catch(Exception e){
            e.printStackTrace();
        }
        return GO_SINGLE_LIST;
    }
    */
    
    /** 树形结构JSON */
    public void findTreeToJson(){
        try{
            List<OrgView> listOrganization = new ArrayList<OrgView>();
            /** 获取查询参数 */
            Map<String,Object> params = getParams();
            listOrganization = organizationUnitService.findOrgViewList(id);
            
            StringBuilder strSb = new StringBuilder();
            strSb.append("[");
            /** 递归方式 **/
            int count = listOrganization.size();
            for(int i =0; i<count; i++){
            	OrgView org = listOrganization.get(i);
            	
            	List<OrgView> subList = organizationUnitService.findOrgViewList(org.getId());
                if(subList!=null && subList.size() > 0){
                    strSb.append("{id:\"");
                    strSb.append(org.getId());
                    strSb.append("\",name:\"");
                    strSb.append(org.getOrgName());
                    strSb.append("\",open:true,isParent:true}");
                }else{
                    strSb.append("{id:\"");
                    strSb.append(org.getId());
                    strSb.append("\",name:\"");
                    strSb.append(org.getOrgName());
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
            loadEmployees(id, treeType);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    private void loadEmployees(String nodeId,String nodeTreeType){
        try{
            List<OrgView> orgUnitList = organizationUnitService.findOrgViewList(nodeId);
            
            if(orgUnitList==null){
                orgUnitList = new LinkedList<OrgView>();
            }
            
            
            StringBuilder strSb = new StringBuilder();
            strSb.append("[");
            /** 递归方式 **/
            int count = orgUnitList.size();
            for(int i =0; i<count; i++){
            	OrgView org = orgUnitList.get(i);
            	
            	List<OrgView> subList = organizationUnitService.findOrgViewList(org.getId());
                if( subList!=null && subList.size() > 0){
                    strSb.append("{id:\"");
                    strSb.append(org.getId());
                    strSb.append("\",treeType:\"");
                    strSb.append(org.getOrgType());
                    strSb.append("\",name:\"");
                    strSb.append(org.getOrgName());
                    strSb.append("\",open:false,isParent:true}");
                }else{
                    strSb.append("{id:\"");
                    strSb.append(org.getId());
                    strSb.append("\",treeType:\"");
                    strSb.append(org.getOrgType());
                    strSb.append("\",name:\"");
                    strSb.append(org.getOrgName());
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
    
    public String goChooseOrganization(){
        return "goChooseOrganization";
    }

	public IEmployeeHrService getEmployeeHrService() {
		return employeeHrService;
	}

	public void setEmployeeHrService(IEmployeeHrService employeeHrService) {
		this.employeeHrService = employeeHrService;
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

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getCheckedObjIds() {
		return checkedObjIds;
	}

	public void setCheckedObjIds(String checkedObjIds) {
		this.checkedObjIds = checkedObjIds;
	}

}
