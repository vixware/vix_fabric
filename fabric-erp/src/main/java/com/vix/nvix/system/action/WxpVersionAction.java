package com.vix.nvix.system.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.id.VixUUID;
import com.vix.core.constant.SearchCondition;
import com.vix.core.web.Pager;
import com.vix.nvix.common.base.action.VixntBaseAction;
import com.vix.oa.task.typeSettings.entity.TaskType;
import com.vix.oa.task.typeSettings.entity.WxpVersion;

/**
 * 任务类型设置
 * 
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class WxpVersionAction extends VixntBaseAction {

	private static final long serialVersionUID = 1L;
	private String id;
	private WxpVersion wxpVersion;

	/** 跳转到列表页面 */
	@Override
	public String goList() {
		return GO_LIST;
	}

	/** 获取列表数据 */
	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = getPager();
			// 排序
			if (null == pager.getOrderField() || "".equals(pager.getOrderField())) {
				pager.setOrderBy("desc");
				pager.setOrderField("id");
			}
			String searchName = getDecodeRequestParameter("searchName");
			if (StringUtils.isNotEmpty(searchName)) {
				params.put("name," + SearchCondition.ANYLIKE, searchName);
			}
			pager = vixntBaseService.findPagerByHqlConditions(pager, TaskType.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				wxpVersion = vixntBaseService.findEntityById(WxpVersion.class, id);
			} else {
				wxpVersion = new WxpVersion();
				wxpVersion.setCode(VixUUID.getUUID());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (wxpVersion != null && StringUtils.isNotEmpty(wxpVersion.getId())) {
				isSave = false;
			}
			initEntityBaseController.initEntityBaseAttribute(wxpVersion);
			wxpVersion = vixntBaseService.merge(wxpVersion);
			if (isSave) {
				renderText(SAVE_SUCCESS);
			} else {
				renderText("更新成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (isSave) {
				renderText(SAVE_FAIL);
			} else {
				renderText("更新失败");
			}
		}
		return UPDATE;
	}

	public void deleteTaskTypeById() {
		try {
			if (StringUtils.isNotEmpty(id)) {
				wxpVersion = vixntBaseService.findEntityById(WxpVersion.class, id);
				if (null != wxpVersion) {
					vixntBaseService.deleteByEntity(wxpVersion);
					renderText(DELETE_SUCCESS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public WxpVersion getWxpVersion() {
		return wxpVersion;
	}

	public void setWxpVersion(WxpVersion wxpVersion) {
		this.wxpVersion = wxpVersion;
	}

}
