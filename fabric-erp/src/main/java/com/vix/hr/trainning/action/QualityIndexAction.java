package com.vix.hr.trainning.action;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.web.Pager;
import com.vix.hr.trainning.entity.QualityIndex;
import com.vix.hr.trainning.service.ITrainingCourseService;

/**
 * @Description: 素质指标
 * @author bobchen
 */
@Controller
@Scope("prototype")
public class QualityIndexAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITrainingCourseService trainingCourseService;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	private String id;
	private QualityIndex qualityIndex;

	@Override
	public String goList() {
		return GO_LIST;
	}

	/** 获取素质指标列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = trainingCourseService.findPagerByHqlConditions(getPager(), QualityIndex.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转素质指标至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (null != id) {
				qualityIndex = trainingCourseService.findEntityById(QualityIndex.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理素质指标修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != qualityIndex.getId()) {
				isSave = false;
			}
			qualityIndex = trainingCourseService.merge(qualityIndex);
			initEntityBaseController.initEntityBaseAttribute(qualityIndex);
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
			QualityIndex pb = trainingCourseService.findEntityById(QualityIndex.class, id);
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

	public QualityIndex getQualityIndex() {
		return qualityIndex;
	}

	public void setQualityIndex(QualityIndex qualityIndex) {
		this.qualityIndex = qualityIndex;
	}

	public InitEntityBaseController getInitEntityBaseController() {
		return initEntityBaseController;
	}

	public void setInitEntityBaseController(InitEntityBaseController initEntityBaseController) {
		this.initEntityBaseController = initEntityBaseController;
	}

}
