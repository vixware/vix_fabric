package com.vix.hr.initialSetup.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.web.Pager;
import com.vix.hr.initialSetup.entity.TechnicalLevel;
import com.vix.hr.trainning.service.ITrainingCourseService;

/**
 * 
 * @ClassName: TechnicalLevelAction
 * @Description: 技术水平
 * @author bobchen
 * @date 2015年11月18日 下午3:04:10
 *
 */
@Controller
@Scope("prototype")
public class TechnicalLevelAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITrainingCourseService trainingCourseService;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	private String id;
	private TechnicalLevel technicalLevel;

	@Override
	public String goList() {
		return GO_LIST;
	}

	/** 获取技术水平列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = trainingCourseService.findPagerByHqlConditions(getPager(), TechnicalLevel.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转技术水平至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				technicalLevel = trainingCourseService.findEntityById(TechnicalLevel.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理技术水平修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(technicalLevel.getId()) && !"0".equals(technicalLevel.getId())) {
				isSave = false;
			}
			technicalLevel = trainingCourseService.merge(technicalLevel);
			initEntityBaseController.initEntityBaseAttribute(technicalLevel);
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

	/** 处理技术水平删除操作 */
	public String deleteById() {
		try {
			TechnicalLevel pb = trainingCourseService.findEntityById(TechnicalLevel.class, id);
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

	public TechnicalLevel getTechnicalLevel() {
		return technicalLevel;
	}

	public void setTechnicalLevel(TechnicalLevel technicalLevel) {
		this.technicalLevel = technicalLevel;
	}

	public InitEntityBaseController getInitEntityBaseController() {
		return initEntityBaseController;
	}

	public void setInitEntityBaseController(InitEntityBaseController initEntityBaseController) {
		this.initEntityBaseController = initEntityBaseController;
	}

}
