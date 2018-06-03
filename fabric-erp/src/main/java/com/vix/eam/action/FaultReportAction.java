package com.vix.eam.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.eam.common.action.EamBaseAction;


@Controller
@Scope("prototype")
public class FaultReportAction extends EamBaseAction {
	private static final long serialVersionUID = 1L;

	
	
	/* 故障缺陷报告/维修通知单 */
	public String gzReportMgr(){
		return "gzReportMgr";
	}

	public String gzReportMgrList(){
		return "gzReportMgrList";
	}

	public String gzReportMgrDetail(){
		return "gzReportMgrDetail";
	}
	

	/* 故障缺陷报告审批单 */
	public String spReview(){
		return "spReview";
	}

	public String spReviewList(){
		return "spReviewList";
	}

	public String spReviewDetail(){
		return "spReviewDetail";
	}
	

	/* 统计分析 */
	public String tjStatisticMgr(){
		return "tjStatisticMgr";
	}


	
}
