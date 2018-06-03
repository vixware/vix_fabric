package com.vix.drp.tvMediaManagement.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.drp.tvMediaManagement.controller.TvMediaManagementController;
import com.vix.drp.tvMediaManagement.entity.TvMedia;

@Controller
@Scope("prototype")
public class TvMediaManagementAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private TvMediaManagementController tvMediaManagementController;
	private String id;
	private TvMedia tvMedia;
	private List<TvMedia> tvMediaList;

	/** 获取列表数据 */
	public String goSingleList() {
		try {
			Map<String, Object> params = getParams();
			// 过滤临时数据
			params.put("isTemp," + SearchCondition.NOEQUAL, "1");
			Pager pager = tvMediaManagementController.findPagerByHqlConditions(params, getPager());
			setPager(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SINGLE_LIST;
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !"0".equals(id)) {
				tvMedia = tvMediaManagementController.doListTvMediaById(id);
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
			if (null != tvMedia.getId() && !"".equals(tvMedia.getId())) {
				isSave = false;
			}
			tvMedia.setIsTemp("");
			tvMedia = tvMediaManagementController.doSaveTvMedia(tvMedia);
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
			TvMedia tvMedia = tvMediaManagementController.doListTvMediaById(id);
			if (null != tvMedia) {
				tvMediaManagementController.doDeleteByEntity(tvMedia);
				renderText(DELETE_SUCCESS);
			} else {
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

	public TvMedia getTvMedia() {
		return tvMedia;
	}

	public void setTvMedia(TvMedia tvMedia) {
		this.tvMedia = tvMedia;
	}

	public List<TvMedia> getTvMediaList() {
		return tvMediaList;
	}

	public void setTvMediaList(List<TvMedia> tvMediaList) {
		this.tvMediaList = tvMediaList;
	}

}
