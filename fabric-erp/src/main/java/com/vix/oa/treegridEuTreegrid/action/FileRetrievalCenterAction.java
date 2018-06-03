package com.vix.oa.treegridEuTreegrid.action;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

@Controller
@Scope("prototype")
public class FileRetrievalCenterAction extends BaseAction{
	public static final String GO_PRUCHASE_LIST_CONTENT ="goListContent";/** 列表数据页 */
	private static final long serialVersionUID = 1L;
	
	public void executeEsper() throws Exception{
		
	}
	@Override
	public String goList(){
	
		return GO_LIST;
	}
	
	
 
	/** 跳转至用户修改页面 */
	 public String goSaveOrUpdate() {
	        try {
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return GO_SAVE_OR_UPDATE;
	   }
}
