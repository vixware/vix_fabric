package com.vix.nvix.oa.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.oa.personaloffice.entity.WorkLogType;

/**
 * 设置工作日志类型
 */
@Controller
@Scope("prototype")
public class NvixWorkLogTypeAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private WorkLogType workLogType;

	public void goSingleList() {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			Pager pager = baseHibernateService.findPagerByHqlConditions(getPager(), WorkLogType.class, params);
			renderDataTable(pager);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				workLogType = baseHibernateService.findEntityById(WorkLogType.class, id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return GO_SAVE_OR_UPDATE;
	}

	/** 处理修改操作 */
	public String saveOrUpdate() {
		boolean isSave = true;
		try {
			if (StrUtils.objectIsNotNull(workLogType.getId())) {
				isSave = false;
				workLogType.setUpdateTime(new Date());
			} else {
				workLogType.setCreateTime(new Date());
			}
			workLogType = baseHibernateService.mergeOriginal(workLogType);
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
			WorkLogType pb = baseHibernateService.findEntityById(WorkLogType.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
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

	public WorkLogType getWorkLogType() {
		return workLogType;
	}

	public void setWorkLogType(WorkLogType workLogType) {
		this.workLogType = workLogType;
	}

}