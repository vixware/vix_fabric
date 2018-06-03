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
import com.vix.oa.task.typeSettings.entity.TaskLevel;

@Controller
@Scope("prototype")
public class TaskLevelAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITaskDefineService taskDefineService;
	
	private String id;
	private TaskLevel taskLevel;
	
	@Autowired
	private InitEntityBaseController initEntityBaseController;

	@Override
	public String goList() {
		return GO_LIST;
	}
	
	/** 获取任务层级列表数据  */
	public String goSingleList(){
		try {
			Map<String,Object> params = getParams();
			Pager pager = taskDefineService.findPagerByHqlConditions(getPager(), TaskLevel.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转至任务层级修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				taskLevel = taskDefineService.findEntityById(TaskLevel.class,id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改任务层级操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != taskLevel.getId()){
				isSave = false;
			}
			taskLevel = taskDefineService.merge(taskLevel);
			initEntityBaseController.initEntityBaseAttribute(taskLevel);
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

	/** 处理删除任务层级操作 */
	public String deleteById() {
		try {
			TaskLevel pb = taskDefineService.findEntityById(TaskLevel.class,id);
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

	public TaskLevel getTaskLevel() {
		return taskLevel;
	}

	public void setTaskLevel(TaskLevel taskLevel) {
		this.taskLevel = taskLevel;
	}

	public InitEntityBaseController getInitEntityBaseController() {
		return initEntityBaseController;
	}

	public void setInitEntityBaseController(
			InitEntityBaseController initEntityBaseController) {
		this.initEntityBaseController = initEntityBaseController;
	}

}
