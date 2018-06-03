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
import com.vix.oa.task.typeSettings.entity.ProgressSituation;

@Controller
@Scope("prototype")
public class ProgressSituationAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITaskDefineService taskDefineService;
	
	private String id;
	private ProgressSituation progressSituation;
	
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	
	/** 获取进度情况列表数据  */
	public String goListContent(){
		try {
			Map<String,Object> params = getParams();
			Pager pager = taskDefineService.findPagerByHqlConditions(getPager(), ProgressSituation.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转至进度情况修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				progressSituation = taskDefineService.findEntityById(ProgressSituation.class,id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改进度情况操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != progressSituation.getId()){
				isSave = false;
			}
			progressSituation = taskDefineService.merge(progressSituation);
			initEntityBaseController.initEntityBaseAttribute(progressSituation);
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

	/** 处理删除进度情况操作 */
	public String deleteById() {
		try {
			ProgressSituation pb = taskDefineService.findEntityById(ProgressSituation.class,id);
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

	public ProgressSituation getProgressSituation() {
		return progressSituation;
	}

	public void setProgressSituation(ProgressSituation progressSituation) {
		this.progressSituation = progressSituation;
	}

	public InitEntityBaseController getInitEntityBaseController() {
		return initEntityBaseController;
	}

	public void setInitEntityBaseController(
			InitEntityBaseController initEntityBaseController) {
		this.initEntityBaseController = initEntityBaseController;
	}

}
