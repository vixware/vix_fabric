package com.vix.system.industrymanagement.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.system.industrymanagement.service.IIndustryManagementService;


@Controller
@Scope("prototype")
public class IndustryManagementSelectAction extends BaseAction {

	private String name;
	
	@Autowired
	private IIndustryManagementService industryManagementService;
    
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
        	if(StrUtils.isNotEmpty(name)){
        		params.put("roleName", name);
        	}
        	
        	Pager pager = industryManagementService.findIndustrymanagementPager(getPager(), params);
        	setPager(pager);
        }catch(Exception e){
            e.printStackTrace();
        }
        return GO_SINGLE_LIST;
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
