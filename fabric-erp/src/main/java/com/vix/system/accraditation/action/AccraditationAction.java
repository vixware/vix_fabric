/**
 * 
 */
package com.vix.system.accraditation.action;

import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.job.entity.JobTodo;
import com.vix.inventory.inbound.entity.StockRecords;

/**
 * @author zhanghaibing
 * 
 * @date 2013-10-29
 */
@Controller
@Scope("prototype")
public class AccraditationAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	/* 待办任务 */
	private JobTodo jobTodo;


	private HttpSession session = getSession();

	private StockRecords wimStockrecords;

	/** 根据不同的待办数据跳到相应的任务处理页面 */
	public String goSaveOrUpdate() {
		session.setAttribute("jobTodoId", id);
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 弹出对话框
	 * 
	 * @return
	 */
	public String showAccraditation() {
		return "showAccraditation";
	}

	/**
	 * 
	 * 根据传入的id及单据类型跳转到相应的业务审批流程
	 * 
	 * @return
	 */
	public String goAccraditation() {
		String url = "";
		String sourceClassPk;
		String jobTodoId = String.valueOf(session.getAttribute("jobTodoId"));
		try {
			if (jobTodoId != null && !"".equals(jobTodoId)) {
				jobTodo = baseHibernateService.findEntityById(JobTodo.class, jobTodoId);
			}
			if (jobTodo != null) {
				if ("INV_INBOUND".equals(jobTodo.getSourceType())) {
					sourceClassPk = jobTodo.getSourceClassPk();
					if (sourceClassPk != null && !"".equals(sourceClassPk)) {
						wimStockrecords = baseHibernateService.findEntityById(StockRecords.class, sourceClassPk);
					}
					// 跳转到入库单处理页面
					url = "goInvInbound";
				} else if ("PUR_ORDER".equals(jobTodo.getSourceType())) {
					// 跳转到采购订单处理页面
					url = "goPurOrder";
				} else {
					url = "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public JobTodo getJobTodo() {
		return jobTodo;
	}

	public void setJobTodo(JobTodo jobTodo) {
		this.jobTodo = jobTodo;
	}

	public StockRecords getWimStockrecords() {
		return wimStockrecords;
	}

	public void setWimStockrecords(StockRecords wimStockrecords) {
		this.wimStockrecords = wimStockrecords;
	}

}
