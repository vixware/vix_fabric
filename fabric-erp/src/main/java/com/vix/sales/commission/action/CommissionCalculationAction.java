package com.vix.sales.commission.action;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.sales.commission.entity.CommissionPlan;

@Controller
@Scope("prototype")
public class CommissionCalculationAction extends BaseAction{
	private static final long serialVersionUID = 1L;

	
	private String id;
	private String pageNo;
	
	@Override
	public String goList(){
		return GO_LIST;
	}
	
	/** 选择计算条件 */
	public String goChooseCondition(){
		return "chooseCondition";
	}
	
	public String goSaveOrUpdate(){
		return "goSaveOrUpdate";
	}
	
	public String goCalculationList(){
		try {
			Map<String,Object> params = getParams();
//			params.put("saleOrg.fs,"+ SearchCondition.NOEQUAL, "");
			if(null != pageNo && !"".equals(pageNo)){
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), CommissionPlan.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	
}