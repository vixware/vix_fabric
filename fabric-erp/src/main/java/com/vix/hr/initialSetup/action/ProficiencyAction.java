package com.vix.hr.initialSetup.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.web.Pager;
import com.vix.hr.initialSetup.entity.Proficiency;
import com.vix.hr.trainning.service.ITrainingCourseService;

/**
 * 
 * @ClassName: ProficiencyAction
 * @Description: 熟练程度
 * @author bobchen
 * @date 2015年11月18日 下午4:28:59
 *
 */
@Controller
@Scope("prototype")
public class ProficiencyAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITrainingCourseService trainingCourseService;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	private String id;
	private Proficiency proficiency;

	@Override
	public String goList() {
		return GO_LIST;
	}

	/** 获取课程类别列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = trainingCourseService.findPagerByHqlConditions(getPager(), Proficiency.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转课程类别至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				proficiency = trainingCourseService.findEntityById(Proficiency.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理课程类别修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(proficiency.getId()) && !"0".equals(proficiency.getId())) {
				isSave = false;
			}
			proficiency = trainingCourseService.merge(proficiency);
			initEntityBaseController.initEntityBaseAttribute(proficiency);
			if (isSave) {
				setMessage(SAVE_SUCCESS);
			} else {
				setMessage(UPDATE_SUCCESS);
			}
			if (isSave) {
				setMessage(SAVE_SUCCESS);
			} else {
				setMessage(UPDATE_SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				this.setMessage(SAVE_FAIL);
			} else {
				this.setMessage(UPDATE_FAIL);
			}
		}
		return UPDATE;
	}

	/** 处理素质指标删除操作 */
	public String deleteById() {
		try {
			Proficiency pb = trainingCourseService.findEntityById(Proficiency.class, id);
			if (null != pb) {
				trainingCourseService.deleteByEntity(pb);
				setMessage(DELETE_SUCCESS);
			} else {
				setMessage(getText("ec_brandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ITrainingCourseService getTrainingCourseService() {
		return trainingCourseService;
	}

	public void setTrainingCourseService(ITrainingCourseService trainingCourseService) {
		this.trainingCourseService = trainingCourseService;
	}

	public Proficiency getProficiency() {
		return proficiency;
	}

	public void setProficiency(Proficiency proficiency) {
		this.proficiency = proficiency;
	}

	public InitEntityBaseController getInitEntityBaseController() {
		return initEntityBaseController;
	}

	public void setInitEntityBaseController(InitEntityBaseController initEntityBaseController) {
		this.initEntityBaseController = initEntityBaseController;
	}

}
