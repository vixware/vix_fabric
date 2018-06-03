package com.vix.hr.initialSetup.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.base.controller.InitEntityBaseController;
import com.vix.core.web.Pager;
import com.vix.hr.initialSetup.entity.TitleType;
import com.vix.hr.trainning.service.ITrainingCourseService;

/**
 * 
 * @ClassName: TitleTypeAction
 * @Description: 职称类型
 * @author bobchen
 * @date 2015年11月18日 上午11:31:29
 *
 */
@Controller
@Scope("prototype")
public class TitleTypeAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ITrainingCourseService trainingCourseService;
	@Autowired
	private InitEntityBaseController initEntityBaseController;
	private String id;
	private TitleType titleType;

	@Override
	public String goList() {
		return GO_LIST;
	}

	/** 获取职称类型列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			Pager pager = trainingCourseService.findPagerByHqlConditions(getPager(), TitleType.class, params);
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	/** 跳转职称类型至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				titleType = trainingCourseService.findEntityById(TitleType.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理职称类型修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StringUtils.isNotEmpty(titleType.getId()) && !"0".equals(titleType.getId())) {
				isSave = false;
			}
			titleType = trainingCourseService.merge(titleType);
			initEntityBaseController.initEntityBaseAttribute(titleType);
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

	/** 处理职称类型删除操作 */
	public String deleteById() {
		try {
			TitleType pb = trainingCourseService.findEntityById(TitleType.class, id);
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

	public TitleType getTitleType() {
		return titleType;
	}

	public void setTitleType(TitleType titleType) {
		this.titleType = titleType;
	}

	public InitEntityBaseController getInitEntityBaseController() {
		return initEntityBaseController;
	}

	public void setInitEntityBaseController(InitEntityBaseController initEntityBaseController) {
		this.initEntityBaseController = initEntityBaseController;
	}

}
