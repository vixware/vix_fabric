package com.vix.oa.task.oec.action;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.oa.task.oec.controller.NissinManagementController;
import com.vix.oa.task.taskDefinition.entity.VixTask;

/**
 * 
 * @ClassName: NissinManagementAction
 * @Description: 日清管理 
 * @author chenzhengwen
 * @author w_a_533@163.com 
 * @date 2014-2-28 下午2:09:52
 */
@Controller
@Scope("prototype")
public class NissinManagementAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Logger logger = Logger.getLogger(NissinManagementController.class);
	
	@Autowired
	private NissinManagementController nissinManagementController;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	
	private VixTask taskDefinition;
	
	private String id;
	
	private String pageNo;
	
	private Date updateTime;
	
	/** 任务定义*/
	private List<VixTask> taskDefinitionList;
		
	
	/** 跳转到列表页面 */
	@Override
	public String goList() {
		try {
			taskDefinitionList = nissinManagementController.doListSalesOrderIndex();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return GO_LIST;
	}
	
	/** 获取日清列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			//状态
			String isPublish = getRequestParameter("isPublish");
			if (null != isPublish && !"".equals(isPublish)) {
				params.put("isPublish," + SearchCondition.EQUAL, Integer.parseInt(isPublish));
			}			
			// 按最近使用
			String endTime = getRequestParameter("endTime");
			if (endTime != null && !"".equals(endTime)) {
				params.put("endTime," + SearchCondition.MORETHAN, getLeastRecentlyUsedTime(endTime));
			}
			//pubids包含当前登录人id
			String employeeId = SecurityUtil.getCurrentEmpId();
			params.put("pubIds," + SearchCondition.ANYLIKE, ","+employeeId+",");
			/** 根据时间进行倒序排序 */
			if (null == getPager().getOrderField() || "".equals(getPager().getOrderField())) {
				getPager().setOrderField("startTime");
				getPager().setOrderBy("desc");
			}
			Pager pager = nissinManagementController.doSubSingleList(params,getPager());
			logger.info("获取任务定义列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	public String goSearch() {
		return "goSearch";
	}
	
	/** 获取搜索任务定义列表数据 */	
	public String goSearchList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = null;
			String i = getRequestParameter("i");
			// 主题
			String questName = getRequestParameter("questName");
			if (null != questName && !"".equals(questName)) {
				questName = URLDecoder.decode(questName, "utf-8");
			}
			// 
			String executiveAgency = getRequestParameter("executiveAgency");
			if (null != executiveAgency && !"".equals(executiveAgency)) {
				executiveAgency = URLDecoder.decode(executiveAgency, "utf-8");
			}
			if (null != pageNo && !"".equals(pageNo)) {
				getPager().setPageNo(Integer.parseInt(pageNo));
			}
			// 简单搜索
			if ("0".equals(i)) {
				params.put("questName," + SearchCondition.ANYLIKE, questName);
				pager = nissinManagementController.goSearchList(params, getPager());
			}
			// 高级搜索
			else {
				if (null != executiveAgency && !"".equals(executiveAgency)) {
					params.put("executiveAgency," + SearchCondition.ANYLIKE, executiveAgency);
				}
				if (null != questName && !"".equals(questName)) {
					params.put("questName," + SearchCondition.ANYLIKE, questName);
				}
				pager = nissinManagementController.goSingleList(params, getPager());
			}
			logger.info("获取任务搜索列表数据页");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	
	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate(){
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				taskDefinition = nissinManagementController.doListEntityById(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return GO_SAVE_OR_UPDATE;
	}
	
	
	/** 处理新增修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(taskDefinition.getId()) && !"".equals(taskDefinition.getId())) {
				isSave = false;
			}

			initEntityBaseController.initEntityBaseAttribute(taskDefinition);
			taskDefinition = nissinManagementController.doSaveSalesOrder(taskDefinition);
			logger.info("新增！");
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}
	
	/** 处理删除操作 */
	public String deleteById() {
		try {
			VixTask pb = nissinManagementController.findEntityById(id);
			if (null != pb) {
				nissinManagementController.doDeleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}



	public NissinManagementController getNissinManagementController() {
		return nissinManagementController;
	}

	public void setNissinManagementController(
			NissinManagementController nissinManagementController) {
		this.nissinManagementController = nissinManagementController;
	}

	public InitEntityBaseController getInitEntityBaseController() {
		return initEntityBaseController;
	}

	public void setInitEntityBaseController(
			InitEntityBaseController initEntityBaseController) {
		this.initEntityBaseController = initEntityBaseController;
	}

	public VixTask getTaskDefinition() {
		return taskDefinition;
	}


	public void setTaskDefinition(VixTask taskDefinition) {
		this.taskDefinition = taskDefinition;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<VixTask> getTaskDefinitionList() {
		return taskDefinitionList;
	}

	public void setTaskDefinitionList(List<VixTask> taskDefinitionList) {
		this.taskDefinitionList = taskDefinitionList;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

}
