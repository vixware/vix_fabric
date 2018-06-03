package com.vix.crm.analyse.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.crm.analyse.service.IRfmAnalyseService;
import com.vix.crm.analyse.wraper.RfmWraper;

@Controller
@Scope("prototype")
public class RfmAnalyseAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IRfmAnalyseService rfmAnalyseService;
	
	private String year;
	private List<List<RfmWraper>> rfmList;
	
	public String showAnalyseData(){
		String tenlantId = SecurityUtil.getCurrentUserTenantId();
		try{
			if(null == year || "".equals(year)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
				year = sdf.format(new Date());
			}
			rfmList = rfmAnalyseService.findRfmByDate(year, tenlantId);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "showAnalyseData";
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<List<RfmWraper>> getRfmList() {
		return rfmList;
	}

	public void setRfmList(List<List<RfmWraper>> rfmList) {
		this.rfmList = rfmList;
	}
}
