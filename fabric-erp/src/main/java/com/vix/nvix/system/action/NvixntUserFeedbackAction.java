package com.vix.nvix.system.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.system.entity.UserFeedback;
/**
 * 用户反馈
 * @author jackie
 *
 */
@Controller
@Scope("prototype")
public class NvixntUserFeedbackAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserFeedback userFeedback;
	
	private String id;
	
	public void goSingleList(){
		try {
			Map<String, Object> params = getParams();
			Pager pager = getPager();
			
			pager = vixntBaseService.findPagerByHqlConditions(pager, UserFeedback.class, params);
			renderDataTable(pager);
 		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdate(){
		try {
			if(StringUtils.isNotEmpty(id)){
				userFeedback = vixntBaseService.findEntityById(UserFeedback.class, id);
			}else{
				userFeedback = new UserFeedback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	public void saveOrUpdate(){
		boolean isSave = true;
		try {
			if(StringUtils.isNotEmpty(userFeedback.getId())){
				isSave = false;
			}
			userFeedback.setCreateTime(new Date());
			userFeedback.setUpdateTime(new Date());
			initEntityBaseController.initEntityBaseAttribute(userFeedback);
			userFeedback = vixntBaseService.merge(userFeedback);
			if(isSave){
				renderText(OPER_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if(isSave){
				renderText(OPER_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
			e.printStackTrace();
		}
	}
	public void deleteById(){
		try {
			if(StringUtils.isNotEmpty(id)){
				userFeedback = vixntBaseService.findEntityById(UserFeedback.class, id);
				if(userFeedback != null){
					vixntBaseService.deleteByEntity(userFeedback);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			renderText(DELETE_FAIL);
			e.printStackTrace();
		}
	}
	public UserFeedback getUserFeedback() {
		return userFeedback;
	}

	public void setUserFeedback(UserFeedback userFeedback) {
		this.userFeedback = userFeedback;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}
}
