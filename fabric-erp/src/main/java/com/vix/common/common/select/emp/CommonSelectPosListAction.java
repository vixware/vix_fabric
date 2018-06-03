package com.vix.common.common.select.emp;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.hr.position.service.IOrgPositionService;

/**
 * 岗位选择(部门)
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class CommonSelectPosListAction extends BaseAction{

	private String id;
	
	@Resource(name="orgPositionService")
	private IOrgPositionService orgPositionService;
	
	/** 部门id */
	private String orgUnitId;
	
	/** 岗位名称  */
	private String posName;
	
	//private String checkedObjIds;
	
    
    /** 获取列表数据  */
    public String goSingleList(){
        try {
        	if(StringUtils.isEmpty(chkStyle)){
    			chkStyle = "checkbox";
    		}
        	
        	 Pager pager = getPager();
             //if(StringUtils.isNotEmpty(treeType) && (null != id || id != 0l) ){
             if(StringUtils.isNotEmpty(posName)){
            	 posName = decode(posName, "UTF-8");
             }
             
             pager =  orgPositionService.findOrgPositionPager(pager, orgUnitId,posName);
             //pager = employeeHrService.findEmp4OrgDrpPager(pager, String.valueOf(treeTypeChar), orgViewId, empName);
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

	public String getOrgUnitId() {
		return orgUnitId;
	}

	public void setOrgUnitId(String orgUnitId) {
		this.orgUnitId = orgUnitId;
	}

	public String getPosName() {
		return posName;
	}

	public void setPosName(String posName) {
		this.posName = posName;
	}
    
}
