package com.vix.oa.personaloffice.action;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.vix.common.base.action.BaseAction;
import com.vix.common.security.util.SecurityUtil;
import com.vix.core.constant.SearchCondition;
import com.vix.core.utils.StrUtils;
import com.vix.core.web.Pager;
import com.vix.oa.personaloffice.entity.WorkLogType;

/**
 * 工作日志类型
 */
@Controller
@Scope("prototype")
public class WorkLogTypeAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String id;
	private WorkLogType workLogType;
	private String name1;

	/** 获取列表数据 */
	public String goListContent() {
		try {
			System.out.println(SecurityUtil.getCurrentUserTenantId());
			Map<String, Object> params = getParams();
			if (name1 != null && !"".equals(name1)) {
				params.put("title", "%" + name1 + "%");
			}

			/*
			 * wxpKnowLedgeService.findPagerByHqlConditions(getPager(),
			 * WxpKnowLedge.class, params);
			 */

			Pager pager = this.getPager();
			pager.setPageSize(20);
			String code = getRequestParameter("code");
			if (StrUtils.isNotEmpty(code)) {
				params.put("code," + SearchCondition.EQUAL, code);
			}
			String name = getDecodeRequestParameter("name");
			if (StrUtils.isNotEmpty(name)) {
				params.put("name," + SearchCondition.ANYLIKE, name);
			}

			pager = this.baseHibernateService.findPagerByHqlConditions(pager,
					WorkLogType.class, params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "goListContent";
	}

	/** 跳转至用户修改页面 */
	public String goSaveOrUpdate() {
		try {
			if (StringUtils.isNotEmpty(id) && !id.equals("0")) {
				workLogType = baseHibernateService.findEntityById(
						WorkLogType.class, id);
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
			if (null != workLogType
					&& StrUtils.objectIsNotNull(workLogType.getId())) {
				isSave = false;
				workLogType.setUpdateTime(new Date());
			} else {
				workLogType.setCreateTime(new Date());
				loadCommonData(workLogType);// 载入数据公司码
			}
			System.out.println(SecurityUtil.getCurrentUserTenantId());
			workLogType = baseHibernateService.merge(workLogType);
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
			WorkLogType pb = baseHibernateService.findEntityById(
					WorkLogType.class, id);
			if (null != pb) {
				baseHibernateService.deleteByEntity(pb);
				renderText(DELETE_SUCCESS);
			} else {
				setMessage(getText("sys_BrandNotExist"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			renderText(DELETE_FAIL);
		}
		return UPDATE;
	}

	public String goSearch() {
		return "goSearch";
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

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

}