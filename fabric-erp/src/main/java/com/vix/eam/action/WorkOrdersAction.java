package com.vix.eam.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseSecAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.eam.common.action.EamBaseAction;
import com.vix.eam.entity.EqCategory;
import com.vix.eam.entity.EqWorkOrder;

@Controller
@Scope("prototype")
public class WorkOrdersAction extends EamBaseAction {
	private static final long serialVersionUID = 1L;

	
	private EqWorkOrder workOrder;
	
	/* 工单创建 */
	public String gdCreateMgr(){
		return "gdCreateMgr";
	}
	
	public String gdCreate(){
		return "gdCreate";
	}

	public String gdGdxx(){
		try {
			if(null != id && !"".equals(id)){
				this.workOrder = this.baseHibernateService.findEntityById(EqWorkOrder.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "gdGdxx";
	}
	public String saveGdxx(){

		if(this.workOrder!=null){
			try{
				if(workOrder.getId()!=null && !workOrder.getId().equals("") && !workOrder.getId().equals("0")){
					this.baseHibernateService.update(workOrder);
				}else{
					this.baseHibernateService.save(workOrder);
				}
				
				BaseSecAction.renderText(this.workOrder.getId().toString());
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return NONE;
	}
	
	public String gdRwmx(){
		return "gdRwmx";
	}
	public String gdZyry(){
		return "gdZyry";
	}
	public String gdWlxx(){
		return "gdWlxx";
	}
	public String gdAqxx(){
		return "gdAqxx";
	}
	public String gdCbgs(){
		return "gdCbgs";
	}
	
	public String gdChooseEQ(){
		return "gdChooseEQ";
	}
	public String gdChooseRY(){
		return "gdChooseRY";
	}
	public String gdChooseWL(){
		return "gdChooseWL";
	}
	

	/* 工单列表 */
	public String lbWorkOrderMgr(){
		return "lbWorkOrderMgr";
	}
	
	public String lbWorkOrderMgrList(){
		try {
			Map<String,Object> params = new HashMap<String,Object>();
			String name = getRequest().getParameter("name");
			if(null != name && !"".equals(name)){
				params.put("eqName,"+SearchCondition.ANYLIKE,name);
			}
			
			Pager pager = this.baseHibernateService.findPagerByHqlConditions(getPager(), EqWorkOrder.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "lbWorkOrderMgrList";
	}
	
	public String lbWorkOrderMgrDetail(){
		try {
			if(null != id && !"".equals(id)){
				this.workOrder = this.baseHibernateService.findEntityById(EqWorkOrder.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "lbWorkOrderMgrDetail";
	}
	

	/* 工单调度审批 */
	public String spReview(){
		return "spReview";
	}

	public String spReviewList(){
		try {
			Map<String,Object> params = getParams();
			if (null != parentId && !"".equals(parentId)){
				params.put("parentCategory.id,"+SearchCondition.EQUAL,parentId);
			}
			Pager pager = this.baseHibernateService.findPagerByHqlConditions(getPager(), EqCategory.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "spReviewList";
	}

	public String spReviewDetail(){
		return "spReviewDetail";
	}
	

	/* 工单报告 */
	public String bgReport(){
		return "bgReport";
	}
	

	/* 完工报告管理 */
	public String wgFinalReport(){
		return "wgFinalReport";
	}
	

	/* 工单档案管理 */
	public String daArchiveRecord(){
		return "daArchiveRecord";
	}

	public String daArchiveRecordList(){
		try {
			Map<String,Object> params = getParams();
			if (null != parentId && !"".equals(parentId)){
				params.put("parentCategory.id,"+SearchCondition.EQUAL,parentId);
			}
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), EqCategory.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "daArchiveRecordList";
	}

	public String daArchiveRecordDetail(){
		return "daArchiveRecordDetail";
	}
	

	/* 统计分析 */
	public String tjStatisticMgr(){
		return "tjStatisticMgr";
	}


	public EqWorkOrder getWorkOrder() {
		return workOrder;
	}

	public void setWorkOrder(EqWorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
