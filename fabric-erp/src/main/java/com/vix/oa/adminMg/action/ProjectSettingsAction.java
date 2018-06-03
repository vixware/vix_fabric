package com.vix.oa.adminMg.action;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.web.Pager;
import com.vix.oa.adminMg.controller.WorkPlanController;
import com.vix.oa.adminMg.entity.WorkPlanType;


@Controller
@Scope("prototype")
public class ProjectSettingsAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	Logger logger = Logger.getLogger(WorkPlanController.class);

	@Autowired
	private WorkPlanController workPlanController;
	private List<WorkPlanType> workPlanTypeList;
	private WorkPlanType workPlanType;
	private String id;
	private WorkPlanType entity;
	private WorkPlanType entityForm;
	
	/** 获取列表数据 */
	public String goListType() {
		try {
			workPlanTypeList = baseHibernateService.findAllByEntityClass(WorkPlanType.class);
			Map<String, Object> params = getParams();
			Pager pager = workPlanController.doSubSingleList2(params, getPager());
			logger.info("获取工作计划列表数据");
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_LIST_TYPE;
	}
	
	public String goSaveOrUpdate() {
		try {
			if(StringUtils.isNotEmpty(id) && !id.equals("0")){//if (null != id && id.longValue() > 0) {
				workPlanType = workPlanController.doListEntityById2(id);
				logger.info("");
			}
		} catch (Exception e) {
			setMessage(e.getMessage());
		}
		return "goSaveOrUpdate";
	}
	
	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (workPlanType != null && null != workPlanType.getId()) {
				isSave = false;
			}
			
			workPlanType = workPlanController.doSaveSalesOrder2(workPlanType);
			logger.info("对工作计划类型进行修改！");
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
			WorkPlanType workPlanType = workPlanController.doListEntityById2(id);
			if (null != workPlanType) {
				workPlanController.doDeleteByEntity(workPlanType);
				logger.info("");
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
			logger.info("删除工作计划类型设置信息");
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public WorkPlanController getWorkPlanController() {
		return workPlanController;
	}

	public void setWorkPlanController(WorkPlanController workPlanController) {
		this.workPlanController = workPlanController;
	}

	public List<WorkPlanType> getWorkPlanTypeList() {
		return workPlanTypeList;
	}

	public void setWorkPlanTypeList(List<WorkPlanType> workPlanTypeList) {
		this.workPlanTypeList = workPlanTypeList;
	}

	public WorkPlanType getWorkPlanType() {
		return workPlanType;
	}

	public void setWorkPlanType(WorkPlanType workPlanType) {
		this.workPlanType = workPlanType;
	}

	public void setEntity(WorkPlanType entity) {
		this.entity = entity;
	}

	public void setEntityForm(WorkPlanType entityForm) {
		this.entityForm = entityForm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public WorkPlanType getEntity() {
		return entity;
	}

	public WorkPlanType getEntityForm() {
		return entityForm;
	}

}
