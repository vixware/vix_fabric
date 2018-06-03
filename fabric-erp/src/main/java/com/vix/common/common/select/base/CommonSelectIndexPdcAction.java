package com.vix.common.common.select.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.service.IIndexPageDataConfigService;
import com.vix.core.web.Pager;


@Controller
@Scope("prototype")
public class CommonSelectIndexPdcAction extends BaseAction {

	private String indexPdcName;
	
	private String divId;
	
	private String roleId;
	
    @Autowired
	private IIndexPageDataConfigService indexPageDataConfigService;
    
    
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
			if(StringUtils.isNotEmpty(indexPdcName)){
				indexPdcName = decode(indexPdcName, "UTF-8");
				params.put("name", "%"+indexPdcName+"%");
			}
			if(StringUtils.isNotEmpty(divId)){
				params.put("divId", "%"+divId+"%");
			}
			if(StringUtils.isNotEmpty(roleId) && !roleId.equals("0")){//if(roleId!=null && roleId>0L){
				params.put("roleId", roleId);
			}
			Pager pager = indexPageDataConfigService.findSelectPdcByRolePager(getPager(), roleId, params);
        	setPager(pager);
        }catch(Exception e){
            e.printStackTrace();
        }
        return GO_SINGLE_LIST;
    }

	public String getIndexPdcName() {
		return indexPdcName;
	}

	public void setIndexPdcName(String indexPdcName) {
		this.indexPdcName = indexPdcName;
	}

	public String getDivId() {
		return divId;
	}

	public void setDivId(String divId) {
		this.divId = divId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
    
}
