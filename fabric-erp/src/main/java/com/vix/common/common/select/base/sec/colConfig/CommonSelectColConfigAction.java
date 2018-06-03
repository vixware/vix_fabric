package com.vix.common.common.select.base.sec.colConfig;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseSecAction;
import com.vix.common.security.entity.DataResColConfig;
import com.vix.common.securityDca.service.IDataColSecService;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;


@Controller
@Scope("prototype")
public class CommonSelectColConfigAction extends BaseSecAction {

	private String name;
	
	@Autowired
	private IDataColSecService dataColSecService;
    
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
        		params.put("configName", name);
        	}
    		//Pager pager = roleService.findPagerByHqlConditions(getPager(), Role.class, params); 
			Pager pager = dataColSecService.findPagerByHqlConditions(getPager(), DataResColConfig.class, params);
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
