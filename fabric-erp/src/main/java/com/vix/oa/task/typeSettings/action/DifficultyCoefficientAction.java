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
import com.vix.oa.task.typeSettings.entity.DifficultyCoefficient;

@Controller
@Scope("prototype")
public class DifficultyCoefficientAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITaskDefineService taskDefineService;
	
	private String id;
	private DifficultyCoefficient difficultyCoefficient;
	
	@Autowired
	private InitEntityBaseController initEntityBaseController;

	@Override
	public String goList() {
		return GO_LIST;
	}
	
	/** 获取难度系数列表数据  */
	public String goSingleList(){
		try {
			Map<String,Object> params = getParams();
			Pager pager = taskDefineService.findPagerByHqlConditions(getPager(), DifficultyCoefficient.class, params);
			setPager(pager);
		}catch(Exception e){
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}
	
	/** 跳转至难度系数修改页面 */
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){
				difficultyCoefficient = taskDefineService.findEntityById(DifficultyCoefficient.class,id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改难度系数操作  */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if(null != difficultyCoefficient.getId()){
				isSave = false;
			}
			difficultyCoefficient = taskDefineService.merge(difficultyCoefficient);
			initEntityBaseController.initEntityBaseAttribute(difficultyCoefficient);
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

	/** 处理删除难度系数操作 */
	public String deleteById() {
		try {
			DifficultyCoefficient pb = taskDefineService.findEntityById(DifficultyCoefficient.class,id);
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

	public DifficultyCoefficient getDifficultyCoefficient() {
		return difficultyCoefficient;
	}

	public void setDifficultyCoefficient(DifficultyCoefficient difficultyCoefficient) {
		this.difficultyCoefficient = difficultyCoefficient;
	}

	public InitEntityBaseController getInitEntityBaseController() {
		return initEntityBaseController;
	}

	public void setInitEntityBaseController(
			InitEntityBaseController initEntityBaseController) {
		this.initEntityBaseController = initEntityBaseController;
	}

}
