package com.vix.drp.newMediaManagement.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.newMediaManagement.controller.NewMediaManagementController;
import com.vix.drp.newMediaManagement.entity.NewMedia;

@Controller
@Scope("prototype")
public class NewMediaManagementAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private NewMediaManagementController newMediaManagementController;
	private String id;
	private NewMedia newMedia;
	private List<NewMedia> newMediaList;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = newMediaManagementController.findPagerByHqlConditions(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				newMedia = newMediaManagementController.doListNewMediaById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/**
	 * 
	 * @return
	 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (null != newMedia.getId() && !"".equals(newMedia.getId())) {
				isSave = false;
			}
			newMedia.setIsTemp("");
			newMedia = newMediaManagementController.doSaveNewMedia(newMedia);
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
			NewMedia newMedia = newMediaManagementController.doListNewMediaById(id);
			if (null != newMedia) {
				newMediaManagementController.doDeleteByEntity(newMedia);
				renderText(DELETE_SUCCESS);
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

	public NewMedia getNewMedia() {
		return newMedia;
	}

	public void setNewMedia(NewMedia newMedia) {
		this.newMedia = newMedia;
	}

	public List<NewMedia> getNewMediaList() {
		return newMediaList;
	}

	public void setNewMediaList(List<NewMedia> newMediaList) {
		this.newMediaList = newMediaList;
	}

}
