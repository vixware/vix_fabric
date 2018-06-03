package com.vix.common.common.select.role;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.service.IRoleService;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;


@Controller
@Scope("prototype")
public class CommonSelectRoleAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
    @Autowired
    private IRoleService roleService;
    
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
        		name = decode(name, "UTF-8");
        		params.put("roleName", name);
        	}
        	
    		//Pager pager = roleService.findPagerByHqlConditions(getPager(), Role.class, params); 
        	Pager pager = roleService.findCommonSelectRolePager(getPager(), params);
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
