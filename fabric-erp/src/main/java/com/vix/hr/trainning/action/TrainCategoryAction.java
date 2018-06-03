package com.vix.hr.trainning.action;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.web.Pager;
import com.vix.hr.trainning.entity.TrainCategory;
import com.vix.hr.trainning.service.ITrainingCourseService;
/**
 * @Description: 培训类别
 * @author bobchen
 */
@Controller
@Scope("prototype")
public class TrainCategoryAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITrainingCourseService trainingCourseService;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	private String id;
	private TrainCategory trainCategory;

	@Override
	public String goList() {
		return GO_LIST;
	}

	/** 获取培训类别列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = trainingCourseService.findPagerByHqlConditions(getPager(), TrainCategory.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转培训类别至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id) {
				trainCategory = trainingCourseService.findEntityById(TrainCategory.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理培训类别修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != trainCategory.getId()) {
				isSave = false;
			}
			trainCategory = trainingCourseService.merge(trainCategory);
			initEntityBaseController.initEntityBaseAttribute(trainCategory);
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

	/** 处理培训类别删除操作 */
	public String deleteById() {
		try {
			TrainCategory pb = trainingCourseService.findEntityById(TrainCategory.class, id);
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

	public TrainCategory getTrainCategory() {
		return trainCategory;
	}

	public void setTrainCategory(TrainCategory trainCategory) {
		this.trainCategory = trainCategory;
	}

	public InitEntityBaseController getInitEntityBaseController() {
		return initEntityBaseController;
	}

	public void setInitEntityBaseController(InitEntityBaseController initEntityBaseController) {
		this.initEntityBaseController = initEntityBaseController;
	}

}
