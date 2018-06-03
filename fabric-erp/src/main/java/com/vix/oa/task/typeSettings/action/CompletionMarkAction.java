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
import com.vix.oa.task.typeSettings.entity.CompletionMark;

@Controller
@Scope("prototype")
public class CompletionMarkAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITaskDefineService taskDefineService;
	
	private String id;
	private CompletionMark completionMark;
	
	@Autowired
	private InitEntityBaseController initEntityBaseController;

	@Override
	public String goList() {
		return GO_LIST;
	}
	
	/** 获取完成标志列表数据  */
	public String goSingleList(){
		try {
			Map<String,Object> params = getParams();
			Pager pager = taskDefineService.findPagerByHqlConditions(getPager(), CompletionMark.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转至完成标志修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				completionMark = taskDefineService.findEntityById(CompletionMark.class,id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改完成标志操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != completionMark.getId()){
				isSave = false;
			}
			completionMark = taskDefineService.merge(completionMark);
			initEntityBaseController.initEntityBaseAttribute(completionMark);
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

	/** 处理删除完成标志操作 */
	public String deleteById() {
		try {
			CompletionMark pb = taskDefineService.findEntityById(CompletionMark.class,id);
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

	public CompletionMark getCompletionMark() {
		return completionMark;
	}

	public void setCompletionMark(CompletionMark completionMark) {
		this.completionMark = completionMark;
	}

	public InitEntityBaseController getInitEntityBaseController() {
		return initEntityBaseController;
	}

	public void setInitEntityBaseController(
			InitEntityBaseController initEntityBaseController) {
		this.initEntityBaseController = initEntityBaseController;
	}

}
