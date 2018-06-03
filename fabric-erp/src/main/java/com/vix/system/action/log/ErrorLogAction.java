package com.vix.system.action.log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.log.entity.ErrorLog;
import com.vix.common.log.service.ILogService;
import com.vix.core.utils.CollectionUtils;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;

@Controller
@Scope("prototype")
public class ErrorLogAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	private ErrorLog errorLog;
	
	@Autowired
	private ILogService logService;
	public void executeDrools() throws Exception{
		
	}
	
	public void executeEsper() throws Exception{
		
	}
	@Override
	public String goList(){
		try {
			String pageNo = getRequestParameter("pageNum");
			String numPerPage = getRequestParameter("numPerPage");
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.valueOf(pageNo));
			}
			if(null != numPerPage && !"".equals(numPerPage)){
				getPager().setPageSize(Integer.valueOf(numPerPage));
			}
			if(null != errorLog){
				Map<String,Object> params = new HashMap<String,Object>();
				if(!StrUtils.objectIsNull(errorLog.getTitle())){
					params.put("title", errorLog.getTitle());
				}
 
				Pager pager = logService.findPagerByHqlConditions(getPager(), ErrorLog.class, params);
				setPager(pager);
			}else{
				Pager pager = logService.findPagerByHql(getPager(), ErrorLog.class);
				setPager(pager);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return UPDATE;
	}
	
	public String deleteById(){
		String id = getRequest().getParameter("id");
		if(null != id && !"".equals(id)){
			try {
				logService.deleteById(ErrorLog.class, String.valueOf(id));
				 
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return UPDATE;
	}
	
	public String deleteByIds(){
		String ids = getRequest().getParameter("ids");
		if(null != ids && !"".equals(ids)){
			try {
				List<String> list = CollectionUtils.convertStringToLongList(ids, ",");
				logService.batchDelete(ErrorLog.class, list);
				 
			} catch (Exception e) {
				e.printStackTrace();
			 
			}
		}
		return UPDATE;
	}

	public ErrorLog getErrorLog() {
		return errorLog;
	}

	public void setErrorLog(ErrorLog errorLog) {
		this.errorLog = errorLog;
	}
}
