package com.vix.nvix.system.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.share.entity.AlertNotice;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;

@Controller
@Scope("prototype")
public class NvixntWaringCenterAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private AlertNotice alertNotice;
	private String type;
	@Override
	public String goList(){
		
		return GO_LIST;
	}
	public void goSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			if(StringUtils.isNotEmpty(type)){
				params.put("type,"+SearchCondition.EQUAL, type);
				pager = vixntBaseService.findPagerByHqlConditions(pager, AlertNotice.class, params);
			}else{
				pager = vixntBaseService.findPagerByHqlConditions(pager, AlertNotice.class, params);
			}
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	public AlertNotice getAlertNotice() {
		return alertNotice;
	}
	public void setAlertNotice(AlertNotice alertNotice) {
		this.alertNotice = alertNotice;
	}
}
