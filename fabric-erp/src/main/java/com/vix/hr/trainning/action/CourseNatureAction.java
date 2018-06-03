package com.vix.hr.trainning.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.web.Pager;
import com.vix.hr.trainning.entity.CourseNature;
import com.vix.hr.trainning.service.ITrainingCourseService;

/**
 * @Description: 课程性质
 * @author bobchen
 */
@Controller
@Scope("prototype")
public class CourseNatureAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITrainingCourseService trainingCourseService;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	private String id;
	private CourseNature courseNature;

	@Override
	public String goList() {
		return GO_LIST;
	}

	/** 获取课程性质列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = trainingCourseService.findPagerByHqlConditions(getPager(), CourseNature.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转课程性质至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id) {
				courseNature = trainingCourseService.findEntityById(CourseNature.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理课程性质修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != courseNature.getId()) {
				isSave = false;
			}
			courseNature = trainingCourseService.merge(courseNature);
			initEntityBaseController.initEntityBaseAttribute(courseNature);
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

	/** 处理课程性质删除操作 */
	public String deleteById() {
		try {
			CourseNature pb = trainingCourseService.findEntityById(CourseNature.class, id);
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

	public CourseNature getCourseNature() {
		return courseNature;
	}

	public void setCourseNature(CourseNature courseNature) {
		this.courseNature = courseNature;
	}

	public InitEntityBaseController getInitEntityBaseController() {
		return initEntityBaseController;
	}

	public void setInitEntityBaseController(InitEntityBaseController initEntityBaseController) {
		this.initEntityBaseController = initEntityBaseController;
	}

}
