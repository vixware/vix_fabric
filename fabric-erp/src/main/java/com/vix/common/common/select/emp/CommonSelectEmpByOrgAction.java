package com.vix.common.common.select.emp;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.org.service.IEmployeeHrService;
import com.vix.common.org.service.IOrganizationUnitService;
import com.vix.core.web.Pager;


@Controller
@Scope("prototype")
public class CommonSelectEmpByOrgAction extends BaseAction {

	/** 职员姓名  */
	private String empName;
	
	/** 部门id */
	private String orgUnitId;
	
	@Autowired
	private IEmployeeHrService employeeHrService;
	
	@Autowired
	private IOrganizationUnitService organizationUnitService;
    
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
        	Map<String,Object> params =new HashMap<String, Object>();
        	Pager pager = getPager();
        	params.put("orgUnitId", orgUnitId);
        	
    		//Pager pager = roleService.findPagerByHqlConditions(getPager(), Role.class, params); 
        	if(StringUtils.isNotEmpty(empName)){
        		empName = decode(empName, "UTF-8");
            	params.put("epmName", "%"+empName+"%");//"%"+posName+"%"
        		
        	}
        	pager =  employeeHrService.findEmpByOrgUnitIdPager(pager, params);
            setPager(pager);
        }catch(Exception e){
            e.printStackTrace();
        }
        return GO_SINGLE_LIST;
    }

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getOrgUnitId() {
		return orgUnitId;
	}

	public void setOrgUnitId(String orgUnitId) {
		this.orgUnitId = orgUnitId;
	}

}
