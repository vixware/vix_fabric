package com.vix.common.common.select.bizOrg;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.common.select.bizOrg.vo.BizOrgEmpVO;
import com.vix.common.org.entity.BusinessView;
import com.vix.common.org.service.IBusinessViewService;
import com.vix.common.security.util.SecurityUtil;

@Controller
@Scope("request")
public class CommonSelectEmpByBizOrgAction extends BaseAction {

    private static final long serialVersionUID = 1L;

    @Autowired
    private IBusinessViewService businessViewService;
    
    /** 改人员树的业务视图标识 */
    private String bizViewCode;
    
    //private String bizViewId;
    /** bizViewId+"V" */
    private String bizViewIdStr = "";
    
    private String treeId;
    
	/**
	 * 选择页面
	 * @return
	 * @throws Exception 
	 */
	public String goChooseEmp() throws Exception{
		if(StringUtils.isEmpty(chkStyle)){
			chkStyle = "checkbox";
		}
		
		if(StringUtils.isNotEmpty(bizViewCode)){
			BusinessView bv = businessViewService.findBizOrgViewByCode(bizViewCode);
			bizViewIdStr = bv.getId()+"V";
		}
		
        return "goChooseEmp";
    }
	
    /**
     * 
     * @Title: findOrgAndUnitTreeToJson
     * @Description: 加载公司和部门、分销的混合树
     * @param     设定文件
     * @return void    返回类型
     * @throws
     */
    public void findEmpBizOrgTreeToJson(){
        try{
        	if(StringUtils.isEmpty(bizViewIdStr) && StringUtils.isNotEmpty(bizViewCode)){
    			BusinessView bv = businessViewService.findBizOrgViewByCode(bizViewCode);
    			bizViewIdStr = bv.getId()+"V";
    		}
        	
        	String bizOrgIdStr = "";
        	if(StringUtils.isNotEmpty(treeId)){
        		bizOrgIdStr = StringUtils.substringBefore(treeId, "_");
        	}
        	loadBizOrg(bizOrgIdStr,bizViewIdStr,SecurityUtil.getCurrentUserTenantId());
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    private void loadBizOrg(String bizOrgIdStr,String bizViewIdStr,String tenantId){
        try{
        	//findEmpByBizOrg(String bizOrgIdStr,String bizOrgViewId,String tenantId)
        	List<BizOrgEmpVO> empList = businessViewService.findEmpByBizOrg(bizOrgIdStr, bizViewIdStr, tenantId);
            
            if(empList==null){
            	empList = new ArrayList<BizOrgEmpVO>();
            }
            
            
            StringBuilder strSb = new StringBuilder();
            strSb.append("[");
            /** 递归方式 **/
            int count = empList.size();
            for(int i =0; i<count; i++){
            	BizOrgEmpVO emp = empList.get(i);
            	Long subCount = emp.getSubCount();
                if( subCount > 0){
                    strSb.append("{treeId:\"");
                    strSb.append(emp.getTreeId());
                    strSb.append("\",name:\"");
                    //strSb.append(emp.getTreeName()+" <input type='checkbox' name='empNode_r_"+emp.getTreeId()+"' />只读  <input type='checkbox' name='empNode_m_"+emp.getTreeId()+"' />修改  ");
                    strSb.append(emp.getTreeName());
                    strSb.append("\",empId:");
                    strSb.append(emp.getEmpId());
                    strSb.append(",bizOrgId:");
                    strSb.append(emp.getBizOrgId());
                    strSb.append(",bizViewIdStr:\"");
                    strSb.append(emp.getBizViewIdStr());
                    strSb.append("\",open:false,isParent:true}");
                }else{
                    strSb.append("{treeId:\"");
                    strSb.append(emp.getTreeId());
                    strSb.append("\",name:\"");
                    strSb.append(emp.getTreeName());
                    strSb.append("\",empId:");
                    strSb.append(emp.getEmpId());
                    strSb.append(",bizOrgId:");
                    strSb.append(emp.getBizOrgId());
                    strSb.append(",bizViewIdStr:\"");
                    strSb.append(emp.getBizViewIdStr());
                    strSb.append("\",open:false,isParent:false}");
                }
                if(i < count -1){
                    strSb.append(",");
                }
            }
            strSb.append("]");
            //renderHtml(strSb.toString());
            renderText(strSb.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

	public String getBizViewCode() {
		return bizViewCode;
	}

	public void setBizViewCode(String bizViewCode) {
		this.bizViewCode = bizViewCode;
	}

	public String getTreeId() {
		return treeId;
	}

	public void setTreeId(String treeId) {
		this.treeId = treeId;
	}

	public String getBizViewIdStr() {
		return bizViewIdStr;
	}

	public void setBizViewIdStr(String bizViewIdStr) {
		this.bizViewIdStr = bizViewIdStr;
	}

}
