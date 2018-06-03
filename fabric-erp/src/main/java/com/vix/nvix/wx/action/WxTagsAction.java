package com.vix.nvix.wx.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.nvix.common.base.action.VixntBaseAction;
@Controller
@Scope("prototype")
public class WxTagsAction extends VixntBaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Override
	public String goList(){
		return GO_LIST;
	}
	public void goSingleList(){
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String goSaveOrUpdate(){
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}
	public void saveOrUpdate(){
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void deleteById(){
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
