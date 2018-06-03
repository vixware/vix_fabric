package com.vix.oa.task.typeSettings.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.web.Pager;
import com.vix.oa.task.taskDefinition.service.ITaskDefineService;
import com.vix.oa.task.typeSettings.entity.TaskType;

@Controller
@Scope("prototype")
public class TaskTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITaskDefineService taskDefineService;
	
	private String id;
	private TaskType taskType;
	
	@Autowired
	private InitEntityBaseController initEntityBaseController;

	@Override
	public String goList() {
		return GO_LIST;
	}
	
	/** 获取任务类型列表数据  */
	public String goSingleList(){
		try {
			Map<String,Object> params = getParams();
			Pager pager = taskDefineService.findPagerByHqlConditions(getPager(), TaskType.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转至任务类型修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				taskType = taskDefineService.findEntityById(TaskType.class,id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改任务类型操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != taskType.getId()){
				isSave = false;
			}
			taskType = taskDefineService.merge(taskType);
			initEntityBaseController.initEntityBaseAttribute(taskType);
			if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
			 if(isSave){
				renderText(SAVE_SUCCESS);
			}else{
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(isSave){
				renderText(SAVE_FAIL);
			}else{
				renderText(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理删除任务类型操作 */
	public String deleteById() {
		try {
			TaskType pb = taskDefineService.findEntityById(TaskType.class,id);
			if(null != pb){
				taskDefineService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			}else{
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ITaskDefineService getTaskDefineService() {
		return taskDefineService;
	}

	public void setTaskDefineService(ITaskDefineService taskDefineService) {
		this.taskDefineService = taskDefineService;
	}


	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	public InitEntityBaseController getInitEntityBaseController() {
		return initEntityBaseController;
	}

	public void setInitEntityBaseController(
			InitEntityBaseController initEntityBaseController) {
		this.initEntityBaseController = initEntityBaseController;
	}

}
