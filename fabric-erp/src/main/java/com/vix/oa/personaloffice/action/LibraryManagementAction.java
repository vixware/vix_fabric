package com.vix.oa.personaloffice.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;

@Controller
@Scope("prototype")
public class LibraryManagementAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	public String showTool() {
		return "showTool";
	}
	
	public String showEasyUiTree() {
		return "showEasyUiTree";
	}
	
	public String showTask(){
		return "task";
	}
	
	public String showNews(){
		return "news";
	}
	
	public String showBug(){
		return "bug";
	}
	
	public String showBizForm(){
		return "bizForm";
	}
	
	public String showCanlendar() {
		return "showCanlendar";
	}
	
	public String showTimeline() {
		return "showTimeline";
	}
	
	public String showMail() {
		return "showMail";
	}
	
	public String showLog() {
		return "showLog";
	}
	
	public String showOaList(){
		return "oaList";
	}
	public String addOaListItem(){
		String tag = getRequestParameter("tag");
		return tag;
	}
	
	public String showDaily() {
		String tag = getRequestParameter("tag");
		if("1".equals(tag)){
			return "daily1";
		}else if("2".equals(tag)){
			return "daily2";
		}else{
			return "daily3";
		}
	}
	
	public String showOrders(){
		return "showOrders";
	}
	
	public String showStatistic(){
		return "showStatistic";
	}
	
	public String showApproval() {
		String tag = getRequestParameter("tag");
		if("1".equals(tag)){
			return "approval1";
		}else{
			return "approval2";
		}
	}
	
	public String showCustomerInfo(){
		return "customerInfo";
	}
	
	public void getEasyUiTreeJson(){
		StringBuilder json = new StringBuilder("{\"total\":50001,\"rows\":[");
		for(int i = 0;i <100;i++){
			json.append("{\"id\":"+(1+i)+",\"code\":\"code1\",\"name\":\"name1\",\"addr\":\"address1\",\"state\":\"closed\"},");
			for(int j = 0;j<20;j++){
				json.append("{\"id\":"+(j+i*100+1)+",\"code\":\"code11\",\"name\":\"name11\",\"addr\":\"address11\",\"EU_parentId\":"+(i+1)+"},");
			}
		}
		json.append("{\"id\":190920,\"code\":\"code120\",\"name\":\"name120\",\"addr\":\"address120\",\"EU_parentId\":10}");
		json.append("]}");
		renderJson(json.toString());
	}
}
